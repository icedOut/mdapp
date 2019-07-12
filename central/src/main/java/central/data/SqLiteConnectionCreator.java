package central.data;

public class SqLiteConnectionCreator implements JDBCConnectionCreator {


  public JDBCConnectionHelper createConnection(ConnectionType type){

    switch(type){

      case POOLED:
        return SqLitePooledConnection.getInstance();

      case SINGLE:
      default:
        return SqLiteSingleConnection.getInstance();

    }

  }
}
