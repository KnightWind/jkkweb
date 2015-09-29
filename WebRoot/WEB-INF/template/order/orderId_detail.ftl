<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>订单详情</title>
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
			<p>所属区域:
			${orderId.area}->
			${orderId.province}->
			${orderId.city}</p>
			<p>所属商户:${orderId.spName}</p>
			<p>订单编号:${orderId.id}</p>
			<p>收货人:${orderId.contactUser}</p>
			<p>收货地址:
			${orderId.city}&nbsp;
			${orderId.regionName}&nbsp;
			${orderId.address}</p>
			<p>手机号码:${orderId.contactMobile}</p>
			<p>订单状态:${orderId.vstatusVal}</p>
			<p>商品总额:${orderId.itemSumPrice}</p>
			<p>发票类型:${orderId.receipTypeVal}</p>
			<p>发票抬头:${orderId.receiptTitleTypeVal}</p>
			<p>下单时间:${orderId.vcreateTime}</p>
			<p>实付金额:${orderId.paySumPrice}</p>		
			<p>发货时间:${orderId.vshipTime}</p>		
			<p>礼券抵消:${orderId.discount}</p>
	    </div>
	    <div class="conBox">
	         <table class="format">
	            <tr>
	              <td>商品编号</td>
	              <td>商品图片</td>
	              <td>商品名称</td>
	              <td>商品金额</td>
	              <td>商品数量</td>
	            </tr>
	            [#list itemList as item]
	              <tr>
		              <td>${item.vitemId}</td>
		              <td>${item.pid}</td>
		              <td>${item.title}</td>
		              <td>${item.itemPrice}</td>
		              <td>${item.num}</td>
	              </tr>
	            [/#list]
	         </table>
	    </div>
	</div>
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]
</body>
</html>