<!DOCTYPE>
<html>
<head>
<title>监理登录</title>
<meta http-equiv="Cache-Control" content="no-store,no-cache,must-revalidate">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Expires" content="-1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Content-Language" content="zh-CN">
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="copyright" content="  ">
<link href="${base}/styles/tissy.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="${base}/scripts/form.js"></script>
<script type="text/javascript">

	//function checkLogin(){
	//	var name = $("#user").val();
	//	var password = $("#pwd").val();
	//	$.get("/supplier/checkUser",{"name":name,"password":password},function(data){
	//		alert(data.ret);
	//		return false;
	//	});
	//	return false;
	//}

</script>
</head>
<body>
<div id="header">
	<div class="width">
    	<div class="logo">监理登录</div>
    </div>
    
</div>
<div class="login">
	<div class="login_bg">
    	<div class="login_box">
			<form id="form1" name="form1" method="post" action="/jtxweb/supplier/supervisorLogin.do">
			<input type="hidden" name="type" value="5" />
			<ul>
			<li>用户名：<input type="text" id="user" class="tx" onblur="this.className='tx'" onfocus="this.className='in'" name="name"/></li>
			<li>密&nbsp;&nbsp;&nbsp;码：<input type="password" id="pwd" class="tx" onblur="this.className='tx'" onfocus="this.className='in'" name="pass" /></li>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="submit" onclick="return checkLogin()" class="btn" name="submit" value="登录" style="cursor:pointer;" />
			&nbsp;&nbsp;
			<input type="reset" onclick="clearError()" class="btn" name="submit" value="重置" style="cursor:pointer;" />
			<li>&nbsp;</li>
			<li><p><font id="error" color="red">${loginError}</font></p></li>
			</ul>
			</form>
        </div>
    </div>
</div>
<div id="footer">
	<div class="footer">
    <p class="ptop">北京居天下网络科技有限公司　010-67482366 67477686 &nbsp;&nbsp;地址：北京朝阳区十八里店周家庄村288号融望大酒店6层 居天下版权所有Copyright © 2013-2015 京ICP备15010944</p>
    </div>
</div>
</body>
</html>