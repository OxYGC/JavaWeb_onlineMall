<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!-- 登录 注册 购物车... -->
<div class="container-fluid">
	<div class="col-md-4">
		<img src="img/logo2.png" />
	</div>
	<div class="col-md-5">
		<img src="img/header.png" />
	</div>
	<div class="col-md-3" style="padding-top:20px">
		<ol class="list-inline">
		
			<c:if test="${empty user }">
				<li><a href="login.jsp">登录</a></li>
				<li><a href="register.jsp">注册</a></li>
			</c:if>
			<c:if test="${!empty user }">
				<li style="color: red">欢迎您,${user.name }</li>
				<li><a href="${pageContext.request.contextPath }/user?method=logout">退出</a></li>
			</c:if>
			
			<li><a href="cart.jsp">购物车</a></li>
			<li><a href="${pageContext.request.contextPath }/order?method=myOrders">我的订单</a></li>
		</ol>
	</div>
</div>

<!-- 导航条 -->
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
				<a class="navbar-brand" href="index.jsp">首页</a>
			</div>

			<!-- 
				通过ajax异步访问Category的数据 返回json数据 通过js 将json数据动态的创建多个li节点  在将li节点放到ul内部
				
			 -->
			<script type="text/javascript">
				$(function(){
					$.post(
						"${pageContext.request.contextPath}/category",
						{"method":"findAllByJson"},
						function(data){
							//[{"cid":"100","cname":"手机数码"},{},{}]
							var content = "";
							for(var i=0;i<data.length;i++){
								content+="<li><a href='${pageContext.request.contextPath}/product?method=findByPage&cid="+data[i].cid+"'>"+data[i].cname+"</a></li>";
							}
							//将所有拼接好li放到ul内部
							$("#categoryList").html(content);
						},
						"json"
					);
				});
			
			</script>

			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul id="categoryList" class="nav navbar-nav">
					<%-- <c:forEach items="${categoryList }" var="category">
						<li><a href="#">${category.cname}</a></li>
					</c:forEach> --%>
				</ul>
				<form class="navbar-form navbar-right" role="search">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Search">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
			</div>
		</div>
	</nav>
</div>