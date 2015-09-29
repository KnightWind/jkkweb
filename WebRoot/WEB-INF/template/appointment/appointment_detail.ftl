<!DOCTYPE HTML>
<html>
<head>
<title>预约详情</title>
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
								${appointment.user}
							</td>
							<td class="textRight">联系电话：</td>
							<td>
								${appointment.mobile}
							</td>
						</tr>
						<tr>
							<td class="textRight">预约途径：</td>
							<td>
								${appointment.typeVal}
							</td>
							<td class="textRight">建筑面积：</td>
							<td>
								${appointment.space}m²
							</td>
						</tr>
						<tr>
							<td class="textRight">装修预算：</td>
							<td>
								${appointment.budget}元
							</td>
							<td class="textRight">预约地址：</td>
							<td>
								${appointment.address}
							</td>
						</tr>
						<tr>
							<td class="textRight">地区：</td>
							<td>
								${appointment.regionname}
							</td>
							<td class="textRight">所在小区：</td>
							<td>
								${appointment.community}
							</td>
						</tr>
						<tr>
							<td class="textRight">用途：</td>
							<td>
								${appointment.houseTypeVal}
							</td>
							<th class="textRight">新房/旧房：</th>
							<td>
								${appointment.suTypeVal}
							</td>
						</tr>
						<tr>
							<td class="textRight">装修风格：</td>
							<td>
								${appointment.cateName}
							</td>
							<th class="textRight">局部/整装：</th>
							<td>
								${appointment.wholeHouseValll}
							</td>
						</tr>
						<tr>
							<td class="textRight">装修方式：</td>
							<td>
								${appointment.methodValll}
							</td>
							<td class="textRight">预约地区：</td>
							<td>
								${regionInfo.firstName}-${regionInfo.secondName}-${regionInfo.thirdName}
							</td>
						</tr>
						<tr>
							<td class="textRight">量房时间：</td>
							<td>
								${appointment.reviewTimeHandle}
							</td>
							<td class="textRight">装修时间：</td>
							<td>
								${appointment.zxTimeHandle}
							</td>
						</tr>
						<tr>
						    <td class="textRight">创建时间：</td>
							<td>
								${appointment.createTimeHandle}
							</td>
						    <td class="textRight">水电改造：</td>
							<td>
								${appointment.shuiLu}
							</td>
						</tr>
						<tr>
						   <td class="textRight">关闭原因：</td>
						   <td colspan="3">${appointment.reason}</td>
						</tr>
					</table>
				    <div style="margin-left:400px;">
				      <span class="btn" onclick="history.back()" style="font-size:12px;">返回</span>
				    </div>
		    </div>
	</div>
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]
</body>
</html>