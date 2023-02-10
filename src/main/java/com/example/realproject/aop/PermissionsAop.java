package com.example.realproject.aop;

import com.example.realproject.entity.Users;
import com.example.realproject.entity.permissions;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service("permissionsAop")
@Aspect
@Order(1)
public class PermissionsAop {

    @Pointcut("execution(* com.example.realproject.Controller.*.*(..))")
    public void pointCut(){

    }

    //开启事务
    @Before("pointCut()")
    public void startTrans() throws ServletException, IOException {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra= (ServletRequestAttributes)requestAttributes;
        HttpServletResponse response=sra.getResponse();
        HttpServletRequest request = sra.getRequest();
        String requestURI=request.getRequestURI();
        if(!requestURI.contains("login")){
            Object userinfo = request.getSession().getAttribute("userinfo");
            if(userinfo==null){
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }
            else{
                int flag=0;
                Users users=(Users) userinfo;
                List<permissions> priList=users.getPriList();
                for(permissions pri:priList){
                    for (permissions sonPri:pri.getPriList()){
                        if(requestURI.endsWith(sonPri.getP_url())){
                            flag=1;
                            break;
                        }
                    }
                }
                if(flag==0){
                    request.getRequestDispatcher("/Error.jsp").forward(request, response);
                }
            }
        }
    }

    //提交
//    @AfterReturning("pointCut()")
//    public void Commit() {
//
//    }

    //回滚操作
//    @AfterThrowing("pointCut()")
//    public void rollback() {
//
//    }

}
