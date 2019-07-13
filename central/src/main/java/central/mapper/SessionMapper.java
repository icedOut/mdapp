package central.mapper;

import central.dto.DTOSession;
import central.models.Session;
import central.services.ServiceGestionSession;
import central.utils.DateFormatter;

import java.text.SimpleDateFormat;

public class SessionMapper {




  public static Session mapFromDto(DTOSession dto){
    try{
      Session session = new Session();
      session.token = dto.token;
      session.codeUsager = dto.codeUsager;
      session.tempsFin = DateFormatter.stringToDate(dto.expiration);
      return session;
    }
    catch(Exception e){
      return null;
    }

  }
}
