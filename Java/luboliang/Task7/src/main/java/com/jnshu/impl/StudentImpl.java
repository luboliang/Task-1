package com.jnshu.impl;

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
    public User find(String user) {
        return  studentDao.find(user);
    }

    @Override
    public User findAll(User user) {
        return studentDao.findAll(user);
    }

    @Override
    public boolean insert(Student student) {
        return studentDao.insert(student);
    }

    @Override
    public boolean delete(long id) {
        return studentDao.delete(id);
    }

    @Override
    public boolean update(Student student) {
        return studentDao.update(student);
    }

    @Override
    public List<Student> select() {
        return studentDao.select();
    }

    @Override
    public Student selectId(long id) {
        return studentDao.selectId(id);
    }

    @Override
    public boolean upIcon(User user) {
        return studentDao.upIcon(user);
    }
}
