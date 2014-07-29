/*  1:   */ package arch.wechat.worker;
/*  2:   */ 
/*  3:   */ import arch.entity.ReplyEntity;
/*  4:   */ import arch.util.AccessTokenUtil;
/*  5:   */ import arch.wechat.api.WeChatAPI;
/*  6:   */ import java.io.PrintStream;
/*  7:   */ import org.apache.log4j.Logger;
/*  8:   */ 
/*  9:   */ public class ReSendWorker
/* 10:   */   implements Runnable
/* 11:   */ {
/* 12:10 */   private static Logger log = Logger.getLogger(ReSendWorker.class);
/* 13:12 */   private ReplyEntity reply = null;
/* 14:   */   
/* 15:   */   public ReSendWorker(ReplyEntity reply)
/* 16:   */   {
/* 17:15 */     this.reply = reply;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public void run()
/* 21:   */   {
/* 22:19 */     this.reply.setAccessToken(AccessTokenUtil.requestAccessToken(this.reply.getWechatId()));
/* 23:20 */     String errcode = WeChatAPI.postMsg(this.reply);
/* 24:21 */     System.out.println("errcode->" + errcode);
/* 25:22 */     switch (Integer.parseInt(errcode))
/* 26:   */     {
/* 27:   */     case 0: 
/* 28:24 */       log.debug("发送成功");
/* 29:25 */       break;
/* 30:   */     default: 
/* 31:27 */       log.error("【错误代码：】" + errcode);
/* 32:   */     }
/* 33:   */   }
/* 34:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.wechat.worker.ReSendWorker
 * JD-Core Version:    0.7.0.1
 */