<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Login</h1>
	<form class="" action="${contextPath }/member/login" method="post">
		<h3>
			ID <input type="text" name="id" value="">
		</h3>
		<h3>
			PASSWORD <input type="password" name="pw" value="">
		</h3>
		<input type="submit" name="" value="LOGIN">
	</form>
</body>
</html>
