
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>购买地管理</title>
[#include "/common/res.ftl"]
<script type="text/javascript" src="${base}/scripts/form.js"></script>
<style>
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
  margin-top:5px;
}
</style>
</head>

<body>
<!-- header -->
[#include "/common/head.ftl"]
<!-- wrap -->
<div class="wrap index bc clr">
	<div class="conWrap fl">
		[#include "/common/nav.ftl"]
		<div class="conBox">
			<div style="float:right;margin-bottom:5px;margin-right:10px;"><span class="btn addnew">添加购买地</span></div>
			    <table class="format table1">
					<thead>				
					    <th width="10%">编号</th>	
						<th width="20%">名称</th>
						<th width="20%">线上/线下</th>
						<th width="20%">创建时间</th>
						<th width="10%">创建人</th>
						<th width="20%">操作</th>
					</thead>
					<tbody>
						[#list purchaseForList as item]
							[#if item.type==1]
								<tr>			
							    	<td>${item.id}</td>			
									<td>${item.gmdname}</td>
									<td>${item.typeVal}</td>
									<td>${item.createTimeHandle}</td>
									<td>${item.createUser}</td>
									<td>
									    <span class="btn modify" rel=["${item.id}","${item.gmdname}","${item.type}"]>修改</span>
										<span class="btn delete" rel=${item.id}>删除</span>				
			                        </td>
								</tr>
							 [/#if]	
						[/#list]
					</tbody>
		    	</table>	
		    	
	    	  <table class="format table2">
					<thead>				
					    <th width="10%">编号</th>	
						<th width="20%">名称</th>
						<th width="20%">线上/线下</th>
						<th width="20%">创建时间</th>
						<th width="10%">创建人</th>
						<th width="20%">操作</th>
					</thead>
					<tbody>
						[#list purchaseForList as item]
							[#if item.type==0]
								<tr>			
							    	<td>${item.id}</td>			
									<td>${item.gmdname}</td>
									<td>${item.typeVal}</td>
									<td>${item.createTimeHandle}</td>
									<td>${item.createUser}</td>
									<td>
									    <span class="btn modify" rel=["${item.id}","${item.gmdname}","${item.type}"]>修改</span>
										<span class="btn delete" rel=${item.id}>删除</span>				
			                        </td>
								</tr>
							 [/#if]	
						[/#list]
					</tbody>
		    	</table>	
	     </div>
	 </div>
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]

<script type="text/html" id="pannel">
<div class="ui-dialog addManager">
	<h2><span class="close"></span><strong>修改购买地信息</strong></h2>
	<form id="send" action="save.do">
	<input type="hidden" name="id">
		<ul>
			<li>
				<strong><b class="red">*</b>购物买：</strong> 
				<input type="text" name="gmdname" class="text" placeholder="购买地">
			</li>
			<li>
				<strong><b class="red">*</b>类&nbsp;&nbsp;&nbsp;型：</strong> 
				<select name="type">
				      <option value="1">线上</option>
				      <option value="0">线下</option>
				</select>
			</li>
			<li class="btn"><span class="btn save" rel="add" >保存</span></li>
		</ul>
	</form>
</div>	
</script>

<script>
$(function(){
   $(".delete").click(function(){
      var TF=confirm("确定删除吗?");
      if(TF){
        var id=$(this).attr("rel");
        $.post("delete.do",{id:id},function(json){
             justTip(json.msg);
             if(json.ret==0){
                location.reload();
             }
        });
        }
      
   });
});
</script>
</body>
</html>