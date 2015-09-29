<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<title>商家管理</title>
		[#include "/common/res.ftl"]
		<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
		<script type="text/javascript" src="${base}/scripts/form.js"></script>
		<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
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
					<span id="sel1" data-role="效果图列表">效果图列表</span>
				</h2>
				<div class="conBox">
			<!--内容放这里 start-->
		<div class="tools white border mb10 sp10 clr">
				<form id="search" action="">
				<ul>
					<li>
						<span class="sp"><strong>编号：</strong><input type="text" class="text" name="id" id=""></span>
						<span class="sp"><strong class="auto">方案标题：</strong><input type="text" class="text" name="name" id=""></span>
						<span class="btn search">查 询</span>
					</li>
				</ul>
				</form>
			</div>
			<div style="float:right;margin-bottom:5px;"><a class="obtn" href="${base}/case/upload.xhtml?op=1">添加方案</a></div>
			<table class="format">
				<thead>
	
					<th width="4%">编号</th>
					<th width="15%">标题</th>
					<th width="15%">所属公司</th>
					<th width="5%">设计师</th>
					<th width="5%">冻结状态</th>
					<th width="5%">状态</th>
					<th width="10%">添加时间</th>			
					<th width="10%">操作</th>				
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						
						<td>${item.id}</td>
						<td>${item.designName}</td>
						<td>${item.spName}</td>
						<td>${item.designer}</td>
						<td>${item.zd}</td>
						<td>${item.isOperate}</td>
						<td>${item.cre}</td>									
						<td>
						<span><a class="obtn" href="${base}/goods/design/update.xhtml?id=${item.id}">编辑</a></span>
						<span class="btn"><a onclick="return confirm('确定删除?')" href="${base}/goods/design/oper.do?id=${item.id}">删除</a></span> 
						</td>
					</tr>
					[/#list]
				</tbody>
		</table>
		[#include "/common/pagination.ftl"]
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
			
			<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr menuid="<%=result.elements[i].id%>">
	<td><%=result.elements[i].id%></td>
	<td><%=result.elements[i].designName%></td>
	<td><%=result.elements[i].spName%></td>
	<td><%=result.elements[i].designer%></td>
	<td><%=result.elements[i].zd%></td>
	<td><%=result.elements[i].isOperate%></td>
	<td><%=result.elements[i].cre%></td>
	<td>
	<span><a class="obtn" href="${base}/goods/design/update.xhtml?id=<%=result.elements[i].id%>">编辑</a></span>
	<span class="btn"><a onclick="return confirm('确定删除?')" href="${base}/goods/design/oper.do?id=<%=result.elements[i].id%>&pageNum=${pageNum}">删除</a></span> 
	</td>
</tr>
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/goods/design/paginationDesignPage.do',
		deal:{'click':'.search'},		
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
	var status=window.location.href.split('status=')[1];
	$('.pageTab span.cur').removeClass('cur');
	switch(status){
		case "0":$('.pageTab span:eq(1)').addClass('cur');break;
		case "1":$('.pageTab span:eq(2)').addClass('cur');break;
		case "-1":$('.pageTab span:eq(3)').addClass('cur');break;
		default:$('.pageTab span:eq(0)').addClass('cur');break;
	}
});	
</script>
		</body>		
	</html>