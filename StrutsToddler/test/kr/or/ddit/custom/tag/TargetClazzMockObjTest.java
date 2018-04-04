package kr.or.ddit.custom.tag;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import kr.or.ddit.utils.LogBackInitialize;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import ch.qos.logback.classic.Logger;

@RunWith(MockitoJUnitRunner.class)
public class TargetClazzMockObjTest {

	private Logger logger;
	
	//test메서드 내에서는 mock(클래스명.class);
	@Mock
	private TargetClazzMockObj annotationMockObj;
	
	public TargetClazzMockObjTest(){
		logger = LogBackInitialize.logbackInit().getLogger(TargetClazzMockObjTest.class);
	}
	
	@Test
	public void testPlus() {
//		Mockito.mock(classToMock)
		//실제 구현 클래스를 MOCK Object로 Mock-up
		TargetClazzMockObj mockObj = mock(TargetClazzMockObj.class);
		
//		Mockito.when(methodCall)
		when(mockObj.plus(1, 2)).thenReturn(3);
		
		//Mock Object의 해당 메서드를 실제 호출 
		assertThat(3, is(mockObj.plus(1, 2)));
		//Mock Object의 해당 메서드가 실제 해당 파라메터로 호출 여부
		verify(mockObj,times(1)).plus(1, 2);
	}

	@Test
	public void testMulitple() {
		TargetClazzMockObj mockObj = mock(TargetClazzMockObj.class);
		
		when(mockObj.mulitple("1", 2)).thenAnswer(new Answer<Integer>() {
			@Override
			public Integer answer(InvocationOnMock invocation) throws Throwable {
				return 2;
			}
			
		});
		
		assertThat(2, is(mockObj.mulitple("1", 2)));
		verify(mockObj,times(1)).mulitple("1", 2);
	}

	@Test
	public void testToday() {
		TargetClazzMockObj mockObj = mock(TargetClazzMockObj.class);
		when(mockObj.today()).thenAnswer(new Answer<String>() {

			@Override
			public String answer(InvocationOnMock invocation) throws Throwable {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				String returnValue = dateFormat.format(new Date());
				return returnValue;
			}
		});
		
		assertEquals("2018/04/02", mockObj.today());
	}

	@Test
	public void testCharCount() {
		//@Mock이 선언된 전역변수의 실제 Mock-up을 수행하는 코드
		MockitoAnnotations.initMocks(this);
		
		logger.debug("@Mock을 활용해서 생성된 Mock Object : "+this.annotationMockObj);
		
		when(annotationMockObj.charCount("12345567", "5")).thenReturn(2);
		
		assertEquals(new Integer(2), annotationMockObj.charCount("12345567", "5"));
	}

}
