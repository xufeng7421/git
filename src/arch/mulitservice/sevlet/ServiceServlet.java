/*   1:    */ package arch.mulitservice.sevlet;
/*   2:    */ 
/*   3:    */ import arch.entity.ReplyEntity;
/*   4:    */ import arch.entity.type.NoticeState;
/*   5:    */ import arch.mq.service.MqService;
/*   6:    */ import arch.mulitservice.entity.ChatMessage;
/*   7:    */ import arch.mulitservice.entity.Sign;
/*   8:    */ import arch.mulitservice.execute.ChatExecute;
/*   9:    */ import arch.properties.QueueProperties;
/*  10:    */ import arch.wechat.api.WeChatAPI;
/*  11:    */ import java.io.IOException;
/*  12:    */ import java.io.PrintStream;
/*  13:    */ import java.io.PrintWriter;
/*  14:    */ import java.util.Iterator;
/*  15:    */ import java.util.List;
/*  16:    */ import javax.servlet.ServletException;
/*  17:    */ import javax.servlet.http.HttpServlet;
/*  18:    */ import javax.servlet.http.HttpServletRequest;
/*  19:    */ import javax.servlet.http.HttpServletResponse;
/*  20:    */ import javax.servlet.http.HttpSession;
/*  21:    */ import org.apache.log4j.Logger;
/*  22:    */ import org.json.JSONArray;
/*  23:    */ import org.json.JSONObject;
/*  24:    */ 
/*  25:    */ public class ServiceServlet
/*  26:    */   extends HttpServlet
/*  27:    */ {
/*  28:    */   private static final long serialVersionUID = 1L;
/*  29: 32 */   private Logger log = Logger.getLogger(ServiceServlet.class);
/*  30:    */   
/*  31:    */   protected void service(HttpServletRequest request, HttpServletResponse response)
/*  32:    */     throws ServletException, IOException
/*  33:    */   {
/*  34: 37 */     String flag = request.getParameter("flag");
/*  35: 38 */     String wechatId = request.getParameter("wechatId");
/*  36: 39 */     String serviceId = request.getParameter("serviceId");
/*  37: 40 */     JSONObject json = new JSONObject();
/*  38: 41 */     PrintWriter pw = null;
/*  39:    */     try
/*  40:    */     {
/*  41: 43 */       json.put("state", "success");
/*  42: 44 */       response.setContentType("application/json");
/*  43: 45 */       response.setCharacterEncoding("UTF-8");
/*  44: 46 */       pw = response.getWriter();
/*  45: 47 */       if ("-1".equals(flag))
/*  46:    */       {
/*  47: 48 */         this.log.debug("用户登录");
/*  48: 49 */         String staffId = request.getParameter("staffId");
/*  49: 50 */         String password = request.getParameter("password");
/*  50: 51 */         if (("teksun".equals(staffId)) && ("888888".equals(password))) {
/*  51: 52 */           response.sendRedirect("dd.jsp");
/*  52:    */         }
/*  53: 54 */         request.getSession().setAttribute("wechatId", wechatId);
/*  54: 55 */         sign();
/*  55:    */       }
/*  56: 56 */       else if ("0".equals(flag))
/*  57:    */       {
/*  58: 57 */         this.log.debug("客服接收等待客户");
/*  59: 58 */         String openId = ChatExecute.acceptCommication(wechatId, serviceId);
/*  60: 59 */         if (openId != null)
/*  61:    */         {
/*  62: 60 */           String nickName = ChatExecute.findNickName(openId);
/*  63: 61 */           json.put("nickName", nickName);
/*  64:    */         }
/*  65: 63 */         json.put("openId", openId);
/*  66:    */       }
/*  67: 64 */       else if ("1".equals(flag))
/*  68:    */       {
/*  69: 65 */         this.log.debug("客服终止与客户的交谈");
/*  70: 66 */         String openId = request.getParameter("openId");
/*  71: 67 */         ChatExecute.endCommicationByService(wechatId, openId, serviceId);
/*  72:    */       }
/*  73: 68 */       else if ("2".equals(flag))
/*  74:    */       {
/*  75: 69 */         this.log.debug("刷新等待人员数量");
/*  76: 70 */         int count = ChatExecute.waitManCount(wechatId);
/*  77: 71 */         json.put("count", count);
/*  78:    */       }
/*  79: 72 */       else if ("3".equals(flag))
/*  80:    */       {
/*  81: 73 */         this.log.debug("客服回复消息");
/*  82: 74 */         String openId = request.getParameter("openId");
/*  83: 75 */         String content = request.getParameter("content");
/*  84: 76 */         ReplyEntity replyEntity = new ReplyEntity(wechatId, openId, content);
/*  85: 77 */         String ret = WeChatAPI.postMsg(replyEntity);
/*  86: 78 */         "0".equals(ret);
/*  87:    */         
/*  88:    */ 
/*  89: 81 */         json.put("result", ret);
/*  90:    */       }
/*  91:    */       else
/*  92:    */       {
/*  93:    */         ChatMessage temp;
/*  94: 82 */         if ("4".equals(flag))
/*  95:    */         {
/*  96: 83 */           this.log.debug("页面刷新消息");
/*  97: 84 */           String openId = request.getParameter("openId");
/*  98: 85 */           List<ChatMessage> list = ChatExecute.findNewMessage(wechatId, openId);
/*  99: 86 */           StringBuilder content = new StringBuilder("");
/* 100: 87 */           for (Iterator localIterator = list.iterator(); localIterator.hasNext();)
/* 101:    */           {
/* 102: 87 */             temp = (ChatMessage)localIterator.next();
/* 103: 88 */             content.append(temp.getContent() + "<br>");
/* 104:    */           }
/* 105: 90 */           json.put("content", content.toString());
/* 106:    */         }
/* 107: 91 */         else if ("5".equals(flag))
/* 108:    */         {
/* 109: 92 */           List<String> list = ChatExecute.findCommunicateOpenId(wechatId, serviceId);
/* 110: 93 */           StringBuilder str = new StringBuilder("[");
/* 111: 94 */           for (String s : list)
/* 112:    */           {
/* 113: 95 */             String obj = "{\"openId\":\"" + s + "\",\"nickName\":\"" + ChatExecute.findNickName(s) + "\"}";
/* 114: 96 */             str.append(obj + ",");
/* 115:    */           }
/* 116: 98 */           String srr = null;
/* 117: 99 */           if (str.length() == 1) {
/* 118:100 */             srr = "[";
/* 119:    */           } else {
/* 120:102 */             srr = str.substring(0, str.length() - 1);
/* 121:    */           }
/* 122:104 */           String ss = srr + "]";
/* 123:105 */           json.put("list", new JSONArray(ss));
/* 124:    */         }
/* 125:    */         else
/* 126:    */         {
/* 127:107 */           System.out.println("错误的标识");
/* 128:108 */           json.put("state", "error");
/* 129:    */         }
/* 130:    */       }
/* 131:110 */       pw.write(json.toString());
/* 132:    */     }
/* 133:    */     catch (Exception e)
/* 134:    */     {
/* 135:112 */       this.log.fatal("执行service出错", e);
/* 136:    */     }
/* 137:    */     finally
/* 138:    */     {
/* 139:114 */       pw.flush();
/* 140:115 */       pw.close();
/* 141:    */     }
/* 142:    */   }
/* 143:    */   
/* 144:    */   public void sign()
/* 145:    */     throws Exception
/* 146:    */   {
/* 147:121 */     Sign sign = new Sign();
/* 148:122 */     sign.setServiceId("1");
/* 149:123 */     sign.setType(NoticeState.START);
/* 150:124 */     sign.setWechatId("1");
/* 151:125 */     MqService.getInstance().send(sign, QueueProperties.getServiceSignQueue());
/* 152:    */   }
/* 153:    */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.mulitservice.sevlet.ServiceServlet
 * JD-Core Version:    0.7.0.1
 */