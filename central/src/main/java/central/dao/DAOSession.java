package central.dao;



import central.data.DaoProvider;
import central.dto.DTOSession;
import central.mapper.SessionMapper;
import central.models.Session;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class DAOSession extends BaseDaoImpl<DTOSession, String> {


  static SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  public DAOSession(ConnectionSource connectionSource) throws SQLException {
    super(connectionSource, DTOSession.class);
  }


  public Session creerSession(String token, String codeUsager){
    try{
      DTOSession session = new DTOSession();
      session.codeUsager = codeUsager;
      session.token = token;
      session.expiration = dateFormater.format(getSessionExpirationTime());
      this.create(session);
      return SessionMapper.mapFromDto(session);

    }
    catch(SQLException sqle){
      System.out.println(sqle.getCause());
      return null;
    }
  }


  public Session obtenirSession(String token){
    try{
      List<DTOSession> sessions = this.queryForEq("token", token);
      if(sessions.size() == 0){
        return null;
      }
      return SessionMapper.mapFromDto(sessions.get(0));
    }
      catch(SQLException sqle){
      System.out.println(sqle.getCause());
      return null;
    }
  }

  public void terminerSession(String token){
    try{
      DTOSession session = this.queryForEq("token", token).get(0);
      session.expiration = dateFormater.format(new Date());
      DaoProvider.getSessionDAO().update(session);
    }
    catch(SQLException sqle){
      System.out.println(sqle.getCause());
    }
  }



  private static Date getSessionExpirationTime(){
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date());
    calendar.add(Calendar.HOUR_OF_DAY, 1);
    return calendar.getTime();
  }


}



