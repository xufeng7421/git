/*   1:    */package arch.wechat.cache;

/*   2:    */
/*   3:    */import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

/*  14:    */
/*  15:    */public class CacheManager
/* 16: */{
	/* 17: 16 */private static Logger log = Logger.getLogger(CacheManager.class);
	/* 18: 17 */private static HashMap<String, Cache> cache = new HashMap();
	/* 19: 18 */private static HashMap<String, Cache> msgCache = new HashMap();
	/* 20: 19 */private static String profilepath = "cache.properties";

	/* 21: */
	/* 22: */public static void loadCache()
	/* 23: */{
		/* 24: 29 */Properties pro = new Properties();
		/* 25: 30 */InputStream in = CacheManager.class.getClassLoader().getResourceAsStream(profilepath);
		/* 26: */try
		/* 27: */{
			/* 28: 32 */pro.load(in);
			/* 29: 33 */Set<Object> set = pro.keySet();
			/* 30: 34 */for (Object openId : set)
			/* 31: */{
				/* 32: 35 */Object businessId = pro.get(openId);
				/* 33: 36 */cache.put((String) openId, new Cache((String) businessId));
				/* 34: */}
			/* 35: */}
		/* 36: */catch (IOException e)
		/* 37: */{
			/* 38: 39 */log.fatal("load Cache Exception", e);
			/* 39: 41 */if (in != null) {
				/* 40: */try
				/* 41: */{
					/* 42: 43 */in.close();
					/* 43: */}
				/* 44: */catch (IOException e1)
				/* 45: */{
					/* 46: 45 */log.fatal("close IO Exception ");
					/* 47: */}
				/* 48: */}
			/* 49: */}
		/* 50: */finally
		/* 51: */{
			/* 52: 41 */if (in != null) {
				/* 53: */try
				/* 54: */{
					/* 55: 43 */in.close();
					/* 56: */}
				/* 57: */catch (IOException e)
				/* 58: */{
					/* 59: 45 */log.fatal("close IO Exception ");
					/* 60: */}
				/* 61: */}
			/* 62: */}
		/* 63: */}

	/* 64: */
	/* 65: */public static void saveCache()
	/* 66: */{
		/* 67: 55 */String filePath = CacheManager.class.getClassLoader().getResource("/").getPath();
		/* 68: 56 */Properties pro = new Properties();
		/* 69: 57 */OutputStream out = null;
		/* 70: */try
		/* 71: */{
			/* 72: 59 */out = new FileOutputStream(filePath + profilepath, false);
			/* 73: 60 */Set<String> keys = getCacheKeys();
			/* 74: 61 */for (String openId : keys) {
				/* 75: 62 */pro.setProperty(openId, ((Cache) cache.get(openId)).getBusinessId());
				/* 76: */}
			/* 77: 64 */pro.store(out, "openId---businessId");
			/* 78: */}
		/* 79: */catch (FileNotFoundException e)
		/* 80: */{
			/* 81: 66 */log.fatal("load Cache Exception", e);
			/* 82: 70 */if (out != null) {
				/* 83: */try
				/* 84: */{
					/* 85: 72 */out.close();
					/* 86: */}
				/* 87: */catch (IOException e1)
				/* 88: */{
					/* 89: 74 */log.fatal("close IO Exception ");
					/* 90: */}
				/* 91: */}
			/* 92: */}
		/* 93: */catch (IOException e)
		/* 94: */{
			/* 95: 68 */log.fatal("load Cache Exception", e);
			/* 96: 70 */if (out != null) {
				/* 97: */try
				/* 98: */{
					/* 99: 72 */out.close();
					/* 100: */}
				/* 101: */catch (IOException e1)
				/* 102: */{
					/* 103: 74 */log.fatal("close IO Exception ");
					/* 104: */}
				/* 105: */}
			/* 106: */}
		/* 107: */finally
		/* 108: */{
			/* 109: 70 */if (out != null) {
				/* 110: */try
				/* 111: */{
					/* 112: 72 */out.close();
					/* 113: */}
				/* 114: */catch (IOException e)
				/* 115: */{
					/* 116: 74 */log.fatal("close IO Exception ");
					/* 117: */}
				/* 118: */}
			/* 119: */}
		/* 120: */}

	/* 121: */
	/* 122: */public static boolean getMsgFlag(String openId)
	/* 123: */{
		/* 124: 87 */if (msgCache.get(openId) != null) {
			/* 125: 88 */return true;
			/* 126: */}
		/* 127: 90 */return false;
		/* 128: */}

	/* 129: */
	/* 130: */public static synchronized void clearMsg()
	/* 131: */{
		/* 132: 97 */Set<String> keys = getMsgKeys();
		/* 133: 98 */for (String openId : keys) {
			/* 134: 99 */if (new Date().getTime() - getMsg(openId).getDate().longValue() > 5000L) {
				/* 135:100 */clearMsgCache(openId);
				/* 136: */}
			/* 137: */}
		/* 138: */}

	/* 139: */
	/* 140: */public static synchronized void clearMsgCache(String openId)
	/* 141: */{
		/* 142:106 */msgCache.remove(openId);
		/* 143: */}

	/* 144: */
	/* 145: */public static synchronized void clearCache()
	/* 146: */{
		/* 147:113 */Set<String> keys = getCacheKeys();
		/* 148:114 */for (String openId : keys) {
			/* 149:115 */if (new Date().getTime() - getCache(openId).getDate().longValue() > 5000L) {
				/* 150:116 */clearCache(openId);
				/* 151: */}
			/* 152: */}
		/* 153: */}

	/* 154: */
	/* 155: */public static synchronized void clearCache(String openId)
	/* 156: */{
		/* 157:122 */cache.remove(openId);
		/* 158: */}

	/* 159: */
	/* 160: */public static boolean getCacheFlag(String openId)
	/* 161: */{
		/* 162:126 */if (cache.get(openId) != null) {
			/* 163:127 */return true;
			/* 164: */}
		/* 165:129 */return false;
		/* 166: */}

	/* 167: */
	/* 168: */public static void setMsgCache(String openId, String msgId)
	/* 169: */{
		/* 170:133 */Cache c = new Cache(msgId);
		/* 171:134 */if (Runtime.getRuntime().freeMemory() < 5242880L) {
			/* 172:135 */clearMsg();
			/* 173: */}
		/* 174:137 */msgCache.put(openId, c);
		/* 175: */}

	/* 176: */
	/* 177: */public static void setCache(String openId, String businessId)
	/* 178: */{
		/* 179:141 */Cache c = new Cache(businessId);
		/* 180:142 */cache.put(openId, c);
		/* 181:143 */writeCache(openId, c);
		/* 182: */}

	/* 183: */
	/* 184: */public static Set<String> getCacheKeys()
	/* 185: */{
		/* 186:147 */return cache.keySet();
		/* 187: */}

	/* 188: */
	/* 189: */public static Set<String> getMsgKeys()
	/* 190: */{
		/* 191:151 */return msgCache.keySet();
		/* 192: */}

	/* 193: */
	/* 194: */public static synchronized Cache getCache(String openId)
	/* 195: */{
		/* 196:154 */return (Cache) cache.get(openId);
		/* 197: */}

	/* 198: */
	/* 199: */public static synchronized Cache getMsg(String openId)
	/* 200: */{
		/* 201:158 */return (Cache) msgCache.get(openId);
		/* 202: */}

	/* 203: */
	/* 204: */public static synchronized void updCache(String openId)
	/* 205: */{
		/* 206:162 */getCache(openId).setDate(Long.valueOf(new Date().getTime()));
		/* 207: */}

	/* 208: */
	/* 209: */public static void writeCache(String openId, Cache cache)
	/* 210: */{
		/* 211:167 */String filePath = CacheManager.class.getClassLoader().getResource("/").getPath();
		/* 212:168 */Properties pro = new Properties();
		/* 213:169 */OutputStream out = null;
		/* 214: */try
		/* 215: */{
			/* 216:171 */out = new FileOutputStream(filePath + profilepath, true);
			/* 217:172 */pro.setProperty(openId, cache.getBusinessId());
			/* 218:173 */pro.store(out, "openId---businessId");
			/* 219: */}
		/* 220: */catch (FileNotFoundException e)
		/* 221: */{
			/* 222:175 */log.fatal("write Cache Exception", e);
			/* 223:179 */if (out != null) {
				/* 224: */try
				/* 225: */{
					/* 226:181 */out.close();
					/* 227: */}
				/* 228: */catch (IOException e1)
				/* 229: */{
					/* 230:183 */log.fatal("close IO Exception ");
					/* 231: */}
				/* 232: */}
			/* 233: */}
		/* 234: */catch (IOException e)
		/* 235: */{
			/* 236:177 */log.fatal("write Cache Exception", e);
			/* 237:179 */if (out != null) {
				/* 238: */try
				/* 239: */{
					/* 240:181 */out.close();
					/* 241: */}
				/* 242: */catch (IOException e1)
				/* 243: */{
					/* 244:183 */log.fatal("close IO Exception ");
					/* 245: */}
				/* 246: */}
			/* 247: */}
		/* 248: */finally
		/* 249: */{
			/* 250:179 */if (out != null) {
				/* 251: */try
				/* 252: */{
					/* 253:181 */out.close();
					/* 254: */}
				/* 255: */catch (IOException e)
				/* 256: */{
					/* 257:183 */log.fatal("close IO Exception ");
					/* 258: */}
				/* 259: */}
			/* 260: */}
		/* 261: */}
	/* 262: */
}

/*
 * Location: C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * 
 * Qualified Name: arch.wechat.cache.CacheManager
 * 
 * JD-Core Version: 0.7.0.1
 */