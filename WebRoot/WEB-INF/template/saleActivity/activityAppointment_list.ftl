<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>引流活动装修预约</title>
[#include "/common/res.ftl"]
<link rel="stylesheet" type="text/css" href="${base}/styles/admin-list.css">
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
				<table class="format">
					<thead>
						<th width="10%">编号</th>
						<th width="10%">用户昵称</th>
						<th width="10%">手机号</th>
						<th width="10%">小区</th>
						<th width="10%">量房时间</th>
						<th width="10%">房屋类型</th>
						<th width="10%">面积(单位:m²)</th>
						<th width="10%">装修预算(单位:元)</th>
						<th width="15%">创建时间</th>
						<th width="10%">操作</th>
					</thead>
					<tbody>
						[#list pagination.dataList as item ]
						<tr>
						    <td>${item.id}</td>
						    <td>${item.user}</td>
							<td>${item.mobile}</td>
							<td>${item.community}</td>
							<td>${item.reviewTimeHandle2}</td>
							<td>${item.houseTypeVal}</td>
							<td>${item.space}</td>
							<td>${item.budget}</td>
							<td>${item.createTimeHandle}</td>	
							<td>
							   <span class="btn modify" rel=["${item.id}","${item.mobile}","${item.community}","${item.reviewTimeHandle2}","${item.houseType}","${item.space}","${item.budget}","${item.tradeNo}"]>编辑</span> 
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
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/form.js"></script>
<script type="text/html" id="pannel">
<div class="ui-dialog addManager">
	<h2><span class="close"></span><strong>预约单编辑</strong></h2>
	<form id="send" action="save.do">
	<input type="hidden" name="id">
		<ul>
			<li><strong> 手机号码：</strong> <input type="text" class="text" name="mobile" placeholder="手机号码"></li>
			<li><strong> 小区：</strong> <input type="text" class="text" name="community" placeholder="小区"></li>
			<li><strong> 装修时间：</strong> <input type="text" class="text Wdate" name="reviewTime" id="" onClick="WdatePicker()" readOnly></li>
			<li><strong><b class="red">*</b> 房屋用途</strong> 
				<select name="houseType" style="float:left;width:210px;">
					<option value="">请选择</option>
					<option value="1">出租</option>
					<option value="2">自用</option>
					<option value="3">婚房</option>
					<option value="4">儿童房</option>
					<option value="5">会所</option>
					<option value="6">工装</option>
				</select>
			</li>
			<li><strong> 房屋面积：</strong> <input type="text" class="text" name="space" placeholder="房屋面积">m²</li>
			<li><strong> 装修预算：</strong> <input type="text" class="text" name="budget" placeholder="装修预算">元</li>
			<li class="btn"><span class="btn save" rel="add">提交保存</span></li>
		</ul>
	</form>
</div>	
</script>
<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr>
		<td><%=result.elements[i].id%></td>
	    <td><%=result.elements[i].user%></td>
		<td><%=result.elements[i].mobile%></td>
		<td><%=result.elements[i].community%></td>
		<td><%=result.elements[i].reviewTimeHandle2%></td>
		<td><%=result.elements[i].houseTypeVal%></td>
		<td><%=result.elements[i].space%></td>
		<td><%=result.elements[i].budget%></td>
		<td><%=result.elements[i].createTimeHandle%></td>	
		<td>
		   <span class="btn modify" rel=["<%=result.elements[i].id%>","<%=result.elements[i].mobile%>","<%=result.elements[i].community%>","<%=result.elements[i].reviewTimeHandle2%>","<%=result.elements[i].houseTypeVal%>","<%=result.elements[i].space%>","<%=result.elements[i].budget%>","<%=result.elements[i].tradeNo%>"]>编辑</span> 
		</td>
	</tr>
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/wxActivityAppointment/pagination.do',
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