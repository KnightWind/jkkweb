<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<title>业主信息管理</title>
		[#include "/common/res.ftl"]	
		<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
		<style>
		.logo{height:82px; background:#fff; border-bottom:2px solid #1D9DA6;}
		.logo p{padding-top:20px;}
		.logo p a{color:#333; font-size:14px;}
		.logo p a i{ font-size:16px;}
		.conWrap h2.hd{ border-color:#ccc;}
		.dataShow{ width:48%; height:500px; float:left; padding-top:20px;}
		.dataShow p{height:40px;}
		.dataShow table{ margin-bottom:20px;}
		.dataShow table td{ border-right:1px solid #ccc;}
		.dataSp{ margin-right:4%;}
		.fieldStyle{
			padding:20px;
		}
		
	.bott{
		
	}
	.left{
		width:63%;
	}
	.right{
		width:35%;
		margin-left:30px;
	}
	
	table.format1{ width: 100%; border: 1px solid #ccc; border-collapse: collapse; background: #fff; font-size: 12px;}
	table.format1 caption{ height: 40px; line-height: 40px; font-size: 16px;}
	table.format1 thead th{ border-bottom:1px solid #ccc; height: 40px; font-size: 14px; text-align: center; background: #E7E8EB; font-weight: bolder;}
	table.format1 tbody td{ border-bottom:1px solid #ccc; height: 40px; text-align: center;word-wrap: break-word; word-break: break-all;}
	table.format1 tbody tr.cur td{ background:#E7E8EB;}
	
	.input td{height: 50px;}
		</style>
	</head>
	<body>
	<!-- header -->
		[#include "/materials/common/head.ftl"]
		<!-- wrap -->
		<div class="wrap index bc clr">
			<div class="conWrap fl">
				<h2 class="hd mb10">
					<strong><a href="#">建材后台</a></strong>
3
				</h2>
				 <div class="conBox">
				[#include "/common/pagination.ftl"]
		         </div>
	       </div>
			[#include "/materials/common/nav.ftl"]
       </div>
	   <!-- footer -->
		<div class="footer">
			<div class="wrap bc tc">
				<p>北京居天下网络科技有限公司　010-67482366 67477686 &nbsp;&nbsp;地址：北京朝阳区十八里店周家庄村288号融望大酒店6层 居天下版权所有Copyright © 2013-2015 京ICP备15010944</p>
			</div>
		</div>
</body>
</html>