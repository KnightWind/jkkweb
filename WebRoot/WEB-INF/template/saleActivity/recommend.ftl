<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>引流活动报名</title>
[#include "/common/res.ftl"]
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
</head>

<body>
<!-- header -->
[#include "/common/head.ftl"]

<!-- wrap -->
<div class="wrap index bc clr">
	<div class="conWrap fl">
		[#include "/common/nav.ftl"]
			<div class="conBox">
			<div class="tools white border mb10 sp10 clr">
				<form id="search">
				<ul>
					<li>
						<span class="sp"><strong>推荐时间：</strong><input type="text" class="text Wdate" name="start" id="start" onClick="WdatePicker()" readOnly></span>
						<span class="sp"><input type="text" class="text Wdate" name="end" id="end" onClick="WdatePicker()" readOnly></span>
					</li>
				</ul>
				</form>
				
			</div>
			<div style="margin-bottom:10px;float:right;"><span class="btn" id="daochu">导出</span></div>
				
			[#include "/common/pagination.ftl"]
		 </div>
	</div>
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]

</body>
<script>
$(function(){
	$("#daochu").click(function(){
	  var start=$("#start").val();
	  var end=$("#end").val();
	  location.href ='${base}/wxActivityJionSign/recommend.do?start='+start+'&end='+end;	
	});    
    
	$('.ui-paging').page({
		url:'${base}/wxActivityJionSign/pagination.do',
		deal:{'click':'span.search'},
		format:['pageCount','count'],
		send:function(){
			var data={
					pageSize:10,
					search:$('#search').formatJSON()
			};
			return data;
		},
	    fun:function(dataR){
	    	if(dataR.records<1){ return false;}
			$('table.format tbody').html(template.render('list',dataR)); 
		}
	});
})
</script>
</html>