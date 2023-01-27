package com.example.quicksend.user;

import com.example.quicksend.util.dto.ServiceResult;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final ModelMapper modelMapper;
    private final ServiceResult<UserDTO> result;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper, ServiceResult<UserDTO> userDTO) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.result = userDTO;
    }

    public ServiceResult<UserDTO> registerUser(UserDTO user) {
        try {
            // check if user email exists
            if(userRepository.existsUserByEmail(user.getEmail())) {
                return result.setStatus(HttpStatus.CONFLICT).setMessage("email address %s already exists".formatted(user.getEmail()));
            } else if(userRepository.existsUserByPhoneNumber(user.getPhoneNumber())) {
                return result.setStatus(HttpStatus.CONFLICT).setMessage("phone number %s already exists".formatted(user.getPhoneNumber()));
            }
            // register user and save to db
            User newUser = new User(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(),
                    user.getPhoneNumber(), user.getAddress(), user.getCity(), user.getCountry(),
                    user.getPostalCode(), user.getDob());
            UserDTO savedUser = modelMapper.map(userRepository.save(newUser), UserDTO.class); // convert newUser back to DTO
            return result.setStatus(HttpStatus.CREATED).setMessage("user successfully registered").setData(savedUser);
        } catch (Exception e) {
            return result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR).setMessage(e.getMessage());
        }
    }

    /**
     *
     * @param userID
     * @param updateRequest
     * @return updated user
     */
    public ServiceResult<UserDTO> updateUser(Long userID, UserDTO updateRequest) {
        try {
            User user = userRepository.findUserById(userID).get();
            if(user == null) {
                return result.setStatus(HttpStatus.NOT_FOUND).setMessage("user does not exist");
            }
            updateRequest.setId(userID); // In case this was not set, in order for BeanUtils to copy properly
            BeanUtils.copyProperties(updateRequest, user); // copy updated properties from updateRequest into user
            UserDTO savedUser = modelMapper.map(userRepository.save(user), UserDTO.class); // convert the user object returned by userRepository to UserDTO
            return result.setStatus(HttpStatus.OK).setMessage("user successfully saved").setData(savedUser);
        } catch (Exception e) {
            return result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR).setMessage(e.getMessage());
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public ServiceResult<UserDTO> getUser(Long id) {
        try {
            Optional<User> user = userRepository.findUserById(id);
            // check if user exists
            if(user.isEmpty()) {
                return result.setStatus(HttpStatus.NOT_FOUND).setMessage("user does not exist");
            }
            // return retrieved user
            UserDTO returnedUser = modelMapper.map(user.get(), UserDTO.class);
            return result.setStatus(HttpStatus.OK).setMessage("user successfully fetched").setData(returnedUser);
        } catch (Exception e) {
            return result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR).setMessage(e.getMessage());
        }
    }

    /**
     *
     * @return
     */
    public ServiceResult<UserDTO> getAllUsers() {
        try {
            List<UserDTO> userList = userRepository.findAll().stream()
                    .map(user -> modelMapper.map(user, UserDTO.class))
                    .collect(Collectors.toList());
            return result.setStatus(HttpStatus.OK).setMessage("Registered users successfully fetched").setData(userList);
        } catch (Exception e) {
            return result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR).setMessage(e.getMessage());
        }
    }


}
