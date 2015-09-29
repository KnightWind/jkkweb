<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<title>接单条件设置</title>
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
		.dataShow{ widtd:48%; height:500px; float:left; padding-top:20px;}
		.dataShow p{height:40px;}
		.dataShow table{ margin-bottom:20px;}
		.dataShow table td{ border-right:1px solid #ccc;}
		.dataSp{ margin-right:4%;}
		tr{
			padding-top:10px;
		}
		td{height:55px;font-size:14px;}
		.td_left{text-align:right;font-size:15px;font-weight:bold;}
		#table_detail{mangin:50px;}
		.requi{color:red}
		#qu{
			width:500px;
			margin-left:80px;
		}
		#qu ul li{
			width:120px;
			float:left;
		}
		</style>
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
					<span id="sel1" data-role="接单条件设置">接单条件设置</span>
				</h2>
				<div class="conBox">
			<!--内容放这里 start-->
			<div class="tools white border mb10 sp10 clr">
				<form action="${base}/condition/save.xhtml" method="post">
					<input type="hidden" name="id" value="${cond.id}" />
					<table>
						 <tr>
	                    	<td class="td_left"><span class="requi">*</span>预算大小：</td>
	                    	<td>
	                    		大于或等于<input name="xzYs" value="${cond.xzYs}" class="text"/> (单位:元)
	                        </td>
	                    </tr>
	                    <tr>
	                    	<td class="td_left"><span class="requi">*</span>装修面积：</td>
	                    	<td>大于或等于<input name="xzMj" value="${cond.xzMj}" class="text"/> (单位:m²)
	                        <span class="error"></span></td>
	                    </tr>
	                  <!--  <tr>
	                    	<td class="td_left"><span class="requi">*</span>装修方式：</td>
	                    	<td>
	                    		[#if cond.xzZxfs == 2]
	                    			<input name="xzZxfs" checked='checked' value="2" type="radio" />全包&nbsp;&nbsp;&nbsp;
	                    			<input name="xzZxfs" value="1" type="radio" />半包
	                    		[#else]
	                    			<input name="xzZxfs" value="2" type="radio" />全包&nbsp;&nbsp;&nbsp;
	                    			<input name="xzZxfs" checked='checked' value="1" type="radio" />半包
	                    		[/#if]
	                        </td>
	                    </tr> -->
	                    <input name="xzZxfs" value="1" type="hidden" />
						<tr>
							<td class="td_left"><span class="requi">*</span>接单区域：</td>
							<td id="">
								区域：
								<select id="changeCity">
				        			<option value="-1"> --请选择-- </option>
				        			[#list RegionList as l]
				        					[#if l.regionid == pRegionId]
				                            	<option value="${l.regionid}" selected="selected">${l.regionname}</option>
				                            [#else]
				                            	<option value="${l.regionid}">${l.regionname}</option>
				                            [/#if]
				                    [/#list]
				        		</select>
				        		城市：
				        		[#if cRegionId != 0]
				        			<select id="shi">
					        			[#list clist as l]
					        					[#if l.regionid == cRegionId]
					                            	<option value="${l.regionid}" selected="selected">${l.regionname}</option>
					                            [#else]
					                            	<option value="${l.regionid}">${l.regionname}</option>
					                            [/#if]
					                    [/#list]
					                </select>
				        		[#else]
					        		<select id="shi">
					        			<option value="-1"> --请选择-- </option>
					        			
					        		</select>
					        	[/#if]
				        		&nbsp;&nbsp;&nbsp;&nbsp;
				        		[#if cRegionId != 0]
				        			<input type="checkbox" class="ckAll" id="ckAll" checked="checked" value="0" />全选
				        		[#else]
				        			<input type="checkbox" class="ckAll" id="ckAll" value="0" />全选
				        		[/#if]
							</td>
						</tr>
					</table>
					
					<div id="qu" style="">
						<ul>
							[#list scces as l]
                            	[#if l.regionId == cRegionId || l.regionId == pRegionId]
    	                        	<input type="hidden" id="re" name="ids" value="${l.regionId}" />
                            	[#else]
	                            	 <li>
										<input class="ck" checked="checked" name="ids" value="${l.regionId}" type="checkbox" id="${l.regionId}" /> 
										${l.regionName}
									</li>
			        			[/#if]
                            [/#list]
						</ul>
					</div>	
					<div style="margin:50px 80px ;">
						<span class="btn" id="save">提交</span>
					</div>			
				</form>
				
			</div>
		[#include "/common/pagination.ftl"]
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
			
<script>
$(function(){
	$("body").delegate('#changeCity','change',function(){
		$("#ckAll").prop("checked",false);
		if(val <= 0)
			return;
		var val = $(this).val();
		var url = "${base}/region/getList.do";
		$.get(url,{"parentId":val},function(data){
			if(data.list.length <= 0){
				return;
			}
			$("#shi option").remove();
			$("#qu ul").children().remove();
			$("#shi").append('<option value="-1"> --请选择-- </option>');
			for(var i = 0;i < data.list.length;i++){
				var html = '<option value="'+data.list[i].regionid+'">'+data.list[i].regionname+'</option>';
				$("#shi").append(html);
			}
		});
	});
	
	$("body").delegate('#shi','change',function(){
		var val = $(this).val();
		var pval = $("#changeCity").val();
		var url = "${base}/region/getList.do";
		if(val <= 0)
			return;
		$("#qu ul").children().remove();
		var str1 = '<input type="hidden" name="ids" id="re" value="'+val+'" />';
		var str2 = '<input type="hidden" name="ids" id="re" value="'+pval+'" />';
		$("#qu ul").append(str1);
		$("#qu ul").append(str2);
		
		$("#ckAll").prop("checked",false);
		
		$.get(url,{"parentId":val},function(data){
			if(data.list.length <= 0){
				return;
			}
			for(var i = 0;i < data.list.length;i++){
				var html = '<li><input class="ck" name="ids" value="'+data.list[i].regionid+'" type="checkbox" id="'+data.list[i].regionid+'" /> '+ data.list[i].regionname +'</li>'
				$("#qu ul").append(html);
			}
		});
	});
	
	$(".ckAll").click(function(){
		var size = $(".ck").length;
		if(size == 0){
			var val = $("#shi").val();
			if(val == -1){
				justTip("请选择城市！")
				return;
			}
		}
		var flag=$(this).prop("checked");
		if(flag){
			$(".ck").prop("checked",true);
		}else{
			$(".ck").prop("checked",false);
		}
	});
	
	$("#save").click(function(){
		
		if($('input[name="xzYs"]').val() == ''){
			justTip("预算大小不能为空！");
			return;
		}
		if(!/^\d+$/.test($("input[name='xzYs']").val())){
			justTip("预算大小为数字 !")
			return;
		}
		if($('input[name="xzMj"]').val() == ''){
			justTip("装修面积不能为空！");
			return;
		}
		if(!/^\d+$/.test($("input[name='xzMj']").val())){
			justTip("装修面积为数字 !")
			return;
		}
		var size = $(".ck").length;
		if(size > 0){
			$(this).parents("form").submit();
		}else{
			justTip("至少选择接单一个地区！");
		}
	});
	
	
});	
</script>
</body>
</html>