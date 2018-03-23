package kr.or.ddit.utils;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
/**
 * @Class Name : RolePagingUtil.java
 * @Description : 게시판 페이지처리
 * @Modification Information
 * @author 정희수
 * @since  2018.03.15.
 * @version 1.0
 * <pre>
 * << 개정이력(Modification Information) >>
 *    수정일       수정자          수정내용
 *    -------      -------     -------------------
 *    2018.03.15.  정희수      최초작성
 * Copyright (c) 2018 by DDIT  All right reserved
 * </pre>
 */
public class RolePagingUtil {
	private int currentPage; //현재페이지
	private int totalCount; //총 게시글 개수
	private int totalPage; //전체 페이지개수
	private int blockCount = 10; //페이지 당 출력될 게시글의 개수
	private int blockPage = 5;   //  페이지 네비게이션 메뉴 개수
	private int startPage; //페이지 네비게이션 메뉴의 시작 페이지 개수
	private int endPage; //페이지 네비게이션 메뉴의 끝 페이지 개수
	private int startCount; //페이지 내 시작 게시글 번호
	private int endCount ; //페이지내 끝 게시글 번호
	private String params = ""; // main.jsp?currnetPagae=1
	private StringBuffer pageHtmls; //문자열 베이스의 페이지 네비게이션 컨텐츠(html)
	private HttpServletRequest request;
	
	public RolePagingUtil(int currentPage, int totalCount, HttpServletRequest request){
		this.currentPage = currentPage;
		this.totalCount = totalCount;
		this.request = request;
		this.pageHtmls = new StringBuffer();
		makePagingHtml();
	}

	private void makePagingHtml() {
		//전체 페이지 개수
		this.totalPage = (int)Math.ceil(this.totalCount/(double) this.blockCount);
		if(this.totalCount == 0){
			this.totalPage =1;
		}
		
		//페이지 내 시작 게시글 번호, 끝 게시글 번호
		this.startCount = this.totalCount - (this.currentPage -1) * this.blockCount;
		this.endCount = this.startCount - this.blockCount +1;
		if(this.endCount<0){
			this.endCount = 1;
		}
		
		//이전|1|2|3|4|5|다음
		this.startPage = ((this.currentPage - 1) / this.blockPage*this.blockPage) +1;
		this.endPage = this.startPage + this.blockPage -1 ;
		if(this.endPage > this.totalPage){
			this.endPage = this.totalPage;
		}
		
		//main.jsp or main.jsp?vurtnaPatd=23&serzahckeycode=~~
		Enumeration<String> paramkeys = request.getParameterNames();
		while(paramkeys.hasMoreElements()){
			String key = paramkeys.nextElement();
			if("currentPage".intern() != key.intern()){
				String[] values = request.getParameterValues(key);
				for(String value : values){
					params += key+"="+value+"&";
				}
			}
		}
		
		this.pageHtmls.append("<div class='text-center'>");
		this.pageHtmls.append("<ul class='pagination mtm mbm'>");
		
		//main.jsp?contentPage=/12/freeboard/freeboardList.jsp
		String requestURI = request.getRequestURI();
		
		
		//이전|1|2|3빨간핵|4|5|다음
		if((this.currentPage - 1) == 0){
			this.pageHtmls.append("<li class='disabled'><a href='#'>&laquo;</a></li>");
		}else{
			this.pageHtmls.append("<li><a href='" + requestURI + "?currentPage=" + 
									(this.currentPage - 1) +"'>&laquo;</a></li>");
		}
		
		for(int i=this.startPage; i<=this.endPage; i++){
			if(this.currentPage == i){
				this.pageHtmls.append("<li class='active'><a href='#'>"+i+"</a></li>");
			}else{
				this.pageHtmls.append("<li><a href='" + requestURI + "?currentPage=" + 
						i +"'>"+i+"</a></li>");
			}
		}//for 끗
		
		if(this.currentPage < this.totalPage){
			this.pageHtmls.append("<li><a href='" + requestURI + "?currentPage=" + 
					(this.currentPage + 1) +"'>&raquo;</a></li>");
		}else{
			this.pageHtmls.append("<li class='disabled'><a href='#'>&raquo;</a></li>");
		}
		
		this.pageHtmls.append("</ul></div>");
		
	}

	public int getStartCount() {
		return startCount;
	}

	public int getEndCount() {
		return endCount;
	}

	public StringBuffer getPageHtmls() {
		return pageHtmls;
	}
	
}
