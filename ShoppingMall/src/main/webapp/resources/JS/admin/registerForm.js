	$('select[name=category-first]').val(100);
	selectBoxTrriger();
	$('select[name=category-first]').change(function(){
		selectBoxTrriger();
	});
	/*
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
		case 400:
			appendOption(401,'철제갑옷');
			appendOption(402,'가죽갑옷');
			break;
		case 500:
			appendOption(501,'철제신발');
			appendOption(502,'가죽신발');
			break;
		case 600:
			appendOption(601,'철제장갑');
			break;
		default:
			console.log('default');
			break;
		
		}
	}*/
	function appendOption(value,text){
		$('select[name=category]').append('<option class="text-center" value='+value+'>'+text+'</option>');
	}
	if (resultData) {
		resultData = JSON.parse(resultData);
		switch (resultData.result) {
		case 0:
			window.location= $('#contextPath').val()+"/goods/goodsArticle?code="+resultData.code;
			//initTagVal();
			//turnOnAlert('alert-success',resultData.message);
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
	/*
	function register2() {
		$('.alert').hide();

		if (!isNumber($('input[name=price]').val())) {
			return;
		}

		if (!isNumber($('input[name=stock]').val())) {
			return;
		}

		$.ajax({
			url : $('#contextPath').val()+"/admin/registerGoods",
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
	}*/
	function initTagVal() {
		$('input').val("");
		//$('textarea').val("");
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