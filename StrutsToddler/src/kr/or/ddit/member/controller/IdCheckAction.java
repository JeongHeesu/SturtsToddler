package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.IMemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

public class IdCheckAction {
	
	private String mem_id;
	private MemberVO memberInfo;
	
	public void execute(){
		Map<String, String> params = new HashMap<String, String>();
		params.put("mem_id", mem_id);
		
		IMemberService service = IMemberServiceImpl.getInstance();
		memberInfo = service.getMemberInfo(params);
		
		Map<String, String> resultMap = new HashMap<String, String>();
		if(memberInfo == null){
			resultMap.put("flag", "true");
		}else{
			resultMap.put("flag", "false");
		}
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonData = mapper.writeValueAsString(resultMap);
			
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=UTF-8"); 
			
			PrintWriter out = response.getWriter();
			out.println(jsonData);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	
}
