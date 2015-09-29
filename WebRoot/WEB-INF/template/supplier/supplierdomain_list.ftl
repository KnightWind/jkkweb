<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>商家域名管理</title>
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
						<span class="sp"><strong>域名地址：</strong><input type="text" class="text" name="city" id=""></span>
						<span class="sp"><strong class="auto">商户名称：</strong><input type="text" class="text" name="sname" id=""></span>
						<strong class="auto">商户类型：</strong>
						<div class="select">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p>
								<a value="">--请选择--</a>
								<a value="1">建材商</a>
                                <a value="2">装修公司</a>
                           		<a value="3">工长</a>
                           		<a value="4">实体店</a>
                           		<a value="5">监理</a>
                           		<a value="6">设计师</a>
							</p>
							<input type="hidden" name="type">
						</div>
					</li>
					<li>
						<span class="sp"><strong>联系人：</strong><input type="text" class="text" name="uname" id=""></span>
						<span class="sp"><strong class="auto">开启时间：</strong><input type="text" class="text Wdate" name="start" id="" onClick="WdatePicker()" readOnly></span>
						<span class="sp"><input type="text" class="text Wdate" name="end" id="" onClick="WdatePicker()" readOnly></span>
						<span class="btn search">查 询</span>
					</li>
				</ul>
				</form>
			</div>
			<table class="format">
				<thead>
					<th width="16%">商户名称</th>
					<th width="8%">域名</th>
					<th width="10%">商户账号</th>
					<th width="6%">商户类型</th>
					<th width="10%">手机号</th>
					<th width="10%">开启时间</th>
					<th width="10%">停用时间</th>
					<th width="5%">绑定状态</th>
					<th width="10%">操作</th>				
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td>${item.sname}</td>
						<td>${item.domain}</td>
						<td>${item.uname}</td>
						<td>${item.type}</td>
						<td>${item.phto}</td>
						<td>${item.end}</td>
						<td>${item.sto}</td>
						<td>${item.bi}</td>										
						<td>
						<a class="obtn" href="${base}/supplier/dc/lst.xhtml?mid=${mid}&pid=${pid}&splid=${item.splid}&yu=${item.domain}&did=${item.id}">绑定</a>
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
	<td><%=result.elements[i].sname%></td>
	<td><%=result.elements[i].domain%></td>
	<td><%=result.elements[i].uname%></td>
	<td><%=result.elements[i].type%></td>
	<td><%=result.elements[i].phto%></td>
	<td><%=result.elements[i].end%></td>					
	<td><%=result.elements[i].sto%></td>
	<td><%=result.elements[i].bi%></td>		
	<td><a class="obtn" href="${base}/supplier/dc/lst.xhtml?mid=${mid}&pid=${pid}&splid=<%=result.elements[i].splid%>&yu=<%=result.elements[i].domain%>&did=<%=result.elements[i].id%>">绑定</a>
	</td>
</tr>	
<%}%>
</script>
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="${base}/scripts/form.js"></script>
<script type="text/javascript">
$(function(){
	$('.ui-paging').page({
		url:'${base}/supplier/dc/pagination.do',
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
})
</script>
</body>
</html>