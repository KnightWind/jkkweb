<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>购物清单分类管理</title>
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
			<table>
				<tr>
					<td>购买用户:</td>
					<td>${porder.createUser}</td>
				</tr>
				<tr>
					<td>购买时间:</td>
					<td>${porder.createTimeHandle}</td>
				</tr>
				<tr>
					<td>购买地:</td>
					<td>${porder.buy_addr}</td>
				</tr>
				<tr>
					<td>购买方式:</td>
					<td>${porder.buyType}</td>
				</tr>
				<tr>
					<td>购买地址:</td>
					<td>${porder.address}</td>
				</tr>
				<tr>
					<td>物品名称:</td>
					<td>${porder.prodNanme}</td>
				</tr>
				<tr>
					<td>物品分类:</td>
					<td>${porder.typeName}</td>
				</tr>
				<tr>
					<td>规格款式:</td>
					<td>${porder.spec}</td>
				</tr>
				<tr>
					<td>购买数量:</td>
					<td>${porder.num}</td>
				</tr>
				<tr>
					<td>总价:</td>
					<td>${porder.totalPrice}</td>
				</tr>
				<tr>
					<td>明细描述:</td>
					<td>${porder.mxDesc}</td>
				</tr>
				<tr>
					<td>描述:</td>
					<td>${porder.remark}</td>
				</tr>
				<tr>
					<td>是否通过:</td>
					<td>${porder.passFlagString}</td>
				</tr>
				<tr>
					<td><span class="btn" onclick="window.history.go(-1);">返回</span></td>
				</tr>
			</table>
	  	</div>
	</div>
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]

<script type="text/html" id="pannel">
<div class="ui-dialog addManager">
	<h2><span class="close"></span><strong>修改分类信息</strong></h2>
	<form id="send" action="save.do">
	<input type="hidden" name="id">
		<ul>
			<li>
				<strong><b class="red">*</b>分类名称：</strong> 
				<input type="text" name="typeName" class="text" placeholder="分类名称" class="text">
			</li>
			<li>
				<strong>分类备注：</strong> 
				<input type="text" name="remark" placeholder="分类备注" class="text">
			</li>
			<li>
				<strong>创建人：</strong> 
				<input type="text" name="createUser" placeholder="创建人" class="text">
			</li>			
			<li class="btn"><span class="btn save" rel="add">提交保存</span></li>
		</ul>
	</form>
</div>	
</script>
<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr>
		<td><%=result.elements[i].typeName %></td>
		<td><%=result.elements[i].prodNanme %></td>
		<td><%=result.elements[i].createUser%></td>
		<td><%=result.elements[i].createTimeHandle%></td>
		<td><%=result.elements[i].num%></td>
		<td><%=result.elements[i].totalPrice%></td>	
		<td><%=result.elements[i].buyAddr %></td>
		<td><%=result.elements[i].buyType %></td>
		<td><%=result.elements[i].address%></td>
		<td><%=result.elements[i].passFlagString%></td>	
		<td> 
			<% if(result.elements[i].passFlag ==0 ){ %>
				<span class="btn del" rel="${base}/porderList/operationApproval.do?flag=true&id=<%=result.elements[i].tid%>">通过</span>
			<%}%>			
		   <a class="obtn" href="${base}/porderList/view.do?id=<%=result.elements[i].tid%>">查看</a>		  
	    </td>
    </tr>	
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/porderList/pagination.do',
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