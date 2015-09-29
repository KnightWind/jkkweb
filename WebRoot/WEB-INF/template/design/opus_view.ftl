<html>
<head>
<meta charset="utf-8">
<title>设计师作品审核管理</title>
[#include "/common/res.ftl"]
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<style>
	#imgList li{
		float:left;
		margin-left:10px;
	}
	img{
		width:100px;
		height:100px;
	}
	p{
		margin-left:120px;
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
			
				<ul>
					<li><strong>作品名称：</strong> ${opus.title}</li>
					<li><strong>公司名称：</strong> ${opus.spName}</li>
					<li><strong>设计师名：</strong> ${opus.designerName}</li>
					<li>
						<strong>作品照片：</strong>
						<ul id="imgList">
							[#list atts as l]
								<li>
									<img src="${l.downloadPath}" />
								</li>
							[/#list]
						</ul>
					</li>
				</ul>
				<br/><br/><br/><br/><br/>
				<p>
				[#if opus.status == 0]
					<a class="obtn" href="${base}/staffopu/examine.xhtml?id=${opus.id}&status=1&url=opus_admin_list&mid=329&pid=266">通过</a>
					<a class="obtn" href="${base}/staffopu/examine.xhtml?id=${opus.id}&status=-1&url=opus_admin_list&mid=329&pid=266">不通过</a>
				[/#if]
					<span class="btn" onclick="history.go(-1);">
						返回
					</span>
				</p>
			</div>				
		[#include "/common/pagination.ftl"]
	  </div>
	</div>
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]

</body>
</html>