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
					<input type="text" class="form-control text-center" placeholder="id"
						aria-label="id" aria-describedby="basic-addon2" name="id">
				</div>
				<div class="col-md-7 col-12 margin">
					<input type="password" class="form-control text-center" placeholder="password"
						aria-label="password" aria-describedby="basic-addon2" name="pw">
				</div>
				<div class="col-md-7 col-12 margin">
					<input type="email" class="form-control text-center" placeholder="email@example.com" name="email">
				</div>
				<div class="col-md-7 col-12 margin">
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
	var imagesPath = "../resources/images/captchaImages/";
	$('.alert').hide();
	createCaptcha();
	var captcha = new captcha();
	
	function captcha(){
		this.captchaKey=null;
		this.cpatchaImage=null;
		
		this.setCaptchaKey = function(captchaKey){
			this.captchaKey = captchaKey;
		};
		this.getCaptchaKey = function(){
			return this.captchaKey;
		};
		this.setCaptchaImage = function(cpatchaImage){
			this.cpatchaImage = cpatchaImage;
		};
		this.getCaptchaImage = function(){
			return this.cpatchaImage;
		};
	}	
	
	function join() {
		$('.alert').hide();
		var email = $('input[name=email]').val();
		var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;

		if (exptext.test(email) == false) {
			turnOnAlert('alert-info','이메일 형식이 잘못되었습니다.');
			return false;
		}

		$.ajax({
			url : "${contextPath }/member/join",
			method : "POST",
			data : {
				id : $("input[name=id]").val(),
				pw : $("input[name=pw]").val(),
				email : $("input[name=email]").val(),
				userCaptchaKey : $("input[name=captcaKey]").val(),
				captchaKey : captcha.getCaptchaKey()
			},
			type : "json"
		}).done(function(resultData) {
			$("input[name=id]").val("");
			$("input[name=pw]").val("");
			$("input[name=email]").val("");
			$("input[name=captcaKey]").val("");
			switch (resultData.result) {
			case 0:
				turnOnAlert('alert-success',resultData.message);
				break;
			case 1:
				turnOnAlert('alert-danger',resultData.message);
				break;
			case 2:
				turnOnAlert('alert-warning',resultData.message);
				break;
			case 3:
				turnOnAlert('alert-info',resultData.message);
				break;
			default:
				break;
			}
			createCaptcha();
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
	function createCaptcha(){
		$.ajax({
			url : "${contextPath }/member/getCaptcha",
			method : "GET"
		}).done(function(resultData) {
			captcha.setCaptchaKey(resultData.key);
			captcha.setCaptchaImage(resultData.image);
			//deleteCaptchaImage();
			$('#refresh-button').attr('disabled','disabled');
			$('#captcha-image').removeAttr('src');
			$('#loading').attr('class','loader');
			setTimeout(function(){
				$('#loading').attr('class','');
				$('#captcha-image').attr('src',imagesPath+resultData.image);
				$('#refresh-button').removeAttr('disabled');
			},5000);
		}).fail(function() {
		}).always(function() {
		});
	}
	function deleteCaptchaImage(){
		$.ajax({
			url : "${contextPath }/member/deleteCaptcha",
			method : "GET",
			data : {
				image : captcha.getCaptchaImage()
			},
			type : "json"
		}).done(function() {
			alert("완료");
		}).fail(function() {
			
		}).always(function() {
		});
	}
</script>
</html>
