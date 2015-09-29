<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>审核列表</title>
[#include "/common/res.ftl"]
<style>
	.fieldStyle{
		padding:20px;
	}
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
			 <div class="white border">
					
					<table id="table_detail"  class="table_09_1 padding-td-10">
                	<tbody>
                    [#if supplierCompany != null]
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
	                [/#if]
                 	[#if supplierCompany == null]
                 		<tr>
	                    	<td class="td_left">监理名称：</td>
	                    	<td><lable>${sp.spName}</lable></td>
	                    </tr>
	                    <tr>
	                    	<td class="td_left">商户地址：</td>
	                    	<td><lable>${sp.address}</lable></td>
	                    </tr>
	                [/#if]
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
                    [#if supplierCompany != null]
	                    <tr>
	                    	<td class="td_left">公司规模：</td>
	                    	<td><lable>${supplierCompany.scale}</lable> (单位:人)</td>
	                    	<td class="td_left">注册资金：</td>
	                    	<td><lable>${supplierCompany.capital}</lable> (单位:万)</td>
	                    </tr>
	                    <tr>
	                    	<td class="td_left">成立时间：</td>
	                    	<td><lable>${supplierCompany.es}</lable></td>
	                    	<td class="td_left">年检时间：</td>
	                    	<td><lable>${supplierCompany.in}</lable></td>
	                    </tr>
	                    <tr>
	                    </tr>
	                    <tr>
	                    	<td class="td_left">营业年限：</td>
	                    	<td>
	                    		<lable>${supplierCompany.st}</lable>&#12288;--&#12288;<lable>${supplierCompany.en}</lable>
	                    	</td>
	                    </tr>
	                    <tr>
	                    </tr>
	                    
                 	[/#if]
	                    <tr>
	                    	<td class="td_left">联系人：</td>
	                    	<td><lable>${sp.contactUser}</lable></td>
	                    	<td class="td_left">联系电话：</td>
	                    	<td><lable>${sp.contactMobile}</lable></td>
	                    </tr>
	                    <tr>
	                    	<td class="td_left">法人：</td>
	                    	<td><lable>${sp.legalPerson}</lable></td>
	                    	<td class="td_left">绑定手机：</td>
	                    	<td><lable>${sp.bindMobile}</lable></td>
	                    </tr>
	                    <tr>
	                    	<td class="td_left">营业执照编码：</td>
	                    	<td><lable>${sp.businessCode}</lable></td>
	                    </tr>
	                    <tr>
	                    	<td class="td_left">营业执照：</td>
	                    	<td>
	                    		<div style="float:left">
	                    			[#list yingyeList as l]
			                            <ul style="float:left;margin-left:10px;">
			                    			<li><img src="${l.downloadPath}" width="70" height="50" /></li>
			                    		</ul>
		                            [/#list]
	                    		</div>
	                    	</td>
	                    </tr>
                 		<tr style="height:10px"><td style="height:10px"></td></tr>
                 		<tr>
	                    	<td class="td_left">身份证：</td>
	                    	<td class="zhengshu">
	                            <div style="float:left">
	                    			[#list fanList as l]
			                            <ul style="float:left;margin-left:10px;">
			                    			<li><img src="${l.downloadPath}" width="70" height="50" /></li>
			                    		</ul>
		                            [/#list]
	                    			[#list zhengList as l]
			                            <ul style="float:left;margin-left:10px;">
			                    			<li><img src="${l.downloadPath}" width="70" height="50" /></li>
			                    		</ul>
		                            [/#list]
	                    		</div>
	                    	</td>
	                    </tr>
                 		<tr>
	                    	<td class="td_left">家装行业从业资格类证书：</td>
	                    	<td class="zhengshu">
	                            <div style="float:left">
	                    			[#list decoration as l]
			                            <ul style="float:left;margin-left:10px;">
			                    			<li><img src="${l.downloadPath}" width="70" height="50" /></li>
			                    		</ul>
		                            [/#list]
	                    		</div>
	                    	</td>
	                    </tr>
                 		<tr>
	                    	<td class="td_left">开户银行许可证：</td>
	                    	<td class="zhengshu">
	                            <div style="float:left">
	                    			[#list bank as l]
			                            <ul style="float:left;margin-left:10px;">
			                    			<li><img src="${l.downloadPath}" width="70" height="50" /></li>
			                    		</ul>
		                            [/#list]
	                    		</div>
	                    	</td>
	                    </tr>
                 		<tr>
	                    	<td class="td_left">商标注册证复印件：</td>
	                    	<td class="zhengshu">
	                            <div style="float:left">
	                    			[#list trademark as l]
			                            <ul style="float:left;margin-left:10px;">
			                    			<li><img src="${l.downloadPath}" width="70" height="50" /></li>
			                    		</ul>
		                            [/#list]
	                    		</div>
	                    	</td>
	                    </tr>
                 		<tr>
	                    	<td class="td_left">授权书：</td>
	                    	<td class="zhengshu">
	                            <div style="float:left">
	                    			[#list author as l]
			                            <ul style="float:left;margin-left:10px;">
			                    			<li><img src="${l.downloadPath}" width="70" height="50" /></li>
			                    		</ul>
		                            [/#list]
	                    		</div>
	                    	</td>
	                    </tr>
                 		<tr>
	                    	<td class="td_left">税务登记证：</td>
	                    	<td class="zhengshu">
	                            <div style="float:left">
	                    			[#list taxation as l]
			                            <ul style="float:left;margin-left:10px;">
			                    			<li><img src="${l.downloadPath}" width="70" height="50" /></li>
			                    		</ul>
		                            [/#list]
	                    		</div>
	                    	</td>
	                    </tr>
                 		<tr>
	                    	<td class="td_left">营业执照副本：</td>
	                    	<td class="zhengshu">
	                            <div style="float:left">
	                    			[#list business as l]
			                            <ul style="float:left;margin-left:10px;">
			                    			<li><img src="${l.downloadPath}" width="70" height="50" /></li>
			                    		</ul>
		                            [/#list]
	                    		</div>
	                    	</td>
	                    </tr>
                 		<tr>
	                    	<td class="td_left">组织机构代码证：</td>
	                    	<td class="zhengshu">
	                            <div style="float:left">
	                    			[#list organization as l]
			                            <ul style="float:left;margin-left:10px;">
			                    			<li><img src="${l.downloadPath}" width="70" height="50" /></li>
			                    		</ul>
		                            [/#list]
	                    		</div>
	                    	</td>
	                    </tr>
                 		<tr>
	                    	<td class="td_left">公司实景图：</td>
	                    	<td class="zhengshu">
	                            <div style="float:left">
	                    			[#list liveList as l]
			                            <ul style="float:left;margin-left:10px;">
			                    			<li><img src="${l.downloadPath}" width="70" height="50" /></li>
			                    		</ul>
		                            [/#list]
	                    		</div>
	                    	</td>
	                    </tr>
	                    [#if supplierCompany != null]
	                 		 <tr>
		                    	<td class="td_left">公司地址：</td>
		                    	<td><lable>${supplierCompany.address}</lable></td>
		                    </tr>
		                    <tr>
		                    	<td class="td_left">公司简介：</td>
		                    	<td colspan="2"><textarea disabled='didabled' class="text" rows="10" cols="100" name="intro">${supplierCompany.intro}</textarea></td>
		                    </tr>
		                [/#if]
	                    <tr>
	                    	<td class="td_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
	                    	<td>
	                    		&nbsp;
	                    		<span onclick="send(this,'${sp.id}')" class="btn" rel="add">通过审核</span>
	                    		&nbsp;&nbsp;&nbsp;
	                    		<span class="btn addnew">不通过审核</span>
	                    		&nbsp;&nbsp;&nbsp;
	                    		<span onclick="history.go(-1)" class="btn" rel="add">返回</span>
	                    	</td> 
	                    </tr>
	            </tbody>
            </table>
					
			 </div>
		[#include "/common/pagination.ftl"]
		</div>
	</div>
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]
</body>
<script type="text/html" id="pannel">
	<div class="ui-dialog addManager" style="width:400px;">
		<h2><span class="close"></span><strong>审核未通过原因</strong></h2>
		<form id="send" method="post">
			<input type="hidden" id="sid" name="id" value="" />
			<ul class="fieldStyle">
				<li>
					<textarea rows="4" cols="53" class="text" name="cause" placeholder="审核未通过原因"></textarea>
				</li>
				<li>&nbsp;</li>
				<li class="btn">
					<span onclick="validataFrom(this)" class="btn" rel="add">提交保存</span>
				</li>
			</ul>
		</form>
	</div>
</script>

<script type="text/javascript">
$(function(){
	$('.ui-paging').page({
		url:'${base}/supplier/examinePagination.do',
		deal:{'click':'span.search'},
		format:['pageCount','count'],
		send:function(){
			var data={
			pageSize:10,
			search:$('#search').formatJSON()
			};
			return data;
		},
	    fun:function(dataR){
	    	if(dataR.records<1){ return false;}
			$('table.format tbody').html(template.render('list',dataR)); 
	    }
	});
});

function validataFrom(el){
	var txt = $("textarea[name='cause']").val();
	var id = $("#sid").val();
	if(txt == ""){
		justTip('请输入原因 !');
		return false;
	}
	$.post('${base}/supplier/examine.do?flag=false',{'cause':txt,'id':${sp.id}},function(data){
		justTip(data.msg);
		window.location.href='${base}/supplier/examineList.xhtml?mid=${mid}&pid=${pid}';
	},'json');
}

function send(el,id){
	$.post('${base}/supplier/examine.do?id='+id+'&flag=true',{'id':id},function(data){
		justTip(data.msg);
		window.location.href='${base}/supplier/examineList.xhtml?mid=${mid}&pid=${pid}';
	},'json');
}

</script>
</html>