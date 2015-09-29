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
		.fieldStyle{
		  font-size:18px;
         }		
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
				<input type="hidden" name="status" value="${status}">
				<ul>					
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
                           <!--  <span class="cur"><a href="${base}/appointmentPush/supplierPushIndex.xhtml?type=${type}">全部</a></span> -->
	                        <span ><a href="${base}/appointmentPush/supplierPushIndex.xhtml?status=0">待抢单</a></span> 
	                        <span ><a href="${base}/appointmentPush/supplierPushIndex.xhtml?status=10">已抢</a></span>
	                       <!-- <span ><a href="${base}/appointmentPush/supplierPushIndex.xhtml?random=1&status=11">已取消</a></span> -->
	                        <span ><a href="${base}/appointmentPush/supplierPushIndex.xhtml?status=20">预约量房</a></span>
	                        <span ><a href="${base}/appointmentPush/supplierPushIndex.xhtml?status=30">已量房</a></span>
	                        <span ><a href="${base}/appointmentPush/supplierPushIndex.xhtml?status=40">已签约</a></span>
	                        <span ><a href="${base}/appointmentPush/supplierPushIndex.xhtml?status=50">已付款</a></span>
                    </div>
                </div>
            </div>
			<table class="format">
				<thead>
					<th width="10%">面积(m²)</th>
					<th width="10%">方式</th>
					<th width="10%">小区</th>
					<th width="10%">预约用户</th>
					<th width="10%">状态</th>
					<th width="10%">量房时间</th>
					<th width="10%">应单时间</th>
					<th width="10%">取消原因</th>
					<th width="15%">操作</th>				
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td>${item.space}</td>
						<td>${item.method}</td>
						<td>${item.community}</td>
						<td>${item.nickName}</td>
						<td>${item.statusName}</td>
						<td>${item.amountTimeHandle}</td>	
						<td>${item.singleTimeHandle}</td>
						<td>${item.reason}</td>													
			            <td>
				             [#if item.status == 0] 
				               <span class="btn qingdan" id="${item.id}" rel=["${item.aid}"]>抢单</span>
				             [/#if]
				             [#if item.status == 30 && item.descnt = 0] 
				               <a class="obtn" href="${base}/goods/design/detail.xhtml?pid=${item.id}">上传方案</a>
				             [/#if]
				             [#if item.status == 20] 
				               <a class="obtn" onclick="return confirm('确认已经量房?')" href="${base}/appointmentPush/already.xhtml?id=${item.id}&sate=30&status=${status}&type=${type}">已量房</a>
				             [/#if]
				             [#if item.random != 1] 
				               <span class="btn modify" rel=["${item.id}"]>结束此单</span>
				             [/#if]
			            </td>
					</tr>
					[/#list]
				</tbody>
		</table>
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
					<td><%=result.elements[i].space%></td>
					<td><%=result.elements[i].method%></td>
					<td><%=result.elements[i].community%></td>
					<td><%=result.elements[i].nickName%></td>
					<td><%=result.elements[i].statusName%></td>
					<td><%=result.elements[i].amountTimeHandle%></td>	
					<td><%=result.elements[i].singleTimeHandle%></td>
					<td><%=result.elements[i].reason%></td>										
				    <td>
				     
					      <% if(result.elements[i].status == 0){ %>
					      	 <span class="btn qingdan" id="<%=result.elements[i].id%>" rel=["<%=result.elements[i].aid%>"]>抢单</span>
					      <%}%>
					      <% if(result.elements[i].status == 30 && result.elements[i].descnt == 0){ %>
					      	 <a class="obtn" href="${base}/goods/design/detail.xhtml?pid=<%=result.elements[i].id%>">上传方案</a>
					      <%}%>
					      <% if(result.elements[i].status == 20){ %>
					      	 <a class="obtn" href="${base}/appointmentPush/already.xhtml?id=<%=result.elements[i].id%>&sate=30&status=${status}&type=${type}">已量房</a>
					      <%}%>
					       <% if(result.elements[i].status != 11){ %>
					      	 <span class="btn modify" rel=["<%=result.elements[i].id%>"]>结束此单</span>
					      <%}%>
				     
			        </td>
				</tr>	
			<%}%>
			</script>
			<script>
				$(function(){
					$('.ui-paging').page({
						url:'${base}/appointmentPush/supplierPushPagination.do?status=${status}',
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
					    case "0":$('.pageTab span:eq(0)').addClass('cur');break;
						case "10":$('.pageTab span:eq(1)').addClass('cur');break;
						//case "11":$('.pageTab span:eq(2)').addClass('cur');break;
						case "20":$('.pageTab span:eq(2)').addClass('cur');break;
						case "30":$('.pageTab span:eq(3)').addClass('cur');break;
						case "40":$('.pageTab span:eq(4)').addClass('cur');break;
						case "50":$('.pageTab span:eq(5)').addClass('cur');break;
						default:$('.pageTab span:eq(0)').addClass('cur');break;
					}
					
					 $('body').delegate(".qingdan","click",function(){
				        var $this=$(this),data=$.parseJSON($this.attr('rel'));
				        var id = $(this).attr("id");
				        $.get("${base}/appointment/getAppointment.xhtml",{"id":data[0]},function(ret){
				        	addMask();
					        $('body').append($('#qingdan').html());
					        $(".ui-dialog .obtn").attr("href", $(".ui-dialog .obtn").attr("href") + id);
					        $(".ui-dialog .community").html(ret.community);
					        $(".ui-dialog .space").html(ret.space + "m²");
					        $(".ui-dialog .budget").html(ret.budget);
					        $(".ui-dialog .address").html(ret.address);
					        $(".ui-dialog .dizhi").attr("href","http://map.baidu.com/?newmap=1&ie=utf-8&s=s%26wd%3D"+ret.address);
					        center($('.ui-dialog'));
				        },"JSON");
				    });
					
					
				});	
				
				function cancel(el){
					$(el).parents("form").submit();
				}
				
			</script>
			
			<script type="text/html" id="qingdan">
			<div class="ui-dialog" style="width:400px;">
				<h2><span class="close"></span></h2>
					<input type="hidden" class='id' name="id" value="" />
					<ul class="fieldStyle">
						<li>
							<strong>面积：</strong>
							<lable class="space"></lable>
						</li>
						<li>
							<strong>预算：</strong>
							<lable class="budget"></lable>
						</li>
						<li>
							<strong>方式：</strong>
							<lable class="zhuangXiu"></lable>
						</li>
						<li>
							<strong>小区：</strong>
							<lable class="community"></lable>
						</li>
						<li>
							<strong>地址：</strong>
							<a style="color:blue;text-decoration: underline;" target="new tab" class='dizhi' href=""><lable class="address"></lable></a>
							<font color="red">(←点击进入地图查看)</font>
						</li>
						<li>&nbsp;</li>
						<li class="btn">
							<a class="obtn" href="${base}/appointmentPush/response.xhtml?id=">抢单</a>
						</li>
					</ul>
			</div>
		</script>
			
			<script type="text/html" id="pannel">
			<div class="ui-dialog addManager" style="width:400px;">
				<h2><span class="close"></span><strong>填写取消预约单原因</strong></h2>
				<form id="send" method="post" action="${base}/appointmentPush/cancel.xhtml?status=${status}">
					<input type="hidden" name="id" value="" />
					<ul class="fieldStyle">
						<li>
							<textarea rows="4" cols="53" class="text" name="cause" placeholder="取消预约单原因"></textarea>
						</li>
						<li>&nbsp;</li>
						<li class="btn">
							<span class="btn" onclick="cancel(this)">提交保存</span>
						</li>
					</ul>
				</form>
				</div>
			</script>
		
		</body>		
	</html>