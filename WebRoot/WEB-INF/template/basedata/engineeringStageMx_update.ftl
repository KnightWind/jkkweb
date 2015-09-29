<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>编辑验收标准</title>
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
					    <input name="id" class="id" value="${engineeringStageMx.id}" type="hidden"/>
						<li><strong><b class="red">*</b> 验收节点：</strong><input type="text" class="text mxDesc" name="mxDesc"  value="${engineeringStageMx.mxDesc}" id=""></li>
						<li><strong><b class="red">*</b> 排序号：</strong><input type="text" class="text ordrBy" value="${engineeringStageMx.ordrBy}" name="ordrBy"  id=""></li>
						<li class="auto">
							<strong>验收标准：</strong>
							<textarea name="checkRule" class="checkRule">${engineeringStageMx.checkRule}</textarea>
						</li>	
						<li>
							<strong>&nbsp;</strong><span class="btn saveOne">保存</span>
							<span class="btn search" onclick="history.back()">返回</span>
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
	   $(function(){
	       $(".saveOne").click(function(){
	             var id=$(".id").val();
	             var mxDesc=$(".mxDesc").val();
	             var ordrBy=$(".ordrBy").val();
	             var checkRule=$(".checkRule").val();
	              $.post("${base}/engineeringStageMx/saveOne.do",{id:id,mxDesc:mxDesc,ordrBy:ordrBy,checkRule:checkRule},function(rel){
	                if(rel.ret==0){
	                     history.go(-1);
	                     location.reload();
	                }
	           });
	       });
	  });
</script>
</html>