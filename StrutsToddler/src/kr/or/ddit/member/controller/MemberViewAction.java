package kr.or.ddit.member.controller;

import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.Action;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.IMemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

//http://localhost/StrutsToddler/member/memberView.do?mem_id=asfasdf
public class MemberViewAction implements Action{

	private String mem_id;
	private MemberVO memberInfo;
	
	@Override
	public String execute() throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("mem_id", mem_id);
		
		IMemberService service = IMemberServiceImpl.getInstance();
		memberInfo = service.getMemberInfo(params);
		
		return SUCCESS;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public MemberVO getMemberInfo() {
		return memberInfo;
	}
	
	
}
