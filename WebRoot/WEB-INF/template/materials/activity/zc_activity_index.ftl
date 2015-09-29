<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<title>家可可活动</title>
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
		</style>
	</head>
	<body>
	<!-- header -->
		[#include "/materials/common/head.ftl"]
		<!-- wrap -->
		<div class="wrap index bc clr">
			<div class="conWrap fl">
				<h2 class="hd mb10">
					<strong><a href="#">建材后台</a></strong> &gt;
					<span id="sel1" data-role="">商家参与名单</span>
				</h2>
				 <div class="conBox">
					<!--内容放这里 start-->
			      
			<table class="format">
				<thead>
					<th width="20%">活动名称</th>
					<th width="10%">活动状态</th>
					<th width="10%">操作</th>
				</thead>
				<tbody>
					<tr>
						<td>团购活动</td>
						<td>进行中</td>
						<td>
							<a class="obtn" href="index.xhtml?url=zc_activity_list">查看</a>
						</td>
					</tr>
				</tbody>
		</table>
		[#include "/common/pagination.ftl"]
				<!--内容放这里 end-->
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

    
<script type="text/html" id="list">
</script>
<script>
$(function(){
});	
</script>
    	
</html>