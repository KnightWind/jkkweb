<!DOCTYPE HTML>
<html>
<head>
<title>首页众凑商品设置</title>
<meta charset="utf-8">
[#include "/common/res.ftl"]
<script type="text/javascript" src="${base}/scripts/form.js"></script>
<script type="text/javascript" src="${base}/scripts/imageHandle.js"></script>
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
					<div style="float:right;margin-bottom:5px;">
					    <span class="btn">
						    <a href="${base}/adminHomeZCItem/index.xhtml">查看首页众凑商品</a>
						</span>
			        </div>		
					<table class="format">
						<thead>
							<th width="5%">编号</th>
							<th width="20%">商品名称</th>
							<th width="10%">最高价(单位:元)</th>
							<th width="10%">最低价(单位:元)</th>
							<th width="5%">众凑人数</th>
							<th width="15%">创建时间</th>
							<th width="5%">首页显示</th>
							<th width="20%">操作</th>	
						</thead>
						<tbody>
							[#list pagination.dataList as item]
							<tr>
								<td>${item.id}</td>	
								<td>${item.title}</td>	
								<td>${item.topPrice}</td>
								<td>${item.lowPrice}</td> 
								<td>${item.numPeople}</td> 
								<td>${item.createTimeHandle}</td>	
								<td>${item.flagVal}</td>	
								<td>
								  [#if item.zcFlag==false]
						            <span class="btn modify" rel=["${item.id}","${item.itemId}"]>设置为首页热门商品</span>
								  [/#if]
								  [#if item.zcFlag==true]
						            <span  style="padding: 4px 12px; background: #cccccc; border-radius: 3px;">设置为首页热门商品</span>
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

<script type="text/html" id="pannel">
<div class="ui-dialog" style="height:430px;">
	<h2><span class="close"></span><strong>设置众凑商品首页图</strong></h2>
	<form id="send" action="saveOneImg.do" enctype="multipart/form-data" method="post">
	<input type="hidden" name="id">
	<input type="hidden" name="itemId">
		<ul>
			<li>
				<div style="padding :20px;">
						<div id="preview"> 
							<input type="hidden" name="avatar"/>
						    <img id="imghead" style="padding-left:10px;"  width=350 height=300 border=0> 
						</div>  
						
						<div style="float:left;">
							<input id="userImgFile" onchange="changeImage(this)" style="width:65px;padding-left:10px;" name="userImg" class="btn" type="file" id="male" />
						<div>
			    </div>
			</li>
			<li class="btn"><span class="btn save" rel="add"  style="margin-left:150px;" >保存</span></li>
		</ul>
	</form>
</div>	
</script>

<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr>
		<td><%=result.elements[i].id%></td>	
		<td><%=result.elements[i].title%></td>	
		<td><%=result.elements[i].topPrice%></td>
		<td><%=result.elements[i].lowPrice%></td> 
		<td><%=result.elements[i].numPeople%></td> 
		<td><%=result.elements[i].createTimeHandle%></td>	
		<td><%=result.elements[i].flagVal%></td>
		<td> 
			<%if(result.elements[i].zcFlag==false){%>		
			   <span class="btn modify" rel=["<%=result.elements[i].id%>","<%=result.elements[i].itemId%>"]>设置为首页热门商品</span>
			<%}else{%>			  
			   <span style="padding: 4px 12px; background: #cccccc; border-radius: 3px;">设置为首页热门商品</span>
		    <%}%>			  
	    </td>
    </tr>	
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/adminZCItem/pagination.do',
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

</html>