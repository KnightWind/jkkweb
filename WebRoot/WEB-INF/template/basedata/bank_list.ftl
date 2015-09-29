<!DOCTYPE HTML>
<html>
<head>
<title>平台银行管理</title>
<meta charset="utf-8">
[#include "/common/res.ftl"]
	<script type="text/javascript" src="${base}/scripts/form.js"></script>
	<script type="text/javascript" src="${base}/scripts/template.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${base}/styles/admin-list.css">
</head>

</head>
<body>
<!-- header -->
[#include "/common/head.ftl"]
<!-- wrap -->
	<div class="wrap index bc clr">
		<div class="conWrap fl">
			[#include "/common/nav.ftl"]
				<div class="conBox">	
				<span class="btn addnew" style="float:right;margin-bottom:5px;margin-right:10px;">添加银行</span>
					<table class="format">
						<thead>
							<th width="15%">编号</th>
							<th width="30%">银行名称</th>
							<th width="30%">银行简称</th>
							<th width="20%">操作</th>
						</thead>
						<tbody>
							[#list pagination.dataList as item]
							<tr>
								<td>${item.id}</td>	
								<td>${item.name}</td>	
								<td>${item.abbreviation}</td>	
								<td>
								 <span class="btn modify" rel=["${item.id}","${item.name}","${item.abbreviation}"]>修改</span>
								</td>	
							</tr>
							[/#list]
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

<script type="text/html" id="pannel">
<div class="ui-dialog addManager">
	<h2><span class="close"></span><strong>添加管理员</strong></h2>
	<form id="send" action="save.do">
	<input type="hidden" id="id" name="id">
	<ul>
		<li><strong><b class="red">*</b> 银行名称：</strong> <input type="text" class="text name" name="name" placeholder="银行名称"></li>
		<li><strong>  银行简称：</strong> <input type="text" class="text abbreviation" name="abbreviation" placeholder="银行简称"></li>
		<li class="btn"><span class="btn" onClick="saveOne()">保存</span></li>
	</ul>
	</form>
</div>	
</script>

<script>
  function saveOne(){
    var name=$(".name").val();
    if(!name){
       justTip("银行名称不能为空");
       return ;
    }
    var abbreviation=$(".abbreviation").val();
    var id=$("#id").val();
    $.post("${base}/admin/bank/saveOne.do",{id:id,name:name,abbreviation:abbreviation},function(rel){
        justTip(rel.msg);
        if(rel.ret==0){
           location.reload();
        }
    });
  }
</script>

<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<tr>
		<td><%=result.elements[i].id%></td>	
		<td><%=result.elements[i].name%></td>	
		<td><%=result.elements[i].abbreviation%></td>	
		<td>
          <span class="btn modify" rel=["<%=result.elements[i].id%>","<%=result.elements[i].name%>","<%=result.elements[i].abbreviation%>"]>修改</span>
        </td>	
    </tr>	
<%}%>
</script>
<script>
$(function(){
	$('.ui-paging').page({
		url:'${base}/admin/bank/pagination.do',
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
	    }
	});
});	
</script>

</html>