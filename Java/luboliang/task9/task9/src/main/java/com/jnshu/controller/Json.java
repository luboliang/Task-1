package com.jnshu.controller;


import com.jnshu.Util.Server;
import com.jnshu.model.Student;
import com.jnshu.service.StudentService;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;


@Controller
public class Json {
    public static Logger logger = Logger.getLogger(Controllers.class);
 StudentService  studentService = Server.getStudentService();

    public Json() throws RemoteException, NotBoundException, MalformedURLException {
    }

    @RequestMapping(value = "/jsons", method = RequestMethod.GET)
    public String select(Model model) {
        List<Student> list = studentService.select();

        if (list != null) {
            model.addAttribute("data", list);
            model.addAttribute("code", 200);
        } else {
            model.addAttribute("code", 201);

        }
        return "student";

    }

    @RequestMapping(value = "/json", method = RequestMethod.DELETE)
    public String delete(long id, Model model) {
        boolean a = studentService.delete(id);
        if (a) {

            model.addAttribute("code", 200);

        } else {
            model.addAttribute("code", 201);
        }
        return "student";
    }

    @RequestMapping(value = "/json", method = RequestMethod.POST)
    public String insert(Model model, Student student) {
        student.setCreate_at(System.currentTimeMillis());
        if (studentService.insert(student)) {
            model.addAttribute("code", 200);
        } else {
            model.addAttribute("code", 201);
        }


        return "student";
    }

    @RequestMapping(value = "/json", method = RequestMethod.PUT)
    public String update(Model model, Student student) {
        student.setUpdate_at(System.currentTimeMillis());
        if (studentService.update(student)) {
            model.addAttribute("code", 200);

        } else {
            model.addAttribute("code", 201);

        }
        return "student";
    }

    @RequestMapping(value = "/json", method = RequestMethod.GET)
    public String select(long id, Model model, Student student) {
        student = studentService.selectId(id);
        if (student != null) {
            model.addAttribute("code", 200);
            model.addAttribute("data", student);
        } else {
            model.addAttribute("code", 201);
        }

        return "json1";

    }

}
