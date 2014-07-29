/*  1:   */ package test;
/*  2:   */ 
/*  3:   */ import java.io.PrintStream;
/*  4:   */ import java.util.HashMap;
/*  5:   */ import java.util.Map;
/*  6:   */ 
/*  7:   */ public class Test
/*  8:   */ {
/*  9:   */   public static void main(String[] arg)
/* 10:   */   {
/* 11: 8 */     Map<String, String> map = new HashMap();
/* 12: 9 */     map.put("wechId", "123");
/* 13:10 */     map.put("wechId", "321");
/* 14:11 */     System.out.println(map);
/* 15:   */   }
/* 16:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     test.Test
 * JD-Core Version:    0.7.0.1
 */