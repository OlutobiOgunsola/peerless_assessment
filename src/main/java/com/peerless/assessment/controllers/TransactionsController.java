package com.peerless.assessment.controllers;

import com.peerless.assessment.lib.dto.TransactionRequestDto;
import com.peerless.assessment.model.Transaction;
import com.peerless.assessment.services.TransactionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("transactions")
@RequiredArgsConstructor
public class TransactionsController {

    private final TransactionsService transactionsService;

//     Schedule a transaction
    @PostMapping("/")
    public ResponseEntity<Transaction> createTransaction (@RequestBody TransactionRequestDto request) {
        // Implement transaction creation logic
        return ResponseEntity.ok(transactionsService.save(request));
    }

    // View all active transactions

//     View all transactions including cancelled transactions
    @GetMapping("/")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        // Implement transaction retrieval logic
        return ResponseEntity.ok(transactionsService.getTransactions());
    }


    // View all transactions including cancelled transactions
    @GetMapping("/{id}")
    public ResponseEntity<List<Transaction>> getAllTransactionsBySenderId(
            @PathVariable String id,
            @RequestParam(defaultValue = "false") boolean onlyActive
    ) {
        // Implement transaction retrieval logic
        return ResponseEntity.ok(transactionsService.getAllTransactionsBySenderId(id, onlyActive));
    }

    // Cancel a scheduled transaction
    @DeleteMapping("/{id}/cancel")
    public ResponseEntity<Transaction> cancelTransaction(@PathVariable Integer id) {
        // Implement transaction cancellation logic
        return ResponseEntity.ok(transactionsService.cancelTransaction(id));
    }
}
