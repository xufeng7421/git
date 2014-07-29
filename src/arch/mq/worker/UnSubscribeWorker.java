/*  1:   */ package arch.mq.worker;
/*  2:   */ 
/*  3:   */ import arch.entity.WxMessage;
/*  4:   */ import arch.mq.DB.service.WeChatService;
/*  5:   */ import arch.util.DbcpConnection;
/*  6:   */ import arch.workerBase.WorkerBase;
/*  7:   */ import java.sql.Connection;
/*  8:   */ import org.apache.log4j.Logger;
/*  9:   */ 
/* 10:   */ public class UnSubscribeWorker
/* 11:   */   extends WorkerBase<WxMessage>
/* 12:   */   implements Runnable
/* 13:   */ {
/* 14:18 */   private static Logger log = Logger.getLogger(UnSubscribeWorker.class);
/* 15:19 */   private WeChatService chatService = new WeChatService();
/* 16:   */   
/* 17:   */   public UnSubscribeWorker(WxMessage wxMessage)
/* 18:   */   {
/* 19:22 */     super(wxMessage);
/* 20:   */   }
/* 21:   */   
/* 22:   */   public void run()
/* 23:   */   {
/* 24:27 */     Connection conn = null;
/* 25:   */     try
/* 26:   */     {
/* 27:29 */       conn = DbcpConnection.getConnection();
/* 28:30 */       this.chatService.updVipSubscribe(conn, ((WxMessage)this.wxMessage).getOpenId(), ((WxMessage)this.wxMessage).getWechatId(), 0);
/* 29:31 */       conn.commit();
/* 30:   */     }
/* 31:   */     catch (Exception e)
/* 32:   */     {
/* 33:33 */       log.fatal("取消关注事件处理异常", e);
/* 34:   */     }
/* 35:   */     finally
/* 36:   */     {
/* 37:35 */       DbcpConnection.close(conn, null, null);
/* 38:   */     }
/* 39:   */   }
/* 40:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.mq.worker.UnSubscribeWorker
 * JD-Core Version:    0.7.0.1
 */