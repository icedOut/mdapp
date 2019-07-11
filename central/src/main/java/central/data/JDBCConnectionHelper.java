package central.data;


import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.BaseConnectionSource;

public interface JDBCConnectionHelper {


  JdbcConnectionSource connect();
  void disconnect();

}
