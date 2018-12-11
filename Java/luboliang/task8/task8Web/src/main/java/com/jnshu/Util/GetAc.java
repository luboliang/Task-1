package com.jnshu.Util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Random;

public class GetAc {
    static ApplicationContext ac;
    static Random random = new Random();

    static int i = random.nextInt(2);

    public static ApplicationContext getBean() {
        System.out.println("随机数="+i);
        try {
            if (i == 1) {
                ac = new ClassPathXmlApplicationContext("spring/server1.xml");
                return ac;
            }
            ac = new ClassPathXmlApplicationContext("spring/server2.xml");
            return ac;
        } catch (Exception e) {
            e.printStackTrace();
            if (i == 2) {
                ac = new ClassPathXmlApplicationContext("spring/server1.xml");
                return ac;
            }
            ac = new ClassPathXmlApplicationContext("spring/server2.xml");
            return ac;

        }


    }


}
