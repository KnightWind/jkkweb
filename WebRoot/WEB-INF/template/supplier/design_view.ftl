<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>查看方案</title>
[#include "/common/res.ftl"]
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="${base}/scripts/form.js"></script>
<style>
	.th_left{
		width:100px;
		text-align:right;
	}
	th,td{
		line-height:30px;
	}
	img{
		margin:20px;
	}
	td{
		width:300px;
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
			<div class="tools white border mb10 sp10 clr">
				<table style="width:50%">
					<tr>
						<th class="th_left">方案名称：</th>
						<td>${d.designName}</td>
						<th class="th_left">类型：</th>
						<td>${huxing}/${fengge}/${kongjian}</td>
					</tr>
					<tr>
						<th class="th_left">设计师：</th>
						<td>${d.designer}</td>
						<th class="th_left">所属商家：</th>
						<td>${spname}</td>
					</tr>	
					<tr>
					<tr>
						<th class="th_left">预约用户：</th>
						<td>${user}</td>
						<th class="th_left">方案状态：</th>
						<td>
							[#if d.isLock ==0]
								正常
							[#else]
								冻结
							[/#if]
						</td>
					</tr>	
					<tr>
						<th class="th_left">方案报价：</th>
						<td>${d.budget}</td>
						<th class="th_left">测量面积：</th>
						<td>${d.space}</td>
					</tr>	
						<th class="th_left">创建时间：</th>
						<td>${d.cre}</td>
						<th class="th_left">是否中标：</th>
						<td>
							[#if d.bidding ==0]
								不中标
							[#else]
								中标
							[/#if]
						</td>
					</tr>	
					<tr>
						<th class="th_left">方案房型：</th>
						<td>${d.suType}</td>
						<th class="th_left">装修时间：</th>
						<td>
							${reviewTime}
						</td>
					</tr>	
					<tr>
						<th class="th_left">装修方式：</th>
						<td>
							[#if d.method ==1]
								半包
							[#else]
								全包
							[/#if]
						</td>
						<th class="th_left">装修时段：</th>
						<td>
							[#if d.workTime == 1]
								工作日
							[#else]
								自然日
							[/#if]
						</td>
					</tr>	
					<tr>
						<th class="th_left">开工工期：</th>
						<td>${d.startWork}</td>
						<th class="th_left">水电工期：</th>
						<td>${d.whiteFuel}</td>
					</tr>	
					<tr>
						<th class="th_left">泥木工期：</th>
						<td>${d.tileWood}</td>
						<th class="th_left">竣工工期：</th>
						<td>${d.completion}</td>
					</tr>
					<tr>
						<th class="th_left">方案描述：</th>
						<td>${d.remark}</td>
						<th class="th_left">合同备注：</th>
						<td>${agr.remark}</td>
					</tr>	
				</table>
				<p>现场图
				[#list xianChangList as di]
					<td><img width="100" height="100" src="${di.downloadPath}"  ></td>
				[/#list]
				</p>
				<p>效果图
				[#list xiaoGuoList as di]
					<td><img width="100" height="100" src="${di.downloadPath}"  ></td>
				[/#list]
				</p>
				<p>合同
				[#list hetongList as di]
					<td><img width="100" height="100" src="${di.downloadPath}"  ></td>
				[/#list]
				</p>
				[#if d.status ==0]
					<p>
						<a class="obtn" href="${base}/goods/design/examine.xhtml?id=${d.id}&flag=1&mid=${mid}&pid=${pid}&status=${status}">通过</a>
						<a class="obtn" href="${base}/goods/design/examine.xhtml?id=${d.id}&flag=-1&mid=${mid}&pid=${pid}&status=${status}">不通过</a>
						<a class="obtn" onclick="history.go(-1)">返回</a>
					</p>
				[/#if]
			</div>			
	  </div>
	</div>
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]

<script>
$(function(){
	
	
});	
</script>

</body>
</html>