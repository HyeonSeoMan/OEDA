<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Insert</title>
<link rel="stylesheet" href="resources/css/binsertForm.css">
</head>
<body>
	<div class="loginWrapper fadeInDown">
		<div id="formContent">
		    <div class="fadeIn first">
		    	<img src="resources/images/logo.png" style="width:100px;"  id="icon">
		    	<h1><a class="underlineHover" style="text-decoration:none" href="home">OEDA</a></h1>
		    </div>
		    <!-- 게시글 작성 폼 -->
		    <form action="binsert" method="post" enctype="multipart/form-data">
		    	<%-- 작성자 = ${logId}(로그인 아이디) --%>
			    <input type="hidden" name="writer" value="${logId}"/>
			    
			    <div>
			  		<span class="input-group-addon"><img src="resources/images/person.png" style="width:30px;"></span>
					<!-- 글 내용 -->
					<textarea rows="10" cols="40" id="contenttest" name=content class="fadeIn second joinId" 
					  	placeholder="내용을 입력해주세요."></textarea>
			  	</div>
			  	
			    <div>
			    	<span class="input-group-addon"><img src="resources/images/tag.png" style="width:30px;"></span>
					<!-- 게시글 태그(게시판 성정과 유사 함) -->
					<input type="text" name="tag"  id="tagtest" class="fadeIn second joinId" placeholder="태그를 입력해주세요."/>
			    </div>
			    
				<div id="shareSettingButton">
					<span class="input-group-addon"><img src="resources/images/share.png" style="width:30px;"></span>
					<!-- 설정 안 할 시 null -->
					<input type="hidden" name="share_tag_list"  id="share_tag"/>
					<!-- 공유 설정 -->
					<input type="button" class="fadeIn fourth postSubmit" id="shareSetting" value="공유 설정"/>
				</div>
				
				<div id="fileUploader">
					<span>
						<img src="resources/images/photo.png" style="width: 30px;">
					</span>
					<!-- 다중 이미지 등록 -->
					<input type="file" multiple="multiple" name="uploadfilef">
				</div>
				<!-- onclick="return binsertCheck()"로 최종 유효성 검사 -->
			    <input type="submit" value="게시물 만들기" class="fadeIn fourth postSubmit"  onclick="return binsertCheck()"/>
		    </form>
		    
		    <div id="formFooter">
		    	<!-- 홈으로 -->
		    	<a class="underlineHover" style="text-decoration:none" href="home">Home</a>
		    </div>
		</div>
	</div>

<script src="resources/js/jquery-3.2.1.min.js"></script>
<script src="resources/js/axShareButton.js"></script>
<script src="resources/js/binsertForm.js"></script>
</body>
</html>