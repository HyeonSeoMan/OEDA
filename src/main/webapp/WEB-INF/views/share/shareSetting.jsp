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
	<!-- ���� ���� �� null -->
	<input type="hidden" name="share_tag_list"  id="share_tag_list"/>
	<!-- ���� ���� ���� -->
	<input type="button" class="shareSubmit" id="shareMySelf" value="���� ����"/>
	<!-- ���� �ϱ� ���� -->
	<input type="button" class="shareSubmit" id="shareTogether" value="���� �ϱ�"/>
	<!-- ���� ��� -->
	<input type="button" class="shareSubmit" id="resetShare" value="���� ���"/>

<script src="resources/js/jquery-3.2.1.min.js"></script>
<script src="resources/js/axShareButton.js"></script>
</body>
</html>