<!DOCTYPE HTML>
<html>
<head>
<title>钱包交易列表</title>
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
					<table class="format">
						<thead>
							<th width="10%">交易号</th>
							<th width="20%">商家名称</th>
							<th width="10%">交易金额(单位:元)</th>	
							<th width="10%">交易状态</th>	
							<th width="15%">交易时间</th>	
						</thead>
						<tbody>
							[#list pagination.dataList as item]
							<tr>
								<td>${item.id}</td>	
								<td>${item.spName}</td>
								<td>${item.price}</td> 
								<td>${item.statusVal}</td> 
								<td>${item.createTimeHandle}</td> 
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
		<td><%=result.elements[i].spName%></td>
		<td><%=result.elements[i].price%></td> 
		<td><%=result.elements[i].statusVal%></td> 
		<td><%=result.elements[i].createTimeHandle%></td>
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
});	
</script>

</html>