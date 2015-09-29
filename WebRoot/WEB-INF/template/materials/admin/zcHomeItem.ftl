<!DOCTYPE HTML>
<html>
<head>
<title>首页众凑商品</title>
<meta charset="utf-8">
[#include "/common/res.ftl"]
<script type="text/javascript" src="${base}/scripts/form.js"></script>
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
				<span class="btn search" onclick="history.back()" style="font-size:12px;float:right;margin-right:20px;">返回</span>
					<table class="format">
						<thead>
							<th width="10%">编号</th>
							<th width="20%">商品名称</th>
							<th width="10%">最高价(单位:元)</th>
							<th width="10%">最低价(单位:元)</th>
							<th width="10%">众凑人数</th>
							<th width="20%">首页图片</th>
							<th width="10%">操作</th>	
						</thead>
						<tbody>
							[#list itemList as item]
							<tr>
								<td>${item.id}</td>	
								<td>${item.title}</td>	
								<td>${item.topPrice}</td>
								<td>${item.lowPrice}</td> 
								<td>${item.numPeople}</td> 
								<td>
								    <img src="${item.imgUrl}" width="100"/>
								</td> 
								<td>
						           <span class="btn deleteOne" rel="${item.id}">移除<span>
								</td>		
							</tr>
							[/#list]
						</tbody>
			       </table>
		    </div>
		</div>
		[#include "/common/menu.ftl"]
	</div>
<!-- footer -->
 [#include "/common/foot.ftl"]
</body>
<script>
   $(function(){
      $(".deleteOne").click(function(){
         var TF=confirm("确定移除吗?");
         if(TF){
           var id=$(this).attr("rel");
           $.post("deleteOne.do",{id:id},function(rel){
               justTip(rel.msg);
               if(rel.ret==0){
                  location.reload();
               }
           })
         }
      });
   });
</script>
</html>