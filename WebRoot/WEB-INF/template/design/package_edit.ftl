<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>限时折扣管理</title>
[#include "/common/res.ftl"]
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/form.js"></script>
<style type="text/css">
.addManager ul{ padding: 20px 0;}
.addManager ul li strong{ width: 10%; display: block; float: left; text-align: right; margin-right: 5px;}
.addManager ul li input{ width: 120px;height:22px; float: left;}
.addManager ul li textarea{ width: 200px; float: left;}
.addManager .buttons{ margin-left: 10%; float: left; margin-top:10px;margin-bottom:10px;padding-left:4px;}
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
			<div class="ui-dialog addManager" style="width:100%;">
				<form id="send" action="${base}/item/special/save.do">
				<ul>
					<li>
						<strong>所属区域：</strong>
						<div class="select province mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p rel="${base}/admin/area/sheng.do">
							<a value="">--请选择--</a>
                            [#list lst as item]
                                <a value="${item.areaDomain}">${item.area}</a>
                             [/#list]
							</p>					
						</div>
						<div class="select city mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p rel="${base}/admin/area/city.do"></p>
						</div>
						<div class="select area mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p rel="${base}/supplier/findByArea.do"></p>
						</div>
						<div class="select supplier mr10" style="width:200px;">
							<i class="new">&#xf107;</i><span style="width:200px;">--请选择--</span>
							<p style="width:208px;"></p>
						</div>
						<div class="supplier mr10">
							<input type="hidden" id="supplierid" />
							<input type="text" id="text" />&nbsp;
							<span class="btn searchSupplier">查询</span>
						</div>
					</li>
				</ul>
				<div>
					<label style="float:left;width:10%;text-align:right;">套餐商品：</label>
					<select size=14 id="leftSelect" style="float:left;width:400px;margin-left:4px;">
					</select>
					
					<span style="float:left;margin-left:10px;margin-right:10px;text-align:center;">
						<br><br><br><br>
						<span class="btn addOne">>></span><br><br>
						<span class="btn delOne"><<</span><br><br>
						<span class="btn delAll"><<</span>
					</span>
					
					<select size=14 id="rightSelect" style="width:400px;margin-left:4px;">
					</select>
				</div>
				<div style="margin-top:10px;">
					<label style="float:left;width:10%;text-align:right;">套装名称：</label>
					<span style="margin-left:4px;"><input id="limitNum" name="packageName" value="${view.packageName}"></span>
				</div>
				<div style="margin-top:10px;">
					<label style="float:left;width:10%;text-align:right;">套装类型：</label>
					<span style="margin-left:4px;">
						<label><input type="radio" name="type" [#if view.type=1]checked[/#if] value="1">主材包</label>
						<label><input type="radio" name="type" [#if view.type=2]checked[/#if] value="2">软装包</label>
					</span>
				</div>
				<div style="margin-top:10px;">
					<label style="float:left;width:10%;text-align:right;">套装图片：</label>
					<span style="margin-left:4px;"><img id="logo" name="logo" src="${view.logo}" /></span>
				</div>
				<div style="margin-top:10px;">
					<label style="float:left;width:10%;text-align:right;">商品价格：￥</label>
				</div>
				<div style="margin-top:10px;">
					<label style="float:left;width:10%;text-align:right;">套装价格：</label>
					<span style="margin-left:4px;"><input id="price" name="price" value="${view.price}"></span>
				</div>
				<div style="margin-top:10px;">
					<label style="float:left;width:10%;text-align:right;">套装数量：</label>
					<span style="margin-left:4px;"><input id="stock" name="stock" value="${view.stock}"></span>
				</div>
				<div style="margin-top:10px;">
					<label style="float:left;width:10%;text-align:right;">套装状态：</label>
					<span style="margin-left:4px;"><input type="checkbox" id="status" name="status" [#if view.type=0]checked[/#if] value="0">${view.statusName}</span>
				</div>
				<div style="margin-top:10px;margin-bottom:20px;">
					<label style="float:left;width:10%;text-align:right;">&nbsp;</label>
					<span style="margin-left:4px;">
						<span class="btn saveBtn">保存</span>&nbsp;
						<a href="${base}/item/special/index.xhtml?mid=${mid}&pid=${pid}"><span class="btn">返回</span></a>
					</span>
				</div>
				</form>
			</div>
		</div>
	</div>
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]
<script type="text/html" id="supplier">
<%for(i=0;i<list.length;i++){%>
	<a supplierid="<%=list[i].id%>"><%=list[i].spName%></a>
<%}%>
</script>
<script type="text/html" id="item">
<%for(i=0;i<list.length;i++){%>
	<option value="<%=list[i].id%>" price="<%=list[i].price%>"><%=list[i].title%>&nbsp;&nbsp;&nbsp;&nbsp;<%=list[i].price%></option>
<%}%>
</script>
<script>
$(function(){
	$('.supplier p').delegate('a','click',function(){
		$("#supplierid").val($(this).attr("supplierid"));
	});
	$(".searchSupplier").click(function() {
		var url = "${base}/goods/item/searchItem.do";
		$.post(url,{"supplierid":$("#supplierid").val(), text:$("#text").val()},function(data){
            $('#leftSelect').html(template.render('item',data)); 
        },'json');
	});
	
	$(".addOne").click(function() {
		var option = $("#leftSelect option:selected");
		if (!option.length) {
			justTip("请选选择商品");
			return;
		}
		option.remove();
		$("#rightSelect").append(option);
	});
	$(".delOne").click(function() {
		var option = $("#rightSelect option:selected");
		if (!option.length) {
			justTip("请选选择商品");
			return;
		}
		option.remove();
		$("#leftSelect").append(option);
	});
	$(".delAll").click(function() {
		$("#rightSelect option").each(function() {
			$(this).remove();
			$("#leftSelect").append(this);
		});
	});
	
	$("#discount").blur(function() {
		var discount = $(this).val();
		var price = $("#selectPrice").html();
		if(price && discount) {
			price = parseFloat(price);
			discount = parseFloat(discount);
			$("#lessPrice").html(price-discount);
		}
	});
	
	$('body').delegate(".saveBtn",'click',function(){
        $("#send").ajaxSubmit({
             type:"post",
             success:function(res){
                if(res.ret==1){
                	document.location.href = "${base}/item/special/edit.xhtml?id=" + res.data.id+"&mid=${mid}&pid=${pid}";
                }else{justTip(res.msg);}
             }
        });
        delMask();
    });
});	
</script>
</body>
</html>