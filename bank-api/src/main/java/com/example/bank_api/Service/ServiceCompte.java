package com.example.bank_api.Service;

import com.example.bank_api.Model.Compte;
import com.example.bank_api.Repository.RepositoryCompte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceCompte {

    @Autowired
    private RepositoryCompte repositoryCompte;

    public Compte creerCompte(Compte compte) {
        return repositoryCompte.save(compte);
    }

    public Optional<Compte> obtenirCompte(Long id) {
        return repositoryCompte.findById(id);
    }

    public List<Compte> obtenirTousLesComptes() {
        return repositoryCompte.findAll();
    }

    public Optional<Compte> mettreAJourCompte(Long id, Compte compteDetails) {
        return repositoryCompte.findById(id)
                .map(compte -> {
//                    compte.setSolde(compteDetails.getSolde());
                    return repositoryCompte.save(compte);
                });
    }

    public boolean supprimerCompte(Long id) {
        return repositoryCompte.findById(id)
                .map(compte -> {
                    repositoryCompte.delete(compte);
                    return true;
                })
                .orElse(false);
    }
}