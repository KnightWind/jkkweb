<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<title>上传设计方案</title>
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
			
			function validataFrom(){
				
				if($("select[name='staffid']").val() == -1){
					justTip("请选择设计师 !")
					return;
				}
				
				if($("input[name='suType']").val() == ""){
					justTip("请输入房型 !")
					return;
				}
				
				if($("input[name='space']").val() == ""){
					justTip("请输入房屋面积 !")
					return;
				}
				
				if(!/^\d+$/.test($("input[name='space']").val())){
					justTip("房屋面积为数字 !")
					return;
				}
				
				if($("input[name='budget']").val() == ""){
					justTip("请输入方案报价 !")
					return;
				}
				
				if(!/^\d+(\.\d+)?$/.test($("input[name='budget']").val())){
					justTip("方案报价为数字 !")
					return;
				}
				
				if($("input[name='startWork']").val() == ""){
					justTip("请输入开工工期 !")
					return;
				}
				
				if(!/^\d+$/.test($("input[name='startWork']").val())){
					justTip("开工工期为数字 !")
					return;
				}
				
				if($("input[name='whiteFuel']").val() == ""){
					justTip("请输入水电工期 !")
					return;
				}
				
				if(!/^\d+$/.test($("input[name='whiteFuel']").val())){
					justTip("水电工期为数字 !")
					return;
				}
				
				if($("input[name='tileWood']").val() == ""){
					justTip("请输入瓦木工期 !")
					return;
				}
				
				if(!/^\d+$/.test($("input[name='tileWood']").val())){
					justTip("瓦木工期为数字 !")
					return;
				}
				
				if($("input[name='completion']").val() == ""){
					justTip("请输入竣工工期 !")
					return;
				}
				
				if(!/^\d+$/.test($("input[name='completion']").val())){
					justTip("竣工工期为数字 !")
					return;
				}
				
				if($("input[name='duration']").val() == ""){
					justTip("请输入预计工期 !")
					return;
				}
				
				if(!/^\d+$/.test($("input[name='duration']").val())){
					justTip("预计工期为数字 !")
					return;
				}
				
				var flag = false;
				$("#img img").each(function(){
					if($(this).attr('src') != ""){
						flag = true;
						
					}
				});
				if(!flag){
					justTip("至少上传一张效果图/现场照片 !")
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
			
			function addHeTong(){
				var html = '<li>' +
						   '<img id="img'+(imgCount+100000)+'" src="${base}/images/defaultAdd.png" />' +
						   '<p><input onchange="javascript:changeImage(this,'+(imgCount+100000)+',this.value)" type="file" name="hetongImgFiles'+(imgCount+100000)+'" style="width:72px;line-height:40px;" /></p>' +
						   '</li>';
				
				imgCount ++;
				$("#htImg").append(html);
			}
			
			$(function(){
				imgCount = $("#img img").length+1;
				if(imgCount==0){
					imgCount++;
				}
				
				$(".fengmian").click(function(){
					var aid = $(this).attr("aid");
					var did = $(this).attr("did");
					var url = "${base}/goods/design/setCover.xhtml"
					if(aid != null && did != null){
						$.post(url,{"aid":aid,"did":did},function(data){
							justTip("设置成功！");
						});
					}
				});
				
				 $('body').delegate("#delete",'click',function(){
					var id = $(this).attr("rel");
					var $el = $(this);
					if(id != null){
						var url = "${base}/goods/design/deletePhoto.do";
						$.get(url,{"id":id},function(data){
							justTip("删除成功！");
							$el.parents("li").remove();
						});
					}
					return false;
				});
				
				
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
					<span id="sel1" data-role="上传设计方案">上传设计方案</span>
				</h2>
				<div class="conBox">
			<!--内容放这里 start-->
			<form name="form" id="firForm" action="${base}/goods/design/save.xhtml" method="post" enctype="multipart/form-data">
                    <input name="id" value="${de.id}" type="hidden"/>
                    <input name="pid" value="${pid}" type="hidden"/>
                    <input type="hidden" name="huxing" value="${huxing}" />
                    <input type="hidden" name="kongjian" value="${kongjian}" />
                    <input type="hidden" name="fengge" value="${fengge}" />
                    <table id="table_detail"  class="table_09_1 padding-td-10" style="float:left;">
		                <tbody>
		                    <tr>
		                    	<td class="td_left"><span class="requi">*</span>效果图/案例分类：</td>
		                    	<td><lable>【${hname}】/【${kname}】/【${fname}】</lable>&nbsp;&nbsp;&nbsp;<span class="btn" rel="add"><a href="${base}/goods/design/detail.xhtml?id=${de.id}">重选分类</a></span></td>
		                    	<td></td>
		                    </tr>
		                  <!--  <tr>
		                    	<td class="td_left"><span class="requi">*</span>效果图/案例名称：</td>
		                    	<td><input type="text" class="text" name="designName" value="${de.designName}" placeholder="效果图/案例名称，必填" /></td>
		                    </tr>
		                    <tr>
		                    	<td class="td_left"><span class="requi">*</span>公司名称：</td>
		                    	<td><input type="text" class="text" name="company" value="${de.company}" placeholder="公司名称" /></td>
		                    </tr>
		                    <tr>
		                    	<td class="td_left"><span class="requi">*</span>小区名称：</td>
		                    	<td><input type="text" class="text" name="community" value="${de.community}" placeholder="小区名称" /></td>
		                    </tr> -->
		                    <tr>
		                    	<td class="td_left"><span class="requi">*</span>设计师：</td>
		                    	<td>
			                    	<select name="staffid">
			                    		<option value="-1">请选择</option>
			                    		[#list staffs as st]
			                    			[#if de.staffid == st.id]
			                    				<option selected='selected' value="${st.id}">${st.name}</option>
			                    			[#else]
			                    				<option value="${st.id}">${st.name}</option>
			                    			[/#if]
			                    			
			                    		[/#list]
			                    	<select>
		                    	</td>
		                    </tr>
		                    <tr>
		                    	<td class="td_left"><span class="requi">*</span>房型：</td>
		                    	<td><input type="text" class="text" name="suType" value="${de.suType}" placeholder="房型" /></td>
		                    </tr>
		                    <tr>
		                    	<td class="td_left"><span class="requi">*</span>面积：</td>
		                    	<td><input type="text" class="text" name="space" value="${de.space}" placeholder="面积" /> （单位:m²）</td>
		                    </tr>
		                    <tr>
		                    	<td class="td_left"><span class="requi">*</span>报价：</td>
		                    	<td><input type="text" class="text" name="budget" value="${de.budget}" placeholder="报价" /> （单位:元）</td>
		                    </tr>
		                     <tr>
		                    	<td class="td_left"><span class="requi">*</span>装修时间：</td>
		                    	<td>
		                    		[#if de.workTime == 2]
			                    		<input class="radio" type="radio" name="workTime" value="1" />工作日  
			                    		<input class="radio" type="radio" checked="checked" name="workTime" value="2" />自然日
		                    		[#else]
			                    		<input class="radio" type="radio" checked="checked" name="workTime" value="1" />工作日  
			                    		<input class="radio" type="radio" name="workTime" value="2" />自然日
		                    		[/#if]
		                    	</td>
		                    </tr>
		                     <tr>
		                    	<td class="td_left"><span class="requi">*</span>装修方式：</td>
		                    	<td>
		                    		[#if de.method == 2]
			                    		<input class="radio" type="radio"  checked="checked" name="method" value="2" />全包 &nbsp;&nbsp;&nbsp;
			                    		<input class="radio" type="radio" name="method" value="1" />半包
		                    		[#else]
			                    		<input class="radio" type="radio" name="method" value="2" />全包&nbsp;&nbsp;&nbsp;
			                    		<input class="radio" type="radio" checked="checked" name="method" value="1" />半包
		                    		[/#if]
		                    	</td>
		                    </tr>
		                     <tr>
		                    	<td class="td_left"><span class="requi">*</span>开工工期：</td>
		                    	<td><input type="text" class="text" name="startWork" value="${de.startWork}" placeholder="预计工期" /> （单位:天）</td>
		                    </tr>
		                     <tr>
		                    	<td class="td_left"><span class="requi">*</span>水电工期：</td>
		                    	<td><input type="text" class="text" name="whiteFuel" value="${de.whiteFuel}" placeholder="水电工期" /> （单位:天）</td>
		                    </tr>
		                     <tr>
		                    	<td class="td_left"><span class="requi">*</span>泥木工期：</td>
		                    	<td><input type="text" class="text" name="tileWood" value="${de.tileWood}" placeholder="泥木工期" /> （单位:天）</td>
		                    </tr>
		                     <tr>
		                    	<td class="td_left"><span class="requi">*</span>竣工工期：</td>
		                    	<td><input type="text" class="text" name="completion" value="${de.completion}" placeholder="竣工工期" /> （单位:天）</td>
		                    </tr>
		                     <tr>
		                    	<td class="td_left"><span class="requi">*</span>预计工期：</td>
		                    	<td><input type="text" class="text" name="duration" value="${de.duration}" placeholder="预计工期" />  （单位:天）</td>
		                    </tr>
		                    <tr>
		                    	<td class="td_left">描述：</td>
		                    	<td><input type="text" class="text" name="remark" value="${de.remark}" placeholder="描述" /></td>
		                    </tr>
		                    <tr>
		                    	<td class="td_left"><span class="requi">*</span>现场照片：</td>
		                    	<td>
		                    		<ul class="img" id="sceneImg">
					            		
					            		[#list xianChangList as item]
												<li>
												   <img id="img${item_index+99999}" src="${item.downloadPath}" />
												   <p><a id="delete" rel="${item.id}">删除</a></p>
											   </li>
										[/#list]
					            		
					            	</ul>
					            	<span onclick="addSceneImg()" class="btn" rel="add">添加</span>
		                    	</td>
		                    	<td></td>
		                    </tr>
		                    <tr>
		                    	<td class="td_left">效果图：</td>
		                    	<td>
		                    		<ul class="img" id="img">
					            		
					            		[#list xiaoGuoList as item]
												<li>
												   <img id="img${item_index+1}" aid="${item.id}" did="${de.id}" class="fengmian" src="${item.downloadPath}" />
												   <p><a id="delete" rel="${item.id}">删除</a></p>
											   </li>
										[/#list]
					            		
					            	</ul>
					            	<span onclick="addFile()" class="btn" rel="add">添加</span>
		                    	</td>
		                    	<td></td>
		                    </tr>
		                    <tr>
		                    	<td class="td_left">合同：</td>
		                    	<td>
		                    		<ul class="img" id="htImg">
					            		
					            		[#list hetongList as item]
												<li>
												   <img id="img${item_index+1}" aid="${item.id}" did="${de.id}" class="fengmian" src="${item.downloadPath}" />
												   <p><a id="delete" rel="${item.id}">删除</a></p>
											   </li>
										[/#list]
					            		
					            	</ul>
					            	<span onclick="addHeTong()" class="btn" rel="add">添加</span>
		                    	</td>
		                    	<td></td>
		                    </tr>
		                    <tr>
		                    	<td class="td_left">合同备注：</td>
		                    	<td><input type="text" class="text" name="hremark" value="${agr.remark}" placeholder="合同备注" /></td>
		                    </tr>
		                    <tr>
		                    	<td>&nbsp;</td>
		                    	<td>
		                    		<span onclick="return validataFrom();return confirm('确认提交数据?');" class="btn" rel="add">提交保存</span>
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