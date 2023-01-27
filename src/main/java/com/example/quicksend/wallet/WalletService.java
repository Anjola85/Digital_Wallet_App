package com.example.quicksend.wallet;

import com.example.quicksend.config.BaseService;
import com.example.quicksend.user.User;
import com.example.quicksend.user.UserDTO;
import com.example.quicksend.user.UserRepository;
import com.example.quicksend.util.dto.ServiceResult;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class WalletService implements BaseService<WalletDTO> {

    private final ModelMapper modelMapper;
    private final ServiceResult<WalletDTO> result;
    private final WalletRepository repository;

    @Autowired
    public WalletService(WalletRepository walletRepository, ModelMapper modelMapper, ServiceResult<WalletDTO> walletDTO) {
        this.repository = walletRepository;
        this.modelMapper = modelMapper;
        this.result = walletDTO;
    }

    @Override
    public ServiceResult<WalletDTO> create(WalletDTO wallet) {
        try {
            // check if user already has a wallet
            if(repository.existsWalletByUserId(wallet.getUserId())) {
                return result.setStatus(HttpStatus.CONFLICT).setMessage("wallet has already been assigned to a user");
            }
            // create the new wallet
            Wallet newWallet = modelMapper.map(wallet, Wallet.class);
            // convert the wallet back to DTO to get the updated fields
            WalletDTO savedWallet = modelMapper.map(newWallet, WalletDTO.class);
            // save the wallet to the db
            repository.save(newWallet);
            return result.setStatus(HttpStatus.CREATED).setMessage("wallet created").setData(savedWallet);
        } catch (Exception e) {
            return result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR).setMessage(e.getMessage());
        }
    }

    @Override
    public ServiceResult<WalletDTO> findAll() {
        return null;
    }

    @Override
    public ServiceResult<WalletDTO> findById(Long id) {
        return null;
    }

    @Override
    public ServiceResult<WalletDTO> update(Long id, WalletDTO dto) {
        return null;
    }
}
