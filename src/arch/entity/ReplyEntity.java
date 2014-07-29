/*  1:   */ package arch.entity;
/*  2:   */ 
/*  3:   */ import arch.entity.type.ReplyType;
/*  4:   */ import arch.util.AccessTokenUtil;
/*  5:   */ import arch.util.MsgUtil;
/*  6:   */ import java.io.Serializable;
/*  7:   */ 
/*  8:   */ public class ReplyEntity
/*  9:   */   implements Serializable
/* 10:   */ {
/* 11:   */   private static final long serialVersionUID = 1L;
/* 12:   */   private String wechatId;
/* 13:   */   private String wxId;
/* 14:   */   private String openId;
/* 15:   */   private ReplyType msgType;
/* 16:   */   private String accessToken;
/* 17:   */   private String content;
/* 18:   */   
/* 19:   */   public ReplyEntity() {}
/* 20:   */   
/* 21:   */   public ReplyEntity(WxMessage wx)
/* 22:   */   {
/* 23:28 */     this.wechatId = wx.getWechatId();
/* 24:29 */     this.wxId = wx.getWxId();
/* 25:30 */     this.openId = wx.getOpenId();
/* 26:   */   }
/* 27:   */   
/* 28:   */   public ReplyEntity(WxMessage wx, String msg)
/* 29:   */   {
/* 30:34 */     this.wechatId = wx.getWechatId();
/* 31:35 */     this.wxId = wx.getWxId();
/* 32:36 */     this.openId = wx.getOpenId();
/* 33:37 */     this.msgType = ReplyType.TEXT;
/* 34:38 */     this.accessToken = AccessTokenUtil.getAccessToken(wx.getWechatId());
/* 35:39 */     this.content = MsgUtil.parseTextMsg(this.openId, msg);
/* 36:   */   }
/* 37:   */   
/* 38:   */   public ReplyEntity(String wechatId, String openId, String msg)
/* 39:   */   {
/* 40:43 */     this.wechatId = wechatId;
/* 41:44 */     this.openId = openId;
/* 42:45 */     this.msgType = ReplyType.TEXT;
/* 43:46 */     this.accessToken = AccessTokenUtil.getAccessToken(wechatId);
/* 44:47 */     this.content = MsgUtil.parseTextMsg(openId, msg);
/* 45:   */   }
/* 46:   */   
/* 47:   */   public String getWxId()
/* 48:   */   {
/* 49:51 */     return this.wxId;
/* 50:   */   }
/* 51:   */   
/* 52:   */   public void setWxId(String wxId)
/* 53:   */   {
/* 54:54 */     this.wxId = wxId;
/* 55:   */   }
/* 56:   */   
/* 57:   */   public String getAccessToken()
/* 58:   */   {
/* 59:57 */     return this.accessToken;
/* 60:   */   }
/* 61:   */   
/* 62:   */   public void setAccessToken(String accessToken)
/* 63:   */   {
/* 64:60 */     this.accessToken = accessToken;
/* 65:   */   }
/* 66:   */   
/* 67:   */   public String getWechatId()
/* 68:   */   {
/* 69:63 */     return this.wechatId;
/* 70:   */   }
/* 71:   */   
/* 72:   */   public void setWechatId(String wechatId)
/* 73:   */   {
/* 74:66 */     this.wechatId = wechatId;
/* 75:   */   }
/* 76:   */   
/* 77:   */   public String getOpenId()
/* 78:   */   {
/* 79:69 */     return this.openId;
/* 80:   */   }
/* 81:   */   
/* 82:   */   public void setOpenId(String openId)
/* 83:   */   {
/* 84:72 */     this.openId = openId;
/* 85:   */   }
/* 86:   */   
/* 87:   */   public ReplyType getMsgType()
/* 88:   */   {
/* 89:76 */     return this.msgType;
/* 90:   */   }
/* 91:   */   
/* 92:   */   public void setMsgType(ReplyType msgType)
/* 93:   */   {
/* 94:80 */     this.msgType = msgType;
/* 95:   */   }
/* 96:   */   
/* 97:   */   public String getContent()
/* 98:   */   {
/* 99:84 */     return this.content;
/* :0:   */   }
/* :1:   */   
/* :2:   */   public void setContent(String content)
/* :3:   */   {
/* :4:87 */     this.content = content;
/* :5:   */   }
/* :6:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.entity.ReplyEntity
 * JD-Core Version:    0.7.0.1
 */