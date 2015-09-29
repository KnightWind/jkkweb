<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>预约详情</title>
[#include "/common/res.ftl"]
</head>

<body>
<!-- header -->
[#include "/common/head.ftl"]

<!-- wrap -->
<div class="wrap index bc clr">
	<div class="conWrap fl">
		[#include "/common/nav.ftl"]
		<div class="conBox">
			<p>所属区域:${orderId.shipCity}</p>
			<p>所属商户:${orderId.spName}</p>
			<p>订单编号:${orderId.id}</p>
			<p>收货人:${orderId.contactUser}</p>
			<p>收货地址:${orderId.address}</p>
			<p>手机号码:${orderId.contactMobile}</p>
			<p>订单状态:${orderId.status}</p>
			<p>商品总额:${orderId.itemSumPrice}</p>
			<p>下单时间:${orderId.createTime?string("yyyy-MM-dd HH:mm:ss")}</p>
			<p>实付金额:${orderId.paySumPrice}</p>
			
	    </div>
	</div>
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]
</body>
</html>