<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>商品列表</title>
[#include "/common/res.ftl"]
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
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
				<form id="search">
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
							<input type="hidden" name="city"  value="">
						</div>
						<div class="select area mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p>
								
							</p>
							<input type="hidden" name="area"  value="">
						</div>
						<span class="sp"><strong>所属商户：</strong><input type="text" class="text" name="" id=""></span>
					</li>
					<li>
						<span class="sp"><strong>商品编号：</strong><input type="text" class="text" name="" id=""></span>
						<span class="sp"><strong>商品名称：</strong><input type="text" class="text" name="" id=""></span>
					</li>
					<li>
						<strong>商品品牌：</strong>
						<div class="select">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p>
								<a value="huadong">华东</a>
								<a value="huanan">华南</a>
								<a value="zhongnan">中南</a>
								<a value="dongbei">东北</a>
								<a value="xinan">西南</a>
								<a value="huabei">华北</a>
								<a value="xibei">西北</a>
								<a value="gangaotai">港澳台</a>
							</p>
						</div>
						<span class="sp"><strong>管理员编号</strong><input type="text" class="text" name="" id=""></span>
						<span class="btn">查 询</span>
					</li>
					<li>
						<strong>商品状态：</strong><span class="sp radio"><input type="radio" name="status" id="status"><label for="status"> 全部 </label><input type="radio" name="status" id="status1"><label for="status1"> 上架 </label><input type="radio" name="status" id="status2"><label for="status2"> 下架 </label></span>
						<strong>0 元 购：</strong><span class="sp radio"><input type="radio" name="status1" id="status3"><label for="status3"> 全部 </label><input type="radio" name="status1" id="status4"><label for="status4"> 参与 </label><input type="radio" name="status1" id="status5"><label for="status5"> 不参与 </label></span>
					</li>
				</ul>
				</form>
			</div>
			
             <div class="page_tab_box">
                <div class="page_tab">
                    <div class="page-tab-head">
                        <span class="page-tab-thisSpan"><a href="${base}/goods/item/zheng.do">正常商品</a></span>
                        <span ><a href="${base}/goods/item/dong.do">冻结商品</a></span>
                    </div>
                </div>
            </div>
            
			<table class="format">
				<thead>
					<th width="8%"><input type="checkbox" name=""  /></th>
					<th width="8%">商品编号</th>
					<th width="8%">商品图片</th>
					<th width="8%">商品名称</th>
					<th width="8%">品牌</th>
					<th width="8%">商品价格</th>
					<th width="8%">商品状态</th>
					<th width="9%">冻结状态</th>
					<th width="9%">库存数量</th>
					<th width="9%">最后编辑时间</th>
					<th width="20%">操作</th>				
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td><input type="checkbox" name=""  /></td>
						<td>${item.id}</td>
						<td><img  src="${item.pid}"></td>
						<td>${item.title}</td>
						<td>${item.brandId}</td>
						<td>${item.price}</td>					
						<td>${item.operate}</td>
						<td>${item.isOperate}</td>	
						<td>${item.stock}</td>	
						<td>${item.updateTime?string("yyyy-MM-dd")}</td>									
						<td>
						<span class="btn modify">查看</span>
						<span class="btn modify">冻结</span>
						<span class="btn modify">取消0元购商品</span>
						</td>
					</tr>
					[/#list]
				</tbody>
		</table>
		[#include "/common/pagination.ftl"]
	</div>
	</div>
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]
</body>
</html>