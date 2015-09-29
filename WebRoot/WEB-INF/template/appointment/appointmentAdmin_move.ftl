<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>预约转移</title>
[#include "/common/res.ftl"]
<link rel="stylesheet" type="text/css" href="/jkkpweb/styles/crm-manage.css" />
</head>
<body>
<!-- header -->
[#include "/common/head.ftl"]

<!-- wrap -->
<div class="wrap index bc clr">
	<div class="conWrap fl">
		[#include "/common/nav.ftl"]
		<div class="conBox">
		   <div class="merchandiser">
			   <div class='nowMerchandiser'>
				 	  当前跟单员:<input type="hidden" id="adminId" value="${adminId}"/>
				      [#list adminList as item]
				         [#if item.id==adminId]
		                	<span>编号:${item.id}</span><span>昵称:${item.nickname}</span>
				         [/#if]
				      [/#list]
			     </div> 
			     <div class='changeMerchandiser'>
				   	   <p>转移跟单员:</p>
				       [#list adminList as item]
				         [#if item.id!=adminId]
				         <ul>
				         	 <li> 
								   <input type="radio" name="GD" value="${item.id}" />
								   <span>编号:</span>
								   <span class='marginR'>${item.id}</span>
								   <span>昵称:</span>
								   <span class='marginR'>${item.nickname}</span>	
								   <span>当前预约数量:</span>
								   <span class='marginR'>${item.status0}</span>
		                     </li>  
				         </ul>       
				         [/#if]
				      [/#list]

			      </div>
		      <span class="moveSubmitBtn btn">提交保存</span>
		   </div>
	    </div>
	</div>
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]
</body>
<script type="text/javascript">
$(function(){
    $(".moveSubmitBtn").click(function(){
       var newId=$('input[name="GD"]:checked').val();
       var oldId=$("#adminId").val();
       $.post("${base}/appointment/moveAppointment.do",{newId:newId,oldId:oldId},function(res){
              if(res.ret == 1){}else{justTip(res.msg);}
       });
    });
});
</script>
</html>