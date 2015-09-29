<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<title>分店管理</title>
		[#include "/common/res.ftl"]	
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
		</style>
	<link rel="stylesheet" type="text/css" href="${base}/styles/admin-list.css">
	</head>
	<body>
	<!-- header -->
		[#include "/materials/common/head.ftl"]
		<!-- wrap -->
		<div class="wrap index bc clr">
			<div class="conWrap fl">
				 <div class="conBox">
					<!--内容放这里 start-->		
					<span class="btn addnew" style="float:right;margin-bottom:5px;margin-right:10px;">
					   <a href="${base}/material/supplierBranch/edit.xhtml">添加分店</a>
					</span>		
					<table class="format">
								<thead>
									<th width="10%">分店名称</th>
									<th width="30%">分店服务电话</th>
									<th width="40%">地址</th>
									<th width="10%">操作</th>
								</thead>
								<tbody>
									[#list supplierBranch as item]
									<tr>
										<td>${item.name}</td>
										<td>${item.tel}</td>
										<td>${item.address}</td>
										<td><span class="btn" onClick="deleteOne(${item.id})">删除</span></td>
									</tr>
									[/#list]
								</tbody>
						</table>
					<!--内容放这里 end-->
				 </div>
	       </div>
			[#include "/materials/common/nav.ftl"]
       </div>
	   <!-- footer -->
		<div class="footer">
			<div class="wrap bc tc">
				<p>北京居天下网络科技有限公司　010-67482366 67477686 &nbsp;&nbsp;地址：北京朝阳区十八里店周家庄村288号融望大酒店6层 居天下版权所有Copyright © 2013-2015 京ICP备15010944</p>
			</div>
		</div>
    </body>		
    
    <script>
      function deleteOne(id){
       var TF=confirm("确定删除分店吗?");
       if(TF){
	        $.post("${base}/material/supplierBranch/deleteOne.do",{id:id},function(rel){
	          if(rel.ret==0){
	            location.reload();
	          }
	        });
       }
      }
    </script>
</html>