<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
<title>INDEX</title>
</head>
<body>
	<div class="">
		<ul class="nav justify-content-center">
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
							<li class="nav-item"><a class="nav-link" href="#">${member.id }님환영합니다.</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${contextPath}/member/#">장바구니</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${contextPath}/member/#">주문배송 조회</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${contextPath}/member/logout">로그아웃</a></li>
						</c:when>
						<c:otherwise>
							<li class="nav-item">${member.id }님환영합니다.</li>
							<li class="nav-item"><a class="nav-link"
								href="${contextPath}/#">상품관리</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${contextPath}/#">배송관리</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${contextPath}/#">회원관리</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${contextPath}/member/logout">로그아웃</a></li>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
	<div class="container text-center">
		<div class="row">
			<div class="col">
				<h1>SHOPPING MALL</h1>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<div class="input-group mb-3">
					<input type="text" class="form-control"
						placeholder="Recipient's username"
						aria-label="Recipient's username" aria-describedby="button-addon2">
					<div class="input-group-append">
						<button class="btn btn-outline-secondary" type="button"
							id="button-addon2">Button</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container text-center">
		<div class="row">
			<div class="col-xl-2 col-sm-4 col-4">
				<div class="btn-group" role="group">
					<button id="" type="button" class="btn dropdown-toggle"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						Menu</button>
					<div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
						<a class="dropdown-item" href="#">Dropdown link</a> <a
							class="dropdown-item" href="#">Dropdown link</a>
					</div>
				</div>
			</div>
			<div class="col-xl-2 col-sm-4 col-4">
				<div class="btn-group" role="group">
					<button id="" type="button" class="btn dropdown-toggle"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						Menu</button>
					<div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
						<a class="dropdown-item" href="#">Dropdown link</a> <a
							class="dropdown-item" href="#">Dropdown link</a>
					</div>
				</div>
			</div>
			<div class="col-xl-2 col-sm-4 col-4">
				<div class="btn-group" role="group">
					<button id="" type="button" class="btn dropdown-toggle"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						Menu</button>
					<div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
						<a class="dropdown-item" href="#">Dropdown link</a> <a
							class="dropdown-item" href="#">Dropdown link</a>
					</div>
				</div>
			</div>
			<div class="col-xl-2 col-sm-4 col-4">
				<div class="btn-group" role="group">
					<button id="" type="button" class="btn dropdown-toggle"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						Menu</button>
					<div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
						<a class="dropdown-item" href="#">Dropdown link</a> <a
							class="dropdown-item" href="#">Dropdown link</a>
					</div>
				</div>
			</div>
			<div class="col-xl-2 col-sm-4 col-4">
				<div class="btn-group" role="group">
					<button id="" type="button" class="btn dropdown-toggle"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						Menu</button>
					<div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
						<a class="dropdown-item" href="#">Dropdown link</a> <a
							class="dropdown-item" href="#">Dropdown link</a>
					</div>
				</div>
			</div>
			<div class="col-xl-2 col-sm-4 col-4">
				<div class="btn-group" role="group">
					<button id="" type="button" class="btn dropdown-toggle"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						Menu</button>
					<div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
						<a class="dropdown-item" href="#">Dropdown link</a> <a
							class="dropdown-item" href="#">Dropdown link</a>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="container text-center">
		<h1 class="text-left">NEW</h1>
		<div class="card-deck">
			<div class="card">
				<img class="card-img-top" src="..." alt="Card image cap">
				<div class="card-body">
					<h5 class="card-title">Card title</h5>
					<p class="card-text">This is a longer card with supporting text
						below as a natural lead-in to additional content. This content is
						a little bit longer.</p>
					<p class="card-text">
						<small class="text-muted">Last updated 3 mins ago</small>
					</p>
				</div>
			</div>
			<div class="card">
				<img class="card-img-top" src="..." alt="Card image cap">
				<div class="card-body">
					<h5 class="card-title">Card title</h5>
					<p class="card-text">This card has supporting text below as a
						natural lead-in to additional content.</p>
					<p class="card-text">
						<small class="text-muted">Last updated 3 mins ago</small>
					</p>
				</div>
			</div>
			<div class="card">
				<img class="card-img-top" src="..." alt="Card image cap">
				<div class="card-body">
					<h5 class="card-title">Card title</h5>
					<p class="card-text">This is a wider card with supporting text
						below as a natural lead-in to additional content. This card has
						even longer content than the first to show that equal height
						action.</p>
					<p class="card-text">
						<small class="text-muted">Last updated 3 mins ago</small>
					</p>
				</div>
			</div>
		</div>
	</div>

	<div class="container text-center">
		<h1 class="text-left">BEST</h1>
		<div class="card-deck">
			<div class="card">
				<img class="card-img-top" src="..." alt="Card image cap">
				<div class="card-body">
					<h5 class="card-title">Card title</h5>
					<p class="card-text">This is a longer card with supporting text
						below as a natural lead-in to additional content. This content is
						a little bit longer.</p>
					<p class="card-text">
						<small class="text-muted">Last updated 3 mins ago</small>
					</p>
				</div>
			</div>
			<div class="card">
				<img class="card-img-top" src="..." alt="Card image cap">
				<div class="card-body">
					<h5 class="card-title">Card title</h5>
					<p class="card-text">This card has supporting text below as a
						natural lead-in to additional content.</p>
					<p class="card-text">
						<small class="text-muted">Last updated 3 mins ago</small>
					</p>
				</div>
			</div>
			<div class="card">
				<img class="card-img-top" src="..." alt="Card image cap">
				<div class="card-body">
					<h5 class="card-title">Card title</h5>
					<p class="card-text">This is a wider card with supporting text
						below as a natural lead-in to additional content. This card has
						even longer content than the first to show that equal height
						action.</p>
					<p class="card-text">
						<small class="text-muted">Last updated 3 mins ago</small>
					</p>
				</div>
			</div>
		</div>
	</div>
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
