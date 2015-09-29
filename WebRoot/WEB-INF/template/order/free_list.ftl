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
					<a href="${base}/supplier/password" class="logout"><i class="new">&#xf09c;</i> 修改密码</a>
				</p>
			</div>
		</div>
		<!-- wrap -->
		<div class="wrap index bc clr">
			<div class="conWrap fl">
				<h2 class="hd mb10">
					<strong><a href="#">商家后台</a></strong> &gt;
					<span id="sel1" data-role="0元商品消纳">0元商品消纳</span> &gt;
					<span id="sel1" data-role="商品消纳列表">商品消纳列表</span>
				</h2>
				<div class="conBox">
			<!--内容放这里 start-->
				<div class="tools white border mb10 sp10 clr">
				<form id="search">
				<ul>
					<li>
						<span class="sp"><strong>0元验证码:</strong><input type="text" class="text" name="id" id=""></span>			
						<span class="sp"><strong class="auto">购买用户：</strong><input type="text" class="text" name="nickName" id=""></span>
					</li>
			     	<li>
					 <span class="sp"><strong>下单时间：</strong><input type="text" class="text Wdate" name="createTimeBegin" id="" onClick="WdatePicker()" readOnly></span>
					 <span class="sp"><input type="text" class="text Wdate" name="createTimeEnd" id="" onClick="WdatePicker()" readOnly></span>
					</li>
					<li>
						<strong>券类型：</strong>
					   	<span class="sp radio">
					   	<input type="radio" name="status" value=""/> 全部 
					   	<input type="radio" name="status" value="0"/> 未领取 
					   	<input type="radio" name="status" value="1"/> 已领取 					   
					   	</span>
					   	<span class="btn search">查 询</span>
					</li>
				</ul>
				</form>
			</div>
			<table class="format">
				<thead>
					<th width="8%">0元验证码</th>
					<th width="8%">购买用户</th>
					<th width="24%">商品名称</th>
					<th width="20%">所属商家</th>
					<th width="10%">购买金额</th>
					<th width="10%">购买时间</th>
					<th width="10%">消费时间</th>
					<th width="10%">使用状态</th>	
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td>${item.id}</td>
						<td>${item.nickName}</td>
						<td>${item.title}</td>					
						<td>${item.spName}</td>
						<td>0</td>
						<td>${item.createTimeHandle}</td>
						<td>${item.getTimeHandle}</td>
						<td>${item.statusHandle}</td>
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
		</body>		
		<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
		<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
		<script type="text/html" id="list">
		<%for(i=0;i<result.elements.length;i++){%>
			<tr>
			<td><%=result.elements[i].id%></td>
			<td ><%=result.elements[i].nickName%></td>
			<td ><%=result.elements[i].title%></td>
			<td ><%=result.elements[i].spName%></td>
			<td >0</td>
			<td ><%=result.elements[i].createTimeHandle%></td>
			<td ><%=result.elements[i].getTimeHandle%></td>
			<td ><%=result.elements[i].statusHandle%></td>
		</tr>	
		<%}%>
		</script>
		<script>
			$(function(){
				$('.ui-paging').page({
					url:'${base}/orderFree/paginationFreeList.do',
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