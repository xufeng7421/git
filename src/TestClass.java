/*  1:   */ import arch.entity.WxMessage;
/*  2:   */ import arch.entity.type.NoticeState;
/*  3:   */ import arch.mq.service.MqService;
/*  4:   */ import arch.mulitservice.entity.Sign;
/*  5:   */ import arch.properties.QueueProperties;
/*  6:   */ import java.io.PrintStream;
/*  7:   */ 
/*  8:   */ public class TestClass
/*  9:   */ {
/* 10:   */   public void sign()
/* 11:   */     throws Exception
/* 12:   */   {
/* 13:11 */     Sign sign = new Sign();
/* 14:12 */     sign.setServiceId("1");
/* 15:13 */     sign.setType(NoticeState.START);
/* 16:14 */     sign.setWechatId("1");
/* 17:15 */     MqService.getInstance().send(sign, QueueProperties.getServiceSignQueue());
/* 18:   */   }
/* 19:   */   
/* 20:   */   public void waitMan(String openId)
/* 21:   */     throws Exception
/* 22:   */   {
/* 23:19 */     WxMessage wxMessage = new WxMessage();
/* 24:20 */     wxMessage.setWechatId("1");
/* 25:21 */     wxMessage.setOpenId(openId);
/* 26:22 */     wxMessage.setMsgType("TEXT");
/* 27:23 */     MqService.getInstance().send(wxMessage, QueueProperties.getServiceReviceQueue());
/* 28:   */   }
/* 29:   */   
/* 30:   */   public void startBus()
/* 31:   */     throws Exception
/* 32:   */   {
/* 33:27 */     WxMessage wxMessage = new WxMessage();
/* 34:28 */     wxMessage.setWechatId("1");
/* 35:29 */     wxMessage.setOpenId("22");
/* 36:30 */     wxMessage.setBusinessId("testDecision");
/* 37:31 */     wxMessage.setBssType(NoticeState.START);
/* 38:32 */     wxMessage.setBssSendQueue("DECISIONSEND");
/* 39:33 */     MqService.getInstance().send(wxMessage, QueueProperties.getServiceReviceQueue());
/* 40:   */   }
/* 41:   */   
/* 42:   */   public static void main(String[] args)
/* 43:   */     throws Exception
/* 44:   */   {
/* 45:37 */     new TestClass().sign();
/* 46:38 */     new TestClass().waitMan("ojU4Jj5-Sc7vdMx5b8TuEn3vwW3g");
/* 47:39 */     new TestClass().waitMan("122234");
/* 48:40 */     new TestClass().waitMan("1222332");
/* 49:41 */     new TestClass().waitMan("1222432");
/* 50:42 */     new TestClass().waitMan("122132");
/* 51:   */     
/* 52:   */ 
/* 53:45 */     System.out.println("done");
/* 54:   */   }
/* 55:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     TestClass
 * JD-Core Version:    0.7.0.1
 */