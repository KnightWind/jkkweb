
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>角色列表</title>
[#include "/common/res.ftl"]
<link rel="stylesheet" type="text/css" href="${base}/styles/opmenu.css">
</head>

<body>
<!-- header -->
[#include "/common/head.ftl"]

<!-- wrap -->
<div class="wrap index bc clr">
	<div class="conWrap fl">
		[#include "/common/nav.ftl"]
		<div class="conBox oprole">
			<div style="float:right;margin-bottom:5px;"><span class="btn addSub">角色添加</span></div>
			<table class="format">
				<thead>
					<th width="20%">角色编号</th>
					<th width="50%">角色名称</th>
					<th width="30%">操作</th>
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr menuid="${item.id}">
						<td>${item.id}</td>
						<td column="name">${item.name}</td>
						<td>
							<a href="${base}/admin/role/edit.xhtml?id=${item.id}&mid=${mid}&pid=${pid}"><span class="btn">编辑</span></a>
							&nbsp;<span class="btn del">删除</span>
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
<script type="text/javascript" src="${base}/scripts/role.js"></script>
<script type="text/javascript" src="${base}/scripts/form.js"></script>
<script type="text/html" id="roleEdit">
<div class="ui-dialog roleEdit">
	<h2><span class="close"></span><i class="new del sp">&#xf013;</i> 角色管理</h2>
	<form id="send" action="${base}/admin/role/save.do">
	<input type="hidden" name="id" />
	<ul>
		<li><strong>角色名称：</strong> <input type="text" name="name" placeholder="角色名称"></li>
		<li class="btn"><span class="btn save">保存</span><span class="btn reset">清空</span></li>
	</ul>
	</form>
</div>
</script>
<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr menuid="<%=result.elements[i].id%>">
	<td><%=result.elements[i].id%></td>
	<td column="name"><%=result.elements[i].name%></td>
	<td><span class="btn modify">编辑</span>&nbsp;<span class="btn del">删除</span></td>
</tr>	
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/admin/role/pagination.do',
		deal:{'click':'.s_btn','change':'#type,#auditStatus'},
		format:['pageCount','count'],
		send:function(){
			var data={pageSize:10};
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