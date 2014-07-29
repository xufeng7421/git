package arch.mq.interfaces;

import arch.entity.BusinessConfig;
import arch.entity.ReplyMsg;
import arch.entity.WxMessage;
import arch.entity.type.QueueType;

public abstract interface Dispatcher
{
  public abstract void doBusiness(WxMessage paramWxMessage, BusinessConfig paramBusinessConfig);
  
  public abstract void doAutoReply(WxMessage paramWxMessage, ReplyMsg paramReplyMsg);
  
  public abstract void toService(WxMessage paramWxMessage);
  
  public abstract BusinessConfig isBusiness(WxMessage paramWxMessage, QueueType paramQueueType);
  
  public abstract ReplyMsg isAutoReply(WxMessage paramWxMessage);
}


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.mq.interfaces.Dispatcher
 * JD-Core Version:    0.7.0.1
 */