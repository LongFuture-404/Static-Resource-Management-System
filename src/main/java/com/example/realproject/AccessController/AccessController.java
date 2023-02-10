package com.example.realproject.AccessController;

import com.example.realproject.entity.PageBean;
import com.example.realproject.entity.Users;
import com.example.realproject.service.UsersService;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.*;

/**
 * 只有@Controller才会生成对应的handler(servlet)
 */
@Controller("accessController")
public class AccessController {


    @Resource(name = "usersServiceImpl")
    UsersService usersservice;
    /**
     * 验证码生成提交控制
     */
    @ResponseBody
    @RequestMapping("/checkCode")
    public void checkCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int width=100;
        int height=50;

        BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);

        Graphics g=image.getGraphics();
        g.setColor(Color.YELLOW);
        g.fillRect(0,0,width,height);

        g.setColor(Color.BLUE);
        g.drawRect(0,0,width-1,height-1);

        String str="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random ran=new Random();
        StringBuilder sb=new StringBuilder();
        for(int i=1;i<=4;i++) {
            int index = ran.nextInt(str.length());
            char ch=str.charAt(index);
            sb.append(ch);

            g.drawString(ch+"",width/5*i,height/2);
        }
        String checkCode_session=sb.toString();
        request.getSession().setAttribute("CHECKCODE_SESSION",checkCode_session);

        g.setColor(Color.GREEN);
        for(int i=0;i<10;i++) {
            int x1 = ran.nextInt(width);
            int x2 = ran.nextInt(width);
            int y1 = ran.nextInt(height);
            int y2 = ran.nextInt(height);
            g.drawLine(x1, y1, x2, y2);
        }

        ImageIO.write(image,"jpg",response.getOutputStream());
    }

    @ResponseBody
    @RequestMapping("/selectByPhone")
    public String SelectByPhone(@RequestParam(value = "phone",required = false)String phone){
        try {
            if(usersservice.UsersSelect(phone)!=null) {
                return usersservice.UsersSelect(phone).getPhone();
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @ResponseBody
    @RequestMapping("/findUserByPageHandler")
    public ModelAndView findUserByPage(HttpServletRequest request){

        ModelAndView modelAndView=new ModelAndView();
        String currentPage=request.getParameter("currentPage");
        String rows=request.getParameter("rows");

        if(currentPage==null||"".equals(currentPage)){
            currentPage="1";
        }
        if(rows==null||"".equals(rows)){
            rows="7";
        }

        Map<String,String[]> condition=request.getParameterMap();

        PageBean<Users> pb=usersservice.findUserByPage(currentPage,rows,condition);
        request.getSession().setAttribute("pb",pb);
        request.getSession().setAttribute("condition",condition);
        modelAndView.setViewName("redirect:/userList.jsp");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/imageLoad")
    public void ImageLoad(@RequestParam(name = "phone",required = false) String account,
                          HttpServletResponse response, HttpServletRequest request) throws IOException {
        Map map=usersservice.UsersGetImage(account);
        if (map != null && map.size() > 0) {
            byte[] bytes = (byte[]) map.get("CONCAT(avatar)");
            response.setContentType("image/jpeg,image/png");//设置输出流内容格式为图片格式
            InputStream in1 = new ByteArrayInputStream(bytes);//将字节流转换为输入流
            IOUtils.copy(in1, response.getOutputStream());//将字节从InputStream复制到OutputStream中
        }
        String logoRealPathDir = request.getSession().getServletContext()
                .getRealPath("/WEB-INF/ymp57g.png");//获取默认图片路径
        response.setContentType("image/jpeg,image/png");
        InputStream is = Files.newInputStream(Paths.get(logoRealPathDir));

        IOUtils.copy(is, response.getOutputStream());
    }

    @ResponseBody
    @RequestMapping("/findUserHandler")
    public ModelAndView findUser(@RequestParam(value = "phone",required = false)String account,
                                 @RequestParam(value = "for",required = false)Integer For,
                                 HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView();

        Users users=usersservice.UsersSelect(account);
        request.getSession().setAttribute("users",users);
        if(For==1) {
            modelAndView.setViewName("redirect:/userView.jsp");
        }
        if(For==2) {
            modelAndView.setViewName("redirect:/userUpdate.jsp");
        }
        return modelAndView;
    }
}
