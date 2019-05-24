<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		  <a class="navbar-brand" href="${contextPath}">SHOPPING MALL</a>
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		  <div class="collapse navbar-collapse" id="navbarSupportedContent">
		    <ul class="navbar-nav mr-auto">
		    </ul>
		    <div class="navbar-text">
      			<ul class="navbar-nav mr-auto">
		    	<c:choose>
					<c:when test="${empty member}">
						<li class="nav-item"><a class="nav-link"
							href="${contextPath}/member/loginForm">로그인</a></li>
						<li class="nav-item"><a class="nav-link"
							href="${contextPath}/member/joinForm">회원가입</a></li>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${!member.admin }">
								<li class="nav-item"><a class="nav-link"
									href="${contextPath}/member/shoppingBasketList">장바구니 목록</a></li>
								<li class="nav-item"><a class="nav-link"
									href="${contextPath}/member/memberOrderList">주문배송 조회</a></li>
								<li class="nav-item dropdown">
									<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		          						${member.id }님환영합니다.
		        					</a>
		        					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							          <a class="dropdown-item" href="${contextPath}/member/logout">로그아웃</a>
							        </div>
								</li>
							</c:when>
							<c:otherwise>
								<li class="nav-item"><a class="nav-link"
									href="${contextPath}/admin/registerForm">상품등록</a></li>
								<li class="nav-item"><a class="nav-link"
									href="${contextPath}/admin/adminOrderList">주문/배송관리</a></li>
								<li class="nav-item"><a class="nav-link"
									href="${contextPath}/#">회원관리</a></li>
								<li class="nav-item dropdown">
									<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		          						${member.id }님환영합니다.
		        					</a>
		        					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							          <a class="dropdown-item" href="${contextPath}/member/logout">로그아웃</a>
							        </div>
								</li>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
				</ul>
    		</div>
  		</div>
	</nav>
</body>
</html>