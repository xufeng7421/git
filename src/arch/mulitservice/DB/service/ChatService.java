/*  1:   */ package arch.mulitservice.DB.service;
/*  2:   */ 
/*  3:   */ import arch.entity.WxMessage;
/*  4:   */ import arch.mulitservice.DB.dao.ChatDao;
/*  5:   */ import arch.mulitservice.DB.dao.impl.ChatDaoImpl;
/*  6:   */ import arch.mulitservice.entity.ChatMessage;
/*  7:   */ import java.sql.Connection;
/*  8:   */ import java.sql.SQLException;
/*  9:   */ import java.util.List;
/* 10:   */ 
/* 11:   */ public class ChatService
/* 12:   */ {
/* 13:15 */   private ChatDao chatDao = new ChatDaoImpl();
/* 14:   */   
/* 15:   */   public void saveMessage(Connection conn, WxMessage wxMessage, String serviceId)
/* 16:   */     throws SQLException
/* 17:   */   {
/* 18:24 */     this.chatDao.addMessage(conn, wxMessage, serviceId);
/* 19:   */   }
/* 20:   */   
/* 21:   */   public List<ChatMessage> findNewMessage(Connection conn, String wechatId, String openId)
/* 22:   */     throws SQLException
/* 23:   */   {
/* 24:34 */     List<ChatMessage> list = this.chatDao.getNewMessage(conn, wechatId, openId);
/* 25:35 */     if (list.size() != 0) {
/* 26:36 */       for (ChatMessage chat : list) {
/* 27:37 */         this.chatDao.chageMessState(conn, chat.getId());
/* 28:   */       }
/* 29:   */     }
/* 30:40 */     return list;
/* 31:   */   }
/* 32:   */   
/* 33:   */   public String findNickName(Connection conn, String openId)
/* 34:   */     throws SQLException
/* 35:   */   {
/* 36:51 */     return this.chatDao.findNickName(openId, conn);
/* 37:   */   }
/* 38:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.mulitservice.DB.service.ChatService
 * JD-Core Version:    0.7.0.1
 */