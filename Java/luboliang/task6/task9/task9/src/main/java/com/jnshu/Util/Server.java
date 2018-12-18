package com.jnshu.Util;

import com.jnshu.model.Profession;
import com.jnshu.service.ProfessionService;
import com.jnshu.service.StudentService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Server {
    static StudentService studentService;
    static ProfessionService professionService;

    public static StudentService getStudentService() throws RemoteException, NotBoundException, MalformedURLException {
        try {
// Math.random() 获取0-1之间的随机数, 乘以2 最大值为2, 范围变为0-2之间, 加1, 范围到1-2之间, int强制转换后就只有两个值: 1 和 2
            int random = (int) (Math.random() * 2 + 1);
            if (random == 1) {
                try {
                    studentService = (StudentService) Naming.lookup("rmi://118.190.199.167:1210/ServiceComponent/studentService");
                    return studentService;
                } catch (Exception e) {
                    studentService = (StudentService) Naming.lookup("rmi://118.190.199.167:1199/ServiceComponent/studentService");
                    return studentService;
                }
            } else {
                try {
                    studentService = (StudentService) Naming.lookup("rmi://118.190.199.167:1199/ServiceComponent/studentService");
                    return studentService;
                } catch (Exception e) {
                    studentService = (StudentService) Naming.lookup("rmi://118.190.199.167:1210/ServiceComponent/studentService");
                    return studentService;
                }
            }
        } catch (Exception e) {
            System.out.println("===========StudentService全over=============");
        }
        return studentService;
    }

    public static ProfessionService getprofessionService() throws RemoteException, NotBoundException, MalformedURLException {
        try {
// Math.random() 获取0-1之间的随机数, 乘以2 最大值为2, 范围变为0-2之间, 加1, 范围到1-2之间, int强制转换后就只有两个值: 1 和 2
            int random = (int) (Math.random() * 2 + 1);
            if (random == 1) {
                try {
                    professionService = (ProfessionService) Naming.lookup("rmi://118.190.199.167:1210/ServiceComponent/studentService");
                    return professionService;
                } catch (Exception e) {
                    professionService = (ProfessionService) Naming.lookup("rmi://118.190.199.167:1199/ServiceComponent/studentService");
                    return professionService;
                }
            } else {
                try {
                    professionService = (ProfessionService) Naming.lookup("rmi://118.190.199.167:1199/ServiceComponent/studentService");
                    return professionService;
                } catch (Exception e) {
                    professionService = (ProfessionService) Naming.lookup("rmi://118.190.199.167:1210/ServiceComponent/studentService");
                    return professionService;
                }
            }
        } catch (Exception e) {
            System.out.println("===========StudentService全over=============");
        }
        return professionService;
    }

}
