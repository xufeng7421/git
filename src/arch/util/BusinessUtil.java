/*  1:   */ package arch.util;
/*  2:   */ 
/*  3:   */ public class BusinessUtil
/*  4:   */ {
/*  5:   */   public static Object newInstance(String className)
/*  6:   */     throws InstantiationException, IllegalAccessException, ClassNotFoundException
/*  7:   */   {
/*  8:15 */     Class<?> cla = Class.forName(className);
/*  9:16 */     return cla.newInstance();
/* 10:   */   }
/* 11:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.util.BusinessUtil
 * JD-Core Version:    0.7.0.1
 */