/*  1:   */ package arch.mq.service;
/*  2:   */ 
/*  3:   */ import arch.properties.ActiveMQProperties;
/*  4:   */ import org.apache.activemq.ActiveMQConnectionFactory;
/*  5:   */ import org.apache.activemq.pool.PooledConnectionFactory;
/*  6:   */ import org.apache.log4j.Logger;
/*  7:   */ 
/*  8:   */ public class MQPooledConnectionFactory
/*  9:   */ {
/* 10:   */   private static PooledConnectionFactory pooledConnectionFactory;
/* 11:15 */   private static Logger log = Logger.getLogger(MQPooledConnectionFactory.class);
/* 12:   */   
/* 13:   */   static
/* 14:   */   {
/* 15:   */     try
/* 16:   */     {
/* 17:20 */       ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
/* 18:21 */       activeMQConnectionFactory.setUserName(ActiveMQProperties.getUser());
/* 19:22 */       activeMQConnectionFactory.setPassword(ActiveMQProperties.getPassword());
/* 20:23 */       activeMQConnectionFactory.setBrokerURL(ActiveMQProperties.getBrokerUrl());
/* 21:   */       
/* 22:25 */       pooledConnectionFactory = new PooledConnectionFactory(activeMQConnectionFactory);
/* 23:   */       
/* 24:27 */       pooledConnectionFactory.setMaximumActive(ActiveMQProperties.getMaximumActive().intValue());
/* 25:28 */       pooledConnectionFactory.setMaxConnections(ActiveMQProperties.getMaxConnections().intValue());
/* 26:   */     }
/* 27:   */     catch (Exception e)
/* 28:   */     {
/* 29:30 */       log.fatal("初始化mq链接池异常", e);
/* 30:   */     }
/* 31:   */   }
/* 32:   */   
/* 33:   */   public static PooledConnectionFactory getPooledConnectionFactory()
/* 34:   */   {
/* 35:37 */     return pooledConnectionFactory;
/* 36:   */   }
/* 37:   */   
/* 38:   */   public static void destory()
/* 39:   */   {
/* 40:44 */     pooledConnectionFactory.stop();
/* 41:   */   }
/* 42:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.mq.service.MQPooledConnectionFactory
 * JD-Core Version:    0.7.0.1
 */