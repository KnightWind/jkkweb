<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<title>店员管理</title>
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
		.conBox{
		  margin-top:30px;
		}
		</style>
	<link rel="stylesheet" type="text/css" href="${base}/styles/admin-list.css">
	</head>
	<body>
	<!-- header -->
		[#include "/materials/common/head.ftl"]
		<!-- wrap -->
		<div class="wrap index bc clr">
			<div class="conWrap fl">
				 <div class="conBox">
					<!--内容放这里 start-->
						<form id="search" action="">
						 <input type="hidden" name="status" value="${status}"/>
				       </form>
					<span class="btn addnew" style="float:right;margin-bottom:5px;margin-right:10px;">添加员工</span>
					<span class="btn"style="float:right;margin-bottom:5px;margin-right:10px;"><a href="${base}/material/supplierStaff/staffExcelUtil.do">导出员工信息</a></span>
						<div class="page_tab_box">
			                <div class="page_tab">
			                    <div class="pageTab">
			                        <span class="cur"><a href="${base}/material/supplierStaff/index.xhtml?pid=${pid}&mid=${mid}">全部</a></span>
			                        <span class="cur"><a href="${base}/material/supplierStaff/index.xhtml?pid=${pid}&mid=${mid}&status=0">待审核</a></span>
			                        <span class="cur"><a href="${base}/material/supplierStaff/index.xhtml?pid=${pid}&mid=${mid}&status=1">审核通过</a></span>
			                        <span class="cur"><a href="${base}/material/supplierStaff/index.xhtml?pid=${pid}&mid=${mid}&status=2">审核未通过</a></span>
			                    </div>
			                </div>
		                </div>
		                [#assign mingCi=0]
						<table class="format">
								<thead>
									<th width="5%">名次</th>
									<th width="15%">手机号</th>
									<th width="10%">姓名</th>
									<th width="10%">所属门店</th>
									<th width="10%">所属主管</th>
									<th width="8%">总交易笔数</th>
									<th width="8%">总交易额</th>
									<th width="10%">总提成</th>
									<th width="10%">未结算提成</th>
									<th width="8%">审核状态</th>
									<th width="10%">操作</th>				
								</thead>
								<tbody>
									[#list supplierStaff as item]
									<tr>
									    [#assign mingCi=mingCi+1]
										<td>${mingCi}</td>
										<td>${item.mobile}</td>
										<td>${item.name}</td>
										<td>${item.branchName}</td>
										<td>${item.leaderName}</td>
										<td>${item.ddCount}</td>
										<td>${item.amountTotal}</td>
										<td>${item.tiChengTotal}</td>
										<td>${item.daiJieSuanTotal}</td>
										<td>${item.statusVal}</td>
										<td>
										  <span class="btn modify" rel=["${item.id}","${item.name}","${item.mobile}","${item.branchId}","${item.leaderId}","${item.bankAccount}","${item.bankId}","${item.accountName}"]>修改</span>
										</td>
									</tr>
									[/#list]
								</tbody>
						</table>
					<!--内容放这里 end-->
				 </div>
	       </div>
			[#include "/materials/common/nav.ftl"]
       </div>
	   <!-- footer -->
		<div class="footer">
			<div class="wrap bc tc">
				<p>北京居天下网络科技有限公司　010-67482366 67477686 &nbsp;&nbsp;地址：北京朝阳区十八里店周家庄村288号融望大酒店6层 居天下版权所有Copyright © 2013-2015 京ICP备15010944</p>
			</div>
		</div>
    </body>		
   
<script type="text/html" id="pannel">
<div class="ui-dialog addManager">
	<h2><span class="close"></span><strong>编辑员工信息</strong></h2>
	<input type="hidden" class="staffId"/>
	<ul>
		<li><strong><b class="red">*</b>姓名：</strong> <input type="text" name="name" class="text name" placeholder="员工姓名"></li>
		<li><strong><b class="red">*</b>手机号：</strong> <input type="text" class="text mobile" name="mobile" maxLength="11" placeholder="员工手机号"></li>
		<li><strong>所属门店：</strong> 
		    <select name="branchId" class="branchId">
		     <option value="0">--主店--</option>
		     [#list supplierBranch as item ]
		       <option value="${item.id}">${item.name}</option>
		     [/#list]
		    </select>
		</li>
		<li><strong>所属主管：</strong> 
		  <select name="leaderId" class="leaderId">
		    <option value="0">--主管--</option>
			[#list supplierZG as item]
			  <option value="${item.id}">${item.name}</option>
			[/#list]
		  </select>	
		</li>
		<li><strong><b class="red">*</b>银行卡帐号：</strong> <input type="text" class="text bankAccount" maxLength="20" name="bankAccount" placeholder="银行卡帐号"></li>
		<li><strong><b class="red">*</b>开户银行：</strong> 
		  <select name="bankId" class="bankId">
		    <option value="0">--请选择--</option>
			[#list bankList as item]
			  <option value="${item.id}">${item.name}</option>
			[/#list]
		  </select>	
		</li>
		<li><strong><b class="red">*</b>开户人姓名：</strong> <input type="text" class="text accountName" name="accountName" placeholder="开户人姓名"></li>
	    <li><span class="btn" onClick="addOne()">保存</span></li>
	</ul>
</div>	
</script>

<script>
   function addOne(){
           var name=$(".name").val();
           if(!name){
              justTip("请输入员工名称");
              return;
           }
           var mobile=$(".mobile").val();
            if(!mobile){
              justTip("请输入员工手机号");
              return;
           }
           var reg=/^1\d{10}$/;
           if(!reg.test(mobile)){
              justTip("手机号码格式不正确");
              return;
           }
           var bankAccount=$(".bankAccount").val();
            if(!bankAccount){
              justTip("请输入银行卡账号");
              return;
           }
           var bankId=$(".bankId").val();
            if(bankId==0){
              justTip("请选择开户银行");
              return;
           }
           var accountName=$(".accountName").val();
            if(!accountName){
              justTip("请输入开户人姓名");
              return;
           }
           var leaderId=$(".leaderId").val();
           var branchId=$(".branchId").val();
           var id=$(".staffId").val();
           $.post("${base}/material/supplierStaff/saveOrUpdate.do",{id:id,name:name,mobile:mobile,bankAccount:bankAccount,bankId:bankId,accountName:accountName,leaderId:leaderId,branchId:branchId},function(rel){
             justTip(rel.msg);
             if(rel.ret==0){
                location.reload();
             }
         });
   }
</script>

<script>
  $(function(){
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