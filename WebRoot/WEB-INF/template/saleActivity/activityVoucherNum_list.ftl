<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>引流活动现金券管理</title>
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
						<th width="10%">现金券</th>
						<th width="10%">用户手机号</th>
						<th width="10%">总数</th>
						<th width="10%">可用数量</th>
						<th width="10%">更新时间</th>
					</thead>
					<tbody>
						[#list pagination.dataList as item ]
						<tr>
						    <td>${item.id}</td>
						    <td>${item.name}</td>
							<td>${item.phone}</td>
							<td>${item.historyNum}</td>
							<td>${item.num}</td>
							<td>${item.updateTimeHandle}</td>
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
	    <td><%=result.elements[i].name%></td>
		<td><%=result.elements[i].phone%></td>
		<td><%=result.elements[i].historyNum%></td>
		<td><%=result.elements[i].num%></td>
		<td><%=result.elements[i].updateTimeHandle%></td>
	</tr>
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/wxActivityVoucherNum/pagination.do',
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