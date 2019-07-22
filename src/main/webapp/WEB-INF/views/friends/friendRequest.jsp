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
					<!-- ģ�� ��û �ݱ� -->
					<button class="friendBtn" id="returnFriendRequest">ģ�� ��û</button>
				</span>
			</div>
		</li>
		<!-- ģ�� ��û ���� �� -->
		<c:if test="${FriendReq==null}">
			&nbsp;&nbsp;&nbsp; ģ�� ��û�� �����ϴ�.
		</c:if>
		<!-- ģ�� ��û ���� �� -->
		<!-- ģ�� ���� ��(ajax ����) -->
		<c:if test="${FriendReq!=null}">
			<!-- ���� ��û�� = �α��� ���̵� -->
			<input type="hidden" name="master_user" id="master_user" value="${logId}"/>
			<!-- ���� �޴� ���� = ģ�� ��û�� -->
			<input type="hidden" name="request_status" id="request_status" value="1"/>
			<div class="scroll">
				<!-- checkbox���� ����Ʈ ���·� ���� -->
				<c:forEach var="fReq" items="${FriendReq}">&nbsp;&nbsp;&nbsp;
					<input type="checkbox" name="friend_request_list" value="${fReq}">${fReq}<br>
				</c:forEach>
			</div>&nbsp;&nbsp;
			<input type="button" class="friendBtn" id="friendRequestAccept" value="����">
		</c:if>
	</div>
<script src="resources/js/jquery-3.2.1.min.js"></script>
<script src="resources/js/axFriendsButton.js"></script>
</body>
</html>