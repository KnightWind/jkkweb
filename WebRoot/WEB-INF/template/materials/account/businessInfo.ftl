<!DOCTYPE html> 
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />  
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />  
		<title>基本信息</title>
		[#include "/common/res.ftl"]	
		<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
		<script type="text/javascript" src="${base}/scripts/ajaxfileupload.js"></script>
		<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=VfeswSvAbqgxNc84XQzAlfCf"></script>
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
		.fieldStyle{
			padding:20px;
		}
		.img,.tips{float:left;margin:5px;}
		.tips{width:300px;height:100px;border:1px double #CCC;line-height:20px;}
		.tips p{text-align: left;margin:5px 0 0 5px;}
		.table tr{line-height:35px;text-align: left;}
		.tr{text-align: left}
		#allmap{height:400px;width:700px;border:3px double #CCC;}
		.address,.spName{width:400px;}
		.table tr td .img{position:relative;display:block;}
		.table tr td p.shadow{position:absolute;bottom:14px;height:25px;background-color:#000;opacity:0.6;filter:alpha(opacity=60);width:100px;z-index:1;display:none;}
		.table tr td p.font{position:absolute;bottom:14px;height:25px;line-height:25px;text-align:center;width:100px;color:#fff;z-index:2;display:none;}
		.table tr td p.font a{color:#FFF}
		.img img{border:1px double #CCC}
		</style>
	</head>
	<body>
	<!-- header -->
		[#include "/materials/common/head.ftl"]
		<!-- wrap -->
		<div class="wrap index bc clr">
			<div class="conWrap fl">
				<h2 class="hd mb10">
					<strong><a href="#">账号信息</a></strong>
				</h2>
				 
				 <h2 class="hd mb10">
					<strong><a href="#">店铺信息</a></strong>
				</h2>
				 <div class="conBox">
					 <table class="table">
					 	<tr>
					 		<td class="tl">店铺头像：</td>
					 		<td class="tr" colspan="2">
					 			<div class="img">
					 				[#if logo != null]
					 					<img src="${logo}" width="100" height="100" id="img" />
					 				[#else]
					 					<img src="${base}/images/materials/defaultuser.jpg" width="100" height="100" id="img" />
					 				[/#if]
					 				<p class="font"><a href="#">修改头像</a></p>
					 				<p class="shadow"></p>
					 			</div>
					 			<div class="tips">
					 				<p>提示：</p>
					 				<p>1、仅支持JPG、PNG、GIF图片格式，2M以内；</p>
					 				<p>2、建议尺寸80PX*80PX；</p>
					 			</div>
					 			<input style="display:none" onchange="ajaxFileUpload('file',this)" type="file" name="file" id="file" />
					 		</td>
					 	</tr>
					 	<tr>
					 		<td class="tl">审核状态：</td>
					 		<td class="tr">
					 			<span>${sp.proxyStatusString}</span>
					 		</td>
					 	</tr>
					 	<tr>
					 		<td class="tl">店铺名称：</td>
					 		<td class="tr">
					 			<input type="text"  class="text spName" name="spName" id="spName" value="${sp.spName}" />
					 		</td>
					 	</tr>
					 	<tr>
					 		<td class="tl">店铺类型：</td>
					 		<td class="tr">
					 			<input type="radio" name="level" [#if sp.level==1 ]checked[/#if] value="1"/>普通商家
					 			
					 			<input type="radio" name="level" [#if sp.level==2 ]checked[/#if] value="2"/>VIP商家 </span>
					 		</td>
					 	</tr>
					 	<tr>
					 		<td class="tl">店员提成比例：</td>
					 		<td class="tr">
					 			<input type="text"  class="text gainRate" name="gainRate" id="gainRate" value="${sp.gainRate}" />%
					 		</td>
					 	</tr>
					 	<tr>
					 		<td class="tl" style="vertical-align:top">商家地址：</td>
					 		<td class="tr">
								<div id="r-result"><input type="text" class="text address" name="address" id="suggestId" value="${sp.address}" /></div>
								<div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>
					 			经度：<input id="longitude" name="longitude" class="text" type="text" style="width:100px; margin-right:10px;" value="${sp.pointx}"  />
								纬度: <input id="latitude" name="latitude" class="text" type="text" style="width:100px; margin-right:10px;" value="${sp.pointy}"  />
								<span class="btn" onclick="getLocationByPoint()"  style="line-height:20px;">查询</span>
					 		</td>
					 	</tr>
					 	<tr>
					 		<td class="tl">&nbsp;</td>
					 		<td colspan="6">
					 			<div id="allmap"></div>
					 		</td>
					 	</tr>
					 	<tr>
					 		<td class="tl">&nbsp;</td>
					 		<td class="tr">
					 			<br/>
					 			<span class="btn" style="line-height:20px;" onclick="vibData(this)" >提交保存</span>
					 		</td>
					 	</tr>
					 </table>
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
<script type="text/javascript">
	// 百度地图API功能
	map = new BMap.Map("allmap");
	[#if sp.pointx == null || sp.pointy == null || sp.pointx == '' || sp.pointy == '']
		var point = new BMap.Point(116.331398,39.897445);
		map.centerAndZoom(point,11);
	[#else]
		var point = new BMap.Point(${sp.pointx},${sp.pointy});
		map.centerAndZoom(point,19);
		var marker = new BMap.Marker(point);  // 创建标注
		map.addOverlay(marker);
		map.panTo(point); 
	[/#if]
	var geoc = new BMap.Geocoder();
	//单击获取点击的经纬度
	map.addEventListener("click",function(e){
		$("#longitude").val(e.point.lng);
		$("#latitude").val(e.point.lat);
		var point = new BMap.Point(e.point.lng,e.point.lat);
		var gc = new BMap.Geocoder();
		gc.getLocation(point, function(rs){
		   var addComp = rs.addressComponents;
		   var addr = addComp.city + addComp.district + addComp.street + addComp.streetNumber;
		   var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
				{"input" : "suggestId"
			});
			ac.setInputValue(addr); 
		   //alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
		})
	});
	
	// 用经纬度设置地图中心点
	function getLocationByPoint(){
		if(document.getElementById("longitude").value != "" && document.getElementById("latitude").value != ""){
			map.clearOverlays(); 
			var new_point = new BMap.Point(document.getElementById("longitude").value,document.getElementById("latitude").value);
			var marker = new BMap.Marker(new_point);  // 创建标注
			map.addOverlay(marker);              // 将标注添加到地图中
			map.panTo(new_point);      
		}
	}
	
	// 编写自定义函数,创建标注
	function addMarker(point,label){
		var marker = new BMap.Marker(point);
		map.addOverlay(marker);
		marker.setLabel(label);
	}
</script>
<script type="text/javascript">
	// 百度地图API功能
	function G(id) {
		return document.getElementById(id);
	}

	
	var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
		{"input" : "suggestId"
	});
	ac.setInputValue("${sp.address}"); 
	ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
	var str = "";
		var _value = e.fromitem.value;
		var value = "";
		if (e.fromitem.index > -1) {
			value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		}    
		str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;
		
		value = "";
		if (e.toitem.index > -1) {
			_value = e.toitem.value;
			value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		}    
		str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
		G("searchResultPanel").innerHTML = str;
	});

	var myValue;
	ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
	var _value = e.item.value;
		myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;
		
		setPlace();
	});

	function setPlace(){
		map.clearOverlays();    //清除地图上所有覆盖物
		function myFun(){
			var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
			map.centerAndZoom(pp, 18);
			map.addOverlay(new BMap.Marker(pp));    //添加标注
		}
		var local = new BMap.LocalSearch(map, { //智能搜索
		  onSearchComplete: myFun
		});
		local.search(myValue);
	}
</script>
<script>
	$(function(){
	
		$(".font").click(function(){
			$("#file").click();
		});
		$(".table tr td .img img,.table tr td p").bind("mouseover",function(){
			$(".shadow,.font").show();
		})
		$(".table tr td .img img,.table tr td p").bind("mouseout",function(){
			$(".shadow,.font").hide();
		})
		
	})
</script>
<script type="text/javascript">
	function vibData(el){
		var spName = $("#spName").val();
		var address = $("#suggestId").val();
		var longitude = $("#longitude").val();
		var latitude = $("#latitude").val();
		if(spName == ""){
			justTip("请输入店铺名称");
			return;
		}
		if(address == ""){
			justTip("请输入店铺地址");
			return;
		}
		if(longitude == ""){
			justTip("请输入经度！");
			return;
		}
		if(latitude == ""){
			justTip("请输入纬度！");
			return;
		}
		var level=$("input[name='level']:checked").val();
		var gainRate=$(".gainRate").val()/100;
		var url = "${base}/material/account/save.do";
		$.post(url,{"level":level,"gainRate":gainRate,"spName":spName,"address":address,"latitude":latitude,"longitude":longitude},function(data){
			justTip(data.msg);
			if(date.code==0){
			  location.reload();
			}
		});
	}

	function ajaxFileUpload(id,file){
		var ImageFileExtend = ".gif,.png,.jpg";
		  var fileExtend = file.value.substring(file.value.lastIndexOf('.')).toLowerCase();
		  if(ImageFileExtend.indexOf(fileExtend) == -1)
	       {
	            justTip('请选择gif,png,jpg图片 !');
	            return;
	       }
	       var url,image,fileSize;
	       fileSize = file.files[0].size;
	       var size = fileSize / 1024;
		   if(size > 2000){   
		      justTip("附件不能大于2M！");  
		      return; 
		   }
	    //执行上传文件操作的函数
	    var sendUrl = "${base}/material/account/uploadLogo.xhtml";
	    $.ajaxFileUpload({
	        //处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
	        url:sendUrl,
	        secureuri:false,                       //是否启用安全提交,默认为false
	        fileElementId:id,           //文件选择框的id属性
	        dataType:'text',                       //服务器返回的格式,可以是json或xml等
	        success:function(data, status){        //服务器响应成功时的处理函数
	            window.location.reload();
	        },
	        error:function(data, status, e){ //服务器响应失败时的处理函数
	        	alert('上传失败，请检查网络后重试！！');
	        }
	    });
	}
</script>
<script type="text/javascript">
  // 添加带有定位的导航控件
  var navigationControl = new BMap.NavigationControl({
    // 靠左上角位置
    anchor: BMAP_ANCHOR_TOP_LEFT,
    // LARGE类型
    type: BMAP_NAVIGATION_CONTROL_LARGE,
    // 启用显示定位
    enableGeolocation: true
  });
  map.addControl(navigationControl);
  // 添加定位控件
  var geolocationControl = new BMap.GeolocationControl();
  geolocationControl.addEventListener("locationSuccess", function(e){
    // 定位成功事件
    var address = '';
    address += e.addressComponent.province;
    address += e.addressComponent.city;
    address += e.addressComponent.district;
    address += e.addressComponent.street;
    address += e.addressComponent.streetNumber;
    alert("当前定位地址为：" + address);
  });
  geolocationControl.addEventListener("locationError",function(e){
    // 定位失败事件
    alert(e.message);
  });
  map.addControl(geolocationControl);
</script>
</html>