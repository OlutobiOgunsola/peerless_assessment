package com.peerless.assessment.lib.dto;

import java.time.LocalDate;

public record TransactionRequestDto(
    String senderAccount,
    String recipientAccount,
    Double amount,
    String transferDate
) {
}
