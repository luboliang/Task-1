package ptteng.util;

import org.springframework.stereotype.Repository;
import ptteng.model.Student;

import java.util.concurrent.TimeUnit;

@Repository
public interface Cache {

    boolean set(String key, Object value, int t);

    Object get(String key);

    boolean update(String key, Object value, int t);

    boolean delete(String key);


    boolean update(String key, Object value);

    boolean set(String key, Object value);
}
