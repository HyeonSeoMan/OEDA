<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="resources/css/friend.css">
<meta charset="EUC-KR">
<title>plus Friend</title>
</head>
<body>
	<div id="returnPlusFriendsArea">
		<li>
			<div>&nbsp;&nbsp;&nbsp; 
				<img src="resources/images/add-friend.png" style="width: 25px;">&nbsp; 
				<span>
					<!-- 친구 추가 가능 목록 -->
					<button class="friendBtn" id="returnPlusFriends">친구 추천</button>
				</span>
			</div>
		</li> 
		<!-- 친구 요청자 = 로그인 아이디 -->
		<input type="hidden" name="master_user" id="master_user" value="${logId}"/>
		<!-- 친구 요청 상태 = 요청(request_status=0) -->
		<input type="hidden" name="request_status" id="request_status" value="0"/>
		<div class="scroll">
			<!-- 모든 친구 추가 가능 목록 출력 -->
			<c:forEach var="fr" items="${Friend}">&nbsp;&nbsp;&nbsp;
				<c:choose>
			         <c:when test = "${fn:contains(fr,'요청 중')}">&nbsp;
			         <!-- 요청중 일 때는 "요청 중"이 붙음 --> 
			            ${fr}<br>
			         </c:when>
			         <c:otherwise>
			            <input type="checkbox" name="friend_user_list" id="friend_user_list" value="${fr}">${fr}<br>
			         </c:otherwise>
			      </c:choose>
			</c:forEach>
		</div>&nbsp;&nbsp;
		<input type="button" class="friendBtn" id="plusFriendAction" value="전송">
	</div>
	
<script src="resources/js/jquery-3.2.1.min.js"></script>
<script src="resources/js/axFriendsButton.js"></script>
</body>
</html>