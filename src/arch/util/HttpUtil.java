/*   1:    */ package arch.util;
/*   2:    */ 
/*   3:    */ import java.io.ByteArrayOutputStream;
/*   4:    */ import java.io.IOException;
/*   5:    */ import java.io.InputStream;
/*   6:    */ import java.io.OutputStream;
/*   7:    */ import java.net.HttpURLConnection;
/*   8:    */ import java.net.URL;
/*   9:    */ import java.security.cert.CertificateException;
/*  10:    */ import java.security.cert.X509Certificate;
/*  11:    */ import javax.net.ssl.HostnameVerifier;
/*  12:    */ import javax.net.ssl.HttpsURLConnection;
/*  13:    */ import javax.net.ssl.SSLContext;
/*  14:    */ import javax.net.ssl.SSLSession;
/*  15:    */ import javax.net.ssl.TrustManager;
/*  16:    */ import javax.net.ssl.X509TrustManager;
/*  17:    */ import org.apache.log4j.Logger;
/*  18:    */ 
/*  19:    */ public class HttpUtil
/*  20:    */ {
/*  21: 23 */   private static Logger log = Logger.getLogger(HttpUtil.class);
/*  22:    */   
/*  23:    */   public static String get(String get_url)
/*  24:    */   {
/*  25: 26 */     HttpURLConnection connection = null;
/*  26: 27 */     InputStream in = null;
/*  27: 28 */     String result = null;
/*  28:    */     try
/*  29:    */     {
/*  30: 30 */       System.setProperty("jsse.enableSNIExtension", "false");
/*  31: 31 */       System.setProperty("https.protocols", "SSLv3,SSLv2Hello");
/*  32: 32 */       URL url = new URL(get_url);
/*  33: 33 */       trustAllHttpsCertificates();
/*  34: 34 */       HostnameVerifier hv = new HostnameVerifier()
/*  35:    */       {
/*  36:    */         public boolean verify(String urlHostName, SSLSession session)
/*  37:    */         {
/*  38: 36 */           HttpUtil.log.info("Warning: URL Host: " + urlHostName + " vs. " + session.getPeerHost());
/*  39: 37 */           return true;
/*  40:    */         }
/*  41: 39 */       };
/*  42: 40 */       HttpsURLConnection.setDefaultHostnameVerifier(hv);
/*  43:    */       
/*  44: 42 */       connection = (HttpURLConnection)url.openConnection();
/*  45: 43 */       connection.setRequestMethod("GET");
/*  46:    */       
/*  47: 45 */       connection.setDoInput(true);
/*  48: 46 */       connection.setDoOutput(true);
/*  49: 49 */       if (connection.getResponseCode() == 200)
/*  50:    */       {
/*  51: 50 */         in = connection.getInputStream();
/*  52: 51 */         result = ChangeInputStream(in, "UTF-8");
/*  53:    */       }
/*  54:    */     }
/*  55:    */     catch (Exception e)
/*  56:    */     {
/*  57: 54 */       log.fatal("HttpUtils-get请求异常：", e);
/*  58:    */     }
/*  59:    */     finally
/*  60:    */     {
/*  61: 56 */       if (in != null) {
/*  62:    */         try
/*  63:    */         {
/*  64: 58 */           in.close();
/*  65:    */         }
/*  66:    */         catch (IOException e)
/*  67:    */         {
/*  68: 60 */           log.fatal("流关闭异常", e);
/*  69:    */         }
/*  70:    */       }
/*  71: 63 */       connection.disconnect();
/*  72:    */     }
/*  73: 65 */     return result;
/*  74:    */   }
/*  75:    */   
/*  76:    */   public static String post(String post_url, String post_data)
/*  77:    */   {
/*  78: 70 */     HttpURLConnection connection = null;
/*  79: 71 */     OutputStream outputStream = null;
/*  80: 72 */     InputStream in = null;
/*  81: 73 */     String result = null;
/*  82:    */     try
/*  83:    */     {
/*  84: 75 */       System.setProperty("jsse.enableSNIExtension", "false");
/*  85: 76 */       System.setProperty("https.protocols", "SSLv3,SSLv2Hello");
/*  86: 77 */       URL url = new URL(post_url);
/*  87: 78 */       trustAllHttpsCertificates();
/*  88: 79 */       HostnameVerifier hv = new HostnameVerifier()
/*  89:    */       {
/*  90:    */         public boolean verify(String urlHostName, SSLSession session)
/*  91:    */         {
/*  92: 81 */           HttpUtil.log.info("Warning: URL Host: " + urlHostName + " vs. " + session.getPeerHost());
/*  93: 82 */           return true;
/*  94:    */         }
/*  95: 84 */       };
/*  96: 85 */       HttpsURLConnection.setDefaultHostnameVerifier(hv);
/*  97: 86 */       connection = (HttpURLConnection)url.openConnection();
/*  98: 87 */       connection.setRequestMethod("POST");
/*  99:    */       
/* 100: 89 */       connection.setDoInput(true);
/* 101: 90 */       connection.setDoOutput(true);
/* 102:    */       
/* 103: 92 */       byte[] data = post_data.getBytes("UTF-8");
/* 104:    */       
/* 105:    */ 
/* 106: 95 */       connection.setRequestProperty("Content-Length", String.valueOf(data.length));
/* 107:    */       
/* 108: 97 */       outputStream = connection.getOutputStream();
/* 109: 98 */       outputStream.write(data, 0, data.length);
/* 110:101 */       if (connection.getResponseCode() == 200)
/* 111:    */       {
/* 112:102 */         in = connection.getInputStream();
/* 113:103 */         result = ChangeInputStream(in, "UTF-8");
/* 114:    */       }
/* 115:    */     }
/* 116:    */     catch (Exception e)
/* 117:    */     {
/* 118:106 */       log.fatal("HttpUtil-post请求异常", e);
/* 119:    */     }
/* 120:    */     finally
/* 121:    */     {
/* 122:108 */       if (in != null) {
/* 123:    */         try
/* 124:    */         {
/* 125:110 */           in.close();
/* 126:    */         }
/* 127:    */         catch (IOException e)
/* 128:    */         {
/* 129:112 */           log.fatal("流关闭异常", e);
/* 130:    */         }
/* 131:    */       }
/* 132:115 */       if (outputStream != null) {
/* 133:    */         try
/* 134:    */         {
/* 135:117 */           outputStream.close();
/* 136:    */         }
/* 137:    */         catch (IOException e)
/* 138:    */         {
/* 139:119 */           log.fatal("输出流关闭异常", e);
/* 140:    */         }
/* 141:    */       }
/* 142:122 */       connection.disconnect();
/* 143:    */     }
/* 144:124 */     return result;
/* 145:    */   }
/* 146:    */   
/* 147:    */   private static void trustAllHttpsCertificates()
/* 148:    */     throws Exception
/* 149:    */   {
/* 150:129 */     TrustManager[] trustAllCerts = new TrustManager[1];
/* 151:130 */     TrustManager tm = new miTM();
/* 152:131 */     trustAllCerts[0] = tm;
/* 153:132 */     SSLContext sc = 
/* 154:133 */       SSLContext.getInstance("SSL");
/* 155:134 */     sc.init(null, trustAllCerts, null);
/* 156:135 */     HttpsURLConnection.setDefaultSSLSocketFactory(sc
/* 157:136 */       .getSocketFactory());
/* 158:    */   }
/* 159:    */   
/* 160:    */   static class miTM
/* 161:    */     implements TrustManager, X509TrustManager
/* 162:    */   {
/* 163:    */     public X509Certificate[] getAcceptedIssuers()
/* 164:    */     {
/* 165:142 */       return null;
/* 166:    */     }
/* 167:    */     
/* 168:    */     public boolean isServerTrusted(X509Certificate[] certs)
/* 169:    */     {
/* 170:147 */       return true;
/* 171:    */     }
/* 172:    */     
/* 173:    */     public boolean isClientTrusted(X509Certificate[] certs)
/* 174:    */     {
/* 175:152 */       return true;
/* 176:    */     }
/* 177:    */     
/* 178:    */     public void checkServerTrusted(X509Certificate[] certs, String authType)
/* 179:    */       throws CertificateException
/* 180:    */     {}
/* 181:    */     
/* 182:    */     public void checkClientTrusted(X509Certificate[] certs, String authType)
/* 183:    */       throws CertificateException
/* 184:    */     {}
/* 185:    */   }
/* 186:    */   
/* 187:    */   private static String ChangeInputStream(InputStream is, String encode)
/* 188:    */   {
/* 189:169 */     String result = null;
/* 190:170 */     ByteArrayOutputStream outputStream = null;
/* 191:    */     try
/* 192:    */     {
/* 193:172 */       outputStream = new ByteArrayOutputStream();
/* 194:173 */       byte[] data = new byte[1024];
/* 195:174 */       int len = 0;
/* 196:175 */       while ((len = is.read(data)) != -1) {
/* 197:176 */         outputStream.write(data, 0, len);
/* 198:    */       }
/* 199:178 */       result = new String(outputStream.toByteArray(), encode);
/* 200:    */     }
/* 201:    */     catch (Exception e)
/* 202:    */     {
/* 203:180 */       log.fatal("转换字符编码异常", e);
/* 204:182 */       if (outputStream != null) {
/* 205:    */         try
/* 206:    */         {
/* 207:184 */           outputStream.close();
/* 208:    */         }
/* 209:    */         catch (Exception e2)
/* 210:    */         {
/* 211:186 */           log.fatal("流关闭异常", e2);
/* 212:    */         }
/* 213:    */       }
/* 214:    */     }
/* 215:    */     finally
/* 216:    */     {
/* 217:182 */       if (outputStream != null) {
/* 218:    */         try
/* 219:    */         {
/* 220:184 */           outputStream.close();
/* 221:    */         }
/* 222:    */         catch (Exception e2)
/* 223:    */         {
/* 224:186 */           log.fatal("流关闭异常", e2);
/* 225:    */         }
/* 226:    */       }
/* 227:    */     }
/* 228:190 */     return result;
/* 229:    */   }
/* 230:    */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.util.HttpUtil
 * JD-Core Version:    0.7.0.1
 */