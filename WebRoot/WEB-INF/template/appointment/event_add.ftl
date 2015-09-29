<!DOCTYPE HTML>
<html>
<head>
<title>事件录入</title>
<meta charset="utf-8">
[#include "/common/res.ftl"]
<link rel="stylesheet" type="text/css" href="${base}/styles/event.css">
</head>

<body>
<!-- header -->
[#include "/common/head.ftl"]

<!-- wrap -->
<div class="wrap index bc clr">
	<div class="conWrap fl">
		[#include "/common/nav.ftl"]
		<div class="conBox">
			<div class="event">
					<form id="send">
					<ul class="border white">
						<li>
							<strong><b class="red">*</b> 事件类型：</strong>
		                    <div class="select mr10">
								<i class="new">&#xf107;</i><span>--请选择--</span>
								<p>
									<a value="">--请选择--</a>
									[#list eventType as item]
							         <a value="${item.id}">${item.type}</a>
						        	[/#list]	 
								</p>
								<input type="hidden" name="type">
							</div>
		                    <span class="btn add">添加</span>
		                </li>
						<li><strong><b class="red">*</b> 联系人：</strong><input type="text" class="text" name="name" id=""></li>
						<li><strong><b class="red">*</b> 联系电话：</strong><input type="text" class="text" name="mobile" id=""></li>
						<li class="auto">
							<strong><b class="red">*</b> 事件内容：</strong>
							<textarea name="content"></textarea>
						</li>
						<li class="btn"><strong>&nbsp;</strong><span class="btn saveOne">提交保存</span></li>
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
<script type="text/javascript" src="${base}/scripts/form.js"></script>
<script type="text/html" id="eventShow">
<div class="ui-dialog eventShow">
	<h2><span class="close"></span>添加事件类型</h2>
	<form id="send" action="${base}/appointment/eventType/save.do">
	<ul>
		<li><strong><b class="red">*</b> 事件类型：</strong> <input type="text" class="text" name="type" placeholder="事件类型"></li>
		<li class="btn"><span class="btn save" rel="add">提 交</span></li>
	</ul>
	</form>
</div>	
</script>



<script>
   $(function(){
       $(".saveOne").click(function(){
          var data=$("#send").serialize();
          $.post("${base}/appointment/event/save.do",data,function(rel){
               if(rel.ret==0){
                   justTip(rel.msg);
               }else{
                  justTip(rel.msg);
               }
          });
       });      
   });
</script>

<script>
$(function(){
	$('.add').bind('click',function(){
		addMask();
        $('body').append($('#eventShow').html());
        center($('.eventShow'));   
	});
	$('body').delegate('span.save','click',function(){
		var value=$('form#send').serialize();
	});
})
</script>
</html>