/*  1:   */ package arch.mq.worker;
/*  2:   */ 
/*  3:   */ import arch.entity.Item;
/*  4:   */ import arch.entity.ReplyEntity;
/*  5:   */ import arch.entity.ReplyMsg;
/*  6:   */ import arch.entity.WxMessage;
/*  7:   */ import arch.entity.type.ReplyType;
/*  8:   */ import arch.mq.DB.service.WeChatService;
/*  9:   */ import arch.mq.service.MqService;
/* 10:   */ import arch.properties.QueueProperties;
/* 11:   */ import arch.util.AccessTokenUtil;
/* 12:   */ import arch.util.DbcpConnection;
/* 13:   */ import arch.util.MsgUtil;
/* 14:   */ import arch.workerBase.WorkerBase;
/* 15:   */ import java.sql.Connection;
/* 16:   */ import org.apache.log4j.Logger;
/* 17:   */ 
/* 18:   */ public class ScanWorker
/* 19:   */   extends WorkerBase<WxMessage>
/* 20:   */   implements Runnable
/* 21:   */ {
/* 22:28 */   private static Logger log = Logger.getLogger(ScanWorker.class);
/* 23:29 */   private WeChatService chatService = new WeChatService();
/* 24:   */   
/* 25:   */   public ScanWorker(WxMessage wxMessage)
/* 26:   */   {
/* 27:31 */     super(wxMessage);
/* 28:   */   }
/* 29:   */   
/* 30:   */   public void run()
/* 31:   */   {
/* 32:36 */     Connection conn = null;
/* 33:   */     try
/* 34:   */     {
/* 35:38 */       conn = DbcpConnection.getConnection();
/* 36:39 */       String eventKey = ((WxMessage)this.wxMessage).getEventKey();
/* 37:40 */       if ("100001".equals(eventKey))
/* 38:   */       {
/* 39:41 */         ReplyMsg replyMsg = this.chatService.findPerViewReply(conn, ((WxMessage)this.wxMessage).getCompanyId(), ((WxMessage)this.wxMessage).getWechatId());
/* 40:42 */         ReplyEntity reply = this.chatService.getReplyEntity(conn, (WxMessage)this.wxMessage, replyMsg);
/* 41:43 */         log.info(reply.getContent());
/* 42:44 */         MqService.getInstance().sendMessage(reply, QueueProperties.getSendQueue());
/* 43:   */       }
/* 44:   */       else
/* 45:   */       {
/* 46:46 */         Item item = new Item(((WxMessage)this.wxMessage).getEventKey(), "", "", 
/* 47:47 */           "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + ((WxMessage)this.wxMessage).getTicket());
/* 48:48 */         ReplyEntity reply = new ReplyEntity((WxMessage)this.wxMessage);
/* 49:49 */         reply.setMsgType(ReplyType.IMAGETEXT);
/* 50:50 */         reply.setContent(MsgUtil.parseNewsMsg(((WxMessage)this.wxMessage).getOpenId(), item));
/* 51:51 */         reply.setAccessToken(AccessTokenUtil.getAccessToken(((WxMessage)this.wxMessage).getWechatId()));
/* 52:52 */         log.info(reply.getContent());
/* 53:53 */         MqService.getInstance().sendMessage(reply, QueueProperties.getSendQueue());
/* 54:   */       }
/* 55:   */     }
/* 56:   */     catch (Exception e)
/* 57:   */     {
/* 58:56 */       log.fatal("点击事件处理异常", e);
/* 59:   */     }
/* 60:   */     finally
/* 61:   */     {
/* 62:58 */       DbcpConnection.close(conn, null, null);
/* 63:   */     }
/* 64:   */   }
/* 65:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.mq.worker.ScanWorker
 * JD-Core Version:    0.7.0.1
 */