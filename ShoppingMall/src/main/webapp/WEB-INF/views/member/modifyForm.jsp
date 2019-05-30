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
<link rel="stylesheet" href="../resources/CSS/custom.css">	
<style media="screen">
.margin {
	margin-top: 20px;
}
</style>
<title>MODIFY FORM</title>
</head>
<body>
	<jsp:include page="../template/navBar.jsp" flush="false"></jsp:include>
	<div class="container text-center margin">
		<h1>MODIFY MEMBER</h1>
		<form id="loginForm" method="post">
			<div class="row justify-content-center margin">
				<div class="col-md-7 col-12 margin">
					<input type="text" class="form-control text-center" aria-describedby="basic-addon2" name="id" readonly="readonly" value="${member.id }"> 
				</div>
				<div class="col-md-7 col-12 margin">
					<input type="password" class="form-control text-center" placeholder="PASSWORD" aria-label="PASSWORD" aria-describedby="basic-addon2" name="pw">
				</div>
				<div class="col-md-7 col-12 margin">
					<input type="password" class="form-control text-center" placeholder="NEW PASSWORD" aria-label="PASSWORD" aria-describedby="basic-addon2" name="newpw">
				</div>
				<div id="alert" class=""></div>
				<div class="col-md-7 col-12 margin">
					<input type="button" value="MODIFY" class="btn btn-outline-info"
						style="width: 100%" onclick="modify();"/>
				</div>
			</div>
		</form>
		<div class="row justify-content-center">
			<div class="col-md-7 col-12 margin">
				<a href="${contextPath }/">SHOPPING MALL</a>
			</div>
		</div>
	</div>
	<div>
		<input type="hidden" value="${contextPath }" id="contextPath">
	</div>
</body>
<script type="text/javascript" src="../resources/JS/member/modifyForm.js"></script>
</html>
