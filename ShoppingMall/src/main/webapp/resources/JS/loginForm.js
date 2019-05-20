$('.alert').hide();

function login(contextPath) {
	$.ajax({
		url : contextPath+"/member/login",
		method : "POST",
		data : {
			id : $("input[name=id]").val(),
			pw : $("input[name=pw]").val()
		},
		type : "json"
	}).done(function(resultData) {
		$("input[name=id]").val("");
		$("input[name=pw]").val("");
		$('.alert').hide();
		// var resultJson = JSON.parse(resultData);
		switch (resultData.result) {
		case 0:
			window.location = "${contextPath}";
			break;
		case 1:
			$(".alert-danger").text(resultData.message);
			$(".alert-danger").fadeIn();
			break;
		case 2:
			$(".alert-warning").text(resultData.message);
			$(".alert-warning").fadeIn();
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