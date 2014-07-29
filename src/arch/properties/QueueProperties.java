/*   1:    */ package arch.properties;
/*   2:    */ 
/*   3:    */ import java.io.IOException;
/*   4:    */ import java.io.InputStream;
/*   5:    */ import java.util.Properties;
/*   6:    */ import org.apache.log4j.Logger;
/*   7:    */ 
/*   8:    */ public class QueueProperties
/*   9:    */ {
/*  10: 14 */   private static Logger log = Logger.getLogger(QueueProperties.class);
/*  11:    */   private static String textQueue;
/*  12:    */   private static String businessQueue;
/*  13:    */   private static String locationQueue;
/*  14:    */   private static String eventClickQueue;
/*  15:    */   private static String eventScanQueue;
/*  16:    */   private static String eventSubscribeQueue;
/*  17:    */   private static String eventUnSubscribeQueue;
/*  18:    */   private static String sendQueue;
/*  19:    */   private static String resendQueue;
/*  20:    */   private static String noticeBusinessQueue;
/*  21:    */   private static String accessTokenQueue;
/*  22:    */   private static String exclusiveQueue;
/*  23:    */   private static String serviceSendQueue;
/*  24:    */   private static String serviceQueue;
/*  25:    */   private static String serviceReviceQueue;
/*  26:    */   private static String serviceSignQueue;
/*  27:    */   
/*  28:    */   static
/*  29:    */   {
/*  30: 33 */     Properties pro = new Properties();
/*  31: 34 */     InputStream in = QueueProperties.class.getClassLoader().getResourceAsStream("queue.properties");
/*  32:    */     try
/*  33:    */     {
/*  34: 36 */       pro.load(in);
/*  35: 37 */       textQueue = pro.getProperty("textQueue");
/*  36: 38 */       businessQueue = pro.getProperty("businessQueue");
/*  37: 39 */       locationQueue = pro.getProperty("locationQueue");
/*  38: 40 */       eventClickQueue = pro.getProperty("eventClickQueue");
/*  39: 41 */       eventScanQueue = pro.getProperty("eventScanQueue");
/*  40: 42 */       eventSubscribeQueue = pro.getProperty("eventSubscribeQueue");
/*  41: 43 */       eventUnSubscribeQueue = pro.getProperty("eventUnsubscribeQueue");
/*  42: 44 */       sendQueue = pro.getProperty("sendQueue");
/*  43: 45 */       resendQueue = pro.getProperty("reSendQueue");
/*  44: 46 */       noticeBusinessQueue = pro.getProperty("noticeBusinessQueue");
/*  45: 47 */       accessTokenQueue = pro.getProperty("accessTokenQueue");
/*  46: 48 */       exclusiveQueue = pro.getProperty("exclusiveQueue");
/*  47: 49 */       serviceSendQueue = pro.getProperty("serviceSendQueue");
/*  48: 50 */       serviceReviceQueue = pro.getProperty("serviceReviceQueue");
/*  49: 51 */       serviceSignQueue = pro.getProperty("serviceSignQueue");
/*  50: 52 */       serviceQueue = pro.getProperty("serviceQueue");
/*  51:    */     }
/*  52:    */     catch (IOException e)
/*  53:    */     {
/*  54: 54 */       log.fatal("QueueProperties读取配置文件抛出异常", e);
/*  55:    */     }
/*  56:    */   }
/*  57:    */   
/*  58:    */   public static String getTextQueue()
/*  59:    */   {
/*  60: 62 */     return textQueue;
/*  61:    */   }
/*  62:    */   
/*  63:    */   public static String getBusinessQueue()
/*  64:    */   {
/*  65: 70 */     return businessQueue;
/*  66:    */   }
/*  67:    */   
/*  68:    */   public static String getLocationQueue()
/*  69:    */   {
/*  70: 78 */     return locationQueue;
/*  71:    */   }
/*  72:    */   
/*  73:    */   public static String getEventClickQueue()
/*  74:    */   {
/*  75: 86 */     return eventClickQueue;
/*  76:    */   }
/*  77:    */   
/*  78:    */   public static String getEventScanQueue()
/*  79:    */   {
/*  80: 94 */     return eventScanQueue;
/*  81:    */   }
/*  82:    */   
/*  83:    */   public static String getEventSubscribeQueue()
/*  84:    */   {
/*  85:102 */     return eventSubscribeQueue;
/*  86:    */   }
/*  87:    */   
/*  88:    */   public static String getEventUnSubscribeQueue()
/*  89:    */   {
/*  90:110 */     return eventUnSubscribeQueue;
/*  91:    */   }
/*  92:    */   
/*  93:    */   public static String getSendQueue()
/*  94:    */   {
/*  95:118 */     return sendQueue;
/*  96:    */   }
/*  97:    */   
/*  98:    */   public static String getResendQueue()
/*  99:    */   {
/* 100:126 */     return resendQueue;
/* 101:    */   }
/* 102:    */   
/* 103:    */   public static String getNoticeBusinessQueue()
/* 104:    */   {
/* 105:134 */     return noticeBusinessQueue;
/* 106:    */   }
/* 107:    */   
/* 108:    */   public static String getAccessTokenQueue()
/* 109:    */   {
/* 110:142 */     return accessTokenQueue;
/* 111:    */   }
/* 112:    */   
/* 113:    */   public static String getExclusiveQueue()
/* 114:    */   {
/* 115:146 */     return exclusiveQueue;
/* 116:    */   }
/* 117:    */   
/* 118:    */   public static String getServiceSendQueue()
/* 119:    */   {
/* 120:150 */     return serviceSendQueue;
/* 121:    */   }
/* 122:    */   
/* 123:    */   public static String getServiceReviceQueue()
/* 124:    */   {
/* 125:154 */     return serviceReviceQueue;
/* 126:    */   }
/* 127:    */   
/* 128:    */   public static String getServiceSignQueue()
/* 129:    */   {
/* 130:158 */     return serviceSignQueue;
/* 131:    */   }
/* 132:    */   
/* 133:    */   public static String getServiceQueue()
/* 134:    */   {
/* 135:162 */     return serviceQueue;
/* 136:    */   }
/* 137:    */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.properties.QueueProperties
 * JD-Core Version:    0.7.0.1
 */