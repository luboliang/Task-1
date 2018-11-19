package ptteng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import ptteng.MyBatis.StudentDao;
import ptteng.model.Student;
import ptteng.service.StudentService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ptteng.serviceImpl.StudentImpl;

import javax.annotation.Resource;
import java.util.List;


@Controller
public class Json {
    public static Logger logger = Logger.getLogger(Json.class);
    @Resource(name = "RedisImpl")
     StudentService  studentService;
    @RequestMapping(value = "/student/list", method = RequestMethod.GET)
    public String select(Model model) {
        logger.info("select====");
        List<Student> studentList = studentService.list();
        if (studentList != null) {
            model.addAttribute("data", studentList);
            model.addAttribute("code", 200);
        } else {
            model.addAttribute("code", -201);

        }
        return "student";
    }

    @RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable long id, Model model) {
        logger.info("delete===id==="+id);
        boolean result = studentService.delete(id);
        if (result) {
            model.addAttribute("code", 200);
        } else {
            model.addAttribute("code", -201);
        }
        return "student";
    }

    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public String insert(Model model, Student student) {
        logger.info("insert=========="+student);
        student.setCreateAt(System.currentTimeMillis());
        student.setUpdateAt(System.currentTimeMillis());
        if (studentService.insert(student)) {
            model.addAttribute("code", 200);
        } else {
            model.addAttribute("code", -201);
        }
        return "student";
    }

    @RequestMapping(value = "/student", method = RequestMethod.PUT)
    public String update(Model model, Student student) {
        logger.info("update======="+student);
        student.setUpdateAt(System.currentTimeMillis());
        if (studentService.update(student)) {
            model.addAttribute("code", 200);
        } else {
            model.addAttribute("code", -201);
        }
        return "student";
    }

    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
    public String select(@PathVariable("id") long id, Model model) {
        logger.info("select========"+id);
      Student  student = studentService.selectId(id);
        if (student != null) {
            model.addAttribute("code", 200);
            model.addAttribute("data", student);
        } else {
            model.addAttribute("code", -201);
        }
        return "json1";
    }
}
