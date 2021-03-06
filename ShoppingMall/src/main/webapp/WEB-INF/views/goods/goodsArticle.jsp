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
<link rel="stylesheet" href="../resources/CSS/custom.css">
<title>goodsArticle</title>
</head>
<body>
	<jsp:include page="../template/navBar.jsp" flush="false"></jsp:include>
	<div class="container text-center">
		<h1>GOODS ARTICLE</h1>
	    <table class="table table-striped">
	      <thead>
	        <tr>
	          <th colspan="4">상품설명</th>
	        </tr>
	      </thead>
	      <tbody>
	        <tr>
	          <th>상품명 :</th>
	          <td>${goods.name }</td>
	          <th>상품코드 :</th>
	          <td>${goods.code }</td>
	        </tr>
	        <tr>
	          	<th>카테고리 :</th>
	          	<td><small class="text-muted">${category_1 }</small> - ${category_2 }</td>
	          	<th>등록일 :</th>
	          	<td>${goods.formatedTime }</td>
	        </tr>
	        <tr>
	        	<th>판매량 :</th>
	          	<td>${goods.sale_count }</td>
	          	<th>재고 :</th>
	          	<td>${goods.stock }</td>
	        </tr>
	        <tr>
	          	<th>가격 :</th>
	          	<td colspan="3">${goods.price } 원</td>	          
	        </tr>
	        <tr>
	          <td colspan="4">
	            <p>
	              <img src="../${goods.thumbnail_image }">
	            </p>
	            <p>${goods.description }</p>
	          </td>
	        </tr>
	      </tbody>
	    </table>
    <div class="row">
      <div class="col-6 text-left">
          <button type="button" class="btn btn-outline-dark" onclick="history.go(-1);">이전 페이지</button>
      </div>
      <div class="col-6 text-right">
      	<c:choose>
      		<c:when test="${member.admin }">
      			<form action="${contextPath }/admin/goodsModifyForm" method="POST">
      				<input type="hidden" name="name" value="${goods.name }">
      				<input type="hidden" name="code" value="${goods.code }">
      				<input type="hidden" name="category_1" value="${category_1 }">
      				<input type="hidden" name="category_2" value="${category_2 }">
      				<input type="hidden" name="price" value="${goods.price }">
      				<input type="hidden" name="stock" value="${goods.stock }">
      				<input type="hidden" name="thumbnail_image" value="${goods.thumbnail_image }">
      				<input type="hidden" name="description" value="${goods.description }">
      				<input type="submit" class="btn btn-outline-success" value="수정">
      				<button type="button" class="btn btn-outline-danger" data-toggle="modal" data-target="#deleteGoodsModal">
					  삭제
					</button>
      			</form>	        	
	      	</c:when>
	      	<c:otherwise>
	      		<button type="button" class="btn btn-outline-primary" onclick="shoppingBasket();">장바구니</button>
	        	<button type="button" class="btn btn-outline-success" onclick="order();">주문하기</button>
	      	</c:otherwise>
	  	</c:choose>
      </div>
    </div>
  </div>
  <div class="modal fade" id="deleteGoodsModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">상품 삭제</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        	정말 상품을 삭제하시겠습니까?
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
	        <a href="${contextPath }/admin/deleteGoods?code=${goods.code}"><button type="button" class="btn btn-danger">삭제하기</button></a>
	      </div>
	    </div>
	  </div>
	</div>
	<div>
		<input type="hidden" value="${contextPath }" id="contextPath">
		<input type="hidden" value="${goods.code }" id="goodsCode">
		<input type="hidden" value="${goods.stock }" id="goodsStock">
	</div>
</body>
<script type="text/javascript" src="../resources/JS/goods/goodsArticle.js"></script>
</html>
