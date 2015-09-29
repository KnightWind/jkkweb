<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>商家评论管理</title>
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
				<form id="search">
				<ul>
					
					<li>
						<strong>用户名称：</strong>
						<span class="sp"><input type="text" class="text" name="uname" ></span>
					</li>
					<li>
						<strong>评论日期：</strong>
						<span class="sp"><input type="text" class="text Wdate" name="createBegin" id="" onClick="WdatePicker()" readOnly> &nbsp;--</span>
						<span class="sp"><input type="text" class="text Wdate" name="createEnd" id="" onClick="WdatePicker()" readOnly></span>
						<span class="btn search">查 询</span>
					</li>
					
				</ul>
				</form>
			</div>	
			<div class="page_tab_box">
                <div class="page_tab">
                    <div class="pageTab">
                        <span class="cur"><a href="${base}/supplierComment/manager/view.xhtml?id=${supplier.id}&state=-1&mid=${mid}&pid=${pid}&status=0">全部状态</a></span>
                        <span ><a href="${base}/supplierComment/manager/view.xhtml?id=${supplier.id}&state=1&mid=${mid}&pid=${pid}&status=1">发布状态</a></span>
                        <span ><a href="${base}/supplierComment/manager/view.xhtml?id=${supplier.id}&state=0&mid=${mid}&pid=${pid}&status=2">屏蔽状态</a></span>
                    </div>
                </div>
            </div>	
			<table class="format" id="table_detail">
				<thead>
					<th width="3%"><input type="checkbox" id="ckAll" name="ckAll" onclick="checkAll(this)" /></th>
					<th width="8%">评论用户</th>
					<th width="8%">用户类型</th>
					<th width="10%">评论时间</th>	
					<th width="8%">评论状态</th>	
					<th width="10%">审核时间</th>	
					<th width="10%">屏蔽时间</th>
					<th width="20%">评论内容</th>
					<th width="10%">操作</th>
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td><input type="checkbox" class="ck" value="${item.id}" name="ck" /></td>
						<td>${item.uname}</td>
						<td>${item.userType}</td>		
						<td>${item.createTimeString}</td>		
						<td>${item.statusString}</td>
						<td>${item.checkTimeString}</td>
						<td>${item.closeTimeString}</td>
						<td>${item.shortContent}</td>	
						<td>
							[#if item.status == 0]
								<span class="btn del" rel="${base}/supplierComment/manager/operationApproval.do?flag=true&id=${item.id}">解除</span>
							[#else]
								<span class="btn del" rel="${base}/supplierComment/manager/operationApproval.do?flag=false&id=${item.id}">屏蔽</span>
							[/#if]
						   	<span class="btn modify" rel=["${item.content}"] >查看</span>	
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
	<tr>
		<td><input type="checkbox" class="ck" value="<%=result.elements[i].id%>" name="ck" /></td>
		<td><%=result.elements[i].uname %></td>
		<td><%=result.elements[i].userType %></td>
		<td><%=result.elements[i].createTimeString %></td>
		<td><%=result.elements[i].statusString%></td>
		<td><%=result.elements[i].checkTimeString%></td>	
		<td><%=result.elements[i].closeTimeString%></td>	
		<td><%=result.elements[i].shortContent%></td>
		<td> 
			<% if(result.elements[i].status == 0) {%>
				<span class="btn del" rel="${base}/supplierComment/manager/operationApproval.do?flag=true&id=<%=result.elements[i].id%>">解除</span>
			<%}else{%>
				<span class="btn del" rel="${base}/supplierComment/manager/operationApproval.do?flag=false&id=<%=result.elements[i].id%>" >屏蔽</span>
			<%}%>
		   <span class="btn modify" rel=["<%=result.elements[i].content%>"] >查看</span>		
	    </td>
    </tr>	
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/supplierComment/manager/commentPagination.do?sId=${sId}&state=${state}',
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
		case "2":$('.pageTab span:eq(2)').addClass('cur');break;
		default:$('.pageTab span:eq(0)').addClass('cur');break;
	}
	
});	
</script>
<script type="text/html" id="pannel">
	<div class="ui-dialog addManager" style="width:400px;">
		<h2><span class="close"></span><strong>查看评论信息</strong></h2>
		<div style="padding:20px;">
			<textarea rows="7" disabled='disabled' cols="53" class="text" name="txt" placeholder=""></textarea>
		<div>
	</div>
</script>
</body>
</html>