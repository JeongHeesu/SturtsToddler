package kr.or.ddit.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContextEvent;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.Dispatcher;
import org.apache.struts2.tiles.StrutsTilesListener;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionProxyFactory;

import junit.framework.TestCase;
// 클라이언트 ------------------------------------->스트럿츠 프레임웍
//													struts.xml
//														member.xml.....
//													팩키지의 namespace찾고.. 액션의 name 찾고
//													액션클래스 인스턴스 후 액션메서드(execute())콜백
public abstract class BaseStrutsTestCase extends TestCase {
	//클라이언트의 요청시 서블릿패스를 기준으로 팩키지와, 액션을 찾아서 액션클래스 인스턴스화 후
	// 액션메서드 콜백.
	protected static ActionProxy proxy = null;
	// Mock Object HttpServletRequest
	protected MockHttpServletRequest request;
	// Mock Object HttpServletResponse
	protected MockHttpServletResponse response;
	// session.setAttribute(키, 값), session.getAttribute(키)
	// Map패턴으로 활용
	private static Map<String, Object> sessionMap;
	//포워딩 Mock Object
	protected static Dispatcher dispatcher;
	// Mock Object application 기본객체
	protected static MockServletContext servletContext; 
	
	public BaseStrutsTestCase(String name) {
		super(name);
	}
	
	static {
		sessionMap = new HashMap<String, Object>();
		  
		servletContext = new MockServletContext();
		servletContext.addInitParameter("org.apache.tiles.impl.BasicTilesContainer.DEFINITIONS_CONFIG", 
				                        "/WEB-INF/tiles/tiles-def.xml");
		final StrutsTilesListener tilesListener = new StrutsTilesListener();
		final ServletContextEvent event = new ServletContextEvent(servletContext);
		tilesListener.contextInitialized(event);
		  
		dispatcher = new Dispatcher(servletContext, new HashMap<String, String>());
		dispatcher.init();
		Dispatcher.setInstance(dispatcher);
	}
	  
	

	protected <T> T createAction(final Class<T> clazz, final String namespace, 
			                     final String actionName, final String methodName) throws Exception {
	  
		proxy = dispatcher.getContainer().getInstance(ActionProxyFactory.class).createActionProxy(namespace, actionName, getSessionMap(), true, false);
		proxy.setMethod(methodName);
		  
		ActionContext.setContext(proxy.getInvocation().getInvocationContext());
		
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		  
		final String pathIntroducer = (namespace.endsWith("/")) ? namespace : namespace + "/";
		request.setServletPath(pathIntroducer + actionName + ".do");
		ServletActionContext.setRequest(request);
		ServletActionContext.setResponse(response);
		ServletActionContext.setServletContext(servletContext);
		  
		return (T) proxy.getAction();
	}
	  
	protected static int getSessionMapSize() {
		return sessionMap.size();
	}
	  
	protected static Object getSessionValue(final String name) {
		if (sessionMap.containsKey(name)) {
			return sessionMap.get(name);
		}
		return null;
	}
	  
	protected static boolean sessionContains(final String name) {
		return sessionMap.containsKey(name);
	}
	  
	protected static void setSessionValue(final String name, final Object value) {
		sessionMap.put(name, value);
	}
	
	public static Map<String, Object> getSessionMap() {
		return sessionMap;
	}
//	@Before
//	protected void throws Exception {
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		sessionMap.clear();
	} 
}
