<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<!-- Bootstrap -->
		<link href="${pageContext.request.contextPath}/css/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
		<!-- layUI -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
		<!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
		<!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
		<!--[if lt IE 9]>
        <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
      <![endif]-->
		<title>商品列表</title>
<!-- <script type="text/javascript">
	function publish(id){
		$.ajax({
			type:"POST",
			url:"${pageContext.request.contextPath}/manager/product/publish/"+id,
			success:function(msg){
				alert(msg);
			}
		});
	}

</script> -->	
	</head>
	<body class="layui-layout-body">
		<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
		<script src="${pageContext.request.contextPath}/js/jquery2.2.2.min.js"></script>
		<!-- jQuery的cookie插件 -->
		<script src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>
		<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
		<script src="${pageContext.request.contextPath}/css/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
		<!-- layUI的js文件 -->
		<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
		
		<div class="layui-layout layui-layout-admin">
			<div class="layui-header">
				<div class="layui-logo">后台管理</div>
				<!-- 头部区域（可配合layui已有的水平导航） -->
				<!-- <ul class="layui-nav layui-layout-left">
					<li class="layui-nav-item"><a href="">控制台</a></li>
					<li class="layui-nav-item"><a href="">商品管理</a></li>
					<li class="layui-nav-item"><a href="">用户</a></li>
					<li class="layui-nav-item">
						<a href="javascript:;">其它系统</a>
						<dl class="layui-nav-child">
							<dd><a href="">邮件管理</a></dd>
							<dd><a href="">消息管理</a></dd>
							<dd><a href="">授权管理</a></dd>
						</dl>
					</li>
				</ul> -->
				<ul class="layui-nav layui-layout-right">
					<li class="layui-nav-item">
						<a href="javascript:;" id="loginOrRegister">
							<img src="http://t.cn/RCzsdCq" class="layui-nav-img">
							管理员
						</a>
						<dl class="layui-nav-child">
							<dd><a href="">基本资料</a></dd>
							<dd><a href="">安全设置</a></dd>
						</dl>
					</li>
					<li class="layui-nav-item"><a href="javascript:;" onclick="logout()">退出</a></li>
				</ul>
			</div>

			<div class="layui-side layui-bg-black">
				<div class="layui-side-scroll">
					<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
					<ul class="layui-nav layui-nav-tree" lay-filter="test">
						<li class="layui-nav-item">
							<a class="" href="javascript:;">商品类型管理</a>
							<dl class="layui-nav-child">
								<dd><a href="${pageContext.request.contextPath}/manager/itemcat/list">商品类型列表</a></dd>
								<dd><a href="${pageContext.request.contextPath}/manager/itemcat/toadd">增加商品类型</a></dd>
								<dd><a href="${pageContext.request.contextPath}/manager/itemcat/toedit/null">编辑商品类型</a></dd>
								<!-- <dd><a href="">超链接</a></dd> -->
							</dl>
						</li>
						<li class="layui-nav-item layui-nav-itemed">
							<a href="javascript:;">商品管理</a>
							<dl class="layui-nav-child">
								<dd><a href="${pageContext.request.contextPath}/manager/product/list">商品列表</a></dd>
								<dd><a href="${pageContext.request.contextPath}/manager/product/toadd">增加商品</a></dd>
								<dd><a href="${pageContext.request.contextPath}/manager/product/toedit/null">编辑商品</a></dd>
							
							</dl>
						</li>
						<!-- <li class="layui-nav-item"><a href="">云市场</a></li>
						<li class="layui-nav-item"><a href="">发布商品</a></li> -->
					</ul>
				</div>
			</div>

			<div class="layui-body">
				<!-- 内容主体区域 -->
				<div style="padding: 15px;">
					<ol class="breadcrumb">
						<li><a href="#">商品管理</a></li>
						<li class="active">商品列表</li>
					</ol>
					<p>
						&nbsp;&nbsp;<a class="btn btn-default btn-info" role="button" href="${pageContext.request.contextPath}/manager/product/toadd">增加</a>
					</p>
					
					<!-- 表格上方的   类型   名称&关键字   是否热卖   是否首推   商品状态等-->
	<form class="form-inline" method="post" 
		action="${pageContext.request.contextPath}/manager/product/list">
		<div class="form-group">
			<label>类型：</label>
			<select class="form-control" name="catid">
				<option value="-1">全部</option>
				<c:forEach items="${catlist}" var="cat">
					<c:choose>
						<c:when test="${param.catid==cat.catid}">
							<option value="${cat.catid}" selected="selected">${cat.catname}</option>
						</c:when>
						<c:otherwise>
							<option value="${cat.catid}">${cat.catname}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
		</div>
		
		&nbsp;
		<div class="form-group">
			<label>名称&关键字：</label> 
			<input class="form-control" type="text" name="pname" value="${param.pname}"  placeholder="条"/>
			
		</div>
		
		&nbsp;
		<div class="form-group">
			<label>是否热卖：</label> 
			<select class="form-control" name="phot">
				<c:choose>
					<c:when test="${param.phot==1}">
						<option value="-1">全部</option>
						<option value="1" selected="selected">热卖</option>
						<option value="0">非热卖</option>
					</c:when>
					<c:when test="${param.phot==0}">
						<option value="-1">全部</option>
						<option value="1">热卖</option>
						<option value="0" selected="selected">非热卖</option>
					</c:when>
					<c:otherwise>
						<option value="-1">全部</option>
						<option value="1">热卖</option>
						<option value="0">非热卖</option>
					</c:otherwise>
				</c:choose>
			</select>
		</div>
		&nbsp;
		<div class="form-group">
			<label>是否首推：</label> 
			<select class="form-control" name="pfirst">
				<c:choose>
					<c:when test="${param.pfirst==1}">
						<option value="-1">全部</option>
						<option value="1" selected="selected">首推</option>
						<option value="0">非首推</option>
					</c:when>
					<c:when test="${param.pfirst==0}">
						<option value="-1">全部</option>
						<option value="1">首推</option>
						<option value="0" selected="selected">非首推</option>
					</c:when>
					<c:otherwise>
						<option value="-1">全部</option>
						<option value="1">首推</option>
						<option value="0">非首推</option>
					</c:otherwise>
				</c:choose>
			</select>
		</div>
		&nbsp;
		<div class="form-group">
			<label>商品状态：</label> 
			<select class="form-control" name="pstate">
				<c:choose>
					<c:when test="${param.pstate==1}">
						<option value="-1">全部</option>
						<option value="1" selected="selected">上架</option>
						<option value="0">下架</option>
					</c:when>
					<c:when test="${param.pstate==0}">
						<option value="-1">全部</option>
						<option value="1">上架</option>
						<option value="0" selected="selected">下架</option>
					</c:when>
					<c:otherwise>
						<option value="-1">全部</option>
						<option value="1">上架</option>
						<option value="0">下架</option>
					</c:otherwise>
				</c:choose>
				
			</select>
		</div>
		&nbsp;&nbsp;
		<button type="submit" class="btn btn-default btn-info">查找</button>
		<a class="btn btn-default btn-info" role="button" href="${pageContext.request.contextPath}/manager/product/toadd">增加</a>
			
		</form>
		<br />
					
		<!-- 数据表格 -->
		<table class="table table-hover" border="1">
			<thead>
				<tr>
					<th>商品名称</th>
					<th>商品类型</th>
					<th>图片</th>
					<th>商品价格</th>
					<th>销售价格</th>
					<th>单位</th>
					<th>热卖</th>
					<th>首推</th>
					<th>销售量</th>
					<th>库存量</th>
					<th>状态</th>
					<th>编辑</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="item">
					<tr class="info">
						<td>${item.pname}</td>
						<td>${item.catname}</td>
						<td>
							<img style="width:80px;height:80px" src="${fserver}${item.pimg}" />
						</td>
						<td>${item.pprice}</td>
						<td>${item.psaleprice}</td>
						<td>${item.pdig}</td>
						<td>
							<c:choose>
								<c:when test="${item.phot==1}">热卖</c:when>
								<c:otherwise>非热卖</c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:choose>
								<c:when test="${item.pfirst==1}">首推</c:when>
								<c:otherwise>非首推</c:otherwise>
							</c:choose>
						</td>
						<td>${item.psalenum}</td>
						<td>${item.pstock}</td>
						<td>
							<c:choose>
								<c:when test="${item.pstate==1}">上架</c:when>
								<c:otherwise>下架</c:otherwise>
							</c:choose>
						</td>
						<td>
							<a href="${pageContext.request.contextPath}/manager/product/toedit/${item.pid}" class="text-primary">编辑商品</a>
							<a href="${pageContext.request.contextPath}/manager/product/toimg/${item.pid}" class="text-primary">图片管理</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
					
					
				</div>
			</div>

			<div class="layui-footer">
				<!-- 底部固定区域 -->
				© edu.tyh.com - 底部固定区域
			</div>
		</div>
		
		<script>
			//JavaScript代码区域
			layui.use('element', function() {
				var element = layui.element;

			});
			
			//引入layer,即layUI的弹窗层
			layui.use('layer', function() { //独立版的layer无需执行这一句
				var $ = layui.jquery,
					layer = layui.layer; //独立版的layer无需执行这一句
			});
			
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
							$("#loginOrRegister").append("<img src=\"http://t.cn/RCzsdCq\" class=\"layui-nav-img\">"+data.username);
						}
					}
				});
			}
			
			$(document).ready(function () {
				checkUserState();
			});
		</script>
	</body>
</html>