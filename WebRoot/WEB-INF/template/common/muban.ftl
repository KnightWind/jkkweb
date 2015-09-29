<!DOCTYPE HTML>
<html>
<head>
<title>管理后台模板</title>
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
									<span class="sp"><strong>预约编号：</strong><input type="text" class="text" name="id" id=""></span>
								    <span class="sp"><strong>用户昵称：</strong><input type="text" class="text" name="nickname" id=""></span>						
								    <span class="btn search">查 询</span>
								</li>
							</ul>
						</form>
					</div>
				
					 <div class="page_tab_box">
		                <div class="page_tab">
		                    <div class="pageTab">
		                        <span class="cur"><a href="${base}/appointment/index.xhtml?pid=${pid}&mid=${mid}">全部预约</a></span>
		                    </div>
		                </div>
	                </div>
					<table class="format">
						<thead>
							<th width="10%">交易号</th>
						</thead>
						<tbody>
							[#list pagination.dataList as item]
							<tr>
								<td>${item.id}</td>	
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
    </tr>	
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/adminZcjs/pagination.do',
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
		case "10":$('.pageTab span:eq(1)').addClass('cur');break;
		default:$('.pageTab span:eq(0)').addClass('cur');break;
	}
});	
</script>

</html>