<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>商家结算</title>
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
						<strong>所属区域：</strong>
						<div class="select province mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p rel="${base}/goods/item/sheng.do">
								<a value="">--请选择--</a>
                            	[#list lst as l]
                                <a value="${l.areaDomain}">${l.area}</a>
                             	[/#list]
							</p>					
							<input type="hidden" name="province" value="">
						</div>
						<div class="select city mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p rel="${base}/goods/item/city.do">
							</p>
						<input type="hidden" name="city"  value="">
						</div>
						<div class="select area mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p>
							</p>
							<input type="hidden" name="area"  value="">
						</div>
					</li>
					<li>
						<span class="sp"><strong>商户名称：</strong><input type="text" class="text" name="sname" id=""></span>
						<span class="sp"><strong class="auto">联系人：</strong><input type="text" class="text" name="user" id=""></span>
					</li>
					<li>
						<span class="sp"><strong>结算日期：</strong><input type="text" class="text Wdate" name="start" id="" onClick="WdatePicker()" readOnly></span>
						<span class="sp"><input type="text" class="text Wdate" name="end" id="" onClick="WdatePicker()" readOnly></span>
						<span class="btn search">查 询</span>
					</li>
				</ul>
				</form>
			</div>
			
             
            
			<table class="format">
				<thead>					
					<th width="16%">商家名称</th>
					<th width="6%">销售金额</th>
					<th width="6%">退货金额</th>
					<th width="6%">实售金额</th>
					<th width="6%">分成比例</th>
					<th width="6%">分成金额</th>
					<th width="6%">结算金额</th>
					<th width="16%">银行开户行</th>
					<th width="6%">户名</th>
					<th width="12%">账号</th>
					<th width="18%">操作</th>				
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						
						<td>${item.spName}</td>
						<td>${item.itemSumPrice}</td>
						<td>${item.itemSumPrice-item.bd}</td>
						<td>${item.price*item.num}</td>
						<td>${item.ga*100}%</td>					
						<td>${item.paySumPrice/100}</td>
						<td>${item.paySumPrice-item.paySumPrice/100}</td>	
						<td>${item.bankName}</td>	
						<td>${item.bankAuthor}</td>	
						<td>${item.bankAccount}</td>								
						<td>
						<a class="obtn" href="${base}/order/ordership/list.xhtml?mid=${mid}&pid=${pid}&id=${item.mid}&sname=${item.spName}&tool=${item.price*item.num}&fc=${item.paySumPrice/100}&jsa=${item.paySumPrice-item.paySumPrice/100}&ga=${item.ga*100}">查看详细</a>
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
	<td><%=result.elements[i].spName%></td>
	<td><%=result.elements[i].itemSumPrice%></td>
	<td><%=result.elements[i].itemSumPrice-result.elements[i].bd%></td>
	<td><%=result.elements[i].price*result.elements[i].num%></td>
	<td><%=result.elements[i].ga*100%>%</td>					
	<td><%=result.elements[i].paySumPrice/100%></td>
	<td><%=result.elements[i].paySumPrice-result.elements[i].paySumPrice/100%></td>
	<td><%=result.elements[i].bankName%></td>	
	<td><%=result.elements[i].bankAuthor%></td>
	<td><%=result.elements[i].bankAccount%></td>
	<td><a class="obtn" href="${base}/order/ordership/list.xhtml?id=<%=result.elements[i].id%>&sname=<%=result.elements[i].spName%>&tool=<%=result.elements[i].price*result.elements[i].num%>&fc=<%=result.elements[i].paySumPrice-result.elements[i].paySumPrice%>&jsa=<%=result.elements[i].paySumPrice-result.elements[i].paySumPrice/100%>&ga=<%=result.elements[i].ga*100%>&pid=${pid}&mid=${mid}">查看详细</a></td>
</tr>	
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/order/ordership/pagination.do',
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
})
</script>
</body>
</html>