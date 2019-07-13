package central.controllers;

import central.models.DossierMedical;
import central.services.ServiceModifierDossierPatient;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class ControleurModificationDossier {

    @PutMapping(value = "/modification")
    @ResponseBody
    public ResponseEntity modifierDossierPatient(@RequestBody DossierMedical modification){


        ServiceModifierDossierPatient serviceModif = new ServiceModifierDossierPatient();
        Optional<DossierMedical> dossierModifier = serviceModif.modifierDossierMedical(modification);
        if(!dossierModifier.isPresent()){
            return ResponseEntity.status(400).body(null);
        }
        return ResponseEntity.status(200).body(dossierModifier.get());


    }


    @PutMapping(value = "/annulation")
    @ResponseBody
    public ResponseEntity<DossierMedical> annulerDerniereModification(@RequestBody DossierMedical modification){

        ServiceModifierDossierPatient serviceModif = new ServiceModifierDossierPatient();
        Optional<DossierMedical> dossierRestaurer = serviceModif.annulerDerniereModification(modification);
        if(!dossierRestaurer.isPresent()){
            return ResponseEntity.status(400).body(null);
        }
        return ResponseEntity.status(200).body(dossierRestaurer.get());
    }

}