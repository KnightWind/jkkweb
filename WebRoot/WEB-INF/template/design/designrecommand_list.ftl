<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>商品列表</title>
[#include "/common/res.ftl"]
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="${base}/scripts/form.js"></script>
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
				<form id="search" action="${base}/design/xg/cha.xhtml">
				<ul>
					<li>
						<strong>所属区域：</strong>
						<div class="select province mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p rel="${base}/goods/item/sheng.do">
							<a value="">--请选择--</a>
                            [#list lst as l]
                                <a  value="${l.areaDomain}">${l.area}</a>
                             [/#list]
							</p>			
							<input type="hidden" name="province" value="">
						</div>
						<div class="select city mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p rel="${base}/goods/item/city.do">
								
							</p>
							<input type="hidden" name="city" value="">
						</div>
						<div class="select area mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p>
								
							</p>
							<input type="hidden" name="area"  value="">
						</div>
					</li>		
					<li>
					<input type="hidden" name="mid"  value="${mid}">
					<input type="hidden"  name="pid" value="${pid}">
						<input type="submit" value="查 询">
					</li>
				</ul>
				</form>
			</div>
		<div style="width:100%;border:1px solid #ccc;background-color:#fff;">
		[#list index as inn]
					<div style="border:1px solid #ccc;width:18%;height:180px;margin:10px;float:left;position:relative">
						<img src="http://img3.jiakeke.com/design/origin/87/45/d8f53e3c07f37c96bb1193ad966d5c84.jpg"  style="width:100%;height:100%">
						<dd style="position: absolute;right:0px;bottom:0px"><a class="obtn" href="${base}/goods/design/lx.xhtml?mid=205&pid=166&did=${inn.id}&city=${city}">选择</a></dd>					
			</div>
			[/#list]
			</div>
		[#include "/common/pagination.ftl"]
	</div>
	</div>
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]
<script>
$(function(){
	save('span.btn');
});	
</script>
</body>
</html>