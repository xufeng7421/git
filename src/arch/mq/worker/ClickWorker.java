/*  1:   */ package arch.mq.worker;
/*  2:   */ 
/*  3:   */ import arch.entity.ReplyEntity;
/*  4:   */ import arch.entity.ReplyMsg;
/*  5:   */ import arch.entity.WxMessage;
/*  6:   */ import arch.mq.DB.service.WeChatService;
/*  7:   */ import arch.mq.service.MqService;
/*  8:   */ import arch.properties.QueueProperties;
/*  9:   */ import arch.util.DbcpConnection;
/* 10:   */ import arch.workerBase.WorkerBase;
/* 11:   */ import java.sql.Connection;
/* 12:   */ import org.apache.log4j.Logger;
/* 13:   */ 
/* 14:   */ public class ClickWorker
/* 15:   */   extends WorkerBase<WxMessage>
/* 16:   */   implements Runnable
/* 17:   */ {
/* 18:22 */   private static Logger log = Logger.getLogger(ClickWorker.class);
/* 19:23 */   private WeChatService chatService = new WeChatService();
/* 20:   */   
/* 21:   */   public ClickWorker(WxMessage wxMessage)
/* 22:   */   {
/* 23:25 */     super(wxMessage);
/* 24:   */   }
/* 25:   */   
/* 26:   */   public void run()
/* 27:   */   {
/* 28:30 */     Connection conn = null;
/* 29:   */     try
/* 30:   */     {
/* 31:32 */       conn = DbcpConnection.getConnection();
/* 32:33 */       String eventKey = ((WxMessage)this.wxMessage).getEventKey();
/* 33:34 */       ReplyMsg replyMsg = this.chatService.findMenuReply(conn, eventKey, ((WxMessage)this.wxMessage).getWechatId(), ((WxMessage)this.wxMessage).getCompanyId());
/* 34:35 */       if (replyMsg != null)
/* 35:   */       {
/* 36:36 */         ReplyEntity reply = this.chatService.getReplyEntity(conn, (WxMessage)this.wxMessage, replyMsg);
/* 37:37 */         log.info(reply.getAccessToken());
/* 38:38 */         log.info(reply.getContent());
/* 39:39 */         MqService.getInstance().sendMessage(reply, QueueProperties.getSendQueue());
/* 40:   */       }
/* 41:   */     }
/* 42:   */     catch (Exception e)
/* 43:   */     {
/* 44:42 */       log.fatal("点击事件处理异常", e);
/* 45:   */     }
/* 46:   */     finally
/* 47:   */     {
/* 48:44 */       DbcpConnection.close(conn, null, null);
/* 49:   */     }
/* 50:   */   }
/* 51:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.mq.worker.ClickWorker
 * JD-Core Version:    0.7.0.1
 */