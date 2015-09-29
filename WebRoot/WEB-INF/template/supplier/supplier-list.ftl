<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>商家列表</title>
[#include "/common/res.ftl"]
</head>

<body>
<!-- header -->
[#include "/common/head.ftl"]
<!-- wrap -->
<div class="wrap index bc clr">
	<div class="conWrap fl">
		[#include "/common/nav.ftl"] 
		<div class="conBox">
		 <div class="tools white border mb10 sp10 clr">
				<form id="search" action="">
				<ul>
					<li>
						<strong><b class="red">*</b>公司所在地：</strong>
						<div class="select province mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p rel="${base}/region/childrenRegion.do">
							<a value="">--请选择--</a>
                            [#list regionList as item]
                                <a value="${item.regionid}">${item.regionname}</a>
                             [/#list]
							</p>					
							<input type="hidden" name="pid" value="">
						</div>
						<div class="select city mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p rel="${base}/region/childrenRegion.do">
								
							</p>
							<input type="hidden" name="pid"  value="">
						</div>
						<div class="select area mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p>
								
							</p>
							<input type="hidden" name="regionId" class="regionId" value="">
						</div>
					</li>
					
					<li>
						<strong>商家类型：</strong>
						<div class="select mr10">
							<i class="new">&#xf107;</i><span>--请选择--</span>
							<p>
                                <a value="">--请选择--</a>
                                <a value="1">装修公司</a>
                                <a value="2">建材商</a>
                                <a value="3">工长</a>
                                <a value="5">监理</a>
							</p>					
							<input type="hidden" name="type" value="">
						</div>
						<span class="sp"><strong class="auto">商家名称：</strong><input type="text"  class="text" name="supplierName"/></span>
					</li>
					<li>
					    <strong>法人：</strong><input type="text" class="text" name="legalPerson"/></span>
					    <span class="btn search">查 询</span>
					 </li>
				</ul>
				</form>
			</div>
		<a class="obtn" href="${base}/supplier/add.xhtml?mid=223&pid=266" style="margin:5px;">添加</a>
		
			<table class="format">
				<thead>
					<th width="5%">商户Id</th>
					<th width="15%">商户名称</th>
					<th width="10%">类型</th>
					<th width="10%">店员提成比例</th>
					<th width="10%">法人</th>
					<th width="10%">联系电话</th>
					<th width="10%">注册时间</th>
					<!--<th width="10%">签约开始时间</th>
					<th width="10%">签约结束时间</th>-->
					<!--<th width="5%">状态</th>-->
					<th width="15%">操作</th>				
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td>${item.id}</td>
						<td>${item.spName}</td>
						<td>${item.typeVal}</td>
						<td>${item.gainRateName}</td>
						<td>${item.legalPerson}</td>
						<td>${item.contactMobile}</td>
						<td>${item.createTimeHandle}</td>	
						<!--<td>${item.startDateHandle}</td>
						<td>${item.endDateHandle}</td>-->
						<!--<td>${item.statusName}</td>		-->									
						<td>
							<!--<a class="obtn" href="${base}/supplier/detail.xhtml?id=${item.id}&mid=${mid}&pid=${pid}">详细</a>-->
							<a class="obtn" href="${base}/supplier/edit.xhtml?id=${item.id}&mid=${mid}&pid=${pid}">修改</a>
							<a class="obtn" href="${base}/supplier/in/ins.xhtml?id=${item.id}&mid=${mid}&pid=${pid}">子帐号</a>
							<span class="btn" onClick="deleteSuInfo(${item.id})">删除</span>
						</td>
					</tr>
					[/#list]
				</tbody>
		</table>
		[#include "/common/pagination.ftl"]
	</div>
	</div>
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]
</body>
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr menuid="<%=result.elements[i].id%>">
	<td><%=result.elements[i].id%></td>
	<td column="spName"><%=result.elements[i].spName%></td>
	<td column="spName"><%=result.elements[i].typeVal%></td>
	<td column="gainRateName"><%=result.elements[i].gainRateName%></td>
	<td column="gainRateName"><%=result.elements[i].legalPerson%></td>
	<td column="contactUser"><%=result.elements[i].contactMobile%></td>
	<td column="createTime"><%=result.elements[i].createTimeHandle%></td>
	<!--<td column="statusName"><%=result.elements[i].statusName%></td>-->
	<td>
	   <!--<a class="obtn" href="${base}/supplier/detail.xhtml?pid=${pid}&mid=${mid}&id=<%=result.elements[i].id%>">详细</a>-->
	   <a class="obtn" href="${base}/supplier/edit.xhtml?pid=${pid}&mid=${mid}&id=<%=result.elements[i].id%>">修改</a>
	   <a class="obtn" href="${base}/supplier/in/ins.xhtml?id=<%=result.elements[i].id%>&mid=${mid}&pid=${pid}">子帐号</a>
	   <span class="btn" onClick="deleteSuInfo(<%=result.elements[i].id%>)">删除</span>
    </td>
</tr>	
<%}%>
</script>
<script type="text/javascript">
$(function(){
	$('.ui-paging').page({
		url:'${base}/supplier/pagination.do',
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

<script>
  function  deleteSuInfo(param){
    var TF=confirm("确定删除商户帐号及其信息吗?");
    if(TF){
       $.post("${base}/supplier/deleteSuInfo.do",{spId:param},function(rel){
          justTip(rel.msg);
          if(rel.ret==0){
             location.reload();
          }
       });
    }
  }
</script>
</html>