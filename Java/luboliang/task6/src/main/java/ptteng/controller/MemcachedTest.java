package ptteng.controller;

import com.danga.MemCached.MemCachedClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemcachedTest {
    @Autowired
    private MemCachedClient memCachedClient;
    @RequestMapping("/memcached")
    public void setMemcachedServer(){
        // 设置、获取
        Boolean target=memCachedClient.set("jz","258369");
        System.out.println("memcached 加载是否成功："+target);
        String result =(String)memCachedClient.get("jz");
        System.out.println("memcached 获取的结果："+result);
    // 替换
        System.out.println("键对应的值 - " + memCachedClient.get("jz"));
    target = memCachedClient.replace("jz","123456");
        System.out.println("replace 替换的状态:" + target);
        System.out.println("replace 替换的结果:" + memCachedClient.get("jz"));
    // 移除
        memCachedClient.delete("jz");
        System.out.println("delete 移除后结果："+memCachedClient.get("jz"));
}

}
