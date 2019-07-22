<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Insert</title>
<link rel="stylesheet" href="resources/css/visitor'sBookForm.css">
</head>
<body>
	<div class="loginWrapper fadeInDown">
	  	<div id="formContent">
		    <div class="fadeIn first">
		    	<img src="resources/images/logo.png" style="width:100px;"  id="icon">
		    	<h1>
		    		<a class="underlineHover" style="text-decoration:none" href="home">OEDA</a>
		    	</h1>
		    </div>
		    <!-- 방명록 작성 폼 -->
		    <form action="binsert" method="post" enctype="multipart/form-data">
		    	<!-- 페이지 주인 -->
			    <input type="hidden" name="writer" value="${personalPage}"/>
			    <!-- 방문객 = 로그인 아이디 -->
			    <input type="hidden" name="visitor" value="${logId}"/>
			    <div>
			    	<span class="input-group-addon">
			     		<img src="resources/images/person.png" style="width:30px;">
			    	</span>
			    	<!-- 방명록 내용 -->
					<textarea rows="10" cols="40" id="contenttestVisit" name=content 
					  	class="fadeIn second joinId"  placeholder="내용을 입력해주세요."></textarea>
			  	</div>
			  	<!-- 태그=null -->
				<input type="hidden" name="tag"/>
				<!-- 공유 설정=null -->
				<input type="hidden" name="share_tag_list"/>
				<div id="fileUploader">
					<span>
						<img src="resources/images/photo.png" style="width: 30px;">
					</span>
					<!-- 사진 등록 --> 
					<input type="file" multiple="multiple" name="uploadfilef">
				</div>
				<!-- onclick="return visitorCheck()"으로 유효성 검사 -->
			    <input type="submit" value="게시물 만들기" class="fadeIn fourth postSubmit"  onclick="return visitorCheck()"/>
		    </form>
		    <!-- 홈으로 -->
		    <div id="formFooter">
		    	<a class="underlineHover" style="text-decoration:none" href="home">Home</a>
		    </div>
		</div>
	</div>

<script src="resources/js/jquery-3.2.1.min.js"></script>
<script src="resources/js/axShareButton.js"></script>
<script src="resources/js/visitor'sBookForm.js"></script>

</body>
</html>