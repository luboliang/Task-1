package ptteng.service;


import ptteng.model.Student;

import java.util.List;

public interface StudentService {

    boolean insert(Student student);

    boolean delete(long id);

    boolean update(Student student);

    List<Student> select();

    Student selectId(long id);
    List<Student>  list();
}
