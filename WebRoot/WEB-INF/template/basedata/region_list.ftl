<!DOCTYPE HTML>
<html>
<head>
<title>平台服务区域管理</title>
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
				   
						<form id="search">
						    <input type="hidden" name="parentid" value="${parentid}">
						</form>
					
					 [#if parentid!="0"]
					    <span class="btn search" onclick="history.back()" style="font-size:12px;margin:5px;">返回</span>
					 [/#if]				 
				  	<table class="format">
						<thead>
							<th width="10%">区域编号</th>
							<th width="10%">区域名称</th>
							<th width="10%">经度</th>
							<th width="10%">纬度</th>
							<th width="10%">状态</th>
							<th width="20%">操作</th>
						</thead>
						<tbody>
							[#list pagination.dataList as item]
							<tr>
								<td>${item.regionid}</td>	
								<td>${item.regionname}</td>	
								<td>${item.pointx}</td>	
								<td>${item.pointy}</td>	
								<td>${item.statusName}</td>	
								<td>
								[#if item.status==0]
								   <span class="btn" onClick="regionPass(${item.regionid})">开通</span>
								[#else]
								   <span class="btn" onClick="regionNoPass(${item.regionid})">关闭</span>
								[/#if]
								
								[#if item.level==3]
								   <span style="padding: 4px 12px; background: #cccccc; border-radius: 3px;" >查看</span>	
								[#else]
								  <span class="btn">
								      <a href="${base}/admin/region/index.xhtml?mid=${mid}&pid=${pid}&parentid=${item.regionid}">查看</a>
								  </span>
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
		<td><%=result.elements[i].regionid%></td>	
		<td><%=result.elements[i].regionname%></td>	
		<td><%=result.elements[i].pointx%></td>	
		<td><%=result.elements[i].pointy%></td>	
		<td><%=result.elements[i].statusName%></td>	
		<td>
		 <%if(result.elements[i].status==0){%>
	           <span class="btn" onClick="regionPass(<%=result.elements[i].regionid%>)">开通</span>
	     <%}else{%>
			   <span class="btn" onClick="regionNoPass(<%=result.elements[i].regionid%>)">关闭</span>
	     <%}%>
		
	     <%if(result.elements[i].level==3){%>
	           <span style="padding: 4px 12px; background: #cccccc; border-radius: 3px;" >查看</span>	
	     <%}else{%>
			 <span class="btn">
			    <a href="${base}/admin/region/index.xhtml?mid=${mid}&pid=${pid}&parentid=<%=result.elements[i].regionid%>">查看</a>
			 </span>
	     <%}%>
	     
		</td>	
    </tr>	
<%}%>

</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/admin/region/pagination.do',
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

  function regionPass(id){
       $.post("${base}/admin/region/regionPass.do",{id:id},function(rel){
          justTip(rel.msg);
          if(rel.ret==0){
            location.reload();
          }
       });
    }
    
    function regionNoPass(id){
       $.post("${base}/admin/region/regionNoPass.do",{id:id},function(rel){
          justTip(rel.msg);
          if(rel.ret==0){
            location.reload();
          }
       });
    }
    
</script>

</html>