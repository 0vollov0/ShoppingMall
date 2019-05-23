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
<title>goodsList</title>
</head>
<body>
	<c:set var="totalPrice" value="0"></c:set>
	<jsp:include page="../template/navMenu.jsp" flush="false"></jsp:include>
	<div class="container text-center">
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
	        		<td><img src="../${basket.value.goods.thumbnail_image }" height="50px"></td>
	        		<td>${basket.value.goods.name }</td>
	        		<td>${basket.value.goods.price }</td>
	        		<td>${basket.value.goods.stock }</td>
	        		<td><span class="minus">-</span><span>${basket.value.count }</span><span class="plus">+</span></td>
	        		<td><button type="button" class="btn btn-outline-info" onclick="deleteGoods(${basket.value.goods.code});">삭제</button></td>
	        	</tr>
	        	<c:set var="totalPrice" value="${basket.value.count*basket.value.goods.price+totalPrice}"></c:set>
        	</c:forEach>
        </tbody>
      </table>
      <div  class="text-right">
      	<h4>총 금액:<span id="total">${totalPrice }</span> 원</h4>
      </div>
  	</div>
</body>
<script type="text/javascript">
	$('.minus').click(function(){
		var temp = $(this).next().text();
		if (temp == 0) {
			return;
		}
		temp--;
		$(this).next().text(temp);
		temmp = Number.parseInt($(this).parent().prev().prev().text());
		$('#total').text($('#total').text()-temmp);
	});

	$('.plus').click(function(){
		var temp = $(this).prev().text();
		temp++;
		$(this).prev().text(temp);
		console.log($(this).parent().prev().prev().text());
		temmp = Number.parseInt($(this).parent().prev().prev().text());
		$('#total').text(Number.parseInt($('#total').text())+temmp);
	});
	
	function deleteGoods(goodsCode) {
		$.ajax({
			url : "${contextPath }/member/deleteShoppingBasket",
			method : "GET",
			data : { code : goodsCode},
			type : "json"
		}).done(function(resultData) {
			var price = Number.parseInt($('#'+resultData.result).children().eq(2).text());
			var count = Number.parseInt($('#'+resultData.result).children().eq(4).children().eq(1).text());
			var total = Number.parseInt($('#total').text());
			total = total - price * count;
			$('#total').text(total);
			$('#'+resultData.result).remove();	
		}).fail(function() {
			$(".alert-danger").text("서버 통신 오류");
			$(".alert-danger").fadeIn();
		}).always(function() {
		});
	}
</script>
</html>
