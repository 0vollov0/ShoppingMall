<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container text-center">
		<div class="row">
			<div class="col-xl-2 col-sm-4 col-4">
				<div class="btn-group" role="group">
					<button id="" type="button" class="btn dropdown-toggle"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						무기</button>
					<div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
    					<a class="dropdown-item" href="${contextPath}/goods/goodsList?category=100&n=1">무기</a>
    					<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="${contextPath}/goods/goodsList?category=101&n=1">한손검</a> 
						<a class="dropdown-item" href="${contextPath}/goods/goodsList?category=102&n=1">두손검</a>
						<a class="dropdown-item" href="${contextPath}/goods/goodsList?category=103&n=1">도끼</a>
					</div>
				</div>
			</div>
			<div class="col-xl-2 col-sm-4 col-4">
				<div class="btn-group" role="group">
					<button id="" type="button" class="btn dropdown-toggle"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						방패</button>
					<div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
						<a class="dropdown-item" href="${contextPath}/goods/goodsList?category=200&n=1">방패</a>
    					<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="${contextPath}/goods/goodsList?category=201&n=1">나무방패</a>
						<a class="dropdown-item" href="${contextPath}/goods/goodsList?category=202&n=1">철제방패</a> 
					</div>
				</div>
			</div>
			<div class="col-xl-2 col-sm-4 col-4">
				<div class="btn-group" role="group">
					<button id="" type="button" class="btn dropdown-toggle"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						투구</button>
					<div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
						<a class="dropdown-item" href="${contextPath}/goods/goodsList?category=300&n=1">투구</a>
    					<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="${contextPath}/goods/goodsList?category=301&n=1">철제투구</a>
						<a class="dropdown-item" href="${contextPath}/goods/goodsList?category=302&n=1">가죽투구</a>
						<a class="dropdown-item" href="${contextPath}/goods/goodsList?category=303&n=1">천모자</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>