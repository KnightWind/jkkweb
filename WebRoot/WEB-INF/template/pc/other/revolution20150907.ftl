<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>装修革命</title>
<link rel = "Shortcut Icon" href="http://www.jiakeke.com/jtxweb/images/favicon.ico">
<script src='${base}/scripts/jquery-1.9.1.min.js'></script>
<link href="${base}/styles/pc/common.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${base}/styles/activity/revolution.css">
<script type="text/javascript" src="${base}/scripts/common.js"></script>

<style>
.justTip{ padding: 20px; color: #fff; line-height: 20px; border-radius: 5px; text-align: left; display:none; font-size: 14px; background: #515558;  position: absolute; z-index: 10008; display: inline-block; }
.justTip i { font-size: 16px; color: #fff; margin-right: 5px;}
</style>
</head>

<body>
   <div class="header">
         [#include "/pc/commonPc/menuPc.ftl"]
    </div>
    <div class="revolution">
        <div class="content">
                <ul>
		          <li><img src="${base}/images/activity/active_1.jpg" /></li>
		          <li><img src="${base}/images/activity/active_2.png" /></li>
		          <li><img src="${base}/images/activity/active_3.png" /></li>
		          <li id="step1"><img src="${base}/images/activity/active_4.png" /></li>
		          <li id="step2"><img src="${base}/images/activity/active_5.png" /></li>
		          <li id="step3"><img src="${base}/images/activity/active_6.png" /></li>
		          <li id="step4"><img src="${base}/images/activity/active_7.png" /></li>
		          <li id="step5"><img src="${base}/images/activity/active_12.png" /></li>
		          <li id="step6"><img src="${base}/images/activity/active_8.png" /></li>
		          <li id="step7"><img src="${base}/images/activity/active_9.png" /></li>
		          <li id="step8"><img src="${base}/images/activity/active_10.png" /></li>
		          <li id="step9"><img src="${base}/images/activity/active_11.jpg" /></li>
        		</ul>
            </div>
            <div class="dataTimeShadow"></div>
            <div class="dataTime">
                <div class="timeCount clr">
                   <b class="bigFont" style="margin-left: 300px;">火爆进行中，现在出发还来得及！</b>
                   <!-- <span class="day"></span>
                    <b class="width50">天</b>
                    <span class="hour"></span>
                    <b class="width50">小时</b>
                    <span class="minute"></span>
                    <b class="width50">分</b>
                    <span class="second"></span>
                    <b class="width50">秒</b>-->
                </div>
            </div>
        </div>
        <div class="slidePosition">
            <ul>
                <li class="slide" rel="1">签到有礼</li>
                <li class="slide" rel="2">众筹狂欢</li>
                <li class="slide" rel="3">VIP特享</li>
                <li class="slide"rel="4">幸运抽奖</li>
                <li class="slide" rel="5">大牌云集</li>
                <li class="slide" rel="6">大牌PK</li>
                <li class="slide" rel="7">红包抽奖</li>
                <li class="slide" rel="8">大牌联购</li>
                <li class="slide" rel="9">活动地点</li>
                <li class="backTop" style="display:block;">回到顶部</li>
            </ul>
        </div>
           <div class="suspend">
            <div class="write">
              <h2>一键购齐幸福家</h2>
              <p>
                <input type="text" placeholder="您的称呼" class="name text" id="name" maxlength="10" />
                <input type="text" placeholder="您的电话" class="phone text" maxlength="11"/>
                <input type="button" value="免费报名" class="submitBtn btn"/>
                <span>报名前500位可享礼包奖励！</span>
                <b><i></i><strong>还没选好装修公司</strong></b>
              </p>
            </div>
            <div class="saoweixin">
              <span>扫一扫，关注微信</span>
              <img src="/jtxweb/images/pc/erweima_small.png" />
              <span>关注微信参与抽奖预购<br />豪华大礼包等你来拿！</span>
            </div>
            <a style="margin-left:10px;" target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=2710475210&site=qq&menu=yes"><img border="0" src="http://wpa.qq.com/pa?p=2:2710475210:53" alt="如果您想了解活动的详细内容，请点击咨询。" title="如果您想了解活动的详细内容，请点击咨询。"/></a>
        </div>
    <div class="bgColor"></div>
    <div class="footer">
        <div class="footerContent">
            <div class="center">
                <p class="clearfix"><a href="#">动态</a><a href="#">招商加盟</a><a href="#">用户协议</a><span class="callNumber">400-1118-108</span></p>
                <b>北京居天下网络科技有限公司　010-58621160  58621669</b>
                <b>地址：北京朝阳区十八里店周家庄村288号融望大酒店6层</b>
                <b>居天下版权所有Copyright &copy; 2013-2015 AdTime Corporation All Right Reserved 京ICP备15010944</b>
                <img src="${base}/images/activity/vip.png" style='width:100px;'>
            </div>
        </div>
    </div>
</body>
</html>

<script>
  $(function(){
       $(".submitBtn").click(function(){
         var decoration="";
       	 if($(".write b i").hasClass("select")){
        	decoration = 1;
      	 }
         else{
            decoration = 0;
         }
          var name=$(".name").val();
          var phone=$(".phone").val();
          var reg =/^1[0-9]{10}$/;
          var rell=reg.test(phone);
          if(rell==false){
             justTip("请输入正确的手机号码");
             return false;
          }          
          $.post("${base}/main/activity/enjoin.do",{name:name,phone:phone,decoration:decoration},function(rel){
            justTip(rel.msg);
          });
       });
  });
</script>

<script type="text/javascript">
  $(window).load(function(){
      $('.slidePosition ul li.slide').bind('click',function(){
         var value=$(this).attr('rel');
         console.log($("#step"+value).offset().top);
         $('html,body').animate({scrollTop:$("#step"+value).offset().top});
      });
      $(".backTop").bind("click",function(){
         $('html,body').animate({scrollTop:0});
      })
  });

  //活动倒计时
  function countTime(year,month,day,hour,obj){ 
      var now = new Date(),
          endDate = new Date(year, month-1, day,hour,0,0), 
          leftTime=endDate.getTime()-now.getTime(),
          leftsecond = parseInt(leftTime/1000),
          day1=Math.floor(leftsecond/(60*60*24)),
          hour=Math.floor((leftsecond-day1*24*60*60)/3600),
          minute=Math.floor((leftsecond-day1*24*60*60-hour*3600)/60),
          second=Math.floor(leftsecond-day1*24*60*60-hour*3600-minute*60);
          if(day1 < 10){
              day1 = "0" + day1;
          } 
          if(hour < 10){
              hour = "0" + hour;
          } 
          if(minute < 10){
              minute = "0" + minute;
          }
          if(second < 10){
              second = "0"+ second;
          }
          $(".day").text(day1);
          $(".hour").text(hour);
          $(".minute").text(minute);
          $(".second").text(second);
          //obj.text(day1+"天"+hour+"小时"+minute+"分"+second+"秒");
  } 
  setInterval(function(){countTime(2015,9,5,9,$('.timeCount'));},1000);
    
  //滚动出现右边定位
    $(window).scroll(function(){
      var value = $(window).scrollTop();
      if(value>500){
        $(".slidePosition").show();
      }
      else{
        $(".slidePosition").hide();
      }
      
      var nowHeight = $(window).scrollTop();
      console.log(nowHeight);
      if(nowHeight>4634){
        $(".dataTimeShadow").css("bottom",192);
        $(".dataTime").css("bottom",192);
      }
      else{
        $(".dataTimeShadow").css("bottom",0);
        $(".dataTime").css("bottom",0);
      }
    });
    

    $(".write b i").bind("click",function(){
        $(this).toggleClass("select");
    })
    
    $('#name').bind('blur',function(){
        var value=$(this).val();
        var reg =/^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$/;
        if(!reg.test(value)){
          $(this).val('');
          alert('输入格式有误');
        }
    })
    </script>
    <script>
	  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
	  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
	  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
	  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
	  ga('create', 'UA-20653174-7', 'auto');
	  ga('send', 'pageview');
	</script>
	<script src="http://surl.aliapp.com/?15450"></script>