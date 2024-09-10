package com.example.bank_api.Service;

import com.example.bank_api.Model.Transaction;
import com.example.bank_api.Repository.RepositoryCompte;
import com.example.bank_api.Repository.RepositoryTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.bank_api.Model.Compte;

import java.util.List;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ServiceTransaction {

    @Autowired
    private RepositoryTransaction repositoryTransaction;

    @Autowired
    private RepositoryCompte repositoryCompte;

    public Transaction creerTransaction(Transaction transaction) {
        // Logique pour mettre à jour les soldes des comptes
        return repositoryTransaction.save(transaction);
    }

    public Optional<Transaction> obtenirTransaction(Long id) {
        return repositoryTransaction.findById(id);
    }

    public List<Transaction> obtenirToutesLesTransactions() {
        return repositoryTransaction.findAll();
    }

    public List<Transaction> obtenirTransactionsParCompte(Long compteId) {
        return repositoryTransaction.findByCompteId(compteId);
    }
}