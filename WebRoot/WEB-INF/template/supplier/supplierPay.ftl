<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>商家充值管理</title>
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
						<span class="sp"><strong>商家名称：</strong><input type="text" class="text" name="supplierName"/></span>
						<span class="sp"><strong class="auto">联系人名:</strong><input type="text" class="text" name="contactUser"/>
				  	</li>
				    <li>
					 	<span class="sp"><strong>最后充值时间：</strong><input type="text" class="text Wdate" name="begin" id="" onClick="WdatePicker()" readOnly></span>
						<span class="sp"><input type="text" class="text Wdate" name="end" id="" onClick="WdatePicker()" readOnly></span>
						<span class="btn search">查 询</span>
					</li>
				</ul>
				</form>
			</div>
			<table class="format">
				<thead>
					<th width="4%"><input type="checkbox"></th>
					<th width="14%">商户名称</th>
					<th width="6%">联系人名</th>
					<th width="10%">联系电话</th>
					<th width="6%">剩余次数</th>
					<th width="6%">已用次数</th>
					<th width="14%">初次充值日期</th>
					<th width="14%">最后充值日期</th>
					<th width="16%">操作</th>					
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td><input type="checkbox" value="${item.id}"></td>
						<td>${item.spName}</td>
						<td>${item.contactUser}</td>
						<td>${item.contactMobile}</td>
						<td>${item.leftNum}</td>
						<td>${item.num}</td>
						<td>${item.firstPayTimeHandle}</td>						
						<td>${item.lastPayTimeHandle}</td>
						<td><span class="btn del" rel="recharge.do?id=${item.id}" id="${item.id}">充值</span>
					</tr>
					[/#list]					
				</tbody>
				</table>				
		        [#include "/common/pagination.ftl"]
		        <div class="tools white tc pt10 pb10 borderL borderB  borderR">
				   <span class="btn" id="manyPay">批量充值</span>
				</div>
	</div>
	</div>
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]
</body>
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="${base}/scripts/form.js"></script>
<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr>
	<td><input type="checkbox" value="<%=result.elements[i].id%>"></td>
	<td><%=result.elements[i].spName%></td>
	<td><%=result.elements[i].contactUser%></td>
	<td><%=result.elements[i].contactMobile%></td>
	<td><%=result.elements[i].leftNum%></td>
	<td><%=result.elements[i].num%></td>
	<td><%=result.elements[i].firstPayTime%></td>
	<td><%=result.elements[i].lastPayTime%></td>
	<td><span class="btn del" rel="recharge.do?id=<%=result.elements[i].id%>" id="<%=result.elements[i].id%>">充值</span>
</tr>	
<%}%>
</script>
<script>
$(function(){
	$("#manyPay").click(function(){
	        var temp=[];
	   		$('.format input:checked').each(function(index,ele){
	   			temp[index]=$(ele).val();
	   		});
	   		var ids=temp.join(',');
	   		$.post("manyRecharge.do",{ids:ids},function(res){
	   		      if (res.ret == 1) {
						} else {
							justTip(res.msg);
						}
	   		});
	   });
	$('.ui-paging').page({
		url:'${base}/supplierPay/pagination.do',
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