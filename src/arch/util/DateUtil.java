/*   1:    */ package arch.util;
/*   2:    */ 
/*   3:    */ import java.text.DateFormat;
/*   4:    */ import java.text.DecimalFormat;
/*   5:    */ import java.text.ParseException;
/*   6:    */ import java.text.ParsePosition;
/*   7:    */ import java.text.SimpleDateFormat;
/*   8:    */ import java.util.Calendar;
/*   9:    */ import java.util.Date;
/*  10:    */ import org.apache.log4j.Logger;
/*  11:    */ 
/*  12:    */ public class DateUtil
/*  13:    */ {
/*  14: 14 */   private static Logger log = Logger.getLogger(DateUtil.class);
/*  15: 15 */   private static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*  16: 16 */   private static SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
/*  17:    */   
/*  18:    */   public static String dateToString(Date date)
/*  19:    */   {
/*  20: 23 */     return sdfDate.format(date);
/*  21:    */   }
/*  22:    */   
/*  23:    */   public static String timeToString(Date date)
/*  24:    */   {
/*  25: 31 */     return sdfTime.format(date);
/*  26:    */   }
/*  27:    */   
/*  28:    */   public static Date stringToDate(String date)
/*  29:    */   {
/*  30: 39 */     log.debug("DateUtil.stringToDate()");
/*  31:    */     try
/*  32:    */     {
/*  33: 41 */       return sdfDate.parse(date);
/*  34:    */     }
/*  35:    */     catch (ParseException e)
/*  36:    */     {
/*  37: 43 */       log.fatal("DateUtil.stringToDate抛出异常", e);
/*  38:    */     }
/*  39: 44 */     return new Date();
/*  40:    */   }
/*  41:    */   
/*  42:    */   public static Date stringToTime(String time)
/*  43:    */   {
/*  44:    */     try
/*  45:    */     {
/*  46: 54 */       return sdfTime.parse(time);
/*  47:    */     }
/*  48:    */     catch (ParseException e)
/*  49:    */     {
/*  50: 56 */       log.fatal("DateUtil.stringToTime抛出异常", e);
/*  51:    */     }
/*  52: 57 */     return new Date();
/*  53:    */   }
/*  54:    */   
/*  55:    */   public static String getYYYYMMDDHHMMSS()
/*  56:    */   {
/*  57: 68 */     String nowTime = "";
/*  58: 69 */     Date now = new Date();
/*  59: 70 */     SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
/*  60: 71 */     nowTime = formatter.format(now);
/*  61: 72 */     return nowTime;
/*  62:    */   }
/*  63:    */   
/*  64:    */   public static String getYYYYMMDD()
/*  65:    */   {
/*  66: 81 */     String nowTime = "";
/*  67: 82 */     Date now = new Date();
/*  68: 83 */     SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
/*  69: 84 */     nowTime = formatter.format(now);
/*  70: 85 */     return nowTime;
/*  71:    */   }
/*  72:    */   
/*  73:    */   public static String getHHMM()
/*  74:    */   {
/*  75: 94 */     String nowTime = "";
/*  76: 95 */     Date now = new Date();
/*  77: 96 */     SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
/*  78: 97 */     nowTime = formatter.format(now);
/*  79: 98 */     return nowTime;
/*  80:    */   }
/*  81:    */   
/*  82:    */   public static int compareTime(String time1, String time2)
/*  83:    */   {
/*  84:110 */     String[] timeArr1 = (String[])null;
/*  85:111 */     String[] timeArr2 = (String[])null;
/*  86:112 */     timeArr1 = time1.split(":");
/*  87:113 */     timeArr2 = time2.split(":");
/*  88:114 */     int minute1 = Integer.valueOf(timeArr1[0]).intValue() * 60 + 
/*  89:115 */       Integer.valueOf(timeArr1[1]).intValue();
/*  90:116 */     int minute2 = Integer.valueOf(timeArr2[0]).intValue() * 60 + 
/*  91:117 */       Integer.valueOf(timeArr2[1]).intValue();
/*  92:118 */     return minute1 - minute2;
/*  93:    */   }
/*  94:    */   
/*  95:    */   public static String addTime(String time, int increase)
/*  96:    */   {
/*  97:129 */     SimpleDateFormat format = new SimpleDateFormat("HH:mm");
/*  98:130 */     String retTime = "";
/*  99:    */     try
/* 100:    */     {
/* 101:133 */       Date date = format.parse(time);
/* 102:134 */       long Time = date.getTime() / 1000L + increase * 60;
/* 103:135 */       date.setTime(Time * 1000L);
/* 104:136 */       retTime = format.format(date);
/* 105:    */     }
/* 106:    */     catch (Exception localException) {}
/* 107:141 */     return retTime;
/* 108:    */   }
/* 109:    */   
/* 110:    */   public static String getNextDay(String nowdate, int delay)
/* 111:    */   {
/* 112:    */     try
/* 113:    */     {
/* 114:155 */       SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
/* 115:156 */       String mdate = "";
/* 116:157 */       Date d = strToDate(nowdate);
/* 117:158 */       long myTime = d.getTime() / 1000L + delay * 24 * 
/* 118:159 */         60 * 60;
/* 119:160 */       d.setTime(myTime * 1000L);
/* 120:161 */       return format.format(d);
/* 121:    */     }
/* 122:    */     catch (Exception e) {}
/* 123:166 */     return "";
/* 124:    */   }
/* 125:    */   
/* 126:    */   public static Date strToDate(String strDate)
/* 127:    */   {
/* 128:178 */     SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
/* 129:179 */     ParsePosition pos = new ParsePosition(0);
/* 130:180 */     Date strtodate = formatter.parse(strDate, pos);
/* 131:181 */     return strtodate;
/* 132:    */   }
/* 133:    */   
/* 134:    */   public static Date strToTime(String strDate)
/* 135:    */   {
/* 136:192 */     SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
/* 137:193 */     ParsePosition pos = new ParsePosition(0);
/* 138:194 */     Date strtodate = formatter.parse(strDate, pos);
/* 139:195 */     return strtodate;
/* 140:    */   }
/* 141:    */   
/* 142:    */   public static String longToYYYYMMDD(long longTime)
/* 143:    */   {
/* 144:206 */     SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
/* 145:207 */     Date strtodate = new Date(longTime);
/* 146:208 */     return formatter.format(strtodate);
/* 147:    */   }
/* 148:    */   
/* 149:    */   public static String longToHHMM(long longTime)
/* 150:    */   {
/* 151:219 */     SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
/* 152:220 */     Date strtodate = new Date(longTime);
/* 153:221 */     return formatter.format(strtodate);
/* 154:    */   }
/* 155:    */   
/* 156:    */   public static String longToYYYYMMDDHHMM(long longTime)
/* 157:    */   {
/* 158:232 */     SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HH:mm");
/* 159:233 */     Date strtodate = new Date(longTime);
/* 160:234 */     return formatter.format(strtodate);
/* 161:    */   }
/* 162:    */   
/* 163:    */   public static int getQuot(String time1, String time2)
/* 164:    */     throws ParseException
/* 165:    */   {
/* 166:239 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 167:240 */     Calendar cal = Calendar.getInstance();
/* 168:241 */     cal.setTime(sdf.parse(time1));
/* 169:242 */     long start = cal.getTimeInMillis();
/* 170:243 */     cal.setTime(sdf.parse(time2));
/* 171:244 */     long end = cal.getTimeInMillis();
/* 172:245 */     long between_days = (end - start) / 86400000L;
/* 173:    */     
/* 174:247 */     return Integer.parseInt(String.valueOf(between_days));
/* 175:    */   }
/* 176:    */   
/* 177:    */   public static double getAttHour(String time1, String time2)
/* 178:    */     throws ParseException
/* 179:    */   {
/* 180:258 */     double hour = 0.0D;
/* 181:259 */     DateFormat fulDate = new SimpleDateFormat("HH:mm");
/* 182:260 */     long t12 = fulDate.parse("12:00").getTime();
/* 183:261 */     long t13 = fulDate.parse("13:00").getTime();
/* 184:262 */     long t1 = fulDate.parse(time1).getTime();
/* 185:263 */     long PERHOUR = 3600000L;
/* 186:264 */     if (time2 == null)
/* 187:    */     {
/* 188:265 */       if (t12 - t1 > 0L) {
/* 189:266 */         hour = (t12 - t1) / PERHOUR;
/* 190:    */       }
/* 191:    */     }
/* 192:    */     else
/* 193:    */     {
/* 194:269 */       long t2 = fulDate.parse(time2).getTime();
/* 195:270 */       if ((t1 <= t12) && (t2 >= t12) && (t2 <= t13)) {
/* 196:271 */         hour = (t12 - t1) / PERHOUR;
/* 197:272 */       } else if ((t1 <= t12) && (t2 >= t13)) {
/* 198:273 */         hour = (t2 - t1) / PERHOUR - 1.0D;
/* 199:274 */       } else if ((t1 >= t12) && (t1 <= t13) && (t2 >= t12) && (t2 <= t13)) {
/* 200:275 */         hour = 0.0D;
/* 201:276 */       } else if ((t1 >= t12) && (t1 <= t13) && (t2 >= t13)) {
/* 202:277 */         hour = (t2 - t13) / PERHOUR;
/* 203:    */       } else {
/* 204:279 */         hour = (t2 - t1) / PERHOUR;
/* 205:    */       }
/* 206:    */     }
/* 207:282 */     DecimalFormat df = new DecimalFormat("#.0");
/* 208:283 */     return Double.parseDouble(df.format(hour));
/* 209:    */   }
/* 210:    */   
/* 211:    */   public static String longToStringDate(long l)
/* 212:    */   {
/* 213:293 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
/* 214:294 */     String date = sdf.format(new Date(l * 1000L));
/* 215:295 */     return date;
/* 216:    */   }
/* 217:    */   
/* 218:    */   public static void main(String[] args)
/* 219:    */     throws ParseException
/* 220:    */   {
/* 221:299 */     log.debug(Double.valueOf(getAttHour("14:15", "18:00")));
/* 222:    */   }
/* 223:    */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.util.DateUtil
 * JD-Core Version:    0.7.0.1
 */