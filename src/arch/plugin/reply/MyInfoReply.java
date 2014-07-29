/*  1:   */ package arch.plugin.reply;
/*  2:   */ 
/*  3:   */ import arch.plugin.interfaces.ReplyPlugin;
/*  4:   */ import arch.util.DbcpConnection;
/*  5:   */ import arch.util.MsgUtil;
/*  6:   */ import java.sql.Connection;
/*  7:   */ import java.sql.PreparedStatement;
/*  8:   */ import java.sql.ResultSet;
/*  9:   */ import java.sql.SQLException;
/* 10:   */ import java.util.Map;
/* 11:   */ import org.apache.log4j.Logger;
/* 12:   */ 
/* 13:   */ public class MyInfoReply
/* 14:   */   implements ReplyPlugin
/* 15:   */ {
/* 16:22 */   private static Logger log = Logger.getLogger(MyInfoReply.class);
/* 17:   */   
/* 18:   */   protected String getUserInfoByOpenId(String openId)
/* 19:   */   {
/* 20:29 */     Connection conn = DbcpConnection.getConnection();
/* 21:30 */     String sql = "select * from U_VIP_INFO where OPEN_ID = ?";
/* 22:31 */     StringBuilder sb = new StringBuilder();
/* 23:   */     try
/* 24:   */     {
/* 25:33 */       PreparedStatement pstmt = conn.prepareStatement(sql);
/* 26:34 */       pstmt.setString(1, openId);
/* 27:35 */       ResultSet rs = pstmt.executeQuery();
/* 28:36 */       if (rs.next()) {
/* 29:40 */         sb.append("你的名称：").append(rs.getString("NICKNAME")).append(" 所在地区：").append(rs.getString("COUNTRY")).append(" ").append(rs.getString("PROVINCE")).append(" ").append(rs.getString("CITY")).append(" 关注时间：").append(rs.getString("SUBSCRIBE_TIME"));
/* 30:   */       } else {
/* 31:42 */         sb.append("很抱歉我已经在很努力地的查询到您的信息，可惜未找到！");
/* 32:   */       }
/* 33:   */     }
/* 34:   */     catch (SQLException e)
/* 35:   */     {
/* 36:45 */       log.error("sql 查询出错了", e);
/* 37:   */     }
/* 38:   */     finally
/* 39:   */     {
/* 40:47 */       sb.append("很抱歉未能查询到您的信息！");
/* 41:48 */       DbcpConnection.close(conn, null, null);
/* 42:   */     }
/* 43:51 */     return sb.toString();
/* 44:   */   }
/* 45:   */   
/* 46:   */   public String ireply(String openId, Map<String, String> map)
/* 47:   */   {
/* 48:56 */     String msg = MsgUtil.parseTextMsg(openId, getUserInfoByOpenId(openId));
/* 49:57 */     log.info(msg);
/* 50:58 */     return msg;
/* 51:   */   }
/* 52:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.plugin.reply.MyInfoReply
 * JD-Core Version:    0.7.0.1
 */