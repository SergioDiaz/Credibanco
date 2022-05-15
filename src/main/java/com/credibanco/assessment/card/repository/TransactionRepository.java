package com.credibanco.assessment.card.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.credibanco.assessment.card.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
