package central.services;

import java.sql.SQLException;
import central.data.DaoProvider;
import central.dto.DTOUtilisateur;
import central.models.DemandeAuthentification;
import central.models.ReponseAuthentification;
import central.utils.Hasher;



public class ServiceAuthentification {


    public ServiceAuthentification(){


        
    }


    public static ReponseAuthentification authentifier(DemandeAuthentification demande){
        try{

            DTOUtilisateur user = DaoProvider.getUtilisateurDAO().queryForId(demande.codeUsager);
            String fullPwd = Hasher.hashPassword(demande.motDePasse, user.salt);

            if(fullPwd.compareTo(user.hash) != 0){
                return new ReponseAuthentification(false);
            }
            String token = ServiceGestionSession.creerSession(demande.codeUsager);

            ReponseAuthentification response = new ReponseAuthentification(true);
            response.token = token;

            return response;
        }
        catch(SQLException sqle){
            System.out.println(sqle.getCause());
            return new ReponseAuthentification(false);
        }

    }


    public static void deconnecter(String token){
        ServiceGestionSession.fermerSession(token);
    }





}
