package central.data;


import com.j256.ormlite.jdbc.JdbcConnectionSource;

public interface JDBCConnectionHelper {


  JdbcConnectionSource connect();
  void disconnect();

}
