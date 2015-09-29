<!DOCTYPE HTML>
<html>
<head>
<title>退款申请详情</title>
<meta charset="utf-8">
[#include "/common/res.ftl"]

<style>
	#dialog tr{
		line-height:40px;
	}
	#dialog td{
		width:230px;
	}
	#dialog{
		font-size:15px;
		margin:10px;
	}
	.textRight{
	    text-align:right;
	}
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
						<table id="dialog">
								<tr>
									<td class="textRight">用户昵称：</td>
									<td>
										${detail.nickName}
									</td>
									<td class="textRight">用户手机：</td>
									<td>
										${detail.mobile}
									</td>
								</tr>
								<tr>
									<td class="textRight">退款金额：</td>
									<td>
										${detail.applyAmount}
									</td>
									<td class="textRight">申请日期：</td>
									<td>
										${detail.applyDateHandle}
									</td>
								</tr>
								<tr>
									<td class="textRight">当前审核进度：</td>
									<td>
										${detail.statusName}
									</td>
									<td class="textRight">审核时间：</td>
									<td>
										${detail.auditDateHandle}
									</td>
								</tr>
								<tr>
									<td class="textRight">审核人：</td>
									<td>
										${detail.auditUserName}
									</td>
									<td class="textRight"></td>
									<td></td>
								</tr>
								<tr>
									<td class="textRight">申请理由：</td>
									<td colSpan="3">
										${detail.applyReason}
									</td>
								</tr>
							</table>
						    <div style="margin-left:400px;">
						      [#if (detail.status==0)]
								<span class="btn" onClick="refundAuditing(${detail.id},1,${detail.refundMobile})">审核通过</span>
								<span class="btn" onClick="refundAuditing(${detail.id},2,${detail.refundMobile})"">审核不通过</span>
							  [/#if]
							  <span class="btn" onclick="history.back()" style="font-size:12px;">返回</span>
				  		    </div>
			    </div>
			  </div>
			[#include "/common/menu.ftl"]
		</div>
	<!-- footer -->
	[#include "/common/foot.ftl"]
	</body>
<script>
   function refundAuditing(id,status,mobile){
     $.post("${base}/refund/refundAuditing.do",{id:id,status:status,mobile:mobile},function(rel){
          justTip(rel.msg);
          if(rel.ret==0){
             location.reload();
          }
     });
   }
</script>
</html>