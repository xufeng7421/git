/*   1:    */ package arch.util;
/*   2:    */ 
/*   3:    */ import arch.entity.Item;
/*   4:    */ import arch.properties.UtilProperties;
/*   5:    */ import java.util.List;
/*   6:    */ import org.apache.log4j.Logger;
/*   7:    */ import org.json.JSONArray;
/*   8:    */ import org.json.JSONException;
/*   9:    */ import org.json.JSONObject;
/*  10:    */ 
/*  11:    */ public class MsgUtil
/*  12:    */ {
/*  13: 18 */   private static Logger log = Logger.getLogger(MsgUtil.class);
/*  14:    */   
/*  15:    */   public static String parseTextMsg(String openId, String content)
/*  16:    */   {
/*  17:    */     try
/*  18:    */     {
/*  19: 27 */       JSONObject text = new JSONObject();
/*  20: 28 */       text.put("content", content);
/*  21: 29 */       JSONObject json = new JSONObject();
/*  22: 30 */       json.put("touser", openId);
/*  23: 31 */       json.put("msgtype", "text");
/*  24: 32 */       json.put("text", text);
/*  25: 33 */       return json.toString();
/*  26:    */     }
/*  27:    */     catch (JSONException e)
/*  28:    */     {
/*  29: 35 */       log.fatal("格式化文本信息异常", e);
/*  30:    */     }
/*  31: 37 */     return null;
/*  32:    */   }
/*  33:    */   
/*  34:    */   public static String parseNewsMsg(String openId, List<Item> list)
/*  35:    */   {
/*  36: 47 */     JSONObject data = new JSONObject();
/*  37:    */     try
/*  38:    */     {
/*  39: 49 */       JSONArray arr = new JSONArray();
/*  40: 50 */       for (Item item : list)
/*  41:    */       {
/*  42: 51 */         JSONObject j = new JSONObject();
/*  43: 52 */         j.put("title", item.getTitle());
/*  44: 53 */         j.put("description", item.getDescription());
/*  45: 54 */         j.put("url", item.getUrl());
/*  46: 55 */         j.put("picurl", item.getPicUrl());
/*  47: 56 */         arr.put(j);
/*  48:    */       }
/*  49: 58 */       JSONObject art = new JSONObject();
/*  50: 59 */       art.put("articles", arr);
/*  51: 60 */       data.put("news", art);
/*  52: 61 */       data.put("touser", openId);
/*  53: 62 */       data.put("msgtype", "news");
/*  54: 63 */       return data.toString();
/*  55:    */     }
/*  56:    */     catch (JSONException e)
/*  57:    */     {
/*  58: 65 */       log.fatal("格式化图文信息异常", e);
/*  59:    */     }
/*  60: 67 */     return null;
/*  61:    */   }
/*  62:    */   
/*  63:    */   public static String parseNewsMsg(String openId, Item item)
/*  64:    */   {
/*  65: 77 */     JSONObject data = new JSONObject();
/*  66:    */     try
/*  67:    */     {
/*  68: 79 */       JSONArray arr = new JSONArray();
/*  69: 80 */       JSONObject j = new JSONObject();
/*  70: 81 */       j.put("title", item.getTitle());
/*  71: 82 */       j.put("description", item.getDescription());
/*  72: 83 */       j.put("url", item.getUrl());
/*  73: 84 */       j.put("picurl", item.getPicUrl());
/*  74: 85 */       arr.put(j);
/*  75: 86 */       JSONObject art = new JSONObject();
/*  76: 87 */       art.put("articles", arr);
/*  77: 88 */       data.put("news", art);
/*  78: 89 */       data.put("touser", openId);
/*  79: 90 */       data.put("msgtype", "news");
/*  80: 91 */       return data.toString();
/*  81:    */     }
/*  82:    */     catch (JSONException e)
/*  83:    */     {
/*  84: 93 */       log.fatal("格式化图文信息异常", e);
/*  85:    */     }
/*  86: 95 */     return null;
/*  87:    */   }
/*  88:    */   
/*  89:    */   public static String getAddress(String url, String address)
/*  90:    */   {
/*  91:105 */     StringBuilder s = new StringBuilder();
/*  92:106 */     s.append("<a href=\"");
/*  93:107 */     s.append(url);
/*  94:108 */     s.append("\">");
/*  95:109 */     s.append(address);
/*  96:110 */     s.append("</a>");
/*  97:111 */     return s.toString();
/*  98:    */   }
/*  99:    */   
/* 100:    */   public static String parseLimitScene(int sceneId)
/* 101:    */   {
/* 102:    */     try
/* 103:    */     {
/* 104:121 */       JSONObject data = new JSONObject();
/* 105:122 */       JSONObject json = new JSONObject();
/* 106:123 */       json.put("scene_id", sceneId);
/* 107:124 */       JSONObject info = new JSONObject();
/* 108:125 */       info.put("scene", json);
/* 109:126 */       data.put("action_name", "QR_LIMIT_SCENE");
/* 110:127 */       data.put("action_info", info);
/* 111:128 */       return data.toString();
/* 112:    */     }
/* 113:    */     catch (JSONException e)
/* 114:    */     {
/* 115:130 */       log.fatal("格式化永久二维码参数异常", e);
/* 116:    */     }
/* 117:132 */     return null;
/* 118:    */   }
/* 119:    */   
/* 120:    */   public static String parseScene(int seconds, int sceneId)
/* 121:    */   {
/* 122:142 */     JSONObject data = new JSONObject();
/* 123:    */     try
/* 124:    */     {
/* 125:144 */       JSONObject json = new JSONObject();
/* 126:145 */       json.put("scene_id", sceneId);
/* 127:146 */       JSONObject info = new JSONObject();
/* 128:147 */       info.put("scene", json);
/* 129:148 */       data.put("expire_seconds", seconds);
/* 130:149 */       data.put("action_name", "QR_SCENE");
/* 131:150 */       data.put("action_info", info);
/* 132:151 */       return data.toString();
/* 133:    */     }
/* 134:    */     catch (JSONException e)
/* 135:    */     {
/* 136:153 */       log.fatal("格式化临时二维码参数异常", e);
/* 137:    */     }
/* 138:155 */     return null;
/* 139:    */   }
/* 140:    */   
/* 141:    */   public static String getActionUrl(String openId, String action, String id, String companyId)
/* 142:    */   {
/* 143:167 */     StringBuilder sb = new StringBuilder();
/* 144:168 */     sb.append(UtilProperties.getHost());
/* 145:169 */     sb.append(action);
/* 146:170 */     sb.append("?openId=");
/* 147:171 */     sb.append(openId);
/* 148:172 */     if (id != null)
/* 149:    */     {
/* 150:173 */       sb.append("&id=");
/* 151:174 */       sb.append(id);
/* 152:    */     }
/* 153:176 */     sb.append("&state=");
/* 154:177 */     sb.append(companyId);
/* 155:178 */     sb.append("#wechat_redirect");
/* 156:179 */     return sb.toString();
/* 157:    */   }
/* 158:    */   
/* 159:    */   public static String getOAuthUrl(String appId, String action, String id, String companyId)
/* 160:    */   {
/* 161:191 */     StringBuilder sb = new StringBuilder();
/* 162:192 */     sb.append("https://open.weixin.qq.com/connect/oauth2/authorize?appid=");
/* 163:193 */     sb.append(appId);
/* 164:194 */     sb.append("&redirect_uri=");
/* 165:195 */     sb.append(UtilProperties.getHost());
/* 166:196 */     sb.append(action);
/* 167:197 */     if (id != null)
/* 168:    */     {
/* 169:198 */       sb.append("?id=");
/* 170:199 */       sb.append(id);
/* 171:    */     }
/* 172:201 */     sb.append("&response_type=code&scope=snsapi_base&state=");
/* 173:202 */     sb.append(companyId);
/* 174:203 */     sb.append("#wechat_redirect");
/* 175:204 */     return sb.toString();
/* 176:    */   }
/* 177:    */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.util.MsgUtil
 * JD-Core Version:    0.7.0.1
 */