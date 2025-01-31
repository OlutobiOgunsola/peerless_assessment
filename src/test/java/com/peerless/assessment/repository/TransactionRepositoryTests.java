package com.peerless.assessment.repository;

import com.peerless.assessment.model.Transaction;
import com.peerless.assessment.services.TransactionsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TransactionRepositoryTests {
    @Autowired
    private TransactionsRepository transactionsRepository;
    private TransactionsService transactionsService;
    // tests creates and saves a transaction
    @Test
    public void TransactionRepository_CreateTransaction_ReturnCreatedTransaction () {
        Transaction transaction = Transaction.builder().senderAccount("sender1").recipientAccount("recipient1").amount(100.00).transferDate("2026-01-01").createdDate(LocalDate.parse("2025-02-10")).build();
        Transaction savedTransaction = transactionsRepository.save(transaction);
        assert savedTransaction.getId() != null;
    };

    // tests retrieves all transactions for id
    @Test
    public void TransactionRepository_GetAllTransactions_ReturnAllTransactions () {
        // add some transactions
        Transaction transaction1 = Transaction.builder().senderAccount("sender1").recipientAccount("recipient1").amount(100.00).transferDate("2026-01-01").createdDate(LocalDate.parse("2025-02-10")).build();
        Transaction transaction2 = Transaction.builder().senderAccount("sender1").recipientAccount("recipient2").amount(100.00).transferDate("2026-01-01").createdDate(LocalDate.parse("2025-02-10")).build();
        Transaction transaction3 = Transaction.builder().senderAccount("sender2").recipientAccount("recipient3").amount(100.00).transferDate("2026-01-01").createdDate(LocalDate.parse("2025-02-10")).build();
        transactionsRepository.saveAll(List.of(transaction1, transaction2, transaction3));
        // verify the retrieved list contains all added transactions
        List<Transaction> allTransactions = transactionsRepository.findAllBySenderAccount("sender1");
        assert allTransactions.contains(transaction1);
        assert allTransactions.contains(transaction2);
        assert allTransactions.size() == 2;
    };
}
