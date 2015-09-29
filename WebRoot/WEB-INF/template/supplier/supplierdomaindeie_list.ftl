<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>商家域名管理</title>
[#include "/common/res.ftl"]
<link rel="stylesheet" type="text/css" href="${base}/styles/pages.css">
</head>

<body>
<!-- header -->
[#include "/common/head.ftl"]
<!-- wrap -->
<div class="wrap index bc clr">
	<div class="conWrap fl">
		[#include "/common/nav.ftl"]
		<div class="conBox crm white border">
		<form method="post" action="${base}/supplier/dc/add.xhtml?mid=${mid}&pid=${pid}">
				[#list pagination.dataList as item]
				<input type="hidden" name="id" value="${item.splid}">
				<input type="hidden" name="did" value="${item.id}">
				<ul>
					<li><span>商户账户：</span>${item.uname}</li>
					<li><span>商户名称：</span>${item.sname}</li>
					<li><span>联系人名：</span>${item.name}</li>
					<li><span>联系电话：</span>${item.phto}</li>
					<li><span>商户类型：</span>${item.type}</li>
					<li><span>绑定域名：</span><input type="text" class="text" name="yu" value="${yu}" >.108jw.com</li>
				</ul>
				<p class="btn"><input type="submit" class="submit mr10" value="绑定"><span class="btn search" onclick="history.back()">返回</span></p>
				[/#list]
		</form>
		</div>
	</div>
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]
</body>
</html>