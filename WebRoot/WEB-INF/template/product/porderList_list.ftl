<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>购物清单列表</title>
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
				<ul>
					<li>
						<strong>物品分类：</strong>
						<div class="select typeId mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p>
							<a value="">--请选择--</a>
                             [#list porderTypeList as l]
                                <a value="${l.id}">${l.typeName}</a>
                             [/#list]
							</p>					
							<input type="hidden" name="typeId" value="">
						</div>
					</li>
					<li>
						<strong>购买日期：</strong>
						<span class="sp"><input type="text" class="text Wdate" name="createBegin" id="" onClick="WdatePicker()" readOnly> &nbsp;--</span>
						<span class="sp"><input type="text" class="text Wdate" name="createEnd" id="" onClick="WdatePicker()" readOnly></span>
						<span class="btn search">查 询</span>
					</li>
				</ul>
				</form>
			</div>							
			<table class="format">
				<thead>
					<th width="10%">物品分类</th>
					<th width="15%">物品名称</th>
					<th width="10%">购买用户</th>	
					<th width="15%">购买时间</th>	
					<th width="5%">数量</th>
					<th width="5%">总价</th>
					<th width="10%">线上/线下</th>
					<th width="10%">购买地</th>	
					<th width="10%">是否通过</th>
					<th width="10%">操作</th>
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td>${item.typeName}</td>		
						<td>${item.prodNanme}</td>
						<td>${item.createUser}</td>
						<td>${item.createTimeHandle}</td>
						<td>${item.num}</td>
						<td>${item.totalPrice}</td>
						<td>${item.typeVal}</td>
						<td>${item.gmdname}</td>		
						<td>${item.passFlagString}</td>		
						<td>
							[#if item.passFlag == 0]
    	        				<span class="btn del" rel="${base}/porderList/operationApproval.do?flag=true&id=${item.tid}">通过</span>
            				[/#if]				
						   <a class="obtn" href="${base}/porderList/view.xhtml?id=${item.tid}&mid=${mid}&pid=${pid}">查看</a>		  
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
		<td><%=result.elements[i].typeVal %></td>
		<td><%=result.elements[i].gmdname%></td>
		<td><%=result.elements[i].passFlagString%></td>	
		<td> 
			<% if(result.elements[i].passFlag ==0 ){ %>
				<span class="btn del" rel="${base}/porderList/operationApproval.do?flag=true&id=<%=result.elements[i].tid%>">通过</span>
			<%}%>			
		   <a class="obtn" href="${base}/porderList/view.xhtml?id=<%=result.elements[i].tid%>&mid=${mid}&pid=${pid}">查看</a>		  
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