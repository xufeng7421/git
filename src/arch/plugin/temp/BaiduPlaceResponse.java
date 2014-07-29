/*   1:    */ package arch.plugin.temp;
/*   2:    */ 
/*   3:    */ import java.util.ArrayList;
/*   4:    */ import java.util.List;
/*   5:    */ import javax.xml.parsers.DocumentBuilder;
/*   6:    */ import javax.xml.parsers.DocumentBuilderFactory;
/*   7:    */ import org.apache.commons.io.IOUtils;
/*   8:    */ import org.w3c.dom.Document;
/*   9:    */ import org.w3c.dom.Element;
/*  10:    */ import org.w3c.dom.Node;
/*  11:    */ import org.w3c.dom.NodeList;
/*  12:    */ 
/*  13:    */ public class BaiduPlaceResponse
/*  14:    */ {
/*  15:    */   private String name;
/*  16:    */   private String telephone;
/*  17:    */   private String address;
/*  18:    */   private String lat;
/*  19:    */   private String lng;
/*  20:    */   
/*  21:    */   public static List<BaiduPlaceResponse> getBaiduPlace(String xml)
/*  22:    */     throws Exception
/*  23:    */   {
/*  24: 25 */     List<BaiduPlaceResponse> resList = new ArrayList();
/*  25: 26 */     DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
/*  26: 27 */     DocumentBuilder builder = factory.newDocumentBuilder();
/*  27: 28 */     Document doc = builder.parse(IOUtils.toInputStream(xml, "utf-8"));
/*  28: 29 */     Element root = doc.getDocumentElement();
/*  29: 30 */     for (Node node = root.getFirstChild(); node != null; node = node.getNextSibling()) {
/*  30: 31 */       if (node.getNodeName().equalsIgnoreCase("results"))
/*  31:    */       {
/*  32: 32 */         NodeList nodeList = node.getChildNodes();
/*  33: 33 */         for (int i = 0; i < nodeList.getLength(); i++)
/*  34:    */         {
/*  35: 34 */           Node rNode = nodeList.item(i);
/*  36: 35 */           if (rNode.getNodeName().equalsIgnoreCase("result"))
/*  37:    */           {
/*  38: 36 */             BaiduPlaceResponse res = new BaiduPlaceResponse();
/*  39: 37 */             NodeList subList = rNode.getChildNodes();
/*  40: 39 */             for (int j = 0; j < subList.getLength(); j++)
/*  41:    */             {
/*  42: 40 */               Node mNode = subList.item(j);
/*  43: 41 */               if (mNode.getNodeName().equalsIgnoreCase("name"))
/*  44:    */               {
/*  45: 42 */                 res.setName(mNode.getTextContent());
/*  46:    */               }
/*  47: 44 */               else if (mNode.getNodeName().equalsIgnoreCase("address"))
/*  48:    */               {
/*  49: 45 */                 res.setAddress(mNode.getTextContent());
/*  50:    */               }
/*  51: 47 */               else if (mNode.getNodeName().equalsIgnoreCase("telephone"))
/*  52:    */               {
/*  53: 48 */                 res.setTelephone(mNode.getTextContent());
/*  54:    */               }
/*  55: 50 */               else if (mNode.getNodeName().equalsIgnoreCase("location"))
/*  56:    */               {
/*  57: 51 */                 NodeList lNodes = mNode.getChildNodes();
/*  58: 52 */                 for (int k = 0; k < lNodes.getLength(); k++)
/*  59:    */                 {
/*  60: 53 */                   Node lnode = lNodes.item(k);
/*  61: 54 */                   if (lnode.getNodeName().equalsIgnoreCase("lat")) {
/*  62: 55 */                     res.setLat(lnode.getTextContent());
/*  63: 57 */                   } else if (lnode.getNodeName().equalsIgnoreCase("lng")) {
/*  64: 58 */                     res.setLng(lnode.getTextContent());
/*  65:    */                   }
/*  66:    */                 }
/*  67:    */               }
/*  68:    */             }
/*  69: 64 */             resList.add(res);
/*  70: 65 */             if (i == 9) {
/*  71:    */               break;
/*  72:    */             }
/*  73:    */           }
/*  74:    */         }
/*  75:    */       }
/*  76:    */     }
/*  77: 72 */     return resList;
/*  78:    */   }
/*  79:    */   
/*  80:    */   public String getName()
/*  81:    */   {
/*  82: 76 */     return this.name;
/*  83:    */   }
/*  84:    */   
/*  85:    */   public void setName(String name)
/*  86:    */   {
/*  87: 79 */     this.name = name;
/*  88:    */   }
/*  89:    */   
/*  90:    */   public String getTelephone()
/*  91:    */   {
/*  92: 83 */     if (this.telephone == null) {
/*  93: 84 */       return "";
/*  94:    */     }
/*  95: 86 */     return this.telephone;
/*  96:    */   }
/*  97:    */   
/*  98:    */   public void setTelephone(String telephone)
/*  99:    */   {
/* 100: 89 */     this.telephone = telephone;
/* 101:    */   }
/* 102:    */   
/* 103:    */   public String getAddress()
/* 104:    */   {
/* 105: 92 */     return this.address;
/* 106:    */   }
/* 107:    */   
/* 108:    */   public void setAddress(String address)
/* 109:    */   {
/* 110: 95 */     this.address = address;
/* 111:    */   }
/* 112:    */   
/* 113:    */   public String getLat()
/* 114:    */   {
/* 115: 99 */     return this.lat;
/* 116:    */   }
/* 117:    */   
/* 118:    */   public void setLat(String lat)
/* 119:    */   {
/* 120:103 */     this.lat = lat;
/* 121:    */   }
/* 122:    */   
/* 123:    */   public String getLng()
/* 124:    */   {
/* 125:107 */     return this.lng;
/* 126:    */   }
/* 127:    */   
/* 128:    */   public void setLng(String lng)
/* 129:    */   {
/* 130:111 */     this.lng = lng;
/* 131:    */   }
/* 132:    */   
/* 133:    */   public String toString()
/* 134:    */   {
/* 135:116 */     return 
/* 136:    */     
/* 137:118 */       "BaiduPlaceResponse [name=" + this.name + ", telephone=" + this.telephone + ", address=" + this.address + ", lat=" + this.lat + ", lng=" + this.lng + "]";
/* 138:    */   }
/* 139:    */   
/* 140:    */   public double getDistance(String location_X, String location_Y)
/* 141:    */   {
/* 142:122 */     double x = Double.valueOf(location_X).doubleValue();
/* 143:123 */     double y = Double.valueOf(location_Y).doubleValue();
/* 144:124 */     return GeoUtil.DistanceOfTwoPoints(Double.valueOf(this.lng).doubleValue(), Double.valueOf(this.lat).doubleValue(), y, x, GeoUtil.GaussSphere.Beijing54);
/* 145:    */   }
/* 146:    */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.plugin.temp.BaiduPlaceResponse
 * JD-Core Version:    0.7.0.1
 */