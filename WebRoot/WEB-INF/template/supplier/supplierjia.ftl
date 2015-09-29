<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<title>商家管理</title>
		[#include "/common/res.ftl"]
		<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
		<script type="text/javascript" src="${base}/scripts/form.js"></script>
		<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
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
		
		.sp_width_33_left { float:left; width:33%;}
.sp_select { height:380px;width:90%;}
td.sp_noborder { border:none; }
.sp_padding_100_left { padding-left:100px; }
.sp_padding_500_left { padding-left:500px; }
.sp_margin_100_left { margin-left:100px; }
.grey{ color:#ccc; }
.btn_margin_right { margin-right: 40px; }
.btn_left_40 { margin-left: 40%; }
.col_no_left_padding { padding-left: 0; }
.sp_top_border { background-color: #FFF !important;border: solid 1px #ddd;padding: 10px; }
.sp_top_report { display:inline;float:right;margin-right:20px; }
.swfupload{
	background-color:#DFF1FB;
	float:left;
	margin-left:50px;
	height:40px;
	width:145px;
	background:url('../images/upload.jpg') no-repeat scroll 0 0 transparent;
}
.small_img { width: 50px; height: 38px; }

.sp_table_border{ border-collapse: collapse; border: 1px solid #ccc; }
.daohang{ 
	background: url("../images/daohang_bg_01.jpg") repeat-x scroll 0 0 #cdcdcd;
	border: 1px solid #ddd;
	margin-top: 10px;
	overflow: hidden;
	padding: 10px;
}

.top_recommand{float:left;width:145px;height:200px;margin-left:55px;border:1px solid #000}
.top_recommand img{width:145px;height:145px;}
.recommand_btn{float: right;padding-top: 34px;padding-right: 1px;}
.fix{padding-top: 6px;}
.progressName{display:none;}

.text_left_padding{
	text-align:left;
	padding-left:10px; 
}
.show_template{
	width:300px;
	height:400px;
	margin: 0 auto;
	margin-bottom: 20px; 
}
.table_07_1{
    width:100%;
    /* border-collapse:collapse; */
    border:1px solid #ccc;
    text-align:center;
	background-color:#fff;
}
.table_07_1 thead th{
    background-color:#ebebeb;
    height:39px;
}
.table_07_1 td{
    height:35px;
    border-bottom:1px solid #ccc;
}
.table_07_1 td a{
    color:#428bca;
}
.threeButton{
    text-align:left;
    float:left;
}
		</style>
	</head>
	<body>
	<!-- header -->
		<div class="header">
			<div class="logo">
				<h1><a href="#"><img src="${base}/images/logo.png"></a></h1>
				<p>
					<a target="_blank" href="http://shop.jiakeke.com?sp_id=${su.spId}">欢迎您${su.spname}</a>
					<a href="${base}/supplier/logout.xhtml"><i class="new">&#xf08b;</i> 注销</a>
					<a href="/supplier/password" class="logout"><i class="new">&#xf09c;</i> 修改密码</a>
				</p>
			</div>
		</div>
		<!-- wrap -->
		<div class="wrap index bc clr">
			<div class="conWrap fl">
				<h2 class="hd mb10">
					<strong><a href="#">商家后台</a></strong> &gt;
					<span id="sel1" data-role="首页">选择户型/空间/风格</span>
				</h2>
				<div class="conBox">
			<!--内容放这里 start-->
	 		<form name="form" id="firForm" action="${base}/goods/design/details.xhtml" method="post">
	 				<input type="hidden" name="id" value="${id}" />
	 				<input type="hidden" name="aid" value="${aid}" />
	 				<input type="hidden" name="pid" value="${pid}" />
                    <table class="table_07_1 padding-ThLR-10 padding-TdTB-10">
                        <tbody>
                            <tr>
                                <td>
                                    <div class="sp_width_33_left">
                                        <p>选择【户型】</p>
                                         <input type="hidden" name="hname" />
                                        <select size="10" id="huxing" name="huxing" class="sp_select">
                                        [#list huxing as item]
                                           <option value="${item.id}">${item.cateName}</option>
                                           
                                        [/#list]        
                                                                      </select>
                                    </div>
                                    <div class="sp_width_33_left">
                                        <p>选择【空间】</p>
                                         <input type="hidden" name="kname" />
                                        <select size="10" id="kongjian" name="kongjian" class="sp_select">
                                        [#list kongjian as item]
                                           <option value="${item.id}">${item.cateName}</option>
                                        [/#list]                                                                                         <option value="4"  >客厅</option>
                                    
                                                                                       
                                                                              </select>
                                    </div>
                                    <div class="sp_width_33_left">
                                        <p>选择【风格】</p>
                                         <input type="hidden" name="fname" />
                                        <select size="10" id="fengge" name="fengge" class="sp_select">
                                       	[#list fengge as item]
                                           <option value="${item.id}">${item.cateName}</option>
                                           
                                        [/#list]  
                                       </select>
                                       <p>&nbsp;</p>
                                    </div>
                                    
                                </td>
                            </tr>
                            
                        </tbody>
                    </table>
                    <br/>
                    <p style="text-align:center;"><input style="width:75px;height:35px" onclick="return confirmFormData();" type="submit" class="btn submit" value="提交分类" /></p>
                </form>
					<!--内容放这里 end-->
				</div>
			</div>
			[#include "/common/supplierMenu.ftl"]
			</div>
			<!-- footer -->
			<div class="footer">
				<div class="wrap bc tc">
					<p>北京居天下网络科技有限公司　010-67482366 67477686 &nbsp;&nbsp;地址：北京朝阳区十八里店周家庄村288号融望大酒店6层 居天下版权所有Copyright © 2013-2015 京ICP备15010944</p>
				</div>
			</div>
		</body>
		<script>
		$(function(){
			$('#huxing').bind('change',function(){
				var value=$(this).find('option:checked').text();
				$(this).prev().val(value);
			});
			$('#kongjian').bind('change',function(){
				var value=$(this).find('option:checked').text();
				$(this).prev().val(value);
			});
			$('#fengge').bind('change',function(){
				var value=$(this).find('option:checked').text();
				$(this).prev().val(value);
			});
		});
		
		
		function confirmFormData(){
				if($("input[name='hname']").val() == ""){
					justTip("请选择户型!");
					return false;
				}
				if($("input[name='kname']").val() == ""){
					justTip("请选择空间!");
					return false;
				}
				
				if($("input[name='fname']").val() == ""){
					justTip("请选择风格!")
					return false;
				}
				return true;
		}
		
		</script>
	</html>