<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>首页商品列表</title>
[#include "/common/res.ftl"]

<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/form.js"></script>
<Style>
	tr{
		padding-top:10px;
	}
	td{height:55px;font-size:14px;}
	.td_left{text-align:right;font-size:15px;font-weight:bold;}
	#table_detail{mangin:50px;}
	.requi{color:red};
</style>

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
						<a class="obtn" href="${base}/activityProduct/edit.xhtml?pid=${pid}&mid=${mid}">
						   	 添加商品
						</a>
					</div>			
					<table class="format">
						<thead>
							<th width="5%">商品编号</th>
							<th width="10%">商品图片</th>
							<th width="20%">商品标题</th>
							<th width="10%">商品原价</th>
							<th width="10%">商品活动价</th>
							<th width="15%">操作</th>			
						</thead>
						<tbody>
							[#list pagination.dataList as item]
							<tr>
								<td>${item.id}</td>		
								<td><img src="${basePath}${item.filepath}" width="100" height="100" /></td>
								<td>${item.title}</td>
								<td>${item.iprice}</td>
								<td>${item.price}</td>
								<td>
								    <a class="obtn" href="${base}/activityProduct/edit.xhtml?id=${item.id}&pid=${pid}&mid=${mid}">
									   	修改
									</a>
								    <a class="obtn" href="${base}/activityProduct/remove.xhtml?id=${item.id}&pid=${pid}&mid=${mid}">
								    	删除			   
								    </a>
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
		<td><%=result.elements[i].id%></td>	
		<td><img src="${basePath}<%=result.elements[i].filepath%>" width="100" height="100" /></td>	
		<td><%=result.elements[i].title%></td>
		<td><%=result.elements[i].iprice%></td>
		<td><%=result.elements[i].price%></td>
		<td> 
		    <a class="obtn" href="${base}/activityProduct/edit.xhtml?id=<%=result.elements[i].id&pid=${pid}&mid=${mid}%>">
			   	修改
			</a>
		    <a class="obtn" href="${base}/activityProduct/remove.xhtml?id=<%=result.elements[i].id%>&pid=${pid}&mid=${mid}">
		    	删除			   
		    </a>
	    </td>
    </tr>	
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/activityProduct/pagination.do',
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


function valiData(el){
	
	if($('input[name="name"]').val() == ""){
		justTip("请输入商品名称！");
		return false;
	}
	if($('input[name="iprice"]').val() == ""){
		justTip("请输入商品原价！");
		return false;
	}
	if(!/^\d+(\.\d+)?$/.test($("input[name='iprice']").val())){
		justTip("商品原价数字 !")
		return;
	}
	if($('input[name="price"]').val() == ""){
		justTip("请输入商品活动价！");
		return false;
	}
	if(!/^\d+(\.\d+)?$/.test($("input[name='price']").val())){
		justTip("商品活动价为数字 !")
		return;
	}
	if(!/^\d+$/.test($("input[name='order']").val())){
		justTip("排序为数字 !")
		return;
	}
	
	$(el).parents("form").submit();

}


</script>
<script type="text/html" id="pannel">
	<div class="ui-dialog addManager" style="width:400px;" onload="alert('we')">
		<h2><span class="close"></span><strong>编辑活动卡</strong></h2>
		<form id="send" action="${base}/activityProduct/save.xhtml?mid=${mid}&pid=${pid}" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" />
			<div >
				<table>
					<tr>
						<td class="td_left"><strong><b class="red">*</b> 商品名称：</strong></td>
						<td>
							<input style="width:195px;" type="text" class="text" name="name" placeholder="商品标题">
						</td>
					</tr>
					<tr class="td_left">
						<td><strong><b class="red">*</b> 活动：</strong></td>
						<td>
							<select name="aid" style="width:195px;">
								<option value="-1">--请选择--</option>
								[#list ah as a]
									<option value="${a.id}">${a.title}</option>
								[/#list]
							</select>
						</td>
					</tr>
					<tr class="td_left">
						<td><strong><b class="red">*</b> 商品原价：</strong></td>
						<td><input style="width:195px;" type="text" class="text" name="iprice" placeholder="原价"></td>
					</tr>
					<tr class="td_left">
						<td><strong><b class="red">*</b> 商品活动价：</strong></td>
						<td><input style="width:195px;" type="text" class="text" name="price" placeholder="活动价"></td>
					</tr>
					<tr class="td_left">
						<td><strong><b class="red">*</b>排序：</strong></td>
						<td><input style="width:195px;" type="text" class="text" name="order" placeholder="排序"></td>
					</tr>
					<tr class="td_left">
						<td><strong><b class="red">*</b> 商品图片：</strong></td>
						<td>
							 <input style="width:195px;" name="img" type="file" />
						</td>
					</tr>
					<tr class="td_left">
						<td colspan="2"><span onclick="return valiData(this)" class="btn" rel="add">提交保存</span></td>
					</tr>
				</table>
			</div>
		</form>
	</div>
</script>
</html>