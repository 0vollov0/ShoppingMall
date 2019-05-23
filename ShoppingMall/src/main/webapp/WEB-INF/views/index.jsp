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
<script src="https://code.jquery.com/jquery-3.4.1.js"
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
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
	<jsp:include page="template/navMenu.jsp" flush="false"></jsp:include>
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
	<jsp:include page="template/goodsMenu.jsp" flush="false"></jsp:include>
	
	<div class="container text-center">
		<h1 class="text-left">NEW</h1>
		<div class="card-deck">
			<c:forEach items="${newGoods }" var="goods">
				<div class="card">
					<img class="card-img-top" src=".${goods.thumbnail_image }" alt="Card image cap">
					<div class="card-body">
						<h5 class="card-title">${goods.name }</h5>
						<p class="card-text">${goods.price } 원</p>
						<p class="card-text">
							<small class="text-muted">${goods.formatedTime }</small>
						</p>
						<a href="${contextPath}/goods/goodsArticle?code=${goods.code }"><button type="button" class="btn btn-outline-success">상세보기</button></a>						
					</div>
				</div>
			</c:forEach>
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
