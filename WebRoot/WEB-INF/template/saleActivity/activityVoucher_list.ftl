<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>代金券管理</title>
[#include "/common/res.ftl"]

<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/form.js"></script>
<Style>
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
[#include "/common/head.ftl"]
<!-- wrap -->
<div class="wrap index bc clr">
	<div class="conWrap fl">
		[#include "/common/nav.ftl"]
				<div class="conBox">		
								<div style="float:right;margin-bottom:5px;">
									<span class="btn addnew" >
									   	 创建现金券
									</span>
								</div>			
								<table class="format">
									<thead>
										<th width="5%">活动编号</th>
										<th width="15%">活动标题</th>
										<th width="10%">代金券名称</th>
										<th width="8%">金额</th>
										<th width="8%">领券人数</th>	
										<th width="10%">状态</th>			
										<th width="10%">创建时间</th>			
										<th width="10%">有效时间</th>			
										<th width="10%">更新时间</th>
										<th width="10%">操作</th>			
									</thead>
									<tbody>
										[#list pagination.dataList as item]
										<tr>
											<td>${item.id}</td>		
											<td>${item.activityName}</td>
											<td>${item.name}</td>
											<td>${item.price}</td>
											<td>${item.num}</td>	
											<td>${item.statusString}</td>	
											<td>${item.createTimeString}</td>	
											<td>${item.activeTimeString}</td>	
											<td>${item.updateTimeString}</td>	
											<td>
											    <span class="btn modify" rel=["${item.id}","${item.activityId}","${item.name}","${item.price}","${item.descr}"]>
												   	修改
												</span>
											    <span class="btn">
											    	<a href="${base}/activityCard/view.xhtml?id=${item.id}&pid=${pid}&mid=${mid}">详情</a>						   
											    </span>
											    <span class="btn" rel="${base}/activityCard/remove.do?id=${item.id}">
											    	删除			   
											    </span>
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

<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr>
		<td><%=result.elements[i].id%></td>		
		<td><%=result.elements[i].activityName%></td>
		<td><%=result.elements[i].name%></td>
		<td><%=result.elements[i].price%></td>
		<td><%=result.elements[i].descrSplit%></td>	
		<td><%=result.elements[i].updateTimeString%></td>	
		<td> 
			<span class="btn">
		    	<a href="${base}/activityCard/edit.xhtml?id=${item.id}&pid=${pid}&mid=${mid}">修改</a>						   
		    </span>
		    <span class="btn">
		    	<a href="${base}/activityCard/view.xhtml?id=${item.id}&pid=${pid}&mid=${mid}">详情</a>						   
		    </span>
		    <span class="btn" rel="${base}/activityCard/remove.do?id=${item.id}">
		    	删除			   
		    </span>
	    </td>
    </tr>	
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/activityCard/pagination.do',
		deal:{'click':'.search'},
		send:function(){
			var data={
					pageSize:1,
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


function valiData(el){
	
	if($('select[name="activityId"]').val() == -1){
		justTip("请选择活动！");
		return false;
	}
	if($('input[name="name"]').val() == ""){
		justTip("请输入代金券名称！");
		return false;
	}
	if($('input[name="price"]').val() == ""){
		justTip("请输入活动卡金额！");
		return false;
	}
	if(!/^\d+$/.test($("input[name='price']").val())){
		justTip("活动卡金额为数字 !")
		return;
	}
	
	$(el).parents("form").submit();

}


</script>
<script type="text/html" id="pannel">
	<div class="ui-dialog addManager" style="width:400px;" onload="alert('we')">
		<h2><span class="close"></span><strong>编辑活动卡</strong></h2>
		<form id="sendGzSuggest" action="${base}/activityCard/save.xhtml?mid=${mid}&pid=${pid}" method="post">
			<input type="hidden" name="id" />
			<div >
				<table>
					<tr>
						<td class="td_left"><strong><b class="red">*</b> 活动标题：</strong></td>
						<td>
							<select name="activityId">
								<option value="-1">--请选择--</option>
								[#list list as a]
	                                <option value="${a.id}" title="${a.title}">${a.title}</option>
	                             [/#list]
							</select>
						</td>
					</tr>
					<tr>
						<td class="td_left"><strong><b class="red">*</b> 代金券图片：</strong></td>
						<td><input type="file" name="img" /></td>
					</tr>
					<tr>
						<td class="td_left"><strong><b class="red">*</b> 代金券名称：</strong></td>
						<td><input style="width:195px;" type="text" class="text" name="name" placeholder="代金券名称"/></td>
					</tr>
					<tr>
						<td class="td_left"><strong><b class="red">*</b> 金额：</strong></td>
						<td><input style="width:195px;" type="text" class="text" name="price" placeholder="卡金额"/></td>
					</tr>
					<tr>
						<td class="td_left"><strong><b class="red">*</b> 是否有效：</strong></td>
						<td>
							<input type="radio" checked="checked" name="status" value="1" /> 有效
							<input type="radio" name="status" value="0" /> 失效
						</td>
					</tr>
					<tr>
						<td class="td_left"><strong><b class="red">*</b> 有效时间：</strong></td>
						<td>
							<input style="width:195px;" type="text" class="text Wdate" name="endTime" onClick="WdatePicker()" readOnly>
						</td>
					</tr>
					<tr>
						<td  class="td_left"><strong><b class="red">*</b> 使用说明：</strong></td>
						<td>
							 <textarea rows="0" cols="24" class="text" name="descr" placeholder="使用说明"/></textarea>
						</td>
					</tr>
					<tr>
						<td colspan="2"><span onclick="return valiData(this)" class="btn" rel="add">提交保存</span></td>
					</tr>
				</table>
			</div>
		</form>
	</div>
</script>
</html>