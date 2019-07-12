package central.services;


import central.data.DaoProvider;
import central.dto.DTOUtilisateur;
import central.models.TypePermission;
import com.j256.ormlite.dao.Dao;

import javax.validation.constraints.Null;
import java.sql.SQLException;

public class ServicePermission {


    public ServicePermission(){
        
    }


    public static boolean verifierPermission(String codeUsager, TypePermission permission){
        try{
            DTOUtilisateur user = DaoProvider.getUtilisateurDAO().queryForId(codeUsager);
            return permission == TypePermission.valueOf(user.typePermission);
        }
        catch(SQLException sqle){
            System.out.println(sqle.getCause());
            return false;
        }
        catch(NullPointerException npe){
            System.out.println(npe.getCause());
            return false;
        }
    }

}

