package kr.or.ddit.join.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.IMemberServiceImpl;
import kr.or.ddit.utils.CryptoGenerator;
import kr.or.ddit.vo.MemberVO;

// http://localhost/StrutsToddler/join/loginCheck.do
//mem_id=alsdk;a32342w3409jfvl4jkhkuhjdfalkl5tj98098&mem_pass=al;sdf;;2l3io;ljfoia 8983whi*(8fsfjkqj3hkH@kjdaf
public class LoginCheckAction {
	
	private String mem_id;
	private String mem_pass;
	private String message;
	
	public String execute(){
		HttpServletRequest requset = ServletActionContext.getRequest();
		HttpSession session = requset.getSession();
		//암호문을 세션 내 저장된 비밀키를 활용해서 평문으로 변경.
//		mem_id = CryptoGenerator.decryptoRSA(session, mem_id);
//		mem_pass = CryptoGenerator.decryptoRSA(session, mem_pass);
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("mem_id", mem_id);
		params.put("mem_pass", mem_pass);
		
		IMemberService service = IMemberServiceImpl.getInstance();
		MemberVO memberInfo = service.getMemberInfo(params);
		
		if( memberInfo == null ){
			message ="회원이 아닙니다.";
			return "loginForm";
		}else{
			session.setAttribute("LOGIN_MEMBERINFO", memberInfo);
			return "memberList";
		}
	}
	
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public void setMem_pass(String mem_pass) {
		this.mem_pass = mem_pass;
	}

	public String getMessage() {
		return message;
	}
}
