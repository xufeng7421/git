package arch.mq.DB.dao;

import arch.entity.AutoReply;
import arch.entity.BusinessConfig;
import arch.entity.PluginCase;
import arch.entity.ProcessEntity;
import arch.entity.type.QueueType;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract interface QueueDao
{
  public abstract BusinessConfig findBusiness(Connection paramConnection, String paramString1, String paramString2, QueueType paramQueueType)
    throws SQLException;
  
  public abstract AutoReply findAutoreplyByKeword(String paramString1, String paramString2, Connection paramConnection)
    throws SQLException;
  
  public abstract BusinessConfig findBusinessConfig(Connection paramConnection, String paramString)
    throws SQLException;
  
  public abstract ProcessEntity findUnClosedProcessId(Connection paramConnection, String paramString)
    throws SQLException;
  
  public abstract BusinessConfig findProcessId(Connection paramConnection, String paramString1, String paramString2)
    throws SQLException;
  
  public abstract List<PluginCase> findUsingPlugin(Connection paramConnection)
    throws SQLException;
  
  public abstract List<PluginCase> findNewPlugin(Connection paramConnection)
    throws SQLException;
  
  public abstract boolean updPlugin(Connection paramConnection, String paramString)
    throws SQLException;
}


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.mq.DB.dao.QueueDao
 * JD-Core Version:    0.7.0.1
 */