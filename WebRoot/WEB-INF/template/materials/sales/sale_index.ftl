<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<title>业主信息管理</title>
		[#include "/common/res.ftl"]	
		<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
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
		
	.bott{
		
	}
	.left{
		<!--width:63%;-->
		width:100%;
	}
	.right{
		width:35%;
		margin-left:30px;
	}
	
	table.format1{ width: 100%; border: 1px solid #ccc; border-collapse: collapse; background: #fff; font-size: 12px;}
	table.format1 caption{ height: 40px; line-height: 40px; font-size: 16px;}
	table.format1 thead th{ border-bottom:1px solid #ccc; height: 40px; font-size: 14px; text-align: center; background: #E7E8EB; font-weight: bolder;}
	table.format1 tbody td{ border-bottom:1px solid #ccc; height: 40px; text-align: center;word-wrap: break-word; word-break: break-all;}
	table.format1 tbody tr.cur td{ background:#E7E8EB;}
	
	.input td{height: 50px;}
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
					<span id="sel1" data-role="">业主信息管理</span>
				</h2>
				 <div class="conBox">
				<div>
					<h2>业主信息录入</h2>
					<hr/>
					<form id="form">
					<table class="input">
						<tr>
							<td>装修需求：</td>
							<td colspan="7"><textarea name="desc" cols="130"></textarea></td>
						</tr>
						<tr>
							<td  class="tr"><font color="red">*</font>业主手机：</td>
							<td><input type="text" class="text"  name="phone"  /></td>
							<td  class="tr">业主姓名：</td>
							<td><input type="text" class="text"  name="name"  /></td>
							<td  class="tr">所在小区：</td>
							<td><input type="text" class="text"  name="community"  /></td>
							<td class="tr">活动卡：</td>
							<td>
								<select name="cardId" class="cardId" id="cardId">
									[#list list as l]
		                                <option value="${l.id}">${l.name}</option>
		                            [/#list]
								</select>
							</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td><span class="btn bm">报名</span></td>
						</tr>
					</table>
					</form>
				</div>
				<h2>业主信息管理</h2>
				<hr/>
				<div class="bott left">
					
					<table class="format" style="float:left;width:100%;">
						<thead>
							<th width="10%">编号</th>
							<th width="10%">业主姓名</th>
							<th width="10%">业主电话</th>
							<th width="20%">所在小区</th>
							<th width="10%">装修需求</th>
						</thead>
						<tbody>
							[#list pagination.dataList as item ]
							<tr>
							    <td>${item.id}</td>
							    <td>${item.username}</td>
								<td>${item.phone}</td>
								<td>${item.community}</td>
								<td>${item.decorateReq}</td>
							</tr>
							[/#list]
						</tbody>
				   </table>
				 </div>
				   <div class="bott right fr" style="display:none">
					<table class="format1">
						<thead>
							<th colspan="2">售卡排名</th>
							<th colspan="2">我的当前名次：<font color="red">${myPreced}</font></th>
						</thead>
						<tbody>
							<tr style="background: #E7E8EB;font-weight:bold;">
								<td widtd="10%">排名</td>
								<td widtd="10%">店铺名称</td>
								<td widtd="10%">售卡数量</td>
								<td widtd="20%">金额</td>
							</tr>
							[#list statList as item ]
							<tr>
							    <td>${item_index+1}</td>
							    <td>${item.spName}</td>
								<td>${item.cnt}</td>
								<td>${item.money}</td>
							</tr>
							[/#list]
						</tbody>
				   </table>
			   </div>
			   <div style="clear:both;"></div>
			[#include "/common/pagination.ftl"]
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
<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr>
		<td><%=result.elements[i].id%></td>
	    <td><%=result.elements[i].username%></td>
		<td><%=result.elements[i].phone%></td>
		<td><%=result.elements[i].community%></td>
		<td><%=result.elements[i].decorateReq%></td>
	</tr>
<%}%>
</script>
<script>
$(function(){


	$(".bm").click(function(){
	
		if($('input[name="phone"]').val() == ''){
			justTip("请输入业主手机！");
			return;
		}	
		
		var re = /^1\d{10}$/
    	if (!re.test($('input[name="phone"]').val())) {
    		justTip("手机号码格式错误！");
			return;
    	}
		
		var url = "${base}/material/sales/add.do"
		$.post(url,$("#form").serialize(),function(data){
			if(data.code == 0){
				justTip("报名成功！");
				//document.getElementById("form").reset();
				window.location.reload();
			}else if(data.code == 1){
				justTip("报名失败，请稍后再试！");
			}else if(data.code == 2){
				justTip("此号码已经报名！");
			}
		});		
		
	});

	$('.ui-paging').page({
		url:'${base}/material/sales/pagination.do',
		deal:{'click':'span.search'},
		format:['pageCount','count'],
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
})
</script>
</html>