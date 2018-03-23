package kr.or.ddit.member.controller;

import java.util.List;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.IMemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberListAction {
	List<MemberVO> memberList;
	
	public String execute(){
		IMemberService service = IMemberServiceImpl.getInstance();
		memberList = service.getMemberList();
		
		return "success";
	}

	public List<MemberVO> getMemberList() {
		return memberList;
	}
}
