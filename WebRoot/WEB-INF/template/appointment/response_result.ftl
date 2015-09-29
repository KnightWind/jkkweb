<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<title>预约列表</title>
		[#include "/common/res.ftl"]
		<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
		<script type="text/javascript" src="${base}/scripts/form.js"></script>
		<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
		<style>
		.logo{height:82px; background:#fff; border-bottom:2px solid #1D9DA6;}
		.logo p{padding-top:20px;}
		.logo p a{color:#333; font-size:14px;}
		.logo p a i{ font-size:16px;}
		.conWrap h2.hd{ border-color:#ccc;}
		</style>
	</head>
	<body onload="daojishi();">
	<!-- header -->
		<div class="header">
			<div class="logo">
				<h1><a href="#"><img src="${base}/images/logo.png"></a></h1>
				<p>
					<a target="_blank" href="http://shop.jiakeke.com?sp_id=${su.spId}">欢迎您${su.spname}</a>
					<a href="${base}/supplier/logout.xhtml"><i class="new">&#xf08b;</i> 注销</a>
					<a href="/supplier/password" class="logout"><i class="new">&#xf09c;</i> 修改密码</a>
				</p>
			</div>
		</div>
			<!-- wrap -->
			<div class="wrap index bc clr">
				<div class="conWrap fl">
					<h2 class="hd mb10">
						<strong><a href="#">商家后台</a></strong> &gt;
						<span id="sel1" data-role="首页">抢单结果</span>
					</h2>
					<div class="conBox">
						<!--内容放这里 start-->
						<div class="tools white border mb10 sp10 clr" style="height:500px;text-align:center;line-height:500px;font-size:20px;">
							<div class="result">${msg}&nbsp;&nbsp;&nbsp;<font color="red" size="5" class="change">3</font>秒后跳转...</div>
						</div>
						<!--内容放这里 end-->
					</div>
				</div>
				[#include "/common/supplierMenu.ftl"]
			</div>
			<!-- footer -->
			<div class="footer">
				<div class="wrap bc tc">
					<p>北京居天下网络科技有限公司　010-67482366 67477686 &nbsp;&nbsp;地址：北京朝阳区十八里店周家庄村288号融望大酒店6层 居天下版权所有Copyright © 2013-2015 京ICP备15010944</p>
				</div>
			</div>
		</body>		
		<script>
			var i = 3;
			function daojishi(){
				if(i < 0){
					$(".result").html("跳转中...");
					window.location.href="${base}/appointmentPush/supplierPushIndex.xhtml?status=0";
					return;
				}
				$(".change").text(i);
				i--;
				setTimeout("daojishi();",1000);
			}
			
		</script>
	</html>