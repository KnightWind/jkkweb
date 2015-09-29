<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<title>分店管理</title>
		[#include "/common/res.ftl"]	
		<style>
		.logo{height:82px; background:#fff; border-bottom:2px solid #1D9DA6;}
		.logo p{padding-top:20px;}
		.logo p a{color:#333; font-size:14px;}
		.logo p a i{ font-size:16px;}
		.conWrap h2.hd{ border-color:#ccc;}
		.dataShow{ width:48%; height:500px; float:left; padding-top:20px;}
		.dataShow p{height:40px;}
		.dataShow table{ margin-bottom:20px;}
		.dataShow table td{ border-right:1px solid #ccc;}
		.dataSp{ margin-right:4%;}
		</style>
	<link rel="stylesheet" type="text/css" href="${base}/styles/event.css">
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=VfeswSvAbqgxNc84XQzAlfCf"></script>
	</head>
	<body>
	<!-- header -->
		[#include "/materials/common/head.ftl"]
		<!-- wrap -->
		<div class="wrap index bc clr">
			<div class="conWrap fl">
				 <div class="conBox">
					<!--内容放这里 start-->		
					   <div class="event">
					<form id="send">
					<ul class="border white">
						<li><strong><b class="red">*</b>分店名称：</strong><input type="text" class="text name"  ></li>
						<li><strong>分店服务电话：</strong><input type="text" class="text tel" name="mobile" ></li>
						<li><strong><b class="red">*</b>分店地址：</strong><input type="text" class="text address" name="mobile" style="width:330px;"></li>
						<li><strong>分店坐标：</strong><input type="text" class="text latitude"  disabled><input type="text" class="text longitude" disabled></li>
						
						<div id="allmap" style=" width:650px;height:600px;margin:10px 170px;"></div>
						
						<li class="btn">
						   <strong>&nbsp;</strong><span class="btn saveOne" onClick="show()">保存</span>
						</li>
					</ul>
					</form>
		      </div>
					<!--内容放这里 end-->
				 </div>
	       </div>
			[#include "/materials/common/nav.ftl"]
       </div>
	   <!-- footer -->
		<div class="footer">
			<div class="wrap bc tc">
				<p>北京居天下网络科技有限公司　010-67482366 67477686 &nbsp;&nbsp;地址：北京朝阳区十八里店周家庄村288号融望大酒店6层 居天下版权所有Copyright © 2013-2015 京ICP备15010944</p>
			</div>
		</div>
    </body>	
    <script>
    function show(){
        var name=$(".name").val();
        if(!name){
           justTip("请输入分店名称");
           return ;
        }
        var address=$(".address").val();
        if(!address){
           justTip("请输入分店地址");
           return ;
        }
        var latitude=$(".latitude").val();
        var longitude=$(".longitude").val();
        var TF=true;
	    if(!latitude||!longitude){
	        TF=confirm("提供坐标，可以方便用户定位您的店铺，确定提交吗?");
	    }
	      
	     if(TF){
	        var tel=$(".tel").val();
	        $.post("${base}/material/supplierBranch/saveOrUpdate.do",{latitude:latitude,longitude:longitude,name:name,tel:tel,address:address},function(rel){
	           justTip(rel.msg);
	           if(rel.ret==0){
	             location.href="${base}/material/supplierBranch/index.xhtml"
	           }
	        });
	     }
	
    };
	</script>
	<script type="text/javascript">
		// 百度地图API功能
		var map = new BMap.Map("allmap");
		var point = new BMap.Point(116.331398,39.897445);
		map.centerAndZoom(point,12); 
		
		function myFun(result){
			var cityName = result.name;
			map.setCenter(cityName);
		}
		var myCity = new BMap.LocalCity();
		myCity.get(myFun);   
		  
		//单击获取点击的经纬度
		map.addEventListener("click",function(e){
		    $(".latitude").val(e.point.lng);
		    $(".longitude").val(e.point.lat);
		});
	</script>	
	
</html>