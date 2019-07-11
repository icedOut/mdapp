package central.controllers;

import central.models.DossierMedical;
import central.services.ServiceTelechargerDossier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ControleurChargementDossier {

    @GetMapping(value = "/dossier")
    @ResponseBody
    public ResponseEntity telechargerDossierPatient(@RequestParam String codeRAMQ){

        //TODO NEED TO CHECK TOKEN
        ServiceTelechargerDossier serviceTelecharger = new ServiceTelechargerDossier();
        Optional<DossierMedical> dossier = serviceTelecharger.telechargerDossierMedical(codeRAMQ);
        if(dossier.isPresent()){
            return ResponseEntity.status(200).body(dossier.get());
        }
        return ResponseEntity.status(400).body("sorry");
    }

}