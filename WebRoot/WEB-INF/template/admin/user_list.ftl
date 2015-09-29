<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>管理员列表</title>
[#include "/common/res.ftl"]
<link rel="stylesheet" type="text/css" href="${base}/styles/admin-list.css">
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
					<span class="sp"><strong>管理员编号：</strong><input type="text" class="text" name="id"></span>
					<span class="sp"><strong>管理员账号：</strong><input type="text" class="text" name="username"></span>
					<span class="sp"><strong>管理员姓名：</strong><input type="text" class="text" name="nickname"></span>
					<span class="btn search">查 询</span>
				</form>
			</div>
			<div style="float:right;margin-bottom:5px;"><span class="btn addnew">管理员添加</span></div>
			<table class="format">
				<thead>
					<th width="10%">编号</th>
					<th width="10%">账号</th>
					<th width="10%">角色</th>
					<th width="10%">姓名</th>
					<th width="10%">手机</th>
					<th width="20%">Email地址</th>
					<th width="20%">最后登录</th>
					<th width="10%">操作</th>
				</thead>
				<tbody>
					[#list pagination.dataList as s ]
					<tr>
						<td rel="id">${s.id}</td>
						<td rel="username">${s.username}</td>
						<td rel="roleName">${s.roleName}</td>
						<td rel="nickname">${s.nickname}</td>
						<td rel="mobile">${s.mobile}</td>
						<td rel="email">${s.email}</td>
						<td>${s.loginTime?string("yyyy-MM-dd HH:mm:ss")}</td>
						<td><span class="btn modify" rel=["${s.id}","${s.username}","${s.rid}","${s.nickname}","${s.mobile}","${s.email}"]>编辑</span> <span class="btn del" rel="${base}/admin/user/remove.do?id=${s.id}">移除</span></td>
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
<script type="text/html" id="pannel">
<div class="ui-dialog addManager">
	<h2><span class="close"></span><strong>添加管理员</strong></h2>
	<form id="send" action="save.do">
	<input type="hidden" name="id">
	<ul>
		<li><strong><b class="red">*</b> 管理员账号：</strong> <input type="text" class="text" name="username" placeholder="管理员账号"></li>
		<li><strong><b class="red">*</b> 选择角色：</strong> 
			<select name="rid" style="float:left;width:210px;">
				<option value="">请选择</option>
				[#list roleList as item]
				<option value="${item.id}">${item.name}</option>
				[/#list]
			</select>
		<li><strong><b class="red">*</b> 管理员姓名：</strong> <input type="text" class="text" name="nickname" placeholder="管理员姓名"></li>
		<li><strong><b class="red">*</b> 管理员手机：</strong> <input type="text" class="text" name="mobile" placeholder="管理员手机"></li>
		<li><strong> Email地址：</strong> <input type="text" class="text" name="email" placeholder="Email地址"></li>
		<li><strong><b class="red">*</b> 登录密码：</strong> <input type="password" class="text" name="pass" placeholder="登录密码"></li>
		<li><strong><b class="red">*</b> 确认密码：</strong> <input type="password" class="text" name="pass1" placeholder="确认密码"></li>
		<li class="btn"><span class="btn save" rel="add">提交保存</span></li>
	</ul>
	</form>
	<input type="hidden" name="id">
</div>	
</script>
<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr>
		<td><%=result.elements[i].id%></td>
		<td><%=result.elements[i].username%></td>
		<td><%=result.elements[i].roleName%></td>
		<td><%=result.elements[i].nickname%></td>
		<td><%=result.elements[i].mobile%></td>
		<td><%=result.elements[i].email%></td>
		<td><%=result.elements[i].loginTime%></td>
		<td>
			<span class="btn modify" rel=["<%=result.elements[i].id%>","<%=result.elements[i].username%>","<%=result.elements[i].rid%>","<%=result.elements[i].nickname%>","<%=result.elements[i].mobile%>","<%=result.elements[i].email%>"]>编辑</span> 
			<span class="btn del" rel="${base}/admin/user/remove.do?id=<%=result.elements[i].id%>">移除</span>
		</td>
	</tr>
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/admin/user/pagination.do',
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