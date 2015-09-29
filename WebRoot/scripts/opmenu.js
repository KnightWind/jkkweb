function op(data, parentName){
    var str='',
        flag=$('.menuEdit').data('flag'),id=$('.menuEdit').data('temp').id;
    switch(flag){
        case 1:
            str='<li class="mainNav"><p class="hd sp"><span><i class="new addSub1">&#xf067;</i><i class="new modify">&#xf040;</i><i class="new del sp">&#xf014;</i></span><b menuid="'+data.id+'" name="'+data.name+'" parentname="'+parentName+'" link="'+data.link+'">'+data.name+'</b></li>';
            var w=$('.menuList ul').width()+114,len=$('.menuList').data('len')+1;
            $('.menuList ul').append('<li rel="'+data.id+'"><a href="javascript:;">'+data.name+'</a></li>');
            $('.menuList ul').width(w);
            $('.menuList').data('len',len);
            break;
        case 2:str='<ul class="sub2 cur"><li><p><span rel="'+id+'"><i class="new addSub2">&#xf067;</i><i class="new modify">&#xf040;</i><i class="new del sp">&#xf014;</i></span><b menuid="'+data.id+'" name="'+data.name+'" parentname="'+parentName+'" link="'+data.link+'">'+data.name+'</b></p></li><ul class="sub3"></ul></ul>';break;
        case 3:
        	$('.menuEdit').data('obj').text(data.name);
        	$('.menuEdit').data('obj').attr('name',data.name);
        	$('.menuEdit').data('obj').attr('link',data.link);
        	$('.menuEdit').data('obj').attr('parentname',data.pid);
        	$('.menuList ul li[rel="'+id+'"] a').text(data.name);
        	break;
        default:
        	str='<li><p><span rel="'+id+'"><i class="new modify">&#xf040;</i><i class="new del sp">&#xf014;</i></span><b menuid="'+data.id+'" name="'+data.name+'" parentname="'+parentName+'" link="'+data.link+'">'+data.name+'</b></p></li>';
        	break;
    }
    if(flag!=3){$('.menuEdit').data('obj').append(str);deldo();}
    $('.ui-dialog').remove();
    delMask();
}
function deldo(){
    var flag=$('ul.sub1 li').length,str='<li class="empty"><i class="new">&#xf06a;</i> 菜单项为空</li>';
    if(flag==0){$('ul.sub1').html(str);}
    $('li.mainNav').each(function(index,ele){
        var len=$(ele).find('ul.sub2').length;
        if(len==0){$(ele).addClass('sp');}else{$(ele).removeClass('sp');}
    });
}
$(function(){
    $('.opmenu').delegate('p','mouseenter',function(){
        $(this).addClass('cur');
    }).delegate('p','mouseleave',function(){
        $(this).removeClass('cur');
    });
    // pop menuadd
    $('.opmenu span.btn').bind('click',function(){
    	var obj=$(this).parent().next(),
	    	parentName=$('input[name="parentName"]').val(),
	    	name=$('input[name="name"]').val(),
	    	menuid=obj.attr('menuid');
	    	link=$('input[name="link"]').val();
    	var temp={
    			id:'',
    			pid:menuid,
    			name:name,
    			link:link
    	};
        addMask();
        $('body').append($('#menuEdit').html());
        $('.menuEdit').data('obj',$('ul.sub1')).data('flag',1).data('temp',temp);
        center($('.menuEdit'));   
    });
    $('body').delegate('span.save1','click',function(){
        var obj=$('.menuEdit').data('temp'),
        	parentName=$('input[name="parentName"]').val(),
        	name=$('input[name="name"]').val(),
        	link=$('input[name="link"]').val();
        	icon=$('input[name="icon"]').val();
        if(obj.name==''){justTip('请填写菜单名称！');return false;}
        $.ajax({
            url : ctx + "/admin/menu/save.do",
            type : "post",
            data : {
                id : obj.id,
                pid : obj.pid,
                name : name,
                link : link,
                icon : icon || ""
            },
            success : function(res) {
            	if(res.ret==0){justTip('操作成功！');op(res.data, parentName);}
            }
        });
    });
    // toggle
    $('.opmenu').delegate('p.hd','click',function(){
    	$('ul.sub2').hide();$('p.hd').addClass('sp');
        $(this).removeClass('sp');
        $(this).parent().find('ul.sub2').show();
    });
    $('.opmenu').delegate('p.hd','dblclick',function(){
        $(this).addClass('sp');
        $(this).parent().find('ul.sub2').hide();
    });
    $('.opmenu').delegate('i.tip','click',function(){
        $(this).parent().parent().addClass('sp');
        $(this).parent().parent().parent().find('ul.sub2').hide();
        return false;
    });
    // menuadd1
    $('.opmenu').delegate('i.addSub1','click',function(){
        var obj=$(this).parent().next(),menuid=obj.attr('menuid');
        var temp={
     			id:'',
     			pid:menuid,
     			name:obj.attr("name"),
     			link:obj.attr("link")
     	};
        addMask();
        $('body').append($('#menuEdit').html());
        $('input[name="parentName"]').val(temp.name);
        $('.menuEdit').data('obj',$(this).parent().parent().parent()).data('flag',2).data('temp',temp);
        center($('.menuEdit'));
   });
    // menuadd2
    $('.opmenu').delegate('i.addSub2','click',function(){
        var obj=$(this).parent().next(),menuid=obj.attr('menuid');
        var temp={
     			id:'',
     			pid:menuid,
     			name:obj.attr("name"),
     			link:obj.attr("link")
     	};
        addMask();
        $('body').append($('#menuEdit').html());
        $('input[name="parentName"]').val(obj.text());
        $('.menuEdit').data('obj',$(this).parent().parent().parent().next()).data('flag',0).data('temp',temp);
        center($('.menuEdit'));
    });
    // menu modify
    $('.opmenu').delegate('i.modify','click',function(){
    	var obj=$(this).parent().next();
     	var temp={
     			id:obj.attr("menuid"),
     			pid:obj.attr("parentname"),
     			name:obj.attr("name"),
     			link:obj.attr("link"),
     			icon:obj.attr("icon") || ""
     	};
        addMask();
        $('body').append($('#menuEdit').html());
        $('input[name="parentName"]').val(temp.pid);
        $('input[name="name"]').val(temp.name);
        $('input[name="link"]').val(temp.link);
        $('input[name="icon"]').val(temp.icon);
        $('.menuEdit').data('obj',$(this).parent().next()).data('flag',3).data('temp',temp);
        center($('.menuEdit'));
    });
    // menu del
    $('.opmenu').delegate('i.del','click',function(){
        var $this=$(this),tip='请注意！此操作会删除菜单中的所有子菜单，是否继续？';
        notice(tip,function(){
            var menuid = $this.parent().next().attr("menuid");
            $.ajax({
                    url : ctx + "/admin/menu/remove.do",
                    data : {
                        id : menuid
                    },
                    success : function(res) {
                        $this.parent().parent().parent().remove();
                        $('.menuList ul li[rel="'+menuid+'"]').remove();
                        deldo();
                        justTip('操作成功！');
                    }
                });
        });
    });
})