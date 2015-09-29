<!DOCTYPE HTML>
<html>
<head>
<title>会员列表</title>
<meta charset="utf-8">
[#include "/common/res.ftl"]

<style>
.tools span.sp {
margin-right: 10px;
float: left;
}
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
				<form id="search" action="">
				<ul>					
					<li>
						<span class="sp"><strong>会员账号：</strong><input type="text" class="text" name="id" id=""></span>
						<span class="sp"><strong class="auto">会员昵称：</strong><input type="text" class="text" name="nickName" id=""></span>
					</li>
					<li>
					 <span class="sp"><strong>创建时间：</strong><input type="text" class="text Wdate" name="createBegin" id="" onClick="WdatePicker()" readOnly></span>
					 <span class="sp"><input type="text" class="text Wdate" name="createEnd" id="" onClick="WdatePicker()" readOnly></span>
					 <span class="btn search">查 询</span>
					</li>			
				</ul>
				</form>
			</div>
			
			<table class="format">
				<thead>
					<th width="10%">会员编号</th>
					<th width="20%">会员账号</th>
					<th width="20%">会员昵称</th>
					<th width="20%">注册时间</th>
					<th width="10%">操作</th>					
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td>${item.id}</td>
						<td>${item.mobile}</td>
						<td>${item.nickname}</td>
						<td>${item.createTimeHandle}</td>
						<td>
						  <span class="btn" onClick="deleteOne(${item.id})">
						          删除
						  </span>
						 <!-- <a href="detail.xhtml?pid=${pid}&mid=${mid}&id=${item.id}" class="obtn">详细</a>-->
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
<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>

<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr>
		<td><%=result.elements[i].id%></td>
		<td><%=result.elements[i].mobile%></td>
		<td><%=result.elements[i].nickname%></td>
		<td><%=result.elements[i].createTimeHandle%></td>
		<td>
		<span class="btn" onClick="deleteOne(<%=result.elements[i].id%>)">
		      删除
	    </span>
		<!--<a href="detail.xhtml?pid=${pid}&mid=${mid}&id=<%=result.elements[i].id%>" class="obtn">详细</a>-->
		</td>	
	</tr>
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/members/pagination.do',
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
})

function deleteOne(id){
    $.post("${base}/members/deleteOne.do",{id:id},function(rel){
       justTip(rel.msg);
       if(rel.ret==0){
          location.reload();
       }
    });
}
</script>
</html>