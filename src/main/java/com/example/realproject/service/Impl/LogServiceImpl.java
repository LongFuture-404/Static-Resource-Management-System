package com.example.realproject.service.Impl;

import com.example.realproject.dao.LogDao;
import com.example.realproject.entity.Operation;
import com.example.realproject.service.LogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("logServiceImpl")
public class LogServiceImpl implements LogService {

    @Resource(name = "logDao")
    LogDao logDao;


    @Override
    public void AddLog(Operation operation) {
        logDao.Add(operation);
    }

    @Override
    public void SuccessLog() {
        logDao.Success();
    }

    @Override
    public void FailLog() {
        logDao.Fail();
    }
}
