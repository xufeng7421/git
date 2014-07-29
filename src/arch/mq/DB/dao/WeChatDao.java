package arch.mq.DB.dao;

import arch.entity.Item;
import arch.entity.PluginEntity;
import arch.entity.ReplyMsg;
import arch.entity.Vip;
import arch.entity.WxMessage;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public abstract interface WeChatDao
{
  public abstract String findVipIdByOpenId(Connection paramConnection, String paramString1, String paramString2)
    throws SQLException;
  
  public abstract int updVipSubscribe(Connection paramConnection, String paramString1, String paramString2, int paramInt)
    throws SQLException;
  
  public abstract String addVip(Connection paramConnection, Vip paramVip)
    throws SQLException;
  
  public abstract ReplyMsg findSubScribeReply(Connection paramConnection, String paramString1, String paramString2)
    throws SQLException;
  
  public abstract ReplyMsg findMenuReply(Connection paramConnection, String paramString1, String paramString2, String paramString3)
    throws SQLException;
  
  public abstract Map<String, String> findMenuEntityMap(Connection paramConnection, String paramString1, String paramString2)
    throws SQLException;
  
  public abstract PluginEntity findPluginMenu(Connection paramConnection, String paramString1, String paramString2)
    throws SQLException;
  
  public abstract Item findActivityById(Connection paramConnection, String paramString, ReplyMsg paramReplyMsg)
    throws SQLException;
  
  public abstract Map<String, String> findArticleAction(Connection paramConnection, String paramString)
    throws SQLException;
  
  public abstract List<Item> findArticleById(Connection paramConnection, String paramString, ReplyMsg paramReplyMsg)
    throws SQLException;
  
  public abstract ReplyMsg findPerViewReply(Connection paramConnection, String paramString1, String paramString2)
    throws SQLException;
  
  public abstract int addSceneLeadin(Connection paramConnection, String paramString, WxMessage paramWxMessage)
    throws SQLException;
  
  public abstract int addVipLeadin(Connection paramConnection, String paramString, WxMessage paramWxMessage)
    throws SQLException;
  
  public abstract int updVipSubscribe(Connection paramConnection, Vip paramVip)
    throws SQLException;
  
  public abstract ReplyMsg findAutoReply(Connection paramConnection, String paramString1, String paramString2, String paramString3)
    throws SQLException;
  
  public abstract int addCommunication(Connection paramConnection, Vip paramVip)
    throws SQLException;
  
  public abstract PluginEntity findPluginReply(Connection paramConnection, String paramString1, String paramString2)
    throws SQLException;
  
  public abstract Map<String, String> findReplyEntityMap(Connection paramConnection, String paramString1, String paramString2)
    throws SQLException;
}


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.mq.DB.dao.WeChatDao
 * JD-Core Version:    0.7.0.1
 */