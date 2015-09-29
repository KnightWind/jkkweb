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
		.dataShow{ widtd:48%; height:500px; float:left; padding-top:20px;}
		.dataShow p{height:40px;}
		.dataShow table{ margin-bottom:20px;}
		.dataShow table td{ border-right:1px solid #ccc;}
		.dataSp{ margin-right:4%;}
		tr{
			padding-top:10px;
		}
		td{height:55px;font-size:14px;}
		.td_left{text-align:right;font-size:15px;font-weight:bold;}
		#table_detail{mangin:50px;}
		.requi{color:red};
		
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
					<span id="sel1" data-role="员工列表">子账号列表</span>
				</h2>
				<div class="conBox">
			<!--内容放这里 start-->
					<div class="tools white border mb10 sp10 clr">
				<form id="search" action="">
				<ul>
					<li>
						<span class="sp"><strong>员工编号：</strong><input type="text" class="text" name="uid" id=""></span>
					</li>
					<li>
						<span class="sp"><strong>员工姓名：</strong><input type="text" class="text" name="uname" id=""></span>
						<span class="btn search">查 询</span>
					</li>
				</ul>
				</form>
			</div>
			<div style="float:right;margin-bottom:5px;"><a class="obtn" href="${base}/supplier/in/supplierUserUpdate.xhtml">添加子账号</a></div>
			<table class="format">
				<thead>
					<th width="8%">登录编号</th>
					<th width="8%">登录账号</th>
					<th width="8%">创建时间</th>
					<th width="20%">账号权限</th>
					<th width="10%">操作</th>				
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td>${item.id}</td>
						<td>${item.username}</td>
						<td>${item.createTimeString}</td>						
						<td>
						[#if item.isAudit==0]
							【审核员 】
						[/#if]
						[#if item.isAdmin==0]
							【管理员】
						[/#if]
						[#if item.isMerchandiser==0]
							【接单员】
						[/#if]
						[#if item.isDesigner==0]
							【设计师】
						[/#if]
						</td>
						<td>
						<a class="obtn" href="${base}/supplier/in/supplierUserUpdate.xhtml?id=${item.id}">修改</a>
						<span class="btn del" rel="${base}/supplier/in/deleteUser.do?id=${item.id}">删除</span>
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
				<td><%=result.elements[i].username%></td>
				<td><%=result.elements[i].createTimeString%></td>
				<td> <%if(result.elements[i].isAudit==0){ %>	
					【审核员】
				<%}%>
				<%if(result.elements[i].isAdmin==0){ %>
					【管理员】
				<%}%>
				<%if(result.elements[i].isMerchandiser==0){ %>	
					【跟单员】
					<%}%>
				<%if(result.elements[i].isDesigner==0){ %>
					【设计师】
				<%}%></td>
				<td>
				<a class="obtn" href="${base}/supplier/in/supplierUserUpdate.xhtml?id=<%=result.elements[i].id%>">修改</a>
				<span class="btn del" rel="${base}/supplier/in/deleteUser.do?id=<%=result.elements[i].id%>">删除</span>
				</td>
			</tr>	
			<%}%>
		</script>
	<script>
	$(function(){
		$('.ui-paging').page({
			url:'${base}/supplier/in/supplierUserpagination.do',
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
				//$('table.doctorList tbody').html(temp.join(''));
		    }
		});
	})
	</script>
	</body>
</html>