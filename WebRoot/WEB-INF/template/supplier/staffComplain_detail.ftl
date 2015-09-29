<!DOCTYPE HTML>
<html>
<head>
<title>设计师-业主投诉跟进</title>
<meta charset="utf-8">
[#include "/common/res.ftl"]
<link rel="stylesheet" type="text/css" href="${base}/styles/complain.css">
<style>

</style>
</head>
<body>
<!-- header -->
[#include "/common/head.ftl"]
<!-- wrap -->
<div class="wrap index bc clr">
	<div class="conWrap fl">
		[#include "/common/nav.ftl"]
		<div class="conBox">		
		   <div class="complaint">	
                <input type="hidden" class="complaintId" value="${complaint.id}"/>	
		   		   <div>
						<span class="sp"><strong>投诉单号:</strong>${complaint.id}</span>
						<span class="sp col1"><strong>状态:</strong>${complaint.statusVal}</span>
						<span class="sp col1"><strong>优先级:</strong>${complaint.levelVal}</span>	
				  </div>
				  <div>
						<span class="sp"><strong>业主:</strong>${complaint.mnickName}</span>
						<span class="sp col1"><strong>电话:</strong>${complaint.mmobile}</span>						
				   </div>
				   <div>
						<span class="sp"><strong>设计师:</strong>${complaint.spName}</span>												
					</div>		
					<div>
					     <span><strong>投诉内容:</strong>${complaint.content}</span>
					</div>			
			    </div>	
			<div>
							
		 [#if pagination.dataList]
			<div class="detailContainer">
			  [#list pagination.dataList as item]
			    <div class="contentDiv">
				    <span>${item.content};</span>
				    <p>
					    ${item.createTimeHandle}&nbsp;
					    ${item.typeName}&nbsp;
					    ${item.userName}
				    </p>
			    </div>			    
			  [/#list]
			</div>
		 [/#if]	
					
		   [#if complaint.status==2]				
				<div>								
			    	<textarea rows="5" cols="100" class="content"></textarea>
			    	<div>
				    	<span class="btn fllow">提交跟进</span>
				    	<span class="btn search" onclick="history.back()" style="font-size:12px;">返回</span>
			    	</div>
	            </div>	
	            [#else]
	               <span class="btn search" onclick="history.back()" style="font-size:12px;">返回</span>
           [/#if]          
	    	[#include "/common/pagination.ftl"]	
	      </div>
	    </div>
	</div>
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]
</body>

<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
<script>
  $(function(){
     $(".fllow").click(function(){
      var content=$(".content").val();
      var id=$(".complaintId").val();
      $.post("save.do",{content:content,scId:id},function(res){		
            if(res.ret==0){
              justTip(res.msg);
              location.reload();
            }else{
			  justTip(res.msg);
			}
          });
     });
  });
</script>
</html>