<div class="header">
	<div class="logo">
		<h1><a href="#">后台管理系统</a></h1>
		<p>
			<a href="#"><i class="new">&#xf015;</i> 首页</a>
			<a href="#"><i class="new">&#xf0e0;</i> 消息</a>
			<a href="#"><i class="new">&#xf013;</i> 设置</a>
			<a href="#"><i class="new">&#xf095;</i> 帮助</a>
			<a href="javascript:;" class="logout"><i class="new">&#xf08b;</i> 安全退出</a>
		</p>
	</div>
	<div class="nav">
		<h2><p class="img"><img src="${base}/images/t.jpg" alt=""></p>AdminLAD</h2>
		<a href="javascript:;" class="btn next"><i class="new">&#xf053;</i></a>
		<div class="menuList">
			<ul>
				[#list topMenu as item]
				<li rel="${item.id}" class="[#if pid==item.id]cur[/#if]"><a href="${base}/admin/index.xhtml?pid=${item.id}" class="[#if pid==item.id]cur[/#if]">${item.name}</a></li>
				[/#list]
			</ul>
		</div>
		<a href="javascript:;" class="btn prev"><i class="new">&#xf054;</i></a>
	</div>
</div>
<script>
	$("body").delegate("a.logout", 'click', function() {
		document.location.href = '${base}/admin/logout.do';
	});
</script>