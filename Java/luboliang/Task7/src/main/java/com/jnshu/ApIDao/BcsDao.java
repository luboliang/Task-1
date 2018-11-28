package com.jnshu.ApIDao;

import org.springframework.stereotype.Repository;

import java.io.File;
@Repository
public interface BcsDao  {
    String getUrl(String key, File file) throws InterruptedException;
}
