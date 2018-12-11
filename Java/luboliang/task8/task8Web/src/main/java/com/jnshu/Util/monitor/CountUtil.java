package com.jnshu.Util.monitor;

public class CountUtil {
    private static int LoginCount;

    public static void add() {
        LoginCount++;
    }

    public static void subtract() {
        LoginCount--;
    }

    public static int getLoginCount() {
        System.out.println("在线人数===="+LoginCount);
        return LoginCount;

    }


}
