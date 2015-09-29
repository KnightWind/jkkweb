<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>预约设计</title>
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
			<table class="format">
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td>预约编号：${item.id}（${item.ison}）</td>
						<td>预约时间：${item.cre}</td>
					</tr>
					<tr>
						<td>预约状态：${item.statusName}</td>
						<td>跟单员：${item.admin}</td>
					</tr>
					<tr>
						<td>预约类型：${item.subTypeName}</td>
						<td>所在城市：${item.city}</td>
					</tr>
					<tr>
						<td>所在小区：${item.community}</td>
						<td>详细地址：${item.address}</td>
					</tr>
					<tr>
						<td>业主称呼：${item.user}</td>
						<td>用户电话：${item.mobile}</td>
					</tr>
					<tr>
						<td>装修方式：${item.zhuangXiu}</td>
						<td>是否交房：${item.istrue}</td>
					</tr>
					<tr>
						<td>房屋类型：</td>
						<td>装修预算：${item.budget} 万</td>
					</tr>
					<tr>
						<td>建筑面积：${item.space}</td>
						<td>签约商家：${item.company}</td>
					</tr>
					<tr>
						<td>客厅地面：${item.die} ${item.dieXia}</td>
						<td>客厅墙面：${item.qian} ${item.qianPi}</td>
					</tr>
					<tr>
						<td>厨房：${item.chuFa} ${item.dieXia}</td>
						<td>卫生间：${item.wei} ${item.weiXi}</td>
					</tr>
						<tr>
						<td>水路改造：${item.shuiLu}</td>
						<td>电路改造：${item.dianLu}</td>
					</tr>
					<tr>
						<td>回坊时间：${item.lin}</td>
						<td>预计开工：${item.cre}</td>
					</tr>
					<tr>
						<td>其他要求：${item.content}</td>						
					</tr>
					[/#list]
				</tbody>
		</table>
	
	</div>
			<div class="conBox">
			<table class="format">
				<tbody>
				<thead>
					<th width="8%">下发公司</th>
					<th width="8%">下发客服</th>
					<th width="8%">下发时间</th>
					<th width="8%">查看状态</th>
				</thead>
					[#list paginationq.dataList as item]
					<tr>
						<td>${item.name}</td>
						<td>${item.uname}</td>
						<td>${item.time}</td>
						<td>${item.zt}</td>
					</tr>		
					[/#list]
				</tbody>
		</table>
	<span class="btn search" onclick="history.back()">返回</span>
	</div>
	</div>
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]
</body>
</html>