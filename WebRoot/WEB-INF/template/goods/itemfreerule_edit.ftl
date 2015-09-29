<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>0元购设置管理-临时页面</title>
[#include "/common/res.ftl"]
</head>

<body>
<!-- header -->
[#include "/common/head.ftl"]

<!-- wrap -->
<div class="wrap index bc clr">
	<div class="conWrap fl">
	
		[#include "/common/nav.ftl"]		
		 <div class="conBox">
		     <div>
		             是否有资格		        		     
		     [#if itemFreeRule.checkHandle]
				<input type="checkbox" name=""  checked="checked"/>有资格
				[#else]
				<input type="checkbox" name=""  />有资格
			 [/#if]
		     <div>
		     <div>
		          每半年可领取<input type="text" class="text" name="" value="${itemFreeRule.spNum}" />
			个商家的商品，每个商家可以领取<input type="text" name="" class="text" value="${itemFreeRule.itemNum}" />个商品
		     </div>
			    <table class="format">
			       <tr>
			         <td>是否添加</td>
			         <td>分类名称</td>
			         <td>限领个数</td>
			       </tr>			     			       
			       [#list cartList as cart]
				        <tr>
				          [#assign check=1]
				            [#list itemFreeRuleCart as item]			             
					             [#if item.cartId==cart.id]
					                 [#assign check=0]
					                 <td><input type="checkbox" name="check"  checked="checked"/></td>
					                 <td>${cart.name}</td>
					                 <td><input type="text" name="${cart.id}" class="text" value="${item.num}"/></td>
					             [/#if]				            		         
					        [/#list]
					        [#if check==1]
					                 <td><input type="checkbox" name="check"/></td>
					                 <td>${cart.name}</td>
					                 <td><input type="text" name="${cart.id}" class="text"/></td>
					        [/#if]	
					   </tr> 	 	
			       [/#list]
			    </table>	
		     <div class="tools white tc pt10 pb10 borderL borderB  borderR">
				   <span class="btn" id="ruleHandle">保存</span>
			  </div>	
	     </div>
	</div>
	[#include "/common/menu.ftl"]	
</div>
<!-- footer -->
[#include "/common/foot.ftl"]
</body>
</html>