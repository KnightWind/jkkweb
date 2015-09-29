<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>商家搜索</title>
[#include "/common/res.ftl"]
</head>

<body>
<!-- wrap -->
<div class="wrap index bc clr">
	<div class="conWrap fl">
		<div class="conBox">
			<form action="${base}/index/search/commonSearch.xhtml" method="post">
				<input name="queryString" /><input class="btn" type="submit" value="搜索" />
			</form>
		</div>
		<div class="conBox">
			[#list resultList as item]
			<p style="height:24px;line-height:24px;">
				<a href="${base}/supplier/detail.xhtml?id=${item.id}" target="_blank">${item.spName}</a>
			</p>
			[/#list]
		</div>
	</div>
</div>
</body>
</html>