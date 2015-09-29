var pageNow=1,flag=true;
window.onload = function(){
    PBL('pics','box');
    //模拟数据
    
    window.onscroll = function(){
        //校验数据请求
        if(getCheck()&&flag){
        	flag=false;
            pageNow++;
            if(pageNow>$('#pics').attr('rel')){return false;}
            getD(pageNow,$('#type1').val(),$('#type2').val(),$('#type3').val());
        }
    }
}
function getD(pageNow,huxing,kongjian,fengge){
	$.ajax({
    	url:'aj_loadDataList.xhtml',
    	dataType:'json',
    	data:{"page":pageNow,"huxing":huxing,"kongjian":kongjian,"fengge":fengge},
    	success:function(res){
    		var pics = $('#pics'),len=res.data.length,temp=[];
    		for(var i=0;i<len;i++){
    			temp[i]='<li class="box"><img src="'+res.data[i].path+'"><b>'+res.data[i].space+'m² '+res.data[i].money+'万/'+res.data[i].houseType+'/'+res.data[i].style+' </b></li>';
    		}
    		$('#pics ul').append(temp.join(''));
    		$('#pics').attr('rel',res.countPage);
    		PBL('pics','box');
    	}
    });
}
/**
* 瀑布流主函数
* @param  pics  [Str] 外层元素的ID
* @param  box   [Str] 每一个box的类名
*/
function PBL(pics,box){
	;
    //  1.获得外层以及每一个box
    var pics = document.getElementById(pics);
    var boxs  = getClass(pics,box);
    //  2.获得屏幕可显示的列数
    var boxW = boxs[0].offsetWidth;
    var colsNum = Math.floor(1016/boxW);
    //pics.style.width = boxW*colsNum+'px';//为外层赋值宽度
    //  3.循环出所有的box并按照瀑布流排列
    var everyH = [];//定义一个数组存储每一列的高度
    for (var i = 0; i < boxs.length; i++) {
        if(i<colsNum){
            everyH[i] = boxs[i].offsetHeight;
        }else{
            var minH = Math.min.apply(null,everyH);//获得最小的列的高度
            var minIndex = getIndex(minH,everyH); //获得最小列的索引
            getStyle(boxs[i],minH,boxs[minIndex].offsetLeft,i);
            everyH[minIndex] += boxs[i].offsetHeight;//更新最小列的高度

        }
    }
    pics.style.height=Math.max.apply(null,everyH)+'px';
    setTimeout(function(){flag=true;},1000);
}
/**
* 获取类元素
* @param  warp      [Obj] 外层
* @param  className [Str] 类名
*/
function getClass(pics,className){
    var obj = pics.getElementsByTagName('*');
    var arr = [];
    for(var i=0;i<obj.length;i++){
        if(obj[i].className == className){
            arr.push(obj[i]);
        }
    }
    return arr;
}
/**
* 获取最小列的索引
* @param  minH   [Num] 最小高度
* @param  everyH [Arr] 所有列高度的数组
*/
function getIndex(minH,everyH){
    for(index in everyH){
        if (everyH[index] == minH ) return index;
    }
}
/**
* 数据请求检验
*/
function getCheck(){
    var documentH = document.documentElement.clientHeight;
    var scrollH = document.documentElement.scrollTop || document.body.scrollTop;
    return documentH+scrollH>=getLastH() ?true:false;
}
/**
* 获得最后一个box所在列的高度
*/
function getLastH(){
    var pics = document.getElementById('pics');
    var boxs = getClass(pics,'box');
    return boxs[boxs.length-1].offsetTop+boxs[boxs.length-1].offsetHeight;
}
/**
* 设置加载样式
* @param  box   [obj] 设置的Box
* @param  top   [Num] box的top值
* @param  left  [Num] box的left值
* @param  index [Num] box的第几个
*/
var getStartNum = 0;//设置请求加载的条数的位置
function getStyle(box,top,left,index){
    //if (getStartNum>=index) return;
    $(box).css({
        'position':'absolute',
        'top':top,
        "left":left,
        "opacity":"0"
    });
    $(box).stop().animate({
        "opacity":"1"
    },999);
    getStartNum = index;//更新请求数据的条数位置
}