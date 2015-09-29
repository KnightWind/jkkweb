<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>代金劵列表</title>
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
				<form id="search">
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
							<input type="hidden" name="city" value="">
						</div>
						<div class="select area mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p>
								
							</p>
							<input type="hidden" name="area"  value="">
						</div>
						
					</li>
					<li><span class="sp"><strong>券批次：</strong><input type="text" class="text" name="no" id=""></span><span class="btn search">查 询</span></li>
					<li>
						<strong>券类型：</strong><span class="sp radio"><input type="radio" name="type" id="id1" value=""><label for="id1"> 全部 </label><input type="radio" name="type" id="id2" value="1"><label for="id2"> 全品券 </label><input type="radio" name="type" id="id3" value="2"><label for="id3">店铺券</label>
						<input type="radio" name="type" id="id3" value="3"><label for="id3">O2O券</label>
						</span>
						<strong class="auto">上架状态：</strong><span class="sp radio"><input type="radio" name="zt" id="id4" value=""><label for="id4"> 全部 </label><input type="radio" name="zt" id="id5" value="0"><label for="id5"> 上架 </label><input type="radio" name="zt" id="id6" value="-1"><label for="id6"> 下架 </label></span>
						<strong class="auto">是否过期：</strong><span class="sp radio"><input type="radio" name="gq" id="status3" value=""><label for="status3"> 全部 </label><input type="radio" name="gq" id="status4" value="0"><label for="status4"> 是 </label><input type="radio" name="gq" id="status5" value="1"><label for="status5">否</label></span>
					</li>
				</ul>
				</form>
			</div>
			<table class="format">
				<thead>					
					<th width="4%">批次</th>
					<th width="4%">类型</th>
					<th width="8%">区域</th>
					<th width="8%">券名</th>
					<th width="8%">适用店铺</th>
					<th width="8%">券验证码</th>
					<th width="9%">开始时间</th>
					<th width="9%">结束时间</th>
					<th width="9%">上架状态</th>
					<th width="8%">是否过期</th>
					<th width="9%">创建日期</th>
					<th width="20%">操作</th>				
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>				
						<td>${item.no}</td>
						<td>${item.typeName}</td>
						<td>${item.city}</td>
						<td>${item.name}</td>	
						<td>${item.spNameHandle}</td>					
						<td>133333*******12</td>
						<td>${item.expireStartStr}</td>	
						<td>${item.expireEndStr}</td>
						<td>${item.saleingName}</td>						
						<td>${item.dateTime}</td>
						<td>${item.createTimeStr}</td>									
						<td>
						[#if item.saleing==0]
							<span class="btn del" rel="${base}/supplier/quan/oper.do?id=${item.id}&isopen=${item.saleing}">下架</span>
						[#else]
						<span class="btn del" rel="${base}/supplier/quan/oper.do?id=${item.id}&isopen=${item.saleing}">上架</span>
						[/#if]					
						<span class="btn del" rel="${base}/supplier/quan/remove.do?id=${item.id}">注销</span>
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
	         <td><%=result.elements[i].id%></td>
						<td><%=result.elements[i].typeName%></td>
						<td><%=result.elements[i].city%></td>
						<td><%=result.elements[i].name%></td>	
						<td><%=result.elements[i].spNameHandle%></td>							
						<td>133333*******12</td>
						<td><%=result.elements[i].expireStartStr%></td>	
						<td><%=result.elements[i].expireEndStr%></td>
						<td><%=result.elements[i].saleingName%></td>					
						<td><%=result.elements[i].dateTime%></td>
						<td><%=result.elements[i].createTimeStr%></td>
	<td><%if (result.elements[i].saleing==0){%>
						<span class="btn del" rel="${base}/supplier/quan/oper.do?id=<%=result.elements[i].id%>&isopen=<%=result.elements[i].saleing%>">下架</span>
						<%} %>
						<%if (result.elements[i].saleing==-1){%>
						<span class="btn del" rel="${base}/supplier/quan/oper.do?id=<%=result.elements[i].id%>&isopen=<%=result.elements[i].saleing%>">上架</span>
						<%} %></span>
	<span class="btn del" rel="${base}/supplier/quan/remove.do?id=<%=result.elements[i].id%>">注销</span>
</tr>	
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/supplier/quan/pagination.do',
		deal:{'click':'.search','change':'#type,#auditStatus'},
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