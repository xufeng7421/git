/*   1:    */ package arch.mq.DB.service;
/*   2:    */ 
/*   3:    */ import arch.entity.AutoReply;
/*   4:    */ import arch.entity.BusinessConfig;
/*   5:    */ import arch.entity.PluginCase;
/*   6:    */ import arch.entity.ProcessEntity;
/*   7:    */ import arch.entity.type.QueueType;
/*   8:    */ import arch.mq.DB.dao.QueueDao;
/*   9:    */ import arch.mq.DB.dao.impl.QueueDaoImpl;
/*  10:    */ import java.sql.Connection;
/*  11:    */ import java.sql.SQLException;
/*  12:    */ import java.util.List;
/*  13:    */ 
/*  14:    */ public class QueueService
/*  15:    */ {
/*  16: 18 */   private QueueDao dao = new QueueDaoImpl();
/*  17:    */   
/*  18:    */   public BusinessConfig findBusiness(Connection conn, String trigger, String wechatId, QueueType queueType)
/*  19:    */     throws SQLException
/*  20:    */   {
/*  21: 29 */     return this.dao.findBusiness(conn, trigger, wechatId, queueType);
/*  22:    */   }
/*  23:    */   
/*  24:    */   public AutoReply findAutoReply(Connection conn, String wechatId, String keyword)
/*  25:    */     throws SQLException
/*  26:    */   {
/*  27: 41 */     return this.dao.findAutoreplyByKeword(wechatId, keyword, conn);
/*  28:    */   }
/*  29:    */   
/*  30:    */   public BusinessConfig findBusinessConfig(Connection conn, String businessKey)
/*  31:    */     throws SQLException
/*  32:    */   {
/*  33: 52 */     return this.dao.findBusinessConfig(conn, businessKey);
/*  34:    */   }
/*  35:    */   
/*  36:    */   public ProcessEntity findUnClosedProcessId(Connection conn, String openId)
/*  37:    */     throws SQLException
/*  38:    */   {
/*  39: 63 */     return this.dao.findUnClosedProcessId(conn, openId);
/*  40:    */   }
/*  41:    */   
/*  42:    */   public BusinessConfig findProcessId(Connection conn, String openId, String businessId)
/*  43:    */     throws SQLException
/*  44:    */   {
/*  45: 76 */     return this.dao.findProcessId(conn, openId, businessId);
/*  46:    */   }
/*  47:    */   
/*  48:    */   public List<PluginCase> findUsingPlugin(Connection conn)
/*  49:    */     throws SQLException
/*  50:    */   {
/*  51: 86 */     return this.dao.findUsingPlugin(conn);
/*  52:    */   }
/*  53:    */   
/*  54:    */   public List<PluginCase> findNewPlugin(Connection conn)
/*  55:    */     throws SQLException
/*  56:    */   {
/*  57: 96 */     return this.dao.findNewPlugin(conn);
/*  58:    */   }
/*  59:    */   
/*  60:    */   public boolean oldedPlugin(Connection conn, String businessKey)
/*  61:    */     throws SQLException
/*  62:    */   {
/*  63:108 */     return this.dao.updPlugin(conn, businessKey);
/*  64:    */   }
/*  65:    */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.mq.DB.service.QueueService
 * JD-Core Version:    0.7.0.1
 */