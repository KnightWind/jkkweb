<!DOCTYPE HTML>
<html>
<head>
<title>商品退款列表</title>
<meta charset="utf-8">
[#include "/common/res.ftl"]
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
						<strong>所属区域：</strong>
						<div class="select province mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p rel="${base}/goods/item/sheng.do">
							<a value="">--请选择--</a>
                            [#list areaDomain as area]
                                <a value="${area.areaDomain}">${area.area}</a>
                             [/#list]
							</p>					
							<input type="hidden" name="province" value="">
						</div>
						<div class="select city mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p rel="${base}/goods/item/city.do">
								
							</p>
							<input type="hidden" name="city" value="">
						</div>
						<div class="select area mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p>
								
							</p>
							<input type="hidden" name="area"  value="">
						</div>
					
					</li>
					<li>
						<span class="sp"><strong>退货编号：</strong><input type="text" class="text" name="orderId" id=""></span>			
						<span class="sp"><strong class="auto">订单编号：</strong><input type="text" class="text" name="id" id=""></span>
						<span class="sp"><strong class="auto">用户昵称：</strong><input type="text" class="text" name="contactUser" id=""></span>
					</li>
					<li>
					 <span class="sp"><strong>退货时间：</strong><input type="text" class="text Wdate" name="begin" id="" onClick="WdatePicker()" readOnly></span>
					 <span class="sp"><input type="text" class="text Wdate" name="end" id="" onClick="WdatePicker()" readOnly></span>
					 <strong class="auto">退货状态：</strong>
				   	 <span class="sp radio">
					   	 <input type="radio" name="status" value=""/> 全部 
					   	 <input type="radio" name="status" value="1"/> 待退款 
					   	 <input type="radio" name="status" value="2"/> 退款成功 
				   	 </span>
				   	 <span class="btn search">查 询</span>
					</li>
				</ul>
				</form>
			</div>
			<table class="format">
				<thead>
					<th width="10%">退货编号</th>
					<th width="10%">订单编号</th>
					<th width="20%">退货时间</th>
					<th width="10%">订单金额</th>
					<th width="10%">实付金额</th>
					<th width="10%">券抵扣</th>
					<th width="10%">姓名/电话</th>
					<th width="10%">退款状态</th>
					<th width="10">操作</th>			
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td>${item.orderId}</td>
						<td>${item.id}</td>
						<td>${item.createTimeHandle}</td>						
						<td>${item.itemSumPrice}</td>
						<td>${item.paySumPrice}</td>		
						<td>${item.discount}</td>
						<td>${item.contactUser}<br/>${item.contactMobile}</td>			
						<td>${item.statusHandle}</td>
						<td><span class="btn modify"></span>
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
<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>

<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr>
	<td><%=result.elements[i].orderId%></td>
	<td><%=result.elements[i].id%></td>
	<td ><%=result.elements[i].createTimeHandle%></td>
	<td ><%=result.elements[i].itemSumPrice%></td>
	<td ><%=result.elements[i].paySumPrice%></td>
	<td ><%=result.elements[i].discount%></td>
	<td ><%=result.elements[i].contactUser%><br/><%=result.elements[i].contactMobile%></td>
	<td ><%=result.elements[i].statusHandle%></td>
	<td><span class="btn modify"></span>
</tr>	
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/order/refund/pagination.do',
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