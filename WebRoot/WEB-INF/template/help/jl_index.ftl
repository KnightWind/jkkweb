<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<title>现金券转码处理</title>
		[#include "/common/res.ftl"]
		<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
		<script type="text/javascript" src="${base}/scripts/form.js"></script>
		<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
		<style>
		.logo{height:82px; background:#fff; border-bottom:2px solid #1D9DA6;}
		.logo p{padding-top:20px;}
		.logo p a{color:#333; font-size:14px;}
		.logo p a i{ font-size:16px;}
		.conWrap h2.hd{ border-color:#ccc;}
		.dataShow{ width:48%; height:500px; float:left; padding-top:20px;}
		.dataShow p{height:40px;}
		.dataShow table{ margin-bottom:20px;}
		.dataShow table td{ border-right:1px solid #ccc;}
		.dataSp{ margin-right:4%;}
		</style>
	</head>
		<body>
		<form action="${base}/help/shengcheng.do" method="post">
			<input type="text" name="text1" style="width:500px;" /><br/>
			<input type="text" name="text2" style="width:500px;"/><br/>
			<input type="text" name="text3" style="width:500px;"/><br/>
			<input type="text" name="text4" style="width:500px;"/><br/>
			<input type="submit" value="生成"/>
		</form>
		</body>
	</html>