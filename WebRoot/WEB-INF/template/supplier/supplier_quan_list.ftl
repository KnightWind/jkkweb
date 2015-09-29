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
						<span class="sp"><strong>券批次</strong><input type="text" class="text" name="no" id=""></span>
						<span class="btn search">查 询</span>
					</li>
					<li>
						<strong>券类型：</strong><span class="sp radio"><input type="radio" name="type" id="id1" value=""><label for="id1"> 全部 </label><input type="radio" name="type" id="id2" value="1"><label for="id2"> 全品券  </label><input type="radio" name="type" id="id3" value="2"><label for="id3"> 店铺券 </label><input type="radio" name="type" id="id4" value="3"><label for="id1"> oto券 </label></span>
						<strong class="auto">上架状态：</strong><span class="sp radio"><input type="radio" name="zt" id="id4" value=""><label for="id4"> 全部 </label><input type="radio" name="zt" id="id5" value="0"><label for="id5"> 上架  </label><input type="radio" name="zt" id="id6" value="-1"><label for="id6"> 下架  </label></span>				
					</li>
				</ul>
				</form>
			</div>
			 
			<table class="format">
				<thead>					
					<th width="4%">批次</th>
					<th width="4%">类型</th>
					<th width="4%">区域</th>
					<th width="18%">券名</th>
					<th width="10%">券验证码</th>
					<th width="12%">开始时间</th>
					<th width="12%">结束时间</th>
					<th width="4%">上架状态</th>
					<th width="12%">创建日期</th>
					<th width="16%">操作</th>				
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>				
						<td>${item.no}</td>
						<td>${item.type}</td>
						<td>${item.city}</td>
						<td>${item.name}</td>									
						<td>133333*******12</td>
						<td>${item.st}</td>	
						<td>${item.en}</td>
						<td>${item.xiajia}</td>						
						<td>${item.cr}</td>									
						<td>
						[#if item.shangjia==0]
							<span class="btn del" rel="${base}/supplier/quan/oper.do?id=${item.qid}&isopen=${item.shangjia}">下架</span>
						[#else]
						<span class="btn del" rel="${base}/supplier/quan/oper.do?id=${item.qid}&isopen=${item.shangjia}">上架</span>
						[/#if]					
						<span class="btn del" rel="${base}/supplier/quan/remove.do?id=${item.qid}">注销</span>
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
				<tr menuid="<%=result.elements[i].id%>">
         			<td><%=result.elements[i].no%></td>
					<td><%=result.elements[i].type%></td>
					<td><%=result.elements[i].city%></td>
					<td><%=result.elements[i].name%></td>								
					<td>133333*******12</td>
					<td><%=result.elements[i].st%></td>	
					<td><%=result.elements[i].en%></td>
					<td><%=result.elements[i].xiajia%></td>
					<td><%=result.elements[i].cr%></td>
					<td><%if (result.elements[i].shangjia==0){%>
					<span class="btn del" rel="${base}/supplier/quan/oper.do?id=<%=result.elements[i].qid%>&isopen=<%=result.elements[i].shangjia%>">下架</span>
					<%} %>
					<%if (result.elements[i].shangjia==-1){%>
					<span class="btn del" rel="${base}/supplier/quan/oper.do?id=<%=result.elements[i].qid%>&isopen=<%=result.elements[i].shangjia%>">上架</span>
					<%} %></span>
					<span class="btn del" rel="${base}/supplier/quan/remove.do?id=<%=result.elements[i].qid%>">注销</span>
				</tr>	
			<%}%>
			</script>
		<script>
		$(function(){
			$('.ui-paging').page({
				url:'${base}/quanO2o/supplierO2OPagination.do',
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
		})
		</script>
		</body>		
	</html>