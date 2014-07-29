/*   1:    */ package arch.mq.DB.dao.impl;
/*   2:    */ 
/*   3:    */ import arch.entity.AutoReply;
/*   4:    */ import arch.entity.BusinessConfig;
/*   5:    */ import arch.entity.PluginCase;
/*   6:    */ import arch.entity.ProcessEntity;
/*   7:    */ import arch.entity.type.QueueType;
/*   8:    */ import arch.mq.DB.dao.QueueDao;
/*   9:    */ import arch.util.DbcpConnection;
/*  10:    */ import java.sql.Connection;
/*  11:    */ import java.sql.PreparedStatement;
/*  12:    */ import java.sql.ResultSet;
/*  13:    */ import java.sql.SQLException;
/*  14:    */ import java.util.ArrayList;
/*  15:    */ import java.util.List;
/*  16:    */ 
/*  17:    */ public class QueueDaoImpl
/*  18:    */   implements QueueDao
/*  19:    */ {
/*  20:    */   public BusinessConfig findBusiness(Connection conn, String trigger, String wechatId, QueueType queueType)
/*  21:    */     throws SQLException
/*  22:    */   {
/*  23: 25 */     BusinessConfig business = null;
/*  24: 26 */     String sql = "SELECT BSS_ID,RECEI_QUEUE,SEND_QUEUE FROM ARCH_BUSINESS_PROP WHERE MESS_TYPE=? AND MESS_CONTENT=? AND WECHAT_ID = ? AND (`STATUS`='0' OR (IS_NEW ='0' AND `STATUS`='1'))";
/*  25: 27 */     PreparedStatement ps = null;
/*  26: 28 */     ResultSet rs = null;
/*  27:    */     try
/*  28:    */     {
/*  29: 30 */       ps = conn.prepareStatement(sql);
/*  30: 31 */       ps.setString(1, queueType.toString());
/*  31: 32 */       ps.setString(2, trigger);
/*  32: 33 */       ps.setString(3, wechatId);
/*  33: 34 */       rs = ps.executeQuery();
/*  34: 35 */       if (rs.next())
/*  35:    */       {
/*  36: 36 */         business = new BusinessConfig();
/*  37: 37 */         business.setBusinessId(rs.getString("BSS_ID"));
/*  38: 38 */         business.setReciveQueue(rs.getString("RECEI_QUEUE"));
/*  39: 39 */         business.setSendQueue(rs.getString("SEND_QUEUE"));
/*  40:    */       }
/*  41:    */     }
/*  42:    */     finally
/*  43:    */     {
/*  44: 42 */       DbcpConnection.close(null, ps, rs);
/*  45:    */     }
/*  46: 44 */     return business;
/*  47:    */   }
/*  48:    */   
/*  49:    */   public AutoReply findAutoreplyByKeword(String wechatId, String keyword, Connection conn)
/*  50:    */     throws SQLException
/*  51:    */   {
/*  52: 50 */     AutoReply autoReply = null;
/*  53: 51 */     PreparedStatement ps = null;
/*  54: 52 */     ResultSet rs = null;
/*  55:    */     try
/*  56:    */     {
/*  57: 54 */       String sql = "SELECT TYPE,CONTENT FROM S_REPLY WHERE WECHAT_ID=? AND KEYWORDS=? ";
/*  58: 55 */       ps = conn.prepareStatement(sql);
/*  59: 56 */       ps.setString(1, wechatId);
/*  60: 57 */       ps.setString(2, keyword);
/*  61: 58 */       rs = ps.executeQuery();
/*  62: 59 */       if (rs.next())
/*  63:    */       {
/*  64: 60 */         autoReply = new AutoReply();
/*  65: 61 */         autoReply.setType(rs.getString("TYPE"));
/*  66: 62 */         autoReply.setContent(rs.getString("CONTENT"));
/*  67:    */       }
/*  68:    */     }
/*  69:    */     finally
/*  70:    */     {
/*  71: 65 */       DbcpConnection.close(null, ps, rs);
/*  72:    */     }
/*  73: 67 */     return autoReply;
/*  74:    */   }
/*  75:    */   
/*  76:    */   public BusinessConfig findBusinessConfig(Connection conn, String businessKey)
/*  77:    */     throws SQLException
/*  78:    */   {
/*  79: 73 */     BusinessConfig config = null;
/*  80: 74 */     PreparedStatement ps = null;
/*  81: 75 */     ResultSet rs = null;
/*  82:    */     try
/*  83:    */     {
/*  84: 77 */       String sql = "SELECT RECEI_QUEUE,SEND_QUEUE FROM ARCH_BUSINESS_PROP WHERE BSS_ID=? ";
/*  85: 78 */       ps = conn.prepareStatement(sql);
/*  86: 79 */       ps.setString(1, businessKey);
/*  87: 80 */       rs = ps.executeQuery();
/*  88: 81 */       if (rs.next())
/*  89:    */       {
/*  90: 82 */         config = new BusinessConfig();
/*  91: 83 */         config.setReciveQueue(rs.getString("RECEI_QUEUE"));
/*  92: 84 */         config.setSendQueue(rs.getString("SEND_QUEUE"));
/*  93:    */       }
/*  94:    */     }
/*  95:    */     finally
/*  96:    */     {
/*  97: 87 */       DbcpConnection.close(null, ps, rs);
/*  98:    */     }
/*  99: 89 */     return config;
/* 100:    */   }
/* 101:    */   
/* 102:    */   public ProcessEntity findUnClosedProcessId(Connection conn, String openId)
/* 103:    */     throws SQLException
/* 104:    */   {
/* 105: 95 */     ProcessEntity proc = null;
/* 106: 96 */     String sql = "SELECT PROINSTANCE_ID,P.RECEI_QUEUE FROM ARCH_JBPM_FORM F,ARCH_BUSINESS_PROP P WHERE F.OPEN_ID=? AND F.STATUS!='2' AND F.PROCESS_KEY=P.BSS_ID";
/* 107: 97 */     PreparedStatement ps = null;
/* 108: 98 */     ResultSet rs = null;
/* 109:    */     try
/* 110:    */     {
/* 111:100 */       ps = conn.prepareStatement(sql);
/* 112:101 */       ps.setString(1, openId);
/* 113:102 */       rs = ps.executeQuery();
/* 114:103 */       if (rs.next())
/* 115:    */       {
/* 116:104 */         proc = new ProcessEntity();
/* 117:105 */         proc.setProId(rs.getString("PROINSTANCE_ID"));
/* 118:106 */         proc.setReceiveQueue(rs.getString("RECEI_QUEUE"));
/* 119:    */       }
/* 120:    */     }
/* 121:    */     finally
/* 122:    */     {
/* 123:109 */       DbcpConnection.close(null, ps, rs);
/* 124:    */     }
/* 125:111 */     return proc;
/* 126:    */   }
/* 127:    */   
/* 128:    */   public BusinessConfig findProcessId(Connection conn, String openId, String businessKey)
/* 129:    */     throws SQLException
/* 130:    */   {
/* 131:117 */     BusinessConfig result = null;
/* 132:118 */     String sql = "SELECT PROINSTANCE_ID,P.RECEI_QUEUE,P.SEND_QUEUE FROM ARCH_JBPM_FORM F,ARCH_BUSINESS_PROP P WHERE F.OPEN_ID=? AND F.PROCESS_KEY=? AND F.STATUS!='2' AND F.PROCESS_KEY=P.BSS_ID ";
/* 133:119 */     PreparedStatement ps = null;
/* 134:120 */     ResultSet rs = null;
/* 135:    */     try
/* 136:    */     {
/* 137:122 */       ps = conn.prepareStatement(sql);
/* 138:123 */       ps.setString(1, openId);
/* 139:124 */       ps.setString(2, businessKey);
/* 140:125 */       rs = ps.executeQuery();
/* 141:126 */       if (rs.next())
/* 142:    */       {
/* 143:127 */         result = new BusinessConfig();
/* 144:128 */         result.setPeocessId(rs.getString("PROINSTANCE_ID"));
/* 145:129 */         result.setReciveQueue(rs.getString("RECEI_QUEUE"));
/* 146:130 */         result.setSendQueue(rs.getString("SEND_QUEUE"));
/* 147:    */       }
/* 148:    */     }
/* 149:    */     finally
/* 150:    */     {
/* 151:133 */       DbcpConnection.close(null, ps, rs);
/* 152:    */     }
/* 153:135 */     return result;
/* 154:    */   }
/* 155:    */   
/* 156:    */   public List<PluginCase> findUsingPlugin(Connection conn)
/* 157:    */     throws SQLException
/* 158:    */   {
/* 159:142 */     List<PluginCase> caseList = new ArrayList();
/* 160:143 */     PluginCase temp = null;
/* 161:144 */     String sql = "SELECT BSS_ID,RECEI_QUEUE,BSS_CLASS,OBJ_CLASS,CORE_POOL,MAX_POOL,`STATUS` FROM `ARCH_BUSINESS_PROP` WHERE `STATUS`='1'";
/* 162:145 */     PreparedStatement ps = null;
/* 163:146 */     ResultSet rs = null;
/* 164:    */     try
/* 165:    */     {
/* 166:148 */       ps = conn.prepareStatement(sql);
/* 167:149 */       rs = ps.executeQuery();
/* 168:150 */       while (rs.next())
/* 169:    */       {
/* 170:151 */         temp = new PluginCase();
/* 171:152 */         temp.setBusinessId(rs.getString("BSS_ID"));
/* 172:153 */         temp.setReciveQueue(rs.getString("RECEI_QUEUE"));
/* 173:154 */         temp.setBssClass(rs.getString("BSS_CLASS"));
/* 174:155 */         temp.setObjClass(rs.getString("OBJ_CLASS"));
/* 175:156 */         temp.setCorePool(Integer.valueOf(rs.getInt("CORE_POOL")));
/* 176:157 */         temp.setMaxPool(Integer.valueOf(rs.getInt("MAX_POOL")));
/* 177:158 */         temp.setStatus(rs.getString("STATUS"));
/* 178:159 */         caseList.add(temp);
/* 179:    */       }
/* 180:    */     }
/* 181:    */     finally
/* 182:    */     {
/* 183:162 */       DbcpConnection.close(null, ps, rs);
/* 184:    */     }
/* 185:164 */     return caseList;
/* 186:    */   }
/* 187:    */   
/* 188:    */   public List<PluginCase> findNewPlugin(Connection conn)
/* 189:    */     throws SQLException
/* 190:    */   {
/* 191:170 */     List<PluginCase> caseList = new ArrayList();
/* 192:171 */     PluginCase temp = null;
/* 193:172 */     String sql = "SELECT BSS_ID,RECEI_QUEUE,BSS_CLASS,OBJ_CLASS,CORE_POOL,MAX_POOL,`STATUS` FROM `ARCH_BUSINESS_PROP` WHERE IS_NEW = '0'";
/* 194:173 */     PreparedStatement ps = null;
/* 195:174 */     ResultSet rs = null;
/* 196:    */     try
/* 197:    */     {
/* 198:176 */       ps = conn.prepareStatement(sql);
/* 199:177 */       rs = ps.executeQuery();
/* 200:178 */       while (rs.next())
/* 201:    */       {
/* 202:179 */         temp = new PluginCase();
/* 203:180 */         temp.setBusinessId(rs.getString("BSS_ID"));
/* 204:181 */         temp.setReciveQueue(rs.getString("RECEI_QUEUE"));
/* 205:182 */         temp.setBssClass(rs.getString("BSS_CLASS"));
/* 206:183 */         temp.setObjClass(rs.getString("OBJ_CLASS"));
/* 207:184 */         temp.setCorePool(Integer.valueOf(rs.getInt("CORE_POOL")));
/* 208:185 */         temp.setMaxPool(Integer.valueOf(rs.getInt("MAX_POOL")));
/* 209:186 */         temp.setStatus(rs.getString("STATUS"));
/* 210:187 */         caseList.add(temp);
/* 211:    */       }
/* 212:    */     }
/* 213:    */     finally
/* 214:    */     {
/* 215:190 */       DbcpConnection.close(null, ps, rs);
/* 216:    */     }
/* 217:192 */     return caseList;
/* 218:    */   }
/* 219:    */   
/* 220:    */   public boolean updPlugin(Connection conn, String businessKey)
/* 221:    */     throws SQLException
/* 222:    */   {
/* 223:198 */     boolean flag = false;
/* 224:199 */     String sql = "UPDATE ARCH_BUSINESS_PROP SET IS_NEW='1' WHERE BSS_ID = ?";
/* 225:200 */     PreparedStatement ps = null;
/* 226:    */     try
/* 227:    */     {
/* 228:202 */       ps = conn.prepareStatement(sql);
/* 229:203 */       ps.setString(1, businessKey);
/* 230:204 */       int count = ps.executeUpdate();
/* 231:205 */       if (count != 0) {
/* 232:206 */         flag = true;
/* 233:    */       }
/* 234:    */     }
/* 235:    */     finally
/* 236:    */     {
/* 237:209 */       DbcpConnection.close(null, ps, null);
/* 238:    */     }
/* 239:211 */     return flag;
/* 240:    */   }
/* 241:    */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.mq.DB.dao.impl.QueueDaoImpl
 * JD-Core Version:    0.7.0.1
 */