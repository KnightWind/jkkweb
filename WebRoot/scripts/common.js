var hx={};
/*------------------------------------Base-------------------------------------------*/
function add_loading(msg){
    var m_h=$(document).height(),tip='数据正在处理中...请稍等';
    if(msg){tip=msg;}
    var html='<div class="win_mask_loading"></div><div class="hx_load"><img src="../images/loading.gif"> '+tip+'</div>';
    $('body').append(html);
    var win=$(window),
        load=$('.hx_load'),
        it_w=load.width(),
        it_h=load.height(),
        pop={'left':(win.width()-it_w)/2,'top':(win.height()-it_h)/2+win.scrollTop()};
    load.css(pop).show();
    $('.win_mask_loading').css('height',m_h).fadeIn();
    $('select').hide();
    };
/*window addMask*/
function addMask(){
    var m_h=$(document).height();
    $('body').append('<div class="win_mask"></div>');
    $('.win_mask').css('height',m_h).fadeIn();
    $('select').hide();
    }
/*window delMask*/
function delMask(){
    $('.ui-dialog,.win_mask').remove();
    $('select').show();
};
/*window justTip*/
function justTip(msg){
    $('body').append('<div class="justTip">'+msg+'</div>');
    center($('.justTip'));
    setTimeout(function(){$('.justTip').fadeOut(1000,function(){$('.justTip').remove();});},1000);
}
/*window pop center*/
function center(obj){
	var $window=$(window),
		win_h=$window.height(),
		win_w=$window.width(),
		obj_h=obj.innerHeight(),
		obj_w=obj.innerWidth(),
		scroll=$window.scrollTop(),
		pos={'left':(win_w-obj_w)/2,'top':(win_h-obj_h)/2+scroll,'position':'absolute'};
		obj.css(pos).show();
        obj.find('.close').bind('click',function(){obj.remove();delMask();});		
};
/*window notice*/
function notice(msg,fun){
    addMask();
    var title='温馨提示';
    var html='<div class="ui-dialog confirm"><h2><span class="close">×</span>'+title+'</h2><p class="cont">'+msg+'</p><p class="button"><span class="btn ok">确定</span> <span class="btn close">取消</span></p></div>';
    $('body').append(html);
    center($('.confirm'));
    if(fun){$('.confirm').find('span.ok').bind('click',fun);}
    $('.confirm').find('span.ok').bind('click',function(){delMask();return false;});
}
/*window pop current pos*/
function curpos(obj,_obj){
	var $window=$(window),
		win_h=$window.height(),
		obj_h=obj.innerHeight(),
		left=_obj.offset().left,
		scroll=$window.scrollTop(),
		pos={'left':left,'top':(win_h-obj_h)/2+scroll,'position':'absolute'};
		obj.css(pos).show();
        obj.find('.close').bind('click',function(){obj.remove();delMask();});		
};
/*menuAuto*/
function menuAuto(){
    var setWidth=$('body').width()-320;
    var obj=$('.menuList'),
        len=obj.find('ul li').length,
        perW=obj.find('ul li').width()+10,
        per=0,
        maxlen=Math.floor(setWidth/perW);
        _len=len-maxlen,
        curpos=$('.menuList ul li.cur').index()+1,
        curMarginLeft=0;
    obj.data('len',_len);
    if(curpos>maxlen){
    	curMarginLeft=-(curpos-maxlen)*perW;
    	per=curpos-maxlen;
    	$('.menuList ul').css({marginLeft:curMarginLeft});
    }
    if(len<maxlen){$('a.btn').hide();}else{$('a.btn').show();}
    obj.css('width',maxlen*perW);
    obj.find('ul').css('width',len*perW);
    $('a.prev').unbind('click').bind('click',function(){
        if(per<obj.data('len')){
            per++;
            $('.menuList ul').animate({marginLeft:-perW*per});
        }
    });
    $('a.next').unbind('click').bind('click',function(){
        if(per<1){return false;}
        per--;
        $('.menuList ul').animate({marginLeft:-perW*per});
    });
}
/*JSON format*/
(function($){
    $.fn.formatJSON=function(){
        var $this=$(this),temp=[],i=0;
        $this.find('input:text,input:hidden,input:checked,select').each(function(index,ele){
            temp[i++]='"'+$(ele).attr('name')+'":"'+$(ele).val()+'"';
        });
        temp='{'+temp.join(',')+'}';
        return temp;
    };
})(jQuery);
/*page paging*/
(function($){
    $.fn.page=function(options){
        var _obj=$(this);
        var op={
            url:'',
            info:{},
            deal:{},
            format:["pageCount","count"],/*[总页数/总条数]*/
            fun:function(data){}
        };
        var options=$.extend(op,options);
        function paging(obj,cur,count,perpage,records){
            var pagePrev='<a href="#" class="ui-paging-prev"><i class="new">&#xf104;</i> 上一页</a> ',
                pageNext='<a href="#" class="ui-paging-next">下一页 <i class="new">&#xf105;</i></a><span class="ui-paging-info"><span class="ui-paging-bold">'+cur+'/'+count+'</span>页，共<span class="ui-paging-bold">'+records+'</span>条，第 </span><span class="ui-paging-which"><input name="some_name" type="text"></span><span class="ui-paging-bold">页</span><a class="ui-paging-info ui-paging-goto" max_page='+count+' href="#">跳转</a> ',
                temp=[],
                pageHtml='';
            function pageMid(start,end){
                for(var i=start;i<end;i++){
                    var curpage=parseInt(i+1);
                    temp[i]='<a href="#" class="ui-paging-item" rel="'+curpage+'">'+curpage+'</a> ';
                }
            };
            if(count<=perpage){
                pageMid(0,count);
                pageHtml=pagePrev+temp.join('')+pageNext;
            }else{
                if(cur<perpage){
                    pageMid(0,perpage);
                    pageHtml=pagePrev+temp.join('')+'<span class="ui-paging-ellipsis">...</span>'+'<a href="#" class="ui-paging-item" rel="'+count+'">'+count+'</a>'+pageNext;
                }else{
                    if(cur>=count-perpage){
                        pageMid(count-perpage-2,count);
                        pageHtml=pagePrev+temp.join('')+pageNext;
                    }else{
                        pageMid(cur-2,parseInt(cur)+perpage);
                        pageHtml=pagePrev+temp.join('')+'<span class="ui-paging-ellipsis">...</span>'+'<a href="#" class="ui-paging-item" rel="'+count+'">'+count+'</a>'+pageNext;
                    }
                }
            }       
            obj.html(pageHtml);
            var prevVlaue = obj.find(".ui-paging-ellipsis").prev(".ui-paging-item").attr("rel");
            var nextValue = obj.find(".ui-paging-ellipsis").next(".ui-paging-item").attr("rel");
            if(parseInt(nextValue) - parseInt(prevVlaue) == 1){
                obj.find(".ui-paging-ellipsis").remove();
            }
            obj.find('a[rel="'+cur+'"]').addClass('ui-paging-current');
            if($('.ui-paging-current').attr('rel') == 1){
                $('.ui-paging-prev').css('cursor','text');
            }
            if($('.ui-paging-current').attr('rel') == count){
                $('.ui-paging-next').css('cursor','text');
            }
            else{
                $('.ui-paging-prev').css('cursor','hand');
                $('.ui-paging-next').css('cursor','hand');
            }
        };
        /*next*/
        $('.ui-paging').delegate('a.ui-paging-next','click',function(){
            var page=parseInt($(this).siblings('a.ui-paging-current').attr('rel')),
                _page=parseInt($(this).prev().attr('rel'));
            if(page!=_page){page++;}else{return false;}
            get_data(page);
            return false;
        });
        /*prev*/
        $('.ui-paging').delegate('a.ui-paging-prev','click',function(){
            var page=parseInt($(this).siblings('a.ui-paging-current').attr('rel'));
            if(page>1){page--;}else{return false;}
            get_data(page);
            return false;
        });
        /*normal*/
        $('.ui-paging').delegate('a.ui-paging-item','click',function(){
            var page=parseInt($(this).attr('rel'));
            get_data(page);
            return false;
        });
        /*topage*/
        $('.ui-paging').delegate('a.ui-paging-goto','click',function(){
            var page=$(this).parent().find('input').val(),max_page=parseInt($(this).attr("max_page"));
            if(!/^\d+$/.test(page)){justTip('页码格式错误！');$(this).parent().find('input').val('');return false;}
            if(page==''||page>max_page){justTip('您输入页码不在1~'+max_page+'范围之内！');return false;}
            get_data(page);
            return false;
        });
        function get_data(pageNum){
            op.info.pageNum=pageNum;
            op.info.perpage=5;
            //op.info.pageSize=10;
                $.ajax({
                    url:op.url,
                    dataType:'json',
                    type:'post',
                    data:op.info,
                    error:function(){return false;},
                    success:function(_data){
                        op.fun(_data);
                        if(_data.result[op.format[1]]>op.info.pageSize){paging(_obj,pageNum,_data.result[op.format[0]],op.info.perpage,_data.result[op.format[1]]);_obj.removeClass('ui-paging-no');}else{_obj.addClass('ui-paging-no').html('');}
                    }
                    
                });
            };
        this.each(function(){
            op.info=op.send();
            if(op.deal.click){
                $(op.deal.click).bind('click',function(){
                    op.info=op.send();
                    get_data(1);
                });
            }
            if(op.deal.change){
                $(op.deal.change).bind('change',function(){
                    op.info=op.send();
                    get_data(1);
                });
            }
            //get_data(1);
        });
    };
})(jQuery);
function menuSelected(){
    var role=$('#sel1').attr('data-role'),_role=$('#sel2').attr('data-role'),
    	num=$('.menu li[data-role="'+role+'"]').find('ul.sub li').length,
    	_num=$('.menu li[data-role="'+_role+'"]').find('ul.sub li').length;
    if(role!=''){
    	if(num==0){$('.menu li[data-role="'+role+'"]').addClass('cur');}
    	else{$('.menu li[data-role="'+role+'"]').addClass('open');}
    	}
    if(_role!=''){
    	if(_num==0){$('.menu li[data-role="'+_role+'"]').addClass('cur');}
    	else{$('.menu li[data-role="'+_role+'"]').addClass('open');}
    	}
}
/*-----------------------------add modify del module -------------------------------------*/
/*add module*/
function addnew(name){
    $('body').delegate(name,'click',function(){
        addMask();
        $('body').append($('#pannel').html());
        $('.ui-dialog').data('act','add');
        center($('.ui-dialog'));
    });
}
/*save module*/
function save(name){
    $('body').delegate(name,'click',function(){
        var obj=$(this).parent().parent().parent(),
        	_obj=$(this).parent().parent(),
        	act=$('.ui-dialog').data('act'),
        	data=$('.ui-dialog').data('data');
        obj.ajaxSubmit({
             type:"post",
             dataType:"json",
             cache:false,
             success:function(res){
                if(res.ret==0){
                	justTip(res.msg);
                	if(act=='add'){
                		$('.ui-paging a:eq(1)').click();
                	}else{
                		$('a.ui-paging-current').click();
                	}
                }else{justTip(res.msg);}
             }
        });
        delMask();
    });
}
/*modify module*/
function modify(name){
    $('body table.format').delegate(name,'click',function(){
        var $this=$(this),data=$.parseJSON($this.attr('rel'));
        addMask();
        $('body').append($('#pannel').html());
        $('.ui-dialog').data('act','modify');
        $('.ui-dialog input,.ui-dialog select,.ui-dialog textarea').each(function(index,ele){
            $(ele).val(data[index]);
        });
        center($('.ui-dialog'));
    });
}
/*del module*/
function del(name){
    $('body table.format').delegate(name,'click',function(){
        var $this=$(this),url=$this.attr('rel');
        $.get(url,function(res){
            if(res.ret==0){
            	$('a.ui-paging-current').click();
            }else{justTip(res.msg);}
        },'json');
    });
}
/*------------------------------------Dom Ready-------------------------------------------*/
$(function(){
    menuAuto();
    menuSelected();
    addnew('span.addnew');
    del('span.del');
    modify('span.modify');
    save('span.save');
    $(window).bind('resize',function(){
    	menuAuto();
    });
	$('.menu li strong').unbind('click').bind('click',function(){
		$(this).parent().toggleClass('open');
	});
    // 表格效果
    $('table.format').delegate('tr','mouseenter',function(){
        $(this).addClass('cur');
    }).delegate('tr','mouseleave',function(){
        $(this).removeClass('cur');
    });
    /*select module*/
    $('.select span').unbind('click').bind('click',function(){
    	var $this=$(this),flag=$this.hasClass('no');
    	if(flag){ return false;}
    		$this.next().show();
    });
    $('.select').bind('mouseleave',function(){
        $(this).find('p').hide();
    });
    $('.select p').delegate('a','click',function(){
        var $this=$(this),value=$this.attr('value'),txt=$this.text();
        $this.parent().next().val(value);
        $this.parent().prev().text(txt);
        $this.parent().hide();
    });
    /*selectAll*/
    $('input.selectAll').bind('change',function(){
    	var flag=$(this).prop('checked');
    	$('table.format tbody td input:checkbox').prop('checked',flag);
    });
    /*province city area*/
    $('.province p a').unbind('click').bind('click',function(){
    	$('.city span,.area span').text('--请选择--');
    	$('.city p,.area p').html('');
		var url=$(this).parent().attr('rel'),id=$(this).attr('value'),str='<a value="">--请选择--</a>';
		$.ajax({url:url,data:{"pid":id},dataType:'json',type:'post',cache:false,success:function(data){
				var obj=data.list;len=obj.length,temp=[];
				for(var i=0;i<len;i++){
					temp[i]='<a value="'+obj[i].regionid+'">'+obj[i].regionname+'</a>';
				}
	        	$('.city p').html(str+temp.join(''));
        	}
        });
	});
	$('.city p').delegate('a','click',function(){
		$('.area span').text('--请选择--');
		$('.area p').html('');
		var url=$(this).parent().attr('rel'),id=$(this).attr('value'),str='<a value="">--请选择--</a>';
		$.ajax({url:url,data:{"pid":id},dataType:'json',type:'post',cache:false,success:function(data){
				var obj=data.list;len=obj.length,temp=[];
				for(var i=0;i<len;i++){
					temp[i]='<a value="'+obj[i].regionid+'">'+obj[i].regionname+'</a>';
				}
		    	$('.area p').html(str+temp.join(''));
        	}
        });
	});
});