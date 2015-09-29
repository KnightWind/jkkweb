<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>帮助中心管理</title>
[#include "/common/res.ftl"]
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
</head>

<body>
<!-- header -->
[#include "/common/head.ftl"]

<!-- wrap -->
<div class="wrap index bc clr">
	<div class="conWrap fl">
		[#include "/common/nav.ftl"]
		<div class="conBox">
			<div class="shd">
				<a class="obtn fr" href="${base}/helpCategory/add.xhtml?pid=${pid}&mid=${mid}" target="_blank">创建</a>
			</div>
		  <table class="format">
				<thead>
					<th width="20%">一级目录</th>
					<th width="20%">二级目录</th>
					<th width="20%">内容</th>
					<th width="20%">状态</th>
					<th width="20%">操作</th>							
				</thead>
				<tbody>
				
				  [#list pagination.dataList as item]
				    <tr>
				         <td>${item.fistCata}</td>
				         <td>${item.secCata}</td>
				         <td>${item.contentVal}</td>
				         <td name="statusVal">${item.statusVal}</td>
				         <td>
				         <span class="btn modify"><a href="${base}/helpCategory/edit.xhtml?pid=${pid}&mid=${mid}&id=${item.vid}">编辑</a></span>				         
				         <span class="btn handleStatus" id="${item.vid}" status="${item.vstatus}">${item.statusHandle}</span>
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
		<td><%=result.elements[i].fistCata%></td>
		<td><%=result.elements[i].secCata%></td>
		<td><%=result.elements[i].contentVal%></td>
		<td><%=result.elements[i].statusVal%></td>
		<td>
		<span class="btn modify"><a href="${base}/helpCategory/edit.xhtml?pid=${pid}&mid=${mid}&id=<%=result.elements[i].vid%>">编辑<a></span>				         
		<span class="btn handleStatus" id="<%=result.elements[i].vid%>" status="<%=result.elements[i].vstatus%>"><%=result.elements[i].statusHandle%></span>
	    </td>
	</tr>
<%}%>
</script>

<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/help/pagination.do',
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


<script>
$(function(){

  $(".format").delegate(".handleStatus","click",
     function(){
    var target = $(this);
	var row = $(this).parent().parent();
	var id = $(this).attr("id");
	var hidden = $(this).attr("status");
	var url = "${base}/help/show.do";
	if (hidden!=-1) {
		url = "${base}/help/hidden.do";
	}
	$.post(url, {id : id}, function(result){
	    if(result.ret==0){
	     $(target).html(hidden==-1?"隐藏":"显示").attr("status",hidden==-1?"0":"-1");
	     $("[name=statusVal]",row).html(hidden==-1?"显示":"隐藏");	        
	    }else{
	        justTip(result.msg)
	    }
	});
   });

  
   });	
</script>


</html>