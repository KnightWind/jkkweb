function changeImage(file,width,height) 
			{ 
			
		      var ImageFileExtend = ".gif,.png,.jpg";
			  var fileExtend = file.value.substring(file.value.lastIndexOf('.')).toLowerCase();
			  if(ImageFileExtend.indexOf(fileExtend) == -1)
		       {
		            justTip('请选择gif,png,jpg图片 !');
		            return;
		       }
			
			
			  var MAXWIDTH  = width; 
			  var MAXHEIGHT = height; 
			  var div = document.getElementById('preview'); 
			  if (file.files && file.files[0]) 
			  { 
			    div.innerHTML = '<img id=imghead>'; 
			    var img = document.getElementById('imghead'); 
			    img.onload = function(){ 
			      var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight); 
			      img.width = rect.width; 
			      img.height = rect.height; 
			    } 
			    var reader = new FileReader(); 
			    reader.onload = function(evt){img.src = evt.target.result;} 
			    reader.readAsDataURL(file.files[0]); 
			  } 
			  else 
			  { 
			    var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="'; 
			    file.select(); 
			    var src = document.selection.createRange().text; 
			    div.innerHTML = '<img id="imghead" style="padding:10px;" />'; 
			    var img = document.getElementById('imghead'); 
			    img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src; 
			    var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight); 
			    status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height); 
			    div.innerHTML = "<div id='divhead' style='width:"+rect.width+"px;height:"+rect.height+"px;"+sFilter+src+"\"'></div>"; 
			  } 
			} 
			
			function clacImgZoomParam( maxWidth, maxHeight, width, height ){ 
			    var param = {top:0, left:0, width:width, height:height}; 
			    if( width>maxWidth || height>maxHeight ) 
			    { 
			        rateWidth = width / maxWidth; 
			        rateHeight = height / maxHeight; 
			         
			        if( rateWidth > rateHeight ) 
			        { 
			            param.width =  maxWidth; 
			            param.height = Math.round(height / rateWidth); 
			        }else 
			        { 
			            param.width = Math.round(width / rateHeight); 
			            param.height = maxHeight; 
			        } 
			    } 
			     
			    param.left = Math.round((maxWidth - param.width) / 2); 
			    param.top = Math.round((maxHeight - param.height) / 2); 
			    return param; 
			}  
