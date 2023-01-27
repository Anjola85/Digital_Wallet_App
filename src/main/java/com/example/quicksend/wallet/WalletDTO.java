package com.example.quicksend.wallet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class WalletDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("cardNumber")
    private String cardNumber;

    @JsonProperty("cvc")
    private String cvc;

    @JsonProperty("pin")
    private String pin; // default value will be 0000, has to be of length 4

    @JsonProperty("expiryDate")
    private LocalDate expiryDate; // 4 years from date created

    @JsonProperty("balance")
    private BigDecimal balance; // current amount of money - provides exact decimal arithmetic
}
