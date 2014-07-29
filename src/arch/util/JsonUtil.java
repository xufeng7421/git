/*  1:   */ package arch.util;
/*  2:   */ 
/*  3:   */ import java.lang.reflect.Field;
/*  4:   */ import org.apache.log4j.Logger;
/*  5:   */ import org.json.JSONObject;
/*  6:   */ 
/*  7:   */ public class JsonUtil
/*  8:   */ {
/*  9:14 */   private static Logger log = Logger.getLogger(JsonUtil.class);
/* 10:   */   
/* 11:   */   public static String parseToJSON(Object obj)
/* 12:   */   {
/* 13:24 */     String result = null;
/* 14:25 */     JSONObject data = new JSONObject();
/* 15:26 */     if (obj != null)
/* 16:   */     {
/* 17:27 */       Class<?> cla = obj.getClass();
/* 18:28 */       Field[] fields = cla.getDeclaredFields();
/* 19:29 */       log.debug("属性个数：   " + fields.length);
/* 20:   */       try
/* 21:   */       {
/* 22:31 */         for (Field f : fields)
/* 23:   */         {
/* 24:32 */           f.setAccessible(true);
/* 25:33 */           if (!f.getType().getSimpleName().equals("List")) {
/* 26:36 */             if (f.get(obj) == null) {
/* 27:37 */               data.put(f.getName(), "");
/* 28:   */             } else {
/* 29:39 */               data.put(f.getName(), f.get(obj).toString());
/* 30:   */             }
/* 31:   */           }
/* 32:   */         }
/* 33:43 */         result = data.toString();
/* 34:   */       }
/* 35:   */       catch (Exception e)
/* 36:   */       {
/* 37:45 */         log.fatal("JsonUtil.ParseToJSON 转换JSON出错", e);
/* 38:46 */         result = "{}";
/* 39:   */       }
/* 40:   */     }
/* 41:50 */     return result;
/* 42:   */   }
/* 43:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.util.JsonUtil
 * JD-Core Version:    0.7.0.1
 */