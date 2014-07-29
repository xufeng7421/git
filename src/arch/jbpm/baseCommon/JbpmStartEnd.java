/*  1:   */ package arch.jbpm.baseCommon;
/*  2:   */ 
/*  3:   */ import arch.entity.NoticeBusiness;
/*  4:   */ import arch.mq.service.MqService;
/*  5:   */ import arch.properties.QueueProperties;
/*  6:   */ import org.apache.log4j.Logger;
/*  7:   */ 
/*  8:   */ public class JbpmStartEnd
/*  9:   */ {
/* 10:18 */   private static Logger log = Logger.getLogger(JbpmStartEnd.class);
/* 11:   */   
/* 12:   */   public void startEvent(NoticeBusiness notice)
/* 13:   */   {
/* 14:   */     try
/* 15:   */     {
/* 16:26 */       MqService sender = MqService.getInstance();
/* 17:27 */       sender.sendMessage(notice, QueueProperties.getNoticeBusinessQueue());
/* 18:   */     }
/* 19:   */     catch (Exception e)
/* 20:   */     {
/* 21:29 */       e.printStackTrace();
/* 22:   */     }
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void endEvent(NoticeBusiness notice)
/* 26:   */   {
/* 27:   */     try
/* 28:   */     {
/* 29:39 */       MqService sender = MqService.getInstance();
/* 30:40 */       sender.sendMessage(notice, QueueProperties.getNoticeBusinessQueue());
/* 31:   */     }
/* 32:   */     catch (Exception e)
/* 33:   */     {
/* 34:42 */       e.printStackTrace();
/* 35:   */     }
/* 36:   */   }
/* 37:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.jbpm.baseCommon.JbpmStartEnd
 * JD-Core Version:    0.7.0.1
 */