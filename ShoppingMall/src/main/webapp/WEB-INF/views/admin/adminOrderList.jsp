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
<title>adminOrderList</title>
</head>
<body>
	<jsp:include page="../template/navMenu.jsp" flush="false"></jsp:include>
	<div class="container text-center">
		<table class="table table-hover">
		    <thead>
		      <tr>
		      	<th scope="col">주문번호</th>
		      	<th scope="col">구매자</th>
		        <th scope="col">상품코드</th>
		        <th scope="col">주문수량</th>
		        <th scope="col">구매가격</th>
		        <th scope="col">주문일시</th>
		        <th scope="col">배송조회</th>
		        <th scope="col">배송정보 입력</th>
		      </tr>
		    </thead>
		    <tbody>
		    	<c:forEach items="${orderList }" var="order">
		    		<tr>
		    			<td>${order.code }</td>
		    			<td>${order.userid }</td>
		        		<td>${order.goodscode }</td>
		        		<td>${order.goodscount }</td>
		        		<td>${order.price } 원</td>
		        		<td>${order.formatedTime }</td>
		        		<td id="${order.code }">
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
	    				<td><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="${order.code }">입력/수정</button></td>
		        	</tr>
		    	</c:forEach>
		    </tbody>
	  </table>
	</div>
	<jsp:include page="../template/shippingModal.jsp" flush="false"></jsp:include>
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">배송 정보 입력</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <form>
          <div class="modal-body">
            <p>주문번호 :</p>
            <h1></h1>
            <input type="hidden" name="code" value="">
            <div class="form-group">
              <label for="recipient-name" class="col-form-label">택배회사 코드:</label>
              <input type="text" class="form-control" name="companyCode">
            </div>
            <div class="form-group">
              <label for="message-text" class="col-form-label">운송장 번호:</label>
              <input type="text" class="form-control" name="invoiceNumber">
            </div>

          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal" id="modal-close-button">닫기</button>
            <button type="button" class="btn btn-primary" id="submit">입력</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</body>
<script type="text/javascript">
$('#submit').click(function(){
    $.ajax({
      url: "${contextPath}/admin/registerShippingInfo",
      method: "POST",
      data: {
        code: $("input[name=code]").val(),
        companyCode: $("input[name=companyCode]").val(),
        invoiceNumber: $("input[name=invoiceNumber]").val()
      },
      type: "json"
    }).done(function(resultData) {
      var htmlText = "<button type='button' class='btn btn-primary' data-toggle='modal' data-target='#exampleModalCenter' onclick='showShippingModal("+resultData.companyCode+","+resultData.invoiceNumber+");'>배송정보 확인</button>"
      $('#'+resultData.code).html(htmlText);
      $('#modal-close-button').click();
    }).fail(function() {
      alert("통신 오류");
    }).always(function() {});
  });
$('#exampleModal').on('show.bs.modal', function (event) {
	  var button = $(event.relatedTarget)
	  var recipient = button.data('whatever')
	  $('h1').text(recipient);
	  $('input[name=code]').val(recipient);
	})
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
