/*   1:    */ package arch.jbpm.utils;
/*   2:    */ 
/*   3:    */ import java.net.MalformedURLException;
/*   4:    */ import java.net.URL;
/*   5:    */ import java.util.List;
/*   6:    */ import java.util.Map;
/*   7:    */ import java.util.zip.ZipInputStream;
/*   8:    */ import org.apache.log4j.Logger;
/*   9:    */ import org.jbpm.api.Execution;
/*  10:    */ import org.jbpm.api.ExecutionService;
/*  11:    */ import org.jbpm.api.HistoryService;
/*  12:    */ import org.jbpm.api.ManagementService;
/*  13:    */ import org.jbpm.api.NewDeployment;
/*  14:    */ import org.jbpm.api.ProcessDefinition;
/*  15:    */ import org.jbpm.api.ProcessDefinitionQuery;
/*  16:    */ import org.jbpm.api.ProcessEngine;
/*  17:    */ import org.jbpm.api.ProcessInstance;
/*  18:    */ import org.jbpm.api.ProcessInstanceQuery;
/*  19:    */ import org.jbpm.api.RepositoryService;
/*  20:    */ import org.jbpm.api.TaskQuery;
/*  21:    */ import org.jbpm.api.TaskService;
/*  22:    */ import org.jbpm.api.model.OpenExecution;
/*  23:    */ import org.jbpm.api.model.OpenProcessInstance;
/*  24:    */ import org.jbpm.api.task.Task;
/*  25:    */ import org.jbpm.pvm.internal.model.ExecutionImpl;
/*  26:    */ import org.jbpm.pvm.internal.task.OpenTask;
/*  27:    */ import org.jbpm.pvm.internal.task.TaskImpl;
/*  28:    */ 
/*  29:    */ public class JbpmUtil
/*  30:    */ {
/*  31:    */   private ProcessEngine processEngine;
/*  32: 44 */   private RepositoryService repositoryService = null;
/*  33: 45 */   private ExecutionService executionService = null;
/*  34: 46 */   private TaskService taskService = null;
/*  35: 47 */   private HistoryService historyService = null;
/*  36: 48 */   private ManagementService managementService = null;
/*  37: 49 */   private static Logger log = Logger.getLogger(JbpmUtil.class);
/*  38:    */   
/*  39:    */   public JbpmUtil() {}
/*  40:    */   
/*  41:    */   public JbpmUtil(ProcessEngine processEngine)
/*  42:    */   {
/*  43: 65 */     this.processEngine = processEngine;
/*  44: 66 */     this.repositoryService = processEngine.getRepositoryService();
/*  45: 67 */     this.executionService = processEngine.getExecutionService();
/*  46: 68 */     this.taskService = processEngine.getTaskService();
/*  47: 69 */     this.historyService = processEngine.getHistoryService();
/*  48: 70 */     this.managementService = processEngine.getManagementService();
/*  49:    */   }
/*  50:    */   
/*  51:    */   public String newDeployDefinitionByPath(String resourceName)
/*  52:    */   {
/*  53: 82 */     return 
/*  54: 83 */       this.repositoryService.createDeployment().addResourceFromClasspath(resourceName).deploy();
/*  55:    */   }
/*  56:    */   
/*  57:    */   public String newDeployDefinitionByZip(String resourceZipName)
/*  58:    */   {
/*  59: 93 */     ZipInputStream zis = new ZipInputStream(getClass()
/*  60: 94 */       .getResourceAsStream(resourceZipName));
/*  61: 95 */     return this.repositoryService.createDeployment()
/*  62: 96 */       .addResourcesFromZipInputStream(zis).deploy();
/*  63:    */   }
/*  64:    */   
/*  65:    */   public String newDeployDefinitionByUrl(String urlStr)
/*  66:    */     throws MalformedURLException
/*  67:    */   {
/*  68:109 */     URL url = new URL(urlStr);
/*  69:110 */     return this.repositoryService.createDeployment().addResourceFromUrl(url)
/*  70:111 */       .deploy();
/*  71:    */   }
/*  72:    */   
/*  73:    */   public ProcessInstance startProcessInstanceById(String processDefinitionId, Map<String, ?> variables)
/*  74:    */   {
/*  75:123 */     return this.executionService.startProcessInstanceById(processDefinitionId, 
/*  76:124 */       variables);
/*  77:    */   }
/*  78:    */   
/*  79:    */   public ProcessInstance startProcessInstanceByKey(String key, Map<String, ?> variables)
/*  80:    */   {
/*  81:137 */     return this.executionService.startProcessInstanceByKey(key, 
/*  82:138 */       variables);
/*  83:    */   }
/*  84:    */   
/*  85:    */   public void completeTask(String taskId, Map<String, ?> variables)
/*  86:    */   {
/*  87:148 */     if (variables == null)
/*  88:    */     {
/*  89:149 */       this.taskService.completeTask(taskId);
/*  90:150 */       return;
/*  91:    */     }
/*  92:152 */     this.taskService.completeTask(taskId, variables);
/*  93:    */   }
/*  94:    */   
/*  95:    */   public void completeTask(String taskId, String outcome)
/*  96:    */   {
/*  97:162 */     if (outcome == null)
/*  98:    */     {
/*  99:163 */       this.taskService.completeTask(taskId);
/* 100:164 */       return;
/* 101:    */     }
/* 102:166 */     this.taskService.completeTask(taskId, outcome);
/* 103:    */   }
/* 104:    */   
/* 105:    */   public void endProcessInstance(String processInstanceId)
/* 106:    */   {
/* 107:176 */     this.executionService.endProcessInstance(processInstanceId, "ended");
/* 108:    */   }
/* 109:    */   
/* 110:    */   public ProcessInstance signalExecutionById(String processInstanceId)
/* 111:    */   {
/* 112:185 */     ProcessInstance processInstance = this.executionService.signalExecutionById(processInstanceId);
/* 113:186 */     return processInstance;
/* 114:    */   }
/* 115:    */   
/* 116:    */   public ProcessInstance signalExecutionByIdVal(String processInstanceId, Map<String, Object> busMap)
/* 117:    */   {
/* 118:195 */     ProcessInstance processInstance = this.executionService.signalExecutionById(processInstanceId, busMap);
/* 119:196 */     return processInstance;
/* 120:    */   }
/* 121:    */   
/* 122:    */   public List<ProcessDefinition> getAllProcessDefinitionList()
/* 123:    */   {
/* 124:205 */     return this.repositoryService.createProcessDefinitionQuery().list();
/* 125:    */   }
/* 126:    */   
/* 127:    */   public List<ProcessInstance> getAllProcessInstanceList()
/* 128:    */   {
/* 129:214 */     return this.executionService.createProcessInstanceQuery().list();
/* 130:    */   }
/* 131:    */   
/* 132:    */   public Object getVariableByexecutionId(String executionId, String variableName)
/* 133:    */   {
/* 134:226 */     return this.executionService.getVariable(executionId, variableName);
/* 135:    */   }
/* 136:    */   
/* 137:    */   public Object getVariable(String taskId, String variableName)
/* 138:    */   {
/* 139:237 */     return this.taskService.getVariable(taskId, variableName);
/* 140:    */   }
/* 141:    */   
/* 142:    */   public void setVariables(String taskId, Map<String, ?> variables)
/* 143:    */   {
/* 144:247 */     this.taskService.setVariables(taskId, variables);
/* 145:    */   }
/* 146:    */   
/* 147:    */   public List<Task> findPersonalTasks(String userId)
/* 148:    */   {
/* 149:257 */     return this.taskService.findPersonalTasks(userId);
/* 150:    */   }
/* 151:    */   
/* 152:    */   public Task getTaskByTaskId(String taskId)
/* 153:    */   {
/* 154:267 */     return this.taskService.getTask(taskId);
/* 155:    */   }
/* 156:    */   
/* 157:    */   public void createSubTasks(Task task, List<String> users)
/* 158:    */   {
/* 159:279 */     OpenTask oTask = (OpenTask)task;
/* 160:    */     
/* 161:    */ 
/* 162:282 */     Execution execution = this.executionService.findExecutionById(task
/* 163:283 */       .getExecutionId());
/* 164:285 */     for (String userId : users)
/* 165:    */     {
/* 166:286 */       TaskImpl subTask = (TaskImpl)oTask.createSubTask();
/* 167:287 */       subTask.setAssignee(userId);
/* 168:288 */       subTask.setName(task.getName());
/* 169:289 */       subTask.setFormResourceName(task.getFormResourceName());
/* 170:    */       
/* 171:291 */       subTask.setExecution((ExecutionImpl)execution);
/* 172:292 */       this.taskService.addTaskParticipatingUser(task.getId(), userId, 
/* 173:293 */         "client");
/* 174:    */     }
/* 175:    */   }
/* 176:    */   
/* 177:    */   public Task getByOpenExecution(OpenExecution execution)
/* 178:    */   {
/* 179:303 */     String pid = execution.getProcessInstance().getId();
/* 180:304 */     return this.taskService.createTaskQuery().processInstanceId(pid)
/* 181:305 */       .activityName(execution.getName()).uniqueResult();
/* 182:    */   }
/* 183:    */   
/* 184:    */   public String saveTask(Task task)
/* 185:    */   {
/* 186:315 */     return this.taskService.saveTask(task);
/* 187:    */   }
/* 188:    */   
/* 189:    */   public void deleteDeploymentCascade(String deploymentId)
/* 190:    */   {
/* 191:325 */     if (deploymentId == null) {
/* 192:326 */       return;
/* 193:    */     }
/* 194:328 */     this.repositoryService.deleteDeploymentCascade(deploymentId);
/* 195:    */   }
/* 196:    */   
/* 197:    */   public ProcessEngine getProcessEngine()
/* 198:    */   {
/* 199:334 */     return this.processEngine;
/* 200:    */   }
/* 201:    */   
/* 202:    */   public void setProcessEngine(ProcessEngine processEngine)
/* 203:    */   {
/* 204:338 */     this.processEngine = processEngine;
/* 205:339 */     log.debug("processEngine=" + processEngine);
/* 206:340 */     this.repositoryService = processEngine.getRepositoryService();
/* 207:341 */     this.executionService = processEngine.getExecutionService();
/* 208:342 */     this.taskService = processEngine.getTaskService();
/* 209:343 */     this.historyService = processEngine.getHistoryService();
/* 210:344 */     this.managementService = processEngine.getManagementService();
/* 211:    */   }
/* 212:    */   
/* 213:    */   public RepositoryService getRepositoryService()
/* 214:    */   {
/* 215:348 */     return this.repositoryService;
/* 216:    */   }
/* 217:    */   
/* 218:    */   public void setRepositoryService(RepositoryService repositoryService)
/* 219:    */   {
/* 220:352 */     this.repositoryService = repositoryService;
/* 221:    */   }
/* 222:    */   
/* 223:    */   public ExecutionService getExecutionService()
/* 224:    */   {
/* 225:356 */     return this.executionService;
/* 226:    */   }
/* 227:    */   
/* 228:    */   public void setExecutionService(ExecutionService executionService)
/* 229:    */   {
/* 230:360 */     this.executionService = executionService;
/* 231:    */   }
/* 232:    */   
/* 233:    */   public TaskService getTaskService()
/* 234:    */   {
/* 235:364 */     return this.taskService;
/* 236:    */   }
/* 237:    */   
/* 238:    */   public void setTaskService(TaskService taskService)
/* 239:    */   {
/* 240:368 */     this.taskService = taskService;
/* 241:    */   }
/* 242:    */   
/* 243:    */   public HistoryService getHistoryService()
/* 244:    */   {
/* 245:372 */     return this.historyService;
/* 246:    */   }
/* 247:    */   
/* 248:    */   public void setHistoryService(HistoryService historyService)
/* 249:    */   {
/* 250:376 */     this.historyService = historyService;
/* 251:    */   }
/* 252:    */   
/* 253:    */   public ManagementService getManagementService()
/* 254:    */   {
/* 255:380 */     return this.managementService;
/* 256:    */   }
/* 257:    */   
/* 258:    */   public void setManagementService(ManagementService managementService)
/* 259:    */   {
/* 260:384 */     this.managementService = managementService;
/* 261:    */   }
/* 262:    */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.jbpm.utils.JbpmUtil
 * JD-Core Version:    0.7.0.1
 */