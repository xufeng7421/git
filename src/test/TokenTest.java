/*  1:   */ package test;
/*  2:   */ 
/*  3:   */ import arch.properties.QueueProperties;
/*  4:   */ import arch.util.MqUtil;
/*  5:   */ import java.io.PrintStream;
/*  6:   */ import java.util.Random;
/*  7:   */ import javax.jms.Connection;
/*  8:   */ import javax.jms.Destination;
/*  9:   */ import javax.jms.JMSException;
/* 10:   */ import javax.jms.Message;
/* 11:   */ import javax.jms.MessageConsumer;
/* 12:   */ import javax.jms.MessageListener;
/* 13:   */ import javax.jms.MessageProducer;
/* 14:   */ import javax.jms.Session;
/* 15:   */ import javax.jms.TextMessage;
/* 16:   */ 
/* 17:   */ public class TokenTest
/* 18:   */ {
/* 19:   */   public static void main(String[] args)
/* 20:   */   {
/* 21:   */     try
/* 22:   */     {
/* 23:28 */       String wechatId = "1";
/* 24:29 */       final Connection connection = MqUtil.getConnection();
/* 25:30 */       Session session = connection.createSession(false, 1);
/* 26:31 */       Destination adminQueue = session.createQueue(QueueProperties.getAccessTokenQueue());
/* 27:   */       
/* 28:33 */       MessageProducer producer = session.createProducer(adminQueue);
/* 29:34 */       producer.setDeliveryMode(1);
/* 30:   */       
/* 31:36 */       Destination tempDest = session.createTemporaryQueue();
/* 32:37 */       MessageConsumer responseConsumer = session.createConsumer(tempDest);
/* 33:38 */       responseConsumer.setMessageListener(new MessageListener()
/* 34:   */       {
/* 35:   */         public void onMessage(Message message)
/* 36:   */         {
/* 37:40 */           System.out.println("------------------");
/* 38:41 */           if ((message instanceof TextMessage))
/* 39:   */           {
/* 40:42 */             TextMessage textMessage = (TextMessage)message;
/* 41:   */             try
/* 42:   */             {
/* 43:44 */               System.out.println("重新获取的acctoken-》" + textMessage.getText());
/* 44:45 */               TokenTest.this.close();
/* 45:46 */               connection.close();
/* 46:   */             }
/* 47:   */             catch (JMSException e)
/* 48:   */             {
/* 49:48 */               e.printStackTrace();
/* 50:   */             }
/* 51:   */           }
/* 52:   */         }
/* 53:54 */       });
/* 54:55 */       TextMessage txtMessage = session.createTextMessage();
/* 55:56 */       txtMessage.setText(wechatId);
/* 56:   */       
/* 57:58 */       txtMessage.setJMSReplyTo(tempDest);
/* 58:   */       
/* 59:60 */       String correlationId = createRandomString();
/* 60:61 */       txtMessage.setJMSCorrelationID(correlationId);
/* 61:62 */       producer.send(txtMessage);
/* 62:   */     }
/* 63:   */     catch (Exception e)
/* 64:   */     {
/* 65:64 */       e.printStackTrace();
/* 66:   */     }
/* 67:   */   }
/* 68:   */   
/* 69:   */   private static String createRandomString()
/* 70:   */   {
/* 71:70 */     Random random = new Random(System.currentTimeMillis());
/* 72:71 */     long randomLong = random.nextLong();
/* 73:72 */     return Long.toHexString(randomLong);
/* 74:   */   }
/* 75:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     test.TokenTest
 * JD-Core Version:    0.7.0.1
 */