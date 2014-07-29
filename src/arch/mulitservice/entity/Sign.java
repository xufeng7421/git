/*  1:   */ package arch.mulitservice.entity;
/*  2:   */ 
/*  3:   */ import arch.entity.type.NoticeState;
/*  4:   */ import java.io.Serializable;
/*  5:   */ 
/*  6:   */ public class Sign
/*  7:   */   implements Serializable
/*  8:   */ {
/*  9:   */   private static final long serialVersionUID = 1L;
/* 10:   */   private NoticeState type;
/* 11:   */   private String serviceId;
/* 12:   */   private String wechatId;
/* 13:   */   
/* 14:   */   public NoticeState getType()
/* 15:   */   {
/* 16:18 */     return this.type;
/* 17:   */   }
/* 18:   */   
/* 19:   */   public void setType(NoticeState type)
/* 20:   */   {
/* 21:21 */     this.type = type;
/* 22:   */   }
/* 23:   */   
/* 24:   */   public String getServiceId()
/* 25:   */   {
/* 26:24 */     return this.serviceId;
/* 27:   */   }
/* 28:   */   
/* 29:   */   public void setServiceId(String serviceId)
/* 30:   */   {
/* 31:27 */     this.serviceId = serviceId;
/* 32:   */   }
/* 33:   */   
/* 34:   */   public String getWechatId()
/* 35:   */   {
/* 36:30 */     return this.wechatId;
/* 37:   */   }
/* 38:   */   
/* 39:   */   public void setWechatId(String wechatId)
/* 40:   */   {
/* 41:33 */     this.wechatId = wechatId;
/* 42:   */   }
/* 43:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.mulitservice.entity.Sign
 * JD-Core Version:    0.7.0.1
 */