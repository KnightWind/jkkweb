<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>限时折扣管理</title>
[#include "/common/res.ftl"]
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
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
					<label style="float:left;width:10%;text-align:right;">折扣商品：</label>
					<select size=10 id="itemSelect" style="width:600px;margin-left:4px;">
						[#if item??]
						<option value="${item.id}" price="${item.price}" selected>${item.title}&nbsp;&nbsp;&nbsp;&nbsp;${item.price}</option>
						[/#if]
					</select>
				</div>
				<div style="margin-top:10px;">
					<label style="float:left;width:10%;text-align:right;">商品价格：</label>
					<span style="margin-left:4px;">￥<span id="selectPrice">${item.price}</span></span>
					<input type="hidden" name="itemId" id="itemId" value="${item.id}" />
					<input type="hidden" name="id" id="id" value="${view.id}" />
					<input type="hidden" name="status" id="status" value="${view.status}" />
					<input type="hidden" name="saleNum" id="saleNum" value="${view.saleNum}" />
					<input type="hidden" name="createTime" id="createTime" value="${view.createTimeStr}" />
				</div>
				<div style="margin-top:10px;">
					<label style="float:left;width:10%;text-align:right;">限时价格：</label>
					<span style="margin-left:4px;"><input id="discount" name="discount" value="${view.discount}"></span>
					<label style="margin-left:20px;">优惠金额：</label>
					<span style="">￥<span id="lessPrice">${lessAmount}</span></span>
				</div>
				<div style="margin-top:10px;">
					<label style="float:left;width:10%;text-align:right;">折扣日期：</label>
					<span style="margin-left:4px;"><input type="text" class="text Wdate" id="startDate" name="startDate" value="${view.startDateStr}" onClick="WdatePicker()" readOnly></span>
					<label style="margin-left:8px;margin-right:8px">-</label>
					<span><input type="text" class="text Wdate" id="endDate" name="endDate" value="${view.endDateStr}" onClick="WdatePicker()" readOnly></span>
				</div>
				<div style="margin-top:10px;">
					<label style="float:left;width:10%;text-align:right;">人均限购：</label>
					<span style="margin-left:4px;"><input id="limitNum" name="limitNum" value="${view.limitNum}"></span>
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
            $('#itemSelect').html(template.render('item',data)); 
        },'json');
	});
	$("#itemSelect").change(function() {
		var option = $("option:selected", this);
		$("#selectPrice").html(option.attr("price"));
		$("#itemId").val(option.val());
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