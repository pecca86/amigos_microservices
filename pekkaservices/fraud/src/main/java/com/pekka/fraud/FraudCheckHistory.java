package com.pekka.fraud;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "FraudCheckHistory")
@Table(name = "fraud_check_history")
public class FraudCheckHistory {

    @Id
    @SequenceGenerator(
            name = "fraud_id_sequence",
            sequenceName = "fraud_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "fraud_id_sequence"
    )
    @Column(name = "id")
    private Integer id;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "is_fraudster")
    private Boolean isFraudster;

    @Column(name = "checked_at")
    private LocalDateTime checkedAt;
}
