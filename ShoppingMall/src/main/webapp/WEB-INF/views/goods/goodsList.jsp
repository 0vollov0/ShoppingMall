<%@ page language="java" contentType="text/html; charset=EUC-KR"
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
<title>goodsList</title>
</head>
<body>
	<jsp:include page="../template/navMenu.jsp" flush="false"></jsp:include>
	<div class="container text-center">
		<div class="row">
			<div class="col">
				<h1>GOODS LIST</h1>
			</div>
		</div>
	</div>
	<jsp:include page="../template/goodsMenu.jsp" flush="false"></jsp:include>

	<div class="container text-center">
		<h1 class="text-left">${category_1 }${category_2 }</h1>
		<div class="row">
			<c:forEach items="${goodsList }" var="goods">
				<div class="col-md-4 col-xl-3">
					<div class="card">
						<img class="card-img-top" src="../${goods.thumbnail_image }"
							alt="Card image cap">
						<div class="card-body">
							<h5 class="card-title">${goods.name }</h5>
							<p class="card-text">${goods.price }원</p>
							<p class="card-text">
								<small class="text-muted">${goods.formatedTime }</small>
							</p>
							<button type="button" class="btn btn-outline-success">상세보기</button>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<div class="container">
		 <div class="row">
		 	<div class="col-6">
		 		<c:if test="${!empty prevNum}">	
		 			<button type="button" class="btn btn-outline-success" onclick="">이전</button>
		 		</c:if>	
		 	</div>
		    <div class="col-6 text-right">
		    	<c:if test="${!empty nextNum}">
		    		<a href=""><button type="button" class="btn btn-outline-success">다음</button></a>		    		
		    	</c:if>
		    </div>
	 	 </div>
	 </div>
	 <c:if test="${!empty goodsList}">
	 	<c:forEach items="${goodsList }" begin="${fn:length(goodsList)-1}" end="${fn:length(goodsList)}" var="goods">
	 		${goods.name }
	 	</c:forEach>
	 </c:if>
</body>
</html>