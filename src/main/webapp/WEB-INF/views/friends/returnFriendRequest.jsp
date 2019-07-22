<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="resources/css/friend.css">
<meta charset="EUC-KR">
<title>return Friend Request</title>
</head>
<body>
	<div id="friendsRequestArea">
		<li>
			<div>&nbsp;&nbsp;&nbsp; 
				<img src="resources/images/request.png" style="width: 25px;">&nbsp; 
				<span>
					<!-- 친구 요청 목록 -->
					<button class="friendBtn" id="friendRequest">친구 요청</button>
				</span>
			</div>
		</li>
	</div>
	
<script src="resources/js/jquery-3.2.1.min.js"></script>
<script src="resources/js/axFriendsButton.js"></script>
</body>
</html>