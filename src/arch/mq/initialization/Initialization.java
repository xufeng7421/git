/*   1:    */ package arch.mq.initialization;
/*   2:    */ 
/*   3:    */ import arch.entity.NoticeBusiness;
/*   4:    */ import arch.entity.ReplyEntity;
/*   5:    */ import arch.entity.WxMessage;
/*   6:    */ import arch.jbpm.processFlow.ServiceFlow;
/*   7:    */ import arch.listener.AccesstokenListener;
/*   8:    */ import arch.listener.PluginListener;
/*   9:    */ import arch.listener.QueueListener;
/*  10:    */ import arch.mq.worker.BusinessDispatcherWorker;
/*  11:    */ import arch.mq.worker.ClickWorker;
/*  12:    */ import arch.mq.worker.ExclusiveWorker;
/*  13:    */ import arch.mq.worker.LocationWorker;
/*  14:    */ import arch.mq.worker.ScanWorker;
/*  15:    */ import arch.mq.worker.SubscribeWorker;
/*  16:    */ import arch.mq.worker.TextWorker;
/*  17:    */ import arch.mq.worker.UnSubscribeWorker;
/*  18:    */ import arch.mulitservice.entity.Sign;
/*  19:    */ import arch.mulitservice.worker.ChatWorker;
/*  20:    */ import arch.mulitservice.worker.SignWorker;
/*  21:    */ import arch.properties.QueueProperties;
/*  22:    */ import arch.properties.ThreadPoolProperties;
/*  23:    */ import arch.properties.UtilProperties;
/*  24:    */ import arch.wechat.worker.NoticeBusinessWorker;
/*  25:    */ import arch.wechat.worker.ReSendWorker;
/*  26:    */ import arch.wechat.worker.SendWorker;
/*  27:    */ import java.text.DateFormat;
/*  28:    */ import java.text.ParseException;
/*  29:    */ import java.text.SimpleDateFormat;
/*  30:    */ import java.util.Date;
/*  31:    */ import java.util.List;
/*  32:    */ import java.util.Timer;
/*  33:    */ import org.apache.log4j.Logger;
/*  34:    */ 
/*  35:    */ public class Initialization
/*  36:    */ {
/*  37: 41 */   private static Logger log = Logger.getLogger(Initialization.class);
/*  38:    */   
/*  39:    */   public static void startBaseListener(List<Thread> list)
/*  40:    */   {
/*  41: 48 */     Runnable run = new QueueListener(ThreadPoolProperties.getTextCoreThread().intValue(), 
/*  42: 49 */       ThreadPoolProperties.getTextMaxThread().intValue(), 
/*  43: 50 */       TextWorker.class, 
/*  44: 51 */       WxMessage.class, 
/*  45: 52 */       QueueProperties.getTextQueue());
/*  46: 53 */     list.add(new Thread(run));
/*  47:    */     
/*  48: 55 */     run = new QueueListener(ThreadPoolProperties.getBusinessCoreThread().intValue(), 
/*  49: 56 */       ThreadPoolProperties.getBusinessMaxThread().intValue(), 
/*  50: 57 */       BusinessDispatcherWorker.class, 
/*  51: 58 */       WxMessage.class, 
/*  52: 59 */       QueueProperties.getBusinessQueue());
/*  53: 60 */     list.add(new Thread(run));
/*  54:    */     
/*  55: 62 */     run = new QueueListener(ThreadPoolProperties.getLocationCoreThread().intValue(), 
/*  56: 63 */       ThreadPoolProperties.getLocationMaxThread().intValue(), 
/*  57: 64 */       LocationWorker.class, 
/*  58: 65 */       WxMessage.class, 
/*  59: 66 */       QueueProperties.getLocationQueue());
/*  60: 67 */     list.add(new Thread(run));
/*  61:    */     
/*  62: 69 */     run = new QueueListener(ThreadPoolProperties.getEventClickCoreThread().intValue(), 
/*  63: 70 */       ThreadPoolProperties.getEventClickTextMaxThread().intValue(), 
/*  64: 71 */       ClickWorker.class, 
/*  65: 72 */       WxMessage.class, 
/*  66: 73 */       QueueProperties.getEventClickQueue());
/*  67: 74 */     list.add(new Thread(run));
/*  68:    */     
/*  69: 76 */     run = new QueueListener(ThreadPoolProperties.getEventScanCoreThread().intValue(), 
/*  70: 77 */       ThreadPoolProperties.getEventScanMaxThread().intValue(), 
/*  71: 78 */       ScanWorker.class, 
/*  72: 79 */       WxMessage.class, 
/*  73: 80 */       QueueProperties.getEventScanQueue());
/*  74: 81 */     list.add(new Thread(run));
/*  75:    */     
/*  76: 83 */     run = new QueueListener(ThreadPoolProperties.getEventSubscribeCoreThread().intValue(), 
/*  77: 84 */       ThreadPoolProperties.getEventSubscribeMaxThread().intValue(), 
/*  78: 85 */       SubscribeWorker.class, 
/*  79: 86 */       WxMessage.class, 
/*  80: 87 */       QueueProperties.getEventSubscribeQueue());
/*  81: 88 */     list.add(new Thread(run));
/*  82:    */     
/*  83: 90 */     run = new QueueListener(ThreadPoolProperties.getEventUnsubscribeCoreThread().intValue(), 
/*  84: 91 */       ThreadPoolProperties.getEventUnsubscribeMaxThread().intValue(), 
/*  85: 92 */       UnSubscribeWorker.class, 
/*  86: 93 */       WxMessage.class, 
/*  87: 94 */       QueueProperties.getEventUnSubscribeQueue());
/*  88: 95 */     list.add(new Thread(run));
/*  89:    */     
/*  90: 97 */     run = new QueueListener(ThreadPoolProperties.getExclusiveCoreThread().intValue(), 
/*  91: 98 */       ThreadPoolProperties.getExclusiveMaxThread().intValue(), 
/*  92: 99 */       ExclusiveWorker.class, 
/*  93:100 */       WxMessage.class, 
/*  94:101 */       QueueProperties.getExclusiveQueue());
/*  95:102 */     list.add(new Thread(run));
/*  96:103 */     run = new QueueListener(ThreadPoolProperties.getServiceSendCoreThread().intValue(), 
/*  97:104 */       ThreadPoolProperties.getServiceSendMaxThread().intValue(), 
/*  98:105 */       SendWorker.class, 
/*  99:106 */       ReplyEntity.class, 
/* 100:107 */       QueueProperties.getServiceSendQueue());
/* 101:108 */     list.add(new Thread(run));
/* 102:    */   }
/* 103:    */   
/* 104:    */   public static void wxLitenerStart(List<Thread> list)
/* 105:    */   {
/* 106:115 */     Runnable run = new QueueListener(ThreadPoolProperties.getNoticeBusinessCoreThread().intValue(), 
/* 107:116 */       ThreadPoolProperties.getNoticeBusinessMaxThread().intValue(), 
/* 108:117 */       NoticeBusinessWorker.class, 
/* 109:118 */       NoticeBusiness.class, 
/* 110:119 */       QueueProperties.getNoticeBusinessQueue());
/* 111:    */     
/* 112:121 */     list.add(new Thread(run));
/* 113:122 */     run = new QueueListener(ThreadPoolProperties.getSendCoreThread().intValue(), 
/* 114:123 */       ThreadPoolProperties.getSendMaxThread().intValue(), 
/* 115:124 */       SendWorker.class, 
/* 116:125 */       ReplyEntity.class, 
/* 117:126 */       QueueProperties.getSendQueue());
/* 118:    */     
/* 119:128 */     list.add(new Thread(run));
/* 120:129 */     run = new QueueListener(ThreadPoolProperties.getReSendCoreThread().intValue(), 
/* 121:130 */       ThreadPoolProperties.getReSendMaxThread().intValue(), 
/* 122:131 */       ReSendWorker.class, 
/* 123:132 */       ReplyEntity.class, 
/* 124:133 */       QueueProperties.getResendQueue());
/* 125:    */     
/* 126:135 */     list.add(new Thread(run));
/* 127:136 */     run = new AccesstokenListener();
/* 128:137 */     list.add(new Thread(run));
/* 129:    */   }
/* 130:    */   
/* 131:    */   public static void startService(List<Thread> list)
/* 132:    */   {
/* 133:142 */     Runnable run = new QueueListener(ThreadPoolProperties.getServiceCoreThread().intValue(), 
/* 134:143 */       ThreadPoolProperties.getServiceMaxThread().intValue(), 
/* 135:144 */       ChatWorker.class, 
/* 136:145 */       WxMessage.class, 
/* 137:146 */       QueueProperties.getServiceReviceQueue());
/* 138:    */     
/* 139:148 */     list.add(new Thread(run));
/* 140:149 */     run = new QueueListener(ThreadPoolProperties.getServiceSignCoreThread().intValue(), 
/* 141:150 */       ThreadPoolProperties.getServiceSignMaxThread().intValue(), 
/* 142:151 */       SignWorker.class, 
/* 143:152 */       Sign.class, 
/* 144:153 */       QueueProperties.getServiceSignQueue());
/* 145:    */     
/* 146:155 */     list.add(new Thread(run));
/* 147:    */     
/* 148:157 */     run = new QueueListener(ThreadPoolProperties.getServiceSignCoreThread().intValue(), 
/* 149:158 */       ThreadPoolProperties.getServiceSignMaxThread().intValue(), 
/* 150:159 */       ServiceFlow.class, 
/* 151:160 */       WxMessage.class, 
/* 152:161 */       QueueProperties.getServiceQueue());
/* 153:    */     
/* 154:163 */     list.add(new Thread(run));
/* 155:    */   }
/* 156:    */   
/* 157:    */   public static void startUsingPlugin()
/* 158:    */   {
/* 159:170 */     new PluginListener().pluginInit();
/* 160:    */   }
/* 161:    */   
/* 162:    */   public static void startPluginTimer(List<Timer> list)
/* 163:    */   {
/* 164:177 */     DateFormat dateFormat = new SimpleDateFormat(UtilProperties.getCaseStartTime());
/* 165:178 */     Date dat = null;
/* 166:    */     try
/* 167:    */     {
/* 168:180 */       dat = dateFormat.parse(dateFormat.format(new Date()));
/* 169:    */     }
/* 170:    */     catch (ParseException e)
/* 171:    */     {
/* 172:182 */       log.fatal("时间格式化出错", e);
/* 173:183 */       dat = new Date();
/* 174:    */     }
/* 175:185 */     Timer timer = new Timer();
/* 176:186 */     timer.schedule(new PluginListener(), dat, UtilProperties.getPerCaseUpdateTime().longValue());
/* 177:187 */     list.add(timer);
/* 178:    */   }
/* 179:    */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.mq.initialization.Initialization
 * JD-Core Version:    0.7.0.1
 */