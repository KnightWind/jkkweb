<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<title>认证信息</title>
		[#include "/common/res.ftl"]	
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
		.fieldStyle{padding:20px;}
		.conBox ul{padding-top:20px;}
		.conBox ul li img{margin-bottom:10px;}
		strong.inputs{width:100px;margin-left:30px;}
		input.sbt{margin:30px 0 0 30px;padding:5px 10px;}
		p.tips{margin:5px 0 5px 10px;}
		input.text{width:200px;}
		</style>
		<script type="text/javascript" src="${base}/scripts/ajaxfileupload.js"></script>
		<script>
		   function changeImage1(file) 
			{ 
		      var ImageFileExtend = ".png,.jpg,.jpeg";
			  var fileExtend = file.value.substring(file.value.lastIndexOf('.')).toLowerCase();
			  if(ImageFileExtend.indexOf(fileExtend) == -1)
		       {
		            justTip('请选择png,jpg,jpeg图片 !');
		            return;
		       }
			
			
			  var MAXWIDTH  = 350; 
			  var MAXHEIGHT = 230; 
			  var div = document.getElementById('preview1'); 
			  if (file.files && file.files[0]) 
			  { 
			    div.innerHTML = '<img id=imghead1>'; 
			    var img = document.getElementById('imghead1'); 
			    img.onload = function(){ 
			      var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight); 
			      img.width = rect.width; 
			      img.height = rect.height; 
			    } 
			    var reader = new FileReader(); 
			    reader.onload = function(evt){img.src = evt.target.result;} 
			    reader.readAsDataURL(file.files[0]); 
			  } 
			  else 
			  { 
			    var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="'; 
			    file.select(); 
			    var src = document.selection.createRange().text; 
			    div.innerHTML = '<img id="imghead" style="padding:10px;" />'; 
			    var img = document.getElementById('imghead'); 
			    img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src; 
			    var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight); 
			    status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height); 
			    div.innerHTML = "<div id='divhead' style='width:"+rect.width+"px;height:"+rect.height+"px;"+sFilter+src+"\"'></div>"; 
			  } 
			} 
			
			function clacImgZoomParam( maxWidth, maxHeight, width, height ){ 
			    var param = {top:0, left:0, width:width, height:height}; 
			    if( width>maxWidth || height>maxHeight ) 
			    { 
			        rateWidth = width / maxWidth; 
			        rateHeight = height / maxHeight; 
			         
			        if( rateWidth > rateHeight ) 
			        { 
			            param.width =  maxWidth; 
			            param.height = Math.round(height / rateWidth); 
			        }else 
			        { 
			            param.width = Math.round(width / rateHeight); 
			            param.height = maxHeight; 
			        } 
			    } 
			     
			    param.left = Math.round((maxWidth - param.width) / 2); 
			    param.top = Math.round((maxHeight - param.height) / 2); 
			    return param; 
			}  
		</script>
		<script>
		   function changeImage2(file) 
			{ 
			
		      var ImageFileExtend = ".png,.jpg,.jpeg";
			  var fileExtend = file.value.substring(file.value.lastIndexOf('.')).toLowerCase();
			  if(ImageFileExtend.indexOf(fileExtend) == -1)
		       {
		            justTip('请选择png,jpg,jpeg图片 !');
		            return;
		       }
			
			
			  var MAXWIDTH  =400; 
			  var MAXHEIGHT = 350; 
			  var div = document.getElementById('preview2'); 
			  if (file.files && file.files[0]) 
			  { 
			    div.innerHTML = '<img id=imghead2>'; 
			    var img = document.getElementById('imghead2'); 
			    img.onload = function(){ 
			      var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight); 
			      img.width = rect.width; 
			      img.height = rect.height; 
			    } 
			    var reader = new FileReader(); 
			    reader.onload = function(evt){img.src = evt.target.result;} 
			    reader.readAsDataURL(file.files[0]); 
			  } 
			  else 
			  { 
			    var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="'; 
			    file.select(); 
			    var src = document.selection.createRange().text; 
			    div.innerHTML = '<img id="imghead2" style="padding:10px;" />'; 
			    var img = document.getElementById('imghead2'); 
			    img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src; 
			    var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight); 
			    status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height); 
			    div.innerHTML = "<div id='divhead' style='width:"+rect.width+"px;height:"+rect.height+"px;"+sFilter+src+"\"'></div>"; 
			  } 
			} 
			
			function clacImgZoomParam( maxWidth, maxHeight, width, height ){ 
			    var param = {top:0, left:0, width:width, height:height}; 
			    if( width>maxWidth || height>maxHeight ) 
			    { 
			        rateWidth = width / maxWidth; 
			        rateHeight = height / maxHeight; 
			         
			        if( rateWidth > rateHeight ) 
			        { 
			            param.width =  maxWidth; 
			            param.height = Math.round(height / rateWidth); 
			        }else 
			        { 
			            param.width = Math.round(width / rateHeight); 
			            param.height = maxHeight; 
			        } 
			    } 
			     
			    param.left = Math.round((maxWidth - param.width) / 2); 
			    param.top = Math.round((maxHeight - param.height) / 2); 
			    return param; 
			}  
		</script>
		<script>
		   function changeImage3(file) 
			{ 
			
		      var ImageFileExtend = ".png,.jpg,.jpeg";
			  var fileExtend = file.value.substring(file.value.lastIndexOf('.')).toLowerCase();
			  if(ImageFileExtend.indexOf(fileExtend) == -1)
		       {
		            justTip('请选择png,jpg,jpeg图片 !');
		            return;
		       }
			
			
			  var MAXWIDTH  =350; 
			  var MAXHEIGHT = 230; 
			  var div = document.getElementById('preview3'); 
			  if (file.files && file.files[0]) 
			  { 
			    div.innerHTML = '<img id=imghead3>'; 
			    var img = document.getElementById('imghead3'); 
			    img.onload = function(){ 
			      var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight); 
			      img.width = rect.width; 
			      img.height = rect.height; 
			    } 
			    var reader = new FileReader(); 
			    reader.onload = function(evt){img.src = evt.target.result;} 
			    reader.readAsDataURL(file.files[0]); 
			  } 
			  else 
			  { 
			    var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="'; 
			    file.select(); 
			    var src = document.selection.createRange().text; 
			    div.innerHTML = '<img id="imghead2" style="padding:10px;" />'; 
			    var img = document.getElementById('imghead3'); 
			    img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src; 
			    var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight); 
			    status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height); 
			    div.innerHTML = "<div id='divhead' style='width:"+rect.width+"px;height:"+rect.height+"px;"+sFilter+src+"\"'></div>"; 
			  } 
			} 
			
			function clacImgZoomParam( maxWidth, maxHeight, width, height ){ 
			    var param = {top:0, left:0, width:width, height:height}; 
			    if( width>maxWidth || height>maxHeight ) 
			    { 
			        rateWidth = width / maxWidth; 
			        rateHeight = height / maxHeight; 
			         
			        if( rateWidth > rateHeight ) 
			        { 
			            param.width =  maxWidth; 
			            param.height = Math.round(height / rateWidth); 
			        }else 
			        { 
			            param.width = Math.round(width / rateHeight); 
			            param.height = maxHeight; 
			        } 
			    } 
			     
			    param.left = Math.round((maxWidth - param.width) / 2); 
			    param.top = Math.round((maxHeight - param.height) / 2); 
			    return param; 
			}  
		</script>
		<script>
		  $(function(){
		     $("#send").submit(function(){
		         var idCard=$(".idCard").val();
		         if(idCard== null || idCard == undefined || idCard == ''){
		            justTip("请输入身份证号码");
		            return false;
		         }
		         var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
		         if(reg.test(idCard)==false){
		            justTip("请输入正确的身份证号");
		            return false;
		         }
		         var businessCode=$(".businessCode").val();
		         if(businessCode== null || businessCode == undefined || businessCode == ''){
		             justTip("请输入营业执照编码");
		            return false;
		         }
		     });
		  });
		</script>
	</head>
	<body>
	<!-- header -->
		[#include "/materials/common/head.ftl"]
		<!-- wrap -->
		<div class="wrap index bc clr">
			<div class="conWrap fl">
				 <div class="conBox">
					<!--内容放这里 start-->
					<form id="send" action="saveOneInfo.do" enctype="multipart/form-data" method="post">
					<ul>
						  <li>
						  	<strong class="inputs"><b class="red">*</b>身份证号码：</strong><input type="text" class="text idCard"  name="idCard" >
						  		<div class="clearfix">
								 	<div style="padding :20px;float:left;">  
										<p class="tips"><b class="red">*</b>身份证正面</p> 
									<div id="preview1">
										<input type="hidden" name="avatar"/>
									    <img id="imghead11"  style="padding-left:10px;"  width=350 height=230 border=0> 
									</div>  
									
									<div style="float:left;">
										<input id="userImgFile" onchange="changeImage1(this)" style="width:165px;padding-left:80px;"  name="frontImg" type="file" id="male" />
									</div>
					        	</div>
					        	<div style="padding :20px;float:left;">
									<div id="preview3">
										<p class="tips"><b class="red">*</b>身份证反面</p>  
										<input type="hidden" name="avatar"/>
									    <img id="imghead3" style="padding-left:10px;"  width=350 height=230 border=0> 
									</div>  
									
									<div style="float:left;">
										<input id="userImgFile" onchange="changeImage3(this)" style="width:165px;padding-left:80px;"  name="negativeImg" type="file" id="male" />
									</div>
					        	</div>
					        </div>
				         </li>
			        </ul>
					<ul style="clear:both;">
						  <li> 
						   <strong class="inputs"><b class="red">*</b>营业执照编码：</strong><input type="text" class="text businessCode"  name="businessCode" id="">
							 <div style="padding :20px;">
								<div id="preview2"> 
									<input type="hidden" name="avatar"/>
								    <img id="imghead2" style="padding-left:10px;" width=400 height=350 border=0> 
								</div>  
								
								<div style="float:left;">	
									<input id="userImgFile" onchange="changeImage2(this)" style="width:365px;padding-left:25px;"   name="licenceImg" type="file" id="male" />
								</div>
					        </div>
				         </li>
				     </ul>
				       <li class="btn"><input type="submit" value="提交" class="sbt" /></li>
				 </form>
			   		<!--内容放这里 end-->
				 </div>
	       </div>
			[#include "/materials/common/nav.ftl"]
       </div>
	   <!-- footer -->
		<div class="footer">
			<div class="wrap bc tc">
				<p>北京居天下网络科技有限公司　010-67482366 67477686 &nbsp;&nbsp;地址：北京朝阳区十八里店周家庄村288号融望大酒店6层 居天下版权所有Copyright © 2013-2015 京ICP备15010944</p>
			</div>
		</div>
    </body>		
</html>