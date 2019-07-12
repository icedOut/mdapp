package central.services;

import java.sql.SQLException;
import central.data.DaoProvider;
import central.dto.DTOUtilisateur;
import central.models.DemandeAuthentification;
import central.models.ReponseAuthentification;
import central.models.Utilisateur;
import central.utils.Hasher;



public class ServiceAuthentification {


    public ServiceAuthentification(){


        
    }


    public static ReponseAuthentification authentifier(DemandeAuthentification demande){

        Utilisateur user = DaoProvider.getUtilisateurDAO().getUtilisateur(demande.codeUsager);
        String fullPwd = Hasher.hashPassword(demande.motDePasse, user.salt);

        if(!motDePasseValide(demande.motDePasse, fullPwd)){
            return new ReponseAuthentification(false);
        }

        String token = ServiceGestionSession.creerSession(demande.codeUsager);

        ReponseAuthentification response = new ReponseAuthentification(true);
        response.token = token;
        return response;
    }


    public static void deconnecter(String token){

        ServiceGestionSession.fermerSession(token);
    }

    private static boolean motDePasseValide(String motdePasse, String hash){
        return motdePasse.compareTo(hash) == 0;
    }





}
