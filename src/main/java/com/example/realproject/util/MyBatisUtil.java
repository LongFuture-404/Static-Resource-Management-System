//package com.example.realproject.util;
//
//import org.apache.ibatis.io.Resources;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.ibatis.session.SqlSessionFactoryBuilder;
//
//import java.io.InputStream;
//
//public class MyBatisUtil {
//    //先要申明一个SqlSessionFactory
//    static SqlSessionFactory sqlSessionFactory;
//
//    static{
//        try{//创建一个sqlSessionFactory工具
//            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
//
//            //从mybatis_config.xml创建数据流
//            InputStream stream = Resources.getResourceAsStream("Mapper/mybatis_config");
//
//            //通过工具构建一个sqlSessionFactory
//            sqlSessionFactory = builder.build(stream);
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//    //获取sqlSession
//    //设置为true则自动提交
//    public static SqlSession getSqlSession()
//    {return sqlSessionFactory.openSession(true);}
//
//}