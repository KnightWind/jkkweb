<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>商家绑定银行卡列表</title>
[#include "/common/res.ftl"]
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<style>
	.bott{
		
	}
	.left{
		width:63%;
	}
	.right{
		width:35%;
		margin-left:30px;
	}
	
	table.format1{ width: 100%; border: 1px solid #ccc; border-collapse: collapse; background: #fff; font-size: 12px;}
	table.format1 caption{ height: 40px; line-height: 40px; font-size: 16px;}
	table.format1 thead th{ border-bottom:1px solid #ccc; height: 40px; font-size: 14px; text-align: center; background: #E7E8EB; font-weight: bolder;}
	table.format1 tbody td{ border-bottom:1px solid #ccc; height: 40px; text-align: center;word-wrap: break-word; word-break: break-all;}
	table.format1 tbody tr.cur td{ background:#E7E8EB;}
	
	.input td{height: 50px;}
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
								<span class="sp"><strong>商家名称：</strong><input type="text" class="text" name="spName"></span>
								<span class="sp"><strong>银行卡号：</strong><input type="text" class="text" name="cardNo" id=""></span>
							</li>
							<li>
								<span class="sp"><strong>银行名称：</strong><input type="text" class="text" name="bankName" id=""></span>
								<span class="btn search">查 询</span>
							</li>
						</ul>
						</form>
					</div>			
					
					<table class="format" style="float:left;width:100%;">
						<thead>
							<th width="3%">编号</th>
							<th width="20%">商家名称</th>
							<th width="10%">银行卡号</th>
							<th width="10%">银行类型</th>
							<th width="10%">银行地址</th>
							<th width="10%">添加时间</th>
							<th width="10%">操作</th>
						</thead>
						<tbody>
							[#list pagination.dataList as item ]
							<tr>
							    <td>${item.id}</td>
							    <td>${item.spName}</td>
								<td>${item.cardNo}</td>
								<td>${item.bankName}</td>
								<td>${item.regionname}</td>
								<td>${item.createTimeString}</td>
								<td></td>
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
<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr>
		<td><%=result.elements[i].id%></td>
	    <td><%=result.elements[i].spName%></td>
		<td><%=result.elements[i].cardNo%></td>
		<td><%=result.elements[i].bankName%></td>
		<td><%=result.elements[i].regionname%></td>
		<td><%=result.elements[i].createTimeString%></td>
		<td></td>
	</tr>
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/material/financeBankCard/pagination.do',
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