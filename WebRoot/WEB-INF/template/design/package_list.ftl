<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>套装包管理</title>
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
				<form id="search">
				<ul>
					<li>
						<strong>所属区域：</strong>
						<div class="select province mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p rel="${base}/admin/area/sheng.do">
							<a value="">--请选择--</a>
                            [#list lst as l]
                                <a value="${l.areaDomain}">${l.area}</a>
                             [/#list]
							</p>					
							<input type="hidden" name="province" value="">
						</div>
						<div class="select city mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p rel="${base}/admin/area/city.do">
								
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
						<span class="sp"><strong>套餐编号：</strong><input type="text" class="text" name="id"></span>
						<span class="sp"><strong class="auto">套餐名称：</strong><input type="text" class="text" name="package_name"></span>
					</li>
					<li>
						<span class="sp"><strong>套餐价格：</strong>
							<input name="priceStart" class="text mr10" type="text"/>
							<input name="priceEnd" class="text" type="text" />
						</span>
						<span class="btn search">查 询</span>
					</li>
					<li>
						<strong>套餐类型：</strong>
						<span class="sp radio">
							<label><input type="radio" name="type" value=""> 全部 </label>
							<label><input type="radio" name="type" value="1"> 主材包 </label>
							<label><input type="radio" name="type" value="2"> 软装包 </label>
						</span>
						<strong class="auto">套餐状态：</strong>
						<span class="sp radio">
							<label><input type="radio" name="status" value=""> 全部 </label>
							<label><input type="radio" name="status" value="0"> 上架 </label>
							<label><input type="radio" name="status" value="-1"> 下架 </label>
						</span>
					</li>
				</ul>
				</form>
			</div>
		
            <div style="height:22px;padding:8px 5px;">
            	<span style="float:right">
            	<a href="${base}/package/edit.xhtml?mid=${mid}&pid=${pid}"><span class="btn">发布</span></a>
            	</span>
            </div>
			<table class="format">
				<thead>
					<th width="8%"><input type="checkbox" name=""  /></th>
					<th width="8%">套餐编号</th>
					<th width="8%">套餐名称</th>
					<th width="8%">类型</th>
					<th width="8%">库存</th>
					<th width="8%">已售</th>
					<th width="8%">套餐价格</th>
					<th width="8%">延米价格</th>
					<th width="8%">状态</th>
					<th width="8%">发布日期</th>
					<th width="20%">操作</th>				
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td><input type="checkbox" name=""  /></td>
						<td>${item.id}</td>
						<td>${item.packageName}</td>
						<td>${item.typeName}</td>
						<td>${item.stock}</td>
						<td>${item.saleNum}</td>
						<td>${item.price}</td>
						<td>${item.yanmi}</td>
						<td name="statusName">${item.statusName}</td>
						<td>${item.createTimeStr}</td>
						<td>
						<a href="${base}/package/edit.xhtml?id=${item.id}&mid=${mid}&pid=${pid}"><span class="btn">编辑</span></a>
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
	var url = "${base}/package/offline.do";
	if (isOffline) {
		url = "${base}/package/online.do";
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
		<td><input type="checkbox" name=""  /></td>
		<td><%=result.elements[i].id%></td>
		<td><%=result.elements[i].packageName%></td>
		<td><%=result.elements[i].typeName%></td>
		<td><%=result.elements[i].stock%></td>
		<td><%=result.elements[i].saleNum%></td>
		<td><%=result.elements[i].price%></td>
		<td><%=result.elements[i].yanmi%></td>
		<td name="statusName"><%=result.elements[i].statusName%></td>
		<td><%=result.elements[i].createTimeStr%></td>
		<td>
		<a href="${base}/package/edit.xhtml?id=${result.elements[i].id}&mid=${mid}&pid=${pid}"><span class="btn">编辑</span></a>
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
		url:'${base}/package/pagination.do',
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
});
</script>
</body>
</html>