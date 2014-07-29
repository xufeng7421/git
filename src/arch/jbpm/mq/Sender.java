/*  1:   */ package arch.jbpm.mq;
/*  2:   */ 
/*  3:   */ import arch.entity.WxMessage;
/*  4:   */ import java.io.PrintStream;
/*  5:   */ import javax.jms.Connection;
/*  6:   */ import javax.jms.ConnectionFactory;
/*  7:   */ import javax.jms.Destination;
/*  8:   */ import javax.jms.MessageProducer;
/*  9:   */ import javax.jms.ObjectMessage;
/* 10:   */ import javax.jms.Session;
/* 11:   */ import javax.jms.TextMessage;
/* 12:   */ import org.apache.activemq.ActiveMQConnectionFactory;
/* 13:   */ 
/* 14:   */ public class Sender
/* 15:   */ {
/* 16:   */   private static final int SEND_NUMBER = 1000;
/* 17:   */   
/* 18:   */   public static void main(String[] args)
/* 19:   */   {
/* 20:25 */     Connection connection = null;
/* 21:   */     
/* 22:   */ 
/* 23:   */ 
/* 24:   */ 
/* 25:   */ 
/* 26:   */ 
/* 27:   */ 
/* 28:   */ 
/* 29:34 */     ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
/* 30:35 */       "system", 
/* 31:36 */       "manager", 
/* 32:37 */       "tcp://192.168.1.199:61616");
/* 33:   */     try
/* 34:   */     {
/* 35:40 */       connection = connectionFactory.createConnection();
/* 36:   */       
/* 37:42 */       connection.start();
/* 38:   */       
/* 39:   */ 
/* 40:   */ 
/* 41:   */ 
/* 42:   */ 
/* 43:   */ 
/* 44:49 */       Session session = connection.createSession(true, 
/* 45:50 */         2);
/* 46:   */       
/* 47:   */ 
/* 48:53 */       Destination destination = session.createQueue("BUSINESSQUEUE");
/* 49:   */       
/* 50:55 */       MessageProducer producer = session.createProducer(destination);
/* 51:   */       
/* 52:   */ 
/* 53:58 */       producer.setDeliveryMode(2);
/* 54:   */       
/* 55:   */ 
/* 56:   */ 
/* 57:   */ 
/* 58:   */ 
/* 59:64 */       sendMessage(session, producer);
/* 60:   */     }
/* 61:   */     catch (Exception e)
/* 62:   */     {
/* 63:69 */       e.printStackTrace();
/* 64:   */       try
/* 65:   */       {
/* 66:72 */         if (connection != null) {
/* 67:73 */           connection.close();
/* 68:   */         }
/* 69:   */       }
/* 70:   */       catch (Throwable localThrowable) {}
/* 71:   */     }
/* 72:   */     finally
/* 73:   */     {
/* 74:   */       try
/* 75:   */       {
/* 76:72 */         if (connection != null) {
/* 77:73 */           connection.close();
/* 78:   */         }
/* 79:   */       }
/* 80:   */       catch (Throwable localThrowable1) {}
/* 81:   */     }
/* 82:   */   }
/* 83:   */   
/* 84:   */   public static void sendMessage(Session session, MessageProducer producer)
/* 85:   */     throws Exception
/* 86:   */   {
/* 87:82 */     TextMessage message = session
/* 88:83 */       .createTextMessage("ActiveMq window发送的消息sdf");
/* 89:   */     
/* 90:85 */     WxMessage test = new WxMessage();
/* 91:86 */     test.setContent("选择人工客服请输入1");
/* 92:   */     
/* 93:   */ 
/* 94:   */ 
/* 95:90 */     ObjectMessage oMess = session.createObjectMessage();
/* 96:91 */     oMess.setObject(test);
/* 97:   */     
/* 98:93 */     System.out.println("发送消息：ActiveMq 发送的消息");
/* 99:94 */     producer.send(oMess);
/* :0:95 */     session.commit();
/* :1:   */   }
/* :2:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.jbpm.mq.Sender
 * JD-Core Version:    0.7.0.1
 */