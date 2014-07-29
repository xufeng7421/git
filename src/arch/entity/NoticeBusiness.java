/*  1:   */ package arch.entity;
/*  2:   */ 
/*  3:   */ import arch.entity.type.NoticeState;
/*  4:   */ import java.io.Serializable;
/*  5:   */ 
/*  6:   */ public class NoticeBusiness
/*  7:   */   implements Serializable
/*  8:   */ {
/*  9:   */   private static final long serialVersionUID = 1L;
/* 10:   */   private String openId;
/* 11:   */   private String businessId;
/* 12:   */   private NoticeState state;
/* 13:   */   
/* 14:   */   public NoticeBusiness() {}
/* 15:   */   
/* 16:   */   public NoticeBusiness(String openId, String businessId, NoticeState state)
/* 17:   */   {
/* 18:25 */     this.openId = openId;
/* 19:26 */     this.businessId = businessId;
/* 20:27 */     this.state = state;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public String getBusinessId()
/* 24:   */   {
/* 25:32 */     return this.businessId;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public void setBusinessId(String businessId)
/* 29:   */   {
/* 30:36 */     this.businessId = businessId;
/* 31:   */   }
/* 32:   */   
/* 33:   */   public String getOpenId()
/* 34:   */   {
/* 35:40 */     return this.openId;
/* 36:   */   }
/* 37:   */   
/* 38:   */   public void setOpenId(String openId)
/* 39:   */   {
/* 40:43 */     this.openId = openId;
/* 41:   */   }
/* 42:   */   
/* 43:   */   public NoticeState getState()
/* 44:   */   {
/* 45:47 */     return this.state;
/* 46:   */   }
/* 47:   */   
/* 48:   */   public void setState(NoticeState state)
/* 49:   */   {
/* 50:51 */     this.state = state;
/* 51:   */   }
/* 52:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.entity.NoticeBusiness
 * JD-Core Version:    0.7.0.1
 */