<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>引流活动购卡管理</title>
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
				<div>
					<h2>业主信息录入</h2>
					<hr/>
					<table class="input">
						<tr>
							<td  class="tr">业主姓名：</td>
							<td><input type="text" class="text"  name="name"  /></td>
							<td  class="tr">业主手机：</td>
							<td><input type="text" class="text"  name="phone"  /></td>
							<td  class="tr">所在小区：</td>
							<td><input type="text" class="text"  name="commutiy"  /></td>
							<td class="tr">活动卡：</td>
							<td>
								<div class="select area mr10">
									<i class="new">&#xf107;</i><span>--请选择--</span>
									<p>
										 [#list list as l]
			                                <a value="${l.id}">${l.name}</a>
			                             [/#list]
									</p>
									<input type="hidden" name="area"  value="">
								</div>
								<input type="hidden" name="status" value="${status}">
							</td>
						</tr>
						<tr>
							<td>装修需求：</td>
							<td colspan="7"><textarea name="desc" cols="130"></textarea></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td><span class="btn">报名</span></td>
						</tr>
					</table>
				</div>
				<h2>业主信息管理</h2>
				<hr/>
				<div class="bott left">
					
					<table class="format" style="float:left;width:100%;">
						<thead>
							<th width="10%">编号</th>
							<th width="10%">业主姓名</th>
							<th width="10%">业主电话</th>
							<th width="20%">所在小区</th>
							<th width="10%">装修需求</th>
						</thead>
						<tbody>
							[#list pagination.dataList as item ]
							<tr>
							    <td>${item.id}</td>
							    <td>${item.cardName}</td>
								<td>${item.phone}</td>
								<td>${item.phone}</td>
								<td>${item.decorateReq}</td>
							</tr>
							[/#list]
						</tbody>
				   </table>
				 </div>
				   <div class="bott right fr">
					<table class="format1">
						<thead>
							<th width="10%">排名</th>
							<th width="10%">店铺名称</th>
							<th width="10%">售卡数量</th>
							<th width="20%">金额</th>
						</thead>
						<tbody>
							[#list pagination.dataList as item ]
							<tr>
							    <td>${item.id}</td>
							    <td>${item.cardName}</td>
								<td>${item.phone}</td>
								<td>${item.phone}</td>
							</tr>
							[/#list]
						</tbody>
				   </table>
			   </div>
			   <div style="clear:both;"></div>
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
	    <td><%=result.elements[i].cardName%></td>
		<td><%=result.elements[i].phone%></td>
		<td><%=result.elements[i].createTimeHandle%></td>
	</tr>
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/wxActivityCardDeals/pagination.do',
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