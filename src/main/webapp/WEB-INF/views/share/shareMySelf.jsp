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
	<!-- ���� ���� -->
	<input type="hidden" name="share_tag_list"  id="share_tag_list" value="${logId}"/>
	���� ����� �����˴ϴ�.
	<!-- ���� ��� -->
	<input type="button" class="onlyShare" id="resetShare" value="���� ���"/>

<script src="resources/js/jquery-3.2.1.min.js"></script>
<script src="resources/js/axShareButton.js"></script>
</body>
</html>