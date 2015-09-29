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
		<form id="search" action="${base}/ba/se/up.xhtml?mid=${mid}&pid=${pid}" method="post">
		<div class="conBox">
			    <div class="page_tab_box">
                <div class="page_tab">
                    <div class="page-tab-head">
                     <span class="page-tab-thisSpan">【首页】搜索下发关键字设置</span>          
                    </div>
                </div>
            </div>
			<table class="format">
			<thead>
					<th width="8%">位置一</th>
					<th width="8%">位置二</th>
					<th width="8%">位置三</th>
					<th width="8%">位置四</th>
					<th width="8%">位置五</th>
					<th width="8%">位置六</th>			
				</thead>
				<tbody>	
				<tr>
					[#list top as item]		
						<td><input type="text" name="word" value="${item.word}" ></td>
						<td><input type="hidden" name="id" value="${item.id}"></td>	
					[/#list]
				</tr>
				</tbody>
		</table>
		
		 <div class="page_tab_box">
                <div class="page_tab">
                    <div class="page-tab-head">
                     <span class="page-tab-thisSpan">【首页-户型效果区域】左边标签设置</span>          
                    </div>
                </div>
            </div>
			<table class="format">
			<thead>
					<th width="8%">位置一</th>
					<th width="8%">位置二</th>
					<th width="8%">位置三</th>
					<th width="8%">位置四</th>
					<th width="8%">位置五</th>
					<th width="8%">位置六</th>
					<th width="8%">位置七</th>
					<th width="8%">位置八</th>
					<th width="8%">位置九</th>
					<th width="8%">位置十</th>
					<th width="8%">位置十一</th>
					<th width="8%">位置十二</th>	
				</thead>
				<tbody>
				<tr>
					[#list hu as item]		
						<td><input type="text" name="word" value="${item.word}" ></td>
						<td><input type="hidden" name="id" value="${item.id}"></td>
					[/#list]
				</tr>	
				</tbody>
		</table>
		 <div class="page_tab_box">
                <div class="page_tab">
                    <div class="page-tab-head">
                     <span class="page-tab-thisSpan">【首页-风格效果区域】左边标签设置</span>          
                    </div>
                </div>
            </div>
			<table class="format">
			<thead>
					<th width="8%">位置一</th>
					<th width="8%">位置二</th>
					<th width="8%">位置三</th>
					<th width="8%">位置四</th>
					<th width="8%">位置五</th>
					<th width="8%">位置六</th>
					<th width="8%">位置七</th>
					<th width="8%">位置八</th>
					<th width="8%">位置九</th>
					<th width="8%">位置十</th>
					<th width="8%">位置十一</th>
					<th width="8%">位置十二</th>	
				</thead>
				<tbody>	
				<tr>
					[#list feng as item]		
						<td><input type="text" name="word" value="${item.word}" ></td>
						<td><input type="hidden" name="id" value="${item.id}"></td>		
					[/#list]
				</tr>
				</tbody>
		</table>
		<input type="submit" value="发布">
		</form>
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