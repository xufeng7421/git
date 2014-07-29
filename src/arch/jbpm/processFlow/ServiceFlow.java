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

/*  26:    */
/*  27:    */public class ServiceFlow
/* 28: */implements Runnable
/* 29: */{
	/* 30: 39 */private static Logger log = Logger.getLogger(ServiceFlow.class);
	/* 31: */private WxMessage mess;
	/* 32: */private BusinessTest busTest;
	/* 33: 46 */private ProcessEngine processEngine = Configuration.getProcessEngine();
	/* 34: 48 */private ProcessInstance processInstance = null;
	/* 35: 50 */private ExecutionService executionService = this.processEngine.getExecutionService();
	/* 36: 52 */private BusinessBaseDao bussDao = new BusinessBaseDaoImpl();
	/* 37: */private String nextStep;
	/* 38: 55 */JbpmUtil jbpmUtil = new JbpmUtil(this.processEngine);
	/* 39: */private NoticeState bssType;
	/* 40: 58 */private String content = "";

	/* 41: */
	/* 42: */public ServiceFlow(WxMessage mess)
	/* 43: */{
		/* 44: 65 */this.mess = mess;
		/* 45: */}

	/* 46: */
	/* 47: */public void run()
	/* 48: */{
		/* 49: 74 */log.debug(JsonUtil.parseToJSON(this.mess));
		/* 50: */
		/* 51: 76 */Connection conn = null;
		/* 52: */try
		/* 53: */{
			/* 54: 78 */conn = DbcpConnection.getConnection();
			/* 55: */
			/* 56: 80 */this.busTest = new BusinessTest();
			/* 57: 81 */this.busTest.setBssId(this.mess.getBusinessId());
			/* 58: 82 */this.busTest.setProcessInstanceId(this.mess.getProcessInstanceId());
			/* 59: 83 */this.busTest.setOpenId(this.mess.getOpenId());
			/* 60: 84 */this.busTest.setWechatId(this.mess.getWechatId());
			/* 61: 85 */this.busTest.setTitle(this.mess.getBusinessId() + "_" + DateUtil.dateToString(new Date()));
			/* 62: */
			/* 63: */
			/* 64: 88 */Map<String, Object> busMap = new HashMap();
			/* 65: 89 */busMap.put("buss", this.busTest);
			/* 66: 90 */this.bssType = this.mess.getBssType();
			/* 67: 91 */switch (this.bssType)
			/* 68: */{
			/* 69: */case END:
				/* 70: 93 */
				log.debug(this.mess.getBusinessId());
				/* 71: */
				/* 72: 95 */
				log.debug("开始启动");
				/* 73: 96 */
				this.processInstance = this.jbpmUtil.startProcessInstanceByKey(this.mess.getBusinessId(), busMap);
				/* 74: */
				/* 75: 98 */
				this.busTest.setNextStep(BusinessUtils.nextStep(this.processInstance));
				/* 76: 99 */
				this.busTest.setProcessInstanceId(this.processInstance.getId());
				/* 77:100 */
				this.bussDao.addProcessInfo(this.busTest, conn);
				/* 78: */
				/* 79:102 */
				String content = "请输入：1转客服，0不转客服。";
				/* 80:103 */
				BusinessUtils.sendBusinessTextMsg(this.mess, content);
				/* 81:104 */
				log.debug("流程开启");
				/* 82:105 */break;
			/* 83: */case RUNNING:
				/* 84:108 */
				log.debug("____结束流程实例ID" + this.mess.getProcessInstanceId());
				/* 85: */
				/* 86:110 */
				this.executionService.setVariable(this.mess.getProcessInstanceId(), "destroy", "0");
				/* 87:111 */
				this.jbpmUtil.endProcessInstance(this.mess.getProcessInstanceId());
				/* 88: */
				/* 89: */
				/* 90:114 */
				BusinessUtils.updNextStep(conn, "", "2", this.busTest);
				/* 91: */
				/* 92:116 */
				log.debug("流程销毁");
				/* 93:117 */break;
			/* 94: */default:
				/* 95:121 */
				this.nextStep = this.bussDao.nextStep(this.busTest, conn);
				/* 96:123 */
				if ("接收消息".equals(this.nextStep))
				/* 97: */{
					/* 98:125 */if (!MsgType.TEXT.equals(this.mess.getMsgType()))
					/* 99: */{
						/* 100:126 */content = "请输入：1转客服，0不转客服。";
						/* 101:127 */BusinessUtils.sendBusinessTextMsg(this.mess, content);
						/* 102: */}
					/* 103:131 */else if (!isContentTrue(this.mess.getContent()))
					/* 104: */{
						/* 105:132 */content = "请输入：1转客服，0不转客服。";
						/* 106:133 */BusinessUtils.sendBusinessTextMsg(this.mess, content);
						/* 107: */}
					/* 108: */else
					/* 109: */{
						/* 110:139 */busMap.put("wxMessage", this.mess);
						/* 111:140 */this.processInstance = this.jbpmUtil.signalExecutionByIdVal(
								this.mess.getProcessInstanceId(), busMap);
						/* 112: */}
					/* 113: */}
				/* 114:142 */else if ("客服消息".equals(this.nextStep))
				/* 115: */{
					/* 116:144 */this.processInstance = this.jbpmUtil.signalExecutionById(this.mess
							.getProcessInstanceId());
					/* 117: */
					/* 118: */
					/* 119: */
					/* 120: */
					/* 121: */
					/* 122: */
					/* 123: */
					/* 124:152 */BusinessUtils.updNextStep(conn, "", "2", this.busTest);
					/* 125: */
					/* 126:154 */log.debug("流程正常结束---");
					/* 127: */}
				/* 128: */break;
			/* 129: */
			}
			/* 130:187 */conn.commit();
			/* 131: */}
		/* 132: */catch (SQLException e)
		/* 133: */{
			/* 134: */try
			/* 135: */{
				/* 136:190 */conn.rollback();
				/* 137: */}
			/* 138: */catch (SQLException e1)
			/* 139: */{
				/* 140:192 */log.fatal("业务流程回滚失败", e1);
				/* 141: */}
			/* 142:194 */log.fatal("业务流程执行失败", e);
			/* 143: */}
		/* 144: */finally
		/* 145: */{
			/* 146:196 */DbcpConnection.close(conn, null, null);
			/* 147: */}
		/* 148: */}

	/* 149: */
	/* 150: */private boolean isContentTrue(String content)
	/* 151: */{
		/* 152:208 */Pattern pattern = Pattern.compile("0|1");
		/* 153:209 */Matcher matcher = pattern.matcher(content);
		/* 154:210 */boolean rs = matcher.matches();
		/* 155:211 */if (rs) {
			/* 156:212 */return true;
			/* 157: */}
		/* 158:214 */return false;
		/* 159: */}
	/* 160: */
}

/*
 * Location: C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * 
 * Qualified Name: arch.jbpm.processFlow.ServiceFlow
 * 
 * JD-Core Version: 0.7.0.1
 */