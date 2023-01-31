package com.example.quicksend.wallet;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    boolean existsWalletByUserId(Long id);
    boolean existsWalletById(Long id);
    Optional<Wallet> findWalletByUserId(Long id);
    Optional<Wallet> findWalletById(Long id);

    boolean existsByCardNumber(String cardNumber);

    boolean existsByCvc(String cvc);
}
