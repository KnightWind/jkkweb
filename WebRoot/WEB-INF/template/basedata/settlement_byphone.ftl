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
					 <div class="add">
				    	<span class="btn" onclick="history.go(-1)">返回</span>
				    </div>
				  	<table class="format">
						<thead>
							<th width="10%">手机号</th>
							<th width="10%">推荐人姓名</th>
							<th width="10%">本期提成</th>
							<th width="12%">本期起始日期</th>
							<th width="10%">本期结算截止日</th>
							<th width="10%">操作</th>
						</thead>
						<tbody>
							[#list pagination.dataList as item]
							<tr>
								<td>${item.mobile}</td>	
								<td>${item.mName}</td>	
								<td>${item.currentBalance}</td>	
								<td>${item.statBeginTimeString}——${item.statEndTimeString}</td>	
								<td>${item.settleTimeString}</td>	
								<td>
									<a onclick="return confirm('确定结算？')" class="obtn send" href='${base}/admin/settlement/settlementById.xhtml?id=${item.id}&phone=${phone}&mid=${mid}&pid=${pid}&op=1&role=${item.role}'>结算</a>
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
		<td><%=result.elements[i].orderCount%></td>	
		<td><%=result.elements[i].orderMoney%></td>	
		<td><%=result.elements[i].pushMoney%></td>	
		<td><%=result.elements[i].pushMoneyCount%></td>	
		<td><%=result.elements[i].unCalePushMoney%></td>	
		<td>
			<a onclick="return confirm('确定结算？')" class="obtn send" href='${base}/admin/settlement/settlementById.xhtml?id=<%=result.elements[i].id%>&phone=${phone}&mid=${mid}&pid=${pid}&op=1&role=<%=result.elements[i].role%>'>结算</a>
		</td>	
    </tr>	
<%}%>

</script>



<script>

$(function(){

	$('.ui-paging').page({
		url:'${base}/admin/settlement/settlementPage.do?role=${role}',
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
});	

    
</script>

</html>