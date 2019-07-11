package central.data;

public class SqLiteConnectionCreator implements JDBCConnectionCreator {


  public JDBCConnectionHelper createConnection(ConnectionType type){

    switch(type){

      case POOLED:
        return new SqLitePooledConnection();

      case SINGLE:
      default:
        return new SqLiteSingleConnection();

    }

  }
}
