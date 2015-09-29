<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<title>结算记录</title>
		[#include "/common/res.ftl"]	
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
		</style>
		<style>
			.jsTable{width:50%;margin:10px 0;}
		 	.jsTable tr td{width:20%;height:40px;line-height:40px;text-align:center;border:1px solid #e9e9e9;}
		</style>
		<script type="text/javascript" src="${base}/scripts/form.js"></script>
	<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
	</head>
	<body>
	<!-- header -->
		[#include "/materials/common/head.ftl"]
		<!-- wrap -->
		<div class="wrap index bc clr">
			<div class="conWrap fl">
				 <div class="conBox">
                     <table class="jsTable">
                        <tr>
                          <td>总收入</td>
                          <td></td>
                          <td>已结算</td>
                          <td></td>
                          <td>未结算</td>
                        </tr>
                        <tr>
                          <td>${inComeTotle}</td>
                          <td>-</td>
                          <td>${jsTotle}</td>
                          <td>=</td>
                          <td>${overplus}</td>
                        </tr>
                     </table>
					<!--内容放这里 start-->
					<table class="format">
						<thead>
							<th width="10%">订单号</th>
							<th width="10%">用户帐号</th>
							<th width="20%">商品名称</th>
							<th width="10%">商品价格(单位:元)</th>
							<th width="10%">支付金额(单位:元)</th>	
							<th width="10%">支付类型</th>	
							<th width="10%">结算状态</th>	
							<th width="15%">结算时间</th>	
						</thead>
						<tbody>
							[#list pagination.dataList as item]
							<tr>
								<td>${item.orderCode}</td>	
								<td>${item.mobile}</td>	
								<td>${item.title}</td>
								<td>${item.orderPrice}</td> 
								<td>${item.moneyCount}</td> 
								<td>${item.payType}</td> 
								<td>${item.jsStatusVal}</td> 
								<td>${item.jsTimeHandle}</td> 
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
<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr>
		<td><%=result.elements[i].orderCode%></td>	
		<td><%=result.elements[i].mobile%></td>	
		<td><%=result.elements[i].title%></td>
		<td><%=result.elements[i].orderPrice%></td> 
		<td><%=result.elements[i].moneyCount%></td> 
		<td><%=result.elements[i].payType%></td> 
		<td><%=result.elements[i].jsStatusVal%></td> 
		<td><%=result.elements[i].jsTimeHandle%></td> 
    </tr>	
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/material/finance/pagination.do',
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

</html>