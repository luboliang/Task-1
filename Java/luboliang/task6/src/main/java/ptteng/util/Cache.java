package ptteng.util;

import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public interface Cache {

    boolean set(String key, Object value, int t);

    Object get(String key);

    boolean update(String key, Object value, int t);

    boolean delete(String key);


}
