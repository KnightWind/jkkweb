<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>商家方案</title>
[#include "/common/res.ftl"]
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="${base}/scripts/form.js"></script>


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
						<span class="sp"><strong>方案编号：</strong><input type="text" class="text" name="id" id=""></span>
						<span class="sp"><strong class="auto">标题：</strong><input type="text" class="text" name="name" id=""></span>
					</li>
					<li>
						<span class="sp"><strong>所属商户：</strong><input type="text" class="text" name="sname" id=""></span>
						<span class="btn search">查 询</span>
					</li>
				</ul>
				</form>
			</div>			
			
			<div class="page_tab_box">
                <div class="page_tab">
                    <div class="pageTab">
                        <span><a href="${base}/goods/design/designList.xhtml?mid=${mid}&pid=${pid}&status=0">待审核</a></span>
                        <span ><a href="${base}/goods/design/designList.xhtml?mid=${mid}&pid=${pid}&status=1">已通过</a></span>
                        <span ><a href="${base}/goods/design/designList.xhtml?mid=${mid}&pid=${pid}&status=-1">未通过</a></span>
                    </div>
                </div>
            </div>			
		<table class="format">
				<thead>
					<th width="4%">编号</th>
					<th width="15%">标题</th>
					<th width="15%">公司</th>
					<th width="5%">设计师</th>
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
						<td>${item.isOperate}</td>
						<td>${item.cre}</td>									
						<td>
							<a class="obtn" href="${base}/goods/design/view.xhtml?id=${item.id}&mid=${mid}&pid=${pid}&status=${status}">查看</a>
							[#if item.status == 0]
								<a class="obtn" onclick="return confirm('确定操作?')" href="${base}/goods/design/examine.xhtml?id=${item.id}&flag=1&mid=${mid}&pid=${pid}&status=${status}">通过</a>
								<a class="obtn" onclick="return confirm('确定操作?')" href="${base}/goods/design/examine.xhtml?id=${item.id}&flag=-1&mid=${mid}&pid=${pid}&status=${status}">不通过</a>
							[/#if]
							<span class="btn"><a onclick="return confirm('确定删除?')" href="${base}/goods/design/deleteOneDesign.do?id=${item.id}&mid=${mid}&pid=${pid}&status=${status}">删除</a></span> 
						</td>
					</tr>
					[/#list]
				</tbody>
		</table>
		
		[#include "/common/pagination.ftl"]
	  </div>
	</div>
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]

	<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr menuid="<%=result.elements[i].id%>">
	<td><%=result.elements[i].id %></td>
	<td><%=result.elements[i].designName%></td>
	<td><%=result.elements[i].spName%></td>
	<td><%=result.elements[i].designer%></td>
	<td><%=result.elements[i].isOperate%></td>
	<td><%=result.elements[i].cre%></td>
	<td>
	<a class="obtn" href="${base}/goods/design/view.xhtml?id=<%=result.elements[i].id %>&mid=${mid}&pid=${pid}&status=${status}">查看</a>
	<% if(result.elements[i].status == 0) {%>
		<a class="obtn" onclick="return confirm('确定操作?')" href="${base}/goods/design/examine.xhtml?id=<%=result.elements[i].id %>&flag=1&mid=${mid}&pid=${pid}&status=${status}">通过</a>
		<a class="obtn" onclick="return confirm('确定操作?')" href="${base}/goods/design/examine.xhtml?id=<%=result.elements[i].id %>&flag=-1&mid=${mid}&pid=${pid}&status=${status}">不通过</a>
	<%}%>
	<span class="btn"><a onclick="return confirm('确定删除?')" href="${base}/goods/design/oper.do?id=<%=result.elements[i].id%>&pageNum=${pageNum}&mid=${mid}&pid=${pid}&status=${status}">删除</a></span> 
	</td>
</tr>
<%}%>
</script>

<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/goods/design/designListPage.do?status=${status}',
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
		case "0":$('.pageTab span:eq(0)').addClass('cur');break;
		case "1":$('.pageTab span:eq(1)').addClass('cur');break;
		case "-1":$('.pageTab span:eq(2)').addClass('cur');break;
		default:$('.pageTab span:eq(0)').addClass('cur');break;
	}
	
});	
</script>

</body>
</html>