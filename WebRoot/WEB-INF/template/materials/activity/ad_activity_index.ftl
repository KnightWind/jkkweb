<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>活动管理</title>
[#include "/common/res.ftl"]

<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/form.js"></script>
<Style>
	tr{
		padding-top:10px;
	}
	td{height:55px;font-size:14px;}
	.td_left{text-align:right;font-size:15px;font-weight:bold;}
	#table_detail{mangin:50px;}
	.requi{color:red};
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
				<!--<form id="search" action="">
				<ul>
					<li>
						<span class="sp"><strong class="auto">活动名称：</strong><input type="text" class="text" name="name"></span>
						<span class="btn search">查 询</span>
					</li>
				</ul>
				
				</form>-->
			</div>
			
			<table class="format">
				<thead>
					<th width="20%">活动名称</th>
					<th width="10%">商家参与名</th>
				</thead>
				<tbody>
					<tr>
						<td>众筹</td>
						<td>
							<a class="obtn" href="index.xhtml?url=ad_activity_list&mid=${mid}&pid=${pid}&status=0">查看</a>
						</td>
					</tr>
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

</html>