<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board Detail</title>
<link rel="stylesheet" id="bootstrap-css" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/boardDetail.css">
</head>
<body>
	<div class="container">
		<div class="row">
			<section id="pinBoot">
				<article class="white-panel">
					<form action="bupdate" method="post">
						<ul>
							<li>
								<h5>
									<!-- 방명록 표시 -->
									<c:if test="${Yellow.visitor!=null}">
										${Yellow.visitor}&nbsp; 
										<img src="resources/images/arrow.png" style="width:20px;">&nbsp;
										${Yellow.writer}
									</c:if>
									<!-- 일반글 작성자 표시 -->
									<c:if test="${Yellow.visitor==null}">
										${Yellow.writer}
									</c:if>
									<!-- 글 작성자와 방명록 받은 페이지 주인 둘다 삭제, 수정 권한 있음 -->
									<c:if test="${Yellow.writer eq logId or Yellow.visitor eq logId}">
										<c:if test="${logId!=null}">
											<input type="submit" class="detailBtn" value="글 수정" />&nbsp;
											<input type="button" class="detailBtn" value="글 삭제" onclick="boardDelete(${Yellow.seq})">
										</c:if>
									</c:if>
								</h5>
								<!-- 작성일자 -->
								<h6>${Yellow.regdate}</h6> 
								<!-- 글의 고유 번호 -->
								<input type="hidden" name="seq" value="${Yellow.seq}"> 
							</li>
							<li>
								<!-- 사진이 있는 글 일 경우 -->
								<c:if test="${Yellow.uploadfile!=null}">
									<c:set var="uf" value="${Yellow.uploadfile}" />
									<!-- 다중 데이터인 uploadfile을 '#여기까지#'를 기준으로 split -->
									<c:set var="img" value="${fn:split(uf, '#여기까지#')}" />
									<!-- 사진이 한장인 경우 사진만 출력 -->
									<c:if test="${fn:length(img)==1}">
										<img class="mainImg" src="${img[0]}" />
									</c:if>
									<!-- 두장 이상인 경우 슬라이드 형태로 출력 -->
									<c:if test="${fn:length(img)!=1}">
										<section>
											<div id="slider-animation${Yellow.seq}" class="carousel slide" 
												data-interval="false" data-ride="carousel">
												<ul class="carousel-indicators">
													<c:set var="uf" value="${Yellow.uploadfile}" />
													<!-- "ufStat"이라는 varStatus의 인덱스 값을 사용해서 데이터의 순서 부여 -->
													<c:forEach items="${fn:split(uf, '#여기까지#')}" var="img" varStatus="ufStat">
														<!-- 첫 번째 사진이 "active" -->
														<c:if test="${ufStat.index==0}">
															<li data-target="#slider-animation${Yellow.seq}"
																data-slide-to="${ufStat.index}" class="active"></li>
														</c:if>
														<c:if test="${ufStat.index!=0}">
															<li data-target="#slider-animation${Yellow.seq}"
																data-slide-to="${ufStat.index}"></li>
														</c:if>
													</c:forEach>
												</ul>
												<!-- 슬라이드 숏컷 -->
												<div class="carousel-inner">
													<c:set var="uf" value="${Yellow.uploadfile}" />
													<c:forEach items="${fn:split(uf, '#여기까지#')}" var="img" varStatus="ufStat">
														<!-- 첫 번째가 "active" -->
														<c:if test="${ufStat.index==0}">
															<div class="carousel-item active">
																<img class="mainImg" src="${img}" />
															</div>
														</c:if>
														<c:if test="${ufStat.index!=0}">
															<div class="carousel-item">
																<img class="mainImg" src="${img}" />
															</div>
														</c:if>
													</c:forEach>
												</div>
												<!-- 좌우 컨트롤러 -->
												<a class="carousel-control-prev" href="#slider-animation${Yellow.seq}" data-slide="prev">
													<span class="carousel-control-prev-icon"></span>
												</a> 
												<a class="carousel-control-next" href="#slider-animation${Yellow.seq}" data-slide="next">
													<span class="carousel-control-next-icon"></span>
												</a>
											</div>
										</section>
									</c:if>
								</c:if>
							</li>
							<li>
								<c:choose>
									<c:when test="${Yellow.writer eq logId or Yellow.visitor eq logId}">
										<!-- 권한 있을 경우 게시글 내용 수정 가능 -->
										<textarea class="contentArea" name="content">${Yellow.content}</textarea>
									</c:when> 
									<c:otherwise>
										<textarea class="contentArea" readonly="readonly">${Yellow.content}</textarea>
									</c:otherwise>
								</c:choose>
							</li>
							<li>
								<h5>
									<!-- 태그 출력 -->
									<c:if test="${Yellow.tag!=null}">
										<c:set var="str" value="${Yellow.tag}" />
										<!-- 태그는 다중 데이터이므로 '#'으로 split하여 출력 -->
										<c:forEach items="${fn:split(str, '#')}" var="item">
											#${item}
										</c:forEach>
									</c:if>
								</h5>
							</li>
						</ul>
					</form>
				</article>
			</section><hr>
		</div>
	</div>

	<div class="scroll">
		<!-- 로그인 시 -->
		<c:if test="${logId!=null}">
			<link rel="stylesheet" href="http://fontawesome.io/assets/font-awesome/css/font-awesome.css">
			<div class="container">
			    <div class="row">
			    	<div class="widget-area no-padding blank">
						<div class="status-upload">
							<!-- 댓글 입력 폼 -->
							<form action="commentInsert" method="post">
								<%-- 댓글의 작성 위치 = ${boardSeq}(해당 글의 고유 번호) --%>
								<input type=hidden value="${boardSeq}" name=boardseq />
								<!-- 작성자= 로그인 아이디 -->
								<input type="hidden" name="writer" value="${logId}" />
								<!-- 댓글 내용 입력 -->
								<textarea placeholder="내용을 입력해주세요." name=content ></textarea>
								<button type="submit" class="detailBtn"><i class="fa fa-share"></i>입력</button>
							</form>
						</div>
					</div>
			    </div>
			</div>
		</c:if>

		<!-- 댓글 목록 출력 -->
		<div>
			<%@ include file="/WEB-INF/views/board/commentList.jsp"%>
		</div>
	</div>

<script src="resources/js/bootstrap.min.js""></script>
<script src="resources/js/boardDetail.js""></script>
</body>
</html>