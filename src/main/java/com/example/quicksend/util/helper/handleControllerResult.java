package com.example.quicksend.util.helper;

import com.example.quicksend.user.UserDTO;
import com.example.quicksend.util.dto.ServiceResult;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class handleControllerResult {

    public handleControllerResult(){};

    public ResponseEntity<?> handleResult(ServiceResult<UserDTO> result) {
        return ResponseEntity.status(result.getStatus()).body(result.getData());
    }

    public ResponseEntity<?> handleUserRegistration(ServiceResult<UserDTO> result, String header, String jwtToken) {
        return ResponseEntity.status(result.getStatus()).header(header, jwtToken).build();
    }
}
