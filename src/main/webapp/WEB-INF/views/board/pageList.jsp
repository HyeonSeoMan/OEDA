<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" id="bootstrap-css" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/pageList.css">
<meta charset="UTF-8">
<title>Page List</title>
</head>
<body>
	<!-- 현재 페이지가 2페이지 이상 일 때 격자 생성 -->
	<!-- 숏컷 역할 -->
	<div id="startArea${currPage}" align="center">
		<c:if test="${Banana[0]!=null}">
			<c:if test="${currPage>1}">
				<hr class="style-two">
			</c:if>
		</c:if>
	</div>

	<div class="container" align="center">
  		<div class="row">
  			<section id="pinBoot">
	  			<c:forEach var="bb" items="${Banana}" varStatus="pagelist">
	    			<article class="white-panel">
	    				<!-- 공유 글 일 때 출력 --> 
	    				<c:if test="${bb.share_tag!=null}">
	    					<c:set  var="st" value="${bb.share_tag}"/>
	    					<!-- 다중 데이터인 share_tag를 '#공유시작#'로 split -->
							<c:set var="share" value="${fn:split(st, '#공유시작#')}"/>
								<!-- 공유자 한명일 경우 '나만 보기' -->
								<c:if test="${fn:length(share)==1}">
									<img src="resources/images/lock.png" style="width:30px;">
									&nbsp; 나만보기
								</c:if>
								<!-- 공유하기를 실행 할 경우 자신을 포함해서 공유하기 때문에 무조건 2명 이상이 됨 -->
								<!-- 그렇기 때문에 두명 이상 일 경우 = 공유하기 -->
								<c:if test="${fn:length(share)!=1}">
									<img src="resources/images/share.png" style="width:30px;">&nbsp;
									<c:forEach items="${share}" varStatus="sv">
										<c:set var="string1" value="${share[sv.index]}"/>
										<!-- 다중 데이터인 share_tag를 '#공유시작#'로 split하고 -->
										<!-- '@끝@'의 닫아주는 격자를 제거 -->
										<!-- 데이터의 양쪽에 격자를 붙여서 저장하는 이유는 검색의 정확도를 높이기 위함 -->
										<c:set var="string2" value="${fn:replace(string1,'@끝@', '')}" />
										<!-- 내 이름 클릭하면 마이 페이지로 -->
										<c:if test="${string2 eq logId}">
					    					<a href="blist?opt=myPost&search=${string2}">@${string2}&nbsp;</a>
					    				</c:if>
					    				<!-- 다른 유저의 이름을 클릭하면 유저의 페이지로 이동 -->
					    				<c:if test="${string2 ne logId}">
					    					<a href="blist?opt=post&search=${string2}">@${string2}&nbsp;</a>
					    				</c:if>
									</c:forEach>
								</c:if>
	    				</c:if>
		    			<h4>
							<c:choose>
								<c:when test="${bb.visitor!=null}">
								<!-- 방명록 일 때 -->
									<!-- 마이 페이지로 이동 -->
									<c:if test="${bb.visitor eq logId}">
		    							<a href="blist?opt=myPost&search=${bb.visitor}">${bb.visitor}&nbsp;</a>
			    					</c:if>
			    					<!-- 유저의 페이지로 이동 -->
			    					<c:if test="${bb.visitor ne logId}">
		    							<a href="blist?opt=post&search=${bb.visitor}">${bb.visitor}&nbsp;</a>
			    					</c:if>
									<img src="resources/images/arrow.png" style="width:20px;">&nbsp;
									<!-- 마이 페이지로 이동 -->
									<c:if test="${bb.writer eq logId}">
		    							<a href="blist?opt=myPost&search=${bb.writer}">${bb.writer}</a>
			    					</c:if>
			    					<!-- 유저의 페이지로 이동 -->
			    					<c:if test="${bb.writer ne logId}">
		    							<a href="blist?opt=post&search=${bb.writer}">${bb.writer}</a>
			    					</c:if>
								</c:when>
								<c:when test="${bb.writer eq logId}">
								<!-- 일반 글 일 떄 -->
									<!-- 마이 페이지로 이동 -->
		    						<a href="blist?opt=myPost&search=${bb.writer}">${bb.writer}</a>
			    				</c:when>
			    				<c:when test="${bb.writer ne logId}">
			    					<!-- 유저의 페이지로 이동 -->
			    					<a href="blist?opt=post&search=${bb.writer}">${bb.writer}</a>
			    				</c:when>
							</c:choose>
		    			</h4>
		    			<!-- 작성일자 -->
		    			<h6>${bb.regdate}</h6>
		    			<!-- 사진이 있을 경우 -->
		    			<c:if test="${bb.uploadfile!=null}">
		    				<c:set  var="uf" value="${bb.uploadfile}"/>
		    				<!-- 다중 데이터인 uploadfile을 '#여기까지#'를 기준으로 split -->
							<c:set var="img" value="${fn:split(uf, '#여기까지#')}"/>
							<!-- 사진이 한장인 경우 사진만 출력 -->
							<c:if test="${fn:length(img)==1}">
								<img class="mainImg" src="${img[0]}" onclick="commentListP(${bb.seq})"/>
							</c:if>
							<!-- 두장 이상인 경우 슬라이드 형태로 출력 -->
							<c:if test="${fn:length(img)!=1}">
								<section>
								    <div id="slider-animation${bb.seq}" class="carousel slide" data-interval="false" data-ride="carousel">
										<ul class="carousel-indicators">
											<c:set  var="uf" value="${bb.uploadfile}"/>
											<!-- "ufStat"이라는 varStatus의 인덱스 값을 사용해서 데이터의 순서 부여 -->
											<c:forEach items="${fn:split(uf, '#여기까지#')}" var="img" varStatus="ufStat">
												<c:if test="${ufStat.index==0}">
													<!-- 첫 번째 사진이 "active" -->
											    	<li data-target="#slider-animation${bb.seq}" data-slide-to="${ufStat.index}" class="active"></li>
												</c:if>
												<c:if test="${ufStat.index!=0}">
											    	<li data-target="#slider-animation${bb.seq}" data-slide-to="${ufStat.index}"></li>
												</c:if>
											</c:forEach>
										</ul>
										<!-- 슬라이드 숏컷 -->
									    <div class="carousel-inner">
											<c:set  var="uf" value="${bb.uploadfile}"/>
											<c:forEach items="${fn:split(uf, '#여기까지#')}" var="img" varStatus="ufStat">
												<!-- 첫 번째가 "active" -->
												<c:if test="${ufStat.index==0}">
													<div class="carousel-item active">
											    		<img class="mainImg" src="${img}" onclick="commentListP(${bb.seq})"/>
											    	</div>
												</c:if>
												<c:if test="${ufStat.index!=0}">
											    	<div class="carousel-item">
											    		<img class="mainImg" src="${img}" onclick="commentListP(${bb.seq})"/>
											    	</div>
												</c:if>
											</c:forEach>
										</div>
										<!-- 좌우 컨트롤러 -->
										<a class="carousel-control-prev" href="#slider-animation${bb.seq}" data-slide="prev">
											<span class="carousel-control-prev-icon"></span>
										</a>
										<a class="carousel-control-next" href="#slider-animation${bb.seq}" data-slide="next">
											<span class="carousel-control-next-icon" ></span>
										</a>
									</div>
								</section>
							</c:if>	
		      			</c:if>
		      			<!-- 게시글 내용 -->
		       			<textarea class="contentArea" rows="5" cols="88" readonly="readonly" onclick="commentListP(${bb.seq})">${bb.content}</textarea>
		      			<h4>
		      			<!-- 태그 출력 -->
		      			<c:if test="${bb.tag!=null}">
			      			<c:set  var="str" value="${bb.tag}"/>
			      			<!-- 태그는 다중 데이터이므로 '#'으로 split하여 출력 -->
							<c:forEach items="${fn:split(str, '#')}" var="item">
			    				<a href="blist?opt=tag&search=${item}">#${item}</a>
							</c:forEach>
						</c:if>
						</h4><br>
	     			 </article>
	      		</c:forEach>
	   		</section><hr>
		</div>
	</div>
	
	<div align="center">
		<!-- '더보기' 실행 시 ajax가 실행 될 공간 -->
		<c:forEach var="p" begin="2" end="${totalPage+1}">
			<div id="plusArea${p}"></div>
		</c:forEach>
		<!-- 더보기 버튼 -->
		<c:if test="${Banana[0]!=null}"><br>
			<button class="morePage" value="${currPage+1}" id="PlusAreaButton">더보기</button><br><br>
		</c:if>
		<!-- 검색 결과 없을 경우 -->
		<c:if test="${Banana[0]==null}">
			<h2 align="center">검색 결과가 없습니다</h2>
		</c:if>
	</div>
	
	<!-- 숏컷 마지막 공간 -->
	<div id="finalArea" align="center"></div>
	
<script src="resources/js/jquery-3.2.1.min.js"></script>
<script src="resources/js/axPlusArea.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/pageList"></script>
<!-- 숏컷 스크립트 -->
<script>
	$(document).ready(function(){
		$(".go_btn").click(function({
			event.preventDefault();
			$('html,body').animate({scrolltop:$(this.hash).offset().top},500);
		})
	})
</script>
</body>
</html>