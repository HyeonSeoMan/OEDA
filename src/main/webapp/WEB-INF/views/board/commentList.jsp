<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>comment List</title>
</head>
<link rel="stylesheet" href="resources/css/commentList.css">
<body>
	<c:forEach var="cm" items="${Comment}" varStatus="vs">
		<div class="container">
			<div class="row">
				<div class="comments-container">
					<ul id="comments-list" class="comments-list">
						<li>
							<div class="comment-main-level">
								<div class="comment-box">
									<div class="comment-head">
										<!-- 댓글 작성자 -->
										<h6 class="comment-name by-author">${cm.writer}</h6>
										<!-- 작성일자 -->
										<span>${cm.regdate}</span>&nbsp;
										<!-- 댓글의 고유 번호 -->
										<input type="hidden" id="compk${vs.index}" value="${cm.compk}">
										<!-- 대댓글 ajax 생성 버튼 -->
										<button class="commentPlus commentBtn" value="${vs.index}">답글</button>
										<c:choose>
											<c:when test="${cm.writer eq logId}">
												<!-- 댓글 작성자만 삭제,수정 권한 -->
												<input type="button" class="commentBtn" value="삭제" onclick="commentDelete(${boardSeq},${cm.compk})">
												<button class="soojung commentBtn" value="${vs.index}">수정</button>
											</c:when>
											<c:when test="${cm.parent eq logId}">
												<!-- 댓글의 부모는 삭제 권한만 있음 -->
												<input type="button" class="commentBtn" value="삭제" onclick="commentDelete(${boardSeq},${cm.compk})">
											</c:when>
										</c:choose>
									</div>
									<div class="comment-content">
										<!-- 댓글 내용 -->
										<c:if test="${cm.writer eq logId}">
											<input  type="text" id="content${vs.index}" class="commentBox" name="content" value="${cm.content}">
										</c:if>
										<c:if test="${cm.writer != logId}">
											<input type="text" id="content${vs.index}" class="commentBox" name="content" value="${cm.content}" readonly="readonly">
										</c:if>
									</div>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<!-- 대댓글 ajax가 생성되는 공간 -->
		<ul>
			<li style="margin-left: 70px;"  id="resultArea${vs.index}"></li>
		</ul>
	</c:forEach>
<script src="resources/js/jquery-3.2.1.min.js"></script>
<script src="resources/js/axCommentList.js"></script>
<script src="resources/js/axCommentUpdate.js"></script>
<script src="resources/js/commentList.js""></script>

</body>
</html>