<%--==============================================================
 * 로그인 폼
 * @author 정희수
 * @since  2018.03.22.
 * @version 1.0
 * <pre>
 * << 개정이력(Modification Information) >>
 *    수정일       수정자          수정내용
 *    -------      -------     -------------------
 *    2018.03.22.  정희수      최초작성 (스크립트) 
 * Copyright (c) 2018 by DDIT  All right reserved
 * </pre>
===============================================================--%>
<%@page import="java.util.Map"%>
<%@page import="kr.or.ddit.utils.CryptoGenerator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath }/css/admin.css" type="text/css"> --%>
<title>회원관리 관리자 로그인</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/validation.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/cookieControl.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jsbn.js "></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/rsa.js "></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/prng4.js "></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/rng.js "></script>
<script>
$(function(){
	//자바스크립트 내에 익스프레션 선언할때는 "" or ''으로 익스프레션을 랩핑.
	
	if(Get_Cookie('mem_id') != null){
		$('input[name=mem_id]').val(Get_Cookie('mem_id'));
		$('input[name=saveCookie]').prop('checked',true);
	}
	
	$('.loginBtn').click(function(){
		if(!$('input[name=mem_id]').val().validationID()){
			alert("아이디를 바르게 입력해주세요.");
			return;
		}
		if(!$('input[name=mem_pass]').val().validationPWD()){
			alert("패스워드를 바르게 입력해주세요.");
			return;
		}
		var modulus = '${publicKeyMap["publicKeyModulus"]}';
		var exponent = '${publicKeyMap["publicKeyExponent"]}';
		
		var rsaObj = new RSAKey();
		rsaObj.setPublic(modulus,exponent);
		
		var encryptID = rsaObj.encrypt($('input[name=mem_id]').val());
		var encryptPWD = rsaObj.encrypt($('input[name=mem_pass]').val());
		
		var $frm =$('<form method="POST" action="${pageContext.request.contextPath}/join/loginCheck.do"></form>');
		var $iptID = $('<input type="hidden" name="mem_id" value="' + encryptID + '"/>');
		var $iptPWD = $('<input type="hidden" name="mem_pass" value="' + encryptPWD + '"/>');
		
		$frm.append($iptID);
		$frm.append($iptPWD);
		
		$(document.body).append($frm);
		
		$frm.submit();
		
		if($('input[type=checkbox]').prop('checked')){
			var mem_id = $('input[name=mem_id]').val();
			Set_Cookie('mem_id', mem_id);
			
		}else{
			Delete_Cookie('mem_id');
		}
		
	});	
	var msg = '${param.message}';
	if(msg != ''){
		alert(msg);
	}
	
});
</script>
</head>
<body>
	<table width="770" border="0" align="center" cellpadding="0"
		cellspacing="0" style="margin: 90px;">
		<tr>
			<td height="150" align="center"><img src="${pageContext.request.contextPath }/image/p_login.gif" /></td>
		</tr>
		<tr>
		
			<td height="174"
				style="background: url(${pageContext.request.contextPath }/image/login_bg.jpg); border: 1px solid #e3e3e3;">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="260" height="110" align="center"
							style="border-right: 1px dotted #736357;">
							<img src="${pageContext.request.contextPath }/image/logo.jpg" />
						</td>
						<td>
							<table border="0" align="center" cellpadding="5"
								cellspacing="0">
								<tr>
									<td><b>아이디</b></td>
									<td><input type="text" name="mem_id" class="box" tabindex="3" height="18" /></td>
									<td rowspan="2">
										<img src="${pageContext.request.contextPath }/image/login.gif" class="loginBtn"/>
									</td>
								</tr>
								<tr>
									<td><b>패스워드</b></td>
									<td><input type="password" name="mem_pass" class="box" tabindex="3" height="18" /></td>
								</tr>
								<tr>
									<td colspan="3" align="right">
										id저장 <input type="checkbox" name="saveCookie"/>
										<a href="${pageContext.request.contextPath }/member/memberForm.do">회원가입을 원하세요?</a>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>
