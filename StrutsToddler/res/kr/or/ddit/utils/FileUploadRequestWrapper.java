package kr.or.ddit.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import kr.or.ddit.global.GlobalConstant;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.util.FileItemHeadersImpl;

/**
 * @Class Name : FileUploadRequestWrapper.java
 * @Description : 파일 올리는 클래스
 * @Modification Information
 * @author 정희수
 * @since  2018.03.14.
 * @version 1.0
 * @see HttpServletRequestWrapper
 * <pre>
 * << 개정이력(Modification Information) >>
 *    수정일       수정자          수정내용
 *    -------      -------     -------------------
 *    2018.03.14.  정희수      최초작성
 * Copyright (c) 2018 by DDIT  All right reserved
 * </pre>
 */

// FileUploadRequestWrapper is a HttpServletReques
public class FileUploadRequestWrapper extends HttpServletRequestWrapper {
	//클라이언트가 업로드처리시 폼필드와 파일이 혼재 전송
	//폼필드 처리와 
	private  Map<String, String[]> parameterMap;
	//파일 처리
	private Map<String, FileItem[]> fileItemMap;
	
	//일반요청과 파일업로드를 구분\
	// 일반요청 : requset.getparameter("mem_id")
	// 파일업로드 요청 : parametermap.get("mem_id)
	private boolean multipartFlag= false; 
	
	
	public FileUploadRequestWrapper(HttpServletRequest request) {
		this(request,-1,-1);
	}

	public FileUploadRequestWrapper(HttpServletRequest request, int threshold, int sizeMax){
		super(request);
		parsing(request, threshold, sizeMax);
	}
	
	private void parsing(HttpServletRequest request, int threshold, int sizeMax){
		multipartFlag = ServletFileUpload.isMultipartContent(request);
		if(multipartFlag){
			parameterMap = new HashMap<String, String[]>();
			fileItemMap = new HashMap<String, FileItem[]>();

			DiskFileItemFactory factory = new DiskFileItemFactory();
			// -1 => 사이즈 무제한
			factory.setSizeThreshold(threshold);
			
			factory.setRepository(new File(GlobalConstant.SAVE_PATH));

			ServletFileUpload fileUploadServlet = new ServletFileUpload(factory);
			fileUploadServlet.setSizeMax(sizeMax);

			List<FileItem> items;
			try {
				items = fileUploadServlet.parseRequest(request);
				for(FileItem item : items){
					String fieldName = item.getFieldName();
					if(item.isFormField()){
						//파일 업로드시 폼필드 parameterMap저장
						String value = item.getString("UTF-8");
						String[] values = parameterMap.get(fieldName);
						if(values == null){
							values = new String[]{value};
						}else{
							String[] temp = new String[values.length+1];
							System.arraycopy(values, 0, temp, 0, values.length);
							temp[temp.length-1] = value;
							
							values = temp;
						}
						parameterMap.put(fieldName, values);
					}else{
						//파일 업로드 시 파일 fileItemMap 저장
						if(item.getSize() > 0 ){
							FileItem[] values = fileItemMap.get(fieldName);
							if(values == null){
								values = new FileItem[]{item};
							}else{
								FileItem[] temp = new FileItem[values.length+1];
								System.arraycopy(values, 0, temp, 0, values.length);
								temp[temp.length-1] = item;
								values =  temp;
							}
							fileItemMap.put(fieldName, values);
							
						}
						
					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String getParameter(String name) {
		if(multipartFlag){
			String[] values = parameterMap.get(name);
			if(values==null){
				return null;
			}else{
				return values[0];
			}
		}else{
			return super.getParameter(name);
		}
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		if(multipartFlag){
			return parameterMap;
		}else{
			return super.getParameterMap();
		}
	}

	@Override
	public Enumeration<String> getParameterNames() {
		if(multipartFlag){
			return new Enumeration<String>() {
			Iterator<String> keys = parameterMap.keySet().iterator();

			@Override
			public boolean hasMoreElements() {
				return keys.hasNext();
			}

			@Override
			public String nextElement() {
				return keys.next();
			}
			
			};
		}else{
			return super.getParameterNames();
		}
	}

	@Override
	public String[] getParameterValues(String name) {
		if(multipartFlag){
			return parameterMap.get(name);
		}else{
			return super.getParameterValues(name);
		}
	}
	
	public FileItem getFileItem(String name){
		if(multipartFlag){
			FileItem[] items = fileItemMap.get(name);
			if(items == null ){
				return null;
			}else{
				return items[0];
			}
		}else{
			return null;
		}
	}
	
	public Map<String, FileItem[]> getFileItemMap(){
		if(multipartFlag){
			return fileItemMap;
		}else{
			return null;
		}
	}
	
	public FileItem[] getFileitemValues(String name){
		if(multipartFlag){
			return fileItemMap.get(name);
		}else{
			return null;
		}
	}
	
	
	
	
	
	
	
	
}
