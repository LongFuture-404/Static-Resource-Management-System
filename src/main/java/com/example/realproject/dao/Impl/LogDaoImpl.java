//package com.example.realproject.dao.Impl;
//
//import com.example.realproject.dao.LogDao;
//import com.example.realproject.entity.Operation;
//import com.example.realproject.util.MyBatisUtil;
//import org.apache.ibatis.session.SqlSession;
//import org.springframework.stereotype.Repository;
//
//@Repository("logDaoImpl")
//public class LogDaoImpl implements LogDao {
//
//    SqlSession sqlSession= MyBatisUtil.getSqlSession();
//    @Override
//    public void Add(Operation operation) {
//        sqlSession.insert("com.example.realproject.dao.LogDao.InsertOp",operation);
//    }
//
//}
