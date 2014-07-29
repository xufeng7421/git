/*   1:    */ package arch.jbpm.utils;
/*   2:    */ 
/*   3:    */ import arch.entity.NoticeBusiness;
/*   4:    */ import arch.entity.ReplyEntity;
/*   5:    */ import arch.entity.WxMessage;
/*   6:    */ import arch.entity.type.NoticeState;
/*   7:    */ import arch.entity.type.ReplyType;
/*   8:    */ import arch.jbpm.db.dao.BusinessBaseDao;
/*   9:    */ import arch.jbpm.db.dao.impl.BusinessBaseDaoImpl;
/*  10:    */ import arch.jbpm.entity.BusinessTest;
/*  11:    */ import arch.jbpm.entity.JbpmBaseEntity;
/*  12:    */ import arch.mq.service.MqService;
/*  13:    */ import arch.properties.QueueProperties;
/*  14:    */ import arch.util.AccessTokenUtil;
/*  15:    */ import arch.util.MsgUtil;
/*  16:    */ import java.sql.Connection;
/*  17:    */ import java.sql.SQLException;
/*  18:    */ import java.util.Iterator;
/*  19:    */ import java.util.Set;
/*  20:    */ import org.apache.log4j.Logger;
/*  21:    */ import org.jbpm.api.ProcessInstance;
/*  22:    */ 
/*  23:    */ public class BusinessUtils
/*  24:    */ {
/*  25: 31 */   private static Logger log = Logger.getLogger(BusinessUtils.class);
/*  26: 33 */   private static BusinessBaseDao bussDao = new BusinessBaseDaoImpl();
/*  27:    */   
/*  28:    */   public static void sendBusinessTextMsg(WxMessage mess, String content)
/*  29:    */   {
/*  30: 42 */     ReplyEntity reply = new ReplyEntity(mess);
/*  31: 43 */     reply.setMsgType(ReplyType.TEXT);
/*  32: 44 */     String msgContent = MsgUtil.parseTextMsg(mess.getOpenId(), content);
/*  33: 45 */     reply.setContent(msgContent);
/*  34: 46 */     String accessToken = AccessTokenUtil.getAccessToken(mess.getWechatId());
/*  35: 47 */     reply.setAccessToken(accessToken);
/*  36: 48 */     MqService sender = MqService.getInstance();
/*  37:    */     try
/*  38:    */     {
/*  39: 50 */       sender.sendMessage(reply, mess.getBssSendQueue());
/*  40:    */     }
/*  41:    */     catch (Exception e)
/*  42:    */     {
/*  43: 52 */       log.fatal("发送回复消息异常", e);
/*  44:    */     }
/*  45:    */   }
/*  46:    */   
/*  47:    */   public static String nextStep(ProcessInstance instance)
/*  48:    */   {
/*  49: 62 */     String nextStep = "";
/*  50:    */     
/*  51: 64 */     Set<String> stepSet = instance.findActiveActivityNames();
/*  52: 65 */     Iterator<String> iter = stepSet.iterator();
/*  53: 66 */     if (iter.hasNext()) {
/*  54: 67 */       nextStep = (String)iter.next();
/*  55:    */     }
/*  56: 69 */     return nextStep;
/*  57:    */   }
/*  58:    */   
/*  59:    */   public static void sendNotice(JbpmBaseEntity busEntity, NoticeState state)
/*  60:    */   {
/*  61: 80 */     NoticeBusiness notice = new NoticeBusiness();
/*  62: 81 */     notice.setBusinessId(busEntity.getBssId());
/*  63: 82 */     notice.setOpenId(busEntity.getOpenId());
/*  64: 83 */     notice.setState(state);
/*  65:    */     try
/*  66:    */     {
/*  67: 86 */       MqService sender = MqService.getInstance();
/*  68: 87 */       sender.sendMessage(notice, QueueProperties.getNoticeBusinessQueue());
/*  69:    */     }
/*  70:    */     catch (Exception e)
/*  71:    */     {
/*  72: 89 */       log.fatal("流程开始结束发送状态通知失败", e);
/*  73:    */     }
/*  74:    */   }
/*  75:    */   
/*  76:    */   public static void updNextStep(Connection conn, String nextStep, String state, BusinessTest busTest)
/*  77:    */   {
/*  78:    */     try
/*  79:    */     {
/*  80:103 */       busTest.setNextStep(nextStep);
/*  81:104 */       busTest.setStatus(state);
/*  82:    */       
/*  83:106 */       bussDao.updProcessStatus(busTest, conn);
/*  84:    */     }
/*  85:    */     catch (SQLException e)
/*  86:    */     {
/*  87:108 */       log.fatal("更新下一步状态失败", e);
/*  88:    */     }
/*  89:    */   }
/*  90:    */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.jbpm.utils.BusinessUtils
 * JD-Core Version:    0.7.0.1
 */