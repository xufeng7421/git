<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv=Content-Type content="text/html;charset=utf-8">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/css/ui-lightness/jquery-ui-1.9.2.custom.min.css" />
<link type="text/css" rel="stylesheet" href="css/communication.css" />
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-ui-1.9.2.custom.min.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/communication.js"
	charset="utf-8"></script>
<script type="text/javascript">
  	var wechatId=<%=request.getSession().getAttribute("wechatId") %>
  </script>
</head>
<body>
	<button id="add_tab">接待等待客户</button>
	<button id="count_tab">等待客户人数</button>
	<span id="count"></span>
	<br>
	<div id="tabs">
		<ul>
			<li><a href="#tabs-1">使用说明</a> <span
				class="ui-icon ui-icon-close" role="presentation">Remove Tab</span>
			</li>
		</ul>
		<div id="tabs-1">
			<p>欢迎使用本系统！</p>
		</div>
	</div>
</body>
</html>
