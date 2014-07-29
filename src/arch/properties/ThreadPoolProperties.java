/*   1:    */ package arch.properties;
/*   2:    */ 
/*   3:    */ import java.io.IOException;
/*   4:    */ import java.io.InputStream;
/*   5:    */ import java.util.Properties;
/*   6:    */ import org.apache.log4j.Logger;
/*   7:    */ 
/*   8:    */ public class ThreadPoolProperties
/*   9:    */ {
/*  10: 17 */   private static Logger log = Logger.getLogger(ThreadPoolProperties.class);
/*  11:    */   private static Integer textCoreThread;
/*  12:    */   private static Integer textMaxThread;
/*  13:    */   private static Integer businessCoreThread;
/*  14:    */   private static Integer businessMaxThread;
/*  15:    */   private static Integer locationCoreThread;
/*  16:    */   private static Integer locationMaxThread;
/*  17:    */   private static Integer eventClickCoreThread;
/*  18:    */   private static Integer eventClickTextMaxThread;
/*  19:    */   private static Integer eventScanCoreThread;
/*  20:    */   private static Integer eventScanMaxThread;
/*  21:    */   private static Integer eventSubscribeCoreThread;
/*  22:    */   private static Integer eventSubscribeMaxThread;
/*  23:    */   private static Integer eventUnsubscribeCoreThread;
/*  24:    */   private static Integer eventUnsubscribeMaxThread;
/*  25:    */   private static Integer noticeBusinessCoreThread;
/*  26:    */   private static Integer noticeBusinessMaxThread;
/*  27:    */   private static Integer sendCoreThread;
/*  28:    */   private static Integer sendMaxThread;
/*  29:    */   private static Integer reSendCoreThread;
/*  30:    */   private static Integer reSendMaxThread;
/*  31:    */   private static Integer exclusiveCoreThread;
/*  32:    */   private static Integer exclusiveMaxThread;
/*  33:    */   private static Integer serviceCoreThread;
/*  34:    */   private static Integer serviceMaxThread;
/*  35:    */   private static Integer serviceSendCoreThread;
/*  36:    */   private static Integer serviceSendMaxThread;
/*  37:    */   private static Integer serviceSignCoreThread;
/*  38:    */   private static Integer serviceSignMaxThread;
/*  39:    */   
/*  40:    */   static
/*  41:    */   {
/*  42: 48 */     Properties pro = new Properties();
/*  43: 49 */     InputStream in = ThreadPoolProperties.class.getClassLoader().getResourceAsStream("threadPool.properties");
/*  44:    */     try
/*  45:    */     {
/*  46: 51 */       pro.load(in);
/*  47: 52 */       textCoreThread = Integer.valueOf(Integer.parseInt(pro.getProperty("textCoreThread")));
/*  48: 53 */       textMaxThread = Integer.valueOf(Integer.parseInt(pro.getProperty("textMaxThread")));
/*  49: 54 */       businessCoreThread = Integer.valueOf(Integer.parseInt(pro.getProperty("businessCoreThread")));
/*  50: 55 */       businessMaxThread = Integer.valueOf(Integer.parseInt(pro.getProperty("businessMaxThread")));
/*  51: 56 */       locationCoreThread = Integer.valueOf(Integer.parseInt(pro.getProperty("locationCoreThread")));
/*  52: 57 */       locationMaxThread = Integer.valueOf(Integer.parseInt(pro.getProperty("locationMaxThread")));
/*  53: 58 */       eventClickCoreThread = Integer.valueOf(Integer.parseInt(pro.getProperty("eventClickCoreThread")));
/*  54: 59 */       eventClickTextMaxThread = Integer.valueOf(Integer.parseInt(pro.getProperty("eventClickTextMaxThread")));
/*  55: 60 */       eventScanCoreThread = Integer.valueOf(Integer.parseInt(pro.getProperty("eventScanCoreThread")));
/*  56: 61 */       eventScanMaxThread = Integer.valueOf(Integer.parseInt(pro.getProperty("eventScanMaxThread")));
/*  57: 62 */       eventSubscribeCoreThread = Integer.valueOf(Integer.parseInt(pro.getProperty("eventSubscribeCoreThread")));
/*  58: 63 */       eventSubscribeMaxThread = Integer.valueOf(Integer.parseInt(pro.getProperty("eventSubscribeMaxThread")));
/*  59: 64 */       eventUnsubscribeCoreThread = Integer.valueOf(Integer.parseInt(pro.getProperty("eventUnsubscribeCoreThread")));
/*  60: 65 */       eventUnsubscribeMaxThread = Integer.valueOf(Integer.parseInt(pro.getProperty("eventUnsubscribeMaxThread")));
/*  61: 66 */       noticeBusinessCoreThread = Integer.valueOf(Integer.parseInt(pro.getProperty("noticeBusinessCoreThread")));
/*  62: 67 */       noticeBusinessMaxThread = Integer.valueOf(Integer.parseInt(pro.getProperty("noticeBusinessMaxThread")));
/*  63: 68 */       sendCoreThread = Integer.valueOf(Integer.parseInt(pro.getProperty("sendCoreThread")));
/*  64: 69 */       sendMaxThread = Integer.valueOf(Integer.parseInt(pro.getProperty("sendMaxThread")));
/*  65: 70 */       reSendCoreThread = Integer.valueOf(Integer.parseInt(pro.getProperty("reSendCoreThread")));
/*  66: 71 */       reSendMaxThread = Integer.valueOf(Integer.parseInt(pro.getProperty("reSendMaxThread")));
/*  67: 72 */       exclusiveCoreThread = Integer.valueOf(Integer.parseInt(pro.getProperty("exclusiveCoreThread")));
/*  68: 73 */       exclusiveMaxThread = Integer.valueOf(Integer.parseInt(pro.getProperty("exclusiveMaxThread")));
/*  69: 74 */       serviceCoreThread = Integer.valueOf(Integer.parseInt(pro.getProperty("serviceCoreThread")));
/*  70: 75 */       serviceMaxThread = Integer.valueOf(Integer.parseInt(pro.getProperty("serviceMaxThread")));
/*  71: 76 */       serviceSendCoreThread = Integer.valueOf(Integer.parseInt(pro.getProperty("serviceSendCoreThread")));
/*  72: 77 */       serviceSendMaxThread = Integer.valueOf(Integer.parseInt(pro.getProperty("serviceSendMaxThread")));
/*  73: 78 */       serviceSignCoreThread = Integer.valueOf(Integer.parseInt(pro.getProperty("serviceSignCoreThread")));
/*  74: 79 */       serviceSignMaxThread = Integer.valueOf(Integer.parseInt(pro.getProperty("serviceSignMaxThread")));
/*  75:    */     }
/*  76:    */     catch (IOException e)
/*  77:    */     {
/*  78: 81 */       log.fatal("ThreadPoolUtil读取配置文件抛出异常", e);
/*  79:    */     }
/*  80:    */   }
/*  81:    */   
/*  82:    */   public static Integer getTextCoreThread()
/*  83:    */   {
/*  84: 89 */     return textCoreThread;
/*  85:    */   }
/*  86:    */   
/*  87:    */   public static Integer getTextMaxThread()
/*  88:    */   {
/*  89: 96 */     return textMaxThread;
/*  90:    */   }
/*  91:    */   
/*  92:    */   public static Integer getBusinessCoreThread()
/*  93:    */   {
/*  94:103 */     return businessCoreThread;
/*  95:    */   }
/*  96:    */   
/*  97:    */   public static Integer getBusinessMaxThread()
/*  98:    */   {
/*  99:110 */     return businessMaxThread;
/* 100:    */   }
/* 101:    */   
/* 102:    */   public static Integer getLocationCoreThread()
/* 103:    */   {
/* 104:117 */     return locationCoreThread;
/* 105:    */   }
/* 106:    */   
/* 107:    */   public static Integer getLocationMaxThread()
/* 108:    */   {
/* 109:124 */     return locationMaxThread;
/* 110:    */   }
/* 111:    */   
/* 112:    */   public static Integer getEventClickCoreThread()
/* 113:    */   {
/* 114:131 */     return eventClickCoreThread;
/* 115:    */   }
/* 116:    */   
/* 117:    */   public static Integer getEventClickTextMaxThread()
/* 118:    */   {
/* 119:138 */     return eventClickTextMaxThread;
/* 120:    */   }
/* 121:    */   
/* 122:    */   public static Integer getEventScanCoreThread()
/* 123:    */   {
/* 124:145 */     return eventScanCoreThread;
/* 125:    */   }
/* 126:    */   
/* 127:    */   public static Integer getEventScanMaxThread()
/* 128:    */   {
/* 129:152 */     return eventScanMaxThread;
/* 130:    */   }
/* 131:    */   
/* 132:    */   public static Integer getEventSubscribeCoreThread()
/* 133:    */   {
/* 134:159 */     return eventSubscribeCoreThread;
/* 135:    */   }
/* 136:    */   
/* 137:    */   public static Integer getEventSubscribeMaxThread()
/* 138:    */   {
/* 139:166 */     return eventSubscribeMaxThread;
/* 140:    */   }
/* 141:    */   
/* 142:    */   public static Integer getEventUnsubscribeCoreThread()
/* 143:    */   {
/* 144:173 */     return eventUnsubscribeCoreThread;
/* 145:    */   }
/* 146:    */   
/* 147:    */   public static Integer getEventUnsubscribeMaxThread()
/* 148:    */   {
/* 149:180 */     return eventUnsubscribeMaxThread;
/* 150:    */   }
/* 151:    */   
/* 152:    */   public static Integer getNoticeBusinessCoreThread()
/* 153:    */   {
/* 154:188 */     return noticeBusinessCoreThread;
/* 155:    */   }
/* 156:    */   
/* 157:    */   public static Integer getNoticeBusinessMaxThread()
/* 158:    */   {
/* 159:195 */     return noticeBusinessMaxThread;
/* 160:    */   }
/* 161:    */   
/* 162:    */   public static Integer getSendCoreThread()
/* 163:    */   {
/* 164:202 */     return sendCoreThread;
/* 165:    */   }
/* 166:    */   
/* 167:    */   public static Integer getSendMaxThread()
/* 168:    */   {
/* 169:209 */     return sendMaxThread;
/* 170:    */   }
/* 171:    */   
/* 172:    */   public static Integer getReSendCoreThread()
/* 173:    */   {
/* 174:216 */     return reSendCoreThread;
/* 175:    */   }
/* 176:    */   
/* 177:    */   public static Integer getReSendMaxThread()
/* 178:    */   {
/* 179:223 */     return reSendMaxThread;
/* 180:    */   }
/* 181:    */   
/* 182:    */   public static Integer getExclusiveCoreThread()
/* 183:    */   {
/* 184:227 */     return exclusiveCoreThread;
/* 185:    */   }
/* 186:    */   
/* 187:    */   public static Integer getExclusiveMaxThread()
/* 188:    */   {
/* 189:230 */     return exclusiveMaxThread;
/* 190:    */   }
/* 191:    */   
/* 192:    */   public static Integer getServiceCoreThread()
/* 193:    */   {
/* 194:233 */     return serviceCoreThread;
/* 195:    */   }
/* 196:    */   
/* 197:    */   public static Integer getServiceMaxThread()
/* 198:    */   {
/* 199:236 */     return serviceMaxThread;
/* 200:    */   }
/* 201:    */   
/* 202:    */   public static Integer getServiceSendCoreThread()
/* 203:    */   {
/* 204:239 */     return serviceSendCoreThread;
/* 205:    */   }
/* 206:    */   
/* 207:    */   public static Integer getServiceSendMaxThread()
/* 208:    */   {
/* 209:242 */     return serviceSendMaxThread;
/* 210:    */   }
/* 211:    */   
/* 212:    */   public static Integer getServiceSignCoreThread()
/* 213:    */   {
/* 214:245 */     return serviceSignCoreThread;
/* 215:    */   }
/* 216:    */   
/* 217:    */   public static Integer getServiceSignMaxThread()
/* 218:    */   {
/* 219:248 */     return serviceSignMaxThread;
/* 220:    */   }
/* 221:    */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.properties.ThreadPoolProperties
 * JD-Core Version:    0.7.0.1
 */