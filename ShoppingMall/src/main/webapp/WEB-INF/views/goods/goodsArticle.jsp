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
	<jsp:include page="../template/navMenu.jsp" flush="false"></jsp:include>
	<div class="container text-center">
    <table class="table table-striped">
      <thead>
        <tr>
          <th colspan="4">상품설명</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <th>상품명 :</th>
          <td>${goods.name }</td>
          <th>상품코드 :</th>
          <td>${goods.code }</td>
        </tr>
        <tr>
          <th>카테고리 :</th>
          <td colspan="3"><small class="text-muted">${category_1 }</small> - ${category_2 }</td>
        </tr>
        <tr>
          <th>등록일 :</th>
          <td colspan="3">${goods.formatedTime }</td>
        </tr>
        <tr>
          <th>가격 :</th>
          <td>${goods.price } 원</td>
          <th>재고 :</th>
          <td>${goods.stock }</td>
        </tr>
        <tr>
          <td colspan="4">
            <p>
              <img src="../${goods.thumbnail_image }">
            </p>
            <p>${goods.description }</p>
          </td>
        </tr>
      </tbody>
    </table>
    <div class="row">
      <div class="col-6 text-left">
          <button type="button" class="btn btn-outline-success" onclick="history.go(-1);">이전 페이지</button>
      </div>
      <div class="col-6 text-right">
        <button type="button" class="btn btn-outline-success" onclick="shoppingBasket();">장바구니</button>
        <button type="button" class="btn btn-outline-success">주문하기</button>
      </div>
    </div>
  </div>
</body>
<script type="text/javascript">
function shoppingBasket() {
	$.ajax({
		url : "${contextPath }/member/shoppingBasket",
		method : "POST",
		data : { code : '${goods.code }'},
		type : "json"
	}).done(function(resultData) {
		alert(resultData.message);	
	}).fail(function() {
		$(".alert-danger").text("서버 통신 오류");
		$(".alert-danger").fadeIn();
	}).always(function() {
	});
}
</script>
</html>
