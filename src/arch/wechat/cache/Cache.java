/*  1:   */ package arch.wechat.cache;
/*  2:   */ 
/*  3:   */ import java.util.Date;
/*  4:   */ 
/*  5:   */ public class Cache
/*  6:   */ {
/*  7:   */   private String businessId;
/*  8:   */   private Long date;
/*  9:   */   
/* 10:   */   public Cache() {}
/* 11:   */   
/* 12:   */   public Cache(String businessId)
/* 13:   */   {
/* 14:14 */     this.businessId = businessId;
/* 15:15 */     this.date = Long.valueOf(new Date().getTime());
/* 16:   */   }
/* 17:   */   
/* 18:   */   public String getBusinessId()
/* 19:   */   {
/* 20:18 */     return this.businessId;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public void setBusinessId(String businessId)
/* 24:   */   {
/* 25:21 */     this.businessId = businessId;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public Long getDate()
/* 29:   */   {
/* 30:24 */     return this.date;
/* 31:   */   }
/* 32:   */   
/* 33:   */   public void setDate(Long date)
/* 34:   */   {
/* 35:27 */     this.date = date;
/* 36:   */   }
/* 37:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.wechat.cache.Cache
 * JD-Core Version:    0.7.0.1
 */