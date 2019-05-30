function viewListPage(page){
	console.log($('#search').val());
	if ($('#search').val()) {
		window.location = $('#contextPath').val()+"/goods/searchGoods?name="+$('#searchName').val()+"&page="+page;
	}else{
		window.location = $('#contextPath').val()+"/goods/goodsList?category="+$('#currentCategory').val()+"&page="+page;
	}
}

if ($('#category_2').val().length > 0) {
	var categoryTitle = "";
	categoryTitle += "<span class='custom-middle-font'>" + $('#category_1').val() + "</span><span class='custom-big-font'>"+$('#category_2').val()+"</span>";
	$('#categoryTitle').html(categoryTitle);
}else{
	var categoryTitle = "";
	categoryTitle += "<span class='custom-big-font'>" + $('#category_1').val() + "</span>";
	$('#categoryTitle').html(categoryTitle);
}
