package com.jnshu.service;

import com.jnshu.model.Student;
import com.jnshu.model.User;
import org.springframework.stereotype.Component;

import java.util.List;


public interface StudentService {
    List<Student> all();

    int count();

    int number();

    int java();

    long register(User user);

    User find(String user);

    User findAll(User user);

    boolean insert(Student student);

    boolean delete(long id);

    boolean update(Student student);

    List<Student> select();

    Student selectId(long id);

    boolean upIcon(User user);
}
