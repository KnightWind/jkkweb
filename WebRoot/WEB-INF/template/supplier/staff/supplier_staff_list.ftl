<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
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
		tr{
			padding-top:10px;
		}
		td{height:55px;font-size:14px;}
		.td_left{text-align:right;font-size:15px;font-weight:bold;}
		#table_detail{mangin:50px;}
		.requi{color:red};
		
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
					<span id="sel1" data-role="员工列表">员工列表</span>
				</h2>
				<div class="conBox">
			<!--内容放这里 start-->
					<div class="tools white border mb10 sp10 clr">
				<form id="search" action="">
				<ul>
					<li>
						<span class="sp"><strong>员工编号：</strong><input type="text" class="text" name="uid" id=""></span>
						<span class="sp"><strong>员工姓名：</strong><input type="text" class="text" name="uname" id=""></span>
					</li>
					<li>
						<strong>创建日期：</strong>
						<span class="sp"><input type="text" class="text Wdate" name="createBegin" id="" onClick="WdatePicker()" readOnly> &nbsp;--</span>
						<span class="sp"><input type="text" class="text Wdate" name="createEnd" id="" onClick="WdatePicker()" readOnly></span>
						<span class="btn search">查 询</span>
					</li>				
				</ul>
				</form>
			</div>
			<div style="float:right;margin-bottom:5px;"><a class="obtn" href="${base}/supplierSaff/edit.xhtml?returnUrl=staff_edit">添加员工</a></div>
			<table class="format">
				<thead>
					<th width="4%"><input type="checkbox" id="ckAll" /></th>
					<th width="8%">编号</th>
					<th width="10%">员工姓名</th>
					<th width="10%">职位</th>
					<th width="8%">创建时间</th>
					<th width="20%">职位描述</th>
					<th width="10%">操作</th>				
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						
						<td><input type="checkbox" id="ckOne" value="${item.id}" /></td>
						<td>${item.id}</td>
						<td>${item.name}</td>
						<td>${item.supplierPosition}</td>
						<td>${item.createTimeString}</td>
						<td>${item.job}</td>
						<td>
							<a class="obtn" href="${base}/supplierSaff/edit.xhtml?id=${item.id}&returnUrl=staff_edit">编辑</a>
							<a class="obtn del" onclick="return confirm('确定删除?')" href="${base}/supplierSaff/remove.xhtml?sid=${item.id}&returnUrl=supplier_staff_list">删除</a> 
						</td>
					</tr>
					[/#list]
				</tbody>
		</table>
		[#include "/common/pagination.ftl"]
			<!--内容放这里 end-->
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
			<a class="obtn" href="${base}/supplierSaff/edit.xhtml?id=<%=result.elements[i].id%>&returnUrl=staff_edit">编辑</a>
			<span class="btn del" onclick="return confirm('确定删除?')" rel="${base}/supplierSaff/remove.xhtml?sid=<%=result.elements[i].id%>&returnUrl=supplier_staff_list">删除</span> 
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
		function ajaxFileUpload(id){
		    //开始上传文件时显示一个图片,文件上传完成将图片隐藏
		    //$("#loading").ajaxStart(function(){$(this).show();}).ajaxComplete(function(){$(this).hide();});
		    //执行上传文件操作的函数
		    var sendUrl = "";
		   	if(id != "logoImg"){
		   		sendUrl = "${base}/supplier/uploadCertificate.xhtml?id=${supplierCompany.spId}"
		   	}else{
		   		sendUrl = "${base}/supplier/uploadLogo.xhtml?id=${supplierCompany.id}"
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
		
		//选择职位类型
		function changeStaffType(val){
			if(val == -1){
				document.getElementById('linian').style.display='none';
				document.getElementById('jieshao').style.display='none';
			}
			if(val == 1){
				document.getElementById('linian').style.display='';
				document.getElementById('jieshao').style.display='none';
			}
			if(val == 2){
				document.getElementById('linian').style.display='none';
				document.getElementById('jieshao').style.display='';
			}
		}
		
		
		//表单验证
		function validataAddUserFrom(){
			
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
			if($("input[name='newUser']").val() == "newUser"){
				$("#send").submit();
				return;
			}
			
			if(sid == 1){
				$("#sendSjsSuggest").submit();
				return;
			}
			
			if(sid == 2){
				$("#sendGzSuggest").submit();
				return;
			}
			
		}
		
		function validataCompyFrom(){
			if($("textarea[name='intro']").val() == ""){
				justTip("请输入公司简介!")
				return;
			}
			if($("input[name='address']").val() == ""){
				justTip("请输公司地址!");
				return;
			}
			if($("input[name='scale']").val() == ""){
				justTip("请输入公司规模人数!");
				return;
			}
			
			if($("input[name='capital']").val() == ""){
				justTip("请输入公司注册资金!")
				return;
			}
			if($("input[name='establish']").val() == ""){
				justTip("请输入公司成立!");
				return;
			}
			
			if($("input[name='inspection']").val() == ""){
				justTip("请输入年检时间!")
				return;
			}
			if($("input[name='durationStart']").val() == ""){
				justTip("请输入公司开始营业时间!");
				return;
			}
			
			if($("input[name='durationEnd']").val() == ""){
				justTip("请输入公司结束营业时间!")
				return;
			}
			$("#compForm").submit();
		}
		
		$(function(){
			
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
		
		});
	</script>
</html>