<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<form action="${contextPath}/goods/searchGoods" method="get"
		autocomplete="off">
		<div class="row">
			<div class="offset-md-3 col-md-6">
				<div class="input-group mb-3">
					<input type="text" class="form-control text-center" name="name">
					<div class="input-group-append">
						<input class="btn btn-outline-secondary" type="submit" value="검색" />
					</div>
				</div>
			</div>
		</div>
		<input type="hidden" name="page" value="1">
	</form>
</body>
</html>