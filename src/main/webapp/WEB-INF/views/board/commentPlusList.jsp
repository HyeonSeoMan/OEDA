<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>comment Plus List</title>
</head>
<body>
	<div id="reComArea${commentSeq}">
		<c:forEach var="rcm" items="${ReComment}" varStatus="re">
			<div class="container">
			<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-T8Gy5hrqNKT+hzMclPo118YTQO6cYprQmhrYwIiQ/3axmI1hQomh7Ud2hPOy8SP1" crossorigin="anonymous">
				<div class="row">
				    <div class="comments-container">
						<ul id="reComments-list" class="comments-list">
							<li>
								<div class="comment-main-level">
									<div class="comment-box">
										<div class="comment-head">
											<!-- 대댓글 작성자 -->
											<h6 class="comment-name by-author">${rcm.writer}</h6>
											<!-- 작성일자 -->
											<span>${rcm.regdate}</span>&nbsp;
											<!-- 대댓글 고유 번호 -->
											<input type="hidden" id="recompk${re.index}" value="${rcm.compk}">
											<c:choose>
												<c:when test="${rcm.writer eq logId}">
													<!-- 작성자만 삭제, 수정 가능 -->
													<input type="button" class="commentBtn" value="삭제" onclick="reCommentDelete(${boardSeq},${commentSeq},${rcm.compk})">
													<button class="resoojung commentBtn" value="${re.index}">수정</button>
												</c:when>
												<c:when test="${rcm.parent eq logId}">
													<!-- 부모는 삭제만 가능 -->
													<input type="button" class="commentBtn" value="삭제" onclick="reCommentDelete(${boardSeq},${commentSeq},${rcm.compk})">
												</c:when>
											</c:choose>
										</div>
										<div class="comment-content">
											<c:if test="${rcm.writer eq logId}">
												<!-- 대댓글 내용 -->
												<input type="text" id="recontent${re.index}" class="commentBox" name="content" value="${rcm.content}">
											</c:if>
											<c:if test="${rcm.writer != logId}">
												<input type="text" id="recontent${re.index}" class="commentBox" name="content" value="${rcm.content}" readonly="readonly">
											</c:if>
										</div>
									</div>
								</div>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</c:forEach>
		<!-- 로그인 시에만 대댓글 입력 가능 -->
		<!-- 대댓글 입력 폼(ajax로 구성) -->
		<c:if test="${logId!=null}">
			<link rel="stylesheet" href="http://fontawesome.io/assets/font-awesome/css/font-awesome.css">
			<div class="container">
			    <div class="row">
			    	<div class="widget-area no-padding blank">
						<div class="status-upload">
							<form>
								<!-- 해당 글의 고유 번호 -->
								<input type="hidden" name="boardseq" id="boardseq" value="${boardSeq}"/>
								<!-- 글 안에서 댓글의 순서 -->
								<input type="hidden" name="boardseq" id="commentSeq" value="${commentSeq}"/>
								<!-- 작성자 -->
								<input type="hidden" name="writer" id="writer" value="${logId}"/>
								<!-- 대댓글의 고유 번호 -->
								<input type="hidden" name="compk" id="compk" value="${compk}"/>
								<!-- 대댓글 내용 -->
								<textarea placeholder="내용을 입력해주세요." name=content id="content" ></textarea>
								<button type="button" class="commentBtn" name=reCommentButton id="reCommentButton"><i class="fa fa-share"></i>입력</button>
							</form>
						</div>
					</div>
			    </div>
			</div>
		</c:if>
	</div>

<script src="resources/js/jquery-3.2.1.min.js"></script>
<script src="resources/js/axReInsert.js"></script>
<script src="resources/js/axReCommentUpdate.js"></script>
<script src="resources/js/commentPlusList.js""></script>
</body>
</html>