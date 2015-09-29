<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>验收节点详细</title>

<style>
.addManager{
  height:200px;
}
#send{
  padding-left:20px;
}
#send .saveBtn{
  padding-left:73px;
}

#send li{
  padding-top:10px;
 
}
#send li .lileft{
width:50px;
}
</style>

[#include "/common/res.ftl"]
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/form.js"></script>
</head>

<body>
<!-- header -->
[#include "/common/head.ftl"]
<!-- wrap -->
<div class="wrap index bc clr">
	<div class="conWrap fl">
		[#include "/common/nav.ftl"]
		<div class="conBox">
			<div>
				 <form id="search">
						<ul>
							<li>
							  <span class="sp"><strong>编号：</strong>${eng.id}</span>							
							</li>
							<li>
							 <span class="sp"><strong>名称：</strong>${eng.stagName}</span>
							</li>
						</ul>
						<ul>
							<li>
							  <span class="sp"><strong>备注：</strong>${eng.remark}</span>							
							</li>
							<li>
							 <span class="sp"><strong>创建时间：</strong>${eng.createTimeHandle}</span>
							</li>
						</ul>
						<ul>
							<li>
							  <span class="sp"><strong>描述：</strong>${eng.stagDesc}</span>							
							</li>					
						<ul>
				 <form>
			</div>		
			<div style="float:right;margin-bottom:5px;margin-right:10px;">
			    <span class="btn addnew">创建验收节点</span>
			    <span class="btn search" onclick="history.back()" style="font-size:12px;">返回</span>
			</div>			
			<table class="format">
				<thead>
					<th width="10%">编号</th>
					<th width="30%">描述</th>
					<th width="20%">备注</th>	
					<th width="20%">创建时间</th>	
					<th width="10%">创建人</th>	
					<th width="10%">操作</th>			
				</thead>
				<tbody>
					[#list engMXList as item]
					<tr>
						<td>${item.id}</td>		
						<td>${item.mxDesc}</td>
						<td>${item.remark}</td>	
						<td>${item.createTimeHandle}</td>
						<td>${item.createUser}</td>
						<td>					   
						    <span class="btn modify" rel=["${item.pid}","${item.id}","${item.mxDesc}","${item.remark}","${item.createUser}"]>修改</span>
						    
						    [#if item.status==1] 
					           <span class="btn del" rel="${base}/engineeringStageMx/hide.do?id=${item.id}">隐藏</span>
					        [#else]
					           <span class="btn del" rel="${base}/engineeringStageMx/show.do?id=${item.id}">显示</span>
						   [/#if]
						   
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

<script type="text/html" id="pannel">
<div class="ui-dialog addManager">
	<h2><span class="close"></span><strong>修改验收标准信息</strong></h2>
	<form id="send" action="${base}/engineeringStageMx/save.do">
	<input type="hidden" name="pid" value="${eng.id}"/>
	<input type="hidden" name="id"/>
		<ul>
			<li>
				<strong class="lileft"><b class="red">*</b> 节点描述：</strong> 
				<input type="text" name="mxDesc" placeholder="节点描述" class="text">
			</li>
			<li>
				<strong class="lileft"><b class="red">*</b> 节点备注：</strong> 
				<input type="text" name="remark" placeholder="备注" class="text">
			</li>
			<li>
				<strong class="lileft"><b class="red">*</b> 创建人：</strong> 
				<input type="text" name="createUser" placeholder="创建人" class="text">
			</li>
			<li class="btn saveBtn"><span class="btn save" rel="add">保存</span></li>
		</ul>
	</form>
</div>	
</script>

</body>
</html>