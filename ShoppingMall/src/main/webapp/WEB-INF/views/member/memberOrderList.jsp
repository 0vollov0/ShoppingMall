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
<title>memberOrderList</title>
</head>
<body>
	<jsp:include page="../template/navMenu.jsp" flush="false"></jsp:include>
	<div class="container text-center">
		<table class="table table-hover">
		    <thead>
		      <tr>
		      	<th scope="col">주문번호</th>
		        <th scope="col">상품코드</th>
		        <th scope="col">주문수량</th>
		        <th scope="col">구매가격</th>
		        <th scope="col">주문일시</th>
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
		        		<td>
		        			<c:choose>
		        				<c:when test="${empty order.company_code ||empty order.invoice_number}">
		        					배송준비 중
			        			</c:when>
			        			<c:otherwise>
			        				<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter" onclick="showShippingModal(${order.company_code },${order.invoice_number});">
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
	<jsp:include page="../template/shippingModal.jsp" flush="false"></jsp:include>
</body>
<script type="text/javascript">
function showShippingModal(code,invoice) {
	if (code < 10) {
		code = "0"+code;
	}
    $.ajax({
      url: "http://info.sweettracker.co.kr/api/v1/trackingInfo",
      method: "GET",
      data: {
        t_key: "pIaxM5d2cce16NTj88IbPA",
        t_code: code,
        t_invoice: invoice
      },
      type: "json"
    }).done(function(resultData) {
      var shippingInfo = "";
      shippingInfo = shippingInfo + "<p>";
      shippingInfo = shippingInfo + "보내는 사람 : " + resultData.senderName + "<br>";
      shippingInfo = shippingInfo + "받는 사람 : " + resultData.receiverName + "<br>";
      shippingInfo = shippingInfo + "받는 주소 : " + resultData.receiverAddr + "<br>";
      shippingInfo = shippingInfo + "</p>";
      $('#shipping-info').html(shippingInfo);
      var trackingInfo = "";

      for (var i = 0; i < resultData.trackingDetails.length; i++) {
        trackingInfo =trackingInfo + "<tr>"
        trackingInfo =trackingInfo + "<td>" + resultData.trackingDetails[i].timeString + "</td>";
        trackingInfo =trackingInfo + "<td>" + resultData.trackingDetails[i].where + "</td>";
        trackingInfo =trackingInfo + "<td>" + resultData.trackingDetails[i].kind + "</td>";
        trackingInfo =trackingInfo + "</tr>"
      }
      $('#tracking-info').html(trackingInfo);

    }).fail(function() {
      alert("통신 오류");
    }).always(function() {});
  }
</script>
</html>
