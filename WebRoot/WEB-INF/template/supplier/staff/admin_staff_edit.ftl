<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>编辑员工信息</title>
[#include "/common/res.ftl"]
<style>
	.fieldStyle{
		padding:20px;
	}
	.tools ul li{margin-bottom:10px;height:inherit;}
	.preview img{width:100px;height:100px;}
	.uploadPic strong{float:left;line-height: 30px;padding: 0 10px;display: block;width: 90px;text-align: right;height:100px;line-height:100px;}
	.uploadPic input{margin:10px 0 0 100px;}
	div.btn{margin-left:130px;}
</style>
<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
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
				[#if staff.id == null]
				<form id="send" action="${base}/supplierSaff/saveSupplierCompanyStaff.xhtml?returnUrl=supplier_staff_addminList&mid=${mid}&pid=${pid}" enctype="multipart/form-data" method="post">
				<input type="hidden" name="spid" value="${sid}">
				[#else]
				<form id="send" action="${base}/supplierSaff/updateSupplierCompanyStaff.xhtml?returnUrl=supplier_staff_addminList&mid=${mid}&pid=${pid}" enctype="multipart/form-data" method="post">
				[/#if]
					<input type="hidden" name="id" value="${staff.id}">
					<input type="hidden" name="token" value="${token}">
					<div >
						<ul style="padding:20px 20px 0 20px ;">
							<li><strong><b class="red">*</b> 员工姓名：</strong> <input style="width:195px;" type="text" class="text" value="${staff.name}" name="name" placeholder="员工姓名"></li>
							<li><strong><b class="red">*</b> 职位类型：</strong> 
								<select onchange="changeStaffType(this.value)" name="sid" style="width:205px;">
									<option  value="1">设计师</option>
								</select>
							</li>
							<li><strong> 职位描述：</strong> <input style="width:195px;" type="text" class="text" value="${staff.job}" name="job" placeholder="职位描述"></li>
							<li id="linian" style=""><strong> 设计理念：</strong> <textarea rows="2" cols="28" class="text" name="sjsSuggest" placeholder="设计师理念">${staff.sjsSuggest}</textarea></li>
							<li id="linian" style="display:none"><strong> 设计理念：</strong> <textarea rows="2" cols="28" class="text" name="sjsSuggest" placeholder="设计师理念">${staff.sjsSuggest}</textarea></li>
							<li id="jieshao" style=""><strong> 个人介绍：</strong> <textarea rows="2" cols="28" class="text" name="gzSuggest" placeholder="个人介绍" style="width:183px;">${staff.gzSuggest}</textarea></li>
						</ul>
					</div>
					<div style="padding : 0 20px 20px 20px;" class="clearfix uploadPic">
						<strong style="float:left">上传头像：</strong>
						<div id="preview"> 
							[#if photo == ""]
								<img id="imghead" style="border:2px double #CCC" onclick="imgClick()"  width=100 height=100 border=0 src='${base}/images/defaultAdd.png' alt="上传头像" style="height:100px;width:100px;"> 
							[#else]
								<img id="imghead" style="border:2px double #CCC" onclick="imgClick()"  width=100 height=100 border=0 src='${photo}' alt="上传头像"> 
							[/#if]
						</div>  
						<input id="userImgFile" onchange="changeImage(this,this.value)" style="display:none;" class="crt" name="userImg" type="file" />
					</div>
					<div class="btn"><span onclick="return validataAddUserFrom(this)" class="btn save" rel="add">提交保存</span></div>
				</form>	
		</div>
	</div>
	</div>
	[#include "/common/menu.ftl"]
</div> 
<!-- footer -->
[#include "/common/foot.ftl"]
</body>
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>

<script>

	function imgClick(){
		$(".crt").click();
	}

	//预览员工头像
	function changeImage(file) 
	{ 
	
      var ImageFileExtend = ".gif,.png,.jpg,.ico,.bmp";
	  var fileExtend = file.value.substring(file.value.lastIndexOf('.')).toLowerCase();
	  if(ImageFileExtend.indexOf(fileExtend) == -1)
       {
            justTip('请选择gif,png,jpg,bmp图片 !');
            return;
       }
	  var MAXWIDTH  = 100; 
	  var MAXHEIGHT = 100; 
	  var div = document.getElementById('preview'); 
	  if (file.files && file.files[0]) 
	  { 
	    div.innerHTML = '<img id=imghead>'; 
	    var img = document.getElementById('imghead'); 
	    img.onload = function(){ 
	      var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight); 
	      img.width = 100; 
	      img.height = 100; 
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
	
	//选择职位类型
	function changeStaffType(val){
		if(val == -1){
			document.getElementById('linian').style.display='none';
			document.getElementById('jieshao').style.display='none';
		}
		if(val == 1){
			document.getElementById('linian').style.display='';
			document.getElementById('jieshao').style.display='';
		}
		if(val == 2){
			document.getElementById('linian').style.display='none';
			document.getElementById('jieshao').style.display='';
		}
	}
	
	
	//表单验证
	function validataAddUserFrom(el){
		
		if($("input[name='name']").val() == ""){
			justTip("请输入员工姓名!");
			return;
		}
		
		if($("input[name='job']").val() == ""){
			justTip("请输入职位描述!")
			return;
		}
		
		var sid = $("select[name='sid']").val();
		
		if(sid == -1){
			justTip("请选择职位类型!")
			return;
		}else if(sid == 1){
			if($("textarea[name='sjsSuggest']").val() == ""){
				justTip("请输入设计理念!")
				return;
			}
		}else if(sid == 2){
			if($("textarea[name='gzSuggest']").val() == ""){
				justTip("请输入工长介绍!")
				return;
			}
		}
		
		var $el = $(el);
		
		$el.parents('form').submit();
		
	}
	
	
</script>
</html>