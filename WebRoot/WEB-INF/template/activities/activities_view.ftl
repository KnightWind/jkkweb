<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>添加"${act.name}"奖项</title>
[#include "/common/res.ftl"]
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="${base}/scripts/form.js"></script>
<style>
	tr{
		padding-top:10px;
	}
	td{height:55px;font-size:14px;}
	.td_left{text-align:right;font-size:15px;font-weight:bold;}
	#table_detail{mangin:50px;}
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
			<div class="tools white border mb10 sp10 clr">
			 <table id="table_detail"  class="table_09_1 padding-td-10">
                <tbody>
                    <tr>
                    	<td class="td_left">活动名称：</td>
                    	<td><lable>${act.name}</lable></td>
                    </tr>
                    <tr>
                    	<td class="td_left">是否显示数量：</td>
                    	<td><lable>${act.disply}</lable></td>
                    </tr>
                    <tr>
                    	<td class="td_left">每人参与总次数：</td>
                    	<td><lable>${act.total}</lable></td>
                    </tr>
                    <tr>
                    	<td class="td_left">每人每天参与次数：</td>
                    	<td><lable>${act.partNum}</lable></td>
                    </tr>
                    <tr>
                    	<td class="td_left">每天最多出奖数量：</td>
                    	<td><lable>${act.maxNum}</lable></td>
                    </tr>
                    <tr>
                    	<td class="td_left">创建时间：</td>
                    	<td><lable>${act.createTimeString}</lable></td>
                    </tr>
                    <tr>
                    	<td class="td_left">活动介绍：</td>
                    	<td><lable><textarea disabled="disabled" rows="2" cols="28" class="text" >${act.description}</textarea></lable></td>
                    </tr>
                    <tr>
                    	<td class="td_left">&nbsp;</td>
                    	<td><span onclick="return validata(this)" class="btn">提交保存</span></td>
                    </tr>
                </tbody>
             </table>
			</div>
		</div>
	</div>
	
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]
<script>

	$(function(){
		
		
	});

</script>
</body>
</html>