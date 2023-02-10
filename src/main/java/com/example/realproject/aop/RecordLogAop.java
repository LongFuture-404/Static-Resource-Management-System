package com.example.realproject.aop;

import com.example.realproject.entity.Operation;
import com.example.realproject.entity.Users;
import com.example.realproject.service.LogService;
//import com.example.realproject.util.MyBatisUtil;
import com.example.realproject.service.UsersService;
import org.apache.ibatis.session.SqlSession;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 事务管理器
 */
@Service("recordLogAop")
@Aspect
@Order(2)
public class RecordLogAop {

    @Resource(name = "logServiceImpl")
    LogService logService;
    @Resource(name = "usersServiceImpl")
    UsersService usersService;

//    SqlSession sqlSession=MyBatisUtil.getSqlSession();
    @Pointcut("execution(* com.example.realproject.Controller.*.*(..))")
    public void pointCut(){

    }

    //开启事务
    @Before("pointCut()")
    public void startTrans(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra= (ServletRequestAttributes)requestAttributes;
        HttpServletRequest request = sra.getRequest();
        //获取ip
        String ip = request.getRemoteAddr() ;
        //获取用户操作
        String requestURI= request.getRequestURI();
        String Toperation=null;
        if(requestURI.contains("login")){
             Toperation="登录";
        }
        if(requestURI.contains("updatePW")){
            Toperation="更改密码";
        }
        if(requestURI.contains("UsersAdd")){
            Toperation="添加用户";
        }
        if(requestURI.contains("delUsers")){
            Toperation="删除用户";
        }
        if(requestURI.contains("updateUsers")){
            Toperation="修改用户";
        }
        //获取用户信息
        String phone;
        //如果记录删除的用户信息则添加以下条件
        //||requestURI.endsWith("delUsers.Handler")
        if(requestURI.endsWith("login.Handler")||requestURI.endsWith("delUsers.Handler")){
                phone=request.getParameter("phone");
        }else{
            Object userInfo = request.getSession().getAttribute("userinfo") ;
            phone =userInfo!=null?((Users)userInfo).getPhone():"null";
        }
        //获取操作时间
        Date date=new Date(System.currentTimeMillis());

        Operation operation=new Operation(ip,date,phone,Toperation);
        logService.AddLog(operation);

//        return MyBatisUtil.getSqlSession();
    }

    //提交
    @AfterReturning("pointCut()")
    public void Commit() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra= (ServletRequestAttributes)requestAttributes;
        HttpServletRequest request = sra.getRequest();
        if(usersService.UsersResult()!=null||request.getRequestURI().contains("delUsers")||request.getRequestURI().contains("updateUsers")) {
            logService.SuccessLog();
        }else {
            logService.FailLog();
        }
        usersService.RemoveResult();
        System.out.println("没有问题，提交");
//        sqlSession.commit();
    }

    //回滚操作
    @AfterThrowing("pointCut()")
    public void rollback() {
        logService.FailLog();
        System.out.println("有问题，回滚");
//        sqlSession.rollback();
    }

}
//代理模式 静态代理 动态代理
//业务层强化功能
