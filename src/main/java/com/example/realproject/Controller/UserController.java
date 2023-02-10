package com.example.realproject.Controller;

import com.example.realproject.entity.Users;
import com.example.realproject.service.UsersService;
import org.apache.commons.beanutils.BeanUtils;
//import org.springframework.beans.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller("userController")
public class UserController {

    @Resource(name = "usersServiceImpl")
    UsersService usersservice;

    @ResponseBody
    @RequestMapping("/updatePW.Handler")
    public ModelAndView updatePWHandler(@RequestParam(name = "password",required = false) String password,
                                        @RequestParam(name = "newPassword",required = false) String newPassword,
                                        @RequestParam(name = "reNewPassword",required = false) String reNewPassword,
                                        HttpServletRequest request, HttpServletResponse response){

        ModelAndView modelAndView=new ModelAndView();

        Map<String,String[]> map=request.getParameterMap();
        Map<String,Object> resultmap=new HashMap<>();
        Users users=new Users();
        try {
            BeanUtils.populate(users,map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        Users selectUser=usersservice.UsersSelect(users.getPhone());
        if(selectUser==null){
            request.getSession().setAttribute("updatePW_msg", "请重试");
            modelAndView.setViewName("redirect:/password.jsp");
            return modelAndView;
        }
        if(users.getPassword().equals(selectUser.getPassword())){
            if (newPassword.equals(reNewPassword)) {
                if(password.equals(newPassword)){
                    request.getSession().setAttribute("updatePW_msg", "设置密码原密码相同");
                }
                else{
                    resultmap.put("phone", users.getPhone());
                    resultmap.put("result", 1);
                    usersservice.UsersAddResult(resultmap);

                    selectUser.setPassword(newPassword);
                    usersservice.UsersUpdatePW(selectUser);
                    request.getSession().setAttribute("updatePW_msg", "更改密码完成");
                }
            } else {
                request.getSession().setAttribute("updatePW_msg", "两次密码不一致请重新输入");
            }
        }else {
            request.getSession().setAttribute("updatePW_msg", "旧密码错误请重新输入");
        }
        modelAndView.setViewName("redirect:/password.jsp");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/UsersAdd.Handler")
    public ModelAndView UsersAddHandler(@RequestParam(name = "userRemi",required = false) String userRemi,
                                        @RequestParam(name = "password",required = false) String password,
                                        @RequestParam(name = "brithday",required = false) String brithday,
                                        @RequestParam(name = "avatar",required = false) MultipartFile uploadfile,
                                        HttpServletRequest request) throws IOException, InvocationTargetException, IllegalAccessException {
        ModelAndView modelAndView=new ModelAndView();
        Map<String,Object> resultmap=new HashMap<>();
        String Mphone=request.getParameter("Mphone");
        Users users = new Users();
        String avatar;
        if(uploadfile.getSize()!=0){
            String realpath = "F:\\MySQL\\ProgramData\\MySQL Server 8.0\\Uploads\\";
            String fileName = uploadfile.getOriginalFilename();
            File destFile = new File(realpath, fileName);
            uploadfile.transferTo(destFile);
            avatar = realpath + fileName;
            users.setAvatar(avatar);
        }

        Map<String,String[]> map=request.getParameterMap();
        DateConverter converter = new DateConverter();
        converter.setPattern(new String("yyyy-MM-dd"));
        ConvertUtils.register(converter, Date.class);
        if(userRemi.equals(password)) {
            if(brithday.equals("")){
                ConvertUtils.register(new DateConverter(null), Date.class);
                BeanUtils.populate(users, map);
                request.getSession().setAttribute("userRemi",userRemi);
                request.getSession().setAttribute("usersA",users);
                request.getSession().setAttribute("add_msg","请完成生日输入");
                modelAndView.setViewName("redirect:/userAdd.jsp");
            }
            else {
                try {
                    BeanUtils.populate(users, map);
                    usersservice.UsersInsert(users);

                    resultmap.put("phone",Mphone);
                    resultmap.put("result",1);
                    usersservice.UsersAddResult(resultmap);
                    request.getSession().setAttribute("userRemi",null);
                    request.getSession().setAttribute("usersA",null);
                    request.getSession().setAttribute("add_msg","添加用户成功");
                    modelAndView.setViewName("redirect:/findUserByPageHandler");
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        else {
            BeanUtils.populate(users, map);
            request.getSession().setAttribute("usersA",users);
            request.getSession().setAttribute("add_msg","密码不一致，请重新输入");
            modelAndView.setViewName("redirect:/userAdd.jsp");
        }
        return modelAndView;
    }
    @ResponseBody
    @RequestMapping("/updateUsers.Handler")
    public ModelAndView UpdateUsersHandler(@RequestParam(name = "avatar",required = false) MultipartFile uploadfile,
                                            HttpServletRequest request) throws IOException {
        ModelAndView modelAndView=new ModelAndView();

        Users users=new Users();
        String avatar;
        if(uploadfile.getSize()!=0){
            String realpath = "F:\\MySQL\\ProgramData\\MySQL Server 8.0\\Uploads\\";
            String fileName = uploadfile.getOriginalFilename();
            File destFile = new File(realpath, fileName);
            uploadfile.transferTo(destFile);
            avatar = realpath + fileName;
            users.setAvatar(avatar);
        }
        Map<String,String[]> map=request.getParameterMap();
        try {
            DateConverter converter = new DateConverter();
            converter.setPattern(new String("yyyy-MM-dd"));
            ConvertUtils.register(converter, Date.class);
            BeanUtils.populate(users,map);
            usersservice.UpdateUsers(users);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        request.getSession().setAttribute("add_msg","修改用户成功");
        modelAndView.setViewName("redirect:/findUserByPageHandler");

        return modelAndView;
    }
    @ResponseBody
    @RequestMapping("/delUsers.Handler")
    public ModelAndView DelUsersHandler( @RequestParam(value = "phone",required = false) String account
                                        ,HttpServletRequest request){
            ModelAndView modelAndView=new ModelAndView();

            usersservice.DeleUsers(account);
            request.getSession().setAttribute("add_msg","删除用户成功");
            modelAndView.setViewName("redirect:/findUserByPageHandler");

            return modelAndView;
    }
}
