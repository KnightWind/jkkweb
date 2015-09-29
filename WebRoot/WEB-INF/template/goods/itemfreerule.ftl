<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>0元购设置管理</title>
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
		<strong><b class="red">0元领取条件设置</b></strong>
            <div class="tools white border mb10 sp10 clr">
            <strong><b class="red">*</b>升级条件</strong>
            <table class="format">
            <form id="ruleForm"> 
				<tbody>				 
					[#list memberLevelRule as item]
					   <tr>
							<td>第${item.levelHandle}级别</td>
							[#if item.level>1]
							  <td>消费满<input type="text" class="text" value="${item.price}"  name="${item.id}"/>元以上</td>
							[#else]
							 <td>${item.rule}</td>						 
							[/#if]						
						</tr>					 
					[/#list]			
				</tbody>
				</form>
		     </table>
		      <div class="tools white tc pt10 pb10 borderL borderB  borderR">
				   <span class="btn" id="ruleHandle">保存修改</span>
			  </div>
			</div>
			
          
            <strong><b class="red">*</b>领取条件</strong>
			<table class="format">
				<tbody>
				
				[#list itemFreeRule as item]
					<tr>
							<td rowspan="2">第${item.levelHandle}级别</td>
							<td rowspan="2">
							  [#if item.checkHandle]
							   <input type="checkbox" name=""  checked="checked" disabled="disabled"/>有资格
							 [#else]
							   <input type="checkbox" name=""  disabled="disabled"/>有资格
							  [/#if]
							</td>
							<td>每半年可领取<input type="text" class="text" name="" value="${item.spNum}" disabled="disabled" />
							个商家的商品，每个商家可以领取<input type="text" name="" class="text" value="${item.itemNum}" disabled="disabled"/>个商品</td>
							<td><span class="btn" ><a href="cartEdit.xhtml?mid=${mid}&pid=${pid}&id=${item.id}">修改等级条件</a></span></td>
				   </tr>
				   <tr>
					 <td colspan="2">已选分类:
					 [#list itemFreeRuleCart as  cartItem]
					      [#if cartItem.ruleId==item.id]
					         <strong>${cartItem.name}&nbsp;&nbsp;</strong>
					      [/#if]
					 [/#list]
					 </td>
				   </tr>		
					[/#list]
				</tbody>
		 </table>
	   </div>
	</div>
	[#include "/common/menu.ftl"]	
</div>
<!-- footer -->
[#include "/common/foot.ftl"]
</body>

<script>
   $(function(){
    $("#ruleHandle").click(function(){
        var dataVal=$("#ruleForm").serialize();
        $.post("update.do",{param:dataVal},function(res){
          if (res.ret == 1) {
				} else {
					justTip(res.msg);
				}
        });
    });    
   });
</script>

</html>