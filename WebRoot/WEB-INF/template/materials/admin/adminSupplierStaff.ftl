<!DOCTYPE HTML>
<html>
<head>
<title>商家员工审核</title>
<meta charset="utf-8">
[#include "/common/res.ftl"]
	<script type="text/javascript" src="${base}/scripts/form.js"></script>
	<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${base}/styles/admin-list.css">
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
						    <input type="hidden" name="status" value="${status}">
							<ul>					
								<li>
								    <span class="sp"><strong>分店名称：</strong><input type="text" class="text" name="branchName" id=""></span>						
									<span class="sp"><strong>手机号码：</strong><input type="text" class="text" name="mobile" id=""></span>
								</li>
								<li>
								    <span class="sp"><strong>员工姓名：</strong><input type="text" class="text" name="nickname" id=""></span>						
								    <span class="sp"><strong>所属主管：</strong><input type="text" class="text" name="leaderName" id=""></span>						
								    <span class="btn search">查 询</span>
								</li>
							</ul>
						</form>
					</div>
				
					 <div class="page_tab_box">
		                <div class="page_tab">
		                    <div class="pageTab">
		                      <span class="cur"><a href="${base}/admin/supplierStaff/index.xhtml?pid=${pid}&mid=${mid}">全部</a></span>
		                      <span class="cur"><a href="${base}/admin/supplierStaff/index.xhtml?pid=${pid}&mid=${mid}&status=0">待审核</a></span>
		                      <span class="cur"><a href="${base}/admin/supplierStaff/index.xhtml?pid=${pid}&mid=${mid}&status=1">审核通过</a></span>
		                      <span class="cur"><a href="${base}/admin/supplierStaff/index.xhtml?pid=${pid}&mid=${mid}&status=2">审核未通过</a></span>
		                    </div>
		                </div>
	                </div>
					<table class="format">
						<thead>
							<th width="5%">编号</th>
							<th width="15%">手机号</th>
							<th width="10%">姓名</th>
							<th width="10%">所属商家</th>
							<th width="10%">所属门店</th>
							<th width="10%">所属主管</th>
							<th width="5%">提成比例</th>
							<th width="10%">总提成</th>
							<th width="5%">状态</th>
							<th width="10%">操作</th>			
						</thead>
						<tbody>
							[#list pagination.dataList as item]
								<tr>
									<td>${item.id}</td>
									<td>${item.mobile}</td>
									<td>${item.name}</td>
									<td>${item.spName}</td>
									<td>${item.branchName}</td>
									<td>${item.leaderName}</td>
									<td>${item.gainRate}</td>
									<td>${item.totalSttleMoney}</td>
									<td>${item.statusVal}</td>
									<td><span class="btn modify" rel=["${item.id}","${item.name}","${item.spName}","${item.branchName}","${item.leaderName}","${item.bankAccount}","${item.bankName}","${item.accountName}","${item.gainRate}"]>审核</span></td>
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

<script type="text/html" id="pannel">
<div class="ui-dialog addManager">
	<h2><span class="close"></span><strong>审核商家员工</strong></h2>
	<form id="send">
	<input id="staffId" type="hidden"/>
	<ul>
		<li><strong>姓名：</strong> <input type="text" class="text" disabled></li>
		<li><strong>手机号：</strong> <input type="text" class="text" disabled></li>
		<li><strong>所属商家：</strong> <input type="text" class="text" disabled></li>
		<li><strong>所属门店：</strong> <input type="text" class="text" disabled></li>
		<li><strong>所属主管：</strong> <input type="text" class="text" disabled></li>
		<li><strong>银行卡帐号：</strong> <input type="text" class="text" disabled></li>
		<li><strong>开户银行：</strong> <input type="text" class="text" disabled></li>
		<li><strong>开户人姓名：</strong> <input type="text" class="text" disabled></li>
		<li><strong>提成比例：</strong> <input type="text" class="text gainRate" ></li>
		<li class="btn"><span class="btn" onClick="noPass()">拒绝</span><span class="btn" onClick="pass()">通过</span></li>
	</ul>
	</form>
</div>	
</script>

<script>
  function pass(){
     var gainRate=$(".gainRate").val()/100;
     var staffId=$("#staffId").val();
     $.post("${base}/admin/supplierStaff/peruseOne.do",{gainRate:gainRate,staffId:staffId,status:1},function(rel){
       justTip(rel.msg);
       if(rel.ret==0){
          location.reload();
       }
     });     
  }
  
  function noPass(){
     var gainRate=$(".gainRate").val()/100;
     var staffId=$("#staffId").val();
     $.post("${base}/admin/supplierStaff/peruseOne.do",{gainRate:gainRate,staffId:staffId,status:2},function(rel){
       justTip(rel.msg);
       if(rel.ret==0){
          location.reload();
       }
     });     
  }
</script>

<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr>
		<td><%=result.elements[i].id%></td>
		<td><%=result.elements[i].mobile%></td>
		<td><%=result.elements[i].name%></td>
		<td><%=result.elements[i].spName%></td>
		<td><%=result.elements[i].branchName%></td>
		<td><%=result.elements[i].leaderName%></td>
		<td><%=result.elements[i].gainRate%></td>
		<td><%=result.elements[i].totalSttleMoney%></td>
		<td><%=result.elements[i].statusVal%></td>
		<td><span class="btn modify" rel=["<%=result.elements[i].id%>","<%=result.elements[i].name%>","<%=result.elements[i].spName%>","<%=result.elements[i].branchName%>","<%=result.elements[i].leaderName%>","<%=result.elements[i].bankAccount%>","<%=result.elements[i].bankName%>","<%=result.elements[i].accountName%>","<%=result.elements[i].gainRate%>"]>审核</span></td>
    </tr>	
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/admin/supplierStaff/pagination.do',
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
		case "1":$('.pageTab span:eq(2)').addClass('cur');break;
		case "2":$('.pageTab span:eq(3)').addClass('cur');break;
		default:$('.pageTab span:eq(0)').addClass('cur');break;
	}
});	
</script>

</html>