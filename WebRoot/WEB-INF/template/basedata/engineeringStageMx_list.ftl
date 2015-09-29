<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>验收节点管理</title>
[#include "/common/res.ftl"]

<style>
.addManager{
  height:200px;
}
#send{
  padding-left:20px;
}
#send .saveBtn{
  padding-left:65px;
}

#send li{
  padding-top:10px;
 
}
.event ul li.auto{ height: auto; padding-bottom: 10px;}
.event ul li.auto textarea{ width: 400px; height: 150px;}
</style>

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
		<form id="search">
		  <input type="hidden" name="ppid" value="${ppid}"/>
		</form>
			<div style="float:right;margin-bottom:5px;">
				<span class="btn">
				    <a href="${base}/engineeringStageMx/edit.xhtml?mid=${mid}&pid=${pid}">创建验收节点</a>
				</span>
				<span class="btn search" onclick="history.back()" style="font-size:12px;">返回</span>
			</div>			
			<table class="format">
				<thead>
					<th width="20%">验收节点</th>
					<th width="10%">排序</th>
					<th width="20%">创建时间</th>	
					<th width="10%">操作</th>			
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td>${item.mxDesc}</td>
						<td>${item.ordrBy}</td>
						<td>${item.createTimeHandle}</td>	
						<td>
							<span class="btn">
							     <a href="${base}/engineeringStageMx/updateOne.xhtml?mid=${mid}&pid=${pid}&id=${item.id}">修改</a>
							</span>  
							<span class="btn deleteOne" rel="${item.id}">删除<span>
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
	<h2><span class="close"></span><strong>信息修改</strong></h2>
	<form id="send" action="saveOne.do">
	<input type="hidden" name="id">
		<ul>
			<li>
				<strong>节点名称：</strong> 
				<input type="text" name="mxDesc" class="text" placeholder="节点名称">
			</li>
			<li>
				<strong>节点排序：</strong> 
				<input type="text" name="ordrBy" placeholder="节点排序" class="text">
			</li>
			<li class="btn saveBtn"><span class="btn save" rel="add">保存</span></li>
		</ul>
	</form>
</div>	
</script>

<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr>
		<td><%=result.elements[i].mxDesc %></td>
		<td><%=result.elements[i].ordrBy %></td>
		<td><%=result.elements[i].createTimeHandle %></td>	
		<td> 
		<span class="btn">
			<a href="${base}/engineeringStageMx/updateOne.xhtml?mid=${mid}&pid=${pid}&id=<%=result.elements[i].id%>">修改</a>
	   </span>
	   <span class="btn deleteOne" rel="<%=result.elements[i].id%>">删除<span>
	    </td>
    </tr>	
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/engineeringStageMx/pagination.do',
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

<script>
   $(function(){
      $(".deleteOne").click(function(){
          var id=$(this).attr("rel");
          $.post("${base}/engineeringStageMx/deleteOne.do",{id:id},function(rel){
               justTip(rel.msg);
               if(rel.ret==0){
                  location.reload();
               }
          });
      });
   });
</script>

</body>
</html>