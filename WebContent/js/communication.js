var locked=false;
var timer=new Array();
$(function() {
	/*******************添加tab页**********************/
		var tabTitle = $("#tab_title"), tabContent = $("#tab_content"), tabTemplate = "<li><a href='\#{href}'>\#{label}</a> <span openId='\#{openId}' index='\#{index}' class='ui-icon ui-icon-close' role='presentation'>Remove Tab</span></li>", tabCounter = 2;
		var tabs = $("#tabs").tabs();
		
		function addTab(obj) {
			var label =  "Tab " + tabCounter, id = "tabs-"
					+ tabCounter, li = $(tabTemplate.replace(/#\{href\}/g,
					"#" + id).replace(/#\{label\}/g, obj.nickName).replace(/#\{openId\}/g, obj.openId).replace(/#\{index\}/g, tabCounter)), tabContentHtml = "<div class=\"t_content\"><P>"+ obj.nickName + " 接入成功 </p><br><div class=\"content\"></div>"+
   			 "<div style='padding:5px;'><br>	<input type=\"text\" id=\"reply2\"> <input openId='\#{openId}' type=\"button\" class=\"btn\" value=\"回复\"></div></div>";
			tabs.find(".ui-tabs-nav").append(li);
			tabs.append("<div id='" + id + "'>" + tabContentHtml.replace(/#\{openId\}/g, obj.openId)
					+ "</div>");
			tabs.tabs("refresh");
			tabs.tabs("select", $("#tabs").find("li").length-1);
			timer[tabCounter]=setInterval(function(){startTimer(obj.openId,id);},10000);
			tabCounter++;
		}
		
		tabs.delegate("span.ui-icon-close", "click", function() {
			var panelId = $(this).closest("li").remove().attr("aria-controls");
			$("#" + panelId).remove();
			
			var openId=$(this).attr("openId");
  			var obj=$(this);
  			$.ajax({
  				url:"service",
				data:{"flag":"1","wechatId":wechatId,"serviceId":"1","openId":openId},
				type:"get",
				success:function(result){
					if(result.state=="success"){
						var len=$(obj).attr("index");
						clearInterval(timer[len]);
					}
				}
  			});
  			
			tabs.tabs("refresh");
		});
		tabs.bind("keyup", function(event) {
			if (event.altKey && event.keyCode === $.ui.keyCode.BACKSPACE) {
				var panelId = tabs.find(".ui-tabs-active").remove().attr(
						"aria-controls");
				$("#" + panelId).remove();
				tabs.tabs("refresh");
			}
		});
		
	/********************客服服务实现*********************/
		$("#add_tab").button().click(function(){
  			var url1="service";
  			$.ajax({
  				url:url1,
				data:{"flag":"0","wechatId":wechatId,"serviceId":"1"},
				type:"get",
				success:function(result){
					if(result.state=="success"&&result.openId!=undefined){
						addTab(result);
					}else{
						alert("没有人员等待");
					}
				}
  			});
  		});
  		
		$("#count_tab").button().click(function(){
			$.ajax({
  				url:"service",
				data:{"flag":"2","wechatId":wechatId},
				type:"post",
				success:function(result){
					if(result.state=="success"){
						$("#count").html(result.count);
					}
				}
			});
  		});
		
		$(".btn").live("click",function(){
			if(!locked){
				locked=true;
			var val=$(this).prev().val();
			var openId=$(this).attr("openId");
			var obj=$(this);
			$.ajax({
  				url:"service",
				data:{"flag":"3","wechatId":wechatId,"content":val,"openId":openId},
				type:"post",
				success:function(result){
					locked=false;
					if(result.state=="success"){
						if(result.result=="0"){
							$(obj).parent().prev().append(dealStr(val));
						}else{
							alert("发送失败!"+result.result);
						}
					}
				}
			});
			}
		});
		
		/*******************刷新时处理*************************/
		$.ajax({
				url:"service",
			data:{"flag":"5","wechatId":wechatId,"serviceId":"1"},
			type:"post",
			success:function(result){
				if(result.state=="success"){
					var len=result.list.length;
					for(var i=0;i<len;i++){
						addTab(result.list[i]);
					}
				}
			},error:function(){
				alert("error");
			}
		});
		
	});



/**
 * 启动计时器  及时获取用户发送的信息
 * @param openId
 * @param divId
 */
function startTimer(openId,divId){
		$.ajax({
		url:"service",
		data:{"flag":"4","wechatId":wechatId,"openId":openId},
		type:"post",
		success:function(result){
			if(result.state=="success"){
				$("#"+divId).find(".content").append(dealStr(result.content));
			}
		}
		});
	}

function dealStr(content){
	var html="";
	if(content.length!=0){
		html="<li ><span>"+new Date().toLocaleString()+"</span><p style='margin-left:10px;'>"+content+"</p></li>"
	}
	return html
}