
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
				<form id="search" action="">
				<ul>
					<li>
						<strong>所属区域：</strong>
						<div class="select province mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p rel="${base}/admin/area/sheng.do">
								<a value="">--请选择--</a>
	                            [#list lst as l]
                                <a value="${l.areaDomain}">${l.area}</a>
                             	[/#list]
							</p>					
							<input type="hidden" name="province" value="">
						</div>
						<div class="select city mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p rel="${base}/admin/area/city.do">
								
							</p>
							<input type="hidden" name="" value="">
						</div>
						<div class="select area mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p>
								
							</p>
							<input type="hidden" name="area" value="">
						</div>
					</li>
					<li>
						<span class="sp"><strong>开通时间：</strong><input type="text" class="text Wdate" name="start" id="" onClick="WdatePicker()" readOnly></span>
						<span class="sp"><input type="text" class="text Wdate" name="end" id="" onClick="WdatePicker()" readOnly></span>
					</li>
					<li>
						<strong>开通状态：</strong>
						<div class="select province mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
								<p>     
									<a value="">--请选择--</a>                      
	                                <a value="0">未开通</a>
	                                <a value="1">开通</a>
	                                <a value="-1">下线</a>                     
								</p>					
							<input type="hidden" name="zt" value="">
						</div>
						<span class="btn search">查 询</span>
					</li>
					
				</ul>
				</form>
			</div>	
			<table class="format">
				<thead>
					<th width="10%">所属区域</th>
					<th width="10%">省份</th>
					<th width="10%">城市</th>
					<th width="10%">状态</th>
					<th width="25%">开通时间</th>
					<th width="25%">下线时间</th>
					<th width="10%">操作</th>
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr key="${item.id}">
						<td>${item.area}</td>
						<td>${item.province}</td>
						<td>${item.city}</td>
						<td>${item.isOpenName}</td>
						<td>${item.openTimeName}</td>
						<td>${item.offlineTimeName}</td>
					<td><span class="btn del" rel="${base}/admin/area/operate.do?id=${item.id}&isopen=${item.open}">${item.operateName}</span></td>
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
	<td><%=result.elements[i].area%></td>
	<td><%=result.elements[i].province%></td>
	<td><%=result.elements[i].city%></td>
	<td><%=result.elements[i].isOpenName%></td>					
	<td><%=result.elements[i].openTimeName%></td>
	<td><%=result.elements[i].offlineTimeName%></td>
	<td><span class="btn del" rel="${base}/admin/area/operate.do?id=<%=result.elements[i].id%>&isopen=<%=result.elements[i].open%>"><%=result.elements[i].operateName%></span></td>
</tr>	
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/admin/area/pagination.do',
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