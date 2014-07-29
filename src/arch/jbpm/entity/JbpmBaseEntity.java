/*  1:   */ package arch.jbpm.entity;
/*  2:   */ 
/*  3:   */ import java.io.Serializable;
/*  4:   */ 
/*  5:   */ public class JbpmBaseEntity
/*  6:   */   implements Serializable
/*  7:   */ {
/*  8:   */   private static final long serialVersionUID = 1L;
/*  9:   */   private String openId;
/* 10:   */   private String bssId;
/* 11:   */   private String status;
/* 12:   */   private String wechatId;
/* 13:   */   private String title;
/* 14:   */   private String nextStep;
/* 15:   */   private String processInstanceId;
/* 16:   */   private String approval;
/* 17:   */   private String approverId;
/* 18:   */   private String comment;
/* 19:   */   
/* 20:   */   public String getOpenId()
/* 21:   */   {
/* 22:27 */     return this.openId;
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void setOpenId(String openId)
/* 26:   */   {
/* 27:30 */     this.openId = openId;
/* 28:   */   }
/* 29:   */   
/* 30:   */   public String getBssId()
/* 31:   */   {
/* 32:33 */     return this.bssId;
/* 33:   */   }
/* 34:   */   
/* 35:   */   public void setBssId(String bssId)
/* 36:   */   {
/* 37:36 */     this.bssId = bssId;
/* 38:   */   }
/* 39:   */   
/* 40:   */   public String getStatus()
/* 41:   */   {
/* 42:39 */     return this.status;
/* 43:   */   }
/* 44:   */   
/* 45:   */   public void setStatus(String status)
/* 46:   */   {
/* 47:42 */     this.status = status;
/* 48:   */   }
/* 49:   */   
/* 50:   */   public String getWechatId()
/* 51:   */   {
/* 52:45 */     return this.wechatId;
/* 53:   */   }
/* 54:   */   
/* 55:   */   public void setWechatId(String wechatId)
/* 56:   */   {
/* 57:48 */     this.wechatId = wechatId;
/* 58:   */   }
/* 59:   */   
/* 60:   */   public String getTitle()
/* 61:   */   {
/* 62:51 */     return this.title;
/* 63:   */   }
/* 64:   */   
/* 65:   */   public void setTitle(String title)
/* 66:   */   {
/* 67:54 */     this.title = title;
/* 68:   */   }
/* 69:   */   
/* 70:   */   public String getNextStep()
/* 71:   */   {
/* 72:57 */     return this.nextStep;
/* 73:   */   }
/* 74:   */   
/* 75:   */   public void setNextStep(String nextStep)
/* 76:   */   {
/* 77:60 */     this.nextStep = nextStep;
/* 78:   */   }
/* 79:   */   
/* 80:   */   public String getApproval()
/* 81:   */   {
/* 82:63 */     return this.approval;
/* 83:   */   }
/* 84:   */   
/* 85:   */   public void setApproval(String approval)
/* 86:   */   {
/* 87:66 */     this.approval = approval;
/* 88:   */   }
/* 89:   */   
/* 90:   */   public String getApproverId()
/* 91:   */   {
/* 92:69 */     return this.approverId;
/* 93:   */   }
/* 94:   */   
/* 95:   */   public void setApproverId(String approverId)
/* 96:   */   {
/* 97:72 */     this.approverId = approverId;
/* 98:   */   }
/* 99:   */   
/* :0:   */   public String getComment()
/* :1:   */   {
/* :2:75 */     return this.comment;
/* :3:   */   }
/* :4:   */   
/* :5:   */   public void setComment(String comment)
/* :6:   */   {
/* :7:78 */     this.comment = comment;
/* :8:   */   }
/* :9:   */   
/* ;0:   */   public String getProcessInstanceId()
/* ;1:   */   {
/* ;2:81 */     return this.processInstanceId;
/* ;3:   */   }
/* ;4:   */   
/* ;5:   */   public void setProcessInstanceId(String processInstanceId)
/* ;6:   */   {
/* ;7:84 */     this.processInstanceId = processInstanceId;
/* ;8:   */   }
/* ;9:   */   
/* <0:   */   public String toString()
/* <1:   */   {
/* <2:88 */     return 
/* <3:   */     
/* <4:   */ 
/* <5:91 */       "JbpmBaseEntity [openId=" + this.openId + ", bssId=" + this.bssId + ", status=" + this.status + ", wechatId=" + this.wechatId + ", title=" + this.title + ", nextStep=" + this.nextStep + ", processInstanceId=" + this.processInstanceId + ", approval=" + this.approval + ", approverId=" + this.approverId + ", comment=" + this.comment + "]";
/* <6:   */   }
/* <7:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.jbpm.entity.JbpmBaseEntity
 * JD-Core Version:    0.7.0.1
 */