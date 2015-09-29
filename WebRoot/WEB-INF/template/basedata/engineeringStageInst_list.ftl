<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>监理报告管理</title>
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
				<input type="hidden" name="reportPass" value="${reportPass}">
				<ul>					
					<li>
						<span class="sp"><strong>监理名称：</strong><input type="text" class="text" name="createUser" id=""></span>
					    <span class="sp"><strong>小区名称：</strong><input type="text" class="text" name="community" id=""></span>						
					</li>
					<li>
						<span class="sp"><strong>用户验收日期：</strong><input type="text" class="text Wdate" name="passTimeStart" id="" onClick="WdatePicker()" readOnly></span>
						<span class="sp"><input type="text" class="text Wdate" name="passTimeEnd" id="" onClick="WdatePicker()" readOnly></span>											
					</li>
					<li>
						<span class="sp"><strong>报告提交日期：</strong><input type="text" class="text Wdate" name="createTimeStart" id="" onClick="WdatePicker()" readOnly></span>
						<span class="sp"><input type="text" class="text Wdate" name="createTimeEnd" id="" onClick="WdatePicker()" readOnly></span>											
					    <span class="btn search">查 询</span>
					</li>
				</ul>
				</form>
			</div>
			
			 <div class="page_tab_box">
                <div class="page_tab">
                    <div class="pageTab">
                        <span class="cur"><a href="${base}/engineeringStageInst/index.xhtml?pid=${pid}&mid=${mid}">全部</a></span>
                        <span ><a href="${base}/engineeringStageInst/index.xhtml?pid=${pid}&mid=${mid}&reportPass=1">已验收</a></span>
                        <span ><a href="${base}/engineeringStageInst/index.xhtml?pid=${pid}&mid=${mid}&reportPass=0">未验收</a></span>
                    </div>
                </div>
            </div>
			
			<table class="format">
				<thead>
					<th width="5%">编号</th>
					<th width="10%">小区名称</th>
					<th width="10%">阶段</th>
					<th width="10%">是否通过</th>
					<th width="10%">监理名称</th>
					<th width="10%">用户验收</th>	
					<th width="15%">用户验收时间</th>	
					<th width="15%">报告提交时间</th>		
					<th width="5%">操作</th>	
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td>${item.id}</td>		
						<td>${item.community}</td>
						<td>${item.stagName}</td>
						<td>${item.finishFlagVal}</td>	
						<td>${item.createUser}</td>	
						<td>${item.reportPassVal}</td>	
						<td>${item.passTimeHandle}</td>	
						<td>${item.createTimeHandle}</td>	
						<td>
						    <span class="btn">
						    	<a href="${base}/engineeringStageInst/detail.xhtml?id=${item.id}&pid=${pid}&mid=${mid}">详情</a>						   
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
	<tr>
	    <td><%=result.elements[i].id%></td>		
		<td><%=result.elements[i].community%></td>
		<td><%=result.elements[i].stagName%></td>
		<td><%=result.elements[i].finishFlagVal%></td>	
		<td><%=result.elements[i].createUser%></td>	
		<td><%=result.elements[i].reportPassVal%></td>	
		<td><%=result.elements[i].passTimeHandle%></td>	
		<td><%=result.elements[i].createTimeHandle%></td>	
		<td> 
			 <span class="btn">
				<a href="${base}/engineeringStageInst/detail.xhtml?id=<%=result.elements[i].id%>&pid=${pid}&mid=${mid}">详情</a>						   
			 </span>
	    </td>
    </tr>	
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/engineeringStageInst/pagination.do',
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
	
	var status=window.location.href.split('reportPass=')[1];
	$('.pageTab span.cur').removeClass('cur');
	switch(status){
		case "0":$('.pageTab span:eq(2)').addClass('cur');break;
		case "1":$('.pageTab span:eq(1)').addClass('cur');break;
		default:$('.pageTab span:eq(0)').addClass('cur');break;
	}
	
});	
</script>

</body>
</html>