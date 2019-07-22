<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>do Finish</title>
</head>
<body>
	<c:if test="${isJoin=='F'}">
	 	회원 가입에 실패하였습니다.<br><br>
	 	<a href="home">home</a> 
	</c:if>
	<c:if test="${isJoin=='U'}">
	 	${logId}님은 내 정보 수정하기 실패 했습니다.<br><br>
	 	다시 하시겠습니까?<br><br>
	 	<a href="#" onclick="history.back()">내정보 수정하러 가기</a> 
	</c:if>
	<c:if test="${isJoin=='D'}">
	 	${logId}님은 회원 탈퇴가 정상적으로 처리 되지 못했습니다.<br><br>
	 	다시 하시겠습니까?<br><br>
	 	<a href="#" onclick="history.back()">회원 탈퇴 하러 가기</a> 
	</c:if>
</body>
</html>