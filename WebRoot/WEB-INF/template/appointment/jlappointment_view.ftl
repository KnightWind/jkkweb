<!DOCTYPE HTML>
<html>
<head>
<title>预约详情</title>
<meta charset="utf-8">
[#include "/common/res.ftl"]
<style>
	table{
		width:50%;
		height:50%;
		border:1px double #CCC;
	}
	
	table tr{
		line-height:40px;
		
	}
	
	.lbl_left{
		width:100px;
		
	}
	
	.lbl_right{
		text-align:left;
		margin-left:20px;
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
				<table>
					<tr>
						<td class="lbl_left">预约用户:</td>
						<td class="lbl_right">${jlappointment.user}</td>
						<td class="lbl_left">预约时间:</td>
						<td class="lbl_right">${jlappointment.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
					</tr>
					<tr>
						<td class="lbl_left">联系电话:</td>
						<td class="lbl_right">${jlappointment.mobile}</td>
						<td class="lbl_left">预约地址:</td>
						<td class="lbl_right">${jlappointment.address}</td>
					</tr>
					<tr>
						<td class="lbl_left">预约来源:</td>
						<td class="lbl_right">${jlappointment.ison}</td>
						<td class="lbl_left">预约状态:</td>
						<td class="lbl_right">${jlappointment.statusName}</td>
					</tr>
					<tr>
						<td class="lbl_left">建筑面积:</td>
						<td class="lbl_right">${jlappointment.space}</td>
						<td class="lbl_left">预约类型:</td>
						<td class="lbl_right">${jlappointment.subTypeName}</td>
					</tr>
					<tr>
						<td class="lbl_left">装修预算:</td>
						<td class="lbl_right">${jlappointment.budget}万</td>
						<td class="lbl_left">装修方式:</td>
						<td class="lbl_right">${jlappointment.methodString}</td>
					</tr>
					<tr>
						<td class="lbl_left">所在城市:</td>
						<td class="lbl_right">${jlappointment.city}</td>
						<td class="lbl_left">所在小区:</td>
						<td class="lbl_right">${jlappointment.community}</td>
					</tr>
					<tr>
						<td class="lbl_left">交房日期:</td>
						<td class="lbl_right">${jlappointment.getRoomDate}</td>
						<td class="lbl_left">签约公司:</td>
						<td class="lbl_right">${jlappointment.company}</td>
					</tr>
					<tr>
						<td class="lbl_left">客厅地面:</td>
						<td class="lbl_right">
							[#if jlappointment.parlourGround == 0]
								不改造
							[/#if]
							[#if jlappointment.parlourGround == 1]
								铺地板
							[/#if]
							[#if jlappointment.parlourGround == 2]
								铺瓷砖
							[/#if]
						</td>
						<td class="lbl_left">客厅拆除项:</td>
						<td class="lbl_right">
							[#if jlappointment.parlourGroundDismantle == 0]
								无拆除项
							[/#if]
							[#if jlappointment.parlourGroundDismantle == 1]
								拆除旧地板
							[/#if]
							[#if jlappointment.parlourGroundDismantle == 2]
								拆除旧瓷砖
							[/#if]
						</td>
					</tr>
					<tr>
						<td class="lbl_left">客厅墙面:</td>
						<td class="lbl_right">
							[#if jlappointment.parlourWall == 0]
								不改造
							[/#if]
							[#if jlappointment.parlourWall == 1]
								贴壁纸
							[/#if]
							[#if jlappointment.parlourWall == 2]
								刷墙纸
							[/#if]
						</td>
						<td class="lbl_left">客厅墙面拆除项:</td>
						<td class="lbl_right">
							[#if jlappointment.parlourWallDismantle == 0]
								无拆除项
							[/#if]
							[#if jlappointment.parlourWallDismantle == 1]
								铲墙皮
							[/#if]
						</td>
					</tr>
					<tr>
						<td class="lbl_left">卧室地面:</td>
						<td class="lbl_right">
							[#if jlappointment.bedroomGround == 0]
								不改造
							[/#if]
							[#if jlappointment.bedroomGround == 1]
								铺地板
							[/#if]
							[#if jlappointment.bedroomGround == 2]
								铺瓷砖
							[/#if]
						</td>
						<td class="lbl_left">卧室地面拆除项:</td>
						<td class="lbl_right">
							[#if jlappointment.bedroomGroundDismantle == 0]
								无拆除项
							[/#if]
							[#if jlappointment.bedroomGroundDismantle == 1]
								拆除旧地板
							[/#if]
							[#if jlappointment.bedroomGroundDismantle == 2]
								拆除旧瓷砖
							[/#if]
						</td>
					</tr>
					<tr>
						<td class="lbl_left">卧室墙面:</td>
						<td class="lbl_right">
							[#if jlappointment.bedroomWall == 0]
								不改造
							[/#if]
							[#if jlappointment.bedroomWall == 1]
								贴壁纸
							[/#if]
							[#if jlappointment.bedroomWall == 2]
								刷墙纸
							[/#if]
						</td>
						<td class="lbl_left">卧室墙面拆除项:</td>
						<td class="lbl_right">
							[#if jlappointment.bedroomWallDismantle == 0]
								无拆除项
							[/#if]
							[#if jlappointment.bedroomWallDismantle == 1]
								铲墙皮
							[/#if]
						</td>
					</tr>
					
				</table>
			 </div>	
			
	    </div>
	</div>
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]
</body>
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
<script type="text/html" id="list">

</script>
</html>