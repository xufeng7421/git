/*  1:   */ package arch.listener;
/*  2:   */ 
/*  3:   */ import java.util.concurrent.LinkedBlockingQueue;
/*  4:   */ import java.util.concurrent.ThreadPoolExecutor;
/*  5:   */ import java.util.concurrent.TimeUnit;
/*  6:   */ import org.apache.log4j.Logger;
/*  7:   */ 
/*  8:   */ public class QueueListener
/*  9:   */   implements Runnable
/* 10:   */ {
/* 11:27 */   private static Logger log = Logger.getLogger(QueueListener.class);
/* 12:28 */   private final long receiverWaitTime = 5L;
/* 13:29 */   private Class<? extends Runnable> cla = null;
/* 14:30 */   private ThreadPoolExecutor consumerPool = null;
/* 15:31 */   private Class<?> objClass = null;
/* 16:32 */   private String queueId = null;
/* 17:   */   
/* 18:   */   public QueueListener(int coreThread, int maxThread, Class<? extends Runnable> cla, Class<?> objClass, String queueId)
/* 19:   */   {
/* 20:41 */     this.consumerPool = new ThreadPoolExecutor(maxThread, maxThread, 
/* 21:42 */       1000L, TimeUnit.MILLISECONDS, 
/* 22:43 */       new LinkedBlockingQueue());
/* 23:44 */     this.cla = cla;
/* 24:45 */     this.objClass = objClass;
/* 25:46 */     this.queueId = queueId;
/* 26:   */   }
/* 27:   */   
/* 28:   */   /* Error */
/* 29:   */   public void run()
/* 30:   */   {
/* 31:   */     // Byte code:
/* 32:   */     //   0: getstatic 33	arch/listener/QueueListener:log	Lorg/apache/log4j/Logger;
/* 33:   */     //   3: new 75	java/lang/StringBuilder
/* 34:   */     //   6: dup
/* 35:   */     //   7: ldc 77
/* 36:   */     //   9: invokespecial 79	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/* 37:   */     //   12: aload_0
/* 38:   */     //   13: getfield 50	arch/listener/QueueListener:queueId	Ljava/lang/String;
/* 39:   */     //   16: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/* 40:   */     //   19: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
/* 41:   */     //   22: invokevirtual 90	org/apache/log4j/Logger:debug	(Ljava/lang/Object;)V
/* 42:   */     //   25: aconst_null
/* 43:   */     //   26: astore_2
/* 44:   */     //   27: aconst_null
/* 45:   */     //   28: astore 4
/* 46:   */     //   30: new 94	org/apache/activemq/ActiveMQConnectionFactory
/* 47:   */     //   33: dup
/* 48:   */     //   34: invokestatic 96	arch/properties/ActiveMQProperties:getUser	()Ljava/lang/String;
/* 49:   */     //   37: invokestatic 101	arch/properties/ActiveMQProperties:getPassword	()Ljava/lang/String;
/* 50:   */     //   40: invokestatic 104	arch/properties/ActiveMQProperties:getBrokerUrl	()Ljava/lang/String;
/* 51:   */     //   43: invokespecial 107	org/apache/activemq/ActiveMQConnectionFactory:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
/* 52:   */     //   46: astore_1
/* 53:   */     //   47: aload_1
/* 54:   */     //   48: invokeinterface 110 1 0
/* 55:   */     //   53: astore_2
/* 56:   */     //   54: aload_2
/* 57:   */     //   55: invokeinterface 116 1 0
/* 58:   */     //   60: aload_2
/* 59:   */     //   61: iconst_0
/* 60:   */     //   62: iconst_1
/* 61:   */     //   63: invokeinterface 121 3 0
/* 62:   */     //   68: astore_3
/* 63:   */     //   69: aload_3
/* 64:   */     //   70: aload_0
/* 65:   */     //   71: getfield 50	arch/listener/QueueListener:queueId	Ljava/lang/String;
/* 66:   */     //   74: invokeinterface 125 2 0
/* 67:   */     //   79: astore 4
/* 68:   */     //   81: aload_3
/* 69:   */     //   82: aload 4
/* 70:   */     //   84: invokeinterface 131 2 0
/* 71:   */     //   89: astore 5
/* 72:   */     //   91: aload_0
/* 73:   */     //   92: getfield 46	arch/listener/QueueListener:consumerPool	Ljava/util/concurrent/ThreadPoolExecutor;
/* 74:   */     //   95: invokevirtual 135	java/util/concurrent/ThreadPoolExecutor:getQueue	()Ljava/util/concurrent/BlockingQueue;
/* 75:   */     //   98: invokeinterface 139 1 0
/* 76:   */     //   103: iconst_1
/* 77:   */     //   104: if_icmpge +82 -> 186
/* 78:   */     //   107: aload 5
/* 79:   */     //   109: invokeinterface 145 1 0
/* 80:   */     //   114: checkcast 151	javax/jms/ObjectMessage
/* 81:   */     //   117: astore 6
/* 82:   */     //   119: aload 6
/* 83:   */     //   121: invokeinterface 153 1 0
/* 84:   */     //   126: astore 7
/* 85:   */     //   128: aload 7
/* 86:   */     //   130: instanceof 157
/* 87:   */     //   133: ifeq +6 -> 139
/* 88:   */     //   136: goto +146 -> 282
/* 89:   */     //   139: aload_0
/* 90:   */     //   140: getfield 44	arch/listener/QueueListener:cla	Ljava/lang/Class;
/* 91:   */     //   143: iconst_1
/* 92:   */     //   144: anewarray 159	java/lang/Class
/* 93:   */     //   147: dup
/* 94:   */     //   148: iconst_0
/* 95:   */     //   149: aload_0
/* 96:   */     //   150: getfield 48	arch/listener/QueueListener:objClass	Ljava/lang/Class;
/* 97:   */     //   153: aastore
/* 98:   */     //   154: invokevirtual 161	java/lang/Class:getDeclaredConstructor	([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;
/* 99:   */     //   157: iconst_1
/* :0:   */     //   158: anewarray 3	java/lang/Object
/* :1:   */     //   161: dup
/* :2:   */     //   162: iconst_0
/* :3:   */     //   163: aload 7
/* :4:   */     //   165: aastore
/* :5:   */     //   166: invokevirtual 165	java/lang/reflect/Constructor:newInstance	([Ljava/lang/Object;)Ljava/lang/Object;
/* :6:   */     //   169: checkcast 5	java/lang/Runnable
/* :7:   */     //   172: astore 8
/* :8:   */     //   174: aload_0
/* :9:   */     //   175: getfield 46	arch/listener/QueueListener:consumerPool	Ljava/util/concurrent/ThreadPoolExecutor;
/* ;0:   */     //   178: aload 8
/* ;1:   */     //   180: invokevirtual 171	java/util/concurrent/ThreadPoolExecutor:execute	(Ljava/lang/Runnable;)V
/* ;2:   */     //   183: goto -92 -> 91
/* ;3:   */     //   186: ldc2_w 12
/* ;4:   */     //   189: invokestatic 175	java/lang/Thread:sleep	(J)V
/* ;5:   */     //   192: goto -101 -> 91
/* ;6:   */     //   195: astore 5
/* ;7:   */     //   197: getstatic 33	arch/listener/QueueListener:log	Lorg/apache/log4j/Logger;
/* ;8:   */     //   200: new 75	java/lang/StringBuilder
/* ;9:   */     //   203: dup
/* <0:   */     //   204: ldc 181
/* <1:   */     //   206: invokespecial 79	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/* <2:   */     //   209: aload_0
/* <3:   */     //   210: getfield 50	arch/listener/QueueListener:queueId	Ljava/lang/String;
/* <4:   */     //   213: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/* <5:   */     //   216: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
/* <6:   */     //   219: aload 5
/* <7:   */     //   221: invokevirtual 183	org/apache/log4j/Logger:fatal	(Ljava/lang/Object;Ljava/lang/Throwable;)V
/* <8:   */     //   224: aload_2
/* <9:   */     //   225: ifnull +82 -> 307
/* =0:   */     //   228: aload_2
/* =1:   */     //   229: invokeinterface 187 1 0
/* =2:   */     //   234: goto +73 -> 307
/* =3:   */     //   237: astore 10
/* =4:   */     //   239: getstatic 33	arch/listener/QueueListener:log	Lorg/apache/log4j/Logger;
/* =5:   */     //   242: ldc 190
/* =6:   */     //   244: aload 10
/* =7:   */     //   246: invokevirtual 183	org/apache/log4j/Logger:fatal	(Ljava/lang/Object;Ljava/lang/Throwable;)V
/* =8:   */     //   249: goto +58 -> 307
/* =9:   */     //   252: astore 9
/* >0:   */     //   254: aload_2
/* >1:   */     //   255: ifnull +24 -> 279
/* >2:   */     //   258: aload_2
/* >3:   */     //   259: invokeinterface 187 1 0
/* >4:   */     //   264: goto +15 -> 279
/* >5:   */     //   267: astore 10
/* >6:   */     //   269: getstatic 33	arch/listener/QueueListener:log	Lorg/apache/log4j/Logger;
/* >7:   */     //   272: ldc 190
/* >8:   */     //   274: aload 10
/* >9:   */     //   276: invokevirtual 183	org/apache/log4j/Logger:fatal	(Ljava/lang/Object;Ljava/lang/Throwable;)V
/* ?0:   */     //   279: aload 9
/* ?1:   */     //   281: athrow
/* ?2:   */     //   282: aload_2
/* ?3:   */     //   283: ifnull +24 -> 307
/* ?4:   */     //   286: aload_2
/* ?5:   */     //   287: invokeinterface 187 1 0
/* ?6:   */     //   292: goto +15 -> 307
/* ?7:   */     //   295: astore 10
/* ?8:   */     //   297: getstatic 33	arch/listener/QueueListener:log	Lorg/apache/log4j/Logger;
/* ?9:   */     //   300: ldc 190
/* @0:   */     //   302: aload 10
/* @1:   */     //   304: invokevirtual 183	org/apache/log4j/Logger:fatal	(Ljava/lang/Object;Ljava/lang/Throwable;)V
/* @2:   */     //   307: getstatic 33	arch/listener/QueueListener:log	Lorg/apache/log4j/Logger;
/* @3:   */     //   310: new 75	java/lang/StringBuilder
/* @4:   */     //   313: dup
/* @5:   */     //   314: ldc 192
/* @6:   */     //   316: invokespecial 79	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/* @7:   */     //   319: aload_0
/* @8:   */     //   320: getfield 50	arch/listener/QueueListener:queueId	Ljava/lang/String;
/* @9:   */     //   323: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/* A0:   */     //   326: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
/* A1:   */     //   329: invokevirtual 90	org/apache/log4j/Logger:debug	(Ljava/lang/Object;)V
/* A2:   */     //   332: return
/* A3:   */     // Line number table:
/* A4:   */     //   Java source line #57	-> byte code offset #0
/* A5:   */     //   Java source line #61	-> byte code offset #25
/* A6:   */     //   Java source line #65	-> byte code offset #27
/* A7:   */     //   Java source line #67	-> byte code offset #30
/* A8:   */     //   Java source line #68	-> byte code offset #34
/* A9:   */     //   Java source line #69	-> byte code offset #37
/* B0:   */     //   Java source line #70	-> byte code offset #40
/* B1:   */     //   Java source line #67	-> byte code offset #43
/* B2:   */     //   Java source line #74	-> byte code offset #47
/* B3:   */     //   Java source line #76	-> byte code offset #54
/* B4:   */     //   Java source line #78	-> byte code offset #60
/* B5:   */     //   Java source line #79	-> byte code offset #62
/* B6:   */     //   Java source line #78	-> byte code offset #63
/* B7:   */     //   Java source line #81	-> byte code offset #69
/* B8:   */     //   Java source line #83	-> byte code offset #81
/* B9:   */     //   Java source line #85	-> byte code offset #91
/* C0:   */     //   Java source line #86	-> byte code offset #107
/* C1:   */     //   Java source line #87	-> byte code offset #119
/* C2:   */     //   Java source line #88	-> byte code offset #128
/* C3:   */     //   Java source line #89	-> byte code offset #136
/* C4:   */     //   Java source line #91	-> byte code offset #139
/* C5:   */     //   Java source line #92	-> byte code offset #174
/* C6:   */     //   Java source line #95	-> byte code offset #186
/* C7:   */     //   Java source line #84	-> byte code offset #192
/* C8:   */     //   Java source line #98	-> byte code offset #195
/* C9:   */     //   Java source line #99	-> byte code offset #197
/* D0:   */     //   Java source line #102	-> byte code offset #224
/* D1:   */     //   Java source line #103	-> byte code offset #228
/* D2:   */     //   Java source line #104	-> byte code offset #237
/* D3:   */     //   Java source line #105	-> byte code offset #239
/* D4:   */     //   Java source line #100	-> byte code offset #252
/* D5:   */     //   Java source line #102	-> byte code offset #254
/* D6:   */     //   Java source line #103	-> byte code offset #258
/* D7:   */     //   Java source line #104	-> byte code offset #267
/* D8:   */     //   Java source line #105	-> byte code offset #269
/* D9:   */     //   Java source line #107	-> byte code offset #279
/* E0:   */     //   Java source line #102	-> byte code offset #282
/* E1:   */     //   Java source line #103	-> byte code offset #286
/* E2:   */     //   Java source line #104	-> byte code offset #295
/* E3:   */     //   Java source line #105	-> byte code offset #297
/* E4:   */     //   Java source line #109	-> byte code offset #307
/* E5:   */     //   Java source line #110	-> byte code offset #332
/* E6:   */     // Local variable table:
/* E7:   */     //   start	length	slot	name	signature
/* E8:   */     //   0	333	0	this	QueueListener
/* E9:   */     //   46	2	1	connectionFactory	javax.jms.ConnectionFactory
/* F0:   */     //   26	261	2	connection	javax.jms.Connection
/* F1:   */     //   68	14	3	session	javax.jms.Session
/* F2:   */     //   28	55	4	destination	javax.jms.Destination
/* F3:   */     //   89	19	5	consumer	javax.jms.MessageConsumer
/* F4:   */     //   195	25	5	e	java.lang.Exception
/* F5:   */     //   117	3	6	objMessage	javax.jms.ObjectMessage
/* F6:   */     //   126	38	7	message	Object
/* F7:   */     //   172	7	8	run	Runnable
/* F8:   */     //   252	28	9	localObject1	Object
/* F9:   */     //   237	8	10	ignore	java.lang.Throwable
/* G0:   */     //   267	8	10	ignore	java.lang.Throwable
/* G1:   */     //   295	8	10	ignore	java.lang.Throwable
/* G2:   */     // Exception table:
/* G3:   */     //   from	to	target	type
/* G4:   */     //   47	195	195	java/lang/Exception
/* G5:   */     //   224	234	237	java/lang/Throwable
/* G6:   */     //   47	224	252	finally
/* G7:   */     //   254	264	267	java/lang/Throwable
/* G8:   */     //   282	292	295	java/lang/Throwable
/* G9:   */   }
/* H0:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.listener.QueueListener
 * JD-Core Version:    0.7.0.1
 */