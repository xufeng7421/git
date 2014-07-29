/*   1:    */package arch.listener;

/*   2:    */
/*   3:    */import java.util.List;
import java.util.TimerTask;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import arch.entity.PluginCase;
import arch.entity.StopedEntity;
import arch.mq.DB.service.QueueService;
import arch.properties.ActiveMQProperties;
import arch.util.DbcpConnection;
import arch.web.listener.InitListener;

/*  18:    */
/*  19:    */public class PluginListener
/* 20: */extends TimerTask
/* 21: */{
	/* 22: 33 */public static Logger log = Logger.getLogger(PluginListener.class);
	/* 23: 35 */private QueueService queueService = new QueueService();

	/* 24: */
	/* 25: */private void startedPlugin(PluginCase plugin, java.sql.Connection conn)
	/* 26: */{
		/* 27: */try
		/* 28: */{
			/* 29: 43 */Class<Runnable> bssClass = (Class<Runnable>) Class.forName(plugin.getBssClass());
			/* 30: 44 */Class<?> objClass = Class.forName(plugin.getObjClass());
			/* 31: 45 */Runnable run = new QueueListener(plugin.getCorePool().intValue(),
			/* 32: 46 */plugin.getMaxPool().intValue(),
			/* 33: 47 */bssClass,
			/* 34: 48 */objClass,
			/* 35: 49 */plugin.getReciveQueue());
			/* 36: 50 */Thread t = new Thread(run);
			/* 37: 51 */InitListener.threadlist.add(t);
			/* 38: 52 */t.start();
			/* 39: 53 */this.queueService.oldedPlugin(conn, plugin.getBusinessId());
			/* 40: */}
		/* 41: */catch (Exception e)
		/* 42: */{
			/* 43: 55 */log.fatal("启动插件出错", e);
			/* 44: */}
		/* 45: */}

	/* 46: */
	/* 47: */private void stopedPlugin(PluginCase plugin, java.sql.Connection conn)
	/* 48: */{
		/* 49: 65 */javax.jms.Connection connection = null;
		/* 50: */
		/* 51: */
		/* 52: */
		/* 53: 69 */ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
		/* 54: 70 */ActiveMQProperties.getUser(),
		/* 55: 71 */ActiveMQProperties.getPassword(),
		/* 56: 72 */ActiveMQProperties.getBrokerUrl());
		/* 57: */try
		/* 58: */{
			/* 59: 74 */connection = connectionFactory.createConnection();
			/* 60: 75 */connection.start();
			/* 61: 76 */Session session = connection.createSession(true,
			/* 62: 77 */2);
			/* 63: 78 */Destination destination = session.createQueue(plugin.getReciveQueue());
			/* 64: 79 */MessageProducer producer = session.createProducer(destination);
			/* 65: 80 */producer.setDeliveryMode(1);
			/* 66: 81 */StopedEntity message = new StopedEntity();
			/* 67: 82 */ObjectMessage oMess = session.createObjectMessage(message);
			/* 68: 83 */oMess.setObject(message);
			/* 69: 84 */producer.send(oMess);
			/* 70: 85 */session.commit();
			/* 71: 86 */this.queueService.oldedPlugin(conn, plugin.getBusinessId());
			/* 72: */}
		/* 73: */catch (Exception e)
		/* 74: */{
			/* 75: 88 */log.error("关闭业务插件出错");
			/* 76: */try
			/* 77: */{
				/* 78: 91 */if (connection != null) {
					/* 79: 92 */connection.close();
					/* 80: */}
				/* 81: */}
			/* 82: */catch (Throwable ignore)
			/* 83: */{
				/* 84: 94 */log.error("关闭连接出错");
				/* 85: */}
			/* 86: */}
		/* 87: */finally
		/* 88: */{
			/* 89: */try
			/* 90: */{
				/* 91: 91 */if (connection != null) {
					/* 92: 92 */connection.close();
					/* 93: */}
				/* 94: */}
			/* 95: */catch (Throwable ignore)
			/* 96: */{
				/* 97: 94 */log.error("关闭连接出错");
				/* 98: */}
			/* 99: */}
		/* 100: */}

	/* 101: */
	/* 102: */public void pluginChanged()
	/* 103: */{
		/* 104:102 */java.sql.Connection conn = null;
		/* 105: */try
		/* 106: */{
			/* 107:104 */conn = DbcpConnection.getConnection();
			/* 108:105 */List<PluginCase> list = this.queueService.findNewPlugin(conn);
			/* 109:106 */if (list.size() != 0) {
				/* 110:107 */for (PluginCase temp : list) {
					/* 111:108 */if ("1".equals(temp.getStatus())) {
						/* 112:109 */stopedPlugin(temp, conn);
						/* 113:110 */} else if ("0".equals(temp.getStatus())) {
						/* 114:111 */startedPlugin(temp, conn);
						/* 115: */} else {
						/* 116:113 */log.error("业务插件状态为出错      " + temp.getBusinessId() + " - " + temp.getStatus());
						/* 117: */}
					/* 118: */}
				/* 119: */}
			/* 120:117 */conn.commit();
			/* 121: */}
		/* 122: */catch (Exception e)
		/* 123: */{
			/* 124:119 */log.fatal("更新插件出错", e);
			/* 125: */}
		/* 126: */finally
		/* 127: */{
			/* 128:121 */DbcpConnection.close(conn, null, null);
			/* 129: */}
		/* 130: */}

	/* 131: */
	/* 132: */public void pluginInit()
	/* 133: */{
		/* 134:129 */java.sql.Connection conn = null;
		/* 135: */try
		/* 136: */{
			/* 137:131 */conn = DbcpConnection.getConnection();
			/* 138:132 */List<PluginCase> list = this.queueService.findUsingPlugin(conn);
			/* 139:133 */if (list.size() != 0) {
				/* 140:134 */for (PluginCase temp : list) {
					/* 141:135 */startedPlugin(temp, conn);
					/* 142: */}
				/* 143: */}
			/* 144:138 */conn.commit();
			/* 145: */}
		/* 146: */catch (Exception e)
		/* 147: */{
			/* 148:140 */log.fatal("更新插件出错", e);
			/* 149: */}
		/* 150: */finally
		/* 151: */{
			/* 152:142 */DbcpConnection.close(conn, null, null);
			/* 153: */}
		/* 154: */}

	/* 155: */
	/* 156: */public void run()
	/* 157: */{
		/* 158:149 */pluginChanged();
		/* 159: */}
	/* 160: */
}

/*
 * Location: C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * 
 * Qualified Name: arch.listener.PluginListener
 * 
 * JD-Core Version: 0.7.0.1
 */