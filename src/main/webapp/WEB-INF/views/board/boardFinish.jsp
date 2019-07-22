<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Finish</title>
</head>
<body>
<h3>
<c:if test="${resultID=='I'}">
	새글 등록 실패<br>
	<a href="binsertf">다시 하기</a>
</c:if>
<c:if test="${resultID=='U'}">
	 글 수정  실패<br>
	<a href="#" onclick="history.back()">다시 하기</a>
</c:if>
<c:if test="${resultID=='D'}">
	 글 삭제  실패<br>
	<a href="#" onclick="history.back()">다시 하기</a>
</c:if>
<c:if test="${resultID=='R'}">
	 댓글 달기  실패<br>
	<a href="#" onclick="history.back()">다시 하기</a>
</c:if>
<c:if test="${resultID=='DSuccess'}">
	<script language="javascript">
		window.opener.top.location.href = "home"
		window.close();
	</script>
</c:if>
<br><br>
<a href="home">[Home]</a>
</h3>
</body>
</html>