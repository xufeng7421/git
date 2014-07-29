/*  1:   */ package arch.wechat.servlet;
/*  2:   */ 
/*  3:   */ import arch.entity.AccessToken;
/*  4:   */ import arch.util.AccessTokenUtil;
/*  5:   */ import arch.wechat.api.WeChatAPI;
/*  6:   */ import java.io.PrintWriter;
/*  7:   */ import javax.servlet.http.HttpServlet;
/*  8:   */ import javax.servlet.http.HttpServletRequest;
/*  9:   */ import javax.servlet.http.HttpServletResponse;
/* 10:   */ import javax.servlet.http.HttpSession;
/* 11:   */ import org.apache.log4j.Logger;
/* 12:   */ import org.json.JSONObject;
/* 13:   */ 
/* 14:   */ public class OAuthServlet
/* 15:   */   extends HttpServlet
/* 16:   */ {
/* 17:   */   private static final long serialVersionUID = 1L;
/* 18:20 */   private Logger log = Logger.getLogger(OAuthServlet.class);
/* 19:   */   
/* 20:   */   protected void service(HttpServletRequest request, HttpServletResponse response)
/* 21:   */   {
/* 22:24 */     PrintWriter pw = null;
/* 23:   */     try
/* 24:   */     {
/* 25:26 */       response.setContentType("application/json;charset=UTF-8");
/* 26:27 */       pw = response.getWriter();
/* 27:28 */       JSONObject json = new JSONObject();
/* 28:29 */       String code = request.getParameter("code");
/* 29:30 */       String wechatId = request.getParameter("wechatId");
/* 30:31 */       this.log.info("code->" + code + "| wechatId->" + wechatId);
/* 31:32 */       AccessToken token = AccessTokenUtil.getToken(wechatId);
/* 32:33 */       if (token != null)
/* 33:   */       {
/* 34:34 */         String openId = WeChatAPI.getOpenIdByCode(token.getAppId(), token.getAppSecret(), code);
/* 35:35 */         if (openId != null)
/* 36:   */         {
/* 37:36 */           request.getSession().setAttribute("openId", openId);
/* 38:37 */           json.put("openId", openId);
/* 39:   */         }
/* 40:   */         else
/* 41:   */         {
/* 42:39 */           openId = (String)request.getSession().getAttribute("openId");
/* 43:40 */           if (openId != null) {
/* 44:41 */             json.put("openId", openId);
/* 45:   */           } else {
/* 46:43 */             json.put("errcode", -1);
/* 47:   */           }
/* 48:   */         }
/* 49:   */       }
/* 50:   */       else
/* 51:   */       {
/* 52:47 */         json.put("errcode", -1);
/* 53:   */       }
/* 54:49 */       pw.print(json.toString());
/* 55:   */     }
/* 56:   */     catch (Exception e)
/* 57:   */     {
/* 58:51 */       this.log.fatal("取openId异常", e);
/* 59:   */     }
/* 60:   */     finally
/* 61:   */     {
/* 62:53 */       pw.flush();
/* 63:54 */       pw.close();
/* 64:   */     }
/* 65:   */   }
/* 66:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.wechat.servlet.OAuthServlet
 * JD-Core Version:    0.7.0.1
 */