import com.aliyun.oss.model.OSSObjectSummary;
import com.jnshu.Util.API.Cos;
import com.jnshu.Util.API.Oos;
import com.jnshu.model.User;
import com.jnshu.service.StudentService;
import com.qcloud.cos.model.COSObjectSummary;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.List;


@RunWith(SpringRunner.class)
@ContextConfiguration({"classpath:spring/springDao.xml"})
public class testicon {
    @Autowired
    StudentService studentService;

    @Test
    public void test() throws InterruptedException {
        System.out.println("================================================开始了吗?");
        File file = new File("src/com/qqq.JPEG");
        String url = String.valueOf(Cos.getURL("qqq.JPEG", file));
        System.out.println("url==================" + url);
        User user = new User();
        user.setUser("zhaolinai");
        user.setPhoto(url);
        System.out.println(studentService.upIcon(user));
    }


    /**
     * 腾讯云迁移到阿里云
     */
    @Test
    public void d() {
        List<COSObjectSummary> objects = Cos.listObjects();
        for (int i = 0; i < objects.size(); i++) {
            COSObjectSummary objectSummary = objects.get(i);
            System.out.println("======================" + objectSummary);
            String key = objectSummary.getKey();
            String path = "E://1/" + key;
            System.out.println(Cos.download(key, path));
            File file = new File(path);
            System.out.println("迁移后的url" + Oos.geturl(key, file));
        }
    }

    @Test
    public void d2() throws InterruptedException {
        List<OSSObjectSummary> ossObjects = Oos.getobjects();
        System.out.println("=========================" + ossObjects);
        for (int i = 0; i < ossObjects.size(); i++) {
            OSSObjectSummary ossObjectSummary = ossObjects.get(i);
            System.out.println("=================" + ossObjectSummary);
            String key = ossObjectSummary.getKey();
            String path = "E://1/" + key;
            System.out.println(Oos.download(key, path));
            File file = new File(path);
            System.out.println("迁移后的url" + Cos.getURL(key, file));
        }

    }
}



