package com.example.bank_api.Repository;

import com.example.bank_api.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryTransaction extends JpaRepository<Transaction, Long> {
    List<Transaction> findByCompteId(Long compteId);
}
