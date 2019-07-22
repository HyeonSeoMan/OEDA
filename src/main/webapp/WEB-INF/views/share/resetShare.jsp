<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>reset Share</title>
</head>
<body>
	<span class="input-group-addon"><img src="resources/images/share.png" style="width:30px;"></span>
	<!-- 공유 설정 없을 시 null -->
	<input type="hidden" name="share_tag_list"  id="share_tag_list"/>
	<!-- 공유 설정 확장 버튼 -->
	<input type="button" class="postSubmit" id="shareSetting" value="공유 설정"/>

<script src="resources/js/jquery-3.2.1.min.js"></script>
<script src="resources/js/axShareButton.js"></script>
</body>
</html>