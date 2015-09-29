<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>平台参数设置</title>
[#include "/common/res.ftl"]
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="${base}/scripts/form.js"></script>
<link rel="stylesheet" type="text/css" href="${base}/styles/event.css">
<style>
   .areaStyle{
      width:666px;
      height:200px;
   }
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
			<div class="shd"><strong>参数设置操作</strong></div>
            <div class="tools white border mb10 sp10 clr event">
				<form id="search" action="${base}/admin/sy/up.xhtml?mid=${mid}&pid=${pid}" method="post">
				<ul>
					<li>
						<strong>参数名称：</strong>
						<input type="text" name="paraName" style="width:350px;" class="text" value="${sys.paraName}"/>
					</li>		
					<!--
						<li>
							<strong>参数值：</strong>
							<input type="text" name="paraValue" class="text" value="${sys.paraValue}"/>
						</li>
					-->
					<li class="auto">
					    <strong>参数值：</strong>
						<textarea  name="paraValue">${sys.paraValue}</textarea>
					</li>
					<li class="auto">
						<strong>参数描述：</strong>
						<textarea  name="paraDesc">${sys.paraDesc}</textarea>
					</li>
					<li class="btn">
						<strong>&nbsp;</strong>
						<input type="hidden" name="paraCode" value="${sys.paraCode}"/>
						<input type="submit" value="保存" class="submit">&nbsp;
						<span class="btn search" onclick="history.back()" style="font-size:12px;">返回</span>
					</li>
				</ul>
				</form>
			</div>
		
		[#include "/common/pagination.ftl"]
	</div>
	</div>
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]
</body>
</html>