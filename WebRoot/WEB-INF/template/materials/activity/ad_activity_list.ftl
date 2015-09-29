<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>众筹活动列表</title>
[#include "/common/res.ftl"]

<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/form.js"></script>
<Style>
	tr{
		padding-top:10px;
	}
	td{height:55px;font-size:14px;}
	.td_left{text-align:right;font-size:15px;font-weight:bold;}
	#table_detail{mangin:50px;}
	.requi{color:red};
</style>
<script>
	function submit(el){
		$("#form").submit();
	}
	
	$(function(){
		$(".qxAll").click(function(){
			$(".ck").prop("checked",$(this).prop("checked"));
		});
		$(".fxAll").click(function(){
			$(".ck").each(function(){
				$(this).prop("checked",!($(this).prop("checked")))
			});
		});
	});
	
</script>
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
						<span class="sp"><strong class="auto">商品名称：</strong><input type="text" class="text" name="name"></span>
						<span class="btn search">查 询</span>
					</li>
				</ul>
				</form>
			</div>
			<div class="page_tab_box">
                <div class="page_tab">
                    <div class="pageTab">
                        <span class="cur"><a href="${base}/material/activity/index.xhtml?url=ad_activity_list&mid=${mid}&pid=${pid}&status=0">待审核</a></span>
                        <span><a href="${base}/material/activity/index.xhtml?url=ad_activity_list&mid=${mid}&pid=${pid}&status=1">已通过</a></span>
                        <span><a href="${base}/material/activity/index.xhtml?url=ad_activity_list&mid=${mid}&pid=${pid}&status=-1">未通过</a></span>
                    </div>
                </div>
            </div>
			<div style="margin-top:-20px;">
				<input type="radio" name="choose" class="qxAll" />全选&nbsp;&nbsp;
				<input type="radio" name="choose" class="fxAll" />反选&nbsp;&nbsp;
				<span class="btn" onclick="submit(this)">通过审核</span>
			</div>
            <form id="form" method="post" action="${base}/materials/item/batchHandle.xhtml?op=1">
				<table class="format">
					<thead>
						<th width="3%"></th>
						<th width="20%">商品名称</th>
						<th width="10%">商家名称</th>
						<th width="10%">活动名称</th>
						<th width="8%">开始时间</th>
						<th width="8%">结束时间</th>
						<th width="5%">活动状态</th>
						<th width="5%">审核状态</th>
						<th width="5%">用户参与名单</th>
						<th width="5%">操作</th>				
					</thead>
					<tbody>
						[#list pagination.dataList as item]
						<tr>
							<td><input name="ids" value="${item.id}" type="checkbox" class="ck" /></td>
							<td>${item.title}</td>
							<td>${item.spName}</td>
							<td>${item.activityName}</td>
							<td>${item.startTimeString}</td>
							<td>${item.endTimeString}</td>
							<td>${item.flagString}</td>
							<td>${item.statusString}</td>
							<td>
								<a class="obtn" href="${base}/material/itemMember/participation.xhtml?id=${item.itemId}&url=ad_partici_list&mid=${mid}&pid=${pid}">查看</a>
							</td>
							<td>
								<a class="obtn" href="${base}/materials/item/view.xhtml?id=${item.id}&mid=${mid}&pid=${pid}">审核</a>
							</td>
						</tr>
						[/#list]
					</tbody>
			</table>
		</form>
		[#include "/common/pagination.ftl"]
				<!--内容放这里 end-->
				 </div>
	       </div>
			[#include "/common/menu.ftl"]
		</div>
		<!-- footer -->
	[#include "/common/foot.ftl"]
</body>

    
<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr menuid="<%=result.elements[i].id%>">
		<td><input type="checkbox" class="ck" /></td>
		<td><%=result.elements[i].title%></td>
		<td><%=result.elements[i].spName%></td>
		<td><%=result.elements[i].activityName%></td>
		<td><%=result.elements[i].startTimeString%></td>
		<td><%=result.elements[i].endTimeString%></td>
		<td><%=result.elements[i].flagString%></td>
		<td><%=result.elements[i].statusString%></td>
		<td>
			<a class="obtn" href="${base}/material/itemMember/participation.xhtml?id=<%=result.elements[i].itemId%>&url=ad_partici_list&mid=${mid}&pid=${pid}">查看</a>
		</td>
		<td>
			<a class="obtn" href="${base}/materials/item/view.xhtml?id=<%=result.elements[i].id%>&mid=${mid}&pid=${pid}">审核</a>
			<!--<%if(result.elements[i].status==0){%>
				
			<%}%>-->
		</td>
	</tr>
<%}%>
</script>
<script>
	
$(function(){
	$('.ui-paging').page({
		url:'${base}/material/activity/pagination.do?url=${url}&status=${status}',
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
		case "0":$('.pageTab span:eq(0)').addClass('cur');break;
		case "1":$('.pageTab span:eq(1)').addClass('cur');break;
		case "-1":$('.pageTab span:eq(2)').addClass('cur');break;
		default:$('.pageTab span:eq(0)').addClass('cur');break;
	}

});	

</script>
</html>