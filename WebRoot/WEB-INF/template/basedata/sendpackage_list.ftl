<!DOCTYPE HTML>
<html>
<head>
<title>发红包列表</title>
<meta charset="utf-8">
[#include "/common/res.ftl"]
	<script type="text/javascript" src="${base}/scripts/form.js"></script>
	<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<style>
	.add{margin-bottom:10px;float:right}
	.formDiv{padding:20px;}
	tr{
		padding-top:10px;
	}
	td{height:55px;}
	.td_left{text-align:right;font-size:15px;font-weight:bold;}
</style>
</head>

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
						<form id="search">
						<ul>					
							<li>
								<span class="sp"><strong>搜索：</strong><input type="text" class="text" name="name" placeholder="请输入关键字"></span>
								<strong>用户类型：</strong>
								<div class="select area mr10">
									<i class="new">&#xf107;</i><span>--请选择--</span>
									<p>
										 <a value="0">--报名--</a>
										 <a value="1">--购卡--</a>
										 <a value="2">--投诉--</a>
									</p>
									<input type="hidden" name="type" value="">
								</div>
								<strong>活动类型：</strong>
								<div class="select area mr10" style="width:200px;">
									<i class="new">&#xf107;</i><span style="width:200px;">--请选择--</span>
									<p style="width:200px;">
										[#list themeList as t]
											<a value="${t.id}">${t.title}</a>
										[/#list]
									</p>
									<input type="hidden" name="themeId" value="">
								</div>
								<span class="btn search">查 询</span>
							</li>
						</ul>
						</form>
					</div>	
					 <div class="add">
				    	<span class="btn send2">导入</span>
				    	<span class="btn send2">批量发红包</span>
				    </div>
				  	<table class="format">
						<thead>
							<th width="5%" ><input type="checkbox" class="ckAll" />全选 <input type="checkbox" class="fxAll" />反选</th>
							<th width="6%">用户昵称</th>
							<th width="10%">手机号</th>
							<th width="10%">所属活动</th>
							<th width="5%">性别</th>
							<th width="10%">装修需求</th>
							<th width="5%">用户类型</th>
							<th width="5%">已领取红包数</th>
							<th width="5%">已领取金额</th>
							<th width="7%">跟进客服</th>
							<th width="7%">操作</th>
						</thead>
						<tbody>
							[#list pagination.dataList as item]
							<tr>
								<td><input type="checkbox" value="${item.mid}" name="ids" class="ids" /></td>
								<td>${item.nickname}</td>	
								<td>${item.phone}</td>	
								<td>${item.title}</td>	
								<td>${item.genderString}</td>	
								<td>${item.decorate}</td>	
								<td>${item.type}</td>	
								<td>${item.takeCount}</td>	
								<td>${item.takeMoney}</td>	
								<td>${item.adminName}</td>	
								<td>
									<span class="btn send" rel='${item.mid}'>发红包</span>
								</td>	
							</tr>
							[/#list]
						</tbody>
			       </table>
			      [#include "/common/pagination.ftl"]
			  </div>
		</div>
		[#include "/common/menu.ftl"]
	</div>
<!-- footer -->
 [#include "/common/foot.ftl"]
</body>

<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr>
		<td><input type="checkbox" value="<%=result.elements[i].mid%>" name="ids" class="ids" /></td>
		<td><%=result.elements[i].nickname%></td>	
		<td><%=result.elements[i].phone%></td>	
		<td><%=result.elements[i].title%></td>	
		<td><%=result.elements[i].genderString%></td>	
		<td><%=result.elements[i].decorate%></td>	
		<td><%=result.elements[i].type%></td>	
		<td><%=result.elements[i].takeCount%></td>	
		<td><%=result.elements[i].takeMoney%></td>	
		<td><%=result.elements[i].adminName%></td>	
		<td>
			<span class="btn" rel='<%=result.elements[i].mid%>'>发红包</span>
		</td>	
    </tr>	
<%}%>

</script>

<script type="text/html" id="pannel2">
	<div class="ui-dialog addManager" style="width:400px;">
		<h2><span class="close"></span><strong>发红包</strong></h2>
		<form id="send" action="${base}/admin/redPackage/send.xhtml?mid=${mid}&pid=${pid}" method="post">
			<input type="hidden" name="paramStr" id="idsStr" value="" />
			<input type="hidden" name="op" id="op" value="many" />
			<div class="formDiv">
				<table>
					<tr class="td_left">
						<td><strong><b class="red">*</b> 选择红包：</strong></td>
						<td>
							<select name="rid" style="width:195px;" id="select2">
								<option value="-1">--请选择--</option>
								[#list packList as p]
									<option value="${p.id}">${p.name}</option>
								[/#list]
							</select>
						</td>
					</tr>
					<tr class="td_left">
						<td><strong><b class="red">*</b> 红包金额：</strong></td>
						<td><input style="width:195px;" disabled="disabled" type="text" class="text" name="iprice" placeholder="红包金额"></td>
					</tr>
					<tr class="td_left">
						<td><strong><b class="red">*</b> 发送总个数：</strong></td>
						<td><input style="width:195px;" disabled="disabled" type="text" class="text" name="icount" value="1" placeholder="发送总个数"></td>
					</tr>
					<tr class="td_left">
						<td><strong><b class="red">*</b> 发送总金额：</strong></td>
						<td><input style="width:195px;" disabled="disabled" type="text" class="text" name="imoney" placeholder="发送总金额"></td>
					</tr>
					<tr class="td_left">
						<td colspan="2"><span onclick="return valiData(this)" class="btn" rel="add">发送</span></td>
					</tr>
				</table>
			</div>
		</form>
	</div>
</script>


<script type="text/html" id="pannel">
	<div class="ui-dialog addManager" style="width:400px;">
		<h2><span class="close"></span><strong>发红包</strong></h2>
		<form id="send" action="${base}/admin/redPackage/send.xhtml?mid=${mid}&pid=${pid}" method="post">
			<input type="hidden" name="rmid" id="rmid" value="" />
			<input type="hidden" name="op" id="op" value="one" />
			<div class="formDiv">
				<table>
					<tr class="td_left">
						<td><strong><b class="red">*</b> 选择红包：</strong></td>
						<td>
							<select name="rid" style="width:195px;" id="select">
								<option value="-1">--请选择--</option>
								[#list packList as p]
									<option value="${p.id}">${p.name}</option>
								[/#list]
							</select>
						</td>
					</tr>
					<tr class="td_left">
						<td><strong><b class="red">*</b> 红包金额：</strong></td>
						<td><input style="width:195px;" disabled="disabled" type="text" class="text" name="price" placeholder="红包金额"></td>
					</tr>
					<tr class="td_left">
						<td><strong><b class="red">*</b> 发送总个数：</strong></td>
						<td><input style="width:195px;" disabled="disabled" type="text" class="text" name="count" value="1" placeholder="发送总个数"></td>
					</tr>
					<tr class="td_left">
						<td><strong><b class="red">*</b> 发送总金额：</strong></td>
						<td><input style="width:195px;" disabled="disabled" type="text" class="text" name="money" placeholder="发送总金额"></td>
					</tr>
					<tr class="td_left">
						<td colspan="2"><span onclick="return valiData(this)" class="btn" rel="add">发送</span></td>
					</tr>
				</table>
			</div>
		</form>
	</div>
</script>


<script>

function valiData(el){
	$(el).parents("form").submit();
}

$(function(){

	$(".ckAll").click(function(){
		$(".ids").prop("checked",$(this).prop("checked"));
	});
	$(".fxAll").click(function(){
		$(".ids").each(function(){
			$(this).prop("checked",!($(this).prop("checked")))
		});
	});

	$('body').delegate('#select','change',function(){
		var url = "${base}/admin/redPackage/getRedPackageMoneyById.do";
		$.get(url,{rid:$(this).val()},function(data){
			$('input[name="price"]').val(data.price);
			$('input[name="money"]').val(data.price);
		});
	});
	
	$('body').delegate('#select2','change',function(){
		var url = "${base}/admin/redPackage/getRedPackageMoneyById.do";
		$.get(url,{rid:$(this).val()},function(data){
			$('input[name="iprice"]').attr("value",data.price);
			var count = $('input[name="icount"]').val();
			$('input[name="imoney"]').val(count * data.price)
		});
	});

	$('body').delegate('.send','click',function(){
		var $this=$(this),data=$.parseJSON($this.attr('rel'));
	    addMask();
	    $('body').append($('#pannel').html());
	    $('#rmid').attr("value",data);
	    center($('.ui-dialog'));
	});
	
	$('body').delegate('.send2','click',function(){
		var flag = false;
		var count = 0;
		var ids = new Array();
		$(".ids").each(function(){
			if($(this).prop("checked")){
				flag = true;
				ids.push($(this).attr("value"));
				count++;
			}
		});
		if(!flag){
			justTip("请先勾选用户！");return;
		}
	    addMask();
	    $('body').append($('#pannel2').html());
	    $('input[name="icount"]').val(count);
	    $('#idsStr').attr("value",ids.join(','))
	    center($('.ui-dialog'));
	});

	$('.ui-paging').page({
		url:'${base}/admin/redPackage/sendRedPackageListPage.do',
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

</html>