package com.example.realproject.service;

import com.example.realproject.entity.Operation;

public interface LogService {

    void AddLog(Operation operation);

    void SuccessLog();

    void FailLog();
}
