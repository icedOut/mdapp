package central.data;

public interface JDBCConnectionCreator {


  JDBCConnectionHelper createConnection(ConnectionType type);

}
