<%@ page contentType="text/html;charset=utf-8" language="java" %>
<!DOCTYPE>
<html>
<head>
<title>后台管理系统</title>
<% String ctx = request.getContextPath(); %>
<meta http-equiv="Cache-Control" content="no-store,no-cache,must-revalidate">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Expires" content="-1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Content-Language" content="zh-CN">
<meta name="description" content="  ">
<meta name="keywords" content="">
<meta name="copyright" content="  ">
<link href="<%=ctx%>/styles/tissy.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="header">
	<div class="width">
    	<div class="logo">后台管理系统</div>
    </div>
</div>
<div class="login">
	<%
	String requestPath = new org.springframework.web.util.UrlPathHelper().getOriginatingRequestUri(request);
	if (requestPath.contains("/supplier_index.xhtml")) {
		request.getRequestDispatcher("/supplierlogin.jsp").forward(request, response);
	} else if (requestPath.contains("/jl_index.xhtml")) {
		request.getRequestDispatcher("/supervisorlogin.jsp").forward(request, response);
	} else if (requestPath.contains("/zc_index.xhtml")){
		request.getRequestDispatcher("/crowdlogin.jsp").forward(request, response);
	} else if (requestPath.contains("/foreman_index.xhtml")){
		request.getRequestDispatcher("/foremanlogin.jsp").forward(request, response);
	}
	%>
	<div class="login_bg">
    	<div class="login_box">
			<ul>
			<form id="form1" name="form1" method="post" action="/jtxweb/admin/j_security_check">
			<li><input type="text" id="user" class="tx" onblur="this.className='tx'" onfocus="this.className='in'" name="j_username"/></li>
			<li><input type="password" id="pwd" class="tx" onblur="this.className='tx'" onfocus="this.className='in'" name="j_password" /></li>
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