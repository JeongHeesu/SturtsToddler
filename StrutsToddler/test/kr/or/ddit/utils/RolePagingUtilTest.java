package kr.or.ddit.utils;

import static org.junit.Assert.*;

import org.junit.Test;
import ch.qos.logback.classic.Logger;
import org.springframework.mock.web.MockHttpServletRequest;

public class RolePagingUtilTest {

	private final Logger logger;
	private final MockHttpServletRequest request;
	
	public RolePagingUtilTest(){
		logger = LogBackInitialize.logbackInit().getLogger(RolePagingUtilTest.class);
		request = new MockHttpServletRequest();
		request.setParameter("search_keycode", "TOTAL");
		request.setParameter("search_keyword", "테스트");
	}
	
	@Test
	public void testRolePagingUtilNomal() {
		try {
			new RolePagingUtil(1, 12, request);
			new RolePagingUtil(2, 21, request);
			
		} catch (Exception e) {
			fail("RolePageingUtil 클래스 인스턴스화 실패");
		}
	}
	@Test(expected=NullPointerException.class)
	public void testRolePagingUtilAbNomal() {
		new RolePagingUtil(1, 12, null);
	}

	@Test
	public void testGetStartCount() {
		RolePagingUtil pagingUtil = new RolePagingUtil(1, 12, request);
		assertNotEquals(Integer.class, pagingUtil.getStartCount());
		assertEquals(12, pagingUtil.getStartCount());
	}

	@Test
	public void testGetEndCount() {
		RolePagingUtil pagingUtil = new RolePagingUtil(1, 12, request);
		assertEquals(3, pagingUtil.getEndCount());
	}

	@Test
	public void testGetPageHtmls() {
		RolePagingUtil pagingUtil = new RolePagingUtil(1, 12, request);
		assertNotNull("널 아님",pagingUtil.getPageHtmls().toString());
	}

}
