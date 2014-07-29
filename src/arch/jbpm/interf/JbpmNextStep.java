package arch.jbpm.interf;

import arch.entity.WxMessage;

public abstract interface JbpmNextStep
{
  public abstract void processDo(WxMessage paramWxMessage, String paramString1, String paramString2, String paramString3);
}


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.jbpm.interf.JbpmNextStep
 * JD-Core Version:    0.7.0.1
 */