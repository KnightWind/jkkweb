<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>轮播图列表</title>
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
				
				<ul>
					<li>
						<strong><b class="red">*</b>公司所在地：</strong>
						<div class="select province mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p rel="${base}/region/childrenRegion.do">
							<a value="">--请选择--</a>
                            [#list regionList as item]
                                <a value="${item.regionid}">${item.regionname}</a>
                             [/#list]
							</p>					
							<input type="hidden" name="pid" value="">
						</div>
						<div class="select city mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p rel="${base}/region/childrenRegion.do">								
							</p>
							<input type="hidden" name="pid"  value="">
						</div>
						<div class="select area mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p>								
							</p>
							<input type="hidden" name="regionId" class="regionId" value="">
						</div>
					</li>		
					<li>
					
					</li>
				</ul>
				</form>
			</div>
			    <div class="page_tab_box">
                <div class="page_tab">
                    <div class="page-tab-head">
                     <span class="page-tab-thisSpan">【首页】顶部banner轮换图</span>
                     <a class="obtn" href="${base}/shop/tianjia.xhtml?mid=${mid}&pid=${pid}&wz=1&city=${city}">添加轮换图</a>
                    </div>
                </div>
            </div>	   
			<table class="format">
				<thead>					
					<th width="8%">轮换图片</th>				
					<th width="9%">顺序</th>
					<th width="9%">连接地址</th>
					<th width="9%">状态</th>				
					<th width="20%">操作</th>				
				</thead>
				<tbody>
					[#list ind as item]
					<tr>			
						<td>${item.title}</td>
						<td>${item.seq}</td>
						<td>${item.place}</td>
						<td>${item.ison}</td>							
						<td>
						
						[#if item.status==0]
						<a class="obtn" href="${base}/shop/li.xhtml?mid=${mid}&pid=${pid}&id=${item.id}&wz=1&city=${city}">修改</a>
						<span class="btn del" rel="${base}/shop/oper.do?id=${item.id}&isopen=${item.status}">禁用</span>
						[#else]
						<a class="obtn" href="${base}/shop/li.xhtml?mid=${mid}&pid=${pid}&id=${item.id}&wz=1&city=${city}">修改</a>
						<span class="btn del" rel="${base}/shop/oper.do?id=${item.id}&isopen=${item.status}">启用</span>
						[/#if]
						</td>
					</tr>
					[/#list]
				</tbody>
		</table>
		  <div class="page_tab_box">
                <div class="page_tab">
                    <div class="page-tab-head">
                     <span class="page-tab-thisSpan">【礼卷导航】顶部banner轮换图</span>
                     <a class="obtn" href="${base}/shop/tianjia.xhtml?mid=${mid}&pid=${pid}&wz=2&city=${city}">添加轮换图</a>
                    </div>
                </div>
            </div>
		<table class="format">
				<thead>					
					<th width="8%">轮换图片</th>				
					<th width="9%">顺序</th>
					<th width="9%">连接地址</th>
					<th width="9%">状态</th>				
					<th width="20%">操作</th>				
				</thead>
				<tbody>
					[#list quan as item]
					<tr>			
						<td>${item.title}</td>
						<td>${item.seq}</td>
						<td>${item.place}</td>
						<td>${item.ison}</td>							
						<td>			
						[#if item.status==0]
						<a class="obtn" href="${base}/shop/li.xhtml?mid=${mid}&pid=${pid}&id=${item.id}&wz=2&city=${city}">修改</a>
						<span class="btn del" rel="${base}/shop/oper.do?id=${item.id}&isopen=${item.status}">禁用</span>
						[#else]
						<a class="obtn" href="${base}/shop/li.xhtml?mid=${mid}&pid=${pid}&id=${item.id}&wz=2&city=${city}">修改</a>
						<span class="btn del" rel="${base}/shop/oper.do?id=${item.id}&isopen=${item.status}">启用</span>
						[/#if]
						</td>
					</tr>
					[/#list]
				</tbody>
		</table>
		<div class="page_tab_box">
                <div class="page_tab">
                    <div class="page-tab-head">
                     <span class="page-tab-thisSpan">【效果图导航】顶部banner轮换图</span>
                     <a class="obtn" href="${base}/shop/tianjia.xhtml?mid=${mid}&pid=${pid}&wz=3&city=${city}">添加轮换图</a>
                    </div>
                </div>
            </div>
		<table class="format">
				<thead>					
					<th width="8%">轮换图片</th>				
					<th width="9%">顺序</th>
					<th width="9%">连接地址</th>
					<th width="9%">状态</th>				
					<th width="20%">操作</th>				
				</thead>
				<tbody>
					[#list design as item]
					<tr>			
						<td>${item.title}</td>
						<td>${item.seq}</td>
						<td>${item.place}</td>
						<td>${item.ison}</td>							
						<td>			
						[#if item.status==0]
						<a class="obtn" href="${base}/shop/li.xhtml?mid=${mid}&pid=${pid}&id=${item.id}&wz=3&city=${city}">修改</a>
						<span class="btn del" rel="${base}/shop/oper.do?id=${item.id}&isopen=${item.status}">禁用</span>
						[#else]
						<a class="obtn" href="${base}/shop/li.xhtml?mid=${mid}&pid=${pid}&id=${item.id}&wz=3&city=${city}">修改</a>
						<span class="btn del" rel="${base}/shop/oper.do?id=${item.id}&isopen=${item.status}">启用</span>
						[/#if]
						</td>
					</tr>
					[/#list]
				</tbody>
		</table>
		<div class="page_tab_box">
                <div class="page_tab">
                    <div class="page-tab-head">
                     <span class="page-tab-thisSpan">【新闻首页】顶部banner轮换图</span>
                     <a class="obtn" href="${base}/shop/tianjia.xhtml?mid=${mid}&pid=${pid}&wz=4&city=${city}">添加轮换图</a>
                    </div>
                </div>
            </div>
		<table class="format">
				<thead>					
					<th width="8%">轮换图片</th>				
					<th width="9%">顺序</th>
					<th width="9%">连接地址</th>
					<th width="9%">状态</th>				
					<th width="20%">操作</th>				
				</thead>
				<tbody>
					[#list news as item]
					<tr>			
						<td>${item.title}</td>
						<td>${item.seq}</td>
						<td>${item.place}</td>
						<td>${item.ison}</td>							
						<td>			
						[#if item.status==0]
						<a class="obtn" href="${base}/shop/li.xhtml?mid=${mid}&pid=${pid}&id=${item.id}&wz=4&city=${city}">修改</a>
						<span class="btn del" rel="${base}/shop/oper.do?id=${item.id}&isopen=${item.status}">禁用</span>
						[#else]
						<a class="obtn" href="${base}/shop/li.xhtml?mid=${mid}&pid=${pid}&id=${item.id}&wz=4&city=${city}">修改</a>
						<span class="btn del" rel="${base}/shop/oper.do?id=${item.id}&isopen=${item.status}">启用</span>
						[/#if]
						</td>
					</tr>
					[/#list]
				</tbody>
		</table>
	</div>
	</div>
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]
</body>
</html>