<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>轮播图管理</title>
[#include "/common/res.ftl"]
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="${base}/scripts/form.js"></script>
</head>
<style type="text/css">
.tools ul li strong{ float: left; line-height: 30px; padding:0 10px; display: block; width: 150px;  text-align: right;}
</style>
<body>
<!-- header -->
[#include "/common/head.ftl"]
<!-- wrap -->
<div class="wrap index bc clr">
	<div class="conWrap fl">
		[#include "/common/nav.ftl"]
		<div class="conBox">
            <div class="tools white border mb10 sp10 clr">
				<form id="search" action="${base}/shop/save.xhtml?mid=${mid}&pid=${pid}&city=${city}" method="post">
				<ul>
				<li>
			
						[#if position==1]
						<strong>*轮换图位置</strong>
						<strong>【首页】顶部banner轮换图</strong>
						<input type="hidden" name="place" value="index"/>
						[/#if]
						[#if position==2]
						<strong>【礼券导航】顶部banner轮换图</strong>
						<input type="hidden" name="place" value="quan"/>
						[/#if]
						[#if position==3]
						<strong>【效果图导航】顶部banner轮换图</strong>
						<input type="hidden" name="place" value="design"/>
						[/#if]
						[#if position==4]
						<strong>【新闻首页】顶部banner轮换图</strong>
						<input type="hidden" name="place" value="news"/>
						[/#if]
					</li>	
					<li>
						<strong>*轮换图名</strong>
						<input type="text" name="title" value="${view.title}"/>
					</li>		
					<li style="width: 100%;height: 110px;">
					<ul class="img" id="img" >
					<li>
						<strong>*轮换图</strong>
						<img id="img${view.seq}" style="width: 100px;height: 80px;"   src="${imgUrl}" />
						<span onclick="addHeTong()" class="btn" rel="add">添加</span>
						<p style="margin-left: 210px;"><a style="text-decoration:none;color: #333;"  id="delete" rel="${item.id}">删除</a></p>
						
				  </li> 
						</ul>
					</li>
					<li>
						<strong>*链接地址</strong>
						<input type="text"  size="30" name="url" value="${view.url}"/>
					</li>
					<li>
						<strong>*顺序</strong>
						<input type="text"  name="seq" value="${view.seq}"/>
					</li>
					<li>
						<strong>*是否启用</strong>
						<input type="radio" name="status" [#if view.status=0]checked[/#if] value="0">启用
						<input type="radio" name="status" [#if view.status=-1]checked[/#if] value="-1">禁用
					</li>
					<li>
					<input type="hidden" name="id" value="${view.id}"/>
					<input type="hidden" name="cityDomain" value="${city}"/>
						<input style="display: inline-block;padding: 4px 12px;background: #1D9DA6;border-radius: 3px;color: #fff;cursor: pointer;" type="submit" value="保存">&nbsp;
						<span class="btn search" onclick="history.back()">返回</span>
					</li>
				</ul>
				</form>
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

<script>
			
			function changeImage(file,id,val) 
			{ 
			  var ImageFileExtend = ".gif,.png,.jpg";
			  var fileExtend = file.value.substring(file.value.lastIndexOf('.')).toLowerCase();
			  if(ImageFileExtend.indexOf(fileExtend) == -1)
		       {
		            justTip('请选择gif,png,jpg图片 !');
		            return;
		       }
			  
			
			  if(val == ""){
			  	var img = document.getElementById('img'+id);
			  	img.src="";
			  }
			  if (file.files && file.files[0]) 
			  { 
			    var img = document.getElementById('img'+id); 
			    var reader = new FileReader(); 
			    reader.onload = function(evt){img.src = evt.target.result;} 
			    reader.readAsDataURL(file.files[0]); 
				  var id = file.id;
				  var form = document.getElementById("firForm")
				  var childNode=document.createElement("input");
				  childNode.name='imgId';
				  childNode.value=id;
				  childNode.type="hidden";
				  form.appendChild(childNode);
			  } 
			  else 
			  { 
			    var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="'; 
			    file.select(); 
			    var src = document.selection.createRange().text; 
			    var img = document.getElementById('img'+id); 
			    img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src; 
			    var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight); 
			    status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height); 
			  }
			  
			} 		
			function addHeTong(){
				var html = '<li style="margin-top:-50px;margin-left: 300px;">' +
						   '<img  src="${base}/images/defaultAdd.png" />' +
						   '<p><input onchange="javascript:changeImage(this,'+(imgCount+100000)+',this.value)" type="file" name="hetongImgFiles'+(imgCount+100000)+'" style="width:72px;line-height:40px;" /></p>' +
						   '</li>';				
				$("#img").append(html);
			}
			
			$(function(){
				 $('body').delegate("#delete",'click',function(){
					var id = $(this).attr("rel");
					var $el = $(this);
					if(id != null){
						var url = "${base}/goods/design/deletePhoto.do";
						$.get(url,{"id":id},function(data){
							justTip("删除成功！");
							$el.parent("li").remove();
						});
					}
					return false;
				});
				
				
			})
			
		</script>
</body>
</html>