package com.jnshu.controller;

import com.aliyuncs.exceptions.ClientException;
import com.jnshu.ApIDao.BcsDao;
import com.jnshu.Util.API.Email;
import com.jnshu.Util.API.Note;
import com.jnshu.Util.GetAc;
import com.jnshu.Util.cache.Cache;
import com.jnshu.Util.encryption.DESUtil;
import com.jnshu.Util.encryption.Md5;
import com.jnshu.model.User;
import com.jnshu.service.StudentService;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;

@Controller
public class LoginController {
    Md5 md5 = new Md5();
    @Resource(name = "redis")
    Cache cache;
    @Resource(name = "oos")
    BcsDao bcsDao;

    public static Logger logger = Logger.getLogger(LoginController.class);

    static ApplicationContext ac = GetAc.getBean();
    static StudentService  studentService = (StudentService) ac.getBean("StudentService");

    /**
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String resgister() {

        return "register";
    }

    /**
     * 手机注册功能实现
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String add(User user, String SmsCode) throws NoSuchAlgorithmException, UnsupportedEncodingException, ClientException {
        logger.info("code=====" + SmsCode);
        if (SmsCode == null) {
            return "error";
        }
        //打印输入的参数
        logger.info("新注册用户信息" + user);
        //对用户输入的密码进行加密
        String str = user.getPassword() + "password";
        //加密后的值赋给password
        user.setPassword(md5.EncoderByMd5(str));
        //判断用户名是否存在
        User user1 = studentService.find(user.getUser());
        logger.info("查找是否已经注册" + user1);
        if (user.getMobile() == 0 && user.getEmail() == null) {
            logger.info("没有输入邮箱或手机号");
            return "error";
        }
//        String code=null;
        //如果用户没有输入手机号,并且 输入了邮箱 验证码从缓存里取邮箱的验证码
        logger.info("再次打印用户输入的信息" + user);
        if (user.getMobile() == 0 && user.getEmail() != null) {
            String code = String.valueOf(cache.get(user.getEmail()));
            logger.info("缓存取出的code====" + code);
            logger.info("用户输入的code====" + SmsCode);
            //如果符合sql语句数据库里没有这个用户,以及手机号未被注册,并且验证码正确
            if (user1 == null && SmsCode.equals(code) && code != null) {
                //就像数据库里插入这条数据
                studentService.register(user);
                logger.info("新增用户的id是======" + user.getId());
                return "login";
            } else {
                logger.info("打印用户输入内容和缓存查到的code" + user + "============" + code + "============="
                        + SmsCode);
                return "error";
            }
            //否则如果没有输入邮箱,输入了手机号拿出短信验证码去对比
        } else if (user.getEmail() == null && user.getMobile() != 0) {
            String code = (String) cache.get(String.valueOf(user.getMobile()));
            //如果符合sql语句数据库里没有这个用户,以及手机号未被注册,并且验证码正确
            if (user1 == null && SmsCode.equals(code) && code != null) {
                studentService.register(user);
                return "login";
            }
            logger.info("打印用户输入内容和缓存查到的code" + user + "============" + code + "============="
                    + SmsCode);

            return "error";
        } else {

            logger.info("用户已存在");
            return "error";
        }
    }

    /**
     * 登陆页面
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        logger.info("gekk==============");

        return "login";
    }

    /**
     * 获取短信验证码
     *
     * @param user
     * @return
     * @throws ClientException
     */
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String code(User user) throws ClientException {
        logger.info("手机号========" + user.getMobile());
        //将手机号转换为String
        String mobile1 = String.valueOf(user.getMobile());
        logger.info("转化为字符串的手机号" + mobile1);
        String code = Note.sengSms(mobile1);
        logger.info("验证码是" + code);
        cache.set(mobile1, code, 5);

        //并存到redis
        return "redirect:/register";

    }

    /**
     * 邮箱注册页面
     *
     * @return
     */
    @RequestMapping(value = "/email/register", method = RequestMethod.GET)
    public String emailresgister() {

        return "emailRegiste";
    }

    /**
     * 获取邮箱验证码
     *
     * @param user
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/email/code", method = RequestMethod.POST)
    public String getEmailCode(User user) throws IOException {
        logger.info("用户的邮箱" + user.getEmail());
        //获取验证码
        String code = Email.emailCode(user);
        logger.info("code=======" + code);
        //存到redis邮箱为key,时常5分钟
        cache.set(user.getEmail(), code, 5);
        //重定向到邮箱注册页面

        return "redirect:/email/register";
    }

    @RequestMapping(value = "/icon", method = RequestMethod.GET)
    public String icon() {

        return "icon";
    }


    @RequestMapping(value = "/img", method = RequestMethod.POST)
    public String img(MultipartFile multipartFile, HttpServletResponse response, HttpServletRequest request) throws IOException, InterruptedException {
        File f = Mul2File(multipartFile);
        logger.info("f===========" + f.getAbsolutePath());
        logger.info("f===========" + f.getName());
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("name")) {
                String url = bcsDao.getUrl(System.currentTimeMillis() + cookie.getValue(), f);
                //删除临时文件
                logger.info("==验证是否删除啊成功==" + f.delete());
//                logger.info("url=========" + url.getPath());
                User user = new User();
                user.setUser(cookie.getValue());
                logger.info("imgurel================================" + url);
                user.setPhoto(url);
                logger.info("跟新数据库的头像是否成功" + studentService.upIcon(user));
                Cookie imgcookie = new Cookie("img", user.getPhoto());
                imgcookie.setMaxAge(30 * 60);
                imgcookie.setPath("/");
                logger.info("url=========" + url);
                response.addCookie(imgcookie);
                return "redirect:/home";

            }
        }
        return "redirect:/home";
    }


    private File Mul2File(MultipartFile file) {
        CommonsMultipartFile cf = (CommonsMultipartFile) file;
        DiskFileItem fi = (DiskFileItem) cf.getFileItem();
        File f = fi.getStoreLocation();
        return f;
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model, HttpServletRequest request, HttpServletResponse response, User user) throws Exception {
        //打印用户输入的参数
        logger.info("用户登陆信息" + user);
        String str;
        //如果用户输入密码,就给密码加密并打印加密后的密码.
        if (user.getPassword() != null) {
            str = user.getPassword() + "password";
            user.setPassword(md5.EncoderByMd5(str));
            logger.info(user.getPassword());
        }
        //如果用户名密码匹配根据用户名和密码查出来是同一个对象点击登陆跳到首页
        //否则重新登陆
        User user1 = studentService.findAll(user);
        if (user1 != null) {
            logger.info(user1);
            DESUtil desUtil = new DESUtil();
            String st1;
            String st2;
            //登陆时间加密
            st1 = desUtil.encryptFromLong(System.currentTimeMillis());
            logger.info(st1);
            //用户id加密
            st2 = desUtil.encryptFromLong(user.getId());
            logger.info(st2);
            String name = URLEncoder.encode("name", "utf8");
            String name1 = URLEncoder.encode(user.getUser(), "utf8");
//            HttpSession session = request.getSession();
            //把用户数据保存在session域对象中
//            session.setAttribute("loginName", user.getUser());
            Cookie namecookie = new Cookie(name, user1.getUser());// 新建一个Cookie对象
            logger.info("==========" + user.getUser());
            namecookie.setMaxAge(30 * 60);// 设置为30min，生命周期
            namecookie.setPath("/");//设置Cookie的使用路径
            response.addCookie(namecookie);// 保存cookie到客户端

            logger.info(name1);

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
