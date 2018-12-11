package com.jnshu.Util.API;

import com.aliyun.oss.model.OSSObjectSummary;
import com.jnshu.Util.API.Cos;
import com.jnshu.Util.API.Oos;
import com.qcloud.cos.model.COSObjectSummary;

import java.io.File;
import java.util.List;

public class Migration {
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
