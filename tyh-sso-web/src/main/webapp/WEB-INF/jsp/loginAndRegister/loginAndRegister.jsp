<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>登录注册</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">

		<!--图标库-->
		<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css'>

		<!--响应式框架，注意，这里使用的是bootstrap4，如果使用bootstrap3页面会失效-->
		<link rel='stylesheet' href='../css/bootstrap-4.1.3.min.css'>

		<!--主要样式-->
		<link rel="stylesheet" href="../css/loginAndRegister.css">

	</head>
	<body>

		<div class="container">

			<div class="card-wrap">

				<div class="card border-0 shadow card--welcome is-show" id="welcome">
					<div class="card-body">
						<h2 class="card-title">欢迎光临</h2>
						<p>欢迎进入登录页面</p>
						<font color="red">
							<span id="message">${msg}</span>
						</font>
						<div class="btn-wrap"><a class="btn btn-lg btn-register js-btn" data-target="register">注册</a><a class="btn btn-lg btn-login js-btn"
							 data-target="login">登录</a></div>
					</div>
				</div>

				<div class="card border-0 shadow card--register" id="register">
					<div class="card-body">
						<h2 class="card-title">用户注册</h2>
						<p class="card-text">第三方注册</p>
						<p class="badge-wrap"><a class="badge"><i class="fab fa-facebook-f"></i></a><a class="badge"><i class="fab fa-google"></i></a><a
							 class="badge"><i class="fab fa-twitter"></i></a><a class="badge"><i class="fab fa-github"></i></a></p>
						<p>或者使用您的用户名进行注册</p>
						
						<!-- 注册表单在这里 -->
						<form action="${pageContext.request.contextPath}/user/register" method="post">
							<div class="form-group">
								<input id="username" name="username" class="form-control" type="text" placeholder="用户名" required="required">
							</div>
							<div class="form-group">
								<input id="usertruename" name="usertruename" class="form-control" type="text" placeholder="真实姓名" required="required">
							</div>
							<div class="form-group">
								<input id="userpwd" name="userpwd" class="form-control" type="password" placeholder="密码" required="required">
							</div>
							<button class="btn btn-lg" type="submit">注册</button>
						</form>
					</div>
					<button class="btn btn-back js-btn" data-target="welcome"><i class="fas fa-angle-left"></i></button>
				</div>

				<div class="card border-0 shadow card--login" id="login">
					<div class="card-body">
						<h2 class="card-title">欢迎登录！</h2>
						<p>第三方登录</p>
						<p class="badge-wrap"><a class="badge"><i class="fab fa-facebook-f"></i></a><a class="badge"><i class="fab fa-google"></i></a><a
							 class="badge"><i class="fab fa-twitter"></i></a><a class="badge"><i class="fab fa-github"></i></a></p>
						<p>或使用你的用户名登录</p>
						
						<!-- 登录表单在这里 -->
						<form action="${pageContext.request.contextPath}/user/login" method="post">
							<div class="form-group">
								<input id="username" name="username" class="form-control" type="text" placeholder="用户名" required="required">
							</div>
							<div class="form-group">
								<input id="userpwd" name="userpwd" class="form-control" type="password" placeholder="密码" required="required">
							</div>
							<p><a href="#">忘记密码?</a></p>
							<button class="btn btn-lg" type="submit">登录</button>
						</form>
					</div>
					<button class="btn btn-back js-btn" data-target="welcome"><i class="fas fa-angle-left"></i></button>
				</div>

			</div>

		</div>


		<script src="../js/loginAndRegister.js"></script>
	</body>
</html>
