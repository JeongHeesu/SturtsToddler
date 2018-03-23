package kr.or.ddit.member.controller;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.IMemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

//t설정 파일 : <interceptor-ref name="modelDriven"></interceptor-ref>
// 앳견 클래스 implement modeldriven
public class UpdateMemberAction implements Action, ModelDriven<MemberVO> {	
	private MemberVO memberInfo;
	
	@Override
	public String execute() throws Exception {
		IMemberService service = IMemberServiceImpl.getInstance();
		service.updateMemberInfo(this.memberInfo);
		return SUCCESS;
	}

	@Override
	public MemberVO getModel() {
		//클라이언트에서 전송되는 파라메터의 키와 일치하는 변수와 
		//setter/getter를 포함하는 VO의 인스턴스르 GetModel()를 통해서 반환하면 ValueStack에 임시저장 후
		// params 인터셉터가 valueStack 내에 존재하는 getNodle()로부터 반환된 VO를 대상으로 파라메터 값 셋팅 수행.
		memberInfo = new MemberVO();
		return memberInfo;
	}

}
