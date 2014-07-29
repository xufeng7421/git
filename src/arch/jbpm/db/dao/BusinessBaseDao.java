package arch.jbpm.db.dao;

import arch.jbpm.entity.JbpmBaseEntity;
import java.sql.Connection;
import java.sql.SQLException;

public abstract interface BusinessBaseDao
{
  public abstract String nextStep(JbpmBaseEntity paramJbpmBaseEntity, Connection paramConnection)
    throws SQLException;
  
  public abstract void addProcessInfo(JbpmBaseEntity paramJbpmBaseEntity, Connection paramConnection)
    throws SQLException;
  
  public abstract void updProcessStatus(JbpmBaseEntity paramJbpmBaseEntity, Connection paramConnection)
    throws SQLException;
  
  public abstract void addProcessStep(JbpmBaseEntity paramJbpmBaseEntity, Connection paramConnection)
    throws SQLException;
  
  public abstract String sendQueue(String paramString, Connection paramConnection)
    throws SQLException;
}


/* Location:           C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * Qualified Name:     arch.jbpm.db.dao.BusinessBaseDao
 * JD-Core Version:    0.7.0.1
 */