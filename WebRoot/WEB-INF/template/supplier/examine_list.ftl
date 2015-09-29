<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>商家审核列表</title>
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
		
			<table class="format">
				<thead>
					<th width="5%">商户Id</th>
					<th width="15%">商户名称</th>
					<th width="10%">联系电话</th>
					<th width="10%">营业类型</th>
					<th width="10%">商家类型</th>
					<th width="10%">店员提成比例</th>
					<th width="10%">注册时间</th>
					<th width="10%">认证状态</th>
					<th width="15%">操作</th>				
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td>${item.id}</td>
						<td>${item.spName}</td>
						<td>${item.contactMobile}</td>
						<td>${item.typeVal}</td>
						<td>${item.levelVal}</td>
						<td>${item.gainRateName}</td>
						<td>${item.createTimeHandle}</td>	
						<td>${item.proxyStatusVal}</td>	
						<td>
						  <span class="btn handle" onClick="modify('.handle')" rel=["${item.id}","${item.spName}","${item.contactMobile}","${item.level}","${item.gainRate}"]>审核</span>						
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
<link rel="stylesheet" type="text/css" href="${base}/styles/admin-list.css">
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>

<script type="text/html" id="pannel">
<div class="ui-dialog addManager">
	<h2><span class="close"></span><strong>商户帐号审核</strong></h2>
	<form id="send">
		<input id="spId" type="hidden"/>
		<ul>
			<li><strong>商家名称：</strong> <input type="text" class="text spName" disabled></li>
			<li><strong>手机号码：</strong> <input type="text" class="text mobile" disabled></li>
			<span style="margin:50px;font-size:14px;"><strong>店铺类型：</strong><input type="radio" class="level1" name="level" value="1"/>普通商家<input type="radio" class="level2" name="level" value="2"/>VIP商家 </span>
			<li style="margin-top:15px;"><strong>店员提成比例：</strong> <input type="text" class="text gainRate" placeholder="店员提成比例"></li>
			<li class="btn"><span class="btn" onClick="proxy(-1)">拒绝</span><span class="btn" onClick="proxy(1)">通过</span></li>
		</ul>
	</form>
</div>	
</script>

<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr>
	<td><%=result.elements[i].id%></td>
	<td column="spName"><%=result.elements[i].spName%></td>
	<td column="contactUser"><%=result.elements[i].contactMobile%></td>
	<td column="spName"><%=result.elements[i].typeVal%></td>
	<td column="spName"><%=result.elements[i].levelVal%></td>
	<td column="gainRateName"><%=result.elements[i].gainRateName%></td>
	<td column="createTime"><%=result.elements[i].createTimeHandle%></td>
	<td column="createTime"><%=result.elements[i].proxyStatusVal%></td>
	<td>
	 <span class="btn modify" rel=["<%=result.elements[i].id%>","<%=result.elements[i].spName%>","<%=result.elements[i].contactMobile%>","<%=result.elements[i].level%>","<%=result.elements[i].gainRate%>"]>审核</span>				
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
   function modify(name){
    $('body').delegate(name,'click',function(){
        var $this=$(this),data=$.parseJSON($this.attr('rel'));
        addMask();
        $('body').append($('#pannel').html());
        $('.ui-dialog').data('act','modify');
        $('#spId').val(data[0]);
        $('.spName').val(data[1]);
        $('.mobile').val(data[2]);
        if(data[3]==1){
           $(".level1").attr("checked",true);
        }
        if(data[3]==2){
            $(".level2").attr("checked",true);
        }
        $('.gainRate').val(data[4]);
        center($('.ui-dialog'));
    });
}

  function proxy(status){
     var spId=$("#spId").val();
     var level=$("input[name='level']:checked").val();
     var gainRate=$(".gainRate").val()/100;
     $.post("${base}/supplier/proxyOneSupplier.do",{level:level,spId:spId,proxyStatus:status,gainRate:gainRate},function(rel){
        justTip(rel.msg);
        if(rel.ret==0){
            location.reload();
        }
     })
  }
</script>
</html>