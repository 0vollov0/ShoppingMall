<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>goodsList</title>
</head>
<body>
	<c:set var="totalPrice" value="0"></c:set>
	<jsp:include page="../template/navMenu.jsp" flush="false"></jsp:include>
	<div class="container text-center">
      <table class="table table-striped">
        <thead>
          <tr>
            <th scope="col">상품이미지</th>
            <th scope="col">상품명</th>
            <th scope="col">상품가격</th>
            <th scope="col">상품재고</th>
            <th scope="col">주문수량</th>
            <th scope="col">장바구니 삭제</th>
          </tr>
        </thead>
        <tbody>
        	<c:forEach items="${shoppingBasketList }" var="basket">
	        	<tr id="${basket.value.goods.code}">
	        		<td><img src="../${basket.value.goods.thumbnail_image }" height="50px"></td>
	        		<td>${basket.value.goods.name }</td>
	        		<td>${basket.value.goods.price }</td>
	        		<td>${basket.value.goods.stock }</td>
	        		<td><span class="minus">-</span><span>${basket.value.count }</span><span class="plus">+</span></td>
	        		<td><button type="button" class="btn btn-outline-info" onclick="deleteGoods(${basket.value.goods.code});">삭제</button></td>
	        	</tr>
	        	<c:set var="totalPrice" value="${basket.value.count*basket.value.goods.price+totalPrice}"></c:set>
        	</c:forEach>
        </tbody>
      </table>
      <div  class="text-right">
      	<h4>총 금액:<span id="total">${totalPrice }</span> 원</h4>
      </div>
  	</div>
  	<div class="container text-center">
    <h3>배송지</h3>
    <form action="${contextPath }/order/requestOrder" method="POST">
    	<table class="table table-striped">
	      <tbody>
	        <tr>
	          <th>주문자</th>
	          <td><input class="form-control text-center" type="text" name="buyer"></td>
	          <th>수령인</th>
	          <td><input class="form-control text-center" type="text" name="shippingRecipient"></td>
	        </tr>
	        <tr>
	          <th>우편번호</th>
	          <td colspan="2"><input class="form-control text-center" type="text" id="sample3_postcode" name="zipCode"></td>
	          <td><input class="form-control" type="button" onclick="sample3_execDaumPostcode()" value="우편번호 찾기"></td>
	        </tr>
	        <tr>
	          <th>주소</th>
	          <td><input class="form-control text-center" type="text" id="sample3_address" name="address"></td>
	          <th>상세주소</th>
	          <td><input class="form-control text-center" type="text" id="sample3_detailAddress" name="detailAddress"></td>
	        </tr>
	        <tr>
	          <th>참고항목</th>
	          <td colspan="3"><input class="form-control text-center" type="text" id="sample3_extraAddress" name="reference"></td>
	        </tr>
	      </tbody>
	    </table>
	    <div id="wrap" class="text-center" style="display:none;border:1px solid;width:500px;height:300px;margin:5px 0;position:relative">
	      <img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
	    </div>
	    <div class="text-right">
	    	<input type="submit" class="btn btn-outline-success" value="결제">
	    </div>
    </form>
  	</div>
</body>
<script type="text/javascript">
	$('.minus').click(function(){
		$.ajax({
			url : "${contextPath }/member/basketUpDown",
			method : "POST",
			data : {
				code : $(this).parent().parent().attr('id'),
				action : -1	
				},
			type : "json"
		}).done(function(result) {
			$('#'+result.id).children().eq(4).children().eq(1).text(result.value);
			var temp = Number.parseInt($('#'+result.id).children().eq(2).text());
			$('#total').text(Number.parseInt($('#total').text())-temp);
		}).fail(function() {
			$(".alert-danger").text("서버 통신 오류");
			$(".alert-danger").fadeIn();
		}).always(function() {
		});
	});

	$('.plus').click(function(){
		$.ajax({
			url : "${contextPath }/member/basketUpDown",
			method : "POST",
			data : {
				code : $(this).parent().parent().attr('id'),
				action : 1	
				},
			type : "json"
		}).done(function(result) {
			$('#'+result.id).children().eq(4).children().eq(1).text(result.value);
			var temp = Number.parseInt($('#'+result.id).children().eq(2).text());
			$('#total').text(Number.parseInt($('#total').text())+temp);
		}).fail(function() {
			$(".alert-danger").text("서버 통신 오류");
			$(".alert-danger").fadeIn();
		}).always(function() {
		});
		/*
		if (result == 0) {
			var temp = $(this).prev().text();
			temp++;
			$(this).prev().text(temp);
			console.log($(this).parent().prev().prev().text());
			temmp = Number.parseInt($(this).parent().prev().prev().text());
			$('#total').text(Number.parseInt($('#total').text())+temmp);
		}*/
	});
	
	function deleteGoods(goodsCode) {
		$.ajax({
			url : "${contextPath }/member/deleteShoppingBasket",
			method : "GET",
			data : { code : goodsCode},
			type : "json"
		}).done(function(resultData) {
			var price = Number.parseInt($('#'+resultData.result).children().eq(2).text());
			var count = Number.parseInt($('#'+resultData.result).children().eq(4).children().eq(1).text());
			var total = Number.parseInt($('#total').text());
			total = total - price * count;
			$('#total').text(total);
			$('#'+resultData.result).remove();	
		}).fail(function() {
			$(".alert-danger").text("서버 통신 오류");
			$(".alert-danger").fadeIn();
		}).always(function() {
		});
	}
</script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
  // 우편번호 찾기 찾기 화면을 넣을 element
  var element_wrap = document.getElementById('wrap');

  function foldDaumPostcode() {
    // iframe을 넣은 element를 안보이게 한다.
    element_wrap.style.display = 'none';
  }

  function sample3_execDaumPostcode() {
    // 현재 scroll 위치를 저장해놓는다.
    var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
    new daum.Postcode({
      oncomplete: function(data) {
        // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

        // 각 주소의 노출 규칙에 따라 주소를 조합한다.
        // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
        var addr = ''; // 주소 변수
        var extraAddr = ''; // 참고항목 변수

        //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
        if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
          addr = data.roadAddress;
        } else { // 사용자가 지번 주소를 선택했을 경우(J)
          addr = data.jibunAddress;
        }

        // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
        if (data.userSelectedType === 'R') {
          // 법정동명이 있을 경우 추가한다. (법정리는 제외)
          // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
          if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
            extraAddr += data.bname;
          }
          // 건물명이 있고, 공동주택일 경우 추가한다.
          if (data.buildingName !== '' && data.apartment === 'Y') {
            extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
          }
          // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
          if (extraAddr !== '') {
            extraAddr = ' (' + extraAddr + ')';
          }
          // 조합된 참고항목을 해당 필드에 넣는다.
          document.getElementById("sample3_extraAddress").value = extraAddr;

        } else {
          document.getElementById("sample3_extraAddress").value = '';
        }

        // 우편번호와 주소 정보를 해당 필드에 넣는다.
        document.getElementById('sample3_postcode').value = data.zonecode;
        document.getElementById("sample3_address").value = addr;
        // 커서를 상세주소 필드로 이동한다.
        document.getElementById("sample3_detailAddress").focus();

        // iframe을 넣은 element를 안보이게 한다.
        // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
        element_wrap.style.display = 'none';

        // 우편번호 찾기 화면이 보이기 이전으로 scroll 위치를 되돌린다.
        document.body.scrollTop = currentScroll;
      },
      // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
      onresize: function(size) {
        element_wrap.style.height = size.height + 'px';
      },
      width: '100%',
      height: '100%'
    }).embed(element_wrap);

    // iframe을 넣은 element를 보이게 한다.
    element_wrap.style.display = 'block';
  }
</script>
</html>
