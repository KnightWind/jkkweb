<!DOCTYPE HTML>
<html>
<head>
<title>投诉单列表</title>
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
				<form id="search" action="">
				<ul>					
					<li>
						<span class="sp"><strong>投诉人电话：</strong><input type="text" class="text" name="mobile" id=""></span>
						<span class="sp"><strong class="auto">被投诉方：</strong><input type="text" class="text" name="spName" id=""></span>
						<span class="sp"><strong class="auto">受理人：</strong><input type="text" class="text" name="nickName" id=""></span>
					</li>
					<li>
					 	 <strong>来源：</strong>
						 <div class="select mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p>
								<a value="">--请选择--</a>
								<a value="1">商城网站</a>
								<a value="2">400电话</a>
							</p>
							<input type="hidden" name="source">
						 </div>		
					 	 <span class="sp"><strong class="auto">来源单号：</strong><input type="text" class="text" name="aid" id=""></span>						
					</li>
					<li>
						<strong>状态：</strong>
					    <span class="sp radio">
						    <input type="radio" name="status" value=""/> 全部 
						   	<input type="radio" name="status" value="1"/> 已解决 
						   	<input type="radio" name="status" value="2"/> 待处理 					   
					    </span>
					    <strong class="auto">优先级：</strong>				 
					   	<span class="sp radio">
						   	<input type="radio" name="level" value=""/> 全部 
						   	<input type="radio" name="level" value="1"/> 低 
						   	<input type="radio" name="level" value="2"/> 中等 
						   	<input type="radio" name="level" value="3"/> 高 
					   	</span>				
					</li>
					<li>
						 <span class="sp"><strong>创建时间：</strong><input type="text" class="text Wdate" name="createBegin" id="" onClick="WdatePicker()" readOnly></span>
						 <span class="sp"><input type="text" class="text Wdate" name="createEnd" id="" onClick="WdatePicker()" readOnly></span>
					</li>
					<li>
						 <span class="sp"><strong>更新时间：</strong><input type="text" class="text Wdate" name="updateBegin" id="" onClick="WdatePicker()" readOnly></span>
						 <span class="sp"><input type="text" class="text Wdate" name="updateEnd" id="" onClick="WdatePicker()" readOnly></span>
						 <span class="btn search">查 询</span>
					</li>
				</ul>
				</form>
			</div>	
			<table class="format">
				<thead>
					<th width="4%">单号</th>
					<th width="12%">创建时间</th>
					<th width="6%">优先级</th>
					<th width="6%">状态</th>
					<th width="6%">来源</th>
					<th width="6%">来源单号</th>
					<th width="6%">投诉人</th>
					<th width="12%">投诉人电话</th>
					<th width="11%">被投诉方</th>				
					<th width="5%">受理人</th>
					<th width="12%">更新时间</th>
					<th width="20">操作</th>			
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td>${item.id}</td>
						<td>${item.createTimeHandle}</td>
						<td>${item.levelVal}</td>
						<td>${item.statusVal}</td>
						<td>${item.sourceVal}</td>
						<td>${item.appId}</td>
						<td>${item.userName}</td>
						<td>${item.mobile}</td>		
						<td>${item.spName}</td>										
						<td>${item.nickname}</td>
						<td>${item.updateTimeHandle}</td>
						<td>
						
						[#if item.status==1]
						     <span class="btn"><a href="detail.xhtml?pid=${pid}&mid=${mid}&id=${item.id}">查看</a></span>				
						    <span class="btn" >已解决</span>		
						[#else]			
						    <span class="btn"><a href="detail.xhtml?pid=${pid}&mid=${mid}&id=${item.id}">跟进</a></span>				
						    <span class="btn del" rel="${base}/complaint/close.do?id=${item.id}">关闭</span>
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
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr>
		<td><%=result.elements[i].id%></td>
		<td><%=result.elements[i].createTimeHandle%></td>
		<td><%=result.elements[i].levelVal%></td>
		<td><%=result.elements[i].statusVal%></td>
		<td><%=result.elements[i].sourceVal%></td>
		<td><%=result.elements[i].appId%></td>
		<td><%=result.elements[i].userName%></td>
		<td><%=result.elements[i].mobile%></td>
		<td><%=result.elements[i].spName%></td>	
		<td><%=result.elements[i].nickname%></td>
		<td><%=result.elements[i].updateTimeHandle%></td>
		<td>
		<% if(result.elements[i].status==1){%>
		        <span class="btn"><a href="detail.xhtml?pid=${pid}&mid=${mid}&id=<%=result.elements[i].id%>">查看</a></span>				
				<span class="btn" >已解决</span>	
		<%}%>
		   
		 <% if(result.elements[i].status==2){%>
		        <span class="btn"><a href="detail.xhtml?pid=${pid}&mid=${mid}&id=<%=result.elements[i].id%>">跟进</a></span>				
				<span class="btn del" rel="${base}/complaint/close.do?id=<%=result.elements[i].id%>">关闭</span>
		<%}%>  
		</td>
	</tr>
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/complaint/pagination.do',
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