<!DOCTYPE HTML>
<html>
<head>
<title>众凑订单</title>
<meta charset="utf-8">
[#include "/common/res.ftl"]
	<script type="text/javascript" src="${base}/scripts/form.js"></script>
	<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
</head>

</head>
<body>
<!-- header -->
[#include "/common/head.ftl"]
<!-- wrap -->
	<div class="wrap index bc clr">
		<div class="conWrap fl">
			[#include "/common/nav.ftl"]
				<div class="conBox">	
					<table class="format">
						<thead>
							<th width="10%">交易号</th>
							<th width="5%">推荐人</th>
							<th width="10%">推荐人手机号码</th>
							<th width="15%">商品名称</th>
							<th width="10%">商家名称</th>
							<th width="5%">用户帐号</th>
							<th width="10%">价格(单位:元)</th>
							<th width="10%">交易金额(单位:元)</th>	
							<th width="13%">订单状态</th>	
							<th width="10%">物流状态</th>	
						</thead>
						<tbody>
							[#list pagination.dataList as item]
							<tr>
								<td>${item.orderCode}</td>	
								<td>${item.recommendName}</td>	
								<td>${item.recommendMobile}</td>	
								<td>${item.itemTitleShowVal}</td>
								<td>${item.spName}</td>
								<td>${item.mobile}</td>	
								<td>${item.itemPriceShowVal}</td> 
								<td>${item.payMoneyVal}</td> 
								<td>${item.orderStatus}(${item.payTypeVal})</td> 
								<td>${item.expressStateVal}</td> 
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
</body>

<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr>
		<td><%=result.elements[i].orderCode%></td>	
		<td><%=result.elements[i].recommendName%></td>	
		<td><%=result.elements[i].recommendMobile%></td>	
		<td><%=result.elements[i].itemTitleShowVal%></td>
		<td><%=result.elements[i].spName%></td>
		<td><%=result.elements[i].mobile%></td>	
		<td><%=result.elements[i].itemPriceShowVal%></td> 
		<td><%=result.elements[i].payMoneyVal%></td> 
		<td><%=result.elements[i].orderStatus%>(<%=result.elements[i].payTypeVal%>)</td> 
		<td><%=result.elements[i].expressStateVal%></td> 
    </tr>	
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/adminZcOrder/pagination.do',
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
});	
</script>

</html>