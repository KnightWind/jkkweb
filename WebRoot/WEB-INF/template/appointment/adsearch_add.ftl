<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>广告位添加</title>
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
				<ul>
					<li><strong><b class="red">*</b> 广告名称：</strong> <input type="text" name="name" placeholder="广告名称"></li>
					<li><strong><b class="red">*</b> 关键词：</strong> <textarea name="aname"></textarea></li>
					<li><strong><b class="red">*</b> 广告图片：<img src=".." /></strong><input type="file" name="pid" /></li>
					<li><div style="clear: both;"></div></li>
					<li>
						<strong><b class="red">*</b> 广告地址：</strong> <input type="text" name="url"  placeholder="广告地址">
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
             url: "${base}/appointment/ad/save.do",
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