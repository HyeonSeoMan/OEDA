<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.Calendar"%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width" , initial-scale="1">
<title>OEDA</title>
<link rel="stylesheet" href="resources/css/bootstrap.css">
<link rel="stylesheet" href="resources/css/codingBooster.css">
<link rel="stylesheet" href="resources/css/home.css">
</head>
<body>
	<!-- 페이지 숏컷을 위한 시작 공간 -->
	<div id="startArea"></div>
	
	<!-- 검색창 네비게이션 -->
	<div class="jbMenu"><br><br>
		<!-- 검색 폼 -->
		<form action="blist" method="get" class="search-form">
			<!-- 검색 내용 -->
		  	<input type="search" value="${Search}" placeholder="Search" class="search-input" name="search" id="search">
			<button type="submit" class="search-button">
			    <svg class="submit-button">
			    	<use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#searchIcon"></use>
			    </svg>
		  	</button>
		<div class="search-option">
		  	<div>
		  		<!-- 검색 옵션 -->
		  		<%-- ${Opt}는 이전 검색 값을 검색창에 남겨 놓기 위함 --%>
		  		<c:if test="${Opt=='tag'}"><input name="opt" type="radio" value="tag" id="type-special" checked=""></c:if>
		    	<c:if test="${Opt!='tag'}"><input name="opt" type="radio" value="tag" id="type-special"></c:if>
			    <input name="opt" type="radio" value="tag" id="type-special" checked="">
			    <label for="type-special">
			    	<svg class="edit-pen-title">
			        	<use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#special"></use>
			        </svg>
			        <span>Tag</span>
			    </label>
		    </div>
		    <div>
		    	<c:if test="${Opt=='writer'}"><input name="opt" type="radio" value="writer" id="type-users" checked=""></c:if>
		    	<c:if test="${Opt!='writer'}"><input name="opt" type="radio" value="writer" id="type-users" ></c:if>
			    <label for="type-users">
			    	<svg class="edit-pen-title">
			        	<use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#user"></use>
			        </svg>
			        <span>Users</span>
			    </label>
		    </div>
		    <div>
		    	<c:if test="${Opt=='content'}"><input name="opt" type="radio" value="content" id="type-posts" checked=""></c:if>
		    	<c:if test="${Opt!='content'}"><input name="opt" type="radio" value="content" id="type-posts" ></c:if>
			    <label for="type-posts">
			        <svg class="edit-pen-title">
			        	<use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#post"></use>
			        </svg>
			        <span>Contents</span>
			    </label>
		    </div>
		  </div>
		</form>
	</div>
	<!-- 검색 기능 아이콘 심볼 -->
	<svg xmlns="http://www.w3.org/2000/svg" width="0" height="0" display="none">
	  <symbol id="searchIcon" viewBox="0 0 32 32">
	    <path d="M 19.5 3 C 14.26514 3 10 7.2651394 10 12.5 C 10 14.749977 10.810825 16.807458 12.125 18.4375 L 3.28125 27.28125 L 4.71875 28.71875 L 13.5625 19.875 C 15.192542 21.189175 17.250023 22 19.5 22 C 24.73486 22 29 17.73486 29 12.5 C 29 7.2651394 24.73486 3 19.5 3 z M 19.5 5 C 23.65398 5 27 8.3460198 27 12.5 C 27 16.65398 23.65398 20 19.5 20 C 15.34602 20 12 16.65398 12 12.5 C 12 8.3460198 15.34602 5 19.5 5 z" />
	  </symbol>
	  <symbol id="user" viewBox="0 0 32 32">
	    <path d="M 16 4 C 12.145852 4 9 7.1458513 9 11 C 9 13.393064 10.220383 15.517805 12.0625 16.78125 C 8.485554 18.302923 6 21.859881 6 26 L 8 26 C 8 21.533333 11.533333 18 16 18 C 20.466667 18 24 21.533333 24 26 L 26 26 C 26 21.859881 23.514446 18.302923 19.9375 16.78125 C 21.779617 15.517805 23 13.393064 23 11 C 23 7.1458513 19.854148 4 16 4 z M 16 6 C 18.773268 6 21 8.2267317 21 11 C 21 13.773268 18.773268 16 16 16 C 13.226732 16 11 13.773268 11 11 C 11 8.2267317 13.226732 6 16 6 z" /></symbol>
	  <symbol id="post" viewbox="0 0 32 32">
	    <path d="M 3 5 L 3 6 L 3 23 C 3 25.209804 4.7901961 27 7 27 L 25 27 C 27.209804 27 29 25.209804 29 23 L 29 13 L 29 12 L 28 12 L 23 12 L 23 6 L 23 5 L 22 5 L 4 5 L 3 5 z M 5 7 L 21 7 L 21 12 L 21 13 L 21 23 C 21 23.73015 21.221057 24.41091 21.5625 25 L 7 25 C 5.8098039 25 5 24.190196 5 23 L 5 7 z M 7 9 L 7 10 L 7 13 L 7 14 L 8 14 L 18 14 L 19 14 L 19 13 L 19 10 L 19 9 L 18 9 L 8 9 L 7 9 z M 9 11 L 17 11 L 17 12 L 9 12 L 9 11 z M 23 14 L 27 14 L 27 23 C 27 24.190196 26.190196 25 25 25 C 23.809804 25 23 24.190196 23 23 L 23 14 z M 7 15 L 7 17 L 12 17 L 12 15 L 7 15 z M 14 15 L 14 17 L 19 17 L 19 15 L 14 15 z M 7 18 L 7 20 L 12 20 L 12 18 L 7 18 z M 14 18 L 14 20 L 19 20 L 19 18 L 14 18 z M 7 21 L 7 23 L 12 23 L 12 21 L 7 21 z M 14 21 L 14 23 L 19 23 L 19 21 L 14 21 z" />
	  </symbol>
	  <symbol id="special" viewbox="0 0 32 32">
	    <path d="M 4 4 L 4 5 L 4 27 L 4 28 L 5 28 L 27 28 L 28 28 L 28 27 L 28 5 L 28 4 L 27 4 L 5 4 L 4 4 z M 6 6 L 26 6 L 26 26 L 6 26 L 6 6 z M 16 8.40625 L 13.6875 13.59375 L 8 14.1875 L 12.3125 18 L 11.09375 23.59375 L 16 20.6875 L 20.90625 23.59375 L 19.6875 18 L 24 14.1875 L 18.3125 13.59375 L 16 8.40625 z M 16 13.3125 L 16.5 14.40625 L 17 15.5 L 18.1875 15.59375 L 19.40625 15.6875 L 18.5 16.5 L 17.59375 17.3125 L 17.8125 18.40625 L 18.09375 19.59375 L 17 19 L 16 18.40625 L 15 19 L 14 19.59375 L 14.3125 18.40625 L 14.5 17.3125 L 13.59375 16.5 L 12.6875 15.6875 L 13.90625 15.59375 L 15.09375 15.5 L 15.59375 14.40625 L 16 13.3125 z" />
	  </symbol>
	</svg>

	<!-- 유틸 사이드바 -->
	<div id="sideBarArea">
		<%@ include file="/WEB-INF/views/UIutill/sideBar.jsp"%>
	</div>
	
	<!-- 친구 관리 사이드 바 -->
	<c:if test="${logId!=null}">
		<div id="friendBarArea">
			<%@ include file="/WEB-INF/views/UIutill/friendRecommendation.jsp"%>
		</div>
	</c:if>
	
	<!-- 점보 트론 페이지 배너 -->
	<div class="jumbotron">
		<c:choose>
			<c:when test="${logId==null}">
				<!-- 비로그인 시 -->
				<h1 class="text-center">OEDA</h1>
			</c:when>
			<c:when test="${personalPage eq logId}">
				<!-- 마이 페이지 -->
				<h1 class="text-center">나의 페이지</h1>
			</c:when>
			<c:when test="${personalPage!=null}">
				<!-- 다른 유저의 페이지 -->
				<h1 class="text-center">${personalPage}님의 페이지</h1>
			</c:when>
			<c:otherwise>
				<!-- 로그인 시 -->
				<h1 class="text-center">OEDA</h1>
			</c:otherwise>
		</c:choose><br>
		
		<p class="text-center">
			<c:choose>
				<c:when test="${logId==null}">
					<!-- 비로그인 시 -->
					<a class="btn btn-primary btn-lg" data-target="#modalLogin" data-toggle="modal" role="button">접속하기</a>
					<a class="btn btn-primary btn-lg" data-target="#modalJoin" data-toggle="modal" role="button">가입하기</a>
				</c:when>
				<c:when test="${personalPage eq logId}">
					<!-- 마이 페이지 -->
					<a class="btn btn-primary btn-lg" data-target="#modalInsert" data-toggle="modal" role="button">게시물 작성</a>
					<a class="btn btn-primary btn-lg" data-target="#modalUpdate" data-toggle="modal">내 정보 수정</a>
					<a class="btn btn-primary btn-lg" onclick="memberDelete('${logId}')">회원 탈퇴</a>
				</c:when>
				<c:when test="${personalPage!=null}">
					<!-- 다른 유저의 페이지 -->
					<a class="btn btn-primary btn-lg" data-target="#modalVisit" data-toggle="modal" role="button">글 남기기</a>
				</c:when>
				<c:when test="${logId!=null}">
					<!-- 로그인 시 -->
					<a class="btn btn-primary btn-lg" data-target="#modalInsert" data-toggle="modal" role="button">게시물 작성</a>
				</c:when>
			</c:choose>
		</p>
	</div>
	
	<!-- 게시글 목록 출력 -->
	<div class="plist">
		<%@ include file="/WEB-INF/views/board/pageList.jsp"%>
	</div>
	
	<!-- 모달 -->
	<!-- 모달 로그인 -->
	<div class="modal" id="modalLogin" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					OEDA 로그인
					<button class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body" style="text-align: center;">
					<%@ include file="/WEB-INF/views/login/loginForm.jsp"%>
				</div>
			</div>
		</div>
	</div>
	<!-- 모달 회원 가입 -->
	<div class="modal" id="modalJoin" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					OEDA 가입하기
					<button class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body" style="text-align: center;">
					<%@ include file="/WEB-INF/views/member/joinForm.jsp"%>
				</div>
			</div>
		</div>
	</div>
	<!-- 모달 정보 수정 -->
	<div class="modal" id="modalUpdate" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					내 정보 수정
					<button class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body" style="text-align: center;">
					<%@ include file="/WEB-INF/views/member/updateForm.jsp"%>
				</div>
			</div>
		</div>
	</div>
	<!-- 모달 회원 탈퇴 -->
	<div class="modal" id="modalDelete" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					회원 탈퇴
					<button class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body" style="text-align: center;">
					<%@ include file="/WEB-INF/views/member/joinForm.jsp"%>
				</div>
			</div>
		</div>
	</div>
	<!-- 모달 게시물 작성 -->
	<div class="modal" id="modalInsert" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					게시물 작성
					<button class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body" style="text-align: center;">
					<%@ include file="/WEB-INF/views/board/binsertForm.jsp"%>
				</div>
			</div>
		</div>
	</div>
	<!-- 모달 글 작성 -->
	<div class="modal" id="modalVisit" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					글 남기기
					<button class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body" style="text-align: center;">
					<%@ include file="/WEB-INF/views/board/visitor'sBookForm.jsp"%>
				</div>
			</div>
		</div>
	</div>
	
	<%
		String logFail = (String) session.getAttribute("logFail");
		if("fail".equals(logFail)){           
		session.setAttribute("logFail", null); 
	%> 
	<script>
		alert("접속 실패입니다. 계정을 확인해주세요.");
	</script>
	<%}%>
<script src="resources/js/home.js"></script>
</body>
</html>
