/*  1:   */ package arch.mq.worker;
/*  2:   */ 
/*  3:   */ import arch.entity.BusinessConfig;
/*  4:   */ import arch.entity.WxMessage;
/*  5:   */ import arch.mq.DB.service.QueueService;
/*  6:   */ import arch.mq.service.MqService;
/*  7:   */ import arch.util.DbcpConnection;
/*  8:   */ import arch.workerBase.WorkerBase;
/*  9:   */ import java.io.Serializable;
/* 10:   */ import java.sql.Connection;
/* 11:   */ import org.apache.log4j.Logger;
/* 12:   */ 
/* 13:   */ public class BusinessDispatcherWorker
/* 14:   */   extends WorkerBase<WxMessage>
/* 15:   */   implements Runnable
/* 16:   */ {
/* 17:22 */   private static Logger log = Logger.getLogger(BusinessDispatcherWorker.class);
/* 18:23 */   private QueueService qService = new QueueService();
/* 19:   */   
/* 20:   */   public BusinessDispatcherWorker(WxMessage wxMessage)
/* 21:   */   {
/* 22:25 */     super(wxMessage);
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void run()
/* 26:   */   {
/* 27:30 */     log.debug("执行业务分发流程");
/* 28:31 */     dispatcher();
/* 29:   */   }
/* 30:   */   
/* 31:   */   private void dispatcher()
/* 32:   */   {
/* 33:35 */     Connection conn = null;
/* 34:   */     try
/* 35:   */     {
/* 36:38 */       conn = DbcpConnection.getConnection();
/* 37:39 */       BusinessConfig config = this.qService.findProcessId(conn, ((WxMessage)this.wxMessage).getOpenId(), ((WxMessage)this.wxMessage).getBusinessId());
/* 38:40 */       ((WxMessage)this.wxMessage).setBssSendQueue(config.getSendQueue());
/* 39:41 */       ((WxMessage)this.wxMessage).setBssReciveQueue(config.getReciveQueue());
/* 40:42 */       ((WxMessage)this.wxMessage).setProcessInstanceId(config.getPeocessId());
/* 41:43 */       MqService sender = MqService.getInstance();
/* 42:44 */       sender.sendMessage((Serializable)this.wxMessage, config.getReciveQueue());
/* 43:   */     }
/* 44:   */     catch (Exception e)
/* 45:   */     {
/* 46:47 */       log.fatal("业务分发出错", e);
/* 47:   */     }
/* 48:   */     finally
/* 49:   */     {
/* 50:49 */       DbcpConnection.close(conn, null, null);
/* 51:   */     }
/* 52:   */   }
/* 53:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.mq.worker.BusinessDispatcherWorker
 * JD-Core Version:    0.7.0.1
 */