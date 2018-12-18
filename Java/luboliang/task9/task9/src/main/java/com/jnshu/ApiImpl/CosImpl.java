package com.jnshu.ApiImpl;

import com.jnshu.ApIDao.BcsDao;
import com.jnshu.Util.API.Cos;
import org.springframework.stereotype.Service;

import java.io.File;
@Service("cos")
public class CosImpl implements BcsDao{
    @Override
    public String getUrl(String key, File file) throws InterruptedException {
        return Cos.getURL(key,file);
    }
}
