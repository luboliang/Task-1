package com.jnshu.service;

import com.jnshu.impl.StudentImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {
//  static   ApplicationContext ac = new ClassPathXmlApplicationContext("server.xml");

        public static void main(String[] args) {
            System.setProperty("java.rmi.server.hostname", "118.190.199.167");
            ApplicationContext ac = new ClassPathXmlApplicationContext("server.xml");

            System.out.println("启动服务成功");
        }


//    public static void main(String[] args) {
//        System.setProperty("java.rmi.server.hostname", "127.0.0.1");
//        try {
//            LocateRegistry.createRegistry(1099);
//            Naming.bind("GreetService", new StudentImpl());
//            Naming.bind("GreetService", new StudentImpl());
//            System.out.println("服务启动成功");
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (AlreadyBoundException e) {
//            e.printStackTrace();
//        }
//
//
//    }

}