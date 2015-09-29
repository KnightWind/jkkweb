<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>商家代管</title>
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
						<span class="sp"><strong>商户名称：</strong><input type="text" class="text" name="sname" id=""></span>
						<strong class="auto">商户类型：</strong>
						<div class="select">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p>
								<a value="">--请选择--</a>
								<a value="1">建材商</a>
                                <a value="2">装修公司</a>
                           		<a value="3">工长</a>
                           		<a value="4">实体店</a>
                           		<a value="5">监理</a>
                           		<a value="6">设计师</a>
							</p>
							<input type="hidden" name="type">
						</div>		
					</li>
					<li>
						<span class="sp"><strong>联系人：</strong><input type="text" class="text" name="uname" id=""></span>
						<span class="sp"><strong class="auto">代管时间：</strong><input type="text" class="text Wdate" name="start" id="" onClick="WdatePicker()" readOnly></span>
						<span class="sp"><input type="text" class="text Wdate" name="end" id="" onClick="WdatePicker()" readOnly></span>
						<input type="hidden" name="checkStatus" value="${checkStatus}">				
						<span class="btn search">查 询</span>
					</li>
				</ul>
				</form>
			</div>		
		 <div class="page_tab_box">
                <div class="page_tab">
                    <div class="pageTab">
                     	<span class="cur"><a href="${base}/supplier/sp/index.xhtml?mid=${mid}&pid=${pid}">全部</a></span>
                      	<span><a href="${base}/supplier/sp/index.xhtml?mid=${mid}&pid=${pid}&checkStatus=0">申请中</a></span>
                       	<span><a href="${base}/supplier/sp/index.xhtml?mid=${mid}&pid=${pid}&checkStatus=1">已通过</a></span>
                        <span><a href="${base}/supplier/sp/index.xhtml?mid=${mid}&pid=${pid}&checkStatus=2">已到期</a></span>
                        <span ><a href="${base}/supplier/sp/index.xhtml?mid=${mid}&pid=${pid}&checkStatus=-1">未通过</a></span>
                    </div>
                </div>
            </div>
			<table class="format">
				<thead>
					<th width="12%">商户名称</th>
					<th width="4%">联系人</th>
					<th width="6%">联系电话</th>
					<th width="11%">申请时间</th>
					<th width="11%">审核时间</th>
					<th width="11%">代管开始时间</th>
					<th width="11%">代管结束时间</th>
					<th width="4%">状态</th>
					<th width="15%">操作</th>				
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td>${item.sname}</td>
						<td>${item.uname}</td>
						<td>${item.phot}</td>
						<td>${item.cr}</td>
						<td>${item.ck}</td>
						<td>${item.op}</td>
						<td>${item.opend}</td>	
						<td>${item.dong}</td>										
						<td>			
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
	<td><%=result.elements[i].sname%></td>
	<td><%=result.elements[i].uname%></td>
	<td><%=result.elements[i].phot%></td>
	<td><%=result.elements[i].cr%></td>
	<td><%=result.elements[i].ck%></td>
	<td><%=result.elements[i].op%></td>					
	<td><%=result.elements[i].opend%></td>
	<td><%=result.elements[i].dong%></td>
	<td></td>
</tr>	
<%}%>
</script>
<script type="text/javascript">
$(function(){
	$('.ui-paging').page({
		url:'${base}/supplier/sp/pagination.do',
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
	var status=window.location.href.split('checkStatus=')[1];
	$('.pageTab span.cur').removeClass('cur');
	switch(status){
		case "0":$('.pageTab span:eq(1)').addClass('cur');break;
		case "1":$('.pageTab span:eq(2)').addClass('cur');break;
		case "2":$('.pageTab span:eq(3)').addClass('cur');break;
		case "-1":$('.pageTab span:eq(4)').addClass('cur');break;
		default:$('.pageTab span:eq(0)').addClass('cur');break;
	}
})
</script>
</body>
</html>