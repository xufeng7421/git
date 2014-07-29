/*   1:    */ package arch.jbpm.processDefinition;
/*   2:    */ 
/*   3:    */ import java.io.FileOutputStream;
/*   4:    */ import java.io.InputStream;
/*   5:    */ import java.util.HashMap;
/*   6:    */ import java.util.List;
/*   7:    */ import java.util.Map;
/*   8:    */ import java.util.Set;
/*   9:    */ import java.util.zip.ZipInputStream;
/*  10:    */ import org.apache.log4j.Logger;
/*  11:    */ import org.jbpm.api.Configuration;
/*  12:    */ import org.jbpm.api.NewDeployment;
/*  13:    */ import org.jbpm.api.ProcessDefinition;
/*  14:    */ import org.jbpm.api.ProcessDefinitionQuery;
/*  15:    */ import org.jbpm.api.ProcessEngine;
/*  16:    */ import org.jbpm.api.RepositoryService;
/*  17:    */ import org.junit.Test;
/*  18:    */ 
/*  19:    */ public class ProcessDefinitionTest
/*  20:    */ {
/*  21: 33 */   ProcessEngine processEngine = Configuration.getProcessEngine();
/*  22: 34 */   private static Logger log = Logger.getLogger(ProcessDefinitionTest.class);
/*  23:    */   
/*  24:    */   @Test
/*  25:    */   public void deploy_1()
/*  26:    */   {
/*  27: 43 */     String deploymentId = this.processEngine.getRepositoryService()
/*  28: 44 */       .createDeployment()
/*  29: 45 */       .addResourceFromClasspath("businessTest/test.jpdl.xml")
/*  30: 46 */       .addResourceFromClasspath("businessTest/test.png")
/*  31: 47 */       .deploy();
/*  32:    */     
/*  33: 49 */     log.debug("deploymentId = " + deploymentId);
/*  34:    */   }
/*  35:    */   
/*  36:    */   @Test
/*  37:    */   public void deploy_zip()
/*  38:    */   {
/*  39: 55 */     InputStream in = getClass().getClassLoader().getResourceAsStream("helloworld/helloworld.zip");
/*  40: 56 */     ZipInputStream zipInputStream = new ZipInputStream(in);
/*  41:    */     
/*  42:    */ 
/*  43: 59 */     String deploymentId = this.processEngine.getRepositoryService()
/*  44: 60 */       .createDeployment()
/*  45: 61 */       .addResourcesFromZipInputStream(zipInputStream)
/*  46: 62 */       .deploy();
/*  47:    */     
/*  48: 64 */     log.debug("deploymentId = " + deploymentId);
/*  49:    */   }
/*  50:    */   
/*  51:    */   @Test
/*  52:    */   public void findAll()
/*  53:    */   {
/*  54: 71 */     List<ProcessDefinition> list = this.processEngine.getRepositoryService()
/*  55: 72 */       .createProcessDefinitionQuery()
/*  56: 73 */       .list();
/*  57: 76 */     for (ProcessDefinition pd : list) {
/*  58: 77 */       log.debug("id=" + pd.getId() + 
/*  59: 78 */         ", name=" + pd.getName() + 
/*  60: 79 */         ", key=" + pd.getKey() + 
/*  61: 80 */         ", version=" + pd.getVersion() + 
/*  62: 81 */         ", deploymentId=" + pd.getDeploymentId());
/*  63:    */     }
/*  64:    */   }
/*  65:    */   
/*  66:    */   @Test
/*  67:    */   public void findAllLatestVersions()
/*  68:    */   {
/*  69: 89 */     List<ProcessDefinition> all = this.processEngine.getRepositoryService()
/*  70: 90 */       .createProcessDefinitionQuery()
/*  71:    */       
/*  72:    */ 
/*  73:    */ 
/*  74: 94 */       .orderAsc("versionProperty.longValue")
/*  75:    */       
/*  76:    */ 
/*  77:    */ 
/*  78:    */ 
/*  79: 99 */       .list();
/*  80:    */     
/*  81:    */ 
/*  82:102 */     Map<String, ProcessDefinition> map = new HashMap();
/*  83:103 */     for (ProcessDefinition pd : all) {
/*  84:104 */       map.put(pd.getKey(), pd);
/*  85:    */     }
/*  86:108 */     for (ProcessDefinition pd : map.values()) {
/*  87:109 */       log.debug("id=" + pd.getId() + 
/*  88:110 */         ", name=" + pd.getName() + 
/*  89:111 */         ", key=" + pd.getKey() + 
/*  90:112 */         ", version=" + pd.getVersion() + 
/*  91:113 */         ", deploymentId=" + pd.getDeploymentId());
/*  92:    */     }
/*  93:    */   }
/*  94:    */   
/*  95:    */   @Test
/*  96:    */   public void deleteById()
/*  97:    */   {
/*  98:120 */     String deploymentId = "1";
/*  99:    */     
/* 100:    */ 
/* 101:    */ 
/* 102:    */ 
/* 103:    */ 
/* 104:126 */     this.processEngine.getRepositoryService().deleteDeploymentCascade(deploymentId);
/* 105:    */   }
/* 106:    */   
/* 107:    */   @Test
/* 108:    */   public void deleteByKey()
/* 109:    */   {
/* 110:133 */     List<ProcessDefinition> list = this.processEngine.getRepositoryService()
/* 111:134 */       .createProcessDefinitionQuery()
/* 112:135 */       .processDefinitionKey("test")
/* 113:136 */       .list();
/* 114:139 */     for (ProcessDefinition pd : list) {
/* 115:140 */       this.processEngine.getRepositoryService().deleteDeploymentCascade(pd.getDeploymentId());
/* 116:    */     }
/* 117:    */   }
/* 118:    */   
/* 119:    */   @Test
/* 120:    */   public void showProcessImage()
/* 121:    */     throws Exception
/* 122:    */   {
/* 123:147 */     String deploymentId = "40001";
/* 124:148 */     String resourceName = "helloworld/helloworld.png";
/* 125:    */     
/* 126:    */ 
/* 127:151 */     Set<String> names = this.processEngine.getRepositoryService().getResourceNames(deploymentId);
/* 128:152 */     for (String name : names) {
/* 129:153 */       log.debug(name);
/* 130:    */     }
/* 131:157 */     InputStream in = this.processEngine.getRepositoryService().getResourceAsStream(deploymentId, resourceName);
/* 132:    */     
/* 133:159 */     FileOutputStream out = new FileOutputStream("c:/process.png");
/* 134:160 */     for (int b = -1; (b = in.read()) != -1;) {
/* 135:161 */       out.write(b);
/* 136:    */     }
/* 137:163 */     out.close();
/* 138:    */   }
/* 139:    */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.jbpm.processDefinition.ProcessDefinitionTest
 * JD-Core Version:    0.7.0.1
 */