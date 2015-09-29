<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>购物清单分类管理</title>
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
		<div style="float:right;margin-bottom:5px;"><span class="btn addnew">添加分类</span></div>				
			<table class="format">
				<thead>
					<th width="15%">分类名称</th>
					<th width="15%">分类备注</th>
					<th width="15%">创建人</th>
					<th width="15%">状态</th>
					<th width="20%">创建时间</th>	
					<th width="15%">操作</th>	
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td>${item.typeName}</td>		
						<td>${item.remark}</td>
						<td>${item.createUser}</td>
						<td>${item.statusVal}</td>
						<td>${item.createTimeHandle}</td>	
						<td>						    
						    <span class="btn modify" rel=["${item.id}","${item.typeName}","${item.remark}","${item.createUser}"]>修改</span>
					       [#if item.status==1] 
					           <span class="btn del" rel="${base}/porderType/hide.do?id=${item.id}">隐藏</span>
					        [#else]
					           <span class="btn del" rel="${base}/porderType/show.do?id=${item.id}">显示</span>
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

<script type="text/html" id="pannel">
<div class="ui-dialog addManager">
	<h2><span class="close"></span><strong>修改分类信息</strong></h2>
	<form id="send" action="save.do">
	<input type="hidden" name="id">
		<ul>
			<li>
				<strong><b class="red">*</b>分类名称：</strong> 
				<input type="text" name="typeName" class="text" placeholder="分类名称">
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
		<td><%=result.elements[i].remark %></td>
		<td><%=result.elements[i].createUser%></td>
		<td><%=result.elements[i].statusVal%></td>
		<td><%=result.elements[i].createTimeHandle%></td>	
		<td> 			
		    <span class="btn modify" rel=["<%=result.elements[i].id%>","<%=result.elements[i].typeName%>","<%=result.elements[i].remark%>","<%=result.elements[i].createUser%>"]>修改</span>	         
	         <%if(result.elements[i].status==1){%>
			   <span class="btn del" rel="${base}/porderType/hide.do?id=<%=result.elements[i].id%>">隐藏</span>
			 <%}%>
			 
			 <%if(result.elements[i].status!=1){%>
				<span class="btn del" rel="${base}/porderType/show.do?id=<%=result.elements[i].id%>">显示</span>
		     <%}%>
	    </td>
    </tr>	
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/porderType/pagination.do',
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