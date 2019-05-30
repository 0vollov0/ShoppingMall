	if ($('#message').val().length > 0) {
		alert($('#message').val());
		window.location = $('#contextPath').val()+"/";
	}
	function modify(){
		if(!checkForm()){
			return;
		}
		$.ajax({
			url : $('#contextPath').val()+"/admin/getModifyGoodsFormResult",
			method : "POST",
			data : {
				code : $("input[name=code]").val(),
				wherecode : $("input[name=wherecode]").val()
			},
			type : "json"
		}).done(function(resultData) {
			switch (resultData.result) {
			case 0:
				$('#modifyForm').submit();	
				break;
			case 1:
				turnOnAlert('alert-warning',resultData.message);
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
	$('select[name=category-first]').val($('#category_1').val());
	$('select[name=category-first]').change(function(){
		selectBoxTrriger();
	});
	$('select[name=category-first]').change();
	$('select[name=category]').val($('#category_2').val());
	/*
	function selectBoxTrriger(){
		//$('select[name=category] option').remove();
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
			$('select[name=category-first]').val(100);
			break;
		
		}
	}*/
	function appendOption(value,text){
		$('select[name=category]').append('<option class="text-center" value='+value+'>'+text+'</option>');
	}
	
	var resultData =  $('#resultData').val();
	/*
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
	*/
	function turnOnAlert(alertStyle,message){
		$('#alert').hide();
		$('#alert').attr('class','alert '+ alertStyle +' alert-dismissible fade show col-md-7 col-12 margin');
		$("#alert").text(message);
		$("#alert").fadeIn("slow");
	}

	function modifyGoods() {
		$('.alert').hide();
		
		if (!isNumber($('input[name=price]').val())) {
			return;
		}

		if (!isNumber($('input[name=stock]').val())) {
			return;
		}
		$("#modifyForm").submit();
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
	
	function checkForm() {
		if ($('input[name=name]').val().length <= 0) {
			turnOnAlert('alert-info',"상품이름을 입력해주세요.");
			return false;
		}
		if ($('input[name=code]').val().length <= 0) {
			turnOnAlert('alert-info',"상품코드를 입력해주세요.");
			return false;
		}
		if (!$('select[name=category-first]').val() || !$('select[name=category]').val()) {
			turnOnAlert('alert-info',"상품분류를 지정해주세요.");
			return false;
		}
		if (!$.isNumeric($('input[name=price]').val()) || !$.isNumeric($('input[name=stock]').val())) {
			console.log("s");
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
	})