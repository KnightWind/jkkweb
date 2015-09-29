<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<title>编辑商品</title>
		[#include "/common/res.ftl"]	
		<link rel="stylesheet" href="${base}/scripts/kindeditor/themes/default/default.css" />
		<script charset="utf-8" type="text/javascript" language="javascript" src="${base}/scripts/kindeditor/kindeditor-all-min.js"></script>
		<script charset="utf-8" type="text/javascript" language="javascript" src="${base}/scripts/kindeditor/lang/zh_CN.js"></script>
		<script charset="utf-8" type="text/javascript" language="javascript" src="${base}/scripts/imageHandle.js"></script>
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
		.fieldStyle{
			padding:20px;
		}
		.table{margin:20px;}
		.req{color:red;}
		.table tr{line-height:40px;}
		.btn{line-height:20px;}
		.table .tr{line-height:40px;text-align:left;}
		.text{width:430px;}
		.img li{float:left;margin-left:10px;}
		.img li img{width:150px;}
		.img li p .file{display:none}
		.img li p .choose{height:15px;}
		
		</style>
	</head>
	<body> 
	<!-- header -->
		[#include "/materials/common/head.ftl"]
		<!-- wrap -->
		<div class="wrap index bc clr">
			<div class="conWrap fl">
				<h2 class="hd mb10">
					<strong><a href="#">建材后台</a></strong> &gt;
					<span id="sel1" data-role="商品管理">编辑商品</span>
				</h2>
				 <div class="conBox">
					<!--内容放这里 start-->
					<form action="${base}/materials/item/save.xhtml" method="post" enctype="multipart/form-data" id="firForm" >
						<input type="hidden" name="id"  value="${item.id}">
						<table class="table">
							<tr>
								<td class="tl">商品图片：</td>
								<td class="tr">
									<ul class="img">
										[#if attachments == null]
											<li>
												<p>
													<img src="${base}/images/materials/defaultAdd.png" id="img1" onclick="selectFile(this)" />
													<input class="file" name="file1" type="file" onchange="changeImage(this,150,150,1,this.value)" />
												</p>
											</li>
											<li>
												<p>
													<img src="${base}/images/materials/defaultAdd.png" id="img2" onclick="selectFile(this)"/>
													<input class="file" name="file2" type="file" onchange="changeImage(this,150,150,2,this.value)" />
												</p>
											</li>
											<li>
												<p>
													<img src="${base}/images/materials/defaultAdd.png" id="img3" onclick="selectFile(this)"/>
													<input class="file" name="file3" type="file" onchange="changeImage(this,150,150,3,this.value)"/>
												</p>
											</li>
											<li>
												<p>
													<img src="${base}/images/materials/defaultAdd.png" id="img4" onclick="selectFile(this)"/>
													<input class="file" name="file4" type="file" onchange="changeImage(this,150,150,4,this.value)"/>
												</p>
											</li>
											<li>
												<p>
													<img src="${base}/images/materials/defaultAdd.png" id="img5" onclick="selectFile(this)"/>
													<input class="file" name="file5"type="file" onchange="changeImage(this,150,150,5,this.value)"/>
												</p>
											</li>
										[#else]
											[#list attachments as a]
												<li>
													<p>
														<img class="limg" src="${a.downloadPath}" id="img${a_index + 1}" onclick="selectFile(this)" />
														<input class="file" id="${a.id}" name="file${a_index + 1}" type="file" onchange="changeImage(this,150,150,${a_index + 1},this.value)" />
													</p>
												</li>
											[/#list]
											[#list inputs as i]
												<li>
													<p>
														${i}
													</p>
												</li>
											[/#list]
										[/#if]
									</ul>
								</td>
							</tr>
							<tr>
								<td class="tl"><span class="req">*</span>商品名称：</td>
								<td class="tr">
									<input type="text" class="text" name="title" value="${item.title}"  />
								</td>
							</tr>
							
							
							<tr>
								<td class="tl"><span class="req">*</span>商品分类：</td>
								<td class="tr">
									<select name="cate">
										<option value="-1">--请选择--</option>
										[#list cates as c]
											[#if c.id == item.cate]
			                                	<option selected="selected" value="${c.id}">${c.name}</option>
			                                [#else]
			                                	<option  value="${c.id}">${c.name}</option>
			                                [/#if]
			                             [/#list]
									</select>
								</td>
							</tr>
							<tr>
								<td class="tl"><span class="req">*</span>商品售价：</td>
								<td class="tr">
									<input type="text" class="text" name="price" value="${item.price}"  />
								</td>
							</tr>
							<tr>
								<td class="tl">商品库存：</td>
								<td class="tr">
									<input type="text" class="text" name="stock" value="${item.stock}"  /><span>（默认为1000）</span>
								</td>
								
							</tr>
							
							<tr>
								<td class="tl" style="vertical-align:top"><span class="req">*</span>商品描述：</td>
								<td class="tr">
									<textarea name="detail">${item.detail}</textarea>
								</td>
							</tr>
							<tr>
								<td class="tl">&nbsp;</td>
								<td class="tr">
									<span class="btn" onclick="verifyData(this)">提交保存</span>
									<span class="btn" onclick="history.go(-1);">返回</span>
								</td> 
							</tr>
						</table>
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
    
    <script>
		KindEditor.ready(function(K) {
			K.create('textarea[name="detail"]', {
				uploadJson :'${base}/materials/item/upload_image.xhtml',
	            allowFileManager : true,
	            allowImageUpload : true, 
				autoHeightMode : true,
				afterCreate : function() {this.loadPlugin('autoheight');},
				afterBlur : function(){ this.sync(); }  //Kindeditor下获取文本框信息
			});
		});
		</script>
    
    <script>
    	
    	function selectFile(el){
    		var $file = $(el).next();
    		$file.click();
    	}	
    	
		function verifyData(el){
			if($("input[name='title']").val() == ""){
				justTip("请输入商品标题 ！")
				return;
			}
			
			if($("select[name='cate']").val() == "-1"){
				justTip("请选择商品分类 ！")
				return;
			}
			if($("input[name='price']").val() == ""){
				justTip("请输入商品价格 ！")
				return;
			}
			
			if(!/^\d+(\.\d+)?$/.test($("input[name='price']").val())){
				justTip("商品价格为数字 !")
				return;
			}
			var stockVal= $("input[name='stock']").val();
			if(stockVal!=null && stockVal!="" && stockVal!=undefined){
				if(!/^\d+$/.test(stockVal)){
					justTip("商品库存为数字 !")
					return;
				}
			}
			
			if($("textarea[name='detail']").val() == ""){
				justTip("请输入商品详情 ！")
				return;
			}
			
			$(el).parents("form").submit();
			
		}
		
      	//预览效果图
		function changeImage(file,width,height,id,val) 
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
		  	img.src="${base}/images/materials/defaultAdd.png";
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

    </script>
</html>