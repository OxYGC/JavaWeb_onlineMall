<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>在线商城首页</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
</head>
<body>
<!--
            	描述：菜单栏
            -->
			<div class="container-fluid">
			
				<div class="col-md-4">
				<a href="${pageContext.request.contextPath}"><img src="${pageContext.request.contextPath}/img/logo2.png" /></a>
					
				</div>
				<div class="col-md-5">
					<img src="${pageContext.request.contextPath}/img/header.png" />
				</div>
				
				 <div class="col-md-3" style="padding-top:20px">
				 <ol class="list-inline">
					 <c:if test="${empty user}">
							<li><a href="${pageContext.request.contextPath}/jsp/login.jsp">登录</a></li>
							<li><a href="${pageContext.request.contextPath}/jsp/register.jsp">注册</a></li>
							<%-- <li><a href="${pageContext.request.contextPath}/jsp/cart.jsp">购物车</a></li> --%>
					</c:if>
					 <c:if test="${not empty user}" >
							<li>欢迎<a href="#">${user.name}</a></li>
							<li><a href="${pageContext.request.contextPath}/UserServlet?method=logOut">退出</a></li>
							<li><a href="${pageContext.request.contextPath}/jsp/cart.jsp">购物车</a></li>
							<li><a href="${pageContext.request.contextPath}/OrderServlet?method=findMyOrdersWithPage&num=1">我的订单</a></li>
					</c:if>
				</ol>
				</div> 
				
			</div>
			<!--
            	描述：导航条
            -->
			<div class="container-fluid">
				<nav class="navbar navbar-inverse">
					<div class="container-fluid">
						<!-- Brand and toggle get grouped for better mobile display -->
						<div class="navbar-header">
							<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
								<span class="sr-only">Toggle navigation</span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
							</button>
							<a class="navbar-brand" href="${pageContext.request.contextPath}">首页</a>
						</div>

						<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
							<ul class="nav navbar-nav" id = "myUL">
							</ul>
							<form class="navbar-form navbar-right" role="search">
								<div class="form-group">
									<input type="text" class="form-control" placeholder="输入搜索的商品">
								</div>
								<button type="submit" class="btn btn-default">搜索</button>
							</form>

						</div>
						<!-- /.navbar-collapse -->
					</div>
					<!-- /.container-fluid -->
				</nav>
			</div>

</body>
<script>
/*js语言注意加#号  */
	$(function(){
		$.post("/yanggengchen_v3.0/CategoryServlet",{"method":"findAllCats"},function(data){
			console.log(data);
			$.each(data,function(i,obj){
				var li="<li><a href='/yanggengchen_v3.0/ProductServlet?method=findProductsWithPage&num=1&cid="+obj.cid+"'>"+obj.cname+"</a></li>";
				$("#myUL").append(li);
		});},"json");
	});
</script>
</html>