<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<title>公司信息</title>
		[#include "/common/res.ftl"]
		<script type="text/javascript" src="${base}/scripts/ajaxfileupload.js"></script>
		<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
		<script type="text/javascript" src="${base}/scripts/form.js"></script>
		<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
		<style>
		.logo{height:82px; background:#fff; border-bottom:2px solid #1D9DA6;}
		.logo p{padding-top:20px;}
		.logo p a{color:#333; font-size:14px;}
		.logo p a i{ font-size:16px;}
		.conWrap h2.hd{ border-color:#ccc;}
		.dataShow{ widtd:48%; height:500px; float:left; padding-top:20px;}
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
		.requi{color:red};
		
		</style>
		<script type="text/javascript">
			function ajaxFileUpload(id,file){
			
				var ImageFileExtend = ".gif,.png,.jpg";
				  var fileExtend = file.value.substring(file.value.lastIndexOf('.')).toLowerCase();
				  if(ImageFileExtend.indexOf(fileExtend) == -1)
			       {
			            justTip('请选择gif,png,jpg图片 !');
			            return;
			       }
			
			    //开始上传文件时显示一个图片,文件上传完成将图片隐藏
			    //$("#loading").ajaxStart(function(){$(this).show();}).ajaxComplete(function(){$(this).hide();});
			    //执行上传文件操作的函数
			    var sendUrl = "";
			   	if(id != "logoImg"){
			   		sendUrl = "${base}/supplier/uploadCertificate.xhtml?id=${supplierCompany.spId}&returnUrl=supplier";
			   	}else{
			   		sendUrl = "${base}/supplier/uploadLogo.xhtml?id=${supplierCompany.id}";
			   	}
			    $.ajaxFileUpload({
			        //处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
			        url:sendUrl,
			        secureuri:false,                       //是否启用安全提交,默认为false
			        fileElementId:id,           //文件选择框的id属性
			        dataType:'text',                       //服务器返回的格式,可以是json或xml等
			        success:function(data, status){        //服务器响应成功时的处理函数
			            justTip('添加成功!')
			            window.location.reload();
			        },
			        error:function(data, status, e){ //服务器响应失败时的处理函数
			        	alert('上传失败，请检查网络后重试！！');
			        }
			    });
			}
			
			//预览员工头像
			function changeImage(file) 
			{ 
			
		      var ImageFileExtend = ".gif,.png,.jpg";
			  var fileExtend = file.value.substring(file.value.lastIndexOf('.')).toLowerCase();
			  if(ImageFileExtend.indexOf(fileExtend) == -1)
		       {
		            justTip('请选择gif,png,jpg图片 !');
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
			
			//选择证书类型
			function changeType(val){
				if(val == 8){
					
				}
			}
			
			
			//表单验证
			function validataAddUserFrom(el){
				
				if($("input[name='name']").val() == ""){
					justTip("请输入员工姓名 ！");
					return;
				}
				
				if($("input[name='job']").val() == ""){
					justTip("请输入职位描述 ！")
					return;
				}
				
				var sid = $("select[name='sid']").val();
				
				if(sid == -1){
					justTip("请选择职位类型 ！")
					return;
				}else if(sid == 1){
					if($("textarea[name='sjsSuggest']").val() == ""){
						justTip("请输入设计理念 ！")
						return;
					}
				}else if(sid == 2){
					if($("textarea[name='gzSuggest']").val() == ""){
						justTip("请输入工长介绍 ！")
						return;
					}
				}
				
				var $el = $(el);
				
				$el.parents('form').submit();
				
			}
			
			function validataCompyFrom(){
				if($("textarea[name='intro']").val() == ""){
					justTip("请输入公司简介 ！")
					return;
				}
				if($("input[name='address']").val() == ""){
					justTip("请输公司地址 ！");
					return;
				}
				if($("input[name='scale']").val() == ""){
					justTip("请输入公司规模人数 ！");
					return;
				}
				
				if(!/^\d+$/.test($("input[name='scale']").val())){
					justTip("规模人数为数字 !")
					return;
				}
				
				if($("input[name='capital']").val() == ""){
					justTip("请输入公司注册资金 ！")
					return;
				}
				
				if(!/^\d+$/.test($("input[name='capital']").val())){
					justTip("注册资金为数字 !")
					return;
				}
				
				if($("input[name='establish']").val() == ""){
					justTip("请输入公司成立 ！");
					return;
				}
				
				if($("input[name='inspection']").val() == ""){
					justTip("请输入年检时间!")
					return;
				}
				if($("input[name='durationStart']").val() == ""){
					justTip("请输入公司开始营业时间 ！");
					return;
				}
				
				if($("input[name='durationEnd']").val() == ""){
					justTip("请输入公司结束营业时间 ！")
					return;
				}
				
				$("#compForm").submit();
			}
			
			
			function imgClick(){
				$(".crt").click();
			}
			
			$(function(){
			
				$("body").delegate('#changeCity','change',function(){
					var val = $(this).val();
					var url = "${base}/region/getList.do";
					$('input[name="xzCity"]').attr("value",val);
					if(val <= 0)
						return;
					$.get(url,{"parentId":val},function(data){
						if(data.list.length <= 0){
							return;
						}
						$("#area").find(".subEar").remove();
						var html = '<select id="changeCity" class="subEar"><option value="-1">--请选择--</option>';
						for(var i = 0;i < data.list.length;i++){
							html += '<option value="'+data.list[i].regionid+'">'+data.list[i].regionname+'</option>';
						}
						html += '</select>';
						$("#area").append(html);
					});
				});
			
				
				$('#table_detail').delegate(".xiugai",'click',function(){
			        var $this=$(this),data=$.parseJSON($this.attr('rel'));
			        addMask();
			        $('body').append($('#pannel').html());
			        $('.ui-dialog').data('act','modify');
			        $('.ui-dialog input,.ui-dialog select,.ui-dialog textarea').each(function(index,ele){
			            $(ele).val(data[index]);
			        });
			        center($('.ui-dialog'));
			    });
			    
			    $('#table_detail').delegate(".sjsSuggest",'click',function(){
			        var $this=$(this),data=$.parseJSON($this.attr('rel'));
			        addMask();
			        $('body').append($('#pannelSjsSuggest').html());
			        $('.ui-dialog').data('act','modify');
			        $('.ui-dialog input,.ui-dialog select,.ui-dialog textarea').each(function(index,ele){
			            $(ele).val(data[index]);
			        });
			        $(".ui-dialog img").attr("src","${base}"+data[data.length-1]);
			        center($('.ui-dialog'));
			    });
			    
			    $('#table_detail').delegate(".gzSuggest",'click',function(){
			        var $this=$(this),data=$.parseJSON($this.attr('rel'));
			        addMask();
			        $('body').append($('#pannelGzSuggest').html());
			        $('.ui-dialog').data('act','modify');
			        $('.ui-dialog input,.ui-dialog select,.ui-dialog textarea').each(function(index,ele){
			            $(ele).val(data[index]);
			        });
			        $(".ui-dialog img").attr("src","${base}"+data[data.length-1]);
			        center($('.ui-dialog'));
			    });
			    
			    $('.uploadCerf').click(function(){
			        addMask();
			        $('body').append($('#uploadCerf').html());
			        center($('.ui-dialog'));
			    });
			
			});
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
					<a href="${base}/supplier/password" class="logout"><i class="new">&#xf09c;</i> 修改密码</a>
				</p>
			</div>
		</div>
		<!-- wrap -->
		<div class="wrap index bc clr">
			<div class="conWrap fl">
				<h2 class="hd mb10">
					<strong><a href="#">商家后台</a></strong> &gt;
					<span id="sel1" data-role="首页">公司首页设置</span>
				</h2>
				<div class="conBox white">
			<!--内容放这里 start-->
			
		<form id="compForm" action="${base}/supplier/update.xhtml"  method="post" >
			<input type="hidden" name="id" value="${supplierCompany.id}" />
			<input type="hidden" name="condId" value="${supplierCondition.id}">
            <table id="table_detail"  class="table_09_1 padding-td-10">
                <tbody>
                    <tr>
                    	<td class="td_left"><span class="requi">*</span>公司名称：</td>
                    	<td><lable>${su.spname}</lable></td>
                    </tr>
                    <tr>
                    	<td class="td_left"><span class="requi">*</span>公司LOGO：</td>
                    	<td>
                    		<div style="float:left" id="certificateList">
                    			[#if logo!=null]<img src="${logo}" width="100" height="100" />[/#if]
                    		</div>
                    		<div style="float:left;margin-left:20px;line-height:55px;" >
                    			<input style="width: 73px;" type="file" id="logoImg" name="img" onchange="ajaxFileUpload('logoImg',this)"/>
                    		</div>
                    		<div style="float:left;margin-left:20px;width:350px">
                    			店铺LOGO图片最多允许上传 1 张，尺寸：<span style="color:red">500 X 389</span>，大小：<span style="color:red">500KB</span>，接受<span style="color:red"> jpg, gif, png </span>格式
                    		</div>
                    	</td>
                    </tr>
                    <tr>
                    	<td class="td_left"><span class="requi">*</span>综合评分：</td>
                    	<td><img width="150" height="25" src="${base}/images/${sp.zongHe}.png" /> <lable>${sp.estimateAverage}</lable></td>
                    </tr>
                    <tr>
                    	<td class="td_left"><span class="requi">*</span>效率评价：</td>
                    	<td><img width="150" height="25" src="${base}/images/${sp.xiaoLv}.png" /> <lable>${sp.estimateEfficiency}</lable></td>
                    </tr>
                     <tr>
                    	<td class="td_left"><span class="requi">*</span>服务评价：</td>
                    	<td><img width="150" height="25" src="${base}/images/${sp.fuWu}.png" /> <lable>${sp.estimateService}</lable></td>
                    </tr>
                     <tr>
                    	<td class="td_left"><span class="requi">*</span>描述评价：</td>
                    	<td><img width="150" height="25" src="${base}/images/${sp.miaoShu}.png" /> <lable>${sp.estimateDesc}</lable></td>
                    </tr>
                    <tr>
                    	<td class="td_left"><span class="requi">*</span>公司等级：</td>
                    	<td>
                    		${level.levelName} (${level.levelMoney})
                        </td>
                    </tr>
                    <tr>
                    	<td class="td_left"><span class="requi">*</span>公司规模：</td>
                    	<td><input name="scale" value="${supplierCompany.scale}" class="text"/> (单位:人)
                        <span class="error"></span></td>
                    </tr>
                    <tr>
                    	<td class="td_left"><span class="requi">*</span>注册资金：</td>
                    	<td><input name="capital" value="${supplierCompany.capital}" class="text"/> (单位:万)<span class="error"></span></td>
                    </tr>
                    <tr>
                    	<td class="td_left"><span class="requi">*</span>成立时间：</td>
                    	<td><input type="text" class="text Wdate" name="establish" value="${supplierCompany.es}" onClick="WdatePicker()" readOnly><span class="error"></span></td>
                    </tr>
                    <tr>
                    	<td class="td_left"><span class="requi">*</span>年检时间：</td>
                    	<td><input name="inspection" class="text Wdate" type="text" value="${supplierCompany.in}" onClick="WdatePicker()" readOnly><span class="error"></span></td>
                    </tr>
                    <tr>
                    	<td class="td_left"><span class="requi">*</span>营业年限：</td>
                    	<td><input name="durationStart" class="text Wdate" type="text" value="${supplierCompany.st}" onClick="WdatePicker()" readOnly><span class="text-center">&#12288;--&#12288;</span> 
                    	<input name="durationEnd" class="text Wdate" type="text" onClick="WdatePicker()" value="${supplierCompany.en}" readOnly><span class="error"></span><span class="error"></span></td>
                    </tr>
                    <tr>
                    	<td class="td_left"><span class="requi">*</span>公司地址：</td>
                    	<td><input class="text" name="address" style="width:400px;" value="${supplierCompany.address}"/>
                        <span class="error"></span></td>
                    </tr>
                    <tr>
                    	<td class="td_left"><span class="requi">*</span>公司简介：</td>
                    	<td colspan="2"><textarea lass="text" rows="5" cols="100" name="intro">${supplierCompany.intro}</textarea></td>
                    </tr>
                    <tr style="height:10px"><td style="height:10px"></td></tr>
                    <!--<tr>
                    	<td class="td_left"><span class="requi">*</span>从业证书：</td>
                    	<td class="zhengshu">
                            <div style="float:left">
                    			[#list pracList as l]
		                            [#if l.typeId != 3]
		                            	<ul style="float:left;margin-left:10px;">
			                    			<li><img src="${l.downloadPath}" width="70" height="50"  /></li>
			                    			<li style="width:70px;text-align:center"><a style="color:blue;" onclick="return confirm('确认删除此证书?');" href="${base}/supplier/deleteCertificate.xhtml?id=${l.id}&returnUrl=xiangqing">删除</a></li>
			                    		</ul>
		                            [/#if]
	                            [/#list]
                    		</div>
                    		<div style="float:left;margin-left:20px;">
                    		</div>
                    		<!--
                    		<div style="float:left;margin-left:20px;width:350px">
                    			公司资质最多允许上传6张,大小: 500KB,尺寸：263 X 177,格式：jpg, gif, png
                    		</div>
                    	</td>
                    </tr> -->
                    <!--<tr>
                    	<td class="td_left"><span class="requi">*</span>资历证书：</td>
                    	<td class="zhengshu">
                            <div style="float:left">
                    			[#list qualList as l]
		                            [#if l.typeId != 3]
		                            	<ul style="float:left;margin-left:10px;">
			                    			<li><img src="${l.downloadPath}" width="70" height="50" /></li>
			                    			<li style="width:70px;text-align:center"><a style="color:blue;" onclick="return confirm('确认删除此证书?');" href="${base}/supplier/deleteCertificate.xhtml?id=${l.id}&returnUrl=xiangqing">删除</a></li>
			                    		</ul>
		                            [/#if]
	                            [/#list]
                    		</div>
                    		<div style="float:left;margin-left:20px;">
                    		</div>
                    		<!--
                    		<div style="float:left;margin-left:20px;width:350px">
                    			公司资质最多允许上传6张,大小: 500KB,尺寸：263 X 177,格式：jpg, gif, png
                    		</div>
                    	</td>
                    </tr> -->
                    
                   <!-- <tr>
                    	<td class="td_left"><span class="requi">*</span>从业证书：</td>
                    	<td class="zhengshu">
                            <div style="float:left">
                    			[#list pracList as l]
		                            [#if l.typeId != 3]
		                            	<ul style="float:left;margin-left:10px;">
			                    			<li><img src="${l.downloadPath}" width="70" height="50"  /></li>
			                    			<li style="width:70px;text-align:center"><a style="color:blue;" onclick="return confirm('确认删除此证书?');" href="${base}/supplier/deleteCertificate.xhtml?id=${l.id}&returnUrl=xiangqing">删除</a></li>
			                    		</ul>
		                            [/#if]
	                            [/#list]
                    		</div>
                    		<div style="float:left;margin-left:20px;">
                    		</div>
                    		<!--
                    		<div style="float:left;margin-left:20px;width:350px">
                    			公司资质最多允许上传6张,大小: 500KB,尺寸：263 X 177,格式：jpg, gif, png
                    		</div> -->
                    	</td>
                    </tr>-->
                    
                    <tr>
                    	<td class="td_left"><span class="requi">*</span>家装行业从业资格类证书：</td>
                    	<td class="zhengshu">
                            <div style="float:left">
                    			[#list decoration as l]
		                            [#if l.typeId != 3]
		                            	<ul style="float:left;margin-left:10px;">
			                    			<li><img src="${l.downloadPath}" width="70" height="50" /></li>
			                    			<li style="width:70px;text-align:center"><a style="color:blue;" onclick="return confirm('确认删除此证书?');" href="${base}/supplier/deleteCertificate.xhtml?id=${l.id}&returnUrl=xiangqing">删除</a></li>
			                    		</ul>
		                            [/#if]
	                            [/#list]
                    		</div>
                    		<div style="float:left;margin-left:20px;">
                    		</div>
                    		<!--
                    		<div style="float:left;margin-left:20px;width:350px">
                    			公司资质最多允许上传6张,大小: 500KB,尺寸：263 X 177,格式：jpg, gif, png
                    		</div> -->
                    	</td>
                    </tr>
                    
                    <tr>
                    	<td class="td_left"><span class="requi">*</span>开户银行许可证：</td>
                    	<td class="zhengshu">
                            <div style="float:left">
                    			[#list bank as l]
		                            [#if l.typeId != 3]
		                            	<ul style="float:left;margin-left:10px;">
			                    			<li><img src="${l.downloadPath}" width="70" height="50" /></li>
			                    			<li style="width:70px;text-align:center"><a style="color:blue;" onclick="return confirm('确认删除此证书?');" href="${base}/supplier/deleteCertificate.xhtml?id=${l.id}&returnUrl=xiangqing">删除</a></li>
			                    		</ul>
		                            [/#if]
	                            [/#list]
                    		</div>
                    		<div style="float:left;margin-left:20px;">
                    		</div>
                    		<!--
                    		<div style="float:left;margin-left:20px;width:350px">
                    			公司资质最多允许上传6张,大小: 500KB,尺寸：263 X 177,格式：jpg, gif, png
                    		</div> -->
                    	</td>
                    </tr>
                    
                    <tr>
                    	<td class="td_left"><span class="requi">*</span>商标注册证复印件：</td>
                    	<td class="zhengshu">
                            <div style="float:left">
                    			[#list trademark as l]
		                            [#if l.typeId != 3]
		                            	<ul style="float:left;margin-left:10px;">
			                    			<li><img src="${l.downloadPath}" width="70" height="50" /></li>
			                    			<li style="width:70px;text-align:center"><a style="color:blue;" onclick="return confirm('确认删除此证书?');" href="${base}/supplier/deleteCertificate.xhtml?id=${l.id}&returnUrl=xiangqing">删除</a></li>
			                    		</ul>
		                            [/#if]
	                            [/#list]
                    		</div>
                    		<div style="float:left;margin-left:20px;">
                    		</div>
                    		<!--
                    		<div style="float:left;margin-left:20px;width:350px">
                    			公司资质最多允许上传6张,大小: 500KB,尺寸：263 X 177,格式：jpg, gif, png
                    		</div> -->
                    	</td>
                    </tr>
                    
                    <tr>
                    	<td class="td_left"><span class="requi">*</span>授权书：</td>
                    	<td class="zhengshu">
                            <div style="float:left">
                    			[#list author as l]
		                            [#if l.typeId != 3]
		                            	<ul style="float:left;margin-left:10px;">
			                    			<li><img src="${l.downloadPath}" width="70" height="50" /></li>
			                    			<li style="width:70px;text-align:center"><a style="color:blue;" onclick="return confirm('确认删除此证书?');" href="${base}/supplier/deleteCertificate.xhtml?id=${l.id}&returnUrl=xiangqing">删除</a></li>
			                    		</ul>
		                            [/#if]
	                            [/#list]
                    		</div>
                    		<div style="float:left;margin-left:20px;">
                    		</div>
                    		<!--
                    		<div style="float:left;margin-left:20px;width:350px">
                    			公司资质最多允许上传6张,大小: 500KB,尺寸：263 X 177,格式：jpg, gif, png
                    		</div> -->
                    	</td>
                    </tr>
                    
                    <tr>
                    	<td class="td_left"><span class="requi">*</span>税务登记证：</td>
                    	<td class="zhengshu">
                            <div style="float:left">
                    			[#list taxation as l]
		                            [#if l.typeId != 3]
		                            	<ul style="float:left;margin-left:10px;">
			                    			<li><img src="${l.downloadPath}" width="70" height="50" /></li>
			                    			<li style="width:70px;text-align:center"><a style="color:blue;" onclick="return confirm('确认删除此证书?');" href="${base}/supplier/deleteCertificate.xhtml?id=${l.id}&returnUrl=xiangqing">删除</a></li>
			                    		</ul>
		                            [/#if]
	                            [/#list]
                    		</div>
                    		<div style="float:left;margin-left:20px;">
                    		</div>
                    		<!--
                    		<div style="float:left;margin-left:20px;width:350px">
                    			公司资质最多允许上传6张,大小: 500KB,尺寸：263 X 177,格式：jpg, gif, png
                    		</div> -->
                    	</td>
                    </tr>
                    
                    <tr>
                    	<td class="td_left"><span class="requi">*</span>营业执照副本：</td>
                    	<td class="zhengshu">
                            <div style="float:left">
                    			[#list business as l]
		                            [#if l.typeId != 3]
		                            	<ul style="float:left;margin-left:10px;">
			                    			<li><img src="${l.downloadPath}" width="70" height="50" /></li>
			                    			<li style="width:70px;text-align:center"><a style="color:blue;" onclick="return confirm('确认删除此证书?');" href="${base}/supplier/deleteCertificate.xhtml?id=${l.id}&returnUrl=xiangqing">删除</a></li>
			                    		</ul>
		                            [/#if]
	                            [/#list]
                    		</div>
                    		<div style="float:left;margin-left:20px;">
                    		</div>
                    		<!--
                    		<div style="float:left;margin-left:20px;width:350px">
                    			公司资质最多允许上传6张,大小: 500KB,尺寸：263 X 177,格式：jpg, gif, png
                    		</div> -->
                    	</td>
                    </tr>
                    
                    <tr>
                    	<td class="td_left"><span class="requi">*</span>组织机构代码证：</td>
                    	<td class="zhengshu">
                            <div style="float:left">
                    			[#list organization as l]
		                            [#if l.typeId != 3]
		                            	<ul style="float:left;margin-left:10px;">
			                    			<li><img src="${l.downloadPath}" width="70" height="50" /></li>
			                    			<li style="width:70px;text-align:center"><a style="color:blue;" onclick="return confirm('确认删除此证书?');" href="${base}/supplier/deleteCertificate.xhtml?id=${l.id}&returnUrl=xiangqing">删除</a></li>
			                    		</ul>
		                            [/#if]
	                            [/#list]
                    		</div>
                    		<div style="float:left;margin-left:20px;">
                    		</div>
                    		<!--
                    		<div style="float:left;margin-left:20px;width:350px">
                    			公司资质最多允许上传6张,大小: 500KB,尺寸：263 X 177,格式：jpg, gif, png
                    		</div> -->
                    	</td>
                    </tr>
                    
                    <tr>
                    	<td class="td_left"><span class="requi">*</span>公司实景：</td>
                    	<td class="zhengshu">
                            <div style="float:left">
                    			[#list liveList as l]
		                            [#if l.typeId != 3]
		                            	<ul style="float:left;margin-left:10px;">
			                    			<li><img src="${l.downloadPath}" width="70" height="50" /></li>
			                    			<li style="width:70px;text-align:center">
			                    				<a>${l.remark}</a>
			                    				<br/>
			                    				<a style="color:blue;" onclick="return confirm('确认删除此证书?');" href="${base}/supplier/deleteCertificate.xhtml?id=${l.id}&returnUrl=xiangqing">删除</a>
			                    			</li>
			                    		</ul>
		                            [/#if]
	                            [/#list]
                    		</div>
                    	</td>
                    </tr>
                    <tr>
                    	<td class="td_left"><span class="requi">*</span>上传证书：</td>
                		<td>&nbsp;&nbsp;<span class="btn uploadCerf">上传证书</span></td>
                	</tr>
                    <tr style="height:10px"><td style="height:10px"></td></tr>
                    <tr>
                    	<td class="td_left"><span class="requi">*</span>员工描述：</td>
                    	<td style="width:800px;">
                    		[#list st as s]
                    			<ul style="float:left;margin-left:10px;margin-bottom:10px;">
	                    			<li>
	                    				[#if s.avatar == null]
	                    					<img src="${base}/images/defaultuser.jpg" width="70" height="70" alt="${s.name}" title="${s.job}" />
	                    				[#else]
	                    					<img src="${s.avatar}" width="70" height="70" alt="${s.name}" title="${s.job}" />
	                    				[/#if]
		                    		</li>
	                    			<li style="width:60px;text-align:center">
	                    				<a style="color:blue;">${s.name}</a>
	                    			</li>
	                    		</ul>
                            [/#list] 
                    	</td>
                    </tr>
                    <tr>
                    	<td class="td_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    	<td>
                    		&nbsp;
                    		<span onclick="return validataCompyFrom();return confirm('确认提交数据?');" class="btn" rel="add">提交保存</span>
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
		
		<script type="text/html" id="uploadCerf">
			<div class="ui-dialog" style="width:400px;">
				<h2><span class="close"></span><strong>上传证书</strong></h2>
				<form id="send" action="${base}/supplier/uploadCertificate.xhtml?returnUrl=xiangqing" enctype="multipart/form-data" method="post">
					<div >
						<ul style="padding:20px;float:left;">
							<li>&nbsp;</li>
							<li><strong><b class="red">*</b> 证书类型：</strong> 
								<select onchange="changeType(this.value)" name="cid" style="width:140px;">
									<option value="-1">--请选择--</option>
									[#list certList as item]
										<option value="${item.id}">${item.name}</option>
									[/#list]
								</select>
							</li>
							<li>&nbsp;</li>
							<li><strong><b class="red">*</b> 编辑备注：</strong> 
								<input type="text" class="text" name="remark" placeholder="可以为空" />
							</li>
							<li>&nbsp;</li>
							<li class="btn"><span onclick="return validataAddUserFrom(this)" class="btn" rel="add">提交保存</span></li>
							<li>&nbsp;</li>
						</ul>
					</div>
					<div style="padding :20px;">
						<div id="preview"> 
						    <img id="imghead" style="padding-left:10px;" onclick="imgClick()"  width=100 height=100 border=0 src='${base}/images/defaultAdd.png'> 
						</div>  
						<div style="float:left;">
							<input id="userImgFile" onchange="changeImage(this)" style="display:none;" name="userImg" class="btn crt" type="file" id="male" />
						<div>
					</div>
					<br />
				</form>
			</div>
		</script>
	</html>