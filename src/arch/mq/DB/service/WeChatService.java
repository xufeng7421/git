/*   1:    */ package arch.mq.DB.service;
/*   2:    */ 
/*   3:    */ import arch.entity.Item;
/*   4:    */ import arch.entity.PluginEntity;
/*   5:    */ import arch.entity.ReplyEntity;
/*   6:    */ import arch.entity.ReplyMsg;
/*   7:    */ import arch.entity.Vip;
/*   8:    */ import arch.entity.WxMessage;
/*   9:    */ import arch.entity.type.MsgType;
/*  10:    */ import arch.entity.type.ReplyType;
/*  11:    */ import arch.mq.DB.dao.WeChatDao;
/*  12:    */ import arch.mq.DB.dao.impl.WeChatDaoImpl;
/*  13:    */ import arch.plugin.interfaces.MenuPlugin;
/*  14:    */ import arch.plugin.interfaces.ReplyPlugin;
/*  15:    */ import arch.util.AccessTokenUtil;
/*  16:    */ import arch.util.MsgUtil;
/*  17:    */ import java.sql.Connection;
/*  18:    */ import java.util.List;
/*  19:    */ import java.util.Map;
/*  20:    */ import org.apache.log4j.Logger;
/*  21:    */ 
/*  22:    */ public class WeChatService
/*  23:    */ {
/*  24: 28 */   private static Logger log = Logger.getLogger(WeChatService.class);
/*  25: 29 */   private WeChatDao weChatDao = new WeChatDaoImpl();
/*  26:    */   
/*  27:    */   public String findVipIdByOpenId(Connection conn, String openId, String wechatId)
/*  28:    */     throws Exception
/*  29:    */   {
/*  30: 41 */     return this.weChatDao.findVipIdByOpenId(conn, openId, wechatId);
/*  31:    */   }
/*  32:    */   
/*  33:    */   public int updVipSubscribe(Connection conn, String openId, String wechatId, int status)
/*  34:    */     throws Exception
/*  35:    */   {
/*  36: 55 */     return this.weChatDao.updVipSubscribe(conn, openId, wechatId, status);
/*  37:    */   }
/*  38:    */   
/*  39:    */   public String addVip(Connection conn, Vip vip)
/*  40:    */     throws Exception
/*  41:    */   {
/*  42: 66 */     this.weChatDao.addCommunication(conn, vip);
/*  43: 67 */     return this.weChatDao.addVip(conn, vip);
/*  44:    */   }
/*  45:    */   
/*  46:    */   public ReplyMsg findSubScribeReply(Connection conn, String wechatId, String companyId)
/*  47:    */     throws Exception
/*  48:    */   {
/*  49: 80 */     return this.weChatDao.findSubScribeReply(conn, wechatId, companyId);
/*  50:    */   }
/*  51:    */   
/*  52:    */   public ReplyMsg findMenuReply(Connection conn, String eventKey, String wechatId, String companyId)
/*  53:    */     throws Exception
/*  54:    */   {
/*  55: 94 */     return this.weChatDao.findMenuReply(conn, eventKey, wechatId, companyId);
/*  56:    */   }
/*  57:    */   
/*  58:    */   public ReplyEntity getReplyEntity(Connection conn, WxMessage wxMessage, ReplyMsg replyMsg)
/*  59:    */     throws Exception
/*  60:    */   {
/*  61:107 */     ReplyEntity reply = new ReplyEntity(wxMessage);
/*  62:108 */     String content = null;
/*  63:109 */     List<Item> list = null;
/*  64:110 */     Item item = null;
/*  65:111 */     switch (Integer.parseInt(replyMsg.getType()))
/*  66:    */     {
/*  67:    */     case 1: 
/*  68:113 */       reply.setMsgType(ReplyType.TEXT);
/*  69:114 */       content = MsgUtil.parseTextMsg(wxMessage.getOpenId(), replyMsg.getContent());
/*  70:115 */       break;
/*  71:    */     case 2: 
/*  72:117 */       reply.setMsgType(ReplyType.IMAGETEXT);
/*  73:118 */       list = this.weChatDao.findArticleById(conn, wxMessage.getOpenId(), replyMsg);
/*  74:119 */       content = MsgUtil.parseNewsMsg(wxMessage.getOpenId(), list);
/*  75:120 */       break;
/*  76:    */     case 3: 
/*  77:122 */       reply.setMsgType(ReplyType.ACTIVITY);
/*  78:123 */       item = this.weChatDao.findActivityById(conn, wxMessage.getOpenId(), replyMsg);
/*  79:124 */       content = MsgUtil.parseNewsMsg(wxMessage.getOpenId(), item);
/*  80:125 */       break;
/*  81:    */     case 9: 
/*  82:127 */       if (wxMessage.getMsgType() != MsgType.TEXT)
/*  83:    */       {
/*  84:128 */         PluginEntity pluginEntity = this.weChatDao.findPluginMenu(conn, replyMsg.getPluginId(), replyMsg.getWechatId());
/*  85:129 */         if (pluginEntity != null)
/*  86:    */         {
/*  87:130 */           Map<String, String> map = this.weChatDao.findMenuEntityMap(conn, replyMsg.getId(), replyMsg.getWechatId());
/*  88:131 */           map.put("url", pluginEntity.getUrl());
/*  89:132 */           reply.setMsgType(getMessReplyType(pluginEntity.getType()));
/*  90:133 */           Class<?> cla = Class.forName(pluginEntity.getClassName());
/*  91:134 */           MenuPlugin m = (MenuPlugin)cla.newInstance();
/*  92:135 */           content = m.iwork(wxMessage.getOpenId(), map);
/*  93:    */         }
/*  94:    */       }
/*  95:    */       else
/*  96:    */       {
/*  97:138 */         PluginEntity pluginEntity = this.weChatDao.findPluginReply(conn, replyMsg.getPluginId(), replyMsg.getWechatId());
/*  98:139 */         if (pluginEntity != null)
/*  99:    */         {
/* 100:140 */           Map<String, String> map = this.weChatDao.findReplyEntityMap(conn, replyMsg.getId(), replyMsg.getWechatId());
/* 101:141 */           reply.setMsgType(getMessReplyType(pluginEntity.getType()));
/* 102:142 */           Class<?> cla = Class.forName(pluginEntity.getClassName());
/* 103:143 */           ReplyPlugin m = (ReplyPlugin)cla.newInstance();
/* 104:144 */           content = m.ireply(wxMessage.getOpenId(), map);
/* 105:    */         }
/* 106:    */       }
/* 107:147 */       break;
/* 108:    */     case 4: 
/* 109:    */     case 5: 
/* 110:    */     case 6: 
/* 111:    */     case 7: 
/* 112:    */     case 8: 
/* 113:    */     default: 
/* 114:149 */       reply.setMsgType(ReplyType.TEXT);
/* 115:150 */       content = MsgUtil.parseTextMsg(wxMessage.getOpenId(), replyMsg.getContent());
/* 116:    */     }
/* 117:153 */     reply.setContent(content);
/* 118:154 */     reply.setAccessToken(AccessTokenUtil.getAccessToken(wxMessage.getWechatId()));
/* 119:155 */     return reply;
/* 120:    */   }
/* 121:    */   
/* 122:    */   private ReplyType getMessReplyType(String type)
/* 123:    */   {
/* 124:164 */     ReplyType rt = ReplyType.TEXT;
/* 125:165 */     switch (Integer.parseInt(type))
/* 126:    */     {
/* 127:    */     case 1: 
/* 128:167 */       rt = ReplyType.TEXT;
/* 129:168 */       break;
/* 130:    */     case 2: 
/* 131:170 */       rt = ReplyType.IMAGETEXT;
/* 132:171 */       break;
/* 133:    */     case 3: 
/* 134:173 */       rt = ReplyType.ACTIVITY;
/* 135:174 */       break;
/* 136:    */     case 4: 
/* 137:176 */       rt = ReplyType.TEXT;
/* 138:177 */       break;
/* 139:    */     case 8: 
/* 140:179 */       rt = ReplyType.IMAGETEXT;
/* 141:180 */       break;
/* 142:    */     }
/* 143:184 */     return rt;
/* 144:    */   }
/* 145:    */   
/* 146:    */   public ReplyMsg findPerViewReply(Connection conn, String companyId, String wechatId)
/* 147:    */     throws Exception
/* 148:    */   {
/* 149:195 */     return this.weChatDao.findPerViewReply(conn, companyId, wechatId);
/* 150:    */   }
/* 151:    */   
/* 152:    */   public int addSceneLeadin(Connection conn, String key, WxMessage wxMessage)
/* 153:    */     throws Exception
/* 154:    */   {
/* 155:207 */     return this.weChatDao.addSceneLeadin(conn, key, wxMessage);
/* 156:    */   }
/* 157:    */   
/* 158:    */   public int addVipLeadin(Connection conn, String key, WxMessage wxMessage)
/* 159:    */     throws Exception
/* 160:    */   {
/* 161:219 */     return this.weChatDao.addVipLeadin(conn, key, wxMessage);
/* 162:    */   }
/* 163:    */   
/* 164:    */   public int updVipSubscribe(Connection conn, Vip vip)
/* 165:    */     throws Exception
/* 166:    */   {
/* 167:230 */     return this.weChatDao.updVipSubscribe(conn, vip);
/* 168:    */   }
/* 169:    */   
/* 170:    */   public ReplyMsg findAutoReply(Connection conn, String wechatId, String companyId, String keyword)
/* 171:    */     throws Exception
/* 172:    */   {
/* 173:244 */     return this.weChatDao.findAutoReply(conn, wechatId, companyId, keyword);
/* 174:    */   }
/* 175:    */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.mq.DB.service.WeChatService
 * JD-Core Version:    0.7.0.1
 */