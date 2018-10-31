package com.jnshu.MyBatis;

import com.jnshu.model.Student;
import com.jnshu.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
public interface StudentDao {
    List<Student> all();

    int count();

    int number();

    int java();

    long register(User user);

    List<User> find(String user);
    List<User>findAll(User user);
}
