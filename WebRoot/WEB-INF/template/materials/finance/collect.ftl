<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<META content="IE=8.0000" http-equiv="X-UA-Compatible">
		<title>收银管理</title>
		[#include "/common/res.ftl"]
	    <script type="text/javascript" src="${base}/scripts/jquery.qrcode.min.js"></script>
	    <script type="text/javascript" src="${base}/scripts/template.min.js"></script>
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
		<style>
		   .dingjin{
		      font-size:18px;
		      margin:100px 0px 10px 30px;
		   }
		   .dingjin li{
		     float:left;
		     margin-left:30px;
		   }
		</style>
		<script>
		    $(function(){
		    
		    	$(".javaQrCode").click(function(){
		    		var fee=$(".fee").val();
		            var type=$(".type").val();
		            if(fee==''){
		                justTip("请输入金额");
		                return;
		            }
		            if(type==0){
		                justTip("请选择支付类型");
		                return;
		            }
		    		$.post("${base}/material/collect/createCode.do",{fee:fee,type:type},function(rel){
		               if(rel.ret == 0){
		                    var address="${base}"+rel.msg;
		                    var html = "<img src='"+address+"' width='200' height='200' />";
		                    $("#qrcode").html('');
		               		$("#qrcode").append(html);
		               }else{
		               		justTip("生成失败!");
		               }
		            });
		    	});
		    
		       $(".codeBtn").click(function(){
		            var fee=$(".fee").val();
		            var type=$(".type").val();
		            if(fee==''){
		                justTip("请输入金额");
		                return;
		            }
		            if(type==0){
		                justTip("请选择支付类型");
		                return;
		            }
		         
		            $.post("${base}/material/collect/createCode.do",{fee:fee,type:type},function(rel){
		               if(rel.ret==1){
		                  justTip(rel.msg);
		               }else{
						  if(rel.msg) {
								content = utf16to8(rel.msg); //解决生成的二维码中文乱码问题
								    $("#qrcode").html("");
									$('#qrcode').qrcode({
										width: 200,
										height:200,
										text: content
									});
			             	   }
		                  }
		            });
		       });
		       
		       function utf16to8(str) {
				    var out, i, len, c;
				    out = "";
				    len = str.length;
				    for(i = 0; i < len; i++) {
					c = str.charCodeAt(i);
					if ((c >= 0x0001) && (c <= 0x007F)) {
					    out += str.charAt(i);
					} else if (c > 0x07FF) {
					    out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
					    out += String.fromCharCode(0x80 | ((c >>  6) & 0x3F));
					    out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));
					} else {
					    out += String.fromCharCode(0xC0 | ((c >>  6) & 0x1F));
					    out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));
					}
				    }
				    return out;
				}
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
					
					  <div class="dingjin" style="float:left">
					     <ul>
					       <li>金额:</li>
					       <li><input type="text" class="text fee" placeholder="请输入金额"/></li>
					       <li>类型:</li>
					       <li>
					       <select class="type">
						       <option value="0">
						                        请选择类型
						       </option> 
						       <option value="1">
						                           定金
						       </option> 
						       <option value="2">
						                           全款
						       </option> 
					       </select>
					       </li>
					       <li><span class="btn javaQrCode">生成二维码</span></li>
					     </ul>
					  </div>
					  <div id="qrcode" style="margin:30px;height:200px"></div>
					
					  <div>
						 <table class="format">
							<thead>		
								<th width="10%">用户手机</th>
								<th width="10%">支付金额</th>
								<th width="15%">活动名称</th>
								<th width="10%">支付类型</th>
								<th width="20%">产品信息</th>
								<th width="20%">收获地址</th>
								<th width="10%">备注</th>
							</thead>
						<tbody>
							[#list pagination.dataList as item]
								<tr>
									<td>${item.mobile}</td>
									<td>${item.fee}</td>
									<td>${item.activityName}</td>
									<td>${item.typeVal}</td>
									<td>
									  <input type="text" class="text" value="${item.productInfo}" onblur="updateProductInfo(${item.id},this)"/>
									</td>					
									<td>
									   <input type="text" class="text" value="${item.address}" onblur="updateAddress(${item.id},this)"/>
									</td>
									<td>
									   <span class="btn modify" rel=["${item.id}","${item.remark}"]>编辑</span>
									</td>	
								</tr>
						    [/#list]
						</tbody>
					</table>
					</div>
		            [#include "/common/pagination.ftl"]
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
    
 <script type="text/html" id="pannel">
	<div class="ui-dialog addManager" style="width:300px;">
		<h2><span class="close"></span><strong>编辑备注信息</strong></h2>
		<form id="send" action="" method="post" style="padding:10px;">
			<input type="hidden" name="id">
			<textarea  rows="5" cols="40" class="text" name="remark" placeholder="编辑备注信息"></textarea>
			<span class="btn save" onclick="tijiao(this)">提交保存</span>
		</form>
	</div>
</script> 
    
  <script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
<tr>
	<td><%=result.elements[i].mobile%></td>
	<td><%=result.elements[i].fee%></td>
	<td><%=result.elements[i].activityName%></td>
	<td><%=result.elements[i].typeVal%></td>
	<td>
	   <input type="text" class="text" value="<%=result.elements[i].productInfo%>" onblur="updateProductInfo(<%=result.elements[i].id%>,this)"/>
	</td>					
	<td>
	 <input type="text" class="text" value="<%=result.elements[i].address%>" onblur="updateAddress(<%=result.elements[i].id%>,this)"/>
	</td>
	<td>
	<span class="btn modify" rel=["<%=result.elements[i].id%>","<%=result.elements[i].remark%>"]>编辑</span>
	</td>	
</tr>	
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/material/collect/pagination.do',
		deal:{'click':'.search'},
		send:function(){
			var data={
					pageSize:10,
					search:$('#search').formatJSON()
			};
			return data;
		},
	    fun:function(dataR){
	    	if(dataR.records<1){ return false;}
			$('table.format tbody').html(template.render('list',dataR)); 
	    }
	});
});	
</script>
    
 <script>
  function updateProductInfo(id,obj){
     var param=$(obj).val();
     if(id==null){
         return;
     }
     $.post("${base}/material/collect/updateProductInfo.do",{id:id,productInfo:param},function(){
       
     });
  }
  function updateAddress(id,obj){
     var param=$(obj).val();
     if(id==null){
         return;
     }
     $.post("${base}/material/collect/updateAddress.do",{id:id,address:param},function(){
       
     });
  }
  function updateRemark(id,obj){
     var param=$(obj).val();
     if(id==null){
         return;
     }
     $.post("",{id:id,remark:param},function(){
       
     });
  }
  
  function tijiao(el){
	var remark = $('textarea[name="remark"]').val();
	var id = $('input[name="id"]').val();
	if(id == '')
		return;
	$.post("${base}/material/collect/updateRemark.do",{"id":id,"remark":remark},function(data){
		if(data.ret == 0){
			window.location.reload();	
		}else{
			justTip(date.msg);
		}
	});
}
  
 </script>
</html>