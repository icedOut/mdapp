package central.data;

import central.utils.Config;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.BaseConnectionSource;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Properties;

public class SqLitePooledConnection implements JDBCConnectionHelper{

  private static JdbcPooledConnectionSource conn = null;
  private static SqLitePooledConnection instance = new SqLitePooledConnection();

  private SqLitePooledConnection(){

  }

  public static SqLitePooledConnection getInstance(){
    return instance;
  }

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
      String scheme = conf.getProperty("db_scheme");
      String dbName = conf.getProperty("db_name");

      Path currentRelativePath = Paths.get("");
      String path = currentRelativePath.toAbsolutePath().toString();

      String url = scheme.concat(path).concat("/").concat(dbName);

      conn = new JdbcPooledConnectionSource(url);
      conn.setMaxConnectionsFree(5);
      System.out.println("Connected to the RAMQ database successfully.");
    } catch (SQLException e) {
      System.out.println("Erreur lors de la connexion à la base de données");
      System.out.println(e.getMessage());
    }

    return conn;
  }
}
