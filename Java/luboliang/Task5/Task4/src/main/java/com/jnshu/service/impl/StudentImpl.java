package com.jnshu.service.impl;

import com.jnshu.MyBatis.StudentDao;
import com.jnshu.model.Student;
import com.jnshu.model.User;
import com.jnshu.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentImpl implements StudentService {
    @Autowired
    StudentDao studentDao;

    @Override
    public List<Student> all() {
        return studentDao.all();
    }

    @Override
    public int count() {
        return studentDao.count();
    }

    @Override
    public int number() {
        return studentDao.number();
    }

    @Override
    public int java() {
        return studentDao.java();
    }

    @Override
    public long register(User user) {
        return studentDao.register(user);
    }

    @Override
    public List<User> find(String user) {
        return  studentDao.find(user);
    }

    @Override
    public List<User> findAll(User user) {
        return studentDao.findAll(user);
    }
}
