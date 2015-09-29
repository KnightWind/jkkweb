<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>公司详情</title>
[#include "/common/res.ftl"]
<style>
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
				<table id="table_detail"  class="table_09_1 padding-td-10">
                <tbody>
                    <tr>
                    	<td class="td_left">监理名称：</td>
                    	<td><lable>${sp.spName}</lable></td>
                    </tr>
                    <tr>
                    	<td class="td_left">监理照片：</td>
                    	<td>
                    		<div style="float:left" id="certificateList">
                    			[#if logo!=null]<img src="${logo}" width="100" height="100" />[/#if]
                    		</div>
                    	</td>
                    </tr>
                    <tr>
                    	<td class="td_left">综合评分：</td>
                    	<td><img width="150" height="25" src="${base}/images/${sp.zongHe}.png" /> <lable>${sp.estimateAverage}</lable></td>
                    </tr>
                    <tr>
                    	<td class="td_left">效率评价：</td>
                    	<td><img width="150" height="25" src="${base}/images/${sp.xiaoLv}.png" /> <lable>${sp.estimateEfficiency}</lable></td>
                    </tr>
                     <tr>
                    	<td class="td_left">服务评价：</td>
                    	<td><img width="150" height="25" src="${base}/images/${sp.fuWu}.png" /> <lable>${sp.estimateService}</lable></td>
                    </tr>
                     <tr>
                    	<td class="td_left">描述评价：</td>
                    	<td><img width="150" height="25" src="${base}/images/${sp.miaoShu}.png" /> <lable>${sp.estimateDesc}</lable></td>
                    </tr>
                    <tr>
                    	<td class="td_left">商户地址：</td>
                    	<td>
                    		${sp.address}
                        </td>
                    </tr>
                    <tr>
                    	<td class="td_left">联系人：</td>
                    	<td>
                    		${sp.contactUser}
                        </td>
                    </tr>
                    <tr>
                    	<td class="td_left">联系电话：</td>
                    	<td>
                    		${sp.contactMobile}
                        </td>
                    </tr>
                    <tr>
                    	<td class="td_left">400分机号：</td>
                    	<td>
                    		${sp.subPhone}
                        </td>
                    </tr>
                    <tr>
                    	<td class="td_left">自定义域名：</td>
                    	<td>${sp.domain}</td>
                    </tr>
                    <tr>
                    	<td class="td_left">法人：</td>
                    	<td>${sp.legalPerson}</td>
                    </tr>
                    <tr>
                    	<td class="td_left">绑定手机：</td>
                    	<td>${sp.bindMobile}</td>
                    </tr>
                    <tr>
                    	<td class="td_left">营业执照：</td>
                    	<td>${sp.businessCode}</td>
                    </tr>
                    <tr>
                    	<td class="td_left">从业证书：</td>
                    	<td class="zhengshu">
                            <div style="float:left">
                    			[#list pracList as l]
		                            [#if l.typeId != 3]
		                            	<ul style="float:left;margin-left:10px;">
			                    			<li><img src="${l.downloadPath}" width="70" height="50"  /></li>
			                    		</ul>
		                            [/#if]
	                            [/#list]
                    		</div>
                    	</td>
                    </tr>
                    <tr>
                    	<td class="td_left">资历证书：</td>
                    	<td class="zhengshu">
                            <div style="float:left">
                    			[#list qualList as l]
		                            [#if l.typeId != 3]
		                            	<ul style="float:left;margin-left:10px;">
			                    			<li><img src="${l.downloadPath}" width="70" height="50" /></li>
			                    		</ul>
		                            [/#if]
	                            [/#list]
                    		</div>
                    		<div style="float:left;margin-left:20px;">
                    		</div>
                    	</td>
                    </tr>
                    <tr>
                    	<td class="td_left">公司实景：</td>
                    	<td class="zhengshu">
                            <div style="float:left">
                    			[#list liveList as l]
		                            [#if l.typeId != 3]
		                            	<ul style="float:left;margin-left:10px;">
			                    			<li><img src="${l.downloadPath}" width="70" height="50" /></li>
			                    		</ul>
		                            [/#if]
	                            [/#list]
                    		</div>
                    	</td>
                    </tr>
                    <tr style="height:10px"><td style="height:10px"></td></tr>
                    <tr>
                    	<td class="td_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    	<td>
                    		&nbsp;
                    		<span onclick="history.go(-1)" class="btn">返回</span>
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