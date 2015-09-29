<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>搜索页广告管理</title>
[#include "/common/res.ftl"]
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/form.js"></script>
<script>
.tools span.sp{
float: left;
margin-right: 10px;
}
</script>
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
				<form id="search" action="http://www.baidu.com">
				<ul>
					<li>
						<span class="sp"><strong>广告编号：</strong><input type="text" class="text" name="id" ></span>
						
					</li>
					<li>
					<span class="sp"><strong>广告名称：</strong><input type="text" class="text" name="name" id=""></span>
					<span class="sp"><strong>关键词：</strong><input type="text" class="text" name="aname" id=""></span>
						
					</li>
					<li>
						<span class="sp radio"><strong>广告状态：</strong><input type="radio" name="status" id="status" value=""><label for="status"> 全部 </label><input type="radio" name="status" id="status1" value="0"><label for="status1"> 正常 </label><input type="radio" name="status" id="status2" value="-1"><label for="status2"> 下架 </label></span>
						<span class="btn search">查 询</span>
					</li>
				</ul>
				</form>
			</div>  
			<a class="obtn" href="${base}/appointment/ad/add.xhtml?mid=${mid}&pid=${pid}">添加</a> 
			<table class="format">
				<thead>
					
					<th width="8%">广告编号</th>
					<th width="8%">广告名称</th>
					<th width="8%">关键词</th>
					<th width="8%">广告状态</th>
					<th width="20%">操作</th>				
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>				
						<td>${item.id}</td>
						<td>${item.name}</td>
						<td>${item.aname}</td>
						<td>${item.ison}</td>									
						<td>
						<a class="obtn" href="${base}/appointment/ad/edit.xhtml?mid=${mid}&pid=${pid}&id=${item.id}">编辑</a>
						[#if item.status==0]
							<span class="btn del" rel="${base}/appointment/ad/oper.do?id=${item.id}&isopen=${item.status}">下架</span>
						[#else]
						<span class="btn del" rel="${base}/appointment/ad/oper.do?id=${item.id}&isopen=${item.status}">上架</span>
						[/#if]					
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
<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr menuid="<%=result.elements[i].id%>">
	         <td><%=result.elements[i].id%></td>
						<td><%=result.elements[i].name%></td>
						<td><%=result.elements[i].aname%></td>
						<td><%=result.elements[i].ison%></td>
						
	<td>
	<a class="obtn"  href="${base}/appointment/ad/edit.xhtml?mid=${mid}&pid=${pid}&id=<%=result.elements[i].id%>">编辑</a>
	<%if (result.elements[i].status==0){%>
						<span class="btn del" rel="${base}/appointment/ad/oper.do?id=<%=result.elements[i].id%>&isopen=<%=result.elements[i].status%>">下架</span>
						<%} %>
						<%if (result.elements[i].status==-1){%>
						<span class="btn del" rel="${base}/appointment/ad/oper.do?id=<%=result.elements[i].id%>&isopen=<%=result.elements[i].status%>">上架</span>
						<%} %>
	
</tr>	
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/appointment/ad/pagination.do',
		deal:{'click':'.search'},
		
		send:function(){
			var data={
			search:$('#search').formatJSON(),
					pageSize:10
			};
			return data;
		},
	    fun:function(dataR){
	    	if(dataR.result.count<1){$('table.format tbody').html('<tr><td colspan=5>没有数据</td></tr>'); return false;}
			$('table.format tbody').html(template.render('list',dataR)); 
			//$('table.doctorList tbody').html(temp.join(''));
	    }
	});
})
</script>
</body>
</html>