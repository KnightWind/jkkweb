<!DOCTYPE HTML>
<html>
<head>
<title>监理报告详情</title>
<meta charset="utf-8">
[#include "/common/res.ftl"]
<style>
    #topic{
      margin-left:10px;
    }
	#dialog tr{
		line-height:30px;
	}
	#dialog td{
		width:180px;
	}
	#dialog{
		font-size:15px;
		margin:10px;
	}
	.mxInst{
	margin-top:30px;
	}
</style>
</head>
<body>
<!-- header -->
[#include "/common/head.ftl"]
<!-- wrap -->
<div class="wrap index bc clr">
	<div class="conWrap fl">
		    [#include "/common/nav.ftl"]
			<div class="conBox">		
				<div id="topic">
						<table id="dialog">
							<tr>
								<td >报告编号：</td>
								<td>
									${supervisor.id}
								</td>
								<td >监理：</td>
								<td>
									${supervisor.spName}
								</td>
							</tr>
							<tr>
								<td>装修小区：</td>
								<td>
									${supervisor.community}
								</td>
								<td>装修阶段：</td>
								<td>
									${supervisor.stagName}
								</td>
							</tr>
							<tr>
								<td>用户：</td>
								<td>
									${supervisor.nickName}
								</td>
								<td >验收时间：</td>
								<td>
									${supervisor.checkTimeHandle}
								</td>
							</tr>
							<tr>
								<td>开工时间：</td>
								<td>
									${supervisor.beginTimeHandle}
								</td>
								<td >竣工时间：</td>
								<td>
									${supervisor.endTimeHandle}
								</td>
							</tr>
						</table>
					 </div>
					 
					 <div class="mxInst">
					    <table class="format">
							<thead>					
								<th width="20%">验收节点</th>
								<th width="10%">验收结果</th>
								<th width="20%">排序</th>
								<th width="30%">不通过理由</th>
								<th width="20">操作</th>			
							</thead>
						    <tbody>
								[#list mxInst as item]
									<tr>						
										<td>${item.mxDesc}</td>
										<td>${item.passFlagVal}</td>
										<td>${item.ordrBy}</td>
										<td>${item.nopassReason}</td>
										<td><span class="btn">操作</span></td>		
									</tr>
								[/#list]
							</tbody>
				    	</table>	
					 </div>
		    </div>
	   </div>
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]
</body>
</html>