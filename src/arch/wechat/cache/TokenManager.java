/*  1:   */ package arch.wechat.cache;
/*  2:   */ 
/*  3:   */ import arch.properties.UtilProperties;
/*  4:   */ import java.util.Date;
/*  5:   */ import java.util.HashMap;
/*  6:   */ 
/*  7:   */ public class TokenManager
/*  8:   */ {
/*  9: 9 */   private static HashMap<String, Long> cache = new HashMap();
/* 10:   */   
/* 11:   */   public static boolean isNew(String wechatId)
/* 12:   */   {
/* 13:16 */     if (cache.get(wechatId) == null) {
/* 14:17 */       return true;
/* 15:   */     }
/* 16:19 */     long aging = UtilProperties.getTokenLimit().longValue();
/* 17:20 */     if (new Date().getTime() - ((Long)cache.get(wechatId)).longValue() > aging) {
/* 18:21 */       return true;
/* 19:   */     }
/* 20:23 */     return false;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public static void setCache(String wechatId)
/* 24:   */   {
/* 25:28 */     cache.put(wechatId, Long.valueOf(new Date().getTime()));
/* 26:   */   }
/* 27:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.wechat.cache.TokenManager
 * JD-Core Version:    0.7.0.1
 */