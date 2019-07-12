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
  private static SqLiteSingleConnection instance = new SqLiteSingleConnection();


  private SqLiteSingleConnection(){

  }


  public static SqLiteSingleConnection getInstance(){
    return instance;
  }

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

    Properties connectionProps = new Properties();
    connectionProps.put("user", conf.getProperty("user"));
    connectionProps.put("password", conf.getProperty("password"));

    try{
      Connection c = DriverManager.getConnection(url,connectionProps);
      conn = new JdbcSingleConnectionSource(url, c);
    }
    catch(SQLException sqle){
      System.out.println(sqle.getCause());
    }


    return conn;
  }






}
