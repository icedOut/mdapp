package central.mapper;

import central.dto.DTOSession;
import central.models.Session;
import central.services.ServiceGestionSession;

import java.text.SimpleDateFormat;

public class SessionMapper {


  static SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



  public static Session mapFromDto(DTOSession dto){
    try{
      Session session = new Session();
      session.token = dto.token;
      session.codeUsager = dto.codeUsager;
      session.tempsFin = dateFormater.parse(dto.expiration);
      return session;
    }
    catch(Exception e){
      return null;
    }

  }
}
