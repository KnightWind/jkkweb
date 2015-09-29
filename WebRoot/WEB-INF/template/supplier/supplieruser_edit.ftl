<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>编辑商家子帐号</title>
[#include "/common/res.ftl"]
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="${base}/scripts/form.js"></script>
</head>

<body>
<!-- header -->
[#include "/common/head.ftl"]
<!-- wrap -->
<div class="wrap index bc clr">
	<div class="conWrap fl">
		[#include "/common/nav.ftl"]
		<div class="conBox">
			 【商家子账号操作】
            <div class="tools white border mb10 sp10 clr">
				<form id="search" action="${base}/supplier/in/up.xhtml" method="post">
				<ul>
				[#if view!=null]
					<li>
						<strong>登录账号:</strong>
						<input type="text" class="text" name="username" value="${view.username}"/>
						<font color="red">${msg}</font>
						<font>（推荐使用手机号码作为登陆账号！）</font>
					</li>
					<li>
						<strong>绑定手机:</strong>
						<input type="text" class="text" name="mobile" maxLength="11" value="${view.mobile}"/>
					</li>
					<li>
						<strong>密码:</strong>
						<input type="password" class="text" name="userpwd" value="${view.userpwd}"/>
					</li>	
						<li>
						授权：
						<label>
						<input type="checkbox" name="isAudit" [#if view.isAudit=0]checked="checked" value="0"[#else] value="1" [/#if]>审核员
						<input type="checkbox" name="isAdmin" [#if view.isAdmin=0]checked="checked" value="0"[#else] value="1" [/#if] >管理员
						<input type="checkbox" name="isMerchandiser" [#if view.isMerchandiser=0] checked="checked" value="0" [#else] value="1" [/#if]>接单员
						<input type="checkbox" name="isDesigner" [#if view.isDesigner=0] checked="checked" value="0" [#else] value="1" [/#if]>设计师
						</label>
						</li>
					[#else]
						<li>
						<strong>登录账号:</strong>
						<input type="text" class="text" name="username" value=""/>
					</li>
					<li>
						<strong>绑定手机:</strong>
						<input type="text" class="text" maxLength="11" name="mobile" value=""/>
					</li>
					<li>
						<strong>密码:</strong>
						<input type="password" class="text" name="userpwd" value=""/>
					</li>				
						<li>
						授权：
						<label>
						<input type="checkbox" name="isAudit" [#if view.isAudit=0]checked="checked" value="0"[#else] value="1" [/#if]>审核员
						<input type="checkbox" name="isAdmin" [#if view.isAdmin=0]checked="checked" value="0"[#else] value="1" [/#if] >管理员
						<input type="checkbox" name="isMerchandiser" [#if view.isMerchandiser=0] checked="checked" value="0" [#else] value="1" [/#if]>接单员
						<input type="checkbox" name="isDesigner" [#if view.isDesigner=0] checked="checked" value="0" [#else] value="1" [/#if]>设计师
						</label>
						</li>
					[/#if]
				
					<li>
					<input type="hidden" name="id" id="id" value="${view.id}"/>
					<input type="hidden" name="mid" value="${mid}"/>
					<input type="hidden" name="pid" value="${pid}"/>
					<input type="hidden" name="bid" value="${bid}"/>
					<input type="hidden" name="spId" value="${bid}"/>
						<span onclick="vaible(this)" style="margin-left:110px;" class="btn" value="保存">提交</span>&nbsp;
						<span class="btn search" onclick="history.back()">返回</span>
					</li>
				</ul>
				</form>
			</div>
		
		[#include "/common/pagination.ftl"]
	</div>
	</div>
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]
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
	

	
	var isHas = false;
	var msg = '';
	var id = $("#id").val();
	if($("#id").val() == ""){
		id = 0;
	}
	var name=$("input[name='username']").val();
	var mobile=$("input[name='mobile']").val();
	$.get('${base}/supplier/in/checkInfos.do?id='+id,{'name':name,'mobile':mobile},function(data){
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