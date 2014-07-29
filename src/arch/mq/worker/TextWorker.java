/*   1:    */ package arch.mq.worker;
/*   2:    */ 
/*   3:    */ import arch.entity.BusinessConfig;
/*   4:    */ import arch.entity.ReplyEntity;
/*   5:    */ import arch.entity.ReplyMsg;
/*   6:    */ import arch.entity.WxMessage;
/*   7:    */ import arch.entity.type.NoticeState;
/*   8:    */ import arch.entity.type.QueueType;
/*   9:    */ import arch.mq.DB.service.QueueService;
/*  10:    */ import arch.mq.DB.service.WeChatService;
/*  11:    */ import arch.mq.execute.ExecuteService;
/*  12:    */ import arch.mq.interfaces.Dispatcher;
/*  13:    */ import arch.mq.service.MqService;
/*  14:    */ import arch.properties.QueueProperties;
/*  15:    */ import arch.util.DbcpConnection;
/*  16:    */ import arch.workerBase.WorkerBase;
/*  17:    */ import java.sql.Connection;
/*  18:    */ import org.apache.log4j.Logger;
/*  19:    */ 
/*  20:    */ public class TextWorker
/*  21:    */   extends WorkerBase<WxMessage>
/*  22:    */   implements Runnable, Dispatcher
/*  23:    */ {
/*  24: 28 */   private static Logger log = Logger.getLogger(TextWorker.class);
/*  25: 29 */   private QueueService queueService = new QueueService();
/*  26: 30 */   private WeChatService chatService = new WeChatService();
/*  27:    */   
/*  28:    */   public TextWorker(WxMessage wxMessage)
/*  29:    */   {
/*  30: 33 */     super(wxMessage);
/*  31:    */   }
/*  32:    */   
/*  33:    */   public void run()
/*  34:    */   {
/*  35: 38 */     log.debug("逻辑处理、、、、");
/*  36: 39 */     BusinessConfig business = isBusiness((WxMessage)this.wxMessage, QueueType.TEXT);
/*  37: 40 */     if (business == null)
/*  38:    */     {
/*  39: 41 */       log.debug("不是业务");
/*  40: 42 */       ReplyMsg autoReply = isAutoReply((WxMessage)this.wxMessage);
/*  41: 43 */       if (autoReply != null) {
/*  42: 44 */         doAutoReply((WxMessage)this.wxMessage, autoReply);
/*  43:    */       }
/*  44:    */     }
/*  45:    */     else
/*  46:    */     {
/*  47: 49 */       doBusiness((WxMessage)this.wxMessage, business);
/*  48:    */     }
/*  49:    */   }
/*  50:    */   
/*  51:    */   public void doBusiness(WxMessage wxMessage, BusinessConfig business)
/*  52:    */   {
/*  53:    */     try
/*  54:    */     {
/*  55: 56 */       log.debug("执行业务处理");
/*  56: 57 */       wxMessage.setBssSendQueue(business.getSendQueue());
/*  57: 58 */       wxMessage.setBssReciveQueue(business.getReciveQueue());
/*  58: 59 */       wxMessage.setBusinessId(business.getBusinessId());
/*  59: 60 */       wxMessage.setBssType(NoticeState.START);
/*  60: 61 */       MqService sender = MqService.getInstance();
/*  61: 62 */       sender.sendMessage(wxMessage, QueueProperties.getExclusiveQueue());
/*  62:    */     }
/*  63:    */     catch (Exception e)
/*  64:    */     {
/*  65: 64 */       log.fatal("发送消息到业务处理队列出错", e);
/*  66:    */     }
/*  67:    */   }
/*  68:    */   
/*  69:    */   public void doAutoReply(WxMessage wxMessage, ReplyMsg replyMsg)
/*  70:    */   {
/*  71: 70 */     log.debug("执行自定义回复");
/*  72: 71 */     Connection conn = null;
/*  73:    */     try
/*  74:    */     {
/*  75: 73 */       conn = DbcpConnection.getConnection();
/*  76: 74 */       ReplyEntity reply = this.chatService.getReplyEntity(conn, wxMessage, replyMsg);
/*  77: 75 */       MqService.getInstance().sendMessage(reply, QueueProperties.getSendQueue());
/*  78:    */     }
/*  79:    */     catch (Exception e)
/*  80:    */     {
/*  81: 77 */       log.fatal("自动回复异常", e);
/*  82:    */     }
/*  83:    */     finally
/*  84:    */     {
/*  85: 79 */       DbcpConnection.close(conn, null, null);
/*  86:    */     }
/*  87:    */   }
/*  88:    */   
/*  89:    */   public void toService(WxMessage wxMessage)
/*  90:    */   {
/*  91: 86 */     log.debug("执行客服流程");
/*  92:    */     try
/*  93:    */     {
/*  94: 88 */       wxMessage.setBssType(NoticeState.START);
/*  95: 89 */       wxMessage.setBusinessId("serviceFlow");
/*  96: 90 */       wxMessage.setBssSendQueue(QueueProperties.getServiceSendQueue());
/*  97: 91 */       MqService.getInstance().sendMessage(wxMessage, QueueProperties.getServiceQueue());
/*  98:    */     }
/*  99:    */     catch (Exception e)
/* 100:    */     {
/* 101: 93 */       log.fatal("发送给客服业务出错", e);
/* 102:    */     }
/* 103:    */   }
/* 104:    */   
/* 105:    */   public BusinessConfig isBusiness(WxMessage wxMessage, QueueType queueType)
/* 106:    */   {
/* 107: 99 */     return ExecuteService.isBusiness(wxMessage.getWechatId(), wxMessage.getContent(), queueType, log);
/* 108:    */   }
/* 109:    */   
/* 110:    */   public ReplyMsg isAutoReply(WxMessage wxMessage)
/* 111:    */   {
/* 112:104 */     Connection conn = null;
/* 113:105 */     ReplyMsg temp = null;
/* 114:    */     try
/* 115:    */     {
/* 116:107 */       conn = DbcpConnection.getConnection();
/* 117:108 */       temp = this.chatService.findAutoReply(conn, wxMessage.getWechatId(), wxMessage.getCompanyId(), wxMessage.getContent());
/* 118:    */     }
/* 119:    */     catch (Exception e)
/* 120:    */     {
/* 121:110 */       log.fatal(e);
/* 122:    */     }
/* 123:    */     finally
/* 124:    */     {
/* 125:112 */       DbcpConnection.close(conn, null, null);
/* 126:    */     }
/* 127:114 */     return temp;
/* 128:    */   }
/* 129:    */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.mq.worker.TextWorker
 * JD-Core Version:    0.7.0.1
 */