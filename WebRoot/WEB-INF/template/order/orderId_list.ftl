<!DOCTYPE HTML>
<html>
<head>
<title>订单列表</title>
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
							<input type="hidden" name="area"  value="">
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
						<span class="sp"><strong>所属商户：</strong><input type="text" class="text" name="spName" id=""></span>
						<span class="sp"><strong class="auto">订单编号：</strong><input type="text" class="text" name="id" id=""></span>
						
					</li>
					<li>
						<span class="sp"><strong>收货人名称：</strong><input type="text" class="text" name="contactUser" id=""></span>
						<span class="sp"><strong class="auto">收货人电话：</strong><input type="text" class="text" name="contactMobile" id=""></span>
					</li>
					<li>
					 <span class="sp"><strong>下单时间：</strong><input type="text" class="text Wdate" name="createBegin" id="" onClick="WdatePicker()" readOnly></span>
					 <span class="sp"><input type="text" class="text Wdate" name="createEnd" id="" onClick="WdatePicker()" readOnly></span>
					</li>
					<li>
					 <span class="sp"><strong>发货时间：</strong><input type="text" class="text Wdate" name="shipBegin" id="" onClick="WdatePicker()" readOnly></span>
					 <span class="sp"><input type="text" class="text Wdate" name="shipEnd" id="" onClick="WdatePicker()" readOnly></span>
					</li>
					<li>
					   	<span class="sp"><strong>注册会员：</strong><input type="text" class="text" name="nickname" id=""></span>
					   	<span class="btn search">查 询</span>
					</li>
				</ul>
				</form>
			</div>
		
		
		<div class="page_tab_box">
                <div class="page_tab">
                    <div class="pageTab">
                        <span class="cur"><a href="${base}/orderId/index.xhtml?pid=${pid}&mid=${mid}">正常商品</a></span>
                        <span ><a href="${base}/orderId/index.xhtml?pid=${pid}&mid=${mid}&state=0">待付款</a></span>
                        <span ><a href="${base}/orderId/index.xhtml?pid=${pid}&mid=${mid}&status=1">待发货</a></span>
                        <span ><a href="${base}/orderId/index.xhtml?pid=${pid}&mid=${mid}&status=2">已发货</a></span>
                        <span ><a href="${base}/orderId/index.xhtml?pid=${pid}&mid=${mid}&status=3">已收货</a></span>
                        <span ><a href="${base}/orderId/index.xhtml?pid=${pid}&mid=${mid}&status=4">已完成</a></span>
                        <span ><a href="${base}/orderId/index.xhtml?pid=${pid}&mid=${mid}&status=-1">已取消</a></span>
                    </div>
                </div>
            </div>
		
			<table class="format">
				<thead>
					<th width="6%">订单编号</th>
					<th width="7%">会员</th>
					<th width="6%">所属城市</th>
					<th width="14%">供货商</th>
					<th width="12%">收货人</th>
					<th width="8%">收货地址</th>
					<th width="6%">商品总额</th>
					<th width="6%">实付金额</th>
					<th width="12%">下单时间</th>
					<th width="12%">发货时间</th>
					<th width="6%">订单状态</th>
					<th width="5">操作</th>			
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td>${item.id}</td>
						<td>${item.nickName}</td>
						<td>${item.city}</td>
						<td>${item.spName}</td>
						<td>${item.contactUser}<br/>${item.contactMobile}</td>
						<td>${item.address}</td>
						<td>￥${item.itemSumPrice}</td>
						<td>￥${item.paySumPrice}</td>
						<td>${item.vcreateTime}</td>
						<td>${item.vshipTime}</td>
						<td>${item.vstatusVal}</td>
						<td><span class="btn modify"><a href="detail.xhtml?pid=${pid}&mid=${mid}&id=${item.id}">详情</a></span></td>
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
		<td><%=result.elements[i].nickName%></td>
		<td><%=result.elements[i].city%></td>
		<td><%=result.elements[i].spName%></td>
		<td><%=result.elements[i].contactUser%><br/><%=result.elements[i].contactMobile%></td>
		<td><%=result.elements[i].address%></td>
		<td>￥<%=result.elements[i].itemSumPrice%></td>
		<td>￥<%=result.elements[i].paySumPrice%></td>
		<td><%=result.elements[i].vcreateTime%></td>
		<td><%=result.elements[i].vshipTime%></td>
		<td><%=result.elements[i].vstatusVal%></td>
		<td><span class="btn modify"><a href="detail.xhtml?pid=${pid}&mid=${mid}&id=<%=result.elements[i].id%>">详情</a></span>
	</tr>
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/orderId/pagination.do',
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
		case "3":$('.pageTab span:eq(4)').addClass('cur');break;
		case "4":$('.pageTab span:eq(5)').addClass('cur');break;
		case "-1":$('.pageTab span:eq(6)').addClass('cur');break;
		default:$('.pageTab span:eq(0)').addClass('cur');break;
	}
})
</script>
</html>