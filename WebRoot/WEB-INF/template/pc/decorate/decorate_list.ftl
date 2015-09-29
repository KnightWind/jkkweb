<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>装修图库</title>
<meta name="description" content="">
<meta name="keywords" content="">
<link href="${base}/styles/pc/common.css" rel="stylesheet">
<link href="${base}/styles/pc/decorateGallery.css" rel="stylesheet">
<script type="text/javascript" src="${base}/scripts/jquery-1.9.1.min.js"></script>
<body>
    <div class="header">
       [#include "/pc/commonPc/menuPc.ftl"]
    </div>
    <div class="picShow column">
        <div class="select clearfix">
            <p class="left fl">
                <span>户型</span>
                <span>空间</span>
                <span style="padding:0;">风格</span>
            </p>
            <div class="fl right">
            	<input type="hidden" id="type1" value="0">
                <ul class="clearfix">
                    <li class="cur alignL choosable" rel="0">全部</li>
                    [#list designCate as item]
	                    [#if item.label='huxing']
	                       <li class="choosable" rel="${item.id}">${item.cateName}</li>
	                    [/#if]
                    [/#list]
                    <li class="open"><img src="${base}/images/pc/arrow_down.png"></li>
                </ul>
                <input type="hidden" id="type2" value="0">
                <ul class="clearfix">
                    <li class="cur alignL choosable" rel="0">全部</li>
                    [#list designCate as item]
	                    [#if item.label='kongjian']
	                       <li class="choosable" rel="${item.id}">${item.cateName}</li>
	                    [/#if]
                    [/#list]
                    <li class="open"><img src="${base}/images/pc/arrow_down.png"></li> 
                </ul>
                <input type="hidden" id="type3" value="0">
                <ul class="clearfix" style="border-bottom: none;">
                    <li class="cur alignL choosable" rel="0">全部</li>
                    [#list designCate as item]
	                    [#if item.label='fengge']
	                       <li class="choosable" rel="${item.id}">${item.cateName}</li>
	                    [/#if]
                    [/#list]
                    <li class="open"><img src="${base}/images/pc/arrow_down.png"></li>
                </ul>
            </div>
        </div>
        <div class="pics clearfix" id="pics" rel="${countPage}">
            <ul class="list">
		        [#list designList as dl]
	                <li class="box" rel="${dl.id}"><img src="${dl.path}"><b>${dl.space}m²/${dl.money}万/${dl.houseType}/${dl.style}</b></li>
		        [/#list]
            </ul>
        </div>
        <div class="noMore">
            <img src="../images/pc/no_more.png" />
        </div>
    </div>
    <div class="shadow"><b class="close"></b></div>
    <div class="picDetails">
        <span class="prev tabPic"></span>
        <div class="morePic clearfix">
            <p class="bigPic">
                <img src="">
            </p>
            <div class="smallPic">
                <p class="top clearfix">
                    <img class="fl" src="" id="tximg">
                    <span class="fl" id="info"><strong></span>
                </p>
                <div class="allPic">
                    
                </div>
            </div>
        </div>
        <span class="next tabPic"></span>
    </div>

    <div class="footer">
        <div class="footerContent">
            <div class="center">
                <p class="clearfix"><a href="#">动态</a><a href="#">招商加盟</a><a href="#">用户协议</a><span class="callNumber">400-888-8888</span></p>
                <b>违法和不良信息举报电话: 028-62836666</b>
                <b>www.jiakeke.com 2015-2019 © All Rights Reserved.</b>
                <b>京公网安备：11010502015324 京ICP备11036675号-2 经营许可证编号：京ICP证120107号</b>
                <img src="../images/pc/erweima_small.png">
            </div>
        </div>
    </div>
    <div class="suspend">
        <p class="borderB">
            <a href="index.html">
                <img src="${base}/images/pc/need.png">
                <span>发布装修需求</span>
            </a>
        </p>
        <p class="borderB">
            <img src="${base}/images/pc/all_erweima.png">
            <span>扫一扫关注微信</span>
        </p>
        <p class="borderB">
            <img src="${base}/images/pc/all_anzhuo.png">
            <span>iphone下载</span>
        </p>
        <p>
            <img src="${base}/images/pc/all_iphone.png">
            <span>Android下载</span>
        </p>
    </div>
</body>
<script type="text/javascript" src="${base}/scripts/pc/pics.js"></script>
</html>
<script type="text/javascript">
	$('.navLists li:eq(6) a').addClass('active');
    /*图片hover出现信息*/
    $(".pics").delegate('li','mouseenter',function(){
        $(this).find("b").show();
    }).delegate('li','mouseleave',function(){
        $(this).find("b").hide();
    });
    /*选择条件改变颜色*/
    $(".select .right ul li.choosable").click(function(){
    	var value=$(this).attr('rel');
    	$(this).parent().prev().val(value);
        $(this).addClass("cur").siblings("li").removeClass("cur");
        $('#pics ul').html('');
        getD(1,$('#type1').val(),$('#type2').val(),$('#type3').val());
    })
    $(".pics ul").delegate('li','click',function(){
    	var sid=$(this).attr('rel');
	    $.ajax({
	    	url:'aj_loadData.xhtml',
	    	dataType:'json',
	    	data:{"id":sid},
	    	success:function(res){
	    		var pics = $('.allPic'),len=res.data.attList.length,temp=[];
	    		if(len<1){return false;}
	    		for(var i=0;i<len;i++){
	    			temp[i]='<span><img src="'+res.data.attList[i].downloadPath+'"></span>';
	    		}
	    		pics.html(temp.join(''));
	    		$('.allPic span:first').addClass('cur');
	    		$('#tximg').attr('src',res.data.cover);
	    		$('#info').html('<strong>'+res.data.community+'</strong>'+res.data.space+'m²/'+res.data.money+'万/'+res.data.houseType+'/'+res.data.style);
	    		$('.bigPic img').attr('src',res.data.attList[0].downloadPath);
	    		var top=$(window).scrollTop();
		        $('.picDetails').css('margin-top',top);
		        $(".shadow,.picDetails,.close").show();
	    	}
	    });
        
    })
    $('.allPic').delegate('span','click',function(){
    	var src=$(this).find('img').attr('src');
    	$('.bigPic img').attr('src',src);
    	$(this).addClass('cur').siblings().removeClass('cur');
    });
    $('span.prev').bind('click',function(){
    	var index=$('.allPic span').index($('.allPic span.cur'));
    	if(index>0){
    		$('.allPic span.cur').prev().addClass('cur').siblings().removeClass('cur');
    		$('.bigPic img').attr('src',$('.allPic span.cur img').attr('src'));
    	}
    });
    $('span.next').bind('click',function(){
    	var index=$('.allPic span').index($('.allPic span.cur')),len=$('.allPic span').length;
    	if(index<len){
    		$('.allPic span.cur').next().addClass('cur').siblings().removeClass('cur');
    		$('.bigPic img').attr('src',$('.allPic span.cur img').attr('src'));
    	}
    });
    $(".close").click(function(){
         $(".shadow,.picDetails,.close").hide();
         $(".picShow").show();
    })
</script>