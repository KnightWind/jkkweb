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
			<div style="float:right;padding-bottom:10px;">
				<span class="btn add" rel="${act.id}" >添加奖项</span>
			</div>
			<table class="format">
				<thead>
					<th width="5%">编号</th>
					<th width="13%">活动名称</th>					
					<th width="8%">奖项名称</th>
					<th width="8%">奖品名称</th>
					<th width="8%">奖品数量</th>
					<th width="8%">中奖概率</th>
					<th width="8%">创建时间</th>
					<th width="10%">操作</th>				
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td>${item.id}</td>
						<td>${item.activeName}</td>
						<td>${item.rank}等奖</td>
						<td>${item.prizeName}</td>
						<td>${item.prizeNum}</td>
						<td>${item.probability}</td>
						<td>${item.createTimeString}</td>
						<td>			
							<span class="btn modify" rel=["${item.id}","${act.id}","${item.rank}","${item.prizeName}","${item.prizeNum}","${item.probability}"]>修改</span>
							<span class="btn del" rel="${base}/awards/remove.do?id=${item.id}">删除</span>
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
	<td><%=result.elements[i].activeName%></td>
	<td><%=result.elements[i].name%></td>
	<td><%=result.elements[i].prizeName%></td>					
	<td><%=result.elements[i].prizeNum%></td>					
	<td><%=result.elements[i].probabilit%></td>
	<td><%=result.elements[i].createTimeString%></td>
	<td>
		<span class="btn modify">修改</span>
		<span class="btn del" rel="${base}/awards/remove.do?id=<%=result.elements[i].id%>">删除</span>
	</td>
</tr>	
<%}%>
</script>
<script>

function validata(el){
	if($("#rank").val() == ""){
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
				<input type="hidden" id="id" name="id"  />
				<input type="hidden" id="activeId" name="activeId"  />
				<ul style="padding:20px;float:left;">
					<li><strong><b class="red">*</b> 奖项名称：</strong> 
						<select id="rank" name="rank" style="width:195px;">
							<option value="1">一等奖</option>
							<option value="2">二等奖</option>
							<option value="3">三等奖</option>
							<option value="4">四等奖</option>
							<option value="5">五等奖</option>
							<option value="6">六等奖</option>
							<option value="7">七等奖</option>
							<option value="8">八等奖</option>
							<option value="9">九等奖</option>
							<option value="10">十等奖</option>
						</select>
					</li>
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