<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="resources/css/friend.css">
<meta charset="EUC-KR">
<title>myFriend</title>
</head>
<body>
	<div id="returnMyFriendsArea">
		<li>
			<div>&nbsp;&nbsp;&nbsp; 
				<img src="resources/images/person.png" style="width: 25px;">&nbsp; 
				<span>
					<!-- 친구 목록 닫기 -->
					<button class="friendBtn" id="returnMyFriend">친구 목록</button>
				</span> 
			</div>
		</li>
		<!-- 친구 목록 없을 시 -->
		<c:if test="${MyFriend==null}">
			&nbsp;&nbsp;&nbsp; 친구 목록이 비어 있습니다.
		</c:if>
		<c:if test="${MyFriend!=null}">
			<div class="scroll">
				<!-- 친구 목록 출력 -->
				<c:forEach var="mf" items="${MyFriend}">&nbsp;&nbsp;&nbsp;
						${mf}<br>
				</c:forEach>
			</div>
		</c:if>
	</div>

<script src="resources/js/jquery-3.2.1.min.js"></script>
<script src="resources/js/axFriendsButton.js"></script>
</body>
</html>