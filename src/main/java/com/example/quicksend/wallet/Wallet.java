package com.example.quicksend.wallet;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name="wallets")
@Table(uniqueConstraints = { @UniqueConstraint(name = "UniqueCardNumberAndCvc", columnNames = { "cardNumber", "cvc" }) })
public class Wallet {

    @Id
    @SequenceGenerator(name="wallet_id_sequence", sequenceName="wallet_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wallet_sequence")

    private Long id;
    private Long userId;
    private String cardNumber; // randomly generated 16 digits that must be unique, must also be a string
    private String cvc; // randomly generated three digits that must be unique everytime, must also be string
    private String pin;
    private LocalDate expiryDate;

    private BigDecimal balance;

    public Wallet() {
        this.expiryDate = LocalDate.from(LocalDate.now()).plusYears(4);  // from the date created + 4 years
        this.pin = "0000"; // default value should be all zeros upon creation
        this.balance = BigDecimal.ZERO; // default value of zero upon creation
    }



    public Wallet(Long id, Long userId, String cardNumber, String cvc, String pin, LocalDate expiryDate, BigDecimal balance) {
        this.id = id;
        this.userId = userId;
        this.cardNumber = cardNumber;
        this.cvc = cvc;
        this.pin = pin;
        this.expiryDate = expiryDate;
        this.balance = balance;
    }

    public Wallet(Long userId, String cardNumber, String cvc, String pin, LocalDate expiryDate, BigDecimal balance) {
        this.userId = userId;
        this.cardNumber = cardNumber;
        this.cvc = cvc;
        this.pin = pin;
        this.expiryDate = expiryDate;
        this.balance = balance;
    }

    public Wallet(Long userId, String cardNumber, String cvc, String pin, BigDecimal balance) {
        this.userId = userId;
        this.cardNumber = cardNumber;
        this.cvc = cvc;
        this.pin = pin;
        this.balance = balance;
    }

    public Wallet(Long userId, String cardNumber, String cvc, String pin) {
        this.userId = userId;
        this.cardNumber = cardNumber;
        this.cvc = cvc;
        this.pin = pin;
    }

    public Long getId() {
        return id;
    }

    public Wallet setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public Wallet setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public Wallet setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public String getCvc() {
        return cvc;
    }

    public Wallet setCvc(String cvc) {
        this.cvc = cvc;
        return this;
    }

    public String getPin() {
        return pin;
    }

    public Wallet setPin(String pin) {
        this.pin = pin;
        return this;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public Wallet setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
        return this;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wallet wallet = (Wallet) o;
        return Objects.equals(id, wallet.id) && Objects.equals(userId, wallet.userId) && Objects.equals(cardNumber, wallet.cardNumber) && Objects.equals(cvc, wallet.cvc) && Objects.equals(pin, wallet.pin) && Objects.equals(expiryDate, wallet.expiryDate) && Objects.equals(balance, wallet.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, cardNumber, cvc, pin, expiryDate, balance);
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", userId=" + userId +
                ", cardNumber='" + cardNumber + '\'' +
                ", cvc='" + cvc + '\'' +
                ", pin='" + pin + '\'' +
                ", expiryDate=" + expiryDate +
                ", balance=" + balance +
                '}';
    }
}
