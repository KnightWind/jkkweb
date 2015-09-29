<!DOCTYPE HTML>
<html>
<head>
<title>设计师-用户投诉列表</title>
<meta charset="utf-8">
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
				<input type="hidden" name="type" value="${type}">
				<ul>					
					<li>
						<span class="sp"><strong >业主：</strong><input type="text" class="text" name="mnickName" id=""></span>
						<span class="sp"><strong class="auto">设计师：</strong><input type="text" class="text" name="snickName" id=""></span>
					</li>					
					<li>
						<strong>状态：</strong>
					    <span class="sp radio">
						    <input type="radio" name="status" value=""/> 全部 
						   	<input type="radio" name="status" value="1"/> 已解决 
						   	<input type="radio" name="status" value="2"/> 待处理 					   
					    </span>
					    <strong class="auto">优先级：</strong>				 
					   	<span class="sp radio">
						   	<input type="radio" name="level" value=""/> 全部 
						   	<input type="radio" name="level" value="1"/> 低 
						   	<input type="radio" name="level" value="2"/> 中等 
						   	<input type="radio" name="level" value="3"/> 高 
					   	</span>				
					</li>
					<li>
						 <span class="sp"><strong>创建时间：</strong><input type="text" class="text Wdate" name="createBegin" id="" onClick="WdatePicker()" readOnly></span>
						 <span class="sp"><input type="text" class="text Wdate" name="createEnd" id="" onClick="WdatePicker()" readOnly></span>
					</li>
					<li>
						 <span class="sp"><strong>更新时间：</strong><input type="text" class="text Wdate" name="updateBegin" id="" onClick="WdatePicker()" readOnly></span>
						 <span class="sp"><input type="text" class="text Wdate" name="updateEnd" id="" onClick="WdatePicker()" readOnly></span>
						 <span class="btn search">查 询</span>
					</li>
				</ul>
				</form>
			</div>	
			
			<div class="page_tab_box">
                <div class="page_tab">
                    <div class="pageTab">
                        <span class="cur"><a href="${base}/staffComplain/index.xhtml?pid=${pid}&mid=${mid}">全部</a></span>
                        <span ><a href="${base}/staffComplain/index.xhtml?pid=${pid}&mid=${mid}&type=2">设计师投诉业主</a></span>
                        <span ><a href="${base}/staffComplain/index.xhtml?pid=${pid}&mid=${mid}&type=1">业主投诉设计师</a></span>                                              
                    </div>
                </div>
            </div>
			
			<table class="format">
				<thead>					
					<th width="10%">用户</th>
					<th width="15%">设计师</th>
					<th width="5%">级别</th>
					<th width="5%">状态</th>
					<th width="10%">类型</th>
					<th width="10%">来源</th>
					<th width="15%">创建时间</th>
					<th width="15%">更新时间</th>				
					<th width="5%">受理人</th>
					<th width="10">操作</th>			
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>						
						<td>${item.mnickName}</td>
						<td>${item.spName}</td>
						<td>${item.levelVal}</td>
						<td>${item.statusVal}</td>
						<td>${item.typeVal}</td>
						<td>${item.sourceVal}</td>
						<td>${item.createTimeHandle}</td>		
						<td>${item.updateTimeHandle}</td>										
						<td>${item.anickName}</td>
						<td>
	                        [#if item.status==1]
							    <span class="btn"><a href="detail.xhtml?pid=${pid}&mid=${mid}&id=${item.id}&scId=${item.id}">查看</a></span>				
							    <span style="padding: 4px 12px; background: #cccccc; border-radius: 3px;" >关闭</span>	
							[#else]			
							    <span class="btn"><a href="detail.xhtml?pid=${pid}&mid=${mid}&id=${item.id}&scId=${item.id}">跟进</a></span>				
							    <span class="btn del" rel="${base}/staffComplain/close.do?id=${item.id}">关闭</span>
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
</body>
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr>
		<td><%=result.elements[i].mnickName%></td>
		<td><%=result.elements[i].spName%></td>
		<td><%=result.elements[i].levelVal%></td>
		<td><%=result.elements[i].statusVal%></td>
		<td><%=result.elements[i].typeVal%></td>
		<td><%=result.elements[i].sourceVal%></td>
		<td><%=result.elements[i].createTimeHandle%></td>		
		<td><%=result.elements[i].updateTimeHandle%></td>										
		<td><%=result.elements[i].anickName%></td>
		<td>
		<% if(result.elements[i].status==1){%>
	        <span class="btn"><a href="detail.xhtml?pid=${pid}&mid=${mid}&id=<%=result.elements[i].id%>&scId=<%=result.elements[i].id%>">查看</a></span>				
			<span style="padding: 4px 12px; background: #cccccc; border-radius: 3px;" >关闭</span>	
		<%}%>
		   
		<% if(result.elements[i].status==2){%>
	        <span class="btn"><a href="detail.xhtml?pid=${pid}&mid=${mid}&id=<%=result.elements[i].id%>&scId=<%=result.elements[i].id%>">跟进</a></span>				
			<span class="btn del" rel="${base}/staffComplain/close.do?id=<%=result.elements[i].id%>">关闭</span>
		<%}%>  
		</td>
	</tr>
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/staffComplain/pagination.do',
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
	
	var status=window.location.href.split('type=')[1];
	$('.pageTab span.cur').removeClass('cur');
	switch(status){
		case "2":$('.pageTab span:eq(1)').addClass('cur');break;
		case "1":$('.pageTab span:eq(2)').addClass('cur');break;		
		default:$('.pageTab span:eq(0)').addClass('cur');break;
	}
	
})
</script>
</html>