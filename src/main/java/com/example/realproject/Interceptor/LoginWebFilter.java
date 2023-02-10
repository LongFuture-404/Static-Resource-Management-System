package com.example.realproject.Interceptor;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 登录静态资源拦截器
 * 可以访问.css login.jsp .js .png
 * 不能访问 .jsp
 */
@WebFilter("/*")
public class LoginWebFilter implements Filter {

    /**
     * 在filter中赋值后在此获取
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest)servletRequest;
        String [] sr=new String[]{"/login.Handler","/login.jsp",".css",".js",".png",".jpg","/checkCode"};
        boolean flag=false;
        for(String url_suffix:sr){
            if (req.getRequestURI().endsWith(url_suffix)){
                flag=true;
            }
        }
        if(flag){
            filterChain.doFilter(servletRequest,servletResponse);//放行
        }else{
            Object userinfo=req.getSession().getAttribute("userinfo");
            if(userinfo==null){
                req.getRequestDispatcher("/login.jsp").forward(servletRequest,servletResponse);
            }
            else{
                filterChain.doFilter(servletRequest,servletResponse);
            }
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

