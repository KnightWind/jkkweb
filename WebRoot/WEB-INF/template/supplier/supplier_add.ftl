<!DOCTYPE HTML>
<html>
<head>
<title>添加商家</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
[#include "/common/res.ftl"]
<link rel="stylesheet" type="text/css" href="${base}/styles/event.css">
<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
<style>
</style>
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
					<form id="" action="${base}/supplier/save.do" method="post">
					<input type="hidden" name="mid" value="${mid}"/>
					<input type="hidden" name="pid" value="${pid}"/>
					<ul class="border white diqu">
					  <li>
							<strong><b class="red">*</b>公司所在地：</strong>
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
							</div>
					</li>
					<li>
					   <strong><b class="red">*</b> 商户名称：</strong><input type="text" class="text spName" style="width:365px" name="spName" id="">
					</li>
					<li>
					   <strong>公司简称：</strong><input type="text" class="text abbreviation" style="width:365px" name="abbreviation" id="">
					</li>
				    <li>
				      <strong><b class="red">*</b> 法人：</strong><input type="text" class="text legalPerson" name="legalPerson" id="">
				    </li>
				    <li>
				      <strong>联系人：</strong><input type="text" class="text contactUser" name="contactUser" id="">
				    </li>
				    <li>
				      <strong>联系电话：</strong><input type="text" class="text contactMobile" maxLength="13" name="contactMobile" id="">(支持手机,座机)
				    </li>
				    <li>
				      <strong><b class="red">*</b>员工提成比例：</strong><input type="text" class="text gainRate" name="gainRate" id="">(百分比)
				    </li>
				    <!--<li>
						 <span class="sp"><strong>签约时间：</strong><input type="text" class="text Wdate startDate" name="startDate" id="" onClick="WdatePicker()" readOnly></span>
						  至&nbsp;&nbsp;&nbsp;
						 <span class="sp"><input type="text" class="text Wdate endDate" name="endDate" id="" onClick="WdatePicker()" readOnly></span>
					</li>-->
					<li>
				      <strong><b class="red">*</b>商户地址：</strong><input type="text" class="text address" name="address" style="width:365px" id="">
				    </li>
				    <li>
				      <strong><b class="red">*</b>类型：</strong>
				      <input type="radio" name="type" value="1">装修公司
				      <input type="radio" name="type" value="2">建材商 
				      <input type="radio" name="type" value="3">工长
				      <input type="radio" name="type" value="5">监理
				    </li>
				    <li>
				      <strong>保证金金额：</strong>
                       <!--[#list supplierLevel as item]
                          <input type="radio" name="levelId" value="${item.id}">${item.levelName}(金额:${item.levelMoney})&nbsp;
                       [/#list]	-->	      
                       <input type="radio" name="levelId" value="1">5000&nbsp;
                       <input type="radio" name="levelId" value="4">10000&nbsp;
                       <input type="radio" name="levelId" value="6">20000&nbsp;
                       <input type="radio" name="levelId" value="0">其它<input type="text" class="text levelMoney" name="levelMoney" />&nbsp;(单位:元)	
				    </li>
				    <li>
				      <strong>备用手机：</strong><input type="text" class="text bindMobile" maxLength="11" name="bindMobile" id="">
				    </li>
					<li class="btn"><strong>&nbsp;</strong>
					<!--<span class="btn saveOne">提交保存</span>-->
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
             var regionId=$(".regionId").val();
              if(!regionId){
                 justTip("请选择公司所在地");
                 return;
              }
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
             var type=$("input[name='type']:checked").val();
             if(!type){
                 justTip("请选择商户类型");
                 return;
              }
           var bindMobile=$(".bindMobile").val();
           var reg=/^1\d{10}$/;
           if(bindMobile!=undefined&&bindMobile!=""&&bindMobile!=null){
	           if(reg.test(bindMobile)==false){
	              justTip("备用手机格式错误");
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