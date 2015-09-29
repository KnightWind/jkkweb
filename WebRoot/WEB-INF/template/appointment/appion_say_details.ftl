<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>活动预约详情</title>
[#include "/common/res.ftl"]
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="${base}/scripts/form.js"></script>
<style>
	
	#pannel li{
		line-height:30px;
	}	

	.table tr{
		line-height:35px;
		font-size:14px;
	}
	.mar{
		padding-left:30px;
	}
	
	#dialog tr{
		line-height:30px;
	}
	#dialog td{
		width:180px;
	}
	#dialog{
		font-size:15px;
		margin:10px;
	}
	
	#topic{
		border:2px double #CCC;
		position:fixed; 
		right: 40px; 
		top: 170px; 
		width:300px; 
		line-height:30px; 
		background: #D3D3D3; 
	}
    .feedbackTime{
      margin-left:166px;
    }
    .feedbackTime2{
      margin-left:117px;
    }
    .orderBy{
     margin-left:13px;
    }
    .feedbackBtn{
      margin-left:185px;
    }
</style>

<style>
.addManager{
  height:250px;
}
#send{
  padding-left:20px;
}
#send .saveBtn{
  padding-left:140px;
}
#send li{
  padding-top: 20px;
｝
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
			<div class="tools white border mb10 sp10 clr">
				<div style="overflow：scroll;">
				<h2><span class="close"></span><strong>预约业主必须知道的17个条件</strong></h2>
				<form id="send" action="${base}/appointment/saveAppoinSay.xhtml" method="post">
					<input type="hidden" name="id" value="${say.id}">
					<input type="hidden" name="aid" value="${id}">
					<input type="hidden" name="pid" value="${pid}">
					<input type="hidden" name="mid" value="${mid}">
					<table class="table">
						<tr>
							<td  colspan="2">
								<b class="red">*</b>
								1、小区/地址是哪里？[要问出小区名字和属于北京哪个城区]
							</td>
						</tr>
						<tr>
							<td class="mar" colspan="2">
								<textarea rows="2" cols="56" class="text" name="community" placeholder="小区/地址">${say.community}</textarea>
							</td>
						</tr>
						<tr>
							<td>
								<b class="red">*</b>
								2、新房还是老房？
							</td>
						</tr>
						<tr>
							<td class="mar" colspan="2">
								[#if say.oldnew == 2]
									<input type="radio" name="oldnew" value="1" /> 新房
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" checked="checked" name="oldnew" value="2" /> 老房
								[#else]
									<input type="radio" checked="checked" name="oldnew" value="1" /> 新房
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" name="oldnew" value="2" /> 老房
								[/#if]
							</td>
						</tr>
						
						<tr>
							<td>
								<b class="red">*</b>
								3、是否拿到钥匙？
							</td>
						</tr>
						<tr>
							<td class="mar" colspan="2">
								[#if say.vkey == 0]
									<input type="radio"  name="vkey" value="1" /> 是
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" checked="checked" name="vkey" value="0" /> 否
								[#else]
									<input type="radio" checked="checked" name="vkey" value="1" /> 是
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio"  name="vkey" value="0" /> 否
								[/#if]
							</td>
						</tr>
						
						<tr>
							<td>
								<b class="red">*</b>
								4、房屋用途？[出租、自用、婚房、儿童房、会所、工装]
							</td>
						</tr>
						<tr>
							<td class="mar" colspan="2">
							[#if say != null]
								[#if say.useId == 1]
									<input type="radio"  checked="checked" name="useId" value="1" onclick="$('#useRemark').hide(300)" /> 出租
								[#else]
									<input type="radio" name="useId" value="1" onclick="$('#useRemark').hide(300)" /> 出租
								[/#if]
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								[#if say.useId == 2]
									<input type="radio" checked="checked" name="useId" value="2" onclick="$('#useRemark').hide(300)" /> 自用
								[#else]
									<input type="radio" name="useId" value="2" onclick="$('#useRemark').hide(300)" /> 自用
								[/#if]
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								[#if say.useId == 3]
									<input type="radio" checked="checked" name="useId" value="3" onclick="$('#useRemark').hide(300)" /> 婚房
								[#else]
									<input type="radio" name="useId" value="3" onclick="$('#useRemark').hide(300)" /> 婚房
								[/#if]
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								[#if say.useId == 4]
									<input type="radio" checked="checked"  name="useId" value="4" onclick="$('#useRemark').hide(300)" /> 儿童房
								[#else]
									<input type="radio" name="useId" value="4" onclick="$('#useRemark').hide(300)" /> 儿童房
								[/#if]
								<br />
								[#if say.useId == 5]
									<input type="radio" checked="checked" name="useId" value="5" onclick="$('#useRemark').hide(300)" /> 会所
								[#else]
									<input type="radio" name="useId" value="5" onclick="$('#useRemark').hide(300)" /> 会所
								[/#if]
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								[#if say.useId == 6]
									<input checked="checked" type="radio" name="useId" value="6" onclick="$('#useRemark').hide(300)" /> 工装
								[#else]
									<input type="radio" name="useId" value="6" onclick="$('#useRemark').hide(300)" /> 工装
								[/#if]
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								[#if say.useId == 7]
									<input checked="checked" type="radio" name="useId" value="7" id="othor" value="7" /> 其他
								[#else]
									<input type="radio" name="useId" value="7" id="othor" value="7" /> 其他
								[/#if]
							[#else]
								<input type="radio"  checked="checked" name="useId" value="1" onclick="$('#useRemark').hide(300)" /> 出租
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="useId" value="2" onclick="$('#useRemark').hide(300)" /> 自用
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="useId" value="3" onclick="$('#useRemark').hide(300)" /> 婚房
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="useId" value="4" onclick="$('#useRemark').hide(300)" /> 儿童房
								<br/>
								<input type="radio" name="useId" value="5" onclick="$('#useRemark').hide(300)" /> 会所
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="useId" value="6" onclick="$('#useRemark').hide(300)" /> 工装
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="useId" value="7" id="othor" value="7" /> 其他
							[/#if]	
							</td>
						</tr>
						[#if say.useId == 7]
							<tr id="useRemark">
								<td class="mar" colspan="2">
									<textarea rows="2" cols="56" class="text" name="useRemark" placeholder="其他用途">${say.useRemark}</textarea>
								</td>
							</tr>
						[#else]
							<tr style="display:none" id="useRemark">
								<td class="mar" colspan="2">
									<textarea rows="2" cols="56" class="text" name="useRemark" placeholder="其他用途"></textarea>
								</td>
							</tr>
						[/#if]
						
						<tr>
							<td>
								<b class="red">*</b>
								5、面积多少？[顺便问问什么户型]
							</td>
						</tr>
						<tr>
							<td class="mar">
								<textarea rows="2" cols="56" class="text" name="area" placeholder="房屋面积">${say.area}</textarea>
							</td>
						</tr>
						<tr>
							<td>
								<b class="red">*</b>
								6、预算多少？[是不是整体预算，施工、建材、家具、家电等等预算]
							</td>
						</tr>
						<tr>
							<td class="mar">
								<textarea rows="2" cols="56" class="text" name="budget" placeholder="装修预算">${say.budget}</textarea>
							</td>
						</tr>
						
						<tr>
							<td>
								<b class="red">*</b>
								7、	局部装修/全部装修 ？ [如果是老房，问是不是整体翻新，是否水电改造，哪里动哪里不动]
							</td>
						</tr>
						<tr>
							<td class="mar">
								<textarea rows="2" cols="56" class="text" name="vrange" placeholder="局部装修/全部装修">${say.vrange}</textarea>
							</td>
						</tr>
						
						
						<tr>
							<td>
								<b class="red">*</b>
								8、	半包/全包/纯设计？
							</td>
						</tr>
						<tr>
							<td class="mar" colspan="2">
							[#if say != null]
								[#if say.method == 2]
									<input type="radio"  checked="checked" name="method" value="2" /> 全包
								[#else]
									<input type="radio"   name="method" value="2" /> 全包
								[/#if]
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								[#if say.method == 1]
									<input checked="checked" type="radio" name="method" value="1" /> 半包
								[#else]
									<input type="radio" name="method" value="1" /> 半包
								[/#if]
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								[#if say.method == 3]
									<input checked="checked" type="radio" name="method" value="3" /> 纯设计
								[#else]
									<input type="radio" name="method" value="3" /> 纯设计
								[/#if]
							[#else]
								<input type="radio"  checked="checked" name="method" value="2" /> 全包
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="method" value="1" /> 半包
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="method" value="3" /> 纯设计
							[/#if]
								
							</td>
						</tr>
						
						<tr>
							<td>
								<b class="red">*</b>
								9、	装修风格
							</td>
						</tr>
						<tr>
							<td class="mar">
								<textarea rows="2" cols="56" class="text" name="style" placeholder="装修风格">${say.style}</textarea>
							</td>
						</tr>
						
						<tr>
							<td>
								<b class="red">*</b>
								10、	什么时候可以安排量房？
							</td>
						</tr>
						<tr>
							<td class="mar">
								<textarea rows="2" cols="56" class="text" name="quantityTime" placeholder="">${say.quantityTime}</textarea>
							</td>
						</tr>
						 
						<tr>
							<td>
								<b class="red">*</b>
								11、	什么时候装修？
							</td>
						</tr>
						<tr>
							<td class="mar">
								<textarea rows="2" cols="56" class="text" name="repairTime" placeholder="">${say.repairTime}</textarea>
							</td>
						</tr>
						
						<tr>
							<td>
								<b class="red">*</b>
								12、	咨询过哪些装修公司？为什么没有选这些装修公司？
							</td>
						</tr>
						<tr>
							<td class="mar">
								<textarea rows="2" cols="56" class="text" name="refer" placeholder="">${say.refer}</textarea>
							</td>
						</tr>
						
						<tr>
							<td>
								<b class="red">*</b>
								13、	业主有没有特殊需求？
							</td>
						</tr>
						<tr>
							<td class="mar">
								<textarea rows="2" cols="56" class="text" name="special" placeholder="">${say.special}</textarea>
							</td>
						</tr>
						
						<tr>
							<td>
								<b class="red">*</b>
								14、	在家可可网站有没有中意的装饰公司？
							</td>
						</tr>
						<tr>
							<td class="mar" colspan="2">
								[#if say.favorite == 0]
									<input type="radio"  name="favorite" value="1" /> 有
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" checked="checked" name="favorite" value="0" /> 没有
								[#else]
									<input type="radio" checked="checked"  name="favorite" value="1" /> 有
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio"  name="favorite" value="0" /> 没有
								[/#if]
							</td>
						</tr>
						
						
						<tr>
							<td>
								<b class="red">*</b>
								15、	家可可平台会按照您的需求匹配最合适的3家装饰公司？
							</td>
						</tr>
						<tr>
							<td class="mar" colspan="2">
								[#if say.vmatch == 0]
									<input type="radio"   name="vmatch" value="1" onclick="$('#matchRemark').hide(300)" /> 是
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" checked="checked" name="vmatch" id="vmatch" value="0" /> 其他
								[#else]
									<input type="radio" checked="checked" name="vmatch" value="1" onclick="$('#matchRemark').hide(300)" /> 是
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio"  name="vmatch" id="vmatch" value="0" /> 其他
								[/#if]
								
							</td>
						</tr>
						[#if say.vmatch == 0]
							<tr id="matchRemark">
								<td class="mar" colspan="2">
									<textarea rows="2" cols="56" class="text" name="matchRemark" placeholder="请填写需求匹配">${say.matchRemark}</textarea>
								</td>
							</tr>
						[#else]
							<tr style="display:none" id="matchRemark">
								<td class="mar" colspan="2">
									<textarea rows="2" cols="56" class="text" name="matchRemark" placeholder="请填写需求匹配"></textarea>
								</td>
							</tr>
						[/#if]
						
						<tr>
							<td>
								<b class="red">*</b>
								16、	业主什么时候接电话方便？
							</td>
						</tr>
						<tr>
							<td class="mar">
								<textarea rows="2" cols="56" class="text" name="expediently" placeholder="">${say.expediently}</textarea>
							</td>
						</tr>
						<tr>
							<td>
								<b class="orderBy">17、是否接通电话[#if say](${say.calledVal})[/#if]:</b>
							    <input type="radio" name="called"  value="1"><label for="status1"> 接通 </label>
							    <input type="radio" name="called"  value="0"><label for="status2"> 未接通</label>
							</td>
						</tr>
						<tr>
							<td>
								<b class="orderBy">18、预约单状态[#if say](${say.statusVal})[/#if]:</b>
							    <input type="radio" name="status"  value="1"><label for="status1"> 未派出 </label>
							    <input type="radio" name="status"  value="2"><label for="status2"> 追踪单</label>
							    <input type="radio" name="status"  value="3"><label for="status1"> 成交单</label>
							    <input type="radio" name="status"  value="4"><label for="status2"> 废单 </label>
							</td>
						</tr>
						[#assign feedbackNum=0]
						[#list feedbacks as item]
							<tr>
								<td>
								    <b class="orderBy">第${item.orderBy}次回访</b>
								    <b class="feedbackTime">回访时间${item.feedbackTimeHandle}</b>
								    <span class="updateOne" rel=["${item.id}","${item.content}"] style="padding: 4px 12px;background: #1D9DA6;border-radius: 3px;color: #fff;cursor: pointer;">修改</span>
								</td>
							</tr>
							<tr>
								<td class="mar">
									<textarea rows="2" cols="56" class="text" placeholder="回访文本" disabled="disabled">${item.content}</textarea>
								</td>
							</tr>
							[#assign feedbackNum=item.orderBy]
						[/#list]
						[#if feedbackNum<3 && freedbackId!=null]
					    <tr>
							<td>
							     <input type="hidden" value="${feedbackNum+1}" class="feedbackNum"/>
							     <input type="hidden" value="${freedbackId}" class="freedbackId"/>
							    <b class="orderBy">第${feedbackNum+1}次回访</b>
							    <b class="feedbackTime2">回访时间
							      <input type="text" class="text Wdate freedbackDate"  id="" onClick="WdatePicker()" readOnly>
							    </b>
							</td>
						</tr>
						<tr>
							<td class="mar">
								<textarea rows="2" cols="56" class="text freedbackContent"  placeholder="回访文本" ></textarea>
							</td>
						</tr>
						<tr>
							<td>
							  <span class="feedbackBtn" style="padding: 4px 12px;background: #1D9DA6;border-radius: 3px;color: #fff;cursor: pointer;">
							           保存
							  </span>
						    </td>
						</tr>
						[/#if]
						<tr>
							<td>
								<b class="red">*</b>
						                   有任何问题随时拨打家可可400客服热线。
							</td>
						</tr>
						<tr>
							<td>
								<b class="red">*</b>
								每一次回访都要记住推荐家可保服务！
											确认信息后台提交给派单组，要求信息清晰准确，预约工作才算结束。
							</td>
						</tr>
						
					</table>
					<br />
					<p>
						<span class="btn" onclick="submit(this)">提交保存</span>
						&nbsp;&nbsp;&nbsp;
						<span class="btn" onclick="history.go(-1)">返回</span>
					</p>
					<br />
				</form>
			</div>
			</div>
		[#include "/common/pagination.ftl"]
	</div>
	</div>
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]

<script>
 $(function(){
     <!--保存回访记录-->
     $(".updateOne").click(function(){
         var $this=$(this),data=$.parseJSON($this.attr('rel'));
	        addMask();
	        $('body').append($('#pannel').html());
	        $('.ui-dialog').data('act','modify');
	        $('.ui-dialog input,.ui-dialog select,.ui-dialog textarea').each(function(index,ele){
	            $(ele).val(data[index]);
	        });
	        center($('.ui-dialog'));
	        
	         $(".saveOneFreeBack").click(function(){
                  var freeBackContent=$(".freeBackContent").val();
                  var id=$(".freeBack").val();
                  $.post("${base}/appoinConditionFeedback/save.do",{id:id,content:freeBackContent},function(rel){
                      justTip(rel.msg);
                      if(rel.ret==0){
                        location.reload();
                      }
                  });
            });
     });
     $(".feedbackBtn").click(function(){
         var pid=$(".freedbackId").val();
         var orderBy=$(".feedbackNum").val();
         var freedbackDate=$(".freedbackDate").val();
         var freedbackContent=$(".freedbackContent").val();
         $.post("${base}/appoinConditionFeedback/save.do",{pid:pid,content:freedbackContent,feedbackTime:freedbackDate,orderBy:orderBy},function(rel){
              justTip(rel.msg);
	          if(rel.ret==0){
	            location.reload();
	          }
         });
     });
     
 });
</script>

<script type="text/html" id="pannel">
<div class="ui-dialog addManager">
	<h2><span class="close"></span><strong>回访文本</strong></h2>
	<form id="send">
	<input type="hidden" name="id" class="freeBack"/>
		<ul>
			<li>
				<textarea rows="5" cols="53" class="text freeBackContent"  placeholder="回访文本" ></textarea>
			</li>
			<li class="btn saveBtn"><span class="btn saveOneFreeBack" rel="add">保存</span></li>
		</ul>
	</form>
</div>	
</script>

<script>
	
	$(function(){
	
		$(".red").parent("td").css("font-weight","bold").css("font-size","15px");
	
		$("#othor").click(function(){
			$('#useRemark').show(300);
		});
		
		$("#vmatch").click(function(){
			$('#matchRemark').show(300);
		});
		
	
	})

	function submit(el){
		if($('textarea[name="communiy"]').val() == ""){
			justTip("请输入用户预约地址！");
			return;
		}
		
		if($('textarea[name="area"]').val() == ""){
			justTip("请输入用户装修面积！");
			return;
		}
		
		if($('textarea[name="budget"]').val() == ""){
			justTip("请输入用户装修预算！");
			return;
		}
		
		if($('textarea[name="vrange"]').val() == ""){
			justTip("请输入局部装修/全部装修！ ");
			return;
		}
		
		if($('textarea[name="style"]').val() == ""){
			justTip("请输入用户装修风格！");
			return;
		}
		
		if($('textarea[name="refer"]').val() == ""){
			justTip("请输入用户咨询过哪些装修公司！");
			return;
		}
		
		if($('textarea[name="special"]').val() == ""){
			justTip("请输入用户有没有特殊需求！");
			return;
		}
		if($('textarea[name="expediently"]').val() == ""){
			justTip("请输入用户什么时候接电话方便！");
			return;
		}
	
		$(el).parents("form").submit();
	}

</script>

	<div id="topic">
	        
			<table id="dialog">
				<tr>
					<td>用户昵称：</td>
					<td>
						${appointment.user}
					</td>
				</tr>
				<tr>
					<td>联系电话：</td>
					<td>
						${appointment.mobile}
					</td>
				</tr>
			    <tr>
					<td >城区：</td>
					<td>
						${appointment.regionname}
					</td>
				</tr>
				<tr>
					<td >小区名称：</td>
					<td>
						${appointment.community}
					</td>
				</tr>
				<tr>
					<td >房屋面积：</td>
					<td>
						${appointment.space}m²
					</td>
				</tr>
				<tr>
					<td>装修预算：</td>
					<td>
						${appointment.budget}
					</td>
				</tr>
					
				<tr>
					<td>装修方式：</td>
					<td>
						${appointment.methodVal}
					</td>
				</tr>
				<tr>
					<td >预约类型：</td>
					<td>
						活动单
					</td>
				</tr>
				<tr>
					<td>整装/局部：</td>
					<td>
						${appointment.wholeHouseHandle}
					</td>
				</tr>
				<tr>
					<td >房屋用途：</td>
					<td>
						${appointment.houseTypeVal}
					</td>
				</tr>
				<tr>
					<td >预约地址：</td>
					<td colspan="3">
						${appointment.address}
					</td>
				</tr>
			</table>
	 </div>
</div>


</body>
</html>