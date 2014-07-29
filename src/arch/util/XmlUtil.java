/*  1:   */ package arch.util;
/*  2:   */ 
/*  3:   */ import arch.entity.WxMessage;
/*  4:   */ import arch.entity.type.NoticeState;
/*  5:   */ import com.thoughtworks.xstream.XStream;
/*  6:   */ import com.thoughtworks.xstream.io.xml.DomDriver;
/*  7:   */ 
/*  8:   */ public class XmlUtil
/*  9:   */ {
/* 10:   */   public static WxMessage parseWxMessage(String xml)
/* 11:   */   {
/* 12:16 */     XStream xstream = new XStream(new DomDriver());
/* 13:17 */     xstream.alias("xml", WxMessage.class);
/* 14:   */     
/* 15:19 */     xstream.aliasField("ToUserName", WxMessage.class, "wxId");
/* 16:20 */     xstream.aliasField("FromUserName", WxMessage.class, "openId");
/* 17:21 */     xstream.aliasField("CreateTime", WxMessage.class, "createTime");
/* 18:22 */     xstream.aliasField("MsgType", WxMessage.class, "msgType");
/* 19:23 */     xstream.aliasField("MsgId", WxMessage.class, "msgId");
/* 20:   */     
/* 21:25 */     xstream.aliasField("Content", WxMessage.class, "content");
/* 22:   */     
/* 23:27 */     xstream.aliasField("Event", WxMessage.class, "event");
/* 24:28 */     xstream.aliasField("EventKey", WxMessage.class, "eventKey");
/* 25:29 */     xstream.aliasField("Ticket", WxMessage.class, "ticket");
/* 26:30 */     xstream.aliasField("Latitude", WxMessage.class, "latitude");
/* 27:31 */     xstream.aliasField("Longitude", WxMessage.class, "longitude");
/* 28:32 */     xstream.aliasField("Precision", WxMessage.class, "precision");
/* 29:   */     
/* 30:34 */     xstream.aliasField("Location_X", WxMessage.class, "location_X");
/* 31:35 */     xstream.aliasField("Location_Y", WxMessage.class, "location_Y");
/* 32:36 */     xstream.aliasField("Scale", WxMessage.class, "scale");
/* 33:37 */     xstream.aliasField("Label", WxMessage.class, "label");
/* 34:   */     
/* 35:39 */     xstream.aliasField("PicUrl", WxMessage.class, "picUrl");
/* 36:   */     
/* 37:41 */     xstream.aliasField("MediaId", WxMessage.class, "mediaId");
/* 38:42 */     xstream.aliasField("Format", WxMessage.class, "format");
/* 39:43 */     xstream.aliasField("Recognition", WxMessage.class, "recognition");
/* 40:   */     
/* 41:45 */     xstream.aliasField("ThumbMediaId", WxMessage.class, "thumbMediaId");
/* 42:   */     
/* 43:47 */     xstream.aliasField("Title", WxMessage.class, "title");
/* 44:48 */     xstream.aliasField("Description", WxMessage.class, "picUrl");
/* 45:49 */     xstream.aliasField("Url", WxMessage.class, "url");
/* 46:50 */     WxMessage wxMessage = (WxMessage)xstream.fromXML(xml);
/* 47:51 */     wxMessage.setBssType(NoticeState.RUNNING);
/* 48:52 */     return wxMessage;
/* 49:   */   }
/* 50:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.util.XmlUtil
 * JD-Core Version:    0.7.0.1
 */