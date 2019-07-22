<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link rel="stylesheet" href="resources/css/sideBar.css">
<title>sideBar</title>
<meta charset="UTF-8">
</head>
<body>
	<!-- 유틸 사이드바 -->
	<div id="sideContainer">
		<div class="row">
			<div class="wrapper">
	    	    <div class="side-bar">
	                <ul>
	                    <li class="menu-head">
	                    	<!-- 로그인 아이디 출력 -->
	                    	<c:if test="${logId!=null}">
	                    		 <img class="logImg" src="${logImg}" style="width:30px;">&nbsp;
	                    		 <!-- 마이 페이지 -->
	                    		 <a href="blist?opt=myPost&search=${logId}">${logId}</a>
	                    	</c:if>
	                    	<!-- 비로그인 시 -->
	                    	<c:if test="${logId==null}">로그인 해주십시오</c:if>
	                    	<!-- 푸시 애니메이션 -->
	                        <a class="push_menu">
	                        	<img src="resources/images/controls.png" class="float-right" style="width:20px;">
	                        </a>
	                    </li>
	                    <div class="menu">
	                    	<!-- 태그 검색 중 일 시 출력 --> 
	                    	<c:if test="${tagValue!=null}">
								<li>
									<a href="#">
										<img src="resources/images/tag.png" style="width:25px;">&nbsp;
										#${tagValue}
									</a>
								</li>
							</c:if>
	                        <li>
	                        	<!-- 홈으로 -->
	                        	<a href="home" class="active">
	                             	<img src="resources/images/home.png" style="width:25px;">&nbsp;
	                            	home
	                           	</a>
	                        </li>
	                        <c:choose>
								<c:when test="${logId!=null}">
								<!-- 로그인 중 일 시 -->
									<li>
										<!-- 내가 공유 되거나 내가 작성한 공유 글 -->
					                	<a href="blist?opt=share_tag&search=${logId}">
											<img src="resources/images/share.png" style="width:25px;">&nbsp;
					                        share
					                    </a>
					            	</li>
									<li>
										<!-- 게시물 작성 -->
										<a data-target="#modalInsert" data-toggle="modal">
											<img src="resources/images/makeContent.png" style="width:25px;">&nbsp;
											게시물 작성
										</a>
									</li>
									<li>
										<!-- 접속 종료 -->
										<a href="logout">
											<img src="resources/images/logout.png" style="width:25px;">&nbsp;
											접속 끊기
										</a>
									</li>
								</c:when>
								<c:otherwise>
								<!-- 비로그인 시 -->
									<li>
										<!-- 로그인 -->
										<a data-target="#modalLogin" data-toggle="modal">
											<img src="resources/images/login.png" style="width:25px;">&nbsp;
											접속하기
										</a>
									</li>
									<li>
										<!-- 회원 가입 -->
										<a data-target="#modalJoin" data-toggle="modal">
											<img src="resources/images/join.png" style="width:25px;">&nbsp;
											가입하기
										</a>
									</li>
								</c:otherwise>
							</c:choose>
	                    </div>
	                </ul>
	                <div class="pagination-wrap">
				    	<ul class="pagination pagination-v1">
				    		<!-- 현재 페이지가 5 이상 일 시 좌우 컨트롤러 출력 -->
				        	<c:if test="${currPage>5}">
				          		<li><a href="#">-</a></li>
				         	</c:if>
				         	<!-- 페이지 숏컷 출력 -->
				          	<c:forEach var="s" begin="1" end="${currPage}">	
					 			<li><a href="#startArea${s}" class="go_btn">${s}</a></li>
						  	</c:forEach>
						  	<!-- 현재 페이지가 5 이상 일 시 좌우 컨트롤러 출력 -->
					        <c:if test="${currPage>5}">
				          		<li><a href="#">+</a></li>
				         	</c:if>
				       	</ul>
					</div>	
	    	    </div>   
			</div>
		</div>
	</div>
	
<script src="resources/js/jquery-3.2.1.min.js"></script>
<script src="resources/js/bootstrap.js"></script>
<script src="resources/js/sideBar.js"></script>
</body>
</html>
