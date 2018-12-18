package com.jnshu.ApiImpl;

import com.jnshu.ApIDao.BcsDao;
import com.jnshu.Util.API.Oos;
import org.springframework.stereotype.Service;

import java.io.File;
@Service("oos")
public class OosImpl implements BcsDao{
    @Override
    public String getUrl(String key,File file) {
        return Oos.geturl(key,file);
    }
}
