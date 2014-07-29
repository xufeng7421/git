/*  1:   */ package arch.entity;
/*  2:   */ 
/*  3:   */ public class Item
/*  4:   */ {
/*  5:   */   private String title;
/*  6:   */   private String description;
/*  7:   */   private String picUrl;
/*  8:   */   private String url;
/*  9:   */   
/* 10:   */   public Item() {}
/* 11:   */   
/* 12:   */   public Item(String title, String description, String picUrl, String url)
/* 13:   */   {
/* 14:16 */     this.title = title;
/* 15:17 */     this.description = description;
/* 16:18 */     this.picUrl = picUrl;
/* 17:19 */     this.url = url;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public String getTitle()
/* 21:   */   {
/* 22:23 */     return this.title;
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void setTitle(String title)
/* 26:   */   {
/* 27:26 */     this.title = title;
/* 28:   */   }
/* 29:   */   
/* 30:   */   public String getDescription()
/* 31:   */   {
/* 32:30 */     return this.description;
/* 33:   */   }
/* 34:   */   
/* 35:   */   public void setDescription(String description)
/* 36:   */   {
/* 37:34 */     this.description = description;
/* 38:   */   }
/* 39:   */   
/* 40:   */   public String getPicUrl()
/* 41:   */   {
/* 42:38 */     return this.picUrl;
/* 43:   */   }
/* 44:   */   
/* 45:   */   public void setPicUrl(String picUrl)
/* 46:   */   {
/* 47:42 */     this.picUrl = picUrl;
/* 48:   */   }
/* 49:   */   
/* 50:   */   public String getUrl()
/* 51:   */   {
/* 52:46 */     return this.url;
/* 53:   */   }
/* 54:   */   
/* 55:   */   public void setUrl(String url)
/* 56:   */   {
/* 57:49 */     this.url = url;
/* 58:   */   }
/* 59:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.entity.Item
 * JD-Core Version:    0.7.0.1
 */