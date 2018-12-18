package com.jnshu.server;

import com.jnshu.impl.StudentImpl;
import org.apache.tuscany.sca.node.Node;
import org.apache.tuscany.sca.node.NodeFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
//        System.setProperty("java.rmi.server.hostname", "127.0.0.1");
        Node node=NodeFactory.newInstance().createNode("service.composite");
        node.start();
        System.out.println("服务启动成功");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
//  static   ApplicationContext ac = new ClassPathXmlApplicationContext("server.xml");

//        public static void main(String[] args) {
////            System.setProperty("java.rmi.server.hostname", "118.190.199.167");
////            ApplicationContext ac = new ClassPathXmlApplicationContext("server.xml");
////
////            System.out.println("启动服务成功");
//            Node node= NodeFactory.newInstance().createNode("service.composite");
//            node.start();
//            System.out.println("启动服务成功");
//        }


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