<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>商品列表</title>
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
		<form id="search" action="${base}/admin/menu/up.xhtml?mid=${mid}&pid=${pid}" method="post">
				<table class="format">
				<thead>					
					<th width="8%">位置</th>				
					<th width="9%">导航名称</th>
					<th width="9%">连接地址</th>							
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>		
					<td>${item_index+1}</td>	
						<td><input type="text" name="name"  value="${item.name}"></td>	
						<td><input type="text" name="url"  value="${item.url}"></td>
						<td><input type="hidden" name="id"  value="${item.id}"></td>
					</tr>
					[/#list]
					
				</tbody>
		</table> 
		<input type="submit" value="提交保存">    
			</form>
		[#include "/common/pagination.ftl"]
	</div>
	</div>
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]
</body>
</html>