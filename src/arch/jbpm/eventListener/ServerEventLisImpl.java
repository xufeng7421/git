/*  1:   */ package arch.jbpm.eventListener;
/*  2:   */ 
/*  3:   */ import arch.jbpm.mq.Sender;
/*  4:   */ import java.io.BufferedReader;
/*  5:   */ import java.io.InputStreamReader;
/*  6:   */ import org.apache.log4j.Logger;
/*  7:   */ import org.jbpm.api.listener.EventListener;
/*  8:   */ import org.jbpm.api.listener.EventListenerExecution;
/*  9:   */ 
/* 10:   */ public class ServerEventLisImpl
/* 11:   */   implements EventListener
/* 12:   */ {
/* 13:   */   private static final long serialVersionUID = 1L;
/* 14:21 */   private static Logger log = Logger.getLogger(ServerEventLisImpl.class);
/* 15:   */   
/* 16:   */   public void notify(EventListenerExecution execution)
/* 17:   */     throws Exception
/* 18:   */   {
/* 19:27 */     log.debug("转入客服消息请输入1:\r\r");
/* 20:28 */     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
/* 21:29 */     String line = br.readLine();
/* 22:   */     
/* 23:31 */     Sender send = new Sender();
/* 24:   */     
/* 25:   */ 
/* 26:   */ 
/* 27:   */ 
/* 28:   */ 
/* 29:   */ 
/* 30:   */ 
/* 31:   */ 
/* 32:   */ 
/* 33:41 */     log.debug(line);
/* 34:42 */     log.debug("设置流程变量");
/* 35:43 */     execution.setVariable("mess", line);
/* 36:   */   }
/* 37:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.jbpm.eventListener.ServerEventLisImpl
 * JD-Core Version:    0.7.0.1
 */