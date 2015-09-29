<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>系统参数配置列表</title>
[#include "/common/res.ftl"]
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
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
			<div class="shd">
				<a class="obtn fr" href="${base}/admin/sy/sa.xhtml?mid=${mid}&pid=${pid}">添加</a>
			</div>
			<table class="format">
				<thead>
					<th width="10%">参数编码</th>
					<th width="25%">参数名称</th>
					<th width="25%">参数值</th>
					<th width="25%">描述</th>
					<th width="15%">操作</th>
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td>${item.paraCode}</td>
						<td>${item.paraName}</td>
						<td>${item.paraValue}</td>
						<td>${item.paraDesc}</td>
					<td><a class="obtn" href="${base}/admin/sy/sa.xhtml?mid=${mid}&pid=${pid}&id=${item.paraCode}">修改</a></td>
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
<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr>
		<td><%=result.elements[i].paraCode%></td>
		<td><%=result.elements[i].paraName%></td>
		<td><%=result.elements[i].paraValue%></td>
		<td><%=result.elements[i].paraDesc%></td>
		<td><a class="obtn" href="${base}/admin/sy/sa.xhtml?mid=${mid}&pid=${pid}&id=<%=result.elements[i].paraCode%>">修改</a></td>
	</tr>	
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/admin/sy/pagination.do',
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
</body>

</html>