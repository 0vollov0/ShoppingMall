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
<title>memberOrderList</title>
</head>
<body>
	<jsp:include page="../template/navBar.jsp" flush="false"></jsp:include>
	<div class="container text-center">
		<h1>MEMBER ORDER LIST</h1>
		<table class="table table-hover">
		    <thead>
		      <tr>
		      	<th scope="col">주문번호</th>
		        <th scope="col">상품코드</th>
		        <th scope="col">주문수량</th>
		        <th scope="col">구매가격</th>
		        <th scope="col">주문일시</th>
		        <th scope="col">배송주소</th>
		        <th scope="col">배송조회</th>
		      </tr>
		    </thead>
		    <tbody>
		    	<c:forEach items="${orderList }" var="order">
		    		<tr>
		    			<td>${order.code }</td>
		        		<td>${order.goodscode }</td>
		        		<td>${order.goodscount }</td>
		        		<td>${order.price } 원</td>
		        		<td>${order.formatedTime }</td>
		        		<td><button type="button" class="btn btn-outline-info" data-toggle="modal" data-target="#deliveryInfo" data-whatever="${order.code }" onclick="showDeliveryModal(${order.code});">조회</button></td>
		        		<td>
		        			<c:choose>
		        				<c:when test="${empty order.company_code ||empty order.invoice_number}">
		        					배송준비 중
			        			</c:when>
			        			<c:otherwise>
			        				<button type="button" class="btn btn-outline-info" data-toggle="modal" data-target="#shippingInfo" onclick="showShippingModal(${order.company_code },${order.invoice_number});">
		    							배송정보 확인
		    						</button>
			        			</c:otherwise>
		        			</c:choose>
	    				</td>
		        	</tr>
		    	</c:forEach>
		    </tbody>
	  </table>
	</div>
	<jsp:include page="../template/pageButtonList.jsp" flush="false"></jsp:include>
	<jsp:include page="../template/shippingModal.jsp" flush="false"></jsp:include>
	<jsp:include page="../template/deliveryModal.jsp" flush="false"></jsp:include>
</body>
<script type="text/javascript">
function viewListPage(page){
	window.location = "${contextPath }/member/memberOrderList?page="+page;
}
function showDeliveryModal(orderCode){
	  $.ajax({
	      url: "${contextPath}/order/getDeliveryInfo",
	      method: "POST",
	      data: {code: orderCode},
	      type: "json"
	    }).done(function(resultData) {
	    	$('#deliveryContent').children().eq(1).text(orderCode);
	    	var deliveryhtml = "";
	    	deliveryhtml += "<h4>"+resultData.buyer+"</h4>";
	    	deliveryhtml += "<h4>"+resultData.shippingRecipient+"</h4>";
	    	deliveryhtml += "<h4>"+resultData.zipCode+"</h4>";
	    	deliveryhtml += "<h4>"+resultData.address+"</h4>";
	    	deliveryhtml += "<h4>"+resultData.detailAddress+"</h4>";
	    	deliveryhtml += "<h4>"+resultData.reference+"</h4>";
	    	$('#deliveryContent').children().eq(2).html(deliveryhtml);
	    }).fail(function() {
	      alert("통신 오류");
	    }).always(function() {});
}
</script>
<script type="text/javascript" src="../resources/JS/member/memberOrderList.js"></script>
</html>
