<!DOCTYPE HTML>
<html>
<head>
<title>监理报告列表</title>
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
				<input type="hidden" name="word" value="${word}">
				<ul>					
					<li>
						<span class="sp"><strong >监理：</strong><input type="text" class="text" name="spName" id=""></span>
					</li>					
					<li>
						 <span class="sp"><strong>验收时间：</strong><input type="text" class="text Wdate" name="checkBegin" id="" onClick="WdatePicker()" readOnly></span>
						 <span class="sp"><input type="text" class="text Wdate" name="checkEnd" id="" onClick="WdatePicker()" readOnly></span>
						 <span class="btn search">查 询</span>
					</li>
				</ul>
				</form>
			</div>	
			
			<div class="page_tab_box">
                <div class="page_tab">
                    <div class="pageTab">
                        <span class="cur"><a href="${base}/supervisor/index.xhtml?pid=${pid}&mid=${mid}">全部</a></span>
                        <span ><a href="${base}/supervisor/index.xhtml?pid=${pid}&mid=${mid}&word=开工">开工交底</a></span>
                        <span ><a href="${base}/supervisor/index.xhtml?pid=${pid}&mid=${mid}&word=水电">水电验收</a></span>    
                        <span ><a href="${base}/supervisor/index.xhtml?pid=${pid}&mid=${mid}&word=瓦工">瓦木验收</a></span> 
                        <span ><a href="${base}/supervisor/index.xhtml?pid=${pid}&mid=${mid}&word=竣工">竣工验收</a></span>                                           
                    </div>
                </div>
            </div>
			
			<table class="format">
				<thead>					
					<th width="10%">装修小区</th>
					<th width="10%">监理</th>
					<th width="10%">阶段</th>
					<th width="20%">开工时间</th>
					<th width="20%">竣工时间</th>
					<th width="20%">验收时间</th>
					<th width="10">操作</th>			
				</thead>
			    <tbody>
					[#list pagination.dataList as item]
					<tr>						
						<td>${item.community}</td>
						<td>${item.spName}</td>
						<td>${item.stagName}</td>
						<td>${item.beginTimeHandle}</td>
						<td>${item.endTimeHandle}</td>
						<td>${item.checkTimeHandle}</td>
						<td><span class="btn"><a href="detail.xhtml?pid=${pid}&mid=${mid}&id=${item.id}">详情</a></span></td>		
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
		<td><%=result.elements[i].community%></td>
		<td><%=result.elements[i].spName%></td>
		<td><%=result.elements[i].stagName%></td>
		<td><%=result.elements[i].beginTimeHandle%></td>
		<td><%=result.elements[i].endTimeHandle%></td>
		<td><%=result.elements[i].checkTimeHandle%></td>
		<td>
		    <span class="btn"><a href="detail.xhtml?pid=${pid}&mid=${mid}&id=<%=result.elements[i].id%>">详情</a></span>				
		</td>
	</tr>
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/supervisor/pagination.do',
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
	
	var status=window.location.href.split('word=')[1];
	$('.pageTab span.cur').removeClass('cur');
	switch(status){
		case '开工':$('.pageTab span:eq(1)').addClass('cur');break;
		case '水电':$('.pageTab span:eq(2)').addClass('cur');break;	
		case '瓦工':$('.pageTab span:eq(3)').addClass('cur');break;
		case '竣工':$('.pageTab span:eq(4)').addClass('cur');break;		
		default:$('.pageTab span:eq(0)').addClass('cur');break;
	}
	
})
</script>
</html>