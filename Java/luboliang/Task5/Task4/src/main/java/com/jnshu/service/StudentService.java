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
    List<User> find(String user);
    List<User>findAll(User user);
}
