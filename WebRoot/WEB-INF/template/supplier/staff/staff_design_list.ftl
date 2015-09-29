<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>审核列表</title>
[#include "/common/res.ftl"]
<style>
	.fieldStyle{
		padding:20px;
	}
	
</style>
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
				<form id="search" action="">
				<ul>
					<li>
						<span class="sp"><strong>员工编号：</strong><input type="text" class="text" name="uid" id=""></span>
						<span class="sp"><strong>员工姓名：</strong><input type="text" class="text" name="uname" id=""></span>
					</li>
					<li>
						<strong>创建日期：</strong>
						<span class="sp"><input type="text" class="text Wdate" name="createBegin" id="" onClick="WdatePicker()" readOnly> &nbsp;--</span>
						<span class="sp"><input type="text" class="text Wdate" name="createEnd" id="" onClick="WdatePicker()" readOnly></span>
						<span class="btn search">查 询</span>
					</li>				
				</ul>
				</form>
			</div>
		
			<table class="format">
				<thead>
					<th width="4%"><input type="checkbox" id="ckAll" /></th>
					<th width="8%">编号</th>
					<th width="10%">员工姓名</th>
					<th width="10%">职位</th>
					<th width="8%">创建时间</th>
					<th width="20%">所属公司</th>
					<th width="10%">操作</th>				
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td><input type="checkbox" id="ckOne" value="${item.id}" /></td>
						<td>${item.id}</td>
						<td>${item.name}</td>
						<td>${item.supplierPosition}</td>
						<td>${item.createTimeString}</td>
						<td>${item.spName}</td>
						<td>
							<a href="${base}/staffComment/designReplyList.xhtml?id=${item.id}&mid=${mid}&pid=${pid}&returnUrl=staff_design_replyList" class="obtn">管理评论</a>
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
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr menuid="<%=result.elements[i].id%>">
		<td><input type="checkbox" id="ckOne" value="<%=result.elements[i].id%>" /></td>
		<td><%=result.elements[i].id%></td>
		<td><%=result.elements[i].name%></td>
		<td><%=result.elements[i].supplierPosition%></td>
		<td><%=result.elements[i].createTimeString%></td>
		<td><%=result.elements[i].spName%></td>
		<td>
			<a href="${base}/staffComment/designReplyList.xhtml?id=${item.id}&mid=${mid}&pid=${pid}&returnUrl=staff_design_replyList" class="obtn">管理评论</a>
		</td>
	</tr>
<%}%>
</script>
<script type="text/javascript">
$(function(){
	$('.ui-paging').page({
		url:'${base}/supplierComment/manager/designPagination.do?sid=${sid}',
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
});	
</script>

</html>