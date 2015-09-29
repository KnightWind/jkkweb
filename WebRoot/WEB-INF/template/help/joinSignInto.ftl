<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<title>微信引流活动用户变身家可可用户</title>
		[#include "/common/res.ftl"]
		<style>
		html,body{width:100%;height:100%;padding:0;margin:0;overflow:auto;}

		</style>
	</head>
		<body>
		  <div style="margin:300px 500px;">
		      <input type="button" class="change" value="一键转换" style="padding:10px;  display: inline-block;background: #1D9DA6;border-radius: 3px;color: #fff;"/>
		  </div>
		</body>
	<script>
	  $(function(){
	    $(".change").click(function(){
	       $.post("${base}/help/memeberChange.do",{},function(rel){
	          justTip(rel.msg);
	       });
	    });
	  });
	</script>
	</html>