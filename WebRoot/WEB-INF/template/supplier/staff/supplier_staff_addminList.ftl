<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>商家员工列表</title>
[#include "/common/res.ftl"]
<style>
	.fieldStyle{
		padding:20px;
	}
	
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
				<form id="search" action="">
				<ul>
					<li>
						<span class="sp"><strong>员工编号：</strong><input type="text" class="text" name="uid" id=""></span>
						<span class="sp"><strong>员工姓名：</strong><input type="text" class="text" name="uname" id=""></span>
						<strong>职位类型：</strong>
						<div class="select area mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p>
								 <a  value="-1">--请选择--</a>
								 [#list spostionList as l]
	                                <a  value="${l.id}">${l.name}</a>
	                             [/#list]
							</p>
							<input type="hidden" name="sid"  value="">
						</div>
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
		
			<div style="float:right;margin-bottom:5px;"><a class="obtn" href="${base}/supplierSaff/edit.xhtml?sid=${id}&returnUrl=admin_staff_edit&mid=${mid}&pid=${pid}">添加员工</a></div>
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
							<a class="obtn" href="${base}/supplierSaff/edit.xhtml?id=${item.id}&returnUrl=admin_staff_edit&mid=${mid}&pid=${pid}">编辑</a>
							<a class="obtn" onclick="return confirm('确定删除?')" href="${base}/supplierSaff/remove.xhtml?sid=${item.id}&returnUrl=supplier_staff_addminList&mid=${mid}&pid=${pid}&id=${id}">删除</a> 
						</td>
					</tr>
					[/#list]
				</tbody>
		</table>
		[#include "/common/pagination.ftl"]
	</div>
	</div>
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]
</body>
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
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
			<a class="obtn" href="${base}/supplierSaff/edit.xhtml?id=<%=result.elements[i].id%>&returnUrl=admin_staff_edit&mid=${mid}&pid=${pid}">编辑</a>
			<a onclick="return confirm('确定删除?')" class="obtn" href="${base}/supplierSaff/remove.xhtml?sid=<%=result.elements[i].id%>&returnUrl=supplier_staff_addminList&mid=${mid}&pid=${pid}&id=${id}">删除</span> 
		</td>
	</tr>
<%}%>
</script>
<script type="text/javascript">
$(function(){
	$('.ui-paging').page({
		url:'${base}/supplierSaff/adminPagination.do?id=${id}',
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
<script type="text/html" id="pannel">
	<div class="ui-dialog addManager" style="width:450px;">
		<h2><span class="close"></span><strong>编辑员工信息</strong></h2>
		<form id="send" action="${base}/supplierSaff/saveSupplierCompanyStaff.xhtml?mid=${mid}&pid=${pid}" enctype="multipart/form-data" method="post">
			<input type="hidden" name="spid">
			<input type="hidden" name="newUser" value="newUser">
			<div >
				<ul style="padding:20px;float:left;">
					<li><strong><b class="red">*</b> 员工姓名：</strong> <input style="width:195px;" type="text" class="text" name="name" placeholder="员工姓名"></li>
					<li>&nbsp;</li>
					<li><strong><b class="red">*</b> 职位类型：</strong> 
						<select onchange="changeStaffType(this.value)" name="sid" style="width:140px;">
							<option value="-1">--请选择--</option>
							[#list spostionList as item]
								<option value="${item.id}">${item.name}</option>
							[/#list]
						</select>
					</li>
					<li>&nbsp;</li>
					<li><strong><b class="red">*</b> 职位描述：</strong> <input style="width:195px;" type="text" class="text" name="job" placeholder="职位描述"></li>
					<li>&nbsp;</li>
					<li id="linian" style="display:none"><strong><b class="red">*</b> 设计理念：</strong> <textarea rows="2" cols="28" class="text" name="sjsSuggest" placeholder="设计师理念"></textarea></li>
					<li id="jieshao" style="display:none"><strong><b class="red">*</b> 个人介绍：</strong> <textarea rows="2" cols="28" class="text" name="gzSuggest" placeholder="工长介绍"></textarea></li>
					<li>&nbsp;</li>
					<li class="btn"><span onclick="return validataAddUserFrom(this)" class="btn" rel="add">提交保存</span></li>
				</ul>
			</div>
			<div style="padding :20px;">
				<div id="preview"> 
					<input type="hidden" name="avatar"/>
				    <img id="imghead" style="padding-left:10px;"  width=100 height=100 border=0 src='${base}/uploadFile/default.jpg'> 
				</div>  
				<div style="float:left;">
					<input id="userImgFile" onchange="changeImage(this)" style="width:65px;padding-left:25px;" name="userImg" class="btn" type="file" id="male" />
				<div>
			</div>
		</form>
	</div>
</script>
<script type="text/html" id="pannelSjsSuggest">
	<div class="ui-dialog addManager" style="width:450px;>
		<h2><span class="close"></span><strong>编辑员工信息</strong></h2>
		<form id="sendSjsSuggest" class="sendSjsSuggest" action="${base}/supplierSaff/updateSupplierCompanyStaff.xhtml?mid=${mid}&pid=${pid}" enctype="multipart/form-data" method="post">
			<input type="hidden" name="spid">
			<input type="hidden" name="id">
			<div >
				<ul style="padding:20px;float:left;">
					<li><strong><b class="red">*</b> 员工姓名：</strong> <input style="width:195px;" type="text" class="text" name="name" placeholder="员工姓名"></li>
					<li>&nbsp;</li>
					<li><strong><b class="red">*</b> 职位类型：</strong> 
						<select onchange="changeStaffType(this.value)" name="sid" style="width:140px;">
							<option value="-1">--请选择--</option>
							[#list spostionList as item]
								<option value="${item.id}">${item.name}</option>
							[/#list]
						</select>
					</li>
					<li>&nbsp;</li>
					<li><strong><b class="red">*</b> 职位描述：</strong> <input style="width:195px;" type="text" class="text" name="job" placeholder="职位描述"></li>
					<li>&nbsp;</li>
					<li id="linian" style=""><strong><b class="red">*</b> 设计理念：</strong> <textarea rows="2" cols="28" class="text" name="sjsSuggest" placeholder="设计师理念"></textarea></li>
					<li id="jieshao" style=""><strong><b class="red">*</b> 个人介绍：</strong> <textarea rows="2" cols="28" class="text" name="gzSuggest" placeholder="个人介绍"></textarea></li>
					<li>&nbsp;</li>
					<li class="btn"><span onclick="return validataAddUserFrom(this)" class="btn" rel="add">提交保存</span></li>
				</ul>
			</div>
			<div style="padding :20px;">
					<input type="hidden" name="avatar"/>
				<div id="preview"> 
				    <img id="imghead" style="padding-left:10px;"  width=100 height=100 border=0 src='${base}/uploadFile/default.jpg'> 
				</div>  
				<div style="float:left;">
					<input id="userImgFile" onchange="changeImage(this)" style="width:65px;padding-left:25px;" name="userImg" class="btn" type="file" id="male" />
				<div>
			</div>
		</form>
	</div>
</script>

<script type="text/html" id="pannelGzSuggest">
	<div class="ui-dialog addManager" style="width:450px;" onload="alert('we')">
		<h2><span class="close"></span><strong>编辑员工信息</strong></h2>
		<form id="sendGzSuggest" action="${base}/supplierSaff/updateSupplierCompanyStaff.xhtml?mid=${mid}&pid=${pid}" enctype="multipart/form-data" method="post">
			<input type="hidden" name="spid">
			<input type="hidden" name="id">
			<div >
				<ul style="padding:20px;float:left;">
					<li><strong><b class="red">*</b> 员工姓名：</strong> <input style="width:195px;" type="text" class="text" name="name" placeholder="员工姓名"></li>
					<li>&nbsp;</li>
					<li><strong><b class="red">*</b> 职位类型：</strong> 
						<select onchange="changeStaffType(this.value)" name="sid" style="width:140px;">
							<option value="-1">--请选择--</option>
							[#list spostionList as item]
								<option value="${item.id}">${item.name}</option>
							[/#list]
						</select>
					</li>
					<li>&nbsp;</li>
					<li><strong><b class="red">*</b> 职位描述：</strong> <input style="width:195px;" type="text" class="text" name="job" placeholder="职位描述"></li>
					<li>&nbsp;</li>
					<li id="linian" style="display:none"><strong><b class="red">*</b> 设计理念：</strong> <textarea rows="2" cols="28" class="text" name="sjsSuggest" placeholder="设计师理念"></textarea></li>
					<li id="jieshao" style="display:"><strong><b class="red">*</b> 个人介绍：</strong> <textarea rows="2" cols="28" class="text" name="gzSuggest" placeholder="个人介绍"></textarea></li>
					<li>&nbsp;</li>
					<li class="btn"><span onclick="return validataAddUserFrom(this)" class="btn" rel="add">提交保存</span></li>
				</ul>
			</div>
			<div style="padding :20px;">
				<input type="hidden" name="avatar"/>
				<div id="preview"> 
				    <img id="imghead" style="padding-left:10px;"  width=100 height=100 border=0 src='${base}/uploadFile/default.jpg'> 
				</div>  
				<div style="float:left;">
					<input id="userImgFile" onchange="changeImage(this)" style="width:65px;padding-left:25px;" name="userImg" class="btn" type="file" id="male" />
				<div>
			</div>
		</form>
	</div>
</script>
<script>
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
	
	$(function(){
		
		$('.add').click(function(){
	        var $this=$(this),data=$.parseJSON($this.attr('rel'));
	        addMask();
	        $('body').append($('#pannel').html());
	        $('.ui-dialog').data('act','modify');
	        $('.ui-dialog input,.ui-dialog select,.ui-dialog textarea').each(function(index,ele){
	            $(ele).val(data[index]);
	        });
	        center($('.ui-dialog'));
	    });
	    
		$('body').delegate(".sjsSuggest",'click',function(){
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
	    
	    $('body').delegate(".gzSuggest",'click',function(){
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