<%--==============================================================
 * 회원의 리스트 출력폼
 * @author 정희수
 * @since  2018.03.22.
 * @version 1.0
 * <pre>
 * << 개정이력(Modification Information) >>
 *    수정일       	수정자          수정내용
 *    -------      -------     -------------------
 *    2018.03.22. 	 정희수    	최초작성 (스크립트) 
 *    2018.02.23. 	 정희수    	(스크립트) 작성 
 * Copyright (c) 2018 by DDIT  All right reserved
 * </pre>
===============================================================--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="list">
	<table class="mdl-data-table mdl-js-data-table mdl-data-table--selectable mdl-shadow--2dp" style="width: 100%;"
			id="memberListTbl">
		<thead>
			<tr>
				<th class="mdl-data-table__cell--non-numeric">아이디</th>
				<th class="mdl-data-table__cell--non-numeric">패스워드</th>
				<th class="mdl-data-table__cell--non-numeric">이름</th>
				<th class="mdl-data-table__cell--non-numeric">주민번호1</th>
				<th class="mdl-data-table__cell--non-numeric">주민번호2</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${memberList }" var="memberInfo">
			<tr>
				<td class="mdl-data-table__cell--non-numeric">${memberInfo.mem_id }</td>
				<td class="mdl-data-table__cell--non-numeric">${memberInfo.mem_pass }</td>
				<td class="mdl-data-table__cell--non-numeric">${memberInfo.mem_name }</td>
				<td class="mdl-data-table__cell--non-numeric">${memberInfo.mem_regno1 }</td>
				<td class="mdl-data-table__cell--non-numeric">${memberInfo.mem_regno2 }</td>
			</tr>
		</c:forEach>	
		</tbody>
	</table>
</div>
<div class="searchForm" align="right" style="margin-top: 10px;">
	<form method="post" action="${pageContext.request.contextPath }/18_1/main.jsp?contentPage=boardList.jsp">
		<select name="search_keycode">
			<option value="ALL">전체</option>
			<option value="TITLE">제목</option>
			<option value="CONTENT">컨텐츠</option>
		</select>
		<input type="text" id="search_keyword" name="search_keyword">
		<button class="mdl-button mdl-js-button mdl-button--raised mdl-button--accent" type="submit">검색</button>
		<button class="mdl-button mdl-js-button mdl-button--raised mdl-button--accent" type="button">글쓰기</button>
	</form>
</div>
<!-- 
	제이쿼리 코드 힌트 창 활성화
	http://code.google.com/a/eclipeslabs.org/p/jquerywtp/downloads/list
	다운로드 : jqueryWTP1.20foEN.jar
	2 cmd - java -jar jqueryWTP1.20~.jar 실행
	3. 실행창에서 jarFile : [browse] - 사용중인 이클립스 플러그인스 
	D:/B_Util/3.Eclipse/keploer/eclipse/plugins/org.eclipse.wst.jsdt.core_1.3.1.v201401291437.jar 선택
	
	4 Output Dir : [browse] -> jquery 코드 힌트 플러그인이 추가된 jar 파일 작성 위치 선택
	jar 파일 작성위치 선택 
	
	5 신규 작성된 jar 파일을 사용중인 이클립스의 plugins 폴더에 텊어쓰기
	
	6 이클립스 종료
	
	7. 워크스페이스 .metadata  plugins  org.eclipse.wst.jsdt.core 삭제
	
	8. 이클립스 실행
	
 -->
</body>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
$(function(){
	
	$('#memberListTbl tr:gt(0)').click(function(){
		var idx = $(this).index();
		var mem_id = $(this).find('td:eq(0)').text();
		$(location).attr('href', '${pageContext.request.contextPath}/member/memberView.do?mem_id='+mem_id );
	})
	
})
</script>
</html>








