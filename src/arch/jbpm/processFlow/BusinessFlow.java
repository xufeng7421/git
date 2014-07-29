/*   1:    */ package arch.jbpm.processFlow;
/*   2:    */ 
/*   3:    */ import arch.entity.WxMessage;
/*   4:    */ import arch.entity.type.MsgType;
/*   5:    */ import arch.entity.type.NoticeState;
/*   6:    */ import arch.jbpm.db.dao.BusinessBaseDao;
/*   7:    */ import arch.jbpm.db.dao.impl.BusinessBaseDaoImpl;
/*   8:    */ import arch.jbpm.entity.BusinessTest;
/*   9:    */ import arch.jbpm.utils.BusinessUtils;
/*  10:    */ import arch.jbpm.utils.JbpmUtil;
/*  11:    */ import arch.util.DateUtil;
/*  12:    */ import arch.util.DbcpConnection;
/*  13:    */ import arch.workerBase.WorkerBase;
/*  14:    */ import java.sql.Connection;
/*  15:    */ import java.sql.SQLException;
/*  16:    */ import java.util.Date;
/*  17:    */ import java.util.HashMap;
/*  18:    */ import java.util.Map;
/*  19:    */ import org.apache.log4j.Logger;
/*  20:    */ import org.jbpm.api.Configuration;
/*  21:    */ import org.jbpm.api.ExecutionService;
/*  22:    */ import org.jbpm.api.ProcessEngine;
/*  23:    */ import org.jbpm.api.ProcessInstance;
/*  24:    */ 
/*  25:    */ public class BusinessFlow
/*  26:    */   extends WorkerBase<WxMessage>
/*  27:    */   implements Runnable
/*  28:    */ {
/*  29:    */   public BusinessFlow(WxMessage wxMessage)
/*  30:    */   {
/*  31: 39 */     super(wxMessage);
/*  32: 40 */     this.mess = wxMessage;
/*  33:    */   }
/*  34:    */   
/*  35: 44 */   private static Logger log = Logger.getLogger(BusinessFlow.class);
/*  36:    */   private WxMessage mess;
/*  37:    */   private BusinessTest busTest;
/*  38: 51 */   private ProcessEngine processEngine = Configuration.getProcessEngine();
/*  39: 53 */   private ProcessInstance processInstance = null;
/*  40: 55 */   private ExecutionService executionService = this.processEngine.getExecutionService();
/*  41: 57 */   private BusinessBaseDao bussDao = new BusinessBaseDaoImpl();
/*  42:    */   private String nextStep;
/*  43: 60 */   JbpmUtil jbpmUtil = new JbpmUtil(this.processEngine);
/*  44:    */   
/*  45:    */   public void run()
/*  46:    */   {
/*  47: 71 */     Connection conn = null;
/*  48:    */     try
/*  49:    */     {
/*  50: 73 */       conn = DbcpConnection.getConnection();
/*  51:    */       
/*  52:    */ 
/*  53: 76 */       this.busTest = new BusinessTest();
/*  54: 77 */       this.busTest.setBssId(this.mess.getBusinessId());
/*  55: 78 */       this.busTest.setProcessInstanceId(this.mess.getProcessInstanceId());
/*  56: 79 */       this.busTest.setOpenId(this.mess.getOpenId());
/*  57: 80 */       this.busTest.setWechatId(this.mess.getWechatId());
/*  58: 81 */       this.busTest.setTitle(this.mess.getBusinessId() + "_" + DateUtil.dateToString(new Date()));
/*  59:    */       
/*  60:    */ 
/*  61: 84 */       Map<String, Object> busMap = new HashMap();
/*  62: 85 */       busMap.put("buss", this.busTest);
/*  63: 88 */       if (this.mess.getBssType() == NoticeState.START)
/*  64:    */       {
/*  65: 90 */         log.debug("开始启动");
/*  66: 91 */         this.processInstance = this.jbpmUtil.startProcessInstanceByKey(this.mess.getBusinessId(), busMap);
/*  67:    */         
/*  68: 93 */         this.busTest.setNextStep(BusinessUtils.nextStep(this.processInstance));
/*  69: 94 */         this.busTest.setProcessInstanceId(this.processInstance.getId());
/*  70: 95 */         this.bussDao.addProcessInfo(this.busTest, conn);
/*  71:    */         
/*  72: 97 */         String content = "流程已开启";
/*  73: 98 */         BusinessUtils.sendBusinessTextMsg(this.mess, content);
/*  74: 99 */         log.debug("流程开启");
/*  75:    */       }
/*  76:101 */       else if (this.mess.getBssType() == NoticeState.END)
/*  77:    */       {
/*  78:103 */         log.debug("____结束流程实例ID" + this.mess.getProcessInstanceId());
/*  79:    */         
/*  80:105 */         this.executionService.setVariable(this.mess.getProcessInstanceId(), "destroy", "1");
/*  81:106 */         this.jbpmUtil.endProcessInstance(this.mess.getProcessInstanceId());
/*  82:    */         
/*  83:    */ 
/*  84:109 */         this.busTest.setStatus("2");
/*  85:110 */         this.busTest.setNextStep("");
/*  86:111 */         this.bussDao.updProcessStatus(this.busTest, conn);
/*  87:112 */         log.debug("流程销毁");
/*  88:    */       }
/*  89:    */       else
/*  90:    */       {
/*  91:116 */         this.nextStep = this.bussDao.nextStep(this.busTest, conn);
/*  92:118 */         if ("提示消息".equals(this.nextStep))
/*  93:    */         {
/*  94:120 */           if (!MsgType.TEXT.equals(this.mess.getMsgType()))
/*  95:    */           {
/*  96:121 */             String content = "您发送的消息格式不正确，请重新输入";
/*  97:122 */             BusinessUtils.sendBusinessTextMsg(this.mess, content);
/*  98:    */           }
/*  99:    */           else
/* 100:    */           {
/* 101:126 */             this.processInstance = this.jbpmUtil.signalExecutionById(this.mess.getProcessInstanceId());
/* 102:    */             
/* 103:128 */             String content = "请输入需要的消息";
/* 104:129 */             BusinessUtils.sendBusinessTextMsg(this.mess, content);
/* 105:130 */             this.busTest.setNextStep(BusinessUtils.nextStep(this.processInstance));
/* 106:131 */             this.busTest.setStatus("1");
/* 107:    */             
/* 108:133 */             this.bussDao.updProcessStatus(this.busTest, conn);
/* 109:134 */             log.debug("提示消息---");
/* 110:    */           }
/* 111:    */         }
/* 112:138 */         else if ("接收消息".equals(this.nextStep))
/* 113:    */         {
/* 114:143 */           this.processInstance = this.jbpmUtil.signalExecutionById(this.mess.getProcessInstanceId());
/* 115:    */           
/* 116:145 */           String content = "接收消息";
/* 117:146 */           BusinessUtils.sendBusinessTextMsg(this.mess, content);
/* 118:    */           
/* 119:148 */           log.debug("接收消息---");
/* 120:    */           
/* 121:150 */           this.busTest.setStatus("2");
/* 122:151 */           this.busTest.setNextStep("");
/* 123:152 */           this.bussDao.updProcessStatus(this.busTest, conn);
/* 124:153 */           log.debug("流程正常结束---");
/* 125:    */         }
/* 126:    */       }
/* 127:157 */       conn.commit();
/* 128:    */     }
/* 129:    */     catch (SQLException e)
/* 130:    */     {
/* 131:    */       try
/* 132:    */       {
/* 133:160 */         conn.rollback();
/* 134:    */       }
/* 135:    */       catch (SQLException e1)
/* 136:    */       {
/* 137:162 */         log.fatal("业务流程回滚失败", e);
/* 138:    */       }
/* 139:164 */       log.fatal("业务流程执行失败", e);
/* 140:    */     }
/* 141:    */     finally
/* 142:    */     {
/* 143:166 */       DbcpConnection.close(conn, null, null);
/* 144:    */     }
/* 145:    */   }
/* 146:    */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.jbpm.processFlow.BusinessFlow
 * JD-Core Version:    0.7.0.1
 */