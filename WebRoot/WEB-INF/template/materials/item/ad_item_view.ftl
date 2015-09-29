<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>'${item.title}'申请取消众筹</title>
[#include "/common/res.ftl"]

<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/form.js"></script>
<Style>
	tr{
		padding-top:10px;
	}
	td{height:55px;font-size:14px;}
	.td_left{text-align:right;font-size:15px;font-weight:bold;}
	#table_detail{mangin:50px;}
	.requi{color:red};
	.table{
			padding:20px;
		}
		
		.table .tr{
			line-height:40px;
			text-align:left;
		}
		.tl{width:100px;}
		.req{color:red}
		.text{width:250px;}
		.oper{width:230px;height:80px;background:#CCC;z-index:999; 
				position:fixed; top:200px; right:50px;line-height:20px;
				text-algin:center;}
		#form{padding:12px;}
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
								<table class="table" style="margin-left:20px;">
							<tr>
								<td class="tl"><span class="req">*</span>商品名称：</td>
								<td class="tr">${item.title}</td>
							<tr>
							<tr>
								<td class="tl"><span class="req">*</span>商品种类：</td>
								<td class="tr">${item.cateName}</td>
							<tr>
							<tr>
								<td class="tl"><span class="req">*</span>起始价格：</td>
								<td class="tr">${item.topPrice}</td>
							<tr>
							<tr>
								<td class="tl"><span class="req">*</span>最低价格：</td>
								<td class="tr">${item.lowPrice}</td>
							<tr>
							<tr>
								<td class="tl"><span class="req">*</span>优惠基数：</td>
								<td class="tr">${item.privilege}</td>
							<tr>
							<tr>
								<td class="tl"><span class="req">*</span>众筹人数：</td>
								<td class="tr">${item.numPeople}</td>
							<tr>
							<tr>
								<td class="tl"><span class="req">*</span>商品定金：</td>
								<td class="tr">${item.stock}</td>
							<tr>
							<tr>
								<td class="tl"><span class="req">*</span>允许退订：</td>
								<td class="tr">
									[#if item.refund == 1]
										允许
									[#else]
										不允许
									[/#if]
								</td>
							<tr>
							[#if item.refund == 1]
								<tr>
									<td class="tl"><span class="req">*</span>退款比例：</td>
									<td class="tr">${item.proportion * 100}%</td>
								<tr>
							[/#if]
							<tr>
								<td class="tl"><span class="req">*</span>开始时间：</td>
								<td class="tr">${item.startTimeString}</td>
							<tr>
							<tr>
								<td class="tl"><span class="req">*</span>结束时间：</td>
								<td class="tr">${item.endTimeString}</td>
							<tr>
							<tr>
								<td class="tl" style="vertical-align:top"><span class="req">*</span>商品描述：</td>
								<td class="tr" style="border:1px double #CCC;width:94%">${item.detail}</td>
							<tr>
						</table>
					<!--内容放这里 end-->
				 </div>
	       </div>
			[#include "/common/menu.ftl"]
		</div>
		<!-- footer -->
	[#include "/common/foot.ftl"]
	
	<div class="oper">
		<form id="form" method="post" action="${base}/material/crowdItem/oper.xhtml?id=${item.id}&mid=${mid}&pid=${pid}">
			【${item.title}】正在申请取消众筹商品<br/>是否通过审核:<input type="radio" checked="checked" name="op" value="3" />是  &nbsp;&nbsp;<input type="radio" name="op" value="1" />否&nbsp;&nbsp;
			<span class="btn" onclick="submit(this)">确认</span>
		</form>
	</div>
	
</body>
    <script>
		function submit(el){
			$(el).parents("form").submit();
		}
	</script>
</html>