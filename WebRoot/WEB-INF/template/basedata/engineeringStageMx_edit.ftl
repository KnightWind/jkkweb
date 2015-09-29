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
						<li>
							<strong><b class="red">*</b> 一级节点：</strong>
		                    <div class="select mr10 oneStage">
								<i class="new">&#xf107;</i><span>--请选择--</span>
								<p class="oneStageOption">
									<a value="">--请选择--</a>
									[#list oneStage as item]
							         <a value="${item.id}">${item.stagName}</a>
						        	[/#list]	 
								</p>
								<input type="hidden">
							</div>		    
		                </li>
		                <li>
							<strong><b class="red">*</b> 二级节点：</strong>
		                    <div class="select twoStage mr10">
								<i class="new">&#xf107;</i><span>--请选择--</span>
								<p>
									<a value="">--请选择--</a>
									 
								</p>
								<input type="hidden" >
							</div>		    
		                </li>
		                <li>
							<strong><b class="red">*</b> 三级节点：</strong>
		                    <div class="select threeStage mr10">
								<i class="new">&#xf107;</i><span>--请选择--</span>
								<p>
									<a value="">--请选择--</a>
									 
								</p>
								<input type="hidden" name="pid" class="pid">
							</div>		    
		                </li>
		                
						<li><strong><b class="red">*</b> 验收节点：</strong><input type="text" class="text mxDesc" name="mxDesc"  id=""></li>
						<li><strong><b class="red">*</b> 排序号：</strong><input type="text" class="text ordrBy" name="ordrBy"  id=""></li>
						<li class="auto">
							<strong>验收标准：</strong>
							<textarea name="checkRule" class="checkRule"></textarea>
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
	       $('.oneStage p a').unbind('click').bind('click',function(){
		      var pid=$(this).attr('value'),temp=[];
		      $(".sHelp span").html('--请选择--');
		      $.post("${base}/engineeringStage/stageChile.do",{pid:pid},function(dataR){
		           if(dataR.length>0){
		              for(var i=0;i<dataR.length;i++){
		                  temp[i]='<a value="'+dataR[i].id+'">'+dataR[i].stagName+'</a>';
		              }
		              $(".twoStage p").html(temp.join('')); 
		           }
		      });
	       });
	       
	       $('#send').delegate('.twoStage p a','click',function(){
		       var pid=$(this).attr('value'),temp=[];
			      $(".sHelp span").html('--请选择--');
			      $.post("${base}/engineeringStage/stageChile.do",{pid:pid},function(dataR){
			           if(dataR.length>0){
			              for(var i=0;i<dataR.length;i++){
			                  temp[i]='<a value="'+dataR[i].id+'">'+dataR[i].stagName+'</a>';
			              }
			              $(".threeStage p").html(temp.join('')); 
			           }
			      });
	       });
	       
	       
	       $(".saveOne").click(function(){
	             var pid=$(".pid").val();
	             var mxDesc=$(".mxDesc").val();
	             var ordrBy=$(".ordrBy").val();
	             var checkRule=$(".checkRule").val();
	              $.post("${base}/engineeringStageMx/saveOne.do",{pid:pid,mxDesc:mxDesc,ordrBy:ordrBy,checkRule:checkRule},function(rel){
	                 justTip(rel.msg);
	                if(rel.ret==0){
	                   var TF=confirm("是否要创建更多?");
	                   if(TF){
	                      location.reload();
	                   }else{
	                     history.go(-1);
	                     location.reload();
	                   }
	                }
	             });
	       });
	  });
</script>
</html>