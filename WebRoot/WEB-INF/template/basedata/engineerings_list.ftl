<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>工程单管理</title>
[#include "/common/res.ftl"]
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="${base}/scripts/form.js"></script>
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
				<form id="search">
				<input type="hidden" name="status" value="${status}">
				<ul>					
					<li>
						<span class="sp"><strong>业主：</strong><input type="text" class="text" name="nickName" id=""></span>
						<span class="sp"><strong class="auto">装修公司：</strong><input type="text" class="text" name="spName" id=""></span>
					</li>
					
					<li>
						<span class="sp"><strong>创建时间：</strong>
						   <input type="text" class="text Wdate" name="createBegin" id="" onClick="WdatePicker()" readOnly>
						</span>
						<span class="sp">
						   <input type="text" class="text Wdate" name="createEnd" id="" onClick="WdatePicker()" readOnly>
						</span>						
						<span class="btn search">查 询</span>
					</li>
				</ul>
				</form>
			</div>
			 
			 <div class="page_tab_box">
                <div class="page_tab">
                    <div class="pageTab">
                        <span class="cur"><a href="${base}/engineerings/index.xhtml?pid=${pid}&mid=${mid}">全部预约</a></span>
                        <span ><a href="${base}/engineerings/index.xhtml?pid=${pid}&mid=${mid}&status=10">待开工</a></span>
                        <span ><a href="${base}/engineerings/index.xhtml?pid=${pid}&mid=${mid}&status=20">开工</a></span>
                        <span ><a href="${base}/engineerings/index.xhtml?pid=${pid}&mid=${mid}&status=30">水电</a></span>
                        <span ><a href="${base}/engineerings/index.xhtml?pid=${pid}&mid=${mid}&status=40">瓦木</a></span>
                        <span ><a href="${base}/engineerings/index.xhtml?pid=${pid}&mid=${mid}&status=50">竣工</a></span>
                        <span ><a href="${base}/engineerings/index.xhtml?pid=${pid}&mid=${mid}&status=100">无坐标工程单</a></span>                                          
                    </div>
                </div>
            </div>
			 
			<table class="format">
				<thead>
					<th width="10%">工程单编号</th>
					<th width="20%">小区名称</th>
					<th width="15%">业主</th>
					<th width="15%">装修公司</th>
					<th width="15%">监理</th>	
					<th width="10%">工程阶段</th>
					<th width="10%">操作</th>			
				</thead>
				<tbody>
					[#list pagination.dataList as item]
					<tr>
						<td>${item.id}</td>
						<td>${item.community}</td>
						<td>${item.nickName}</td>
						<td>${item.spName}</td>
						<td>${item.jlName}</td>
						<td>${item.stageVal}</td>
						<td>
						   <span class="btn"><a href="detail.xhtml?pid=${pid}&mid=${mid}&id=${item.id}&req=engineerings_detail">详情</a></span>
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
<script type="text/html" id="list">
	<%for(i=0;i<result.elements.length;i++){%>
		<tr>
			<td><%=result.elements[i].id%></td>
			<td><%=result.elements[i].community%></td>
			<td><%=result.elements[i].nickName%></td>
			<td><%=result.elements[i].spName%></td>
			<td><%=result.elements[i].jlName%></td>
			<td><%=result.elements[i].stageVal%></td>
			<td>
			 <span class="btn"><a href="detail.xhtml?pid=${pid}&mid=${mid}&id=<%=result.elements[i].id%>&req=engineerings_detail">详情</a></span>
			</td>													    
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
		case "100":$('.pageTab span:eq(6)').addClass('cur');break;		
		default:$('.pageTab span:eq(0)').addClass('cur');break;
	}
});	
</script>
</body>
</html>