<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>用户预约推送管理</title>
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
						<span class="sp"><strong>预约推送编号：</strong><input type="text" class="text" name="id" id=""></span>
						<span class="sp"><strong class="auto">预约单编号：</strong><input type="text" class="text" name="aid" id=""></span>
					</li>
					<li>
						<span class="sp"><strong>推送商家：</strong><input type="text" class="text" name="spName" id=""></span>
						<span class="sp"><strong class="auto">预约用户：</strong><input type="text" class="text" name="member" id=""></span>
					</li>
					<li>
						<span class="sp"><strong>创建时间：</strong>
						   <input type="text" class="text Wdate" name="createTimeStart" id="" onClick="WdatePicker()" readOnly>
						</span>
						<span class="sp">
						   <input type="text" class="text Wdate" name="createTimeEnd" id="" onClick="WdatePicker()" readOnly>
						</span>										
					</li>
					<li>
						<span class="sp"><strong>应单时间：</strong>
						   <input type="text" class="text Wdate" name="singleTimeStart" id="" onClick="WdatePicker()" readOnly>
						</span>
						<span class="sp">
						   <input type="text" class="text Wdate" name="singleTimeEnd" id="" onClick="WdatePicker()" readOnly>
						</span>						
					</li>
					<li>
						<span class="sp"><strong>量房时间：</strong>
						   <input type="text" class="text Wdate" name="amountTimeStart" id="" onClick="WdatePicker()" readOnly>
						</span>
						<span class="sp">
						   <input type="text" class="text Wdate" name="amountTimEnd" id="" onClick="WdatePicker()" readOnly>
						</span>						
						<span class="btn search">查 询</span>
					</li>
				</ul>
				</form>
			</div>
			 <div class="page_tab_box">
                <div class="page_tab">
                    <div class="pageTab">
                        <span class="cur"><a href="${base}/appointmentPush/index.xhtml?mid=${mid}&pid=${pid}">全部</a></span>
                        <span ><a href="${base}/appointmentPush/index.xhtml?mid=${mid}&pid=${pid}&status=0">待抢单</a></span>
                        <span ><a href="${base}/appointmentPush/index.xhtml?mid=${mid}&pid=${pid}&status=10">已应单</a></span>
                        <span ><a href="${base}/appointmentPush/index.xhtml?mid=${mid}&pid=${pid}&status=11">取消</a></span>
                        <span ><a href="${base}/appointmentPush/index.xhtml?mid=${mid}&pid=${pid}&status=15">待应单</a></span>
                        <span ><a href="${base}/appointmentPush/index.xhtml?mid=${mid}&pid=${pid}&status=20">预约量房</a></span>
                        <span ><a href="${base}/appointmentPush/index.xhtml?mid=${mid}&pid=${pid}&status=30">已量房</a></span>
                        <span ><a href="${base}/appointmentPush/index.xhtml?mid=${mid}&pid=${pid}&status=40">已签约</a></span>
                        <span ><a href="${base}/appointmentPush/index.xhtml?mid=${mid}&pid=${pid}&status=50">已付款</a></span>
                        <span ><a href="${base}/appointmentPush/index.xhtml?mid=${mid}&pid=${pid}&status=60">已付定金</a></span>
                        <span ><a href="${base}/appointmentPush/index.xhtml?mid=${mid}&pid=${pid}&status=70">已结束</a></span>
                    </div>
                </div>
            </div>
			<table class="format">
				<thead>
					<th width="5%">推送编号</th>
					<th width="5%">预约单编号</th>
					<th width="15%">推送商家</th>
					<th width="10%">预约用户</th>
					<th width="5%">状态</th>
					<th width="10%">创建时间</th>
					<th width="10%">应单时间</th>
					<th width="10%">量房时间</th>
					<th width="20%">取消原因</th>					
					<th width="10%">操作</th>				
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td>${item.id}</td>
						<td>${item.aid}</td>
						<td>${item.spName}</td>
						<td>${item.nickName}</td>
						<td>${item.statusName}</td>
						<td>${item.createTimeHandle}</td>
						<td>${item.singleTimeHandle}</td>
						<td>${item.amountTimeHandle}</td>	
						<td>${item.reason}</td>															
			            <td>
			            [#if item.random==1||item.random==2||item.status==70] 
			              <span style="padding: 4px 12px; background: #cccccc; border-radius: 3px;" >关闭</span>	
			             [#else]
			             <span class="btn del" rel="${base}/appointmentPush/close.do?id=${item.id}">关闭</span>
			             [/#if]
			             
			              [#if item.random==1||item.random==2||item.status==70]			             
			                 <span class="btn del" rel="${base}/appointmentPush/rePush.do?id=${item.id}&aid=${item.aid}">推送</span>
			              [#else]
			                 <span style="padding: 4px 12px; background: #cccccc; border-radius: 3px;" >推送</span>	
			              [/#if]
			            <td>
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
		<tr>
			<td><%=result.elements[i].id%></td>
			<td><%=result.elements[i].aid%></td>
			<td><%=result.elements[i].spName%></td>
			<td><%=result.elements[i].nickName%></td>
			<td><%=result.elements[i].statusName%></td>
			<td><%=result.elements[i].createTimeHandle%></td>
			<td><%=result.elements[i].singleTimeHandle%></td>
			<td><%=result.elements[i].amountTimeHandle%></td>	
			<td><%=result.elements[i].reason%></td>											
		    <td>
		      <% if(result.elements[i].random==1||result.elements[i].random==2||result.elements[i].status==70){%>
		        <span style="padding: 4px 12px; background: #cccccc; border-radius: 3px;" >关闭</span>			         
		      <%}else{%>
                <span class="btn del" rel="${base}/appointmentPush/close.do?id=<%=result.elements[i].id%>">关闭</span>		      
		      <%}%>
		      
		     <% if(result.elements[i].random==1||result.elements[i].random==2||result.elements[i].status==70){%>
               <span class="btn del" rel="${base}/appointmentPush/rePush.do?id=<%=result.elements[i].id%>&aid=<%=result.elements[i].aid%>">推送</span>
             <%}else{%>
                 <span style="padding: 4px 12px; background: #cccccc; border-radius: 3px;" >推送</span>	
             <%}%>
            <td>
		</tr>	
	<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/appointmentPush/pagination.do',
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
		case "10":$('.pageTab span:eq(2)').addClass('cur');break;
		case "11":$('.pageTab span:eq(3)').addClass('cur');break;
		case "20":$('.pageTab span:eq(4)').addClass('cur');break;
		case "30":$('.pageTab span:eq(5)').addClass('cur');break;
		case "40":$('.pageTab span:eq(6)').addClass('cur');break;
		case "50":$('.pageTab span:eq(7)').addClass('cur');break;
		case "60":$('.pageTab span:eq(8)').addClass('cur');break;
		default:$('.pageTab span:eq(0)').addClass('cur');break;
	}
});	
</script>
</body>
</html>