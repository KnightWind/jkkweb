<!DOCTYPE HTML>
<html>
<head>
<title>0元购订单</title>
<meta charset="utf-8">
[#include "/common/res.ftl"]
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
						<span class="sp"><strong>0元验证码:</strong><input type="text" class="text" name="id" id=""></span>			
						<span class="sp"><strong class="auto">购买用户：</strong><input type="text" class="text" name="nickName" id=""></span>
					</li>
			     	<li>
					 <span class="sp"><strong>下单时间：</strong><input type="text" class="text Wdate" name="createTimeBegin" id="" onClick="WdatePicker()" readOnly></span>
					 <span class="sp"><input type="text" class="text Wdate" name="createTimeEnd" id="" onClick="WdatePicker()" readOnly></span>
					</li>
					<li>
						<strong>券类型：</strong>
					   	<span class="sp radio">
					   	<input type="radio" name="status" value=""/> 全部 
					   	<input type="radio" name="status" value="0"/> 未领取 
					   	<input type="radio" name="status" value="1"/> 已领取 					   
					   	</span>
					   	<span class="btn search">查 询</span>
					</li>
				</ul>
				</form>
			</div>
		
			<table class="format">
				<thead>
					<th width="8%">0元验证码</th>
					<th width="8%">购买用户</th>
					<th width="24%">商品名称</th>
					<th width="20%">所属商家</th>
					<th width="10%">购买金额</th>
					<th width="10%">购买时间</th>
					<th width="10%">消费时间</th>
					<th width="10%">使用状态</th>	
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td>${item.id}</td>
						<td>${item.nickName}</td>
						<td>${item.title}</td>					
						<td>${item.spName}</td>
						<td>0</td>
						<td>${item.createTimeHandle}</td>
						<td>${item.getTimeHandle}</td>
						<td>${item.statusHandle}</td>
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
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr>
	<td><%=result.elements[i].id%></td>
	<td ><%=result.elements[i].nickName%></td>
	<td ><%=result.elements[i].title%></td>
	<td ><%=result.elements[i].spName%></td>
	<td >0</td>
	<td ><%=result.elements[i].createTimeHandle%></td>
	<td ><%=result.elements[i].getTimeHandle%></td>
	<td ><%=result.elements[i].statusHandle%></td>
</tr>	
<%}%>
</script>

<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/orderFree/pagination.do',
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