/*  1:   */ package arch.mulitservice.DB.dao.impl;
/*  2:   */ 
/*  3:   */ import arch.entity.WxMessage;
/*  4:   */ import arch.entity.type.MsgType;
/*  5:   */ import arch.mulitservice.DB.dao.ChatDao;
/*  6:   */ import arch.mulitservice.entity.ChatMessage;
/*  7:   */ import arch.util.DbcpConnection;
/*  8:   */ import java.sql.Connection;
/*  9:   */ import java.sql.PreparedStatement;
/* 10:   */ import java.sql.ResultSet;
/* 11:   */ import java.sql.SQLException;
/* 12:   */ import java.util.ArrayList;
/* 13:   */ import java.util.List;
/* 14:   */ 
/* 15:   */ public class ChatDaoImpl
/* 16:   */   implements ChatDao
/* 17:   */ {
/* 18:   */   public int addMessage(Connection conn, WxMessage wxMessage, String serviceId)
/* 19:   */     throws SQLException
/* 20:   */   {
/* 21:23 */     PreparedStatement ps = null;
/* 22:24 */     int result = 0;
/* 23:   */     try
/* 24:   */     {
/* 25:26 */       String sql = "INSERT INTO S_SERVICE_LOG(WECHAT_ID,OPEN_ID,SERVICE_ID,MSG_TYPE,IS_SEND,MSG_CONTENT,CREATE_TIME) VALUES(?,?,?,?,'0',?,NOW())";
/* 26:27 */       ps = conn.prepareStatement(sql);
/* 27:28 */       ps.setString(1, wxMessage.getWechatId());
/* 28:29 */       ps.setString(2, wxMessage.getOpenId());
/* 29:30 */       ps.setString(3, serviceId);
/* 30:31 */       ps.setString(4, wxMessage.getMsgType().toString());
/* 31:32 */       ps.setString(5, wxMessage.getContent());
/* 32:33 */       result = ps.executeUpdate();
/* 33:   */     }
/* 34:   */     finally
/* 35:   */     {
/* 36:35 */       DbcpConnection.close(null, ps, null);
/* 37:   */     }
/* 38:37 */     return result;
/* 39:   */   }
/* 40:   */   
/* 41:   */   public List<ChatMessage> getNewMessage(Connection conn, String wechatId, String openId)
/* 42:   */     throws SQLException
/* 43:   */   {
/* 44:42 */     List<ChatMessage> list = new ArrayList();
/* 45:43 */     PreparedStatement ps = null;
/* 46:44 */     ResultSet rs = null;
/* 47:45 */     ChatMessage chat = null;
/* 48:   */     try
/* 49:   */     {
/* 50:47 */       String sql = "SELECT ID,MSG_CONTENT FROM S_SERVICE_LOG WHERE OPEN_ID=? AND WECHAT_ID=? AND MSG_TYPE='TEXT' AND IS_NEW ='0'";
/* 51:48 */       ps = conn.prepareStatement(sql);
/* 52:49 */       ps.setString(1, openId);
/* 53:50 */       ps.setString(2, wechatId);
/* 54:51 */       rs = ps.executeQuery();
/* 55:52 */       while (rs.next())
/* 56:   */       {
/* 57:53 */         chat = new ChatMessage();
/* 58:54 */         chat.setId(rs.getString("ID"));
/* 59:55 */         chat.setContent(rs.getString("MSG_CONTENT"));
/* 60:56 */         list.add(chat);
/* 61:   */       }
/* 62:   */     }
/* 63:   */     finally
/* 64:   */     {
/* 65:59 */       DbcpConnection.close(null, ps, rs);
/* 66:   */     }
/* 67:61 */     return list;
/* 68:   */   }
/* 69:   */   
/* 70:   */   public int chageMessState(Connection conn, String id)
/* 71:   */     throws SQLException
/* 72:   */   {
/* 73:67 */     PreparedStatement ps = null;
/* 74:68 */     int result = 0;
/* 75:   */     try
/* 76:   */     {
/* 77:70 */       String sql = "UPDATE S_SERVICE_LOG SET IS_NEW='1' WHERE ID=?";
/* 78:71 */       ps = conn.prepareStatement(sql);
/* 79:72 */       ps.setString(1, id);
/* 80:73 */       result = ps.executeUpdate();
/* 81:   */     }
/* 82:   */     finally
/* 83:   */     {
/* 84:75 */       DbcpConnection.close(null, ps, null);
/* 85:   */     }
/* 86:77 */     return result;
/* 87:   */   }
/* 88:   */   
/* 89:   */   public String findNickName(String openId, Connection conn)
/* 90:   */     throws SQLException
/* 91:   */   {
/* 92:82 */     String nickName = null;
/* 93:83 */     PreparedStatement ps = null;
/* 94:84 */     ResultSet rs = null;
/* 95:   */     try
/* 96:   */     {
/* 97:86 */       String sql = "SELECT NICKNAME FROM U_VIP_INFO WHERE OPEN_ID = ?";
/* 98:87 */       ps = conn.prepareStatement(sql);
/* 99:88 */       ps.setString(1, openId);
/* :0:89 */       rs = ps.executeQuery();
/* :1:90 */       if (rs.next()) {
/* :2:91 */         nickName = rs.getString("NICKNAME");
/* :3:   */       }
/* :4:   */     }
/* :5:   */     finally
/* :6:   */     {
/* :7:94 */       DbcpConnection.close(null, ps, null);
/* :8:   */     }
/* :9:96 */     return nickName;
/* ;0:   */   }
/* ;1:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.mulitservice.DB.dao.impl.ChatDaoImpl
 * JD-Core Version:    0.7.0.1
 */