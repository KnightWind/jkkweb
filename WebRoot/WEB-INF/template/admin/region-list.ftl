
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>城市列表</title>
[#include "/common/res.ftl"]
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/form.js"></script>
<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
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
				
		<div class="tools white border mb10 sp10 clr">
				<form id="search" action="">
				<ul>
					<li>
						<strong>所属区域：</strong>
						<div class="select province mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p rel="${base}/admin/region/sheng.do">
								<a value="">--请选择--</a>
	                            [#list lst as l]
                                <a value="${l.regionid}">${l.regionname}</a>
                             	[/#list]
							</p>					
							<input type="hidden" name="province" value="">
						</div>
						
						<div class="select city mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p rel="${base}/admin/region/city.do">
								
							</p>
							<input type="hidden" name="" value="">
						</div>
						
					</li>

					<li>
						<strong>开通状态：</strong>
						<div class="select province mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
								<p>     
									<a value="">--请选择--</a>                      
	                                <a value="0">未开通</a>
	                                <a value="1">开通</a>                  
								</p>					
							<input type="hidden" name="status" value="">
						</div>
						<span class="btn search">查 询</span>
					</li>
					
				</ul>
				</form>
			</div>			
				
			</div>	
			<table class="format">
				<thead>
					<th width="20%">城市</th>
					<th width="20%">状态</th>
					<th width="20%">经度</th>
					<th width="20%">纬度</th>
					<th width="20%">操作</th>
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr key="${item.regionid}">
						<td>${item.regionname}</td>
						<td>${item.statusName}</td>
						<td>${item.pointx}</td>
						<td>${item.pointy}</td>
					<td><span class="btn del" rel="${base}/admin/region/operate.do?id=${item.regionid}&isopen=${item.status}">${item.operateName}</span></td>
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
<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr menuid="<%=result.elements[i].id%>">
	<td><%=result.elements[i].regionname%></td>
	<td><%=result.elements[i].statusName%></td>
	<td><%=result.elements[i].pointx%></td>
	<td><%=result.elements[i].pointy%></td>		
	<td><%=result.elements[i].regionid%></td>				
	<td><span class="btn del" rel="${base}/admin/region/operate.do?id=<%=result.elements[i].regionid%>&isopen=<%=result.elements[i].status%>"><%=result.elements[i].operateName%></span></td>
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
})
</script>
</body>
</html>