/*   1:    */ package arch.mulitservice.execute;
/*   2:    */ 
/*   3:    */ import arch.entity.BusinessConfig;
/*   4:    */ import arch.entity.ReplyEntity;
/*   5:    */ import arch.entity.WxMessage;
/*   6:    */ import arch.entity.type.NoticeState;
/*   7:    */ import arch.mq.DB.service.QueueService;
/*   8:    */ import arch.mq.service.MqService;
/*   9:    */ import arch.mulitservice.DB.service.ChatService;
/*  10:    */ import arch.mulitservice.entity.ChatEntity;
/*  11:    */ import arch.mulitservice.entity.ChatMessage;
/*  12:    */ import arch.properties.QueueProperties;
/*  13:    */ import arch.terminology.Terminology;
/*  14:    */ import arch.util.DbcpConnection;
/*  15:    */ import java.sql.Connection;
/*  16:    */ import java.sql.SQLException;
/*  17:    */ import java.util.ArrayList;
/*  18:    */ import java.util.HashSet;
/*  19:    */ import java.util.Hashtable;
/*  20:    */ import java.util.List;
/*  21:    */ import java.util.Map;
/*  22:    */ import java.util.Set;
/*  23:    */ import org.apache.log4j.Logger;
/*  24:    */ 
/*  25:    */ public class ChatExecute
/*  26:    */ {
/*  27: 34 */   private static Logger log = Logger.getLogger(ChatExecute.class);
/*  28: 47 */   private static ChatService chatService = new ChatService();
/*  29: 50 */   private static Map<String, String> relationMap = new Hashtable();
/*  30: 51 */   private static Map<String, ChatEntity> serverMap = new Hashtable();
/*  31:    */   
/*  32:    */   public static boolean isReceived(String openId)
/*  33:    */   {
/*  34: 59 */     return relationMap.containsKey(openId);
/*  35:    */   }
/*  36:    */   
/*  37:    */   public static String getServiceId(String openId)
/*  38:    */   {
/*  39: 67 */     return (String)relationMap.get(openId);
/*  40:    */   }
/*  41:    */   
/*  42:    */   public static void addService(String wechatId, String serviceId)
/*  43:    */   {
/*  44: 94 */     if (!serverMap.containsKey(wechatId)) {
/*  45: 95 */       serverMap.put(wechatId, new ChatEntity());
/*  46:    */     }
/*  47: 97 */     ChatEntity sEntity = (ChatEntity)serverMap.get(wechatId);
/*  48: 98 */     if (!sEntity.getServiceMap().containsKey(serviceId)) {
/*  49: 99 */       sEntity.getServiceMap().put(serviceId, new HashSet());
/*  50:    */     }
/*  51:    */   }
/*  52:    */   
/*  53:    */   public static void removeService(String wechatId, String serviceId)
/*  54:    */   {
/*  55:110 */     Set<String> temp = (Set)((ChatEntity)serverMap.get(wechatId)).getServiceMap().remove(serviceId);
/*  56:111 */     if (temp != null) {
/*  57:112 */       for (String str : temp) {
/*  58:113 */         relationMap.remove(str);
/*  59:    */       }
/*  60:    */     }
/*  61:    */   }
/*  62:    */   
/*  63:    */   public static int addWaitMan(String wechatId, String openId)
/*  64:    */   {
/*  65:124 */     if (!serverMap.containsKey(wechatId)) {
/*  66:125 */       serverMap.put(wechatId, new ChatEntity());
/*  67:    */     }
/*  68:127 */     List<String> list = ((ChatEntity)serverMap.get(wechatId)).getQueueList();
/*  69:128 */     int len = -1;
/*  70:129 */     if (!list.contains(openId))
/*  71:    */     {
/*  72:130 */       list.add(openId);
/*  73:131 */       len = list.size();
/*  74:    */     }
/*  75:    */     else
/*  76:    */     {
/*  77:133 */       len = list.indexOf(openId) + 1;
/*  78:    */     }
/*  79:135 */     return len;
/*  80:    */   }
/*  81:    */   
/*  82:    */   public static void remoreWaitMain(String wechatId, String openId)
/*  83:    */   {
/*  84:160 */     ((ChatEntity)serverMap.get(wechatId)).getQueueList().remove(openId);
/*  85:    */   }
/*  86:    */   
/*  87:    */   public static void saveMessage(WxMessage wxMessage, String serviceId)
/*  88:    */   {
/*  89:169 */     Connection conn = null;
/*  90:    */     try
/*  91:    */     {
/*  92:171 */       conn = DbcpConnection.getConnection();
/*  93:172 */       chatService.saveMessage(conn, wxMessage, serviceId);
/*  94:173 */       conn.commit();
/*  95:    */     }
/*  96:    */     catch (SQLException e)
/*  97:    */     {
/*  98:175 */       log.fatal("保存交互信息出错", e);
/*  99:    */     }
/* 100:    */     finally
/* 101:    */     {
/* 102:177 */       DbcpConnection.close(conn, null, null);
/* 103:    */     }
/* 104:    */   }
/* 105:    */   
/* 106:    */   public static List<ChatMessage> findNewMessage(String wechatId, String openId)
/* 107:    */   {
/* 108:187 */     Connection conn = null;
/* 109:188 */     List<ChatMessage> list = null;
/* 110:    */     try
/* 111:    */     {
/* 112:190 */       conn = DbcpConnection.getConnection();
/* 113:191 */       list = chatService.findNewMessage(conn, wechatId, openId);
/* 114:192 */       conn.commit();
/* 115:    */     }
/* 116:    */     catch (SQLException e)
/* 117:    */     {
/* 118:194 */       log.fatal("保存交互信息出错", e);
/* 119:    */     }
/* 120:    */     finally
/* 121:    */     {
/* 122:196 */       DbcpConnection.close(conn, null, null);
/* 123:    */     }
/* 124:198 */     return list;
/* 125:    */   }
/* 126:    */   
/* 127:    */   public static String acceptCommication(String wechatId, String serviceId)
/* 128:    */   {
/* 129:208 */     ChatEntity chatEntity = (ChatEntity)serverMap.get(wechatId);
/* 130:209 */     String openId = null;
/* 131:210 */     if (chatEntity != null)
/* 132:    */     {
/* 133:211 */       List<String> list = chatEntity.getQueueList();
/* 134:212 */       if (list.size() != 0)
/* 135:    */       {
/* 136:213 */         openId = (String)list.get(0);
/* 137:214 */         relationMap.put(openId, serviceId);
/* 138:215 */         list.remove(openId);
/* 139:216 */         ((Set)chatEntity.getServiceMap().get(serviceId)).add(openId);
/* 140:217 */         String msg = Terminology.getServiceStart(serviceId);
/* 141:    */         try
/* 142:    */         {
/* 143:219 */           ReplyEntity message = new ReplyEntity(wechatId, openId, msg);
/* 144:220 */           MqService.getInstance().send(message, QueueProperties.getServiceSendQueue());
/* 145:    */         }
/* 146:    */         catch (Exception e)
/* 147:    */         {
/* 148:222 */           log.fatal("发送消息到客服发送队列出错", e);
/* 149:    */         }
/* 150:    */       }
/* 151:    */     }
/* 152:226 */     return openId;
/* 153:    */   }
/* 154:    */   
/* 155:    */   public static void endCommicationByService(String wechatId, String openId, String serviceId)
/* 156:    */   {
/* 157:235 */     relationMap.remove(openId);
/* 158:236 */     ChatEntity chatEntity = (ChatEntity)serverMap.get(wechatId);
/* 159:237 */     ((Set)chatEntity.getServiceMap().get(serviceId)).remove(openId);
/* 160:238 */     String msg = Terminology.getServiceEnd();
/* 161:239 */     Connection conn = null;
/* 162:    */     try
/* 163:    */     {
/* 164:241 */       conn = DbcpConnection.getConnection();
/* 165:242 */       BusinessConfig config = new QueueService().findProcessId(conn, openId, "serviceFlow");
/* 166:243 */       ReplyEntity message = new ReplyEntity(wechatId, openId, msg);
/* 167:244 */       MqService.getInstance().send(message, QueueProperties.getServiceSendQueue());
/* 168:245 */       WxMessage wxMessage = new WxMessage();
/* 169:246 */       wxMessage.setOpenId(openId);
/* 170:247 */       wxMessage.setBssType(NoticeState.END);
/* 171:248 */       wxMessage.setBusinessId("serviceFlow");
/* 172:249 */       wxMessage.setProcessInstanceId(config.getPeocessId());
/* 173:250 */       MqService.getInstance().send(wxMessage, QueueProperties.getServiceQueue());
/* 174:    */     }
/* 175:    */     catch (Exception e)
/* 176:    */     {
/* 177:252 */       log.fatal("发送消息到客服发送队列出错", e);
/* 178:    */     }
/* 179:    */     finally
/* 180:    */     {
/* 181:254 */       DbcpConnection.close(conn, null, null);
/* 182:    */     }
/* 183:    */   }
/* 184:    */   
/* 185:    */   public static List<String> findCommunicateOpenId(String wechatId, String serviceId)
/* 186:    */   {
/* 187:266 */     List<String> list = new ArrayList();
/* 188:267 */     if (serverMap.get(wechatId) != null)
/* 189:    */     {
/* 190:268 */       Set<String> set = (Set)((ChatEntity)serverMap.get(wechatId)).getServiceMap().get(serviceId);
/* 191:269 */       for (String s : set) {
/* 192:270 */         list.add(s);
/* 193:    */       }
/* 194:    */     }
/* 195:273 */     return list;
/* 196:    */   }
/* 197:    */   
/* 198:    */   public static void endCommicationByCustomer(String wechatId, String openId)
/* 199:    */   {
/* 200:282 */     String serviceId = (String)relationMap.remove(openId);
/* 201:283 */     String msg = Terminology.getServiceEnd();
/* 202:284 */     Connection conn = null;
/* 203:    */     try
/* 204:    */     {
/* 205:286 */       conn = DbcpConnection.getConnection();
/* 206:287 */       BusinessConfig config = new QueueService().findProcessId(conn, openId, "serviceFlow");
/* 207:288 */       ReplyEntity message = new ReplyEntity(wechatId, openId, msg);
/* 208:289 */       MqService.getInstance().send(message, QueueProperties.getServiceSendQueue());
/* 209:290 */       WxMessage wxMessage = new WxMessage();
/* 210:291 */       wxMessage.setOpenId(openId);
/* 211:292 */       wxMessage.setBssType(NoticeState.END);
/* 212:293 */       wxMessage.setBusinessId("serviceFlow");
/* 213:294 */       wxMessage.setProcessInstanceId(config.getPeocessId());
/* 214:295 */       MqService.getInstance().send(wxMessage, QueueProperties.getServiceQueue());
/* 215:296 */       log.error("done");
/* 216:    */     }
/* 217:    */     catch (Exception e)
/* 218:    */     {
/* 219:298 */       log.fatal("发送消息到客服发送队列出错", e);
/* 220:    */     }
/* 221:    */     finally
/* 222:    */     {
/* 223:300 */       DbcpConnection.close(conn, null, null);
/* 224:    */     }
/* 225:302 */     if (serviceId != null)
/* 226:    */     {
/* 227:303 */       ChatEntity chatEntity = (ChatEntity)serverMap.get(wechatId);
/* 228:304 */       ((Set)chatEntity.getServiceMap().get(serviceId)).remove(openId);
/* 229:    */     }
/* 230:    */   }
/* 231:    */   
/* 232:    */   public static int waitManCount(String wechatId)
/* 233:    */   {
/* 234:313 */     int count = 0;
/* 235:314 */     if (serverMap.get(wechatId) != null) {
/* 236:315 */       count = ((ChatEntity)serverMap.get(wechatId)).getQueueList().size();
/* 237:    */     }
/* 238:317 */     return count;
/* 239:    */   }
/* 240:    */   
/* 241:    */   public static String findNickName(String openId)
/* 242:    */   {
/* 243:326 */     Connection conn = null;
/* 244:327 */     String result = null;
/* 245:    */     try
/* 246:    */     {
/* 247:329 */       conn = DbcpConnection.getConnection();
/* 248:330 */       result = chatService.findNickName(conn, openId);
/* 249:    */     }
/* 250:    */     catch (SQLException e)
/* 251:    */     {
/* 252:332 */       log.fatal("保存交互信息出错", e);
/* 253:    */     }
/* 254:    */     finally
/* 255:    */     {
/* 256:334 */       DbcpConnection.close(conn, null, null);
/* 257:    */     }
/* 258:336 */     return result;
/* 259:    */   }
/* 260:    */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.mulitservice.execute.ChatExecute
 * JD-Core Version:    0.7.0.1
 */