<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<title>银行卡绑定</title>
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
		.fieldStyle{
			padding:20px;
		}
		</style>
		<style type="text/css">
		    .edit{font-size: 12px;margin:30px;}
		    .edit li{line-height: 40px;}
		    .edit .text li span{width: 150px;display: block;float: left;text-align: right;}
		    .edit .text li p{float: left;}
		    .edit .text li p input{font-size: 12px;padding: 5px 10px;}
		    .edit .text li p strong b{font-weight: normal;color: #f30;font-size: 10px;}
		    .edit .time li span{width: 150px;display: block;float: left;text-align: right;}
		    .edit .time li p{float: left;}
		    .edit .time li input{margin-right: 5px;padding: 5px 10px;width: 50px;font-size: 12px;}
		    .edit .time li p input.checkbox{padding: 0;width: 10px;}
		    .edit .time li textarea{padding: 6px 7px;width: 386px;height: 80px;float: left;margin-right: 10px;}
		    .edit .time li p strong b{font-weight: normal;font-size: 10px;color: #f30;}
		    .edit .time li.last{margin-top: 7px;}
		    .edit .time li.last p strong{display: block;float: left;height: 92px;line-height: 92px;}
		    .p{margin-left: 150px;}
		</style>
		<script>
			$(function(){
					$(".parentRegion").change(function(){
					    $(".childRegion").empty();
						 var pid=$(this).val();
						 if(pid==0||pid==undefined||pid==""){
						    $(".childRegion").append("<option value='0'>--请选择--</option>");
						    return ;
						 }
						 $.post("${base}/main/mainPC/child.do",{pid:pid},function(rel){
							if(rel.length>0){
							   for(var i=0;i<rel.length;i++){
							        $(".childRegion").append("<option value="+rel[i].regionid+">"+rel[i].regionname+"</option>");
							   }
							}
						 });
					});
					
					$(".saveOne").click(function(){
					     var cardNo=$("#cardNo").val();
					     if(cardNo==undefined||cardNo==""){
					        justTip("请输入银行卡号");
					        return;
					     }
					     var reg=/^\d{16,19}$/;
					     if(reg.test(cardNo)==false){
					       justTip("请输入正确的银行卡号");
					       return;
					     }
					     
					     var bankName=$("#bankName").val();
					     if(bankName==undefined||bankName==""){
					        justTip("请输入发卡行");
					        return;
					     }
					     
					     var regionId=$("#regionId").val();
					     if(regionId==undefined||regionId==""||regionId==0){
					        justTip("请选择银行卡所在地");
					        return;
					     }
					     var data={'bankName':bankName,'cardNo':cardNo,'regionId':regionId}
					     var pwd=$("#pwd").val();
					     if(pwd!=undefined&&pwd!=""&&pwd!=null){
							     var reg2=/^\w{6,20}$/;
							     if(reg2.test(pwd)==false){
							        justTip("支付密码格式错误");
							        $("#payPwd").val("");
							        $("#pwd").val("");
							        return;
							     }
							       
							     var payPwd=$("#payPwd").val();
							     if(payPwd!=pwd){
							       justTip("两次密码不一致");
							        $("#payPwd").val("");
							        $("#pwd").val("");
							       return;
							     }
							     data={'bankName':bankName,'cardNo':cardNo,'regionId':regionId,'payPwd':payPwd};
					     }
					     $.post("${base}/material/financeBankCard/saveOne.do",data,function(rel){
					       justTip(rel.msg);
					       if(rel.ret==0){
					         location.href="${base}/material/financeBankCard/edit.xhtml";
					       }
					     });
					});
					
			   });
		</script>
		
	</head>
	<body>
	<!-- header -->
		[#include "/materials/common/head.ftl"]
		<!-- wrap -->
		<div class="wrap index bc clr">
			<div class="conWrap fl">
				 <div class="conBox">
					<!--内容放这里 start-->
					   <div class="edit">
				        <div>
				             <ul class="text">
				                <li class="clr">
					                <span><b class="red">*</b>银行卡号：</span>
					                <p>
					                    <input type="text" class="text"  maxlength="19" placeholder="银行卡号" id="cardNo" style="width:195px" />
					                </p>
					                <font>16~19位数字</font>
				                </li>
					          </ul>
				        </div>
				        <div>
				            <ul class="text">
				                <li class="clr">
				                    <span><b class="red">*</b>银行卡发卡行：</span>
				                    <p>
				                        <input type="text" class="text"  placeholder="银行卡发卡行" id="bankName" style="width:195px"/ >
				                    </p>
				                </li>
				                <li class="clr">
				                    <span><b class="red">*</b>银行卡所在地：</span>
				                    <p>
				                       <select class="parentRegion" style="width:106px;">
				                             <option value="0">--请选择--</option>
				                         [#list parentRegions as item]
							                  <option value="${item.regionid}">${item.regionname}</option>
							             [/#list]
				                       </select>
				                       <select class="half childRegion" id="regionId" style="width:106px;">
                                          	 <option value="0">--请选择--</option>
                                       </select>
				                    </p>
				                   
				                </li>
				            </ul>
				        </div>
				        <div>
				            <ul class="text">
				                <li class="clr">
				                    <span>设置支付密码：</span>
				                    <p>
				                        <input type="password" class="text" placeholder="支付密码" id="pwd" style="width:195px"/>
				                    </p>
				                    <font>6~20数字或字母组合</font>
				                </li>
				                <li class="clr">
				                    <span>确认支付密码：</span>
				                    <p>
				                        <input type="password" class="text"  placeholder="确认密码" id="payPwd" style="width:195px"/>
				                    </p>
				                </li>
				            </ul>
				        </div>
				       <span class="btn saveOne" style="margin-left:151px; margin-top:10px;">确认绑定</span>
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
</html>