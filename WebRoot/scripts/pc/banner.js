$(function(){
    $('.adBanner').bannerSlide();   
});
    //焦点图插件
    (function($){
        $.fn.bannerSlide = function(){
            var This = $(this);
            var len = This.find('.bannerPic li').length;
            var iNow =0;
            var timer = null;
  
            for(var i = 0;i<len;i++)
            {
                This.find('.bannerBtn').append("<a href='javascript:;' style='margin:0 5px'></a>")
            }
  
            //初始化
            This.find('.bannerPic li').eq(0).fadeIn();
            This.find('.bannerBtn a').eq(0).addClass('btnActive');
  
            This.find('.bannerBtn a').click(function(){
                iNow = $(this).index();
                fnFadeMove ();
            });
			// This.find('.bannerBtn2 .leftBtn').click(function(){
			// 	if(iNow>0){
			// 	  iNow--;					
			// 	}else if(iNow==0){
			// 	  iNow=2;	
			// 	};
			// 	fnFadeMove ();
			// });
			// This.find('.bannerBtn2 .rtBtn').click(function(){
			// 	if(iNow<2){
			// 	  iNow++;					
			// 	}else if(iNow==2){
			// 	  iNow=0;	
			// 	};
			// 	fnFadeMove ();
			// });
  
            timer = setInterval(function(){
                iNow++;
                if(iNow>len-1)
                {
                    iNow = 0;
                }
                fnFadeMove ()
            },3000);
            This.hover(function(){
                clearInterval(timer);				
            },function(){
                timer = setInterval(function(){
                    iNow++;
                    if(iNow>len-1)
                    {
                        iNow = 0;
                    }
                    fnFadeMove ()
                },3000);
            });
            function fnFadeMove (){
                This.find('.bannerBtn a').removeClass('btnActive')
                This.find('.bannerBtn a').eq(iNow).addClass('btnActive');
                This.find('.bannerPic li').fadeOut('fast');
				This.find('.bannerPic li').eq(iNow).fadeIn('slow');				
            };
        };
        
    })(jQuery);