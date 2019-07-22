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
					<!-- ģ�� �߰� ���� ��� -->
					<button class="friendBtn" id="returnPlusFriends">ģ�� ��õ</button>
				</span>
			</div>
		</li> 
		<!-- ģ�� ��û�� = �α��� ���̵� -->
		<input type="hidden" name="master_user" id="master_user" value="${logId}"/>
		<!-- ģ�� ��û ���� = ��û(request_status=0) -->
		<input type="hidden" name="request_status" id="request_status" value="0"/>
		<div class="scroll">
			<!-- ��� ģ�� �߰� ���� ��� ��� -->
			<c:forEach var="fr" items="${Friend}">&nbsp;&nbsp;&nbsp;
				<c:choose>
			         <c:when test = "${fn:contains(fr,'��û ��')}">&nbsp;
			         <!-- ��û�� �� ���� "��û ��"�� ���� --> 
			            ${fr}<br>
			         </c:when>
			         <c:otherwise>
			            <input type="checkbox" name="friend_user_list" id="friend_user_list" value="${fr}">${fr}<br>
			         </c:otherwise>
			      </c:choose>
			</c:forEach>
		</div>&nbsp;&nbsp;
		<input type="button" class="friendBtn" id="plusFriendAction" value="����">
	</div>
	
<script src="resources/js/jquery-3.2.1.min.js"></script>
<script src="resources/js/axFriendsButton.js"></script>
</body>
</html>