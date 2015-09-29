/*
*，高防JQ animate函数
*程序作者：明哥QQ406507227  （又名带带，霸王男）
*5月27日开发，高防JQ animate函数，
*程序运行快速
*用法示例：dom("cde").animate({"width":"50px","height":"505px","opacity":"0.1"},1000,function(){alert("动画已结束");});
*/
var isset_animate={is:new Array}//API
function dom(domid){
var  Public={
t_style:null,
t:null,
trueTimer:true,
taskquantity:0,
functions:false,
parameterarray:new Array,
exitTimer:null,
Calculation:function(num,originalvalue,time){
	var num2=num-originalvalue;
	if(!+"\v1")time=time/20;
	 else
	time=time/4.4;
	var average = num2/time;
  return average;
	},
lowup:function(b){
	var b=b.toLowerCase();
	 b=b.replace(/(-[a-z])/ig,function(e){return (e.toUpperCase())}); 
         b=b.replace(/-/g,"");
		 return b;
	},
original:function(a,t,a2){
	var oStyle = t.currentStyle? t.currentStyle : window.getComputedStyle(t, null);
        if (oStyle.getPropertyValue) {
			 var n = parseFloat(oStyle.getPropertyValue(a2));
			 if(isNaN(n))n=false;
            return n
        } else {
	var txt= oStyle.getAttribute(a);
 if(a=="filter"){
	 if(txt!=""){
var numie	= /opacity\s{0,}=s{0,}([0-9]+)/i.exec(txt);
return parseFloat(numie[1]);
}else{
return 100;
 }
	 }
	if(/%/i.exec(txt)=="%")return false;
	var n = parseFloat(txt);
	if(isNaN(n))n=false;
	 return n;
    }
},
setsyle:function(key,t_style,obj,num,char){
 if(obj.callstop==2){ obj.callstop=1;   return;}
switch (key){
	case "filter": 
	t_style["filter"]="alpha(opacity="+num+")";
	break;
	
	case "scrollTop": 
	obj[key]=num;
	break;
	
	case "scrollLeft": 
	obj[key]=num;
	break;
	
	default:
	t_style[key]=num+char;
	break;
	}	
},	
tochangeinto:function(key,average,char,num,originalvalue){
	var  t_style=this.t_style,obj=this.t,ations=0,exercise=0,Complete =false,originalvalueations=0,Public=this;
return function(){
if(Complete)return false;
ations+=average;
	originalvalueations =originalvalue+ations;
	if((average>=0 && originalvalueations>=num )|| (average<0 && originalvalueations<=num )){
	if(Public.taskquantity>1){
		Complete=true;
		Public.setsyle(key,t_style,obj,num,char);
		Public.taskquantity--;
		
}else {
	clearInterval(Public.exitTimer);
	Complete=true;Public.exitTimer=null;
	Public.setsyle(key,t_style,obj,num,char);Public.DEL_ISDOM();
if(typeof Public.functions ==="function")Public.functions();

	}
return false;
 }
exercise = originalvalue+ations;
Public.setsyle(key,t_style,obj,exercise,char);
return false;
};
},

EMCbug_indexOf:function(is,dom){
if(!is.indexOf) {
Array.prototype.indexOf = function(obj){
 for(var i=0; i<this.length; i++) {  if(this[i]==obj) {  return i;}} 
 return -1; } 
} 
return is.indexOf(dom);
},
DEL_ISDOM:function(){
var exist  = this.EMCbug_indexOf(isset_animate.is,this.t);
if(exist>-1){isset_animate.is.splice(exist,1);return true;}
},
ISDOM:function(){
var exist  = this.EMCbug_indexOf(isset_animate.is,this.t);
if(exist<0 || exist===null) {exist=isset_animate.is.push(this.t)-1;}else exist=-1;
return exist;
	},	
Timer:function(xx){
 var Ti_length =this.parameterarray.length, c=this.parameterarray,object=this;
	this.taskquantity=xx;
var	forpar=function(){
if(!object.trueTimer)return false;
for(var i=0;i<Ti_length;i++){
setTimeout(c[i],1);
}
object.trueTimer=true;

	
}
var  exist = this.ISDOM();
if(exist==-1)return false;
this.exitTimer=setInterval(forpar,1);
isset_animate.is[exist]["stop"]=this.exitTimer;
	},
	
main:function(key,value,time){
	var Public=this;
    var	key2=key;
    key=this.lowup(key); 
	var  char = /[a-z]+/i.exec(value); 
char=char===null?"":char;
   time= parseFloat(time);
	var t=this.t;
	var originalvalue;
	switch (key){
	case "scrolltop": 
	key="scrollTop";
	originalvalue=this.t[key];
	break;
	
	case "scrollleft": 
	key="scrollLeft";
	originalvalue=this.t[key];
	break;
	
case "opacity":
	if(!+"\v1" ){ 
	key="filter";
	}
originalvalue=this.original(key,t,key2);
	break;

	default:
	originalvalue=this.original(key,t,key2);
	break;
	}
		var   num;
var Symbol = /(\+=)|(\-=)/i.exec(value);
if(Symbol!==null){
Symbol=Symbol[0];
value = value.replace(Symbol,"");
num=  parseFloat(value);
num=key=="filter"?num*100:num;
if(Symbol=="+=")num+=originalvalue;	else if(Symbol=="-=")num=originalvalue-num;	
}
else{
num=  parseFloat(value);
num=key==="filter"?num*100:num;}
if(key==="opacity" || key==="filter"){
if(key==="opacity" && num>1)num=1;
else
if(key==="opacity" && num<0)num=0;
else
if(key==="filter" && num>100)num=100;
else
if(key==="filter" && num<0)num=0;
}
 var average=this.Calculation(num,originalvalue,time);
this.parameterarray.push(this.tochangeinto(key,average,char,num,originalvalue));}
}
var t=domid;
Public.t=t;
Public.t_style=t.style;
var xx=0;
	return {
	animate:function (json,time,functions){	
	Public.functions=functions?functions:false;
	for(var a in json){
	xx++;
	Public.main(a,json[a],time);	
		}
Public.Timer(xx);
return this;
		},
is:function(){
var exist  = Public.EMCbug_indexOf(isset_animate.is,t);
return  exist>-1 && true;
		},
stop:function(){
var exist  = Public.EMCbug_indexOf(isset_animate.is,t);
if(exist>-1){
clearInterval(isset_animate.is[exist]["stop"]);
isset_animate.is[exist]["stop"]=null;
if(typeof(t.callstop)==="undefined"){Object.prototype.callstop=1;}
t.callstop=2;
isset_animate.is.splice(exist,1);
}	
}			
}		
	}