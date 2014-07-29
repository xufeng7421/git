/*  1:   */ package arch.wechat.DB.service;
/*  2:   */ 
/*  3:   */ import arch.entity.WxMessage;
/*  4:   */ import arch.entity.type.EventType;
/*  5:   */ import arch.wechat.DB.dao.WxDao;
/*  6:   */ import arch.wechat.DB.dao.impl.WxDaoImpl;
/*  7:   */ import java.sql.Connection;
/*  8:   */ 
/*  9:   */ public class WxService
/* 10:   */ {
/* 11:11 */   private WxDao wxDao = new WxDaoImpl();
/* 12:   */   
/* 13:   */   public String findCompanyIdByWeChatId(Connection conn, String wechatId)
/* 14:   */     throws Exception
/* 15:   */   {
/* 16:21 */     return this.wxDao.findCompanyIdByWeChatId(conn, wechatId);
/* 17:   */   }
/* 18:   */   
/* 19:   */   public int updCommuication(WxMessage wxMessage, Connection conn)
/* 20:   */     throws Exception
/* 21:   */   {
/* 22:32 */     if (EventType.LOCATION == wxMessage.getEvent()) {
/* 23:33 */       return this.wxDao.updLocation(wxMessage, conn);
/* 24:   */     }
/* 25:35 */     return this.wxDao.updCommuication(wxMessage, conn);
/* 26:   */   }
/* 27:   */   
/* 28:   */   public int addWxMessage(WxMessage wxMessage, Connection conn)
/* 29:   */     throws Exception
/* 30:   */   {
/* 31:46 */     return this.wxDao.addWxMessage(wxMessage, conn);
/* 32:   */   }
/* 33:   */   
/* 34:   */   public String findVipIdByOpenId(Connection conn, String openId, String wechatId)
/* 35:   */     throws Exception
/* 36:   */   {
/* 37:58 */     return this.wxDao.findVipIdByOpenId(conn, openId, wechatId);
/* 38:   */   }
/* 39:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.wechat.DB.service.WxService
 * JD-Core Version:    0.7.0.1
 */