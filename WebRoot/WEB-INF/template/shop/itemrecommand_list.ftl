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
				<form id="search" action="${base}/shop/em/cha.xhtml">
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
					</li>		
					<li>
					<input type="hidden" name="mid"  value="${mid}">
					<input type="hidden"  name="pid" value="${pid}">
						<input type="submit" value="查 询">
					</li>
				</ul>
				</form>
			</div>
			  <div class="row-10"></div>
			  
<div class="admin_daohang">
    <span>特卖专区</span>
</div>
 <h1>下期商品预告设置</h1>
 <label><input id="text"  type="text"  name="name"><input  type="button" id="btn"  name=""  value="搜索" />仅搜索特卖商品</label>
 <form name="form" method="post" style="background:#fff;padding-bottom:10px;">
	<div>
					<select name="" size=14 id="leftSelect" style="float:left;width:400px;margin-left:4px;">
					</select>
					<span style="float:left;margin-left:10px;margin-right:10px;text-align:center;">
						<br><br><br><br>
						<span class="btn addOne">>></span><br><br>
						<span class="btn delOne"><<</span><br><br>
						<span class="btn delAll">清空</span>
					</span>	
					<select name="itemid" size=14 id="rightSelect" style="width:400px;margin-left:4px;">
					[#list yugao as y]
					<option value="${y.itemId}">${y.title}</option>
					[/#list]
					</select>
					<input type="hidden" id="itemIds" name="itemIds" />
				</div>
 <div style="height:500px;margin:0 auto;border:1px solid #ccc"  >
 			<div><h1>特卖商品设置</h1></div>
 			[#list index as d]
 			<div style="height:50%;width:30%;float:left;margin:10px 0px;">
		           <div style="height:80%;width:100%;border:1px solid #ccc;position:relative">
		            <img src="http://img2.jiakeke.com/item/origin/76/11/53492ea0c745a9f85c38f1e737a50fe2.jpg"   style="width:100%;height:100%">
		           <div style="position: absolute;right:0px;bottom:0px">	
		            	 <div style="float: left;margin-left: 20px;"></div>
		            	 <input type="hidden" value="${d.itemId}" name="it" />            
		           </div>
		           </div>
		           <dd style="float:left" class="select_special"><input type="button"  value="选择" /></dd>
		           <label style="overflow:hidden;margin-left:80px;line-height:16px;display:block">${d.title}</label>
           </div>
            <input type="hidden" value="${d.id}" name="id" />
           [/#list]
 			
  </div>
   <div style="clear: both;">
   		<input type="hidden" value="${city}" name="city" />
   		
        <dd><input type="button" class="submit" value="发布"></dd>
    </div>
    </form>
			  
	
	</div>
	</div>
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]
<script type="text/html" id="item">
<%for(i=0;i<list.length;i++){%>
	<option value="<%=list[i].id%>"><%=list[i].title%></option>
<%}%>
</script>
<script>
$(function(){
	$("#btn").click(function() {
		var url = "${base}/shop/em/so.do";
		$.post(url,{"name":$("#text").val()},function(data){
            $('#leftSelect').html(template.render('item',data)); 
        },'json');
	});
	$(".addOne").click(function() {
		var option = $("#leftSelect option:selected");
		if (!option.length) {
			justTip("请选选择商品");
			return;
		}
		option.remove();
		$("#rightSelect").append(option);
	});
	$(".delOne").click(function() {
		var option = $("#rightSelect option:selected");
		if (!option.length) {
			justTip("请选选择商品");
			return;
		}
		option.remove();
		$("#leftSelect").append(option);
	});
	$(".delAll").click(function() {
		$("#rightSelect option").each(function() {
			$(this).remove();
		});
	});
		$(".select_special").click(function(){
				justTip("差不多了就等着梁工");
				});
	save('span.btn');
	
	$('body').delegate(':button.submit','click',function() {
		var itemIds = [];
		$("#rightSelect option").each(function() {
			itemIds.push($(this).val());
		});
		$("#itemIds").val(itemIds.join(","));
	
		$("form[name='form']").ajaxSubmit({
			url : "${base}/shop/em/save.do",
			dataType:"json",
            cache:false,
            success:function(res) {
            	if (res.ret == 0) {
            		document.location.reload();
            	} else {
            		justTip(res.msg);
            	}
			}
		});
	});
	
});	
</script>
</body>
</html>