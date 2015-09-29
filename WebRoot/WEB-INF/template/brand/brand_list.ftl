<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>商品分类列表</title>
[#include "/common/res.ftl"]
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/form.js"></script>
<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
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
						<span class="sp"><strong>品牌名称：</strong><input type="text" class="text" name="name"></span>					
						<span class="sp"><strong class="auto">品牌编号：</strong><input type="text" class="text" name="id"></span>
					</li>
					<li>
					<span class="sp"><strong>创建时间：</strong><input type="text" class="text Wdate" name="beginTime" id="" onClick="WdatePicker()" readOnly></span>
                    <span class="sp"><input type="text" class="text Wdate" name="endTime" id="" onClick="WdatePicker()" readOnly></span>					
					</li>			
					<li>
						<strong>品牌状态：</strong>
						<span class="sp radio">
							<input type="radio" name="status" id="status" value="">
							<label for="status"> 全部 </label>
							<input type="radio" name="status" id="status1" value="0">
							<label for="status1"> 上架 </label>
							<input type="radio" name="status" id="status2" value="1">
							<label for="status2"> 下架 </label>
						</span>
						<span class="btn search">查 询</span>
					</li>
				</ul>
				</form>
			</div>
		
			<table class="format">
				<thead>
					<th width="4%"><input type="checkbox" name="" class="selectAll"/></th>
					<th width="8%">品牌编号</th>
					<th width="12%">品牌名称</th>
					<th width="10%">关键词(SEO)</th>
					<th width="8%">状态</th>
					<th width="8%">创建时间</th>
					<th width="18%">操作</th>				
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td><input type="checkbox" name=""  /></td>
						<td>${item.id}</td>
						<td>${item.name}</td>
						<td>${item.keyword}</td>
						<td name="statusName">${item.statusName}</td>
						<td>${item.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
						<td>
						<a href="${base}/brand/edit.xhtml?id=${item.id}&mid=${mid}&pid=${pid}"><span class="btn">编辑</span></a>
						<span class="btn handle" id="${item.id}" status="${item.status}">${item.statusNameHandle}</span>
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
</body>

<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr>
	<td><input type="checkbox" name=""  /></td>
	<td ><%=result.elements[i].id%></td>
	<td ><%=result.elements[i].name%></td>
	<td ><%=result.elements[i].keyword%></td>
	<td name="statusName"><%=result.elements[i].statusName%></td>
	<td><%=result.elements[i].createTime%></td>
	<td>
	  <a href="${base}/brand/edit.xhtml?id=<%=result.elements[i].id%>&mid=<%=result.elements[i].mid%>&pid=<%=result.elements[i].pid%>"><span class="btn">编辑</span></a>
	  <span class="btn handle" id="<%=result.elements[i].id%>" status="<%=result.elements[i].status%>"><%=result.elements[i].statusNameHandle%></span>
    </td>
</tr>	
<%}%>
</script>

<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/brand/pagination.do',
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
			$('input.selectAll').click();
	    }
	});
})
</script>
<script>
$(function(){
$(".format").delegate("span.handle","click",function() {
		var target = this;
		var row = $(this).parent().parent();
		var id = $(this).attr("id");
		var isOffline = $(this).attr("status") == 1;
		var url = "${base}/brand/offline.do";
		if (isOffline) {
			url = "${base}/brand/online.do";
		}
		$.post(url, {id : id}, function(res) {
			if (res.ret == 1) {
				$("[name=statusName]", row).html(isOffline ? "上架" : "下架");
				$(target).attr("status", isOffline ? 0 : 1);
				$(target).html(!isOffline ? "上架" : "下架");
			} else {
				justTip(res.msg);
			}
		}, "json");
	});
	
});	
</script>
</html>