<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
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
<link rel="stylesheet" href="../resources/CSS/custom.css">
<title>goodsList</title>
</head>
<body>
	<jsp:include page="../template/navBar.jsp" flush="false"></jsp:include>
	<div class="container text-center">
		<div class="row">
			<div class="col">
				<h1>GOODS LIST</h1>
				<jsp:include page="../template/searchBox.jsp" flush="false"></jsp:include>
			</div>
		</div>
	</div>
	<jsp:include page="../template/goodsMenu.jsp" flush="false"></jsp:include>

	<div class="container text-center">
		<div id="categoryTitle" class="text-left"></div>
		<hr>
		<div class="row">
			<c:forEach items="${goodsList }" var="goods">
				<div class="col-md-6 col-xl-3">
					<div class="card">
						<img class="card-img-top" src="../${goods.thumbnail_image }"
							alt="Card image cap">
						<div class="card-body">
							<h5 class="card-title">${goods.name }</h5>
							<p class="card-text">${goods.price }원</p>
							<p class="card-text">
								<small class="text-muted">${goods.formatedTime }</small>
							</p>
							<a href="${contextPath}/goods/goodsArticle?code=${goods.code }"><button type="button" class="btn btn-outline-danger">상세보기</button></a>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<jsp:include page="../template/pageButtonList.jsp" flush="false"></jsp:include>
	<div>
		<input type="hidden" value="${contextPath }" id="contextPath">
		<input type="hidden" value="${category }" id="currentCategory">
		<input type="hidden" value="${search }" id="search">
		<input type="hidden" value="${category_2 }" id="searchName">
		<input type="hidden" value="${category_1 }" id="category_1">
		<input type="hidden" value="${category_2 }" id="category_2">
	</div>
</body>
<script type="text/javascript" src="../resources/JS/goods/goodsList.js"></script>
</html>