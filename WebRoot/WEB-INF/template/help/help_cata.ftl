<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>帮助目录</title>
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
			<div class="shd"><strong>分类:${helpName}</strong></div>
			<table class="format">
				<thead>
					<th width="40%">一级目录</th>
				    <th width="40%">子目录数</th>
					<th width="20%">操作</th>				
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td>${item.name}</td>
						<td>${item.chilItemCount}</td>														
						<td>
						<span class="btn modify">
						 [#if item.chilItemCount>0]
						    	<a href="${base}/helpCategory/index.xhtml?pid=${pid}&mid=${mid}&parentId=${item.id}">查看</a>
						   [#else]
						  	    <a>查看</a>
						 [/#if]
						   
						</span>
						<span class="btn modify" rel=["${item.id}","${item.name}"]>修改</span> 
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
<script type="text/javascript" src="${base}/scripts/form.js"></script>
<script type="text/html" id="pannel">
<div class="ui-dialog addManager">
	<h2><span class="close"></span><strong>修改目录</strong></h2>
	<form id="send" action="save.do">
	<input type="hidden" name="id">
	<ul>
		<li><strong><b class="red">*</b> 目录名称：</strong> <input type="text" name="name" placeholder="目录名称"></li>
		<li class="btn"><span class="btn save" rel="add">提交保存</span></li>
	</ul>
	</form>
</div>	
</script>
</html>