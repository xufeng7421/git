/*  1:   */ package arch.terminology;
/*  2:   */ 
/*  3:   */ public class Terminology
/*  4:   */ {
/*  5:   */   public static String getServiceWait(int len)
/*  6:   */   {
/*  7:11 */     String str = "您好，您前面还有" + len + "个人正在等待,请您耐心等候。。。退出等待请输入88";
/*  8:12 */     return str;
/*  9:   */   }
/* 10:   */   
/* 11:   */   public static String getServiceStart(String serviceId)
/* 12:   */   {
/* 13:20 */     return "您好," + serviceId + "号客服为您服务,请问您有什么需要帮助的";
/* 14:   */   }
/* 15:   */   
/* 16:   */   public static String getServiceEnd()
/* 17:   */   {
/* 18:28 */     return "您好,本次服务已结束，感谢您的支持！";
/* 19:   */   }
/* 20:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.terminology.Terminology
 * JD-Core Version:    0.7.0.1
 */