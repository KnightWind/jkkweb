<!DOCTYPE HTML>
<html>
<head>
<title>支付跳转</title>
<meta charset="utf-8">
</head>
<body>
			[#if (resultMap.payway==0)]
					${resultMap.resultCode}
		   		    ${resultMap.resultInfo}
			[#elseif (resultMap.payway==1)]
				[#if (resultMap.resultCode==0)]
					<#escape x as x?html>
			   			 <#noescape>
							 ${resultMap.formHtml}
						 </#noescape>
					</#escape>	 
		   		[else]
		   			  ${resultMap.resultCode}
		   			  ${resultMap.resultInfo}
		   		[/#if]
			[#elseif (resultMap.payway==2)]
		   		[#if (resultMap.resultCode==0)]
		   			 <#noescape>
						<script>
							alert("退款成功");
							window.close();
						</script>
					 </#noescape>
		   		[else]
		   			  ${resultMap.resultCode}
		   			  ${resultMap.resultInfo}
		   		[/#if]
		   	[#/else]	
		   		  ${resultMap.resultCode}
		   		  ${resultMap.resultInfo}
		    [/#if]
</body>

</html>