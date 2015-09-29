<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<title>我的活动信息</title>
		[#include "/common/res.ftl"]	
		<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
		<script type="text/javascript" src="${base}/scripts/form.js"></script>
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
		[#include "/materials/common/head.ftl"]
		<!-- wrap -->
		<div class="wrap index bc clr">
			<div class="conWrap fl">
				<h2 class="hd mb10">
					<strong><a href="#">建材后台</a></strong> &gt;
					<span id="sel1" data-role="">商家参与名单</span>
				</h2>
				 <div class="conBox">
					<!--内容放这里 start-->
			      
            <div class="tools white border mb10 sp10 clr">
				<form id="search" action="">
				<ul>
					<li>
						<span class="sp"><strong class="auto">活动名称：</strong><input type="text" class="text" name="name"></span>
						<span class="btn search">查 询</span>
					</li>
				</ul>
				
				</form>
			</div>
			
			<table class="format">
				<thead>
					<th width="20%">商品名称</th>
					<th width="5%">市场价格</th>
					<th width="5%">当前价格</th>
					<th width="8%">开始时间</th>
					<th width="8%">结束时间</th>
					<th width="5%">活动状态</th>
					<th width="5%">用户参与名单</th>
					<th width="5%">操作</th>				
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td>${item.title}</td>
						<td>${item.topPrice}</td>
						<td>${item.lowPrice}</td>
						<td>${item.startTimeString}</td>
						<td>${item.endTimeString}</td>
						<td>${item.flagString}</td>
						<td>
							<a class="obtn" href="${base}/material/order/participation.xhtml?itemId=${item.itemId}&url=zc_partici_list">参与名单</a>
						</td>
						<td style="text-algin:left">
							<a class="obtn" href="${base}/materials/item/setItemToZC.xhtml?itemId=${item.itemId}&op=0">查看</a>
							[#if item.flag == false]
								<a class="obtn" href="${base}/materials/item/setItemToZC.xhtml?itemId=${item.itemId}&op=1">继续活动</a>
							[/#if]
						</td>
					</tr>
					[/#list]
				</tbody>
		</table>
		[#include "/common/pagination.ftl"]
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

    
<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr menuid="<%=result.elements[i].id%>">
		<td><%=result.elements[i].title%></td>
		<td><%=result.elements[i].topPrice%></td>
		<td><%=result.elements[i].lowPrice%></td>
		<td><%=result.elements[i].startTimeString%></td>
		<td><%=result.elements[i].endTimeString%></td>
		<td><%=result.elements[i].flagString%></td>
		<td>
			<a class="obtn" href="${base}/material/order/participation.xhtml?itemId=${item.itemId}&url=zc_partici_list">参与名单</a>
		</td>
		<td style="text-algin:left">
			<a class="obtn" href="${base}/materials/item/setItemToZC.xhtml?itemId=<%=result.elements[i].itemId%>&op=0">查看</a>
			<%if(!result.elements[i].flag){%>
				<a class="obtn" href="${base}/materials/item/setItemToZC.xhtml?itemId=<%=result.elements[i].itemId%>&op=1">继续活动</a>
			<%}%>
		</td>
	</tr>
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/material/activity/pagination.do?url=${url}',
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
});	
</script>
    	
</html>