/*  1:   */ package arch.mulitservice.worker;
/*  2:   */ 
/*  3:   */ import arch.entity.ReplyEntity;
/*  4:   */ import arch.entity.WxMessage;
/*  5:   */ import arch.mq.service.MqService;
/*  6:   */ import arch.mulitservice.execute.ChatExecute;
/*  7:   */ import arch.properties.QueueProperties;
/*  8:   */ import arch.terminology.Terminology;
/*  9:   */ import arch.workerBase.WorkerBase;
/* 10:   */ import org.apache.log4j.Logger;
/* 11:   */ 
/* 12:   */ public class ChatWorker
/* 13:   */   extends WorkerBase<WxMessage>
/* 14:   */   implements Runnable
/* 15:   */ {
/* 16:19 */   private Logger log = Logger.getLogger(ChatWorker.class);
/* 17:   */   
/* 18:   */   public ChatWorker(WxMessage wxMessage)
/* 19:   */   {
/* 20:22 */     super(wxMessage);
/* 21:   */   }
/* 22:   */   
/* 23:   */   public void run()
/* 24:   */   {
/* 25:27 */     String openId = ((WxMessage)this.wxMessage).getOpenId();
/* 26:28 */     String wechatId = ((WxMessage)this.wxMessage).getWechatId();
/* 27:30 */     if (ChatExecute.isReceived(openId))
/* 28:   */     {
/* 29:31 */       this.log.debug("被接待的客户");
/* 30:32 */       String serviceId = ChatExecute.getServiceId(openId);
/* 31:33 */       ChatExecute.saveMessage((WxMessage)this.wxMessage, serviceId);
/* 32:   */     }
/* 33:   */     else
/* 34:   */     {
/* 35:35 */       this.log.debug("未被接待的客户");
/* 36:36 */       if (!"88".equals(((WxMessage)this.wxMessage).getContent()))
/* 37:   */       {
/* 38:37 */         int len = ChatExecute.addWaitMan(wechatId, openId);
/* 39:   */         try
/* 40:   */         {
/* 41:39 */           ReplyEntity message = new ReplyEntity((WxMessage)this.wxMessage, Terminology.getServiceWait(len));
/* 42:40 */           MqService.getInstance().send(message, QueueProperties.getServiceSendQueue());
/* 43:   */         }
/* 44:   */         catch (Exception e)
/* 45:   */         {
/* 46:42 */           this.log.fatal("发送消息到客服发送队列出错", e);
/* 47:   */         }
/* 48:   */       }
/* 49:   */       else
/* 50:   */       {
/* 51:45 */         ChatExecute.endCommicationByCustomer(wechatId, openId);
/* 52:   */       }
/* 53:   */     }
/* 54:   */   }
/* 55:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.mulitservice.worker.ChatWorker
 * JD-Core Version:    0.7.0.1
 */