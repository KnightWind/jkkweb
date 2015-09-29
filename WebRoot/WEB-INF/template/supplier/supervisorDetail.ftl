<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<title>监理管理</title>
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
			    var sendUrl = "${base}/supplier/uploadLogo.xhtml?id=${supplier.id}"
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
			
			function validataCompyFrom(){
				if($("textarea[name='contactUser']").val() == ""){
					justTip("请输入联系人 !")
					return;
				}
				if($("input[name='address']").val() == ""){
					justTip("请输商户地址 !");
					return;
				}
				if($("input[name='contactMobile']").val() == ""){
					justTip("请输入联系电话 !");
					return;
				}
				if($("input[name='contactMobile']").val().length != 11){
					justTip("请输入11位手机号码 !");
					return;
				}
				
				if($("input[name='subPhone']").val() == ""){
					justTip("请输入分机号 !")
					return;
				}
				if($("input[name='legalPerson']").val() == ""){
					justTip("请输入商户法人 !");
					return;
				}
				
				if($("input[name='bindMobile']").val() == ""){
					justTip("请输入商户绑定手机 !")
					return;
				}
				if($("input[name='bindMobile']").val().length != 11){
					justTip("请输入11位绑定手机号码 !");
					return;
				}
				
				if(!/^\d+$/.test($("input[name='bindMobile']").val())){
					justTip("绑定手机号码为11位数字 !")
					return;
				}
				
				if($("input[name='businessCode']").val() == ""){
					justTip("请输入营业执照 !");
					return;
				}
				if($("input[name='legalPerson']").val() == ""){
					justTip("请输入营业执照 !");
					return;
				}
				
				$("#compForm").submit();
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
			
			
			function submit(el){
				$(el).parents("form").submit();
			}
			
			function imgClick(){
				$(".crt").click();
			}
			
			$(function(){
			
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
		[#include "/common/supervisorHead.ftl"]
		<!-- wrap -->
		<div class="wrap index bc clr">
			<div class="conWrap fl"> 
				<h2 class="hd mb10">
					<strong><a href="#">监理后台</a></strong> &gt;
					<span id="sel1" data-role="首页">监理首页设置</span>
				</h2>
				<div class="conBox">
			<!--内容放这里 start-->
		<form id="compForm" action="${base}/supplier/updateSupervisor.xhtml"  method="post" >
			<input type="hidden" name="id" value="${sp.id}" />
			<input type="hidden" name="condId" value="${supplierCondition.id}">
            <table id="table_detail"  class="table_09_1 padding-td-10">
                <tbody>
                    <tr>
                    	<td class="td_left"><span class="requi">*</span>监理名称：</td>
                    	<td><lable>${sp.spName}</lable></td>
                    </tr>
                    <tr>
                    	<td class="td_left"><span class="requi">*</span>监理照片：</td>
                    	<td>
                    		<div style="float:left" id="certificateList">
                    			[#if logo!=null]<img src="${logo}" width="100" height="100" />[/#if]
                    		</div>
                    		<div style="float:left;margin-left:20px;line-height:55px;" >
                    			<input style="width: 73px;" type="file" id="logoImg" name="img" onchange="ajaxFileUpload('logoImg',this)"/>
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
                    	<td class="td_left"><span class="requi">*</span>商户等级：</td>
                    	<td>
                    		${level.levelName} (${level.levelMoney})
                        </td>
                    </tr>
                    <tr>
                    	<td class="td_left"><span class="requi">*</span>商户地址：</td>
                    	<td><input class="text" name="address" style="width:400px;" value="${sp.address}"/>
                        <span class="error"></span></td>
                    </tr>
                    <tr>
                    	<td class="td_left"><span class="requi">*</span>联系人：</td>
                    	<td><input name="contactUser" value="${sp.contactUser}" class="text"/>
                        <span class="error"></span></td>
                    </tr>
                    <tr>
                    	<td class="td_left"><span class="requi">*</span>联系电话：</td>
                    	<td><input name="contactMobile" value="${sp.contactMobile}" class="text"/>
                        <span class="error"></span></td>
                    </tr>
                    <tr>
                    	<td class="td_left"><span class="requi">*</span>400分机号：</td>
                    	<td><input name="subPhone" value="${sp.subPhone}" class="text"/>
                        <span class="error"></span></td>
                    </tr>
                    <tr>
                    	<td class="td_left"><span class="requi">*</span>自定义域名：</td>
                    	<td><input type="text" class="text" name="domain" value="${sp.domain}" ><span class="error"></span></td>
                    </tr>
                    <tr>
                    	<td class="td_left"><span class="requi">*</span>法人：</td>
                    	<td><input name="legalPerson" class="text" type="text" value="${sp.legalPerson}" ><span class="error"></span></td>
                    </tr>
                    <tr>
                    	<td class="td_left"><span class="requi">*</span>绑定手机：</td>
                    	<td><input name="bindMobile" class="text" type="text" value="${sp.bindMobile}" ><span class="error"></span></td>
                    </tr>
                    <tr>
                    	<td class="td_left"><span class="requi">*</span>营业执照：</td>
                    	<td><input name="businessCode" class="text" type="text" value="${sp.businessCode}"><span class="text-center"></span></td>
                    </tr>
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
			[#include "/common/supervisorMenu.ftl"]</div>
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
				<form id="send" action="${base}/supplier/uploadCertificate.xhtml?returnUrl=supervisorDetail" enctype="multipart/form-data" method="post">
					<div >
						<ul style="padding:20px;float:left;">
							<li>&nbsp;</li>
							<li><strong><b class="red">*</b> 证书类型：</strong> 
								<select onchange="changeStaffType(this.value)" name="cid" style="width:140px;">
									<option value="-1">--请选择--</option>
									[#list certList as item]
										<option value="${item.id}">${item.name}</option>
									[/#list]
								</select>
							</li>
							<li>&nbsp;</li>
							<li class="btn"><span onclick="submit(this)" class="btn save" rel="add">提交保存</span></li>
							<li>&nbsp;</li>
						</ul>
					</div>
					<div style="padding :20px;">
						<div id="preview"> 
							<input type="hidden" name="avatar"/>
						    <img id="imghead" style="padding-left:10px;"  onclick="imgClick()"  width=100 height=100 border=0 src='${base}/images/defaultAdd.png'> 
						</div>  
						<div style="float:left;">
							<input id="userImgFile" onchange="changeImage(this)" style="display:none;" name="userImg" class="btn crt" type="file" id="male" />
						<div>
					</div>
					<br />
				</form>
			</div>
		</script>
		
		<script type="text/html" id="pannel">
			<div class="ui-dialog addManager" style="width:450px;" onload="alert('we')">
				<h2><span class="close"></span><strong>编辑员工信息</strong></h2>
				<form id="send" action="${base}/supplier/saveSupplierCompanyStaff.xhtml" enctype="multipart/form-data" method="post">
					<input type="hidden" name="id">
					
				</form>
			</div>
		</script>
		
	</html>