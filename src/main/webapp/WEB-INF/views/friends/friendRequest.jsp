<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="resources/css/friend.css">
<meta charset="EUC-KR">
<title>friend Request</title>
</head>
<body>
	<div id="returnFriendsRequestArea">
		<li>
			<div>&nbsp;&nbsp;&nbsp; 
				<img src="resources/images/request.png" style="width: 25px;">&nbsp; 
				<span>
					<!-- 친구 요청 닫기 -->
					<button class="friendBtn" id="returnFriendRequest">친구 요청</button>
				</span>
			</div>
		</li>
		<!-- 친구 요청 없을 시 -->
		<c:if test="${FriendReq==null}">
			&nbsp;&nbsp;&nbsp; 친구 요청이 없습니다.
		</c:if>
		<!-- 친구 요청 있을 시 -->
		<!-- 친구 수락 폼(ajax 형태) -->
		<c:if test="${FriendReq!=null}">
			<!-- 수락 신청자 = 로그인 아이디 -->
			<input type="hidden" name="master_user" id="master_user" value="${logId}"/>
			<!-- 수락 받는 유저 = 친구 요청자 -->
			<input type="hidden" name="request_status" id="request_status" value="1"/>
			<div class="scroll">
				<!-- checkbox에서 리스트 형태로 전송 -->
				<c:forEach var="fReq" items="${FriendReq}">&nbsp;&nbsp;&nbsp;
					<input type="checkbox" name="friend_request_list" value="${fReq}">${fReq}<br>
				</c:forEach>
			</div>&nbsp;&nbsp;
			<input type="button" class="friendBtn" id="friendRequestAccept" value="전송">
		</c:if>
	</div>
<script src="resources/js/jquery-3.2.1.min.js"></script>
<script src="resources/js/axFriendsButton.js"></script>
</body>
</html>