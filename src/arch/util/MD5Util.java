/*  1:   */ package arch.util;
/*  2:   */ 
/*  3:   */ import java.security.MessageDigest;
/*  4:   */ import org.apache.log4j.Logger;
/*  5:   */ 
/*  6:   */ public class MD5Util
/*  7:   */ {
/*  8: 8 */   private static Logger log = Logger.getLogger(MD5Util.class);
/*  9: 9 */   private static final char[] hexDigits = { '0', '1', '2', '3', '4', '5', 
/* 10:10 */     '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
/* 11:   */   
/* 12:   */   private static String bytesToHex(byte[] bytes)
/* 13:   */   {
/* 14:13 */     StringBuffer sb = new StringBuffer();
/* 15:15 */     for (int i = 0; i < 16; i++)
/* 16:   */     {
/* 17:16 */       int t = bytes[i];
/* 18:17 */       if (t < 0) {
/* 19:18 */         t += 256;
/* 20:   */       }
/* 21:19 */       sb.append(hexDigits[(t >>> 4)]);
/* 22:20 */       sb.append(hexDigits[(t % 16)]);
/* 23:   */     }
/* 24:22 */     return sb.toString();
/* 25:   */   }
/* 26:   */   
/* 27:   */   public static String md5(String input)
/* 28:   */   {
/* 29:32 */     return md5(input, 32);
/* 30:   */   }
/* 31:   */   
/* 32:   */   public static String md5(String input, int bit)
/* 33:   */   {
/* 34:   */     try
/* 35:   */     {
/* 36:43 */       MessageDigest md = MessageDigest.getInstance(System.getProperty("MD5.algorithm", "MD5"));
/* 37:44 */       if (bit == 16) {
/* 38:45 */         return bytesToHex(md.digest(input.getBytes("utf-8"))).substring(8, 24);
/* 39:   */       }
/* 40:47 */       return bytesToHex(md.digest(input.getBytes("utf-8")));
/* 41:   */     }
/* 42:   */     catch (Exception e)
/* 43:   */     {
/* 44:49 */       log.fatal("MD5加密异常", e);
/* 45:   */     }
/* 46:51 */     return null;
/* 47:   */   }
/* 48:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.util.MD5Util
 * JD-Core Version:    0.7.0.1
 */