<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<title>商品列表</title>
		[#include "/common/res.ftl"]	
		<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
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
				 <div class="conBox">
					<!--内容放这里 start-->
			      
            <div class="tools white border mb10 sp10 clr">
				<form id="search" action="">
					<ul>
						<li>
							<span class="sp"><strong>商品编号：</strong><input type="text" class="text" name="id" id=""></span>
							<span class="sp"><strong>商品名称：</strong><input type="text" class="text" name="title" id=""></span>
							<input type="hidden" name="is" value="${is}">
							<span class="btn search">查 询</span>
						</li>
				    </ul>
				</form>
			</div>
	         
			
             <div class="page_tab_box">
                <div class="page_tab">
			        	<a class="obtn fr" href="${base}/materials/item/edit.xhtml">添加商品</a>
                </div>
            </div>
			<table class="format">
				<thead>					
					<th width="5%">商品编号</th>
					<th width="10%">商品分类</th>
					<th width="20%">商品名称</th>
					<th width="15%">商品价格(单位:元)</th>
					<th width="5%">库存数量</th>
					<th width="8%">商品众筹状态</th>
					<th width="15%">更新时间</th>
					<th width="20%">操作</th>				
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td>${item.id}</td>
						<td>${item.bname}</td>
						<td>${item.title}</td>
						<td>${item.price}</td>					
						<td>${item.stock}</td>	
						<td>${item.crowdStatus}</td>	
						<td>${item.up}</td>									
						<td>
						<a class="obtn" href="${base}/materials/item/edit.xhtml?id=${item.id}">修改</a>
						<!--[#if item.isFree==1]
							<span class="btn del" rel="${base}/materials/item/oper.do?id=${item.id}&isopen=${item.isFree}">取消0元购</span>
						[#else]
							<span class="btn del" rel="${base}/materials/item/oper.do?id=${item.id}&isopen=${item.isFree}">设置0元购</span>
						[/#if]-->
						<a class="obtn" href="${base}/materials/item/setItemToZC.xhtml?itemId=${item.id}">设为众筹</a>
						[#if item.cnt > 0 && item.zitemStatus == 1]
							<a class="obtn" onclick="return confirm('确定申请取消众筹？')" href="${base}/materials/item/removeCrowd.xhtml?itemId=${item.id}">取消众筹</a>
						[/#if]
						[#if item.cnt == 0 || (item.zitemStatus != 1 && item.zitemStatus != 2)]
							<a class="obtn" onclick="return confirm('确定删除？')" href="${base}/materials/item/remove.xhtml?id=${item.id}">删除</a>
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
	<td><%=result.elements[i].id%></td>
	<td><%=result.elements[i].bname%></td>
	<td><%=result.elements[i].title%></td>
	<td><%=result.elements[i].price%></td>					
	<td><%=result.elements[i].stock%></td>	
	<td><%=result.elements[i].crowdStatus%></td>	
	<td><%=result.elements[i].up%></td>
	<td>
		<a class="obtn" href="${base}/materials/item/edit.xhtml?id=<%=result.elements[i].id%>">修改</a>
		<!--<%if (result.elements[i].isFree==1){%>
		<span class="btn del" rel="${base}/materials/item/oper.do?id=<%=result.elements[i].id%>&isopen=<%=result.elements[i].isFree%>">取消0元购</span>
		<%} %>
		<%if (result.elements[i].isFree==0){%>
		<span class="btn del" rel="${base}/materials/item/oper.do?id=<%=result.elements[i].id%>&isopen=<%=result.elements[i].isFree%>">设置0元购</span>
		<%} %>-->
		<a class="obtn" href="${base}/materials/item/setItemToZC.xhtml?itemId=<%=result.elements[i].id%>">设为众筹</a>
		<%if (result.elements[i].zitemStatus == 1 && result.elements[i].cnt > 0){%>
		<a class="obtn" onclick="return confirm('确定申请取消众筹？')" href="${base}/materials/item/removeCrowd.xhtml?itemId=<%=result.elements[i].id%>">取消众筹</a>
		<%} %>
		<%if ((result.elements[i].zitemStatus != 1 && result.elements[i].zitemStatus != 2) || result.elements[i].cnt == 0){%>
		<a class="obtn" onclick="return confirm('确定删除？')" href="${base}/materials/item/remove.xhtml?id=<%=result.elements[i].id%>">删除</a>
		<%} %>
	</td>
</tr>	
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/materials/item/pagination.do?status=${status}',
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
		case "1":$('.pageTab span:eq(1)').addClass('cur');break;
		default:$('.pageTab span:eq(0)').addClass('cur');break;
	}
});	
</script>
    	
</html>