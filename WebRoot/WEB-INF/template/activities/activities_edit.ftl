<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>添加活动</title>
[#include "/common/res.ftl"]
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="${base}/scripts/form.js"></script>
<style type="text/css">
    .edit{font-size: 12px;}
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
<style>
</style
</head>

<body>
<!-- header -->
[#include "/common/head.ftl"]
<!-- wrap -->
<div class="wrap index bc clr">
	<div class="conWrap fl">
		[#include "/common/nav.ftl"]
		<div class="conBox">
			<div class="white border mb10 sp10">
				<form action="${base}/activities/save.xhtml?mid=${mid}&pid=${pid}" method="post" />
					<div class="edit">
	        			<input type="hidden" name="id" value="${act.id}" />
				        <div>
				             <ul class="text">
				                <li class="clr">
				                <span>活动名称：</span>
				                <p>
				                    <input type="text" class="text" name="name" placeholder="活动名称" value="${act.name}" />
				                    <strong><b>*</b>不可超过20个字</strong>
				                </p>
				            </li>
				          <!--  <li class="clr">
				                <span>回复关键字：</span>
				                <p>
				                    <input type="text" class="text" name="keyword" placeholder="回复关键字" value="${act.keyword}" />
				                    <strong><b>*</b>建议中文词组,回复时需全匹配.</strong>
				                </p>
				            </li>-->
				            </ul>
				        </div>
				        <div>
				            <ul class="text">
				                <li class="clr">
				                    <span>活动开始时间：</span>
				                    <p>
				                        <input type="text" class="text Wdate" name="starDate" value="${act.startTimeString}" onClick="WdatePicker({ dateFmt:'yyyy-MM-dd HH:mm:ss'})" >
				                    </p>
				                </li>
				                <li class="clr">
				                    <span>活动结束时间：</span>
				                    <p>
				                        <input type="text" class="text Wdate" name="endDate" value="${act.endTimeString}" onClick="WdatePicker({ dateFmt:'yyyy-MM-dd HH:mm:ss'})" >
				                    </p>
				                </li>
				            </ul>
				        </div>
				        <div>
				            <ul class="time">
				                <li class="clr">
				                    <span>奖品数量显示：</span>
				                    <p>
				                       [#if act.displayNum = 2]
				                        <label><input type="radio" id="checkbox1"  name="displayNum" value="1" class="checkbox" />显示 </label>
				                        <label><input type="radio" id="checkbox2" checked="checked" name="displayNum" class="checkbox" value="2" />不显示 </label>
				                       [#else]
				                        <label><input type="radio" id="checkbox1" checked="checked" name="displayNum" value="1" class="checkbox" />显示 </label>
				                        <label><input type="radio" id="checkbox2" name="displayNum" class="checkbox" value="2" />不显示 </label>
				                       [/#if]
				                        <strong><b>*</b>取消后在活动页面中将不会显示奖品数量</strong>
				                    </p>
				                </li>
				                <li class="clr">
				                    <span>每人参与总次数：</span>
				                    <p>
				                        <input type="text" name="total" value="${act.total}" class="text" />次
				                        <strong><b>*</b>次数必须在1-1000范围之间</strong>
				                    </p>
				                </li>
				                <li class="clr">
				                    <span>每人每天参与次数：</span>
				                    <p>
				                        <input type="text" name="partNum" value="${act.partNum}" class="text" />次
				                        <strong><b>*</b>次数必须是大于1且不能大于每人参与的总次数</strong>
				                    </p>
				                </li>
				                <li class="clr">
				                    <span>每天最多出奖数量：</span>
				                    <p>
				                        <input type="text" name="maxNum" value="${act.maxNum}" class="text" />次
				                        <strong><b>*</b>填写0代表不限制出奖数量</strong>
				                    </p>
				                </li>
				                
				                <li class="clr">
				                    <span>抽奖方式：</span>
				                    <p>
				                        <label><input type="radio" name="lotteryType" value="1" checked="checked" class="checkbox" />必定中奖</label>
				                        <label><input type="radio" name="lotteryType" value="2" [#if act.lotteryType == 2] checked="checked"[/#if] class="checkbox" />概率中奖</label>
				                    </p>
			                        <div id="time" style="margin-left:30px; display:[#if act.lotteryType == 1] inline-block[#else] none [/#if];">
			                        	&nbsp;&nbsp;每抽奖<input type="number" name="times" value="${act.times}"/>人次 必中<input type="number" name="luckyTimes" value="${act.luckyTimes}"/>人次
			                        	<strong><b>*</b>如：每抽100人次,则必有10人中奖</strong>
			                        </div>
				                </li>
				                
				                <li class="clr last">
				                    <span>活动介绍：</span>
				                    <p class="">
				                        <textarea class="text" name="description" placeholder="活动介绍">${act.description}</textarea>
				                    </p>
				                </li>
				            </ul>
				        </div>
				    </div>
				    <br/>
					<p class="p">
						<span class="btn" onclick="return vaibleData(this)">提交</span> 
						<span class="btn" onclick="history.go(-1)">返回</span>
					</p>
				</div>
			</form>
		</div>
	</div>
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]
<script>

function vaibleData(el){
	if($('input[name="name"]').val() == ""){
		justTip("请输入活动名称！");
		return false;
	}
	
	if($('input[name="startTime"]').val() == ""){
		justTip("请输入活动开始时间！");
		return false;
	}
	
	if($('input[name="endTime"]').val() == ""){
		justTip("请输入活动结束时间！");
		return false;
	}
	
	if($('input[name="total"]').val() == ""){
		justTip("请输入每人参与总次数！");
		return false;
	}
	
	if(!/^\d+$/.test($("input[name='total']").val())){
		justTip("每人参与总次数为数字 !")
		return false;
	}
	
	if($('input[name="partNum"]').val() == ""){
		justTip("请输入每人每天参与次数！");
		return false;
	}
	
	if(!/^\d+$/.test($("input[name='partNum']").val())){
		justTip("每人每天参与次数为数字 !")
		return false;
	}
	
	if($('input[name="maxNum"]').val() == ""){
		justTip("请输入每天最多出奖数量！");
		return false;
	}
	
	if(!/^\d+$/.test($("input[name='maxNum']").val())){
		justTip("每天最多出奖数量为数字 !")
		return false;
	}
	
	var lotteryType	= $("input:radio:checked[name=lotteryType]").val();
	if(lotteryType == 1) {
		var times = $("input[name=times]").val();
		var luckyTimes = $("input[name=luckyTimes]").val();
		if(!/[1-9]+[0-9]*/.test(times) || times < 1) {
			justTip("抽奖次数必需为大于0的正整数");
			$("input[name=times]").focus().select();
			return false;
		}
		if(!/[1-9]+[0-9]*/.test(luckyTimes)) {
			justTip("中奖人数必需为大于0的正整数");
			$("input[name=luckyTimes]").focus().select();
			return false;
		}
		
		if(times < luckyTimes) {
			justTip("中奖人数必需小于或者等于抽奖次数");
			$("input[name=luckyTimes]").focus().select();
			return false;
		}
	} else {
		$("input[name=times]").val("");
		$("input[name=luckyTimes]").val("");
	}
	
	$(el).parents("form").submit();
	
}
$(function(){
	$('input[name="starDate"]').change(function(){
		alert($(this).val());
	});
	
	$("input[name=lotteryType]").click(function(){
		var lotteryType	= $(this).val();
		if(lotteryType == 1) {
			$("#time").show();
		} else {
			$("#time").hide();
		}
	});

})

</script>
</body>
</html>