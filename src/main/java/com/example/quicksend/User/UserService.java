package com.example.quicksend.User;

import com.example.quicksend.util.dto.ServiceResult;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


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

    public ServiceResult<UserDTO> registerUser(User user) {
        try {
            // check if user email exists
            if(userRepository.existsUserByEmail(user.getEmail())) {
                return result.setStatus(HttpStatus.CONFLICT).setMessage("Email address already exists");
            }
            // save user to database
            UserDTO savedUser = modelMapper.map(userRepository.save(user), UserDTO.class);
            return result.setStatus(HttpStatus.CREATED).setMessage("User successfully registered").setData(savedUser);
        } catch (Exception e) {
            return result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR).setMessage(e.getMessage());
        }
    }

    public ServiceResult<UserDTO> updateUser(User user) {
        try {
            if(!userRepository.existsUserByEmail(user.getEmail())) {
                return result.setStatus(HttpStatus.NOT_FOUND).setMessage("User does not exist");
            }
            UserDTO savedUser = modelMapper.map(userRepository.save(user), UserDTO.class);
            return result.setStatus(HttpStatus.OK).setMessage("user successfully saved").setData(savedUser);
        } catch (Exception e) {
            return result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR).setMessage(e.getMessage());
        }
    }

    public ServiceResult<UserDTO> getUser(Long id) {
        try {
            Optional<User> user = userRepository.findUserById(id);
            // check if user exists
            if(!user.isPresent()) {
                return result.setStatus(HttpStatus.NOT_FOUND).setMessage("User does not exist");
            }
            // return retrieved user
            UserDTO returnedUser = modelMapper.map(user.get(), UserDTO.class);
            return result.setStatus(HttpStatus.OK).setMessage("user successfully fetched").setData(returnedUser);
        } catch (Exception e) {
            return result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR).setMessage(e.getMessage());
        }
    }

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
