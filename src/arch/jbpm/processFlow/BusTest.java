/*  1:   */ package arch.jbpm.processFlow;
/*  2:   */ 
/*  3:   */ import arch.entity.WxMessage;
/*  4:   */ import arch.listener.QueueListener;
/*  5:   */ import arch.properties.ThreadPoolProperties;
/*  6:   */ 
/*  7:   */ public class BusTest
/*  8:   */ {
/*  9:   */   public static void main(String[] args)
/* 10:   */   {
/* 11:29 */     Runnable run = new QueueListener(ThreadPoolProperties.getTextCoreThread().intValue(), 
/* 12:30 */       ThreadPoolProperties.getTextMaxThread().intValue(), 
/* 13:31 */       BusinessFlow.class, 
/* 14:32 */       WxMessage.class, 
/* 15:33 */       "TEST1QUEUE");
/* 16:34 */     new Thread(run).start();
/* 17:   */     
/* 18:36 */     run = new QueueListener(ThreadPoolProperties.getTextCoreThread().intValue(), 
/* 19:37 */       ThreadPoolProperties.getTextMaxThread().intValue(), 
/* 20:38 */       BusinessTest1.class, 
/* 21:39 */       WxMessage.class, 
/* 22:40 */       "DECISIONQUEUE");
/* 23:41 */     new Thread(run).start();
/* 24:   */   }
/* 25:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.jbpm.processFlow.BusTest
 * JD-Core Version:    0.7.0.1
 */