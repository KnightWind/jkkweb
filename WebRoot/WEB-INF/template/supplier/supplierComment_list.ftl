<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>商家列表</title>
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
				<ul>
					<li>
						<strong>所属区域：</strong>
						<div class="select province mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p rel="${base}/admin/area/sheng.do">
							<a value="">--请选择--</a>
                            [#list list as l]
                                <a  value="${l.areaDomain}">${l.area}</a>
                             [/#list]
							</p>					
							<input type="hidden" name="province" value="">
						</div>
						<div class="select city mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p rel="${base}/admin/area/city.do">
								
							</p>
							<input type="hidden" name="city"  value="">
						</div>
						<div class="select area mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p>
								
							</p>
							<input type="hidden" name="area"  value="">
						</div>
					</li>
					<li>
						<strong>商家名称：</strong>
						<span class="sp"><input style="width:300px;" type="text" class="text" name="supplierName" ></span>
					</li>
					<li>
						<strong>创建日期：</strong>
						<span class="sp"><input type="text" class="text Wdate" name="createBegin" id="" onClick="WdatePicker()" readOnly> &nbsp;--</span>
						<span class="sp"><input type="text" class="text Wdate" name="createEnd" id="" onClick="WdatePicker()" readOnly></span>
						<span class="btn search">查 询</span>
					</li>
					
				</ul>
				</form>
			</div>	
							
			<table class="format" id="table_detail">
				<thead>
					<th width="20%">商家名称</th>
					<th width="8%">所在城市</th>	
					<th width="25%">商家地址</th>	
					<th width="5%">联系人</th>	
					<th width="10%">联系电话</th>
					<th width="10%">创建时间</th>
					<th width="5%">商家状态</th>	
					<th width="10%">操作</th>
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td>${item.spName}</td>
						<td>${item.city}</td>		
						<td>${item.address}</td>		
						<td>${item.contactUser}</td>
						<td>${item.contactMobile}</td>
						<td>${item.createTimeHandle}</td>
						<td>${item.statusName}</td>	
						<td>
						   	<a class="obtn" href="${base}/supplierComment/manager/view.xhtml?id=${item.id}&mid=${mid}&pid=${pid}">管理评论</a>	
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
	<tr>
		<td><%=result.elements[i].spName %></td>
		<td><%=result.elements[i].city %></td>
		<td><%=result.elements[i].address %></td>
		<td><%=result.elements[i].contactUser%></td>
		<td><%=result.elements[i].contactMobile%></td>	
		<td><%=result.elements[i].createTimeHandle%></td>	
		<td><%=result.elements[i].statusName%></td>	
		<td> 
		   	<a class="obtn" href="${base}/supplierComment/manager/view.xhtml?id=<%=result.elements[i].id%>&mid=${mid}&pid=${pid}">管理评论</a>		
	    </td>
    </tr>	
<%}%>
</script>

<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/supplierComment/manager/pagination.do?typeId=${typeId}',
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
</body>
</html>