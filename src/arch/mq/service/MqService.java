/*   1:    */ package arch.mq.service;
/*   2:    */ 
/*   3:    */ import java.io.Serializable;
/*   4:    */ import java.util.Random;
/*   5:    */ import javax.jms.Connection;
/*   6:    */ import javax.jms.Destination;
/*   7:    */ import javax.jms.JMSException;
/*   8:    */ import javax.jms.Message;
/*   9:    */ import javax.jms.MessageConsumer;
/*  10:    */ import javax.jms.MessageListener;
/*  11:    */ import javax.jms.MessageProducer;
/*  12:    */ import javax.jms.ObjectMessage;
/*  13:    */ import javax.jms.Queue;
/*  14:    */ import javax.jms.Session;
/*  15:    */ import javax.jms.TextMessage;
/*  16:    */ import org.apache.activemq.pool.PooledConnectionFactory;
/*  17:    */ import org.apache.log4j.Logger;
/*  18:    */ 
/*  19:    */ public class MqService
/*  20:    */ {
/*  21: 27 */   private String accessToken = null;
/*  22: 28 */   private static Logger log = Logger.getLogger(MqService.class);
/*  23:    */   
/*  24:    */   public void send(Serializable msg, String que)
/*  25:    */     throws Exception
/*  26:    */   {
/*  27: 33 */     Connection connection = null;
/*  28: 34 */     Session session = null;
/*  29: 35 */     Queue queue = null;
/*  30: 36 */     MessageProducer producer = null;
/*  31: 37 */     ObjectMessage message = null;
/*  32:    */     try
/*  33:    */     {
/*  34: 41 */       connection = MQPooledConnectionFactory.getPooledConnectionFactory().createConnection();
/*  35: 42 */       session = connection.createSession(false, 1);
/*  36: 43 */       queue = session.createQueue(que);
/*  37: 44 */       producer = session.createProducer(queue);
/*  38:    */       
/*  39: 46 */       connection.start();
/*  40:    */     }
/*  41:    */     catch (Exception e)
/*  42:    */     {
/*  43: 48 */       e.printStackTrace();
/*  44:    */     }
/*  45: 51 */     message = session.createObjectMessage(msg);
/*  46: 52 */     producer.send(message);
/*  47: 53 */     message.clearBody();
/*  48: 54 */     message.clearProperties();
/*  49:    */     
/*  50:    */ 
/*  51:    */ 
/*  52:    */ 
/*  53: 59 */     session.close();
/*  54:    */     
/*  55:    */ 
/*  56: 62 */     connection.close();
/*  57: 63 */     message = null;
/*  58:    */   }
/*  59:    */   
/*  60:    */   public static MqService getInstance()
/*  61:    */   {
/*  62: 69 */     return new MqService();
/*  63:    */   }
/*  64:    */   
/*  65:    */   public synchronized void sendMessage(Serializable msg, String que)
/*  66:    */     throws Exception
/*  67:    */   {
/*  68: 74 */     send(msg, que);
/*  69:    */   }
/*  70:    */   
/*  71:    */   public synchronized String reqAccessToken(String wechatId, String que)
/*  72:    */     throws Exception
/*  73:    */   {
/*  74: 80 */     return send(wechatId, que);
/*  75:    */   }
/*  76:    */   
/*  77:    */   public String send(String wechatId, String que)
/*  78:    */     throws Exception
/*  79:    */   {
/*  80: 85 */     Connection connection = null;
/*  81: 86 */     Session session = null;
/*  82: 87 */     Queue queue = null;
/*  83: 88 */     MessageProducer producer = null;
/*  84:    */     try
/*  85:    */     {
/*  86: 90 */       connection = MQPooledConnectionFactory.getPooledConnectionFactory().createConnection();
/*  87: 91 */       session = connection.createSession(false, 1);
/*  88: 92 */       queue = session.createQueue(que);
/*  89: 93 */       producer = session.createProducer(queue);
/*  90: 94 */       producer.setDeliveryMode(1);
/*  91:    */       
/*  92: 96 */       connection.start();
/*  93:    */     }
/*  94:    */     catch (Exception e)
/*  95:    */     {
/*  96: 98 */       e.printStackTrace();
/*  97:    */     }
/*  98:101 */     Destination tempDest = session.createTemporaryQueue();
/*  99:102 */     MessageConsumer responseConsumer = session.createConsumer(tempDest);
/* 100:103 */     responseConsumer.setMessageListener(new MessageListener()
/* 101:    */     {
/* 102:    */       public void onMessage(Message message)
/* 103:    */       {
/* 104:105 */         String messageText = null;
/* 105:    */         try
/* 106:    */         {
/* 107:107 */           if ((message instanceof TextMessage))
/* 108:    */           {
/* 109:108 */             TextMessage textMessage = (TextMessage)message;
/* 110:109 */             messageText = textMessage.getText();
/* 111:110 */             MqService.log.debug(messageText);
/* 112:111 */             MqService.this.accessToken = messageText;
/* 113:    */           }
/* 114:    */         }
/* 115:    */         catch (JMSException localJMSException) {}
/* 116:    */       }
/* 117:119 */     });
/* 118:120 */     TextMessage txtMessage = session.createTextMessage();
/* 119:121 */     txtMessage.setText(wechatId);
/* 120:122 */     txtMessage.setJMSReplyTo(tempDest);
/* 121:123 */     String correlationId = createRandomString();
/* 122:124 */     txtMessage.setJMSCorrelationID(correlationId);
/* 123:125 */     producer.send(txtMessage);
/* 124:    */     
/* 125:127 */     txtMessage.clearBody();
/* 126:128 */     txtMessage.clearProperties();
/* 127:    */     
/* 128:130 */     connection.close();
/* 129:131 */     txtMessage = null;
/* 130:132 */     return this.accessToken;
/* 131:    */   }
/* 132:    */   
/* 133:    */   private String createRandomString()
/* 134:    */   {
/* 135:136 */     Random random = new Random(System.currentTimeMillis());
/* 136:137 */     long randomLong = random.nextLong();
/* 137:138 */     return Long.toHexString(randomLong);
/* 138:    */   }
/* 139:    */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.mq.service.MqService
 * JD-Core Version:    0.7.0.1
 */