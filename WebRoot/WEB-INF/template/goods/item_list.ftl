<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>商品列表</title>
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
                                <a  value="${l.areaDomain}">${l.area}</a>
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
					</li>
					<li>
						<span class="sp"><strong>所属商户：</strong><input type="text" class="text" name="sname" id=""></span>
						<span class="sp"><strong class="auto">商品编号：</strong><input type="text" class="text" name="id" id=""></span>
						<span class="sp"><strong class="auto">商品名称：</strong><input type="text" class="text" name="title" id=""></span>
					</li>
					<li>
						<strong>商品品牌：</strong>
						<div class="select mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p>
							[#list paginationq.dataList as b]
                                <a href="javascript:;" value="${b.id}">${b.name}</a>
                            [/#list]
							</p>
							<input type="hidden" name="bid">
						</div>
						<input type="hidden" name="is" value="${is}">
						<span class="btn search">查 询</span>
					</li>
					<li>
						<strong>商品状态：</strong><span class="sp radio"><input type="radio" name="zt" id="status" value=""><label for="status"> 全部 </label><input type="radio" name="zt" id="status1" value="0"><label for="status1"> 上架 </label><input type="radio" name="zt" id="status2" value="1"><label for="status2"> 下架 </label></span>
						<strong class="auto">0 元 购：</strong><span class="sp radio"><input type="radio" name="oyg" id="status3" value=""><label for="status3"> 全部 </label><input type="radio" name="oyg" id="status4" value="1"><label for="status4"> 参与 </label><input type="radio" name="oyg" id="status5" value="0"><label for="status5"> 不参与 </label></span>
					</li>
				</ul>
				
				</form>
			</div>
			
             <div class="page_tab_box">
                <div class="page_tab">
                    <div class="pageTab">
                        <span class="cur"><a href="${base}/goods/item/index.xhtml?mid=${mid}&pid=${pid}&state=0">正常商品</a></span>
                        <span><a href="${base}/goods/item/index.xhtml?mid=${mid}&pid=${pid}&state=1">冻结商品</a></span>
                    </div>
                </div>
            </div>
            
			<table class="format">
				<thead>					
					<th width="6%">商品编号</th>
					<th width="21%">商品名称</th>
					<th width="8%">品牌</th>
					<th width="6%">商品价格</th>
					<th width="6%">商品状态</th>
					<th width="6%">冻结状态</th>
					<th width="6%">库存数量</th>
					<th width="12%">最后编辑时间</th>
					<th width="20%">操作</th>				
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td>${item.id}</td>
						<td>${item.title}</td>
						<td>${item.bname}</td>
						<td>${item.price}</td>					
						<td>${item.shang}</td>
						<td>${item.dong}</td>	
						<td>${item.stock}</td>	
						<td>${item.up}</td>									
						<td>
						<span class="btn modify">查看</span>
						[#if item.isLock==0]
						<span class="btn del" rel="${base}/goods/item/operate.do?id=${item.id}&isopen=${item.isLock}">冻结</span>
						[#else]
						<span class="btn del" rel="${base}/goods/item/operate.do?id=${item.id}&isopen=${item.isLock}">解冻</span>
						[/#if]
						[#if item.isFree==1]
						<span class="btn del" rel="${base}/goods/item/oper.do?id=${item.id}&isopen=${item.isFree}">取消0元购商品</span>
						[#else]
						<span class="btn del" rel="${base}/goods/item/oper.do?id=${item.id}&isopen=${item.isFree}">设置0元购商品</span>
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
	<td><%=result.elements[i].title%></td>
	<td><%=result.elements[i].bname%></td>
	<td><%=result.elements[i].price%></td>					
	<td><%=result.elements[i].shang%></td>
	<td><%=result.elements[i].dong%></td>
	<td><%=result.elements[i].stock%></td>	
	<td><%=result.elements[i].up%></td>
	<td>
		<span class="btn modify">查看</span>
		<span class="btn del" rel="${base}/goods/item/operate.do?id=<%=result.elements[i].id%>&isopen=<%=result.elements[i].isLock%>">
			<%=result.elements[i].isLock == 0 ? "冻结" : "解冻" %>
		</span>
		<%if (result.elements[i].isFree==1){%>
		<span class="btn del" rel="${base}/goods/item/oper.do?id=<%=result.elements[i].id%>&isopen=<%=result.elements[i].isFree%>">取消0元购商品</span>
		<%} %>
		<%if (result.elements[i].isFree==0){%>
		<span class="btn del" rel="${base}/goods/item/oper.do?id=<%=result.elements[i].id%>&isopen=<%=result.elements[i].isFree%>">设置0元购商品</span>
		<%} %>
	</td>
</tr>	
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/goods/item/pagination.do?state=${state}',
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
	var status = '${state}';
	$('.pageTab span.cur').removeClass('cur');
	switch(status){
		case "0":$('.pageTab span:eq(0)').addClass('cur');break;
		case "1":$('.pageTab span:eq(1)').addClass('cur');break;
		default:$('.pageTab span:eq(0)').addClass('cur');break;
	}
});	
</script>
</body>
</html>