<!DOCTYPE HTML>
<html>
<head>
<title>修改商家信息</title>
<meta charset="utf-8">
[#include "/common/res.ftl"]
<link rel="stylesheet" type="text/css" href="${base}/styles/event.css">
<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
</head>

<body>
<!-- header -->
[#include "/common/head.ftl"]

<!-- wrap -->
<div class="wrap index bc clr">
	<div class="conWrap fl">
		[#include "/common/nav.ftl"]
		<div class="conBox">
			<div class="event">
					<form action="${base}/supplier/updateOne.do" method="post">
					<input type="hidden" name="spId" class="spId" value="${supplier.id}"/>
					<input type="hidden" name="mid" value="${mid}"/>
					<input type="hidden" name="pid" value="${pid}"/>
					<ul class="border white">
					      <li>
						   <strong>当前所在地：</strong>${regionName.firstName}-${regionName.secondName}-${regionName.thirdName}
						</li>
						 <li>
							<strong>公司所在地：</strong>
							<div class="select province mr10">
								<i class="new">&#xf107;</i><span>--请选择--</span>
								<p rel="${base}/region/childrenRegion.do">
								<a value="">--请选择--</a>
	                            [#list regionList as item]
	                                <a value="${item.regionid}">${item.regionname}</a>
	                             [/#list]
								</p>					
								<input type="hidden" name="pid" value="">
							</div>
							<div class="select city mr10">
								<i class="new">&#xf107;</i><span>--请选择--</span>
								<p rel="${base}/region/childrenRegion.do">
									
								</p>
								<input type="hidden" name="pid"  value="">
							</div>
							<div class="select area mr10">
								<i class="new">&#xf107;</i><span>--请选择--</span>
								<p>
									
								</p>
								<input type="hidden" name="regionId" class="regionId" value="">
							</div><b class="red">*不选则默认不修改</b>
					   </li>
						<li>
						   <strong><b class="red">*</b> 商家名称：</strong><input type="text" class="text spName" name="spName" style="width:365px" value="${supplier.spName}" id="">
						</li>
						<li>
					        <strong>公司简称：</strong><input type="text" class="text abbreviation" style="width:365px" name="abbreviation" value="${supplier.abbreviation}" id="">
					    </li>
					    <li>
					      <strong><b class="red">*</b> 法人：</strong><input type="text" class="text legalPerson" value="${supplier.legalPerson}" name="legalPerson" id="">
					    </li>
					    <li>
					      <strong>联系人：</strong><input type="text" class="text contactUser" value="${supplier.contactUser}" name="contactUser" id="">
					    </li>
					    <li>
					      <strong>联系电话：</strong><input type="text" class="text contactMobile" value="${supplier.contactMobile}" name="contactMobile" id="">(支持手机,座机)
					    </li>
					    <li>
					      <strong><b class="red">*</b>员工提成比例：</strong><input type="text" class="text gainRate" value="${supplier.gainRate}" name="gainRate" id="">(百分比)
					    </li>
					    <!--<li>
							 <span class="sp"><strong>签约时间：</strong><input type="text" class="text Wdate startDate" name="startDate" value="${supplier.startDateHandle}"  onClick="WdatePicker()" readOnly></span>
							  至&nbsp;&nbsp;&nbsp;
							 <span class="sp">
							 <input type="text" class="text Wdate endDate" name="endDate" value="${supplier.endDateHandle}" onClick="WdatePicker()"  readOnly>
							 </span>
						</li>-->
						<li>
					      <strong><b class="red">*</b>商户地址：</strong><input type="text" style="width:365px" value="${supplier.address}" class="text address" name="address" id="">
					    </li>
					    <li>
					      <strong><b class="red">*</b>类型：</strong>
					      <input type="radio" [#if supplier.type==1]checked="checked"[/#if]  name="type" value="1">装修公司
					      <input type="radio" [#if supplier.type==2]checked="checked"[/#if]  name="type" value="2">建材商
					      <input type="radio" [#if supplier.type==3]checked="checked"[/#if]  name="type" value="3">工长
					      <input type="radio" [#if supplier.type==5]checked="checked"[/#if] name="type" value="5">监理
					    </li>
					    <li>
					      <strong>保证金金额：</strong>
	                      <!-- [#list supplierLevel as item]
	                           <input type="radio" [#if supplier.levelId==item.id]checked="checked"[/#if] name="levelId" value="${item.id}">${item.levelName}(金额:${item.levelMoney})&nbsp;
	                       [/#list] -->
	                       
	                       <input type="radio" name="levelId" [#if supplier.levelId==1]checked="checked"[/#if] value="1">5000&nbsp;
	                       <input type="radio" name="levelId" [#if supplier.levelId==4]checked="checked"[/#if] value="4">10000&nbsp;
	                       <input type="radio" name="levelId" [#if supplier.levelId==6]checked="checked"[/#if] value="6">20000&nbsp;
	                       [#if supplierLevel!=null]
	                          <input type="radio" name="levelId" checked="checked" value="${supplierLevel.id}">${supplierLevel.levelName}(金额:${supplierLevel.levelMoney})&nbsp;
	                       [/#if]
	                       <input type="radio" name="levelId" value="0">其它<input type="text" class="text levelMoney"  name="levelMoney"/>&nbsp;(单位:元)		      
					    </li>
					    <li>
					      <strong>备用手机：</strong><input type="text" class="text bindMobile" name="bindMobile" value="${supplier.bindMobile}" id="">
					    </li>
						<li class="btn"><strong>&nbsp;</strong>
						<span class="btn" onclick="check(this)" style="font-size:12px;">保存</span>
					    <span class="btn" onclick="history.back()" style="font-size:12px;">返回</span>
						</li>
					</ul>
					</form>
		      </div>
		
	    </div>
	</div>
	
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]
</body>

<script>
      function check(el){
             var spName=$(".spName").val();
              if(!spName){
                 justTip("请输入商户名称");
                 return;
              }
              var legalPerson=$(".legalPerson").val();
              if(!legalPerson){
                 justTip("请输入法人");
                 return;
              }
	            var contactMobile=$(".contactMobile").val();
	             if(contactMobile!=undefined&&contactMobile!=""&&contactMobile!=null){
	             var mobileReg=/^([0-9]{3,4}-)?[0-9]{7,8}$/;
	             var phoneReg=/^\d{11}$/;
	             if(mobileReg.test(contactMobile)==false&&phoneReg.test(contactMobile)==false){
	                  justTip("联系方式格式错误");
	                   $(".contactMobile").focus();
		              return;
	             }
	           }
             var gainRate=$(".gainRate").val();
              if(!gainRate){
                 justTip("请输入分成比例");
                 return;
              }
              if(gainRate>100){
                justTip("提成比例不能大于100");
                return; 
              }
             var address=$(".address").val();
             if(!address){
                 justTip("请输入商户地址");
                 return;
              }
             var address=$(".address").val();
             if(!address){
                 justTip("请输入商户地址");
                 return;
              }
             var type=$("input[name='type']:checked").val();
             if(!type){
                 justTip("请选择商户类型");
                 return;
              }
           var bindMobile=$(".bindMobile").val();
           var reg=/^1\d{10}$/;
           if(bindMobile!=undefined&&bindMobile!=""&&bindMobile!=null){
	           if(reg.test(bindMobile)==false){
	              justTip("绑定手机格式错误");
	              $(".bindMobile").focus();
	              return;
	           }
           }
           
           var levelMoney=$(".levelMoney").val();
           var moneyReg=/^\d+$/;
           if(levelMoney!=undefined&&levelMoney!=""&&levelMoney!=null){
               if(moneyReg.test(levelMoney)==false){
                  justTip("等级金额格式错误");
                  $(".levelMoney").focus();
	              return;
               }
           }
           $(el).parents("form").submit();
      }
</script>
</html>