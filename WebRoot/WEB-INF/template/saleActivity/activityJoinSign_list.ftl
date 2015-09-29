<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>引流活动报名</title>
[#include "/common/res.ftl"]
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
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
					<ul>					
						<li>
							<span>
								<strong>手机号码：</strong>
							   <input type="text" class="text" name="phone" />
							</span>						
							<span class="btn search">查 询</span>
						</li>
					</ul>
				</form>
			</div>
				<table class="format">
					<thead>
						<th width="10%">编号</th>
						<th width="10%">用户昵称</th>
						<th width="10%">手机号</th>
						<th width="10%">性别</th>
						<th width="10%">小区名称</th>
						<th width="10%">报名时间</th>
						<th width="8%">签到时间</th>
						<th width="10%">装修需求</th>
						<th width="8%">跟进客服</th>
					</thead>
					<tbody>
						[#list pagination.dataList as item ]
						<tr>
						    <td>${item.id}</td>
						    <td>${item.name}</td>
							<td>${item.phone}</td>
							<td>${item.sexVal}</td>
							<td>${item.community}</td>
							<td>${item.joinTimeHandle}</td>
							<td>${item.signTimeHandle}</td>	
							<td>${item.decorationVal}</td>
							<td>${item.nickname}</td>
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
	    <td><%=result.elements[i].name%></td>
		<td><%=result.elements[i].phone%></td>
		<td><%=result.elements[i].sexVal%></td>
		<td><%=result.elements[i].community%></td>
		<td><%=result.elements[i].joinTimeHandle%></td>
		<td><%=result.elements[i].signTimeHandle%></td>	
		<td><%=result.elements[i].decorationVal%></td>
		<td><%=result.elements[i].nickname%></td>
	</tr>
<%}%>
</script>
<script>
$(function(){
	
    
	$('.ui-paging').page({
		url:'${base}/wxActivityJionSign/pagination.do',
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
</script>
</html>