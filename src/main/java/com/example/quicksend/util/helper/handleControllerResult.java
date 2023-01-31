package com.example.quicksend.util.helper;

import com.example.quicksend.user.UserDTO;
import com.example.quicksend.util.dto.ServiceResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * This class contains methods that help return formatted result to api calls
 */
public class handleControllerResult<T> {
    private JsonParser<T> json;

    public handleControllerResult() {
        json = new JsonParser<T>();
    }

    /**
     * Handles the response of the api in the controller
     * @param result
     * @return
     */
    public ResponseEntity<?> handleResult(ServiceResult<T> result) {
        json.setResult(result);
        return ResponseEntity.status(result.getStatus()).body(json);
    }

    public ResponseEntity<?> handleUserRegistration(ServiceResult<T> result, String header, String jwtToken) {
        json.setHeader(header).setJwt(jwtToken).setResult(result);
        if(result.getStatus().isError()) {
            json.setHeader(null);
            json.setJwt(null);
        }
        return ResponseEntity.status(result.getStatus()).header(header, jwtToken).body(json);
    }
}
