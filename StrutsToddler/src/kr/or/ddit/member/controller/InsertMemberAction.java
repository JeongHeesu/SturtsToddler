package kr.or.ddit.member.controller;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.IMemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class InsertMemberAction implements Action,ModelDriven<MemberVO> {
	private MemberVO memberInfo;
	
	@Override
	public String execute() throws Exception {
		IMemberService service = IMemberServiceImpl.getInstance();
		service.insertMemberInfo(memberInfo);
		return SUCCESS;
	}

	@Override
	public MemberVO getModel() {
		memberInfo = new MemberVO();
		return memberInfo;
	}

}
