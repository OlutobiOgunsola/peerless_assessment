package com.peerless.assessment.model;


import java.time.LocalDate;
import java.time.LocalDateTime;

import com.peerless.assessment.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="scheduled_transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name="transaction_status")
    private String transactionStatus;
    @Column(name="transfer_date")
    private String transferDate;
    @Column(name="sender_account", nullable=false)
    private String senderAccount;
    @Column(name="recipient_account", nullable=false)
    private String recipientAccount;
    @Column(name="amount", nullable=false)
    private Double amount;
    @Column(name="created_date", nullable=false)
    private LocalDate createdDate;

}
