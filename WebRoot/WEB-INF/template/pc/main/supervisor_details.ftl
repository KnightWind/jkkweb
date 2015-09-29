<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="keywords" content="家可可,家可可装修,装修O2O,新房装修,客厅装修,小户型装修" />
<meta name="description" content="家可可是一家新兴家装O2O平台，拥有丰富的装修设计案例参考，业主装修日记，附近实景工地；全程监管，业主与可以在线查看装修进程；完善的信用体系，装修公司、监理口碑在线查看，一键完成幸福家！" />
<title>家可可装修-让装修真保障！新房装修, 客厅装修, 小户型装修轻松搞定！</title>
[#include "/pc/commonPc/resource.ftl"]
<style>
.clr{content:'';overflow:hidden;display:block;clear:both;}
.pic1{height:255px;background: url(${base}/images/activity/zt2_1.jpg) no-repeat center 0;}
.pic2{height:759px;background: url(${base}/images/activity/zt2_2.jpg) no-repeat center 0;}
.pic3{height:349px;background: url(${base}/images/activity/zt2_3.jpg) no-repeat center 0;}
.pic4{height:1071px;background: url(${base}/images/activity/zt2_4.jpg) no-repeat center 0;}
.pic5{height:216px;background: url(${base}/images/activity/zt1_10.jpg) no-repeat center 0;}
.select_package{background-color: #f8f8f8;}
.select_package_content{width:1000px;margin:0 auto; font-size: 30px;padding: 87px 0;text-align: center;}
.select_package_content p{cursor:pointer;}
.select_package_content p.gouxuan{margin-bottom: 48px;line-height: 59px;width: 400px;margin-left: auto;margin-right: auto;}
.select_package_content p b{display: block;float: left;width: 59px;height: 59px;background: url(${base}/images/activity/select.jpg) no-repeat left top;margin-right: 30px;}
.select_package_content p b.cur{display: block;float: left;width: 59px;height: 59px;background: url(${base}/images/activity/select_ok.jpg) no-repeat left top;margin-right: 30px;}
.select_package_content p span{float: left;}
.select_package_content p strong{font-weight: normal;color: #e86a32;}
.select_package_content a{background-color: #e86a32;display: block;color: #fff;height: 136px;border-radius: 5px;-moz-border-radius: 5px;-webkit-border-radius: 5px;line-height: 136px;text-align: center;font-size: 40px;margin-bottom: 20px;}
</style>
</head>
<body rel="0">
	<div class="pic1"></div>
    <div class="pic2"></div>
    <div class="pic3"></div>
    <div class="pic4"></div>
    <div class="select_package">
        <div class="select_package_content">
            <p class="clr gouxuan">
                <b class="cur"></b><span>管家套餐<strong>199</strong>元</span>
            </p> 
            <p class="clr gouxuan">
                <b></b><span>质量套餐<strong>399</strong>元</span>
            </p>
            <p class="clr gouxuan">
                <b></b><span>至尊套餐<strong>599</strong>元</span>
            </p>
            <a href="#">去支付</a>
            <p>限量体检：前99名，只需<strong>199元</strong>即可享受管家套餐的服务</p>
        </div>
    </div>
	[#include "/pc/commonPc/footer.ftl"]
	<script type="text/javascript" src="${base}/scripts/jquery-1.9.1.min.js"></script>
	<script type="text/javascript">
    $(".select_package_content p").bind("click",function(){
        $(".select_package_content p b").removeClass("cur");
        $(this).find("b").addClass("cur"); 
    })
</script>
</body>
</html>