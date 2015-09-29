<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>权限分配管理</title>
[#include "/common/res.ftl"]
<link rel="stylesheet" type="text/css" href="${base}/styles/role_set.css">
<style>
.checkAll{
margin-left:30px;
}
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
			<form id="search" action="">
				<div class="tools white border mb10 sp10 clr">
					<ul>
						<li>
							<span class="sp">
								<strong><b class="red">*</b> 角色名称：</strong>
								<input type="hidden" name="id" id="id" value="${view.id}" />
								<input type="text" class="text" name="name" id="name" value="${view.name}" />
							</span>
						</li>
					</ul>
				</div>
				<div class="access white border sp10 clr">
					<h2><b class="red">*</b> 权限分配</h2>
					<ul>
						[#list datalist as item]
						<li>
							<strong>
							${item.name}
							<input type="checkbox" name="checkbox" checked class="checkAll"/>全选
							</strong>
							<p>
								[#list item.children as child]
								<span>
									<input type="checkbox" class="child-item" id="${child.id}" [#if child.selected = 1]checked[/#if]>
									<label for="${child.id}">${child.name}</label>
								</span>
									[#list child.children as obj]
									[#if obj.name?? && obj.name != '']
									<span>
										<input type="checkbox" class="child-item" id="${obj.id}" [#if obj.selected = 1]checked[/#if]>
										<label for="${obj.id}">${obj.name}</label>
									</span>
									[/#if]
									[/#list]
								[/#list]
							</p>
						</li>
						[/#list]
						<p class="btn"><span class="btn saveBtn">提交保存</span></p>
					</ul>
				</div>
			</form>
		</div>
	</div>
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]
</body>
<script>
$(".saveBtn").click(function() {
	var name = $("#name").val();
	if (!name) {justTip("请输入角色名称！");return;}
	var id = $("#id").val();
	var keys = [];
	$(":checkbox:checked.child-item").each(function() {
		keys.push($(this).attr("id"));
	});
	$.ajax({
		url : "${base}/admin/role/saveRole.do",
		type : "post",
		data : {
			id : id,
			name : name,
			keys : keys.join(",")
		},
		success : function(res) {
			justTip(res.msg);
		}
	});
});
</script>

<script>
    $(function(){
        $(".checkAll").change(function(){
        	var flag=$(this).prop('checked'),obj=$(this).parent().next().find('input:checkbox');
        	if(flag){obj.prop('checked',true);}else{obj.prop('checked',false);}
        });
    });
</script>
</html>