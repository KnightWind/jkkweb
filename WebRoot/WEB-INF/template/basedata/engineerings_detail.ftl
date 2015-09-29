<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>工程单详细信息</title>
[#include "/common/res.ftl"]
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/form.js"></script>
<style>
    
	#dialog tr{
		line-height:30px;
	}
	#dialog td{
		width:250px;
	}
	#dialog{
		font-size:15px;
		margin:10px;
	}

	#handleDiv{
	 margin-left:409px;
	}
</style>
<style>
.addManager{
  height:200px;
}
#send{
  padding-left:50px;
}
#send .saveBtn{
  padding-left:114px;
}

#send li{
  padding-top:10px;
 
}
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
				<div id="engineering">
					<table id="dialog">
						<tr>
							<td class="textRight">小区(工地)名称:</td>
							<td >
								${engineering.community}
							</td>
							<td class="textRight">用户:</td>
							<td>
								${engineering.nickName}
							</td>
						<tr>
						<tr>
							<td class="textRight">装修阶段:</td>
							<td>
								${engineering.stageVal}
							</td>
							<td class="textRight">商家报价(单位:元):</td>
							<td>
								${engineering.zxFund}
							</td>
						<tr>
						<tr>
							<td class="textRight">监管款(单位:元):</td>
							<td>
								${engineering.jgFund}
							</td>
							<td class="textRight">支付状态</td>
							<td>
								${engineering.statusVal}
							</td>
						<tr>
						<tr>
							<td class="textRight">装修公司:</td>
							<td>
								${engineering.spName}
							</td>
							<td class="textRight">设计师:</td>
							<td>
								${engineering.designerName}
							</td>
						<tr>
						<tr>
							<td class="textRight">创建人:</td>
							<td>
								${engineering.creauser}
							</td>
							<th class="textRight">创建时间:</th>
							<td>
								${engineering.createTimeHandle}
							</td>
						<tr>
						<tr>
							<td class="textRight">开工时间:</td>
							<td>
								${engineering.startTimeHandle}
							</td>
							<th class="textRight">竣工时间:</th>
							<td>
								${engineering.endTimeHandle}
							</td>
						<tr>
						<tr>
							<td class="textRight">经度:</td>
							<td>
								${engineering.pointx}
							</td>
							<td class="textRight">纬度:</td>
							<td>
								${engineering.pointy}
							</td>
						<tr>
						<tr>
						   <td class="textRight">备注</td>
						   <td colspan="3">${engineering.note}</td>
						</tr>
					</table>
				 </div>
				 <div id="handleDiv">
				      <span class="btn handle" rel=["${engineering.id}","${engineering.pointx}","${engineering.pointy}"]>坐标纠正</span>
				      <span class="btn search" onclick="history.back()" style="font-size:12px;">返回</span>
				 </div>
		   </div>					
	</div>
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]
</body>

<script type="text/html" id="pannel">
<div class="ui-dialog addManager">
	<h2><span class="close"></span><strong>工程单坐标纠正</strong></h2>
	<form id="send">
	<input type="hidden" class="pointId">
		<ul>
			<li>
				<strong><b class="red">*</b>经度：</strong> 
				<input type="text" class="text pointx" placeholder="经度" >
			</li>
			<li>
				<strong><b class="red">*</b>纬度：</strong> 
				<input type="text" placeholder="纬度" class="pointy text">
			</li>
			<li class="btn saveBtn"><span class="btn saveOne" rel="add">提交保存</span></li>
		</ul>
	</form>
</div>	
</script>

<script>
   $(function(){
          $('#handleDiv').delegate('.handle','click',function(){
		        var $this=$(this),data=$.parseJSON($this.attr('rel'));
		        addMask();
		        $('body').append($('#pannel').html());
		        $('.ui-dialog').data('act','modify');
		        $('.ui-dialog input,.ui-dialog select,.ui-dialog textarea').each(function(index,ele){
		            $(ele).val(data[index]);
		        });
		        center($('.ui-dialog'));
		        $(".saveOne").click(function(){
		           var pointy=$(".pointy").val();
		           var pointx=$(".pointx").val();
		           var id=$(".pointId").val();
		           $.post("${base}/engineerings/updatePoint.do",{id:id,pointx:pointx,pointy:pointy},function(rel){
		               justTip(rel.msg);
		               if(rel.ret==0){
		                 location.reload();
		              }
		           })
		        });
		    });
	
   });
</script>
</html>