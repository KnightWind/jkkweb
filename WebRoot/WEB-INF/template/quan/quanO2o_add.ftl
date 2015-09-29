<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>添加O2O券</title>
[#include "/common/res.ftl"]
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/form.js"></script>
<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
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
				<div>
					<label style="float:left;width:10%;text-align:right;">O2O券批次：</label>
					<span style="margin-left:4px;"><input class="text" id="no" name="no" value=""></span>
				</div>
				<div style="margin-top:10px;">
					<label style="float:left;width:10%;text-align:right;">O2O实际面值：</label>
					<span style="margin-left:4px;"><input class="text" id="parValue" name="parValue" value=""></span>
				</div>
				<div style="margin-top:10px;">
					<label style="float:left;width:10%;text-align:right;">O2O有效期限：</label>
					
					<span style="margin-left:4px;">				 
					  <input class="text  Wdate" id="expireStart" name="expireStart" value="" onClick="WdatePicker()" readOnly>
					</span>
					<label style="margin-left:8px;margin-right:8px">-</label>
					<span>
					  <input id="expireEnd" class="text  Wdate" name="expireEnd" value="" onClick="WdatePicker()" readOnly>
					</span>
				</div>
				<div style="margin-top:10px;">
					<label style="float:left;width:10%;text-align:right;">O2O预发总数：</label>
					<span style="margin-left:4px;"><input id="num" class="text" type="text" name="num" value=""></span>
				</div>
				<div style="margin-top:10px;margin-bottom:20px;">
					<label style="float:left;width:10%;text-align:right;">&nbsp;</label>
					<span style="margin-left:4px;">
						<span class="btn saveBtn">保存</span>&nbsp;
						<span class="btn " onclick="history.back()">返回</span>
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