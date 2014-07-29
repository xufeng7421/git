/*  1:   */ package arch.plugin.menu;
/*  2:   */ 
/*  3:   */ import arch.entity.WxMessage;
/*  4:   */ import arch.plugin.interfaces.MenuPlugin;
/*  5:   */ import arch.plugin.temp.PlaceUtil;
/*  6:   */ import arch.properties.UtilProperties;
/*  7:   */ import arch.util.DateUtil;
/*  8:   */ import arch.util.DbcpConnection;
/*  9:   */ import arch.util.MsgUtil;
/* 10:   */ import java.sql.Connection;
/* 11:   */ import java.sql.PreparedStatement;
/* 12:   */ import java.sql.ResultSet;
/* 13:   */ import java.util.Date;
/* 14:   */ import java.util.Map;
/* 15:   */ import org.apache.log4j.Logger;
/* 16:   */ 
/* 17:   */ public class NearShop
/* 18:   */   implements MenuPlugin
/* 19:   */ {
/* 20:19 */   private static Logger log = Logger.getLogger(NearShop.class);
/* 21:   */   
/* 22:   */   public String iwork(String openId, Map<String, String> map)
/* 23:   */   {
/* 24:23 */     WxMessage wx = getLocotion(openId);
/* 25:24 */     if ((wx.getCreateTime() != null) && 
/* 26:25 */       (new Date().getTime() - DateUtil.stringToTime(wx.getCreateTime()).getTime() < UtilProperties.getTokenLimit().longValue())) {
/* 27:   */       try
/* 28:   */       {
/* 29:27 */         return MsgUtil.parseNewsMsg(openId, PlaceUtil.getItems(wx.getLatitude(), wx.getLongitude(), "兴化农商行"));
/* 30:   */       }
/* 31:   */       catch (Exception e)
/* 32:   */       {
/* 33:29 */         log.fatal("百度API异常", e);
/* 34:   */       }
/* 35:   */     }
/* 36:33 */     return MsgUtil.parseTextMsg(openId, "请先发送您的位置信息，或者开启提供位置");
/* 37:   */   }
/* 38:   */   
/* 39:   */   public WxMessage getLocotion(String openId)
/* 40:   */   {
/* 41:38 */     WxMessage wx = null;
/* 42:39 */     Connection conn = null;
/* 43:   */     try
/* 44:   */     {
/* 45:41 */       conn = DbcpConnection.getConnection();
/* 46:42 */       String sql = "SELECT * FROM S_COMMUNICATION WHERE OPEN_ID = ?";
/* 47:43 */       PreparedStatement pstmt = conn.prepareStatement(sql);
/* 48:44 */       pstmt.setString(1, openId);
/* 49:45 */       ResultSet rs = pstmt.executeQuery();
/* 50:46 */       if (rs.next())
/* 51:   */       {
/* 52:47 */         wx = new WxMessage();
/* 53:48 */         wx.setLatitude(rs.getString("LATITUDE"));
/* 54:49 */         wx.setLongitude(rs.getString("LONGTITUDE"));
/* 55:50 */         wx.setCreateTime(rs.getString("LOCATION_TIME"));
/* 56:   */       }
/* 57:   */     }
/* 58:   */     catch (Exception e)
/* 59:   */     {
/* 60:53 */       log.fatal("sql Excetion", e);
/* 61:   */     }
/* 62:   */     finally
/* 63:   */     {
/* 64:55 */       DbcpConnection.close(conn, null, null);
/* 65:   */     }
/* 66:57 */     return wx;
/* 67:   */   }
/* 68:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.plugin.menu.NearShop
 * JD-Core Version:    0.7.0.1
 */