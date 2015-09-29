<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>抽奖</title>
<script src="${base}/scripts/jquery-1.9.1.min.js"></script>
<style>
*{ margin: 0; padding: 0;}
body{ background: url(${base}/images/materials/lbg.png) center 0; font-family:'微软雅黑';}
.lottery{ width: 528px; height: 506px;/* background: #f00;*/ margin: 125px auto 0;}
.lottery h2{ width: 475px; height: 188px; margin: 0 auto 20px;}
.lottery h2 img{ display: block;}
.lottery p.phone{ width: 527px; height: 113px; line-height: 113px; font-size: 50px; color: #fff; text-align: center; background: url(${base}/images/materials/fbg.png) no-repeat;}
.lottery p.type{ height: 84px; line-height: 84px; text-align: center; text-indent: 54px;}
.lottery p.type input{ width:16px; height: 16px; margin-right: 16px; vertical-align: middle; cursor: pointer;}
.lottery p.type label{ margin-right: 36px; cursor: pointer;}
.lottery a.btn{ width: 456px; height: 100px; display: block; margin: 0 auto; border-radius: 5px; line-height: 100px; text-align: center; color: #fff; background: #ff8901; font-size: 50px; cursor: pointer;}
</style>
</head>

<body>
<div class="lottery">
	<h2><img src="${base}/images/materials/tip.png" alt="中奖了"></h2>
	<p class="phone" id="phone"></p>
	<p class="type">
		<input type="checkbox" id="type1" value="0"><label for="type1">报名</label>
		<input type="checkbox" id="type2" value="1" checked><label for="type2">签到</label>
		<input type="checkbox" id="type3" value="0"><label for="type3">购卡</label>
		<input type="checkbox" id="type4" value="0"><label for="type4">线下购卡</label>
	</p>
	<a class="btn">开始抽奖</a>
</div>
</body>
<script>
function rnd(n,m){return Math.floor(Math.random()*(m-n+1)+n)};//取随机数；
$(function(){
	var flag=0,
		$phone=$('#phone'),
		thenum='',
		pertime=null;
	$('.btn').bind('click',function(){
		var that=$(this),_num=$('input:checked').length;
		if(that.hasClass('open')){$phone.text(thenum);thenum='';clearInterval(pertime);that.removeClass('open').text('开始抽奖');return false;};	
		if(_num<1){alert('请先选择类型');return false};//至少选一个类型才能抽奖；
		$.ajax({
			url:'${base}/material/index/lottery.do',//取数据url
			data:{baoming:$('#type1').val(),qiandao:$('#type2').val(),gouka:$('#type3').val(),xianxia:$('#type4').val()},//状态为1 为选中状态 
			error:function(){ return false},
			success:function(data){
					if(data.code == 0){
						/*callback-start*/
						var phone=data.phoneList;//phone 手机号码列表
						thenum=data.thenum;//thenum 中奖号码
						that.text('停').addClass('open');
						pertime=setInterval(function(){
									var len=phone.length-1,num=rnd(0,len);
									$phone.text(phone[num].phone);
								},50);
						/*callback-end*/
					}
					if(data.code == 2){
						alert("主持人，所有用户都中奖了！");
						return;
					}
					if(data.code == 3){
						alert("主持人，没有满足条件的用户！");
						return;
					}
					if(data.code == 4){
						alert("主持人，此号码已经中过奖咯！");
						return;
					}
					
			}
		});
	});
	$('.type input:checkbox').bind('change',function(){
		if($(this).prop('checked')){$(this).val(1);}else{$(this).val(0);}
	});
})
</script>
</html>