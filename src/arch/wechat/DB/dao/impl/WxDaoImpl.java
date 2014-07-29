/*   1:    */ package arch.wechat.DB.dao.impl;
/*   2:    */ 
/*   3:    */ import arch.entity.WxMessage;
/*   4:    */ import arch.entity.type.EventType;
/*   5:    */ import arch.entity.type.MsgType;
/*   6:    */ import arch.util.DbcpConnection;
/*   7:    */ import arch.wechat.DB.dao.WxDao;
/*   8:    */ import java.sql.Connection;
/*   9:    */ import java.sql.PreparedStatement;
/*  10:    */ import java.sql.ResultSet;
/*  11:    */ import java.sql.SQLException;
/*  12:    */ 
/*  13:    */ public class WxDaoImpl
/*  14:    */   implements WxDao
/*  15:    */ {
/*  16:    */   public String findCompanyIdByWeChatId(Connection conn, String wechatId)
/*  17:    */     throws SQLException
/*  18:    */   {
/*  19: 16 */     PreparedStatement ps = null;
/*  20: 17 */     ResultSet rs = null;
/*  21: 18 */     String companyId = null;
/*  22:    */     try
/*  23:    */     {
/*  24: 20 */       String sql = "SELECT COMPANY_ID FROM M_WEIXIN_CONFIG WHERE WECHAT_ID = ? ";
/*  25: 21 */       ps = conn.prepareStatement(sql);
/*  26: 22 */       ps.setString(1, wechatId);
/*  27: 23 */       rs = ps.executeQuery();
/*  28: 24 */       if (rs.next()) {
/*  29: 25 */         companyId = rs.getString("COMPANY_ID");
/*  30:    */       }
/*  31:    */     }
/*  32:    */     finally
/*  33:    */     {
/*  34: 28 */       DbcpConnection.close(null, ps, rs);
/*  35:    */     }
/*  36: 30 */     return companyId;
/*  37:    */   }
/*  38:    */   
/*  39:    */   public int updCommuication(WxMessage wxMessage, Connection conn)
/*  40:    */     throws SQLException
/*  41:    */   {
/*  42: 36 */     PreparedStatement ps = null;
/*  43:    */     try
/*  44:    */     {
/*  45: 38 */       String sql = "UPDATE S_COMMUNICATION SET COMMUTY_TIME = NOW() WHERE COMPANY_ID = ? AND OPEN_ID = ? AND WECHAT_ID = ?";
/*  46: 39 */       ps = conn.prepareStatement(sql);
/*  47: 40 */       ps.setString(1, wxMessage.getCompanyId());
/*  48: 41 */       ps.setString(2, wxMessage.getOpenId());
/*  49: 42 */       ps.setString(3, wxMessage.getWechatId());
/*  50: 43 */       return ps.executeUpdate();
/*  51:    */     }
/*  52:    */     finally
/*  53:    */     {
/*  54: 45 */       DbcpConnection.close(null, ps, null);
/*  55:    */     }
/*  56:    */   }
/*  57:    */   
/*  58:    */   public int addWxMessage(WxMessage wxMessage, Connection conn)
/*  59:    */     throws SQLException
/*  60:    */   {
/*  61: 52 */     PreparedStatement ps = null;
/*  62:    */     try
/*  63:    */     {
/*  64: 54 */       String sql = "INSERT INTO S_MESSAGE (COMPANY_ID,WECHAT_ID,OPEN_ID,CREATE_TIME,MSG_TYPE,IS_SEND,CONTENT,EVENT,EVENT_KEY) VALUES (?,?,?,SYSDATE(),?,0,?,?,?)";
/*  65:    */       
/*  66: 56 */       ps = conn.prepareStatement(sql);
/*  67: 57 */       ps.setString(1, wxMessage.getCompanyId());
/*  68: 58 */       ps.setString(2, wxMessage.getWechatId());
/*  69: 59 */       ps.setString(3, wxMessage.getOpenId());
/*  70: 60 */       ps.setString(4, wxMessage.getMsgType() == null ? null : wxMessage.getMsgType().toString());
/*  71: 61 */       ps.setString(5, wxMessage.getContent());
/*  72: 62 */       ps.setString(6, wxMessage.getEvent() == null ? null : wxMessage.getEvent().toString());
/*  73: 63 */       ps.setString(7, wxMessage.getEventKey());
/*  74: 64 */       return ps.executeUpdate();
/*  75:    */     }
/*  76:    */     finally
/*  77:    */     {
/*  78: 66 */       DbcpConnection.close(null, ps, null);
/*  79:    */     }
/*  80:    */   }
/*  81:    */   
/*  82:    */   public int updLocation(WxMessage wxMessage, Connection conn)
/*  83:    */     throws SQLException
/*  84:    */   {
/*  85: 73 */     PreparedStatement ps = null;
/*  86:    */     try
/*  87:    */     {
/*  88: 75 */       String sql = "UPDATE S_COMMUNICATION SET LATITUDE = ? ,LONGTITUDE = ?,LOCATION_TIME =  NOW(),  COMMUTY_TIME = NOW() WHERE COMPANY_ID = ? AND OPEN_ID = ? AND WECHAT_ID = ?";
/*  89: 76 */       ps = conn.prepareStatement(sql);
/*  90: 77 */       ps.setString(1, wxMessage.getLatitude());
/*  91: 78 */       ps.setString(2, wxMessage.getLongitude());
/*  92: 79 */       ps.setString(3, wxMessage.getCompanyId());
/*  93: 80 */       ps.setString(4, wxMessage.getOpenId());
/*  94: 81 */       ps.setString(5, wxMessage.getWechatId());
/*  95: 82 */       return ps.executeUpdate();
/*  96:    */     }
/*  97:    */     finally
/*  98:    */     {
/*  99: 84 */       DbcpConnection.close(null, ps, null);
/* 100:    */     }
/* 101:    */   }
/* 102:    */   
/* 103:    */   public String findVipIdByOpenId(Connection conn, String openId, String wechatId)
/* 104:    */     throws SQLException
/* 105:    */   {
/* 106: 91 */     PreparedStatement ps = null;
/* 107: 92 */     ResultSet rs = null;
/* 108: 93 */     String vipId = null;
/* 109:    */     try
/* 110:    */     {
/* 111: 95 */       String sql = "SELECT VIP_ID FROM U_VIP_INFO WHERE  WECHAT_ID=? AND OPEN_ID = ? ";
/* 112: 96 */       ps = conn.prepareStatement(sql);
/* 113: 97 */       ps.setString(1, wechatId);
/* 114: 98 */       ps.setString(2, openId);
/* 115: 99 */       rs = ps.executeQuery();
/* 116:100 */       while (rs.next()) {
/* 117:101 */         vipId = rs.getString("VIP_ID");
/* 118:    */       }
/* 119:    */     }
/* 120:    */     finally
/* 121:    */     {
/* 122:104 */       DbcpConnection.close(null, ps, rs);
/* 123:    */     }
/* 124:106 */     return vipId;
/* 125:    */   }
/* 126:    */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.wechat.DB.dao.impl.WxDaoImpl
 * JD-Core Version:    0.7.0.1
 */