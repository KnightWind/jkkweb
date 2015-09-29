<!DOCTYPE HTML>
<html>
<head>
<title>监理报告详情</title>
<meta charset="utf-8">
[#include "/common/res.ftl"]
<script type="text/javascript" src="${base}/scripts/form.js"></script>
<script type="text/javascript" src="${base}/scripts/ajaxfileupload.js"></script>
<script type="text/javascript" src="${base}/scripts/imageHandle.js"></script>
<script>
  $(function(){
     $(".deleteOne").click(function(){
       var TF=confirm("确定删除该众凑分类?");
       if(TF){
	        var id=$(this).attr("rel");
	        $.post("deleteOne.do",{id:id},function(rel){
	           justTip(rel.msg);
	           if(rel.ret==0){
	              location.reload();
	           }
	        });
	     }
     });
  });
</script>
<style>
.addManager{
height:600px;
width:500px;
}
.table2{
margin-top:30px;
}
.table1{
margin-top:30px;
}
#send{
padding:20px;
}
#send li{
  margin-top:20px;	
}

.categoryContain{
margin:10px 0px 10px 30px;
}
.setbtnContain{
	position:relative;
   margin:50px 0px 10px 10px;
}
.setbtnContain span{
	position:absolute;
	margin:140px 0 0 10px;
}
</style>
</head>

</head>
<body>
<!-- header -->
[#include "/common/head.ftl"]
<!-- wrap -->
	<div class="wrap index bc clr">
		<div class="conWrap fl">
			[#include "/common/nav.ftl"]
				<div class="conBox">	
				  <div class="categoryContain">	
					    <select name="" id="category" size=8 style="float:left;width:200px;">
						      [#list icList as item]
						        [#assign flag=0]
							         [#list itemCategory as temp]
							            [#if temp.categoryId==item.id]
						                 <option value="${item.id}" disabled >${item.name}</option>
						                 [#assign flag=1]
							            [/#if]
							         [/#list]
							         [#if flag==0]
							            <option value="${item.id}">${item.name}</option>
							         [/#if]
						      [/#list]
						</select>
				  </div>
				  <div class="setbtnContain">
				    <span class="btn setbtn">设置为众筹分类</span>
				  </div>
				<div>
					<table class="format"  >
						<thead>
							<th width="5%">顺序号</th>
							<th width="10%">标题</th>
							<th width="10%">分类名称</th>
							<th width="10%">图片</th>
							<th width="10%">创建时间</th>
							<th width="5%">操作</th>	
						</thead>
						<tbody>
							[#list itemCategory as item]
							<tr>
								<td>${item.seq}</td>		
								<td>${item.title}</td>
								<td>${item.name}</td> 
								<td><img src="${item.imgUrl}" width="100"/></td>	
								<td>${item.createTimeHandle}</td>	
								<td>
						            <span class="btn deleteOne" rel="${item.id}">删除<span>
								</td>		
							</tr>
							[/#list]
						</tbody>
			       </table>
				</div>
		  </div>
		</div>
		[#include "/common/menu.ftl"]
	</div>
<!-- footer -->
 [#include "/common/foot.ftl"]
</body>

<script type="text/html" id="pannel">
<div class="ui-dialog addManager">
	<h2><span class="close"></span><strong>设置众凑分类</strong></h2>
	<form id="send" action="saveOneCate.do" enctype="multipart/form-data" method="post">
	   <input name="categoryId" type="hidden" id="gmdname"/>
		<ul>
			<li>
				<strong><b class="red">*</b>标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题：</strong> 
				<input type="text" name="title" id="gmdname" class="text" placeholder="标题">
			</li>
			<li>
				<strong><b class="red">*</b>显示顺序：</strong> 
				<select name="seq">
				    [#list useCategory as item]
				       <option value="${item}">${item}</option>
				    [/#list]
				</select>
			</li>
			<li>
				<div style="padding :20px;">
						<div id="preview"> 
							<input type="hidden" name="avatar"/>
						    <img id="imghead" style="padding-left:10px;"  width=300 height=300 border=0> 
						</div>  
						
						<div style="float:left;">
							<input id="userImgFile" onchange="changeImage(this,300,300)" style="width:65px;padding:15px;" name="userImg" class="btn" type="file" id="male" />
						<div>
			    </div>
			</li>
			<li class="btn"><input type="submit"  style="margin-left:55px;" value="保存"/></li>
		</ul>
	</form>
</div>	
</script>
<script>
$(function(){
	$('#category').bind('change',function(){
		var value=$(this).val();
		$('span.setbtn').attr('rel',value);
	});
	$('.setbtn').bind('click',function(){
        var categoryId=$(this).attr('rel');
        if(categoryId==null||categoryId==''||categoryId==undefined ){
           justTip("请选择分类");
           return;
        }
        addMask();
        $('body').append($('#pannel').html());
        $('#gmdname').val(categoryId);
        center($('.ui-dialog'));
    });
})
</script>
</html>