<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MENU</title>
</head>
<body>
	<hr>
	<div class="container text-center">
		<div id="menu-button" class="row">
		</div>
	</div>
	<hr>
</body>
<script type="text/javascript">
	$.ajax({
		url : "${contextPath }/goods/getCategory",
		method : "GET"
	}).done(function(resultData) {
		var buttonHtml = new Array();
		resultData.forEach(function(data) {
			  if (data.code % 100 == 0 ) {
				  buttonHtml.push("<div class='col-xl-2 col-sm-4 col-4 '><div class='btn-group' role='group'><button type='button' class='btn dropdown-toggle'data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>"+data.name+"</button><div class='dropdown-menu' aria-labelledby='btnGroupDrop1'><a class='dropdown-item' href='${contextPath}/goods/goodsList?category="+data.code+"&page=1'>"+data.name+"</a><div class='dropdown-divider'></div>");
			  }else{
				  buttonHtml[Number.parseInt(data.code/100)-1] +=  "<a class='dropdown-item' href='${contextPath}/goods/goodsList?category="+data.code+"&page=1'>"+data.name+"</a>";
			  }
		});
		
		for (var i = 0; i < buttonHtml.length; i++) {
			buttonHtml[i] += "</div></div></div>";
		}
		
		$('#menu-button').html(buttonHtml);
		
	}).fail(function() {
		alert("서버 통신 오류");
	}).always(function() {
	});
</script>
</html>