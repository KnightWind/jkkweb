<!DOCTYPE HTML>
<html>
<head>
<title>监理报告详情</title>
<meta charset="utf-8">
[#include "/common/res.ftl"]
<style>
.complaint div{
	font-size:14px;
}
.complaint div span.sp{
	display:block;
}
.complaint div span strong{
	font-size:14px;
	line-height:30px;
	width:90px;
	text-align:right;
	display:inline-block;
	margin:0px 10px 0px 20px;
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
		        <div class="complaint">	
					   <div>
							<span class="sp"><strong>阶段:</strong>${engineeringStageInst.stagName}</span>
							<span class="sp col1"><strong>是否通过:</strong>${engineeringStageInst.finishFlagVal}</span>
							<span class="sp col1"><strong>监理名称:</strong>${engineeringStageInst.createUser}</span>	
					   </div>
					  <div>
							<span class="sp"><strong>用户验收:</strong>${engineeringStageInst.reportPassVal}</span>
							<span class="sp col1"><strong>验收时间:</strong>${engineeringStageInst.passTimeHandle}</span>	
							<span class="sp col1"><strong>报告提交时间:</strong>${engineeringStageInst.createTimeHandle}</span>						
					   </div>
					   <div>
					      <span class="btn search" onclick="history.back()" style="font-size:12px; margin-left:300px;">返回</span>
					   </div>
			    </div>	
			    
			    <div>
			       [#list stageInstResult as SortList]
				       <div style="font-size:20px;margin:10px 0px 10px 0px;">
				          ${SortList.title}
				       </div>
						[#list SortList.children as item]
						  <div style="font-size:16px;margin:10px 0px 10px 0px;">
						    ${item.title}
						  </div>
						   <table class="format">
									<thead>
										<th width="50%">验收节点</th>
										<th width="50%">验收结果</th>
									</thead>
									<tbody>
											   [#list item as tt]
												     [#list tt.items as pp]
														<tr>
															<td>${pp.name}</td>		
															<td>${pp.statusName}</td>
														</tr>
												     [/#list]
												[/#list]
									</tbody>
						   </table>
					    [/#list]
				   [/#list]
			    </div>
			</div>
	  </div>
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]
</body>
</html>