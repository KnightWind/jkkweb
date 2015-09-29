<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>装修公司</title>
<meta name="description" content="">
<meta name="keywords" content="">
[#include "/pc/commonPc/resource.ftl"]
<link rel="stylesheet" type="text/css" href="${base}/styles/pc/decorateCompany.css">
</head>
<body rel="2">
    <div class="header mb20">
        [#include "/pc/commonPc/menuPc.ftl"]
    </div>
    <div class="decorateCompany clearfix">
        <div class="companyList">
            [#list pagination.dataList as b]
	            <div class="company clearfix">
	                <i>
	                	[#if b.filepath != null]
	                		<img width="94" height="94" src="${basePath}${b.filepath}">
	                	[#else]
	                		<img width="94" height="94" src="${base}/images/nopic.jpg">
	                	[/#if]
	                </i>
	                <p>
	                    <!--<a href="${base}/main/businessPC/details.xhtm?id=${b.id}" class="companyName">${b.spName}</a>-->
	                    <a class="companyName">${b.spName}</a>
	                    <span>
	                        <strong>
	                            <b class="kin">金</b>${b.money}元
	                        </strong>
	                        <strong>
	                            [#if b.jkbFlag == 1]
	                            	<b class="safe">保</b>
	                            [/#if]
	                            ${b.engcount}套
	                        </strong>
	                    </span>
	                    <strong class="locall">${b.address}</strong>
	                </p>
	            </div>
            [/#list]
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
                  	<option value="0">请选择</option>
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
        <div style="clear:both"></div>
        <div class="ui-paging" style="text-align:left;[#if (pagination.pages <= 1)]display:none;[/#if]">
			<a href="#" class="ui-paging-prev" [#if !pagination.hasPreviousPage]style="cursor: text;"[/#if]>上一页</a>
			[#list pagination.navigatePageNumbers as item]
			<a href="#" class="ui-paging-item [#if pagination.pageNumber == item]ui-paging-current[/#if]" rel="${item}">${item}</a>
			[/#list]
			<a href="#" class="ui-paging-next" [#if !pagination.hasNextPage]style="cursor: text;"[/#if]>下一页</a>
			<span class="ui-paging-info">
				<span class="ui-paging-bold">${pagination.pageNumber}/${pagination.pages}</span>页，共
				<span class="ui-paging-bold">${pagination.total}</span>条，第 </span>
				<span class="ui-paging-which">
					<input name="some_name" type="text">
				</span>
				<span class="ui-paging-bold">页</span>
				<a class="ui-paging-info ui-paging-goto" href="#" max_page="${pagination.pages}">跳转</a>
		</div>
    </div>
    [#include "/pc/commonPc/footer.ftl"]
    <!--
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
    -->
</body>
<script type="text/html" id="list">
<%for(i=0;i<result.elements.length;i++){%>
	<div class="company clearfix">
        <i>
        	<%if(result.elements[i].filepath != null){%>
        		<img width="94" height="94" src="${basePath}<%=result.elements[i].filepath%>">
        	<%}else{%>
        		<img width="94" height="94" src="${base}/images/nopic.jpg">
        	<%}%>
        </i>
        <p>
            <a href="${base}/main/businessPC/details.xhtm?id=${b.id}" class="companyName"><%=result.elements[i].spName%></a>
            <span>
                <strong>
                    <b class="kin">金</b><%=result.elements[i].money%>元
                </strong>
                <strong>
                    <%if(result.elements[i].jkbFlag == 1){%>
                    	<b class="safe">保</b>
                    <%}%>
                   <%=result.elements[i].engcount%>套
                </strong>
            </span>
            <strong class="locall"><%=result.elements[i].address%></strong>
        </p>
    </div>
<%}%>
</script>
<script type="text/javascript">
$(function(){
		$(".parentRegion").change(function(){
		    $(".childRegion").empty();
			 var pid=$(this).val(),temp=[];
			 if(pid==66){
			   return false;
			 }
			 $.post("${base}/main/mainPC/child.do",{pid:pid},function(rel){
				if(rel.length>0){
				   for(var i=0;i<rel.length;i++){
				   		temp[i]="<option value="+rel[i].regionid+">"+rel[i].regionname+"</option>";
				   }
				   $(".childRegion").html(temp.join());
				}
			 });
		});
		$(".submitOneAppointment").click(function(){
			    var mobile=$(".mobile").val(),
					regionid=$(".childRegion").val(),
					community=$(".community").val(),
					space=$(".space").val(),
					budget=$(".budget").val(),
					method=$(".method").val();
			$.post("${base}/main/mainPC/addOne.do",{mobile:mobile,regionid:regionid,community:community,space:space,budget:budget,method:method},function(rel){
			     justTip(rel.msg);
			     if(rel.ret==0){
			         $(".community,.mobile,.space,.budget").val("");
			     }
			});
		});
});
$(function(){
	$('.ui-paging').page({
		url:'${base}/main/businessPC/pagination.do',
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
			$('html,body').animate({scrollTop:0},50);
	    }
	});
});	
</script>
</html>