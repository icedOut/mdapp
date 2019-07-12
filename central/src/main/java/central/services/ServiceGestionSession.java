package central.services;

import central.data.DaoProvider;
import central.dto.DTOSession;
import central.models.Session;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ServiceGestionSession {

    static SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public ServiceGestionSession(){
        
    }


    public static String creerSession(String codeUsager){
        DTOSession session = new DTOSession();
        session.codeUsager = codeUsager;
        session.token = UUID.randomUUID().toString();
        session.expiration = dateFormater.format(getSessionExpiration());

        try{
            DaoProvider.getSessionDAO().create(session);
        }
        catch(SQLException sqle){
            System.out.println(sqle.getCause());
            return null;
        }


        return session.token;
    }


    public static boolean verifierSessionActive(String token){
        try{
            List<DTOSession> sessions = DaoProvider.getSessionDAO().queryForEq("token", token);
            if(sessions.size() <= 0) return false;

            Date expiration = dateFormater.parse(sessions.get(0).expiration);
            Date now = new Date();

            return expiration.after(now);
        }
        catch(SQLException sqle){
            System.out.println(sqle.getCause());
            return false;
        }
        catch(ParseException pe){
            System.out.println(pe.getErrorOffset());
            return false;
        }
    }

    public static void fermerSession(String token){
        try{
            if(verifierSessionActive(token)){
                DTOSession session = DaoProvider.getSessionDAO().queryForEq("token", token).get(0);
                session.expiration = dateFormater.format(new Date());
                DaoProvider.getSessionDAO().update(session);
            }
        }
        catch(SQLException sqle){
            System.out.println(sqle.getCause());
        }
    }

    public static Session getSession(String token){
        try{
            DTOSession dto = DaoProvider.getSessionDAO().queryForEq("token", token).get(0);
            Session session = new Session();
            session.codeUsager = dto.codeUsager;
            session.tempsFin = dateFormater.parse(dto.expiration);
            session.token = dto.token;
            return session;
        }
        catch(Exception e){
            System.out.println(e.getCause());
            return null;
        }
    }


    private static Date getSessionExpiration(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        return calendar.getTime();
    }


}
