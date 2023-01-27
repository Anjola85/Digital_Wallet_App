package com.example.quicksend.util.helper;

import com.example.quicksend.util.dto.ServiceResult;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonParser<T> {
    @JsonProperty("header")
    private String header = null;
    @JsonProperty("jwt-token")
    private String jwt = null;
    @JsonProperty("status")
    private String status = null;

    @JsonProperty("message")
    private  String message = null;

    @JsonProperty("body")
    private List<T> body = null;

    public JsonParser() {

    }

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

    public JsonParser(String header, String jwt, ServiceResult result) {
        this.header = header;
        this.jwt = jwt;
        this.status = result.getStatus().toString();
        this.message = result.getMessage();
        this.body = result.getData();
    }

    /**
     * sets the status, message and body
     * @param status
     * @param message
     * @param body
     */
    public JsonParser(String status, String message, List<T> body) {
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

    public String getHeader() {
        return header;
    }

    public JsonParser<T> setHeader(String header) {
        this.header = header;
        return this;
    }

    public String getJwt() {
        return jwt;
    }

    public JsonParser<T> setJwt(String jwt) {
        this.jwt = jwt;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public JsonParser<T> setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public JsonParser<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public List<T> getBody() {
        return body;
    }

    public JsonParser<T> setBody(List<T> body) {
        this.body = body;
        return this;
    }

    public JsonParser<T> setResult(ServiceResult<T> result) {
        this.status = result.getStatus().toString();
        this.message = result.getMessage();
        this.body = result.getData();
        return this;
    }
}
