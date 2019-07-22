<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Form</title>
<link rel="stylesheet" href="resources/css/loginForm.css">
</head>
<body>
	<div class="loginWrapper fadeInDown">
		<div id="formContent">
		    <div class="fadeIn first">
		    	<img src="resources/images/logo.png" style="width:100px;"  id="icon">
		    	<h1><a class="underlineHover" style="text-decoration:none" href="home">OEDA</a></h1>
		    </div>
		    <!-- 로그인 폼 -->
		    <form action="login" method="post" role="form">
		    	<!-- 로그인 아이디 -->
		    	<input type="text" name="id" id="id" class="fadeIn second loginId" placeholder="유저 ID"><br>
		    	<!-- 아이디 유효성 검사 실패 시 메시지 출력 -->
		    	<span id=iMessage></span>
		    	<!-- 로그인 비밀 번호 -->
		    	<input type="password" name="password" id="password" class="fadeIn third loginPw" placeholder="비밀 번호"><br>
		    	<!-- 비밀 번호 유효성 검사 실패 시 메시지 출력 -->
		    	<span id=pMessage></span><br><br>
		    	<!-- onclick="return inputCheck()"으로 최종 유효성 검사 -->
		    	<input type="submit" class="fadeIn fourth loginSubmit" value="접속하기" onclick="return inputCheck()">
		    </form>
		    <!-- 홈으로 -->
		    <div id="formFooter">
		    	<a class="underlineHover" style="text-decoration:none" href="home">Home</a>
		    </div>
		</div>
	</div>

<script src="resources/js/jquery-3.2.1.min.js"></script>
<script src="resources/js/loginForm.js"></script>
</body>
</html>