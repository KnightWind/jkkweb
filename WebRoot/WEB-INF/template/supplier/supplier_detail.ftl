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
                    	<td class="td_left">公司名称：</td>
                    	<td><lable>${sp.spName}</lable></td>
                    </tr>
                    <tr>
                    	<td class="td_left">公司LOGO：</td>
                    	<td>
                    		<div style="float:left" id="certificateList">
                    			[#if logo!=null]<img src="${logo}" width="300" height="70" />[/#if]
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
                    	<td class="td_left">公司规模：</td>
                    	<td>${supplierCompany.scale} (单位:人)
                        </td>
                    </tr>
                    <tr>
                    	<td class="td_left">注册资金：</td>
                    	<td> ${supplierCompany.capital} (单位:万)</td>
                    </tr>
                    <tr>
                    	<td class="td_left">成立时间：</td>
                    	<td>${supplierCompany.es}</td>
                    </tr>
                    <tr>
                    	<td class="td_left">年检时间：</td>
                    	<td>${supplierCompany.in}</td>
                    </tr>
                    <tr>
                    	<td class="td_left">营业年限：</td>
                    	<td>
                    		${supplierCompany.st}&#12288;--&#12288;</span> 
                    		${supplierCompany.en}
                    	</td>
                    </tr>
                    <tr>
                    	<td class="td_left">公司地址：</td>
                    	<td>
                    		${supplierCompany.address}
                        </td>
                    </tr>
                    <tr>
                    	<td class="td_left">公司简介：</td>
                    	<td colspan="2"><textarea class="text" rows="5" cols="100" disabled="disabled">${supplierCompany.intro}</textarea></td>
                    </tr>
                    <tr style="height:10px"><td style="height:10px"></td></tr>
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
                    		<div style="float:left;margin-left:20px;">
                    		</div>
                    		<!--
                    		<div style="float:left;margin-left:20px;width:350px">
                    			公司资质最多允许上传6张,大小: 500KB,尺寸：263 X 177,格式：jpg, gif, png
                    		</div> -->
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
                    		<!--
                    		<div style="float:left;margin-left:20px;width:350px">
                    			公司资质最多允许上传6张,大小: 500KB,尺寸：263 X 177,格式：jpg, gif, png
                    		</div> -->
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
                    		<div style="float:left;margin-left:20px;">
                    		</div>
                    		<!--
                    		<div style="float:left;margin-left:20px;width:350px">
                    			公司资质最多允许上传6张,大小: 500KB,尺寸：263 X 177,格式：jpg, gif, png
                    		</div> -->
                    	</td>
                    </tr>
                    <tr style="height:10px"><td style="height:10px"></td></tr>
                    <tr>
                    	<td class="td_left">员工描述：</td>
                    	<td style="width:800px;">
                    		[#list st as s]
                    			<ul style="float:left;margin-left:10px;margin-bottom:10px;">
	                    			<li>
	                    				<img src="${s.avatar}" width="70" height="70" alt="${s.name}" title="${s.job}" />
	                    				<!--<span style="filter:alpha(opacity=75);opacity:.75;font-size:8px;text-align:center;">${s.job}</span>-->
		                    		</li>
	                    			<li style="width:60px;text-align:center">
	                    				[#if s.sid==2]
	                    					<span rel=["${s.id}","${s.name}","${s.sid}","${s.job}","${s.sjsSuggest}","${s.gzSuggest}","${s.avatar}"] class="gzSuggest">
		                    					<a id="csss" style="color:blue;">${s.name}</a>
		                    				</span>
	                    				[/#if]
	                    				[#if s.sid==1]
	                    					<span rel=["${s.id}","${s.name}","${s.sid}","${s.job}","${s.sjsSuggest}","${s.gzSuggest}","${s.avatar}"] class="sjsSuggest">
		                    					<a id="csss" style="color:blue;">${s.name}</a>
		                    				</span>
	                    				[/#if]
	                    			</li>
	                    		</ul>
                            [/#list] 
                    	</td>
                    	<td>
                    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    		<!--<span class="btn addnew">添加员工</span> -->
                    	</td>
                    </tr>
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