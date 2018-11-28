package com.jnshu.MyBatis;

import com.jnshu.model.Student;
import com.jnshu.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDao {
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
