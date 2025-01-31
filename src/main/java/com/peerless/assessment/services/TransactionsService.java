package com.peerless.assessment.services;

import com.peerless.assessment.lib.dto.TransactionRequestDto;
import com.peerless.assessment.model.Transaction;
import com.peerless.assessment.repository.TransactionsRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionsService {
    private final TransactionsRepository transactionsRepository;

    public Transaction save(TransactionRequestDto request) {
        Transaction transaction = new Transaction();
        if(request.senderAccount() == null) {
            throw new IllegalArgumentException("Sender account cannot be null.");
        }
        transaction.setSenderAccount(request.senderAccount());
        if(request.recipientAccount() == null) {
            throw new IllegalArgumentException("Recipient account cannot be null.");
        }
        transaction.setRecipientAccount(request.recipientAccount());
        transaction.setTransactionStatus("scheduled");

        LocalDate createDate = LocalDate.now();
        transaction.setCreatedDate(createDate);

        if(request.amount() < 0.0) {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }
        transaction.setAmount(request.amount());

        LocalDate today = LocalDate.now();
        LocalDate tfDate = LocalDate.parse(request.transferDate());
        if(today.isAfter(tfDate)) {
            throw new IllegalArgumentException("Transfer date cannot be in the past.");
        }
        transaction.setTransferDate(request.transferDate());
        transactionsRepository.save(transaction);
        return transaction;
    }

    public Transaction getTransaction(Integer id) {
        return transactionsRepository.findById(id).orElse(null);
    }

    public List<Transaction> getTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        transactionsRepository.findAll().forEach(transactions::add);
        return transactions;
    }

    public List<Transaction> getAllTransactionsBySenderId(String senderId, Boolean onlyActive) {
        List<Transaction> transactions = new ArrayList<>();
        transactionsRepository.findAllBySenderAccount(senderId).forEach(transactions::add);
        if(onlyActive) {
            return transactions.stream().filter(transaction ->
                transaction.getTransactionStatus().equals("scheduled")
            ).collect(Collectors.toList());
        }
        return transactions;
    }

    public Transaction cancelTransaction(Integer id) {
        Transaction transaction = transactionsRepository.findById(id).orElse(null);
        if(transaction != null) {
            transaction.setTransactionStatus("cancelled");
            transactionsRepository.save(transaction);
        }
        return transaction;
    }
}
