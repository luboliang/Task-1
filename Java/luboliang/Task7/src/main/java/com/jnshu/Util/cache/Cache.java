package com.jnshu.Util.cache;

import org.springframework.stereotype.Repository;

@Repository
public interface Cache {

    boolean set(String key, Object value, int t);

    Object get(String key);

    boolean update(String key, Object value, int t);

    boolean delete(String key);


    boolean update(String key, Object value);

    boolean set(String key, Object value);
}
