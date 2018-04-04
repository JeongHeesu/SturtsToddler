package kr.or.ddit.custom.tag;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import kr.or.ddit.utils.LogBackInitialize;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.internal.runners.TestClass;
import ch.qos.logback.classic.Logger;

public class TargetClazzTest {

	private final Logger logger;
	
	public TargetClazzTest(){
		logger = LogBackInitialize.logbackInit().getLogger(TargetClazzTest.class);
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//해당 테스트 클래스에서테스트 최초 수행 전 1회 콜백
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		//해당 테스트 클래스에서 테스트 최종 완료 전 1회 콜백
	}

	@Before
	public void setUp() throws Exception {
		//해당 테스트 클래스 내 테스트 케이스 코드(테스트 메서드) 호출 전에 반복 콕백
	}

	@After
	public void tearDown() throws Exception {
		//해당 테스트 클래스 내 테스트 케이스 코드(테스트 메서드) 종료 후에 반복 콜백
	}

//	@Ignore : 테스트 무시 및 넘기고 싶을때 사용
	@Test
	public void testPlus() {
		logger.debug("testPlus() 테스트 수행");
		int returnValue = TargetClazz.plus(1, 2);
		assertEquals(3, returnValue);
		assertNotEquals(4, returnValue);
	}

	@Test
	public void testMulitple() {
		logger.debug("testMulitple() 테스트 수행");
		int returnValue = TargetClazz.mulitple("1", 2);
		assertEquals(2, returnValue);
		assertNotEquals(3, returnValue);
	}

	@Test
	public void testToday() {
		logger.debug("testToday() 테스트 수행");
		String returnValue = TargetClazz.today();
		assertEquals(new SimpleDateFormat("yyyy/MM/dd").format(new Date()), returnValue);
		assertNotEquals("1974/04/11", returnValue);
	}

	@Test
	public void testCharCount() {
		logger.debug("testCharCount() 테스트 수행");
		int returnValue = TargetClazz.charCount("12345567", "5");
		assertEquals(2, returnValue);
		assertNotEquals(3, returnValue);
	}

}
