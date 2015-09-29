<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>引流活动抽奖管理</title>
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
				
				<div class="tools white border mb10 sp10 clr">
					<form id="search" action="">
					<ul>
						<li>
							<span class="sp"><strong>手机号：</strong><input type="text" class="text" name="phone" id=""></span>
							<span class="sp"><strong>奖品：</strong><input type="text" class="text" name="name" id=""></span>
							<span class="btn search">查 询</span>
						</li>
					</ul>
					</form>
				</div>			
				
				<div class="page_tab_box">
	                <div class="page_tab">
	                    <div class="pageTab">
	                        <span ><a href="${base}/wxActivityLotteryLog/takeAward.xhtml?mid=${mid}&pid=${pid}&status=0">未领取</a></span>
	                        <span ><a href="${base}/wxActivityLotteryLog/takeAward.xhtml?mid=${mid}&pid=${pid}&status=1">已领取</a></span>
	                    </div>
	                </div>
	            </div>		
			
				<table class="format">
					<thead>
						<th width="15%">编号</th>
						<th width="15%">手机号</th>
						<th width="15%">奖品</th>
						<th width="15%">是否领取</th>
						<th width="15%">抽奖时间</th>
						<th width="15%">领奖时间</th>
						<th width="7%">操作</th>
					</thead>
					<tbody>
						[#list pagination.dataList as item ]
						<tr>
						    <td>${item.id}</td>
						    <td>${item.phone}</td>
							<td>${item.awardContent}</td>
							<td>${item.receiveVal}</td>
							<td>${item.createTimeHandle}</td>
							<td>${item.receiveTimeHandle}</td>
							<td>
								[#if item.receive == 0 || item.receive == null]
									<a onclick="return confirm('确认兑换吗?')" class="obtn" href="${base}/wxActivityLotteryLog/take.xhtml?id=${item.id}&mid=${mid}&pid=${pid}">兑换</a>
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
<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr>
		<td><%=result.elements[i].id%></td>
	    <td><%=result.elements[i].phone%></td>
		<td><%=result.elements[i].awardContent%></td>
		<td><%=result.elements[i].receiveVal%></td>
		<td><%=result.elements[i].createTimeHandle%></td>
		<td><%=result.elements[i].receiveTimeHandle%></td>
		<td>
			<%if(result.elements[i].receive == 0 || result.elements[i].receive == null){%>
				<a onclick="return confirm('确认兑换吗?')" class="obtn" href="${base}/wxActivityLotteryLog/take.xhtml?id=<%=result.elements[i].id%>&mid=${mid}&pid=${pid}">兑换</a>
			<%}%>
		</td>
	</tr>
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/wxActivityLotteryLog/takeAwardPagination.do?status=${status}',
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
	var status=window.location.href.split('status=')[1];
	$('.pageTab span.cur').removeClass('cur');
	switch(status){
		case "0":$('.pageTab span:eq(0)').addClass('cur');break;
		case "1":$('.pageTab span:eq(1)').addClass('cur');break;
		default:$('.pageTab span:eq(0)').addClass('cur');break;
	}
})
</script>
</html>