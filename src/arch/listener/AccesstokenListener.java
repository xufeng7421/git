/*  1:   */ package arch.listener;
/*  2:   */ 
/*  3:   */ import org.apache.log4j.Logger;
/*  4:   */ 
/*  5:   */ public class AccesstokenListener
/*  6:   */   implements Runnable
/*  7:   */ {
/*  8:22 */   private static Logger log = Logger.getLogger(AccesstokenListener.class);
/*  9:   */   
/* 10:   */   /* Error */
/* 11:   */   public void run()
/* 12:   */   {
/* 13:   */     // Byte code:
/* 14:   */     //   0: aconst_null
/* 15:   */     //   1: astore_1
/* 16:   */     //   2: aconst_null
/* 17:   */     //   3: astore_2
/* 18:   */     //   4: invokestatic 28	arch/util/MqUtil:getConnection	()Ljavax/jms/Connection;
/* 19:   */     //   7: astore_1
/* 20:   */     //   8: aload_1
/* 21:   */     //   9: iconst_0
/* 22:   */     //   10: iconst_1
/* 23:   */     //   11: invokeinterface 34 3 0
/* 24:   */     //   16: astore_2
/* 25:   */     //   17: aload_2
/* 26:   */     //   18: invokestatic 40	arch/properties/QueueProperties:getAccessTokenQueue	()Ljava/lang/String;
/* 27:   */     //   21: invokeinterface 46 2 0
/* 28:   */     //   26: astore_3
/* 29:   */     //   27: aload_2
/* 30:   */     //   28: aload_3
/* 31:   */     //   29: invokeinterface 52 2 0
/* 32:   */     //   34: astore 4
/* 33:   */     //   36: aload_2
/* 34:   */     //   37: aconst_null
/* 35:   */     //   38: invokeinterface 56 2 0
/* 36:   */     //   43: astore 5
/* 37:   */     //   45: aload 5
/* 38:   */     //   47: iconst_1
/* 39:   */     //   48: invokeinterface 60 2 0
/* 40:   */     //   53: aload 4
/* 41:   */     //   55: invokeinterface 66 1 0
/* 42:   */     //   60: checkcast 72	javax/jms/TextMessage
/* 43:   */     //   63: astore 6
/* 44:   */     //   65: aload 6
/* 45:   */     //   67: invokeinterface 74 1 0
/* 46:   */     //   72: astore 7
/* 47:   */     //   74: aload 7
/* 48:   */     //   76: invokestatic 77	arch/util/AccessTokenUtil:getToken	(Ljava/lang/String;)Larch/entity/AccessToken;
/* 49:   */     //   79: astore 8
/* 50:   */     //   81: aconst_null
/* 51:   */     //   82: astore 9
/* 52:   */     //   84: aload 8
/* 53:   */     //   86: ifnonnull +10 -> 96
/* 54:   */     //   89: ldc 83
/* 55:   */     //   91: astore 9
/* 56:   */     //   93: goto +65 -> 158
/* 57:   */     //   96: aload 7
/* 58:   */     //   98: invokestatic 85	arch/wechat/cache/TokenManager:isNew	(Ljava/lang/String;)Z
/* 59:   */     //   101: ifeq +50 -> 151
/* 60:   */     //   104: aload 8
/* 61:   */     //   106: invokevirtual 91	arch/entity/AccessToken:getAppId	()Ljava/lang/String;
/* 62:   */     //   109: aload 8
/* 63:   */     //   111: invokevirtual 96	arch/entity/AccessToken:getAppSecret	()Ljava/lang/String;
/* 64:   */     //   114: invokestatic 99	arch/wechat/api/WeChatAPI:getAccessToken	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
/* 65:   */     //   117: astore 9
/* 66:   */     //   119: aload 9
/* 67:   */     //   121: ifnull +23 -> 144
/* 68:   */     //   124: aload 8
/* 69:   */     //   126: aload 9
/* 70:   */     //   128: invokevirtual 105	arch/entity/AccessToken:setAccessToken	(Ljava/lang/String;)V
/* 71:   */     //   131: aload 8
/* 72:   */     //   133: invokestatic 109	arch/util/AccessTokenUtil:updAccessToken	(Larch/entity/AccessToken;)V
/* 73:   */     //   136: aload 7
/* 74:   */     //   138: invokestatic 113	arch/wechat/cache/TokenManager:setCache	(Ljava/lang/String;)V
/* 75:   */     //   141: goto +17 -> 158
/* 76:   */     //   144: ldc 83
/* 77:   */     //   146: astore 9
/* 78:   */     //   148: goto +10 -> 158
/* 79:   */     //   151: aload 8
/* 80:   */     //   153: invokevirtual 116	arch/entity/AccessToken:getAccessToken	()Ljava/lang/String;
/* 81:   */     //   156: astore 9
/* 82:   */     //   158: aload_2
/* 83:   */     //   159: invokeinterface 118 1 0
/* 84:   */     //   164: astore 10
/* 85:   */     //   166: aload 10
/* 86:   */     //   168: aload 9
/* 87:   */     //   170: invokeinterface 122 2 0
/* 88:   */     //   175: aload 10
/* 89:   */     //   177: aload 6
/* 90:   */     //   179: invokeinterface 125 1 0
/* 91:   */     //   184: invokeinterface 128 2 0
/* 92:   */     //   189: aload 5
/* 93:   */     //   191: aload 6
/* 94:   */     //   193: invokeinterface 131 1 0
/* 95:   */     //   198: aload 10
/* 96:   */     //   200: invokeinterface 135 3 0
/* 97:   */     //   205: goto -152 -> 53
/* 98:   */     //   208: astore 6
/* 99:   */     //   210: getstatic 18	arch/listener/AccesstokenListener:log	Lorg/apache/log4j/Logger;
/* :0:   */     //   213: ldc 139
/* :1:   */     //   215: aload 6
/* :2:   */     //   217: invokevirtual 141	org/apache/log4j/Logger:fatal	(Ljava/lang/Object;Ljava/lang/Throwable;)V
/* :3:   */     //   220: aload_1
/* :4:   */     //   221: ifnull +57 -> 278
/* :5:   */     //   224: aload_1
/* :6:   */     //   225: invokeinterface 145 1 0
/* :7:   */     //   230: goto +48 -> 278
/* :8:   */     //   233: astore 12
/* :9:   */     //   235: getstatic 18	arch/listener/AccesstokenListener:log	Lorg/apache/log4j/Logger;
/* ;0:   */     //   238: ldc 148
/* ;1:   */     //   240: aload 12
/* ;2:   */     //   242: invokevirtual 141	org/apache/log4j/Logger:fatal	(Ljava/lang/Object;Ljava/lang/Throwable;)V
/* ;3:   */     //   245: goto +33 -> 278
/* ;4:   */     //   248: astore 11
/* ;5:   */     //   250: aload_1
/* ;6:   */     //   251: ifnull +24 -> 275
/* ;7:   */     //   254: aload_1
/* ;8:   */     //   255: invokeinterface 145 1 0
/* ;9:   */     //   260: goto +15 -> 275
/* <0:   */     //   263: astore 12
/* <1:   */     //   265: getstatic 18	arch/listener/AccesstokenListener:log	Lorg/apache/log4j/Logger;
/* <2:   */     //   268: ldc 148
/* <3:   */     //   270: aload 12
/* <4:   */     //   272: invokevirtual 141	org/apache/log4j/Logger:fatal	(Ljava/lang/Object;Ljava/lang/Throwable;)V
/* <5:   */     //   275: aload 11
/* <6:   */     //   277: athrow
/* <7:   */     //   278: return
/* <8:   */     // Line number table:
/* <9:   */     //   Java source line #27	-> byte code offset #0
/* =0:   */     //   Java source line #28	-> byte code offset #2
/* =1:   */     //   Java source line #33	-> byte code offset #4
/* =2:   */     //   Java source line #34	-> byte code offset #8
/* =3:   */     //   Java source line #36	-> byte code offset #17
/* =4:   */     //   Java source line #37	-> byte code offset #27
/* =5:   */     //   Java source line #39	-> byte code offset #36
/* =6:   */     //   Java source line #40	-> byte code offset #45
/* =7:   */     //   Java source line #42	-> byte code offset #53
/* =8:   */     //   Java source line #43	-> byte code offset #65
/* =9:   */     //   Java source line #44	-> byte code offset #74
/* >0:   */     //   Java source line #45	-> byte code offset #81
/* >1:   */     //   Java source line #46	-> byte code offset #84
/* >2:   */     //   Java source line #47	-> byte code offset #89
/* >3:   */     //   Java source line #49	-> byte code offset #96
/* >4:   */     //   Java source line #50	-> byte code offset #104
/* >5:   */     //   Java source line #51	-> byte code offset #119
/* >6:   */     //   Java source line #52	-> byte code offset #124
/* >7:   */     //   Java source line #53	-> byte code offset #131
/* >8:   */     //   Java source line #54	-> byte code offset #136
/* >9:   */     //   Java source line #56	-> byte code offset #144
/* ?0:   */     //   Java source line #59	-> byte code offset #151
/* ?1:   */     //   Java source line #62	-> byte code offset #158
/* ?2:   */     //   Java source line #63	-> byte code offset #166
/* ?3:   */     //   Java source line #64	-> byte code offset #175
/* ?4:   */     //   Java source line #65	-> byte code offset #189
/* ?5:   */     //   Java source line #41	-> byte code offset #205
/* ?6:   */     //   Java source line #68	-> byte code offset #208
/* ?7:   */     //   Java source line #69	-> byte code offset #210
/* ?8:   */     //   Java source line #72	-> byte code offset #220
/* ?9:   */     //   Java source line #73	-> byte code offset #224
/* @0:   */     //   Java source line #74	-> byte code offset #233
/* @1:   */     //   Java source line #75	-> byte code offset #235
/* @2:   */     //   Java source line #70	-> byte code offset #248
/* @3:   */     //   Java source line #72	-> byte code offset #250
/* @4:   */     //   Java source line #73	-> byte code offset #254
/* @5:   */     //   Java source line #74	-> byte code offset #263
/* @6:   */     //   Java source line #75	-> byte code offset #265
/* @7:   */     //   Java source line #77	-> byte code offset #275
/* @8:   */     //   Java source line #78	-> byte code offset #278
/* @9:   */     // Local variable table:
/* A0:   */     //   start	length	slot	name	signature
/* A1:   */     //   0	279	0	this	AccesstokenListener
/* A2:   */     //   1	254	1	connection	javax.jms.Connection
/* A3:   */     //   3	156	2	session	javax.jms.Session
/* A4:   */     //   26	3	3	destination	javax.jms.Destination
/* A5:   */     //   34	20	4	consumer	javax.jms.MessageConsumer
/* A6:   */     //   43	147	5	replyProducer	javax.jms.MessageProducer
/* A7:   */     //   63	129	6	txtMsg	javax.jms.TextMessage
/* A8:   */     //   208	8	6	e	javax.jms.JMSException
/* A9:   */     //   72	65	7	wechatId	java.lang.String
/* B0:   */     //   79	73	8	token	arch.entity.AccessToken
/* B1:   */     //   82	87	9	accessToken	java.lang.String
/* B2:   */     //   164	35	10	response	javax.jms.TextMessage
/* B3:   */     //   248	28	11	localObject	Object
/* B4:   */     //   233	8	12	ignore	java.lang.Throwable
/* B5:   */     //   263	8	12	ignore	java.lang.Throwable
/* B6:   */     // Exception table:
/* B7:   */     //   from	to	target	type
/* B8:   */     //   4	208	208	javax/jms/JMSException
/* B9:   */     //   220	230	233	java/lang/Throwable
/* C0:   */     //   4	220	248	finally
/* C1:   */     //   250	260	263	java/lang/Throwable
/* C2:   */   }
/* C3:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.listener.AccesstokenListener
 * JD-Core Version:    0.7.0.1
 */