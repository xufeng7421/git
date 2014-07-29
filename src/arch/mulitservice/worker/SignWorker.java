/*  1:   */ package arch.mulitservice.worker;
/*  2:   */ 
/*  3:   */ import arch.entity.type.NoticeState;
/*  4:   */ import arch.mulitservice.entity.Sign;
/*  5:   */ import arch.mulitservice.execute.ChatExecute;
/*  6:   */ import arch.workerBase.WorkerBase;
/*  7:   */ import org.apache.log4j.Logger;
/*  8:   */ 
/*  9:   */ public class SignWorker
/* 10:   */   extends WorkerBase<Sign>
/* 11:   */   implements Runnable
/* 12:   */ {
/* 13:18 */   private static Logger log = Logger.getLogger(SignWorker.class);
/* 14:   */   
/* 15:   */   public SignWorker(Sign sign)
/* 16:   */   {
/* 17:21 */     super(sign);
/* 18:   */   }
/* 19:   */   
/* 20:   */   public void run()
/* 21:   */   {
/* 22:27 */     String wechatId = ((Sign)this.wxMessage).getWechatId();
/* 23:28 */     String serviceId = ((Sign)this.wxMessage).getServiceId();
/* 24:29 */     if (((Sign)this.wxMessage).getType() == NoticeState.START)
/* 25:   */     {
/* 26:30 */       log.debug("客服签到 - " + wechatId + " - " + serviceId);
/* 27:31 */       ChatExecute.addService(wechatId, serviceId);
/* 28:   */     }
/* 29:32 */     else if (((Sign)this.wxMessage).getType() == NoticeState.END)
/* 30:   */     {
/* 31:33 */       log.debug("客服结束服务 - " + wechatId + " - " + serviceId);
/* 32:34 */       ChatExecute.removeService(wechatId, serviceId);
/* 33:   */     }
/* 34:   */     else
/* 35:   */     {
/* 36:36 */       log.error("签到  消息类型错误");
/* 37:   */     }
/* 38:   */   }
/* 39:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.mulitservice.worker.SignWorker
 * JD-Core Version:    0.7.0.1
 */