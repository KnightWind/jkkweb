<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<title>预约看工地</title>
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
					<span id="sel1" data-role="首页">预约看工地</span>
				</h2>
				<div class="conBox">
			<!--内容放这里 start-->
			<div class="tools white border mb10 sp10 clr">
				<form id="search">
				   <input type="hidden" name="status" value="${status}">
				   <ul>					
					<li>
						<span class="sp"><strong>预约单编号：</strong><input type="text" class="text" name="aid" id=""></span>
						<span class="sp"><strong>预约用户：</strong><input type="text" class="text" name="member" id=""></span>
					</li>
					<li>
						<span class="sp"><strong>应单时间：</strong>
						   <input type="text" class="text Wdate" name="singleTimeStart" id="" onClick="WdatePicker()" readOnly>
						</span>
						<span class="sp">
						   <input type="text" class="text Wdate" name="singleTimeEnd" id="" onClick="WdatePicker()" readOnly>
						</span>						
						<span class="btn search">查 询</span>
					</li>
				</ul>
				</form>
			</div>
			<div class="page_tab_box">
                <div class="page_tab">
                    <div class="pageTab">
	                    <span class="cur"><a href="${base}/appWorksite/supplierPushGDIndex.xhtml">全部</a></span>
	                    <span ><a href="${base}/appWorksite/supplierPushGDIndex.xhtml?status=0">待应单</a></span>
	                    <span ><a href="${base}/appWorksite/supplierPushGDIndex.xhtml?status=10">已应单</a></span>
	                    <span ><a href="${base}/appWorksite/supplierPushGDIndex.xhtml?status=20">已取消</a></span>
                    </div>
                </div>
            </div>
			<table class="format">
				<thead>
					<th width="10%">预约编号</th>
					<th width="10%">预约用户</th>
					<th tidth="10%">预约小区</th>
					<th width="20%">预约时间</th>
					<th width="20%">应单时间</th>
					<th width="10%">状态</th>
					<th width="20%">操作</th>				
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td>${item.id}</td>
						<td>${item.nickName}</td>
						<td>${item.appointAddress}</td>
						<td>${item.appointTimeHandle}</td>
						<td>${item.respondTimeString}</td>
						<td>${item.statusString}</td>
			            <td>
			             [#if item.status == 0] 
			               <span class="btn del" id="${item.id}" rel="${base}/appWorksite/change.do?id=${item.id}&status=10">应单</span>
			             [/#if]
			             [#if item.status != 0] 
			              <span style="padding: 4px 12px; background: #cccccc; border-radius: 3px;" >应单</span>
			             [/#if]
			             [#if item.status != 20] 
			               <span class="btn del" id="${item.id}" rel="${base}/appWorksite/change.do?id=${item.id}&status=20">取消</span>
			             [/#if]
			             [#if item.status == 20] 
			                <span style="padding: 4px 12px; background: #cccccc; border-radius: 3px;" >取消</span>
			             [/#if]
			            <td>
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
				<tr>
				        <td><%=result.elements[i].id%></td>
				        <td><%=result.elements[i].nickName%></td>
						<td><%=result.elements[i].appointAddress%></td>
						<td><%=result.elements[i].appointTimeHandle%></td>
						<td><%=result.elements[i].respondTimeString%></td>
						<td><%=result.elements[i].statusString%></td>
				    <td>
				      <% if(result.elements[i].status == 0){ %>
				      	 <span class="btn del" id="${item.id}" rel="${base}/appWorksite/change.do?id=<%=result.elements[i].id%>&status=10">应单</span>
				      <%}%>
				      <% if(result.elements[i].status != 0){ %>
				      	<span style="padding: 4px 12px; background: #cccccc; border-radius: 3px;" >应单</span>
				      <%}%>
				      <% if(result.elements[i].status != 20){ %>
				      	 <span class="btn del" id="${item.id}" rel="${base}/appWorksite/change.do?id=<%=result.elements[i].id%>&status=20">放弃</span>
				      <%}%>
				      <% if(result.elements[i].status == 20){ %>
				      	 <span style="padding: 4px 12px; background: #cccccc; border-radius: 3px;" >取消</span>
				      <%}%>
			        <td>
				</tr>	
			<%}%>
			</script>
			<script>
				$(function(){
					$('.ui-paging').page({
						url:'${base}/appWorksite/supplierPushGDPagination.do',
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
						case "10":$('.pageTab span:eq(2)').addClass('cur');break;
						case "20":$('.pageTab span:eq(3)').addClass('cur');break;
						default:$('.pageTab span:eq(0)').addClass('cur');break;
					}
				});	
			</script>
		</body>		
	</html>