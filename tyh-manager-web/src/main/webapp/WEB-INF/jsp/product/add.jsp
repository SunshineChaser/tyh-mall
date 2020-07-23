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
		<!-- ckeditor富文本编辑器的js文件 -->
		<script src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
		
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
						<li class="active">增加商品</li>
					</ol>
					
					<div class="alert alert-warning alert-dismissible" role="alert">
						<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<strong>提示！</strong>${msg}
					</div>
					
					<!-- 表格上方的   类型   名称&关键字   是否热卖   是否首推   商品状态等-->
					<div class="addform">
	<form class="form-horizontal col-md-offset-1"
				action="${pageContext.request.contextPath}/manager/product/add"
				method="post" enctype="multipart/form-data">
				<div class="form-group">
					<label class="col-sm-2 col-md-2 control-label" for="pname">商品名称</label>
					<div class="col-sm-10 col-md-5">
						<input id="pname" class="form-control" type="text" name="pname" placeholder="商品名称"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 col-md-2 control-label" for="catid">商品类型</label>
					<div class="col-sm-10 col-md-5">
						<select id="catid" name="catid" class="form-control">
							<c:forEach items="${catlist}" var="cat">
								<option value="${cat.catid}">${cat.catname}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 col-md-2 control-label" for="pdig">单位</label>
					<div class="col-sm-10 col-md-5">
						<input type="text" name="pdig" id="pdig" class="form-control" placeholder="单位"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 col-md-2 control-label" for="pno">商品条码</label>
					<div class="col-sm-10 col-md-5">
						<input type="text" name="pno" id="pno" class="form-control" placeholder="商品条码"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 col-md-2 control-label" for="pkeywords">关键词</label>
					<div class="col-sm-10 col-md-5">
						<input type="text" name="pkeywords" id="pkeywords" class="form-control" placeholder="关键词"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 col-md-2 control-label" for="pprice">商品价格</label>
					<div class="col-sm-10 col-md-5">
						<input type="text" name="pprice" id="pprice" class="form-control" placeholder="商品价格"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 col-md-2 control-label" for="psaleprice">销售价格</label>
					<div class="col-sm-10 col-md-5">
						<input type="text" name="psaleprice" id="psaleprice" class="form-control" placeholder="销售价格"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 col-md-2 control-label" for="catorder">销售量</label>
					<div class="col-sm-10 col-md-5">
						<input type="text" name="psalenum" id="psalenum" class="form-control" placeholder="销售量"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 col-md-2 control-label" for="pstock">库存量</label>
					<div class="col-sm-10 col-md-5">
						<input type="text" name="pstock" id="pstock" class="form-control" placeholder="库存量"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 col-md-2 control-label" for="pdes">商品说明</label>
					<div class="col-sm-10">
						<textarea rows="5" cols="40" class="ckeditor" name="pdes" id="pdes"></textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 col-md-2 control-label" for="phot">是否热卖</label>
					<div class="col-sm-10 col-md-5">
						<select name="phot" class="form-control">
							<option value="1">热卖</option>
							<option value="0">非热卖</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 col-md-2 control-label" for="pfirst">是否首推</label>
					<div class="col-sm-10 col-md-5">
						<select name="pfirst" class="form-control">
							<option value="1">首推</option>
							<option value="0">非首推</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 col-md-2 control-label" for="pstate">商品状态</label>
					<div class="col-sm-10 col-md-5">
						<select name="pstate" class="form-control">
							<option value="1">上架</option>
							<option value="0">下架</option>
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 col-md-2 control-label" for="file">商品图片</label>
					<div class="col-sm-10 col-md-5">
						<input type="file" name="file" id="file">
						<div class="layui-upload-list">
							<img class="layui-upload-img" id="imgPreview" style="width:100px;height:100px">
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-3">
						<button type="submit" class="btn btn-primary">提交</button>
						<a class="btn btn-default" href="${pageContext.request.contextPath}/manager/product/list" role="button">返回</a>
					</div>
				</div>
			</form>
					</div>
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
			
			//预读取上传的图片
			$(function() {
				$("#file").change(function(e) {
					$("#imgPreview").attr('src', URL.createObjectURL($(this)[0].files[0]));
				});
			});
			
			//layUI实现的图片上传组件
			/* layui.use('upload', function() {
				var $ = layui.jquery,
					upload = layui.upload;

				//普通图片上传
				var uploadInst = upload.render({
					elem: '#file',
					//url: '' //改成您自己的上传接口
							//,
					before: function(obj) {
						//预读本地文件示例，不支持ie8
						obj.preview(function(index, file, result) {
							$('#imgPreview').attr('src', result); //图片链接（base64）
						});
					}
				});
			}); */
		</script>
		<script type="text/javascript">
			CKEDITOR.replace('pdes');
		</script>
	</body>
</html>