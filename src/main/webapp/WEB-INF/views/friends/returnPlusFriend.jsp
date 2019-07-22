<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="resources/css/friend.css">
<meta charset="EUC-KR">
<title>return Plus Friend</title>
</head>
<body>
	<div id="plusFriendsArea">
		<li>
			<div>&nbsp;&nbsp;&nbsp; 
				<img src="resources/images/add-friend.png" style="width: 25px;">&nbsp; 
				<span>
					<!-- 친구 추가 가능 목록 -->
					<button class="friendBtn" id="plusFriends">친구 추천</button>
				</span>
			</div>
		</li>
	</div>
	
<script src="resources/js/jquery-3.2.1.min.js"></script>
<script src="resources/js/axFriendsButton.js"></script>	
</body>
</html>