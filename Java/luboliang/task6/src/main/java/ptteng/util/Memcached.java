package ptteng.util;

import com.danga.MemCached.MemCachedClient;
import org.apache.velocity.tools.config.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service("mem")
public class Memcached implements Cache{
    @Autowired
    private MemCachedClient memCachedClient;
    @Override
    public boolean set(String key, Object value, int t) {
        return memCachedClient.set(key,value);
    }

    @Override
    public Object get(String key) {
        return  memCachedClient.get(key);
    }
    @Override
    public boolean update(String key,Object value, int t) {
        return memCachedClient.replace(key,value, t);
    }

    @Override
    public boolean delete(String key) {
        return memCachedClient.delete(key);
    }

    @Override
    public boolean update(String key, Object value) {
        return memCachedClient.replace(key,value);
    }

    @Override
    public boolean set(String key, Object value) {
        return memCachedClient.set(key,value);
    }
}
