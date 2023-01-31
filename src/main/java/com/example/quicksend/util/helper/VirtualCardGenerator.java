package com.example.quicksend.util.helper;

import com.example.quicksend.wallet.WalletRepository;
import org.springframework.stereotype.Component;

@Component
public class VirtualCardGenerator {

    public String generateUniqueCardNumber(WalletRepository walletRepository) {
        String cardNumber;
        do {
            cardNumber = generateRandomCardNumber();
        } while (walletRepository.existsByCardNumber(cardNumber));
        return cardNumber;
    }

    public String generateUniqueCvc(WalletRepository walletRepository) {
        String cvc;
        do {
            cvc = generateRandomCvc();
        } while (walletRepository.existsByCvc(cvc));
        return cvc;
    }

    private String generateRandomCardNumber() {
        return String.valueOf((long) Math.floor(Math.random() * 10000000000000000L));
    }

    private String generateRandomCvc() {
        return String.valueOf((int) Math.floor(Math.random() * 1000));
    }

}