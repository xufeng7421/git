/*   1:    */ package arch.entity;
/*   2:    */ 
/*   3:    */ import arch.entity.type.EventType;
/*   4:    */ import arch.entity.type.MsgType;
/*   5:    */ import arch.entity.type.NoticeState;
/*   6:    */ import arch.util.MD5Util;
/*   7:    */ import java.io.Serializable;
/*   8:    */ 
/*   9:    */ public class WxMessage
/*  10:    */   implements Serializable
/*  11:    */ {
/*  12:    */   private static final long serialVersionUID = 1L;
/*  13:    */   private String businessId;
/*  14:    */   private String companyId;
/*  15:    */   private String wechatId;
/*  16:    */   private String wxId;
/*  17:    */   private String openId;
/*  18:    */   private String createTime;
/*  19:    */   private String msgType;
/*  20:    */   private String msgId;
/*  21:    */   private String content;
/*  22:    */   private String event;
/*  23:    */   private String eventKey;
/*  24:    */   private String ticket;
/*  25:    */   private String latitude;
/*  26:    */   private String longitude;
/*  27:    */   private String precision;
/*  28:    */   private String location_X;
/*  29:    */   private String location_Y;
/*  30:    */   private String scale;
/*  31:    */   private String label;
/*  32:    */   private String picUrl;
/*  33:    */   private String mediaId;
/*  34:    */   private String format;
/*  35:    */   private String recognition;
/*  36:    */   private String thumbMediaId;
/*  37:    */   private String title;
/*  38:    */   private String description;
/*  39:    */   private String url;
/*  40:    */   private String ProcessInstanceId;
/*  41:    */   private String bssSendQueue;
/*  42:    */   private String bssReciveQueue;
/*  43: 59 */   private NoticeState bssType = NoticeState.RUNNING;
/*  44:    */   
/*  45:    */   public String getWechatId()
/*  46:    */   {
/*  47: 62 */     return this.wechatId;
/*  48:    */   }
/*  49:    */   
/*  50:    */   public void setWechatId(String wechatId)
/*  51:    */   {
/*  52: 65 */     this.wechatId = wechatId;
/*  53:    */   }
/*  54:    */   
/*  55:    */   public String getWxId()
/*  56:    */   {
/*  57: 68 */     return this.wxId;
/*  58:    */   }
/*  59:    */   
/*  60:    */   public void setWxId(String wxId)
/*  61:    */   {
/*  62: 71 */     this.wxId = wxId;
/*  63:    */   }
/*  64:    */   
/*  65:    */   public String getOpenId()
/*  66:    */   {
/*  67: 74 */     return this.openId;
/*  68:    */   }
/*  69:    */   
/*  70:    */   public void setOpenId(String openId)
/*  71:    */   {
/*  72: 77 */     this.openId = openId;
/*  73:    */   }
/*  74:    */   
/*  75:    */   public String getCreateTime()
/*  76:    */   {
/*  77: 80 */     return this.createTime;
/*  78:    */   }
/*  79:    */   
/*  80:    */   public void setCreateTime(String createTime)
/*  81:    */   {
/*  82: 83 */     this.createTime = createTime;
/*  83:    */   }
/*  84:    */   
/*  85:    */   public MsgType getMsgType()
/*  86:    */   {
/*  87: 86 */     return MsgType.valueOf(this.msgType.toUpperCase());
/*  88:    */   }
/*  89:    */   
/*  90:    */   public void setMsgType(String msgType)
/*  91:    */   {
/*  92: 89 */     this.msgType = msgType;
/*  93:    */   }
/*  94:    */   
/*  95:    */   public String getContent()
/*  96:    */   {
/*  97: 92 */     return this.content;
/*  98:    */   }
/*  99:    */   
/* 100:    */   public void setContent(String content)
/* 101:    */   {
/* 102: 95 */     this.content = content;
/* 103:    */   }
/* 104:    */   
/* 105:    */   public String getMsgId()
/* 106:    */   {
/* 107: 98 */     return this.msgId;
/* 108:    */   }
/* 109:    */   
/* 110:    */   public void setMsgId(String msgId)
/* 111:    */   {
/* 112:101 */     this.msgId = msgId;
/* 113:    */   }
/* 114:    */   
/* 115:    */   public EventType getEvent()
/* 116:    */   {
/* 117:104 */     if (this.event != null) {
/* 118:105 */       return EventType.valueOf(this.event.toUpperCase());
/* 119:    */     }
/* 120:107 */     return null;
/* 121:    */   }
/* 122:    */   
/* 123:    */   public void setEvent(String event)
/* 124:    */   {
/* 125:111 */     this.event = event;
/* 126:    */   }
/* 127:    */   
/* 128:    */   public String getEventKey()
/* 129:    */   {
/* 130:114 */     return this.eventKey;
/* 131:    */   }
/* 132:    */   
/* 133:    */   public void setEventKey(String eventKey)
/* 134:    */   {
/* 135:117 */     this.eventKey = eventKey;
/* 136:    */   }
/* 137:    */   
/* 138:    */   public String getLocation_X()
/* 139:    */   {
/* 140:120 */     return this.location_X;
/* 141:    */   }
/* 142:    */   
/* 143:    */   public void setLocation_X(String location_X)
/* 144:    */   {
/* 145:123 */     this.location_X = location_X;
/* 146:    */   }
/* 147:    */   
/* 148:    */   public String getLocation_Y()
/* 149:    */   {
/* 150:126 */     return this.location_Y;
/* 151:    */   }
/* 152:    */   
/* 153:    */   public void setLocation_Y(String location_Y)
/* 154:    */   {
/* 155:129 */     this.location_Y = location_Y;
/* 156:    */   }
/* 157:    */   
/* 158:    */   public String getScale()
/* 159:    */   {
/* 160:132 */     return this.scale;
/* 161:    */   }
/* 162:    */   
/* 163:    */   public void setScale(String scale)
/* 164:    */   {
/* 165:135 */     this.scale = scale;
/* 166:    */   }
/* 167:    */   
/* 168:    */   public String getLabel()
/* 169:    */   {
/* 170:138 */     return this.label;
/* 171:    */   }
/* 172:    */   
/* 173:    */   public void setLabel(String label)
/* 174:    */   {
/* 175:141 */     this.label = label;
/* 176:    */   }
/* 177:    */   
/* 178:    */   public String getTicket()
/* 179:    */   {
/* 180:144 */     return this.ticket;
/* 181:    */   }
/* 182:    */   
/* 183:    */   public void setTicket(String ticket)
/* 184:    */   {
/* 185:147 */     this.ticket = ticket;
/* 186:    */   }
/* 187:    */   
/* 188:    */   public String getLatitude()
/* 189:    */   {
/* 190:150 */     return this.latitude;
/* 191:    */   }
/* 192:    */   
/* 193:    */   public void setLatitude(String latitude)
/* 194:    */   {
/* 195:153 */     this.latitude = latitude;
/* 196:    */   }
/* 197:    */   
/* 198:    */   public String getLongitude()
/* 199:    */   {
/* 200:156 */     return this.longitude;
/* 201:    */   }
/* 202:    */   
/* 203:    */   public void setLongitude(String longitude)
/* 204:    */   {
/* 205:159 */     this.longitude = longitude;
/* 206:    */   }
/* 207:    */   
/* 208:    */   public String getPrecision()
/* 209:    */   {
/* 210:162 */     return this.precision;
/* 211:    */   }
/* 212:    */   
/* 213:    */   public void setPrecision(String precision)
/* 214:    */   {
/* 215:165 */     this.precision = precision;
/* 216:    */   }
/* 217:    */   
/* 218:    */   public String getPicUrl()
/* 219:    */   {
/* 220:168 */     return this.picUrl;
/* 221:    */   }
/* 222:    */   
/* 223:    */   public void setPicUrl(String picUrl)
/* 224:    */   {
/* 225:171 */     this.picUrl = picUrl;
/* 226:    */   }
/* 227:    */   
/* 228:    */   public String getMediaId()
/* 229:    */   {
/* 230:174 */     return this.mediaId;
/* 231:    */   }
/* 232:    */   
/* 233:    */   public void setMediaId(String mediaId)
/* 234:    */   {
/* 235:177 */     this.mediaId = mediaId;
/* 236:    */   }
/* 237:    */   
/* 238:    */   public String getFormat()
/* 239:    */   {
/* 240:180 */     return this.format;
/* 241:    */   }
/* 242:    */   
/* 243:    */   public void setFormat(String format)
/* 244:    */   {
/* 245:183 */     this.format = format;
/* 246:    */   }
/* 247:    */   
/* 248:    */   public String getRecognition()
/* 249:    */   {
/* 250:186 */     return this.recognition;
/* 251:    */   }
/* 252:    */   
/* 253:    */   public void setRecognition(String recognition)
/* 254:    */   {
/* 255:189 */     this.recognition = recognition;
/* 256:    */   }
/* 257:    */   
/* 258:    */   public String getThumbMediaId()
/* 259:    */   {
/* 260:192 */     return this.thumbMediaId;
/* 261:    */   }
/* 262:    */   
/* 263:    */   public void setThumbMediaId(String thumbMediaId)
/* 264:    */   {
/* 265:195 */     this.thumbMediaId = thumbMediaId;
/* 266:    */   }
/* 267:    */   
/* 268:    */   public String getTitle()
/* 269:    */   {
/* 270:198 */     return this.title;
/* 271:    */   }
/* 272:    */   
/* 273:    */   public void setTitle(String title)
/* 274:    */   {
/* 275:201 */     this.title = title;
/* 276:    */   }
/* 277:    */   
/* 278:    */   public String getDescription()
/* 279:    */   {
/* 280:204 */     return this.description;
/* 281:    */   }
/* 282:    */   
/* 283:    */   public void setDescription(String description)
/* 284:    */   {
/* 285:207 */     this.description = description;
/* 286:    */   }
/* 287:    */   
/* 288:    */   public String getUrl()
/* 289:    */   {
/* 290:210 */     return this.url;
/* 291:    */   }
/* 292:    */   
/* 293:    */   public void setUrl(String url)
/* 294:    */   {
/* 295:213 */     this.url = url;
/* 296:    */   }
/* 297:    */   
/* 298:    */   public String getBusinessId()
/* 299:    */   {
/* 300:216 */     return this.businessId;
/* 301:    */   }
/* 302:    */   
/* 303:    */   public void setBusinessId(String businessId)
/* 304:    */   {
/* 305:219 */     this.businessId = businessId;
/* 306:    */   }
/* 307:    */   
/* 308:    */   public String getProcessInstanceId()
/* 309:    */   {
/* 310:222 */     return this.ProcessInstanceId;
/* 311:    */   }
/* 312:    */   
/* 313:    */   public void setProcessInstanceId(String processInstanceId)
/* 314:    */   {
/* 315:225 */     this.ProcessInstanceId = processInstanceId;
/* 316:    */   }
/* 317:    */   
/* 318:    */   public String getBssSendQueue()
/* 319:    */   {
/* 320:228 */     return this.bssSendQueue;
/* 321:    */   }
/* 322:    */   
/* 323:    */   public void setBssSendQueue(String bssSendQueue)
/* 324:    */   {
/* 325:231 */     this.bssSendQueue = bssSendQueue;
/* 326:    */   }
/* 327:    */   
/* 328:    */   public NoticeState getBssType()
/* 329:    */   {
/* 330:234 */     return this.bssType;
/* 331:    */   }
/* 332:    */   
/* 333:    */   public void setBssType(NoticeState bssType)
/* 334:    */   {
/* 335:237 */     this.bssType = bssType;
/* 336:    */   }
/* 337:    */   
/* 338:    */   public String getBssReciveQueue()
/* 339:    */   {
/* 340:240 */     return this.bssReciveQueue;
/* 341:    */   }
/* 342:    */   
/* 343:    */   public void setBssReciveQueue(String bssReciveQueue)
/* 344:    */   {
/* 345:243 */     this.bssReciveQueue = bssReciveQueue;
/* 346:    */   }
/* 347:    */   
/* 348:    */   public String getCompanyId()
/* 349:    */   {
/* 350:247 */     return this.companyId;
/* 351:    */   }
/* 352:    */   
/* 353:    */   public void setCompanyId(String companyId)
/* 354:    */   {
/* 355:250 */     this.companyId = companyId;
/* 356:    */   }
/* 357:    */   
/* 358:    */   public String getMsgMd5()
/* 359:    */   {
/* 360:253 */     return MD5Util.md5(this.msgType + this.content + this.event + this.eventKey, 16);
/* 361:    */   }
/* 362:    */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.entity.WxMessage
 * JD-Core Version:    0.7.0.1
 */