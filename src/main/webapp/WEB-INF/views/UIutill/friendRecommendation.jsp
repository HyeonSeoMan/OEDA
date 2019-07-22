<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link rel="stylesheet" href="resources/css/friendRecommendation.css">
<meta charset="UTF-8">
<title>friend Recommendation</title>
</head>
<body>
	<!-- 친구 관리 사이드 바 -->
	<div id="friendContainer">
		<div class="row">
			<div class="wrapper_friend">
	    		<div class="friend-bar">
	                <ul>
	                    <li class="menu-head">
	                    	<!-- 푸시 애니메이션 -->
	                        <a class="push_menu_friend">
	                        	<img src="resources/images/controls.png" class="float-right" style="width:20px;">
	                        </a>
	                    </li>
	                    <div class="menu">
	                    	<div id="myFriendsArea">
		                    	<li>
		                    		<div>&nbsp;&nbsp;&nbsp;
			                             <img src="resources/images/person.png" style="width:25px;">&nbsp;
			                             <span>
			                             	<!-- 친구 목록 출력 -->
			                             	<button class="friendBtn" id="myFriend">친구 목록</button>
			                             </span>
		                             </div>
		                        </li>
	                        </div>
	                        <div id="plusFriendsArea">
		                    	<li>
		                    		<div>&nbsp;&nbsp;&nbsp;
			                             <img src="resources/images/add-friend.png" style="width:25px;">&nbsp;
			                             <span>
			                             	<!-- 친구 추가 가능 목록 -->
			                             	<button class="friendBtn" id="plusFriends">친구 추천</button>
			                             </span>
		                             </div>
		                        </li>
	                        </div>
	                        <div id="friendsRequestArea">
		                        <li>
		                    		<div>&nbsp;&nbsp;&nbsp;
			                             <img src="resources/images/request.png" style="width:25px;">&nbsp;
			                             <span>
			                             	<!-- 친구 요청 목록 -->
			                             	<button class="friendBtn" id="friendRequest">친구 요청</button>
			                             </span>
		                             </div>
		                        </li>
	                        </div>
	                    </div>
	                </ul>
	    	    </div>   
			</div>
		</div>
	</div>

<script src="resources/js/jquery-3.2.1.min.js"></script>
<script src="resources/js/bootstrap.js"></script>
<script src="resources/js/axFriendsButton.js"></script>
<script src="resources/js/friendRecommendation.js"></script>
</body>
</html>
