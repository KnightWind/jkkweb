<!DOCTYPE HTML>
<html>
<head>
<title>投诉单跟进</title>
<meta charset="utf-8">
[#include "/common/res.ftl"]
</head>
<style>
.col1{
margin-left:20px;
}
.detailContainer{
width:400px;
border:1px solid #cccccc;
border-radius:15px;
margin:10px 0px;
}
.contentDiv{
width:300px;
height:80px;
border:1px solid #cccccc;
border-radius:15px;
margin-top:10px;
}
</style>
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
						<span class="sp"><strong>投诉单号:${complaint.id}</strong></span>
						<span class="sp col1"><strong>状态:${complaint.statusVal}</strong></span>
						<span class="sp col1"><strong>优先级:${complaint.levelVal}</strong></span>	
				  </div>
					<div>
						<span class="sp"><strong>投诉人:${complaint.userName}</strong></span>
						<span class="sp col1"><strong>电话:${complaint.mobile}</strong></span>						
					</div>
					<div>
						<span class="sp"><strong>被投诉人名称:${complaint.spName}</strong></span>
						<span class="sp col1"><strong>联系电话:${complaint.smobile}</strong></span>						
					</div>
					<div>
						<span class="sp"><strong>来源:${complaint.sourceVal}</strong></span>
						<span class="sp col1"><strong>来源单号:${complaint.appId}</strong></span>						
					</div>							
			    </div>	
			<div>
							
			<div class="detailContainer">
			  [#list complaintsDetails as item]
			    <div class="contentDiv">
				    ${item.content}&nbsp;
				    ${item.createTimeHandle}&nbsp;
				    ${item.typeName}&nbsp;
				    ${item.userName}
			    </div>			    
			  [/#list]
			</div>
					
					
		   [#if complaint.status==2]				
				<div>								
			    	<textarea rows="5" cols="100" class="content"></textarea>
			    	<div><span class="btn fllow">提交跟进</span></div>
	            </div>	
           [/#if]
           
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
      $.post("save.do",{content:content,cid:id},function(res){		
			justTip(res.msg);
          });
     });
  });
</script>
</html>