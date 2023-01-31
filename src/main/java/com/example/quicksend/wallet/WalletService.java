package com.example.quicksend.wallet;

import com.example.quicksend.config.BaseService;
import com.example.quicksend.user.User;
import com.example.quicksend.user.UserDTO;
import com.example.quicksend.user.UserRepository;
import com.example.quicksend.util.dto.ServiceResult;
import com.example.quicksend.util.helper.VirtualCardGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WalletService implements BaseService<WalletDTO> {

    private final ModelMapper modelMapper;
    private final ServiceResult<WalletDTO> result;
    private final WalletRepository repository;

    private final VirtualCardGenerator virtualCardGenerator;

    @Autowired
    public WalletService(WalletRepository walletRepository,
                         ModelMapper modelMapper,
                         ServiceResult<WalletDTO> walletDTO,
                         VirtualCardGenerator virtualCardGenerator) {
        this.repository = walletRepository;
        this.modelMapper = modelMapper;
        this.result = walletDTO;
        this.virtualCardGenerator = virtualCardGenerator;
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
        try {
            List<WalletDTO> walletDTOList = repository.findAll().stream()
                    .map(wallet -> modelMapper.map(wallet, WalletDTO.class))
                    .collect(Collectors.toList());
            return result.setStatus(HttpStatus.OK).setMessage("wallets successfully fetched").setData(walletDTOList);
        } catch (Exception e) {
            return new ServiceResult<WalletDTO>().setStatus(HttpStatus.INTERNAL_SERVER_ERROR).setMessage(e.getMessage());
        }
    }

    @Override
    public ServiceResult<WalletDTO> findById(Long id) {
        try {
            Optional<Wallet> wallet = repository.findWalletById(id);
            if(wallet.isEmpty()) {
                return new ServiceResult<WalletDTO>().setStatus(HttpStatus.NOT_FOUND).setMessage("wallet does not exist");
            }
            WalletDTO returnedWallet = modelMapper.map(wallet.get(), WalletDTO.class);
            return new ServiceResult<WalletDTO>().setStatus(HttpStatus.OK).setMessage("wallet successfully fetched").setData(returnedWallet);
        } catch(Exception e) {
            return new ServiceResult<WalletDTO>().setStatus(HttpStatus.INTERNAL_SERVER_ERROR).setMessage(e.getMessage());
        }
    }

    @Override
    public ServiceResult<WalletDTO> update(Long id, WalletDTO updateRequest) {
        try {
            Wallet wallet = repository.findWalletById(id).get();
            if(wallet == null) {
                return new ServiceResult<WalletDTO>().setStatus(HttpStatus.NOT_FOUND).setMessage("wallet does not exist");
            }
            updateRequest.setId(id);
            BeanUtils.copyProperties(updateRequest, wallet);
            WalletDTO savedWallet = modelMapper.map(repository.save(wallet), WalletDTO.class);
            return result.setStatus(HttpStatus.OK).setMessage("wallet successfully saved").setData(savedWallet);
        } catch(Exception e) {
            return new ServiceResult<WalletDTO>().setStatus(HttpStatus.INTERNAL_SERVER_ERROR).setMessage(e.getMessage());
        }
    }

    public ServiceResult<WalletDTO> findByUserId(Long id) {
        try {
            Optional<Wallet> wallet = repository.findWalletByUserId(id);
            if(wallet.isEmpty()) {
                return new ServiceResult<WalletDTO>().setStatus(HttpStatus.NOT_FOUND).setMessage("user with wallet does not exist");
            }
            WalletDTO returnedWallet = modelMapper.map(wallet.get(), WalletDTO.class);
            return new ServiceResult<WalletDTO>().setStatus(HttpStatus.OK).setMessage("wallet successfully fetched").setData(returnedWallet);
        } catch(Exception e) {
            return new ServiceResult<WalletDTO>().setStatus(HttpStatus.INTERNAL_SERVER_ERROR).setMessage(e.getMessage());
        }
    }

    public WalletDTO createWallet(Long userId) {
        Wallet wallet = new Wallet();
        wallet.setBalance(new BigDecimal(0.0));
        wallet.setUserId(userId);

        // generate values for virtual card fields
        wallet.setCardNumber(virtualCardGenerator.generateUniqueCardNumber(repository));
        wallet.setCvc(virtualCardGenerator.generateUniqueCvc(repository));

        // save to DB
        repository.save(wallet);
        return modelMapper.map(wallet, WalletDTO.class);
    }
}
