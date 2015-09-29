<!DOCTYPE HTML>
<html>
	<head>
<meta charset="utf-8">
<title>投诉管理</title>
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
					<strong><a href="#">监理后台</a></strong> &gt;
					<span id="sel1" data-role="首页">投诉</span>
				</h2>
				<div class="conBox">
		    	<!--内容放这里 start-->
							
		      <div>      
				<form id="search" action="">
				   <input type="hidden" name="jlid" value="${jlid}">
				</form>
			  </div>	
							
			<table class="format">
				<thead>					
					<th width="10%">用户</th>
					<th width="5%">状态</th>
					<th width="10%">类型</th>
					<th width="10%">来源</th>
					<th width="15%">创建时间</th>
					<th width="15%">更新时间</th>				
					<th width="5%">受理人</th>
					<th width="10">操作</th>			
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>						
						<td>${item.mnickName}</td>
						<td>${item.statusVal}</td>
						<td>${item.typeVal}</td>
						<td>${item.sourceVal}</td>
						<td>${item.createTimeHandle}</td>		
						<td>${item.updateTimeHandle}</td>										
						<td>${item.anickName}</td>
						<td>
						<span class="btn"><a href="detailJL.xhtml?id=${item.id}&cid=${item.id}">查看</a></span>				
                        </td>
					</tr>
					[/#list]
				</tbody>
	    	</table>	
	    	[#include "/common/pagination.ftl"]
	    	<!--内容放这里 end-->
			</div>
	</div>
   [#include "/common/supervisorMenu.ftl"]
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
		
		
	<script type="text/html" id="list">
    <%for(i=0;i<result.elements.length;i++){%>
    	<tr>
		<td><%=result.elements[i].mnickName%></td>
		<td><%=result.elements[i].statusVal%></td>
		<td><%=result.elements[i].typeVal%></td>
		<td><%=result.elements[i].sourceVal%></td>
		<td><%=result.elements[i].createTimeHandle%></td>		
		<td><%=result.elements[i].updateTimeHandle%></td>										
		<td><%=result.elements[i].anickName%></td>
		<td>
	       <span class="btn"><a href="detailJL.xhtml?id=<%=result.elements[i].id%>&cid=<%=result.elements[i].id%>">查看</a></span>				
		</td>
	 </tr>
   <%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/jlComplain/pagination.do',
		deal:{'click':'span.search'},
		format:['pageCount','count'],
		send:function(){
			var data={
					pageSize:10,
					search:$('#search').formatJSON()
			};
			return data;
		},
	    fun:function(dataR){
	    	if(dataR.records<1){ return false;}
			$('table.format tbody').html(template.render('list',dataR)); 
		}
	});
})
</script>
</html>