<!DOCTYPE HTML>
<html>
<head>
<title>红包列表管理</title>
<meta charset="utf-8">
[#include "/common/res.ftl"]
	<script type="text/javascript" src="${base}/scripts/form.js"></script>
	<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
	<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
<style>
	.add{margin-bottom:10px;float:right}
	.btn{width:100px;text-align:center;margin-left:60px;}
</style>
</head>

</head>
<body>
<!-- header -->
[#include "/common/head.ftl"]
<!-- wrap -->
	<div class="wrap index bc clr">
		<div class="conWrap fl">
			[#include "/common/nav.ftl"]
				<div class="conBox">	
					<div class="tools white border mb10 sp10 clr">
						<form id="search" action="${base}/admin/redPackage/save.xhtml">
							<input type="hidden" name="id" value="${pack.id}">
							<input type="hidden" name="mid" value="${mid}">
							<input type="hidden" name="pid" value="${pid}">
							<input type="hidden" name="token" value="${token}">
							<ul>					
								<li>
									<span class="sp"><strong>红包名称：</strong><input type="text" class="text" name="name" value="${pack.name}"></span>
								</li>
								<li>
									<span class="sp"><strong>红包金额：</strong><input type="text" class="text" name="price" value="${pack.price}"></span>
								</li>
								<li>
									<span class="sp"><strong>使用范围：</strong>
										<input onclick="isDisply(false)"  [#if pack.platform == 0 || pack == null]checked="checked"[/#if] type="radio" name="platform" value="0">全平台 
										<input onclick="isDisply(true)" [#if pack.platform == 1]checked="checked"[/#if] type="radio" name="platform" value="1">家装 
										<input onclick="isDisply(true)" [#if pack.platform == 2]checked="checked"[/#if] type="radio" name="platform" value="2">建材
									</span>
								</li>
								<li class="isDisply" [#if pack.platform == 0 || pack == null]style="display:none"[/#if]>
									<span class="sp"><strong>适应品类：</strong>
										<!--<select name="cid" style="width:140px;">-->
											[#list cate as c]
												<input class="ids" name="ids" [#if pack.platform != 0 && ids!=null && ids.contains(c.id)]checked="checked"[/#if] type="checkbox" value="${c.id}"/>${c.name} &nbsp;
											[/#list]
										<!--</select>-->
									</span>
								</li>
								<li>
									<span class="sp"><strong>满减规则：</strong>满 <input size="5" type="text" class="text" name="startMoney" value="${pack.startMoney}"> 即可使用</span>
								</li>
								<li>
									<span class="sp"><strong>每个账号限：</strong>领 <input size="5" type="text" class="text" name="limitNum" value="${pack.limitNum}"> 个</span>
								</li>
								<li>
									<span class="sp"><strong>是否支持拆分：</strong>
										<input [#if pack.isSplit == 1 || pack == null]checked="checked"[/#if] type="radio" name="isSplit" value="1">是 
										<input [#if pack.isSplit == 0]checked="checked"[/#if] type="radio" name="isSplit" value="0">否 
									</span>
								</li>
								<li>
									<span class="sp"><strong>支付使用条件：</strong>
										<input [#if pack.useCondition == 0 || pack == null]checked="checked"[/#if] type="radio" name="useCondition" value="0">全款
										<input [#if pack.useCondition == 1]checked="checked"[/#if] type="radio" name="useCondition" value="1">定金 
									</span>
								</li>
								<li>
									<span class="sp"><strong>开始使用时间：</strong>
									<input type="text" class="text Wdate" name="beginTime" value="${pack.beginTimeString}" onClick="WdatePicker()" readOnly>
								</li>
								<li>
									<span class="sp"><strong>结束使用时间：</strong>
									<input type="text" class="text Wdate" name="endTime" value="${pack.endTimeString}" onClick="WdatePicker()" readOnly>
								</li>
								<li>
									<span class="btn" onclick="submitData(this)">确定</span>
								</li>
							</ul>
						</form>
					</div>	
			  </div>
		</div>
		[#include "/common/menu.ftl"]
	</div>
<!-- footer -->
 [#include "/common/foot.ftl"]
</body>
<script>

	function submitData(el){
		if($('input[name = "name"]').val() == ''){
			justTip("请输入红包名称！");
			return;
		}
		if($('input[name = "price"]').val() == ''){
			justTip("请输入红包金额！");
			return;
		}
		if(!/^\d+(\.\d+)?$/.test($('input[name = "price"]').val())){
			justTip("红包金额为数字！");
			return;
		}
		if($('input[name = "startMoney"]').val() == ''){
			justTip("请输入满减规则！");
			return;
		}
		if(!/^\d+(\.\d+)?$/.test($('input[name = "startMoney"]').val())){
			justTip("满减规则为数字！");
			return;
		}
		if($('input[name = "limitNum"]').val() == ''){
			justTip("请输入每个账号限领人数！");
			return;
		}
		if(!/^\d+(\.\d+)?$/.test($('input[name = "limitNum"]').val())){
			justTip("每个账号限领人数为数字！");
			return;
		}
		if($('input[name = "beginTime"]').val() == ''){
			justTip("请输入开始时间！");
			return;
		}
		if($('input[name = "endTime"]').val() == ''){
			justTip("请输入结束时间！");
			return;
		}
		if($('input[name = "beginTime"]').val() >= $('input[name = "endTime"]').val()){
			justTip("开始时间不能大于结束时间！");
			return;
		}
		var flag = false;
		$(".ids").each(function(){
			if($(this).prop("checked")){
				flag = true;
			}
		});
		
		if(!flag){
			justTip("请至少选择一个适用品类！");
			return;
		}
		
		$(el).parents("form").submit();
	}
	
	function isDisply(flag){
		if(flag){
			$(".isDisply").show(200);
		}else{
			$(".isDisply").hide(200);
		}
	}
	
	
</script>

</html>