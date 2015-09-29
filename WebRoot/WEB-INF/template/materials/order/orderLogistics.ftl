<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<title>订单管理</title>
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
	<script type="text/javascript" src="${base}/scripts/form.js"></script>
	<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
	<script>
	  function fahuo(param){
	     var TF=confirm("确定发货吗?");
	     if(TF){
	         $.post("${base}/materials/logistics/updateZcOrderExpressToOne.do",{orderId:param},function(rel){
	            justTip(rel.msg);
	            if(rel.ret==0){
	               location.reload();
	            }
	         })
	     }
	  }
	</script>
	</head>
	<body>
	<!-- header -->
		[#include "/materials/common/head.ftl"]
		<!-- wrap -->
		<div class="wrap index bc clr">
			<div class="conWrap fl">
				 <div class="conBox">
					<!--内容放这里 start-->
					<div class="tools white border mb10 sp10 clr" style="margin-top:10px;">
						<form id="search" action="">
							<ul>					
								<li>
									<span class="sp"><strong >用户手机:</strong><input type="text" class="text" name="phone" ></span>
									<span class="btn search">搜索</span>
								</li>					
							</ul>
						</form>
					</div>	
					
					<table class="format">
						<thead>
							<th width="7%">推荐人</th>
							<th width="10%">推荐人手机号</th>
							<th width="10%">用户手机</th>
							<th width="18%">商品名称/扫码订单</th>
							<th width="10%">商品价格(单位:元)</th>
							<th width="8%">支付金额(单位:元)</th>	
							<th width="10%">订单状态</th>	
							<th width="7%">物流状态</th>	
							<th width="13%">创建时间</th>	
							<th width="5%">操作</th>	
						</thead>
						<tbody>
							[#list pagination.dataList as item]
							<tr>
								<td>${item.recommendName}</td>	
								<td>${item.recommendMobile}</td>	
								<td>${item.mobile}</td>	
								<td>${item.itemTitleShowVal}</td>
								<td>${item.itemPriceShowVal}</td> 
								<td>${item.payMoneyVal}</td> 
								<td>${item.orderStatus}(${item.payTypeVal})</td> 
								<td>${item.expressStateVal}</td> 
								<td>${item.createTimeHandle}</td> 
								<td>
								     [#if item.logisticsHandle==true]
								       <span class="btn" onClick="fahuo(${item.id})">发货</span>
								       [#else]
								       <span  style="padding: 4px 12px; background: #cccccc; border-radius: 3px;">发货</span>
								     [/#if]
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
<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr>
		<td><%=result.elements[i].recommendName%></td>	
		<td><%=result.elements[i].recommendMobile%></td>	
		<td><%=result.elements[i].mobile%></td>	
		<td><%=result.elements[i].itemTitleShowVal%></td>
		<td><%=result.elements[i].itemPriceShowVal%></td> 
		<td><%=result.elements[i].payMoneyVal%></td> 
		
		<td><%=result.elements[i].orderStatus%>(<%=result.elements[i].payTypeVal%>)</td> 
		<td><%=result.elements[i].expressStateVal%></td> 
		<td><%=result.elements[i].createTimeHandle%></td> 
		<td>
		   <% if(result.elements[i].logisticsHandle==true){%> 
		       <span class="btn"  onClick="fahuo(<%=result.elements[i].id%>)">发货</span>
		   <%}else{%>
		          <span  style="padding: 4px 12px; background: #cccccc; border-radius: 3px;">发货</span>
		   <%}%>
		</td>
    </tr>	
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/materials/logistics/pagination.do',
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