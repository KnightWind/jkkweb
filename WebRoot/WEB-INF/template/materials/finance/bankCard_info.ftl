<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<title>银行卡绑定</title>
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
		<style type="text/css">
		    .edit{font-size: 12px;margin:30px;}
		    .edit li{line-height: 40px;}
		    .edit .text li span{width: 150px;display: block;float: left;text-align: right;}
		    .edit .text li p{float: left;}
		    .edit .text li p input{font-size: 12px;padding: 5px 10px;}
		    .edit .text li p strong b{font-weight: normal;color: #f30;font-size: 10px;}
		    .p{margin-left: 150px;}
		</style>	
	</head>
	<body>
	<!-- header -->
		[#include "/materials/common/head.ftl"]
		<!-- wrap -->
		<div class="wrap index bc clr">
			<div class="conWrap fl">
				 <div class="conBox">
					<!--内容放这里 start-->
					   <div class="edit">
				        <div>
				             <ul class="text">
				                <li class="clr">
					                <span>当前银行卡号：</span>
					                <p>
					                    <input type="text" class="text" value="${card.cardNo}" disabled placeholder="银行卡号" id="cardNo" style="width:196px" />
					                </p>
				                </li>
					          </ul>
				        </div>
				        <div>
				            <ul class="text">
				                <li class="clr">
				                    <span>当前银行卡发卡行：</span>
				                    <p>
				                        <input type="text" class="text" value="${card.bankName}" placeholder="银行卡发卡行" disabled style="width:196px"/ >
				                    </p>
				                </li>
				                <li class="clr">
				                    <span>当前银行卡所在地：</span>
				                    <p>
				                       <input type="text" class="text" value="${pRegionName}" placeholder="银行卡发卡所在城市" disabled style="width:85px"/ >
				                       <input type="text" class="text" value="${regionName}" placeholder="银行卡发卡行所在区" disabled style="width:85px"/ >
				                    </p>
				                </li>
				            </ul>
				        </div>
				        <div>
				            <ul class="text">
				                <li class="clr">
				                    <span>当前支付密码：</span>
				                    <p>
				                        <input type="password" class="text" value="${card.payPwd}" disabled placeholder="支付密码" style="width:196px"/>
				                    </p>
				                </li>
				            </ul>
				        </div>
				       <span class="btn" style="margin-left:151px; margin-top:10px;"><a href="${base}/material/financeBankCard/add.xhtml">重新绑定</a></span>
				      </div>
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
</html>