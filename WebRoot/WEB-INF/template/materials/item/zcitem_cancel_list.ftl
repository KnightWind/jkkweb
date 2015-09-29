<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>众筹活动列表</title>
[#include "/common/res.ftl"]

<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/form.js"></script>
<Style>
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
[#include "/common/head.ftl"]
<!-- wrap -->
<div class="wrap index bc clr">
	<div class="conWrap fl">
		[#include "/common/nav.ftl"]
				<div class="conBox">		
								<div class="tools white border mb10 sp10 clr">
				<form id="search" action="">
				<ul>
					<li>
						<span class="sp"><strong class="auto">商品名称：</strong><input type="text" class="text" name="name"></span>
						<span class="btn search">查 询</span>
					</li>
				</ul>
				</form>
			</div>
				<table class="format">
					<thead>					
						<th width="5%">商品编号</th>
						<th width="10%">商品分类</th>
						<th width="20%">商品名称</th>
						<th width="15%">所属商家</th>
						<th width="15%">商品价格(单位:元)</th>
						<th width="20%">操作</th>				
					</thead>
					<tbody>
						[#list pagination.dataList as item]
						<tr>
							<td>${item.id}</td>
							<td>${item.cateName}</td>
							<td>${item.title}</td>
							<td>${item.spName}</td>
							<td>${item.price}</td>					
							<td>
								<a class="obtn" href="${base}/material/crowdItem/view.xhtml?id=${item.id}&mid=${mid}&pid=${pid}">审核</a>
							</td>
						</tr>
						[/#list]
					</tbody>
			</table>
		[#include "/common/pagination.ftl"]
				<!--内容放这里 end-->
				 </div>
	       </div>
			[#include "/common/menu.ftl"]
		</div>
		<!-- footer -->
	[#include "/common/foot.ftl"]
</body>

    
<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr menuid="<%=result.elements[i].id%>">
	<td><%=result.elements[i].id%></td>
	<td><%=result.elements[i].cateName%></td>
	<td><%=result.elements[i].title%></td>
	<td><%=result.elements[i].spName%></td>
	<td><%=result.elements[i].price%></td>					
	<td>
		<a class="obtn" href="${base}/material/crowdItem/view.xhtml?id=<%=result.elements[i].id%>&mid=${mid}&pid=${pid}">审核</a>
	</td>
</tr>	
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/material/crowdItem/cancelListPage.do?status=${status}',
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