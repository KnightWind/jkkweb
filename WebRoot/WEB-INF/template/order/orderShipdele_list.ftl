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
		 <div class="tools white border mb10 sp10 clr">
				<form id="search" action="">
				<ul>
					<li>
						<input type="hidden" name="id"  value="${mid}">
						<span class="sp"><strong>商家名称：${sname}</strong></span>
						<span class="sp"><strong>结算订单总数:${jj}</strong></span>
					</li>
					<li>
					<span class="sp"><strong>实际销售金额:${tool}</strong></span>
			  	    <span class="sp"><strong>分成金额:${fc}</strong></span>
			  	    <span class="sp"><strong>结算金额:${jsa}</strong></span>
					</li>
					<li>
						<span class="sp"><strong>结算日期:-</strong></span>
			  			<span class="sp"><strong>分成比例:${ga}%</strong></span>
					</li>
				</ul>
				</form>
			</div>   
			<table class="format">
				<thead>					
					<th width="8%">订单编号</th>
					<th width="8%">收货姓名</th>
					<th width="8%">收货电话</th>
					<th width="8%">销售金额</th>
					<th width="8%">退货金额</th>
					<th width="8%">实售金额</th>
					<th width="9%">下单时间</th>
					<th width="9%">可结算时间</th>
					<th width="20%">操作</th>			
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>	
						<td>${item.id}</td>
						<td>${item.contactUser}</td>
						<td>${item.contactMobile}</td>
						<td>${item.itemSumPrice}</td>
						<td>${item.itemSumPrice-item.bd}</td>					
						<td>${item.price*item.num}</td>
						<td>${item.cre}</td>	
						<td>${item.dg}</td>									
						<td>
						<a class="obtn" href="${base}/order/ordership/lst.xhtml?mid=${mid}&pid=${pid}&ppid=${item.id}">订单详细</a>
						</td>
					</tr>
					[/#list]
				</tbody>
		</table>
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
	<td><span class="btn modify">订单详细</span></td>
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