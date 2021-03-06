function viewListPage(page){
	console.log($('#search').val());
	if ($('#search').val()) {
		window.location = $('#contextPath').val()+"/goods/searchGoods?name="+$('#searchName').val()+"&page="+page;
	}else{
		window.location = $('#contextPath').val()+"/goods/goodsList?category="+$('#currentCategory').val()+"&page="+page;
	}
}

function shoppingBasket() {
	if (isEmptyStock()) {
		alert('재고가 없습니다.');
		return;
	}
	$.ajax({
		url : $('#contextPath').val()+"/member/shoppingBasket",
		method : "POST",
		data : { code : $('#goodsCode').val()},
		type : "json"
	}).done(function(resultData) {
		alert(resultData.message);	
	}).fail(function() {
		$(".alert-danger").text("서버 통신 오류");
		$(".alert-danger").fadeIn();
	}).always(function() {
	});
}
function order(){
	if (isEmptyStock()) {
		alert('재고가 없습니다.');
		return;
	}
	$.ajax({
		url : $('#contextPath').val()+"/member/shoppingBasket",
		method : "POST",
		data : { code : $('#goodsCode').val()},
		type : "json"
	}).done(function(resultData) {
		window.location = $('#contextPath').val()+"/member/shoppingBasketList";
	}).fail(function() {
		$(".alert-danger").text("서버 통신 오류");
		$(".alert-danger").fadeIn();
	}).always(function() {
	});
}

function isEmptyStock(){
	if ($('#stock').val() <= 0) {
		return true;
	}
	return false;
}