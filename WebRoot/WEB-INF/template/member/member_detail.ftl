<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>会员详细信息</title>
[#include "/common/res.ftl"]
</head>

<body>
<!-- header -->
[#include "/common/head.ftl"]

<!-- wrap -->
<div class="wrap index bc clr">
	<div class="conWrap fl">
		[#include "/common/nav.ftl"]
		<div class="conBox">
			<p>会员编号:${member.id}</p>
		    <p>会员账号:${member.mobile}</p>	
		    <p>会员昵称:${member.nickname}</p>	
		    <p>会员性别:${member.genderVal}</p>	
		    <p>会员邮箱:${member.email}</p>	
		    <p>会员生日:${member.birthdayVal}</p>	
		    <p>真实姓名:${member.reallName}</p>	
		    <p>婚姻状态:${member.marryVal}</p>	
		    <p>身份证号码:${member.certificate}</p>	
		    <p>消费金额:${member.costMoney}</p>	
		    <p>喜欢风格:${member.styleVal}</p>	
		    <p>完成订单数:${member.styleVal}</p>	
		    <p>评论数量:${member.styleVal}</p>	
		    <p>日记数量:${member.styleVal}</p>	
		    <p>最后登录:${member.loginTimeHandle}</p>	
	    </div>
	    <span class="btn" onclick="history.back()" style="font-size:12px;float:right;margin:0px 80px 10px 0px ;">返回</span>
	    <div class="conBox">
	    订单信息
	       <table class="format">
	          <tr>
	            <td width="10%">订单编号</td>
	            <td width="10%">订单类型</td>
	            <td width="40%">商品</td>
	            <td width="10%">订单金额</td>
	            <td width="20%">下单时间</td>
	            <td width="10%">订单状态</td>
	          </tr>
	          [#list orderFree as item]
	            <tr>
		            <td>${item.id}</td>
		            <td>0元购订单</td>
		            <td>${item.title}</td>
		            <td>0</td>
		            <td>${item.createTimeHandle}</td>
		            <td>${item.statusHandle}</td>
	            </tr>
	          [/#list]
	          [#list orderPackage as item]
	            <tr>
		            <td>${item.oid}</td>
		            <td>商品订单</td>
		            <td>${item.packageName}</td>
		            <td>${item.packagePrice}</td>
		            <td>${item.createTimeVal}</td>
		            <td>${item.statusHandle}</td>
	            </tr>
	          [/#list]
	           [#list orderPackageItem as item]
	            <tr>
		            <td>${item.oid}</td>
		            <td>商品套餐包订单</td>
		            <td>
			            ${item.packageName}<br/>
			            ${item.title}
		            </td>
		            <td>${item.itemPrice}</td>
		            <td>${item.createTimeVal}</td>
		            <td>${item.statusHandle}</td>
	            </tr>
	          [/#list]
	       </table>
	    </div>
	    <div class="conBox">
	                 事件信息
	       <table class="format">
	          <tr>
	            <td width="20%">事件类型</td>
	            <td width="60%">内容</td>
	            <td width="20%">发布时间</td>	           
	          </tr>
	          [#list events as item]
	            <tr>
		            <td>${item.btype}</td>
		            <td>${item.content}</td>
		            <td>${item.createTimeHandle}</td>		      
	            </tr>
	          [/#list]
	       </table>
	    </div>
	    
	</div>
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]
</body>
</html>