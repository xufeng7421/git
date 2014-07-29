/*  1:   */ package arch.util;
/*  2:   */ 
/*  3:   */ import java.sql.Connection;
/*  4:   */ import java.sql.PreparedStatement;
/*  5:   */ import java.sql.ResultSet;
/*  6:   */ import java.sql.SQLException;
/*  7:   */ 
/*  8:   */ public class DictionaryUtil
/*  9:   */ {
/* 10:   */   public static String findLastInsertId(Connection conn)
/* 11:   */     throws SQLException
/* 12:   */   {
/* 13:24 */     String id = null;
/* 14:25 */     PreparedStatement ps = null;
/* 15:26 */     ResultSet rs = null;
/* 16:   */     try
/* 17:   */     {
/* 18:28 */       String sql = "SELECT   LAST_INSERT_ID()";
/* 19:29 */       ps = conn.prepareStatement(sql);
/* 20:30 */       rs = ps.executeQuery();
/* 21:31 */       if (rs.next()) {
/* 22:32 */         id = rs.getString(1);
/* 23:   */       }
/* 24:   */     }
/* 25:   */     finally
/* 26:   */     {
/* 27:35 */       DbcpConnection.close(null, ps, rs);
/* 28:   */     }
/* 29:37 */     return id;
/* 30:   */   }
/* 31:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.util.DictionaryUtil
 * JD-Core Version:    0.7.0.1
 */