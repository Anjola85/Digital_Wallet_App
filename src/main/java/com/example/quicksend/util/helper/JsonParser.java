package com.example.quicksend.util.helper;

import com.example.quicksend.util.dto.ServiceResult;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

public class JsonParser<T> {
    @JsonProperty("header")
    private String header;
    @JsonProperty("jwt-token")
    private String jwt;
    @JsonProperty("status")
    private String status;

    @JsonProperty("message")
    private  String message;

    @JsonProperty("body")
    private List body;

    public JsonParser() {}

    /**
     * Sets all the class variables
     * @param header
     * @param jwt
     * @param status
     * @param message
     * @param body
     */
    public JsonParser(String header, String jwt, String status, String message, List body) {
        this.header = header;
        this.jwt = jwt;
        this.status = status;
        this.message = message;
        this.body = body;
    }

    /**
     * sets the status, message and body
     * @param status
     * @param message
     * @param body
     */
    public JsonParser(String status, String message, List body) {
        this.status = status;
        this.message = message;
        this.body = body;
    }


    /**
     * Sets the header and jwt
     * @param header
     * @param jwt
     */
    public JsonParser(String header, String jwt) {
        this.header = header;
        this.jwt = jwt;
    }

    public JsonParser setResult(ServiceResult result) {
        this.status = result.getStatus().toString();
        this.message = result.getMessage();
        this.body = result.getData();
        return this;
    }
}
