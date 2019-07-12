package central.mapper;

import central.dto.DTOUtilisateur;
import central.models.TypePermission;
import central.models.Utilisateur;

public class UtilisateurMapper {


  public UtilisateurMapper() {
  }

  public static Utilisateur mapFromDto(DTOUtilisateur dto){
    Utilisateur user = new Utilisateur();
    user.codeUsager = dto.codeUsager;
    user.hash = dto.hash;
    user.permissions = new TypePermission[] { TypePermission.valueOf(dto.typePermission) };
    user.salt = dto.salt;
    return user;
  }
}
