<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID 중복확인</title>
</head>
<body>
	<!-- 아이디 사용 가능 -->
	<c:if test="${idResult=='T'}">
		<script>
			opener.document.getElementById('id').value="${userId}" ;
			opener.document.getElementById('sss').disabled="";
			self.close() ;
			alert("${userId}는 사용 가능한 ID 입니다.");
		</script>
	</c:if>
	<!-- 아이디 중복 -->
	<c:if test="${idResult=='F'}">
		<script>
			self.close();
			alert("${userId}는 사용중인 ID 입니다.");
		</script>
	</c:if>
		
<script src="resources/js/jquery-3.2.1.min.js"></script>
<script src="resources/js/idDup.js"></script>
<script src="resources/js/idCheck.js"></script>
</body>
</html>