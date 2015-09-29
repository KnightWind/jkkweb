<div class="headerContent">
        <img class="fl" src="${base}/images/pc/logo.png">
        <div class="place fl">
                <a>北京</a>
                <ul class="selectCity">
                 [#list parentRegions as item]
                    <li rel="${item.regionid}">${item.regionname}</li>
	             [/#list]
                </ul>
         </div>
         <ul class="fl nav" style="background:none;">
           	<li><a href="${base}/main/mainPC/index.do">首页</a></li>
            <li><a>监理</a></li>
            <li><a href="${base}/main/businessPC/index.do">装修公司</a></li>
            <li><a href="${base}/main/activity/index.do">建材众筹</a></li>
            <li><a>装修图库</a></li>
            <!--
            <li><a href="${base}/main/mainPC/index.xhtml">首页</a></li>
            <li><a href="${base}/main/supervisorPC/index.xhtml">监理</a></li>
            <li><a href="http://design.jiakeke.com/">装修图库</a></li>
            -->
        </ul>
        <p class="fr phoneNum">400-1118-108</p>
</div>