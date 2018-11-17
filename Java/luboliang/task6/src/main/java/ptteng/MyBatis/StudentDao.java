package ptteng.MyBatis;

import ptteng.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDao {

    boolean insert(Student student);

    boolean delete(long id);

    boolean update(Student student);

    List<Student> select();

    Student selectId(long id);


}
