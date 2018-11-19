package ptteng;


import atg.taglib.json.util.JSONArray;
import atg.taglib.json.util.JSONObject;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ptteng.model.Person;
import ptteng.model.Student;
import ptteng.service.StudentService;
import ptteng.util.Cache;
import ptteng.util.SerializeUtil;


import javax.annotation.Resource;

import static org.springframework.data.repository.init.ResourceReader.Type.JSON;

@RunWith(SpringRunner.class)
@ContextConfiguration({"classpath:spring/*.xml"})
public class TestRedis {
    public static final Logger logger = Logger.getLogger(TestRedis.class);
    @Autowired
    private RedisTemplate redisTemplate;
    @Resource(name = "RedisImpl")
    StudentService studentService;
    @Resource(name = "redis")
    Cache cache;

    @Test
    public void test() {

//        String a = "lu";
//        Student student = new Student();
//        student.setName("lu");
//        student.setIcon("lu");
//        student.setOverview("lu");
//        student.setPosition("lu");
//
//        boolean cacheResult = cache.set("hello", student.toString(),8888);
//        System.out.println("cacheResult==="+cacheResult);
//
//        Object o =  cache.get("hello");
//        System.out.println("cache Object======="+o);
//        Student student1= (Student) o;
        redisTemplate.delete("lu");
        redisTemplate.delete("test");
        redisTemplate.delete("hello");
        System.out.println("............................");

    }

    @Test
    public void kd() {
//        Object object = redisTemplate.opsForValue().get("liii");
//        if (object != null) {
//            System.out.println("可以判断");
//        }
        // SerializeUtil serializeUtil = new SerializeUtil();
        Person person = new Person();
        person.setAge(123);
        //序列化
        //byte[] p = SerializeUtil.serialize(person);
        //System.out.println("序列化后等于==="+p);
        //String a=new String(p);
        // System.out.println("强转string 后===="+a);
        //set

        redisTemplate.opsForValue().set("test", person);


        System.out.println("cacheResult===" + "插入成功");

        //Person person1 = (Person) redisTemplate.opsForValue().get("test");
        System.out.println((Person) redisTemplate.opsForValue().get("test"));
        //System.out.println("查到了吗=========="+person1);

/*        //System.out.println("变成字节数组的结果"+p);
//        p=a.getBytes();
        //反序列化
       object= SerializeUtil.unserizlize(p);
        System.out.println("反序列化后=======" + object);
        int age = ((Person) object).getAge();
        System.out.println("age=======" + age);*/


    }

    @Test
    public void asd() {
        SerializeUtil serializeUtil = new SerializeUtil();
        String a = "你是阿萨德说的";
        byte[] b = a.getBytes();
        System.out.println("强壮byte" + b);
        a = new String(b);
        System.out.println("强转String" + a);
        System.out.println("再转回byte" + a.getBytes());
        System.out.println(a.getBytes());
        System.out.println(a.getBytes());
        System.out.println(a.getBytes());
        System.out.println(a.getBytes());
        a = new String(a);
        System.out.println(a);


    }

    @Test
    public void das(){
        Student student=new Student();
        student.setUpdateAt(System.currentTimeMillis());
        student.setCreateAt(System.currentTimeMillis());
        student.setId(333333);
    studentService.selectId(student.getId());
    studentService.insert(student);
        System.out.println(student.getId());

    }

}

