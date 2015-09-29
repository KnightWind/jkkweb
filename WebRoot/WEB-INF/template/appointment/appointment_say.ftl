<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>微信活动预约管理</title>
[#include "/common/res.ftl"]
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="${base}/scripts/form.js"></script>
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
				<input type="hidden" name="status" value="${status}">
				<ul>					
					<li>
						<span class="sp"><strong>预约编号：</strong><input type="text" class="text" name="id" id=""></span>
					    <span class="sp"><strong>用户手机：</strong><input type="text" class="text" name="mobile" id=""></span>						
					</li>
				    <li>
						<strong>电话是否接通：</strong>
						  <span class="sp radio">
							    <input type="radio" name="called"  value=""><label for="status"> 全部 </label>
							    <input type="radio" name="called"  value="1"><label for="status1"> 接通 </label>
							    <input type="radio" name="called"  value="0"><label for="status2"> 未接通</label>
						  </span>
						<strong class="auto">
						  <strong>接单客服：</strong>
							<input type="text" name="adminName" class="text"/>
						</strong>
					</li>
					<li>
					    <span class="sp">
						       <strong>装修预算：</strong>
							   <input type="text" class="text" name="budgetLeast" id="">--
							   <input type="text" class="text" name="budgetMost" id="">(单位:元)
						</span>
					</li>	
					<li>
					   <span class="sp">
						<strong>装修面积：</strong>
						   <input type="text" class="text" name="areaLeast" id="">--
						   <input type="text" class="text" name="areaMost" id="">(单位:m²)
						</span>	
					<span class="btn search">查 询</span>
					</li>
				</ul>
				</form>
			</div>
			
			 <div class="page_tab_box">
                <div class="page_tab">
                    <div class="pageTab">
                        <span class="cur"><a href="${base}/appointment/beijingSay.xhtml?pid=${pid}&mid=${mid}">全部</a></span>
                        <span ><a href="${base}/appointment/beijingSay.xhtml?pid=${pid}&mid=${mid}&status=1">未派出</a></span>
                        <span ><a href="${base}/appointment/beijingSay.xhtml?pid=${pid}&mid=${mid}&status=2">追踪单</a></span>
                        <span ><a href="${base}/appointment/beijingSay.xhtml?pid=${pid}&mid=${mid}&status=3">成交单</a></span>
                        <span ><a href="${base}/appointment/beijingSay.xhtml?pid=${pid}&mid=${mid}&status=4">废单</a></span>
                    </div>
                </div>
            </div>
			
			<table class="format">
				<thead>
					<th width="5%">编号</th>
					<th width="10%">预约用户</th>					
					<th width="8%">手机号</th>
					<th width="8%">区域</th>
					<th width="8%">所在小区</th>
					<th width="8%">面积(单位:m²)</th>
					<th width="10%">装修预算(单位:元)</th>					
					<th width="7%">创建时间</th>
					<th width="15%">客服</th>
					<th width="10%">操作</th>				
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td>${item.id}</td>
						<td>${item.user}</td>
						<td>${item.mobile}</td>
						<td>${item.regionname}</td>
						<td>${item.community}</td>
						<td>${item.space}m²</td>
						<td>${item.budget}</td>
						<td>${item.createTimeHandle}</td>	
						<td>${item.createUserVal}</td>
						<td>			
							<a class="obtn" href="${base}/appointment/sayView.xhtml?id=${item.id}&mid=${mid}&pid=${pid}">详情</a>
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
	<td><%=result.elements[i].user%></td>
	<td><%=result.elements[i].mobile%></td>
	<td><%=result.elements[i].regionname%></td>					
	<td><%=result.elements[i].community%></td>					
	<td><%=result.elements[i].space%>m²</td>
	<td><%=result.elements[i].budget%></td>
	<td><%=result.elements[i].createTimeHandle%></td>	
	<td><%=result.elements[i].createUserVal%></td>
	<td>
	 	<a class="obtn" href="${base}/appointment/sayView.xhtml?id=<%=result.elements[i].id%>&mid=${mid}&pid=${pid}">详情</a>
	</td>
</tr>	
<%}%>
</script>
<script>

function getData(){
	$.get("${base}/appointment/ajaxGetDate.do",function(data){
		$("tbody tr").remove();
		for(var i = 0;i < data.length; i++){
			var html = '<tr>'
					 + '<td>' + data[i].id + '</td>'
					 + '<td>' + data[i].user + '</td>'
					 + '<td>' + data[i].mobile + '</td>'
					 + '<td>' + data[i].regionname + '</td>'
					 + '<td>' + data[i].community + '</td>'
					 + '<td>' + data[i].space + 'm²</td>'
					 + '<td>' + data[i].budget + '</td>'
					 + '<td>活动单</td>'
					 + '<td>' + data[i].address + '</td>'
					 + '<td>'
					 + '<a class="obtn" href="${base}/appointment/sayView.xhtml?id=' + data[i].id + '&mid=${mid}&pid=${pid}">详情</a>'
					 + '</td>'
					 + '</tr>'
					 
			$("tbody").append(html);	 
		}
	});
	
	time = setTimeout("getData()", 30000);
	
}

function closeRef(){
	
	clearTimeout(time);
	
}


$(function(){

	<!--getData();-->

	$('.ui-paging').page({
		url:'${base}/appointment/beijingSayPage.do',
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
	
	var status=window.location.href.split('status=')[1];
	$('.pageTab span.cur').removeClass('cur');
	switch(status){
		case "1":$('.pageTab span:eq(1)').addClass('cur');break;
		case "2":$('.pageTab span:eq(2)').addClass('cur');break;
		case "3":$('.pageTab span:eq(3)').addClass('cur');break;
		case "4":$('.pageTab span:eq(4)').addClass('cur');break;
		default:$('.pageTab span:eq(0)').addClass('cur');break;
	}
	
});	
</script>
</body>
</html>