<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>商家员工管理列表</title>
[#include "/common/res.ftl"]
<style>
	.fieldStyle{
		padding:20px;
	}
	
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
						<strong>商家类型：</strong>
						<div class="select mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p>
                                <a value="">--请选择--</a>
                                <a value="1">装修公司</a>
                                <a value="2">建材商</a>
                                <a value="3">工长</a>
                                <a value="5">监理</a>
							</p>					
							<input type="hidden" name="type" value="">
						</div>
						<span class="sp"><strong>商家名称：</strong><input type="text"  class="text" name="supplierName"/></span>
						<span class="btn search">查 询</span>
					</li>
				</ul>
				</form>
			</div>
		
			<table class="format">
				<thead>
					<th width="6%">商户Id</th>
					<th width="15%">商户名称</th>
					<th width="10%">装修公司法人</th>
					<th width="10%">员工数</th>
					<th width="10%">操作</th>					
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td>${item.id}</td>
						<td>${item.spName}</td>
						<td>${item.legalPerson}</td>
						<td>${item.count}</td>
						<td>
							<a href="${base}/supplierSaff/index.xhtml?id=${item.id}&mid=${mid}&pid=${pid}&returnUrl=supplier_staff_addminList" class="obtn">管理</a>
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
</body>
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr menuid="<%=result.elements[i].id%>">
	<td><%=result.elements[i].id%></td>
	<td column="spName"><%=result.elements[i].spName%></td>
	<td column="legalPerson"><%=result.elements[i].legalPerson%></td>
	<td column="count"><%=result.elements[i].count%></td>
	<td>
	   <a href="${base}/supplierSaff/index.xhtml?id=<%=result.elements[i].id%>&mid=${mid}&pid=${pid}&returnUrl=supplier_staff_addminList" class="obtn">管理</a>
    </td>
</tr>	
<%}%>
</script>
<script type="text/javascript">
$(function(){
	$('.ui-paging').page({
		url:'${base}/supplier/supplierByTypePagination.do',
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
});


</script>
</html>