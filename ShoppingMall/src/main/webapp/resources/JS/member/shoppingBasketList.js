$('.minus').click(function(){
	if (Number.parseInt($(this).next().text()) <= 1) {
		return;
	}
	$.ajax({
		url : $('#contextPath').val()+"/member/basketUpDown",
		method : "POST",
		data : {
			code : $(this).parent().parent().attr('id'),
			action : -1	
			},
		type : "json"
	}).done(function(result) {
		$('#'+result.id).children().eq(4).children().eq(1).text(result.value);
		var temp = Number.parseInt($('#'+result.id).children().eq(2).text());
		$('#total').text(Number.parseInt($('#total').text())-temp);
	}).fail(function() {
		$(".alert-danger").text("서버 통신 오류");
		$(".alert-danger").fadeIn();
	}).always(function() {
	});
});

$('.plus').click(function(){
	if (Number.parseInt($(this).prev().text()) >= Number.parseInt($(this).parent().prev().text())) {
		return;
	}
	$.ajax({
		url : $('#contextPath').val()+"/member/basketUpDown",
		method : "POST",
		data : {
			code : $(this).parent().parent().attr('id'),
			action : 1	
			},
		type : "json"
	}).done(function(result) {
		$('#'+result.id).children().eq(4).children().eq(1).text(result.value);
		var temp = Number.parseInt($('#'+result.id).children().eq(2).text());
		$('#total').text(Number.parseInt($('#total').text())+temp);
	}).fail(function() {
		$(".alert-danger").text("서버 통신 오류");
		$(".alert-danger").fadeIn();
	}).always(function() {
	});
});

function deleteGoods(goodsCode) {
	$.ajax({
		url : $('#contextPath').val()+"/member/deleteShoppingBasket",
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

function order(){
	$.ajax({
		url : $('#contextPath').val()+"/order/requestOrder",
		method : "POST",
		data : {
			buyer : $("input[name=buyer]").val(),
			shippingRecipient : $("input[name=shippingRecipient]").val(),
			zipCode : $("input[name=zipCode]").val(),
			address : $("input[name=address]").val(),
			detailAddress :  $("input[name=detailAddress]").val(),
			reference :  $("input[name=reference]").val()
		},
		type : "json"
	}).done(function(resultData) {
		switch (resultData.result) {
		case 0:
			window.location = $('#contextPath').val()+"/member/memberOrderList"
			break;
		case 1:
			turnOnOrderAlert('alert-info',resultData.message);
			break;
		case 6:
			turnOnOrderAlert('alert-warning',resultData.message);
			break;
		case 7:
			turnOnOrderAlert('alert-danger',resultData.message);
			break;
		default:
			break;
		}
	}).fail(function() {
		$(".alert-danger").text("서버 통신 오류");
		$(".alert-danger").fadeIn();
	}).always(function() {
	});
}

function turnOnOrderAlert(alertStyle,message){
	$('#orderMessage').hide();
	$('#orderMessage').attr('class','alert '+ alertStyle +' alert-dismissible fade show col-12 custom-margin-top');
	$("#orderMessage").text(message);
	$("#orderMessage").fadeIn("slow");
}
