<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>用户预约管理</title>
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
					    <span class="sp"><strong>用户昵称：</strong><input type="text" class="text" name="nickname" id=""></span>						
					</li>
					<li>
						<span class="sp"><strong>创建日期：</strong><input type="text" class="text Wdate" name="start" id="" onClick="WdatePicker()" readOnly></span>
						<span class="sp"><input type="text" class="text Wdate" name="end" id="" onClick="WdatePicker()" readOnly></span>											
					</li>
					<li>
					    <span class="sp">
						       <strong>装修预算：</strong>
							   <input type="text" class="text" name="budgetLeast" id="">
							   <input type="text" class="text" name="budgetMost" id="">(单位:元)
						</span>
					</li>
					<li>
					   <span class="sp">
						<strong>装修面积：</strong>
						   <input type="text" class="text" name="areaLeast" id="">
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
                        <span class="cur"><a href="${base}/appointment/index.xhtml?pid=${pid}&mid=${mid}">全部预约</a></span>
                        <span ><a href="${base}/appointment/index.xhtml?pid=${pid}&mid=${mid}&status=0">流单</a></span>
                        <span ><a href="${base}/appointment/index.xhtml?pid=${pid}&mid=${mid}&status=45">退款中</a></span>                      
                        <span ><a href="${base}/appointment/index.xhtml?pid=${pid}&mid=${mid}&status=1">草稿</a></span>
                        <span ><a href="${base}/appointment/index.xhtml?pid=${pid}&mid=${mid}&status=10">待抢单</a></span>
                        <span ><a href="${base}/appointment/index.xhtml?pid=${pid}&mid=${mid}&status=20">待量房</a></span>
                        <span ><a href="${base}/appointment/index.xhtml?pid=${pid}&mid=${mid}&status=30">待报价</a></span>
                        <span ><a href="${base}/appointment/index.xhtml?pid=${pid}&mid=${mid}&status=40">待签约</a></span>
                        <span ><a href="${base}/appointment/index.xhtml?pid=${pid}&mid=${mid}&status=50">待开工</a></span>
                        <span ><a href="${base}/appointment/index.xhtml?pid=${pid}&mid=${mid}&status=60">施工中</a></span>
                        <span ><a href="${base}/appointment/index.xhtml?pid=${pid}&mid=${mid}&status=70">已完工</a></span>
                        <span ><a href="${base}/appointment/index.xhtml?pid=${pid}&mid=${mid}&status=80">暂停</a></span>
                        <span ><a href="${base}/appointment/index.xhtml?pid=${pid}&mid=${mid}&status=90">关闭</a></span>   
                        <span ><a href="${base}/appointment/index.xhtml?pid=${pid}&mid=${mid}&status=100">结束抢单</a></span>                      
                    </div>
                </div>
            </div>
			<table class="format">
				<thead>
					<th width="5%">编号</th>
					<th width="6%">预约称呼</th>					
					<th width="8%">手机号</th>
					<th width="15%">预约地址</th>
					<th width="8%">所在小区</th>
					<th width="8%">面积(单位:m²)</th>
					<th width="10%">装修预算(单位:元)</th>					
					<th width="8%">预约用户</th>
					<th width="7%">预约单状态</th>
					<th width="15%">创建时间</th>					
					<th width="10%">操作</th>				
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td>${item.id}</td>
						<td>${item.user}</td>
						<td>${item.mobile}</td>
						<td>${item.address}</td>
						<td>${item.community}</td>
						<td>${item.space}m²</td>
						<td>${item.budget}</td>
						<td>${item.nickname}</td>
						<td>${item.statusName}</td>	
						<td>${item.createTimeHandle}</td>																
						<td>			
							[#if item.status==90||item.status==100]
							   <span style="padding: 4px 12px; background: #cccccc; border-radius: 3px;" >关闭</span>	
							[#else]
							 <span class="btn del" rel="${base}/appointment/close.do?id=${item.id}">关闭</span>
							[/#if]
							 <span class="btn">
							    <a href="${base}/appointment/appointmentDetail.xhtml?pid=${pid}&${mid}&id=${item.id}">详情</a>
							 </span>
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
	<td><%=result.elements[i].address%></td>
	<td><%=result.elements[i].community%></td>					
	<td><%=result.elements[i].space%>m²</td>
	<td><%=result.elements[i].budget%></td>
	<td><%=result.elements[i].nickname%></td>
	<td><%=result.elements[i].statusName%></td>	
	<td><%=result.elements[i].createTimeHandle%></td>
	<td>
	 <% if(result.elements[i].status==90||result.elements[i].status==100){%>
	   <span style="padding: 4px 12px; background: #cccccc; border-radius: 3px;" >关闭</span>	
	 <%}%>
	 <% if(result.elements[i].status!=90&&result.elements[i].status!=100){ %>
	   <span class="btn del" rel="${base}/appointment/close.do?id=<%=result.elements[i].id%>">关闭</span>
	 <%}%>
	 <span class="btn">
	    <a href="${base}/appointment/appointmentDetail.xhtml?pid=${pid}&${mid}&id=<%=result.elements[i].id%>">详情</a>
	 </span>
	</td>
</tr>	
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/appointment/pagination.do',
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
		case "0":$('.pageTab span:eq(1)').addClass('cur');break;
		case "1":$('.pageTab span:eq(2)').addClass('cur');break;
		case "10":$('.pageTab span:eq(3)').addClass('cur');break;
		case "20":$('.pageTab span:eq(4)').addClass('cur');break;
		case "30":$('.pageTab span:eq(5)').addClass('cur');break;
		case "40":$('.pageTab span:eq(6)').addClass('cur');break;
		case "50":$('.pageTab span:eq(7)').addClass('cur');break;
		case "60":$('.pageTab span:eq(8)').addClass('cur');break;
		case "70":$('.pageTab span:eq(9)').addClass('cur');break;
		case "80":$('.pageTab span:eq(10)').addClass('cur');break;
		case "90":$('.pageTab span:eq(11)').addClass('cur');break;	
		case "100":$('.pageTab span:eq(12)').addClass('cur');break;	
		default:$('.pageTab span:eq(0)').addClass('cur');break;
	}
});	
</script>
</body>
</html>