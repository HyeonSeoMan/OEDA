<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>share Setting</title>
<link rel="stylesheet" href="resources/css/shareSetting.css">
</head>
<body>
	<span class="input-group-addon"><img src="resources/images/share.png" style="width:30px;"></span>
	<!-- 설정 안할 시 null -->
	<input type="hidden" name="share_tag_list"  id="share_tag_list"/>
	<!-- 나만 보기 선택 -->
	<input type="button" class="shareSubmit" id="shareMySelf" value="나만 보기"/>
	<!-- 공유 하기 선택 -->
	<input type="button" class="shareSubmit" id="shareTogether" value="공유 하기"/>
	<!-- 공유 취소 -->
	<input type="button" class="shareSubmit" id="resetShare" value="공유 취소"/>

<script src="resources/js/jquery-3.2.1.min.js"></script>
<script src="resources/js/axShareButton.js"></script>
</body>
</html>