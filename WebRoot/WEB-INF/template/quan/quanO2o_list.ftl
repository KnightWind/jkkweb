<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>O2O管理</title>
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
						<input type="text" class="text" name="startValue">
						<span>-</span>
						<input type="text" class="text" name="endValue">
						</span>
					</li>	
					<li>
						<span class="sp"><strong>有效期：</strong>
						<input type="text" class="text Wdate" name="startDate" id="" onClick="WdatePicker()" readOnly>
						<span>-</span>
						<input type="text" class="text Wdate" name="endDate" id="" onClick="WdatePicker()" readOnly>						
						</span>
					</li>
					<li>
						<strong>发布状态：</strong>
						<span class="sp radio">
							<label><input type="radio" name="status" value=""> 全部 </label>
							<label><input type="radio" name="status" value="1"> 已发布 </label>
							<label><input type="radio" name="status" value="0"> 未发布 </label>
							<label><input type="radio" name="status" value="-1"> 注销 </label>
						</span>
						<span class="btn search">查 询</span>
					</li>
				</ul>
				</form>
			</div>
		
            <div style="height:22px;padding:8px 5px;">
            	<span style="float:right">
            	<a href="${base}/quanO2o/add.xhtml?mid=${mid}&pid=${pid}"><span class="btn">添加</span></a>
            	</span>
            </div>
			<table class="format">
				<thead>
					<th width="4%"><input type="checkbox" name=""  /></th>
					<th width="4%">券批次</th>
					<th width="6%">实际面值</th>
					<th width="8%">销售价格</th>
					<th width="12%">开始时间</th>
					<th width="12%">结束时间</th>
					<th width="6%">预发总数</th>
					<th width="8%">发布状态</th>
					<th width="6%">上架状态</th>
					<th width="14%">创建日期</th>
					<th width="20%">操作</th>				
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td><input type="checkbox" name=""  /></td>
						<td>${item.no}</td>
						<td>${item.par}</td>
						<td>${item.price}</td>
						<td>${item.st}</td>
						<td>${item.en}</td>
						<td>${item.num}</td>
						<td>${item.zt}</td>
						<td>${item.xiajia}</td>
						<td>${item.cr}</td>
						<td>
						<a href="${base}/quanO2o/edit.xhtml?qid=${item.qid}&city=${item.city}&num=${item.num}&pid=${pid}&mid=${mid}"><span class="btn">查看</span></a>
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
		<td><input type="checkbox" name=""  /></td>
		<td><%=result.elements[i].no%></td>
		<td><%=result.elements[i].par%></td>
		<td><%=result.elements[i].price%></td>
		<td><%=result.elements[i].st%></td>
		<td><%=result.elements[i].en%></td>
		<td><%=result.elements[i].num%></td>
		<td><%=result.elements[i].zt%></td>
		<td><%=result.elements[i].xiajia%></td>
		<td><%=result.elements[i].cr%></td>
		<td>
		<a href="${base}/quanO2o/edit.xhtml?qid=<%=result.elements[i].qid%>&city=<%=result.elements[i].city%>&num=<%=result.elements[i].num%>&pid=${pid}&mid=${mid}"><span class="btn">查看</span></a>
		<span class="btn send" id="<%=result.elements[i].id%>">推送</span>
		<span class="btn export" id="<%=result.elements[i].id%>">导出</span>
		<span class="btn logout" id="<%=result.elements[i].id%>">注销</span>
		</td>
	</tr>
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/quanO2o/pagination.do',
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