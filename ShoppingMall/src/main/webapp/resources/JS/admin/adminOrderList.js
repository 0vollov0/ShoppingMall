function viewListPage(page){
	window.location = $('#contextPath').val()+"/admin/adminOrderList?page="+page;
}

$('#submit').click(function(){
    $.ajax({
      url: $('#contextPath').val()+"/order/registerShippingInfo",
      method: "POST",
      data: {
        code: $("input[name=code]").val(),
        companyCode: $("select[name=companyCode]").val(),
        invoiceNumber: $("input[name=invoiceNumber]").val()
      },
      type: "json"
    }).done(function(resultData) {
    	var htmlText = "<button type='button' class='btn btn-primary' data-toggle='modal' data-target='#shippingInfo' onclick='showShippingModal("+resultData.companyCode+","+resultData.invoiceNumber+");'>배송정보 확인</button>"
    	$('#'+resultData.code).html(htmlText);
    	$('#invoiceModalClose').click();	
    }).fail(function() {
      alert("통신 오류");
    }).always(function() {});
  });
function writeOrderCode(orderCode){
	$('#invoiceOrderCode').text(orderCode);
}

$('#invoiceModal').on('show.bs.modal', function (event) {
	  var button = $(event.relatedTarget)
	  var recipient = button.data('whatever')
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
	  if (resultData.status != false) {
		  for (var i = 0; i < resultData.trackingDetails.length; i++) {
		        trackingInfo =trackingInfo + "<tr>"
		        trackingInfo =trackingInfo + "<td>" + resultData.trackingDetails[i].timeString + "</td>";
		        trackingInfo =trackingInfo + "<td>" + resultData.trackingDetails[i].where + "</td>";
		        trackingInfo =trackingInfo + "<td>" + resultData.trackingDetails[i].kind + "</td>";
		        trackingInfo =trackingInfo + "</tr>"
		      }	
	  }		
      $('#tracking-info').html(trackingInfo);

    }).fail(function() {
      alert("통신 오류");
    }).always(function() {});
  }

  function showDeliveryModal(orderCode){
	  $.ajax({
	      url: $('#contextPath').val()+"/order/getDeliveryInfo",
	      method: "POST",
	      data: {code: orderCode},
	      type: "json"
	    }).done(function(resultData) {
	    	$('#deliveryContent').children().eq(1).text(orderCode);
	    	var deliveryhtml = "";
	    	deliveryhtml += "<h1>"+resultData.buyer+"</h1>";
	    	deliveryhtml += "<h1>"+resultData.shippingRecipient+"</h1>";
	    	deliveryhtml += "<h1>"+resultData.zipCode+"</h1>";
	    	deliveryhtml += "<h1>"+resultData.address+"</h1>";
	    	deliveryhtml += "<h1>"+resultData.detailAddress+"</h1>";
	    	deliveryhtml += "<h1>"+resultData.reference+"</h1>";
	    	$('#basicModalTitle').text('배송정보');
	    	$('#basicModalContent').html(deliveryhtml);
	    }).fail(function() {
	      alert("통신 오류");
	    }).always(function() {});
  }
  
	function showOrderGoodsInfo(orderCode,goodsName,goodsCode){
		var orderGoodsInfoHtml = "";
		
		orderGoodsInfoHtml += "<p>주문번호 : </p>";
		orderGoodsInfoHtml += "<h1>"+orderCode+"</h1>";
		orderGoodsInfoHtml += "<p>상품이름</p>";
		orderGoodsInfoHtml += "<h1>"+goodsName+"</h1>";
		orderGoodsInfoHtml += "<p>상품코드</p>";
		orderGoodsInfoHtml += "<h1>"+goodsCode+"</h1>";
		console.log(orderGoodsInfoHtml);
		
		$('#basicModalTitle').text();
		$('#basicModalContent').html(orderGoodsInfoHtml);
	}