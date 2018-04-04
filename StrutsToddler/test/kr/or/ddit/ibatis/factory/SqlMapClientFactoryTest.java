package kr.or.ddit.ibatis.factory;

import static org.junit.Assert.*;
import kr.or.ddit.utils.BaseStrutsTestCase;
import kr.or.ddit.utils.LogBackInitialize;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ch.qos.logback.classic.Logger;

public class SqlMapClientFactoryTest extends BaseStrutsTestCase {

	private final Logger logger;
	
	public SqlMapClientFactoryTest(String name){
		super(name);
		logger = LogBackInitialize.logbackInit().getLogger(SqlMapClientFactoryTest.class);
	}
	
	//테스트 케이스 코드(테스트 메서드) 명명규칙 : test + 메서드명 선두어를 대문자로 한 메서드명
	//										메서드명 선두에는 소무자로한 메서드명  + Test
	@Test
	public void testGetSqlMapClient() {
		logger.debug("찍힐까? 안찍히지..");
		Object returnValue = SqlMapClientFactory.getSqlMapClient();
		assertNotNull("널이 아님 ", returnValue );
	}
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//테스트 실행 종료 전에 1회 콜백
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		//@Test가 선언된 테스트 케이스 코드가 실행 전에 반복적으로 콕백
	}

	@Before
	public void setUp() throws Exception {
		//@Test가 선언된 테스트 케이스 코드가 실행 전에 반복적으로 콕백
	}

	@After
	public void tearDown() throws Exception {
		//@Test가 선언된 테스트 케이스 코드가 실행 종료 후에 반복적으로 콕백
	}


}
