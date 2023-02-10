package com.example.realproject.Controller;

import com.example.realproject.entity.Users;
import com.example.realproject.entity.permissions;
import com.example.realproject.service.UsersService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("loginController")
public class LoginController {

    @Resource(name = "usersServiceImpl")
    UsersService service;

    /**
     * 登录操作控制[次数存储在数据库]
     * redirect:重定向
     */
//    @ResponseBody
//    @RequestMapping("/login.Handler")
//    public ModelAndView loginHandler(@RequestParam("verifyCode") String verifyCode,
//                                     HttpServletRequest request) {
//
//        ModelAndView modelAndView=new ModelAndView();
//
//        HttpSession session=request.getSession();
//        String checkCode_session= (String) session.getAttribute("CHECKCODE_SESSION");
//        session.removeAttribute("CHECKCODE_SESSION");
//
//        if(!checkCode_session.equalsIgnoreCase(verifyCode)){
//            request.getSession().setAttribute("login_msg","验证码错误");
//            modelAndView.setViewName("/login.jsp");
//            return modelAndView;
//        }
//
//        Map<String,String[]> map=request.getParameterMap();
//        Users users=new Users();
//        Date date=new Date(System.currentTimeMillis());
//        users.setLastTime(date);
//        try {
//            BeanUtils.populate(users,map);
//        } catch (IllegalAccessException | InvocationTargetException e) {
//            throw new RuntimeException(e);
//        }
//        Users loginUser=service.UsersLogin(users);
//
//        if(loginUser!=null){
//            if (loginUser.getState() == 1 && loginUser.getTryCount() > 0) {
//                request.getSession().setAttribute("users", loginUser);
//                modelAndView.setViewName("redirect:/permHandler");
//            } else {
//                request.getSession().setAttribute("TryCount", "剩余次数:"+loginUser.getTryCount());
//                if (loginUser.getTryCount() == 0) {
//                    request.getSession().setAttribute("login_msg", "登录次数为零");
//                } else {
//                    request.getSession().setAttribute("login_msg", "用户名或密码错误");
//                }
//                modelAndView.setViewName("/login.jsp");
//            }
//        }else {
//            request.getSession().setAttribute("login_msg", "该用户不存在");
//            modelAndView.setViewName("/login.jsp");
//        }
//
//        return modelAndView;
//    }

    /**
     * 登录操作控制[次数存储在session]
     */
    @ResponseBody
    @RequestMapping("/login.Handler")
    public ModelAndView login(@RequestParam(name = "phone") String acount,
                              @RequestParam(name = "password") String pwd,
                              @RequestParam("verifyCode") String verifyCode,
                              HttpServletRequest req) throws Exception {

        //先声明一个返回值
        ModelAndView mav = new ModelAndView();
        //调用service层  (账号以及密码同时传入service/dao/)
        Users user = service.UsersSelect(acount);
        Map<String,Object> resultmap=new HashMap<>();

        HttpSession session=req.getSession();
        String checkCode_session= (String) session.getAttribute("CHECKCODE_SESSION");
        session.removeAttribute("CHECKCODE_SESSION");
        mav.setViewName("/login.jsp");

        if(!checkCode_session.equalsIgnoreCase(verifyCode)){
            req.getSession().setAttribute("login_msg","验证码错误");
            return mav;
        }
        //判定账号是否存在
        else {
            if (user == null) {
                //说明这个账号是不存在的
                //返回跳转到登陆页面 -且提示账号不存在
                mav.addObject("login_msg", "账号不存在");
                return mav;
            }
            //判定账号是否锁定
            if (user.getState() == 0) {
                //跳转到登陆页面
                //提示账号锁定
                mav.addObject("login_msg", "账号已经锁定");
                return mav;
            }
            //执行账号存在的逻辑
            //判断该账号对应的密码是否匹配  （从浏览器端接收到的密码是否正确）
            if (!user.getPassword().equals(pwd)) {
                //密码不匹配怎么办 ？
                ServletContext servletContext = req.getServletContext();
                //剩余次数判定
                Object obj_limit = servletContext.getAttribute("limit_" + user.getPhone());
                //如果账号是第一次/第二次错误  ---只是需要提示-以及剩余次数
                //设置用户一共有N次机会
                int n = 5;
                if (obj_limit == null) {
                    //提示-剩余次数
                    mav.addObject("login_msg", "剩余登陆次数为" + (n - 1) + "次");
                    //设置session中用户的剩余次数
                    servletContext.setAttribute("limit_" + user.getPhone(), n - 1);
                    return mav;
                }
                //obj_limit不为null
                //目前剩余的次数
                int limit = (int) obj_limit;
                //如果减一等于 0 说明剩余次数用完了
                if (limit - 1 == 0) {
                    //说明当前是最后一次机会
                    //如果账号是第三次错误 --除了提示-该该用户的状态 -改为 0  -锁定
                    //锁定账号
                    service.UsersLock(user.getPhone());
                    //设置页面提示信息
                    mav.addObject("login_msg", "次数用完,账号锁定");
                    //清理session的限制
                    servletContext.removeAttribute("limit_" + user.getPhone());
                } else {
                    //limit-1>0  说明还有机会  //计算剩余次数-提示
                    mav.addObject("login_msg", "密码错误-剩余" + (limit - 1) + "次数");
                    //更新session
                    servletContext.setAttribute("limit_" + user.getPhone(), limit - 1);
                }
                //跳转到登陆页面 -提示密码不正确
                return mav;
            }
        }
        //密码正确的逻辑

        resultmap.put("phone",user.getPhone());
        resultmap.put("result",1);
        service.UsersAddResult(resultmap);

        List<permissions> userPri= service.getUserPri(user.getR_id());
        user.setPriList(userPri);
        //跳转 （到主页/登陆）
        req.getSession().setAttribute("userinfo", user);
        mav.setViewName("redirect:/index.jsp");
        return mav;
    }

}
