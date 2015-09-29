<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<title>工程单</title>
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
		</style>
	</head>
	<body>
	<!-- header -->
		[#include "/common/supervisorHead.ftl"]
		<!-- wrap -->
		<div class="wrap index bc clr">
			<div class="conWrap fl">
				<h2 class="hd mb10">
					<strong><a href="#">监理后台</a></strong> &gt;
					<span id="sel1" data-role="首页">工程单列表</span>
				</h2>
		<div class="conBox">
			<!--内容放这里 start-->
			
					<form id="search">
						<input type="hidden" name="jlid" value="${jlid}">
						<input type="hidden" name="status" value="${status}">
					</form>
					
			     <div class="page_tab_box">
                <div class="page_tab">
                    <div class="pageTab">
                        <span class="cur"><a href="${base}/engineerings/indexJL.xhtml?pid=${pid}&mid=${mid}">全部</a></span>
                        <span ><a href="${base}/engineerings/indexJL.xhtml?pid=${pid}&mid=${mid}&status=10">待开工</a></span>
                        <span ><a href="${base}/engineerings/indexJL.xhtml?pid=${pid}&mid=${mid}&status=20">开工</a></span>
                        <span ><a href="${base}/engineerings/indexJL.xhtml?pid=${pid}&mid=${mid}&status=30">水电</a></span>
                        <span ><a href="${base}/engineerings/indexJL.xhtml?pid=${pid}&mid=${mid}&status=40">瓦木</a></span>
                        <span ><a href="${base}/engineerings/indexJL.xhtml?pid=${pid}&mid=${mid}&status=50">竣工</a></span>                                          
                    </div>
                </div>
            </div>
			 
			<table class="format">
				<thead>
					<th width="5%">工程单编号</th>
					<th width="10%">小区名称</th>
					<th width="10%">预约用户</th>
					<th width="15%">装修公司</th>
					<th width="5%">阶段</th>
					<th width="15%">开工日期</th>
					<th width="15%">竣工日期</th>
					<th width="15%">创建时间</th>
					<th width="5%">创建人</th>		
					<th width="5%">详情</th>			
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td>${item.id}</td>
						<td>${item.community}</td>
						<td>${item.nickName}</td>
						<td>${item.spName}</td>
						<td>${item.stageVal}</td>
						<td>${item.startTimeHandle}</td>
						<td>${item.endTimeHandle}</td>
						<td>${item.createTimeHandle}</td>	
						<td>${item.creauser}</td>	
						<td>
						 <span class="btn"><a href="detail.xhtml?id=${item.id}&req=engineeringsJL_detail">详情</a></span>
						</td>		           
					</tr>
					[/#list]
				</tbody>
		</table>
		
		         [#include "/common/pagination.ftl"]
			<!--内容放这里 end-->
		 </div>
	</div>
			[#include "/common/supervisorMenu.ftl"]
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
			<td><%=result.elements[i].community%></td>
			<td><%=result.elements[i].nickName%></td>
			<td><%=result.elements[i].spName%></td>
			<td><%=result.elements[i].stageVal%></td>
			<td><%=result.elements[i].startTimeHandle%></td>
			<td><%=result.elements[i].endTimeHandle%></td>
			<td><%=result.elements[i].createTimeHandle%></td>	
			<th>
			 <span class="btn"><a href="detail.xhtml?id=<%=result.elements[i].id%>&req=engineeringsSU_detail">详情</a></span>
			</th>													    
		</tr>	
	<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/engineerings/pagination.do',
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
		case "10":$('.pageTab span:eq(1)').addClass('cur');break;
		case "20":$('.pageTab span:eq(2)').addClass('cur');break;
		case "30":$('.pageTab span:eq(3)').addClass('cur');break;
		case "40":$('.pageTab span:eq(4)').addClass('cur');break;
		case "50":$('.pageTab span:eq(5)').addClass('cur');break;		
		default:$('.pageTab span:eq(0)').addClass('cur');break;
	}
});	
</script>
	</html>