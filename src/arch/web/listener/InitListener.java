/*  1:   */ package arch.web.listener;
/*  2:   */ 
/*  3:   */ import arch.mq.initialization.Initialization;
/*  4:   */ import arch.mq.service.MQPooledConnectionFactory;
/*  5:   */ import arch.wechat.cache.CacheManager;
/*  6:   */ import java.util.ArrayList;
/*  7:   */ import java.util.List;
/*  8:   */ import java.util.Timer;
/*  9:   */ import javax.servlet.ServletContextEvent;
/* 10:   */ import javax.servlet.ServletContextListener;
/* 11:   */ 
/* 12:   */ public class InitListener
/* 13:   */   implements ServletContextListener
/* 14:   */ {
/* 15:22 */   public static List<Thread> threadlist = new ArrayList();
/* 16:23 */   public static List<Timer> timerlist = new ArrayList();
/* 17:   */   
/* 18:   */   public void contextInitialized(ServletContextEvent sce)
/* 19:   */   {
/* 20:30 */     Initialization.wxLitenerStart(threadlist);
/* 21:31 */     Initialization.startBaseListener(threadlist);
/* 22:32 */     Initialization.startService(threadlist);
/* 23:33 */     for (Thread t : threadlist) {
/* 24:34 */       t.start();
/* 25:   */     }
/* 26:36 */     Initialization.startUsingPlugin();
/* 27:37 */     Initialization.startPluginTimer(timerlist);
/* 28:38 */     CacheManager.loadCache();
/* 29:   */   }
/* 30:   */   
/* 31:   */   public void contextDestroyed(ServletContextEvent sce)
/* 32:   */   {
/* 33:51 */     for (Timer t : timerlist) {
/* 34:52 */       t.cancel();
/* 35:   */     }
/* 36:57 */     for (Thread t : threadlist) {
/* 37:58 */       if (t.isAlive()) {
/* 38:59 */         t.stop();
/* 39:   */       }
/* 40:   */     }
/* 41:62 */     MQPooledConnectionFactory.destory();
/* 42:63 */     CacheManager.saveCache();
/* 43:   */   }
/* 44:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.web.listener.InitListener
 * JD-Core Version:    0.7.0.1
 */