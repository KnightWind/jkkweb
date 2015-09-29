<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>商品列表</title>
[#include "/common/res.ftl"]
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="${base}/scripts/form.js"></script>
</head>

<body>
<!-- header -->
[#include "/common/head.ftl"]
<!-- wrap -->
<div class="wrap index bc clr">
	<div class="conWrap fl">
		[#include "/common/nav.ftl"]
		<div class="conBox">
		[#list pagination.dataList as item]
		 <div class="tools white border mb10 sp10 clr">
				<form id="search" action="">
				<ul>
					<li>
						<span class="sp"><strong>所属商户:${item.spName}</strong></span>
						<span class="sp"><strong>订单编号:${item.id}</strong></span>
						<span class="sp"><strong>收货人:${item.contactUser}</strong></span>
						<span class="sp"><strong>收货地址:${item.address}</strong></span>
						<span class="sp"><strong>手机号码:${item.contactMobile}</strong></span>
					</li>
					<li>
					<table class="format">
					<tr>
					<td>订单状态:</td>
					<td>${item.zt}</td>
					<td>商品总额:</td>
					<td>￥ ${item.itemSumPrice}</td>
					<td>发票类型:</td>
					<td>${item.fa}</td>
					</tr>
					<tr>
					<td>下单时间:</td>
					<td>${item.cre}</td>
					<td>实付总额:</td>
					<td>￥ ${item.paySumPrice}</td>
					<td>发票抬头:</td>
					<td>${item.receiptTitle}</td>
					</tr>
					<tr>
					<td>发货时间:</td>
					<td>${item.shi}</td>
					<td>礼卷抵消:</td>
					<td>-￥ ${item.discount}</td>
					<td>发票明细:</td>
					<td>${item.ming}</td>
					</tr>
					</table>
					</li>
					<li>
						<span class="sp"><strong>商品清单</strong></span>		
					</li>
				</ul>
				</form>
			</div>   
			<table class="format">
				<thead>					
					<th width="8%">商品编号</th>
					<th width="8%">商品图片</th>
					<th width="8%">商品名称</th>
					<th width="8%">商品金额</th>
					<th width="8%">商品数量</th>	
				</thead>
				<tbody>
					<tr>	
						<td>${item.tid}</td>
						<td><img  src="${item.img}" /></td>
						<td>${item.title}</td>
						<td>${item.price}</td>
						<td>${item.num}</td>
					</tr>
				
				</tbody>
		</table>
		[/#list]
		[#include "/common/pagination.ftl"]
	</div>
	</div>
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]
<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr menuid="<%=result.elements[i].id%>">
	<td><%=result.elements[i].id%></td>
	<td><%=result.elements[i].contactUser%></td>
	<td><%=result.elements[i].contactMobile%></td>
	<td><%=result.elements[i].itemSumPrice%></td>				
	<td><%=result.elements[i].itemSumPrice-result.elements[i].bd%></td>
	<td><%=result.elements[i].price*result.elements[i].num%></td>
	<td><%=result.elements[i].cre%></td>	
	<td><%=result.elements[i].dg%></td>
</tr>	
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/order/ordership/pagination.do',
		deal:{'click':'.search'},
		send:function(){
			var data={
					pageSize:10,
					search:$('#search').formatJSON()
			};
			return data;
		},
	    fun:function(dataR){
	    	if(dataR.records<1){ return false;}
			$('table.format tbody').html(template.render('list',dataR)); 
	    }
	});
})
</script>
<script>
$(function(){
	save('span.btn');
});	
</script>
</body>
</html>