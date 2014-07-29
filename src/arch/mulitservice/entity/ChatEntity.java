/*  1:   */ package arch.mulitservice.entity;
/*  2:   */ 
/*  3:   */ import java.util.Hashtable;
/*  4:   */ import java.util.List;
/*  5:   */ import java.util.Map;
/*  6:   */ import java.util.Set;
/*  7:   */ import java.util.Vector;
/*  8:   */ 
/*  9:   */ public class ChatEntity
/* 10:   */ {
/* 11:   */   private List<String> queueList;
/* 12:   */   private Map<String, Set<String>> serviceMap;
/* 13:   */   
/* 14:   */   public List<String> getQueueList()
/* 15:   */   {
/* 16:20 */     return this.queueList;
/* 17:   */   }
/* 18:   */   
/* 19:   */   public void setQueueList(List<String> queueList)
/* 20:   */   {
/* 21:23 */     this.queueList = queueList;
/* 22:   */   }
/* 23:   */   
/* 24:   */   public Map<String, Set<String>> getServiceMap()
/* 25:   */   {
/* 26:26 */     return this.serviceMap;
/* 27:   */   }
/* 28:   */   
/* 29:   */   public void setServiceMap(Map<String, Set<String>> serviceMap)
/* 30:   */   {
/* 31:29 */     this.serviceMap = serviceMap;
/* 32:   */   }
/* 33:   */   
/* 34:   */   public ChatEntity()
/* 35:   */   {
/* 36:32 */     this.queueList = new Vector();
/* 37:33 */     this.serviceMap = new Hashtable();
/* 38:   */   }
/* 39:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.mulitservice.entity.ChatEntity
 * JD-Core Version:    0.7.0.1
 */