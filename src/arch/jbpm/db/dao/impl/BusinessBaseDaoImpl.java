/*   1:    */ package arch.jbpm.db.dao.impl;
/*   2:    */ 
/*   3:    */ import arch.jbpm.db.dao.BusinessBaseDao;
/*   4:    */ import arch.jbpm.entity.JbpmBaseEntity;
/*   5:    */ import arch.util.DbcpConnection;
/*   6:    */ import java.sql.Connection;
/*   7:    */ import java.sql.PreparedStatement;
/*   8:    */ import java.sql.ResultSet;
/*   9:    */ import java.sql.SQLException;
/*  10:    */ 
/*  11:    */ public class BusinessBaseDaoImpl
/*  12:    */   implements BusinessBaseDao
/*  13:    */ {
/*  14:    */   public void addProcessInfo(JbpmBaseEntity business, Connection conn)
/*  15:    */     throws SQLException
/*  16:    */   {
/*  17: 23 */     PreparedStatement ps = null;
/*  18:    */     try
/*  19:    */     {
/*  20: 25 */       StringBuilder sql = new StringBuilder();
/*  21: 26 */       sql.append("INSERT INTO ARCH_JBPM_FORM( PROINSTANCE_ID,OPEN_ID, PROCESS_KEY, APPLY_TIME, TITLE, STATUS, WECHAT_ID,NEXT_STEP) VALUES(?,?,?,DATE_FORMAT(NOW(),'%Y-%m-%d %H:%i:%S'),?,'0',?,?)");
/*  22: 27 */       ps = conn.prepareStatement(sql.toString());
/*  23: 28 */       ps.setString(1, business.getProcessInstanceId());
/*  24: 29 */       ps.setString(2, business.getOpenId());
/*  25: 30 */       ps.setString(3, business.getBssId());
/*  26: 31 */       ps.setString(4, business.getTitle());
/*  27: 32 */       ps.setString(5, business.getWechatId());
/*  28: 33 */       ps.setString(6, business.getNextStep());
/*  29: 34 */       ps.executeUpdate();
/*  30:    */     }
/*  31:    */     finally
/*  32:    */     {
/*  33: 36 */       DbcpConnection.close(null, ps, null);
/*  34:    */     }
/*  35:    */   }
/*  36:    */   
/*  37:    */   public void addProcessStep(JbpmBaseEntity business, Connection conn)
/*  38:    */     throws SQLException
/*  39:    */   {
/*  40: 46 */     PreparedStatement ps = null;
/*  41:    */     try
/*  42:    */     {
/*  43: 48 */       StringBuilder sql = new StringBuilder();
/*  44: 49 */       sql.append("INSERT INTO ARCH_JBPM_PROCESS (PROINSTANCE_ID, APPROVE_TIME, APPROVAL, COMMENT, APPROVER_ID)")
/*  45: 50 */         .append("VALUES(?,DATE_FORMAT(NOW(),'%Y-%m-%d %H:%i:%S'),?,?,?,?)");
/*  46: 51 */       ps = conn.prepareStatement(sql.toString());
/*  47: 52 */       ps.setString(1, business.getProcessInstanceId());
/*  48: 53 */       ps.setString(2, business.getApproval());
/*  49: 54 */       ps.setString(3, business.getComment());
/*  50: 55 */       ps.setString(4, business.getApproverId());
/*  51:    */       
/*  52: 57 */       ps.executeUpdate();
/*  53:    */     }
/*  54:    */     finally
/*  55:    */     {
/*  56: 59 */       DbcpConnection.close(null, ps, null);
/*  57:    */     }
/*  58:    */   }
/*  59:    */   
/*  60:    */   public String nextStep(JbpmBaseEntity business, Connection conn)
/*  61:    */     throws SQLException
/*  62:    */   {
/*  63: 72 */     PreparedStatement ps = null;
/*  64: 73 */     ResultSet rs = null;
/*  65: 74 */     String nextStep = "";
/*  66:    */     try
/*  67:    */     {
/*  68: 76 */       StringBuilder sql = new StringBuilder();
/*  69: 77 */       sql.append("SELECT NEXT_STEP FROM  ARCH_JBPM_FORM F WHERE PROINSTANCE_ID=?");
/*  70: 78 */       ps = conn.prepareStatement(sql.toString());
/*  71: 79 */       ps.setString(1, business.getProcessInstanceId());
/*  72: 80 */       rs = ps.executeQuery();
/*  73: 81 */       while (rs.next()) {
/*  74: 82 */         nextStep = rs.getString(1);
/*  75:    */       }
/*  76:    */     }
/*  77:    */     finally
/*  78:    */     {
/*  79: 86 */       DbcpConnection.close(null, ps, null);
/*  80:    */     }
/*  81: 88 */     return nextStep;
/*  82:    */   }
/*  83:    */   
/*  84:    */   public String sendQueue(String busId, Connection conn)
/*  85:    */     throws SQLException
/*  86:    */   {
/*  87: 97 */     PreparedStatement ps = null;
/*  88: 98 */     ResultSet rs = null;
/*  89: 99 */     String sendQueue = "";
/*  90:    */     try
/*  91:    */     {
/*  92:101 */       StringBuilder sql = new StringBuilder();
/*  93:102 */       sql.append("SELECT SEND_QUEUE FROM ARCH_BUSINESS_PROP WHERE BSS_ID=?");
/*  94:103 */       ps = conn.prepareStatement(sql.toString());
/*  95:104 */       ps.setString(1, busId);
/*  96:105 */       rs = ps.executeQuery();
/*  97:106 */       while (rs.next()) {
/*  98:107 */         sendQueue = rs.getString(1);
/*  99:    */       }
/* 100:    */     }
/* 101:    */     finally
/* 102:    */     {
/* 103:111 */       DbcpConnection.close(null, ps, null);
/* 104:    */     }
/* 105:113 */     return sendQueue;
/* 106:    */   }
/* 107:    */   
/* 108:    */   public void updProcessStatus(JbpmBaseEntity business, Connection conn)
/* 109:    */     throws SQLException
/* 110:    */   {
/* 111:122 */     PreparedStatement ps = null;
/* 112:    */     try
/* 113:    */     {
/* 114:124 */       StringBuilder sql = new StringBuilder();
/* 115:125 */       sql.append("UPDATE ARCH_JBPM_FORM SET STATUS=? , NEXT_STEP=?  WHERE PROINSTANCE_ID=?");
/* 116:126 */       ps = conn.prepareStatement(sql.toString());
/* 117:127 */       ps.setString(1, business.getStatus());
/* 118:128 */       ps.setString(2, business.getNextStep());
/* 119:129 */       ps.setString(3, business.getProcessInstanceId());
/* 120:130 */       ps.executeUpdate();
/* 121:    */     }
/* 122:    */     finally
/* 123:    */     {
/* 124:132 */       DbcpConnection.close(null, ps, null);
/* 125:    */     }
/* 126:    */   }
/* 127:    */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.jbpm.db.dao.impl.BusinessBaseDaoImpl
 * JD-Core Version:    0.7.0.1
 */