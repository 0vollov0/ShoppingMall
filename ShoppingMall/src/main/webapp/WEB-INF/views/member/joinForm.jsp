<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Join</h1>
	<form class="" action="${contextPath }/member/join" method="post">
		<h3>
			ID <input type="text" name="id" value="">
		</h3>
		<h3>
			PASSWORD <input type="password" name="pw" value="">
		</h3>
		<h3>
			E-MAIL <input type="text" name="email" value="">
		</h3>
		<input type="submit" name="" value="JOIN">
	</form>
</body>
</html>
