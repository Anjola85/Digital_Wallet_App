package com.example.quicksend.util.helper;

import com.example.quicksend.user.UserDTO;
import com.example.quicksend.util.dto.ServiceResult;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * This class contains methods that help return formatted result to api calls
 */
public class handleControllerResult {

    public handleControllerResult(){};

    /**
     * @precondition - result.getData() cannot be null
     * @param result
     * @return
     */
    public ResponseEntity<?> handleResult(ServiceResult<UserDTO> result) {
        JsonParser json = new JsonParser().setResult(result);
        return ResponseEntity.status(result.getStatus()).body(json);
    }

    public ResponseEntity<?> handleUserRegistration(ServiceResult<UserDTO> result, String header, String jwtToken) {
        JsonParser json = new JsonParser(header, jwtToken);
        return ResponseEntity.status(result.getStatus()).header(header, jwtToken).body(result.getData());
    }
}
