/*  1:   */ package arch.wechat.servlet;
/*  2:   */ 
/*  3:   */ import arch.util.AccessTokenUtil;
/*  4:   */ import arch.util.DbcpConnection;
/*  5:   */ import arch.util.MsgUtil;
/*  6:   */ import arch.wechat.DB.service.WxService;
/*  7:   */ import arch.wechat.api.WeChatAPI;
/*  8:   */ import java.io.PrintWriter;
/*  9:   */ import java.sql.Connection;
/* 10:   */ import javax.servlet.http.HttpServlet;
/* 11:   */ import javax.servlet.http.HttpServletRequest;
/* 12:   */ import javax.servlet.http.HttpServletResponse;
/* 13:   */ import org.apache.log4j.Logger;
/* 14:   */ import org.json.JSONObject;
/* 15:   */ 
/* 16:   */ public class QrcodeServlet
/* 17:   */   extends HttpServlet
/* 18:   */ {
/* 19:   */   private static final long serialVersionUID = 1L;
/* 20:24 */   private Logger log = Logger.getLogger(QrcodeServlet.class);
/* 21:25 */   private WxService wxService = new WxService();
/* 22:   */   
/* 23:   */   protected void service(HttpServletRequest request, HttpServletResponse response)
/* 24:   */   {
/* 25:29 */     PrintWriter pw = null;
/* 26:30 */     Connection conn = null;
/* 27:   */     try
/* 28:   */     {
/* 29:32 */       conn = DbcpConnection.getConnection();
/* 30:33 */       response.setContentType("application/json;charset=UTF-8");
/* 31:34 */       pw = response.getWriter();
/* 32:35 */       JSONObject json = new JSONObject();
/* 33:36 */       String openId = request.getParameter("openId");
/* 34:37 */       String wechatId = request.getParameter("wechatId");
/* 35:38 */       String accessToken = AccessTokenUtil.getAccessToken(wechatId);
/* 36:39 */       String vipId = this.wxService.findVipIdByOpenId(conn, openId, wechatId);
/* 37:40 */       if (vipId != null)
/* 38:   */       {
/* 39:41 */         int sceneId = 20000 + Integer.parseInt(vipId);
/* 40:42 */         String codeUrl = WeChatAPI.createQrcode(accessToken, MsgUtil.parseScene(300, sceneId));
/* 41:43 */         if (codeUrl != null)
/* 42:   */         {
/* 43:44 */           json.put("errcode", 0);
/* 44:45 */           json.put("codeUrl", codeUrl);
/* 45:   */         }
/* 46:   */         else
/* 47:   */         {
/* 48:47 */           json.put("errcode", -1);
/* 49:   */         }
/* 50:   */       }
/* 51:   */       else
/* 52:   */       {
/* 53:50 */         json.put("errcode", -1);
/* 54:   */       }
/* 55:52 */       pw.print(json.toString());
/* 56:   */     }
/* 57:   */     catch (Exception e)
/* 58:   */     {
/* 59:54 */       this.log.fatal("生成临时二维码异常", e);
/* 60:   */     }
/* 61:   */     finally
/* 62:   */     {
/* 63:56 */       DbcpConnection.close(conn, null, null);
/* 64:57 */       pw.flush();
/* 65:58 */       pw.close();
/* 66:   */     }
/* 67:   */   }
/* 68:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.wechat.servlet.QrcodeServlet
 * JD-Core Version:    0.7.0.1
 */