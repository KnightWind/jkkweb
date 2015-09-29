<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>创建帮助中心</title>
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
			<form  id="form">
				<div class="event">
					<ul class="border white">
						<li>
						<strong> 目录：</strong>
						<div class="select pHelp mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p>
								[#list cateParentList as item]
						         <a value="${item.id}">${item.name}</a>
					        	[/#list]	 
							</p>
							<input type="hidden" name="pHelp">
						</div>
					   <div class="select sHelp mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p></p>
							<input type="hidden" name="cateId">
						</div>
					   </li>
					   <li><strong>标题：</strong><input type="text" name="title" class="text"/></li>
					   <li class="auto"><strong>内容：</strong><textarea name="content"></textarea></li>
					   <li class="btn"><strong>&nbsp;</strong><input type="button" class="submit" value="保 存"/></li>
				 </div>
			 </form>
	    </div>
	</div>
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]
</body>

<script>
   $(function(){
        $(".submit").click(function(){
             var data=$("#form").serialize();
             $.post("${base}/help/save.do",data,function(rel){
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
  $('.pHelp p a').unbind('click').bind('click',function(){
      var pid=$(this).attr('value'),temp=[];
      $(".sHelp span").html('--请选择--');
      $.post("${base}/helpCategory/Subclass.do",{pid:pid},function(dataR){
           if(dataR.length>0){
              for(var i=0;i<dataR.length;i++){
                  temp[i]='<a value="'+dataR[i].id+'">'+dataR[i].name+'</a>';
              }
              $(".sHelp p").html(temp.join('')); 
           }
      });
  });
});
</script>
</html>