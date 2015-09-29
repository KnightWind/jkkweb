<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<title>上传设计师作品</title>
		[#include "/common/res.ftl"]
		<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
		<script type="text/javascript" src="${base}/scripts/form.js"></script>
		<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
		<style>
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
	</head>
	<body>
	<!-- header -->
		<div class="header">
			<div class="logo">
				<h1><a href="#"><img src="${base}/images/logo.png"></a></h1>
				<p>
					<a target="_blank" href="http://shop.jiakeke.com?sp_id=${su.spId}">欢迎您${su.spname}</a>
					<a href="${base}/supplier/logout.xhtml"><i class="new">&#xf08b;</i> 注销</a>
					<a href="/supplier/password" class="logout"><i class="new">&#xf09c;</i> 修改密码</a>
				</p>
			</div>
		</div>
		<!-- wrap -->
		<div class="wrap index bc clr">
			<div class="conWrap fl">
				<h2 class="hd mb10">
					<strong><a href="#">商家后台</a></strong> &gt;
					<span id="sel1" data-role="上传设计师作品">上传设计师作品</span>
				</h2>
				<div class="conBox">
			<!--内容放这里 start-->
			<form name="form" id="firForm" action="${base}/staffopu/save.xhtml" method="post" enctype="multipart/form-data">
                    <input name="id" value="${opus.id}" type="hidden"/>
                    <table id="table_detail"  class="table_09_1 padding-td-10" style="float:left;">
		                <tbody>
		                    <tr>
		                    	<td class="td_left"><span class="requi">*</span>作品名称：</td>
		                    	<td><input type="text" class="text" name="title" value="${opus.title}" placeholder="作品名称" /></td>
		                    </tr>
		                    <tr>
		                    	<td class="td_left"><span class="requi">*</span>设计师：</td>
		                    	<td>
		                    		<select name="staffId">opus
										<option value="-1">--请选择--</option>
									[#list staffList as sl]
										[#if sl.id == opus.staffId]
											<option selected="selected" value="${sl.id}">${sl.name}</option>
										[/#if]
										<option value="${sl.id}">${sl.name}</option>
									[/#list]
									</select>
								</td>
		                    </tr>
		                    <tr>
		                    	<td class="td_left"><span class="requi">*</span>作品图片：</td>
		                    	<td>
		                    		<ul class="img" id="img">
					            		
					            		[#list list as item]
											<li>
											   <img id="img${item_index+1}" src="${item.downloadPath}" />
											   <p><input class="desginImg" onchange="javascript:changeImage(this,${item_index+1},this.value)" id="${item.id}" type="file" name="desImgFiles${item_index}" style="width:72px;line-height:40px;" /></p>
										   </li>
										[/#list]
					            		
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
				<!--内容放这里 end-->
				</div>
			</div>
			[#include "/common/supplierMenu.ftl"]</div>
			<!-- footer -->
			<div class="footer">
				<div class="wrap bc tc">
					<p>北京居天下网络科技有限公司　010-67482366 67477686 &nbsp;&nbsp;地址：北京朝阳区十八里店周家庄村288号融望大酒店6层 居天下版权所有Copyright © 2013-2015 京ICP备15010944</p>
				</div>
			</div>
		</body>
	</html>