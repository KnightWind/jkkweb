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
		.logo{height:82px; background:#fff; border-bottom:2px solid #1D9DA6;}
		.logo p{padding-top:20px;}
		.logo p a{color:#333; font-size:14px;}
		.logo p a i{ font-size:16px;}
		.conWrap h2.hd{ border-color:#ccc;}
		.dataShow{ width:48%; height:500px; float:left; padding-top:20px;}
		.dataShow p{height:40px;}
		.dataShow table{ margin-bottom:20px;}
		.dataShow table td{ border-right:1px solid #ccc;}
		.dataSp{ margin-right:4%;}
		tr{
			padding-top:10px;
		}
		td{height:55px;font-size:14px;}
		.td_left{text-align:right;font-size:15px;font-weight:bold;}
		#table_detail{mangin:50px;}
		#table_detail .text{
		 	width:350px;
		 }
		.requi{color:red}
		.img{
			float:left;
		}
		.img li{
			float:left;margin-right:20px;
		}
		.img li p{
			text-align:center;
		}
		.img img{
			width:100px;
			height:80px;
		}
		.radio{
			width:50px;
		}
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
				<form  name="form" id="firForm"  action="${base}/shop/add.xhtml?mid=${mid}&pid=${pid}&city=${city}" method="post" enctype="multipart/form-data">
				  <table id="table_detail"  class="table_09_1 padding-td-10" style="float:left;">
		                <tbody>
		                    <tr>
		                  [#if position==1]
						<td class="td_left"><span class="requi">*</span>轮换图位置</td>
						<td>【首页】顶部banner轮换图</td>
						<input type="hidden" name="place" value="index"/>
						[/#if]
						[#if position==2]
							<td class="td_left"><span class="requi">*</span>轮换图位置</td>
						<td>【礼券导航】顶部banner轮换图</td>
						<input type="hidden" name="place" value="quan"/>
						[/#if]
						[#if position==3]
					 <td class="td_left"><span class="requi">*</span>轮换图位置</td>
						<td>【效果图导航】顶部banner轮换图</td>
						<input type="hidden" name="place" value="design"/>
						[/#if]
						[#if position==4]
						<td class="td_left"><span class="requi">*</span>轮换图位置</td>
						<td>【新闻首页】顶部banner轮换图</td>
						<input type="hidden" name="place" value="news"/>
						[/#if]
		                    </tr>
		                    <tr>
		                   <td class="td_left"><span class="requi">*</span>轮换图名:</td>
						    <td><input type="text" name="title" value="${view.title}"/></td>
		                    </tr>
		                    <tr>
		                    	<td class="td_left"><span class="requi">*</span>轮换图:</td>
		                    	<td>
		                    		<ul class="img" id="img">
					            		
					            	
											<li>
											   <img id="img${view.id}" src="${imgUrl}" />
											   <p><input class="desginImg" onchange="javascript:changeImage(this,${view.id},this.value)" id="${view.id}" type="file" name="desImgFiles${view.id}" style="width:72px;line-height:40px;" /></p>
											  
										   </li>
									
					            		
					            	</ul>
					            	<span onclick="addFile()" class="btn" rel="add">添加</span>
		                    	</td>
		                    	<td></td>
		                    </tr>
		                    <tr>
		                    	<td>&nbsp;</td>
		                    	<td>
		                    		<span onclick="return validataFrom(this);" class="btn" rel="add">提交保存</span>
		                    		&nbsp;&nbsp;&nbsp;
		                    		<span onclick="history.go(-1);" class="btn" rel="add">返回</span>
		                    	</td>
		                    </tr>
		                </tbody>
		            </table>
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
			//预览效果图
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
			
			function validataFrom(el){
				
				if($("input[name='title']").val() == ""){
					justTip("请输入作品名称 !")
					return;
				}
				
				var flag = false;
				$("#img img").each(function(){
					if($(this).attr('src') != ""){
						flag = true;
						
					}
				});
				if(!flag){
					justTip("至少上传一张设计图 !")
					return;
				}
				$("#firForm").submit();
			}
			
			function addFile(){
				var html = '<li>' +
						   '<img id="img'+(imgCount+10000)+'" src="${base}/images/defaultAdd.png" />' +
						   '<p><input onchange="javascript:changeImage(this,'+(imgCount+10000)+',this.value)" type="file" name="desImgFiles'+(imgCount+10000)+'" style="width:72px;line-height:40px;" /></p>' +
						   '</li>';
				
				imgCount ++;
				$("#img").append(html);
			}
			
			function addSceneImg(){
				var html = '<li>' +
						   '<img id="img'+(imgCount+1000)+'" src="${base}/images/defaultAdd.png" />' +
						   '<p><input onchange="javascript:changeImage(this,'+(imgCount+1000)+',this.value)" type="file" name="sceneImgFiles'+(imgCount+1000)+'" style="width:72px;line-height:40px;" /></p>' +
						   '</li>';
				
				imgCount ++;
				$("#sceneImg").append(html);
			}
			
			$(function(){
				imgCount = $("#img img").length+1;
				if(imgCount==0){
					imgCount++;
				}
			})
			
		</script>
</body>
</html>