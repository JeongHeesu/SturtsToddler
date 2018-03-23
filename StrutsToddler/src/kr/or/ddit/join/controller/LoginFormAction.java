package kr.or.ddit.join.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import kr.or.ddit.utils.CryptoGenerator;

public class LoginFormAction {
	// 액션 클래스 내에 전역으로 선언된 변수는 포워딩 된 jsp에 값 전달시 
	// 해당 변수명을 키로 getter를 통해 전달.
	//ValueStack : HttpServletRequest의 스트럿츠 확장판
	private Map<String, String> publicKeyMap;
	
	// 스크럿츠 프레임웍의 액션 클래스를 정의하고, default 콜백되는 메서드(execute()) 정의
	public String execute(){
		HttpServletRequest requset = ServletActionContext.getRequest();
		HttpSession session = requset.getSession();
		publicKeyMap = CryptoGenerator.getGeneratorKey(session);
		
		return "success";
	}
	
	public Map<String, String> getPublicKeyMap() {
		//HttpServletRequest.setAttribute("전역변수명", getter를 통해서 저장)과 동일.
		return publicKeyMap;
	}
}
