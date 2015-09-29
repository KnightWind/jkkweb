<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>商品分类列表</title>
[#include "/common/res.ftl"]
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
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
           <!-- [#if errorMessage]
            <div class="promptBox promptBox4">
          		<i class='hinticon_nor hinticon_nor hinticon_nor_PY_fork'></i>
                <b style="color:red;">一级分类最多只能添加7个</b>
                <span></span>
            </div>
            [/#if]-->
            <div class="shd">
            	<span style="float:right">
            	[#if (parentId >= 0)]
            	<a href="${base}/goods/itemCategory/index.xhtml?parentId=${parentId}&mid=${mid}&pid=${pid}"><span class="btn">返回</span></a>
            	[/#if]
            	<a href="${base}/goods/itemCategory/edit.xhtml?parentId=${id}&mid=${mid}&pid=${pid}"><span class="btn">添加</span></a>
            	</span>
            	<strong>分类：${categoryName}</strong>
            </div>
			<table class="format">
				<thead>
					<th width="4%"><input type="checkbox" name="" class="selectAll"/></th>
					<th width="8%">一级分类名</th>
					<th width="8%">子分类数</th>
					<th width="14%">备注</th>
					<th width="18%">操作</th>				
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td><input type="checkbox" name=""  /></td>
						<td>${item.name}</td>
						<td>[#if (item.subItemCount gt 0)]${item.subItemCount}[#else]无[/#if]</td>					
						<td>${item.comments}</td>
						<td>
						<a href="${base}/goods/itemCategory/index.xhtml?parentId=${item.id}&mid=${mid}&pid=${pid}"><span class="btn">查看</span></a>
						<a href="${base}/goods/itemCategory/edit.xhtml?id=${item.id}&mid=${mid}&pid=${pid}"><span class="btn modify">修改</span></a>
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
</html>