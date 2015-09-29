<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<META HTTP-EQUIV="pragma" CONTENT="no-cache"> 
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate"> 
		<META HTTP-EQUIV="expires" CONTENT="Wed, 26 Feb 1997 08:21:57 GMT"> 
		<title>商家管理</title>
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
		.dataShow{ widtd:48%; height:500px; float:left; padding-top:20px;}
		.dataShow p{height:40px;}
		.dataShow table{ margin-bottom:20px;}
		.dataShow table td{ border-right:1px solid #ccc;}
		.dataSp{ margin-right:4%;}
		.requi{color:red};
		div.btn{margin-left:130px;}
		.fieldStyle{
			padding:20px;
		}
		.tools ul li{margin-bottom:10px;height:inherit;}
		.preview img{width:100px;height:100px;}
		.uploadPic strong{float:left;line-height: 30px;padding: 0 10px;display: block;width: 90px;text-align: right;height:100px;line-height:100px;}
		.uploadPic input{margin:10px 0 0 100px;}
		#btn{margin-left: 133px;}
		</style>
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
					<span id="sel1" data-role="员工列表">编辑员工</span>
				</h2>
				<div class="conBox">
					<!--内容放这里 start-->
					<div class="tools white border mb10 sp10 clr">
						[#if staff.id == null]
						<form id="send" action="${base}/supplierSaff/saveSupplierCompanyStaff.xhtml?returnUrl=supplier_staff_list" enctype="multipart/form-data" method="post">
						[#else]
						<form id="send" action="${base}/supplierSaff/updateSupplierCompanyStaff.xhtml?returnUrl=supplier_staff_list" enctype="multipart/form-data" method="post">
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
							<div id="btn"><span onclick="return validataAddUserFrom(this)" class="btn">提交保存</span></div>
						</form>	
					</div>
				</div>
			</div>
		[#include "/common/supplierMenu.ftl"]
		</div>
		
		<!-- footer -->
		<div class="footer">
			<div class="wrap bc tc">
				<p>北京居天下网络科技有限公司　010-67482366 67477686 &nbsp;&nbsp;地址：北京朝阳区十八里店周家庄村288号融望大酒店6层 居天下版权所有Copyright © 2013-2015 京ICP备15010944</p>
			</div>
		</div>
			
	<script type="text/html" id="list">
	<%for(i=0;i<result.elements.length;i++){%>
	<tr menuid="<%=result.elements[i].id%>">
		<td><input type="checkbox" id="ckOne" value="<%=result.elements[i].id%>" /></td>
		<td><%=result.elements[i].id%></td>
		<td><%=result.elements[i].name%></td>
		<td><%=result.elements[i].supplierPosition%></td>
		<td><%=result.elements[i].createTimeString%></td>
		<td><%=result.elements[i].job%></td>
		<td>
			<a class="obtn" href="${base}/supplierSaff/edit.xhtml?id=<%=result.elements[i].id%>">编辑</a>
			<span class="btn del" onclick="return confirm('确定删除?')" rel="${base}/supplierSaff/remove.do?id=<%=result.elements[i].id%>">删除</span> 
		</td>
	</tr>
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/supplierSaff/pagination.do',
		deal:{'click':'.search'},		
		send:function(){
			var data={
					pageSize:10,
					search:$('#search').formatJSON()
			};
			return data;
		},
	    fun:function(dataR){
	    	if(dataR.records<1){ return false;}
			$('table.format tbody').html(template.render('list',dataR)); 
	    }
	});
});	
</script>
	</body>
		<script type="text/javascript">
		
			//预览效果图
			function changeImage(file,val) 
			{ 
			  var ImageFileExtend = ".gif,.png,.jpg";
			  var fileExtend = file.value.substring(file.value.lastIndexOf('.')).toLowerCase();
			  if(ImageFileExtend.indexOf(fileExtend) == -1)
		       {
		            justTip('请选择gif,png,jpg图片 !');
		            return;
		       }
			  
			
			  if(val == ""){
			  	var img = document.getElementById("imghead");
			  	img.src="";
			  }
			  if (file.files && file.files[0]) 
			  { 
			    var img = document.getElementById("imghead"); 
			    var reader = new FileReader(); 
			    reader.onload = function(evt){img.src = evt.target.result;} 
			    reader.readAsDataURL(file.files[0]); 
			  } 
			  else 
			  { 
			    var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="'; 
			    file.select(); 
			    var src = document.selection.createRange().text; 
			    var img = document.getElementById("imghead"); 
			    img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src; 
			    var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight); 
			    status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height); 
			  }
			  
			} 
		
		function imgClick(){
			$(".crt").click();
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
				document.getElementById('linian').value = '';
				document.getElementById('jieshao').style.display='';
			}
		}
		
		
		//表单验证
		function validataAddUserFrom(el){
			
			if($("input[name='name']").val() == ""){
				justTip("请输入员工姓名!");
				return;
			}
			
			var sid = $("select[name='sid']").val();
			
			if(sid == -1){
				justTip("请选择职位类型!")
				return;
			}
			
			if($("input[name='newUser']").val() == "newUser"){
				$("#send").submit();
				return;
			}
			
			
			$(el).parents("form").submit();
			
		}
		
	</script>
</html>