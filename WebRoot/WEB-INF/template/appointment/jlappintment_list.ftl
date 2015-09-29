<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>预约监理管理</title>
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
				<input type="hidden" name="status" value="${status}">
				<ul>					
					<li>
						<span class="sp"><strong>预约编号：</strong><input type="text" class="text" name="id" id=""></span>
					    <span class="sp"><strong>预约用户：</strong><input type="text" class="text" name="nickname" id=""></span>						
					</li>
					<li>
						<span class="sp"><strong>创建日期：</strong><input type="text" class="text Wdate" name="createStart" id="" onClick="WdatePicker()" readOnly></span>
						<span class="sp"><input type="text" class="text Wdate" name="createEnd" id="" onClick="WdatePicker()" readOnly></span>											
					</li>
		
					<li>
					   <span class="sp">
					     <strong>装修小区：</strong>
						   <input type="text" class="text" name="community" id="">
					    </span>	
					<span class="btn search">查 询</span>
					</li>
				</ul>
				</form>
			</div>
			 <div class="page_tab_box">
                <div class="page_tab">
                    <div class="pageTab">
                        <span class="cur"><a href="${base}/jlappointment/index.xhtml?pid=${pid}&mid=${mid}">全部预约</a></span>
                        <span ><a href="${base}/jlappointment/index.xhtml?pid=${pid}&mid=${mid}&status=0">流单</a></span>
                        <span ><a href="${base}/jlappointment/index.xhtml?pid=${pid}&mid=${mid}&status=10">待应单</a></span>
                        <span ><a href="${base}/jlappointment/index.xhtml?pid=${pid}&mid=${mid}&status=20">待选定</a></span>
                        <span ><a href="${base}/jlappointment/index.xhtml?pid=${pid}&mid=${mid}&status=30">已选定</a></span>
                        <span ><a href="${base}/jlappointment/index.xhtml?pid=${pid}&mid=${mid}&status=40">关闭</a></span>
                    </div>
                </div>
            </div>
			<table class="format">
				<thead>
					<th width="5%">编号</th>
					<th width="6%">装修小区</th>				 	
					<th width="8%">预约用户</th>
					<th width="8%">状态</th>
					<th width="15%">创建时间</th>
					<th width="10%">操作</th>				
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td>${item.id}</td>
						<td>${item.vCommunity}</td>
						<td>${item.nickname}</td>
						<td>${item.statusName}</td>
						<td>${item.createTimeHandle}</td>
						<td>			
							[#if item.status==40]
							   <span style="padding: 4px 12px; background: #cccccc; border-radius: 3px;" >关闭</span>
							[#else]
							 <span class="btn deleteHandle" rel="${item.id}">关闭</span>
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
<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr menuid="<%=result.elements[i].id%>">
	<td><%=result.elements[i].id%></td>
	<td><%=result.elements[i].vCommunity%></td>
	<td><%=result.elements[i].nickname%></td>
	<td><%=result.elements[i].statusName%></td>
	<td><%=result.elements[i].createTimeHandle%></td>					
	<td>
	 <% if(result.elements[i].status==40){%>
	     <span style="padding: 4px 12px; background: #cccccc; border-radius: 3px;" >关闭</span>
	 <%}%>
	 <% if(result.elements[i].status!=40){ %>
	   <span class="btn deleteHandle" rel="<%=result.elements[i].id%>">关闭</span>
	 <%}%>
	</td>
</tr>	
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/jlappointment/managePagination.do',
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
	var status=window.location.href.split('status=')[1];
	$('.pageTab span.cur').removeClass('cur');
	switch(status){
		case "0":$('.pageTab span:eq(1)').addClass('cur');break;
		case "10":$('.pageTab span:eq(2)').addClass('cur');break;
		case "20":$('.pageTab span:eq(3)').addClass('cur');break;
		case "30":$('.pageTab span:eq(4)').addClass('cur');break;
		case "40":$('.pageTab span:eq(5)').addClass('cur');break;
		default:$('.pageTab span:eq(0)').addClass('cur');break;
	}
});	
</script>

<script>
$(function(){
    $(".format").delegate(".deleteHandle",'click',function(){
	     var TF=confirm("确定关闭预约吗?");
	        if(TF){
	           var id=$(this).attr("rel");
	           $.post("close.do",{id:id},function(json){
	              justTip(json.msg);
	              if(json.ret==0){
	                 location.reload();
	              }
	           });
	        }
    });
});
</script>
</body>
</html>