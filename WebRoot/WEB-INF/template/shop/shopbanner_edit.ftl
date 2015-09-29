<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>轮播图管理</title>
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
				<form id="search" action="${base}/shop/ad.xhtml?mid=${mid}&pid=${pid}" method="post">
				<ul>
					<li>
						<strong>*轮换图名</strong>
						<input type="text" name="title" value="${view.title}"/>
					</li>		
					<li>
						<strong>*轮换图</strong>
						<input type="file"  name=""/>
					</li>
					<li>
						<strong>*链接地址</strong>
						<input type="text"  name="place" value="${view.place}"/>
					</li>
					<li>
						<strong>*顺序</strong>
						<input type="text"  name="seq" value="${view.seq}"/>
					</li>
					<li>
						<strong>*是否启用</strong>
						<input type="radio" name="status" [#if view.status=0]checked[/#if] value="0">启用
						<input type="radio" name="status" [#if view.status=1]checked[/#if] value="1">禁用
					</li>
					<li>
					<input type="hidden" name="id" value="${view.id}"/>
						<input type="submit" value="修改">&nbsp;
						<span class="btn search" onclick="history.back()">返回</span>
					</li>
				</ul>
				</form>
			</div>
		
		[#include "/common/pagination.ftl"]
	</div>
	</div>
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]
</body>
</html>