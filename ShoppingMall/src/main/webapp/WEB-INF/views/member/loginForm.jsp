<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://code.jquery.com/jquery-3.4.1.js"
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
<style media="screen">
.margin {
	margin-top: 20px;
}
</style>
<title>LOGIN FORM</title>
</head>
<body>
	<div class="container text-center margin">
		<h1>SHOPPING MALL LOGIN</h1>
		<!--  
		<form class="" action="${contextPath }/member/login" method="post">
		-->
		<form id="loginForm" method="post">
			<div class="row justify-content-center margin">
				<div class="col-md-7 col-12 margin">
					<input type="text" class="form-control text-center" placeholder="ID"
						aria-label="ID" aria-describedby="basic-addon2" name="id">
				</div>
				<div class="col-md-7 col-12 margin">
					<input type="password" class="form-control text-center" placeholder="PASSWORD"
						aria-label="PASSWORD" aria-describedby="basic-addon2" name="pw">
				</div>
				<div id="alert" class=""></div>
				<div class="col-md-7 col-12 margin">
					<input type="button" value="LOGIN" class="btn btn-outline-info"
						style="width: 100%" onclick="login();"/>
				</div>
			</div>
		</form>
		<div class="row justify-content-center">
			<div class="col-md-7 col-12 margin">
				<a href="${contextPath }/member/joinForm">JOIN US</a>
			</div>
			<div class="col-md-7 col-12 margin">
				<a href="${contextPath }/">SHOPPING MALL</a>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$('.alert').hide();
	function login() {
		$.ajax({
			url : "${contextPath }/member/login",
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
			//var resultJson = JSON.parse(resultData);
			switch (resultData.result) {
			case 0:
				window.location = "${contextPath}";
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
</script>
</html>
