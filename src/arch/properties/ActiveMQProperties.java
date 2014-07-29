/*  1:   */ package arch.properties;
/*  2:   */ 
/*  3:   */ import java.io.IOException;
/*  4:   */ import java.io.InputStream;
/*  5:   */ import java.util.Properties;
/*  6:   */ import org.apache.log4j.Logger;
/*  7:   */ 
/*  8:   */ public class ActiveMQProperties
/*  9:   */ {
/* 10:16 */   private static Logger log = Logger.getLogger(ActiveMQProperties.class);
/* 11:   */   private static String user;
/* 12:   */   private static String password;
/* 13:   */   private static String brokerUrl;
/* 14:   */   private static Integer maximumActive;
/* 15:   */   private static Integer maxConnections;
/* 16:   */   
/* 17:   */   static
/* 18:   */   {
/* 19:24 */     Properties pro = new Properties();
/* 20:25 */     InputStream in = ActiveMQProperties.class.getClassLoader().getResourceAsStream("activemq.properties");
/* 21:   */     try
/* 22:   */     {
/* 23:27 */       pro.load(in);
/* 24:28 */       user = pro.getProperty("user");
/* 25:29 */       password = pro.getProperty("password");
/* 26:30 */       brokerUrl = pro.getProperty("brokerUrl");
/* 27:31 */       maximumActive = Integer.valueOf(Integer.parseInt(pro.getProperty("maximumActive")));
/* 28:32 */       maxConnections = Integer.valueOf(Integer.parseInt(pro.getProperty("maxConnections")));
/* 29:   */     }
/* 30:   */     catch (IOException e)
/* 31:   */     {
/* 32:34 */       log.fatal("ActiveMQUtil读取配置文件抛出异常", e);
/* 33:   */     }
/* 34:   */   }
/* 35:   */   
/* 36:   */   public static String getUser()
/* 37:   */   {
/* 38:39 */     return user;
/* 39:   */   }
/* 40:   */   
/* 41:   */   public static String getPassword()
/* 42:   */   {
/* 43:43 */     return password;
/* 44:   */   }
/* 45:   */   
/* 46:   */   public static String getBrokerUrl()
/* 47:   */   {
/* 48:47 */     return brokerUrl;
/* 49:   */   }
/* 50:   */   
/* 51:   */   public static Integer getMaximumActive()
/* 52:   */   {
/* 53:51 */     return maximumActive;
/* 54:   */   }
/* 55:   */   
/* 56:   */   public static Integer getMaxConnections()
/* 57:   */   {
/* 58:55 */     return maxConnections;
/* 59:   */   }
/* 60:   */   
/* 61:   */   public static void main(String[] name)
/* 62:   */   {
/* 63:59 */     log.debug(getBrokerUrl());
/* 64:   */   }
/* 65:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.properties.ActiveMQProperties
 * JD-Core Version:    0.7.0.1
 */