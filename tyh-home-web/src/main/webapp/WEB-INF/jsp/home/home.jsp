<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = "";
%>

<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
		<title>home</title>

		<!-- Bootstrap -->
		<link href="../css/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
		<link href="../css/home.css" rel="stylesheet" />
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
		<!-- jQuery的cookie插件 -->
		<script src="../js/jquery.cookie.js"></script>
		<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
		<script src="../css/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
		<!-- layUI的js文件 -->
		<script src="../layui/layui.js"></script>
		
		<!-- 导航栏 -->
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
					<a class="navbar-brand" href="#">商城首页</a>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li class="active"><a href="#">商城分类 <span class="sr-only">(current)</span></a></li>
						<li><a href="#">购物车</a></li>
						<li class="dropdown">
							<a id="navItemcat" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">商品类别
								<span class="caret"></span></a>
							<ul class="dropdown-menu" id="navItemcatlist">
								<li><a href="#" onclick="getFirstProduct()">全部类型</a></li>
								<li role="separator" class="divider"></li>
								<li><a href="#">学习用品</a></li>
							</ul>
						</li>
					</ul>
					<form class="navbar-form navbar-left">
						<div class="form-group">
							<input type="text" class="form-control" placeholder="查询商品">
						</div>
						<button type="submit" class="btn btn-default">查询</button>
					</form>
					<ul class="nav navbar-nav navbar-right" id="loginOrRegister">
						<li><a href="http://localhost:8090/user/tologinOrRegister" cursor="pointer" class="text-warning">登录</a></li>
						<li><a href="http://localhost:8090/user/tologinOrRegister" cursor="pointer">注册</a></li>
					</ul>
				</div><!-- /.navbar-collapse -->
			</div><!-- /.container-fluid -->
		</nav>
		
		<!-- 商品类型列表 -->
		<div class="list-group col-md-1" id="typelist">
			<a href="#" onclick="getFirstProduct()" class="list-group-item active">
				商品类型
			</a>
			<!-- 示例商品类型条 -->
			<!-- <a href="#" class="list-group-item">学习用品</a> -->
		</div>
		
		
		<!-- 轮播窗口 -->
		<div id="homecarousel" class="carousel slide col-md-offset-1" data-ride="carousel">
			<!-- Indicators -->
			<ol class="carousel-indicators">
				<li data-target="#homecarousel" data-slide-to="0" class="active"></li>
				<li data-target="#homecarousel" data-slide-to="1"></li>
				<li data-target="#homecarousel" data-slide-to="2"></li>
			</ol>

			<!-- Wrapper for slides -->
			<div class="carousel-inner" role="listbox">
				<div class="item active">
					<img src="../img/carousel1.jpg" alt="">
					<div class="carousel-caption">
						...
					</div>
				</div>
				<div class="item">
					<img src="../img/carousel2.jpg" alt="...">
					<div class="carousel-caption">
						...
					</div>
				</div>
				<div class="item">
					<img src="../img/carousel3.webp" alt="...">
					<div class="carousel-caption">
						...
					</div>
				</div>
			</div>

			<!-- Controls -->
			<a class="left carousel-control" href="#homecarousel" role="button" data-slide="prev">
				<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				<span class="sr-only">Previous</span>
			</a>
			<a class="right carousel-control" href="#homecarousel" role="button" data-slide="next">
				<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			</a>
		</div>

		<div class="page-header">
			<h1>欢迎来选购属于你的商品<small>!!!</small></h1>
		</div>
		
		<!-- 商品展示列表，点击图片或商品标题可进入商品详情页面-->
		<div id="productlist">
			<div class="row" id="firstProduct">
				<!-- 示例商品信息框 -->
				<!-- <div class="col-sm-6 col-md-3">
					<div class="thumbnail">
						<a href="../details/details.html" target="_blank">
							<img src="../img/OPPO_A5.jpg" alt="OPPO_A5"></a>
						<div class="caption">
							<h3><a href="../details/details.html" target="_blank">OPPO_A5</a></h3>
							<p>...</p>
							这两个暂时不做
							<p><a href="#" class="btn btn-default btn-warning" role="button" data-toggle="tooltip" data-placement="bottom" title="暂时不做实现">加入购物车</a>
								<a href="#" class="btn btn-default btn-warning" role="button" data-toggle="tooltip" data-placement="bottom" title="暂时不做实现">立即购买</a></p>
						</div>
					</div>
				</div> -->

			</div>
		</div>
		
		<!-- 分页组件 -->
		<nav aria-label="Page navigation" class="text-center">
			<ul class="pagination">
				<li class="disabled">
					<a href="#" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
					</a>
				</li>
				<li class="active"><a href="#">1</a></li>
				<li><a href="#">2</a></li>
				<li><a href="#">3</a></li>
				<li><a href="#">4</a></li>
				<li><a href="#">5</a></li>
				<li>
					<a href="#" aria-label="Next">
						<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
			</ul>
		</nav>
		
		<script>
			//引入layUI
			layui.use('element', function() {
				var element = layui.element;
			
			});
			
			//引入layer,即layUI的弹窗层
			layui.use('layer', function() { //独立版的layer无需执行这一句
				var $ = layui.jquery,
					layer = layui.layer; //独立版的layer无需执行这一句
			});
			
			//layui的top按钮，就是点击回到页面最上方的那个
			layui.use('util', function(){
			  var util = layui.util
			    ,laydate = layui.laydate
			    ,$ = layui.$
			    ,layer = layui.layer;
			    //固定块
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
			
			//使用返回的json刷新商品数据列表
			function eachData(data) {
				$.each(data,function(i,product){
					$("#firstProduct").append(
							"<div class=\"col-sm-6 col-md-3\">"+
							"<div class=\"thumbnail\">"+
							"<a href=\"\" target=\"_blank\">"+
								"<img src=\""+"${fserver}"+product.pimg+"\" alt=\""+product.pname+"\"></a>"+
							"<div class=\"caption\">"+
								"<h3><a href=\"\" target=\"_blank\">"+product.pname+"</a></h3>"+
								"<p>...</p>"+
								"<p><a href=\"#\" class=\"btn btn-default btn-warning\" role=\"button\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"暂时不做实现\">加入购物车</a>"+
									"<a href=\"#\" class=\"btn btn-default btn-warning\" role=\"button\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"暂时不做实现\">立即购买</a></p>"+
							"</div></div></div>"
					);
				});
			}
			
			//获取首推商品列表（数据库中的图片数据有误，导致了浏览器的两个请求404）
			function getFirstProduct() {
				$("#firstProduct").empty();//刷新商品列表前进行清空操作
				$.ajax({
					url:"${pageContext.request.contextPath}/getFirstProduct",
					type:"GET",
					dataType:"json",
					//async:true,    //是否异步
					//dataType:"jsonp",
					//jsonp: "callbackparam",
					success:function(data){
						eachData(data);
					}
				});
			}
			
			//根据商品类型获取商品列表
			function getProductByItemCat(catid) {
				$("#firstProduct").empty();//刷新商品列表前进行清空操作
				$.ajax({
					url:"${pageContext.request.contextPath}/productList/"+catid,
					type:"GET",
					dataType:"json",
					success:function(data){
						eachData(data);
					}
				});
			}
			
			//获取商品类型列表
			function getItemCatList() {
				$.ajax({
					url:"${pageContext.request.contextPath}/getItemCatList",
					type:"GET",
					dataType:"json",
					//async:true,    //是否异步
					//dataType:"jsonp",
					//jsonp: "callbackparam",
					success:function(data){
						//初始化左侧商品类型列表
						$.each(data,function(i,itemcat){
							$("#typelist").append("<a href=\"javascript:void(0);\" onclick=\"getProductByItemCat(\'"+itemcat.catid+"\')\" class=\"list-group-item\">"+itemcat.catname+"</a>");
							//初始化七个后列表就只剩下一个位置了，用来存放“...”
							if(i==6){
								$("#typelist").append("<a href=\"javascript:void(0);\" id=\"moreItemcat\" class=\"list-group-item\" onclick=\"clickMoreItemCat()\">...</a>");
								return false;
							}
						});
						//初始化导航栏中的商品类型列表
						$.each(data,function(i,itemcat){
							$("#navItemcatlist").append("<li><a href=\"javascript:void(0);\" onclick=\"getProductByItemCat(\'"+itemcat.catid+"\')\">"+itemcat.catname+"</a></li>");
						});
					}
				});
			}
			
			function logout() {
				layer.confirm('确认退出登录吗?', function(index){
					layer.close(index);
					$.ajax({
						url:"http://localhost:8090/user/logout",
						type:"GET",
						dataType:"jsonp",    //跨域json请求一定是jsonp
						success:function(data){
							
						}
					});
					//退出登录提示框
					layer.msg('你已成功退出登录', {
						  icon: 1,
						  time: 2000 //2秒关闭（如果不配置，默认是3秒）
					});
					//等待一秒后刷新当前页面
					setTimeout(function() {window.location.reload();}, 1000);
				});  
			}
			
			function checkUserState() {
				//从浏览器cookie中读取用户登录信息
				var token = $.cookie('token');
				$.ajax({
					url:"http://localhost:8092/sso/sso/getuser/"+token,
					type:"GET",
					dataType:"json",
					success:function(data){
						if(data!=null){
							$("#loginOrRegister").empty();
							$("#loginOrRegister").append(
									"<li class=\"dropdown\">"+
								    "<a href=\"javascript:;\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\">"+
								    "<img src=\"//t.cn/RCzsdCq\" class=\"layui-nav-img\">"+data.username+"<span class=\"caret\"></span></a>"+
								    "<ul class=\"dropdown-menu\">"+
								      "<li><a href=\"http://localhost:8080/manager/product/list\" target=\"_blank\">后台管理</a></li>"+
								      "<li><a href=\"javascript:;\" onclick=\"logout()\">退出登录</a></li>"+
								  "</ul></li>");
						}
					}
				});
			}
			
			function clickMoreItemCat() {
				$("#navItemcat").click();
			}
			
			//进入页面时执行的初始化方法
			$(document).ready(function () {
				getItemCatList();
				getFirstProduct();
				checkUserState();
			});
		</script>

	</body>
</html>
