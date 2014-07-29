/*  1:   */ package arch.plugin.temp;
/*  2:   */ 
/*  3:   */ import arch.entity.Item;
/*  4:   */ import arch.properties.UtilProperties;
/*  5:   */ import java.io.IOException;
/*  6:   */ import java.io.UnsupportedEncodingException;
/*  7:   */ import java.util.ArrayList;
/*  8:   */ import java.util.List;
/*  9:   */ import org.apache.http.client.HttpClient;
/* 10:   */ import org.apache.http.client.ResponseHandler;
/* 11:   */ import org.apache.http.client.methods.HttpGet;
/* 12:   */ import org.apache.http.impl.client.BasicResponseHandler;
/* 13:   */ import org.apache.http.impl.client.DefaultHttpClient;
/* 14:   */ 
/* 15:   */ public class PlaceUtil
/* 16:   */ {
/* 17:   */   public static List<Item> getItems(String location_X, String location_Y, String query)
/* 18:   */     throws Exception
/* 19:   */   {
/* 20:19 */     List<Item> items = new ArrayList();
/* 21:20 */     Item item0 = new Item();
/* 22:   */     
/* 23:22 */     item0.setTitle("附近的兴化农村商业银行网点：");
/* 24:23 */     item0.setPicUrl(UtilProperties.getHost() + "images/index/title.jpg");
/* 25:24 */     item0.setUrl("");
/* 26:25 */     item0.setDescription("");
/* 27:26 */     items.add(item0);
/* 28:27 */     String xml = getPlaces(location_X, location_Y, query);
/* 29:28 */     List<BaiduPlaceResponse> placeList = BaiduPlaceResponse.getBaiduPlace(xml);
/* 30:29 */     if (placeList.size() > 0)
/* 31:   */     {
/* 32:30 */       for (BaiduPlaceResponse place : placeList)
/* 33:   */       {
/* 34:31 */         Item item = new Item();
/* 35:32 */         item.setTitle("【" + place.getName() + "】   <" + place.getDistance(location_X, location_Y) + "KM>\n" + 
/* 36:33 */           place.getAddress() + "\n" + place.getTelephone());
/* 37:34 */         item.setPicUrl(UtilProperties.getHost() + "images/index/location.jpg");
/* 38:35 */         item.setDescription("");
/* 39:36 */         item.setUrl("http://api.map.baidu.com/direction?origin=latlng:" + location_X + "," + location_Y + "|name:目前位置&destination=latlng:" + 
/* 40:37 */           place.getLat() + "," + place.getLng() + "|name:" + place.getName() + "&mode=driving&region=兴化&output=html&src=yourCompanyName|yourAppName");
/* 41:38 */         items.add(item);
/* 42:39 */         if (items.size() > 9) {
/* 43:   */           break;
/* 44:   */         }
/* 45:   */       }
/* 46:   */     }
/* 47:   */     else
/* 48:   */     {
/* 49:44 */       Item item = new Item();
/* 50:45 */       item.setTitle("【未搜索到结果】");
/* 51:46 */       item.setPicUrl("");
/* 52:47 */       item.setUrl("");
/* 53:48 */       items.add(item);
/* 54:   */     }
/* 55:51 */     return items;
/* 56:   */   }
/* 57:   */   
/* 58:   */   public static String getPlaces(String location_X, String location_Y, String query)
/* 59:   */     throws IOException
/* 60:   */   {
/* 61:56 */     HttpClient httpClient = new DefaultHttpClient();
/* 62:57 */     String url = palceRequestUrl(query, location_X, location_Y);
/* 63:58 */     HttpGet httpget = new HttpGet(url);
/* 64:59 */     ResponseHandler<String> responseHandler = new BasicResponseHandler();
/* 65:60 */     String responseBody = (String)httpClient.execute(httpget, responseHandler);
/* 66:61 */     return responseBody;
/* 67:   */   }
/* 68:   */   
/* 69:   */   public static String palceRequestUrl(String query, String lat, String lng)
/* 70:   */     throws UnsupportedEncodingException
/* 71:   */   {
/* 72:65 */     String url = "http://api.map.baidu.com/place/v2/search?&query=" + query + "&location=" + lat + "," + lng + "&radius=15000&output=xml&ak=768a584cb6e8259d63b27d4f97f8471d";
/* 73:66 */     return url;
/* 74:   */   }
/* 75:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.plugin.temp.PlaceUtil
 * JD-Core Version:    0.7.0.1
 */