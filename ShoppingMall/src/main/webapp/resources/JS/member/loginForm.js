function login() {
	$.ajax({
		url : $('#contextPath').val()+"/member/login",
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
		switch (resultData.result) {
		case 0:
			window.location = $('#contextPath').val();
			break;
		case 1:
			turnOnAlert('alert-danger',resultData.message);
			break;
		case 2:
			turnOnAlert('alert-warning',resultData.message);
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

function turnOnAlert(alertStyle,message){
	$('#alert').hide();
	$('#alert').attr('class','alert '+ alertStyle +' alert-dismissible fade show col-md-7 col-12 margin');
	$("#alert").text(message);
	$("#alert").fadeIn("slow");
}
