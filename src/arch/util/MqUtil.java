/*  1:   */ package arch.util;
/*  2:   */ 
/*  3:   */ import arch.properties.ActiveMQProperties;
/*  4:   */ import javax.jms.Connection;
/*  5:   */ import javax.jms.ConnectionFactory;
/*  6:   */ import javax.jms.JMSException;
/*  7:   */ import org.apache.activemq.ActiveMQConnectionFactory;
/*  8:   */ import org.apache.log4j.Logger;
/*  9:   */ 
/* 10:   */ public class MqUtil
/* 11:   */ {
/* 12:13 */   private static Logger log = Logger.getLogger(MqUtil.class);
/* 13:   */   
/* 14:   */   public static Connection getConnection()
/* 15:   */   {
/* 16:16 */     Connection connection = null;
/* 17:   */     try
/* 18:   */     {
/* 19:18 */       ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
/* 20:19 */         ActiveMQProperties.getUser(), 
/* 21:20 */         ActiveMQProperties.getPassword(), 
/* 22:21 */         ActiveMQProperties.getBrokerUrl());
/* 23:22 */       connection = connectionFactory.createConnection();
/* 24:23 */       connection.start();
/* 25:   */     }
/* 26:   */     catch (JMSException e)
/* 27:   */     {
/* 28:26 */       log.fatal("MqConnection链接异常", e);
/* 29:   */     }
/* 30:28 */     return connection;
/* 31:   */   }
/* 32:   */   
/* 33:   */   public static void closeConnection(Connection connection)
/* 34:   */   {
/* 35:   */     try
/* 36:   */     {
/* 37:32 */       if (connection != null) {
/* 38:33 */         connection.close();
/* 39:   */       }
/* 40:   */     }
/* 41:   */     catch (JMSException e)
/* 42:   */     {
/* 43:36 */       log.fatal("MqConnection关闭异常", e);
/* 44:   */     }
/* 45:   */   }
/* 46:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.util.MqUtil
 * JD-Core Version:    0.7.0.1
 */