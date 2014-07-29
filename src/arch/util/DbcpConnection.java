/*   1:    */package arch.util;

/*   2:    */
/*   3:    */import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

/*  13:    */
/*  14:    */public class DbcpConnection
/* 15: */{
	/* 16: 20 */private static Logger log = Logger.getLogger(DbcpConnection.class);
	/* 17: */private static DataSource dataSource;

	/* 18: */
	/* 19: */public static Connection getConnection()
	/* 20: */{
		/* 21: 24 */if (dataSource == null) {
			/* 22: 25 */initDataSource();
			/* 23: */}
		/* 24: 27 */Connection conn = null;
		/* 25: */try
		/* 26: */{
			/* 27: 29 */conn = dataSource.getConnection();
			/* 28: 30 */conn.setAutoCommit(false);
			/* 29: */}
		/* 30: */catch (SQLException e)
		/* 31: */{
			/* 32: 32 */log.fatal("数据库初始化异常", e);
			/* 33: */}
		/* 34: 34 */return conn;
		/* 35: */}

	/* 36: */
	/* 37: */public static void initDataSource()
	/* 38: */{
		/* 39: 38 */InputStream in = null;
		/* 40: 39 */Properties pro = new Properties();
		/* 41: 40 */String driver = null;
		/* 42: 41 */String url = null;
		/* 43: 42 */String username = null;
		/* 44: 43 */String password = null;
		/* 45: 44 */int initialSize = 0;
		/* 46: 45 */int minIdle = 0;
		/* 47: 46 */int maxIdle = 0;
		/* 48: 47 */int maxWait = 0;
		/* 49: 48 */int maxActive = 0;
		/* 50: 49 */in = DbcpConnection.class.getClassLoader().getResourceAsStream("dbcp.properties");
		/* 51: */try
		/* 52: */{
			/* 53: 51 */pro.load(in);
			/* 54: 52 */driver = pro.getProperty("dbcp.driverClassName");
			/* 55: 53 */url = pro.getProperty("dbcp.url");
			/* 56: 54 */username = pro.getProperty("dbcp.username");
			/* 57: 55 */password = pro.getProperty("dbcp.password");
			/* 58: 56 */initialSize = Integer.parseInt(pro.getProperty("dbcp.initialSize"));
			/* 59: 57 */minIdle = Integer.parseInt(pro.getProperty("dbcp.minIdle"));
			/* 60: 58 */maxWait = Integer.parseInt(pro.getProperty("dbcp.maxIdle"));
			/* 61: 59 */maxActive = Integer.parseInt(pro.getProperty("dbcp.maxActive"));
			/* 62: */}
		/* 63: */catch (IOException e)
		/* 64: */{
			/* 65: 61 */log.fatal("数据库配置文件读写异常", e);
			/* 66: */try
			/* 67: */{
				/* 68: 64 */in.close();
				/* 69: */}
			/* 70: */catch (IOException e1)
			/* 71: */{
				/* 72: 66 */log.fatal("读取dbcp.properties之后，关闭流异常", e1);
				/* 73: */}
			/* 74: */}
		/* 75: */finally
		/* 76: */{
			/* 77: */try
			/* 78: */{
				/* 79: 64 */in.close();
				/* 80: */}
			/* 81: */catch (IOException e)
			/* 82: */{
				/* 83: 66 */log.fatal("读取dbcp.properties之后，关闭流异常", e);
				/* 84: */}
			/* 85: */}
		/* 86: 69 */BasicDataSource ds = new BasicDataSource();
		/* 87: 70 */ds.setDriverClassName(driver);
		/* 88: 71 */ds.setUrl(url);
		/* 89: 72 */ds.setUsername(username);
		/* 90: 73 */ds.setPassword(password);
		/* 91: 74 */ds.setInitialSize(initialSize);
		/* 92: 75 */ds.setMaxActive(maxActive);
		/* 93: 76 */ds.setMinIdle(minIdle);
		/* 94: 77 */ds.setMaxIdle(maxIdle);
		/* 95: 78 */ds.setMaxWait(maxWait);
		/* 96: 79 */dataSource = ds;
		/* 97: */}

	/* 98: */
	/* 99: */public static void close(Connection conn, PreparedStatement pstm, ResultSet rs)
	/* 100: */{
		/* 101: 84 */if (rs != null) {
			/* 102: */try
			/* 103: */{
				/* 104: 86 */rs.close();
				/* 105: */}
			/* 106: */catch (SQLException e)
			/* 107: */{
				/* 108: 88 */log.fatal("DbcpConnect关闭PreparedStatement连接抛出异常", e);
				/* 109: */}
			/* 110: */}
		/* 111: 91 */if (pstm != null) {
			/* 112: */try
			/* 113: */{
				/* 114: 93 */pstm.close();
				/* 115: */}
			/* 116: */catch (SQLException e)
			/* 117: */{
				/* 118: 95 */log.fatal("DbcpConnect关闭PreparedStatement连接抛出异常", e);
				/* 119: */}
			/* 120: */}
		/* 121: 98 */if (conn != null) {
			/* 122: */try
			/* 123: */{
				/* 124:100 */conn.close();
				/* 125: */}
			/* 126: */catch (SQLException e)
			/* 127: */{
				/* 128:102 */log.fatal("DbcpConnect关闭Connection连接抛出异常", e);
				/* 129: */}
			/* 130: */}
		/* 131: */}

	/* 132: */
	/* 133: */public static void main(String[] args)
	/* 134: */{
		/* 135:107 */Connection c = getConnection();
		/* 136:108 */close(c, null, null);
		/* 137: */}
	/* 138: */
}

/*
 * Location: C:\Users\Administrator\Desktop\JRCBQ\WEB-INF\classes\
 * 
 * Qualified Name: arch.util.DbcpConnection
 * 
 * JD-Core Version: 0.7.0.1
 */