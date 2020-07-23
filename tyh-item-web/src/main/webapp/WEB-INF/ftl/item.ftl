<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
		<title>商品详情</title>

		<!-- Bootstrap -->
		<link href="../css/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
		<!-- 商品详情部分 -->
		<link href="../css/details.css" rel="stylesheet" />
		<!-- layui -->
		<link href="../layui/css/layui.css" rel="stylesheet" />

		<!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
		<!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
		<!--[if lt IE 9]>
		<script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
		<![endif]-->
	</head>
	<body>
		
		<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
		<script src="../js/jquery2.2.2.min.js"></script>
		<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
		<script src="../css/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
		<!-- layUI的js文件 -->
		<script src="../layui/layui.js"></script>
		<!-- 下面这部分js是详情部分的，网上找的，我也看不懂 -->
		<script src="../js/modernizr-custom-v2.7.1.min.js"></script>
		<script type="text/javascript">
			$(function() {
				$(".item a").on('mouseenter', function() {
					//获取鼠标移动到元素的scr
					var temp_small_src = $(this).find('img').attr('src');
					//获取大图的元素,然后修改地址为移动元素的src
					$(this).siblings('a').find('img[tag=big]').attr('src', temp_small_src);
				})
			});
		</script>
		
		<script>
			$(document).ready(function(){
		var $miaobian=$('.Xcontent08>div');
		var $huantu=$('.Xcontent06>img');
		var $miaobian1=$('.Xcontent26>div');
		$miaobian.mousemove(function(){miaobian(this);});
		$miaobian1.click(function(){miaobian1(this);});
		function miaobian(thisMb){
			for(var i=0; i<$miaobian.length; i++){
				$miaobian[i].style.borderColor = '#dedede';
			}
			thisMb.style.borderColor = '#cd2426';
		
			$huantu[0].src = thisMb.children[0].src;
		}
		function miaobian1(thisMb1){
			for(var i=0; i<$miaobian1.length; i++){
				$miaobian1[i].style.borderColor = '#dedede';
			}
		//		thisMb.style.borderColor = '#cd2426';
			$miaobian.css('border-color','#dedede');
			thisMb1.style.borderColor = '#cd2426';
			$huantu[0].src = thisMb1.children[0].src;
		}
				$(".Xcontent33").click(function(){
				var value=parseInt($('.input').val())+1;
				$('.input').val(value);
			})
		
			$(".Xcontent32").click(function(){	
				var num = $(".input").val()
				if(num>0){
					$(".input").val(num-1);
				}			
				
			})
		
			})
		</script>
		
		<!-- 导航条 -->
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					 aria-expanded="false">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#">商城详情页面</a>
				</div>

				<!-- ������ -->
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li class="active"><a href="#">商城分类<span class="sr-only">(current)</span></a></li>
						<li><a href="http://localhost:8091/home">购物车</a></li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">商品类别
								<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="#">牛仔裤</a></li>
								<li><a href="#">饮料</a></li>
								<li><a href="#">零食</a></li>
								<li role="separator" class="divider"></li>
								<li><a href="#">生活用品</a></li>
							</ul>
						</li>
					</ul>
					<form class="navbar-form navbar-left">
						<div class="form-group">
							<input type="text" class="form-control" placeholder="查询商品">
						</div>
						<button type="submit" class="btn btn-default">查询</button>
					</form>
					<ul class="nav navbar-nav navbar-right">
						<li><a href="../loginAndRegister/loginAndRegister.html" cursor="pointer" class="text-warning">登录</a></li>
						<li><a href="../loginAndRegister/loginAndRegister.html" cursor="pointer">注册</a></li>
					</ul>
				</div><!-- /.navbar-collapse -->
			</div><!-- /.container-fluid -->
		</nav>
		<!-- 商品详情部分 -->
		<div class="Xcontent">
			<ul class="Xcontent01">
				
				<div class="Xcontent06">
						<#list imgs as img>
							<#if img_index == 0>
								<img src="${fserver}${img.pimgaddr}" id="big" width="430" height="430">
							</#if>
						</#list>
				</div>
				<ol class="Xcontent08">
				
					<div class="Xcontent07">
						<#list imgs as img>
							<#if img_index == 1>
								<img src="${fserver}${img.pimgaddr}">
							</#if>
						</#list>
					</div>
					<div class="Xcontent09">
						<#list imgs as img>
							<#if img_index == 2>
								<img src="${fserver}${img.pimgaddr}">
							</#if>
						</#list>
					</div>
					<div class="Xcontent10">
						<#list imgs as img>
							<#if img_index == 3>
								<img src="${fserver}${img.pimgaddr}">
							</#if>
						</#list>
					</div>
					<div class="Xcontent11">
						<#list imgs as img>
							<#if img_index == 4>
								<img src="${fserver}${img.pimgaddr}">
							</#if>
						</#list>
					</div>
					<div class="Xcontent12">
						<#list imgs as img>
							<#if img_index == 5>
								<img src="${fserver}${img.pimgaddr}">
							</#if>
						</#list>
					</div>
				</ol>
				<ol class="Xcontent13">
					<div class="Xcontent14"><a href="#"><p>${pro.pname}</p></a></div>
					<div class="Xcontent15"><img src="../img/details/X11.png"></div>
					<div class="Xcontent16"><p>${pro.pkeywords}</p></div>
					<div class="Xcontent17">
						<p class="Xcontent18">售价</p>
						<p class="Xcontent19">￥<span>${pro.pprice}</span></p>
						<div class="Xcontent20">
							<p class="Xcontent21">促销</p>
							<img src="../img/details/X12.png">
							<p class="Xcontent22">领610元新年礼券，满再赠好礼</p>
						</div>
						<div class="Xcontent23">
							<p class="Xcontent24">服务</p>
							<p class="Xcontent25">30天无忧退货&nbsp;&nbsp;&nbsp;&nbsp;48小时快速退款 &nbsp;&nbsp;&nbsp;&nbsp;满88元免邮</p>
						</div>
						
					</div>
					<div class="Xcontent30">
						<p class="Xcontent31">数量</p>
						<div class="Xcontent32"><img src="../img/details/X15.png"></div>
						<form><input class="input" value="1"></form>
						<div class="Xcontent33"><img src="../img/details/16.png"></div>
					</div>
					<div class="Xcontent34"><a href="#"><img src="../img/details/X17.png"></a></div>
					<div class="Xcontent35"><a href="#"><img src="../img/details/X18.png"></a></div>
				</ol>
			</ul>
		</div>
		<h1 align="center">这下面是商品的图文...</h1>
		<div class="page-header">
					<div id="leftt" style="height: 4000px;width: 350px;float: left;"></div>
					<div id="centerr" style="height: 4000px;width: 800px;float: left;">
						<#list imgs as img>
							<img src="${fserver}${img.pimgaddr}" width="600" height="600" style="margin-left: 100px;margin-right: 100px;">
							<br>
							<br>
							<br>
							<br>
						</#list>
						<p style="font-size: 30px;color: teal;font-weight: 400;">${pro.pdes}</p>
					</div>
					<div id="rightt" style="height: 4000px;width: 350px;float: right;"></div>
		</div>
		
		<script>
			//引入layUI
			layui.use('element', function() {
				var element = layui.element;
			
			});
			
			//引入layer,即layUI的弹窗层
			layui.use('layer', function() { //�������layer����ִ����һ��
				var $ = layui.jquery,
					layer = layui.layer; //�������layer����ִ����һ��
			});
			
			//layui的top按钮，就是点击回到页面最上方的那个
			layui.use('util', function(){
			  var util = layui.util
			    ,laydate = layui.laydate
			    ,$ = layui.$
			    ,layer = layui.layer;
			    //�̶���
			    util.fixbar({
			      bar1: true
			      ,bar2: true
			      ,css: {right: 50, bottom: 100}
			      ,bgcolor: '#393D49'
			      ,click: function(type){
			        if(type === 'bar1'){
			          layer.msg('icon 是可以随便换的')
			        } else if(type === 'bar2') {
			          layer.msg('两个 bar 都可以设定是否开启')
			        }
			      }
			    });
			});
		</script>
	</body>
</html>