package kr.or.ddit.join.controller;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import kr.or.ddit.utils.BaseStrutsTestCase;
import kr.or.ddit.utils.LogBackInitialize;

import org.junit.Test;
import ch.qos.logback.classic.Logger;

public class LoginCheckActionTest extends BaseStrutsTestCase {

	private final Logger logger;
	
	public LoginCheckActionTest(String name) {
		super(name);
		logger = LogBackInitialize.logbackInit().getLogger(LoginCheckActionTest.class);
	}

	@Test
	public void testExecuteNomkal() throws Exception {
		//가상의 서버 실행 환경 구성 완료된 상황에서(BaseStrutsTestCase.class 내 static{})
		//클라이언트의 /join/loginCheck.do 요청을 가상으로 설정하고 해당 요청시의 
		//대상 액션 클래스와 액션메서드 설정을.
		createAction(LoginCheckAction.class, "/join", "loginCheck", "execute");
		Map<String, String> params =  new HashMap<String, String>();
		params.put("mem_id", "a001");
		params.put("mem_pass", "asdfasdf");
		//가상ㅇ의 클라이언트 요청 시 생성되는 ServletActionContext에 요청 파라메터를 셋팅.
		proxy.getInvocation().getInvocationContext().setParameters(params);
		// 실제 Struts 팩키지의 네임스페이싀와 액션 네임으로 가상의 요청을 전달
		// 액션메서드 반환값 = returnValue
		String returnValue = proxy.execute();
		
		assertEquals("해당 액션 클래스의 액션 메서드 반환 값", "memberList", returnValue);
	}
	
	@Test
	public void testExecuteAbNomal() throws Exception {
		createAction(LoginCheckAction.class, "/join", "loginCheck", "execute");
		
		Map<String, String> params =  new HashMap<String, String>();
		params.put("mem_id", "a767");
		params.put("mem_pass", "dddddd");
		
		proxy.getInvocation().getInvocationContext().setParameters(params);
		
		String returnValue = proxy.execute();
		
		assertEquals("해당 액션 클래스의 액션 메서드 반환 값", "loginForm", returnValue);
		
	}

}
