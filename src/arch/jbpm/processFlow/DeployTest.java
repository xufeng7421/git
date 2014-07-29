/*  1:   */ package arch.jbpm.processFlow;
/*  2:   */ 
/*  3:   */ import java.io.IOException;
/*  4:   */ import org.apache.log4j.Logger;
/*  5:   */ import org.jbpm.api.Configuration;
/*  6:   */ import org.jbpm.api.ExecutionService;
/*  7:   */ import org.jbpm.api.NewDeployment;
/*  8:   */ import org.jbpm.api.ProcessEngine;
/*  9:   */ import org.jbpm.api.ProcessInstance;
/* 10:   */ import org.jbpm.api.RepositoryService;
/* 11:   */ import org.junit.Test;
/* 12:   */ 
/* 13:   */ public class DeployTest
/* 14:   */ {
/* 15:14 */   private ProcessEngine processEngine = Configuration.getProcessEngine();
/* 16:15 */   private static Logger log = Logger.getLogger(DeployTest.class);
/* 17:   */   
/* 18:   */   @Test
/* 19:   */   public void deploy()
/* 20:   */   {
/* 21:22 */     String deploymentId = this.processEngine.getRepositoryService()
/* 22:23 */       .createDeployment()
/* 23:24 */       .addResourceFromClasspath("serviceFlow/serviceFlow.jpdl.xml")
/* 24:25 */       .addResourceFromClasspath("serviceFlow/serviceFlow.png")
/* 25:26 */       .deploy();
/* 26:   */     
/* 27:28 */     log.debug("deploymentId = " + deploymentId);
/* 28:   */   }
/* 29:   */   
/* 30:   */   @Test
/* 31:   */   public void startProcessInstance()
/* 32:   */     throws IOException
/* 33:   */   {
/* 34:40 */     ProcessInstance pi = this.processEngine.getExecutionService().startProcessInstanceByKey("server");
/* 35:41 */     log.debug("ProcessInstance: id=" + pi.getId() + ", processDefinitionId=" + pi.getProcessDefinitionId());
/* 36:   */   }
/* 37:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.jbpm.processFlow.DeployTest
 * JD-Core Version:    0.7.0.1
 */