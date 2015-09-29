<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<title>商家管理</title>
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
		p{
			line-height:30px;
		}
		#panl{
			margin:20px;
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
					<span id="sel1" data-role="子账号列表">子账号列表</span>
				</h2>
				<div class="conBox">
				<!--内容放这里 start-->
				<div class="tools white border mb10 sp10 clr">
					<div id="panl">
						<form id="search" action="${base}/supplier/in/updateSupplierUser.xhtml" method="post">
							<input type="hidden" id="id" name="id" value="${view.id}"/>
							<ul>
								<li>
									<strong>登录账号:</strong>
									<input type="text" class="text" name="username" value="${view.username}"/>
									<font color="red">${msg}</font>
									<font>（推荐使用手机号码作为登陆账号！）</font>
								</li>
								<li>
									<strong>密码:</strong>
									<input type="password" class="text" name="userpwd" value="${view.userpwd}"/>
								</li>
								<li>
									<strong>授权:</strong>
									<p>
										<input type="checkbox" name="isAudit" [#if view.isAudit=0]checked="checked" value="0"[#else] value="1" [/#if]>审核员 &nbsp;
										<input type="checkbox" name="isAdmin" [#if view.isAdmin=0]checked="checked" value="0"[#else] value="1" [/#if] >管理员 &nbsp;
										<input type="checkbox" name="isMerchandiser" [#if view.isMerchandiser=0] checked="checked" value="0" [#else] value="1" [/#if]>接单员 &nbsp;
										<input type="checkbox" name="isDesigner" [#if view.isDesigner=0] checked="checked" value="0" [#else] value="1" [/#if]>设计师 &nbsp;
									</p>
								</li>	
								<li>
									<strong> &nbsp;</strong>
									<span onclick="vaible(this)"  class="btn" value="保存">提交</span>&nbsp;
									<span class="btn search" onclick="history.back()">返回</span>
								</li>
							</ul>
						</form>
					</div>
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
			$('input[name="isAudit"]').bind('click',function(){
			 var flag=$(this).prop('checked');
			 if(flag){$(this).val(0);}else{$(this).val(1);}
			});
			$('input[name="isAdmin"]').bind('click',function(){
			 var flag=$(this).prop('checked');
			 if(flag){$(this).val(0);}else{$(this).val(1);}
			});
			$('input[name="isMerchandiser"]').bind('click',function(){
			 var flag=$(this).prop('checked');
			 if(flag){$(this).val(0);}else{$(this).val(1);}
			});
			$('input[name="isDesigner"]').bind('click',function(){
			 var flag=$(this).prop('checked');
			 if(flag){$(this).val(0);}else{$(this).val(1);}
			});
			
		});
		
		
		function vaible(el){
			
			if($("input[name='username']").val() == ""){
				justTip('请输入账号！')
				return;
			}
			
			if($("input[name='userpwd']").val() == ""){
				justTip('请输入密码！')
				return;
			}
			
			var flag = false;
			$("input[type='checkbox']").each(function(i){
				if($(this).val() == 0){
					flag = true
				}
			});
			
			if(!flag){
				justTip('至少选择一个权限！')
				return;
			}
			
			var isHas = false;
			var msg = '';
			var id = $("#id").val();
			if($("#id").val() == ""){
				id = 0;
			}
			$.get('${base}/supplier/in/checkUserName.do?id='+id,{'name':$("input[name='username']").val()},function(data){
				if(data.ret == 1){
					isHas = true;
					justTip(data.msg);
					return;
				}else{
					$(el).parents('form').submit();
				}
			});
		}
	</script>
	</body>
</html>