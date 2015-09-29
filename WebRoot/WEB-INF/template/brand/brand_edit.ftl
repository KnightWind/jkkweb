<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>商品分类列表</title>
[#include "/common/res.ftl"]
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/form.js"></script>
<style type="text/css">
.addManager ul{ padding: 20px 0;}
.addManager ul li{ height: 46px; line-height: 30px; text-align: center; font-size: 14px;}
.addManager ul li strong{ width: 10%; display: block; float: left; text-align: right; margin-right: 5px;}
.addManager ul li input{ width: 200px; float: left;}
.addManager ul li textarea{ width: 200px; float: left;}
.addManager ul li.btn{ line-height: normal; text-align: center;}
.addManager ul li.btn span{ padding: 4px 20px;}
.addManager ul li.btn span.save{ margin-right: 15px;}
.addManager .buttons{ margin-left: 10%; float: left; margin-top:10px;margin-bottom:10px;padding-left:4px;}
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
			<div class="ui-dialog addManager" style="width:100%;">
				<form id="send">
				<input type="hidden" name="id" value="${view.id}">
				<input type="hidden" name="label" value="${label}">
				<input type="hidden" name="logoPid" value="${view.logoPid}">
				<ul>
					<li><strong><b class="red">*</b> 品牌名称：</strong> <input type="text" name="name" value="${view.name}" placeholder="品牌名称"></li>
					<li><strong><b class="red">*</b> 关键词(SEO)：</strong> <input type="text" name="keyword" value="${view.keyword}" placeholder="关键词(SEO)"></li>
					<li><strong><b class="red">*</b> 品牌状态：</strong> <input type="checkbox" [#if view.status=0]checked[/#if] value="0" name="status" value="${view.status}">上架</li>
					<li><strong><b class="red">*</b> 品牌图片：</strong> </li>
					<li>
						<strong><b class="red">*</b> 添加分类：</strong> 
						<select>
						</select>
					</li>
					<li>
						<strong><b class="red">*</b> 品牌介绍：</strong> 
						<textarea name="content">${view.content}</textarea>
					</li>
				</ul>
				<span class="buttons">
					<span class="btn save">提交保存</span>&nbsp;
				</span>
				</form>
			</div>
		</div>
	</div>
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]
<script>
$('body').delegate('span.save','click',function(){
		$("#send").ajaxSubmit({
			 type:"post",
             url: "${base}/design/cate/save.do",
             success:function(res){
				if(res.ret==1) {
					
				} else {
					justTip(res.msg);
				}
             }
		});
	});
</script>
</body>
</html>