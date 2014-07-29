/*   1:    */package arch.jbpm.processFlow;

/*   2:    */
/*   3:    */import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.jbpm.api.Configuration;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;

import arch.entity.WxMessage;
import arch.entity.type.MsgType;
import arch.entity.type.NoticeState;
import arch.jbpm.db.dao.BusinessBaseDao;
import arch.jbpm.db.dao.impl.BusinessBaseDaoImpl;
import arch.jbpm.entity.BusinessTest;
import arch.jbpm.utils.BusinessUtils;
import arch.jbpm.utils.JbpmUtil;
import arch.util.DateUtil;
import arch.util.DbcpConnection;
import arch.util.JsonUtil;

/*  27:    */
/*  28:    */public class BusinessTest1
/* 29: */implements Runnable
/* 30: */{
	/* 31: 39 */private static Logger log = Logger.getLogger(BusinessTest1.class);
	/* 32: */private WxMessage mess;
	/* 33: */private BusinessTest busTest;
	/* 34: 46 */private ProcessEngine processEngine = Configuration.getProcessEngine();
	/* 35: 48 */private ProcessInstance processInstance = null;
	/* 36: 50 */private ExecutionService executionService = this.processEngine.getExecutionService();
	/* 37: 52 */private BusinessBaseDao bussDao = new BusinessBaseDaoImpl();
	/* 38: */private String nextStep;
	/* 39: 55 */JbpmUtil jbpmUtil = new JbpmUtil(this.processEngine);
	/* 40: */private NoticeState bssType;
	/* 41: 58 */private String content = "";

	/* 42: */
	/* 43: */public BusinessTest1(WxMessage mess)
	/* 44: */{
		/* 45: 65 */this.mess = mess;
		/* 46: */}

	/* 47: */
	/* 48: */public void run()
	/* 49: */{
		/* 50: 74 */log.debug(JsonUtil.parseToJSON(this.mess));
		/* 51: */
		/* 52: 76 */Connection conn = null;
		/* 53: */try
		/* 54: */{
			/* 55: 78 */conn = DbcpConnection.getConnection();
			/* 56: */
			/* 57: 80 */this.busTest = new BusinessTest();
			/* 58: 81 */this.busTest.setBssId(this.mess.getBusinessId());
			/* 59: 82 */this.busTest.setProcessInstanceId(this.mess.getProcessInstanceId());
			/* 60: 83 */this.busTest.setOpenId(this.mess.getOpenId());
			/* 61: 84 */this.busTest.setWechatId(this.mess.getWechatId());
			/* 62: 85 */this.busTest.setTitle(this.mess.getBusinessId() + "_" + DateUtil.dateToString(new Date()));
			/* 63: */
			/* 64: */
			/* 65: 88 */Map<String, Object> busMap = new HashMap();
			/* 66: 89 */busMap.put("buss", this.busTest);
			/* 67: 90 */this.bssType = this.mess.getBssType();
			/* 68: 91 */switch (this.bssType)
			/* 69: */{
			/* 70: */case END:
				/* 71: 93 */
				log.debug(this.mess.getBusinessId());
				/* 72: */
				/* 73: 95 */
				log.debug("开始启动");
				/* 74: 96 */
				this.processInstance = this.jbpmUtil.startProcessInstanceByKey(this.mess.getBusinessId(), busMap);
				/* 75: */
				/* 76: 98 */
				this.busTest.setNextStep(BusinessUtils.nextStep(this.processInstance));
				/* 77: 99 */
				this.busTest.setProcessInstanceId(this.processInstance.getId());
				/* 78:100 */
				this.bussDao.addProcessInfo(this.busTest, conn);
				/* 79: */
				/* 80:102 */
				String content = "请输入：1转客服，0不转客服。";
				/* 81:103 */
				BusinessUtils.sendBusinessTextMsg(this.mess, content);
				/* 82:104 */
				log.debug("流程开启");
				/* 83:105 */break;
			/* 84: */case RUNNING:
				/* 85:108 */
				log.debug("____结束流程实例ID" + this.mess.getProcessInstanceId());
				/* 86: */
				/* 87:110 */
				this.executionService.setVariable(this.mess.getProcessInstanceId(), "destroy", "1");
				/* 88:111 */
				this.jbpmUtil.endProcessInstance(this.mess.getProcessInstanceId());
				/* 89: */
				/* 90: */
				/* 91:114 */
				BusinessUtils.updNextStep(conn, "", "2", this.busTest);
				/* 92: */
				/* 93:116 */
				log.debug("流程销毁");
				/* 94:117 */break;
			/* 95: */default:
				/* 96:121 */
				this.nextStep = this.bussDao.nextStep(this.busTest, conn);
				/* 97:123 */
				if ("接收消息".equals(this.nextStep))
				/* 98: */{
					/* 99:125 */if (!MsgType.TEXT.equals(this.mess.getMsgType()))
					/* 100: */{
						/* 101:126 */content = "请输入：1转客服，0不转客服。";
						/* 102:127 */BusinessUtils.sendBusinessTextMsg(this.mess, content);
						/* 103: */}
					/* 104:131 */else if (!isContentTrue(this.mess.getContent()))
					/* 105: */{
						/* 106:132 */content = "请输入：1转客服，0不转客服。";
						/* 107:133 */BusinessUtils.sendBusinessTextMsg(this.mess, content);
						/* 108: */}
					/* 109: */else
					/* 110: */{
						/* 111:137 */busMap.put("content", this.mess.getContent());
						/* 112:138 */this.processInstance = this.jbpmUtil.signalExecutionByIdVal(
								this.mess.getProcessInstanceId(), busMap);
						/* 113:139 */System.out.println(this.executionService.getVariable(this.processInstance.getId(),
								"buss"));
						/* 114: */}
					/* 115: */}
				/* 116:141 */else if ("客服消息".equals(this.nextStep)) {
					/* 117:144 */if (NoticeState.SERVICE == this.mess.getBssType())
					/* 118: */{
						/* 119:146 */this.processInstance = this.jbpmUtil.signalExecutionById(this.mess
								.getProcessInstanceId());
						/* 120: */
						/* 121:148 */content = "客服消息";
						/* 122:149 */BusinessUtils.sendBusinessTextMsg(this.mess, content);
						/* 123: */
						/* 124:151 */log.debug("客服消息---");
						/* 125: */
						/* 126:153 */BusinessUtils.updNextStep(conn, "", "2", this.busTest);
						/* 127: */
						/* 128:155 */log.debug("流程正常结束---");
						/* 129: */}
					/* 130: */}
				/* 131: */break;
			/* 132: */
			}
			/* 133:192 */conn.commit();
			/* 134: */}
		/* 135: */catch (SQLException e)
		/* 136: */{
			/* 137: */try
			/* 138: */{
				/* 139:195 */conn.rollback();
				/* 140: */}
			/* 141: */catch (SQLException e1)
			/* 142: */{
				/* 143:197 */log.fatal("业务流程回滚失败", e1);
				/* 144: */}
			/* 145:199 */log.fatal("业务流程执行失败", e);
			/* 146: */}
		/* 147: */finally
		/* 148: */{
			/* 149:201 */DbcpConnection.close(conn, null, null);
			/* 150: */}
		/* 151: */}

	/* 152: */
	/* 153: */private boolean isContentTrue(String content)
	/* 154: */{
		/* 155:213 */Pattern pattern = Pattern.compile("0|1");
		/* 156:214 */Matcher matcher = pattern.matcher(content);
		/* 157:215 */boolean rs = matcher.matches();
		/* 158:216 */if (rs) {
			/* 159:217 */return true;
			/* 160: */}
		/* 161:219 */return false;
		/* 162: */}
	/* 163: */
}

/*
 * Location: C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * 
 * Qualified Name: arch.jbpm.processFlow.BusinessTest1
 * 
 * JD-Core Version: 0.7.0.1
 */