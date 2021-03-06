<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>三级节点管理</title>
[#include "/common/res.ftl"]

<style>
.addManager{
  height:250px;
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
		<div style="float:right;margin-bottom:5px;">
		    <span class="btn">
			    <a href="${base}/engineeringStage/oneTwoStage.xhtml?mid=${mid}&pid=${pid}">一、二级节点管理</a>
			</span>
			<span class="btn">
			    <a href="${base}/engineeringStage/stageThreeAdd.xhtml?mid=${mid}&pid=${pid}">创建三级节点</a>
			</span>
			
		</div>			
			<table class="format">
				<thead>
					<th width="15%">一级节点</th>
					<th width="15%">二级节点</th>
					<th width="20%">三级节点</th>
					<th width="10%">排序</th>
					<th width="20%">创建时间</th>	
					<th width="20%">操作</th>					
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td>${item.firstStage}</td>		
						<td>${item.secondStage}</td>
						<td>${item.stagName}</td>
						<td>${item.ordrBy}</td>
						<td>${item.createTimeHandle}</td>	
						<td>
						    <span class="btn">
						    	<a href="${base}/engineeringStageMx/index.xhtml?ppid=${item.id}&pid=${pid}&mid=${mid}">验收节点</a>						   
						    </span>
						    <span class="btn modify" rel=["${item.id}","${item.firstStage}","${item.secondStage}","${item.stagName}","${item.ordrBy}"]>修改</span>
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
				<strong>一级节点：</strong> 
				<input type="text" class="text" disabled="disabled">
			</li>
			<li>
				<strong>二级节点：</strong> 
				<input type="text" class="text" disabled="disabled">
			</li>
			<li>
				<strong>三级节点：</strong> 
				<input type="text" name="stagName" placeholder="三级节点" class="text">
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
		<td><%=result.elements[i].firstStage %></td>
		<td><%=result.elements[i].secondStage %></td>
		<td><%=result.elements[i].stagName %></td>
		<td><%=result.elements[i].ordrBy %></td>	
		<td><%=result.elements[i].createTimeHandle %></td>
		<td> 
			<span class="btn">
				<a href="${base}/engineeringStage/detail.xhtml?pid=<%=result.elements[i].id %>&pid=${pid}&mid=${mid}">验收节点</a>						   
		    </span>
		    <span class="btn modify" rel=["<%=result.elements[i].id%>","<%=result.elements[i].firstStage%>","<%=result.elements[i].secondStage%>","<%=result.elements[i].stagName%>","<%=result.elements[i].ordrBy%>"]>修改</span>
	        <span class="btn deleteOne" rel="<%=result.elements[i].id %>">删除<span>
	    </td>
    </tr>	
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/engineeringStage/pagination.do',
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
      $(".format").delegate(".deleteOne","click", function(){
          var id=$(this).attr("rel");
          $.post("${base}/engineeringStage/deleteOne.do",{id:id},function(rel){
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