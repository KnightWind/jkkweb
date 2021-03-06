<!DOCTYPE HTML>
<html>
<head>
<title>券类退款列表</title>
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
						<span class="sp"><strong>订单编号：</strong><input type="text" class="text" name="id" id=""></span>
						<span class="sp"><strong class="auto">所属商户：</strong><input type="text" class="text" name="spName" id=""></span>
						<span class="sp"><strong class="auto">用户昵称：</strong><input type="text" class="text" name="nickName" id=""></span>
					</li>
					<li>
						 <span class="sp"><strong>退券日期：</strong><input type="text" class="text Wdate" name="begin" id="" onClick="WdatePicker()" readOnly></span>
						 <span class="sp"><input type="text" class="text Wdate" name="end" id="" onClick="WdatePicker()" readOnly></span>
						 <strong class="auto">券状态：</strong>
					   	 <span class="sp radio">
						   	<input type="radio" name="type" value=""/> 全部 
						   	<input type="radio" name="type" value="1"/> 代金券 
						   	<input type="radio" name="type" value="2"/> 店铺券 
						   	<input type="radio" name="type" value="3"/> O2O券 
					   	 </span>
						 <span class="btn search">查 询</span>
					</li>
				</ul>
				</form>
			</div>
		     <div>
				<table class="format">
					<thead>
						<th width="10%">订单编号</th>
						<th width="5%">券类型</th>
						<th width="10%">购买用户</th>
						<th width="6%">所属城市</th>
						<th width="13%">所属商户</th>
						<th width="8%">券面值</th>
						<th width="8%">购买金额</th>
						<th width="12%">退券时间</th>
						<th width="12%">退款时间</th>
						<th width="6%">订单状态</th>
						<th width="10">操作</th>			
					</thead>
					<tbody>
						[#list pagination.dataList as item]
						<tr>
							<td>${item.id}</td>
							<td>${item.type}</td>
							<td>
							${item.nickName}
							${item.mobile}
							</td>						
							<td>${item.quanCity}</td>
							<td>${item.spName}</td>
							<td>${item.parValue}</td>
							<td>${item.price}</td>
							<td>${item.vrefundTime}</td>
							<td>${item.vrefundMoneyTime}</td>			
							<td>${item.vstatusVal}</td>
							<td><span class="btn modify"></span></td>
						</tr>
						[/#list]
					</tbody>
		    	</table>	
	    	</div>
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
<script type="text/html" id="datalist">
<%for(i=0;i<result.elements.length;i++){%>
	<tr>
		<td><%=result.elements[i].id%></td>
		<td><%=result.elements[i].type%></td>
		<td>
		<%=result.elements[i].nickName%>
		<%=result.elements[i].mobile%>
		</td>						
		<td><%=result.elements[i].quanCity%></td>
		<td><%=result.elements[i].spName%></td>
		<td><%=result.elements[i].parValue%></td>
		<td><%=result.elements[i].price%></td>
		<td><%=result.elements[i].vrefundTime%></td>
		<td><%=result.elements[i].vrefundMoneyTime%></td>			
		<td><%=result.elements[i].vstatusVal%></td>
		<td><span class="btn modify"></span>
	</tr>
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/orderQuan/refundPage.do',
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
			$('table.format tbody').html(template.render('datalist',dataR)); 
	    }
	});
});	
</script>
</html>