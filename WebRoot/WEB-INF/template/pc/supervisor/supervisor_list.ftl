<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title></title>
<meta name="description" content="">
<meta name="keywords" content="">
<link href="${base}/styles/pc/common.css" rel="stylesheet">
<link href="${base}/styles/pc/jianli.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${base}/styles/pc/slide.css">
<link rel="stylesheet" type="text/css" href="${base}/styles/common.css">
<script type="text/javascript" src="${base}/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${base}/scripts/template.min.js"></script>

<script type="text/javascript" src="${base}/scripts/common.js"></script>
<script>
$(function(){
		$(".parentRegion").change(function(){
		    $(".childRegion").empty();
			 var pid=$(this).val();
			 if(pid==66){
			   return false;
			 }
			 $.post("${base}/main/mainPC/child.do",{pid:pid},function(rel){
				if(rel.length>0){
				   for(var i=0;i<rel.length;i++){
				        $(".childRegion").append("<option value="+rel[i].regionid+">"+rel[i].regionname+"</option>");
				   }
				}
			 });
		});
		$(".submitOneAppointment").click(function(){
			    var mobile=$(".mobile").val();
				var regionid=$(".childRegion").val();
				var community=$(".community").val();
				var space=$(".space").val();
				var budget=$(".budget").val();
				var method=$(".method").val();
			$.post("${base}/main/mainPC/addOne.do",{mobile:mobile,regionid:regionid,community:community,space:space,budget:budget,method:method},function(rel){
			     justTip(rel.msg);
			     if(rel.ret==0){
			         $(".community").val("");
			         $(".mobile").val("");
			         $(".space").val("");
			         $(".budget").val("");
			     }
			});
		});
});
</script>

<style>
.justTip{ padding: 20px; color: #fff; line-height: 20px; border-radius: 5px; text-align: left; display:none; font-size: 14px; background: #515558;  position: absolute; z-index: 10008; display: inline-block; }
.justTip i { font-size: 16px; color: #fff; margin-right: 5px;}
</style>

</head>
<body>
    <div class="header">
        [#include "/pc/commonPc/menuPc.ftl"]
    </div>
    <div class="decorateCompany clearfix jianli">
    <div clas="fl">
    	<div class="companyList">
            [#list pagination.dataList as item]
            <div class="company clearfix">
            	<i>
            	[#if item.filepath != null]
            		 <img width="94" height="94" src="${basePath}/${item.filepath}">
            	[#else]
            		<img width="94" height="94" src="${base}/images/nopic.jpg">
            	[/#if]
            	</i>
                <p>
                    <b class="companyName">${item.spName}</b>
                    <strong class="star"><!-- <i class="orange"></i><i class="orange"></i><i class="orange"></i><i class="orange"></i><i class="white"></i> -->
                        <img src="${base}/images/${item.zongHe}.png" width="100" height="15" />
                        <i>${item.assess}</i>
                    </strong>
                    <span>
                        <strong>
                            <b class="kin">藏</b>${item.collectCount}
                        </strong>
                        <strong>
                            <b class="safe">案</b>${item.caseNum}套
                        </strong>
                    </span>
                </p>
            </div>
            [/#list]
        </div>
    </div>  
    
        <div class="publishNeed">
            <div class="publishNeedForm">
                <input type="text" class="mobile" placeholder="手机号码" />
                <select class="half first parentRegion">
                   <option value="66">请选择</option>
	                [#list parentRegions as item]
	                    <option value="${item.regionid}">${item.regionname}</option>
	                [/#list]
                </select>
                <select class="half childRegion">
                  	
                </select>
                <input type="text" class="community" placeholder="小区名字" />
                <input type="text" class="space" placeholder="房屋面积" />
                <input type="text" class="budget" placeholder="装修预算" />
                <select class="half method">
                    <option value="2">全包</option>
                    <option value="1">半包</option>
                </select>
                <a class="submitOneAppointment">发布装修需求</a>
            </div>
        </div> 
    </div>

	    <div class="ui-paging" style="text-align:center;margin-left:-400px;[#if (pagination.pages <= 1)]display:none;[/#if]">
			<a href="#" class="ui-paging-prev" [#if !pagination.hasPreviousPage]style="cursor: text;"[/#if]>上一页</a>
			[#list pagination.navigatePageNumbers as item]
			<a href="#" class="ui-paging-item [#if pagination.pageNumber == item]ui-paging-current[/#if]" rel="${item}">${item}</a>
			[/#list]
			<a href="#" class="ui-paging-next" [#if !pagination.hasNextPage]style="cursor: text;"[/#if]>下一页 </a>
			<span class="ui-paging-info">
			<span class="ui-paging-bold">${pagination.pageNumber}/${pagination.pages}</span>页，共
			<span class="ui-paging-bold">${pagination.total}</span>条，第 </span>
			<span class="ui-paging-which">
				<input name="some_name" type="text">
			</span>
			<span class="ui-paging-bold">页</span>
			<a class="ui-paging-info ui-paging-goto" href="#" max_page="${pagination.pages}">跳转</a>
		</div> 
	[#include "/pc/commonPc/footer.ftl"]
    <div class="suspend">
        <p class="borderB">
            <a href="${base}/main/mainPC/index.xhtm">
                <img src="${base}/images/pc/need.png">
                <span>发布装修需求</span>
            </a>
        </p>
        <p class="borderB">
            <img src="${base}/images/pc/all_erweima.png">
            <span>扫一扫关注微信</span>
        </p>
        <p class="borderB">
            <img src="${base}/images/pc/all_anzhuo.png">
            <span>iphone下载</span>
        </p>
        <p>
            <img src="${base}/images/pc/all_iphone.png">
            <span>Android下载</span>
        </p>
    </div>
</body>

<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<div class="company clearfix">
    	<i>
    	<%if(result.elements[i].filepath != null){%>
    		 <img width="94" height="94" src="${basePath}/<%=result.elements[i].filepath%>">
    	<%}else{%>
    		<img onload="" width="94" height="94" src="${base}/images/nopic.jpg">
    	<%}%>
    	</i>
        <p>
            <b class="companyName"><%=result.elements[i].spName%></b>
            <strong class="star"><!-- <i class="orange"></i><i class="orange"></i><i class="orange"></i><i class="orange"></i><i class="white"></i> -->
                <img src="${base}/images/<%=result.elements[i].zongHe%>.png" width="100" height="15" />
                <i><%=result.elements[i].assess%></i>
            </strong>
            <span>
                <strong>
                    <b class="kin">藏</b><%=result.elements[i].collectCount%>
                </strong>
                <strong>
                    <b class="safe">案</b><%=result.elements[i].caseNum%>套
                </strong>
            </span>
        </p>
    </div>
<%}%>
</script>

</html>
<script type="text/javascript">
$('.navLists li:eq(1) a').addClass('active');

$(function(){
	$('.ui-paging').page({
		url:'${base}/main/supervisorPC/pagination.do',
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
			$('.companyList').html(template.render('list',dataR));
			window.scrollTo(0,0);
	    }
	});
});	

</script>