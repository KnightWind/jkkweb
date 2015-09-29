<!DOCTYPE HTML>
<html>
	<head>
			<meta charset="utf-8">
			<title>工程单详情</title>
			[#include "/common/res.ftl"]
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
				<style>
					#dialog tr{
						line-height:30px;
					}
					#dialog td{
						width:200px;
					}
					#dialog{
						font-size:15px;
						margin:10px;
					}
			</style>
</head>
	</head>
<body>
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
					<span id="sel1" data-role="首页">我的日记</span>
				</h2>
				<div class="conBox">
		    	<!--内容放这里 start-->
					
					<div id="engineering">
					<table id="dialog">
						<tr>
							<td >小区(工地)名称:</td>
							<td>
								${engineering.community}
							</td>
							<td >用户:</td>
							<td>
								${engineering.nickName}
							</td>
						<tr>
						<tr>
							<td>装修阶段:</td>
							<td>
								${engineering.stageVal}
							</td>
							<td>工程款(单位:元):</td>
							<td>
								${engineering.zxFund}
							</td>
						<tr>
						<tr>
							<td>监管款(单位:元):</td>
							<td>
								${engineering.jgFund}
							</td>
							<td >待付款(单位:元):</td>
							<td>
								${engineering.toFund}
							</td>
						<tr>
						<tr>
							<td>装修公司:</td>
							<td>
								${engineering.spName}
							</td>
							<td >设计师:</td>
							<td>
								${engineering.designerName}
							</td>
						<tr>
						<tr>
							<td >创建人:</td>
							<td>
								${engineering.creauser}
							</td>
							<th>创建时间:</th>
							<td>
								${engineering.createTimeHandle}
							</td>
						<tr>
						<tr>
							<td >经度:</td>
							<td>
								${engineering.pointx}
							</td>
							<td >纬度:</td>
							<td>
								${engineering.pointy}
							</td>
						<tr>
						<tr>
						   <td>备注</td>
						   <td colspan="3">${engineering.note}</td>
						</tr>
					</table>
				 </div>
					<span class="btn" onclick="history.back()" style="font-size:12px;margin-left:300px;">返回</span>
				<div id="design_detail">
				   <h3>装修方案</h3>
				   <table class="format">
						<tr>
						    <td>方案编号:</td>
						    <td>
						       ${design.id}
						    </td>
							<td >设计名称:</td>
							<td>
								${design.designName}
							</td>
							<td >商户名称:</td>
							<td>
								${design.spName}
							</td>
							<td >工长名称:</td>
							<td>
								${design.forman}
							</td>
						<tr>
						<tr>
						    <td>设计公司:</td>
						    <td>
						       ${design.company}
						    </td>
							<td >设计师:</td>
							<td>
								${design.designer}
							</td>
							<td >审核状态:</td>
							<td>
								${design.statusVal}
							</td>
							<td >冻结状态:</td>
							<td>
								${design.zd}
							</td>
						<tr>
						<tr>
						    <td>户型:</td>
						    <td>
						       ${design.huXing}
						    </td>
							<td >空间:</td>
							<td>
								${design.kongJian}
							</td>
							<td >风格:</td>
							<td>
								${design.fengGe}
							</td>
							<td >创建时间:</td>
							<td>
								${design.createTimeHandle}
							</td>
						<tr>
						<tr>
						    <td>城市:</td>
						    <td>
						       ${design.city}
						    </td>
							<td >备注:</td>
							<td colspan="5">
								${design.remark}
							</td>
						<tr>
					</table>
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
		<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
		<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
</html>