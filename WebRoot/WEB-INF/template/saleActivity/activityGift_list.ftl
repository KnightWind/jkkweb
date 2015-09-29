<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>微信引流活动礼包管理</title>
[#include "/common/res.ftl"]

<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/form.js"></script>
<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
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
									   	 创建礼包
									</span>
								</div>			
								<table class="format">
									<thead>
										<th width="5%">活动编号</th>
										<th width="20%">活动标题</th>
										<th width="15%">礼包名称</th>
										<th width="15%">活动卡名称</th>
										<th width="10%">有效时间</th>
										<th width="10%">失效时间</th>	
										<th width="10%">更新时间</th>			
										<th width="15%">操作</th>			
									</thead>
									<tbody>
										[#list pagination.dataList as item]
										<tr>
											<td>${item.id}</td>		
											<td>${item.activityName}</td>
											<td>${item.name}</td>
											<td>${item.cardName}</td>
											<td>${item.startTimeString}</td>
											<td>${item.endTimeString}</td>	
											<td>${item.updateTimeString}</td>	
											<td>
											    <span class="btn modify" rel=["${item.id}","${item.activityId}","${item.cardId}","${item.name}","${item.startTimeString}","${item.endTimeString}","${item.content}"]>
												   	修改
												</span>
											    <span class="btn">
											    	<a href="${base}/activityGift/view.xhtml?id=${item.id}&pid=${pid}&mid=${mid}">详情</a>						   
											    </span>
											    <span class="btn" rel="${base}/activityGift/remove.do?id=${item.id}">
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
		<td><%=result.elements[i].cardName%></td>
		<td><%=result.elements[i].startTimeString%></td>	
		<td><%=result.elements[i].updateTimeString%></td>	
		<td><%=result.elements[i].updateTimeString%></td>	
		<td> 
			<span class="btn">
		    	<a href="${base}/activityGift/edit.xhtml?id=${item.id}&pid=${pid}&mid=${mid}">修改</a>						   
		    </span>
		    <span class="btn">
		    	<a href="${base}/activityGift/view.xhtml?id=${item.id}&pid=${pid}&mid=${mid}">详情</a>						   
		    </span>
		    <span class="btn" rel="${base}/activityGift/remove.do?id=${item.id}">
		    	删除			   
		    </span>
	    </td>
    </tr>	
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/activityGift/pagination.do',
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
	if($('select[name="cardId"]').val() == -1){
		justTip("请选择活动！");
		return false;
	}
	if($('input[name="name"]').val() == ""){
		justTip("请输入礼包名称！");
		return false;
	}
	if($('input[name="startTime"]').val() == ""){
		justTip("请输入活动礼包有效时间！");
		return false;
	}
	if($('input[name="endTime"]').val() == ""){
		justTip("请输入活动礼包有效时间！");
		return false;
	}
	if($('input[name="startTime"]').val() == ""){
		justTip("请输入活动礼包失效时间！");
		return false;
	}
	if($('input[name="content"]').val() == ""){
		justTip("请输入活动礼包详情内容！");
		return false;
	}
	
	$(el).parents("form").submit();

}




</script>
<script type="text/html" id="pannel">
	<div class="ui-dialog addManager" style="width:400px;" onload="alert('we')">
		<h2><span class="close"></span><strong>编辑活动礼包</strong></h2>
		<form id="sendGzSuggest" action="${base}/activityGift/save.xhtml?mid=${mid}&pid=${pid}" method="post">
			<input type="hidden" name="id" />
			<div >
				<table>
					<tr>
						<td class="td_left"><strong><b class="red">*</b> 活动标题：</strong></td>
						<td>
							<select name="activityId">
								<option value="-1">--请选择--</option>
								[#list aThemelist as a]
	                                <option value="${a.id}">${a.title}</option>
	                             [/#list]
							</select>
						</td>
					</tr>
					<tr>
						<td class="td_left"><strong><b class="red">*</b> 活动卡名称：</strong></td>
						<td>
							<select name="cardId">
								<option value="-1">--请选择--</option>
								[#list aCardlist as a]
	                                <option value="${a.id}">${a.name}</option>
	                             [/#list]
							</select>
						</td>
					</tr>
					<tr class="td_left">
						<td><strong><b class="red">*</b> 礼包名称：</strong></td>
						<td><input style="width:195px;" type="text" class="text" name="name" placeholder="礼包名称"></td>
					</tr>
					<tr class="td_left">
						<td><strong><b class="red">*</b> 有效时间：</strong></td>
						<td>
							<input style="width:195px;" type="text" class="text Wdate" name="startTime" onClick="WdatePicker()" readOnly>
						</td>
					</tr>
					<tr class="td_left">
						<td><strong><b class="red">*</b> 失效时间：</strong></td>
						<td>
							<input style="width:195px;" type="text" class="text Wdate" name="endTime" onClick="WdatePicker()" readOnly>
						</td>
					</tr>
					<tr class="td_left">
						<td><strong><b class="red">*</b> 礼包详情：</strong></td>
						<td>
							 <textarea rows="0" cols="21" class="text" name="content" placeholder="礼包详情内容"></textarea>
						</td>
					</tr>
					<tr class="td_left">
						<td colspan="2"><span onclick="return valiData(this)" class="btn" rel="add">提交保存</span></td>
					</tr>
				</table>
			</div>
		</form>
	</div>
</script>
</html>