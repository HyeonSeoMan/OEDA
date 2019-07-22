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
					<!-- ģ�� ��� �ݱ� -->
					<button class="friendBtn" id="returnMyFriend">ģ�� ���</button>
				</span> 
			</div>
		</li>
		<!-- ģ�� ��� ���� �� -->
		<c:if test="${MyFriend==null}">
			&nbsp;&nbsp;&nbsp; ģ�� ����� ��� �ֽ��ϴ�.
		</c:if>
		<c:if test="${MyFriend!=null}">
			<div class="scroll">
				<!-- ģ�� ��� ��� -->
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