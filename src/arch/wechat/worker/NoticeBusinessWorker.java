/*  1:   */ package arch.wechat.worker;
/*  2:   */ 
/*  3:   */ import arch.entity.NoticeBusiness;
/*  4:   */ import arch.entity.type.NoticeState;
/*  5:   */ import arch.properties.UtilProperties;
/*  6:   */ import arch.wechat.cache.CacheManager;
/*  7:   */ import org.apache.log4j.Logger;
/*  8:   */ 
/*  9:   */ public class NoticeBusinessWorker
/* 10:   */   implements Runnable
/* 11:   */ {
/* 12:11 */   private static Logger log = Logger.getLogger(NoticeBusinessWorker.class);
/* 13:13 */   private NoticeBusiness message = null;
/* 14:   */   
/* 15:   */   public NoticeBusinessWorker(NoticeBusiness message)
/* 16:   */   {
/* 17:16 */     this.message = message;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public void run()
/* 21:   */   {
/* 22:20 */     if (NoticeState.START == this.message.getState())
/* 23:   */     {
/* 24:21 */       CacheManager.setCache(this.message.getOpenId(), this.message.getBusinessId());
/* 25:22 */       log.info(this.message.getOpenId() + "加入业务路由");
/* 26:   */     }
/* 27:23 */     else if (NoticeState.END == this.message.getState())
/* 28:   */     {
/* 29:24 */       CacheManager.clearCache(this.message.getOpenId());
/* 30:25 */       log.info(this.message.getOpenId() + "移除业务路由");
/* 31:   */     }
/* 32:26 */     else if (NoticeState.SERVICE == this.message.getState())
/* 33:   */     {
/* 34:27 */       CacheManager.setCache(this.message.getOpenId(), UtilProperties.getServiceFalg());
/* 35:28 */       log.info(this.message.getOpenId() + "加入业务路由");
/* 36:   */     }
/* 37:   */   }
/* 38:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.wechat.worker.NoticeBusinessWorker
 * JD-Core Version:    0.7.0.1
 */