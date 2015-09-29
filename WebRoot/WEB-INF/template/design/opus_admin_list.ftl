<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>设计师作品审核管理</title>
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
						<span class="sp"><strong>作品标题：</strong><input type="text" class="text" name="title" id=""></span>
						<span class="sp"><strong>设计师：</strong><input type="text" class="text" name="designer" id=""></span>
						<span class="btn search">查 询</span>
					</li>
				</ul>
				</form>
			</div>				
			
			<div class="page_tab_box">
                <div class="page_tab">
                    <div class="pageTab">
                        <span><a href="${base}/staffopu/index.xhtml?url=opus_admin_list&mid=${mid}&pid=${pid}&status=0">待审核</a></span>
                        <span ><a href="${base}/staffopu/index.xhtml?url=opus_admin_list&mid=${mid}&pid=${pid}&status=1">已通过</a></span>
                        <span ><a href="${base}/staffopu/index.xhtml?url=opus_admin_list&mid=${mid}&pid=${pid}&status=-1">未通过</a></span>
                    </div>
                </div>
            </div>
			<table class="format">
				<thead>
					<th width="5%">作品编号</th>
					<th width="20%">作品标题</th>
					<th width="8%">设计师</th>
					<th width="20%">装修公司</th>
					<th width="8%">创建时间</th>
					<th width="8%">状态</th>
					<th width="15%">操作</th>				
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td>${item.id}</td>
						<td>${item.title}</td>
						<td>${item.designerName}</td>
						<td>${item.spName}</td>	
						<td>${item.createTimeString}</td>
						<td>${item.statusString}</td>						
						<td>
							<a class="obtn" href="${base}/staffopu/opusView.xhtml?id=${item.id}&mid=${mid}&pid=${pid}">查看</a>
							[#if item.status == 0]
							<span class="btn del" rel="${base}/staffopu/examine.do?id=${item.id}&status=1">通过</span>
							<span class="btn del" rel="${base}/staffopu/examine.do?id=${item.id}&status=-1">不通过</span>
							[/#if]
							<span class="btn del" rel="${base}/staffopu/remove.do?id=${item.id}">删除</span>
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
			<td><%=result.elements[i].id%></td>
			<td><%=result.elements[i].title%></td>
			<td><%=result.elements[i].designerName%></td>
			<td><%=result.elements[i].spName%></td>
			<td><%=result.elements[i].createTimeString%></td>
			<td><%=result.elements[i].statusString%></td>
			<td>
				<a class="obtn" href="${base}/staffopu/opusView.xhtml?id=<%=result.elements[i].id%>&mid=${mid}&pid=${pid}">查看</a>
				<%if(result.elements[i].status ==0){%>
					<span class="btn del" rel="${base}/staffopu/examine.do?id=${item.id}&status=1">通过</span>
					<span class="btn del" rel="${base}/staffopu/examine.do?id=${item.id}&status=-1">不通过</span>
				<%}%>
				<span class="btn del" rel="${base}/staffopu/remove.do?id=<%=result.elements[i].id%>">删除</span>
			</td>
		</tr>
	<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/staffopu/pagination.do?status=${status}',
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