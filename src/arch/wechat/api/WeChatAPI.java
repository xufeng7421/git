/*   1:    */ package arch.wechat.api;
/*   2:    */ 
/*   3:    */ import arch.entity.ReplyEntity;
/*   4:    */ import arch.util.HttpUtil;
/*   5:    */ import java.util.ArrayList;
/*   6:    */ import java.util.List;
/*   7:    */ import org.apache.log4j.Logger;
/*   8:    */ import org.json.JSONArray;
/*   9:    */ import org.json.JSONException;
/*  10:    */ import org.json.JSONObject;
/*  11:    */ 
/*  12:    */ public class WeChatAPI
/*  13:    */ {
/*  14: 15 */   private static Logger log = Logger.getLogger(WeChatAPI.class);
/*  15:    */   private static final String POST_MSG = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";
/*  16:    */   private static final String ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=";
/*  17:    */   private static final String USER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=";
/*  18:    */   private static final String CREATE_MENU = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";
/*  19:    */   private static final String USER_GET = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=";
/*  20:    */   private static final String QRCODE = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=";
/*  21:    */   private static final String SHOW_QRCODE = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=";
/*  22:    */   private static final String OAUTH = "https://api.weixin.qq.com/sns/oauth2/access_token?grant_type=authorization_code";
/*  23:    */   
/*  24:    */   public static String postMsg(String accessToken, String data)
/*  25:    */   {
/*  26: 42 */     String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + accessToken;
/*  27: 43 */     String errcode = "-1";
/*  28: 44 */     String result = threePost(url, data);
/*  29: 45 */     if (result != null) {
/*  30:    */       try
/*  31:    */       {
/*  32: 47 */         JSONObject json = new JSONObject(result);
/*  33: 48 */         errcode = json.getString("errcode");
/*  34:    */       }
/*  35:    */       catch (JSONException e)
/*  36:    */       {
/*  37: 50 */         log.fatal(result);
/*  38:    */       }
/*  39:    */     }
/*  40: 53 */     return errcode;
/*  41:    */   }
/*  42:    */   
/*  43:    */   public static String getAccessToken(String appId, String appSecret)
/*  44:    */   {
/*  45: 63 */     log.info("req   accessToken");
/*  46: 64 */     String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret;
/*  47: 65 */     String result = threeGet(url);
/*  48: 66 */     if (result != null)
/*  49:    */     {
/*  50: 67 */       JSONObject json = null;
/*  51:    */       try
/*  52:    */       {
/*  53: 69 */         json = new JSONObject(result);
/*  54: 70 */         return json.getString("access_token");
/*  55:    */       }
/*  56:    */       catch (JSONException e)
/*  57:    */       {
/*  58: 72 */         log.fatal(result);
/*  59:    */       }
/*  60:    */     }
/*  61: 75 */     return null;
/*  62:    */   }
/*  63:    */   
/*  64:    */   public static JSONObject getVips(String accessToken, String openId)
/*  65:    */   {
/*  66: 85 */     String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + accessToken + "&openid=" + openId;
/*  67: 86 */     String result = threeGet(url);
/*  68: 87 */     JSONObject json = null;
/*  69: 88 */     if (result != null) {
/*  70:    */       try
/*  71:    */       {
/*  72: 90 */         json = new JSONObject(result);
/*  73: 91 */         json.get("openid");
/*  74:    */       }
/*  75:    */       catch (JSONException e)
/*  76:    */       {
/*  77: 93 */         log.fatal(result);
/*  78:    */       }
/*  79:    */     }
/*  80: 96 */     return json;
/*  81:    */   }
/*  82:    */   
/*  83:    */   public static String creatMultMenu(String accessToken, String data)
/*  84:    */   {
/*  85:106 */     String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken;
/*  86:107 */     String result = threePost(url, data);
/*  87:108 */     String errcode = null;
/*  88:109 */     if (result != null)
/*  89:    */     {
/*  90:110 */       JSONObject json = null;
/*  91:    */       try
/*  92:    */       {
/*  93:112 */         json = new JSONObject(result);
/*  94:113 */         errcode = json.getString("errcode");
/*  95:    */       }
/*  96:    */       catch (JSONException e)
/*  97:    */       {
/*  98:115 */         log.fatal(result);
/*  99:    */       }
/* 100:    */     }
/* 101:118 */     return errcode;
/* 102:    */   }
/* 103:    */   
/* 104:    */   public static List<String> getUsers(String accessToken)
/* 105:    */   {
/* 106:127 */     List<String> list = new ArrayList();
/* 107:128 */     String url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=" + accessToken + "&next_openid=";
/* 108:129 */     String result = threeGet(url);
/* 109:130 */     if (result != null) {
/* 110:    */       try
/* 111:    */       {
/* 112:132 */         JSONObject json = new JSONObject(result);
/* 113:133 */         int total = json.getInt("total");
/* 114:134 */         String nextOpenId = json.getString("next_openid");
/* 115:135 */         String data = json.getString("data");
/* 116:136 */         JSONObject j = new JSONObject(data);
/* 117:137 */         JSONArray arr = j.getJSONArray("openid");
/* 118:138 */         for (int k = 0; k < arr.length(); k++) {
/* 119:139 */           list.add(arr.getString(k));
/* 120:    */         }
/* 121:141 */         int m = total / 10000;
/* 122:142 */         for (int n = 1; n < m; n++) {
/* 123:143 */           nextOpenId = getUsers(list, accessToken, nextOpenId);
/* 124:    */         }
/* 125:    */       }
/* 126:    */       catch (Exception e)
/* 127:    */       {
/* 128:146 */         log.fatal(result);
/* 129:    */       }
/* 130:    */     }
/* 131:149 */     return list;
/* 132:    */   }
/* 133:    */   
/* 134:    */   public static String getUsers(List<String> list, String accessToken, String nextOpenId)
/* 135:    */   {
/* 136:160 */     String url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=" + accessToken + "&next_openid=" + nextOpenId;
/* 137:161 */     String result = threeGet(url);
/* 138:162 */     if (result != null) {
/* 139:    */       try
/* 140:    */       {
/* 141:164 */         JSONObject json = new JSONObject(result);
/* 142:165 */         int count = json.getInt("count");
/* 143:166 */         nextOpenId = json.getString("next_openid");
/* 144:167 */         if (count > 0)
/* 145:    */         {
/* 146:168 */           String data = json.getString("data");
/* 147:169 */           JSONObject j = new JSONObject(data);
/* 148:170 */           JSONArray arr = j.getJSONArray("openid");
/* 149:171 */           for (int k = 0; k < arr.length(); k++) {
/* 150:172 */             list.add(arr.getString(k));
/* 151:    */           }
/* 152:    */         }
/* 153:    */       }
/* 154:    */       catch (JSONException e)
/* 155:    */       {
/* 156:176 */         log.fatal(result);
/* 157:    */       }
/* 158:    */     }
/* 159:179 */     return nextOpenId;
/* 160:    */   }
/* 161:    */   
/* 162:    */   public static String createQrcode(String accessToken, String data)
/* 163:    */   {
/* 164:190 */     String qrcodeUrl = null;
/* 165:191 */     String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + accessToken;
/* 166:192 */     String result = threePost(url, data);
/* 167:193 */     if (result != null) {
/* 168:    */       try
/* 169:    */       {
/* 170:195 */         JSONObject json = new JSONObject(result);
/* 171:196 */         qrcodeUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + json.getString("ticket");
/* 172:    */       }
/* 173:    */       catch (JSONException e)
/* 174:    */       {
/* 175:198 */         log.fatal(result);
/* 176:    */       }
/* 177:    */     }
/* 178:201 */     return qrcodeUrl;
/* 179:    */   }
/* 180:    */   
/* 181:    */   public static String getOpenIdByCode(String appId, String appSecret, String code)
/* 182:    */   {
/* 183:212 */     StringBuilder url = new StringBuilder("https://api.weixin.qq.com/sns/oauth2/access_token?grant_type=authorization_code");
/* 184:213 */     url.append("&appid=");
/* 185:214 */     url.append(appId);
/* 186:215 */     url.append("&secret=");
/* 187:216 */     url.append(appSecret);
/* 188:217 */     url.append("&code=");
/* 189:218 */     url.append(code);
/* 190:219 */     String result = threeGet(url.toString());
/* 191:220 */     String openId = null;
/* 192:221 */     if (result != null) {
/* 193:    */       try
/* 194:    */       {
/* 195:223 */         JSONObject json = new JSONObject(result);
/* 196:224 */         openId = json.getString("openid");
/* 197:    */       }
/* 198:    */       catch (JSONException e)
/* 199:    */       {
/* 200:226 */         log.fatal(result);
/* 201:    */       }
/* 202:    */     }
/* 203:229 */     return openId;
/* 204:    */   }
/* 205:    */   
/* 206:    */   private static String threeGet(String url)
/* 207:    */   {
/* 208:233 */     String result = null;
/* 209:234 */     int i = 0;
/* 210:235 */     while (i < 3)
/* 211:    */     {
/* 212:236 */       result = HttpUtil.get(url);
/* 213:237 */       if (result != null) {
/* 214:    */         break;
/* 215:    */       }
/* 216:240 */       i++;
/* 217:    */     }
/* 218:242 */     return result;
/* 219:    */   }
/* 220:    */   
/* 221:    */   private static String threePost(String url, String data)
/* 222:    */   {
/* 223:246 */     String result = null;
/* 224:247 */     int i = 0;
/* 225:248 */     while (i < 3)
/* 226:    */     {
/* 227:249 */       result = HttpUtil.post(url, data);
/* 228:250 */       if (result != null) {
/* 229:    */         break;
/* 230:    */       }
/* 231:253 */       i++;
/* 232:    */     }
/* 233:255 */     return result;
/* 234:    */   }
/* 235:    */   
/* 236:    */   public static String postMsg(ReplyEntity replyEntity)
/* 237:    */   {
/* 238:261 */     return postMsg(replyEntity.getAccessToken(), replyEntity.getContent());
/* 239:    */   }
/* 240:    */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.wechat.api.WeChatAPI
 * JD-Core Version:    0.7.0.1
 */