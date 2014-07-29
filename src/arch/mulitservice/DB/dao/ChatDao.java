package arch.mulitservice.DB.dao;

import arch.entity.WxMessage;
import arch.mulitservice.entity.ChatMessage;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract interface ChatDao
{
  public abstract int addMessage(Connection paramConnection, WxMessage paramWxMessage, String paramString)
    throws SQLException;
  
  public abstract List<ChatMessage> getNewMessage(Connection paramConnection, String paramString1, String paramString2)
    throws SQLException;
  
  public abstract int chageMessState(Connection paramConnection, String paramString)
    throws SQLException;
  
  public abstract String findNickName(String paramString, Connection paramConnection)
    throws SQLException;
}


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.mulitservice.DB.dao.ChatDao
 * JD-Core Version:    0.7.0.1
 */