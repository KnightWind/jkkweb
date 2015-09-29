<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<title>预约列表</title>
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
					<span id="sel1" data-role="首页">预约列表</span>
				</h2>
				<div class="conBox">
			<!--内容放这里 start-->
				<div class="tools white border mb10 sp10 clr">
				<form id="search">
				<input type="hidden" name="status" value="${status}">
				<ul>					
					<li>
						<span class="sp"><strong>预约推送编号：</strong><input type="text" class="text" name="id" id=""></span>
						<span class="sp"><strong class="auto">预约单编号：</strong><input type="text" class="text" name="aid" id=""></span>
						<span class="sp"><strong class="auto">预约用户：</strong><input type="text" class="text" name="member" id=""></span>
					</li>
					<li>
						<span class="sp"><strong>创建时间：</strong>
						   <input type="text" class="text Wdate" name="createTimeStart" id="" onClick="WdatePicker()" readOnly>
						</span>
						<span class="sp">
						   <input type="text" class="text Wdate" name="createTimeEnd" id="" onClick="WdatePicker()" readOnly>
						</span>										
					</li>
					<li>
						<span class="sp"><strong>应单时间：</strong>
						   <input type="text" class="text Wdate" name="singleTimeStart" id="" onClick="WdatePicker()" readOnly>
						</span>
						<span class="sp">
						   <input type="text" class="text Wdate" name="singleTimeEnd" id="" onClick="WdatePicker()" readOnly>
						</span>						
					</li>
					<li>
						<span class="sp"><strong>量房时间：</strong>
						   <input type="text" class="text Wdate" name="amountTimeStart" id="" onClick="WdatePicker()" readOnly>
						</span>
						<span class="sp">
						   <input type="text" class="text Wdate" name="amountTimEnd" id="" onClick="WdatePicker()" readOnly>
						</span>						
						<span class="btn search">查 询</span>
					</li>
				</ul>
				</form>
			</div>
			<div class="page_tab_box">
                <div class="page_tab">
                    <div class="pageTab">
                        <span class="cur"><a href="${base}/jlappointmentPush/jlPushIndex.xhtml">全部</a></span>
                        <span ><a href="${base}/jlappointmentPush/jlPushIndex.xhtml?status=0">待应单</a></span>
                        <span ><a href="${base}/jlappointmentPush/jlPushIndex.xhtml?status=10">已应单</a></span>
                        <span ><a href="${base}/jlappointmentPush/jlPushIndex.xhtml?status=11">取消</a></span>
                    </div>
                </div>
            </div>
			<table class="format">
				<thead>
					<th width="5%">推送编号</th>
					<th width="5%">预约单编号</th>
					<th width="15%">推送商家</th>
					<th width="10%">预约用户</th>
					<th width="5%">状态</th>
					<th width="10%">创建时间</th>
					<th width="10%">应单时间</th>
					<th width="10%">量房时间</th>
					<th width="20%">取消原因</th>					
					<th width="10%">操作</th>				
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td>${item.id}</td>
						<td>${item.aid}</td>
						<td>${item.spName}</td>
						<td>${item.nickName}</td>
						<td>${item.statusName}</td>
						<td>${item.createTimeHandle}</td>
						<td>${item.singleTimeHandle}</td>
						<td>${item.amountTimeHandle}</td>	
						<td>${item.reason}</td>															
			            <td>
			             [#if item.status == 0] 
			               <span class="btn del" rel="${base}/jlappointmentPush/response.do?id=${item.id}&flag=true">应单</span>
			             [/#if]
			              [#if item.status != 11] 
			               <span class="btn modify" rel=["${item.id}"]>放弃</span>
			             [/#if]
			            <td>
					</tr>
					[/#list]
				</tbody>
		</table>
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
			
			<script type="text/html" id="list">
			<%for(i=0;i<result.elements.length;i++){%>
				<tr>
					<td><%=result.elements[i].id%></td>
					<td><%=result.elements[i].aid%></td>
					<td><%=result.elements[i].spName%></td>
					<td><%=result.elements[i].nickName%></td>
					<td><%=result.elements[i].statusName%></td>
					<td><%=result.elements[i].createTimeHandle%></td>
					<td><%=result.elements[i].singleTimeHandle%></td>
					<td><%=result.elements[i].amountTimeHandle%></td>	
					<td><%=result.elements[i].reason%></td>											
				    <td>
				      <% if(result.elements[i].status == 0){ %>
				      	 <span class="btn del" rel="${base}/jlappointmentPush/response.do?id=<%=result.elements[i].id%>&flag=true">应单</span>
				      <%}%>
				      <% if(result.elements[i].status != 11){ %>
				      	 <span class="btn modify" rel=["<%=result.elements[i].id%>"]>放弃</span>
				      <%}%>
			        <td>
				</tr>	
			<%}%>
			</script>
			<script>
				$(function(){
					$('.ui-paging').page({
						url:'${base}/jlappointmentPush/jlPushIndexPagination.do',
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
						case "10":$('.pageTab span:eq(2)').addClass('cur');break;
						case "11":$('.pageTab span:eq(3)').addClass('cur');break;
						default:$('.pageTab span:eq(0)').addClass('cur');break;
					}
				});	
				
			</script>
			<script type="text/html" id="pannel">
			<div class="ui-dialog addManager" style="width:400px;">
				<h2><span class="close"></span><strong>填写取消预约单原因</strong></h2>
				<form id="send" method="post" action="${base}/jlappointmentPush/response.do?&flag=false">
					<input type="hidden" name="id" value="" />
					<ul class="fieldStyle">
						<li>
							<textarea rows="4" cols="53" class="text" name="cause" placeholder="取消预约单原因"></textarea>
						</li>
						<li>&nbsp;</li>
						<li class="btn">
							<span class="btn save" rel="add">提交保存</span>
						</li>
					</ul>
				</form>
			</div>
		</script>
		</body>		
	</html>