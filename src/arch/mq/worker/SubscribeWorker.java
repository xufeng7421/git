/*  1:   */package arch.mq.worker;

/*  2:   */
/*  3:   */import java.sql.Connection;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import arch.entity.ReplyEntity;
import arch.entity.ReplyMsg;
import arch.entity.Vip;
import arch.entity.WxMessage;
import arch.mq.DB.service.WeChatService;
import arch.mq.service.MqService;
import arch.properties.QueueProperties;
import arch.properties.UtilProperties;
import arch.util.AccessTokenUtil;
import arch.util.DbcpConnection;
import arch.wechat.api.WeChatAPI;
import arch.workerBase.WorkerBase;

/* 19:   */
/* 20:   */public class SubscribeWorker
/* 21: */extends WorkerBase<WxMessage>
/* 22: */implements Runnable
/* 23: */{
	/* 24:27 */private static Logger log = Logger.getLogger(SubscribeWorker.class);
	/* 25:28 */private WeChatService chatService = new WeChatService();

	/* 26: */
	/* 27: */public SubscribeWorker(WxMessage wxMessage)
	/* 28: */{
		/* 29:30 */super(wxMessage);
		/* 30: */}

	/* 31: */
	/* 32: */public void run()
	/* 33: */{
		/* 34:35 */Connection conn = null;
		/* 35: */try
		/* 36: */{
			/* 37:37 */conn = DbcpConnection.getConnection();
			/* 38:38 */Vip vip = new Vip(WeChatAPI.getVips(
					AccessTokenUtil.getAccessToken(((WxMessage) this.wxMessage).getWechatId()),
					((WxMessage) this.wxMessage).getOpenId()));
			/* 39:39 */vip.setCompanyId(((WxMessage) this.wxMessage).getCompanyId());
			/* 40:40 */vip.setWechatId(((WxMessage) this.wxMessage).getWechatId());
			/* 41:42 */if (this.chatService.updVipSubscribe(conn, vip) == 0)
			/* 42: */{
				/* 43:44 */String vipId = this.chatService.addVip(conn, vip);
				/* 44:45 */if (vipId != null) {
					/* 45:46 */System.out.println("vipId->" + vipId);
					/* 46: */}
				/* 47: */}
			/* 48:50 */if (!StringUtils.isBlank(((WxMessage) this.wxMessage).getEventKey()))
			/* 49: */{
				/* 50:51 */String key = ((WxMessage) this.wxMessage).getEventKey().substring(8);
				/* 51:53 */if (Integer.parseInt(key) > UtilProperties.getSceneCode().intValue())
				/* 52: */{
					/* 53:54 */key = String.valueOf(Integer.parseInt(key) - UtilProperties.getSceneCode().intValue());
					/* 54:55 */this.chatService.addVipLeadin(conn, key, (WxMessage) this.wxMessage);
					/* 55: */}
				/* 56: */else
				/* 57: */{
					/* 58:58 */this.chatService.addSceneLeadin(conn, key, (WxMessage) this.wxMessage);
				}
			}
			ReplyMsg replyMsg = this.chatService.findSubScribeReply(conn, ((WxMessage) this.wxMessage).getWechatId(),
					((WxMessage) this.wxMessage).getCompanyId());
			/* 62:62 */if (replyMsg != null)
			/* 63: */{
				/* 64:63 */ReplyEntity reply = this.chatService.getReplyEntity(conn, (WxMessage) this.wxMessage,
						replyMsg);
				/* 65:64 */MqService.getInstance().sendMessage(reply, QueueProperties.getSendQueue());
				/* 66: */}
			/* 67:66 */conn.commit();
			/* 68: */}
		/* 69: */catch (Exception e)
		/* 70: */{
			/* 71:68 */log.fatal("关注事件处理异常", e);
			/* 72: */}
		/* 73: */finally
		/* 74: */{
			/* 75:70 */DbcpConnection.close(conn, null, null);
			/* 76: */}
		/* 77: */}
	/* 78: */
}

/*
 * Location: C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * 
 * Qualified Name: arch.mq.worker.SubscribeWorker
 * 
 * JD-Core Version: 0.7.0.1
 */