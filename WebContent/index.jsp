<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>登录</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/css/common.css" />
</head>
<body>
	<!-- 头部 开始 -->
	<div class="">
		<div class="head_box">
			<div class="divClass">
				<h1 class="logo">
					<a href="javascript:void(0);"></a>
				</h1>
			</div>
			<div class="login-nav"></div>
		</div>
	</div>
	<!-- 头部 结束 -->

	<!-- 登陆框 开始 -->
	<form action="service" name="form1" method="post" id="form1">
		<div class="login-main">
			<div class="login-main-con">
				<div class="login-panel">
					<h3>登录</h3>
					<!-- 显示错误 -->
					<div
						style="border-top-width: 0px; margin-top: 30px; margin-left: 100px;">
						<font style="color: red;">${errmsg}</font>
					</div>
					<input type="hidden" value="1" name="wechatId">
					<div class="divformClass">
						<div class="login_number">
							<span class="icon-wrapper"><i class="icon24-login un"></i>
							</span> <input type="hidden" name="flag" value="-1"> <input
								type="text" name="staffId" placeholder="邮箱/用户名/手机号" id="account">
						</div>

						<div class="login_password">
							<span class="icon-wrapper"><i class="icon24-login pwd"></i>
							</span> <input type="password" id="password" name="password"
								placeholder="密码">
						</div>
					</div>

					<div class="login-help-panel"></div>

					<div class="loginButton">
						<input class="tijiao" id="userLogin" type="submit" value="登录" />
					</div>

					<!--  <ul class="logulClass">
       <div class="login-un">      <span class="icon-wrapper"><i class="icon24-login un"></i></span>      <input type="text" placeholder="邮箱/微信号/QQ号" id="account">    </div>
        <li class="wbk1">
        <div >
        <span class="icon-wrapper"><i class="icon24-login un"></i></span>
          <input class="zhanghao" name="staffId"  type="text" onfocus="if(this.value=='邮箱\\微信\\手机号') {this.value='';}" onblur="if(this.value=='') {this.value='邮箱\\微信\\手机号';}"  value="邮箱\微信\手机号" />
          </div>
        </li>
        <li class="wbk2">
        <span class="icon-wrapper"><i class="icon24-login un"></i></span>
          <input class="mima" type="password" name="password" />
        </li>
        <li class="wbk3">
        
          <a class="xinshou" href="userLoginAction_userRegisterUI.action">您是新用户，请先立即注册</a></li>
        <li class="wbk4">
        
          <input class="tijiao" id="userLogin" type="submit" value="登录" />
        </li>
      </ul>
      -->
				</div>
			</div>
		</div>
	</form>
	<!-- 登陆框 结束 -->

</body>
</html>

