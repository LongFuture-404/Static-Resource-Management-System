package com.example.realproject.dao;

import com.example.realproject.entity.Operation;
import org.springframework.stereotype.Repository;

@Repository("logDao")
public interface LogDao {

    void Add(Operation operation);

    void Success();

    void Fail();
}
