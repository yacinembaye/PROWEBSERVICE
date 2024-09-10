package com.example.bank_api.Controller;

import com.example.bank_api.Model.Compte;
import com.example.bank_api.Service.ServiceCompte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comptes")
public class ControllerCompte {
    @Autowired
    private ServiceCompte serviceCompte;

    @PostMapping
    public ResponseEntity<Compte> creerCompte(@RequestBody Compte compte) {
        Compte nouveauCompte = serviceCompte.creerCompte(compte);
        return new ResponseEntity<>(nouveauCompte, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compte> obtenirCompte(@PathVariable Long id) {
        return serviceCompte.obtenirCompte(id)
                .map(compte -> new ResponseEntity<>(compte, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Compte>> obtenirTousLesComptes() {
        List<Compte> comptes = serviceCompte.obtenirTousLesComptes();
        return new ResponseEntity<>(comptes, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Compte> mettreAJourCompte(@PathVariable Long id, @RequestBody Compte compte) {
        return serviceCompte.mettreAJourCompte(id, compte)
                .map(compteModifie -> new ResponseEntity<>(compteModifie, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerCompte(@PathVariable Long id) {
        if (serviceCompte.supprimerCompte(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
