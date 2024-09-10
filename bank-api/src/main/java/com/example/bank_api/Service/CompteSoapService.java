package com.example.bank_api.Service;

import com.example.bank_api.Model.Compte;
import com.example.bank_api.Model.Transaction;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.math.BigDecimal;
import java.util.List;

@WebService
public class CompteSoapService {
    @PersistenceContext
    private EntityManager em;

    @WebMethod
    public double getSolde(@WebParam(name = "compteid") long compteid) {
        Compte compte = em.find(Compte.class, compteid);
        if (compte != null) {
            return compte.getSolde().doubleValue();
        } else {
            throw new RuntimeException("Compte non trouvé");
        }
    }

    @WebMethod
    public Transaction[] getOperations(@WebParam(name = "compteid") long compteid,
                                       @WebParam(name = "numeroPage") int numeroPage,
                                       @WebParam(name = "taillePage") int taillePage) {
        Compte compte = em.find(Compte.class, compteid);
        if (compte != null) {
            List<Transaction> transactions = compte.getTransactions();
            int startIndex = (numeroPage - 1) * taillePage;
            int endIndex = startIndex + taillePage;
            List<Transaction> paginatedTransactions = transactions.subList(startIndex, endIndex);
            return paginatedTransactions.toArray(new Transaction[0]);
        } else {
            throw new RuntimeException("Compte non trouvé");
        }
    }

    @WebMethod
    public boolean transfererFonds(@WebParam(name = "bénéficiaire") long bénéficiaire,
                                   @WebParam(name = "débiteur") long débiteur,
                                   @WebParam(name = "montant") double montant,
                                   @WebParam(name = "devise") String devise) {
        Compte compteDébiteur = em.find(Compte.class, débiteur);
        Compte compteBénéficiaire = em.find(Compte.class, bénéficiaire);
        if (compteDébiteur != null && compteBénéficiaire != null) {
            BigDecimal montantBigDecimal = BigDecimal.valueOf(montant);
            if (compteDébiteur.getSolde().compareTo(montantBigDecimal) >= 0) {
                compteDébiteur.setSolde(compteDébiteur.getSolde().subtract(montantBigDecimal));
                compteBénéficiaire.setSolde(compteBénéficiaire.getSolde().add(montantBigDecimal));
                Transaction transaction = new Transaction(compteDébiteur, montantBigDecimal, "Virement vers compte " + compteBénéficiaire.getNumeroCompte());
                compteDébiteur.ajouterTransaction(transaction);
                em.persist(transaction);
                return true;
            } else {
                throw new RuntimeException("Solde insuffisant");
            }
        } else {
            throw new RuntimeException("Compte non trouvé");
        }
    }
}