<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>INDEX</title>
</head>
<body>
	<header>
		<div class="">
			<c:choose>
				<c:when test="${empty member}">
					<ul>
						<li><a href="${contextPath}/member/loginForm">로그인</a></li>
						<li><a href="${contextPath}/member/joinForm">회원가입</a></li>
					</ul>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${!member.admin }">
							<ul>
								<li>${member.id } 님 환영합니다.</li>
								<li><a href="${contextPath}/member/#">장바구니</a></li>
								<li><a href="${contextPath}/member/#">주문배송 조회</a></li>
								<li><a href="${contextPath}/member/logout">로그아웃</a></li>
							</ul>
						</c:when>
						<c:otherwise>
							<ul>
								<li>${member.id } 님 환영합니다.</li>
								<li><a href="${contextPath}/#">상품관리</a></li>
								<li><a href="${contextPath}/#">배송관리</a></li>
								<li><a href="${contextPath}/#">회원관리</a></li>
								<li><a href="${contextPath}/member/logout">로그아웃</a></li>
							</ul>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="">
			<a href="${contextPath }"><h1>TITLE</h1></a>
		</div>
		<div class="">
			<input type="text" name="" value="">
		</div>
	</header>
	<section>
		<div class="">
			<ul>
				<li>대분류</li>
				<li>대분류</li>
				<li>대분류</li>
				<li>대분류</li>
				<li>대분류</li>
			</ul>
		</div>
	</section>
	<section>
		<div class="">
			<h1>NEW</h1>
			<ul>
				<li>상품</li>
				<li>상품</li>
				<li>상품</li>
				<li>상품</li>
				<li>상품</li>
				<li>상품</li>
				<li>상품</li>
				<li>상품</li>
				<li>상품</li>
			</ul>
		</div>
		<div class="">
			<h1>BEST</h1>
			<ul>
				<li>상품</li>
				<li>상품</li>
				<li>상품</li>
				<li>상품</li>
				<li>상품</li>
				<li>상품</li>
				<li>상품</li>
				<li>상품</li>
				<li>상품</li>
			</ul>
		</div>
	</section>
	<footer>
		<div class="">
			<ul>
				<li>이용약관</li>
				<li>주소</li>
				<li>대표</li>
			</ul>
		</div>
	</footer>
</body>
</html>
