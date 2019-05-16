<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta charset="utf-8">
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
<title>Insert title here</title>
</head>
<body>
	<div class="container text-center margin">
		<h1>SHOPPING MALL JOIN FORM</h1>
		<form id="joinForm" method="post">
			<div class="row justify-content-center margin">
				<div class="col-md-7 col-12 margin">
					<input type="text" class="form-control" placeholder="id"
						aria-label="id" aria-describedby="basic-addon2" name="id">
				</div>
				<div class="col-md-7 col-12 margin">
					<input type="text" class="form-control" placeholder="password"
						aria-label="password" aria-describedby="basic-addon2" name="pw">
				</div>
				<div class="col-md-7 col-12 margin">
					<input type="email" class="form-control"
						id="exampleDropdownFormEmail1" placeholder="email@example.com"
						name="email">
				</div>
				<div
					class="alert alert-danger alert-dismissible fade show col-md-7 col-12 margin"
					role="alert">
				</div>
				<div
					class="alert alert-warning alert-dismissible fade show col-md-7 col-12 margin"
					role="alert">
				</div>
				<div
					class="alert alert-success alert-dismissible fade show col-md-7 col-12 margin"
					role="alert">
				</div>
				<div class="col-md-7 col-12 margin">
					<input type="button" value="JOIN" class="btn btn-outline-info"
						style="width: 100%" onclick="join();">
				</div>
			</div>
		</form>
		<div class="row justify-content-center">
			<div class="col-md-7 col-12 margin">
				<a href="${contextPath }/member/loginForm">LOGIN</a>
			</div>
			<div class="col-md-7 col-12 margin">
				<a href="${contextPath }/">SHOPPING MALL</a>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$('.alert').hide();
	function join() {
		$('.alert').hide();
		var email = $('input[name=email]').val();
		var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;

		if (exptext.test(email) == false) {
			$(".alert-warning").text("이메일 형식이 잘못되었습니다.");
			$(".alert-warning").fadeIn();
			return false;
		}

		$.ajax({
			url : "${contextPath }/member/join",
			method : "POST",
			data : {
				id : $("input[name=id]").val(),
				pw : $("input[name=pw]").val(),
				email : $("input[name=email]").val()
			},
			type : "json"
		}).done(function(resultData) {
			$("input[name=id]").val("");
			$("input[name=pw]").val("");
			$("input[name=email]").val("");
			var resultJson = JSON.parse(resultData);
			switch (resultJson.result) {
			case 0:
				$(".alert-success").text(resultJson.message);
				$(".alert-success").fadeIn();
				break;
			case 1:
				$(".alert-danger").text(resultJson.message);
				$(".alert-danger").fadeIn();
				break;
			case 2:
				$(".alert-warning").text(resultJson.message);
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
</script>
</html>
