<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<div class="container text-center">
	  	<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
		  	<div class="btn-group col-12" role="group" aria-label="First group">
		  		<button type="button" class="btn btn-secondary" onclick="viewListPage(${currentPage-1});">PREV</button>
		  		<c:forEach var="i" begin="${minPage }" end="${maxPage }">
		  			<c:choose>
		  				<c:when test="${currentPage==i }">
		  					<button type="button" class="btn btn-dark" onclick="viewListPage(${i})">${i }</button>
		  				</c:when>
		  				<c:otherwise>
		  					<button type="button" class="btn btn-secondary" onclick="viewListPage(${i})">${i }</button>
		  				</c:otherwise>
		  			</c:choose>
			  	</c:forEach>
		    	<button type="button" class="btn btn-secondary" onclick="viewListPage(${currentPage+1});">NEXT</button>
		  	</div>
		</div>
	</div>
</body>
</html>