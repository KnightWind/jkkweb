<!DOCTYPE HTML>
<html>
<head>
<title>平台退款列表</title>
<meta charset="utf-8">
     [#include "/common/res.ftl"]
	<script type="text/javascript" src="${base}/scripts/form.js"></script>
	<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
</head>

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
									<span class="sp"><strong>用户昵称：</strong><input type="text" class="text" name="nickName" id=""></span>
								    <span class="sp"><strong>用户手机：</strong><input type="text" class="text" name="mobile" id=""></span>						
								    <span class="btn search">查 询</span>
								</li>
							</ul>
						</form>
					</div>
				
					<div class="page_tab_box">
		                <div class="page_tab">
		                    <div class="pageTab">
		                        <span class="cur"><a href="${base}/refund/index.xhtml?pid=${pid}&mid=${mid}">全部</a></span>
		                        <span class="cur"><a href="${base}/refund/index.xhtml?pid=${pid}&mid=${mid}&status=0">待审核</a></span>
		                        <span class="cur"><a href="${base}/refund/index.xhtml?pid=${pid}&mid=${mid}&status=1">审核通过</a></span>
		                        <span class="cur"><a href="${base}/refund/index.xhtml?pid=${pid}&mid=${mid}&status=2">审核不通过</a></span>
		                    </div>
		                </div>
	                </div>
					<table class="format">
						<thead>
							<th width="5%">编号</th>
							<th width="10%">用户</th>
							<th width="15%">用户手机</th>
							<th width="10%">退款金额</th>
							<th width="5%">退款类型</th>
							<th width="15%">申请日期</th>
							<th width="10%">审核状态</th>
							<th width="10%">审核时间</th>
							<th width="10%">操作</th>
						</thead>
						<tbody>
							[#list pagination.dataList as item]
							<tr>
								<td>${item.id}</td>	
								<td>${item.nickName}</td>	
								<td>${item.mobile}</td>	
								<td>${item.applyAmount}</td>	
								<td>${item.refundTypeVal}</td>	
								<td>${item.applyDateHandle}</td>	
								<td>${item.statusName}</td>	
								<td>${item.auditDateHandle}</td>	
								<td>
								  <span class="btn"><a href="${base}/refund/refundDetail.xhtml?id=${item.id}&mid=${mid}&pid=${pid}">审核</a></span>
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
		<td><%=result.elements[i].nickName%></td>	
		<td><%=result.elements[i].mobile%></td>	
		<td><%=result.elements[i].applyAmount%></td>	
		<td><%=result.elements[i].refundTypeVal%></td>	
		<td><%=result.elements[i].applyDateHandle%></td>	
		<td><%=result.elements[i].statusName%></td>	
		<td><%=result.elements[i].auditDateHandle%></td>	
		<td>
		  <span class="btn"><a href="${base}/refund/refundDetail.xhtml?id=<%=result.elements[i].id%>&mid=${mid}&pid=${pid}">审核</a></span>
		</td>	
    </tr>	
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/refund/pagination.do',
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
		case "2":$('.pageTab span:eq(3)').addClass('cur');break;
		default:$('.pageTab span:eq(0)').addClass('cur');break;
	}
});	
</script>
</html>