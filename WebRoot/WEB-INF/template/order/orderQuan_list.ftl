<!DOCTYPE HTML>
<html>
<head>
<title>券类订单列表</title>
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
                                <a  value="${area.areaDomain}">${area.area}</a>
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
						<span class="sp"><strong>订单编号：</strong><input type="text" class="text" name="id" id=""></span>			
						<span class="sp"><strong class="auto">用户名称：</strong><input type="text" class="text" name="nickname" id=""></span>
						<span class="sp"><strong class="auto">所属商户：</strong><input type="text" class="text" name="spName" id=""></span>
					</li>
					<li>
					 <span class="sp"><strong>下单时间：</strong><input type="text" class="text Wdate" name="begin" id="" onClick="WdatePicker()" readOnly></span>
					 <span class="sp"><input type="text" class="text Wdate" name="end" id="" onClick="WdatePicker()" readOnly></span>
					</li>
					
					<li>
						<strong>券类型：</strong>
					   	<span class="sp radio">
					   	<input type="radio" name="type" value=""/> 全部 
					   	<input type="radio" name="type" value="1"/> 全品券 
					   	<input type="radio" name="type" value="2"/> 店铺券 
					   	<input type="radio" name="type" value="3"/> O2O券 
					   	</span>
					   	<span class="btn search">查 询</span>
					</li>
				</ul>
				</form>
			</div>
		
		    <div class="page_tab_box">
                <div class="page_tab">
                    <div class="pageTab">
                        <span class="cur"><a href="${base}/orderQuan/index.xhtml?mid=${mid}&pid=${pid}">全部订单</a></span>
                        <span ><a href="${base}/orderQuan/index.xhtml?mid=${mid}&pid=${pid}&state=0">待付款</a></span>
                        <span ><a href="${base}/orderQuan/index.xhtml?mid=${mid}&pid=${pid}&status=1">未使用</a></span>
                        <span ><a href="${base}/orderQuan/index.xhtml?mid=${mid}&pid=${pid}&status=2">已使用</a></span>
                        <span ><a href="${base}/orderQuan/index.xhtml?mid=${mid}&pid=${pid}&status=-2">已过期</a></span>             
                        <span ><a href="${base}/orderQuan/index.xhtml?mid=${mid}&pid=${pid}&status=-1">已取消</a></span>
                    </div>
                </div>
            </div>
		
			<table class="format">
				<thead>
					<th width="6%">订单编号</th>
					<th width="6%">券类型</th>
					<th width="6%">购买用户</th>
					<th width="6%">所属城市</th>
					<th width="14%">所属商户</th>
					<th width="10%">券面值</th>
					<th width="6%">购买金额</th>
					<th width="12%">下单时间</th>
					<th width="10%">使用时间</th>
					<th width="12%">过期时间</th>
					<th width="6%">订单状态</th>
					<th width="6">操作</th>			
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td>${item.id}</td>
						<td>${item.typeHandle}</td>
						<td>${item.nickName}</td>						
						<td>${item.city}</td>
						<td>${item.spName}</td>
						<td>￥${item.parValue}</td>
						<td>￥${item.price}</td>
						<td>${item.createTimeHandle}</td>
						<td>${item.useTimeHandle}</td>
						<td>${item.expireDateHandle}</td>
						<td>${item.statusValHandle}</td>
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
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr>
		<td><%=result.elements[i].id%></td>
		<td><%=result.elements[i].typeHandle%></td>
		<td><%=result.elements[i].nickName%></td>
		<td><%=result.elements[i].city%></td>
		<td><%=result.elements[i].spName%></td>
		<td><%=result.elements[i].parValue%></td>
		<td>￥<%=result.elements[i].price%></td>
		<td>￥<%=result.elements[i].createTimeHandle%></td>
		<td><%=result.elements[i].useTimeHandle%></td>
		<td><%=result.elements[i].expireDateHandle%></td>
		<td><%=result.elements[i].statusValHandle%></td>
		<td></td>
	</tr>
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/orderQuan/pagination.do',
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
	var status=window.location.href.split('status=')[1];
	$('.pageTab span.cur').removeClass('cur');
	switch(status){
		case "0":$('.pageTab span:eq(1)').addClass('cur');break;
		case "1":$('.pageTab span:eq(2)').addClass('cur');break;
		case "2":$('.pageTab span:eq(3)').addClass('cur');break;
		case "-2":$('.pageTab span:eq(4)').addClass('cur');break;
		case "-1":$('.pageTab span:eq(5)').addClass('cur');break;
		default:$('.pageTab span:eq(0)').addClass('cur');break;
	}
})
</script>

</html>