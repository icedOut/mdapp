package central.controllers;

import central.models.DemandeAuthentification;
import central.models.ReponseAuthentification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@Controller
public class ControleurAuthentification {

    @PostMapping(value = "/authentifier")
    @ResponseBody
    public ResponseEntity authentifierUsager(@RequestBody DemandeAuthentification demande){
        ReponseAuthentification reponse = new ReponseAuthentification(true);
        return ResponseEntity.status(200).body(reponse);
    }

    @PutMapping(value = "/deconnexion")
    @ResponseBody
    public ResponseEntity deconnexion(@RequestParam String token){
        return ResponseEntity.status(200).body(null);
    }

}