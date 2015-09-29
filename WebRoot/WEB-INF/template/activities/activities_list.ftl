<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>活动列表</title>
[#include "/common/res.ftl"]
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
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
			<div class="tools white border mb10 sp10 clr">
				<form id="search">
				<input type="hidden" name="status" value="${status}">
				<ul>					
					<li>
					    <span class="sp"><strong>活动名称：</strong><input type="text" class="text" name="name" id=""></span>						
					</li>
					<li>
					    <span class="sp">
						       <strong>开始时间：</strong>
							   <span class="sp"> <input type="text" class="text Wdate" name="startTime1" id="" onClick="WdatePicker()" readOnly></span>
							   <span class="sp"><input type="text" class="text Wdate" name="startTime2" id="" onClick="WdatePicker()" readOnly></span>
						</span>
					</li>
					<li>
					    <span class="sp">
						       <strong>结束时间：</strong>
							   <span class="sp"> <input type="text" class="text Wdate" name="endTime1" id="" onClick="WdatePicker()" readOnly></span>
							   <span class="sp"><input type="text" class="text Wdate" name="endTime2" id="" onClick="WdatePicker()" readOnly></span>
						</span>
					</li>
					<li>
					   <span class="sp">
						<strong>创建时间：</strong>
						   <span class="sp"> <input type="text" class="text Wdate" name="createStart" id="" onClick="WdatePicker()" readOnly></span>
						   <span class="sp"><input type="text" class="text Wdate" name="createEnd" id="" onClick="WdatePicker()" readOnly></span>
						</span>	
					<span class="btn search">查 询</span>
					</li>
				</ul>
				</form>
			</div>
			<div style="float:right;padding-bottom:10px;">
				<a class="obtn" href="${base}/activities/edit.xhtml?mid=${mid}&pid=${pid}">添加活动</a>
			</div>
			<table class="format">
				<thead>
					<th width="5%">编号</th>
					<th width="13%">活动名称</th>					
					<th width="8%">奖项数量</th>
					<th width="8%">开始时间</th>
					<th width="8%">结束时间</th>
					<th width="8%">是否显示数量</th>
					<th width="8%">每人参与总次数</th>
					<th width="8%">每人每天参与次数</th>					
					<th width="7%">每天最多出奖数量</th>
					<th width="8%">创建时间</th>
					<th width="10%">操作</th>				
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td>${item.id}</td>
						<td>${item.name}</td>
						<td>${item.awardsCount}</td>
						<td>${item.startTimeString}</td>
						<td>${item.endTimeString}</td>
						<td>${item.disply}</td>
						<td>${item.total}</td>
						<td>${item.partNum}</td>
						<td>${item.maxNum}</td>
						<td>${item.createTimeString}</td>
						<td>			
							<a class="obtn" href="${base}/awards/index.xhtml?id=${item.id}&mid=${mid}&pid=${pid}">管理奖项</a>
							<!--<span class="btn add" rel=${item.id}>添加奖项</span>-->
							<a class="obtn" href="${base}/activities/edit.xhtml?id=${item.id}&mid=${mid}&pid=${pid}">修改</a>
							<!--<a class="obtn" href="${base}/activities/view.xhtml?id=${item.id}&mid=${mid}&pid=${pid}">详情</a>-->
							<span class="btn del" rel="${base}/activities/remove.do?id=${item.id}">删除</span>
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


<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr menuid="<%=result.elements[i].id%>">
	<td><%=result.elements[i].id%></td>
	<td><%=result.elements[i].name%></td>
	<td><%=result.elements[i].awardsCount%></td>
	<td><%=result.elements[i].startTimeString%></td>					
	<td><%=result.elements[i].endTimeString%></td>					
	<td><%=result.elements[i].disply%></td>
	<td><%=result.elements[i].total%></td>
	<td><%=result.elements[i].partNum%></td>
	<td><%=result.elements[i].maxNum%></td>
	<td><%=result.elements[i].createTimeString%></td>
	<td>
		<a class="obtn" href="${base}/awards/index.xhtml?id=<%=result.elements[i].id%>&mid=${mid}&pid=${pid}">管理奖项</a>
		<!--<span class="btn add" rel=<%=result.elements[i].id%>>添加奖项</span>-->
		<a class="obtn" href="${base}/activities/edit.xhtml?id<%=result.elements[i].id%>&mid=${mid}&pid=${pid}">修改</a>
		<!--<a class="obtn" href="${base}/activities/view.xhtml?id=<%=result.elements[i].id%>&mid=${mid}&pid=${pid}">详情</a>-->
		<span class="btn del" rel="${base}/activities/remove.do?id=${item.id}">删除</span>
	</td>
</tr>	
<%}%>
</script>
<script>

function validata(el){
	if($("#awarname").val() == ""){
		justTip("请输入奖项名称 !")
		return false;
	}
	if($("input[name='prizeName']").val() == ""){
		justTip("请输入奖品名称 !")
		return;
	}
	if($("input[name='prizeNum']").val() == ""){
		justTip("请输入奖品数量 !")
		return false;
	}
	if(!/^\d+$/.test($("input[name='prizeNum']").val())){
		justTip("奖品数量为数字 !")
		return;
	}
	if($("input[name='probability']").val() == ""){
		justTip("请输入中奖概率 !")
		return false;
	}
	
	if(!/^(\d+)$/.test($("input[name='probability']").val())){
		justTip("中奖概率为数字 !")
		return false;
	}
	
	$(el).parents("form").submit();
	
}

$(function(){
	
	 $('body').delegate(".add",'click',function(){
	 	var $this=$(this),data=$.parseJSON($this.attr('rel'));
	    addMask();
	    $('body').append($('#pannel').html());
	    $('.ui-dialog').data('act','modify');
        center($('.ui-dialog'));
        $('.ui-dialog #activeId').attr("value",$(this).attr('rel'));
    });
    

	$('.ui-paging').page({
		url:'${base}/activities/indexpage.do',
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
	<div class="ui-dialog addManager" style="width:350px;">
		<h2><span class="close"></span><strong>添加奖项</strong></h2>
		<form id="send" action="${base}/activities/savAwards.xhtml?mid=${mid}&pid=${pid}" method="post">
				<input type="hidden" id="activeId" name="activeId"  />
				<ul style="padding:20px;float:left;">
					<li><strong><b class="red">*</b> 奖项名称：</strong> <input style="width:195px;" type="text" id="awarname" class="text awarname" name="name" placeholder="奖项名称"></li>
					<li>&nbsp;</li>
					<li><strong><b class="red">*</b> 奖品名称：</strong> <input style="width:195px;" type="text" class="text" name="prizeName" placeholder="奖品名称"></li>
					<li>&nbsp;</li>
					<li><strong><b class="red">*</b> 奖品数量：</strong> <input style="width:195px;" type="text" class="text" name="prizeNum" placeholder="奖品数量"></li>
					<li>&nbsp;</li>
					<li><strong><b class="red">*</b> 中奖概率：</strong> <input style="width:195px;" type="text" class="text" name="probability" placeholder="中奖概率"></li>
					<li>&nbsp;</li>
					<li class="btn"><span onclick="return validata(this)" class="btn">提交保存</span></li>
				</ul>
		</form>
	</div>
</script>

</body>
</html>