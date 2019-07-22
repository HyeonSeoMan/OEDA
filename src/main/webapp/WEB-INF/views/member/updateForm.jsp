<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>update Form</title>
<link rel="stylesheet" href="resources/css/updateForm.css">
</head>
<body>
	<div class="loginWrapper fadeInDown">
		<div id="formContent">
			<div class="fadeIn first">
		    	<img src="resources/images/logo.png" style="width:100px;"  id="icon">
		    	<h1><a class="underlineHover" style="text-decoration:none" href="home">OEDA</a></h1>
		    </div>
		    <!-- 회원 정보 수정 폼 -->
		    <form action="update" method="post" enctype="multipart/form-data">
		    	<label for="confirm" class="cols-sm-2 control-label">유저 ID</label>
		    	<div>
		    		<span class="input-group-addon"><img src="resources/images/person.png" style="width:30px;"></span>
		    		<!-- 아이디 수정 불가 -->
					<input type="text" class="fadeIn second joinId" name="id" id=Id  value="${logId}" readonly="readonly"/>
		      	</div>
		  	    <label for="confirm" class="cols-sm-2 control-label">비밀 번호</label>
		    	<div>
		    		<span class="input-group-addon"><img src="resources/images/lock.png" style="width:30px;"></span>
		    		<!-- 비밀 번호 수정 -->
					<input type="password" class="fadeIn second joinPw" name="password" id="updatePassword"  placeholder="비밀번호를 입력해주세요."/>
		      	</div>
			    <label for="confirm" class="cols-sm-2 control-label">비밀 번호 확인</label>
		      	<div>
		    		<span class="input-group-addon"><img src="resources/images/lock.png" style="width:30px;"></span>
		    		<!-- 비밀 번호 수정 확인 -->
					<input type="password" class="fadeIn second joinPw" id="updatePassword2"  placeholder="비밀번호를 확인해 주세요."/>
		      	</div>
				<label for="confirm" class="cols-sm-2 control-label">프로필 이미지</label>
				<div id="fileUploader">
					<span>
						<img src="resources/images/photo.png" style="width: 30px;">
					</span> 
					<label for="ex_file" class="fbLabel fadeIn fourth">프로필 선택</label>
					<!-- 프로필 수정 --> 
					<input type="file" name="uploadfilef" id="ex_file" />
				</div>
				<!-- onclick="return updateCheck()"로 최종 유효성 검사 -->
				<input type="submit" class="fadeIn fourth joinSubmit" value="수정하기" onclick="return updateCheck()">
		    </form>
		    <!-- 홈으로 -->
		    <div id="formFooter">
		    	<a class="underlineHover" style="text-decoration:none" href="home">Home</a>
			</div>
		</div>
	</div>
		
<script src="resources/js/jquery-3.2.1.min.js"></script>
<script src="resources/js/updateForm.js"></script>
</body>
</html>