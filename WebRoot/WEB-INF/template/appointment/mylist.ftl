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
					<a href="/supplier/password" class="logout"><i class="new">&#xf09c;</i> 修改密码</a>
				</p>
			</div>
		</div>
		<!-- wrap -->
		<div class="wrap index bc clr">
			<div class="conWrap fl">
				<h2 class="hd mb10">
					<strong><a href="#">商家后台</a></strong> &gt;
					<span id="sel1" data-role="首页">预约列表</span>
				</h2>
				<div class="conBox">
			<!--内容放这里 start-->
				<div class="tools white border mb10 sp10 clr">
				<form id="search">
				<ul>
					<li>
						<strong>所属区域：</strong>
						<div class="select province mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p rel="${base}/admin/area/sheng.do">
							<a value="">--请选择--</a>
                            [#list lst as l]
                                <a value="${l.areaDomain}">${l.area}</a>
                             [/#list]
							</p>					
							<input type="hidden" name="province" value="">
						</div>
						<div class="select city mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p rel="${base}/admin/area/city.do">
								
							</p>
							<input type="hidden" name="city" value="">
						</div>
						<div class="select area mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p>
								
							</p>
							<input type="hidden" name="area"  value="">
						</div>
					</li>
					<li>
						<span class="sp"><strong>预约编号：</strong><input type="text" class="text" name="id" id=""></span>
						<span class="sp"><strong>预约日期：</strong><input type="text" class="text Wdate" name="start" id="" onClick="WdatePicker()" readOnly></span>
						<span class="sp"><input type="text" class="text Wdate" name="end" id="" onClick="WdatePicker()" readOnly></span>
						<span class="btn search">查 询</span>
					</li>
				</ul>
				</form>
			</div>
			 <div class="page_tab_box">
                <div class="page_tab">
                    <div class="pageTab">
                        <span class="cur"><a href="${base}/appointment/mylist.xhtml?isRead=-1&state=0">全部预约</a></span>
                        <span ><a href="${base}/appointment/mylist.xhtml?isRead=0&state=1">未查看</a></span>
                        <span ><a href="${base}/appointment/mylist.xhtml?isRead=1&state=2">已查看</a></span>
                    </div>
                </div>
            </div>
			<table class="format">
				<thead>
					<th width="7%">预约编号</th>
					<th width="7%">预约类型</th>
					<th width="7%">预约用户</th>
					<th width="7%">手机号</th>
					<th width="7%">城市</th>
					<th width="7%">预约时间</th>
					<th width="7%">下发时间</th>
					<th width="7%">预算费用</th>
					<th width="7%">报价</th>			
					<th width="7%">面积</th>				
					<th width="7%">装修方式</th>				
					<th width="7%">预约状态</th>				
					<th width="7%">是否查看</th>				
					<th width="8%">操作</th>		
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td>${item.id}</td>
						<td>${item.subTypeName}</td>
						<td>${item.user}</td>
						<td>${item.mobile}</td>
						<td>${item.city}</td>
						<td>${item.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
						<td> </td>
						<td>${item.budget}万/m²</td>
						<td>${item.payment}万</td>	
						<td>${item.space}</td>
						<td>
							[#if item.method==1]
							半包
							[/#if]
							[#if item.method==2]
							全包
							[/#if]
						</td>
						<td>${item.statusName}</td>
						<td>
						[#if item.zt==0]
							未查看
						[/#if]
						[#if item.zt!=0]
							已查看
						[/#if]
						</td>											
						<td>
							<span class="btn del" rel="${base}/appointment/">应单</span>
							<a class="obtn" href="${base}/appointment/">退单</a>
						</td>
					</tr>
					[/#list]
				</tbody>
		</table>
		[#include "/common/pagination.ftl"]
			<!--内容放这里 end-->
				</div>
			</div>
			[#include "/common/supplierMenu.ftl"]
			</div>
			<!-- footer -->
			<div class="footer">
				<div class="wrap bc tc">
					<p>北京居天下网络科技有限公司　010-67482366 67477686 &nbsp;&nbsp;地址：北京朝阳区十八里店周家庄村288号融望大酒店6层 居天下版权所有Copyright © 2013-2015 京ICP备15010944</p>
				</div>
			</div>
			
			<script type="text/html" id="list">
			<%for(i=0;i<result.elements.length;i++){%>
				<tr>
					<td><%=result.elements[i].id%></td>
					<td><%=result.elements[i].subTypeName%></td>
					<td><%=result.elements[i].user%></td>
					<td><%=result.elements[i].mobile%></td>
					<td><%=result.elements[i].city%></td>					
					<td><%=result.elements[i].createTime%></td>
					<td> </td>
					<td><%=result.elements[i].budget%>万/m²</td>
					<td><%=result.elements[i].payment%>万</td>
					<td><%=result.elements[i].space%></td>
					<td>
						<%if(result.elements[i].method == 1){%>
							半包
						<%}%>
						<%if(result.elements[i].method == 2){%>
							全包
						<%}%>
					</td>
					<td><%=result.elements[i].statusName%></td>	
					<td>
						<%if(result.elements[i].zt == 0){%>
							未查看
						<%}%>
						<%if(result.elements[i].zt != 0){%>
							已查看
						<%}%>
					</td>
					<td>
						<span class="btn del" rel="${base}/appointment/">应单</span>
						<a class="obtn" href="${base}/appointment/">退单</a>
					</td>
			    </tr>	
			<%}%>
			</script>
			<script>
			$(function(){
				$('.ui-paging').page({
					url:'${base}/appointment/appointmentPage.do',
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
				var status=${status};
				$('.pageTab span.cur').removeClass('cur');
				switch(status){
					case "0":$('.pageTab span:eq(1)').addClass('cur');break;
					case "1":$('.pageTab span:eq(2)').addClass('cur');break;
					case "-1":$('.pageTab span:eq(3)').addClass('cur');break;
					default:$('.pageTab span:eq(0)').addClass('cur');break;
				}
			});	
			</script>
			
	
			<script>
			$(function(){
				$('.ui-paging').page({
					url:'${base}/appointment/appointmentPage.do?isRead=${isRead}',
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
				var status=window.location.href.split('state=')[1];
				$('.pageTab span.cur').removeClass('cur');
				switch(status){
					case "0":$('.pageTab span:eq(0)').addClass('cur');break;
					case "1":$('.pageTab span:eq(1)').addClass('cur');break;
					case "2":$('.pageTab span:eq(2)').addClass('cur');break;
					default:$('.pageTab span:eq(0)').addClass('cur');break;
				}
			});	
			</script>

		</body>		
	</html>