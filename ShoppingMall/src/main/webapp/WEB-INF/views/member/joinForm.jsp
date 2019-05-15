<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
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
		<form class="" action="${contextPath }/member/join" method="post">
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
				<div class="col-md-7 col-12 margin">
					<input type="submit" value="JOIN" class="btn btn-outline-info"
						style="width: 100%">
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
</html>
