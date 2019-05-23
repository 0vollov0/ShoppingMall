<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.js"
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
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
<title>register form</title>
</head>
<body>
	<jsp:include page="../template/navMenu.jsp" flush="false"></jsp:include>
	<div class="container text-center">
		<div class="row">
			<div class="col">
				<h1>GOODS REGISTER FORM</h1>
			</div>
		</div>
		<form id="registerForm" method="post" action="${contextPath }/admin/registerGoods" enctype="multipart/form-data" autocomplete="off">
			<div class="row justify-content-center margin">
				<div class="col-md-7 col-12 margin">
					<table class="table table-hover">
						<thead>
							<tr>
								<th scope="col">항목</th>
								<th scope="col">입력란</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th scope="row">상품이름</th>
								<td><input type="text" class="form-control text-center"
									aria-describedby="basic-addon2" name="name"></td>
							</tr>
							<tr>
								<th scope="row">상품코드</th>
								<td><input type="text" class="form-control text-center"
									aria-describedby="basic-addon2" name="code"></td>
							</tr>
							<tr>
								<th scope="row">대분류</th>
								<td>
									<select class="custom-select text-center" name="category-first">
											<option value="100">무기</option>
											<option value="200">방패</option>
											<option value="300">투구</option>
									</select>
								</td>
							</tr>
							<tr>
								<th scope="row">소분류</th>
								<td>
									<select class="custom-select text-center" name="category">
											
									</select>
								</td>
							</tr>
							<tr>
								<th scope="row">상품가격</th>
								<td><input type="text" class="form-control text-center"
									aria-describedby="basic-addon2" name="price"></td>
							</tr>
							<tr>
								<th scope="row">재고</th>
								<td><input type="text" class="form-control text-center"
									aria-describedby="basic-addon2" name="stock"></td>
							</tr>
							<tr>
								<th scope="row">상품설명</th>
								<td><textarea class="form-control" rows="10"
										name="description"></textarea></td>
							</tr>
							<tr>
								<th scope="row">썸네일</th>
								<td><input type="file" class="form-control text-center"
									aria-describedby="basic-addon2" name="imageFile"></td>
							</tr>
							<tr>
								<th scope="row">업로드된 이미지</th>
								<td><img id="uploaded-img" src=""></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div id="alert" class=""></div>
				<div class="col-md-7 col-12 margin text-right">
					<button type="button" class="btn btn-outline-success"
						onclick="register();">상품등록</button>
				</div>
			</div>
		</form>
	</div>	
</body>
<script type="text/javascript">
	$('select[name=category-first]').val(100);
	selectBoxTrriger();
	$('select[name=category-first]').change(function(){
		selectBoxTrriger();
	});
	function selectBoxTrriger(){
		$('select[name=category] option').remove();
		switch (Number.parseInt($('select[name=category-first]').val())) {
		case 100:
			appendOption(101,'한손검');
			appendOption(102,'두손검');
			appendOption(103,'도끼');
			break;
		case 200:
			appendOption(201,'나무방패');
			appendOption(202,'철제방패');
			break;
		case 300:
			appendOption(301,'철제투구');
			appendOption(302,'가죽투구');
			appendOption(303,'천모자');
			break;
		default:
			console.log('default');
			break;
		
		}
	}
	function appendOption(value,text){
		$('select[name=category]').append('<option class="text-center" value='+value+'>'+text+'</option>');
	}
	var resultData = '${resultData}';
	
	if (resultData) {
		resultData = JSON.parse(resultData);
		switch (resultData.result) {
		case 0:
			initTagVal();
			turnOnAlert('alert-success',resultData.message);
			break;
		case 1:
			turnOnAlert('alert-danger',resultData.message);;
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
	}
	
	function turnOnAlert(alertStyle,message){
		$('#alert').hide();
		$('#alert').attr('class','alert '+ alertStyle +' alert-dismissible fade show col-md-7 col-12 margin');
		$("#alert").text(message);
		$("#alert").fadeIn("slow");
	}

	function register() {
		$('.alert').hide();
		
		if (!isNumber($('input[name=price]').val())) {
			return;
		}

		if (!isNumber($('input[name=stock]').val())) {
			return;
		}
		
		if($("input[name=imageFile]").val() === ""){
			turnOnAlert('alert-info',"상품 이미지를 등록해주세요.");
			return;
		}
		
		$("#registerForm").submit();
	}
	
	function register2() {
		$('.alert').hide();

		if (!isNumber($('input[name=price]').val())) {
			return;
		}

		if (!isNumber($('input[name=stock]').val())) {
			return;
		}

		$.ajax({
			url : "${contextPath }/admin/registerGoods",
			method : "POST",
			data : {
				name : $("input[name=name]").val(),
				code : $("input[name=code]").val(),
				category : $("select[name=category]").val(),
				price : $("input[name=price]").val(),
				stock : $("input[name=stock]").val(),
				description : $("input[name=description]").val(),
				thumbnail : $("input[name=thumbnail_image]").val()
			},
			type : "json"
		}).done(function(resultData) {
			switch (resultData.result) {
			case 0:
				initTagVal();
				$(".alert-success").text(resultData.message);
				$(".alert-success").fadeIn();
				break;
			case 1:
				$(".alert-danger").text(resultData.message);
				$(".alert-danger").fadeIn();
				break;
			case 2:
				$(".alert-warning").text(resultData.message);
				$(".alert-warning").fadeIn();
				break;
			case 3:
				$(".alert-info").text(resultData.message);
				$(".alert-info").fadeIn();
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
	function initTagVal() {
		$('input').val("");
		$('textarea').val("");
		$('select').val('1')
	}

	function isNumber(value) {
		if (!$.isNumeric(value)) {
			turnOnAlert('alert-info',"상품가격과 재고값에 올바른 [정수&양수]값을 입력해주세요.");
			return false;
		}

		if (!Number.isInteger(Number.parseInt(value))) {
			turnOnAlert('alert-info',"상품가격과 재고값에 올바른 [정수&양수]값을 입력해주세요.");
			return false;
		}

		return true;
	}
	$("input[name=imageFile]").change(function() {
		if (this.files && this.files[0]) {
			var reader = new FileReader;
			reader.onload = function(data) {
				$("#uploaded-img").attr("src", data.target.result).width(300);
			}
			reader.readAsDataURL(this.files[0]);
		}
	});
</script>
</html>