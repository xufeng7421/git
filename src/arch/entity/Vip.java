/*   1:    */ package arch.entity;
/*   2:    */ 
/*   3:    */ import arch.util.DateUtil;
/*   4:    */ import org.json.JSONObject;
/*   5:    */ 
/*   6:    */ public class Vip
/*   7:    */ {
/*   8:    */   private String vipId;
/*   9:    */   private String tradeId;
/*  10:    */   private String companyId;
/*  11:    */   private String wechatId;
/*  12:    */   private String openId;
/*  13:    */   private String cardId;
/*  14:    */   private String nickName;
/*  15:    */   private String realName;
/*  16:    */   private String mobile;
/*  17:    */   private String address;
/*  18:    */   private String isActive;
/*  19:    */   private String activeTime;
/*  20:    */   private String picUrl;
/*  21:    */   private String sex;
/*  22:    */   private String city;
/*  23:    */   private String country;
/*  24:    */   private String province;
/*  25:    */   private String language;
/*  26:    */   private String headimgurl;
/*  27:    */   private String subscribe;
/*  28:    */   private String subscribe_time;
/*  29:    */   
/*  30:    */   public String getVipId()
/*  31:    */   {
/*  32: 58 */     return this.vipId;
/*  33:    */   }
/*  34:    */   
/*  35:    */   public void setVipId(String vipId)
/*  36:    */   {
/*  37: 61 */     this.vipId = vipId;
/*  38:    */   }
/*  39:    */   
/*  40:    */   public String getTradeId()
/*  41:    */   {
/*  42: 64 */     return this.tradeId;
/*  43:    */   }
/*  44:    */   
/*  45:    */   public void setTradeId(String tradeId)
/*  46:    */   {
/*  47: 67 */     this.tradeId = tradeId;
/*  48:    */   }
/*  49:    */   
/*  50:    */   public String getCompanyId()
/*  51:    */   {
/*  52: 70 */     return this.companyId;
/*  53:    */   }
/*  54:    */   
/*  55:    */   public void setCompanyId(String companyId)
/*  56:    */   {
/*  57: 73 */     this.companyId = companyId;
/*  58:    */   }
/*  59:    */   
/*  60:    */   public String getOpenId()
/*  61:    */   {
/*  62: 76 */     return this.openId;
/*  63:    */   }
/*  64:    */   
/*  65:    */   public void setOpenId(String openId)
/*  66:    */   {
/*  67: 79 */     this.openId = openId;
/*  68:    */   }
/*  69:    */   
/*  70:    */   public String getCardId()
/*  71:    */   {
/*  72: 82 */     return this.cardId;
/*  73:    */   }
/*  74:    */   
/*  75:    */   public void setCardId(String cardId)
/*  76:    */   {
/*  77: 85 */     this.cardId = cardId;
/*  78:    */   }
/*  79:    */   
/*  80:    */   public String getNickName()
/*  81:    */   {
/*  82: 88 */     return this.nickName;
/*  83:    */   }
/*  84:    */   
/*  85:    */   public void setNickName(String nickName)
/*  86:    */   {
/*  87: 91 */     this.nickName = nickName;
/*  88:    */   }
/*  89:    */   
/*  90:    */   public String getRealName()
/*  91:    */   {
/*  92: 94 */     return this.realName;
/*  93:    */   }
/*  94:    */   
/*  95:    */   public void setRealName(String realName)
/*  96:    */   {
/*  97: 97 */     this.realName = realName;
/*  98:    */   }
/*  99:    */   
/* 100:    */   public String getMobile()
/* 101:    */   {
/* 102:100 */     return this.mobile;
/* 103:    */   }
/* 104:    */   
/* 105:    */   public void setMobile(String mobile)
/* 106:    */   {
/* 107:103 */     this.mobile = mobile;
/* 108:    */   }
/* 109:    */   
/* 110:    */   public String getAddress()
/* 111:    */   {
/* 112:106 */     return this.address;
/* 113:    */   }
/* 114:    */   
/* 115:    */   public void setAddress(String address)
/* 116:    */   {
/* 117:109 */     this.address = address;
/* 118:    */   }
/* 119:    */   
/* 120:    */   public String getActiveTime()
/* 121:    */   {
/* 122:112 */     return this.activeTime;
/* 123:    */   }
/* 124:    */   
/* 125:    */   public void setActiveTime(String activeTime)
/* 126:    */   {
/* 127:115 */     this.activeTime = activeTime;
/* 128:    */   }
/* 129:    */   
/* 130:    */   public String getPicUrl()
/* 131:    */   {
/* 132:118 */     return this.picUrl;
/* 133:    */   }
/* 134:    */   
/* 135:    */   public void setPicUrl(String picUrl)
/* 136:    */   {
/* 137:121 */     this.picUrl = picUrl;
/* 138:    */   }
/* 139:    */   
/* 140:    */   public String getSex()
/* 141:    */   {
/* 142:124 */     return this.sex;
/* 143:    */   }
/* 144:    */   
/* 145:    */   public void setSex(String sex)
/* 146:    */   {
/* 147:127 */     this.sex = sex;
/* 148:    */   }
/* 149:    */   
/* 150:    */   public String getCity()
/* 151:    */   {
/* 152:130 */     return this.city;
/* 153:    */   }
/* 154:    */   
/* 155:    */   public void setCity(String city)
/* 156:    */   {
/* 157:133 */     this.city = city;
/* 158:    */   }
/* 159:    */   
/* 160:    */   public String getCountry()
/* 161:    */   {
/* 162:136 */     return this.country;
/* 163:    */   }
/* 164:    */   
/* 165:    */   public void setCountry(String country)
/* 166:    */   {
/* 167:139 */     this.country = country;
/* 168:    */   }
/* 169:    */   
/* 170:    */   public String getProvince()
/* 171:    */   {
/* 172:142 */     return this.province;
/* 173:    */   }
/* 174:    */   
/* 175:    */   public void setProvince(String province)
/* 176:    */   {
/* 177:145 */     this.province = province;
/* 178:    */   }
/* 179:    */   
/* 180:    */   public String getLanguage()
/* 181:    */   {
/* 182:148 */     return this.language;
/* 183:    */   }
/* 184:    */   
/* 185:    */   public void setLanguage(String language)
/* 186:    */   {
/* 187:151 */     this.language = language;
/* 188:    */   }
/* 189:    */   
/* 190:    */   public String getHeadimgurl()
/* 191:    */   {
/* 192:154 */     return this.headimgurl;
/* 193:    */   }
/* 194:    */   
/* 195:    */   public void setHeadimgurl(String headimgurl)
/* 196:    */   {
/* 197:157 */     this.headimgurl = headimgurl;
/* 198:    */   }
/* 199:    */   
/* 200:    */   public String getSubscribe()
/* 201:    */   {
/* 202:160 */     return this.subscribe;
/* 203:    */   }
/* 204:    */   
/* 205:    */   public void setSubscribe(String subscribe)
/* 206:    */   {
/* 207:163 */     this.subscribe = subscribe;
/* 208:    */   }
/* 209:    */   
/* 210:    */   public String getSubscribe_time()
/* 211:    */   {
/* 212:166 */     return this.subscribe_time;
/* 213:    */   }
/* 214:    */   
/* 215:    */   public void setSubscribe_time(String subscribeTime)
/* 216:    */   {
/* 217:169 */     this.subscribe_time = subscribeTime;
/* 218:    */   }
/* 219:    */   
/* 220:    */   public String getIsActive()
/* 221:    */   {
/* 222:172 */     return this.isActive;
/* 223:    */   }
/* 224:    */   
/* 225:    */   public void setIsActive(String isActive)
/* 226:    */   {
/* 227:175 */     this.isActive = isActive;
/* 228:    */   }
/* 229:    */   
/* 230:    */   public String getWechatId()
/* 231:    */   {
/* 232:179 */     return this.wechatId;
/* 233:    */   }
/* 234:    */   
/* 235:    */   public void setWechatId(String wechatId)
/* 236:    */   {
/* 237:182 */     this.wechatId = wechatId;
/* 238:    */   }
/* 239:    */   
/* 240:    */   public Vip() {}
/* 241:    */   
/* 242:    */   public Vip(JSONObject json)
/* 243:    */     throws Exception
/* 244:    */   {
/* 245:189 */     this.openId = json.getString("openid");
/* 246:190 */     this.nickName = new String(json.getString("nickname").getBytes("GBK"), "GBK");
/* 247:191 */     this.sex = json.getString("sex");
/* 248:192 */     this.subscribe = json.getString("subscribe");
/* 249:193 */     this.language = json.getString("language");
/* 250:194 */     this.city = json.getString("city");
/* 251:195 */     this.province = json.getString("province");
/* 252:196 */     this.country = json.getString("country");
/* 253:197 */     this.headimgurl = json.getString("headimgurl");
/* 254:198 */     this.subscribe_time = DateUtil.longToStringDate(json.getLong("subscribe_time"));
/* 255:    */   }
/* 256:    */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.entity.Vip
 * JD-Core Version:    0.7.0.1
 */