<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<title>认证信息</title>
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
		.fieldStyle{padding:20px;}
		.conBox ul{padding-top:20px;}
		.conBox ul li img{margin-bottom:10px;}
		strong.inputs{width:100px;margin-left:30px;}
		input.sbt{margin:30px 0 0 30px;padding:5px 10px;}
		p.tips{margin:5px 0 5px 10px;}
		input.text{width:200px;}
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
					<form id="send" action="saveOneInfo.do" enctype="multipart/form-data" method="post">
					<ul>
						  <li>
						  	<strong class="inputs">身份证号码：</strong><input type="text" class="text" disabled value="${supplier.idCard}" name="idCard" >
						  		<div class="clearfix">
								 	<div style="padding :20px;float:left;">  
										<p class="tips">身份证正面</p> 
									<div id="preview1">
										<input type="hidden" name="avatar"/>
									    <img id="imghead11" src="${fontImg}" style="padding-left:10px;"  width=350 height=230 border=0> 
									</div>  
					        	</div>
					        	<div style="padding :20px;float:left;">
									<div id="preview3">
										<p class="tips">身份证反面</p>  
										<input type="hidden" name="avatar"/>
									    <img id="imghead3" style="padding-left:10px;"  src="${negativeImg}" width=350 height=230 border=0> 
									</div>  
					        	</div>
					        </div>
				         </li>
			        </ul>
					<ul style="clear:both;">
						  <li> 
						   <strong class="inputs">营业执照编码：</strong><input type="text" disabled class="text businessCode" value="${supplier.businessCode}" name="businessCode" id="">
							 <div style="padding :20px;">
								<div id="preview2"> 
									<input type="hidden" name="avatar"/>
								    <img id="imghead2" style="padding-left:10px;" src="${licenceImg}"  width=400 height=350 border=0> 
								</div>  
					        </div>
				         </li>
					     <li>
					        <a href="${base}/material/authentication/add.xhtml" style="padding: 4px 12px;border-radius: 3px;">重新绑定</a>
					     </li>
				     </ul>
				 </form>
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