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
			    <div class="page_tab_box">
                <div class="page_tab">
                    <div class="page-tab-head">
                     <span class="page-tab-thisSpan">【首页】中部广告管理</span>          
                    </div>
                </div>
            </div>
            <form id="search" action="${base}/de/update.xhtml?mid=${mid}&pid=${pid}" method="post">
			<table class="format">
				<tbody>
					[#list pagination.dataList as item]
					<tr>			
						<td><img src="${item.pid}" /></td>
					</tr>
					<tr>
						<td>链接地址：<input type="text" name="url" value="${item.url}" ></td>
						</tr>
						<tr>
						<td><input type="file"  name="pid"/></td>
						<td><input type="hidden" name="id" value="${item.id}"></td>							
					</tr>
					[/#list]
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