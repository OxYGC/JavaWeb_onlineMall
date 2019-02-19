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
		<div class="container-fluid">

			<!-- 引入header.jsp -->
			<jsp:include page="/jsp/header.jsp"></jsp:include>

			<div class="container-fluid">
				<div class="main_con">
					<h2>商城简介</h2>
					<hr/>
					<div>
						<p>
							<font color="red">“在线商城购物系统”</font>在线商城购物系统是中国深受欢迎的网购零售平台，业务包括C2C、团购、分销、拍卖等多种电子商务模式在内的综合性零售商圈。目前已经成为世界范围的电子商务交易平台之一。
						</p>

						<p>
							在线商城致力于推动“货真价实、物美价廉、按需定制”网货的普及，帮助更多的消费者享用海量且丰富的网货，获得更高的生活品质；通过提供网络销售平台等基础性服务，帮助更多的企业开拓市场、建立品牌，实现产业升级；帮助更多胸怀梦想的人通过网络 实现创业就业。。
						</p>

						<p>
							本在线商城不仅是中国深受欢迎的网络零售平台，也是中国的消费者交流社区和全球创意商品的集中地。这在很大程度上改变了传统的生产方式，也改变了人们的生活消费方式。
						</p>

						<p>
							不做冤大头、崇尚时尚和个性、开放擅于交流的心态以及理性的思维，成为本网上上崛起的“新一代”的重要特征。本网站多样化的消费体验，让新一代们乐在其中：团设计、玩定制、赶时髦、爱传统。
						</p>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 引入footer.jsp -->
		<jsp:include page="/jsp/footer.jsp"></jsp:include>

	</body>

</html>