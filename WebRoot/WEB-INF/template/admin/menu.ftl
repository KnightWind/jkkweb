<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>菜单管理</title>
[#include "/common/res.ftl"]
<link rel="stylesheet" type="text/css" href="${base}/styles/opmenu.css">
</head>

<body>
<!-- header -->
[#include "/common/head.ftl"]

<!-- wrap -->
<div class="wrap index bc clr">
	<div class="conWrap fl">
		[#include "/common/nav.ftl"]
		<div class="conBox opmenu">
			<h2><span class="btn">添加一级菜单</span>菜单管理</h2>
			<ul class="sub1 white border bt_none">
				[#list menuList as item]
				<li class="mainNav">
					<p class="hd sp"><span><i class="new tip">&#xf102;</i><i class="new addSub1">&#xf067;</i><i class="new modify">&#xf040;</i><i class="new del sp">&#xf014;</i></span><b menuid="${item.id}" parentName="" name="${item.name}" link="${item.link}">${item.name}</b></p>
					[#if item.children??]
					[#list item.children as item2]
					<ul class="sub2">
						<li>
							<p><span><i class="new addSub2">&#xf067;</i><i class="new modify">&#xf040;</i><i class="new del sp">&#xf014;</i></span><b menuid="${item2.id}" parentName="${item.name}" name="${item2.name}" icon="${item2.icon}" link="${item2.link}">${item2.name}</b></p>
						</li>
						[#if item2.children??]
						<ul class="sub3">
							[#list item2.children as item3]
							<li>
								<p><span><i class="new modify">&#xf040;</i><i class="new del sp">&#xf014;</i></span><b menuid="${item3.id}" name="${item3.name}" parentName="${item2.name}" link="${item3.link}">${item3.name}</b></p>
							</li>
							[/#list]
						</ul>
						[/#if]
					</ul>
					[/#list]
					[/#if]
				</li>
				[/#list]
			</ul>
		</div>
	</div>

	[#include "/common/menu.ftl"]
	
</div>
<!-- footer -->
[#include "/common/foot.ftl"]
</body>
<script type="text/javascript" src="${base}/scripts/form.js"></script>
<script type="text/javascript" src="${base}/scripts/opmenu.js"></script>
<script type="text/html" id="menuEdit">
<div class="ui-dialog menuEdit">
	<h2><span class="close"></span><i class="new del sp">&#xf013;</i> 菜单管理</h2>
	<form id="send">
	<ul>
		<li><strong>父级菜单：</strong> <input type="text" class="text" name="parentName" class="readOnly" placeholder="父级菜单" readOnly></li>
		<li><strong>菜单名称：</strong> <input type="text" class="text" name="name" placeholder="菜单名称"></li>
		<li><strong>链接地址：</strong> <input type="text" class="text" name="link" placeholder="链接地址"></li>
		<li><strong>菜单图标：</strong> <input type="text" class="text" name="icon" placeholder="菜单图标"></li>
		<li class="btn"><span class="btn save1">保存</span></li>
	</ul>
	</form>
</div>	
</script>
</html>