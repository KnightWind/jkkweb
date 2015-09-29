
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>城市列表</title>
[#include "/common/res.ftl"]
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
<script type="text/javascript" src="${base}/scripts/form.js"></script>
<script type="text/javascript" src="${base}/scripts/datepicker/WdatePicker.js"></script>
<style>
.tools span.sp {
margin-right: 10px;
float: left;
}
</style>
</head>

<body>
<!-- header -->
[#include "/common/head.ftl"]
<!-- wrap -->
<div class="wrap index bc clr">
	<div class="conWrap fl">
		[#include "/common/nav.ftl"]
	<div class="conBox">
			<table class="format">
				<thead>
					<th width="10%">..dd</th>
				</thead>
				<tbody>
	<form action="${base}/supplier/cosss/save.do" method="post">
	会员id：<input name="uid" type="text"><br />
	[#list pagination.dataList as l]
	<input  name="spid" type="checkbox" value="${l.id}">${l.spName}<br />
	[/#list]
	<input type="submit" value="提交">
	</form>
	</tbody>
		</table>
		[#include "/common/pagination.ftl"]
	</div>
	</div>
	
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]
</body>
<script type="text/html" id="list">
<form action="${base}/supplier/cosss/save.do" method="post">
会员id：<input name="uid" type="text"><br />
<%for(i=0;i<result.elements.length;i++){%>
	<input  name="spid" type="checkbox" value="<%=result.elements[i].id%>"><%=result.elements[i].spName%><br />
<%}%>
<input type="submit" value="提交">
</form>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/supplier/cosss/pagination.do',
		deal:{'click':'.search'},	
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
			//$('table.doctorList tbody').html(temp.join(''));
	    }
	});
})
</script>

</html>