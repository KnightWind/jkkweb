<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>引流活动购卡管理</title>
[#include "/common/res.ftl"]
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
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
						<th width="10%">编号</th>
						<th width="10%">活动卡名称</th>
						<th width="10%">用户手机号</th>
						<th width="10%">支付状态</th>
						<th width="10%">支付方式</th>
						<th width="10%">购卡时间</th>
					</thead>
					<tbody>
						[#list pagination.dataList as item ]
						<tr>
						    <td>${item.id}</td>
						    <td>${item.cardName}</td>
							<td>${item.phone}${item.paymentType}</td>
							<td>
							  [#if item.payStatus==1]
							        已支付
							  [#else]
							        未支付
							  [/#if]
							</td>
							<td>
							  [#if item.paymenType==1]
							        支付宝
							  [/#if]
							  [#if item.paymenType==2]
							         易宝
							  [/#if]
							  [#if item.paymenType==3]
							        微信
							  [/#if]
							</td>
							<td>${item.createTimeHandle}</td>
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
		<td><%=result.elements[i].id%></td>
	    <td><%=result.elements[i].cardName%></td>
		<td><%=result.elements[i].phone%></td>
		<td>
		 <% if(result.elements[i].payStatus==1){%>
		       已支付
		 <%}else{%>
		     未支付
		 <%}%>
		</td>
		<td>
		 <% if(result.elements[i].paymenType==1){%>
		   支付宝
		 <%}%>
		 <% if(result.elements[i].paymenType==2){%>
		  易宝
		 <%}%>
		 <% if(result.elements[i].paymenType==3){%>
		  微信
		 <%}%>
		</td>
		<td><%=result.elements[i].createTimeHandle%></td>
	</tr>
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/wxActivityCardDeals/pagination.do',
		deal:{'click':'span.search'},
		format:['pageCount','count'],
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
</html>