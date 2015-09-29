<!DOCTYPE HTML>
<html>
	<head>
			<meta charset="utf-8">
			<title>我的日记</title>
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
					<strong><a href="#">监理后台</a></strong> &gt;
					<span id="sel1" data-role="首页">我的日记</span>
				</h2>
				<div class="conBox">
		    	<!--内容放这里 start-->
							
					 <div class="tools white border mb10 sp10 clr">
						<form id="search" action="">
							<ul>		
								<input type="hidden" name="spid" value="${spid}"/>	
								<li>
									<span class="sp"><strong>标题：</strong>
									   <input type="text" class="text" name="title">
									</span>
								</li>					
								<li>
									 <span class="sp"><strong>创建时间：</strong><input type="text" class="text Wdate" name="createBegin" id="" onClick="WdatePicker()" readOnly></span>
									 <span class="sp"><input type="text" class="text Wdate" name="createEnd" id="" onClick="WdatePicker()" readOnly></span>
								     <span class="btn search">查 询</span>
								</li>
							</ul>
						</form>
					</div>	
							
					<table class="format">
						<thead>				
								<th width="15%">标题</th>
								<th width="10%">装修小区</th>
								<th width="10%">审核状态</th>
								<th width="15%">创建时间</th>
								<th width="15%">审核时间</th>
								<th width="15%">屏蔽时间</th>						
								<th width="15">操作</th>			
						</thead>
						<tbody>
							[#list pagination.dataList as item]
							<tr>			
								<td>${item.title}</td>
								<td>${item.community}</td>
								<td>${item.statusVal}</td>
								<td>${item.createTimeHandle}</td>
								<td>${item.checkTimeHandle}</td>		
								<td>${item.closeTimeHandle}</td>															
								<td>
		                           <span class="btn deleteOne" rel="${item.id}">删除</span>		
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
		<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
		<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
		
		<script>
		   $(function(){
		      $(".format").delegate(".deleteOne","click",function(){
		           var TF=confirm("确定删除吗?");
		           if(TF){
			           var id=$(this).attr("rel");
			           $.post("deleteOneTopic.do",{id:id},function(res){
			               justTip(res.msg);
			               if(res.ret==0){
			                  location.reload();
			               }
			             });
			           }
		      });
		   });
		</script>
		
		<script type="text/html" id="list">
		<%for(i=0;i<result.elements.length;i++){%>
			<tr>
				<td><%=result.elements[i].title%></td>
				<td><%=result.elements[i].community%></td>
				<td><%=result.elements[i].statusVal%></td>
				<td><%=result.elements[i].createTimeHandle%></td>
				<td><%=result.elements[i].checkTimeHandle%></td>		
				<td><%=result.elements[i].closeTimeHandle%></td>		
				<td>		
				    <span class="btn deleteOne" rel="<%=result.elements[i].id%>">删除</span>			
				</td>
			</tr>
		<%}%>
		</script>
		<script>
			$(function(){
				$('.ui-paging').page({
					url:'${base}/jtopic/pagination.do',
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