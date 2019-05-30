var imagesPath = "../resources/images/captchaImages/";
$('.alert').hide();
createCaptcha();
var captcha = new captcha();

//var loadinghtml = "<div class='spinner-border' style='width: 3rem; height: 3rem;' role='status'></div>";

function captcha() {
	this.captchaKey = null;
	this.cpatchaImage = null;

	this.setCaptchaKey = function(captchaKey) {
		this.captchaKey = captchaKey;
	};
	this.getCaptchaKey = function() {
		return this.captchaKey;
	};
	this.setCaptchaImage = function(cpatchaImage) {
		this.cpatchaImage = cpatchaImage;
	};
	this.getCaptchaImage = function() {
		return this.cpatchaImage;
	};
}

function join() {
	$('.alert').hide();
	var email = $('input[name=email]').val();
	var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;

	if (exptext.test(email) == false) {
		turnOnAlert('alert-info', '이메일 형식이 잘못되었습니다.');
		return false;
	}

	$.ajax({
		url : $('#contextPath').val() + "/member/join",
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
			turnOnAlert('alert-success', resultData.message);
			break;
		case 1:
			turnOnAlert('alert-danger', resultData.message);
			break;
		case 2:
			turnOnAlert('alert-warning', resultData.message);
			break;
		case 3:
			turnOnAlert('alert-info', resultData.message);
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
function turnOnAlert(alertStyle, message) {
	$('#alert').hide();
	$('#alert').attr('class','alert ' + alertStyle+ ' alert-dismissible fade show col-md-7 col-12 margin');
	$("#alert").text(message);
	$("#alert").fadeIn("slow");
}
function createCaptcha() {
	$.ajax({
		url : $('#contextPath').val() + "/member/getCaptcha",
		method : "GET"
	}).done(function(resultData) {
		captcha.setCaptchaKey(resultData.key);
		captcha.setCaptchaImage(resultData.image);
		// deleteCaptchaImage();
		$('#refresh-button').attr('disabled', 'disabled');
		$('#captcha-image').removeAttr('src');
		//$('#loading').attr('class', 'loader');
		$('#loading').attr('class', 'spinner-border');
		$('#loading').attr('style', 'width: 3rem; height: 3rem;');
		setTimeout(function() {
			//$('#loading').attr('class', '');
			$('#loading').removeAttr('class');
			$('#loading').removeAttr('style');
			$('#captcha-image').attr('src', imagesPath + resultData.image);
			$('#refresh-button').removeAttr('disabled');
		}, 5000);
	}).fail(function() {
	}).always(function() {
	});
}
function deleteCaptchaImage() {
	$.ajax({
		url : $('#contextPath').val() + "/member/deleteCaptcha",
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