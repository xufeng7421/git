/*  1:   */ package arch.jbpm.entity;
/*  2:   */ 
/*  3:   */ import java.io.Serializable;
/*  4:   */ 
/*  5:   */ public class BusinessTest
/*  6:   */   extends JbpmBaseEntity
/*  7:   */   implements Serializable
/*  8:   */ {
/*  9:   */   private static final long serialVersionUID = 1L;
/* 10:   */   private String content;
/* 11:   */   
/* 12:   */   public String getContent()
/* 13:   */   {
/* 14:23 */     return this.content;
/* 15:   */   }
/* 16:   */   
/* 17:   */   public void setContent(String content)
/* 18:   */   {
/* 19:26 */     this.content = content;
/* 20:   */   }
/* 21:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.jbpm.entity.BusinessTest
 * JD-Core Version:    0.7.0.1
 */