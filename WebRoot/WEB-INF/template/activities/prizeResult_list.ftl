<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>活动列表</title>
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
					    <span class="sp"><strong>手机号码：</strong><input type="text" class="text" name="phone" id=""></span>	
					    <span class="sp"><strong>奖项名称：</strong><input type="text" class="text" name="prizeName" id=""></span>					
					    <span class="sp">
						   <strong>领取状态：</strong>
						     <div class="select province mr10">
									<i class="new">&#xf107;</i><span>--请选择--</span>
									<p>
										<a value="">--请选择--</a>
		                                <a value="1">已领取</a>
		                                <a value="2">未领取</a>
									</p>					
								<input type="hidden" name="unclaimed" value="">
							</div>
						</span>
					   <span class="sp">
							<strong>开始时间：</strong>
						   <span class="sp"> <input type="text" class="text Wdate" name="start" id="" onClick="WdatePicker()" readOnly></span>
						</span>	
					   <span class="sp">
							<strong>结束时间：</strong>
						   <span class="sp"><input type="text" class="text Wdate" name="end" id="" onClick="WdatePicker()" readOnly></span>
						</span>	
					<span class="btn search">查 询</span>
					</li>
				</ul>
				</form>
			</div>
			<table class="format">
				<thead>
					<th width="5%">编号</th>
					<th width="8%">微信名</th>
					<th width="8%">手机</th>
					<th width="15%">奖项</th>
					<th width="15%">活动名称</th>					
					<th width="8%">参与时间</th>
					<th width="8%">状态</th>
					<th width="10%">操作</th>				
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td>${item.id}</td>
						<td>${item.nickname}</td>
						<td>${item.phone}</td>
						<td>${item.prizeName}</td>
						<td>${item.activeName}</td>
						<td>${item.createTimeString}</td>
						<td>${item.stauts}</td>
						<td>			
							<!--<a class="obtn" href="${base}/prizeResult/view.xhtml?id=${item.id}&mid=${mid}&pid=${pid}">详情</a>-->
							[#if item.isUnclaimed == 2]
							<span class="btn del" rel="${base}/prizeResult/changeStatus.do?id=${item.id}">已领取</span>
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
	<td><%=result.elements[i].nickname%></td>
	<td><%=result.elements[i].phone%></td>
	<td><%=result.elements[i].prizeName%></td>					
	<td><%=result.elements[i].activeName%></td>					
	<td><%=result.elements[i].createTimeString%></td>
	<td><%=result.elements[i].stauts%></td>
	<td>
		<!--<a class="obtn" href="${base}/prizeResult/view.xhtml?id=<%=result.elements[i].id%>&mid=${mid}&pid=${pid}">详情</a>-->
		<%if(result.elements[i].isUnclaimed ==2){%>
			<span class="btn del" rel="${base}/prizeResult/changeStatus.do?id=<%=result.elements[i].id%>">已领取</span>
		<%}%>
	</td>
</tr>	
<%}%>
</script>
<script>


$(function(){

	$('.ui-paging').page({
		url:'${base}/prizeResult/indexpage.do',
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
});	
</script>
</body>
</html>