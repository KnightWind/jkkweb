<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>限时折扣管理</title>
[#include "/common/res.ftl"]
<style type="text/css">
	.tools .line {margin-bottom:8px;}
	.tools .line strong {width:140px;}	
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
				<form id="search">
				<ul>
					<li>
						<input type="hidden" id="area_domain" name="area_domain">
						<strong>所属区域：</strong>
						<div class="select province mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p rel="${base}/admin/area/sheng.do">
							<a value="">--请选择--</a>
                            [#list lst as item]
                                <a value="${item.areaDomain}">${item.area}</a>
                             [/#list]
							</p>					
						</div>
						<div class="select city mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p rel="${base}/admin/area/city.do">
							</p>
							<input type="hidden" name="city"  value="">
						</div>
						<div class="select area mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p></p>
							<input type="hidden" name="area"  value="">
						</div>
					</li>
					<li>
						<span class="sp mr10"><strong>商品编号：</strong><input type="text" class="text" name="product_id"></span>
					    <span class="sp"><strong>商品名称：</strong><input type="text" class="text" name="product_name"></span>
					</li>
					<li>
						
						<strong>是否过期：</strong>
						<span class="sp radio">
							<label><input type="radio" name="expired" value=""> 全部</label>
							<label><input type="radio" name="expired" value="1"> 是</label>
							<label><input type="radio" name="expired" value="0"> 否</label>
						</span>				
					</li>
					<li>
						<strong>限时折扣：</strong>
						<span class="sp radio">
							<label><input type="radio" name="status" value=""> 全部</label>
							<label><input type="radio" name="status" value="0"> 上架</label>
							<label><input type="radio" name="status" value="-1"> 下架</label>
						</span>
						<span class="btn search">查 询</span>
					</li>
				</ul>
				</form>
			</div>
		
            <div style="height:22px;padding:8px 5px;">
            	<span style="float:right">
            	<a href="${base}/item/special/edit.xhtml?mid=${mid}&pid=${pid}"><span class="btn">添加</span></a>
            	</span>
            </div>
			<table class="format">
				<thead>					
					<th width="6%">商品编号</th>
					<th width="24%">商品名称</th>
					<th width="4%">库存</th>
					<th width="4%">已售</th>
					<th width="8%">人均限购</th>
					<th width="4.5%">
					商品价格<br/>
					限时价格
					</th>					
					<th width="14%">时间范围</th>
					<th width="4%">状态</th>
					<th width="8%">是否过期</th>
					<th width="8%">发布日期</th>
					<th width="20%">操作</th>				
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>						
						<td>${item.productId}</td>
						<td>${item.productName}</td>
						<td>${item.stock}</td>
						<td>${item.saleNum}</td>
						<td>${item.limitNum}</td>
						<td style="text-align:left;">
							￥${item.productPrice}<br/>
							￥${item.discount}
						</td>
						<td>${item.startDateStr}至${item.endDateStr}</td>
						<td name="statusName">${item.statusName}</td>
						<td>${item.expireName}</td>
						<td>${item.createTimeStr}</td>
						<td>
						<a href="${base}/item/special/edit.xhtml?id=${item.id}&mid=${mid}&pid=${pid}"><span class="btn">编辑</span></a>
						<span class="btn offline" id="${item.id}" status="${item.status}">[#if item.status=-1]上架[#else]下架[/#if]</span>
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

<script>
var offline = function() {
	var target = this;
	var row = $(this).parent().parent();
	var id = $(this).attr("id");
	var isOffline = $(this).attr("status") == -1;
	var url = "${base}/item/special/offline.do";
	if (isOffline) {
		url = "${base}/item/special/online.do";
	}
	$.post(url, {id : id}, function(res) {
		if (res.ret == 1) {
			$("[name=statusName]", row).html(isOffline ? "上架" : "下架");
			$(target).attr("status", isOffline ? 0 : -1);
			$(target).html(!isOffline ? "上架" : "下架");
		} else {
			justTip(res.msg);
		}
	}, "json");
};

$(function(){
	$("span.offline").click(offline);
});	
</script>

<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr>
		<td><%=result.elements[i].productId%></td>
		<td><%=result.elements[i].productName%></td>
		<td><%=result.elements[i].stock%></td>
		<td><%=result.elements[i].saleNum%></td>
		<td><%=result.elements[i].limitNum%></td>
		<td style="text-align:left;">
		￥<%=result.elements[i].productPrice%>
		<br/>
		￥<%=result.elements[i].discount%></td>
		<td><%=result.elements[i].startDateStr%>至<%=result.elements[i].endDateStr%></td>
		<td name="statusName"><%=result.elements[i].statusName%></td>
		<td><%=result.elements[i].expireName%></td>
		<td><%=result.elements[i].createTimeStr%></td>
		<td>
		<a href="${base}/item/special/edit.xhtml?id=<%=result.elements[i].id%>&mid=${mid}&pid=${pid}"><span class="btn">查看</span></a>
		<span class="btn offline" id="<%=result.elements[i].id%>" status="<%=result.elements[i].status%>">
		<%= result.elements[i].status = -1 ? '上架' : '下架'%>
		</span>
		</td>
	</tr>
<%}%>
</script>
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/form.js"></script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/item/special/pagination.do',
		deal:{'click':'span.search'},
		format:['pageCount','count'],
		send:function(){
			return {
				pageSize : 10,
				search:$('#search').formatJSON()
			};
		},
	    fun:function(dataR){
	    	if(dataR.records<1){ return false;}
			$('table.format tbody').html(template.render('list',dataR));
			$("span.offline").click(offline);
		}
	});
})
</script>
</body>
</html>