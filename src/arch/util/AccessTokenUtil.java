/*   1:    */ package arch.util;
/*   2:    */ 
/*   3:    */ import arch.entity.AccessToken;
/*   4:    */ import arch.properties.QueueProperties;
/*   5:    */ import arch.properties.UtilProperties;
/*   6:    */ import java.sql.PreparedStatement;
/*   7:    */ import java.sql.ResultSet;
/*   8:    */ import java.sql.SQLException;
/*   9:    */ import java.util.Date;
/*  10:    */ import java.util.Random;
/*  11:    */ import javax.jms.Destination;
/*  12:    */ import javax.jms.JMSException;
/*  13:    */ import javax.jms.Message;
/*  14:    */ import javax.jms.MessageConsumer;
/*  15:    */ import javax.jms.MessageProducer;
/*  16:    */ import javax.jms.Session;
/*  17:    */ import javax.jms.TextMessage;
/*  18:    */ import org.apache.commons.lang.StringUtils;
/*  19:    */ import org.apache.log4j.Logger;
/*  20:    */ 
/*  21:    */ public class AccessTokenUtil
/*  22:    */ {
/*  23: 25 */   private static Logger log = Logger.getLogger(AccessTokenUtil.class);
/*  24:    */   
/*  25:    */   public static AccessToken getToken(String wechatId)
/*  26:    */   {
/*  27: 28 */     java.sql.Connection conn = null;
/*  28: 29 */     PreparedStatement ps = null;
/*  29: 30 */     ResultSet rs = null;
/*  30: 31 */     AccessToken token = null;
/*  31:    */     try
/*  32:    */     {
/*  33: 33 */       conn = DbcpConnection.getConnection();
/*  34: 34 */       String sql = "SELECT APP_ID,APP_SECRET,ACCESS_TOKEN,UPDATE_TIME FROM  M_WEIXIN_CONFIG  WHERE WECHAT_ID = ? ";
/*  35: 35 */       ps = conn.prepareStatement(sql);
/*  36: 36 */       ps.setString(1, wechatId);
/*  37: 37 */       rs = ps.executeQuery();
/*  38: 38 */       if (rs.next())
/*  39:    */       {
/*  40: 39 */         token = new AccessToken();
/*  41: 40 */         token.setWechatId(wechatId);
/*  42: 41 */         token.setAppId(rs.getString("APP_ID"));
/*  43: 42 */         token.setAppSecret(rs.getString("APP_SECRET"));
/*  44: 43 */         token.setAccessToken(rs.getString("ACCESS_TOKEN"));
/*  45: 44 */         token.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
/*  46:    */       }
/*  47:    */     }
/*  48:    */     catch (Exception e)
/*  49:    */     {
/*  50: 47 */       log.fatal("查询Accesstoken异常", e);
/*  51:    */     }
/*  52:    */     finally
/*  53:    */     {
/*  54: 49 */       DbcpConnection.close(conn, ps, rs);
/*  55:    */     }
/*  56: 51 */     return token;
/*  57:    */   }
/*  58:    */   
/*  59:    */   public static String getAccessToken(String wechatId)
/*  60:    */   {
/*  61: 56 */     AccessToken token = getToken(wechatId);
/*  62: 57 */     if (token != null)
/*  63:    */     {
/*  64: 58 */       long aging = UtilProperties.getTokenLimit().longValue();
/*  65: 59 */       if ((!StringUtils.isBlank(token.getAccessToken())) && (new Date().getTime() - token.getUpdateTime().getTime() < aging)) {
/*  66: 60 */         return token.getAccessToken();
/*  67:    */       }
/*  68: 62 */       return requestAccessToken(wechatId);
/*  69:    */     }
/*  70: 65 */     return null;
/*  71:    */   }
/*  72:    */   
/*  73:    */   private static String createRandomString()
/*  74:    */   {
/*  75: 69 */     Random random = new Random(System.currentTimeMillis());
/*  76: 70 */     long randomLong = random.nextLong();
/*  77: 71 */     return Long.toHexString(randomLong);
/*  78:    */   }
/*  79:    */   
/*  80:    */   public static String requestAccessToken(String wechatId)
/*  81:    */   {
/*  82: 75 */     javax.jms.Connection connection = null;
/*  83:    */     
/*  84: 77 */     String token = null;
/*  85:    */     try
/*  86:    */     {
/*  87: 79 */       connection = MqUtil.getConnection();
/*  88: 80 */       Session session = connection.createSession(false, 1);
/*  89: 81 */       Destination adminQueue = session.createQueue(QueueProperties.getAccessTokenQueue());
/*  90: 82 */       MessageProducer producer = session.createProducer(adminQueue);
/*  91: 83 */       producer.setDeliveryMode(1);
/*  92: 84 */       Destination tempDest = session.createTemporaryQueue();
/*  93: 85 */       MessageConsumer responseConsumer = session.createConsumer(tempDest);
/*  94: 86 */       TextMessage txtMessage = session.createTextMessage();
/*  95: 87 */       txtMessage.setText(wechatId);
/*  96: 88 */       txtMessage.setJMSReplyTo(tempDest);
/*  97: 89 */       String correlationId = createRandomString();
/*  98: 90 */       txtMessage.setJMSCorrelationID(correlationId);
/*  99: 91 */       producer.send(txtMessage);
/* 100: 92 */       Message message = responseConsumer.receive();
/* 101: 93 */       if ((message instanceof TextMessage))
/* 102:    */       {
/* 103: 94 */         TextMessage textMessage = (TextMessage)message;
/* 104: 95 */         log.debug("重新获取的accesstoken->" + textMessage.getText());
/* 105: 96 */         token = textMessage.getText();
/* 106: 97 */         session.close();
/* 107:    */       }
/* 108:    */     }
/* 109:    */     catch (JMSException e)
/* 110:    */     {
/* 111:100 */       log.fatal("accesstoken队列异常", e);
/* 112:    */     }
/* 113:    */     finally
/* 114:    */     {
/* 115:102 */       MqUtil.closeConnection(connection);
/* 116:    */     }
/* 117:104 */     return token;
/* 118:    */   }
/* 119:    */   
/* 120:    */   public static void updAccessToken(AccessToken token)
/* 121:    */   {
/* 122:108 */     log.debug("更新accesstoken");
/* 123:109 */     java.sql.Connection conn = null;
/* 124:110 */     PreparedStatement ps = null;
/* 125:    */     try
/* 126:    */     {
/* 127:112 */       conn = DbcpConnection.getConnection();
/* 128:113 */       String sql = "UPDATE M_WEIXIN_CONFIG SET ACCESS_TOKEN = ? ,UPDATE_TIME=SYSDATE()  WHERE WECHAT_ID = ? ";
/* 129:114 */       ps = conn.prepareStatement(sql);
/* 130:115 */       ps.setString(1, token.getAccessToken());
/* 131:116 */       ps.setString(2, token.getWechatId());
/* 132:117 */       ps.executeUpdate();
/* 133:118 */       conn.commit();
/* 134:119 */       log.info("update accessToken" + token.getAccessToken());
/* 135:    */     }
/* 136:    */     catch (Exception e)
/* 137:    */     {
/* 138:121 */       log.fatal("更新AccessToken异常", e);
/* 139:    */       try
/* 140:    */       {
/* 141:123 */         conn.rollback();
/* 142:    */       }
/* 143:    */       catch (SQLException e1)
/* 144:    */       {
/* 145:125 */         log.fatal("更新AccessToken回滚异常", e1);
/* 146:    */       }
/* 147:    */     }
/* 148:    */     finally
/* 149:    */     {
/* 150:129 */       DbcpConnection.close(conn, ps, null);
/* 151:    */     }
/* 152:    */   }
/* 153:    */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.util.AccessTokenUtil
 * JD-Core Version:    0.7.0.1
 */