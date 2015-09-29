<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>案例列表</title>
[#include "/common/res.ftl"]
<style>
	.fieldStyle{
		padding:20px;
	}
	
</style>
<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
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
						<span class="sp"><strong>设计名称：</strong><input type="text" class="text" name="designName" id=""></span>
						<span class="sp"><strong>设计师名：</strong><input type="text" class="text" name="uname" id=""></span>
						<span class="sp"><strong>日记标题:</strong><input type="text" class="text" name="title" id=""></span>
					</li>
					<li>
						<strong>创建日期：</strong>
						<span class="sp"><input type="text" class="text Wdate" name="createBegin" id="" onClick="WdatePicker()" readOnly> &nbsp;--</span>
						<span class="sp"><input type="text" class="text Wdate" name="createEnd" id="" onClick="WdatePicker()" readOnly></span>
						<span class="btn search">查 询</span>
					</li>		
				</ul>
				</form>
			</div>
		
			<table class="format">
				<thead>
					<th width="17%">设计名称</th>
					<th width="10%">商家</th>
					<th width="5%">设计师</th>
					<th width="12%">案例名称</th>
					<th width="10%">监理</th>
					<th width="8%">日记标题</th>
					<th width="8%">创建日期</th>
					<th width="8%">创建用户</th>
					<th width="7%">操作</th>				
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td>${item.designName}</td>
						<td>${item.spName}</td>
						<td>${item.name}</td>
						<td>${item.engcommunity}</td>
						<td>${item.jlName}</td>
						<td>${item.subject}</td>
						<td>${item.createTimeString}</td>
						<td>${item.createUser}</td>
						<td>
							<span class="btn">分享</span>
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
</body>
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr menuid="<%=result.elements[i].id%>">
		<td><%=result.elements[i].designName%></td>
		<td><%=result.elements[i].spName%></td>
		<td><%=result.elements[i].name%></td>
		<td><%=result.elements[i].engcommunity%></td>
		<td><%=result.elements[i].jlName%></td>
		<td><%=result.elements[i].subject%></td>
		<td><%=result.elements[i].createTimeString%></td>
		<td><%=result.elements[i].createUser%></td>
		<td>
			<span class="btn">分享</span>
		</td>
	</tr>
<%}%>
</script>
<script type="text/javascript">
$(function(){
	$('.ui-paging').page({
		url:'${base}/case/pagination.do',
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