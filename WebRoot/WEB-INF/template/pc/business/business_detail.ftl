<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>装修公司详情</title>
<link href="${base}/styles/pc/common.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${base}/styles/pc/gs.css">
<script type="text/javascript" src="${base}/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${base}/scripts/pc/banner.js"></script>
</head>

<body rel="2">
<div class="header">
    <div class="headerContent clearfix">
       [#include "/pc/commonPc/menuPc.ftl"]
    </div>
</div>
<div class="wrap">
	<div class="here">装修公司 > 装修公司详情</div>
	<div class="info clearfix">
		
		<div class="pics fl advertise">
			<div class="adBanner">
				<ul class="bannerPic">
					[#list caseList as cl]
						<li>
							<img width="484" height="284" src="${cl.path}" />
							<p>
								<span>${cl.space}m²/${cl.money}万/${cl.houseType}/${cl.style}</span>
							</p>
							<b></b>
						</li>
					[/#list]
				</ul>
				<div class="bannerBtn">
				</div>
			</div>
		</div>
		<ul class="companyInfo fl">
			<li class="clearfix">
				<p class="fl cp">
					[#if vb.filepath != null]
						<img src="${vb.filepath}" width="94" height="94" alt="${vb.spName}" title="${vb.spName}">
                	[#else]
                		<img onload="" width="94" height="94" src="${base}/images/nopic.jpg">
                	[/#if]
				</p>
				<p class="fl text">
					<strong>${vb.spName}</strong>
					<span class="star">
						<img src="${base}/images/${vb.zongHe}.png" width="280" height="45" alt="星级评分">
					</span>
					<span class="tips">
						<b class="kin">金</b>${vb.money}元<b class="blue">案</b>
						[#if vb.engcount > 0]
							${vb.engcount}
						[#else]
							0
						[/#if]
						套
					</span>
				</p>
			</li>
			<li class="font"><span>公司简介：</span><br />${vb.intro}</li>
			<li class="hotLine"><b>家可可客服热线：</b>400-111-8108</li>
		</ul>
	</div>
	<div class="pro clearfix">
		<h2>装修案例</h2>
		<ul class="nav fl">
			<li class="cur">田园风格<br/>Rural style</li>
			<li>中式风格<br/>Chinese style</li>
			<li>欧式风格<br/>Europe type style</li>
			<li>极简风格<br/>minimalist style</li>
			<li>更多风格<br/>More style</li>
		</ul>
		<ul class="list fl">
			<li>
				[#list tyList as t]
					<img width="382" height="290" src="${t.path}">
				[/#list]
			</li>
			<li>
				[#list zsList as t]
					<img width="382" height="290" src="${t.path}">
				[/#list]
			</li>
			<li class="cur">
				[#list osList as t]
					<img width="382" height="290" src="${t.path}">
				[/#list]
			</li>
			<li>
				[#list jjList as t]
					<img width="382" height="290" src="${t.path}">
				[/#list]
			</li>
			<li>
				[#list gdList as t]
					<img width="382" height="290" src="${t.path}">
				[/#list]
			</li>
		</ul>
	</div>
	<div class="team">
		<h2>设计团队</h2>
		<ul class="clearfix">
			[#list staffList as sl]
				<li>
					[#if sl.path != null]
						<img width="235" height="271" src="${sl.path}" alt="">
                	[#else]
                		<img width="235" height="271" src="${base}/images/notperson.png">
                	[/#if]
					<p class="mask"></p><p>${sl.stylistName}<br/>${sl.position}</p>
				</li>
			[/#list]
		</ul>
	</div>
	<div class="comment">
		<h2 class="hd">业主点评</h2>
		<ul>
			<li class="clearfix">
				<p class="fl rt">
					<strong>金科观天下</strong>
					98m²<br/>
					5万<br/>
					三室两厅
				</p>
				<div class="cont fl">
					<h2 class="clearfix">
						<i class="stepicon">水电</i>
						<p>设计水平
							<span class="star">
								<i class="orangeStar"></i>
								<i class="orangeStar"></i>
								<i class="orangeStar"></i>
								<i class="orangeStar"></i>
								<i class="whiteStar"></i>
							</span>
							<b>9.0</b>
						</p>
						<p>施工质量
							<span class="star">
								<i class="orangeStar"></i>
								<i class="orangeStar"></i>
								<i class="orangeStar"></i>
								<i class="orangeStar"></i>
								<i class="whiteStar"></i>
							</span>
							<b>9.0</b>
						</p>
						<p>服务态度
							<span class="star">
								<i class="orangeStar"></i>
								<i class="orangeStar"></i>
								<i class="orangeStar"></i>
								<i class="orangeStar"></i>
								<i class="whiteStar"></i>
							</span>
							<b>9.0</b>
						</p>
					</h2>
					<p class="textDetails">施工员很负责，也很专业，有经验。能够按照实际需求帮业主一步到位。感谢，老公很满意，就是有点小贵。始水电改造，材料进场.感谢，老公很满意，就是有点小贵。始水电改造，材料进场.</p>
					<span class="dateTime">2015-07-02  12:33</span>
				</div>
			</li>
			<li class="clearfix">
				<p class="fl rt">
					<strong>金科观天下</strong>
					98m²<br/>
					5万<br/>
					三室两厅
				</p>
				<div class="cont fl">
					<h2 class="clearfix">
						<i class="stepicon">水电</i>
						<p>设计水平
							<span class="star">
								<i class="orangeStar"></i>
								<i class="orangeStar"></i>
								<i class="orangeStar"></i>
								<i class="orangeStar"></i>
								<i class="whiteStar"></i>
							</span>
							<b>9.0</b>
						</p>
						<p>施工质量
							<span class="star">
								<i class="orangeStar"></i>
								<i class="orangeStar"></i>
								<i class="orangeStar"></i>
								<i class="orangeStar"></i>
								<i class="whiteStar"></i>
							</span>
							<b>9.0</b>
						</p>
						<p>服务态度
							<span class="star">
								<i class="orangeStar"></i>
								<i class="orangeStar"></i>
								<i class="orangeStar"></i>
								<i class="orangeStar"></i>
								<i class="whiteStar"></i>
							</span>
							<b>9.0</b>
						</p>
					</h2>
					<p class="textDetails">施工员很负责，也很专业，有经验。能够按照实际需求帮业主一步到位。感谢，老公很满意，就是有点小贵。始水电改造，材料进场.感谢，老公很满意，就是有点小贵。始水电改造，材料进场.</p>
					<span class="dateTime">2015-07-02  12:33</span>
				</div>
			</li>
			<li class="clearfix">
				<p class="fl rt">
					<strong>金科观天下</strong>
					98m²<br/>
					5万<br/>
					三室两厅
				</p>
				<div class="cont fl">
					<h2 class="clearfix">
						<i class="stepicon">水电</i>
						<p>设计水平
							<span class="star">
								<i class="orangeStar"></i>
								<i class="orangeStar"></i>
								<i class="orangeStar"></i>
								<i class="orangeStar"></i>
								<i class="whiteStar"></i>
							</span>
							<b>9.0</b>
						</p>
						<p>施工质量
							<span class="star">
								<i class="orangeStar"></i>
								<i class="orangeStar"></i>
								<i class="orangeStar"></i>
								<i class="orangeStar"></i>
								<i class="whiteStar"></i>
							</span>
							<b>9.0</b>
						</p>
						<p>服务态度
							<span class="star">
								<i class="orangeStar"></i>
								<i class="orangeStar"></i>
								<i class="orangeStar"></i>
								<i class="orangeStar"></i>
								<i class="whiteStar"></i>
							</span>
							<b>9.0</b>
						</p>
					</h2>
					<p class="textDetails">施工员很负责，也很专业，有经验。能够按照实际需求帮业主一步到位。感谢，老公很满意，就是有点小贵。始水电改造，材料进场.感谢，老公很满意，就是有点小贵。始水电改造，材料进场.</p>
					<span class="dateTime">2015-07-02  12:33</span>
				</div>
			</li>
			<li class="clearfix">
				<p class="fl rt">
					<strong>金科观天下</strong>
					98m²<br/>
					5万<br/>
					三室两厅
				</p>
				<div class="cont fl">
					<h2 class="clearfix">
						<i class="stepicon">水电</i>
						<p>设计水平
							<span class="star">
								<i class="orangeStar"></i>
								<i class="orangeStar"></i>
								<i class="orangeStar"></i>
								<i class="orangeStar"></i>
								<i class="whiteStar"></i>
							</span>
							<b>9.0</b>
						</p>
						<p>施工质量
							<span class="star">
								<i class="orangeStar"></i>
								<i class="orangeStar"></i>
								<i class="orangeStar"></i>
								<i class="orangeStar"></i>
								<i class="whiteStar"></i>
							</span>
							<b>9.0</b>
						</p>
						<p>服务态度
							<span class="star">
								<i class="orangeStar"></i>
								<i class="orangeStar"></i>
								<i class="orangeStar"></i>
								<i class="orangeStar"></i>
								<i class="whiteStar"></i>
							</span>
							<b>9.0</b>
						</p>
					</h2>
					<p class="textDetails">施工员很负责，也很专业，有经验。能够按照实际需求帮业主一步到位。感谢，老公很满意，就是有点小贵。始水电改造，材料进场.感谢，老公很满意，就是有点小贵。始水电改造，材料进场.</p>
					<span class="dateTime">2015-07-02  12:33</span>
				</div>
			</li>
		</ul>
	</div>
</div>
[#include "/pc/commonPc/footer.ftl"]
</body>
</html>
<script type="text/javascript">
	$(".nav li").bind("click",function(){
		$(this).addClass("cur").siblings().removeClass("cur");
		var index = $(this).index();
		$(".list li").eq(index).show().siblings(".list li").hide();
	})
</script>