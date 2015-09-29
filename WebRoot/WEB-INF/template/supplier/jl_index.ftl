<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<title>监理管理</title>
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
		.dataShow{ width:48%; height:500px; float:left; padding-top:20px;}
		.dataShow p{height:40px;}
		.dataShow table{ margin-bottom:20px;}
		.dataShow table td{ border-right:1px solid #ccc;}
		.dataSp{ margin-right:4%;}
		</style>
	</head>
	<body>
		<!-- header -->
		[#include "/common/supervisorHead.ftl"]</div>
		<!-- wrap -->
		<div class="wrap index bc clr">
			<div class="conWrap fl">
				<h2 class="hd mb10">
					<strong><a href="#">监理后台</a></strong> &gt;
					<span id="sel1" data-role="首页">首页</span>
				</h2>
				<div class="conBox">
					<div class="dataShow dataSp">
						<p><span class="btn">店铺信息</span></p>
						<table class="format">
	                        <tr>
	                          <td>签约时间</td>
	                          <td>.0.....</td>
	                        </tr>
	                        <tr>
	                          <td>商家状态</td>
	                          <td>正常</td>
	                        </tr>
	                        <tr>
	                          <td>银行账户</td>
	                          <td></td>
	                        </tr>
	                    </table>
						<p><span class="btn">订单信息</span></p>
						<table class="format">
	                        <tr>
	                            <td>总数</td>
	                            <td>等待付款</td>
	                            <td>等待发货</td>
	                            <td>退货申请</td>
	                        </tr>
	                        <tr>
	                            <td>0</td>
	                            <td>0</td>
	                            <td>0</td>
	                            <td>0</td>
	                        </tr>
	                    </table>
					</div>
					<div class="dataShow">
						<p><span class="btn">数据报告</span></p>
						<table class="format">
	                        <tr>
	                            <td>今日销售额</td>
	                            <td>0 元</td>
	                        </tr>
	                        <tr>
	                            <td>出售中的商品</td>
	                            <td>0 件</td>
	                        </tr>
	                        <tr>
	                            <td>仓库中的商品</td>
	                            <td>0 件</td>
	                        </tr>
	                        <tr>
	                            <td>待发货订单</td>
	                            <td>0 单</td>
	                        </tr>
	                        <tr>
	                            <td>已发货订单</td>
	                            <td>0 单</td>
	                        </tr>
	                    </table>
						<p><span class="btn">结算信息</span></p>
						<table class="format">
	                        <tr>
	                            <td>全部</td>
	                            <td>未结算</td>
	                            <td>已结算</td>
	                        </tr>
	                        <tr>
	                            <td>0.00</td>
	                            <td>0.00</td>
	                            <td>0.00</td>
	                        </tr>
	                    </table>
					</div>
				</div>
			</div>
			
			[#include "/common/supervisorMenu.ftl"]
			</div>
			<!-- footer -->
			<div class="footer">
				<div class="wrap bc tc">
					<p>北京居天下网络科技有限公司　010-67482366 67477686 &nbsp;&nbsp;地址：北京朝阳区十八里店周家庄村288号融望大酒店6层 居天下版权所有Copyright © 2013-2015 京ICP备15010944</p>
				</div>
			</div>
		</body>
	</html>