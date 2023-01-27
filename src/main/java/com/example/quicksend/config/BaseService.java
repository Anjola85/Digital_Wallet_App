package com.example.quicksend.config;

import com.example.quicksend.util.dto.ServiceResult;

public interface BaseService<T> {
    ServiceResult<T> create(T dto);

    ServiceResult<T> findAll();

    ServiceResult<T> findById(Long id);

    ServiceResult<T> update(Long id, T dto);
}
