<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>限时折扣管理</title>
[#include "/common/res.ftl"]
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/form.js"></script>
<style type="text/css">
.addManager ul{ padding: 20px 0;}
.addManager ul li strong{ width: 10%; display: block; float: left; text-align: right; margin-right: 5px;}
.addManager ul li input{ width: 120px;height:22px; float: left;}
.addManager ul li textarea{ width: 200px; float: left;}
.addManager .buttons{ margin-left: 10%; float: left; margin-top:10px;margin-bottom:10px;padding-left:4px;}
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
			<div class="ui-dialog addManager" style="width:100%;">
		
				<table style="width:100%" border="1">
					<tr height=25>
						<th>券批次</th>
						<td>${view.no}</td>
						<th>发行张数</th>
						<td>${num}</td>
						<th>有效日期</th>
						<td>${view.expireStartStr} - ${view.expireEndStr}</td>
					</tr>
					<tr height=25>
						<th>o2o券名</th>
						<td>${view.name}</td>
						<th>所属分类</th>
						<td></td>
						<th>实际面值</th>
						<td>${view.parValue}</td>
					</tr>
					<tr height=25>
						<th>销售价格</th>
						<td colspan="5">${view.price}</td>
					</tr>
				</table>
				
				<table style="width:100%;margin-top:30px;" border="1">
					<tr height=25 style="background:#ccc;">
						<th>类型</th>
						<th>所属区域</th>			
						<th>发布数量</th>
						<th>开始日期</th>
						<th>结束日期</th>
						<th>发布日期</th>
					</tr>
					<tr height=25>
						<td>${view.ison}</td>
						<td>${city}</td>
						<td>${num}</td>
						<td>${view.expireStartStr}</td>
						<td>${view.expireEndStr}</td>
						<td>${view.createTimeStr}</td>
					</tr>
				</table>
				
			</div>
		</div>
	</div>
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]
</body>
</html>