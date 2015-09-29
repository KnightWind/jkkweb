<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>预约设计</title>
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
			<table class="format">
				<thead>
					<th width="5%">编号</th>
					<th width="5%">帐号</th>
					<th width="5%">角色</th>
					<th width="10%">姓名</th>
					<th width="10%">手机</th>
					<th width="10%">Email地址</th>
					<th width="10%">处理数量</th>
					<th width="10%">已处理</th>
					<th width="10%">未处理</th>
					<th width="10%">最后登录</th>
					<th width="10%">操作</th>
					<th width="5">操作</th>
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td>${item.id}</td>
						<td>${item.username}</td>
						<td>预约回访员</td>
						<td>${item.nickname}</td>
						<td>${item.mobile}</td>
						<td>100</td>
						<td>100</td>
						<td>100</td>
						<td>${item.loginTime}</td>
						<td><span class="btn modify">转移</span>
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
</html>