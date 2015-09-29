<!DOCTYPE HTML>
<html>
<head>
<title>红包列表</title>
<meta charset="utf-8">
[#include "/common/res.ftl"]
	<script type="text/javascript" src="${base}/scripts/form.js"></script>
	<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<style>
	.add{margin-bottom:10px;float:right}
</style>
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
						<ul>					
							<li>
								<span class="sp"><strong>红包名称：</strong><input type="text" class="text" name="name"></span>
								<span class="btn search">查 询</span>
							</li>
						</ul>
						</form>
					</div>	
				    <div class="add">
				    	<a class="obtn" href="${base}/admin/redPackage/edit.xhtml?mid=${mid}&pid=${pid}">添加</a>
				    </div>
				  	<table class="format">
						<thead>
							<th width="15%">红包名称</th>
							<th width="5%">适用平台</th>
							<th width="5%">金额</th>
							<th width="10%">满减规则</th>
							<!--<th width="5%">品类限制</th>-->
							<th width="7%">每个账号限领个数</th>
							<th width="10%">是否支持拆分</th>
							<th width="15%">时间期限</th>
							<th width="7%">支付使用条件</th>
							<th width="5%">已发数</th>
							<th width="5%">已发金额</th>
							<th width="5%">已使用金额</th>
							<th width="7%">操作</th>
						</thead>
						<tbody>
							[#list pagination.dataList as item]
							<tr>
								<td>${item.name}</td>	
								<td>${item.platformString}</td>	
								<td>${item.price}</td>	
								<td>满${item.startMoney}可使用</td>	
								<!--<td>${item}</td>-->
								<td>${item.limitNum}</td>	
								<td>${item.isSplitString}</td>	
								<td>${item.beginTimeString}——${item.endTimeString}</td>	
								<td>${item.useConditionString}</td>	
								<td>${item.sendCount}</td>	
								<td>${item.sendMoneyCount}</td>	
								<td>${item.usedMoneyCount}</td>	
								<td>
									[#if item.sendCount == 0 || item.sendMoneyCount == item.usedMoneyCount]
										<a class="obtn" href="${base}/admin/redPackage/edit.xhtml?id=${item.id}&mid=${mid}&pid=${pid}">修改</a>
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
		<td><%=result.elements[i].name%></td>	
		<td><%=result.elements[i].platformString%></td>	
		<td><%=result.elements[i].price%></td>	
		<td>满<%=result.elements[i].startMoney%>可使用</td>	
		<td><%=result.elements[i].limitNum%></td>	
		<td><%=result.elements[i].isSplitString%></td>	
		<td><%=result.elements[i].beginTimeString%>——<%=result.elements[i].endTimeString%></td>	
		<td><%=result.elements[i].useConditionString%></td>	
		<td><%=result.elements[i].sendCount%></td>	
		<td><%=result.elements[i].sendMoneyCount%></td>	
		<td><%=result.elements[i].usedMoneyCount%></td>	
		<td>
			<% if(result.elements[i].sendCount == 0 || result.elements[i].sendMoneyCount == result.elements[i].usedMoneyCount){ %>
				<a class="obtn" href="${base}/admin/redPackage/edit.xhtml?id=<%=result.elements[i].id%>">修改</a>
			<%}%>
		</td>	
    </tr>	
<%}%>

</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/admin/redPackage/pagination.do',
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