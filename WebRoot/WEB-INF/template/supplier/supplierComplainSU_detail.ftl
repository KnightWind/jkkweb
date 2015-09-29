<!DOCTYPE HTML>
<html>
	<head>
<meta charset="utf-8">
<title>投诉管理</title>
	[#include "/common/res.ftl"]
	<link rel="stylesheet" type="text/css" href="${base}/styles/complain.css">
		<style>
		.logo{height:82px; background:#fff; border-bottom:2px solid #1D9DA6;}
		.logo p{padding-top:20px;}
		.logo p a{color:#333; font-size:14px;}
		.logo p a i{ font-size:16px;}
		.conWrap h2.hd{ border-color:#ccc;}
		.dataShow{ width:48%; height:500px; float:left; padding-top:20px;}
		.dataShow p{height:40px;}
		.dataShow table{ margin-bottom:20px;}
		.dataShow table td{ border-right:1px solid #ccc;}
		.dataSp{ margin-right:4%;}
		.fieldStyle{
			padding:20px;
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
					<span id="sel1" data-role="首页">投诉</span>
				</h2>
				<div class="conBox">
		    	<!--内容放这里 start-->
							
			     <div class="complaint">	
	                <input type="hidden" class="complaintId" value="${complaint.id}"/>	
			   		   <div>
							<span class="sp"><strong>投诉单号:</strong>${complaint.id}</span>
							<span class="sp col1"><strong>状态:</strong>${complaint.statusVal}</span>
					  </div>
					  <div>
							<span class="sp"><strong>业主:</strong>${complaint.mnickName}</span>
							<span class="sp col1"><strong>电话:</strong>${complaint.mmobile}</span>						
					   </div>
					   <div>
							<span class="sp col1"><strong>联系电话:</strong>${complaint.smobile}</span>						
						</div>		
						<div>
						     <span><strong>投诉内容:</strong>${complaint.content}</span>
						</div>		
				    </div>	
			
			[#if pagination.dataList]
				<div class="detailContainer">
				  [#list pagination.dataList as item]
				    <div class="contentDiv">
				    
					    <span>${item.content}&nbsp;</span>
					    <p>
						    ${item.createTimeHandle}&nbsp;
						    ${item.typeName}&nbsp;
						    ${item.userName}
					    </p>
				    </div>			    
				  [/#list]
				</div> 
			[/#if]
			
	    	[#include "/common/pagination.ftl"]
				 [#if complaint.status==2]				
					<div>								
				    	<textarea rows="5" cols="100" class="content"></textarea>
				    	<div>
				    	 <span class="btn fllow">回复</span>
				    	 <span class="btn search" onclick="history.back()" style="font-size:12px;">返回</span>
				    	</div>			    	 
		            </div>	
		            [#else]
		             <span class="btn search" onclick="history.back()" style="font-size:12px;">返回</span>
	           [/#if]
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
		</body>
		<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
		<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
		
<script>
  $(function(){
     $(".fllow").click(function(){
      var content=$(".content").val();
      var id=$(".complaintId").val();
      $.post("saveSU.do",{content:content,cid:id},function(res){		
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