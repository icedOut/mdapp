package central.services;

import central.models.DemandeAuthentification;
import central.models.ReponseAuthentification;

public class ServiceAuthentification {


    public ServiceAuthentification(){


        
    }


    public ReponseAuthentification authentifier(DemandeAuthentification demande){
        return new ReponseAuthentification(true);
    }



}
