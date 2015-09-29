<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>微信引流活动管理</title>
[#include "/common/res.ftl"]

<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
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
								<div style="float:right;margin-bottom:5px;">
								    <span class="btn">
									    <a href="${base}/engineeringStage/oneTwoStage.xhtml?mid=${mid}&pid=${pid}">一、二级节点管理</a>
									</span>
									<span class="btn">
									    <a href="${base}/engineeringStage/stageThreeAdd.xhtml?mid=${mid}&pid=${pid}">创建三级节点</a>
									</span>
								</div>			
								<table class="format">
									<thead>
										<th width="5%">活动编号</th>
										<th width="15%">活动标题</th>
										<th width="15%">举办地点</th>
										<th width="5%">活动状态</th>
										<th width="15%">开始时间</th>	
										<th width="15%">结束时间</th>	
										<th width="15%">更新时间</th>			
										<th width="10%">操作</th>			
									</thead>
									<tbody>
										[#list pagination.dataList as item]
										<tr>
											<td>${item.id}</td>		
											<td>${item.title}</td>
											<td>${item.addr}</td>
											<td>${item.statusVal}</td>
											<td>${item.beginTimeHandle}</td>	
											<td>${item.endTimeHandle}</td>	
											<td>${item.updateTimeHandle}</td>	
											<td>
											    <span class="btn">
											    	<a href="${base}/engineeringStageMx/index.xhtml?ppid=${item.id}&pid=${pid}&mid=${mid}">修改</a>						   
											    </span>
											    <span class="btn">
											    	<a href="${base}/engineeringStageMx/index.xhtml?ppid=${item.id}&pid=${pid}&mid=${mid}">详情</a>						   
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

</body>

<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr>
		<td><%=result.elements[i].id%></td>		
		<td><%=result.elements[i].title%></td>
		<td><%=result.elements[i].addr%></td>
		<td><%=result.elements[i].statusVal%></td>
		<td><%=result.elements[i].beginTimeHandle%></td>	
		<td><%=result.elements[i].endTimeHandle%></td>	
		<td><%=result.elements[i].updateTimeHandle%></td>
		<td> 
			<span class="btn">
				<a href="${base}/engineeringStage/detail.xhtml?pid=<%=result.elements[i].id %>&pid=${pid}&mid=${mid}">验收节点</a>						   
		    </span>
		    <span class="btn">
				<a href="${base}/engineeringStage/detail.xhtml?pid=<%=result.elements[i].id %>&pid=${pid}&mid=${mid}">验收节点</a>						   
		    </span>
	    </td>
    </tr>	
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/activityTheme/pagination.do',
		deal:{'click':'.search'},
		send:function(){
			var data={
					pageSize:1,
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