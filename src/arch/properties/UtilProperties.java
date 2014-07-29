/*  1:   */ package arch.properties;
/*  2:   */ 
/*  3:   */ import java.io.IOException;
/*  4:   */ import java.io.InputStream;
/*  5:   */ import java.util.Properties;
/*  6:   */ import org.apache.log4j.Logger;
/*  7:   */ 
/*  8:   */ public class UtilProperties
/*  9:   */ {
/* 10: 8 */   private static Logger log = Logger.getLogger(UtilProperties.class);
/* 11:   */   private static Integer pageSize;
/* 12:   */   private static String host;
/* 13:   */   private static String api;
/* 14:   */   private static String token;
/* 15:   */   private static Integer sceneCode;
/* 16:   */   private static Long replyLimit;
/* 17:   */   private static Long tokenLimit;
/* 18:   */   private static String separator;
/* 19:   */   private static String caseStartTime;
/* 20:   */   private static Long perCaseUpdateTime;
/* 21:   */   private static String serviceBusiness;
/* 22:   */   private static String serviceFalg;
/* 23:   */   
/* 24:   */   static
/* 25:   */   {
/* 26:23 */     Properties pro = new Properties();
/* 27:24 */     InputStream in = UtilProperties.class.getClassLoader().getResourceAsStream("util.properties");
/* 28:   */     try
/* 29:   */     {
/* 30:26 */       pro.load(in);
/* 31:27 */       pageSize = Integer.valueOf(Integer.parseInt(pro.getProperty("pageSize")));
/* 32:28 */       host = pro.getProperty("HOST");
/* 33:29 */       token = pro.getProperty("TOKEN");
/* 34:30 */       api = pro.getProperty("API");
/* 35:31 */       sceneCode = Integer.valueOf(Integer.parseInt(pro.getProperty("sceneCode")));
/* 36:32 */       replyLimit = Long.valueOf(Long.parseLong(pro.getProperty("replyLimit")));
/* 37:33 */       tokenLimit = Long.valueOf(Long.parseLong(pro.getProperty("tokenLimit")));
/* 38:34 */       separator = pro.getProperty("separator");
/* 39:35 */       caseStartTime = pro.getProperty("caseStartTime");
/* 40:36 */       perCaseUpdateTime = Long.valueOf(Long.parseLong(pro.getProperty("perCaseUpdateTime")));
/* 41:37 */       serviceBusiness = pro.getProperty("serviceBusiness");
/* 42:38 */       serviceFalg = pro.getProperty("serviceFalg");
/* 43:   */     }
/* 44:   */     catch (IOException e)
/* 45:   */     {
/* 46:40 */       log.fatal("UtilProperties读取配置文件抛出异常", e);
/* 47:   */     }
/* 48:   */   }
/* 49:   */   
/* 50:   */   public static Integer getPageSize()
/* 51:   */   {
/* 52:45 */     return pageSize;
/* 53:   */   }
/* 54:   */   
/* 55:   */   public static String getHost()
/* 56:   */   {
/* 57:48 */     return host;
/* 58:   */   }
/* 59:   */   
/* 60:   */   public static String getToken()
/* 61:   */   {
/* 62:51 */     return token;
/* 63:   */   }
/* 64:   */   
/* 65:   */   public static Long getReplyLimit()
/* 66:   */   {
/* 67:54 */     return replyLimit;
/* 68:   */   }
/* 69:   */   
/* 70:   */   public static Long getTokenLimit()
/* 71:   */   {
/* 72:57 */     return tokenLimit;
/* 73:   */   }
/* 74:   */   
/* 75:   */   public static String getSeparator()
/* 76:   */   {
/* 77:60 */     return separator;
/* 78:   */   }
/* 79:   */   
/* 80:   */   public static String getApi()
/* 81:   */   {
/* 82:63 */     return api;
/* 83:   */   }
/* 84:   */   
/* 85:   */   public static String getCaseStartTime()
/* 86:   */   {
/* 87:66 */     return caseStartTime;
/* 88:   */   }
/* 89:   */   
/* 90:   */   public static Long getPerCaseUpdateTime()
/* 91:   */   {
/* 92:69 */     return perCaseUpdateTime;
/* 93:   */   }
/* 94:   */   
/* 95:   */   public static String getServiceBusiness()
/* 96:   */   {
/* 97:72 */     return serviceBusiness;
/* 98:   */   }
/* 99:   */   
/* :0:   */   public static String getServiceFalg()
/* :1:   */   {
/* :2:75 */     return serviceFalg;
/* :3:   */   }
/* :4:   */   
/* :5:   */   public static Integer getSceneCode()
/* :6:   */   {
/* :7:78 */     return sceneCode;
/* :8:   */   }
/* :9:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.properties.UtilProperties
 * JD-Core Version:    0.7.0.1
 */