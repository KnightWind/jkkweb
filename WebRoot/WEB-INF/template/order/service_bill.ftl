<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<title>商家管理</title>
		[#include "/common/res.ftl"]
		<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
		<script type="text/javascript" src="${base}/scripts/form.js"></script>
		<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
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
		</style>
	</head>
	<body>
	<!-- header -->
		<div class="header">
			<div class="logo">
				<h1><a href="#"><img src="${base}/images/logo.png"></a></h1>
				<p>
					<a target="_blank" href="http://shop.jiakeke.com?sp_id=${su.spId}">欢迎您${su.spname}</a>
					<a href="${base}/supplier/logout.xhtml"><i class="new">&#xf08b;</i> 注销</a>
					<a href="${base}/supplier/password" class="logout"><i class="new">&#xf09c;</i> 修改密码</a>
				</p>
			</div>
		</div>
		<!-- wrap -->
		<div class="wrap index bc clr">
			<div class="conWrap fl">
				<h2 class="hd mb10">
					<strong><a href="#">商家后台</a></strong> &gt;
					<span id="sel1" data-role="结算列表">结算列表</span> &gt;
					<span id="sel1" data-role="服务订单结算列表">服务订单结算列表</span>
				</h2>
				<div class="conBox">
			<!--内容放这里 start-->
				<div class="tools white border mb10 sp10 clr">
				<form id="search" action="">
				<ul>
					<li>
						<strong>所属区域：</strong>
						<div class="select province mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p rel="${base}/goods/item/sheng.do">
								<a value="">--请选择--</a>
                            	[#list lst as l]
                                <a value="${l.areaDomain}">${l.area}</a>
                             	[/#list]
							</p>					
							<input type="hidden" name="province" value="">
						</div>
						<div class="select city mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p rel="${base}/goods/item/city.do">
							</p>
						<input type="hidden" name="city"  value="">
						</div>
						<div class="select area mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p>
							</p>
							<input type="hidden" name="area"  value="">
						</div>
					</li>
					<li>
						<span class="sp"><strong>商户名称：</strong><input type="text" class="text" name="sname" id=""></span>
						<span class="sp"><strong class="auto">联系人：</strong><input type="text" class="text" name="user" id=""></span>
					</li>
					<li>
						<span class="sp"><strong>结算日期：</strong><input type="text" class="text Wdate" name="start" id="" onClick="WdatePicker()" readOnly></span>
						<span class="sp"><input type="text" class="text Wdate" name="end" id="" onClick="WdatePicker()" readOnly></span>
						<span class="btn search">查 询</span>
					</li>
				</ul>
				</form>
			</div>
			<table class="format">
				<thead>					
					<th width="11%">预约订单数</th>
					<th width="11%">销售金额</th>
					<th width="11%">退货金额</th>
					<th width="11%">实售金额</th>
					<th width="11%">分成比例</th>
					<th width="11%">分成金额</th>
					<th width="11%">结算金额</th>
					<th width="11%">操作</th>				
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td></td>
						<td>${item.itemSumPrice}</td>
						<td>${item.itemSumPrice-item.bd}</td>
						<td>${item.price*item.num}</td>
						<td>${item.ga*100}%</td>					
						<td>${item.paySumPrice/100}</td>
						<td>${item.paySumPrice-item.paySumPrice/100}</td>	
						<td>
							<a class="obtn" href="${base}/order/ordership/list.xhtml?mid=${mid}&pid=${pid}&id=${item.mid}&sname=${item.spName}&tool=${item.price*item.num}&fc=${item.paySumPrice/100}&jsa=${item.paySumPrice-item.paySumPrice/100}&ga=${item.ga*100}">查看详细</a>
						</td>
					</tr>
					[/#list]
				</tbody>
		</table>
		[#include "/common/pagination.ftl"]
			<!--内容放这里 end-->
				</div>
			</div>
			[#include "/common/supplierMenu.ftl"]</div>
			<!-- footer -->
			<div class="footer">
				<div class="wrap bc tc">
					<p>北京居天下网络科技有限公司　010-67482366 67477686 &nbsp;&nbsp;地址：北京朝阳区十八里店周家庄村288号融望大酒店6层 居天下版权所有Copyright © 2013-2015 京ICP备15010944</p>
				</div>
			</div>
		</body>		
		<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
		<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
		<script type="text/html" id="list">
		<%for(i=0;i<result.elements.length;i++){%>
			<tr menuid="<%=result.elements[i].id%>">
			<td></td>
			<td><%=result.elements[i].itemSumPrice%></td>
			<td><%=result.elements[i].itemSumPrice-result.elements[i].bd%></td>
			<td><%=result.elements[i].price*result.elements[i].num%></td>
			<td><%=result.elements[i].ga*100%>%</td>					
			<td><%=result.elements[i].paySumPrice/100%></td>
			<td><%=result.elements[i].paySumPrice-result.elements[i].paySumPrice/100%></td>
			<td><a class="obtn" href="${base}/order/ordership/list.xhtml?id=<%=result.elements[i].id%>&sname=<%=result.elements[i].spName%>&tool=<%=result.elements[i].price*result.elements[i].num%>&fc=<%=result.elements[i].paySumPrice-result.elements[i].paySumPrice%>&jsa=<%=result.elements[i].paySumPrice-result.elements[i].paySumPrice/100%>&ga=<%=result.elements[i].ga*100%>&pid=${pid}&mid=${mid}">查看详细</a></td>
		</tr>	
		<%}%>
		</script>
		<script>
			$(function(){
				$('.ui-paging').page({
					url:'${base}/order/ordership/serviceBillPage.do',
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
				var status=window.location.href.split('status=')[1];
				$('.pageTab span.cur').removeClass('cur');
				switch(status){
					case "0":$('.pageTab span:eq(1)').addClass('cur');break;
					case "1":$('.pageTab span:eq(2)').addClass('cur');break;
					case "-1":$('.pageTab span:eq(3)').addClass('cur');break;
					default:$('.pageTab span:eq(0)').addClass('cur');break;
				}
			});	
			</script>
	</html>