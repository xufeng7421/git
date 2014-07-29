package arch.wechat.DB.dao;

import arch.entity.WxMessage;
import java.sql.Connection;
import java.sql.SQLException;

public abstract interface WxDao
{
  public abstract String findCompanyIdByWeChatId(Connection paramConnection, String paramString)
    throws SQLException;
  
  public abstract int updCommuication(WxMessage paramWxMessage, Connection paramConnection)
    throws SQLException;
  
  public abstract int addWxMessage(WxMessage paramWxMessage, Connection paramConnection)
    throws SQLException;
  
  public abstract int updLocation(WxMessage paramWxMessage, Connection paramConnection)
    throws SQLException;
  
  public abstract String findVipIdByOpenId(Connection paramConnection, String paramString1, String paramString2)
    throws SQLException;
}


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.wechat.DB.dao.WxDao
 * JD-Core Version:    0.7.0.1
 */