package ptteng.serviceImpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import ptteng.MyBatis.StudentDao;
import ptteng.model.Student;
import ptteng.service.StudentService;
import ptteng.util.Cache;

import javax.annotation.Resource;
import java.util.List;
@Service(value = "RedisImpl")
public class RedisImpl implements StudentService {

    public static Logger logger = Logger.getLogger(StudentImpl.class);
    @Resource(name = "redis")
    Cache cache;

    @Autowired
    StudentDao studentDao;

    @Override
    public boolean insert(Student student) {
        boolean b = studentDao.insert(student);
        if (b) {
            logger.info("数据库增加成功");
            if (cache.set("student" + student.getId(), student)) {
                logger.info("缓存也添加成功");
            }
        } else {
            logger.info("数据添加失败");
        }
        return b;
    }

    @Override
    public List<Student> list() {
        List<Student> students;
        students = studentDao.select();
        return students;
    }

    @Override
    public boolean delete(long id) {
        boolean a = studentDao.delete(id);
        if (a) {
            logger.info("数据库删除成功");
            if (cache.delete("student" + id)) {
                logger.info("缓存内已删除");
            } else {
                logger.info("缓存里没有或删除失败");
            }
        } else {
            logger.info("数据库没有这条信息或因为其他原因删除失败");
        }
        return a;
    }

    @Override
    public boolean update(Student student) {
        boolean a = studentDao.update(student);
        if (a) {
            logger.info("数据库修改成功");
            if (cache.update("student" + student.getId(), student)) {
                logger.info("缓存修改成功");
            } else {
                logger.info("缓存没有或因为其他原因修改失败");
            }
        } else {
            logger.info("数据库里面没有或修改失败");
        }
        return a;
    }

    @Override
    public List<Student> select() {
        return studentDao.select();
    }

    @Override
    public Student selectId(long id) {
        Object object = (cache.get("student" + id));
        if (object == null) {
            object = studentDao.selectId(id);
            cache.set("student" + id, object);
            logger.info("数据库里拿");
            return (Student) object;
        } else {
            logger.info("缓存里拿");
            return (Student) object;
        }
    }
}
