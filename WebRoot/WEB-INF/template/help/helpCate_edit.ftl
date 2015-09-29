<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>创建帮助中心</title>
[#include "/common/res.ftl"]
<link rel="stylesheet" type="text/css" href="${base}/styles/event.css">
</head>

<body>
<!-- header -->
[#include "/common/head.ftl"]

<!-- wrap -->
<div class="wrap index bc clr">
	<div class="conWrap fl">
		[#include "/common/nav.ftl"]
		<div class="conBox">
			<form action="${base}/help/save.do" method="post">
			<input type="hidden" name="id" value="${helpInfo.id}"/>
				<div class="event">
					<ul class="border white">
						<li>
						<strong> 目录：</strong>
						<div class="select pHelp mr10 noclick">
							<i class="new">&#xf107;</i><span class="no">${helpInfo.pname}</span>
						</div>
					   <div class="select sHelp mr10 noclick">
							<i class="new">&#xf107;</i><span class="no">${helpInfo.name}</span>
						</div>
					   </li>
					   <li class="auto"><strong>内容：</strong><textarea name="content">${helpInfo.vcontent}</textarea></li>
					   <li class="btn"><strong>&nbsp;</strong><input type="submit" class="submit" value="保 存"/></li>
				 </div>
			 </form>
	    </div>
	</div>
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]
</body>
</html>