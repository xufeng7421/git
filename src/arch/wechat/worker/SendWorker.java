/*  1:   */ package arch.wechat.worker;
/*  2:   */ 
/*  3:   */ import arch.entity.ReplyEntity;
/*  4:   */ import arch.mq.service.MqService;
/*  5:   */ import arch.properties.QueueProperties;
/*  6:   */ import arch.wechat.api.WeChatAPI;
/*  7:   */ import org.apache.log4j.Logger;
/*  8:   */ 
/*  9:   */ public class SendWorker
/* 10:   */   implements Runnable
/* 11:   */ {
/* 12:12 */   private static Logger log = Logger.getLogger(SendWorker.class);
/* 13:14 */   private ReplyEntity reply = null;
/* 14:   */   
/* 15:   */   public SendWorker(ReplyEntity reply)
/* 16:   */   {
/* 17:17 */     this.reply = reply;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public void run()
/* 21:   */   {
/* 22:21 */     String errcode = WeChatAPI.postMsg(this.reply);
/* 23:22 */     log.debug("errcode->" + errcode);
/* 24:23 */     switch (Integer.parseInt(errcode))
/* 25:   */     {
/* 26:   */     case 0: 
/* 27:25 */       log.debug("发送成功");
/* 28:26 */       break;
/* 29:   */     case 40001: 
/* 30:   */     case 42001: 
/* 31:   */       try
/* 32:   */       {
/* 33:30 */         MqService sender = MqService.getInstance();
/* 34:31 */         sender.sendMessage(this.reply, QueueProperties.getResendQueue());
/* 35:   */       }
/* 36:   */       catch (Exception e)
/* 37:   */       {
/* 38:33 */         log.fatal("accesstoken失效,发送失败,发送到重发队列异常", e);
/* 39:   */       }
/* 40:   */     default: 
/* 41:37 */       log.error("【错误代码：】" + errcode);
/* 42:   */     }
/* 43:   */   }
/* 44:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.wechat.worker.SendWorker
 * JD-Core Version:    0.7.0.1
 */