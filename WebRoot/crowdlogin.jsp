<%@page import="com.jkkp.secure.JkkpUserPrincipal"%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<!DOCTYPE>
<html>
<head>
<title>建材商管理系统</title>
<%String ctx = request.getContextPath();%>
<meta http-equiv="Cache-Control" content="no-store,no-cache,must-revalidate">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Expires" content="-1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Content-Language" content="zh-CN">
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="copyright" content="  ">
<link href="<%=ctx %>/styles/tissy.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=ctx %>/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=ctx %>/scripts/template.min.js"></script>
<script type="text/javascript" src="<%=ctx %>/scripts/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=ctx %>/scripts/form.js"></script>
<script type="text/javascript">
	function checkLogin(){
		var username = $("#user").val();
		if (!username) {
			return false;
		}
		$("#j_username").val('<%=JkkpUserPrincipal.CROWD_PREFIX%>'+username);
		return true;
	}
</script>
</head>
<body>
<div id="header">
	<div class="width">
    	<div class="logo">建材商管理系统</div>
    </div>
    
</div>
<div class="login">
	<div class="login_bg">
    	<div class="login_box">
			<form id="form1" name="form1" method="post" action="j_security_check" onsubmit="checkLogin();">
			<input type="hidden" name="j_username" id="j_username" />
			<ul>
			<li>用户名：<input type="text" id="user" class="tx" onblur="this.className='tx'" name="" onfocus="this.className='in'"/></li>
			<li>密&nbsp;&nbsp;&nbsp;码：<input type="password" id="pwd" class="tx" onblur="this.className='tx'" onfocus="this.className='in'" name="j_password" /></li>
			<% if ("true".equals(request.getParameter("error"))) { %>
			<li class="error">用户名或密码错！</li>
			<%} %>
			<input type="submit" class="btn" name="submit" value="登录" style="cursor:pointer;" />
			&nbsp;&nbsp;
			<input type="reset" onclick="clearError()" class="btn" value="重置" style="cursor:pointer;" />
			</ul>
			</form>
        </div>
    </div>
</div>
<div id="footer">
	<div class="footer">
    <p class="ptop">北京居天下网络科技有限公司　010-67482366 67477686<br>
地址：北京朝阳区十八里店周家庄村288号融望大酒店6层
居天下版权所有Copyright © 2013-2015   京ICP备15010944</p>
    </div>
</div>
</body>
</html>