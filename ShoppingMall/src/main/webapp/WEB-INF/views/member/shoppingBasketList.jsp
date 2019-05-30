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
<title>goodsList</title>
</head>
<body>
	<c:set var="totalPrice" value="0"></c:set>
	<jsp:include page="../template/navBar.jsp" flush="false"></jsp:include>
	<div class="container text-center">
		<h1>BASKET LIST</h1>
      <table class="table table-striped">
        <thead>
          <tr>
            <th scope="col">상품이미지</th>
            <th scope="col">상품명</th>
            <th scope="col">상품가격</th>
            <th scope="col">상품재고</th>
            <th scope="col">주문수량</th>
            <th scope="col">장바구니 삭제</th>
          </tr>
        </thead>
        <tbody>
        	<c:forEach items="${shoppingBasketList }" var="basket">
	        	<tr id="${basket.value.goods.code}">
	        		<td><img class="basket-img" src="../${basket.value.goods.thumbnail_image }"></td>
	        		<td>${basket.value.goods.name }</td>
	        		<td>${basket.value.goods.price }</td>
	        		<td id="stock">${basket.value.goods.stock }</td>
	        		<td><button type="button" class="btn btn-outline-info minus">-</button><span id="count">${basket.value.count }</span><button type="button" class="btn btn-outline-info plus">+</button></td>
	        		<td><button type="button" class="btn btn-outline-danger" onclick="deleteGoods('${basket.value.goods.code}');">삭제</button></td>
	        	</tr>
	        	<c:set var="totalPrice" value="${basket.value.count*basket.value.goods.price+totalPrice}"></c:set>
        	</c:forEach>
        </tbody>
      </table>
      <div  class="text-right">
      	<h4>총 금액:<span id="total">${totalPrice }</span> 원</h4>
      </div>
  	</div>
  	<div class="container text-center">
    <h1>DELIVERY FORM</h1>
    <form action="${contextPath }/order/requestOrder" method="POST" id="order-form" autocomplete="off">
    	<table class="table table-striped">
	      <tbody>
	        <tr>
	          <th>구매자</th>
	          <td><input class="form-control text-center" type="text" name="buyer"></td>
	          <th>수령인</th>
	          <td><input class="form-control text-center" type="text" name="shippingRecipient"></td>
	        </tr>
	        <tr>
	          <th>우편번호</th>
	          <td colspan="2"><input class="form-control text-center" type="text" id="sample3_postcode" name="zipCode"></td>
	          <td><input class="form-control" type="button" onclick="sample3_execDaumPostcode()" value="우편번호 찾기"></td>
	        </tr>
	        <tr>
	          <th>주소</th>
	          <td><input class="form-control text-center" type="text" id="sample3_address" name="address"></td>
	          <th>상세주소</th>
	          <td><input class="form-control text-center" type="text" id="sample3_detailAddress" name="detailAddress"></td>
	        </tr>
	        <tr>
	          <th>참고항목</th>
	          <td colspan="3"><input class="form-control text-center" type="text" id="sample3_extraAddress" name="reference"></td>
	        </tr>
	      </tbody>
	    </table>
	    <div id="wrap" class="text-center" style="display:none;border:1px solid;width:500px;height:300px;margin:5px 0;position:relative">
	      <img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
	    </div>
	    <div class="text-right">
	    	<button type="button" class="btn btn-outline-success  col-md-3 col-6" onclick="order();" >구매</button>
	    </div>
    </form>
    <div id="orderMessage"></div>
  	</div>
  	<div>
		<input type="hidden" value="${contextPath }" id="contextPath">
	</div>
</body>
<script type="text/javascript" src="../resources/JS/member/shoppingBasketList.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript" src="../resources/JS/addressAPI.js"></script>
</html>
