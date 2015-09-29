<%@page import="com.jkkp.secure.JkkpUserPrincipal"%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<!DOCTYPE>
<html>
<head>
<title>后台管理登陆</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Content-Language" content="zh-CN">
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="copyright" content="  ">
<link href="/jtxweb/styles/tissy.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="header">
	<div class="width">
    	<div class="logo">后台管理系统</div>
    </div>
</div>
<div class="login">
	<%
		String username = request.getParameter("j_username");
		if (username!=null) {
			if (username.startsWith(JkkpUserPrincipal.SUPPLIER_PREFIX)) {
				request.getRequestDispatcher("/supplierlogin.jsp?error=true").forward(request, response);
			} else if (username.startsWith(JkkpUserPrincipal.SUPERVISOR_PREFIX)) {
				request.getRequestDispatcher("/supervisorlogin.jsp?error=true").forward(request, response);
			} else if (username.startsWith(JkkpUserPrincipal.CROWD_PREFIX)) {
                request.getRequestDispatcher("/crowdlogin.jsp?error=true").forward(request, response);
			} else if (username.startsWith("FOREMAN_PREFIX_")) {
                request.getRequestDispatcher("/foremanlogin.jsp?error=true").forward(request, response);
			}
		}
	%>
	<div class="login_bg">
    	<div class="login_box">
			<ul>
			<form id="form1" name="form1" method="post" action="j_security_check">
			<li><input type="text" id="user" class="tx" onblur="this.className='tx'" onfocus="this.className='in'" name="j_username"/></li>
			<li><input type="password" id="pwd" class="tx" onblur="this.className='tx'" onfocus="this.className='in'" name="j_password" /></li>
			<li class="error">用户名或密码错！</li>
			<input type="submit" class="btn" name="submit" value="登录" style="cursor:pointer;" />
			</form>
			</ul>
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