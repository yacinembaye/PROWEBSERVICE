package com.example.bank_api.Controller;

import com.example.bank_api.Model.Transaction;
import com.example.bank_api.Service.ServiceTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class ControllerTransaction {
    @Autowired
    private ServiceTransaction serviceTransaction;

    @PostMapping
    public ResponseEntity<Transaction> creerTransaction(@RequestBody Transaction transaction) {
        Transaction nouvelleTransaction = serviceTransaction.creerTransaction(transaction);
        return new ResponseEntity<>(nouvelleTransaction, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> obtenirTransaction(@PathVariable Long id) {
        return serviceTransaction.obtenirTransaction(id)
                .map(transaction -> new ResponseEntity<>(transaction, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> obtenirToutesLesTransactions() {
        List<Transaction> transactions = serviceTransaction.obtenirToutesLesTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/compte/{compteId}")
    public ResponseEntity<List<Transaction>> obtenirTransactionsParCompte(@PathVariable Long compteId) {
        List<Transaction> transactions = serviceTransaction.obtenirTransactionsParCompte(compteId);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
