package com.example.quicksend.config;

import com.example.quicksend.util.dto.ServiceResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface BaseController<T> {
    ResponseEntity<?> create(@RequestBody T body);

    ResponseEntity<?> findAll();

    ResponseEntity<?> findById(@PathVariable Long id);

    ResponseEntity<?> update(@PathVariable Long id, @RequestBody T dto);
}
