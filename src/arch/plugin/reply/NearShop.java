/*  1:   */ package arch.plugin.reply;
/*  2:   */ 
/*  3:   */ import arch.entity.WxMessage;
/*  4:   */ import arch.plugin.interfaces.ReplyPlugin;
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
/* 18:   */   implements ReplyPlugin
/* 19:   */ {
/* 20:21 */   private static Logger log = Logger.getLogger(NearShop.class);
/* 21:   */   
/* 22:   */   public String ireply(String openId, Map<String, String> map)
/* 23:   */   {
/* 24:25 */     WxMessage wx = getLocotion(openId);
/* 25:26 */     if ((wx != null) && 
/* 26:27 */       (new Date().getTime() - DateUtil.stringToTime(wx.getCreateTime()).getTime() < UtilProperties.getTokenLimit().longValue())) {
/* 27:   */       try
/* 28:   */       {
/* 29:29 */         return MsgUtil.parseNewsMsg(openId, PlaceUtil.getItems(wx.getLatitude(), wx.getLongitude(), "兴华农商行"));
/* 30:   */       }
/* 31:   */       catch (Exception e)
/* 32:   */       {
/* 33:31 */         log.fatal("百度API异常", e);
/* 34:   */       }
/* 35:   */     }
/* 36:35 */     return MsgUtil.parseTextMsg(openId, "请先发送您的位置信息，或者开启提供位置");
/* 37:   */   }
/* 38:   */   
/* 39:   */   public WxMessage getLocotion(String openId)
/* 40:   */   {
/* 41:40 */     WxMessage wx = null;
/* 42:41 */     Connection conn = null;
/* 43:   */     try
/* 44:   */     {
/* 45:43 */       conn = DbcpConnection.getConnection();
/* 46:44 */       String sql = "SELECT * FROM S_COMMUNICATION WHERE OPEN_ID = ?";
/* 47:45 */       PreparedStatement pstmt = conn.prepareStatement(sql);
/* 48:46 */       pstmt.setString(1, openId);
/* 49:47 */       ResultSet rs = pstmt.executeQuery();
/* 50:48 */       if (rs.next())
/* 51:   */       {
/* 52:49 */         wx = new WxMessage();
/* 53:50 */         wx.setLatitude(rs.getString("LATITUDE"));
/* 54:51 */         wx.setLongitude(rs.getString("LONGTITUDE"));
/* 55:52 */         wx.setCreateTime(rs.getString("LOCATION_TIME"));
/* 56:   */       }
/* 57:   */     }
/* 58:   */     catch (Exception e)
/* 59:   */     {
/* 60:55 */       log.fatal("sql Excetion", e);
/* 61:   */     }
/* 62:   */     finally
/* 63:   */     {
/* 64:57 */       DbcpConnection.close(conn, null, null);
/* 65:   */     }
/* 66:59 */     return wx;
/* 67:   */   }
/* 68:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.plugin.reply.NearShop
 * JD-Core Version:    0.7.0.1
 */