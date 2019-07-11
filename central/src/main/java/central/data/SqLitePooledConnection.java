package central.data;

import central.utils.Config;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.BaseConnectionSource;

import java.sql.SQLException;
import java.util.Properties;

public class SqLitePooledConnection implements JDBCConnectionHelper{

  private static JdbcPooledConnectionSource conn = null;

  @Override
  public JdbcPooledConnectionSource connect() {
    return this.getConnection();
  }

  @Override
  public void disconnect() {

  }



  private JdbcPooledConnectionSource getConnection(){
    if (conn != null) return conn;
    try {

      Properties conf = Config.getConfig();
      String url = conf.getProperty("db_conn_string");

      conn = new JdbcPooledConnectionSource(url);
      conn.setMaxConnectionsFree(5);
      System.out.println("Connected to the RAMQ database successfully.");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }

    return conn;
  }
}
