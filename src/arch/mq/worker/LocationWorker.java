/*  1:   */ package arch.mq.worker;
/*  2:   */ 
/*  3:   */ import arch.entity.Item;
/*  4:   */ import arch.entity.ReplyEntity;
/*  5:   */ import arch.entity.WxMessage;
/*  6:   */ import arch.entity.type.ReplyType;
/*  7:   */ import arch.mq.service.MqService;
/*  8:   */ import arch.plugin.temp.PlaceUtil;
/*  9:   */ import arch.properties.QueueProperties;
/* 10:   */ import arch.util.AccessTokenUtil;
/* 11:   */ import arch.util.MsgUtil;
/* 12:   */ import arch.workerBase.WorkerBase;
/* 13:   */ import java.util.List;
/* 14:   */ import org.apache.log4j.Logger;
/* 15:   */ 
/* 16:   */ public class LocationWorker
/* 17:   */   extends WorkerBase<WxMessage>
/* 18:   */   implements Runnable
/* 19:   */ {
/* 20:25 */   private static Logger log = Logger.getLogger(LocationWorker.class);
/* 21:   */   
/* 22:   */   public LocationWorker(WxMessage wxMessage)
/* 23:   */   {
/* 24:28 */     super(wxMessage);
/* 25:   */   }
/* 26:   */   
/* 27:   */   public void run()
/* 28:   */   {
/* 29:33 */     log.debug("处理位置事件");
/* 30:   */     try
/* 31:   */     {
/* 32:36 */       ReplyEntity reply = new ReplyEntity((WxMessage)this.wxMessage);
/* 33:37 */       List<Item> list = PlaceUtil.getItems(((WxMessage)this.wxMessage).getLocation_X(), ((WxMessage)this.wxMessage).getLocation_Y(), "兴化农商行");
/* 34:38 */       reply.setContent(MsgUtil.parseNewsMsg(((WxMessage)this.wxMessage).getOpenId(), list));
/* 35:39 */       reply.setMsgType(ReplyType.IMAGETEXT);
/* 36:40 */       reply.setAccessToken(AccessTokenUtil.getAccessToken(((WxMessage)this.wxMessage).getWechatId()));
/* 37:41 */       MqService.getInstance().sendMessage(reply, QueueProperties.getSendQueue());
/* 38:   */     }
/* 39:   */     catch (Exception e)
/* 40:   */     {
/* 41:43 */       log.fatal("发送回复消息异常", e);
/* 42:   */     }
/* 43:   */   }
/* 44:   */ }


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.mq.worker.LocationWorker
 * JD-Core Version:    0.7.0.1
 */