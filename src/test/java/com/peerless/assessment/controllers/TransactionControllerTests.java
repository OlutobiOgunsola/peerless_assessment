package com.peerless.assessment.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.peerless.assessment.lib.dto.TransactionRequestDto;
import com.peerless.assessment.model.Transaction;
import com.peerless.assessment.services.TransactionsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = TransactionsController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class TransactionControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TransactionsService transactionsService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void CreateTransaction_ReturnCreated () throws Exception {
        // Arrange
        TransactionRequestDto request = new TransactionRequestDto("sender", "recipient", 100.0, "2026-02-12");
        Transaction transaction = new Transaction();
        transaction.setId(1);
        transaction.setSenderAccount("sender");
        transaction.setRecipientAccount("recipient");
        transaction.setAmount(100.0);
        transaction.setCreatedDate(LocalDate.now());
        transaction.setTransferDate("2026-01-01");
        transaction.setTransactionStatus("scheduled");
        // Act
        String jsonContent = objectMapper.writeValueAsString(request);
        ResultActions response = mockMvc.perform(post("/transactions/").contentType("application/json")
                .content(jsonContent));
        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
}
