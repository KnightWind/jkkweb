<!DOCTYPE HTML>
<html>
<head>
<title>业主日记管理</title>
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
				<input type="hidden" name="status" value="${status}">
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
							<input type="hidden" name="area" value="">
						</div>
						<div class="select city mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p rel="${base}/goods/item/city.do">
								
							</p>
							<input type="hidden" name="province"  value="">
						</div>
						<div class="select area mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p>
								
							</p>
							<input type="hidden" name="city"  value="">
						</div>
					</li>			
					<li>
						<span class="sp">
						   <strong >业主：</strong>
						   <input type="text" class="text" name="nickName" id="">
					   </span>
						
					</li>										
					<li>
						 <span class="sp"><strong>创建时间：</strong><input type="text" class="text Wdate" name="createBegin" id="" onClick="WdatePicker()" readOnly></span>
						 <span class="sp"><input type="text" class="text Wdate" name="createEnd" id="" onClick="WdatePicker()" readOnly></span>
					</li>
					<li>
						 <span class="sp"><strong>审核时间：</strong><input type="text" class="text Wdate" name="checkBegin" id="" onClick="WdatePicker()" readOnly></span>
						 <span class="sp"><input type="text" class="text Wdate" name="checkEnd" id="" onClick="WdatePicker()" readOnly></span>
					</li>
					<li>
						 <span class="sp"><strong>屏蔽时间：</strong><input type="text" class="text Wdate" name="closeBegin" id="" onClick="WdatePicker()" readOnly></span>
						 <span class="sp"><input type="text" class="text Wdate" name="closeEnd" id="" onClick="WdatePicker()" readOnly></span>
					     <span class="btn search">查 询</span>
					</li>
				</ul>
				</form>
			</div>	
			
			<div class="page_tab_box">
                <div class="page_tab">
                    <div class="pageTab">
                        <span class="cur"><a href="${base}/topic/index.xhtml?pid=${pid}&mid=${mid}">全部</a></span>
                        <span ><a href="${base}/topic/index.xhtml?pid=${pid}&mid=${mid}&status=0">未审核</a></span>
                        <span ><a href="${base}/topic/index.xhtml?pid=${pid}&mid=${mid}&status=1">通过</a></span>     
                        <span ><a href="${base}/topic/index.xhtml?pid=${pid}&mid=${mid}&status=-1">未通过</a></span>                                          
                    </div>
                </div>
            </div>
			
			<table class="format">
				<thead>				
				    <th width="5%">编号</th>	
					<th width="10%">标题</th>
					<th width="10%">业主</th>
					<th width="5%">城市</th>
					<th width="10%">装修小区</th>
					<th width="5%">审核状态</th>
					<th width="15%">创建时间</th>
					<th width="15%">审核时间</th>
					<th width="15%">屏蔽时间</th>						
					<th width="10">操作</th>			
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>			
				    	<td>${item.id}</td>			
						<td>${item.subject}</td>
						<td>${item.nickName}</td>
						<td>${item.city}</td>
						<td>${item.title}</td>
						<td>${item.statusVal}</td>
						<td>${item.createTimeHandle}</td>
						<td>${item.checkTimeHandle}</td>		
						<td>${item.closeTimeHandle}</td>															
						<td>
							<span class="btn"><a href="detail.xhtml?pid=${pid}&mid=${mid}&id=${item.id}">审核</a></span>				
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
		<td><%=result.elements[i].subject%></td>
		<td><%=result.elements[i].nickName%></td>
		<td><%=result.elements[i].city%></td>
		<td><%=result.elements[i].title%></td>
		<td><%=result.elements[i].statusVal%></td>
		<td><%=result.elements[i].createTimeHandle%></td>
		<td><%=result.elements[i].checkTimeHandle%></td>		
		<td><%=result.elements[i].closeTimeHandle%></td>		
		<td>		
		   <span class="btn"><a href="detail.xhtml?pid=${pid}&mid=${mid}&id=<%=result.elements[i].id%>">审核</a></span>						
		</td>
	</tr>
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/topic/pagination.do',
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
	
	var status=window.location.href.split('status=')[1];
	$('.pageTab span.cur').removeClass('cur');
	switch(status){
		case "0":$('.pageTab span:eq(1)').addClass('cur');break;
		case "1":$('.pageTab span:eq(2)').addClass('cur');break;
		case "-1":$('.pageTab span:eq(3)').addClass('cur');break;		
		default:$('.pageTab span:eq(0)').addClass('cur');break;
	}
	
})
</script>
</html>