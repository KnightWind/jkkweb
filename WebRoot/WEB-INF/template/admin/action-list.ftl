<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
[#include "/common/res.ftl"]
<link rel="stylesheet" type="text/css" href="${base}/styles/action.css">
<style>
table.list tr{ display:none;}
table.list tr.sp{ cursor:pointer;}
table.list tr.sp td{ background: #E7E8EB;}
</style>
</head>

<body>
<!-- header -->
[#include "/common/head.ftl"]
<!-- wrap -->
<div class="wrap index bc clr">
	<div class="conWrap fl">
		[#include "/common/nav.ftl"]
		<div class="conBox">
			<div class="shd"><span class="btn addSub fr">权限添加</span></div>
			<table class="format list">
				<thead>
					<th width="30%">操作名称</th>
					<th width="40%">连接地址</th>
					<th width="30%">操作</th>
				</thead>
				<tbody>
					[#list dataList as item]
					<tr key="${item.id}" name="main" expand=0 class="sp">
						<td colspan="3" style="font-size:14px;font-weight:bold;">${item.name}</td>
					</tr>
					[#list item.children as item2]
					<tr key="${item2.id}" pid="${item.id}">
						<td name="name" style="text-align:left;padding-left:150px;">${item2.name}</td>
						<td name="link">${item2.link}</td>
						<td>
							<span class="btn edit">编辑</span>
							<span class="btn del">删除</span>
						</td>
					</tr>
					[/#list]
					[/#list]
				</tbody>
		</table>
	</div>
	</div>
	
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]

</body>
<script type="text/javascript" src="${base}/scripts/action.js"></script>

<script type="text/html" id="actionEdit">
<div class="ui-dialog actionEdit">
	<h2><span class="close"></span><i class="new del sp">&#xf013;</i> 添加/编辑权限</h2>
	<form id="send">
	<input type="hidden" name="id" />
	<ul>
		<li><strong>操作名称：</strong> <input type="text" name="name" placeholder="操作名称"></li>
		<li><strong>链接地址：</strong> <input type="text" name="link" placeholder="链接地址"></li>
		<li><strong style="float:left">父级操作：</strong> 
			<select style="float:left">
				<option value="0">顶级</option>
				[#list operation as item]
				<option value="${item.id}">${item.name}</option>
				[/#list]
			<select>
		</li>
		<li class="btn"><span class="btn save">保存</span><span class="btn reset">清空</span></li>
	</ul>
	</form>
</div>
</script>
</html>