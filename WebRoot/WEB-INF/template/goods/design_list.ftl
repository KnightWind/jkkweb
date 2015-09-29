<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>效果图列表</title>
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
		<div class="tools white border mb10 sp10 clr">
				<form id="search" action="">
				<ul>
					<li>
						<strong>所属区域：</strong>
						<div class="select province mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p rel="${base}/goods/item/sheng.do">
							<a value="">--请选择--</a>
                            [#list lst as l]
                                <a value="${l.areaDomain}">${l.area}</a>
                             [/#list]
							</p>					
							<input type="hidden" name="province" value="">
						</div>
						<div class="select city mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p rel="${base}/goods/item/city.do">
								
							</p>
							<input type="hidden" name="city"  value="">
						</div>
						<div class="select area mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p>
								
							</p>
							<input type="hidden" name="area"  value="">
						</div>
						<input type="hidden" name="status" value="${status}">
					</li>
					<li>
						<span class="sp"><strong>效果编号：</strong><input type="text" class="text" name="id" id=""></span>
						<span class="sp"><strong class="auto">效果图名：</strong><input type="text" class="text" name="name" id=""></span>
					</li>
					<li>
						<span class="sp"><strong>所属商户：</strong><input type="text" class="text" name="sname" id=""></span>
						<span class="btn search">查 询</span>
					</li>
				</ul>
				</form>
			</div>
		  <div class="page_tab_box">
                <div class="page_tab">
                    <div class="pageTab">
                    	<span class="cur"><a href="${base}/goods/design/index.xhtml?mid=${mid}&pid=${pid}">全部</a></span>
                        <span><a href="${base}/goods/design/index.xhtml?mid=${mid}&pid=${pid}&state=0">待审核效果图</a></span>
                        <span ><a href="${base}/goods/design/index.xhtml?mid=${mid}&pid=${pid}&status=1">通过效果图</a></span>
                        <span ><a href="${base}/goods/design/index.xhtml?mid=${mid}&pid=${pid}&status=-1">不通过效果图</a></span>
                    </div>
                </div>
            </div>
			<table class="format">
				<thead>					
					<th width="4%">编号</th>
					<th width="5%"></th>
					<th width="10%">效果图</th>
					<th width="10%">公司</th>
					<th width="4%">工长</th>
					<th width="4%">设计师</th>
					<th width="4%">图片数</th>
					<th width="4%">户型</th>
					<th width="4%">空间</th>
					<th width="4%">风格</th>
					<th width="4%">状态</th>
					<th width="8%">最后编辑</th>			
					<th width="20%">操作</th>				
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>						
						<td>${item.id}</td>
						<td><img  src="${item.pid}"></td>
						<td>${item.designName}</td>
						<td>${item.company}</td>
						<td>${item.forman}</td>
						<td>${item.designer}</td>
						<td>${item.imageNum}</td>
						<td>${item.fname}</td>
						<td>${item.dname}</td>
						<td>${item.ename}</td>		
						<td>${item.isOperate}</td>
						<td>${item.cre}</td>									
						<td>
						[#if item.status==1]<span class="btn modify">查看</span>
						<span class="btn del" rel="${base}/goods/design/oper.do?id=${item.id}&isopen=${item.status}">不通过</span>[#else]
						<span class="btn del" rel="${base}/goods/design/oper.do?id=${item.id}&isopen=${item.status}">通过</span> [/#if]	
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
	<td><img  src="<%=result.elements[i].pid%>"></td>
	<td><%=result.elements[i].designName%></td>
	<td><%=result.elements[i].company%></td>
	<td><%=result.elements[i].forman%></td>
	<td><%=result.elements[i].designer%></td>	
	<td><%=result.elements[i].imageNum%></td>
	<td><%=result.elements[i].fname%></td>
	<td><%=result.elements[i].dname%></td>
	<td><%=result.elements[i].ename%></td>
	<td><%=result.elements[i].isOperate%></td>
	<td><%=result.elements[i].cre%></td>
	<td>
		<%if (result.elements[i].status==1){%><span class="btn modify">查看</span>
		<span class="btn del" rel="${base}/goods/design/oper.do?id=<%=result.elements[i].id%>&isopen=<%=result.elements[i].status%>">不通过</span><%}%>
		<%if (result.elements[i].status==-1){%>
		<span class="btn del" rel="${base}/goods/design/oper.do?id=<%=result.elements[i].id%>&isopen=<%=result.elements[i].status%>">通过</span> <%}%>
	</td>
</tr>
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/goods/design/pagination.do?mid=${mid}&pid=${pid}&status=${status}',
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
		case "1":$('.pageTab span:eq(2)').addClass('cur');break;
		case "-1":$('.pageTab span:eq(3)').addClass('cur');break;
		default:$('.pageTab span:eq(0)').addClass('cur');break;
	}
});	
</script>
</body>
</html>