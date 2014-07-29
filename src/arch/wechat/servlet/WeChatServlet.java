/*   1:    */ package arch.wechat.servlet;
/*   2:    */ 
/*   3:    */ import arch.entity.WxMessage;
/*   4:    */ import arch.entity.type.EventType;
/*   5:    */ import arch.entity.type.MsgType;
/*   6:    */ import arch.mq.service.MqService;
/*   7:    */ import arch.properties.QueueProperties;
/*   8:    */ import arch.properties.UtilProperties;
/*   9:    */ import arch.util.DbcpConnection;
/*  10:    */ import arch.util.MD5Util;
/*  11:    */ import arch.util.XmlUtil;
/*  12:    */ import arch.wechat.DB.service.WxService;
/*  13:    */ import arch.wechat.cache.Cache;
/*  14:    */ import arch.wechat.cache.CacheManager;
/*  15:    */ import java.io.PrintWriter;
/*  16:    */ import java.security.MessageDigest;
/*  17:    */ import java.security.NoSuchAlgorithmException;
/*  18:    */ import java.sql.Connection;
/*  19:    */ import java.util.Arrays;
/*  20:    */ import java.util.Date;
/*  21:    */ import javax.servlet.http.HttpServlet;
/*  22:    */ import javax.servlet.http.HttpServletRequest;
/*  23:    */ import javax.servlet.http.HttpServletResponse;
/*  24:    */ import org.apache.log4j.Logger;
/*  25:    */ 
/*  26:    */ public class WeChatServlet
/*  27:    */   extends HttpServlet
/*  28:    */ {
/*  29:    */   private static final long serialVersionUID = 1L;
/*  30: 34 */   private Logger log = Logger.getLogger(WeChatServlet.class);
/*  31: 35 */   private WxService wxService = new WxService();
/*  32:    */   
/*  33:    */   protected void doGet(HttpServletRequest request, HttpServletResponse response)
/*  34:    */   {
/*  35: 40 */     PrintWriter pw = null;
/*  36:    */     try
/*  37:    */     {
/*  38: 43 */       pw = response.getWriter();
/*  39: 44 */       if (checkSignature(request))
/*  40:    */       {
/*  41: 45 */         String echostr = request.getParameter("echostr");
/*  42: 46 */         pw.print(echostr);
/*  43:    */       }
/*  44:    */     }
/*  45:    */     catch (Exception e)
/*  46:    */     {
/*  47: 49 */       this.log.fatal("验证URL异常", e);
/*  48:    */     }
/*  49:    */     finally
/*  50:    */     {
/*  51: 51 */       pw.flush();
/*  52: 52 */       arch.util.IOUtils.closeIO(pw, this.log);
/*  53:    */     }
/*  54:    */   }
/*  55:    */   
/*  56:    */   protected void doPost(HttpServletRequest request, HttpServletResponse response)
/*  57:    */   {
/*  58: 59 */     PrintWriter pw = null;
/*  59: 60 */     Connection conn = null;
/*  60:    */     try
/*  61:    */     {
/*  62: 62 */       response.setContentType("text/xml;charset=UTF-8");
/*  63: 63 */       pw = response.getWriter();
/*  64: 64 */       if (checkSignature(request))
/*  65:    */       {
/*  66: 65 */         String wechatId = getWeChatId(request);
/*  67: 66 */         conn = DbcpConnection.getConnection();
/*  68: 67 */         String companyId = this.wxService.findCompanyIdByWeChatId(conn, wechatId);
/*  69: 68 */         if (companyId == null) {
/*  70: 69 */           return;
/*  71:    */         }
/*  72: 71 */         String wxMsgXml = org.apache.commons.io.IOUtils.toString(request.getInputStream(), "utf-8");
/*  73: 72 */         this.log.info(wxMsgXml);
/*  74: 73 */         WxMessage wxMessage = XmlUtil.parseWxMessage(wxMsgXml);
/*  75: 74 */         wxMessage.setWechatId(wechatId);
/*  76: 75 */         wxMessage.setCompanyId(companyId);
/*  77:    */         
/*  78: 77 */         this.wxService.updCommuication(wxMessage, conn);
/*  79: 78 */         if (isRepeat(wxMessage)) {
/*  80: 79 */           return;
/*  81:    */         }
/*  82: 82 */         String que = getQueue(wxMessage);
/*  83: 83 */         if (que != null)
/*  84:    */         {
/*  85: 84 */           MqService sender = MqService.getInstance();
/*  86: 85 */           sender.sendMessage(wxMessage, que);
/*  87:    */           
/*  88: 87 */           this.wxService.addWxMessage(wxMessage, conn);
/*  89:    */         }
/*  90: 89 */         conn.commit();
/*  91:    */       }
/*  92:    */     }
/*  93:    */     catch (Exception e)
/*  94:    */     {
/*  95: 92 */       this.log.fatal("消息处理异常", e);
/*  96:    */     }
/*  97:    */     finally
/*  98:    */     {
/*  99: 94 */       pw.flush();
/* 100: 95 */       arch.util.IOUtils.closeIO(pw, this.log);
/* 101: 96 */       DbcpConnection.close(conn, null, null);
/* 102:    */     }
/* 103:    */   }
/* 104:    */   
/* 105:    */   private String getQueue(WxMessage wxMessage)
/* 106:    */   {
/* 107:111 */     String que = null;
/* 108:112 */     MsgType msgType = wxMessage.getMsgType();
/* 109:113 */     if (MsgType.EVENT.equals(msgType))
/* 110:    */     {
/* 111:114 */       EventType event = wxMessage.getEvent();
/* 112:115 */       if (EventType.LOCATION.equals(event)) {
/* 113:117 */         return null;
/* 114:    */       }
/* 115:    */     }
/* 116:120 */     String openId = wxMessage.getOpenId();
/* 117:121 */     if (CacheManager.getCacheFlag(openId))
/* 118:    */     {
/* 119:123 */       if (UtilProperties.getServiceFalg().equals(CacheManager.getCache(openId).getBusinessId()))
/* 120:    */       {
/* 121:125 */         que = QueueProperties.getServiceReviceQueue();
/* 122:    */       }
/* 123:    */       else
/* 124:    */       {
/* 125:127 */         if (MsgType.EVENT.equals(msgType))
/* 126:    */         {
/* 127:129 */           CacheManager.clearCache(wxMessage.getOpenId());
/* 128:130 */           return getEventQueue(wxMessage.getEvent());
/* 129:    */         }
/* 130:132 */         wxMessage.setBusinessId(CacheManager.getCache(openId).getBusinessId());
/* 131:133 */         que = QueueProperties.getBusinessQueue();
/* 132:    */       }
/* 133:137 */       CacheManager.updCache(openId);
/* 134:    */     }
/* 135:    */     else
/* 136:    */     {
/* 137:139 */       que = getMsgQueue(wxMessage);
/* 138:    */     }
/* 139:141 */     return que;
/* 140:    */   }
/* 141:    */   
/* 142:    */   private boolean isRepeat(WxMessage wxMessage)
/* 143:    */     throws Exception
/* 144:    */   {
/* 145:150 */     boolean flag = false;
/* 146:151 */     String msgId = wxMessage.getMsgMd5();
/* 147:152 */     String openId = wxMessage.getOpenId();
/* 148:153 */     if (CacheManager.getMsgFlag(openId))
/* 149:    */     {
/* 150:155 */       Cache cache = CacheManager.getMsg(openId);
/* 151:156 */       if (new Date().getTime() - cache.getDate().longValue() > 2000L)
/* 152:    */       {
/* 153:157 */         this.log.info("re_add_cache");
/* 154:158 */         CacheManager.setMsgCache(openId, msgId);
/* 155:    */       }
/* 156:    */       else
/* 157:    */       {
/* 158:160 */         this.log.info("ignore");
/* 159:161 */         flag = true;
/* 160:    */       }
/* 161:    */     }
/* 162:    */     else
/* 163:    */     {
/* 164:164 */       this.log.info("add_cache");
/* 165:165 */       CacheManager.setMsgCache(openId, msgId);
/* 166:    */     }
/* 167:167 */     return flag;
/* 168:    */   }
/* 169:    */   
/* 170:    */   private String getMsgQueue(WxMessage wxMessage)
/* 171:    */   {
/* 172:176 */     String que = null;
/* 173:177 */     switch (wxMessage.getMsgType())
/* 174:    */     {
/* 175:    */     case EVENT: 
/* 176:179 */       que = QueueProperties.getTextQueue();
/* 177:180 */       break;
/* 178:    */     case LINK: 
/* 179:182 */       que = QueueProperties.getLocationQueue();
/* 180:183 */       break;
/* 181:    */     case IMAGE: 
/* 182:185 */       que = getEventQueue(wxMessage.getEvent());
/* 183:186 */       break;
/* 184:    */     }
/* 185:190 */     return que;
/* 186:    */   }
/* 187:    */   
/* 188:    */   private String getEventQueue(EventType event)
/* 189:    */   {
/* 190:200 */     String que = null;
/* 191:201 */     switch (event)
/* 192:    */     {
/* 193:    */     case SUBSCRIBE: 
/* 194:203 */       que = QueueProperties.getEventClickQueue();
/* 195:204 */       break;
/* 196:    */     case UNSUBSCRIBE: 
/* 197:206 */       que = QueueProperties.getEventScanQueue();
/* 198:207 */       break;
/* 199:    */     case LOCATION: 
/* 200:209 */       que = QueueProperties.getEventSubscribeQueue();
/* 201:210 */       break;
/* 202:    */     case SCAN: 
/* 203:212 */       que = QueueProperties.getEventUnSubscribeQueue();
/* 204:213 */       break;
/* 205:    */     }
/* 206:217 */     return que;
/* 207:    */   }
/* 208:    */   
/* 209:    */   private String getWeChatId(HttpServletRequest request)
/* 210:    */   {
/* 211:226 */     StringBuffer url = new StringBuffer(request.getRequestURL());
/* 212:227 */     String api = UtilProperties.getApi();
/* 213:228 */     url.delete(0, url.lastIndexOf(api) + api.length());
/* 214:229 */     return url.substring(0, url.indexOf("/"));
/* 215:    */   }
/* 216:    */   
/* 217:    */   private boolean checkSignature(HttpServletRequest request)
/* 218:    */   {
/* 219:    */     try
/* 220:    */     {
/* 221:237 */       String signature = request.getParameter("signature");
/* 222:238 */       String timestamp = request.getParameter("timestamp");
/* 223:239 */       String nonce = request.getParameter("nonce");
/* 224:240 */       StringBuffer url = new StringBuffer(request.getRequestURL());
/* 225:241 */       String api = UtilProperties.getApi();
/* 226:242 */       url.delete(0, url.lastIndexOf(api) + api.length());
/* 227:243 */       String identity = url.substring(url.indexOf("/") + 1);
/* 228:244 */       String TOKEN = MD5Util.md5(UtilProperties.getToken() + identity, 16);
/* 229:245 */       String[] ArrTmp = { TOKEN, timestamp, nonce };
/* 230:246 */       Arrays.sort(ArrTmp);
/* 231:247 */       StringBuffer sb = new StringBuffer();
/* 232:248 */       for (int i = 0; i < ArrTmp.length; i++) {
/* 233:249 */         sb.append(ArrTmp[i]);
/* 234:    */       }
/* 235:251 */       String pwd = Encrypt(sb.toString());
/* 236:252 */       if (pwd.equals(signature)) {
/* 237:253 */         return true;
/* 238:    */       }
/* 239:    */     }
/* 240:    */     catch (Exception e)
/* 241:    */     {
/* 242:256 */       this.log.fatal("验证签名异常", e);
/* 243:    */     }
/* 244:258 */     return false;
/* 245:    */   }
/* 246:    */   
/* 247:    */   private String Encrypt(String strSrc)
/* 248:    */     throws NoSuchAlgorithmException
/* 249:    */   {
/* 250:263 */     byte[] bt = strSrc.getBytes();
/* 251:264 */     MessageDigest md = MessageDigest.getInstance("SHA-1");
/* 252:265 */     md.update(bt);
/* 253:266 */     String strDes = bytes2Hex(md.digest());
/* 254:267 */     return strDes;
/* 255:    */   }
/* 256:    */   
/* 257:    */   private String bytes2Hex(byte[] bts)
/* 258:    */   {
/* 259:271 */     String des = "";
/* 260:272 */     String tmp = null;
/* 261:273 */     for (int i = 0; i < bts.length; i++)
/* 262:    */     {
/* 263:274 */       tmp = Integer.toHexString(bts[i] & 0xFF);
/* 264:275 */       if (tmp.length() == 1) {
/* 265:276 */         des = des + "0";
/* 266:    */       }
/* 267:278 */       des = des + tmp;
/* 268:    */     }
/* 269:280 */     return des;
/* 270:    */   }
/* 271:    */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.wechat.servlet.WeChatServlet
 * JD-Core Version:    0.7.0.1
 */