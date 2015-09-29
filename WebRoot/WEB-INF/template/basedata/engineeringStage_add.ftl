<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>创建三级节点</title>
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
							<strong><b class="red">*</b> 一级节点：</strong>
		                    <div class="select mr10">
								<i class="new">&#xf107;</i><span>--请选择--</span>
								<p>
									<a value="">--请选择--</a>
									[#list pEng as item]
							         <a value="${item.id}">${item.stagName}</a>
						        	[/#list]	 
								</p>
								<input type="hidden" name="ppid">
							</div>		    
		                </li>
		                <li>
							<strong><b class="red">*</b> 二级节点：</strong>
		                    <div class="select mr10">
								<i class="new">&#xf107;</i><span>--请选择--</span>
								<p>
									<a value="">--请选择--</a>
									[#list pEng as item]
							         <a value="${item.id}">${item.stagName}</a>
						        	[/#list]	 
								</p>
								<input type="hidden" name="pid">
							</div>		    
		                </li>
						<li><strong><b class="red">*</b> 三级节点：</strong><input type="text" class="text" name="stagName" id=""></li>
						<li><strong><b class="red">*</b> 排序号：</strong><input type="text" class="text" name="ordrBy" id=""></li>
						<li class="auto">
							<strong>备注：</strong>
							<textarea name="remark"></textarea>
						</li>
						<li>
							<strong>&nbsp;</strong><span class="btn saveOne">保存</span>
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
</body>

<script>
   $(function(){
       $(".saveOne").click(function(){
          var data=$("#send").serialize();
          $.post("${base}/engineeringStage/saveChildren.do",data,function(rel){
               if(rel.ret==0){
                   justTip(rel.msg);
               }else{
                  justTip(rel.msg);
               }
          });
       });      
   });
</script>

</html>