function  Navigation(){
this.touchover=	false,this.begin_x=this.end_x=this.setTimeoutID=this.obj=this.navul=this.howscroll=this.howscrollWidth=this.iall=this.iclass=this.setIntervalID=this.begin_xx=null;
this.ioverleft=this.overDistance=0;
	}
Navigation.prototype.touchSatrtFunc=function() {
	
	var self=this;
	return function(evt){
if(self.setIntervalID){clearInterval(self.setIntervalID);self.setIntervalID=null;}
var evttouches= evt.touches;
if(evttouches.length>1)return false;
var touch = evttouches[0];   
var x = Number(touch.pageX);  
self.begin_x  =self.begin_xx=x;
self.setTimeoutID=setTimeout(self.del.call(self),700);//忍耐极度700毫秒，超过时间就缩回去
return false;
}
 }	

Navigation.prototype.cachemover=function(end_x) {
var navulleft=this.ioverleft;	
	var num= this.begin_xx-end_x;
	if(num<0 && navulleft==0){
	 this.ioverleft=navulleft=-this.howscrollWidth;
		}else
if(num>0 &&navulleft==-this.howscrollWidth){
	 this.ioverleft=navulleft=0;
		}	
this.navul.style.left = navulleft-num+"px";
return num;
	}

Navigation.prototype.touchMoveFunc=function() {
	var self=this;
return function(evt){
	evt.preventDefault();
//时间	
var evttouches= evt.touches;
if(evttouches.length>1)return false;
var touch = evttouches[0];   
var x = Number(touch.pageX);  
self.end_x=x;
self.overDistance=Math.abs(self.cachemover(x));
///时间
return false;
}

 }	
Navigation.prototype.del=function(){
	var self=this;
return function(){
 self.begin_x=self.end_x=self.setTimeoutID=null;

}
 }
 //幻灯片

Navigation.prototype.slidecss=function(){
	var navli = this.navul.getElementsByTagName("li");
var navlilength=this.navlilength=navli.length;
var navlilength1=navlilength+1
var j = navlilength1*100;

this.navul.style.width=j+"%";
var navli0 =this.navli0=navli[0];
 var html="<li>"+navli0.innerHTML+"</li>";
this.navul.insertAdjacentHTML("beforeEnd",html);
var liPercentage=100/navlilength1;
for(var i=0;i<navlilength1;i++){
navli[i].style.width=liPercentage+"%";
	}
	this.howscroll = navli0.offsetWidth;
this.howscrollWidth = navlilength*this.howscroll;	

}	 

Navigation.prototype.slideclick=function(cmd){  //点击时
 if(this.overDistance!=0)return false  
	this.slide(cmd);
	this.theanimate(cmd);
	}
Navigation.prototype.slide=function(cmd){
	var self=this;
	var stylenav = this.navul.style;
var navulleft = Math.abs(parseFloat(stylenav.left));
if(navulleft==this.howscrollWidth && cmd == "-="){stylenav.left=0;}
else
if(navulleft==0 && cmd == "+="){stylenav.left=-this.howscrollWidth+"px";
}
}	 
//幻灯片 
Navigation.prototype.theanimate=function(cmd){
	var self=this;
 dom(this.navul).animate(
 {"left":cmd+(this.howscroll-this.overDistance)+"px"},200,
 function(){
 var v= self.ioverleft=parseFloat(self.navul.style.left);
  var s=  parseInt(Math.abs(v)/self.howscroll);
var c =self.iall[s];
self.iclass && (self.iclass.className="");
if(c){c.className="iover";self.iclass=c;} else {self.iall[0].className="iover";self.iclass=self.iall[0];}
 self.overDistance=0;
	 }
	
 );
	}
	 
Navigation.prototype.touchEndFunc=function(){
var self=this;
	return function(evt){
if(self.setTimeoutID){clearTimeout(self.setTimeoutID);self.setTimeoutID=null}
!self.setIntervalID && self.timefor();
var begin_x=self.begin_x;
var end_x=self.end_x;
var num= self.begin_xx-end_x;
var cmd= num>0?"-=":"+=";
var intnum=Math.abs(num);
if(self.overDistance < self.howscroll*0.6){//0.6   拖动60%就通过
if(intnum<20 || !begin_x || !end_x || begin_x-end_x==0){
	dom(self.navul).animate({"left":self.ioverleft+"px",},100,
	function(){
		self.overDistance=0
		}
	);self.del()();return}}
self.theanimate(cmd)
self.del()();
return false;
	}
 }	
	 Navigation.prototype.timefor=function(){
		 var self=this;
	this.setIntervalID=setInterval(function(){
		self.slideclick('-=')},3000); //图片自动循 
		 }
Navigation.prototype.bindEvent=function(obj,navul,theiall,left_on,right_next) { 
	this.obj=obj;
	this.navul=navul;
	navul.style.left=this.ioverleft=0;
	this.slidecss();
	var self=this;
  	window.onresize=function(){  
	  	if(self.setIntervalID){clearInterval(self.setIntervalID);self.setIntervalID=null;}
	 	dom(self.navul).stop();
		self.howscroll = self.navli0.offsetWidth;
		self.navul.style.left=self.ioverleft=0;
		self.iclass.className="";
		self.iclass=self.iall[0];
		self.iclass.className="iover";
		self.howscrollWidth = self.navlilength*self.howscroll;	
		self.overDistance=0;   
		self.timefor(); 
		return false;
    }


	obj.addEventListener('touchstart', this.touchSatrtFunc.call(this), false);  
	obj.addEventListener('touchmove', this.touchMoveFunc.call(this), false);  
	obj.addEventListener('touchend', this.touchEndFunc.call(this), false);
	obj.addEventListener('touchcancel',this.touchEndFunc.call(this), false);
	obj.scrollLeft=0;
//圆图标点击
left_on.onclick=function(){
	self.slideclick('+=');
	}
right_next.onclick=function(){
	self.slideclick('-=');
	}
function ifun(iall,i){
	iall[i].onclick=function(){
		dom(self.navul).stop();
	var clickcroll =  self.howscroll*i
	clickcroll=clickcroll>self.howscrollWidth?self.howscrollWidth:clickcroll;
	var c =iall[i];
self.iclass && (self.iclass.className="");
if(c){c.className="iover";self.iclass=c;} else {self.iall[0].className="iover";self.iclass=self.iall[0];}
    var clickcrolls = -clickcroll;
	self.ioverleft=clickcrolls;
	 dom(self.navul).animate({"left":clickcrolls+"px",},200);
		}
	}
//圆图标点击
this.iall = theiall.getElementsByTagName("i");
this.iclass=this.iall[0];
for(var i=0;i<this.iall.length;i++){
ifun(this.iall,i);
	}
 } 

var str=  new  Navigation();
str.bindEvent(document.getElementById("nav"),
document.getElementById("navul"),            
document.getElementById("iall"),
document.getElementById("left_on"),
document.getElementById("right_next")
);
str.timefor();
$("#navul li:last").addClass("orange");