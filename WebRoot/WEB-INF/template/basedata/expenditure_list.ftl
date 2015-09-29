<!DOCTYPE HTML>
<html>
<head>
<title>支出管理列表</title>
<meta charset="utf-8">
[#include "/common/res.ftl"]
	<script type="text/javascript" src="${base}/scripts/form.js"></script>
	<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
	<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
<style>
	.add{margin-bottom:10px;float:right}
	.formDiv{padding:20px;}
	tr{
		padding-top:10px;
	}
	td{height:55px;}
	.td_left{text-align:right;font-size:15px;font-weight:bold;}
</style>
</head>

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
							<ul>					
								<li>
									<span class="sp"><strong>搜索：</strong><input type="text" class="text" name="name" placeholder="请输入关键字"></span>
									<span class="btn search">查 询</span>
								</li>
							</ul>
						</form>
					</div>	
					<div class="page_tab_box">
		                <div class="page_tab">
		                    <div class="pageTab">
		                        <span><a href="${base}/admin/expenditure/index.xhtml?mid=${mid}&pid=${pid}&status=0">员工</a></span>
		                        <span><a href="${base}/admin/expenditure/memberExpenIndex.xhtml?mid=${mid}&pid=${pid}&status=1">业主</a></span>
		                    </div>
		                </div>
		            </div>
				  	<table class="format">
						<thead>
							<th width="6%">手机号</th>
							<th width="10%">推荐人姓名</th>
							<th width="10%">推荐人类型</th>
							<th width="5%">所属商家</th>
							<th width="10%">所属门店</th>
							<th width="5%">所属主管</th>
							<th width="5%">总交易笔数</th>
							<th width="5%">总交易额</th>
							<th width="7%">提成比例</th>
							<th width="7%">总提成</th>
							<th width="7%">未结算提成</th>
							<th width="7%">操作</th>
						</thead>
						<tbody>
							[#list pagination.dataList as item]
							<tr>
								<td>${item.rphone}</td>	
								<td>${item.rname}</td>	
								<td>员工</td>	
								<td>${item.spName}</td>	
								<td>${item.departStore}</td>	
								<td>${item.departPerson}</td>	
								<td>${item.orderCount}</td>	
								<td>${item.orderMoney}</td>	
								<td>${item.pushMoney * 100}%</td>	
								<td>${item.pushMoneyCount}</td>	
								<td>${item.unCalePushMoney}</td>	
								<td>
									<a class="obtn send" href='${base}/admin/settlement/settlementByPhone.xhtml?phone=${item.rphone}&role=1&mid=${mid}&pid=${pid}'>去结算</a>
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

<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr>
		<td><%=result.elements[i].rphone%></td>	
		<td><%=result.elements[i].rname%></td>	
		<td>员工</td>	
		<td><%=result.elements[i].spName%></td>	
		<td><%=result.elements[i].departStore%></td>	
		<td><%=result.elements[i].departPerson%></td>	
		<td><%=result.elements[i].orderCount%></td>	
		<td><%=result.elements[i].orderMoney%></td>	
		<td><%=result.elements[i].pushMoney * 100%>%</td>	
		<td><%=result.elements[i].pushMoneyCount%></td>	
		<td><%=result.elements[i].unCalePushMoney%></td>	
		<td>
			<a class="obtn send" href='${base}/admin/settlement/settlementByPhone.xhtml?phone=<%=result.elements[i].rphone%>&role=1&mid=${mid}&pid=${pid}'>去结算</a>
		</td>	
    </tr>	
<%}%>

</script>



<script>

$(function(){

	$('.ui-paging').page({
		url:'${base}/admin/expenditure/pagination.do',
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
		case "1":$('.pageTab span:eq(1)').addClass('cur');break;
		default:$('.pageTab span:eq(0)').addClass('cur');break;
	}
	
});	

    
</script>

</html>