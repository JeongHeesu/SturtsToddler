package kr.or.ddit.member.controller;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import ch.qos.logback.classic.Logger;
import kr.or.ddit.utils.BaseStrutsTestCase;
import kr.or.ddit.utils.LogBackInitialize;
import kr.or.ddit.vo.MemberVO;

public class UpdateMemberActionTest extends BaseStrutsTestCase {
	private Logger logger;
	public UpdateMemberActionTest(String name) {
		super(name);
		logger = LogBackInitialize.logbackInit().getLogger(UpdateMemberActionTest.class);
	}

	@Test
	public void testExecute() throws Exception {
		UpdateMemberAction actionClazz = createAction(UpdateMemberAction.class, "/member", "updateMemberInfo", "execute");
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("mem_id", "a111");
		params.put("mem_pass", "asdf");
		params.put("mem_name", "홍길동");
		params.put("mem_add1", "대전광역시 중구 대흥동 500");
		params.put("mem_add2", "대덕인재개발원");
		params.put("mem_hometel", "042-222-8202");
		params.put("mem_comtel", "042-222-8202");
		params.put("mem_hp", "010-2222-8202");
		params.put("mem_job", "직업");
		params.put("mem_like", "취미");
		params.put("mem_zip", "111-111");
		params.put("mem_mail", "asdf@asdf.asd");
		
		proxy.getInvocation().getInvocationContext().setParameters(params);
		
		// 가상의 클라이언트 요청시 세션에 값 설정
		Map<String, Object> sessionParams = new HashMap<String, Object>();
		sessionParams.put("LOGIN_MEMBERINFO", new MemberVO());
		proxy.getInvocation().getInvocationContext().setSession(sessionParams);
		
		String returnValue = proxy.execute();
		assertEquals("해당 액션클래스의 액션 메서드 반환값", "success",returnValue);
		
		MemberVO model = actionClazz.getModel();
		assertTrue(model instanceof MemberVO);
		
		
	}

}

