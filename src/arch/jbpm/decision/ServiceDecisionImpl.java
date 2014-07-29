/*  1:   */ package arch.jbpm.decision;
/*  2:   */ 
/*  3:   */ import arch.entity.WxMessage;
/*  4:   */ import arch.entity.type.NoticeState;
/*  5:   */ import arch.jbpm.entity.BusinessTest;
/*  6:   */ import arch.jbpm.utils.BusinessUtils;
/*  7:   */ import arch.mq.service.MqService;
/*  8:   */ import arch.properties.QueueProperties;
/*  9:   */ import arch.util.DbcpConnection;
/* 10:   */ import java.sql.Connection;
/* 11:   */ import java.sql.SQLException;
/* 12:   */ import org.apache.log4j.Logger;
/* 13:   */ import org.jbpm.api.jpdl.DecisionHandler;
/* 14:   */ import org.jbpm.api.model.OpenExecution;
/* 15:   */ 
/* 16:   */ public class ServiceDecisionImpl
/* 17:   */   implements DecisionHandler
/* 18:   */ {
/* 19:   */   private static final long serialVersionUID = 1L;
/* 20:31 */   private static Logger log = Logger.getLogger(ServiceDecisionImpl.class);
/* 21:   */   
/* 22:   */   public String decide(OpenExecution execution)
/* 23:   */   {
/* 24:35 */     Connection conn = DbcpConnection.getConnection();
/* 25:   */     try
/* 26:   */     {
/* 27:37 */       WxMessage mess = (WxMessage)execution.getVariable("wxMessage");
/* 28:38 */       BusinessTest busTest = (BusinessTest)execution.getVariable("buss");
/* 29:39 */       if ("1".equals(mess.getContent()))
/* 30:   */       {
/* 31:40 */         BusinessUtils.updNextStep(conn, "客服消息", "1", busTest);
/* 32:41 */         conn.commit();
/* 33:   */         
/* 34:   */ 
/* 35:44 */         BusinessUtils.sendNotice(busTest, NoticeState.SERVICE);
/* 36:45 */         MqService.getInstance().send(mess, QueueProperties.getServiceReviceQueue());
/* 37:46 */         return "to 客服消息";
/* 38:   */       }
/* 39:48 */       BusinessUtils.updNextStep(conn, "", "2", busTest);
/* 40:49 */       conn.commit();
/* 41:50 */       return "to end1";
/* 42:   */     }
/* 43:   */     catch (SQLException e)
/* 44:   */     {
/* 45:   */       try
/* 46:   */       {
/* 47:54 */         conn.rollback();
/* 48:   */       }
/* 49:   */       catch (SQLException e1)
/* 50:   */       {
/* 51:56 */         log.fatal("修改下一步失败回滚失败", e1);
/* 52:   */       }
/* 53:58 */       log.fatal("修改下一步失败", e);
/* 54:   */     }
/* 55:   */     catch (Exception e)
/* 56:   */     {
/* 57:60 */       log.fatal("发送队列消息失败", e);
/* 58:   */     }
/* 59:62 */     return "to end2";
/* 60:   */   }
/* 61:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.jbpm.decision.ServiceDecisionImpl
 * JD-Core Version:    0.7.0.1
 */