function showDeliveryModal(orderCode){
	  $.ajax({
	      url: $('#contextPath').val()+"/order/getDeliveryInfo",
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
      console.log(resultData);
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