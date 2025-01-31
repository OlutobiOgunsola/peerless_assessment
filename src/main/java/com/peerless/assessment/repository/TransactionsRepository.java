package com.peerless.assessment.repository;

import com.peerless.assessment.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionsRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findAllBySenderAccount(String senderId);
}
