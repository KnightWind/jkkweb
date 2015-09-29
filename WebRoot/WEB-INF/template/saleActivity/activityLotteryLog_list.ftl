<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>引流活动抽奖管理</title>
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
						<th width="15%">编号</th>
						<th width="15%">手机号</th>
						<th width="15%">奖品</th>
						<th width="15%">中奖状态</th>
						<th width="15%">是否领取</th>
						<th width="15%">抽奖时间</th>
						<th width="15%">领奖时间</th>
					</thead>
					<tbody>
						[#list pagination.dataList as item ]
						<tr>
						    <td>${item.id}</td>
						    <td>${item.phone}</td>
							<td>${item.awardContent}</td>
							<td>${item.winVal}</td>
							<td>${item.receiveVal}</td>
							<td>${item.createTimeHandle}</td>
							<td>${item.receiveTimeHandle}</td>
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
	    <td><%=result.elements[i].phone%></td>
		<td><%=result.elements[i].awardContent%></td>
		<td><%=result.elements[i].winVal%></td>
		<td><%=result.elements[i].receiveVal%></td>
		<td><%=result.elements[i].createTimeHandle%></td>
		<td><%=result.elements[i].receiveTimeHandle%></td>
	</tr>
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/wxActivityLotteryLog/pagination.do',
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