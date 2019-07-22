<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="resources/css/shareTogether.css">
<meta charset="EUC-KR">
<title>share Together</title>
</head>
<body>
	<span class="input-group-addon"><img src="resources/images/share.png" style="width:30px;"></span>
	&nbsp;[공유 할 친구 추가]
	<div class="scroll">
		<!-- 친구 목록에서 공유 선택 -->
		<c:forEach var="ora" items="${Orange}">
			<input type="checkbox"  name="share_tag_list" value="${ora}">${ora}<br>
		</c:forEach>
	</div>
	<!-- 공유 취소 -->
	<input type="button" class="postSubmit" id="resetShare" value="공유 취소"/>
	
<script src="resources/js/jquery-3.2.1.min.js"></script>
<script src="resources/js/axShareButton.js"></script>
</body>
</html>