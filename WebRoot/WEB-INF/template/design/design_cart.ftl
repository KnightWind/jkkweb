<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>户型,空间,风格</title>
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
			<style>
			.otj-table{border-collapse:collapse;}
			.otj-table td{padding:10px;border:1px solid #ccc;vertical-align: top;}
			.table_01_1 {width:100%;}
			.table_01_1 th {height:36px;width:100%;text-align:center;}
			.table_01_1 td {text-align:center;}
			.table_01_tr th{background:rgb(229,242,251);border:1px solid rgb(204,204,204);}
			.table_01_tr span{ color:#333; font-size:14px;}
			</style>
			<table width="100%" class="otj-table white">
				<tr>
					<td>
					<div>
						<div style="height:32px;">
							<span>【户型】分类列表  </span>
							<span style="float:right;" class="btn" onclick="location.href='${base}/design/cate/edit.xhtml?label=huxing&mid=${mid}&pid=${pid}'">户型添加</span>
						</div>
						<table class="table_01_1" style="width:100%;" border="0">
							<tr class="table_01_tr">
					            <th style="width:70%;"><span>分类名称</span></th>
					            <th style="width:30%;"><span>操作</span></th>    
					        </tr>
					        [#list huxing as item]
					        <tr>
					        	<td>${item.cateName}</td>
					        	<td><a href="${base}/design/cate/edit.xhtml?id=${item.id}&label=huxing&mid=${mid}&pid=${pid}"><span class="btn">修改</a></td>
					        </tr>
					        [/#list]
						</table>
					</div>
					</td>
					<td>
					<div>
						<div style="height:32px;">
							<span>【空间】分类列表  </span>
							<span style="float:right;" class="btn" onclick="location.href='${base}/design/cate/edit.xhtml?label=kongjian&mid=${mid}&pid=${pid}'">空间添加</span>
						</div>
						<table class="table_01_1 text-center padding-td-10 text-center-th padding-th-10"  border="0">
							<tr class="table_01_tr">
					            <th style="width:70%;"><span class="red"></span><span>分类名称</span></th>
					            <th style="width:30%;"><span class="red"></span><span>操作</span></th>    
					        </tr>
					        [#list kongjian as item]
					        <tr>
					        	<td>${item.cateName}</td>
					        	<td><a href="${base}/design/cate/edit.xhtml?id=${item.id}&label=kongjian&mid=${mid}&pid=${pid}"><span class="btn">修改</a></td>
					        </tr>
					        [/#list]
						</table>
					</div>
					</td>
					<td>
					<div>
						<div style="height:32px;">
							<span>【风格】分类列表  </span>
							<span style="float:right;" class="btn" onclick="location.href='${base}/design/cate/edit.xhtml?label=fengge&mid=${mid}&pid=${pid}'">风格添加</span>
						</div>
						<table class="table_01_1 text-center padding-td-10 text-center-th padding-th-10"  border="0">
							<tr class="table_01_tr">
					            <th style="width:70%;"><span class="red"></span><span>分类名称</span></th>
					            <th style="width:30%;"><span class="red"></span><span>操作</span></th>    
					        </tr>
					        [#list fengge as item]
					        <tr>
					        	<td>${item.cateName}</td>
					        	<td><a href="${base}/design/cate/edit.xhtml?id=${item.id}&label=fengge&mid=${mid}&pid=${pid}"><span class="btn">修改</a></td>
					        </tr>
					        [/#list]
						</table>
					</div>
					</td>
				</tr>
			</table>
			[#include "/common/pagination.ftl"]
		</div>
	</div>
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]
<script>
$(function(){
	$("span.modify").click(function() {
		var target = this;
		var row = $(this).parent().parent();
		var id = $(this).attr("id");
		var isOffline = $(this).attr("status") == 1;
		var url = "${base}/brand/offline.do";
		if (isOffline) {
			url = "${base}/brand/online.do";
		}
		$.post(url, {id : id}, function(res) {
			if (res.ret == 1) {
				$("[name=statusName]", row).html(isOffline ? "上架" : "下架");
				$(target).attr("status", isOffline ? 0 : 1);
				$(target).html(!isOffline ? "上架" : "下架");
			} else {
				justTip(res.msg);
			}
		}, "json");
	});
});	
</script>
</body>
</html>