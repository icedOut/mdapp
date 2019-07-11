package central.data;


import central.utils.Config;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.jdbc.JdbcSingleConnectionSource;

import java.io.IOException;
import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class SqLiteSingleConnection implements JDBCConnectionHelper {

  private static JdbcSingleConnectionSource conn = null;



  public JdbcSingleConnectionSource connect(){
    return getConnection();
  }

  @Override
  public void disconnect() {
    if(this.conn != null) this.conn.close();
  }


  private JdbcSingleConnectionSource getConnection() {
    if (conn != null) return conn;
    Properties conf = Config.getConfig();
    String url = conf.getProperty("db_conn_string");

    conn = new JdbcSingleConnectionSource();

    return conn;
  }






}
