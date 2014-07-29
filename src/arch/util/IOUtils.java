/*  1:   */ package arch.util;
/*  2:   */ 
/*  3:   */ import java.io.BufferedReader;
/*  4:   */ import java.io.BufferedWriter;
/*  5:   */ import java.io.PrintWriter;
/*  6:   */ import org.apache.log4j.Logger;
/*  7:   */ 
/*  8:   */ public class IOUtils
/*  9:   */ {
/* 10:   */   public static void closeIO(PrintWriter pw, Logger log)
/* 11:   */   {
/* 12:24 */     if (pw != null) {
/* 13:   */       try
/* 14:   */       {
/* 15:26 */         pw.close();
/* 16:   */       }
/* 17:   */       catch (Exception e)
/* 18:   */       {
/* 19:28 */         log.fatal("printWriter流关闭失败", e);
/* 20:   */       }
/* 21:   */     }
/* 22:   */   }
/* 23:   */   
/* 24:   */   public static void closeIO(BufferedReader br, Logger log)
/* 25:   */   {
/* 26:39 */     if (br != null) {
/* 27:   */       try
/* 28:   */       {
/* 29:41 */         br.close();
/* 30:   */       }
/* 31:   */       catch (Exception e)
/* 32:   */       {
/* 33:43 */         log.fatal("BufferedReader流关闭失败", e);
/* 34:   */       }
/* 35:   */     }
/* 36:   */   }
/* 37:   */   
/* 38:   */   public static void closeIO(BufferedWriter bw, Logger log)
/* 39:   */   {
/* 40:56 */     if (bw != null) {
/* 41:   */       try
/* 42:   */       {
/* 43:58 */         bw.close();
/* 44:   */       }
/* 45:   */       catch (Exception e)
/* 46:   */       {
/* 47:60 */         log.fatal("BufferedWriter流关闭失败", e);
/* 48:   */       }
/* 49:   */     }
/* 50:   */   }
/* 51:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.util.IOUtils
 * JD-Core Version:    0.7.0.1
 */