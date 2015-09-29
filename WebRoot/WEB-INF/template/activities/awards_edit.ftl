<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>添加"${act.rank}"奖项</title>
[#include "/common/res.ftl"]
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="${base}/scripts/form.js"></script>
<style>
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

.choujiangEdit{font-size: 12px;}
.choujiangEdit thead tr th.null{visibility: visible;width: 65px;text-align: center;padding: 0;}
.choujiangEdit tr{margin-bottom: 5px;display: block;}
.choujiangEdit tr td{margin-right: 1px;display: inline-block;}
.choujiangEdit th{height: 30px;padding: 3px 5px;width: 65px;text-align: center;margin-right: 5px;display: inline-block;line-height: 36px;font-weight: bold;}
.choujiangEdit th span{color: #f30;}
.choujiangEdit input{width: 65px;height: 30px;padding: 2px 5px;}
.rewardRrade{width: 65px;text-align: center;}
.p{margin-left: 70px;}
.err{background:#d7b9c0;border:1px double red;}
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
				 <div class="choujiangEdit">
				 	 <input type="hidden" name="activeId" value="${act.id}" />
				 	<form id="send" action="${base}/activities/savAwards.xhtml" method="post" />
			        <table cellpadding="5">
			            <thead>
			                <tr>
			                    <th class="null"></th>
			                    <th><span>*</span>奖项名称</th>
			                    <th><span>*</span>奖品名称</th>
			                    <th><span>*</span>奖品数量</th>
			                    <th><span>*</span>中奖概率</th>
			                </tr>
			            </thead>
			            <tbody>
			            [#list asas as a]
			            	 <tr class="clearfix">
			                    <td class="rewardRrade">
			                                                             奖项${item_index+1}：
			                    </td>
			                    <td>
			                        <input type="text" name="awar[${item_index+1}].rank" class="text" class="" value="${a.rank}" />
			                    </td>
			                    <td>
			                        <input type="text" name="awar[${item_index+1}].prizeName" class="text" value="${a.prizeName}" />
			                    </td>
			                    <td>
			                        <input type="text" name="awar[${item_index+1}].prizeNum" class="text" value="${a.prizeNum}" />
			                    </td>
			                    <td>
			                        <input type="text" name="awar[${item_index+1}].probability" class="text" value="${a.probability}" />%
			                    </td>
			                </tr>
			            [/#list]
			            </tbody>
			        </table>
			        </form>
			        <br/>
			        <p class="p">
			        	<span class="btn" id="add">添加奖项</span> 
						<span class="btn" id="form">提交</span> 
						<span class="btn" onclick="history.go(-1)">返回</span>
					</p>
			        <br/>
			    </div>
				
			</div>
		</div>
	</div>
	
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]
<script>

	$(function(){
		$("#add").click(function(){
			count = $("tr").length;
			var html = ' <tr class="clearfix">'
					 + '<td class="rewardRrade">奖项'+count+'： </td>'
					 + '<td><input type="text" xiang="'+count+'" tips="奖项名称" name="awar['+(count-1)+'].rank" class="text" value="" /></td>'
					 + '<td><input type="text" xiang="'+count+'" tips="奖品名称" name="awar['+(count-1)+']prizeName" class="text" value="" /></td>'
					 + '<td><input type="text" xiang="'+count+'" tips="奖品数量" name="awar['+(count-1)+']prizeNum" class="text" value="" /></td>'
					 + '<td><input type="text" xiang="'+count+'" tips="中奖概率" name="awar['+(count-1)+']probability" class="text" value="" />%</td>'
					 + '</tr>'
			$("tbody").append(html);
		
		});
		
		$("#form").click(function(){
			$('input[type="text"]').each(function(){
				if($(this).val() == ""){
					$(this).addClass("err");
				}
			});
			$('input[type="text"]').each(function(){
				if($(this).val() == ""){
					var count = $(this).attr("xiang");
					var tips = $(this).attr("tips");
					justTip("请填写奖项"+count+"的"+tips+"！");
					$(this).addClass("err");
					return false;
				}
				
			});
			
			$("#send").submit();
			
		});
		
		$("body").delegate('input','focus',function(){
			$(this).removeClass("err");
		});
		
	});

</script>
</body>
</html>