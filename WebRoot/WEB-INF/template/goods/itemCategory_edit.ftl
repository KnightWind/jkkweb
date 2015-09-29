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
				<input type="hidden" name="parentId" value="${view.parentId}">
				<input type="hidden" name="adminId" value="${view.adminId}">
				<input type="hidden" name="status" value="${view.status}">
				<input type="hidden" name="seq" value="${view.seq}">
				<ul>
					<li><strong>分类：</strong> <span style="float:left;">${parentName}</span></li>
					<li><strong><b class="red">*</b> 分类名：</strong> <input type="text" name="name" value="${view.name}" placeholder="分类名"></li>
					<li><strong><b class="red">*</b> 备注：</strong> <textarea name="comments">${view.comments}</textarea></li>
				</ul>
				<span class="buttons">
					<span class="btn save">添加</span>&nbsp;
					<a href="${base}/goods/itemCategory/index.xhtml?parentId=${parentId}&mid=${mid}&pid=${pid}"><span class="btn" >返回</span></a>
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
             url: "${base}/goods/itemCategory/save.do",
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