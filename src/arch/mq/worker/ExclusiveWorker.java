/*  1:   */ package arch.mq.worker;
/*  2:   */ 
/*  3:   */ import arch.entity.ProcessEntity;
/*  4:   */ import arch.entity.WxMessage;
/*  5:   */ import arch.entity.type.NoticeState;
/*  6:   */ import arch.mq.DB.service.QueueService;
/*  7:   */ import arch.mq.service.MqService;
/*  8:   */ import arch.util.DbcpConnection;
/*  9:   */ import arch.util.JsonUtil;
/* 10:   */ import arch.workerBase.WorkerBase;
/* 11:   */ import java.io.Serializable;
/* 12:   */ import java.sql.Connection;
/* 13:   */ import org.apache.log4j.Logger;
/* 14:   */ 
/* 15:   */ public class ExclusiveWorker
/* 16:   */   extends WorkerBase<WxMessage>
/* 17:   */   implements Runnable
/* 18:   */ {
/* 19:25 */   private static Logger log = Logger.getLogger(ExclusiveWorker.class);
/* 20:26 */   private QueueService queueService = new QueueService();
/* 21:   */   
/* 22:   */   public ExclusiveWorker(WxMessage wxMessage)
/* 23:   */   {
/* 24:28 */     super(wxMessage);
/* 25:   */   }
/* 26:   */   
/* 27:   */   public void run()
/* 28:   */   {
/* 29:33 */     log.debug("排他队列");
/* 30:34 */     doWork();
/* 31:   */   }
/* 32:   */   
/* 33:   */   private void doWork()
/* 34:   */   {
/* 35:38 */     Connection conn = null;
/* 36:   */     try
/* 37:   */     {
/* 38:40 */       conn = DbcpConnection.getConnection();
/* 39:41 */       ProcessEntity proc = this.queueService.findUnClosedProcessId(conn, ((WxMessage)this.wxMessage).getOpenId());
/* 40:42 */       MqService sender = MqService.getInstance();
/* 41:43 */       if (proc != null)
/* 42:   */       {
/* 43:44 */         log.debug("排他");
/* 44:45 */         WxMessage temp = new WxMessage();
/* 45:46 */         temp.setProcessInstanceId(proc.getProId());
/* 46:47 */         temp.setOpenId(((WxMessage)this.wxMessage).getOpenId());
/* 47:48 */         temp.setBssType(NoticeState.END);
/* 48:49 */         log.debug(JsonUtil.parseToJSON("temp" + temp));
/* 49:50 */         sender.sendMessage(temp, proc.getReceiveQueue());
/* 50:   */       }
/* 51:52 */       log.debug("业务流转" + ((WxMessage)this.wxMessage).getBssReciveQueue());
/* 52:53 */       sender.sendMessage((Serializable)this.wxMessage, ((WxMessage)this.wxMessage).getBssReciveQueue());
/* 53:54 */       log.debug(JsonUtil.parseToJSON("wx:  " + this.wxMessage));
/* 54:   */     }
/* 55:   */     catch (Exception e)
/* 56:   */     {
/* 57:56 */       log.fatal("排他出错", e);
/* 58:   */     }
/* 59:   */     finally
/* 60:   */     {
/* 61:58 */       DbcpConnection.close(conn, null, null);
/* 62:   */     }
/* 63:   */   }
/* 64:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.mq.worker.ExclusiveWorker
 * JD-Core Version:    0.7.0.1
 */