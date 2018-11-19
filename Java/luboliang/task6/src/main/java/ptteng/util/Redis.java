package ptteng.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service("redis")
public class Redis implements Cache {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean set(String key, Object value, int t) {
        try {
            redisTemplate.opsForValue().set(key, value, t, TimeUnit.MINUTES);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public boolean update(String key, Object value, int t) {
     return set(key, value, t);
    }

    @Override
    public boolean delete(String key) {
        try {
            redisTemplate.delete(key);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(String key, Object value) {
        return set(key,value);
    }

    @Override
    public boolean set(String key, Object value) {
        try{
            redisTemplate.opsForValue().set(key,value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}