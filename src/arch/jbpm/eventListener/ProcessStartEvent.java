/*  1:   */ package arch.jbpm.eventListener;
/*  2:   */ 
/*  3:   */ import arch.entity.type.NoticeState;
/*  4:   */ import arch.jbpm.entity.JbpmBaseEntity;
/*  5:   */ import arch.jbpm.utils.BusinessUtils;
/*  6:   */ import org.apache.log4j.Logger;
/*  7:   */ import org.jbpm.api.listener.EventListener;
/*  8:   */ import org.jbpm.api.listener.EventListenerExecution;
/*  9:   */ 
/* 10:   */ public class ProcessStartEvent
/* 11:   */   implements EventListener
/* 12:   */ {
/* 13:   */   private static final long serialVersionUID = 1L;
/* 14:26 */   private static Logger log = Logger.getLogger(ProcessStartEvent.class);
/* 15:   */   
/* 16:   */   public void notify(EventListenerExecution execution)
/* 17:   */     throws Exception
/* 18:   */   {
/* 19:31 */     JbpmBaseEntity busEntity = (JbpmBaseEntity)execution.getVariable("buss");
/* 20:32 */     BusinessUtils.sendNotice(busEntity, NoticeState.START);
/* 21:   */   }
/* 22:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.jbpm.eventListener.ProcessStartEvent
 * JD-Core Version:    0.7.0.1
 */