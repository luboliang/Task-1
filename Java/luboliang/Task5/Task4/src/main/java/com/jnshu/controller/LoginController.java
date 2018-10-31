package com.jnshu.controller;

import com.jnshu.Util.DESUtil;
import com.jnshu.Util.Md5;
import com.jnshu.model.User;
import com.jnshu.service.StudentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Controller
public class LoginController {
    Md5 md5 = new Md5();

    public static Logger logger = Logger.getLogger(Controllers.class);
    @Autowired
    StudentService studentService;

    /**
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String resgister() {

        return "register";
    }

    /**
     * @param user
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String add(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //打印输入的参数
        logger.info("新注册用户信息" + user);
        //对用户输入的密码进行加密
        String str = user.getPassword()+user.getUser();
        //加密后的值赋给password
        user.setPassword(md5.EncoderByMd5(str));
        //判断用户名是否存在
        List<User> list = studentService.find(user.getUser());
        logger.info("查找是否已经注册" + list);
        //如果没有注册跳到登陆页面,否则重新注册
        if (list.size() == 0) {
            //向数据库添加账户信息密码是加密后的
            studentService.register(user);
            logger.info("新注册用户id==============" + user.getId());
            return "login";
        } else {
            logger.info("用户已存在");
            return "error";
        }

    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){

        return "login";
    }



    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model, HttpServletRequest request, HttpServletResponse response, Object o, User user) throws Exception {
        //打印用户输入的参数
        logger.info("用户登陆信息" + user);

        String str;
        //如果用户输入密码,就给密码加密并打印加密后的密码.
        if (user.getPassword() != null) {
            str = user.getPassword()+user.getUser();
            user.setPassword(md5.EncoderByMd5(str));
            logger.info(user.getPassword());
        }
        //如果用户名密码匹配根据用户名和密码查出来是同一个对象点击登陆跳到首页
        //否则重新登陆
        List<User> list = studentService.findAll(user);
        if (list.size() > 0) {
            logger.info(list);
            DESUtil desUtil=new DESUtil();
            String st1;
            String st2;
            //登陆时间加密
         st1=  desUtil.encryptFromLong(System.currentTimeMillis());
            logger.info(st1);
            //用户id加密
         st2= desUtil.encryptFromLong(user.getId());
            logger.info(st2);
            Cookie namecookie = new Cookie("name", user.getUser());// 新建一个Cookie对象
            namecookie.setMaxAge(30 * 60);// 设置为30min，生命周期
            namecookie.setPath("/");//设置Cookie的使用路径
            response.addCookie(namecookie);// 保存cookie到客户端

            return "redirect:/home";

        } else {

            return "errors";
        }


    }

    @RequestMapping(value = "/cookie", method = RequestMethod.GET)
    public String edit(HttpServletRequest httpServletRequest, HttpServletResponse response) {
        Cookie[] cookies = httpServletRequest.getCookies();

        if (cookies == null || cookies.length == 0) {
            return "login";
        } else {
            //遍历cookies
            for (Cookie c : cookies) {
                //如果cookies的name等于name将它的生命周期设置为0
                if (c.getName().equals("name"))
                    c.setMaxAge(0);
                // 保存cookie到客户端
                response.addCookie(c);
                logger.info("退出登陆返回首页");
            }
            return "redirect:/home";
        }
    }

}
