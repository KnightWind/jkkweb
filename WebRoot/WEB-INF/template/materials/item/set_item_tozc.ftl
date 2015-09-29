<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<title>编辑众筹商品</title>
		[#include "/common/res.ftl"]	
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
		.fieldStyle{
			padding:20px;
		}
		.table{
			padding:20px;
		}
		
		.table img{
			margin:5px 0 0 20px;
			width:20px;
			height:20px;
		}
		.table .tr{
			line-height:40px;
			text-align:left;
		}
		.req{color:red}
		.text{width:250px;}
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
					<span id="sel1" data-role="编辑众筹商品">编辑众筹商品</span>
				</h2>
				 <div class="conBox">
					<!--内容放这里 start-->
					<form id="send" action="${base}/materials/item/setItemToZC.xhtml" method="post">
						<input type="hidden" name="itemId" value="${itemId}">
						<input type="hidden" name="id" value="${item.id}">
						<input type="hidden" name="activityId" value="${item.activityId}">
						<input type="hidden" name="op" value="${op}">
						<table class="table" style="margin-left:20px;">
							<tr>
								<td class="tl"><span class="req">*</span>活动名称：</td>
								<td class="tr"><input [#if op == 0]disabled="disabled"[/#if] type="text" class="text" name="activityName" value="${actitvity.activityName}" /></td>
							<tr>
							<tr>
								<td class="tl"><span class="req">*</span>起始价格：</td>
								<td class="tr"><input [#if op == 0]disabled="disabled"[/#if] type="text" class="text" name="topPrice" value="${item.topPrice}" /></td>
							<tr>
							<tr>
								<td class="tl"><span class="req">*</span>最低价格：</td>
								<td class="tr"><input [#if op == 0]disabled="disabled"[/#if] type="text" class="text" name="lowPrice" value="${item.lowPrice}" /></td>
							<tr>
							<tr>
								<td class="tl"><span class="req">&nbsp;&nbsp;</span>平台价格：</td>
								<td class="tr"><input [#if op == 0]disabled="disabled"[/#if] type="text" class="text" name="platPrice" value="${item.platPrice}" /></td>
							<tr>
							<tr>
								<td class="tl"><span class="req">&nbsp;&nbsp;</span>优惠基数：</td>
								<td class="tr"><input [#if op == 0]disabled="disabled"[/#if] type="text" class="text" name="privilege" value="${item.privilege}" /></td>
								<td>
									<img src="${base}/images/materials/tips.png" onclick="javascript:showTips('.privilege');"  />
									<span style="display:none;color:red" class="privilege">设置优惠基数后，参与活动的业主数量需超过优惠基数之后才才能开始享受众筹优惠。</span>
								</td>
							<tr>
							<!--<tr>
								<td class="tl"><span class="req">*</span>价格梯度：</td>
								<td class="tr"><input [#if op == 0]disabled="disabled"[/#if] type="text" class="text" name="priceGrad" value="${item.priceGrad}" /></td>
								<td>
									<img src="${base}/images/materials/tips.png" onclick="javascript:showTips('.priceGrad');"  />
									<span style="display:none;color:red" class="priceGrad">价格梯度是指该商品最低价格到市场价格之间的一个优惠梯度。</span>
								</td>
							<tr>-->
							<tr>
								<td class="tl"><span class="req">*</span>众筹人数：</td>
								<td class="tr"><input [#if op == 0]disabled="disabled"[/#if] type="text" class="text" name="numPeople" value="${item.numPeople}" /></td>
							<tr>
							<tr>
								<td class="tl"><span class="req">*</span>商品定金：</td>
								<td class="tr"><input [#if op == 0]disabled="disabled"[/#if] type="text" class="text" name="deposit" value="${actitvity.deposit}" /></td>
							<tr>
							<tr>
								<td class="tl"><span class="req">*</span>允许退订：</td>
								<td class="tr">
									[#if item.refund == 0]
										<input [#if op == 0]disabled="disabled"[/#if] type="radio" value="1" name="refund" onclick="shows();" /> 允许
										&nbsp;&nbsp;&nbsp;&nbsp;
										<input [#if op == 0]disabled="disabled"[/#if] type="radio" checked="checked" value="0" name="refund" onclick="hides();" /> 不允许
									[#else]
										<input [#if op == 0]disabled="disabled"[/#if] type="radio" checked="checked" value="1" name="refund" onclick="shows();" /> 允许
										&nbsp;&nbsp;&nbsp;&nbsp;
										<input [#if op == 0]disabled="disabled"[/#if] type="radio" value="0" name="refund" onclick="hides();" /> 不允许
									[/#if]
								</td>
							<tr>
							[#if item.refund == 0]
							<tr class="bili" style="display:none">
							[#else]
							<tr class="bili">
							[/#if]
								<td class="tl"><span class="req">*</span>退订比例：</td>
								<td class="tr">
									<select [#if op == 0]disabled="disabled"[/#if] name="proportion" style="width:260px;">
										<option value="-1">--请选择--</option>
										<option [#if item.proportion == 0.1] selected="selected" [/#if] value="0.1">--10%--</option>
										<option [#if item.proportion == 0.2] selected="selected" [/#if] value="0.2">--20%--</option>
										<option [#if item.proportion == 0.3] selected="selected" [/#if] value="0.3">--30%--</option>
										<option [#if item.proportion == 0.4] selected="selected" [/#if] value="0.4">--40%--</option>
										<option [#if item.proportion == 0.5] selected="selected" [/#if] value="0.5">--50%--</option>
										<option [#if item.proportion == 0.6] selected="selected" [/#if] value="0.6">--60%--</option>
										<option [#if item.proportion == 0.7] selected="selected" [/#if] value="0.7">--70%--</option>
										<option [#if item.proportion == 0.8] selected="selected" [/#if] value="0.8">--80%--</option>
										<option [#if item.proportion == 0.9] selected="selected" [/#if] value="0.9">--90%--</option>
										<option [#if item.proportion == 1] selected="selected" [/#if] value="1">--100%--</option>
									</select>
								</td>
								<td>
									<img src="${base}/images/materials/tips.png" onclick="javascript:showTips('.platPrice');" />
									<span style="display:none;color:red" class="platPrice">商家选择允许退款，用户支付定金之后，可选择退定金。且不影响最后成交价格。</span>
								</td>
							<tr>
							<tr>
								<td class="tl"><span class="req">*</span>开始时间：</td>
								<td class="tr"><input [#if op == 0]disabled="disabled"[/#if] name="startTime" class="text Wdate" type="text" onClick="WdatePicker()" readOnly  value='${actitvity.startTimeString}' /></td>
							<tr>
							<tr>
								<td class="tl"><span class="req">*</span>结束时间：</td>
								<td class="tr"><input [#if op == 0]disabled="disabled"[/#if] name="endTime" class="text Wdate" type="text" onClick="WdatePicker()" readOnly  value='${actitvity.endTimeString}' /></td>
							<tr>
							<tr>
								<td class="tl">&nbsp;</td>
								<td class="tr"  style="line-height:20px;">
									[#if op != 0]
									<span class="btn" onclick="verifyData(this)">提交保存</span>
									[/#if]
									<span class="btn" onclick="history.go(-1);">返回</span>
								</td> 
							<tr>
						</table>
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
    
    <script>
	
	
	function shows(){
		$(".bili").show(200);
	}
	
	function hides(){
		$(".bili").hide(200);
	}
	
	function showTips(className){
		$(className).show(200);
	}
	
	function verifyData(el){
		
		if($("input[name='activityName']").val() == ""){
			justTip("请输入活动名称 ！")
			return;
		}
		
		if($("input[name='topPrice']").val() == ""){
			justTip("请输入市场价格 ！")
			return;
		}
		
		if(!/^\d+(\.\d+)?$/.test($("input[name='topPrice']").val())){
			justTip("市场价格为数字 !")
			return;
		}
		
		if($("input[name='lowPrice']").val() == ""){
			justTip("请输入最低价格 ！")
			return;
		}
		
		if(!/^\d+(\.\d+)?$/.test($("input[name='lowPrice']").val())){
			justTip("最低价格为数字 !")
			return;
		}
		if($("input[name='numPeople']").val() == ""){
			justTip("请输入众筹人数 ！")
			return;
		}
		
		if(!/^\d+(\.\d+)?$/.test($("input[name='numPeople']").val())){
			justTip("众筹人数为数字 !")
			return;
		}
		
		if(!/^\d+(\.\d+)?$/.test($("input[name='deposit']").val())){
			justTip("商品定金为数字 !")
			return;
		}
		
		if($("input[name='lowPrice']").val() >  $("input[name='deposit']").val()){
			justTip("定金不能小于最低价格！")
			return;
		}
		
		if($("input[name='proportion']").val() == "-1" && $("input[name='refund']").val() ==1){
			justTip("请选择 退款比例！")
			return;
		}
		if($("input[name='startTime']").val() == ""){
			justTip("请输入开始时间 ！")
			return;
		}
		if($("input[name='endTime']").val() == ""){
			justTip("请输入结束时间 ！")
			return;
		}
		
		if($("input[name='startTime']").val() >= $("input[name='endTime']").val()){
			justTip("开始时间不能大于结束时间 ！")
			return;
		}
		
		
		
		
		$(el).parents("form").submit();
		
	}
	
</script>
    
</html>