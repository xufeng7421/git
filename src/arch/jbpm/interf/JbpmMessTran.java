package arch.jbpm.interf;

import arch.entity.WxMessage;
import org.jbpm.api.ProcessInstance;

public abstract interface JbpmMessTran
{
  public abstract ProcessInstance startProcessInstance(String paramString1, String paramString2);
  
  public abstract void processFlow(String paramString1, String paramString2, WxMessage paramWxMessage, ProcessInstance paramProcessInstance);
  
  public abstract void processNext(String paramString1, String paramString2, ProcessInstance paramProcessInstance);
  
  public abstract Object receiveMess(String paramString1, String paramString2);
  
  public abstract void sendMess(String paramString1, Object paramObject, String paramString2);
}


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.jbpm.interf.JbpmMessTran
 * JD-Core Version:    0.7.0.1
 */