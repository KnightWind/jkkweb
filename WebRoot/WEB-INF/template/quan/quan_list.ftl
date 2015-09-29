<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>代金券管理</title>
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
						<span class="sp"><strong>券批次：</strong><input type="text" class="text" name="no"></span>
					</li>
					<li>
						<span class="sp"><strong>实际面值：</strong>
						<input type="text" class="text mr10" name="startValue"><input type="text" class="text" name="endValue">
						</span>
					</li>
					<li>
						<span class="sp"><strong>有效期限：</strong><input type="text" class="text Wdate" name="startDate" id="" onClick="WdatePicker()" readOnly></span>
						<span class="sp"><input type="text" class="text Wdate" name="endDate" id="" onClick="WdatePicker()" readOnly></span>
					</li>
					<li>
						<strong>发布状态：</strong>
						<span class="sp radio">
							<label><input type="radio" name="status" value="">全部</label>
							<label><input type="radio" name="status" value="0">未发布</label>
							<label><input type="radio" name="status" value="1">已发布</label>
							<label><input type="radio" name="status" value="-1">注销</label>
						</span>
						<span class="btn search">查 询</span>
					</li>
				</ul>
				</form>
			</div>
		
            <div style="height:22px;padding:8px 5px;">
            	<span style="float:right">
            	<a href="${base}/quanCard/add.xhtml?mid=${mid}&pid=${pid}"><span class="btn">添加</span></a>
            	</span>
            </div>
			<table class="format">
				<thead>					
					<th width="8%">券批次</th>
					<th width="8%">实际面值</th>
					<th width="8%">销售价格</th>
					<th width="10%">开始时间</th>
					<th width="10%">结束时间</th>
					<th width="8%">类型</th>
					<th width="5%">预发总数</th>
					<th width="5%">发布状态</th>
					<th width="8%">上架状态</th>
					<th width="10%">创建日期</th>
					<th width="20%">操作</th>				
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>						
						<td>${item.no}</td>
						<td>${item.parValue}</td>
						<td>${item.price}</td>
						<td>${item.expireStartStr}</td>
						<td>${item.expireEndStr}</td>
						<td>${item.typeName}</td>
						<td>${item.salesNum}</td>
						<td>${item.statusName}</td>
						<td>${item.saleingName}</td>
						<td>${item.createTimeStr}</td>
						<td>
						<a href="${base}/quanCard/edit.xhtml?id=${item.id}&city=${item.city}&num=${item.num}"><span class="btn">查看</span></a>
						<span class="btn send" id="${item.id}">推送</span>
						<span class="btn export" id="${item.id}">导出</span>
						<span class="btn logout" id="${item.id}">注销</span>
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
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/form.js"></script>
<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
<script>
var send = function() {
	var id = $(this).attr("id");
	var url = "${base}/supplier/quan/push.do";
	$.post(url, {id : id}, function(res) {
		if (res.ret == 1) {
		} else {
			justTip(res.msg);
		}
	}, "json");
};
var logout = function() {
	var id = $(this).attr("id");
	var url = "${base}/supplier/quan/logout.do";
	$.post(url, {id : id}, function(res) {
		if (res.ret == 1) {
		} else {
			justTip(res.msg);
		}
	}, "json");
}

$(function(){
	$("span.send").click(send);
	$("span.logout").click(logout);
});	
</script>

<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr>
		<td><%=result.elements[i].no%></td>
		<td><%=result.elements[i].parValue%></td>
		<td><%=result.elements[i].price%></td>
		<td><%=result.elements[i].expireStartStr%></td>
		<td><%=result.elements[i].expireEndStr%></td>
		<td><%=result.elements[i].typeName%></td>
		<td><%=result.elements[i].salesNum%></td>
		<td><%=result.elements[i].statusName%></td>
		<td><%=result.elements[i].saleingName%></td>
		<td><%=result.elements[i].createTimeStr%></td>
		<td>
			<a href="${base}/quanCard/edit.xhtml?id=<%=result.elements[i].id%>&city=<%=result.elements[i].city%>&num=<%=result.elements[i].num%>"><span class="btn">查看</span></a>
			<span class="btn send" id="${item.id}">推送</span>
			<span class="btn export" id="${item.id}">导出</span>
			<span class="btn logout" id="${item.id}">注销</span>
		</td>
	</tr>
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/quanCard/pagination.do',
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
			$("span.send").click(send);
			$("span.logout").click(logout);
		}
	});
})

</script>
</body>
</html>