<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>跟单员列表</title>
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
		<div class="tools white border mb10 sp10 clr">
				<form id="search">
				<hidden  name="role" value="role"/>
				<ul>
					<li>
						<span class="sp"><strong>客服编号:</strong><input type="text" class="text" name="id"/></span>
						<span class="sp"><strong class="auto">客服姓名:</strong><input type="text" class="text" name="nickname"/></span>
						<span class="btn search">查 询</span>
					</li>
				</ul>
				</form>
			</div>	
			<table class="format">
				<thead>
					<th width="4%">编号</th>
					<th width="12%">账号</th>
					<th width="10%">角色</th>
					<th width="8%">姓名</th>
					<th width="8%">手机</th>
					<th width="12%">Email地址</th>
					<th width="8%">处理数量</th>
					<th width="5%">已处理</th>
					<th width="7%">未处理</th>
					<th width="10%">最后登录</th>		
					<th width="10%">操作</th>	
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td>${item.id}</td>
						<td>${item.username}</td>
						<td>${item.name}</td>						
						<td>${item.nickname}</td>	
						<td>${item.mobile}</td>
						<td>${item.email}</td>
						<td>${item.handleSum}</td>
						<td>${item.handleSum5}</td>
						<td>${item.handleSum0}</td>
						<td>${item.loginTimeHandle}</td>
						<td><span class="btn modify"><a href="move.xhtml?mid=${mid}&pid=${pid}&id=${item.id}" target="_blank">转移</a></span>					
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
<script type="text/javascript" src="${base}/scripts/form.js"></script>
<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr>
	<td><%=result.elements[i].id%></td>
	<td><%=result.elements[i].username%></td>
	<td><%=result.elements[i].name%></td>
	<td><%=result.elements[i].nickname%></td>
	<td><%=result.elements[i].mobile%></td>
	<td><%=result.elements[i].email%></td>
	<td><%=result.elements[i].handleSum%></td>
	<td><%=result.elements[i].handleSum5%></td>
	<td><%=result.elements[i].handleSum0%></td>
	<td><%=result.elements[i].loginTimeHandle%></td>
    <td><span class="btn modify"><a href="move.xhtml?id=<%=result.elements[i].id%>" target="_blank">转移</a></span>
</tr>	
<%}%>
</script>

<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/admin/role/pagination.do',
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