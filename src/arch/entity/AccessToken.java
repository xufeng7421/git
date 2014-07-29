/*  1:   */ package arch.entity;
/*  2:   */ 
/*  3:   */ import java.util.Date;
/*  4:   */ 
/*  5:   */ public class AccessToken
/*  6:   */ {
/*  7:   */   private String wechatId;
/*  8:   */   private String appId;
/*  9:   */   private String appSecret;
/* 10:   */   private String accessToken;
/* 11:   */   private Date updateTime;
/* 12:   */   
/* 13:   */   public String getWechatId()
/* 14:   */   {
/* 15:14 */     return this.wechatId;
/* 16:   */   }
/* 17:   */   
/* 18:   */   public void setWechatId(String wechatId)
/* 19:   */   {
/* 20:17 */     this.wechatId = wechatId;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public String getAppId()
/* 24:   */   {
/* 25:20 */     return this.appId;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public void setAppId(String appId)
/* 29:   */   {
/* 30:23 */     this.appId = appId;
/* 31:   */   }
/* 32:   */   
/* 33:   */   public String getAppSecret()
/* 34:   */   {
/* 35:26 */     return this.appSecret;
/* 36:   */   }
/* 37:   */   
/* 38:   */   public void setAppSecret(String appSecret)
/* 39:   */   {
/* 40:29 */     this.appSecret = appSecret;
/* 41:   */   }
/* 42:   */   
/* 43:   */   public String getAccessToken()
/* 44:   */   {
/* 45:32 */     return this.accessToken;
/* 46:   */   }
/* 47:   */   
/* 48:   */   public void setAccessToken(String accessToken)
/* 49:   */   {
/* 50:35 */     this.accessToken = accessToken;
/* 51:   */   }
/* 52:   */   
/* 53:   */   public Date getUpdateTime()
/* 54:   */   {
/* 55:38 */     return this.updateTime;
/* 56:   */   }
/* 57:   */   
/* 58:   */   public void setUpdateTime(Date updateTime)
/* 59:   */   {
/* 60:41 */     this.updateTime = updateTime;
/* 61:   */   }
/* 62:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.entity.AccessToken
 * JD-Core Version:    0.7.0.1
 */