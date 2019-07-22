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
	&nbsp;[���� �� ģ�� �߰�]
	<div class="scroll">
		<!-- ģ�� ��Ͽ��� ���� ���� -->
		<c:forEach var="ora" items="${Orange}">
			<input type="checkbox"  name="share_tag_list" value="${ora}">${ora}<br>
		</c:forEach>
	</div>
	<!-- ���� ��� -->
	<input type="button" class="postSubmit" id="resetShare" value="���� ���"/>
	
<script src="resources/js/jquery-3.2.1.min.js"></script>
<script src="resources/js/axShareButton.js"></script>
</body>
</html>