package central.services;

import central.data.DaoProvider;
import central.dto.DTOSession;
import central.mapper.SessionMapper;
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
        String token = UUID.randomUUID().toString();
        Session session = DaoProvider.getSessionDAO().creerSession(token, codeUsager);
        if(session == null) return null;
        return token;
    }


    public static boolean verifierSessionActive(String token){

        Session session = DaoProvider.getSessionDAO().obtenirSession(token);
        if(session == null) return false;
        Date now = new Date();
        return session.tempsFin.after(now);

    }

    public static void fermerSession(String token){
        if(!verifierSessionActive(token)) return;
        DaoProvider.getSessionDAO().terminerSession(token);
    }

    public static Session obtenirSession(String token){
        return DaoProvider.getSessionDAO().obtenirSession(token);
    }





}
