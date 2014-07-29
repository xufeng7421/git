/*  1:   */ package arch.plugin.temp;
/*  2:   */ 
/*  3:   */ public class GeoUtil
/*  4:   */ {
/*  5:   */   public static enum GaussSphere
/*  6:   */   {
/*  7: 7 */     Beijing54,  Xian80,  WGS84;
/*  8:   */   }
/*  9:   */   
/* 10:   */   private static double Rad(double d)
/* 11:   */   {
/* 12:11 */     return d * 3.141592653589793D / 180.0D;
/* 13:   */   }
/* 14:   */   
/* 15:   */   public static double DistanceOfTwoPoints(double lng1, double lat1, double lng2, double lat2, GaussSphere gs)
/* 16:   */   {
/* 17:16 */     double radLat1 = Rad(lat1);
/* 18:17 */     double radLat2 = Rad(lat2);
/* 19:18 */     double a = radLat1 - radLat2;
/* 20:19 */     double b = Rad(lng1) - Rad(lng2);
/* 21:20 */     double s = 2.0D * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2.0D), 2.0D) + 
/* 22:21 */       Math.cos(radLat1) * Math.cos(radLat2) * 
/* 23:22 */       Math.pow(Math.sin(b / 2.0D), 2.0D)));
/* 24:23 */     s *= (gs == GaussSphere.Xian80 ? 6378140.0D : gs == GaussSphere.WGS84 ? 6378137.0D : 6378245.0D);
/* 25:24 */     s = Math.round(s * 10000.0D) / 10000L / 1000L;
/* 26:25 */     return s;
/* 27:   */   }
/* 28:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.plugin.temp.GeoUtil
 * JD-Core Version:    0.7.0.1
 */