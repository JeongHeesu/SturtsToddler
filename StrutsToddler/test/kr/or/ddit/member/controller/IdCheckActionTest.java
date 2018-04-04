package kr.or.ddit.member.controller;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import ch.qos.logback.classic.Logger;

import kr.or.ddit.utils.BaseStrutsTestCase;
import kr.or.ddit.utils.LogBackInitialize;

public class IdCheckActionTest extends BaseStrutsTestCase {

	private final Logger logger;
	
	public IdCheckActionTest(String name) {
		super(name);
		logger = LogBackInitialize.logbackInit().getLogger(IdCheckActionTest.class);
	}

	@Test
	public void testExecute() throws Exception {
		createAction(IdCheckAction.class, "/member", "idCheck", "execute");
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("mem_id", "a001");
		
		proxy.getInvocation().getInvocationContext().setParameters(params);
		
		String returnValue = proxy.execute();
		String encodingType = response.getCharacterEncoding();
		String contentType = response.getContentType();
		
		String content = response.getContentAsString();
		
		assertNull(returnValue);
		assertEquals("application/json; charset=UTF-8", contentType);
		assertEquals("UTF-8", encodingType);
//		assertEquals("{\"flag\":\"false\"}", content);
		assertNotNull(content);
		
		
	}

}
