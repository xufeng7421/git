/*  1:   */ package arch.mq.execute;
/*  2:   */ 
/*  3:   */ import arch.entity.BusinessConfig;
/*  4:   */ import arch.entity.WxMessage;
/*  5:   */ import arch.entity.type.QueueType;
/*  6:   */ import arch.mq.DB.service.QueueService;
/*  7:   */ import arch.util.DbcpConnection;
/*  8:   */ import java.sql.Connection;
/*  9:   */ import org.apache.log4j.Logger;
/* 10:   */ 
/* 11:   */ public class ExecuteService
/* 12:   */ {
/* 13:25 */   private Logger log = Logger.getLogger(ExecuteService.class);
/* 14:26 */   private static QueueService queueService = new QueueService();
/* 15:   */   
/* 16:   */   public static BusinessConfig isBusiness(String wechatId, String trigger, QueueType queueType, Logger log)
/* 17:   */   {
/* 18:36 */     BusinessConfig businessConfig = null;
/* 19:37 */     Connection conn = null;
/* 20:   */     try
/* 21:   */     {
/* 22:39 */       conn = DbcpConnection.getConnection();
/* 23:40 */       businessConfig = queueService.findBusiness(conn, trigger, wechatId, queueType);
/* 24:   */     }
/* 25:   */     catch (Exception e)
/* 26:   */     {
/* 27:42 */       log.fatal("查询是否为业务出错", e);
/* 28:   */     }
/* 29:   */     finally
/* 30:   */     {
/* 31:44 */       DbcpConnection.close(conn, null, null);
/* 32:   */     }
/* 33:46 */     return businessConfig;
/* 34:   */   }
/* 35:   */   
/* 36:   */   public static void toService(WxMessage wxMessage) {}
/* 37:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.mq.execute.ExecuteService
 * JD-Core Version:    0.7.0.1
 */