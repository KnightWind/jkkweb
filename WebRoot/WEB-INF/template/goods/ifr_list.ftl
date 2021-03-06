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
                                <a value="${l.areaDomain}">${l.area}</a>
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
			[#list list as inn]
					<div style="border:1px solid #ccc;width:13%;height:240px;margin:10px;float:left;position:relative">
						<img src="http://img2.jiakeke.com/item/origin/97/83/c5f72c1c3cecfa71ff6a9361f67c26af.jpg"  style="width:100%;height:70%">
						<div>${inn.title}</div>
						<span style="display:block;position:absolute;left:0px;bottom:0px">￥${inn.price}</span>
						<dd class="btn_base btn_l_blue_dark"  style="position: absolute;right:0px;bottom:0px"><a class="obtn" href="${base}/shop/item/xuanzhe.xhtml?mid=${mid}&pid=${pid}&did=${inn.id}&city=${city}">选择</a></dd>
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