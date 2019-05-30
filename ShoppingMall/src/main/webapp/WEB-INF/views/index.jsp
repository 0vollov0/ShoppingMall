<%@ page language="java" contentType="text/html; charset=UTF-8"
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
<link rel="stylesheet" type="text/css" href="resources/CSS/custom.css">
<title>INDEX</title>
</head>
<body>
	<jsp:include page="template/navBar.jsp" flush="false"></jsp:include>
	<div class="container text-center">
		<h1>SHOPPING MALL</h1>
		<jsp:include page="template/searchBox.jsp" flush="false"></jsp:include>
	</div>
	<jsp:include page="template/goodsMenu.jsp" flush="false"></jsp:include>
	
	<div class="container text-center ">
		<h1 class="text-left">NEW ITEMS</h1>
			<div class="row">
			<c:forEach items="${newGoods }" var="goods">
				<div class="col-md-6 col-xl-3">
					<div class="card">
						<img class="card-img-top" src=".${goods.thumbnail_image }" alt="Card image cap">
						<div class="card-body">
							<span class="badge badge-pill badge-warning">NEW</span>
							<h5 class="card-title">${goods.name }</h5>
							<p class="card-text">${goods.price } 원</p>
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

	<div class="container text-center custom-margin-bottom">
		<h1 class="text-left">BEST ITEMS</h1>
		<div class="row">
			<c:forEach items="${bestGoods }" var="goods">
				<div class="col-md-6 col-xl-3">
					<div class="card">
						<img class="card-img-top" src=".${goods.thumbnail_image }" alt="Card image cap">
						<div class="card-body">
							<span class="badge badge-pill badge-danger">BEST</span>
							<h5 class="card-title">${goods.name }</h5>
							<p class="card-text">${goods.price } 원</p>
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
	<div>
		<input type="hidden" value="${message }" id="message">
	</div>>
</body>
<script type="text/javascript">
	if ($('#message').val() == true) {
		alert($('#message').val());
	}
</script>
</html>
