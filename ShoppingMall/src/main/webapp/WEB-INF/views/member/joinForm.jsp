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
<link rel="stylesheet" type="text/css" href="../resources/CSS/loading.css">
<link rel="stylesheet" href="../resources/CSS/custom.css">
<title>JoinForm</title>
</head>
<body>
	<jsp:include page="../template/navBar.jsp" flush="false"></jsp:include>
	<div class="container text-center custom-form">
		<h1>SHOPPING MALL JOIN FORM</h1>
		<form id="joinForm" method="post" autocomplete="off">
			<div class="row justify-content-center ">
				<div class="col-md-7 col-12 ">
					<input type="text" class="form-control text-center" placeholder="id"
						aria-label="id" aria-describedby="basic-addon2" name="id">
				</div>
				<div class="col-md-7 col-12 ">
					<input type="password" class="form-control text-center" placeholder="password"
						aria-label="password" aria-describedby="basic-addon2" name="pw">
				</div>
				<div class="col-md-7 col-12 ">
					<input type="email" class="form-control text-center" placeholder="email@example.com" name="email">
				</div>
				<div class="col-md-7 col-12 ">
					<div class="row">
						<div class="col-5">
							<div id="loading"></div>
							<img id="captcha-image">
						</div>
						<div class="col-5">
							<input type="text" class="form-control text-center" placeholder="captca key" name="captcaKey">
						</div>
						<div class="col-2">
							<button type="button" class="btn btn-outline-success" onclick="createCaptcha();" id="refresh-button">Refresh</button>
						</div>
					</div>
				</div>
				<div id="alert" class=""></div>
				<div class="col-md-7 col-12 ">
					<input type="button" value="JOIN" class="btn btn-outline-info" style="width: 100%" onclick="join();" id="join-button">
				</div>
			</div>
		</form>
		<div class="row justify-content-center">
			<div class="col-md-7 col-12 ">
				<a href="${contextPath }/member/loginForm">LOGIN</a>
			</div>
			<div class="col-md-7 col-12 ">
				<a href="${contextPath }/">SHOPPING MALL</a>
			</div>
		</div>
	</div>
	<div>
		<input type="hidden" value="${contextPath }" id="contextPath">
	</div>
</body>
<script type="text/javascript" src="../resources/JS/member/joinForm.js"></script>
</html>
