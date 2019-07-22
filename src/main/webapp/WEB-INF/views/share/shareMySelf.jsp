<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>share My Self</title>
<link rel="stylesheet" href="resources/css/shareMySelf.css">
</head>
<body>
	<span class="input-group-addon"><img src="resources/images/share.png" style="width:30px;"></span>&nbsp;
	<!-- 나만 보기 -->
	<input type="hidden" name="share_tag_list"  id="share_tag_list" value="${logId}"/>
	나만 보기로 설정됩니다.
	<!-- 공유 취소 -->
	<input type="button" class="onlyShare" id="resetShare" value="공유 취소"/>

<script src="resources/js/jquery-3.2.1.min.js"></script>
<script src="resources/js/axShareButton.js"></script>
</body>
</html>