/*  1:   */ package arch.plugin.menu;
/*  2:   */ 
/*  3:   */ import arch.entity.Item;
/*  4:   */ import arch.plugin.interfaces.MenuPlugin;
/*  5:   */ import arch.util.MsgUtil;
/*  6:   */ import java.util.Map;
/*  7:   */ 
/*  8:   */ public class UrlSkip
/*  9:   */   implements MenuPlugin
/* 10:   */ {
/* 11:   */   public String iwork(String openId, Map<String, String> map)
/* 12:   */   {
/* 13:13 */     Item item = new Item((String)map.get("atitle"), (String)map.get("atext"), (String)map.get("aimg"), (String)map.get("url"));
/* 14:   */     
/* 15:15 */     return MsgUtil.parseNewsMsg(openId, item);
/* 16:   */   }
/* 17:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.plugin.menu.UrlSkip
 * JD-Core Version:    0.7.0.1
 */