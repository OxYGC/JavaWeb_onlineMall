<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>在线商城信息展示</title>
		<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
		<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</head>

	<body>
		<%@include file="/jsp/header.jsp" %>
		<div class="container">
			<div class="container">
				<h1>${msg}</h1>
				</div>
			</div>
		<!-- 引入footer.jsp -->
		<jsp:include page="/jsp/footer.jsp"></jsp:include>

	</body>

</html>