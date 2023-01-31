package com.example.quicksend.wallet;

import com.example.quicksend.config.BaseController;
import com.example.quicksend.util.helper.handleControllerResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallet")
public class WalletController implements BaseController<WalletDTO> {

    private final WalletService walletService;
    private final handleControllerResult<WalletDTO> result;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
        this.result = new handleControllerResult<>();
    }

    @PostMapping("/create-card")
    public ResponseEntity<?> create(@RequestBody WalletDTO wallet) {
        return result.handleResult(walletService.create(wallet));
    }

    @GetMapping("/all-wallets")
    public ResponseEntity<?> findAll() {
        return result.handleResult(walletService.findAll());
    }

    @GetMapping("{walletId}")
    public ResponseEntity<?> findById(@PathVariable("walletId") Long walletId) {
        return result.handleResult(walletService.findById(walletId));
    }

    @PutMapping("update/{walletId}")
    public ResponseEntity<?> update(@PathVariable("walletId") Long walletId, @RequestBody WalletDTO updateRequest) {
        return result.handleResult(walletService.update(walletId, updateRequest));
    }
}
