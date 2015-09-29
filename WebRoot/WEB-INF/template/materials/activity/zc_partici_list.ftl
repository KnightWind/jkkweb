<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<title>用户参与名单</title>
		[#include "/common/res.ftl"]	
		<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
		<script type="text/javascript" src="${base}/scripts/form.js"></script>
		<style>
		.logo{height:82px; background:#fff; border-bottom:2px solid #1D9DA6;}
		.logo p{padding-top:20px;}
		.logo p a{color:#333; font-size:14px;}
		.logo p a i{ font-size:16px;}
		.conWrap h2.hd{ border-color:#ccc;}
		.dataShow{ width:48%; height:500px; float:left; padding-top:20px;}
		.dataShow p{height:40px;}
		.dataShow table{ margin-bottom:20px;}
		.dataShow table td{ border-right:1px solid #ccc;}
		.dataSp{ margin-right:4%;}
		.fieldStyle{
			padding:20px;
		}
		.address{width:361px;}
		</style>
	</head>
	<body>
	<!-- header -->
		[#include "/materials/common/head.ftl"]
		<!-- wrap -->
		<div class="wrap index bc clr">
			<div class="conWrap fl">
				<h2 class="hd mb10">
					<strong><a href="#">建材后台</a></strong> &gt;
					<span id="sel1" data-role="">用户参与名单</span>
				</h2>
				 <div class="conBox">
					<!--内容放这里 start-->
			      
            <div class="tools white border mb10 sp10 clr">
				<form id="search" action="">
				<ul>
					<li>
						<span class="sp"><strong class="auto">用户名称：</strong><input type="text" class="text" name="name"></span>
						<span class="btn search">查 询</span>
					</li>
				</ul>
				
				</form>
			</div>
			
			<table class="format">
				<thead>
					<th width="10%">用户账号</th>
					<th width="7%">支付金额</th>
					<th width="7%">支付类型</th>
					<th width="8%">创建时间</th>
					<th width="12%">用户收货信息</th>
					<th width="12%">备注信息</th>
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td>${item.userName}</td>
						<td>${item.moneys}</td>
						<td>${item.payTypeVal}</td>
						<td>${item.createDateString}</td>
						<td><input type="text" class="text address" value="${item.address}" onblur="setAddress(${item.id},this)" /></td>
						<td>
							<span class="btn modify" rel=["${item.id}","${item.remark}"]>编辑</span>
						</td>
					</tr>
					[/#list]
				</tbody>
		</table>
		[#include "/common/pagination.ftl"]
				<!--内容放这里 end-->
				 </div>
	       </div>
			[#include "/materials/common/nav.ftl"]
       </div>
	   <!-- footer -->
		<div class="footer">
			<div class="wrap bc tc">
				<p>北京居天下网络科技有限公司　010-67482366 67477686 &nbsp;&nbsp;地址：北京朝阳区十八里店周家庄村288号融望大酒店6层 居天下版权所有Copyright © 2013-2015 京ICP备15010944</p>
			</div>
		</div>
    </body>	

<script type="text/html" id="pannel">
	<div class="ui-dialog addManager" style="width:300px;">
		<h2><span class="close"></span><strong>编辑备注信息</strong></h2>
		<form id="send" action="" method="post" style="padding:10px;">
			<input type="hidden" name="id">
			<textarea  rows="5" cols="40" class="text" name="remark" placeholder="编辑备注信息"></textarea>
			<span class="btn save" onclick="tijiao(this)">提交保存</span>
		</form>
	</div>
</script>

<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr menuid="<%=result.elements[i].id%>">
		<td><%=result.elements[i].userName%></td>
		<td><%=result.elements[i].moneys%></td>
		<td><%=result.elements[i].payTypeVal%></td>
		<td><%=result.elements[i].createDateString%></td>
		<td><input type="text" class="text address" value="<%=result.elements[i].address%>"  onblur="setAddress(<%=result.elements[i].id%>,this)" /></td>
		<td>
			<span class="btn modify" rel=["<%=result.elements[i].id%>","<%=result.elements[i].remark%>"]>编辑</span>
		</td>
	</tr>
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/material/order/participationPage.do',
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

function setAddress(id,el){
	var address = $(el).val();
	if(id == null)
		return;
	var url = "${base}/material/order/setAddress.do";
	$.post(url,{"id":id,"address":address},function(data){
	});
}

function tijiao(el){
	var remark = $('textarea[name="remark"]').val();
	var id = $('input[name="id"]').val();
	if(id == '' || remark == '')
		return;
	$.post("${base}/material/order/setRemark.do",{"id":id,"remark":remark},function(data){
		if(data.code == 0){
			window.location.reload();	
		}else{
			justTip("保存失败");
		}
	});
}



</script>
    	
</html>