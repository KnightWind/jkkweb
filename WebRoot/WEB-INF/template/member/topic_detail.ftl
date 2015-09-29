<!DOCTYPE HTML>
<html>
<head>
<title>业主日记详情</title>
<meta charset="utf-8">
[#include "/common/res.ftl"]
<style>
    
	#dialog tr{
		line-height:30px;
	}
	#dialog td{
		width:180px;
	}
	#dialog{
		font-size:15px;
		margin:10px;
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
			
			<div id="engineering">
					<table id="dialog">
						<tr>
							<td >日记编号：</td>
							<td>
								${topic.id}
							</td>
							<td >标题：</td>
							<td>
								${topic.subject}
							</td>
						</tr>
						<tr>
							<td>装修预算：</td>
							<td>
								${topic.budget}万
							</td>
							<td>装修面积：</td>
							<td>
								${topic.space}平方米
							</td>
						</tr>
						<tr>
							<td>装修风格：</td>
							<td>
								${topic.styleName}
							</td>
							<td >户型：</td>
							<td>
								${topic.houseTypeName}
							</td>
						</tr>
						<tr>
							<td>用户：</td>
							<td>
								${topic.nickName}
							</td>
							<td >所在城市：</td>
							<td>
								${topic.city}
							</td>
						</tr>
						<tr>
							<td >日记状态：</td>
							<td>
								${topic.statusVal}
							</td>
							<th>所在小区：</th>
							<td>
								${topic.community}
							</td>
						</tr>
						<tr>
							<td >屏蔽时间：</td>
							<td>
								${topic.closeTimeHandle}
							</td>
							<td >创建时间：</td>
							<td>
								${topic.createTimeHandle}
							</td>
						</tr>
						<tr>
							<td >审核时间：</td>
							<td>
								${topic.checkTimeHandle}
							</td>
							<td >更新时间：</td>
							<td>
								${topic.updateTimeHandle}
							</td>
						</tr>
						<tr>
							<td >浏览数：</td>
							<td>
								${topic.browseCount}
							</td>
							<td >评论数：</td>
							<td>
								${topic.commentCount}
							</td>
						</tr>
						<tr>
						   <td >内容：</td>
						   <td colspan="3">${topic.content}</td>
						</tr>
					</table>
						<div style="margin:0px 0px 20px 500px;">
							<span class="btn pass" rel="${topic.id}">通过</span>
							<span class="btn noPass" rel="${topic.id}">不通过</span>
							<span class="btn search" onclick="history.back()" style="font-size:12px;">返回</span>
					    </div>
				 </div>
									
									
				 <div>
					<table class="format">
						     <thead>	
						       <tr>											    
									<th width="45%">内容</th>
									<th width="10%">评论用户</th>
									<th width="5%">审核状态</th>
									<th width="10%">创建时间</th>
									<th width="10%">审核时间</th>
									<th width="20%">操作</th>
								</tr>		
							</thead>
							<tbody>
								[#list topicComment as item]
							      <tr>							          
							          <td>${item.content}</td>
							          <td>${item.typeVal}-${item.createUser}</td>
							          <td>${item.statusVal}</td>
							          <td>${item.createTimeHandle}</td>
							          <td>${item.checkTimeHandle}</td>		
							          <td>
							            <span class="btn del" rel="${base}/topic/commentPass.do?id=${item.id}">通过</span>
							            <span class="btn del" rel="${base}/topic/commentNoPass.do?id=${item.id}">不通过</span>
							            [#if item.hcomment==0]
							               <span class="btn">没有子评论</span>
							            [#else]
							               <span class="btn"><a href="detail.xhtml?pid=${pid}&mid=${mid}&cid=${item.id}&id=${topic.id}&storey=2">查看子评论</a></span>				
							            [/#if]
							          </td>				        
							      </tr>
							    [/#list]
						    </tbody>
					</table>
					<div style="text-align:center;padding-top:10px">
					      [#if storey==2]
					        <span class="btn search" onclick="history.back()" style="font-size:12px;">返回上一层</span>
					       [/#if]  
					 </div>
				</div>												         
		    </div>
		    [#include "/common/pagination.ftl"]	
	</div>
	[#include "/common/menu.ftl"]
</div>
<!-- footer -->
[#include "/common/foot.ftl"]
</body>

<script>
   $(function(){     
      $(".pass").click(function(){
	       var data=$(this).attr("rel");
	       $.post("pass.do",{id:data},function(json){
	              justTip(json.msg);
	              if(json.ret==0){
	                 location.reload();
	              }
	       })
      });
      $(".noPass").click(function(){
	       var data=$(this).attr("rel");
	       $.post("noPass.do",{id:data},function(json){
	              justTip(json.msg);
	               if(json.ret==0){
	                 location.reload();
	              }
	       })
      });
   });
</script>
</html>