<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>商家子帐号管理</title>
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
						<span class="sp"><strong>员工编号：</strong><input type="text" class="text" name="uid" id=""></span>
					</li>
					<li>
						<span class="sp"><strong>员工姓名：</strong><input type="text" class="text" name="uname" id=""></span>
						<span class="btn search">查 询</span>
					</li>
				</ul>
				</form>
			</div>
		    <a class="obtn" href="${base}/supplier/in/update.xhtml?bid=${bid}&mid=${mid}&pid=${pid}">添加</a>
		    <span class="btn search" onclick="history.back()" style="font-size:12px;">返回</span>
		<br/>
			<table class="format">
				<thead>
					<th width="10%">账号编号</th>
					<th width="20%">登录账号</th>
					<th width="15%">绑定手机</th>
					<th width="15%">创建时间</th>
					<th width="20%">账号权限</th>
					<th width="10%">操作</th>				
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td>${item.id}</td>
						<td>${item.username}</td>
						<td>${item.mobile}</td>
						<td>${item.createTimeString}</td>
						<td>
						[#if item.isAudit==0]
							【审核员 】
						[/#if]
						[#if item.isAdmin==0]
							【管理员】
						[/#if]
						[#if item.isMerchandiser==0]
							【接单员】
						[/#if]
						[#if item.isDesigner==0]
							【设计师】
						[/#if]
						</td>
													
						<td>
						<a class="obtn" href="${base}/supplier/in/update.xhtml?id=${item.id}&bid=${bid}&mid=${mid}&pid=${pid}">修改</a>
						<span class="btn del" rel="${base}/supplier/in/deleteUser.do?id=${item.id}&bid=${bid}&mid=${mid}&pid=${pid}">删除</span>
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
	<td><%=result.elements[i].username%></td>
	<td><%=result.elements[i].mobile%></td>
	<td><%=result.elements[i].createTimeString%></td>
	<td> <%if(result.elements[i].isAudit==0){ %>	
							审核员 
						<%}%>
						<%if(result.elements[i].isAdmin==0){ %>
						管理员
						<%}%>
						<%if(result.elements[i].isMerchandiser==0){ %>	
							跟单员
							<%}%>
						<%if(result.elements[i].isDesigner==0){ %>
							设计师
						<%}%></td>
	<td>
	<a class="obtn" href="${base}/supplier/in/update.xhtml?id=<%=result.elements[i].id%>&bid=${bid}&mid=${mid}&pid=${pid}">修改</a>
	<span class="btn del" rel="${base}/supplier/in/deleteUser.do?id=<%=result.elements[i].id%>&bid=${bid}&mid=${mid}&pid=${pid}">删除</span>
</tr>	
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/supplier/in/pagination.do?sid=${bid}',
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
			//$('table.doctorList tbody').html(temp.join(''));
	    }
	});
})
</script>
</body>
</html>