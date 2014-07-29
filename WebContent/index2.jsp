<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv=Content-Type content="text/html;charset=utf-8">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/css/ui-lightness/jquery-ui-1.9.2.custom.min.css" />
</head>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-ui-1.9.2.custom.min.js"
	charset="utf-8"></script>
<script type="text/javascript">
  var locked=false;
  var timer=new Array();
  	$(function(){
  		$("#wait").click(function(){
  			var url1="service";
  			$.ajax({
  				url:url1,
				data:{"flag":"0","wechatId":"1","serviceId":"1"},
				type:"get",
				success:function(result){
					if(result.state=="success"){
						if($("#talk1").attr("flag")=="0"){
							$("#talk1").attr("openId",result.openId);
							$("#talk1").attr("flag","1");
							$("#talk1").find(".content").html("已接入"+result.openId+"<br>");
							timer[0]=setInterval(function(){startTimer(result.openId,"talk1");},10000);
						}else if($("#talk2").attr("flag")=="0"){
							$("#talk2").attr("openId",result.openId);
							$("#talk2").attr("flag","1");
							$("#talk2").find(".content").html("已接入"+result.openId+"<br>");
							timer[1]=setInterval(function(){startTimer(result.openId,"talk2");},10000);
						}else if($("#talk3").attr("flag")=="0"){
							$("#talk3").attr("openId",result.openId);
							$("#talk3").attr("flag","1");
							$("#talk3").find(".content").html("已接入"+result.openId+"<br>");
							timer[2]=setInterval(function(){startTimer(result.openId,"talk3");},10000);
						}else {
							alert(openId);
						}
							
					}
				}
  			});
  		});
  		
		$(".stop").click(function(){
  			var openId=$(this).parent().parent().attr("openId");
  			var obj=$(this);
  			$.ajax({
  				url:"service",
				data:{"flag":"1","wechatId":"1","serviceId":"1","openId":openId},
				type:"get",
				success:function(result){
					if(result.state=="success"){
						$(obj).parent().next().html("已断开连接");
						$(obj).parent().parent().attr("flag","0");
						var len=$(obj).parent().parent().attr("timer");
						clearInterval(timer[len]);
					}
				}
  			});
		});
		
		$("#flesh").click(function(){
			$.ajax({
  				url:"service",
				data:{"flag":"2","wechatId":"1"},
				type:"post",
				success:function(result){
					if(result.state=="success"){
						$("#count").html(result.count);
					}
				}
			});
  		});
		
		$(".btn").click(function(){
			if(!locked){
				locked=true;
			var val=$(this).prev().val();
			var openId=$(this).parent().parent().attr("openId");
			var obj=$(this);
			$.ajax({
  				url:"service",
				data:{"flag":"3","wechatId":"1","content":val,"openId":openId},
				type:"post",
				success:function(result){
					locked=false;
					if(result.state=="success"){
						if(result.result=="0"){
							$(obj).parent().prev().append("<br>"+val+"<br>");
						}else{
							alert("发送失败!"+result.result);
						}
					}
				}
			});
			}
		});
  	});
  	
  	function startTimer(openId,divId){
  		$.ajax({
			url:"service",
			data:{"flag":"4","wechatId":"1","openId":openId},
			type:"post",
			success:function(result){
				if(result.state=="success"){
					$("#"+divId).find(".content").append(result.content);
				}
			}
  		});
  	}
  </script>
<body>
	<a href="javascript:void(0);" id="wait">客服接收等待客户</a>
	<a href="javascript:void(0);" id="flesh">刷新等待人员数量</a>
	<span id="count"></span>
	<br>
	<hr>
	<br>
	<div id="talk1" style="border: 1px solid red;" flag="0" timer="0">
		<p>
			<a href="javascript:void(0);" class="stop">客服终止与客户的交谈</a>
		</p>
		<div class="content"></div>
		<div>
			<input type="text" id="reply1"> <input type="button"
				class="btn" value="回复">
		</div>
	</div>
	<hr>
	<div id="talk2" style="border: 1px solid red;" flag="0" timer="1">
		<p>
			<a href="javascript:void(0);" class="stop">客服终止与客户的交谈</a>
		</p>
		<div class="content"></div>
		<div>
			<input type="text" id="reply2"> <input type="button"
				class="btn" value="回复">
		</div>
	</div>
	<hr>
	<div id="talk3" style="border: 1px solid red;" flag="0" timer="2">
		<p>
			<a href="javascript:void(0);" class="stop">客服终止与客户的交谈</a>
		</p>
		<div class="content"></div>
		<div>
			<input type="text" id="reply3"> <input type="button"
				class="btn" value="回复">
		</div>
	</div>
	<hr>
</body>
</html>
