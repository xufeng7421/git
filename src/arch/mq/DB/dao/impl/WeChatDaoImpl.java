/*   1:    */ package arch.mq.DB.dao.impl;
/*   2:    */ 
/*   3:    */ import arch.entity.Item;
/*   4:    */ import arch.entity.PluginEntity;
/*   5:    */ import arch.entity.ReplyMsg;
/*   6:    */ import arch.entity.Vip;
/*   7:    */ import arch.entity.WxMessage;
/*   8:    */ import arch.mq.DB.dao.WeChatDao;
/*   9:    */ import arch.properties.UtilProperties;
/*  10:    */ import arch.util.DbcpConnection;
/*  11:    */ import arch.util.DictionaryUtil;
/*  12:    */ import java.sql.Connection;
/*  13:    */ import java.sql.PreparedStatement;
/*  14:    */ import java.sql.ResultSet;
/*  15:    */ import java.sql.SQLException;
/*  16:    */ import java.util.ArrayList;
/*  17:    */ import java.util.HashMap;
/*  18:    */ import java.util.List;
/*  19:    */ import java.util.Map;
/*  20:    */ 
/*  21:    */ public class WeChatDaoImpl
/*  22:    */   implements WeChatDao
/*  23:    */ {
/*  24:    */   public String findVipIdByOpenId(Connection conn, String openId, String wechatId)
/*  25:    */     throws SQLException
/*  26:    */   {
/*  27: 26 */     String vipId = null;
/*  28: 27 */     PreparedStatement ps = null;
/*  29: 28 */     ResultSet rs = null;
/*  30:    */     try
/*  31:    */     {
/*  32: 30 */       String sql = "SELECT VIP_ID FROM U_VIP_INFO WHERE OPEN_ID = ? AND WECHAT_ID = ? ";
/*  33: 31 */       ps = conn.prepareStatement(sql);
/*  34: 32 */       ps.setString(1, openId);
/*  35: 33 */       ps.setString(2, wechatId);
/*  36: 34 */       rs = ps.executeQuery();
/*  37: 35 */       if (rs.next()) {
/*  38: 36 */         vipId = rs.getString("VIP_ID");
/*  39:    */       }
/*  40:    */     }
/*  41:    */     finally
/*  42:    */     {
/*  43: 39 */       DbcpConnection.close(null, ps, rs);
/*  44:    */     }
/*  45: 41 */     return vipId;
/*  46:    */   }
/*  47:    */   
/*  48:    */   public int updVipSubscribe(Connection conn, String openId, String wechatId, int status)
/*  49:    */     throws SQLException
/*  50:    */   {
/*  51: 47 */     int i = 0;
/*  52: 48 */     PreparedStatement ps = null;
/*  53:    */     try
/*  54:    */     {
/*  55: 50 */       String sql = "UPDATE U_VIP_INFO SET SUBSCRIBE = ?,SUBSCRIBE_TIME = NOW() WHERE OPEN_ID = ? AND WECHAT_ID = ? ";
/*  56: 51 */       ps = conn.prepareStatement(sql);
/*  57: 52 */       ps.setInt(1, status);
/*  58: 53 */       ps.setString(2, openId);
/*  59: 54 */       ps.setString(3, wechatId);
/*  60: 55 */       i = ps.executeUpdate();
/*  61:    */     }
/*  62:    */     finally
/*  63:    */     {
/*  64: 57 */       DbcpConnection.close(null, ps, null);
/*  65:    */     }
/*  66: 59 */     return i;
/*  67:    */   }
/*  68:    */   
/*  69:    */   public String addVip(Connection conn, Vip vip)
/*  70:    */     throws SQLException
/*  71:    */   {
/*  72: 64 */     PreparedStatement ps = null;
/*  73: 65 */     ResultSet rs = null;
/*  74: 66 */     String result = null;
/*  75:    */     try
/*  76:    */     {
/*  77: 68 */       String sql = "INSERT INTO U_VIP_INFO(COMPANY_ID,OPEN_ID,CARD_ID,NICKNAME,REALNAME,MOBILE,ADDRESS,ACTIVE_TIME,IS_ACTIVE,PIC_URL,SEX,CITY,COUNTRY,PROVINCE,LANGUAGUE,HEADIMGURL,SUBSCRIBE,SUBSCRIBE_TIME,WECHAT_ID) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,NOW(),?);";
/*  78: 69 */       ps = conn.prepareStatement(sql);
/*  79: 70 */       ps.setString(1, vip.getCompanyId());
/*  80: 71 */       ps.setString(2, vip.getOpenId());
/*  81: 72 */       ps.setString(3, vip.getCardId());
/*  82: 73 */       ps.setString(4, vip.getNickName());
/*  83: 74 */       ps.setString(5, vip.getRealName());
/*  84: 75 */       ps.setString(6, vip.getMobile());
/*  85: 76 */       ps.setString(7, vip.getAddress());
/*  86: 77 */       ps.setString(8, vip.getActiveTime());
/*  87: 78 */       ps.setString(9, "0");
/*  88: 79 */       ps.setString(10, vip.getPicUrl());
/*  89: 80 */       ps.setString(11, vip.getSex());
/*  90: 81 */       ps.setString(12, vip.getCity());
/*  91: 82 */       ps.setString(13, vip.getCountry());
/*  92: 83 */       ps.setString(14, vip.getProvince());
/*  93: 84 */       ps.setString(15, vip.getLanguage());
/*  94: 85 */       ps.setString(16, vip.getHeadimgurl());
/*  95: 86 */       ps.setString(17, vip.getSubscribe());
/*  96: 87 */       ps.setString(18, vip.getWechatId());
/*  97: 88 */       if (ps.executeUpdate() > 0) {
/*  98: 89 */         result = DictionaryUtil.findLastInsertId(conn);
/*  99:    */       }
/* 100:    */     }
/* 101:    */     finally
/* 102:    */     {
/* 103: 92 */       DbcpConnection.close(null, ps, rs);
/* 104:    */     }
/* 105: 94 */     return result;
/* 106:    */   }
/* 107:    */   
/* 108:    */   public ReplyMsg findSubScribeReply(Connection conn, String wechatId, String companyId)
/* 109:    */     throws SQLException
/* 110:    */   {
/* 111:100 */     PreparedStatement ps = null;
/* 112:101 */     ResultSet rs = null;
/* 113:102 */     ReplyMsg reply = null;
/* 114:    */     try
/* 115:    */     {
/* 116:104 */       String sql = "SELECT * FROM S_SUBSCRIBE WHERE COMPANY_ID = ? AND WECHAT_ID = ?";
/* 117:105 */       ps = conn.prepareStatement(sql);
/* 118:106 */       ps.setString(1, companyId);
/* 119:107 */       ps.setString(2, wechatId);
/* 120:108 */       rs = ps.executeQuery();
/* 121:109 */       if (rs.next())
/* 122:    */       {
/* 123:110 */         reply = new ReplyMsg();
/* 124:111 */         reply.setId(rs.getString("SEQ_NO"));
/* 125:112 */         reply.setCompanyId(rs.getString("COMPANY_ID"));
/* 126:113 */         reply.setWechatId(rs.getString("WECHAT_ID"));
/* 127:114 */         reply.setType(rs.getString("TYPE"));
/* 128:115 */         reply.setContent(rs.getString("CONTENT"));
/* 129:    */       }
/* 130:    */     }
/* 131:    */     finally
/* 132:    */     {
/* 133:118 */       DbcpConnection.close(null, ps, rs);
/* 134:    */     }
/* 135:120 */     return reply;
/* 136:    */   }
/* 137:    */   
/* 138:    */   public ReplyMsg findMenuReply(Connection conn, String eventKey, String wechatId, String companyId)
/* 139:    */     throws SQLException
/* 140:    */   {
/* 141:126 */     PreparedStatement ps = null;
/* 142:127 */     ResultSet rs = null;
/* 143:128 */     ReplyMsg reply = null;
/* 144:    */     try
/* 145:    */     {
/* 146:130 */       String sql = "SELECT * FROM S_PUBLISH_MENU WHERE SEQ_NO = ? AND  COMPANY_ID = ? AND WECHAT_ID = ?";
/* 147:131 */       ps = conn.prepareStatement(sql);
/* 148:132 */       ps.setString(1, eventKey);
/* 149:133 */       ps.setString(2, companyId);
/* 150:134 */       ps.setString(3, wechatId);
/* 151:135 */       rs = ps.executeQuery();
/* 152:136 */       if (rs.next())
/* 153:    */       {
/* 154:137 */         reply = new ReplyMsg();
/* 155:138 */         reply.setId(rs.getString("SEQ_NO"));
/* 156:139 */         reply.setCompanyId(rs.getString("COMPANY_ID"));
/* 157:140 */         reply.setWechatId(rs.getString("WECHAT_ID"));
/* 158:141 */         reply.setType(rs.getString("TYPE"));
/* 159:142 */         reply.setContent(rs.getString("CONTENT"));
/* 160:143 */         reply.setPluginId(rs.getString("PLUGIN_ID"));
/* 161:    */       }
/* 162:    */     }
/* 163:    */     finally
/* 164:    */     {
/* 165:146 */       DbcpConnection.close(null, ps, rs);
/* 166:    */     }
/* 167:148 */     return reply;
/* 168:    */   }
/* 169:    */   
/* 170:    */   public Item findActivityById(Connection conn, String openId, ReplyMsg replyMsg)
/* 171:    */     throws SQLException
/* 172:    */   {
/* 173:153 */     Map<String, String> map = findArticleAction(conn, "activity_type");
/* 174:154 */     PreparedStatement ps = null;
/* 175:155 */     ResultSet rs = null;
/* 176:156 */     Item item = null;
/* 177:    */     try
/* 178:    */     {
/* 179:158 */       String sql = "SELECT * FROM A_ACTIVITY WHERE ACTIVE_ID = ? AND COMPANY_ID = ? ";
/* 180:159 */       ps = conn.prepareStatement(sql);
/* 181:160 */       ps.setString(1, replyMsg.getContent());
/* 182:161 */       ps.setString(2, replyMsg.getCompanyId());
/* 183:162 */       rs = ps.executeQuery();
/* 184:163 */       if (rs.next())
/* 185:    */       {
/* 186:164 */         item = new Item();
/* 187:165 */         item.setTitle(rs.getString("ACTIVE_NAME"));
/* 188:166 */         item.setDescription(rs.getString("ABSTRACT"));
/* 189:167 */         item.setPicUrl(UtilProperties.getHost() + rs.getString("MAIN_PIC"));
/* 190:168 */         item.setUrl(getMenuUrl((String)map.get(rs.getString("ACTIVE_TYPE")), rs.getString("ACTIVE_ID"), replyMsg, openId));
/* 191:    */       }
/* 192:    */     }
/* 193:    */     finally
/* 194:    */     {
/* 195:171 */       DbcpConnection.close(null, ps, rs);
/* 196:    */     }
/* 197:173 */     return item;
/* 198:    */   }
/* 199:    */   
/* 200:    */   private String getMenuUrl(String action, String id, ReplyMsg replyMsg, String openId)
/* 201:    */   {
/* 202:177 */     StringBuilder url = new StringBuilder();
/* 203:178 */     url.append(UtilProperties.getHost());
/* 204:179 */     url.append(action);
/* 205:180 */     url.append("?id=");
/* 206:181 */     url.append(id);
/* 207:182 */     url.append("&companyId=");
/* 208:183 */     url.append(replyMsg.getCompanyId());
/* 209:184 */     url.append("&openId=");
/* 210:185 */     url.append(openId);
/* 211:186 */     if ("view".equals(replyMsg.getFlag())) {
/* 212:187 */       url.append("&view=view");
/* 213:    */     }
/* 214:189 */     url.append("#wechat_redirect");
/* 215:190 */     return url.toString();
/* 216:    */   }
/* 217:    */   
/* 218:    */   public Map<String, String> findArticleAction(Connection conn, String paraType)
/* 219:    */     throws SQLException
/* 220:    */   {
/* 221:196 */     Map<String, String> map = new HashMap();
/* 222:197 */     PreparedStatement ps = null;
/* 223:198 */     ResultSet rs = null;
/* 224:    */     try
/* 225:    */     {
/* 226:200 */       String sql = "SELECT * FROM M_DICTIONARY WHERE PARA_TYPE = ? ";
/* 227:201 */       ps = conn.prepareStatement(sql);
/* 228:202 */       ps.setString(1, paraType);
/* 229:203 */       rs = ps.executeQuery();
/* 230:204 */       while (rs.next()) {
/* 231:205 */         map.put(rs.getString("PARA_ID"), rs.getString("PARA_NAME"));
/* 232:    */       }
/* 233:    */     }
/* 234:    */     finally
/* 235:    */     {
/* 236:208 */       DbcpConnection.close(null, ps, rs);
/* 237:    */     }
/* 238:210 */     return map;
/* 239:    */   }
/* 240:    */   
/* 241:    */   public List<Item> findArticleById(Connection conn, String openId, ReplyMsg replyMsg)
/* 242:    */     throws SQLException
/* 243:    */   {
/* 244:215 */     List<Item> list = new ArrayList();
/* 245:216 */     PreparedStatement ps = null;
/* 246:217 */     ResultSet rs = null;
/* 247:    */     try
/* 248:    */     {
/* 249:219 */       String sql = "SELECT * FROM A_MATERIAL WHERE MATERIAL_ID = ? AND COMPANY_ID = ? ";
/* 250:220 */       ps = conn.prepareStatement(sql);
/* 251:221 */       ps.setString(1, replyMsg.getContent());
/* 252:222 */       ps.setString(2, replyMsg.getCompanyId());
/* 253:223 */       rs = ps.executeQuery();
/* 254:224 */       String isMuti = null;
/* 255:225 */       if (rs.next())
/* 256:    */       {
/* 257:226 */         Item item = new Item();
/* 258:227 */         item.setTitle(rs.getString("TITLE"));
/* 259:228 */         item.setDescription(rs.getString("DESCRIPTION"));
/* 260:229 */         item.setPicUrl(UtilProperties.getHost() + rs.getString("PIC_URL"));
/* 261:230 */         item.setUrl(getMenuUrl("msg_article.action", rs.getString("MATERIAL_ID"), replyMsg, openId));
/* 262:231 */         list.add(item);
/* 263:232 */         isMuti = rs.getString("IS_MUTI");
/* 264:    */       }
/* 265:235 */       if ("1".equals(isMuti))
/* 266:    */       {
/* 267:236 */         sql = "SELECT SUB_ID,MATERIAL_TYPE FROM A_MATERIAL_RELATION WHERE  MATERIAL_ID = ?  ORDER BY SUB_ORDER ";
/* 268:237 */         ps = conn.prepareStatement(sql);
/* 269:238 */         ps.setString(1, replyMsg.getContent());
/* 270:239 */         rs = ps.executeQuery();
/* 271:240 */         while (rs.next())
/* 272:    */         {
/* 273:241 */           replyMsg.setContent(rs.getString("SUB_ID"));
/* 274:242 */           if ("1".equals(rs.getString("MATERIAL_TYPE"))) {
/* 275:243 */             list.add(findActivityById(conn, openId, replyMsg));
/* 276:    */           } else {
/* 277:245 */             list.addAll(findArticleById(conn, openId, replyMsg));
/* 278:    */           }
/* 279:    */         }
/* 280:    */       }
/* 281:    */     }
/* 282:    */     finally
/* 283:    */     {
/* 284:250 */       DbcpConnection.close(null, ps, rs);
/* 285:    */     }
/* 286:252 */     return list;
/* 287:    */   }
/* 288:    */   
/* 289:    */   public ReplyMsg findPerViewReply(Connection conn, String companyId, String wechatId)
/* 290:    */     throws SQLException
/* 291:    */   {
/* 292:258 */     PreparedStatement ps = null;
/* 293:259 */     ResultSet rs = null;
/* 294:260 */     ReplyMsg reply = null;
/* 295:    */     try
/* 296:    */     {
/* 297:262 */       String sql = "SELECT * FROM S_VIEW WHERE COMPANY_ID = ? AND WECHAT_ID = ?  ";
/* 298:263 */       ps = conn.prepareStatement(sql);
/* 299:264 */       ps.setString(1, companyId);
/* 300:265 */       ps.setString(2, wechatId);
/* 301:266 */       rs = ps.executeQuery();
/* 302:267 */       if (rs.next())
/* 303:    */       {
/* 304:268 */         reply = new ReplyMsg();
/* 305:269 */         reply.setId(rs.getString("SEQ_NO"));
/* 306:270 */         reply.setCompanyId(rs.getString("COMPANY_ID"));
/* 307:271 */         reply.setType(rs.getString("TYPE"));
/* 308:272 */         reply.setContent(rs.getString("MATERIAL_ID"));
/* 309:273 */         reply.setFlag("view");
/* 310:    */       }
/* 311:    */     }
/* 312:    */     finally
/* 313:    */     {
/* 314:276 */       DbcpConnection.close(null, ps, rs);
/* 315:    */     }
/* 316:278 */     return reply;
/* 317:    */   }
/* 318:    */   
/* 319:    */   public int addSceneLeadin(Connection conn, String key, WxMessage wxMessage)
/* 320:    */     throws SQLException
/* 321:    */   {
/* 322:284 */     PreparedStatement ps = null;
/* 323:285 */     ResultSet rs = null;
/* 324:    */     try
/* 325:    */     {
/* 326:287 */       String sql = "SELECT SEQ_NO FROM S_SCENE_LEADIN WHERE OPEN_ID = ? AND SCENE_ID= ? AND WECHAT_ID = ? AND COMPANY_ID = ? ";
/* 327:288 */       ps = conn.prepareStatement(sql);
/* 328:289 */       ps.setString(1, wxMessage.getOpenId());
/* 329:290 */       ps.setString(2, key);
/* 330:291 */       ps.setString(3, wxMessage.getWechatId());
/* 331:292 */       ps.setString(4, wxMessage.getCompanyId());
/* 332:293 */       rs = ps.executeQuery();
/* 333:294 */       if (!rs.next())
/* 334:    */       {
/* 335:295 */         sql = "INSERT INTO S_SCENE_LEADIN (COMPANY_ID,WECHAT_ID,OPEN_ID,SCENE_ID,C_DATE) VALUES (?,?,?,?,SYSDATE()) ";
/* 336:296 */         ps = conn.prepareStatement(sql);
/* 337:297 */         ps.setString(1, wxMessage.getCompanyId());
/* 338:298 */         ps.setString(2, wxMessage.getWechatId());
/* 339:299 */         ps.setString(3, wxMessage.getOpenId());
/* 340:300 */         ps.setString(4, key);
/* 341:301 */         int i = ps.executeUpdate();return i;
/* 342:    */       }
/* 343:    */     }
/* 344:    */     finally
/* 345:    */     {
/* 346:304 */       DbcpConnection.close(null, ps, rs);
/* 347:    */     }
/* 348:306 */     return 0;
/* 349:    */   }
/* 350:    */   
/* 351:    */   public int addVipLeadin(Connection conn, String key, WxMessage wxMessage)
/* 352:    */     throws SQLException
/* 353:    */   {
/* 354:312 */     PreparedStatement ps = null;
/* 355:313 */     ResultSet rs = null;
/* 356:    */     try
/* 357:    */     {
/* 358:315 */       String sql = "SELECT SEQ_NO FROM S_VIP_LEADIN WHERE OPEN_ID = ? AND VIP_ID= ? AND WECHAT_ID = ? AND COMPANY_ID = ? ";
/* 359:316 */       ps = conn.prepareStatement(sql);
/* 360:317 */       ps.setString(1, wxMessage.getOpenId());
/* 361:318 */       ps.setString(2, key);
/* 362:319 */       ps.setString(3, wxMessage.getWechatId());
/* 363:320 */       ps.setString(4, wxMessage.getCompanyId());
/* 364:321 */       rs = ps.executeQuery();
/* 365:322 */       if (!rs.next())
/* 366:    */       {
/* 367:323 */         sql = "INSERT INTO S_VIP_LEADIN (COMPANY_ID,WECHAT_ID,OPEN_ID,VIP_ID,C_DATE) VALUES (?,?,?,?,SYSDATE()) ";
/* 368:324 */         ps = conn.prepareStatement(sql);
/* 369:325 */         ps.setString(1, wxMessage.getCompanyId());
/* 370:326 */         ps.setString(2, wxMessage.getWechatId());
/* 371:327 */         ps.setString(3, wxMessage.getOpenId());
/* 372:328 */         ps.setString(4, key);
/* 373:329 */         int i = ps.executeUpdate();return i;
/* 374:    */       }
/* 375:    */     }
/* 376:    */     finally
/* 377:    */     {
/* 378:332 */       DbcpConnection.close(null, ps, rs);
/* 379:    */     }
/* 380:334 */     return 0;
/* 381:    */   }
/* 382:    */   
/* 383:    */   public int updVipSubscribe(Connection conn, Vip vip)
/* 384:    */     throws SQLException
/* 385:    */   {
/* 386:339 */     int i = 0;
/* 387:340 */     PreparedStatement ps = null;
/* 388:    */     try
/* 389:    */     {
/* 390:342 */       String sql = "UPDATE U_VIP_INFO SET SUBSCRIBE = ?,NICKNAME= ?,HEADIMGURL=?,SUBSCRIBE_TIME=NOW() WHERE OPEN_ID = ? AND WECHAT_ID = ? ";
/* 391:343 */       ps = conn.prepareStatement(sql);
/* 392:344 */       ps.setString(1, vip.getSubscribe());
/* 393:345 */       ps.setString(2, vip.getNickName());
/* 394:346 */       ps.setString(3, vip.getHeadimgurl());
/* 395:347 */       ps.setString(4, vip.getOpenId());
/* 396:348 */       ps.setString(5, vip.getWechatId());
/* 397:349 */       i = ps.executeUpdate();
/* 398:    */     }
/* 399:    */     finally
/* 400:    */     {
/* 401:351 */       DbcpConnection.close(null, ps, null);
/* 402:    */     }
/* 403:353 */     return i;
/* 404:    */   }
/* 405:    */   
/* 406:    */   public ReplyMsg findAutoReply(Connection conn, String wechatId, String companyId, String keyword)
/* 407:    */     throws SQLException
/* 408:    */   {
/* 409:359 */     PreparedStatement ps = null;
/* 410:360 */     ResultSet rs = null;
/* 411:361 */     ReplyMsg replyMsg = null;
/* 412:    */     try
/* 413:    */     {
/* 414:363 */       String sql = "SELECT * FROM S_REPLY WHERE COMPANY_ID = ? AND WECHAT_ID=? AND KEYWORDS=? ";
/* 415:364 */       ps = conn.prepareStatement(sql);
/* 416:365 */       ps.setString(1, companyId);
/* 417:366 */       ps.setString(2, wechatId);
/* 418:367 */       ps.setString(3, keyword);
/* 419:368 */       rs = ps.executeQuery();
/* 420:369 */       if (rs.next())
/* 421:    */       {
/* 422:370 */         replyMsg = new ReplyMsg();
/* 423:371 */         replyMsg.setCompanyId(companyId);
/* 424:372 */         replyMsg.setWechatId(wechatId);
/* 425:373 */         replyMsg.setType(rs.getString("TYPE"));
/* 426:374 */         replyMsg.setContent(rs.getString("CONTENT"));
/* 427:375 */         replyMsg.setPluginId(rs.getString("PLUGIN_ID"));
/* 428:    */       }
/* 429:    */     }
/* 430:    */     finally
/* 431:    */     {
/* 432:378 */       DbcpConnection.close(null, ps, rs);
/* 433:    */     }
/* 434:380 */     return replyMsg;
/* 435:    */   }
/* 436:    */   
/* 437:    */   public Map<String, String> findMenuEntityMap(Connection conn, String menuId, String wechatId)
/* 438:    */     throws SQLException
/* 439:    */   {
/* 440:386 */     PreparedStatement ps = null;
/* 441:387 */     ResultSet rs = null;
/* 442:388 */     Map<String, String> map = new HashMap();
/* 443:389 */     map.put("wechatId", wechatId);
/* 444:    */     try
/* 445:    */     {
/* 446:391 */       String sql = "SELECT `KEY`,`VALUE` FROM S_MENU_ENTITY WHERE MENU_ID = ? AND WECHAT_ID = ?";
/* 447:392 */       ps = conn.prepareStatement(sql);
/* 448:393 */       ps.setString(1, menuId);
/* 449:394 */       ps.setString(2, wechatId);
/* 450:395 */       rs = ps.executeQuery();
/* 451:396 */       while (rs.next()) {
/* 452:397 */         map.put(rs.getString("KEY"), rs.getString("VALUE"));
/* 453:    */       }
/* 454:    */     }
/* 455:    */     finally
/* 456:    */     {
/* 457:400 */       DbcpConnection.close(null, ps, rs);
/* 458:    */     }
/* 459:402 */     return map;
/* 460:    */   }
/* 461:    */   
/* 462:    */   public PluginEntity findPluginMenu(Connection conn, String pluginId, String wechatId)
/* 463:    */     throws SQLException
/* 464:    */   {
/* 465:408 */     PreparedStatement ps = null;
/* 466:409 */     ResultSet rs = null;
/* 467:410 */     PluginEntity plEntity = null;
/* 468:    */     try
/* 469:    */     {
/* 470:412 */       String sql = "SELECT P.CLASS_NAME,P.TYPE,P.URL FROM `S_MENU_PLUGIN` P WHERE P.SEQ_NO= ? AND P.WECHAT_ID = ?";
/* 471:413 */       ps = conn.prepareStatement(sql);
/* 472:414 */       ps.setString(1, pluginId);
/* 473:415 */       ps.setString(2, wechatId);
/* 474:416 */       rs = ps.executeQuery();
/* 475:417 */       if (rs.next())
/* 476:    */       {
/* 477:418 */         plEntity = new PluginEntity();
/* 478:419 */         plEntity.setClassName(rs.getString("CLASS_NAME"));
/* 479:420 */         plEntity.setType(rs.getString("TYPE"));
/* 480:421 */         plEntity.setUrl(rs.getString("URL"));
/* 481:    */       }
/* 482:    */     }
/* 483:    */     finally
/* 484:    */     {
/* 485:424 */       DbcpConnection.close(null, ps, rs);
/* 486:    */     }
/* 487:426 */     return plEntity;
/* 488:    */   }
/* 489:    */   
/* 490:    */   public int addCommunication(Connection conn, Vip vip)
/* 491:    */     throws SQLException
/* 492:    */   {
/* 493:431 */     int i = 0;
/* 494:432 */     PreparedStatement ps = null;
/* 495:    */     try
/* 496:    */     {
/* 497:434 */       String sql = "INSERT INTO S_COMMUNICATION (COMPANY_ID,WECHAT_ID,OPEN_ID,COMMUTY_TIME) VALUES (?,?,?,SYSDATE())";
/* 498:435 */       ps = conn.prepareStatement(sql);
/* 499:436 */       ps.setString(1, vip.getCompanyId());
/* 500:437 */       ps.setString(2, vip.getWechatId());
/* 501:438 */       ps.setString(3, vip.getOpenId());
/* 502:439 */       i = ps.executeUpdate();
/* 503:    */     }
/* 504:    */     finally
/* 505:    */     {
/* 506:441 */       DbcpConnection.close(null, ps, null);
/* 507:    */     }
/* 508:443 */     return i;
/* 509:    */   }
/* 510:    */   
/* 511:    */   public PluginEntity findPluginReply(Connection conn, String pluginId, String wechatId)
/* 512:    */     throws SQLException
/* 513:    */   {
/* 514:449 */     PreparedStatement ps = null;
/* 515:450 */     ResultSet rs = null;
/* 516:451 */     PluginEntity plEntity = null;
/* 517:    */     try
/* 518:    */     {
/* 519:453 */       String sql = "SELECT P.CLASS_NAME,P.TYPE FROM `S_REPLY_PLUGIN` P WHERE P.SEQ_NO= ? AND P.WECHAT_ID = ?";
/* 520:454 */       ps = conn.prepareStatement(sql);
/* 521:455 */       ps.setString(1, pluginId);
/* 522:456 */       ps.setString(2, wechatId);
/* 523:457 */       rs = ps.executeQuery();
/* 524:458 */       if (rs.next())
/* 525:    */       {
/* 526:459 */         plEntity = new PluginEntity();
/* 527:460 */         plEntity.setClassName(rs.getString("CLASS_NAME"));
/* 528:461 */         plEntity.setType(rs.getString("TYPE"));
/* 529:    */       }
/* 530:    */     }
/* 531:    */     finally
/* 532:    */     {
/* 533:464 */       DbcpConnection.close(null, ps, rs);
/* 534:    */     }
/* 535:466 */     return plEntity;
/* 536:    */   }
/* 537:    */   
/* 538:    */   public Map<String, String> findReplyEntityMap(Connection conn, String id, String wechatId)
/* 539:    */     throws SQLException
/* 540:    */   {
/* 541:472 */     PreparedStatement ps = null;
/* 542:473 */     ResultSet rs = null;
/* 543:474 */     Map<String, String> map = new HashMap();
/* 544:475 */     map.put("wechatId", wechatId);
/* 545:    */     try
/* 546:    */     {
/* 547:477 */       String sql = "SELECT `KEY`,`VALUE` FROM S_REPLY_ENTITY WHERE REPLY_ID = ? AND WECHAT_ID = ?";
/* 548:478 */       ps = conn.prepareStatement(sql);
/* 549:479 */       ps.setString(1, id);
/* 550:480 */       ps.setString(2, wechatId);
/* 551:481 */       rs = ps.executeQuery();
/* 552:482 */       while (rs.next()) {
/* 553:483 */         map.put(rs.getString("KEY"), rs.getString("VALUE"));
/* 554:    */       }
/* 555:    */     }
/* 556:    */     finally
/* 557:    */     {
/* 558:486 */       DbcpConnection.close(null, ps, rs);
/* 559:    */     }
/* 560:488 */     return map;
/* 561:    */   }
/* 562:    */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.mq.DB.dao.impl.WeChatDaoImpl
 * JD-Core Version:    0.7.0.1
 */